/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.DTSJSDocParser;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeStringNormalizer;

public class TypeScriptDefinitionParser {
    private static final Pattern INTERFACE_PATTERN = Pattern.compile("export\\s+interface\\s+(\\w+)(?:<([^>]*)>)?(?:\\s+extends\\s+([^{]+?))?\\s*\\{");
    private static final Pattern NESTED_INTERFACE_PATTERN = Pattern.compile("(?<!export\\s)\\binterface\\s+(\\w+)(?:<([^>]*)>)?(?:\\s+extends\\s+([^{]+?))?\\s*\\{");
    private static final Pattern CLASS_PATTERN = Pattern.compile("export\\s+class\\s+(\\w+)(?:<([^>]*)>)?(?:\\s+extends\\s+([^{]+?))?\\s*\\{");
    private static final Pattern TYPE_PARAM_PATTERN = Pattern.compile("(\\w+)(?:\\s+extends\\s+(\\w+)(?:\\s*/\\*\\s*([\\w.]+)\\s*\\*/)?)?");
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("(?:export\\s+)?namespace\\s+(\\w+)\\s*\\{");
    private static final Pattern TYPE_ALIAS_PATTERN = Pattern.compile("export\\s+type\\s+(\\w+)\\s*=\\s*([^;\\n]+);?");
    private static final Pattern METHOD_PATTERN = Pattern.compile("(?m)^\\s*(static\\s+)?(\\w+)\\??\\s*\\((.*)\\)\\s*:\\s*([^;]+);", 8);
    private static final Pattern FIELD_PATTERN = Pattern.compile("^\\s*(static\\s+)?(readonly\\s+)?(\\w+)\\??\\s*:\\s*([^;]+);", 8);
    private static final Pattern GLOBAL_FUNCTION_PATTERN = Pattern.compile("function\\s+(\\w+)\\s*\\((.*)\\)\\s*:\\s*([^;]+);");
    private static final Pattern GLOBAL_TYPE_ALIAS_PATTERN = Pattern.compile("type\\s+(\\w+)\\s*=\\s*import\\(['\"]([^'\"]+)['\"]\\)\\.([\\w.]+);");
    private static final Pattern HOOKS_NAMESPACE_PATTERN = Pattern.compile("declare\\s+namespace\\s+(I\\w*Event|\\w+)\\s*\\{", 8);
    private final JSTypeRegistry registry;

    public TypeScriptDefinitionParser(JSTypeRegistry registry) {
        this.registry = registry;
    }

