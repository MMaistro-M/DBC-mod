/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.expression;

import java.util.List;
import java.util.function.Function;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

@Deprecated
public class CastExpressionResolver {
    public static <T> TypeInfo resolveCastOrParenthesizedExpression(String expr, int position, Function<String, TypeInfo> resolveType, TypeResolver resolveExpression, ChainParser<T> parseChain, ChainSegmentResolver<T> resolveChainSegment) {
        TypeInfo castType;
        if (!expr.startsWith("(")) {
            return null;
        }
        int depth = 0;
        int closeParen = -1;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < expr.length(); ++i) {
            char c = expr.charAt(i);
            if (!(c != '\"' && c != '\'' || i != 0 && expr.charAt(i - 1) == '\\')) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (inString) continue;
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            closeParen = i;
            break;
        }
        if (closeParen < 0) {
            return null;
        }
        String insideParens = expr.substring(1, closeParen).trim();
        String afterParens = expr.substring(closeParen + 1).trim();
        if (CastExpressionResolver.looksLikeCastType(insideParens) && CastExpressionResolver.looksLikeExpressionStart(afterParens) && (castType = resolveType.apply(insideParens)) != null && castType.isResolved()) {
            if (!afterParens.isEmpty() && afterParens.startsWith(".")) {
                String chainExpr = afterParens.substring(1).trim();
                List<T> segments = parseChain.parse(chainExpr);
                TypeInfo currentType = castType;
                for (T segment : segments) {
                    if ((currentType = resolveChainSegment.resolve(currentType, segment)) != null) continue;
                    return null;
                }
                return currentType;
            }
            return castType;
        }
        TypeInfo innerType = resolveExpression.resolve(insideParens, position);
        if (innerType != null && !afterParens.isEmpty() && afterParens.startsWith(".")) {
            String chainExpr = afterParens.substring(1).trim();
            List<T> segments = parseChain.parse(chainExpr);
            TypeInfo currentType = innerType;
            for (T segment : segments) {
                if ((currentType = resolveChainSegment.resolve(currentType, segment)) != null) continue;
                return null;
            }
            return currentType;
        }
        return innerType;
    }

    public static boolean looksLikeCastType(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        while (s.endsWith("[]")) {
            s = s.substring(0, s.length() - 2).trim();
        }
        if (s.isEmpty()) {
            return false;
        }
        switch (s) {
            case "byte": 
            case "short": 
            case "int": 
            case "long": 
            case "float": 
            case "double": 
            case "char": 
            case "boolean": {
                return true;
            }
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return true;
        }
        if (s.contains(".")) {
            String[] parts = s.split("\\.");
            String lastPart = parts[parts.length - 1];
            return !lastPart.isEmpty() && Character.isUpperCase(lastPart.charAt(0));
        }
        return false;
    }

    public static boolean looksLikeExpressionStart(String s) {
        char second;
        if (s == null || s.isEmpty()) {
            return false;
        }
        if ((s = s.trim()).isEmpty()) {
            return false;
        }
        char first = s.charAt(0);
        if (first == '(') {
            return true;
        }
        if (Character.isJavaIdentifierStart(first)) {
            return true;
        }
        if (first == '\"' || first == '\'') {
            return true;
        }
        if (Character.isDigit(first)) {
            return true;
        }
        if (first == '.' && s.length() > 1 && Character.isDigit(s.charAt(1))) {
            return true;
        }
        if (first == '!' || first == '~') {
            return true;
        }
        return !(first != '+' && first != '-' || s.length() <= 1 || (second = s.charAt(1)) != first && !Character.isDigit(second) && !Character.isJavaIdentifierStart(second) && second != '(');
    }

    @FunctionalInterface
    public static interface ChainSegmentResolver<T> {
        public TypeInfo resolve(TypeInfo var1, T var2);
    }

    @FunctionalInterface
    public static interface ChainParser<T> {
        public List<T> parse(String var1);
    }

    @FunctionalInterface
    public static interface TypeResolver {
        public TypeInfo resolve(String var1, int var2);
    }
}

