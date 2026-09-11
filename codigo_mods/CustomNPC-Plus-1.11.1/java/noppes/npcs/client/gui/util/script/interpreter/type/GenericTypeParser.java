/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.List;

public class GenericTypeParser {
    public static ParsedType parse(String typeExpr) {
        if (typeExpr == null || typeExpr.isEmpty()) {
            return null;
        }
        String expr = typeExpr.trim();
        ParseResult result = GenericTypeParser.parseTypeWithGenerics(expr, 0);
        if (result == null || result.type == null) {
            return new ParsedType(expr, null, typeExpr);
        }
        return result.type;
    }

    private static ParseResult parseTypeWithGenerics(String expr, int startIndex) {
        char c;
        int i;
        int len = expr.length();
        for (i = startIndex; i < len && Character.isWhitespace(expr.charAt(i)); ++i) {
        }
        if (i >= len) {
            return null;
        }
        StringBuilder baseNameBuilder = new StringBuilder();
        while (i < len && (Character.isJavaIdentifierPart(c = expr.charAt(i)) || c == '.')) {
            baseNameBuilder.append(c);
            ++i;
        }
        String baseName = baseNameBuilder.toString().trim();
        if (baseName.isEmpty()) {
            return null;
        }
        while (baseName.endsWith(".")) {
            baseName = baseName.substring(0, baseName.length() - 1);
        }
        while (i < len && Character.isWhitespace(expr.charAt(i))) {
            ++i;
        }
        ArrayList<ParsedType> typeArgs = new ArrayList<ParsedType>();
        if (i < len && expr.charAt(i) == '<') {
            ++i;
            while (i < len) {
                while (i < len && Character.isWhitespace(expr.charAt(i))) {
                    ++i;
                }
                if (i >= len) break;
                char c2 = expr.charAt(i);
                if (c2 == '>') {
                    ++i;
                    break;
                }
                if (c2 == ',') {
                    ++i;
                    continue;
                }
                ParseResult argResult = GenericTypeParser.parseTypeArgument(expr, i);
                if (argResult != null && argResult.type != null) {
                    typeArgs.add(argResult.type);
                    i = argResult.endIndex;
                    continue;
                }
                i = GenericTypeParser.skipToNextTypeArgDelimiter(expr, i);
            }
        }
        ParsedType type = new ParsedType(baseName, typeArgs, expr.substring(startIndex, i));
        return new ParseResult(type, i);
    }

    private static int skipToNextTypeArgDelimiter(String expr, int startIndex) {
        int i = startIndex;
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (inString) {
                if (c == stringChar && (i == 0 || expr.charAt(i - 1) != '\\')) {
                    inString = false;
                }
                ++i;
                continue;
            }
            if (c == '\'' || c == '\"') {
                inString = true;
                stringChar = c;
                ++i;
                continue;
            }
            if (c == '<' || c == '(' || c == '[') {
                ++depth;
            } else if (c == '>' || c == ')' || c == ']') {
                if (depth > 0) {
                    --depth;
                } else if (c == '>') {
                    return i;
                }
            } else if ((c == ',' || c == '>') && depth == 0) {
                return i;
            }
            ++i;
        }
        return i;
    }

    private static ParseResult parseTypeArgument(String expr, int startIndex) {
        int i;
        int len = expr.length();
        for (i = startIndex; i < len && Character.isWhitespace(expr.charAt(i)); ++i) {
        }
        if (i >= len) {
            return null;
        }
        if (expr.charAt(i) == '?') {
            ParseResult boundResult;
            ++i;
            while (i < len && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
            if (i < len && expr.substring(i).startsWith("extends ")) {
                ParseResult boundResult2 = GenericTypeParser.parseTypeWithGenerics(expr, i += 8);
                if (boundResult2 != null) {
                    return boundResult2;
                }
            } else if (i < len && expr.substring(i).startsWith("super ") && (boundResult = GenericTypeParser.parseTypeWithGenerics(expr, i += 6)) != null) {
                return boundResult;
            }
            return new ParseResult(new ParsedType("Object", null, "?"), i);
        }
        ParseResult typeResult = GenericTypeParser.parseTypeWithGenerics(expr, i);
        if (typeResult == null) {
            return null;
        }
        ParsedType baseType = typeResult.type;
        for (i = typeResult.endIndex; i < len && Character.isWhitespace(expr.charAt(i)); ++i) {
        }
        while (i + 1 < len && expr.charAt(i) == '[' && expr.charAt(i + 1) == ']') {
            i += 2;
            while (i < len && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
        }
        if (i < len && expr.charAt(i) == '?') {
            ++i;
        }
        if (i != typeResult.endIndex) {
            return new ParseResult(new ParsedType(baseType.baseName, baseType.typeArgs, expr.substring(startIndex, i)), i);
        }
        return new ParseResult(baseType, i);
    }

    public static boolean hasGenerics(String typeExpr) {
        return typeExpr != null && typeExpr.contains("<") && typeExpr.contains(">");
    }

    public static String stripGenerics(String typeExpr) {
        if (typeExpr == null) {
            return null;
        }
        int idx = typeExpr.indexOf(60);
        if (idx > 0) {
            return typeExpr.substring(0, idx).trim();
        }
        return typeExpr;
    }

    private static class ParseResult {
        final ParsedType type;
        final int endIndex;

        ParseResult(ParsedType type, int endIndex) {
            this.type = type;
            this.endIndex = endIndex;
        }
    }

    public static class ParsedType {
        public final String baseName;
        public final List<ParsedType> typeArgs;
        public final String rawString;

        public ParsedType(String baseName, List<ParsedType> typeArgs, String rawString) {
            this.baseName = baseName;
            this.typeArgs = typeArgs != null ? typeArgs : new ArrayList();
            this.rawString = rawString;
        }

        public boolean hasTypeArgs() {
            return !this.typeArgs.isEmpty();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.baseName);
            if (!this.typeArgs.isEmpty()) {
                sb.append("<");
                for (int i = 0; i < this.typeArgs.size(); ++i) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.typeArgs.get(i).toString());
                }
                sb.append(">");
            }
            return sb.toString();
        }
    }
}

