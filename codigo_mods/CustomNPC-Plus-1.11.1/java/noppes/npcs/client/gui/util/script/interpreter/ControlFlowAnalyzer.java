/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import noppes.npcs.client.gui.util.script.interpreter.CodeParser;

public final class ControlFlowAnalyzer {
    private ControlFlowAnalyzer() {
    }

    public static boolean hasGuaranteedReturn(String body) {
        String cleanBody = CodeParser.removeStringsAndComments(body);
        return ControlFlowAnalyzer.hasTopLevelReturn(cleanBody) || ControlFlowAnalyzer.hasCompleteIfElseReturn(cleanBody) || ControlFlowAnalyzer.hasTryFinallyReturn(cleanBody) || ControlFlowAnalyzer.hasCompleteSwitchReturn(cleanBody);
    }

    private static boolean hasTopLevelReturn(String body) {
        int braceDepth = 0;
        int parenDepth = 0;
        for (int i = 0; i < body.length(); ++i) {
            char c = body.charAt(i);
            if (c == '{') {
                ++braceDepth;
            } else if (c == '}') {
                --braceDepth;
            } else if (c == '(') {
                ++parenDepth;
            } else if (c == ')') {
                --parenDepth;
            }
            if (braceDepth != 0 || parenDepth != 0 || !ControlFlowAnalyzer.isKeywordAt(body, i, "return")) continue;
            return true;
        }
        return false;
    }

    private static boolean hasCompleteIfElseReturn(String body) {
        int braceDepth = 0;
        for (int i = 0; i < body.length(); ++i) {
            char c = body.charAt(i);
            if (c == '{') {
                ++braceDepth;
                continue;
            }
            if (c == '}') {
                --braceDepth;
                continue;
            }
            if (braceDepth != 0 || !ControlFlowAnalyzer.isKeywordAt(body, i, "if") || !ControlFlowAnalyzer.checkIfElseChainReturns(body, i)) continue;
            return true;
        }
        return false;
    }

    private static boolean checkIfElseChainReturns(String body, int ifStart) {
        for (int pos = ifStart; pos < body.length(); pos += 4) {
            if (!body.substring(pos).startsWith("if")) {
                return false;
            }
            pos += 2;
            if ((pos = ControlFlowAnalyzer.skipWhitespace(body, pos)) >= body.length() || body.charAt(pos) != '(') {
                return false;
            }
            int condStart = pos;
            int condEnd = CodeParser.findMatchingParen(body, pos);
            if (condEnd < 0) {
                return false;
            }
            String condition = body.substring(condStart + 1, condEnd).trim();
            boolean isAlwaysTrue = condition.equals("true");
            boolean isAlwaysFalse = condition.equals("false");
            pos = ControlFlowAnalyzer.skipWhitespace(body, condEnd + 1);
            if (pos >= body.length() || body.charAt(pos) != '{') {
                return false;
            }
            int blockEnd = CodeParser.findMatchingBrace(body, pos);
            if (blockEnd < 0) {
                return false;
            }
            String ifBlock = body.substring(pos + 1, blockEnd);
            boolean ifBlockReturns = ControlFlowAnalyzer.hasGuaranteedReturn(ifBlock);
            if (isAlwaysTrue && ifBlockReturns) {
                return true;
            }
            pos = ControlFlowAnalyzer.skipWhitespace(body, blockEnd + 1);
            if (body.substring(pos).startsWith("else")) continue;
            return false;
        }
        return false;
    }

    private static boolean hasTryFinallyReturn(String body) {
        for (int i = 0; i < body.length(); ++i) {
            String finallyBlock;
            int finallyBlockEnd;
            int finallyBlockStart;
            int tryBlockEnd;
            int tryBlockStart;
            if (!ControlFlowAnalyzer.isKeywordAt(body, i, "try") || (tryBlockStart = body.indexOf(123, i)) < 0 || (tryBlockEnd = CodeParser.findMatchingBrace(body, tryBlockStart)) < 0) continue;
            int searchPos = ControlFlowAnalyzer.skipCatchBlocks(body, tryBlockEnd + 1);
            if (!body.substring(searchPos = ControlFlowAnalyzer.skipWhitespace(body, searchPos)).startsWith("finally") || (finallyBlockStart = body.indexOf(123, searchPos)) < 0 || (finallyBlockEnd = CodeParser.findMatchingBrace(body, finallyBlockStart)) < 0 || !ControlFlowAnalyzer.hasGuaranteedReturn(finallyBlock = body.substring(finallyBlockStart + 1, finallyBlockEnd))) continue;
            return true;
        }
        return false;
    }

    private static int skipCatchBlocks(String body, int start) {
        int catchBlockEnd;
        int catchBlockStart;
        int pos = start;
        while ((pos = ControlFlowAnalyzer.skipWhitespace(body, pos)) < body.length() && body.substring(pos).startsWith("catch") && (catchBlockStart = body.indexOf(123, pos)) >= 0 && (catchBlockEnd = CodeParser.findMatchingBrace(body, catchBlockStart)) >= 0) {
            pos = catchBlockEnd + 1;
        }
        return pos;
    }

    private static boolean hasCompleteSwitchReturn(String body) {
        return false;
    }

    private static boolean isKeywordAt(String text, int pos, String keyword) {
        if (pos + keyword.length() > text.length()) {
            return false;
        }
        if (!text.substring(pos).startsWith(keyword)) {
            return false;
        }
        boolean validBefore = pos == 0 || !Character.isLetterOrDigit(text.charAt(pos - 1));
        boolean validAfter = pos + keyword.length() >= text.length() || !Character.isLetterOrDigit(text.charAt(pos + keyword.length()));
        return validBefore && validAfter;
    }

    private static int skipWhitespace(String text, int pos) {
        while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
            ++pos;
        }
        return pos;
    }
}

