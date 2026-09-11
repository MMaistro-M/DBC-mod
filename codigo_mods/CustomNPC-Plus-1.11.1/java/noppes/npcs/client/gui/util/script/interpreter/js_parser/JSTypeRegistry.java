/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.api.handler.IHookDefinition;
import noppes.npcs.client.gui.util.script.interpreter.bridge.DtsJavaBridge;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.DtsModScanner;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeScriptDefinitionParser;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.scripted.NpcAPI;

public class JSTypeRegistry {
    private static JSTypeRegistry INSTANCE;
    private final Map<String, JSTypeInfo> types = new LinkedHashMap<String, JSTypeInfo>();
    private final Map<String, JSTypeInfo> typesByJavaFqn = new LinkedHashMap<String, JSTypeInfo>();
    private final Map<String, String> typeAliases = new HashMap<String, String>();
    private final Map<String, String> typeOrigins = new HashMap<String, String>();
    private final Map<String, String> aliasOrigins = new HashMap<String, String>();
    private final Map<String, Map<String, List<HookSignature>>> contextHooks = new LinkedHashMap<String, Map<String, List<HookSignature>>>();
    private final Map<String, List<HookSignature>> hooks = new LinkedHashMap<String, List<HookSignature>>();
    private static final String GLOBAL_NAMESPACE = "Global";
    private final Map<String, String> globalEngineObjects = new LinkedHashMap<String, String>();
    private final Map<String, JSTypeInfo> globalEngineImports = new LinkedHashMap<String, JSTypeInfo>();
    private final Map<String, JSMethodInfo> globalEngineFunctions = new LinkedHashMap<String, JSMethodInfo>();
    private static final Set<String> PRIMITIVES;
    private boolean initialized = false;
    private boolean initializationAttempted = false;
    private int lastSyncedHookRevision = -1;
    private String currentSource = null;

