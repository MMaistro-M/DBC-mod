/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  jdk.nashorn.api.scripting.ScriptObjectMirror
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers;

import cpw.mods.fml.common.eventhandler.Event;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NBTTags;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.scripted.NpcAPI;

public class ScriptContainer
implements IScriptUnit {
    private static final String lock = "lock";
    public static ScriptContainer Current;
    private static String CurrentType;
    public String fullscript = "";
    public String script = "";
    public TreeMap<Long, String> console = new TreeMap();
    public boolean errored = false;
    public List<String> scripts = new ArrayList<String>();
    private HashSet<String> unknownFunctions = new HashSet();
    public long lastCreated = 0L;
    private String currentScriptLanguage = null;
    public ScriptEngine engine = null;
    private IScriptHandler handler = null;
    private boolean evaluated = false;
    private static Method luaCoerce;
    private static Method luaCall;
    private CompiledScript compScript = null;
    private final HashMap<String, ScriptObjectMirror> cachedFunctions = new HashMap();
    private long lastGlobalsVersion = -1L;
    private String language = null;
    private Bindings instanceBindings = null;

    public ScriptContainer(IScriptHandler handler) {
        this.handler = handler;
    }

    @Override
    public IScriptUnit createInstanceScope(IScriptHandler instanceHandler) {
        if (this.engine == null) {
            this.setEngine(this.getLanguage());
        }
        ScriptContainer instance = new ScriptContainer(instanceHandler);
        instance.script = this.script;
        instance.scripts = new ArrayList<String>(this.scripts);
        instance.console = this.console;
        instance.language = this.language;
        instance.engine = this.engine;
        instance.currentScriptLanguage = this.currentScriptLanguage;
        instance.instanceBindings = this.engine.createBindings();
        return instance;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        String prevScript = this.script;
        this.script = compound.func_74779_i("Script");
        for (int i = 0; i < ConfigScript.ExpandedScriptLimit && compound.func_74764_b("ExpandedScript" + i); ++i) {
            this.script = this.script + compound.func_74779_i("ExpandedScript" + i);
        }
        if (!this.script.equals(prevScript)) {
            this.evaluated = false;
        }
        this.console = NBTTags.GetLongStringMap(compound.func_150295_c("Console", 10));
        this.scripts = NBTTags.getStringList(compound.func_150295_c("ScriptList", 10));
        this.lastCreated = 0L;
        if (compound.func_74764_b("ContainerLanguage")) {
            String lang = compound.func_74779_i("ContainerLanguage");
            this.language = lang.isEmpty() ? null : lang;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("ScriptUnitType", "ECMAScript");
        if (this.script.length() < 65535) {
            compound.func_74778_a("Script", this.script);
        } else if (ConfigScript.ExpandedScriptLimit > 0) {
            String str;
            int i = 0;
            for (int length = this.script.length(); length > 0 && i <= ConfigScript.ExpandedScriptLimit; ++i, length -= str.length()) {
                str = "";
                if (i == 0) {
                    compound.func_74778_a("Script", this.script.substring(0, 65535));
                    str = this.script.substring(0, 65535);
                    continue;
                }
                int end = length - 65535 >= 0 ? 65535 * (i + 1) : 65535 * i + length;
                str = this.script.substring(65535 * i, end);
                compound.func_74778_a("ExpandedScript" + (i - 1), str);
            }
        } else {
            compound.func_74778_a("Script", this.script.substring(0, 65535));
        }
        compound.func_74782_a("Console", (NBTBase)NBTTags.NBTLongStringMap(this.console));
        compound.func_74782_a("ScriptList", (NBTBase)NBTTags.nbtStringList(this.scripts));
        if (this.language != null) {
            compound.func_74778_a("ContainerLanguage", this.language);
        }
        return compound;
    }

    private String getFullCode() {
        if (!this.evaluated) {
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
            this.fullscript = sb.toString();
            this.unknownFunctions = new HashSet();
        }
        return this.fullscript;
    }

    private void appendExternalScripts(StringBuilder sb) {
        for (String loc : this.scripts) {
            String code = ScriptController.Instance.scripts.get(loc);
            if (code == null || code.isEmpty()) continue;
            sb.append(code).append("\n");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void run(ScriptEngine engine) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.engine.getContext().setWriter(pw);
        this.engine.getContext().setErrorWriter(pw);
        try {
            if (this.compScript == null && engine instanceof Compilable) {
                this.compScript = ((Compilable)((Object)engine)).compile(this.getFullCode());
            }
            if (this.compScript != null) {
                this.compScript.eval(engine.getContext());
            } else {
                engine.eval(this.getFullCode());
            }
        }
        catch (Throwable var14) {
            this.errored = true;
            var14.printStackTrace(pw);
        }
        finally {
            String errorString = sw.getBuffer().toString().trim();
            this.appendConsole(errorString);
            pw.close();
        }
    }

    @Override
    public void run(EnumScriptType type, Event event) {
        if (!CustomNpcs.proxy.isScriptingEnabled()) {
            return;
        }
        this.run(type.function, (Object)event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run(String type, Object event) {
        if (!CustomNpcs.proxy.isScriptingEnabled() || this.errored || !this.hasCode() || this.unknownFunctions.contains(type)) {
            return;
        }
        this.setEngine(this.getLanguage());
        if (this.engine == null) {
            return;
        }
        if (ScriptController.Instance.lastLoaded > this.lastCreated) {
            this.lastCreated = ScriptController.Instance.lastLoaded;
            this.evaluated = false;
            this.lastGlobalsVersion = -1L;
        }
        String string = lock;
        synchronized (lock) {
            boolean globalsChanged;
            Current = this;
            CurrentType = type;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            this.engine.getContext().setWriter(pw);
            this.engine.getContext().setErrorWriter(pw);
            long currentGlobalsVersion = NpcAPI.engineObjectsVersion;
            boolean bl = globalsChanged = this.lastGlobalsVersion != currentGlobalsVersion;
            if (this.instanceBindings != null) {
                if (globalsChanged) {
                    this.instanceBindings.put("API", (Object)NpcAPI.Instance());
                    for (Map.Entry<String, Object> objectEntry : NpcAPI.engineObjects.entrySet()) {
                        this.instanceBindings.put(objectEntry.getKey(), objectEntry.getValue());
                    }
                    this.lastGlobalsVersion = currentGlobalsVersion;
                }
            } else if (globalsChanged) {
                this.engine.put("API", NpcAPI.Instance());
                for (Map.Entry<String, Object> objectEntry : NpcAPI.engineObjects.entrySet()) {
                    this.engine.put(objectEntry.getKey(), objectEntry.getValue());
                }
                this.lastGlobalsVersion = currentGlobalsVersion;
            }
            try {
                if (!this.evaluated) {
                    this.cachedFunctions.clear();
                    if (this.instanceBindings != null) {
                        this.engine.eval(this.getFullCode(), this.instanceBindings);
                    } else {
                        this.engine.eval(this.getFullCode());
                    }
                    this.evaluated = true;
                }
                if (this.engine.getFactory().getLanguageName().equals("lua")) {
                    Object ob;
                    Object object = ob = this.instanceBindings != null ? this.instanceBindings.get(type) : this.engine.get(type);
                    if (ob != null) {
                        if (luaCoerce == null) {
                            luaCoerce = Class.forName("org.luaj.vm2.lib.jse.CoerceJavaToLua").getMethod("coerce", Object.class);
                            luaCall = ob.getClass().getMethod("call", Class.forName("org.luaj.vm2.LuaValue"));
                        }
                        luaCall.invoke(ob, luaCoerce.invoke(null, event));
                    } else {
                        this.unknownFunctions.add(type);
                    }
                } else {
                    ScriptObjectMirror func;
                    if (!this.cachedFunctions.containsKey(type)) {
                        if (this.instanceBindings != null) {
                            Object val = this.instanceBindings.get(type);
                            func = val instanceof ScriptObjectMirror ? (ScriptObjectMirror)val : null;
                        } else {
                            ScriptObjectMirror global = (ScriptObjectMirror)this.engine.getBindings(100);
                            func = (ScriptObjectMirror)global.get((Object)type);
                        }
                        this.cachedFunctions.put(type, func);
                    }
                    if ((func = this.cachedFunctions.get(type)) != null) {
                        func.call(null, new Object[]{event});
                    }
                }
            }
            catch (NoSuchMethodException e) {
                this.unknownFunctions.add(type);
            }
            catch (Throwable e) {
                this.errored = true;
                e.printStackTrace(pw);
            }
            finally {
                this.appendConsole(sw.getBuffer().toString().trim());
                pw.close();
                Current = null;
            }
            // ** MonitorExit[var3_3] (shouldn't be in output)
            return;
        }
    }

    @Override
    public void appendConsole(String message) {
        if (message != null && !message.isEmpty()) {
            long time = System.currentTimeMillis();
            if (this.console.containsKey(time)) {
                message = this.console.get(time) + "\n" + message;
            }
            this.console.put(time, message);
            while (this.console.size() > 40) {
                this.console.remove(this.console.firstKey());
            }
        }
    }

    public boolean isValid() {
        return this.evaluated && !this.errored;
    }

    @Override
    public boolean hasCode() {
        if (!this.scripts.isEmpty()) {
            return true;
        }
        return !this.getFullCode().isEmpty();
    }

    @Override
    public boolean isUnknownFunction(String type) {
        return this.unknownFunctions.contains(type);
    }

    public void setEngine(String scriptLanguage) {
        if (!Objects.equals(scriptLanguage, this.currentScriptLanguage)) {
            if (this.instanceBindings != null) {
                return;
            }
            this.currentScriptLanguage = scriptLanguage;
            if (ConfigScript.ScriptingECMA6 && scriptLanguage.equals("ECMAScript")) {
                System.setProperty("nashorn.args", "--language=es6");
            }
            this.engine = ScriptController.Instance.getEngineByName(scriptLanguage.toLowerCase());
            this.evaluated = false;
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
    }

    @Override
    public List<String> getExternalScripts() {
        return this.scripts;
    }

    @Override
    public void setExternalScripts(List<String> scripts) {
        this.scripts = scripts;
        this.evaluated = false;
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
    public String getLanguage() {
        return this.language != null ? this.language : "ECMAScript";
    }

    @Override
    public void setLanguage(String language) {
        if (!Objects.equals(this.language, language)) {
            this.language = language;
            this.evaluated = false;
        }
    }

    @Override
    public String generateHookStub(String hookName, Object hookData) {
        return "function " + hookName + "(event) {\n    \n}\n";
    }

    @Override
    public void ensureCompiled() {
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
    public boolean isJanino() {
        return false;
    }
}

