/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericTypeParser;

public final class TypeStringNormalizer {
    private TypeStringNormalizer() {
    }

    public static String stripImportTypeSyntax(String type) {
        int importIdx;
        if (type == null) {
            return null;
        }
        String out = type.trim();
        while ((importIdx = out.indexOf("import(")) >= 0) {
            int i;
            int depth = 1;
            boolean inString = false;
            char stringChar = '\u0000';
            for (i = importIdx + "import(".length(); i < out.length() && depth > 0; ++i) {
                char c = out.charAt(i);
                if (inString) {
                    if (c != stringChar || i != 0 && out.charAt(i - 1) == '\\') continue;
                    inString = false;
                    continue;
                }
                if (c == '\'' || c == '\"') {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c == '(') {
                    ++depth;
                    continue;
                }
                if (c != ')') continue;
                --depth;
            }
            if (depth != 0) break;
            if (i < out.length() && out.charAt(i) == '.') {
                int removeEnd = i + 1;
                out = out.substring(0, importIdx) + out.substring(removeEnd);
                continue;
            }
            out = out.substring(0, importIdx) + out.substring(i);
        }
        return out;
    }

    public static String stripNullableSuffix(String type) {
        if (type == null) {
            return null;
        }
        String out = type.trim();
        if (out.endsWith("?")) {
            return out.substring(0, out.length() - 1).trim();
        }
        return out;
    }

    public static ArraySplit splitArraySuffixes(String type) {
        if (type == null) {
            return new ArraySplit(null, 0);
        }
        String out = type.trim();
        int dims = 0;
        while (out.endsWith("[]")) {
            ++dims;
            out = out.substring(0, out.length() - 2).trim();
        }
        return new ArraySplit(out, dims);
    }

    public static List<String> splitTopLevelUnion(String type) {
        ArrayList<String> parts = new ArrayList<String>();
        if (type == null) {
            return parts;
        }
        String expr = type.trim();
        if (expr.isEmpty()) {
            parts.add("");
            return parts;
        }
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        int start = 0;
        for (int i = 0; i < expr.length(); ++i) {
            char c = expr.charAt(i);
            if (inString) {
                if (c != stringChar || i != 0 && expr.charAt(i - 1) == '\\') continue;
                inString = false;
                continue;
            }
            if (c == '\'' || c == '\"' || c == '`') {
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '<' || c == '(' || c == '[' || c == '{') {
                ++depth;
                continue;
            }
            if (c == '>' || c == ')' || c == ']' || c == '}') {
                if (depth <= 0) continue;
                --depth;
                continue;
            }
            if (c != '|' || depth != 0) continue;
            String part = expr.substring(start, i).trim();
            if (!part.isEmpty()) {
                parts.add(part);
            }
            start = i + 1;
        }
        String tail = expr.substring(start).trim();
        if (!tail.isEmpty()) {
            parts.add(tail);
        }
        if (parts.isEmpty()) {
            parts.add(expr);
        }
        return parts;
    }

    public static String pickPreferredUnionBranch(String type) {
        if (type == null) {
            return null;
        }
        List<String> parts = TypeStringNormalizer.splitTopLevelUnion(type);
        if (parts.size() <= 1) {
            return type.trim();
        }
        for (String part : parts) {
            if (TypeStringNormalizer.isNullishBranch(part)) continue;
            return part.trim();
        }
        return parts.get(0).trim();
    }

    private static boolean isNullishBranch(String part) {
        if (part == null) {
            return false;
        }
        String s = part.trim();
        if (s.isEmpty()) {
            return false;
        }
        s = GenericTypeParser.stripGenerics(s);
        return "null".equals(s = s.trim().toLowerCase()) || "undefined".equals(s) || "void".equals(s);
    }

    public static final class ArraySplit {
        public final String base;
        public final int dimensions;

        public ArraySplit(String base, int dimensions) {
            this.base = base;
            this.dimensions = dimensions;
        }
    }
}