    public static JSTypeRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JSTypeRegistry();
        }
        return INSTANCE;
    }

    private JSTypeRegistry() {
    }

    public void initializeFromResources() {
        if (this.initialized || this.initializationAttempted) {
            return;
        }
        this.initializationAttempted = true;
        try {
            TypeScriptDefinitionParser parser = new TypeScriptDefinitionParser(this);
            List<DtsModScanner.DtsFileRef> dtsFiles = DtsModScanner.collectDtsFilesFromMods();
            if (dtsFiles.isEmpty()) {
                Set<String> fallbackFiles = this.findAllDtsFilesInResources("assets/customnpcs/api");
                if (fallbackFiles.contains("hooks.d.ts")) {
                    this.loadResourceFile(parser, "hooks.d.ts");
                }
                if (fallbackFiles.contains("index.d.ts")) {
                    this.loadResourceFile(parser, "index.d.ts");
                }
                for (String filePath : fallbackFiles) {
                    if (filePath.equals("hooks.d.ts") || filePath.equals("index.d.ts")) continue;
                    this.loadResourceFile(parser, filePath);
                }
            } else {
                DtsModScanner.logSummary(dtsFiles);
                this.loadDtsFiles(parser, dtsFiles);
            }
            this.resolveAllTypeParameters();
            this.resolveAllMemberTypes();
            this.resolveAllJSDocTypes();
            this.resolveInheritance();
            this.registerEngineGlobalObjects();
            this.registerES5Builtins();
            this.initialized = true;
        }
        catch (Exception e) {
            System.err.println("[JSTypeRegistry] Failed to load type definitions from resources: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void loadDtsFiles(TypeScriptDefinitionParser parser, List<DtsModScanner.DtsFileRef> dtsFiles) {
        DtsModScanner.sortDtsFiles(dtsFiles);
        for (DtsModScanner.DtsFileRef ref : dtsFiles) {
            try {
                InputStream is = ref.openStream();
                Throwable throwable = null;
                try {
                    if (is == null) continue;
                    String content = this.readFully(new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)));
                    this.setCurrentSource(ref.getOrigin());
                    parser.parseDefinitionFile(content, ref.getRelativePath());
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    if (is == null) continue;
                    if (throwable != null) {
                        try {
                            is.close();
                        }
                        catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        continue;
                    }
                    is.close();
                }
            }
            catch (Exception e) {
                System.err.println("[JSTypeRegistry] Failed to load " + ref.getOrigin() + ": " + e.getMessage());
            }
            finally {
                this.setCurrentSource(null);
            }
        }
    }

    private String readFully(BufferedReader reader) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private Set<String> findAllDtsFilesInResources(String resourcePath) {
        HashSet<String> dtsFiles = new HashSet<String>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(resourcePath);
            while (resources.hasMoreElements()) {
                int separatorIndex;
                URL resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    File directory = new File(resource.getFile());
                    this.scanDirectoryForDts(directory, "", dtsFiles);
                    continue;
                }
                if (!resource.getProtocol().equals("jar")) continue;
                String jarPath = resource.getPath();
                if (jarPath.startsWith("file:")) {
                    jarPath = jarPath.substring(5);
                }
                if ((separatorIndex = jarPath.indexOf("!")) != -1) {
                    jarPath = jarPath.substring(0, separatorIndex);
                }
                this.scanJarForDts(jarPath, resourcePath, dtsFiles);
            }
        }
        catch (Exception e) {
            System.err.println("[JSTypeRegistry] Error scanning for .d.ts files: " + e.getMessage());
        }
        return dtsFiles;
    }

    private void scanDirectoryForDts(File directory, String currentPath, Set<String> dtsFiles) {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            String filePath;
            String fileName = file.getName();
            String string = filePath = currentPath.isEmpty() ? fileName : currentPath + "/" + fileName;
            if (file.isDirectory()) {
                this.scanDirectoryForDts(file, filePath, dtsFiles);
                continue;
            }
            if (!fileName.endsWith(".d.ts")) continue;
            dtsFiles.add(filePath);
        }
    }

    private void scanJarForDts(String jarPath, String resourcePath, Set<String> dtsFiles) {
        try {
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (!entryName.startsWith(resourcePath + "/") || !entryName.endsWith(".d.ts")) continue;
                String relativePath = entryName.substring(resourcePath.length() + 1);
                dtsFiles.add(relativePath);
            }
            jarFile.close();
        }
        catch (Exception e) {
            System.err.println("[JSTypeRegistry] Error scanning JAR for .d.ts files: " + e.getMessage());
        }
    }

    private void loadResourceFile(TypeScriptDefinitionParser parser, String fileName) {
        block15: {
            try {
                ResourceLocation loc = new ResourceLocation("customnpcs", "api/" + fileName);
                InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(loc).func_110527_b();
                if (is == null) break block15;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));){
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    parser.parseDefinitionFile(sb.toString(), fileName);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void initializeFromDirectory(File directory) {
        if (this.initialized) {
            return;
        }
        try {
            TypeScriptDefinitionParser parser = new TypeScriptDefinitionParser(this);
            parser.parseDirectory(directory);
            this.resolveAllTypeParameters();
            this.resolveAllMemberTypes();
            this.resolveAllJSDocTypes();
            this.resolveInheritance();
            this.initialized = true;
        }
        catch (IOException e) {
            System.err.println("[JSTypeRegistry] Failed to load type definitions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initializeFromVsix(File vsixFile) {
        if (this.initialized) {
            return;
        }
        try {
            TypeScriptDefinitionParser parser = new TypeScriptDefinitionParser(this);
            parser.parseVsixArchive(vsixFile);
            this.resolveAllTypeParameters();
            this.resolveAllMemberTypes();
            this.resolveAllJSDocTypes();
            this.resolveInheritance();
            this.initialized = true;
        }
        catch (IOException e) {
            System.err.println("[JSTypeRegistry] Failed to load VSIX: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registerType(JSTypeInfo type) {
        String fullName = type.getFullName();
        String javaFqn = type.getJavaFqn();
        JSTypeInfo existingType = null;
        if (javaFqn != null && !javaFqn.isEmpty()) {
            existingType = this.typesByJavaFqn.get(javaFqn);
        }
        if (existingType == null && this.types.containsKey(fullName)) {
            existingType = this.types.get(fullName);
        }
        if (existingType != null) {
            this.mergeType(existingType, type, this.getCurrentSource());
            if (!this.types.containsKey(fullName)) {
                this.types.put(fullName, existingType);
                this.typeOrigins.put(fullName, this.getCurrentSource());
            }
            return;
        }
        this.types.put(fullName, type);
        this.typeOrigins.put(fullName, this.getCurrentSource());
        if (javaFqn != null && !javaFqn.isEmpty() && !this.typesByJavaFqn.containsKey(javaFqn)) {
            this.typesByJavaFqn.put(javaFqn, type);
        }
    }

    public JSTypeInfo getTypeByJavaFqn(String javaFqn) {
        if (javaFqn == null || javaFqn.isEmpty()) {
            return null;
        }
        return this.typesByJavaFqn.get(javaFqn);
    }

    public void registerTypeAlias(String alias, String fullType) {
        if (this.typeAliases.containsKey(alias)) {
            String existing = this.typeAliases.get(alias);
            if (!Objects.equals(existing, fullType)) {
                this.logAliasCollision(alias, existing, fullType, this.getCurrentSource());
            }
            return;
        }
        this.typeAliases.put(alias, fullType);
        this.aliasOrigins.put(alias, this.getCurrentSource());
    }

    public void registerHook(String namespace, String functionName, String paramName, String paramType) {
        HookSignature sig = new HookSignature(paramName, paramType, null, namespace);
        this.contextHooks.computeIfAbsent(namespace, k -> new LinkedHashMap()).computeIfAbsent(functionName, k -> new ArrayList()).add(sig);
        this.hooks.computeIfAbsent(functionName, k -> new ArrayList()).add(sig);
    }

    public JSTypeInfo getType(String name) {
        return this.getType(name, new HashSet<String>());
    }

    private JSTypeInfo getType(String name, Set<String> visited) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        String baseName = name.replace("[]", "").trim();
        if (PRIMITIVES.contains(baseName)) {
            return null;
        }
        if (visited.contains(baseName)) {
            if (this.types.containsKey(baseName)) {
                return this.types.get(baseName);
            }
            return null;
        }
        visited.add(baseName);
        if (this.types.containsKey(baseName)) {
            return this.types.get(baseName);
        }
        if (this.typeAliases.containsKey(baseName)) {
            String resolved = this.typeAliases.get(baseName);
            if (resolved.equals(baseName)) {
                return null;
            }
            return this.getType(resolved, visited);
        }
        if (this.globalEngineImports.containsKey(baseName)) {
            return this.globalEngineImports.get(baseName);
        }
        for (JSTypeInfo type : this.types.values()) {
            if (!type.getSimpleName().equals(baseName)) continue;
            return type;
        }
        return null;
    }

    public boolean isPrimitive(String typeName) {
        return PRIMITIVES.contains(typeName);
    }

    public List<HookSignature> getHookSignatures(String functionName) {
        return this.hooks.getOrDefault(functionName, Collections.emptyList());
    }

    public boolean isHook(String functionName) {
        return this.hooks.containsKey(functionName);
    }

    public String getHookParameterType(String functionName) {
        List<HookSignature> sigs = this.hooks.get(functionName);
        if (sigs != null && !sigs.isEmpty()) {
            return sigs.get((int)0).paramType;
        }
        return null;
    }

    public List<HookSignature> getHookSignatures(String namespace, String functionName) {
        List<HookSignature> sigs;
        Map<String, List<HookSignature>> globalMap;
        List<HookSignature> sigs2;
        Map<String, List<HookSignature>> namespaceMap = this.contextHooks.get(namespace);
        if (namespaceMap != null && (sigs2 = namespaceMap.get(functionName)) != null && !sigs2.isEmpty()) {
            return sigs2;
        }
        if (!GLOBAL_NAMESPACE.equals(namespace) && (globalMap = this.contextHooks.get(GLOBAL_NAMESPACE)) != null && (sigs = globalMap.get(functionName)) != null && !sigs.isEmpty()) {
            return sigs;
        }
        return Collections.emptyList();
    }

    public boolean isHook(String namespace, String functionName) {
        return !this.getHookSignatures(namespace, functionName).isEmpty();
    }

    public String getHookParameterType(String namespace, String functionName) {
        List<HookSignature> sigs = this.getHookSignatures(namespace, functionName);
        if (!sigs.isEmpty()) {
            return sigs.get((int)0).paramType;
        }
        return null;
    }

    public Set<String> getHookNames(String namespace) {
        Map<String, List<HookSignature>> globalMap;
        HashSet<String> names = new HashSet<String>();
        Map<String, List<HookSignature>> namespaceMap = this.contextHooks.get(namespace);
        if (namespaceMap != null) {
            names.addAll(namespaceMap.keySet());
        }
        if (!GLOBAL_NAMESPACE.equals(namespace) && (globalMap = this.contextHooks.get(GLOBAL_NAMESPACE)) != null) {
            names.addAll(globalMap.keySet());
        }
        return names;
    }

    public Map<String, Map<String, List<HookSignature>>> getAllContextHooks() {
        return Collections.unmodifiableMap(this.contextHooks);
    }

    public List<HookSignature> getHookSignatures(List<String> namespaces, String functionName) {
        List<HookSignature> sigs;
        for (String namespace : namespaces) {
            List<HookSignature> sigs2;
            Map<String, List<HookSignature>> namespaceMap = this.contextHooks.get(namespace);
            if (namespaceMap == null || (sigs2 = namespaceMap.get(functionName)) == null || sigs2.isEmpty()) continue;
            return sigs2;
        }
        Map<String, List<HookSignature>> globalMap = this.contextHooks.get(GLOBAL_NAMESPACE);
        if (globalMap != null && (sigs = globalMap.get(functionName)) != null && !sigs.isEmpty()) {
            return sigs;
        }
        return Collections.emptyList();
    }

    public boolean isHook(List<String> namespaces, String functionName) {
        return !this.getHookSignatures(namespaces, functionName).isEmpty();
    }

    public String getHookParameterType(List<String> namespaces, String functionName) {
        List<HookSignature> sigs = this.getHookSignatures(namespaces, functionName);
        if (!sigs.isEmpty()) {
            return sigs.get((int)0).paramType;
        }
        return null;
    }

    public Set<String> getHookNames(List<String> namespaces) {
        HashSet<String> names = new HashSet<String>();
        for (String namespace : namespaces) {
            Map<String, List<HookSignature>> namespaceMap = this.contextHooks.get(namespace);
            if (namespaceMap == null) continue;
            names.addAll(namespaceMap.keySet());
        }
        Map<String, List<HookSignature>> globalMap = this.contextHooks.get(GLOBAL_NAMESPACE);
        if (globalMap != null) {
            names.addAll(globalMap.keySet());
        }
        return names;
    }

    public void resolveAllTypeParameters() {
        for (JSTypeInfo type : this.types.values()) {
            type.resolveTypeParameters();
        }
    }

    public void resolveAllMemberTypes() {
        for (JSTypeInfo type : this.types.values()) {
            type.resolveMemberTypes();
        }
    }

    public void resolveAllJSDocTypes() {
        for (JSTypeInfo type : this.types.values()) {
            type.resolveJSDocTypes();
        }
    }

    public void resolveInheritance() {
        Iterator<JSTypeInfo> iterator = this.types.values().iterator();
        while (iterator.hasNext()) {
            JSTypeInfo type;
            JSTypeInfo child = type = iterator.next();
            while (child != null && child.getExtendsType() != null && child.getResolvedParent() == null) {
                JSTypeInfo parent = this.getType(child.getExtendsType());
                if (parent != null) {
                    child.setResolvedParent(parent);
                }
                child = parent;
            }
        }
    }

    public Collection<JSTypeInfo> getAllTypes() {
        return this.types.values();
    }

    public Set<String> getTypeNames() {
        return this.types.keySet();
    }

    public Set<String> getHookNames() {
        return this.hooks.keySet();
    }

    public Map<String, List<HookSignature>> getAllHooks() {
        return this.hooks;
    }

    public void registerGlobalObject(String name, String typeName) {
        this.globalEngineObjects.put(name, typeName);
    }

    public String getGlobalObjectType(String name) {
        return this.globalEngineObjects.get(name);
    }

    public boolean isGlobalObject(String name) {
        return this.globalEngineObjects.containsKey(name);
    }

    public Map<String, String> getGlobalEngineObjects() {
        return Collections.unmodifiableMap(this.globalEngineObjects);
    }

    private void registerEngineGlobalObjects() {
        HashMap<String, Object> engineObjects = new HashMap<String, Object>(NpcAPI.engineObjects);
        engineObjects.put("API", NpcAPI.Instance());
        if (engineObjects != null) {
            for (Map.Entry entry : engineObjects.entrySet()) {
                String name = (String)entry.getKey();
                Object obj = entry.getValue();
                if (obj == null) continue;
                String concreteClassName = obj.getClass().getSimpleName();
                String typeName = this.mapConcreteToAbstractClassName(concreteClassName);
                this.registerGlobalObject(name, typeName);
            }
        }
    }

    private String mapConcreteToAbstractClassName(String concreteClassName) {
        if (this.types.containsKey(concreteClassName)) {
            return concreteClassName;
        }
        String abstractName = "Abstract" + concreteClassName;
        if (this.types.containsKey(abstractName)) {
            return abstractName;
        }
        return concreteClassName;
    }

    private void registerES5Builtins() {
        this.registerGlobalIfTypeExists("Math", "Math");
        this.registerGlobalIfTypeExists("JSON", "JSON");
        this.registerGlobalIfTypeExists("Date", "Date");
        this.registerGlobalIfTypeExists("Array", "Array");
        this.registerGlobalIfTypeExists("Object", "Object");
        this.registerGlobalIfTypeExists("String", "String");
        this.registerGlobalIfTypeExists("Number", "Number");
        this.registerGlobalIfTypeExists("Boolean", "Boolean");
        this.registerGlobalIfTypeExists("RegExp", "RegExp");
        this.registerGlobalIfTypeExists("Error", "Error");
        this.extractGlobalFunctions();
    }

    private void registerGlobalIfTypeExists(String globalName, String typeName) {
        if (this.types.containsKey(typeName)) {
            this.globalEngineImports.put(globalName, this.types.remove(typeName));
        }
    }

    private void extractGlobalFunctions() {
        JSTypeInfo globalFns = this.types.get("GlobalFunctions");
        if (globalFns == null) {
            return;
        }
        for (Map.Entry<String, JSMethodInfo> entry : globalFns.getMethods().entrySet()) {
            String key = entry.getKey();
            String baseName = key.contains("$") ? key.substring(0, key.indexOf(36)) : key;
            this.globalEngineFunctions.putIfAbsent(baseName, entry.getValue());
        }
        this.types.remove("GlobalFunctions");
    }

    public JSTypeInfo getGlobalImportType(String name) {
        return this.globalEngineImports.get(name);
    }

    public boolean isGlobalImport(String name) {
        return this.globalEngineImports.containsKey(name);
    }

    public Map<String, JSTypeInfo> getGlobalEngineImports() {
        return Collections.unmodifiableMap(this.globalEngineImports);
    }

    public JSMethodInfo getGlobalEngineFunction(String name) {
        return this.globalEngineFunctions.get(name);
    }

    public boolean isGlobalEngineFunction(String name) {
        return this.globalEngineFunctions.containsKey(name);
    }

    public Map<String, JSMethodInfo> getGlobalEngineFunctions() {
        return Collections.unmodifiableMap(this.globalEngineFunctions);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void clear() {
        this.types.clear();
        this.typesByJavaFqn.clear();
        this.typeAliases.clear();
        this.typeOrigins.clear();
        this.aliasOrigins.clear();
        this.hooks.clear();
        this.contextHooks.clear();
        this.globalEngineObjects.clear();
        this.globalEngineImports.clear();
        this.globalEngineFunctions.clear();
        DtsJavaBridge.clearCache();
        this.initialized = false;
        this.initializationAttempted = false;
        this.currentSource = null;
        this.lastSyncedHookRevision = -1;
    }

    public void syncHooksFromScriptHookControllerIfNeeded() {
        try {
            if (ScriptHookController.Instance == null) {
                return;
            }
            int currentRevision = ScriptHookController.Instance.getHookRevision();
            if (currentRevision == this.lastSyncedHookRevision) {
                return;
            }
            this.hooks.clear();
            this.contextHooks.clear();
            HashSet<String> seen = new HashSet<String>();
            String[] contexts = ScriptHookController.Instance.getContexts();
            if (contexts != null) {
                for (String context : contexts) {
                    List<IHookDefinition> defs;
                    if (context == null || context.isEmpty() || (defs = ScriptHookController.Instance.getAllHookDefinitions(context)) == null || defs.isEmpty()) continue;
                    for (IHookDefinition def : defs) {
                        String hookName;
                        if (def == null || (hookName = def.hookName()) == null || hookName.isEmpty()) continue;
                        String usableTypeName = def.getUsableTypeName();
                        String namespace = usableTypeName != null && usableTypeName.contains(".") ? usableTypeName.substring(0, usableTypeName.indexOf(46)) : GLOBAL_NAMESPACE;
                        String paramType = usableTypeName != null ? usableTypeName : "any";
                        String[] paramNames = def.paramNames();
                        String paramName = paramNames != null && paramNames.length > 0 && paramNames[0] != null && !paramNames[0].isEmpty() ? paramNames[0] : "event";
                        String dedupKey = namespace + "|" + hookName + "|" + paramType + "|" + paramName;
                        if (!seen.add(dedupKey)) continue;
                        this.registerHook(namespace, hookName, paramName, paramType);
                    }
                }
            }
            this.lastSyncedHookRevision = currentRevision;
        }
        catch (Throwable t) {
            System.err.println("[JSTypeRegistry] Failed to sync runtime hooks: " + t.getMessage());
            t.printStackTrace();
        }
    }

    private void setCurrentSource(String source) {
        this.currentSource = source;
    }

    private String getCurrentSource() {
        return this.currentSource == null ? "unknown" : this.currentSource;
    }

    private void logTypeCollision(String fullName, String incomingSource) {
        String existingSource = this.typeOrigins.getOrDefault(fullName, "unknown");
    }

    private void logAliasCollision(String alias, String existingType, String incomingType, String incomingSource) {
        String existingSource = this.aliasOrigins.getOrDefault(alias, "unknown");
    }

    private String findOwnMethodKeyBySignature(JSTypeInfo type, JSMethodInfo candidate) {
        String methodName;
        Map<String, JSMethodInfo> methods = type.getMethods();
        JSMethodInfo direct = methods.get(methodName = candidate.getName());
        if (direct != null && this.hasSameParameterSignature(direct, candidate)) {
            return methodName;
        }
        int index = 1;
        String overloadKey = methodName + "$" + index;
        while (methods.containsKey(overloadKey)) {
            JSMethodInfo overload = methods.get(overloadKey);
            if (this.hasSameParameterSignature(overload, candidate)) {
                return overloadKey;
            }
            overloadKey = methodName + "$" + ++index;
        }
        return null;
    }

    private boolean hasSameParameterSignature(JSMethodInfo left, JSMethodInfo right) {
        if (left == null || right == null) {
            return false;
        }
        if (left.getParameterCount() != right.getParameterCount()) {
            return false;
        }
        List<JSMethodInfo.JSParameterInfo> leftParams = left.getParameters();
        List<JSMethodInfo.JSParameterInfo> rightParams = right.getParameters();
        for (int i = 0; i < leftParams.size(); ++i) {
            String rightType;
            String leftType = leftParams.get(i).getType();
            if (Objects.equals(leftType, rightType = rightParams.get(i).getType())) continue;
            return false;
        }
        return true;
    }

    private void mergeType(JSTypeInfo existing, JSTypeInfo incoming, String incomingSource) {
        int methodsMerged = 0;
        int fieldsMerged = 0;
        for (Map.Entry<String, JSMethodInfo> entry : incoming.getMethods().entrySet()) {
            JSMethodInfo incomingMethod = entry.getValue();
            String methodName = incomingMethod.getName();
            String existingKey = this.findOwnMethodKeyBySignature(existing, incomingMethod);
            if (existingKey != null) {
                existing.getMethods().put(existingKey, incomingMethod);
                incomingMethod.setContainingType(existing);
                ++methodsMerged;
                continue;
            }
            existing.addMethod(incomingMethod);
            ++methodsMerged;
        }
        for (Map.Entry<String, Object> entry : incoming.getFields().entrySet()) {
            String fieldName = entry.getKey();
            JSFieldInfo incomingField = (JSFieldInfo)entry.getValue();
            existing.getFields().put(fieldName, incomingField);
            incomingField.setContainingType(existing);
            ++fieldsMerged;
        }
        if (existing.getJavaFqn() == null && incoming.getJavaFqn() != null) {
            existing.setJavaFqn(incoming.getJavaFqn());
        }
        if (existing.getJsDocInfo() == null && incoming.getJsDocInfo() != null) {
            existing.setJsDocInfo(incoming.getJsDocInfo());
        }
        if (existing.getExtendsType() == null && incoming.getExtendsType() != null) {
            existing.setExtends(incoming.getExtendsType());
        }
    }

    static {
        PRIMITIVES = new HashSet<String>(Arrays.asList("number", "string", "boolean", "void", "any", "null", "undefined", "never", "object", "byte", "short", "int", "integer", "long", "float", "double", "char", "bool"));
    }

    public static class HookSignature {
        public final String paramName;
        public final String paramType;
        public final String doc;
        public final String namespace;

        public HookSignature(String paramName, String paramType) {
            this(paramName, paramType, null, JSTypeRegistry.GLOBAL_NAMESPACE);
        }

        public HookSignature(String paramName, String paramType, String doc) {
            this(paramName, paramType, doc, JSTypeRegistry.GLOBAL_NAMESPACE);
        }

        public HookSignature(String paramName, String paramType, String doc, String namespace) {
            this.paramName = paramName;
            this.paramType = paramType;
            this.doc = doc;
            this.namespace = namespace != null ? namespace : JSTypeRegistry.GLOBAL_NAMESPACE;
        }

        public String toString() {
            return this.paramName + ": " + this.paramType + " [" + this.namespace + "]";
        }
    }
}

