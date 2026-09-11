/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.hover;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.DocumentError;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocReturnTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocSeeTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTypeTag;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenErrorMessage;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class TokenHoverInfo {
    private String packageName;
    private String iconIndicator;
    private List<TextSegment> declaration = new ArrayList<TextSegment>();
    private List<String> documentation = new ArrayList<String>();
    private List<DocumentationLine> jsDocLines = new ArrayList<DocumentationLine>();
    private List<String> errors = new ArrayList<String>();
    private List<String> additionalInfo = new ArrayList<String>();
    private final Token token;

    private TokenHoverInfo(Token token) {
        this.token = token;
    }

    public static TokenHoverInfo fromToken(Token token) {
        if (token == null) {
            return null;
        }
        TokenHoverInfo info = new TokenHoverInfo(token);
        info.extractErrors(token);
        switch (token.getType()) {
            case IMPORTED_CLASS: 
            case CLASS_DECL: 
            case INTERFACE_DECL: 
            case ENUM_DECL: 
            case TYPE_DECL: 
            case GENERIC_TYPE_PARAM: {
                info.extractClassInfo(token);
                break;
            }
            case METHOD_CALL: {
                info.extractMethodCallInfo(token);
                break;
            }
            case METHOD_DECL: {
                info.extractMethodDeclInfo(token);
                break;
            }
            case ENUM_CONSTANT: {
                info.extractEnumConstantInfo(token);
                break;
            }
            case GLOBAL_FIELD: {
                info.extractGlobalFieldInfo(token);
                break;
            }
            case LOCAL_FIELD: {
                info.extractLocalFieldInfo(token);
                break;
            }
            case PARAMETER: {
                info.extractParameterInfo(token);
                break;
            }
            case UNDEFINED_VAR: {
                info.extractUndefinedInfo(token);
                break;
            }
            case LITERAL: 
            case KEYWORD: 
            case STRING: 
            case COMMENT: {
                if (info.hasErrors()) {
                    return info;
                }
                return null;
            }
            default: {
                if (token.getTypeInfo() != null) {
                    info.extractClassInfo(token);
                    break;
                }
                if (token.getMethodInfo() != null) {
                    info.extractMethodDeclInfo(token);
                    break;
                }
                if (token.getFieldInfo() != null) {
                    info.extractFieldInfoGeneric(token);
                    break;
                }
                if (info.hasErrors()) {
                    return info;
                }
                return null;
            }
        }
        return info;
    }

    private void extractErrors(Token token) {
        ScriptDocument doc;
        TokenErrorMessage msg;
        if (token.getType() == TokenType.UNDEFINED_VAR) {
            this.errors.add("Cannot resolve symbol '" + token.getText() + "'");
        }
        if ((msg = token.getErrorMessage()) != null && !msg.getMessage().isEmpty()) {
            if (msg.clearOtherErrors) {
                this.errors.clear();
            }
            this.errors.add(msg.getMessage());
        }
        if ((doc = ScriptDocument.INSTANCE) != null) {
            int tokenStart = token.getGlobalStart();
            int tokenEnd = token.getGlobalEnd();
            for (DocumentError error : doc.getErrors()) {
                if (tokenStart >= error.getEndPos() || tokenEnd <= error.getStartPos()) continue;
                this.errors.add(error.getMessage());
            }
        }
    }

    private void extractClassInfo(Token token) {
        TypeInfo typeInfo = token.getTypeInfo();
        if (typeInfo == null) {
            return;
        }
        if (typeInfo.isTypeParameter()) {
            this.extractTypeParameterInfo(typeInfo);
            return;
        }
        this.packageName = this.getPackageName(typeInfo);
        Class<?> clazz = typeInfo.getJavaClass();
        if (clazz != null) {
            boolean isEnum = clazz.isEnum();
            this.iconIndicator = clazz.isInterface() ? "I" : (isEnum ? "E" : "C");
            int mods = clazz.getModifiers();
            if (Modifier.isPublic(mods)) {
                this.addSegment("public ", TokenType.KEYWORD.getHexColor());
            }
            if (Modifier.isAbstract(mods) && !clazz.isInterface()) {
                this.addSegment("abstract ", TokenType.KEYWORD.getHexColor());
            }
            if (Modifier.isFinal(mods) && !isEnum) {
                this.addSegment("final ", TokenType.KEYWORD.getHexColor());
            }
            if (clazz.isInterface()) {
                this.addSegment("interface ", TokenType.KEYWORD.getHexColor());
            } else if (isEnum) {
                this.addSegment("enum ", TokenType.KEYWORD.getHexColor());
            } else {
                this.addSegment("class ", TokenType.KEYWORD.getHexColor());
            }
            int classColor = clazz.isInterface() ? TokenType.INTERFACE_DECL.getHexColor() : (clazz.isEnum() ? TokenType.ENUM_DECL.getHexColor() : TokenType.IMPORTED_CLASS.getHexColor());
            this.addTypeSegments(typeInfo);
            this.addDeclaredTypeParamSegments(typeInfo);
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null && superclass != Object.class && !isEnum) {
                this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
                this.addSegment(superclass.getSimpleName(), this.getColorForClass(superclass));
            }
            Object declaration = null;
            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces.length > 0) {
                this.addSegment(clazz.isInterface() ? " extends " : " implements ", TokenType.KEYWORD.getHexColor());
                for (int i = 0; i < Math.min(interfaces.length, 3); ++i) {
                    if (i > 0) {
                        this.addSegment(", ", TokenType.DEFAULT.getHexColor());
                    }
                    this.addSegment(interfaces[i].getSimpleName(), TokenType.INTERFACE_DECL.getHexColor());
                }
                if (interfaces.length > 3) {
                    this.addSegment(", ...", TokenType.DEFAULT.getHexColor());
                }
            }
        } else if (typeInfo instanceof ScriptTypeInfo) {
            JSDocInfo jsDocInfo;
            List<TypeInfo> implementedInterfaces;
            ScriptTypeInfo scriptType = (ScriptTypeInfo)typeInfo;
            this.iconIndicator = scriptType.getKind() == TypeInfo.Kind.INTERFACE ? "I" : (scriptType.getKind() == TypeInfo.Kind.ENUM ? "E" : "C");
            int mods = scriptType.getModifiers();
            if (Modifier.isPublic(mods)) {
                this.addSegment("public ", TokenType.KEYWORD.getHexColor());
            }
            if (Modifier.isAbstract(mods) && scriptType.getKind() != TypeInfo.Kind.INTERFACE) {
                this.addSegment("abstract ", TokenType.KEYWORD.getHexColor());
            }
            if (Modifier.isFinal(mods)) {
                this.addSegment("final ", TokenType.KEYWORD.getHexColor());
            }
            if (Modifier.isStatic(mods)) {
                this.addSegment("static ", TokenType.KEYWORD.getHexColor());
            }
            if (scriptType.getKind() == TypeInfo.Kind.INTERFACE) {
                this.addSegment("interface ", TokenType.KEYWORD.getHexColor());
            } else if (scriptType.getKind() == TypeInfo.Kind.ENUM) {
                this.addSegment("enum ", TokenType.KEYWORD.getHexColor());
            } else {
                this.addSegment("class ", TokenType.KEYWORD.getHexColor());
            }
            int classColor = scriptType.getKind() == TypeInfo.Kind.INTERFACE ? TokenType.INTERFACE_DECL.getHexColor() : (scriptType.getKind() == TypeInfo.Kind.ENUM ? TokenType.ENUM_DECL.getHexColor() : TokenType.IMPORTED_CLASS.getHexColor());
            this.addTypeSegments(typeInfo);
            this.addDeclaredTypeParamSegments(typeInfo);
            if (scriptType.hasSuperClass()) {
                this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
                TypeInfo superClass = scriptType.getSuperClass();
                if (superClass != null && superClass.isResolved()) {
                    this.addTypeSegments(superClass);
                } else {
                    String superName = scriptType.getSuperClassName();
                    if (superName != null) {
                        this.addSegment(superName, TokenType.UNDEFINED_VAR.getHexColor());
                    }
                }
            }
            if (!(implementedInterfaces = scriptType.getImplementedInterfaces()).isEmpty()) {
                String keyword;
                String string = keyword = scriptType.getKind() == TypeInfo.Kind.INTERFACE ? " extends " : " implements ";
                if (scriptType.hasSuperClass() && scriptType.getKind() == TypeInfo.Kind.INTERFACE) {
                    this.addSegment(", ", TokenType.DEFAULT.getHexColor());
                } else {
                    this.addSegment(keyword, TokenType.KEYWORD.getHexColor());
                }
                List<String> interfaceNames = scriptType.getImplementedInterfaceNames();
                for (int i = 0; i < implementedInterfaces.size(); ++i) {
                    String ifaceName;
                    if (i > 0) {
                        this.addSegment(", ", TokenType.DEFAULT.getHexColor());
                    }
                    TypeInfo ifaceType = implementedInterfaces.get(i);
                    String string2 = ifaceName = i < interfaceNames.size() ? interfaceNames.get(i) : this.getName(ifaceType);
                    if (ifaceType != null && ifaceType.isResolved()) {
                        this.addTypeSegments(ifaceType);
                        continue;
                    }
                    this.addSegment(ifaceName, TokenType.UNDEFINED_VAR.getHexColor());
                }
            }
            if ((jsDocInfo = scriptType.getJSDocInfo()) != null) {
                this.formatJSDocumentation(jsDocInfo, null);
            }
        } else if (typeInfo.isJSType()) {
            JSDocInfo jsDocInfo;
            JSTypeInfo jsType = typeInfo.getJSTypeInfo();
            this.iconIndicator = "I";
            this.addSegment("interface ", TokenType.KEYWORD.getHexColor());
            this.addTypeSegments(typeInfo);
            this.addDeclaredTypeParamSegments(typeInfo);
            if (jsType.getExtendsType() != null) {
                this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
                this.addSegment(jsType.getExtendsType(), TokenType.INTERFACE_DECL.getHexColor());
            }
            if ((jsDocInfo = typeInfo.getJSDocInfo()) != null) {
                this.formatJSDocumentation(jsDocInfo, null);
            }
        } else {
            this.iconIndicator = "?";
            String typeName = this.getName(typeInfo);
            this.splitAndAddTypeName(typeName, token.getType().getHexColor());
            if (!typeInfo.isResolved()) {
                this.errors.add("Cannot resolve class '" + typeName + "'");
            }
        }
        if (token.getMethodInfo() != null) {
            MethodInfo constructor = token.getMethodInfo();
            this.declaration.add(new TextSegment("\n", TokenType.DEFAULT.getHexColor()));
            this.buildConstructorDeclaration(constructor, typeInfo);
        }
    }

    private void extractTypeParameterInfo(TypeInfo typeInfo) {
        this.iconIndicator = "T";
        String paramName = typeInfo.getTypeParameterName();
        this.addSegment("type parameter ", TokenType.KEYWORD.getHexColor());
        this.addSegment("<", TokenType.DEFAULT.getHexColor());
        this.addSegment(paramName, TokenType.GENERIC_TYPE_PARAM.getHexColor());
        TypeInfo bound = typeInfo.getBoundType();
        if (bound != null && bound.getJavaClass() != null && bound.getJavaClass() != Object.class) {
            this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
            this.addSegment(bound.getSimpleName(), this.getColorForTypeInfo(bound));
        }
        this.addSegment(">", TokenType.DEFAULT.getHexColor());
    }

    private void extractMethodCallInfo(Token token) {
        String pkg;
        MethodInfo methodInfo;
        MethodCallInfo callInfo = token.getMethodCallInfo();
        MethodInfo methodInfo2 = methodInfo = callInfo != null ? callInfo.getResolvedMethod() : token.getMethodInfo();
        if (methodInfo == null && callInfo == null) {
            return;
        }
        this.iconIndicator = "m";
        TypeInfo containingType = null;
        if (callInfo != null) {
            TypeInfo trueContainingType;
            containingType = callInfo.getReceiverType();
            if (callInfo.getResolvedMethod() != null && (trueContainingType = callInfo.getResolvedMethod().getContainingType()) != null) {
                containingType = trueContainingType;
            }
        }
        if (containingType == null && methodInfo != null) {
            containingType = methodInfo.getContainingType();
        }
        if (containingType != null && (pkg = this.getPackageName(containingType)) != null && !pkg.isEmpty()) {
            this.packageName = pkg;
        }
        if (methodInfo != null && this.shouldPreferMethodInfo(methodInfo)) {
            this.buildBasicMethodDeclaration(methodInfo, containingType);
            return;
        }
        if (methodInfo != null && methodInfo.getJavaMethod() != null) {
            if (containingType != null && containingType.isParameterized()) {
                this.buildBasicMethodDeclaration(methodInfo, containingType);
                return;
            }
            TypeInfo methodReturnType = methodInfo.getReturnType();
            Class<?> reflectedReturnType = methodInfo.getJavaMethod().getReturnType();
            if (methodReturnType != null && methodReturnType.getJavaClass() != null && methodReturnType.getJavaClass() != reflectedReturnType) {
                this.buildBasicMethodDeclaration(methodInfo, containingType);
                return;
            }
            this.buildMethodDeclaration(methodInfo, containingType);
            this.extractJavadoc(methodInfo.getJavaMethod());
            return;
        }
        if (methodInfo != null) {
            this.buildBasicMethodDeclaration(methodInfo, containingType);
        }
    }

    private void extractMethodDeclInfo(Token token) {
        MethodInfo methodInfo = token.getMethodInfo();
        if (methodInfo == null) {
            return;
        }
        this.iconIndicator = "m";
        this.buildBasicMethodDeclaration(methodInfo, null);
    }

    private void extractGlobalFieldInfo(Token token) {
        int modifiers;
        TypeInfo receiverType;
        FieldInfo fieldInfo = token.getFieldInfo();
        FieldAccessInfo accessInfo = token.getFieldAccessInfo();
        if (fieldInfo == null && accessInfo != null) {
            fieldInfo = accessInfo.getResolvedField();
        }
        if (fieldInfo == null) {
            return;
        }
        this.iconIndicator = "f";
        JSDocInfo jsDoc = fieldInfo.getJSDocInfo();
        if (jsDoc != null) {
            this.formatJSDocumentation(jsDoc, null);
        } else if (fieldInfo.getDocumentation() != null && !fieldInfo.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = fieldInfo.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
        TypeInfo declaredType = fieldInfo.getTypeInfo();
        boolean foundModifiers = false;
        if (accessInfo != null && accessInfo.getReceiverType() != null && (receiverType = accessInfo.getReceiverType()).getJavaClass() != null) {
            try {
                Field javaField = receiverType.getJavaClass().getField(fieldInfo.getName());
                if (javaField != null) {
                    this.addFieldModifiers(javaField.getModifiers());
                }
                foundModifiers = true;
            }
            catch (Exception javaField) {
                // empty catch block
            }
        }
        if ((modifiers = fieldInfo.getModifiers()) != 0 && !foundModifiers) {
            this.addFieldModifiers(modifiers);
        }
        if (declaredType != null) {
            String pkg = this.getPackageName(accessInfo != null ? accessInfo.getReceiverType() : declaredType);
            if (pkg != null && !pkg.isEmpty()) {
                this.packageName = pkg;
            }
            this.addTypeSegments(declaredType);
            this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        }
        this.addSegment(fieldInfo.getName(), TokenType.GLOBAL_FIELD.getHexColor());
        this.addInitializationTokens(token, fieldInfo);
    }

    private void extractEnumConstantInfo(Token token) {
        TypeInfo enumType;
        FieldInfo fieldInfo = token.getFieldInfo();
        if (fieldInfo == null) {
            return;
        }
        EnumConstantInfo enumInfo = fieldInfo.getEnumInfo();
        if (enumInfo == null) {
            return;
        }
        this.iconIndicator = "e";
        if (fieldInfo.getDocumentation() != null && !fieldInfo.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = fieldInfo.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
        if ((enumType = enumInfo.getEnumType()) != null) {
            String pkg = this.getPackageName(enumType);
            if (pkg != null && !pkg.isEmpty()) {
                this.packageName = pkg;
            }
            this.addTypeSegments(enumType);
            this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        }
        this.addSegment(token.getStylePrefix() + fieldInfo.getName(), TokenType.ENUM_CONSTANT.getHexColor());
        this.addInitializationTokens(token, fieldInfo);
    }

    private void extractLocalFieldInfo(Token token) {
        TypeInfo declaredType;
        FieldInfo fieldInfo = token.getFieldInfo();
        if (fieldInfo == null) {
            return;
        }
        this.iconIndicator = "v";
        if (fieldInfo.getDocumentation() != null && !fieldInfo.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = fieldInfo.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
        if ((declaredType = fieldInfo.getTypeInfo()) != null) {
            this.addTypeSegments(declaredType);
            this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        }
        this.addSegment(fieldInfo.getName(), TokenType.LOCAL_FIELD.getHexColor());
        this.addInitializationTokens(token, fieldInfo);
    }

    public String getPackageName(TypeInfo type) {
        String fullName;
        TypeInfo base;
        if (type == null) {
            return null;
        }
        if (type.isTypeParameter()) {
            return null;
        }
        TypeInfo typeInfo = base = type.isArray() ? type.getElementType() : type;
        if (base == null) {
            base = type;
        }
        if ((fullName = base.getFullName()) != null && !fullName.isEmpty()) {
            return fullName;
        }
        String pkg = base.getPackageName();
        String className = this.getName(base);
        if (pkg != null && !pkg.isEmpty()) {
            return pkg + "." + className;
        }
        return className;
    }

    private void extractParameterInfo(Token token) {
        TypeInfo declaredType;
        FieldInfo fieldInfo = token.getFieldInfo();
        if (fieldInfo == null) {
            return;
        }
        this.iconIndicator = "p";
        if (fieldInfo.getDocumentation() != null && !fieldInfo.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = fieldInfo.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
        if ((declaredType = fieldInfo.getTypeInfo()) != null) {
            this.addTypeSegments(declaredType);
            this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        }
        this.addSegment(fieldInfo.getName(), TokenType.PARAMETER.getHexColor());
    }

    private void extractUndefinedInfo(Token token) {
        this.iconIndicator = "?";
    }

    public String getName(TypeInfo type) {
        if (type instanceof ScriptTypeInfo) {
            return ((ScriptTypeInfo)type).getDotSeparatedName();
        }
        return type.getDisplayName();
    }

    private void addTypeSegments(TypeInfo typeInfo) {
        this.addTypeSegments(typeInfo, 0);
    }

    private void addTypeSegments(TypeInfo typeInfo, int depth) {
        if (typeInfo == null) {
            this.addSegment("any", TokenType.DEFAULT.getHexColor());
            return;
        }
        if (depth > 25) {
            this.addSegment(this.getName(typeInfo), this.getColorForTypeInfo(typeInfo));
            return;
        }
        if (typeInfo.isParameterized()) {
            TypeInfo raw = typeInfo.getRawType();
            String rawName = this.getName(raw);
            this.splitAndAddTypeName(rawName, this.getColorForTypeInfo(raw));
            this.addSegment("<", TokenType.DEFAULT.getHexColor());
            List<TypeInfo> args = typeInfo.getAppliedTypeArgs();
            for (int i = 0; i < args.size(); ++i) {
                if (i > 0) {
                    this.addSegment(", ", TokenType.DEFAULT.getHexColor());
                }
                this.addTypeSegments(args.get(i), depth + 1);
            }
            this.addSegment(">", TokenType.DEFAULT.getHexColor());
            return;
        }
        String typeName = this.getName(typeInfo);
        this.splitAndAddTypeName(typeName, this.getColorForTypeInfo(typeInfo));
    }

    private void addDeclaredTypeParamSegments(TypeInfo typeInfo) {
        List<TypeParamInfo> params = typeInfo.getTypeParams();
        if (params == null || params.isEmpty()) {
            return;
        }
        if (typeInfo.isParameterized()) {
            return;
        }
        this.addSegment("<", TokenType.DEFAULT.getHexColor());
        for (int i = 0; i < params.size(); ++i) {
            if (i > 0) {
                this.addSegment(", ", TokenType.DEFAULT.getHexColor());
            }
            TypeParamInfo param = params.get(i);
            this.addSegment(param.getName(), TokenType.GENERIC_TYPE_PARAM.getHexColor());
            if (param.getBoundTypeName() == null || "Object".equals(param.getBoundTypeName())) continue;
            this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
            TypeInfo boundType = param.getBoundTypeInfo();
            if (boundType != null && boundType.isResolved()) {
                this.addTypeSegments(boundType);
            } else {
                this.addSegment(param.getBoundTypeName(), TokenType.IMPORTED_CLASS.getHexColor());
            }
            List<TypeInfo> additionalBoundTypes = param.getAdditionalBoundTypes();
            List<String> additionalBoundNames = param.getAdditionalBoundNames();
            for (int j = 0; j < additionalBoundNames.size(); ++j) {
                TypeInfo additionalBoundType;
                this.addSegment(" & ", TokenType.DEFAULT.getHexColor());
                TypeInfo typeInfo2 = additionalBoundType = j < additionalBoundTypes.size() ? additionalBoundTypes.get(j) : null;
                if (additionalBoundType != null && additionalBoundType.isResolved()) {
                    this.addTypeSegments(additionalBoundType);
                    if (additionalBoundType.isParameterized()) continue;
                    this.addBoundTypeParamSegments(additionalBoundType);
                    continue;
                }
                this.addSegment(additionalBoundNames.get(j), TokenType.IMPORTED_CLASS.getHexColor());
            }
        }
        this.addSegment(">", TokenType.DEFAULT.getHexColor());
    }

    private void addBoundTypeParamSegments(TypeInfo boundType) {
        List<TypeParamInfo> params = boundType.getTypeParams();
        if (params == null || params.isEmpty()) {
            return;
        }
        this.addSegment("<", TokenType.DEFAULT.getHexColor());
        for (int i = 0; i < params.size(); ++i) {
            if (i > 0) {
                this.addSegment(", ", TokenType.DEFAULT.getHexColor());
            }
            TypeParamInfo tp = params.get(i);
            this.addSegment(tp.getName(), TokenType.GENERIC_TYPE_PARAM.getHexColor());
            if (tp.getBoundTypeName() == null || "Object".equals(tp.getBoundTypeName())) continue;
            this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
            TypeInfo innerBound = tp.getBoundTypeInfo();
            if (innerBound != null && innerBound.isResolved()) {
                this.addTypeSegments(innerBound);
                if (!innerBound.isParameterized()) {
                    this.addBoundTypeParamSegments(innerBound);
                }
            } else {
                this.addSegment(tp.getBoundTypeName(), TokenType.IMPORTED_CLASS.getHexColor());
            }
            List<TypeInfo> innerAdditional = tp.getAdditionalBoundTypes();
            List<String> innerAdditionalNames = tp.getAdditionalBoundNames();
            for (int j = 0; j < innerAdditionalNames.size(); ++j) {
                TypeInfo innerAddBound;
                this.addSegment(" & ", TokenType.DEFAULT.getHexColor());
                TypeInfo typeInfo = innerAddBound = j < innerAdditional.size() ? innerAdditional.get(j) : null;
                if (innerAddBound != null && innerAddBound.isResolved()) {
                    this.addTypeSegments(innerAddBound);
                    if (innerAddBound.isParameterized()) continue;
                    this.addBoundTypeParamSegments(innerAddBound);
                    continue;
                }
                this.addSegment(innerAdditionalNames.get(j), TokenType.IMPORTED_CLASS.getHexColor());
            }
            this.addSegment(" extends ", TokenType.KEYWORD.getHexColor());
        }
        this.addSegment(">", TokenType.DEFAULT.getHexColor());
    }

    private void splitAndAddTypeName(String typeName, int typeColor) {
        int idx;
        if (typeName == null || !typeName.endsWith("[]")) {
            this.addSegment(typeName, typeColor);
            return;
        }
        for (idx = typeName.length() - 2; idx >= 1 && typeName.charAt(idx - 1) == '[' && typeName.charAt(idx) == ']'; idx -= 2) {
        }
        if (idx > 0) {
            this.addSegment(typeName.substring(0, idx), typeColor);
        }
        this.addSegment(typeName.substring(idx), TokenType.DEFAULT.getHexColor());
    }

    private void extractFieldInfoGeneric(Token token) {
        FieldInfo fieldInfo = token.getFieldInfo();
        if (fieldInfo == null) {
            return;
        }
        switch (fieldInfo.getScope()) {
            case GLOBAL: {
                this.extractGlobalFieldInfo(token);
                break;
            }
            case LOCAL: {
                this.extractLocalFieldInfo(token);
                break;
            }
            case PARAMETER: {
                this.extractParameterInfo(token);
            }
        }
    }

    private void buildConstructorDeclaration(MethodInfo constructor, TypeInfo containingType) {
        this.declaration.clear();
        int mods = constructor.getModifiers();
        if (Modifier.isPublic(mods)) {
            this.addSegment("public ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isProtected(mods)) {
            this.addSegment("protected ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isPrivate(mods)) {
            this.addSegment("private ", TokenType.KEYWORD.getHexColor());
        }
        this.addSegment(constructor.getName(), containingType.getTokenType().getHexColor());
        this.addSegment("(", TokenType.DEFAULT.getHexColor());
        List<FieldInfo> params = constructor.getParameters();
        for (int i = 0; i < params.size(); ++i) {
            FieldInfo param;
            TypeInfo paramType;
            if (i > 0) {
                this.addSegment(", ", TokenType.DEFAULT.getHexColor());
            }
            if ((paramType = (param = params.get(i)).getTypeInfo()) != null) {
                this.addTypeSegments(paramType);
                if (param.isVarArg()) {
                    this.addSegment("...", TokenType.DEFAULT.getHexColor());
                }
                this.addSegment(" ", TokenType.DEFAULT.getHexColor());
            }
            this.addSegment(param.getName(), TokenType.PARAMETER.getHexColor());
        }
        this.addSegment(")", TokenType.DEFAULT.getHexColor());
        if (constructor.getDocumentation() != null && !constructor.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = constructor.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
    }

    private void addSegment(String text, int color) {
        this.declaration.add(new TextSegment(text, color));
    }

    private void buildMethodDeclaration(MethodInfo methodInfo, TypeInfo containingType) {
        Method method = methodInfo.getJavaMethod();
        int mods = method.getModifiers();
        if (Modifier.isPublic(mods)) {
            this.addSegment("public ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isProtected(mods)) {
            this.addSegment("protected ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isPrivate(mods)) {
            this.addSegment("private ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isStatic(mods)) {
            this.addSegment("static ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isFinal(mods)) {
            this.addSegment("final ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isAbstract(mods)) {
            this.addSegment("abstract ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isSynchronized(mods)) {
            this.addSegment("synchronized ", TokenType.KEYWORD.getHexColor());
        }
        TypeInfo returnType = methodInfo.getReturnType();
        String returnTypeName = returnType.getSimpleName();
        this.splitAndAddTypeName(returnTypeName, this.getColorForTypeInfo(methodInfo.getReturnType()));
        this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        this.addSegment(method.getName(), TokenType.METHOD_DECL.getHexColor());
        this.addSegment("(", TokenType.DEFAULT.getHexColor());
        List<FieldInfo> params = methodInfo.getParameters();
        for (int i = 0; i < params.size(); ++i) {
            FieldInfo param;
            TypeInfo paramType;
            if (i > 0) {
                this.addSegment(", ", TokenType.DEFAULT.getHexColor());
            }
            if ((paramType = (param = params.get(i)).getTypeInfo()) != null) {
                this.addTypeSegments(paramType);
                if (param.isVarArg()) {
                    this.addSegment("...", TokenType.DEFAULT.getHexColor());
                }
                this.addSegment(" ", TokenType.DEFAULT.getHexColor());
            }
            this.addSegment(param.getName(), TokenType.PARAMETER.getHexColor());
        }
        this.addSegment(")", TokenType.DEFAULT.getHexColor());
    }

    private void buildBasicMethodDeclaration(MethodInfo methodInfo, TypeInfo containingType) {
        if (methodInfo.isDeclaration() && methodInfo.getDeclarationOffset() >= 0) {
            String modifiers = this.extractModifiersAtPosition(methodInfo.getDeclarationOffset());
            if (modifiers != null && !modifiers.isEmpty()) {
                this.addSegment(modifiers + " ", TokenType.KEYWORD.getHexColor());
            }
        } else if (methodInfo.isStatic()) {
            this.addSegment("static ", TokenType.KEYWORD.getHexColor());
        }
        TypeInfo returnType = methodInfo.getReturnType();
        if (ScriptDocument.INSTANCE.isJavaScript() && methodInfo.isDeclaration()) {
            this.addSegment("function ", TokenType.KEYWORD.getHexColor());
        } else if (returnType != null) {
            this.addTypeSegments(returnType);
            this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        } else {
            this.addSegment("void ", TokenType.KEYWORD.getHexColor());
        }
        this.addSegment(methodInfo.getName(), TokenType.METHOD_DECL.getHexColor());
        this.addSegment("(", TokenType.DEFAULT.getHexColor());
        List<FieldInfo> params = methodInfo.getParameters();
        for (int i = 0; i < params.size(); ++i) {
            FieldInfo param;
            TypeInfo paramType;
            if (i > 0) {
                this.addSegment(", ", TokenType.DEFAULT.getHexColor());
            }
            if ((paramType = (param = params.get(i)).getTypeInfo()) != null) {
                this.addTypeSegments(paramType);
                if (param.isVarArg()) {
                    this.addSegment("...", TokenType.DEFAULT.getHexColor());
                }
                this.addSegment(" ", TokenType.DEFAULT.getHexColor());
            }
            this.addSegment(param.getName(), TokenType.PARAMETER.getHexColor());
        }
        this.addSegment(")", TokenType.DEFAULT.getHexColor());
        JSDocInfo jsDoc = methodInfo.getJSDocInfo();
        if (jsDoc != null) {
            this.formatJSDocumentation(jsDoc, methodInfo.getParameters());
        } else if (methodInfo.getDocumentation() != null && !methodInfo.getDocumentation().isEmpty()) {
            String[] docLines;
            for (String line : docLines = methodInfo.getDocumentation().split("\n")) {
                this.documentation.add(line);
            }
        }
    }

    private boolean shouldPreferMethodInfo(MethodInfo methodInfo) {
        if (methodInfo.getJSDocInfo() != null) {
            return true;
        }
        String doc = methodInfo.getDocumentation();
        if (doc != null && !doc.isEmpty()) {
            return true;
        }
        for (FieldInfo param : methodInfo.getParameters()) {
            String name = param.getName();
            if (name == null || name.matches("arg\\d+")) continue;
            return true;
        }
        return false;
    }

    private static List<TextSegment> tokenizeCodeLine(String line) {
        ArrayList<TextSegment> tokens = new ArrayList<TextSegment>();
        if (line == null || line.isEmpty()) {
            return tokens;
        }
        HashSet<String> keywords = new HashSet<String>(Arrays.asList("var", "let", "const", "function", "return", "if", "else", "while", "for", "do", "break", "continue", "new", "this", "null", "undefined", "true", "false", "typeof", "instanceof", "void", "delete", "in", "of", "class", "extends", "import", "export", "from", "try", "catch", "finally", "throw", "switch", "case", "default", "with", "debugger", "yield", "async", "await"));
        int i = 0;
        int len = line.length();
        while (i < len) {
            char nc;
            int start;
            char c = line.charAt(i);
            if (c == '/' && i + 1 < len && line.charAt(i + 1) == '/') {
                tokens.add(new TextSegment(line.substring(i), 6986069));
                break;
            }
            if (c == '\"' || c == '\'') {
                char quote = c;
                int start2 = i++;
                while (i < len && line.charAt(i) != quote) {
                    if (line.charAt(i) == '\\') {
                        ++i;
                    }
                    ++i;
                }
                if (i < len) {
                    ++i;
                }
                tokens.add(new TextSegment(line.substring(start2, i), 6981465));
                continue;
            }
            if (Character.isDigit(c)) {
                start = i++;
                while (i < len && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '.' || line.charAt(i) == 'x' || line.charAt(i) == 'X' || line.charAt(i) >= 'a' && line.charAt(i) <= 'f' || line.charAt(i) >= 'A' && line.charAt(i) <= 'F')) {
                    ++i;
                }
                tokens.add(new TextSegment(line.substring(start, i), 6854587));
                continue;
            }
            if (Character.isLetter(c) || c == '_' || c == '$') {
                start = i++;
                while (i < len && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_' || line.charAt(i) == '$')) {
                    ++i;
                }
                String word = line.substring(start, i);
                int color = keywords.contains(word) ? 13400114 : 11122630;
                tokens.add(new TextSegment(word, color));
                continue;
            }
            start = i++;
            while (i < len && !Character.isLetterOrDigit(nc = line.charAt(i)) && nc != '_' && nc != '$' && nc != '\"' && nc != '\'' && nc != '/') {
                ++i;
            }
            tokens.add(new TextSegment(line.substring(start, i), 11122630));
        }
        return tokens;
    }

    private static boolean isCodeOperatorChar(char c) {
        return "=<>!+-*/%&|^~,;:([{".indexOf(c) >= 0;
    }

    private void addDescriptionAsMarkdown(String description) {
        if (description == null || description.isEmpty()) {
            return;
        }
        String[] lines = description.split("\n", -1);
        boolean inCodeFence = false;
        boolean nextIsFirst = false;
        for (String line : lines) {
            String text;
            DocumentationLine docLine = new DocumentationLine();
            if (line.trim().startsWith("```")) {
                boolean bl = inCodeFence = !inCodeFence;
                if (inCodeFence) {
                    nextIsFirst = true;
                }
                this.jsDocLines.add(new DocumentationLine());
                continue;
            }
            if (inCodeFence) {
                int leadingSpaces;
                if (line.trim().isEmpty()) {
                    DocumentationLine emptyCode = new DocumentationLine();
                    emptyCode.isCodeLine = true;
                    if (nextIsFirst) {
                        emptyCode.isCodeBlockFirst = true;
                        nextIsFirst = false;
                    }
                    this.jsDocLines.add(emptyCode);
                    continue;
                }
                for (leadingSpaces = 0; leadingSpaces < line.length() && line.charAt(leadingSpaces) == ' '; ++leadingSpaces) {
                }
                String codeLine = leadingSpaces > 0 ? line.substring(leadingSpaces) : line;
                List<TextSegment> codeTokens = TokenHoverInfo.tokenizeCodeLine(codeLine);
                if (codeTokens.isEmpty()) {
                    docLine.addSegment(codeLine, 6981465);
                } else {
                    for (TextSegment tok : codeTokens) {
                        docLine.segments.add(tok);
                    }
                }
                docLine.isCodeLine = true;
                docLine.codeLeadingSpaces = leadingSpaces;
                if (nextIsFirst) {
                    docLine.isCodeBlockFirst = true;
                    nextIsFirst = false;
                }
                this.jsDocLines.add(docLine);
                continue;
            }
            if (line.trim().startsWith("###")) {
                text = line.trim().substring(3).trim();
                docLine.addSegment(text, 16762477);
                this.jsDocLines.add(docLine);
                continue;
            }
            if (line.trim().startsWith("##")) {
                text = line.trim().substring(2).trim();
                docLine.addSegment(text, 16762477);
                this.jsDocLines.add(docLine);
                continue;
            }
            if (line.trim().isEmpty()) {
                this.jsDocLines.add(new DocumentationLine());
                continue;
            }
            docLine.addText(line.trim());
            this.jsDocLines.add(docLine);
        }
        while (!this.jsDocLines.isEmpty() && this.jsDocLines.get(this.jsDocLines.size() - 1).isEmpty() && !this.jsDocLines.get((int)(this.jsDocLines.size() - 1)).isCodeLine) {
            this.jsDocLines.remove(this.jsDocLines.size() - 1);
        }
    }

    private void formatJSDocumentation(JSDocInfo jsDoc, List<FieldInfo> methodParams) {
        List<JSDocTag> allTags;
        List<JSDocSeeTag> seeTags;
        JSDocReturnTag returnTag;
        List<JSDocParamTag> paramTags;
        JSDocTypeTag typeTag;
        String description = jsDoc.getDescription();
        if (description != null && !description.isEmpty()) {
            this.addDescriptionAsMarkdown(description);
        }
        if ((typeTag = jsDoc.getTypeTag()) != null) {
            String typeDesc;
            DocumentationLine typeLine = new DocumentationLine();
            typeLine.addSegment("Type:", TokenType.JSDOC_TAG.getHexColor());
            if (typeTag.hasType()) {
                typeLine.addText(" ");
                typeLine.addSegment("{", TokenType.JSDOC_TYPE.getHexColor());
                typeLine.addSegment(typeTag.getTypeName(), TokenType.getColor(typeTag.getTypeInfo()));
                typeLine.addSegment("}", TokenType.JSDOC_TYPE.getHexColor());
            }
            if ((typeDesc = typeTag.getDescription()) != null && !typeDesc.isEmpty()) {
                typeLine.addText(" - ");
                typeLine.addText(typeDesc.trim());
            }
            this.jsDocLines.add(typeLine);
        }
        if ((paramTags = jsDoc.getParamTags()) != null && !paramTags.isEmpty()) {
            DocumentationLine paramsHeader = new DocumentationLine();
            paramsHeader.addSegment("Params:", TokenType.JSDOC_TAG.getHexColor());
            this.jsDocLines.add(paramsHeader);
            for (JSDocParamTag paramTag : paramTags) {
                String paramDesc;
                DocumentationLine paramLine = new DocumentationLine();
                String paramName = paramTag.getParamName();
                if (paramName == null || paramName.isEmpty()) {
                    paramLine.addSegment("param", TokenType.JSDOC_TAG.getHexColor());
                } else {
                    boolean paramExists = methodParams != null && methodParams.stream().anyMatch(p -> p.getName().equals(paramName));
                    paramLine.addSegment(paramName, paramExists ? TokenType.PARAMETER.getHexColor() : TokenType.UNDEFINED_VAR.getHexColor());
                }
                if (paramTag.hasType()) {
                    paramLine.addSegment(" {", TokenType.JSDOC_TYPE.getHexColor());
                    paramLine.addSegment(paramTag.getTypeName(), TokenType.getColor(paramTag.getTypeInfo()));
                    paramLine.addSegment("}", TokenType.JSDOC_TYPE.getHexColor());
                }
                if ((paramDesc = paramTag.getDescription()) != null && !paramDesc.isEmpty()) {
                    String[] paramDescLines = paramDesc.split("\n", -1);
                    paramLine.addText(" - ");
                    paramLine.addText(paramDescLines[0].trim());
                    this.jsDocLines.add(paramLine);
                    for (int i = 1; i < paramDescLines.length; ++i) {
                        if (paramDescLines[i].trim().isEmpty()) continue;
                        DocumentationLine contLine = new DocumentationLine();
                        contLine.addText("  " + paramDescLines[i].trim());
                        this.jsDocLines.add(contLine);
                    }
                    continue;
                }
                this.jsDocLines.add(paramLine);
            }
        }
        if ((returnTag = jsDoc.getReturnTag()) != null) {
            Object returnDesc;
            DocumentationLine returnLine = new DocumentationLine();
            returnLine.addSegment("Returns:", TokenType.JSDOC_TAG.getHexColor());
            if (returnTag.hasType()) {
                returnLine.addText(" ");
                returnLine.addSegment("{", TokenType.JSDOC_TYPE.getHexColor());
                returnLine.addSegment(returnTag.getTypeName(), TokenType.getColor(returnTag.getTypeInfo()));
                returnLine.addSegment("}", TokenType.JSDOC_TYPE.getHexColor());
            }
            if ((returnDesc = returnTag.getDescription()) != null && !((String)returnDesc).isEmpty()) {
                String[] returnDescLines = ((String)returnDesc).split("\n", -1);
                returnLine.addText(" - ");
                returnLine.addText(returnDescLines[0].trim());
                this.jsDocLines.add(returnLine);
                for (int i = 1; i < returnDescLines.length; ++i) {
                    if (returnDescLines[i].trim().isEmpty()) continue;
                    DocumentationLine contLine = new DocumentationLine();
                    contLine.addText("  " + returnDescLines[i].trim());
                    this.jsDocLines.add(contLine);
                }
            } else {
                this.jsDocLines.add(returnLine);
            }
        }
        if ((seeTags = jsDoc.getSeeTags()) != null && !seeTags.isEmpty()) {
            for (JSDocSeeTag seeTag : seeTags) {
                DocumentationLine seeLine = new DocumentationLine();
                seeLine.addSegment("See:", TokenType.JSDOC_TAG.getHexColor());
                String reference = seeTag.getReference();
                if (seeTag.hasLinkText()) {
                    seeLine.addText(" ");
                    seeLine.addSegment(seeTag.getLinkText(), TokenType.INTERFACE_DECL.getHexColor());
                } else if (reference != null) {
                    seeLine.addText(" " + reference);
                }
                this.jsDocLines.add(seeLine);
            }
        }
        if ((allTags = jsDoc.getAllTags()) != null && !allTags.isEmpty()) {
            for (JSDocTag tag : allTags) {
                String tagDesc;
                String normalized;
                String tagName = tag.getTagName();
                if (tagName == null || "type".equals(normalized = tagName.toLowerCase()) || "param".equals(normalized) || "return".equals(normalized) || "returns".equals(normalized) || "see".equals(normalized)) continue;
                DocumentationLine tagLine = new DocumentationLine();
                String capTagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
                tagLine.addSegment(capTagName + ":", TokenType.JSDOC_TAG.getHexColor());
                if (tag.hasType()) {
                    tagLine.addSegment(" {", TokenType.JSDOC_TYPE.getHexColor());
                    tagLine.addSegment(tag.getTypeName(), TokenType.getColor(tag.getTypeInfo()));
                    tagLine.addSegment("}", TokenType.JSDOC_TYPE.getHexColor());
                }
                if ((tagDesc = tag.getDescription()) != null && !tagDesc.isEmpty()) {
                    tagLine.addText(" - ");
                    tagLine.addText(tagDesc.trim());
                }
                this.jsDocLines.add(tagLine);
            }
        }
    }

    private void extractJavadoc(Method method) {
        if (method.isAnnotationPresent(Deprecated.class)) {
            this.additionalInfo.add("@Deprecated");
        }
    }

    private String extractModifiersAtPosition(int position) {
        String[] words;
        int searchStart;
        if (this.token.getParentLine().getParent() == null) {
            return null;
        }
        String text = this.token.getParentLine().getParent().getText();
        if (position < 0 || position >= text.length()) {
            return null;
        }
        for (searchStart = position - 1; searchStart >= 0; --searchStart) {
            char c = text.charAt(searchStart);
            if (c != ';' && c != '{' && c != '}' && c != '\n') continue;
            ++searchStart;
            break;
        }
        if (searchStart < 0) {
            searchStart = 0;
        }
        String beforeDecl = text.substring(searchStart, position).trim();
        StringBuilder modifiers = new StringBuilder();
        for (String word : words = beforeDecl.split("\\s+")) {
            if (!TypeResolver.isModifier(word)) continue;
            if (modifiers.length() > 0) {
                modifiers.append(" ");
            }
            modifiers.append(word);
        }
        return modifiers.toString();
    }

    private void addFieldModifiers(int mods) {
        if (Modifier.isPublic(mods)) {
            this.addSegment("public ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isProtected(mods)) {
            this.addSegment("protected ", TokenType.KEYWORD.getHexColor());
        } else if (Modifier.isPrivate(mods)) {
            this.addSegment("private ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isStatic(mods)) {
            this.addSegment("static ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isFinal(mods)) {
            this.addSegment("final ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isVolatile(mods)) {
            this.addSegment("volatile ", TokenType.KEYWORD.getHexColor());
        }
        if (Modifier.isTransient(mods)) {
            this.addSegment("transient ", TokenType.KEYWORD.getHexColor());
        }
    }

    private int getColorForClass(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return TokenType.KEYWORD.getHexColor();
        }
        if (clazz.isInterface()) {
            return TokenType.INTERFACE_DECL.getHexColor();
        }
        if (clazz.isEnum()) {
            return TokenType.ENUM_DECL.getHexColor();
        }
        return TokenType.IMPORTED_CLASS.getHexColor();
    }

    private int getColorForTypeInfo(TypeInfo typeInfo) {
        return TokenType.getColor(typeInfo);
    }

    private void addInitializationTokens(Token token, FieldInfo fieldInfo) {
        if (!fieldInfo.hasInitializer()) {
            return;
        }
        ScriptLine line = token.getParentLine();
        if (line == null || line.getParent() == null) {
            return;
        }
        ScriptDocument doc = line.getParent();
        List<Token> initTokens = doc.getTokensInRange(fieldInfo.getInitStart(), fieldInfo.getInitEnd() + 1);
        if (initTokens.isEmpty()) {
            return;
        }
        this.addSegment(" ", TokenType.DEFAULT.getHexColor());
        String lastText = null;
        for (Token initToken : initTokens) {
            String text = initToken.getText();
            if ((text = text.replaceAll("\\s+", " ").trim()).isEmpty()) continue;
            if (lastText != null && this.shouldAddSpace(lastText, text)) {
                this.addSegment(" ", TokenType.DEFAULT.getHexColor());
            }
            this.addSegment(text, initToken.getType().getHexColor());
            lastText = text;
        }
    }

    private boolean shouldAddSpace(String lastToken, String currentToken) {
        boolean firstIsIdentifier;
        if (lastToken.isEmpty() || currentToken.isEmpty()) {
            return false;
        }
        char lastChar = lastToken.charAt(lastToken.length() - 1);
        char firstChar = currentToken.charAt(0);
        if (firstChar == '(' || firstChar == '[' || firstChar == '{' || firstChar == '.' || firstChar == ',' || firstChar == ';' || firstChar == '>' || firstChar == ':' && lastToken.equals("?")) {
            return false;
        }
        if (lastChar == '(' || lastChar == '[' || lastChar == '{' || lastChar == '.' || lastChar == '<') {
            return false;
        }
        if (this.isOperatorChar(lastChar) || this.isOperatorChar(firstChar)) {
            return true;
        }
        if (lastChar == ')' || lastChar == ']' || lastChar == '}') {
            return true;
        }
        if (lastChar == ':' && !lastToken.equals("?:") || lastChar == ',') {
            return true;
        }
        boolean lastIsIdentifier = Character.isLetterOrDigit(lastChar) || lastChar == '_';
        boolean bl = firstIsIdentifier = Character.isLetterOrDigit(firstChar) || firstChar == '_';
        return lastIsIdentifier && firstIsIdentifier;
    }

    private boolean isOperatorChar(char c) {
        return "+-*/%<>=!&|^?:".indexOf(c) >= 0;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getIconIndicator() {
        return this.iconIndicator;
    }

    public List<TextSegment> getDeclaration() {
        return this.declaration;
    }

    public List<String> getDocumentation() {
        return this.documentation;
    }

    public List<DocumentationLine> getJSDocLines() {
        return this.jsDocLines;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public List<String> getAdditionalInfo() {
        return this.additionalInfo;
    }

    public Token getToken() {
        return this.token;
    }

    public boolean hasContent() {
        return !this.declaration.isEmpty() || !this.errors.isEmpty() || !this.documentation.isEmpty() || !this.jsDocLines.isEmpty();
    }

    public boolean hasJSDocContent() {
        return !this.jsDocLines.isEmpty();
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public static class TextSegment {
        public final String text;
        public final int color;
        public static final int COLOR_KEYWORD = 13400114;
        public static final int COLOR_TYPE = 6854587;
        public static final int COLOR_CLASS = 11122630;
        public static final int COLOR_METHOD = 16762477;
        public static final int COLOR_FIELD = 9991850;
        public static final int COLOR_PARAM = 11122630;
        public static final int COLOR_PACKAGE = 0x808080;
        public static final int COLOR_DEFAULT = 11122630;
        public static final int COLOR_ERROR = 16739176;
        public static final int COLOR_STRING = 6981465;
        public static final int COLOR_ANNOTATION = 12301609;

        public TextSegment(String text, int color) {
            this.text = text;
            this.color = color;
        }
    }

    public static class DocumentationLine {
        public final List<TextSegment> segments = new ArrayList<TextSegment>();
        public boolean isCodeLine = false;
        public boolean isCodeBlockFirst = false;
        public int codeLeadingSpaces = 0;

        public void addSegment(String text, int color) {
            this.segments.add(new TextSegment(text, color));
        }

        public void addText(String text) {
            this.segments.add(new TextSegment(text, 11122630));
        }

        public boolean isEmpty() {
            return this.segments.isEmpty() || this.segments.stream().allMatch(s -> s.text == null || s.text.isEmpty());
        }
    }
}

