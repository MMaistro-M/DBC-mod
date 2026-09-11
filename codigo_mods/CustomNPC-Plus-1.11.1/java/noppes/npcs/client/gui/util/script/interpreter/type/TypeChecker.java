/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class TypeChecker {
    private static boolean CURRENT_JAVASCRIPT_MODE;

    private TypeChecker() {
    }

    public static void enterTypeCheckingContext(boolean javaScriptMode) {
        CURRENT_JAVASCRIPT_MODE = javaScriptMode;
    }

    static boolean isJavaScriptMode() {
        return CURRENT_JAVASCRIPT_MODE;
    }

    public static boolean isTypeCompatible(TypeInfo expected, TypeInfo actual) {
        if (expected == null) {
            return true;
        }
        if (actual == null) {
            return true;
        }
        if (expected.equals(actual)) {
            return true;
        }
        if (expected.isArray() && actual.isArray()) {
            TypeInfo expectedElement = expected.getElementType();
            TypeInfo actualElement = actual.getElementType();
            if (expectedElement != null && actualElement != null) {
                return TypeChecker.isTypeCompatible(expectedElement, actualElement);
            }
        }
        if ("__script_method_ref__".equals(actual.getFullName())) {
            return expected.isFunctionalInterface();
        }
        if ("any".equals(expected.getFullName()) || "any".equals(actual.getFullName())) {
            return true;
        }
        if (TypeChecker.isJavaScriptMode()) {
            if ("Object".equals(expected.getSimpleName()) || "Object".equals(actual.getSimpleName())) {
                return true;
            }
            if ("String".equals(expected.getSimpleName())) {
                return true;
            }
        }
        if ("<null>".equals(actual.getFullName())) {
            Class<?> expectedClass = expected.getJavaClass();
            return expectedClass != null && !expectedClass.isPrimitive() || "<null>".equals(expected.getFullName());
        }
        String expectedName = expected.getSimpleName();
        String actualName = actual.getSimpleName();
        if (expectedName == null || actualName == null) {
            return true;
        }
        if ("number".equals(expectedName) && TypeChecker.isNumericType(actualName)) {
            return true;
        }
        if ("number".equals(actualName) && TypeChecker.isNumericType(expectedName)) {
            return true;
        }
        if (TypeChecker.isNumericType(expectedName) && TypeChecker.isNumericType(actualName)) {
            if (TypeChecker.isJavaScriptMode()) {
                return true;
            }
            return TypeChecker.canWiden(actualName, expectedName);
        }
        if (expected.isJSType() && actual.isJSType() && TypeChecker.isJSTypeAssignableFrom(expected.getJSTypeInfo(), actual.getJSTypeInfo())) {
            return true;
        }
        if (expected.getFullName().equals(actual.getFullName())) {
            if (expected.isParameterized() && actual.isParameterized()) {
                return TypeChecker.areTypeArgumentsCompatible(expected, actual);
            }
            return true;
        }
        if (actual instanceof ScriptTypeInfo && TypeChecker.isScriptTypeAssignableTo((ScriptTypeInfo)actual, expected)) {
            return true;
        }
        if (expected.getJavaClass() != null && actual.getJavaClass() != null) {
            Class<?> actualClass;
            Class<?> expectedClass = expected.getJavaClass();
            if (expectedClass.isAssignableFrom(actualClass = actual.getJavaClass())) {
                return true;
            }
            if (TypeChecker.isPrimitiveWidening(actualClass, expectedClass)) {
                return true;
            }
            if (TypeChecker.isBoxingCompatible(actualClass, expectedClass)) {
                return true;
            }
        }
        if (TypeChecker.isPrimitiveOrWrapper(expectedName) && TypeChecker.isPrimitiveOrWrapper(actualName)) {
            return TypeChecker.getUnboxedName(expectedName).equals(TypeChecker.getUnboxedName(actualName));
        }
        return false;
    }

    private static boolean isJSTypeAssignableFrom(JSTypeInfo expected, JSTypeInfo actual) {
        String currentFullName;
        if (expected == null || actual == null) {
            return false;
        }
        HashSet<String> visited = new HashSet<String>();
        for (JSTypeInfo current = actual; current != null && (currentFullName = current.getFullName()) != null && visited.add(currentFullName); current = current.getResolvedParent()) {
            if (expected.getFullName() == null || !expected.getFullName().equals(currentFullName)) continue;
            return true;
        }
        return false;
    }

    private static boolean isScriptTypeAssignableTo(ScriptTypeInfo actual, TypeInfo expected) {
        String expectedFull = expected.getFullName();
        String expectedSimple = expected.getSimpleName();
        ArrayDeque<TypeInfo> worklist = new ArrayDeque<TypeInfo>();
        worklist.add(actual);
        while (!worklist.isEmpty()) {
            TypeInfo current = (TypeInfo)worklist.poll();
            if (current == null || !current.isResolved()) continue;
            String currentFull = current.getFullName();
            if (currentFull != null) {
                if (expectedFull != null && expectedFull.equals(currentFull)) {
                    return true;
                }
                if (expectedSimple != null && expectedSimple.equals(current.getSimpleName())) {
                    return true;
                }
            }
            if (current instanceof ScriptTypeInfo) {
                ScriptTypeInfo scriptCurrent = (ScriptTypeInfo)current;
                TypeInfo superClass = scriptCurrent.getSuperClass();
                if (superClass != null) {
                    worklist.add(superClass);
                }
                for (TypeInfo iface : scriptCurrent.getImplementedInterfaces()) {
                    worklist.add(iface);
                }
                continue;
            }
            if (current.getJavaClass() == null || expected.getJavaClass() == null || !expected.getJavaClass().isAssignableFrom(current.getJavaClass())) continue;
            return true;
        }
        return false;
    }

    private static boolean areTypeArgumentsCompatible(TypeInfo expected, TypeInfo actual) {
        List<TypeInfo> expectedArgs = expected.getAppliedTypeArgs();
        List<TypeInfo> actualArgs = actual.getAppliedTypeArgs();
        if (actualArgs.isEmpty()) {
            return true;
        }
        boolean allActualAreTypeParams = true;
        for (TypeInfo actualArg : actualArgs) {
            if (actualArg.isTypeParameter()) continue;
            allActualAreTypeParams = false;
            break;
        }
        if (allActualAreTypeParams) {
            return true;
        }
        for (int i = 0; i < expectedArgs.size(); ++i) {
            TypeInfo expectedArg = expectedArgs.get(i);
            TypeInfo actualArg = actualArgs.get(i);
            if (expectedArg.isTypeParameter() || actualArg.isTypeParameter() || TypeChecker.isTypeCompatible(expectedArg, actualArg) || TypeChecker.isTypeCompatible(actualArg, expectedArg)) continue;
            return false;
        }
        return true;
    }

    public static boolean isNumericType(String typeName) {
        switch (typeName) {
            case "number": 
            case "byte": 
            case "Byte": 
            case "short": 
            case "Short": 
            case "int": 
            case "Integer": 
            case "long": 
            case "Long": 
            case "float": 
            case "Float": 
            case "double": 
            case "Double": 
            case "char": 
            case "Character": {
                return true;
            }
        }
        return false;
    }

    public static boolean canWiden(String from, String to) {
        int toRank;
        int fromRank = TypeChecker.getNumericRank(from);
        return fromRank <= (toRank = TypeChecker.getNumericRank(to));
    }

    public static int getNumericRank(String typeName) {
        switch (TypeChecker.getUnboxedName(typeName)) {
            case "byte": {
                return 1;
            }
            case "short": 
            case "char": {
                return 2;
            }
            case "int": {
                return 3;
            }
            case "long": {
                return 4;
            }
            case "float": {
                return 5;
            }
            case "number": 
            case "double": {
                return 6;
            }
        }
        return 0;
    }

    public static boolean isNumericPrimitive(TypeInfo type) {
        if (type == null || type.getJavaClass() == null) {
            return false;
        }
        Class<?> cls = type.getJavaClass();
        return cls == Byte.TYPE || cls == Byte.class || cls == Short.TYPE || cls == Short.class || cls == Integer.TYPE || cls == Integer.class || cls == Long.TYPE || cls == Long.class || cls == Float.TYPE || cls == Float.class || cls == Double.TYPE || cls == Double.class;
    }

    public static boolean canPromoteNumeric(TypeInfo from, TypeInfo to) {
        if (from == null || to == null || from.getJavaClass() == null || to.getJavaClass() == null) {
            return false;
        }
        Class<Object> fromClass = from.getJavaClass();
        Class<Object> toClass = to.getJavaClass();
        if (fromClass == Byte.class) {
            fromClass = Byte.TYPE;
        }
        if (fromClass == Short.class) {
            fromClass = Short.TYPE;
        }
        if (fromClass == Integer.class) {
            fromClass = Integer.TYPE;
        }
        if (fromClass == Long.class) {
            fromClass = Long.TYPE;
        }
        if (fromClass == Float.class) {
            fromClass = Float.TYPE;
        }
        if (fromClass == Double.class) {
            fromClass = Double.TYPE;
        }
        if (toClass == Byte.class) {
            toClass = Byte.TYPE;
        }
        if (toClass == Short.class) {
            toClass = Short.TYPE;
        }
        if (toClass == Integer.class) {
            toClass = Integer.TYPE;
        }
        if (toClass == Long.class) {
            toClass = Long.TYPE;
        }
        if (toClass == Float.class) {
            toClass = Float.TYPE;
        }
        if (toClass == Double.class) {
            toClass = Double.TYPE;
        }
        int fromRank = TypeChecker.getNumericRank(fromClass);
        int toRank = TypeChecker.getNumericRank(toClass);
        return fromRank >= 0 && toRank >= 0 && fromRank <= toRank;
    }

    public static int getNumericRank(Class<?> cls) {
        if (cls == Byte.TYPE) {
            return 0;
        }
        if (cls == Short.TYPE) {
            return 1;
        }
        if (cls == Integer.TYPE) {
            return 2;
        }
        if (cls == Long.TYPE) {
            return 3;
        }
        if (cls == Float.TYPE) {
            return 4;
        }
        if (cls == Double.TYPE) {
            return 5;
        }
        return -1;
    }

    public static boolean isPrimitiveOrWrapper(String typeName) {
        switch (typeName) {
            case "byte": 
            case "Byte": 
            case "short": 
            case "Short": 
            case "int": 
            case "Integer": 
            case "long": 
            case "Long": 
            case "float": 
            case "Float": 
            case "double": 
            case "Double": 
            case "char": 
            case "Character": 
            case "boolean": 
            case "Boolean": {
                return true;
            }
        }
        return false;
    }

    public static String getUnboxedName(String typeName) {
        switch (typeName) {
            case "Byte": {
                return "byte";
            }
            case "Short": {
                return "short";
            }
            case "Integer": {
                return "int";
            }
            case "Long": {
                return "long";
            }
            case "Float": {
                return "float";
            }
            case "Double": {
                return "double";
            }
            case "Character": {
                return "char";
            }
            case "Boolean": {
                return "boolean";
            }
        }
        return typeName;
    }

    public static String getBoxedName(String typeName) {
        switch (typeName) {
            case "byte": {
                return "Byte";
            }
            case "short": {
                return "Short";
            }
            case "int": {
                return "Integer";
            }
            case "long": {
                return "Long";
            }
            case "float": {
                return "Float";
            }
            case "double": {
                return "Double";
            }
            case "char": {
                return "Character";
            }
            case "boolean": {
                return "Boolean";
            }
        }
        return typeName;
    }

    public static boolean isVoidType(String typeName) {
        return typeName == null || typeName.equals("void") || typeName.equals("Void");
    }

    public static boolean isVoidType(TypeInfo type) {
        if (type == null) {
            return true;
        }
        return TypeChecker.isVoidType(type.getSimpleName());
    }

    private static boolean isPrimitiveWidening(Class<?> from, Class<?> to) {
        if (!from.isPrimitive() || !to.isPrimitive()) {
            return false;
        }
        if (from == Byte.TYPE) {
            return to == Short.TYPE || to == Integer.TYPE || to == Long.TYPE || to == Float.TYPE || to == Double.TYPE;
        }
        if (from == Short.TYPE || from == Character.TYPE) {
            return to == Integer.TYPE || to == Long.TYPE || to == Float.TYPE || to == Double.TYPE;
        }
        if (from == Integer.TYPE) {
            return to == Long.TYPE || to == Float.TYPE || to == Double.TYPE;
        }
        if (from == Long.TYPE) {
            return to == Float.TYPE || to == Double.TYPE;
        }
        if (from == Float.TYPE) {
            return to == Double.TYPE;
        }
        return false;
    }

    private static boolean isBoxingCompatible(Class<?> from, Class<?> to) {
        Class<?> wrapper;
        if (from.isPrimitive() && (wrapper = TypeChecker.getWrapperClassInternal(from)) != null && to.isAssignableFrom(wrapper)) {
            return true;
        }
        return to.isPrimitive() && (wrapper = TypeChecker.getWrapperClassInternal(to)) != null && wrapper.isAssignableFrom(from);
    }

    private static Class<?> getWrapperClassInternal(Class<?> primitive) {
        if (primitive == Boolean.TYPE) {
            return Boolean.class;
        }
        if (primitive == Byte.TYPE) {
            return Byte.class;
        }
        if (primitive == Character.TYPE) {
            return Character.class;
        }
        if (primitive == Short.TYPE) {
            return Short.class;
        }
        if (primitive == Integer.TYPE) {
            return Integer.class;
        }
        if (primitive == Long.TYPE) {
            return Long.class;
        }
        if (primitive == Float.TYPE) {
            return Float.class;
        }
        if (primitive == Double.TYPE) {
            return Double.class;
        }
        return null;
    }

    public static TypeInfo narrowLiteralToExpectedType(String literalText, TypeInfo expectedType) {
        if (literalText == null || literalText.isEmpty() || expectedType == null) {
            return null;
        }
        String expectedSimple = expectedType.getSimpleName();
        if (expectedSimple == null) {
            return null;
        }
        String unboxed = TypeChecker.getUnboxedName(expectedSimple);
        if (!("byte".equals(unboxed) || "short".equals(unboxed) || "char".equals(unboxed))) {
            return null;
        }
        Long value = TypeChecker.parseIntegerLiteral(literalText);
        if (value == null) {
            return null;
        }
        long v = value;
        switch (unboxed) {
            case "byte": {
                if (v >= -128L && v <= 127L) break;
                return null;
            }
            case "short": {
                if (v >= -32768L && v <= 32767L) break;
                return null;
            }
            case "char": {
                if (v >= 0L && v <= 65535L) break;
                return null;
            }
            default: {
                return null;
            }
        }
        return TypeInfo.fromPrimitive(unboxed);
    }

    private static Long parseIntegerLiteral(String text) {
        String s = text.trim();
        if (s.isEmpty()) {
            return null;
        }
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        if (s.isEmpty()) {
            return null;
        }
        if (s.charAt(s.length() - 1) == 'l' || s.charAt(s.length() - 1) == 'L') {
            s = s.substring(0, s.length() - 1);
        }
        if (s.isEmpty()) {
            return null;
        }
        if ((s = s.replace("_", "")).isEmpty()) {
            return null;
        }
        try {
            long value = s.length() > 2 && s.charAt(0) == '0' && (s.charAt(1) == 'x' || s.charAt(1) == 'X') ? Long.parseUnsignedLong(s.substring(2), 16) : (s.length() > 2 && s.charAt(0) == '0' && (s.charAt(1) == 'b' || s.charAt(1) == 'B') ? Long.parseUnsignedLong(s.substring(2), 2) : Long.parseLong(s));
            return negative ? -value : value;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public static String[] getJavaKeywords() {
        String[] keywords = new String[]{"if", "else", "for", "while", "do", "switch", "case", "break", "continue", "return", "try", "catch", "finally", "throw", "throws", "new", "this", "super", "true", "false", "null", "instanceof", "import", "class", "interface", "enum", "extends", "implements", "public", "private", "protected", "static", "final", "abstract", "synchronized", "volatile", "transient", "native", "void", "boolean", "byte", "short", "int", "long", "float", "double", "char"};
        return keywords;
    }

    public static String[] getJavaScriptKeywords() {
        String[] keywords = new String[]{"function", "var", "let", "const", "if", "else", "for", "while", "do", "switch", "case", "break", "continue", "return", "try", "catch", "finally", "throw", "delete", "new", "typeof", "instanceof", "in", "of", "this", "null", "undefined", "true", "false", "async", "await", "yield", "class", "extends", "import", "export", "default"};
        return keywords;
    }

    public static boolean isJavaScriptKeyword(String keyword) {
        for (String k : TypeChecker.getJavaScriptKeywords()) {
            if (!k.equals(keyword)) continue;
            return true;
        }
        return false;
    }
}

