/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.janino;

import cpw.mods.fml.common.eventhandler.Event;
import io.github.somehussar.janinoloader.api.script.IScriptBodyBuilder;
import io.github.somehussar.janinoloader.api.script.IScriptClassBody;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NBTTags;
import noppes.npcs.api.handler.IHookDefinition;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.janino.JaninoHookResolver;
import noppes.npcs.janino.annotations.ParamName;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Sandbox;

public abstract class JaninoScript<T>
implements IScriptUnit {
    public ScriptContext context = ScriptContext.GLOBAL;
    public boolean errored = false;
    public String script = "";
    public List<String> externalScripts = new ArrayList<String>();
    public TreeMap<Long, String> console = new TreeMap();
    public boolean evaluated;
    public final Class<T> type;
    public final IScriptBodyBuilder<T> builder;
    protected final Sandbox sandbox;
    protected IScriptClassBody<T> scriptBody;
    private final JaninoHookResolver hookResolver = new JaninoHookResolver();
    private final String[] defaultImports;
    private String[] cachedImports;
    private Map<String, IHookDefinition> hookDefCache;
    private int lastHookRevision = -1;
    private int lastSeenGlobalRevision;

    protected JaninoScript(Class<T> type, String[] defaultImports, boolean isClient) {
        this.type = type;
        this.defaultImports = defaultImports != null ? defaultImports : new String[]{};
        this.builder = IScriptBodyBuilder.getBuilder(type, isClient ? CustomNpcs.getClientCompiler() : CustomNpcs.getDynamicCompiler()).setDefaultImports(this.defaultImports);
        Permissions permissions = new Permissions();
        permissions.setReadOnly();
        this.sandbox = new Sandbox(permissions);
        this.scriptBody = this.builder.build();
    }

    protected JaninoScript(Class<T> type, String[] defaultImports) {
        this(type, defaultImports, false);
    }

    protected String getHookContext() {
        return this.context.hookContext;
    }

    protected T getUnsafe() {
        return this.scriptBody.get();
    }

    public <R> R call(Function<T, R> fn) {
        this.ensureCompiled();
        Object t = this.getUnsafe();
        if (t == null) {
            return null;
        }
        try {
            return (R)this.sandbox.confine(() -> fn.apply(t));
        }
        catch (Exception e) {
            this.appendConsole("Runtime Error: " + e.getMessage());
            return null;
        }
    }

    public void run(Consumer<T> fn) {
        this.ensureCompiled();
        Object t = this.getUnsafe();
        if (t == null) {
            return;
        }
        try {
            this.sandbox.confine(() -> {
                fn.accept(t);
                return null;
            });
        }
        catch (Exception e) {
            this.appendConsole("Runtime Error: " + e.getMessage());
        }
    }

    public void unload() {
    }

    @Override
    public IScriptUnit createInstanceScope(IScriptHandler instanceHandler) {
        IScriptUnit instance = instanceHandler.createJaninoScriptUnit();
        if (instance == null) {
            return null;
        }
        instance.setScript(this.script);
        instance.setExternalScripts(new ArrayList<String>(this.externalScripts));
        return instance;
    }

    public void compileScript(String code) {
        try {
            this.cachedImports = this.collectImportsForCode(code);
            this.builder.setDefaultImports(this.cachedImports);
            this.scriptBody = this.builder.build();
            this.scriptBody.setScript(code);
        }
        catch (InternalCompilerException e) {
            this.appendConsole("Compilation error: " + e.getMessage());
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            if (e != cause) {
                this.appendConsole(cause.getMessage());
            }
            this.hookResolver.clearResolutionCaches();
        }
        catch (Exception e) {
            this.appendConsole("Error: " + e.getMessage());
            this.hookResolver.clearResolutionCaches();
        }
    }

    public void compileScript() {
        this.compileScript(this.getFullCode());
    }

    @Override
    public void ensureCompiled() {
        int global = ScriptController.Instance.globalRevision;
        if (!this.evaluated || global != this.lastSeenGlobalRevision) {
            this.compileScript();
            this.lastSeenGlobalRevision = global;
            this.evaluated = true;
        }
    }

    private String getFullCode() {
        StringBuilder sb = new StringBuilder();
        if (CustomNpcs.proxy.isRunLoadedScriptsFirst()) {
            this.appendExternalScripts(sb);
        }
        if (this.script != null && !this.script.isEmpty()) {
            sb.append(this.script).append("\n");
        }
        if (!CustomNpcs.proxy.isRunLoadedScriptsFirst()) {
            this.appendExternalScripts(sb);
        }
        return sb.toString();
    }

    private void appendExternalScripts(StringBuilder sb) {
        for (String name : this.externalScripts) {
            String code = ScriptController.Instance.scripts.get(name);
            if (code == null || code.isEmpty()) continue;
            sb.append(code).append("\n");
        }
    }

    private String[] collectImportsForCode(String code) {
        LinkedHashSet imports = new LinkedHashSet();
        Collections.addAll(imports, this.defaultImports);
        if (ScriptHookController.Instance == null) {
            return imports.toArray(new String[0]);
        }
        String context = this.getHookContext();
        if (context == null || context.isEmpty()) {
            return imports.toArray(new String[0]);
        }
        for (IHookDefinition def : ScriptHookController.Instance.getAllHookDefinitions(context)) {
            String[] hookImports;
            if (!code.contains(def.hookName() + "(") || (hookImports = def.requiredImports()) == null) continue;
            Collections.addAll(imports, hookImports);
        }
        return imports.toArray(new String[0]);
    }

    @Override
    public Object callFunction(String hookName, Object ... args) {
        if (hookName == null || hookName.isEmpty()) {
            return null;
        }
        this.ensureCompiled();
        T instance = this.scriptBody.get();
        if (instance == null) {
            return null;
        }
        Object[] invokeArgs = args == null ? new Object[]{} : args;
        MethodHandle handle = this.hookResolver.resolveHookHandle(hookName, invokeArgs, instance);
        if (handle == null) {
            return null;
        }
        try {
            return this.sandbox.confine(() -> {
                try {
                    Object[] fullArgs = new Object[invokeArgs.length + 1];
                    fullArgs[0] = instance;
                    System.arraycopy(invokeArgs, 0, fullArgs, 1, invokeArgs.length);
                    return handle.invokeWithArguments(fullArgs);
                }
                catch (Throwable e) {
                    this.appendConsole("Error in " + hookName + ": " + e.getMessage());
                    return null;
                }
            });
        }
        catch (Exception e) {
            this.appendConsole("Runtime error in " + hookName + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void run(EnumScriptType type, Event event) {
        if (type != null) {
            this.callFunction(type.function, event);
        }
    }

    @Override
    public void run(String hookName, Object event) {
        this.callFunction(hookName, event);
    }

    protected IHookDefinition getHookDefinition(String hookName) {
        if (ScriptHookController.Instance == null) {
            return null;
        }
        int rev = ScriptHookController.Instance.getHookRevision();
        if (this.hookDefCache == null || rev != this.lastHookRevision) {
            this.rebuildHookDefCache();
            this.lastHookRevision = rev;
        }
        return this.hookDefCache.get(hookName);
    }

    private void rebuildHookDefCache() {
        this.hookDefCache = new HashMap<String, IHookDefinition>();
        String context = this.getHookContext();
        if (context == null || context.isEmpty() || ScriptHookController.Instance == null) {
            return;
        }
        for (IHookDefinition def : ScriptHookController.Instance.getAllHookDefinitions(context)) {
            this.hookDefCache.put(def.hookName(), def);
        }
    }

    public List<String> getHookList() {
        LinkedHashSet<String> hooks = new LinkedHashSet<String>();
        String context = this.getHookContext();
        if (context != null && !context.isEmpty() && ScriptHookController.Instance != null) {
            hooks.addAll(ScriptHookController.Instance.getAllHooks(context));
        }
        if (this.type != null) {
            for (Method m : this.type.getDeclaredMethods()) {
                if (Modifier.isFinal(m.getModifiers()) || hooks.contains(m.getName())) continue;
                hooks.add(m.getName());
            }
        }
        return new ArrayList<String>(hooks);
    }

    @Override
    public String generateHookStub(String hookName, Object hookData) {
        Method method;
        IHookDefinition def = this.getHookDefinition(hookName);
        if (def != null) {
            String paramName;
            String typeName = def.getUsableTypeName();
            String[] params = def.paramNames();
            String string = paramName = params != null && params.length > 0 ? params[0] : "event";
            if (typeName != null && !typeName.isEmpty()) {
                return String.format("public void %s(%s %s) {\n    \n}\n", def.hookName(), typeName, paramName);
            }
        }
        if ((method = this.findInterfaceMethod(hookName)) != null) {
            return JaninoScript.generateMethodStub(method);
        }
        return String.format("public void %s(Object event) {\n    \n}\n", hookName);
    }

    private Method findInterfaceMethod(String methodName) {
        if (this.type == null || methodName == null) {
            return null;
        }
        for (Method m : this.type.getDeclaredMethods()) {
            if (!m.getName().equals(methodName) || Modifier.isFinal(m.getModifiers())) continue;
            return m;
        }
        return null;
    }

    public static String generateMethodStub(Method method) {
        String mods = Modifier.toString(method.getModifiers());
        if (!(mods = mods.replace("abstract ", "").replace("abstract", "").replace("default ", "").replace("default", "").trim()).isEmpty()) {
            mods = mods + " ";
        }
        String returnTypeStr = JaninoScript.getUsableTypeName(method.getReturnType());
        String name = method.getName();
        HashMap<String, Integer> typeCount = new HashMap<String, Integer>();
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < method.getParameters().length; ++i) {
            String paramName;
            Parameter p = method.getParameters()[i];
            String typeName = JaninoScript.getUsableTypeName(p.getType());
            ParamName annotation = p.getAnnotation(ParamName.class);
            if (annotation != null) {
                paramName = annotation.value();
            } else {
                String baseName = Character.toLowerCase(p.getType().getSimpleName().charAt(0)) + p.getType().getSimpleName().substring(1);
                int count = typeCount.getOrDefault(baseName, 0) + 1;
                typeCount.put(baseName, count);
                String string = paramName = count == 1 ? baseName : baseName + (count - 1);
            }
            if (i > 0) {
                params.append(", ");
            }
            params.append(typeName).append(" ").append(paramName);
        }
        Class<?> returnType = method.getReturnType();
        String body = returnType == Void.TYPE ? "" : (returnType.isPrimitive() ? (returnType == Boolean.TYPE ? "    return false;" : (returnType == Character.TYPE ? "    return '\\0';" : "    return 0;")) : "    return null;");
        return String.format("%s%s %s(%s) {\n%s\n}\n", mods, returnTypeStr, name, params, body);
    }

    private static String getUsableTypeName(Class<?> type) {
        if (type.isPrimitive() || type.getEnclosingClass() == null) {
            return type.getSimpleName();
        }
        StringBuilder sb = new StringBuilder();
        for (Class<?> current = type; current != null; current = current.getEnclosingClass()) {
            if (sb.length() > 0) {
                sb.insert(0, ".");
            }
            sb.insert(0, current.getSimpleName());
        }
        return sb.toString();
    }

    public String[] getDefaultImports() {
        return this.defaultImports;
    }

    private String[] getCachedImports() {
        if (this.cachedImports == null) {
            this.cachedImports = this.collectImportsForCode(this.getFullCode());
        }
        return this.cachedImports;
    }

    public Set<String> getHookTypes() {
        HashSet<String> types = new HashSet<String>();
        Collections.addAll(types, this.getCachedImports());
        return types;
    }

    private void addTypeAndEnclosingTypes(Set<String> types, Class<?> clazz) {
        if (clazz == null || clazz.isPrimitive()) {
            return;
        }
        types.add(clazz.getName());
        for (Class<?> enclosing = clazz.getDeclaringClass(); enclosing != null; enclosing = enclosing.getDeclaringClass()) {
            types.add(enclosing.getName());
        }
    }

    @Override
    public String getScript() {
        return this.script;
    }

    @Override
    public void setScript(String script) {
        this.script = script;
        this.evaluated = false;
        this.cachedImports = null;
        this.hookResolver.clearResolutionCaches();
    }

    @Override
    public List<String> getExternalScripts() {
        return this.externalScripts;
    }

    @Override
    public void setExternalScripts(List<String> scripts) {
        this.externalScripts = scripts;
        this.evaluated = false;
        this.cachedImports = null;
        this.hookResolver.clearResolutionCaches();
    }

    @Override
    public TreeMap<Long, String> getConsole() {
        return this.console;
    }

    @Override
    public void clearConsole() {
        this.console.clear();
    }

    @Override
    public void appendConsole(String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        long time = System.currentTimeMillis();
        if (this.console.containsKey(time)) {
            message = this.console.get(time) + "\n" + message;
        }
        this.console.put(time, message);
        while (this.console.size() > 40) {
            this.console.remove(this.console.firstKey());
        }
    }

    @Override
    public String getLanguage() {
        return "Java";
    }

    @Override
    public void setLanguage(String language) {
    }

    @Override
    public boolean hasCode() {
        return !this.externalScripts.isEmpty() || this.script != null && !this.script.isEmpty();
    }

    @Override
    public boolean hasErrored() {
        return this.errored;
    }

    @Override
    public void setErrored(boolean errored) {
        this.errored = errored;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("ScriptUnitType", "Janino");
        compound.func_74782_a("console", (NBTBase)NBTTags.NBTLongStringMap(this.console));
        compound.func_74782_a("externalScripts", (NBTBase)NBTTags.nbtStringList(this.externalScripts));
        compound.func_74778_a("script", this.script);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.console = NBTTags.GetLongStringMap(compound.func_150295_c("console", 10));
        this.setExternalScripts(NBTTags.getStringList(compound.func_150295_c("externalScripts", 10)));
        this.setScript(compound.func_74779_i("script"));
    }

    public static <T, S extends JaninoScript<T>> S readFromNBT(NBTTagCompound compound, S script, Supplier<S> factory) {
        if (compound.func_74764_b("Script")) {
            if (script == null) {
                script = (JaninoScript)factory.get();
            }
            script.readFromNBT(compound.func_74775_l("Script"));
        }
        return script;
    }

    public static <T> NBTTagCompound writeToNBT(NBTTagCompound compound, JaninoScript<T> script) {
        if (script != null) {
            compound.func_74782_a("Script", (NBTBase)script.writeToNBT(new NBTTagCompound()));
        }
        return compound;
    }
}

