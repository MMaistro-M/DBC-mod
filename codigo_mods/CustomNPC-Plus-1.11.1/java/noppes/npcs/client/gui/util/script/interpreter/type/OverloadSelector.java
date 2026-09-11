/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.lang.reflect.Method;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class OverloadSelector {
    private static final int ARITY_MISMATCH_BASE = 10000;
    private static final int VARARGS_PENALTY = 50;
    private static final int UNKNOWN_TYPE_PENALTY = 10;
    private static final int COMPATIBLE_TYPE_PENALTY = 5;
    private static final int INCOMPATIBLE_TYPE_PENALTY = 100;

    public static MethodInfo selectBestOverload(List<MethodInfo> overloads, TypeInfo[] argTypes) {
        MethodInfo numericMatch;
        MethodInfo exactMatch;
        int argCount;
        if (overloads == null || overloads.isEmpty()) {
            return null;
        }
        int n = argCount = argTypes == null ? 0 : argTypes.length;
        if (argCount == 0) {
            for (MethodInfo method : overloads) {
                if (method.getParameterCount() != 0) continue;
                return method;
            }
            return OverloadSelector.selectClosestArity(overloads, 0);
        }
        boolean allArgsKnown = true;
        for (TypeInfo argType : argTypes) {
            if (argType != null) continue;
            allArgsKnown = false;
            break;
        }
        if (allArgsKnown && (exactMatch = OverloadSelector.findExactMatch(overloads, argTypes, argCount)) != null) {
            return exactMatch;
        }
        if (allArgsKnown && (numericMatch = OverloadSelector.findNumericPromotionMatch(overloads, argTypes, argCount)) != null) {
            return numericMatch;
        }
        return OverloadSelector.scoringBasedSelection(overloads, argTypes, argCount);
    }

    private static MethodInfo findExactMatch(List<MethodInfo> overloads, TypeInfo[] argTypes, int argCount) {
        for (MethodInfo method : overloads) {
            if (method.getParameterCount() != argCount) continue;
            boolean exactMatch = true;
            List<FieldInfo> params = method.getParameters();
            for (int i = 0; i < argCount; ++i) {
                TypeInfo paramType = params.get(i).getTypeInfo();
                TypeInfo argType = argTypes[i];
                if (paramType != null && paramType.equals(argType)) continue;
                exactMatch = false;
                break;
            }
            if (!exactMatch) continue;
            return method;
        }
        return null;
    }

    private static MethodInfo findNumericPromotionMatch(List<MethodInfo> overloads, TypeInfo[] argTypes, int argCount) {
        MethodInfo bestNumericMatch = null;
        int bestNumericRank = Integer.MAX_VALUE;
        for (MethodInfo method : overloads) {
            int paramRank;
            if (method.getParameterCount() != argCount) continue;
            List<FieldInfo> params = method.getParameters();
            boolean allNumeric = true;
            for (int i = 0; i < argCount; ++i) {
                TypeInfo paramType = params.get(i).getTypeInfo();
                TypeInfo argType = argTypes[i];
                if (paramType != null && TypeChecker.isNumericPrimitive(paramType) && TypeChecker.isNumericPrimitive(argType)) continue;
                allNumeric = false;
                break;
            }
            if (!allNumeric) continue;
            TypeInfo commonParamType = params.get(0).getTypeInfo();
            boolean allParamsSame = true;
            for (int i = 1; i < params.size(); ++i) {
                TypeInfo paramType = params.get(i).getTypeInfo();
                if (paramType.equals(commonParamType)) continue;
                allParamsSame = false;
                break;
            }
            if (!allParamsSame) continue;
            boolean canPromote = true;
            for (int i = 0; i < argCount; ++i) {
                if (TypeChecker.canPromoteNumeric(argTypes[i], commonParamType)) continue;
                canPromote = false;
                break;
            }
            if (!canPromote || (paramRank = TypeChecker.getNumericRank(commonParamType.getJavaClass())) >= bestNumericRank) continue;
            bestNumericRank = paramRank;
            bestNumericMatch = method;
        }
        return bestNumericMatch;
    }

    private static MethodInfo scoringBasedSelection(List<MethodInfo> overloads, TypeInfo[] argTypes, int argCount) {
        MethodInfo bestCandidate = null;
        int bestScore = Integer.MAX_VALUE;
        for (MethodInfo method : overloads) {
            int score = OverloadSelector.scoreOverload(method, argTypes, argCount);
            if (score >= bestScore) continue;
            bestScore = score;
            bestCandidate = method;
        }
        return bestCandidate != null ? bestCandidate : overloads.get(0);
    }

    public static int scoreOverload(MethodInfo method, TypeInfo[] argTypes, int argCount) {
        TypeInfo argType;
        boolean arityApplicable;
        int score = 0;
        int paramCount = method.getParameterCount();
        boolean isVarArgs = false;
        Method javaMethod = method.getJavaMethod();
        if (javaMethod != null) {
            isVarArgs = javaMethod.isVarArgs();
        } else {
            List<FieldInfo> checkParams = method.getParameters();
            if (!checkParams.isEmpty()) {
                isVarArgs = checkParams.get(checkParams.size() - 1).isVarArg();
            }
        }
        if (isVarArgs) {
            arityApplicable = argCount >= paramCount - 1;
        } else {
            boolean bl = arityApplicable = paramCount == argCount;
        }
        if (!arityApplicable) {
            score += 10000 + Math.abs(paramCount - argCount);
        }
        if (isVarArgs) {
            score += 50;
        }
        List<FieldInfo> params = method.getParameters();
        int paramsToCheck = Math.min(argCount, paramCount);
        for (int i = 0; i < paramsToCheck; ++i) {
            TypeInfo paramType = params.get(i).getTypeInfo();
            TypeInfo typeInfo = argType = argTypes != null && i < argTypes.length ? argTypes[i] : null;
            if (argType == null) {
                score += 10;
                continue;
            }
            if (paramType == null) {
                score += 10;
                continue;
            }
            if (paramType.equals(argType)) {
                score += 0;
                continue;
            }
            if (TypeChecker.isTypeCompatible(paramType, argType)) {
                score += 5;
                continue;
            }
            score += 100;
        }
        if (isVarArgs && argCount > paramCount && !params.isEmpty()) {
            TypeInfo varargElemType = params.get(paramCount - 1).getTypeInfo();
            for (int i = paramCount; i < argCount; ++i) {
                TypeInfo typeInfo = argType = argTypes != null && i < argTypes.length ? argTypes[i] : null;
                if (argType == null || varargElemType == null) {
                    score += 10;
                    continue;
                }
                if (varargElemType.equals(argType)) continue;
                if (TypeChecker.isTypeCompatible(varargElemType, argType)) {
                    score += 5;
                    continue;
                }
                score += 100;
            }
        }
        if (!isVarArgs && argCount > paramCount) {
            score += (argCount - paramCount) * 100;
        }
        return score;
    }

    public static MethodInfo selectClosestArity(List<MethodInfo> overloads, int targetArity) {
        if (overloads == null || overloads.isEmpty()) {
            return null;
        }
        MethodInfo best = null;
        int bestDistance = Integer.MAX_VALUE;
        int bestParamCount = Integer.MAX_VALUE;
        for (MethodInfo method : overloads) {
            int paramCount = method.getParameterCount();
            int distance = Math.abs(paramCount - targetArity);
            if (distance >= bestDistance && (distance != bestDistance || paramCount >= bestParamCount)) continue;
            best = method;
            bestDistance = distance;
            bestParamCount = paramCount;
        }
        return best != null ? best : overloads.get(0);
    }
}

