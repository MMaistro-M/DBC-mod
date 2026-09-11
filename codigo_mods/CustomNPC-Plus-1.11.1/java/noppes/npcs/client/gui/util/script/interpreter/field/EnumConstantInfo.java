/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class EnumConstantInfo {
    private final FieldInfo fieldInfo;
    private final MethodCallInfo constructorCall;
    private final TypeInfo enumType;

    public EnumConstantInfo(FieldInfo fieldInfo, MethodCallInfo constructorCall, TypeInfo enumType) {
        this.fieldInfo = fieldInfo;
        this.constructorCall = constructorCall;
        this.enumType = enumType;
    }

    public static List<EnumConstantInfo> parseEnumConstants(ScriptTypeInfo enumType, String bodyText, int bodyOffset, Pattern keywordPattern) {
        ArrayList<EnumConstantInfo> constants = new ArrayList<EnumConstantInfo>();
        int constantsEnd = EnumConstantInfo.findEnumConstantsEnd(bodyText);
        if (constantsEnd <= 0) {
            constantsEnd = bodyText.length();
        }
        Pattern constantPattern = Pattern.compile("([A-Za-z_][a-zA-Z0-9_]*)\\s*(\\(([^)]*)\\))?");
        Matcher m = constantPattern.matcher(bodyText);
        int lastEnd = 0;
        while (m.find() && m.start() < constantsEnd) {
            int absPos = bodyOffset + m.start();
            if (ScriptDocument.INSTANCE.isExcluded(absPos)) {
                lastEnd = m.end();
                continue;
            }
            String beforeMatch = bodyText.substring(lastEnd, m.start()).trim();
            if (!beforeMatch.isEmpty() && !beforeMatch.equals(",")) continue;
            String constantName = m.group(1);
            String argsClause = m.group(2);
            String args = m.group(3);
            if (keywordPattern.matcher(constantName).matches()) {
                lastEnd = m.end();
                continue;
            }
            int initStart = -1;
            int initEnd = -1;
            MethodCallInfo constructorCall = null;
            if (argsClause != null && !argsClause.isEmpty()) {
                initStart = bodyOffset + m.start(2);
                initEnd = bodyOffset + m.end(2) - 1;
                constructorCall = EnumConstantInfo.createConstructorCall(enumType, constantName, absPos, initStart, initEnd);
            } else if (enumType.hasConstructors()) {
                initEnd = initStart = absPos + constantName.length();
                constructorCall = EnumConstantInfo.createConstructorCall(enumType, constantName, absPos, initStart, initEnd);
            }
            FieldInfo fieldInfo = FieldInfo.enumConstant(constantName, enumType, absPos, initStart, initEnd, null);
            EnumConstantInfo constantInfo = new EnumConstantInfo(fieldInfo, constructorCall, enumType);
            fieldInfo.setEnumConstantInfo(constantInfo);
            constants.add(constantInfo);
            lastEnd = m.end();
        }
        return constants;
    }

    private static MethodCallInfo createConstructorCall(ScriptTypeInfo enumType, String constantName, int constantStart, int openParenPos, int closeParenPos) {
        List<MethodInfo> constructors = enumType.getConstructors();
        List<MethodCallInfo.Argument> arguments = ScriptDocument.INSTANCE.parseMethodArguments(openParenPos + 1, closeParenPos, null, enumType);
        MethodInfo matchedConstructor = null;
        ArrayList<MethodInfo> candidates = new ArrayList<MethodInfo>();
        for (MethodInfo constructor : constructors) {
            candidates.add(constructor);
            if (constructor.getParameterCount() != arguments.size()) continue;
            matchedConstructor = constructor;
            break;
        }
        MethodCallInfo callInfo = new MethodCallInfo(constantName, constantStart, openParenPos, openParenPos, closeParenPos, arguments, enumType, matchedConstructor);
        callInfo.setConstructor(true);
        callInfo.validate();
        return callInfo;
    }

    public static EnumConstantInfo fromReflection(String constantName, TypeInfo enumType, Field javaField) {
        if (enumType == null || enumType.getJavaClass() == null || !enumType.getJavaClass().isEnum()) {
            return null;
        }
        try {
            FieldInfo fieldInfo = FieldInfo.enumConstant(constantName, enumType, -1, -1, -1, javaField);
            EnumConstantInfo enumInfo = new EnumConstantInfo(fieldInfo, null, enumType);
            fieldInfo.setEnumConstantInfo(enumInfo);
            return enumInfo;
        }
        catch (SecurityException e) {
            return null;
        }
    }

    private static int findEnumConstantsEnd(String bodyText) {
        int parenDepth = 0;
        boolean foundConstant = false;
        for (int i = 0; i < bodyText.length(); ++i) {
            char c = bodyText.charAt(i);
            if (c == '(') {
                ++parenDepth;
            } else if (c == ')') {
                --parenDepth;
            } else {
                if (c == ';' && parenDepth == 0) {
                    return i;
                }
                if (c == '{' && parenDepth == 0) {
                    return EnumConstantInfo.findStatementStart(bodyText, i);
                }
            }
            if (!Character.isJavaIdentifierStart(c)) continue;
            foundConstant = true;
        }
        return foundConstant ? bodyText.length() : -1;
    }

    private static int findStatementStart(String text, int fromPos) {
        int parenDepth = 0;
        for (int pos = fromPos - 1; pos >= 0; --pos) {
            char c = text.charAt(pos);
            if (c == ')') {
                ++parenDepth;
                continue;
            }
            if (c == '(') {
                --parenDepth;
                continue;
            }
            if (c != ',' && c != ';' && c != '{' && c != '}' || parenDepth != 0) continue;
            return pos + 1;
        }
        return 0;
    }

    public FieldInfo getFieldInfo() {
        return this.fieldInfo;
    }

    public MethodCallInfo getConstructorCall() {
        return this.constructorCall;
    }

    public TypeInfo getEnumType() {
        return this.enumType;
    }

    public String getName() {
        return this.fieldInfo.getName();
    }

    public int getDeclarationOffset() {
        return this.fieldInfo.getDeclarationOffset();
    }

    public boolean hasError() {
        return this.constructorCall != null && this.constructorCall.hasError();
    }

    public String getErrorMessage() {
        return null;
    }
}

