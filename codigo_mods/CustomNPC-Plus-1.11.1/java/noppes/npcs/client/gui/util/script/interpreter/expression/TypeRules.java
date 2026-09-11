/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.expression;

import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionTypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.expression.OperatorType;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class TypeRules {
    private static boolean isJsNumber(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        String name = type.getSimpleName();
        String full = type.getFullName();
        return "number".equals(name) || "number".equals(full);
    }

    private static boolean isNumericPrimitiveOrWrapper(TypeInfo type) {
        if (type == null || type.getJavaClass() == null) {
            return false;
        }
        Class<?> cls = type.getJavaClass();
        return cls == Byte.TYPE || cls == Byte.class || cls == Short.TYPE || cls == Short.class || cls == Integer.TYPE || cls == Integer.class || cls == Long.TYPE || cls == Long.class || cls == Float.TYPE || cls == Float.class || cls == Double.TYPE || cls == Double.class || cls == Character.TYPE || cls == Character.class;
    }

    private static boolean isAny(TypeInfo type) {
        if (type == null) {
            return false;
        }
        if (type == TypeInfo.ANY) {
            return true;
        }
        String full = type.getFullName();
        return "any".equals(full);
    }

    public static boolean isNumeric(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        if (TypeRules.isJsNumber(type)) {
            return true;
        }
        String name = type.getSimpleName();
        return "int".equals(name) || "long".equals(name) || "float".equals(name) || "double".equals(name) || "byte".equals(name) || "short".equals(name) || "char".equals(name);
    }

    public static boolean isIntegral(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        String name = type.getSimpleName();
        return "int".equals(name) || "long".equals(name) || "byte".equals(name) || "short".equals(name) || "char".equals(name);
    }

    public static boolean isFloatingPoint(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        if (TypeRules.isJsNumber(type)) {
            return true;
        }
        String name = type.getSimpleName();
        return "float".equals(name) || "double".equals(name);
    }

    public static boolean isBoolean(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        return "boolean".equals(type.getSimpleName());
    }

    public static boolean isString(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        String name = type.getSimpleName();
        String fullName = type.getFullName();
        return "String".equals(name) || "java.lang.String".equals(fullName);
    }

    public static TypeInfo binaryNumericPromotion(TypeInfo left, TypeInfo right) {
        if (TypeRules.isJsNumber(left) || TypeRules.isJsNumber(right)) {
            return TypeInfo.NUMBER;
        }
        if (left == null || right == null || !left.isResolved() || !right.isResolved()) {
            return null;
        }
        String l = left.getSimpleName();
        String r = right.getSimpleName();
        if ("double".equals(l) || "double".equals(r)) {
            return TypeInfo.fromPrimitive("double");
        }
        if ("float".equals(l) || "float".equals(r)) {
            return TypeInfo.fromPrimitive("float");
        }
        if ("long".equals(l) || "long".equals(r)) {
            return TypeInfo.fromPrimitive("long");
        }
        return TypeInfo.fromPrimitive("int");
    }

    public static TypeInfo unaryNumericPromotion(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return null;
        }
        if (TypeRules.isJsNumber(type)) {
            return TypeInfo.NUMBER;
        }
        String name = type.getSimpleName();
        if ("double".equals(name) || "float".equals(name) || "long".equals(name)) {
            return type;
        }
        if ("byte".equals(name) || "short".equals(name) || "char".equals(name) || "int".equals(name)) {
            return TypeInfo.fromPrimitive("int");
        }
        return null;
    }

    public static TypeInfo resolveBinaryOperatorType(OperatorType op, TypeInfo left, TypeInfo right) {
        if (op == null) {
            return null;
        }
        switch (op.getCategory()) {
            case ARITHMETIC: {
                if (op == OperatorType.ADD && (TypeRules.isString(left) || TypeRules.isString(right))) {
                    return TypeRules.validateAgainstExpectedType(TypeInfo.string());
                }
                if (TypeRules.isAny(left) || TypeRules.isAny(right)) {
                    return TypeRules.validateAgainstExpectedType(TypeInfo.ANY);
                }
                if (TypeRules.isNumeric(left) && TypeRules.isNumeric(right)) {
                    TypeInfo promoted = TypeRules.binaryNumericPromotion(left, right);
                    return TypeRules.validateAgainstExpectedType(promoted);
                }
                return null;
            }
            case RELATIONAL: {
                if (op == OperatorType.EQUALS || op == OperatorType.NOT_EQUALS) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                if (TypeRules.isAny(left) || TypeRules.isAny(right)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                if (TypeRules.isNumeric(left) && TypeRules.isNumeric(right)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                return null;
            }
            case LOGICAL: {
                if (TypeRules.isAny(left) || TypeRules.isAny(right)) {
                    return TypeInfo.ANY;
                }
                if (TypeRules.isBoolean(left) && TypeRules.isBoolean(right)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                return null;
            }
            case BITWISE: {
                if (TypeRules.isAny(left) || TypeRules.isAny(right)) {
                    return TypeRules.validateAgainstExpectedType(TypeInfo.ANY);
                }
                if ((op == OperatorType.LEFT_SHIFT || op == OperatorType.RIGHT_SHIFT || op == OperatorType.UNSIGNED_RIGHT_SHIFT) && TypeRules.isIntegral(left)) {
                    TypeInfo promoted = TypeRules.unaryNumericPromotion(left);
                    return TypeRules.validateAgainstExpectedType(promoted);
                }
                if (TypeRules.isIntegral(left) && TypeRules.isIntegral(right)) {
                    TypeInfo promoted = TypeRules.binaryNumericPromotion(left, right);
                    return TypeRules.validateAgainstExpectedType(promoted);
                }
                if (TypeRules.isBoolean(left) && TypeRules.isBoolean(right)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                return null;
            }
            case ASSIGNMENT: {
                return left;
            }
        }
        return null;
    }

    public static TypeInfo resolveUnaryOperatorType(OperatorType op, TypeInfo operand) {
        if (op == null || operand == null || !operand.isResolved()) {
            return null;
        }
        if (TypeRules.isAny(operand)) {
            if (op == OperatorType.LOGICAL_NOT) {
                return TypeInfo.fromPrimitive("boolean");
            }
            return TypeInfo.ANY;
        }
        switch (op) {
            case UNARY_PLUS: 
            case UNARY_MINUS: {
                if (TypeRules.isNumeric(operand)) {
                    TypeInfo promoted = TypeRules.unaryNumericPromotion(operand);
                    return TypeRules.validateAgainstExpectedType(promoted);
                }
                return null;
            }
            case BITWISE_NOT: {
                if (TypeRules.isIntegral(operand)) {
                    TypeInfo promoted = TypeRules.unaryNumericPromotion(operand);
                    return TypeRules.validateAgainstExpectedType(promoted);
                }
                return null;
            }
            case LOGICAL_NOT: {
                if (TypeRules.isBoolean(operand)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                return null;
            }
            case PRE_INCREMENT: 
            case PRE_DECREMENT: 
            case POST_INCREMENT: 
            case POST_DECREMENT: {
                if (TypeRules.isNumeric(operand)) {
                    return operand;
                }
                return null;
            }
        }
        return null;
    }

    public static boolean isAssignmentCompatible(TypeInfo sourceType, TypeInfo targetType) {
        if (sourceType == null || targetType == null) {
            return false;
        }
        if (!targetType.isResolved()) {
            return true;
        }
        if (TypeRules.isAny(sourceType) || TypeRules.isAny(targetType)) {
            return true;
        }
        if ("<null>".equals(sourceType.getFullName())) {
            return !TypeRules.isPrimitive(targetType);
        }
        if (!sourceType.isResolved()) {
            return false;
        }
        if (TypeRules.isJsNumber(sourceType) && TypeRules.isNumericPrimitiveOrWrapper(targetType) || TypeRules.isJsNumber(targetType) && TypeRules.isNumericPrimitiveOrWrapper(sourceType)) {
            return true;
        }
        if (sourceType.equals(targetType)) {
            return true;
        }
        if (sourceType.getFullName().equals(targetType.getFullName())) {
            return true;
        }
        String source = sourceType.getSimpleName();
        String target = targetType.getSimpleName();
        if ("byte".equals(source)) {
            return "short".equals(target) || "int".equals(target) || "long".equals(target) || "float".equals(target) || "double".equals(target);
        }
        if ("short".equals(source)) {
            return "int".equals(target) || "long".equals(target) || "float".equals(target) || "double".equals(target);
        }
        if ("char".equals(source)) {
            return "int".equals(target) || "long".equals(target) || "float".equals(target) || "double".equals(target);
        }
        if ("int".equals(source)) {
            return "long".equals(target) || "float".equals(target) || "double".equals(target);
        }
        if ("long".equals(source)) {
            return "float".equals(target) || "double".equals(target);
        }
        if ("float".equals(source)) {
            return "double".equals(target);
        }
        return false;
    }

    public static boolean isPrimitive(TypeInfo type) {
        if (type == null || !type.isResolved()) {
            return false;
        }
        String name = type.getSimpleName();
        return "int".equals(name) || "long".equals(name) || "float".equals(name) || "double".equals(name) || "byte".equals(name) || "short".equals(name) || "char".equals(name) || "boolean".equals(name) || "void".equals(name);
    }

    public static TypeInfo validateAgainstExpectedType(TypeInfo computedType) {
        if (computedType == null) {
            return null;
        }
        TypeInfo expectedType = ExpressionTypeResolver.CURRENT_EXPECTED_TYPE;
        if (expectedType != null && expectedType.isResolved() && computedType.isResolved()) {
            if (TypeRules.isAssignmentCompatible(computedType, expectedType)) {
                return expectedType;
            }
            return computedType;
        }
        return computedType;
    }

    public static TypeInfo resolveTernaryType(TypeInfo thenType, TypeInfo elseType) {
        if (thenType == null && elseType == null) {
            return null;
        }
        if (thenType == null) {
            return elseType;
        }
        if (elseType == null) {
            return thenType;
        }
        boolean thenIsNull = "<null>".equals(thenType.getFullName());
        boolean elseIsNull = "<null>".equals(elseType.getFullName());
        if (thenIsNull && elseIsNull) {
            return thenType;
        }
        if (thenIsNull) {
            return elseType;
        }
        if (elseIsNull) {
            return thenType;
        }
        if (!thenType.isResolved()) {
            return elseType.isResolved() ? elseType : null;
        }
        if (!elseType.isResolved()) {
            return thenType;
        }
        TypeInfo expectedType = ExpressionTypeResolver.CURRENT_EXPECTED_TYPE;
        if (expectedType != null && expectedType.isResolved()) {
            boolean thenCompatible = TypeRules.isAssignmentCompatible(thenType, expectedType);
            boolean elseCompatible = TypeRules.isAssignmentCompatible(elseType, expectedType);
            if (thenCompatible && elseCompatible) {
                return expectedType;
            }
            if (!elseCompatible) {
                return elseType;
            }
            return thenType;
        }
        if (thenType.equals(elseType)) {
            return thenType;
        }
        if (TypeRules.isNumeric(thenType) && TypeRules.isNumeric(elseType)) {
            return TypeRules.binaryNumericPromotion(thenType, elseType);
        }
        return thenType;
    }
}