    public void parseVsixArchive(File vsixFile) throws IOException {
        try (ZipFile zip = new ZipFile(vsixFile);){
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().endsWith(".d.ts")) continue;
                InputStream is = zip.getInputStream(entry);
                Throwable throwable = null;
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    Throwable throwable2 = null;
                    try {
                        String content = this.readFully(reader);
                        String fileName = entry.getName();
                        this.parseDefinitionFile(content, fileName);
                    }
                    catch (Throwable throwable3) {
                        throwable2 = throwable3;
                        throw throwable3;
                    }
                    finally {
                        if (reader == null) continue;
                        if (throwable2 != null) {
                            try {
                                reader.close();
                            }
                            catch (Throwable throwable4) {
                                throwable2.addSuppressed(throwable4);
                            }
                            continue;
                        }
                        reader.close();
                    }
                }
                catch (Throwable throwable5) {
                    throwable = throwable5;
                    throw throwable5;
                }
                finally {
                    if (is == null) continue;
                    if (throwable != null) {
                        try {
                            is.close();
                        }
                        catch (Throwable throwable6) {
                            throwable.addSuppressed(throwable6);
                        }
                        continue;
                    }
                    is.close();
                }
            }
        }
    }

    public void parseDirectory(File directory) throws IOException {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a directory: " + directory);
        }
        this.parseDirectoryRecursive(directory);
    }

    private void parseDirectoryRecursive(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                this.parseDirectoryRecursive(file);
                continue;
            }
            if (!file.getName().endsWith(".d.ts")) continue;
            this.parseFile(file);
        }
    }

    public void parseFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));){
            String content = this.readFully(reader);
            this.parseDefinitionFile(content, file.getName());
        }
    }

    public void parseDefinitionFile(String content, String fileName) {
        if (fileName.contains("hooks.d.ts")) {
            return;
        }
        if (fileName.contains("index.d.ts")) {
            this.parseIndexFile(content);
            return;
        }
        String packageName = this.derivePackageName(fileName);
        this.parseInterfaceFile(content, null, packageName);
    }

    private void parseHooksFile(String content) {
        Matcher namespaceMatcher = HOOKS_NAMESPACE_PATTERN.matcher(content);
        while (namespaceMatcher.find()) {
            String namespace = namespaceMatcher.group(1);
            int bodyStart = namespaceMatcher.end();
            int bodyEnd = this.findMatchingBrace(content, bodyStart - 1);
            if (bodyEnd <= bodyStart) continue;
            String namespaceBody = content.substring(bodyStart, bodyEnd);
            Matcher funcMatcher = GLOBAL_FUNCTION_PATTERN.matcher(namespaceBody);
            while (funcMatcher.find()) {
                String[] parts;
                String funcName = funcMatcher.group(1);
                String params = funcMatcher.group(2);
                if (params.isEmpty() || (parts = params.split(":\\s*", 2)).length != 2) continue;
                String paramName = parts[0].trim();
                String paramType = parts[1].trim();
                this.registry.registerHook(namespace, funcName, paramName, paramType);
            }
        }
    }

    private void parseIndexFile(String content) {
        Matcher m = GLOBAL_TYPE_ALIAS_PATTERN.matcher(content);
        while (m.find()) {
            String aliasName = m.group(1);
            String importPath = m.group(2);
            String typeName = m.group(3);
            this.registry.registerTypeAlias(aliasName, typeName);
        }
    }

    private void parseInterfaceFile(String content, String parentNamespace, String packageName) {
        String body;
        int bodyStart;
        int bodyEnd;
        String scanContent = this.stripNamespaceBlocks(content);
        Matcher interfaceMatcher = INTERFACE_PATTERN.matcher(scanContent);
        while (interfaceMatcher.find()) {
            String javaFqn;
            String interfaceName = interfaceMatcher.group(1);
            String typeParamsStr = interfaceMatcher.group(2);
            String extendsClause = interfaceMatcher.group(3);
            JSTypeInfo typeInfo = new JSTypeInfo(interfaceName, parentNamespace);
            JSDocInfo jsDoc = this.extractJSDocBefore(content, interfaceMatcher.start());
            if (jsDoc != null) {
                typeInfo.setJsDocInfo(jsDoc);
            }
            if ((javaFqn = this.findJavaFqn(jsDoc, packageName, typeInfo.getFullName())) != null && !javaFqn.isEmpty()) {
                typeInfo.setJavaFqn(javaFqn);
            }
            if (typeParamsStr != null && !typeParamsStr.isEmpty()) {
                this.parseTypeParameters(typeParamsStr, typeInfo);
            }
            if (extendsClause != null) {
                String extendsType = extendsClause.trim();
                if ((extendsType = extendsType.replaceAll("<[^>]*>", "")).contains(",")) {
                    extendsType = extendsType.substring(0, extendsType.indexOf(44)).trim();
                }
                extendsType = this.cleanType(extendsType);
                typeInfo.setExtends(extendsType);
            }
            if ((bodyEnd = this.findMatchingBrace(content, (bodyStart = interfaceMatcher.end()) - 1)) > bodyStart) {
                body = content.substring(bodyStart, bodyEnd);
                this.parseInterfaceBody(body, typeInfo);
                String fullNamespace = parentNamespace != null ? parentNamespace + "." + interfaceName : interfaceName;
                this.parseNestedTypes(body, fullNamespace, packageName);
            }
            this.registry.registerType(typeInfo);
        }
        Matcher nestedInterfaceMatcher = NESTED_INTERFACE_PATTERN.matcher(scanContent);
        while (nestedInterfaceMatcher.find()) {
            String interfaceName = nestedInterfaceMatcher.group(1);
            String typeParamsStr = nestedInterfaceMatcher.group(2);
            String extendsClause = nestedInterfaceMatcher.group(3);
            JSTypeInfo typeInfo = new JSTypeInfo(interfaceName, parentNamespace);
            JSDocInfo jsDoc = this.extractJSDocBefore(content, nestedInterfaceMatcher.start());
            if (jsDoc != null) {
                typeInfo.setJsDocInfo(jsDoc);
            }
            if (typeParamsStr != null && !typeParamsStr.isEmpty()) {
                this.parseTypeParameters(typeParamsStr, typeInfo);
            }
            if (extendsClause != null) {
                String extendsType = extendsClause.trim();
                if ((extendsType = extendsType.replaceAll("<[^>]*>", "")).contains(",")) {
                    extendsType = extendsType.substring(0, extendsType.indexOf(44)).trim();
                }
                extendsType = this.cleanType(extendsType);
                typeInfo.setExtends(extendsType);
            }
            if ((bodyEnd = this.findMatchingBrace(content, (bodyStart = nestedInterfaceMatcher.end()) - 1)) > bodyStart) {
                body = content.substring(bodyStart, bodyEnd);
                this.parseInterfaceBody(body, typeInfo);
            }
            this.registry.registerType(typeInfo);
        }
        Matcher classMatcher = CLASS_PATTERN.matcher(scanContent);
        while (classMatcher.find()) {
            int bodyStart2;
            int bodyEnd2;
            String className = classMatcher.group(1);
            String typeParamsStr = classMatcher.group(2);
            String extendsClause = classMatcher.group(3);
            JSTypeInfo typeInfo = new JSTypeInfo(className, parentNamespace);
            JSDocInfo jsDoc = this.extractJSDocBefore(content, classMatcher.start());
            if (jsDoc != null) {
                typeInfo.setJsDocInfo(jsDoc);
            }
            if (typeParamsStr != null && !typeParamsStr.isEmpty()) {
                this.parseTypeParameters(typeParamsStr, typeInfo);
            }
            if (extendsClause != null) {
                String extendsType = extendsClause.trim();
                if ((extendsType = extendsType.replaceAll("<[^>]*>", "")).contains(",")) {
                    extendsType = extendsType.substring(0, extendsType.indexOf(44)).trim();
                }
                extendsType = this.cleanType(extendsType);
                typeInfo.setExtends(extendsType);
            }
            if ((bodyEnd2 = this.findMatchingBrace(content, (bodyStart2 = classMatcher.end()) - 1)) > bodyStart2) {
                String body2 = content.substring(bodyStart2, bodyEnd2);
                this.parseInterfaceBody(body2, typeInfo);
            }
            this.registry.registerType(typeInfo);
        }
        Matcher namespaceMatcher = NAMESPACE_PATTERN.matcher(content);
        while (namespaceMatcher.find()) {
            String namespaceName = namespaceMatcher.group(1);
            int bodyStart3 = namespaceMatcher.end();
            int bodyEnd3 = this.findMatchingBrace(content, bodyStart3 - 1);
            if (bodyEnd3 <= bodyStart3) continue;
            String body3 = content.substring(bodyStart3, bodyEnd3);
            String fullNamespace = parentNamespace != null ? parentNamespace + "." + namespaceName : namespaceName;
            this.parseInterfaceFile(body3, fullNamespace, packageName);
        }
        if (parentNamespace == null) {
            this.parseTypeAliases(content, null);
        }
    }

    private String stripNamespaceBlocks(String content) {
        if (content == null || content.isEmpty()) {
            return content;
        }
        Matcher m = NAMESPACE_PATTERN.matcher(content);
        if (!m.find()) {
            return content;
        }
        char[] chars = content.toCharArray();
        int searchFrom = 0;
        m.reset();
        while (m.find(searchFrom)) {
            int start = m.start();
            int bodyStart = m.end();
            int bodyEnd = this.findMatchingBrace(content, bodyStart - 1);
            if (bodyEnd <= start) {
                searchFrom = Math.max(m.end(), searchFrom + 1);
                continue;
            }
            int endExclusive = Math.min(chars.length, bodyEnd + 1);
            for (int i = start; i < endExclusive; ++i) {
                chars[i] = 32;
            }
            searchFrom = endExclusive;
        }
        return new String(chars);
    }

    private void parseTypeParameters(String typeParamsStr, JSTypeInfo typeInfo) {
        String[] params;
        for (String param : params = typeParamsStr.split(",")) {
            Matcher m;
            if ((param = param.trim()).isEmpty() || !(m = TYPE_PARAM_PATTERN.matcher(param)).find()) continue;
            String name = m.group(1);
            String boundType = m.group(2);
            String fullBoundType = m.group(3);
            typeInfo.addTypeParam(new TypeParamInfo(name, boundType, fullBoundType));
        }
    }

    private void parseNestedTypes(String content, String namespace, String packageName) {
        Matcher nestedInterfaceMatcher = NESTED_INTERFACE_PATTERN.matcher(content);
        while (nestedInterfaceMatcher.find()) {
            int bodyStart;
            int bodyEnd;
            String javaFqn;
            String interfaceName = nestedInterfaceMatcher.group(1);
            String typeParamsStr = nestedInterfaceMatcher.group(2);
            String extendsClause = nestedInterfaceMatcher.group(3);
            JSTypeInfo typeInfo = new JSTypeInfo(interfaceName, namespace);
            JSDocInfo jsDoc = this.extractJSDocBefore(content, nestedInterfaceMatcher.start());
            if (jsDoc != null) {
                typeInfo.setJsDocInfo(jsDoc);
            }
            if ((javaFqn = this.findJavaFqn(jsDoc, packageName, typeInfo.getFullName())) != null && !javaFqn.isEmpty()) {
                typeInfo.setJavaFqn(javaFqn);
            }
            if (typeParamsStr != null && !typeParamsStr.isEmpty()) {
                this.parseTypeParameters(typeParamsStr, typeInfo);
            }
            if (extendsClause != null) {
                String extendsType = extendsClause.trim();
                if ((extendsType = extendsType.replaceAll("<[^>]*>", "")).contains(",")) {
                    extendsType = extendsType.substring(0, extendsType.indexOf(44)).trim();
                }
                extendsType = this.cleanType(extendsType);
                typeInfo.setExtends(extendsType);
            }
            if ((bodyEnd = this.findMatchingBrace(content, (bodyStart = nestedInterfaceMatcher.end()) - 1)) > bodyStart) {
                String body = content.substring(bodyStart, bodyEnd);
                this.parseInterfaceBody(body, typeInfo);
            }
            this.registry.registerType(typeInfo);
        }
        this.parseTypeAliases(content, namespace);
    }

    private String findJavaFqn(JSDocInfo jsDoc, String packageName, String typeFullName) {
        String tagged = this.extractJavaFqnFromJSDoc(jsDoc);
        if (tagged != null && !tagged.isEmpty()) {
            return tagged;
        }
        return this.buildJavaFqnFromPackage(packageName, typeFullName);
    }

    private String extractJavaFqnFromJSDoc(JSDocInfo jsDoc) {
        if (jsDoc == null) {
            return null;
        }
        for (JSDocTag tag : jsDoc.getAllTags()) {
            if (tag == null || !"javaFqn".equals(tag.getTagName())) continue;
            if (tag.getDescription() != null && !tag.getDescription().trim().isEmpty()) {
                return tag.getDescription().trim();
            }
            if (tag.getTypeName() == null || tag.getTypeName().trim().isEmpty()) continue;
            return tag.getTypeName().trim();
        }
        return null;
    }

    private String buildJavaFqnFromPackage(String packageName, String typeFullName) {
        if (typeFullName == null || typeFullName.isEmpty()) {
            return null;
        }
        if (packageName == null || packageName.isEmpty()) {
            return null;
        }
        return packageName + "." + typeFullName;
    }

    private String derivePackageName(String fileName) {
        int lastSlash;
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        String normalized = fileName.replace('\\', '/');
        if (normalized.endsWith(".d.ts")) {
            normalized = normalized.substring(0, normalized.length() - 5);
        }
        if ((lastSlash = normalized.lastIndexOf(47)) < 0) {
            return null;
        }
        String pkgPath = normalized.substring(0, lastSlash);
        if (pkgPath.isEmpty()) {
            return null;
        }
        return pkgPath.replace('/', '.');
    }

    private void parseTypeAliases(String content, String namespace) {
        Matcher m = TYPE_ALIAS_PATTERN.matcher(content);
        while (m.find()) {
            String aliasName = m.group(1);
            String targetType = m.group(2).trim();
            JSTypeInfo typeInfo = new JSTypeInfo(aliasName, namespace);
            typeInfo.setExtends(targetType);
            this.registry.registerType(typeInfo);
        }
    }

    private void parseInterfaceBody(String body, JSTypeInfo typeInfo) {
        JSDocInfo jsDoc;
        Matcher methodMatcher = METHOD_PATTERN.matcher(body);
        while (methodMatcher.find()) {
            boolean isStatic = methodMatcher.group(1) != null;
            String methodName = methodMatcher.group(2);
            String params = methodMatcher.group(3);
            String returnType = this.cleanType(methodMatcher.group(4));
            List<JSMethodInfo.JSParameterInfo> parameters = this.parseParameters(params);
            JSMethodInfo method = new JSMethodInfo(methodName, returnType, parameters);
            method.setStatic(isStatic);
            jsDoc = this.extractJSDocBefore(body, methodMatcher.start());
            if (jsDoc != null) {
                method.setJsDocInfo(jsDoc);
            }
            typeInfo.addMethod(method);
        }
        Matcher fieldMatcher = FIELD_PATTERN.matcher(body);
        while (fieldMatcher.find()) {
            boolean isStatic = fieldMatcher.group(1) != null;
            String fieldName = fieldMatcher.group(3);
            if ("ICustomNPCsEvent".equals(typeInfo.getSimpleName()) && "API".equals(fieldName)) continue;
            String fieldType = this.cleanType(fieldMatcher.group(4));
            boolean readonly = fieldMatcher.group(2) != null;
            JSFieldInfo field = new JSFieldInfo(fieldName, fieldType, readonly);
            field.setStatic(isStatic);
            jsDoc = this.extractJSDocBefore(body, fieldMatcher.start());
            if (jsDoc != null) {
                field.setJsDocInfo(jsDoc);
            }
            typeInfo.addField(field);
        }
    }

    private List<JSMethodInfo.JSParameterInfo> parseParameters(String params) {
        ArrayList<JSMethodInfo.JSParameterInfo> result = new ArrayList<JSMethodInfo.JSParameterInfo>();
        if (params == null || params.trim().isEmpty()) {
            return result;
        }
        List<String> paramParts = this.splitParameters(params);
        for (String part : paramParts) {
            int colonIndex;
            if ((part = part.trim()).isEmpty() || (colonIndex = part.indexOf(58)) <= 0) continue;
            String name = part.substring(0, colonIndex).trim().replace("?", "");
            String type = this.cleanType(part.substring(colonIndex + 1).trim());
            boolean isVarArg = name.startsWith("...");
            if (isVarArg) {
                name = name.substring(3).trim();
                if (type.endsWith("[]")) {
                    type = type.substring(0, type.length() - 2).trim();
                } else if (type.startsWith("Array<") && type.endsWith(">")) {
                    type = type.substring(6, type.length() - 1).trim();
                }
            }
            JSMethodInfo.JSParameterInfo param = new JSMethodInfo.JSParameterInfo(name, type);
            param.setVarArg(isVarArg);
            result.add(param);
        }
        return result;
    }

    private List<String> splitParameters(String params) {
        ArrayList<String> result = new ArrayList<String>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < params.length(); ++i) {
            char c = params.charAt(i);
            if (c == '<' || c == '(' || c == '[') {
                ++depth;
                continue;
            }
            if (c == '>' || c == ')' || c == ']') {
                --depth;
                continue;
            }
            if (c != ',' || depth != 0) continue;
            result.add(params.substring(start, i));
            start = i + 1;
        }
        if (start < params.length()) {
            result.add(params.substring(start));
        }
        return result;
    }

    private String cleanType(String type) {
        if (type == null) {
            return "any";
        }
        return TypeStringNormalizer.stripImportTypeSyntax(type);
    }

    private int findMatchingBrace(String text, int openBracePos) {
        int depth = 1;
        for (int i = openBracePos + 1; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    private String readFully(BufferedReader reader) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private JSDocInfo extractJSDocBefore(String content, int elementStart) {
        String jsDocBlock = DTSJSDocParser.extractJSDocBefore(content, elementStart);
        if (jsDocBlock != null) {
            return DTSJSDocParser.parseJSDocBlock(jsDocBlock);
        }
        return null;
    }
}

