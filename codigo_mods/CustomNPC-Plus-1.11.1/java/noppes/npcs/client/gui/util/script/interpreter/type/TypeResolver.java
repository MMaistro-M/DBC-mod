/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import noppes.npcs.client.gui.util.script.PackageFinder;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ClassIndex;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericTypeParser;
import noppes.npcs.client.gui.util.script.interpreter.type.ImportData;
import noppes.npcs.client.gui.util.script.interpreter.type.NashornBuiltins;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeStringNormalizer;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticType;

public class TypeResolver {
    private final Map<String, TypeInfo> typeCache = new HashMap<String, TypeInfo>();
    private final Map<String, TypeInfo> jsMergeCache = new HashMap<String, TypeInfo>();
    private final Set<String> validPackages = new HashSet<String>();
    private JSTypeRegistry jsTypeRegistry;
    private final Map<String, SyntheticType> syntheticTypes = new LinkedHashMap<String, SyntheticType>();
    public static final Set<String> JAVA_LANG_CLASSES = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("Object", "String", "Class", "System", "Math", "Integer", "Double", "Float", "Long", "Short", "Byte", "Character", "Boolean", "Number", "Void", "Thread", "Runnable", "Exception", "RuntimeException", "Error", "Throwable", "StringBuilder", "StringBuffer", "Enum", "Comparable", "Iterable", "CharSequence", "Cloneable", "Process", "ProcessBuilder", "Runtime", "SecurityManager", "ClassLoader", "Package", "ArithmeticException", "ArrayIndexOutOfBoundsException", "ClassCastException", "IllegalArgumentException", "IllegalStateException", "IndexOutOfBoundsException", "NullPointerException", "NumberFormatException", "UnsupportedOperationException", "AssertionError", "OutOfMemoryError", "StackOverflowError")));
    private static final Map<String, String> JS_PRIMITIVE_TO_JAVA = new HashMap<String, String>();
    private static TypeResolver instance;

    public static TypeResolver getInstance() {
        if (instance == null) {
            instance = new TypeResolver();
        }
        return instance;
    }

    public TypeResolver() {
        this.validPackages.add("java");
        this.validPackages.add("java.lang");
        this.validPackages.add("java.util");
        this.validPackages.add("java.io");
        this.initializeSyntheticTypes();
    }

    private void initializeSyntheticTypes() {
        for (SyntheticType type : NashornBuiltins.getInstance().getAllBuiltinTypes()) {
            this.syntheticTypes.put(type.getName(), type);
        }
        Map<String, SyntheticType> nashornGlobals = NashornBuiltins.getInstance().getAllGlobalFunctions();
        this.syntheticTypes.putAll(nashornGlobals);
    }

    public void registerSyntheticType(String name, SyntheticType type) {
        this.syntheticTypes.put(name, type);
    }

    public boolean isSyntheticType(String name) {
        return this.syntheticTypes.containsKey(name);
    }

    public SyntheticType getSyntheticType(String name) {
        return this.syntheticTypes.get(name);
    }

    public Collection<SyntheticType> getAllSyntheticTypes() {
        return this.syntheticTypes.values();
    }

    public JSTypeRegistry getJSTypeRegistry() {
        if (this.jsTypeRegistry == null) {
            this.jsTypeRegistry = JSTypeRegistry.getInstance();
            if (!this.jsTypeRegistry.isInitialized()) {
                this.jsTypeRegistry.initializeFromResources();
            }
            this.jsTypeRegistry.syncHooksFromScriptHookControllerIfNeeded();
        }
        return this.jsTypeRegistry;
    }

    public boolean isJSHook(String functionName) {
        return this.getJSTypeRegistry().isHook(functionName);
    }

    public String getJSHookParameterType(String functionName) {
        return this.getJSTypeRegistry().getHookParameterType(functionName);
    }

    public List<JSTypeRegistry.HookSignature> getJSHookSignatures(String functionName) {
        return this.getJSTypeRegistry().getHookSignatures(functionName);
    }

    public boolean isJSHook(String namespace, String functionName) {
        return this.getJSTypeRegistry().isHook(namespace, functionName);
    }

    public String getJSHookParameterType(String namespace, String functionName) {
        return this.getJSTypeRegistry().getHookParameterType(namespace, functionName);
    }

    public List<JSTypeRegistry.HookSignature> getJSHookSignatures(String namespace, String functionName) {
        return this.getJSTypeRegistry().getHookSignatures(namespace, functionName);
    }

    public Set<String> getJSHookNames(String namespace) {
        return this.getJSTypeRegistry().getHookNames(namespace);
    }

    public boolean isJSHook(List<String> namespaces, String functionName) {
        return this.getJSTypeRegistry().isHook(namespaces, functionName);
    }

    public String getJSHookParameterType(List<String> namespaces, String functionName) {
        return this.getJSTypeRegistry().getHookParameterType(namespaces, functionName);
    }

    public List<JSTypeRegistry.HookSignature> getJSHookSignatures(List<String> namespaces, String functionName) {
        return this.getJSTypeRegistry().getHookSignatures(namespaces, functionName);
    }

    public Set<String> getJSHookNames(List<String> namespaces) {
        return this.getJSTypeRegistry().getHookNames(namespaces);
    }

    public void clearCache() {
        this.typeCache.clear();
        this.validPackages.clear();
        this.validPackages.add("java");
        this.validPackages.add("java.lang");
        this.validPackages.add("java.util");
        this.validPackages.add("java.io");
    }

    public TypeInfo resolve(String typeName) {
        if (typeName == null || typeName.isEmpty()) {
            return null;
        }
        TypeInfo jsType = this.resolveJSType(typeName);
        if (jsType != null && jsType.isResolved()) {
            return jsType;
        }
        TypeInfo fullType = this.resolveFullName(typeName);
        if (fullType != null && fullType.isResolved()) {
            return fullType;
        }
        return jsType;
    }

    public TypeInfo resolveJSType(String jsTypeName) {
        if (jsTypeName == null || jsTypeName.isEmpty()) {
            return TypeInfo.fromPrimitive("void");
        }
        String normalized = TypeStringNormalizer.stripImportTypeSyntax(jsTypeName);
        if (normalized == null || normalized.isEmpty()) {
            return TypeInfo.fromPrimitive("void");
        }
        normalized = TypeStringNormalizer.pickPreferredUnionBranch(normalized);
        normalized = TypeStringNormalizer.stripNullableSuffix(normalized);
        TypeStringNormalizer.ArraySplit arraySplit = TypeStringNormalizer.splitArraySuffixes(normalized);
        String baseExpr = arraySplit.base;
        int arrayDims = arraySplit.dimensions;
        if (baseExpr == null || baseExpr.isEmpty()) {
            return TypeInfo.unresolved(jsTypeName, jsTypeName);
        }
        TypeInfo resolved = null;
        if (!baseExpr.contains("<")) {
            resolved = this.resolveBaseType(baseExpr);
        } else {
            GenericTypeParser.ParsedType parsed = GenericTypeParser.parse(baseExpr);
            if (parsed != null) {
                TypeInfo baseType = this.resolveBaseType(parsed.baseName);
                if (parsed.hasTypeArgs() && baseType != null && baseType.isResolved()) {
                    ArrayList<TypeInfo> resolvedArgs = new ArrayList<TypeInfo>();
                    for (GenericTypeParser.ParsedType argParsed : parsed.typeArgs) {
                        if (argParsed == null) {
                            resolvedArgs.add(TypeInfo.ANY);
                            continue;
                        }
                        TypeInfo argType = this.resolveJSType(argParsed.rawString);
                        resolvedArgs.add(argType != null ? argType : TypeInfo.unresolved(argParsed.baseName, argParsed.baseName));
                    }
                    if (!resolvedArgs.isEmpty()) {
                        baseType = baseType.parameterize(resolvedArgs);
                    }
                }
                resolved = baseType != null ? baseType : TypeInfo.unresolved(parsed.baseName, parsed.rawString);
            } else {
                resolved = this.resolveBaseType(baseExpr);
            }
        }
        if (resolved == null) {
            resolved = TypeInfo.unresolved(baseExpr, jsTypeName);
        }
        for (int i = 0; i < arrayDims; ++i) {
            resolved = TypeInfo.arrayOf(resolved);
        }
        return resolved;
    }

    private TypeInfo resolveBaseType(String baseName) {
        if (baseName == null || baseName.isEmpty()) {
            return null;
        }
        if (TypeResolver.isPrimitiveType(baseName)) {
            return TypeInfo.fromPrimitive(baseName);
        }
        if (baseName.startsWith("Java.")) {
            baseName = baseName.substring(5);
        }
        if (this.isSyntheticType(baseName)) {
            SyntheticType syntheticType = this.getSyntheticType(baseName);
            return syntheticType.getTypeInfo();
        }
        JSTypeInfo jsTypeInfo = this.getJSTypeRegistry().getType(baseName);
        if (jsTypeInfo != null) {
            TypeInfo merged = this.tryMergeJSWithJava(baseName, jsTypeInfo);
            if (merged != null) {
                return merged;
            }
            return TypeInfo.fromJSTypeInfo(jsTypeInfo);
        }
        switch (baseName.toLowerCase()) {
            case "string": {
                return TypeInfo.STRING;
            }
            case "number": 
            case "int": 
            case "integer": {
                return TypeInfo.NUMBER;
            }
            case "boolean": 
            case "bool": {
                return TypeInfo.BOOLEAN;
            }
            case "void": {
                return TypeInfo.VOID;
            }
            case "any": 
            case "object": 
            case "*": {
                return TypeInfo.ANY;
            }
        }
        TypeInfo javaType = this.resolveFullName(baseName);
        if (javaType != null && javaType.isResolved()) {
            return javaType;
        }
        return TypeInfo.unresolved(baseName, baseName);
    }

    private static TypeInfo getJSJavaMergeBase(String baseName) {
        switch (baseName) {
            case "String": {
                return TypeInfo.STRING;
            }
            case "Object": {
                return TypeInfo.OBJECT;
            }
        }
        return null;
    }

    public JSTypeInfo findJSTypeForJavaClass(Class<?> javaClass) {
        if (javaClass == null) {
            return null;
        }
        String simpleName = javaClass.getSimpleName();
        if (TypeResolver.getJSJavaMergeBase(simpleName) != null) {
            return this.getJSTypeRegistry().getType(simpleName);
        }
        return this.getJSTypeRegistry().getTypeByJavaFqn(javaClass.getName());
    }

    private TypeInfo tryMergeJSWithJava(String baseName, JSTypeInfo jsTypeInfo) {
        TypeInfo javaBase = TypeResolver.getJSJavaMergeBase(baseName);
        if (javaBase == null) {
            return null;
        }
        return this.jsMergeCache.computeIfAbsent(baseName, k -> {
            TypeInfo result = TypeInfo.fromClass(javaBase.getJavaClass());
            if (result == null) {
                result = javaBase;
            }
            TypeInfo.injectJSMembers(result, jsTypeInfo);
            if (javaBase == TypeInfo.STRING) {
                TypeInfo.jsString = result;
            } else if (javaBase == TypeInfo.OBJECT) {
                TypeInfo.jsObject = result;
            }
            return result;
        });
    }

    public boolean isJSPrimitive(String typeName) {
        return JS_PRIMITIVE_TO_JAVA.containsKey(typeName);
    }

    public TypeInfo resolveFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return null;
        }
        String normalized = fullName.replaceAll("\\s*\\.\\s*", ".").trim();
        if (this.typeCache.containsKey(normalized)) {
            return this.typeCache.get(normalized);
        }
        TypeInfo result = this.tryResolveClass(normalized);
        if (result != null) {
            return result;
        }
        String[] segments = normalized.split("\\.");
        for (int i = segments.length - 1; i > 0; --i) {
            int j;
            StringBuilder candidate = new StringBuilder();
            for (j = 0; j < i; ++j) {
                if (j > 0) {
                    candidate.append('.');
                }
                candidate.append(segments[j]);
            }
            for (j = i; j < segments.length; ++j) {
                candidate.append(j == i ? (char)'.' : '$');
                candidate.append(segments[j]);
            }
            result = this.tryResolveClass(candidate.toString());
            if (result == null) continue;
            this.typeCache.put(normalized, result);
            return result;
        }
        this.typeCache.put(normalized, null);
        return null;
    }

    public TypeInfo resolveSimpleName(String simpleName, Map<String, ImportData> imports, Set<String> wildcardPackages) {
        TypeInfo langType;
        TypeInfo resolved;
        if (simpleName == null || simpleName.isEmpty()) {
            return TypeInfo.unresolved(simpleName, simpleName);
        }
        ImportData importData = imports.get(simpleName);
        if (importData != null && importData.getResolvedType() != null) {
            return importData.getResolvedType();
        }
        if (importData != null && (resolved = this.resolveFullName(importData.getFullPath())) != null) {
            importData.setResolvedType(resolved);
            return resolved;
        }
        if (JAVA_LANG_CLASSES.contains(simpleName) && (langType = this.resolveFullName("java.lang." + simpleName)) != null) {
            return langType;
        }
        if (wildcardPackages != null) {
            for (String pkg : wildcardPackages) {
                TypeInfo fromPkg = this.resolveFullName(pkg + "." + simpleName);
                if (fromPkg != null) {
                    return fromPkg;
                }
                TypeInfo fromInner = this.resolveFullName(pkg + "$" + simpleName);
                if (fromInner == null) continue;
                return fromInner;
            }
        }
        return TypeInfo.unresolved(simpleName, simpleName);
    }

    private TypeInfo tryResolveClass(String className) {
        if (className == null || className.isEmpty()) {
            return null;
        }
        if (this.typeCache.containsKey(className)) {
            return this.typeCache.get(className);
        }
        try {
            Class<?> clazz = Class.forName(className);
            TypeInfo info = TypeInfo.fromClass(clazz);
            this.typeCache.put(className, info);
            this.registerPackage(info.getPackageName());
            return info;
        }
        catch (ClassNotFoundException e) {
            this.typeCache.put(className, null);
            return null;
        }
        catch (LinkageError e) {
            this.typeCache.put(className, null);
            return null;
        }
    }

    public boolean isValidPackage(String packagePath) {
        String[] testClasses;
        if (packagePath == null || packagePath.isEmpty()) {
            return false;
        }
        if (this.validPackages.contains(packagePath)) {
            return true;
        }
        if (PackageFinder.find(packagePath)) {
            this.registerPackage(packagePath);
            return true;
        }
        for (String testClass : testClasses = this.getTestClassesForPackage(packagePath)) {
            try {
                Class.forName(testClass);
                this.registerPackage(packagePath);
                return true;
            }
            catch (ClassNotFoundException | LinkageError throwable) {
            }
        }
        return false;
    }

    private void registerPackage(String packagePath) {
        int lastDot;
        if (packagePath == null || packagePath.isEmpty()) {
            return;
        }
        this.validPackages.add(packagePath);
        String current = packagePath;
        while ((lastDot = current.lastIndexOf(46)) > 0) {
            current = current.substring(0, lastDot);
            this.validPackages.add(current);
        }
    }

    private String[] getTestClassesForPackage(String packagePath) {
        switch (packagePath) {
            case "java": {
                return new String[]{"java.lang.Object"};
            }
            case "java.util": {
                return new String[]{"java.util.List", "java.util.Map"};
            }
            case "java.io": {
                return new String[]{"java.io.File", "java.io.InputStream"};
            }
            case "java.net": {
                return new String[]{"java.net.URL", "java.net.Socket"};
            }
            case "java.lang": {
                return new String[]{"java.lang.Object", "java.lang.String"};
            }
        }
        return new String[0];
    }

    public Map<String, ImportData> resolveImports(List<ImportData> imports) {
        HashMap<String, ImportData> resolved = new HashMap<String, ImportData>();
        for (ImportData imp : imports) {
            if (imp.isWildcard()) {
                imp.markResolved(this.isValidPackage(imp.getFullPath()) || this.resolveFullName(imp.getFullPath()) != null);
                continue;
            }
            TypeInfo typeInfo = this.resolveFullName(imp.getFullPath());
            imp.setResolvedType(typeInfo);
            if (imp.getSimpleName() == null) continue;
            resolved.put(imp.getSimpleName(), imp);
        }
        return resolved;
    }

    public List<GenericTypeOccurrence> parseGenericTypes(String content, Map<String, ImportData> imports, Set<String> wildcardPackages) {
        ArrayList<GenericTypeOccurrence> results = new ArrayList<GenericTypeOccurrence>();
        this.parseGenericTypesRecursive(content, 0, imports, wildcardPackages, results);
        return results;
    }

    private void parseGenericTypesRecursive(String content, int baseOffset, Map<String, ImportData> imports, Set<String> wildcardPackages, List<GenericTypeOccurrence> results) {
        if (content == null || content.isEmpty()) {
            return;
        }
        int i = 0;
        while (i < content.length()) {
            char c = content.charAt(i);
            if (!Character.isJavaIdentifierStart(c)) {
                ++i;
                continue;
            }
            int start = i;
            while (i < content.length() && Character.isJavaIdentifierPart(content.charAt(i))) {
                ++i;
            }
            String typeName = content.substring(start, i);
            if (Character.isUpperCase(typeName.charAt(0))) {
                TypeInfo info = this.resolveSimpleName(typeName, imports, wildcardPackages);
                results.add(new GenericTypeOccurrence(baseOffset + start, baseOffset + i, typeName, info));
            }
            while (i < content.length() && Character.isWhitespace(content.charAt(i))) {
                ++i;
            }
            if (i >= content.length() || content.charAt(i) != '<') continue;
            int nestedStart = i + 1;
            int depth = 1;
            ++i;
            while (i < content.length() && depth > 0) {
                if (content.charAt(i) == '<') {
                    ++depth;
                } else if (content.charAt(i) == '>') {
                    --depth;
                }
                ++i;
            }
            if (nestedStart >= i - 1) continue;
            String nestedContent = content.substring(nestedStart, i - 1);
            this.parseGenericTypesRecursive(nestedContent, baseOffset + nestedStart, imports, wildcardPackages, results);
        }
    }

    public static boolean isPrimitiveType(String typeName) {
        return typeName.equals("boolean") || typeName.equals("byte") || typeName.equals("char") || typeName.equals("short") || typeName.equals("int") || typeName.equals("long") || typeName.equals("float") || typeName.equals("double") || typeName.equals("void");
    }

    public static boolean isModifier(String word) {
        return word.equals("public") || word.equals("private") || word.equals("protected") || word.equals("static") || word.equals("final") || word.equals("abstract") || word.equals("synchronized") || word.equals("volatile") || word.equals("transient") || word.equals("native") || word.equals("strictfp");
    }

    public List<String> findClassesByPrefix(String prefix, int maxResults) {
        return ClassIndex.getInstance().findByPrefix(prefix, maxResults);
    }

    @Deprecated
    public List<String> findClassesBySimpleName(String simpleName, int maxResults) {
        return this.findClassesByPrefix(simpleName, maxResults);
    }

    public static boolean isStaticAccess(TypeInfo typeInfo, boolean wasResolvedAsType) {
        if (wasResolvedAsType) {
            return true;
        }
        if (typeInfo == null) {
            return false;
        }
        return typeInfo.isClassReference();
    }

    public static boolean isStaticAccessExpression(String identifier, int position, ScriptDocument document) {
        if (identifier == null || identifier.isEmpty() || document == null) {
            return false;
        }
        if (identifier.contains(".") || identifier.contains("(") || identifier.contains("[") || document.containsOperators(identifier)) {
            TypeInfo dotResolved;
            TypeInfo exprType = document.resolveExpressionType(identifier, position);
            if (TypeResolver.isStaticAccess(exprType, false)) {
                return true;
            }
            return !identifier.contains("(") && !identifier.contains("[") && !document.containsOperators(identifier) && (dotResolved = document.resolveType(identifier)) instanceof ScriptTypeInfo;
        }
        FieldInfo fieldInfo = document.resolveVariable(identifier, position);
        if (fieldInfo != null && fieldInfo.isResolved()) {
            return TypeResolver.isStaticAccess(fieldInfo.getTypeInfo(), false);
        }
        TypeInfo typeCheck = document.resolveType(identifier);
        boolean isType = typeCheck != null && typeCheck.isResolved();
        return TypeResolver.isStaticAccess(typeCheck, isType);
    }

    public static String[] parseStringArguments(String argumentsStr) {
        if (argumentsStr == null || argumentsStr.isEmpty()) {
            return new String[0];
        }
        ArrayList<String> args = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < argumentsStr.length(); ++i) {
            char c = argumentsStr.charAt(i);
            if (inString) {
                current.append(c);
                if (c != stringChar || i != 0 && argumentsStr.charAt(i - 1) == '\\') continue;
                inString = false;
                continue;
            }
            if (c == '\"' || c == '\'') {
                current.append(c);
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                ++depth;
                current.append(c);
                continue;
            }
            if (c == ')' || c == ']' || c == '}') {
                --depth;
                current.append(c);
                continue;
            }
            if (c == ',' && depth == 0) {
                args.add(current.toString().trim());
                current = new StringBuilder();
                continue;
            }
            current.append(c);
        }
        if (current.length() > 0) {
            args.add(current.toString().trim());
        }
        return args.toArray(new String[0]);
    }

    static {
        JS_PRIMITIVE_TO_JAVA.put("string", "String");
        JS_PRIMITIVE_TO_JAVA.put("number", "double");
        JS_PRIMITIVE_TO_JAVA.put("boolean", "boolean");
        JS_PRIMITIVE_TO_JAVA.put("any", "Object");
        JS_PRIMITIVE_TO_JAVA.put("void", "void");
        JS_PRIMITIVE_TO_JAVA.put("null", "null");
        JS_PRIMITIVE_TO_JAVA.put("undefined", "void");
        JS_PRIMITIVE_TO_JAVA.put("object", "Object");
    }

    public static class GenericTypeOccurrence {
        public final int startOffset;
        public final int endOffset;
        public final String typeName;
        public final TypeInfo typeInfo;

        public GenericTypeOccurrence(int startOffset, int endOffset, String typeName, TypeInfo typeInfo) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.typeName = typeName;
            this.typeInfo = typeInfo;
        }

        public TokenType getTokenType() {
            if (this.typeInfo == null || !this.typeInfo.isResolved()) {
                return TokenType.UNDEFINED_VAR;
            }
            return this.typeInfo.getTokenType();
        }
    }
}

