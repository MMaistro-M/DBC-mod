/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;

public final class MultiDeclaratorParser {
    private MultiDeclaratorParser() {
    }

    public static void scanJSContinuationDeclarators(ScriptDocument doc, String source, int scanStart, int absBase, JSContinuationCallback callback) {
        int pos = scanStart;
        while (pos < source.length()) {
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            if (pos >= source.length() || source.charAt(pos) != ',') break;
            ++pos;
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            if (pos >= source.length()) break;
            int nameStart = pos;
            while (pos < source.length() && Character.isJavaIdentifierPart(source.charAt(pos))) {
                ++pos;
            }
            if (pos == nameStart) break;
            String varName = source.substring(nameStart, pos);
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            String initializer = null;
            int initStart = -1;
            int initEnd = -1;
            if (pos < source.length() && source.charAt(pos) == '=') {
                int rhsStart;
                int rhsEnd;
                if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') break;
                if ((rhsEnd = doc.findJsInitializerEnd(source, rhsStart = doc.skipSegmentWhitespace(source, ++pos), true)) > rhsStart && (initializer = source.substring(rhsStart, rhsEnd).trim()).isEmpty()) {
                    initializer = null;
                }
                initStart = rhsStart;
                initEnd = rhsEnd;
                pos = rhsEnd;
            }
            int absNamePos = absBase + nameStart;
            int absInitStart = initStart >= 0 ? absBase + initStart : -1;
            int absInitEnd = initEnd >= 0 ? absBase + initEnd : -1;
            callback.accept(varName, absNamePos, initializer, absInitStart, absInitEnd);
        }
    }

    public static void scanJavaContinuationDeclarators(String source, int scanStart, int absBase, JavaContinuationCallback callback) {
        int pos = scanStart;
        while (pos < source.length()) {
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            if (pos >= source.length() || source.charAt(pos) != ',') break;
            ++pos;
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            if (pos >= source.length()) break;
            int nameStart = pos;
            while (pos < source.length() && Character.isJavaIdentifierPart(source.charAt(pos))) {
                ++pos;
            }
            if (pos == nameStart) break;
            String varName = source.substring(nameStart, pos);
            while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
                ++pos;
            }
            int initStart = -1;
            int initEnd = -1;
            if (pos < source.length() && source.charAt(pos) == '=') {
                int scanPos;
                if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') break;
                initStart = pos++;
                for (int rhsStart = pos; rhsStart < source.length() && Character.isWhitespace(source.charAt(rhsStart)); ++rhsStart) {
                }
                int depth = 0;
                int angleDepth = 0;
                for (scanPos = rhsStart; scanPos < source.length(); ++scanPos) {
                    char c = source.charAt(scanPos);
                    if (c == '(' || c == '[' || c == '{') {
                        ++depth;
                        continue;
                    }
                    if (c == ')' || c == ']' || c == '}') {
                        --depth;
                        continue;
                    }
                    if (c == '<') {
                        ++angleDepth;
                        continue;
                    }
                    if (c == '>') {
                        --angleDepth;
                        continue;
                    }
                    if (c != ';' && c != ',' || depth != 0 || angleDepth != 0) continue;
                    initEnd = scanPos;
                    break;
                }
                pos = initEnd >= 0 ? initEnd : scanPos;
            } else if (pos >= source.length() || source.charAt(pos) != ';' && source.charAt(pos) != ',') break;
            int absNamePos = absBase + nameStart;
            int absInitStartAbs = initStart >= 0 ? absBase + initStart : -1;
            int absInitEndAbs = initEnd >= 0 ? absBase + initEnd : -1;
            callback.accept(varName, absNamePos, absInitStartAbs, absInitEndAbs);
        }
    }

    public static int jsDeclaratorScanStart(String bodyText, int matchEnd, boolean hasEquals, int initEnd) {
        if (hasEquals && initEnd >= 0) {
            return initEnd;
        }
        return matchEnd;
    }

    public static int javaDeclaratorScanStart(String source, String delimiter, int delimEnd, int initEnd) {
        if (";".equals(delimiter)) {
            return -1;
        }
        if (initEnd >= 0) {
            return initEnd;
        }
        if (",".equals(delimiter)) {
            return delimEnd - 1;
        }
        return delimEnd;
    }

    public static int findFirstDepthZeroComma(String s) {
        int depth = 0;
        int angleDepth = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                ++depth;
                continue;
            }
            if (c == ')' || c == ']' || c == '}') {
                --depth;
                continue;
            }
            if (c == '<') {
                ++angleDepth;
                continue;
            }
            if (c == '>') {
                --angleDepth;
                continue;
            }
            if (c != ',' || depth != 0 || angleDepth != 0) continue;
            return i;
        }
        return -1;
    }

    public static String extractRealTypeFromGreedyMatch(String typeGroup, int commaIndex) {
        int lastTab;
        String beforeComma = typeGroup.substring(0, commaIndex).trim();
        int lastSpace = beforeComma.lastIndexOf(32);
        int lastWS = Math.max(lastSpace, lastTab = beforeComma.lastIndexOf(9));
        if (lastWS < 0) {
            return beforeComma;
        }
        return beforeComma.substring(0, lastWS).trim();
    }

    public static String extractFirstVarFromGreedyMatch(String typeGroup, int commaIndex) {
        int lastTab;
        String beforeComma = typeGroup.substring(0, commaIndex).trim();
        int lastSpace = beforeComma.lastIndexOf(32);
        int lastWS = Math.max(lastSpace, lastTab = beforeComma.lastIndexOf(9));
        if (lastWS < 0) {
            return beforeComma;
        }
        return beforeComma.substring(lastWS + 1).trim();
    }

    public static int findFirstVarPosition(String source, int typeGroupStart, String typeGroup, int commaIndex) {
        String firstVar = MultiDeclaratorParser.extractFirstVarFromGreedyMatch(typeGroup, commaIndex);
        String beforeComma = typeGroup.substring(0, commaIndex).trim();
        int varStartInGroup = beforeComma.lastIndexOf(firstVar);
        return typeGroupStart + varStartInGroup;
    }

    public static boolean startsWithJSKeyword(String source, int stmtStart, int equalsPos) {
        int i;
        for (i = stmtStart; i < equalsPos && Character.isWhitespace(source.charAt(i)); ++i) {
        }
        String prefix = source.substring(i, Math.min(i + 6, equalsPos)).trim();
        return prefix.startsWith("var ") || prefix.startsWith("var\t") || prefix.startsWith("let ") || prefix.startsWith("let\t") || prefix.startsWith("const ") || prefix.startsWith("const\t");
    }

    @FunctionalInterface
    public static interface JavaContinuationCallback {
        public void accept(String var1, int var2, int var3, int var4);
    }

    @FunctionalInterface
    public static interface JSContinuationCallback {
        public void accept(String var1, int var2, String var3, int var4, int var5);
    }
}

