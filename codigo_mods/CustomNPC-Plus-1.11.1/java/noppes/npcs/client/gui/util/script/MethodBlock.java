/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodBlock {
    public static final Pattern METHOD_PATTERN = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_<>\\[\\]]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
    public static final Pattern LOCAL_VAR_DECL = Pattern.compile("\\b([A-Z][a-zA-Z0-9_<>\\[\\]]*|[a-z][a-zA-Z0-9_]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*(=|;)");
    public static final Pattern PARAMETER_PATTERN = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_<>\\[\\]]*(?:\\.{3})?)\\s+([a-zA-Z_][a-zA-Z0-9_]*)");
    public int startOffset;
    public int endOffset;
    public String text;
    public List<String> localVariables = new ArrayList<String>();
    public List<String> parameters = new ArrayList<String>();
    public Map<String, Integer> localVarPositions = new HashMap<String, Integer>();

    public MethodBlock(int start, int end, String text) {
        this.startOffset = start;
        this.endOffset = end;
        this.text = text;
        this.extractParameters();
        this.extractLocalVariables();
    }

    private void extractParameters() {
        this.parameters.clear();
        int parenStart = this.text.indexOf(40);
        int parenEnd = this.text.indexOf(41);
        if (parenStart < 0 || parenEnd <= parenStart) {
            return;
        }
        String paramList = this.text.substring(parenStart + 1, parenEnd);
        if (paramList.trim().isEmpty()) {
            return;
        }
        Matcher m = PARAMETER_PATTERN.matcher(paramList);
        while (m.find()) {
            String paramName = m.group(2);
            if (this.parameters.contains(paramName)) continue;
            this.parameters.add(paramName);
        }
    }

    private void extractLocalVariables() {
        this.localVariables.clear();
        this.localVarPositions.clear();
        List<int[]> excludedRanges = MethodBlock.getExcludedRanges(this.text);
        Matcher m = LOCAL_VAR_DECL.matcher(this.text);
        while (m.find()) {
            int checkStart;
            String before;
            String varName = m.group(2);
            int declPosition = m.start();
            if (MethodBlock.isInExcludedRange(declPosition, excludedRanges)) continue;
            boolean isThisFieldAssignment = false;
            if (declPosition >= 5 && (before = this.text.substring(checkStart = Math.max(0, declPosition - 10), declPosition)).matches(".*\\bthis\\s*\\.\\s*$")) {
                isThisFieldAssignment = true;
            }
            if (isThisFieldAssignment || this.localVariables.contains(varName)) continue;
            this.localVariables.add(varName);
            this.localVarPositions.put(varName, this.startOffset + declPosition);
        }
    }

    public boolean isLocalDeclaredAtPosition(String varName, int absolutePosition) {
        if (!this.localVariables.contains(varName)) {
            return false;
        }
        Integer declPos = this.localVarPositions.get(varName);
        if (declPos == null) {
            return false;
        }
        return absolutePosition >= declPos;
    }

    public boolean containsPosition(int position) {
        return position >= this.startOffset && position < this.endOffset;
    }

    public String toString() {
        return this.text;
    }

    public static List<MethodBlock> collectMethodBlocks(String text) {
        ArrayList<MethodBlock> methods = new ArrayList<MethodBlock>();
        Pattern methodPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_<>\\[\\]]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\([^)]*\\)\\s*\\{", 8);
        Matcher m = methodPattern.matcher(text);
        while (m.find()) {
            int braceEnd;
            int start = m.start();
            int braceStart = text.indexOf("{", m.end() - 1);
            if (braceStart == -1 || (braceEnd = MethodBlock.findMatchingBrace(text, braceStart)) == -1) continue;
            String methodText = text.substring(start, braceEnd + 1);
            methods.add(new MethodBlock(start, braceEnd + 1, methodText));
        }
        return methods;
    }

    public static List<int[]> getExcludedRanges(String text) {
        ArrayList<int[]> ranges = new ArrayList<int[]>();
        Pattern stringPattern = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
        Matcher stringMatcher = stringPattern.matcher(text);
        while (stringMatcher.find()) {
            ranges.add(new int[]{stringMatcher.start(), stringMatcher.end()});
        }
        Pattern commentPattern = Pattern.compile("/\\*[\\s\\S]*?(?:\\*/|$)|//.*");
        Matcher commentMatcher = commentPattern.matcher(text);
        while (commentMatcher.find()) {
            ranges.add(new int[]{commentMatcher.start(), commentMatcher.end()});
        }
        if (ranges.isEmpty()) {
            return ranges;
        }
        ranges.sort((a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> merged = new ArrayList<int[]>();
        int[] current = (int[])ranges.get(0);
        for (int i = 1; i < ranges.size(); ++i) {
            int[] next = (int[])ranges.get(i);
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
                continue;
            }
            merged.add(current);
            current = next;
        }
        merged.add(current);
        return merged;
    }

    private static boolean isInExcludedRange(int pos, List<int[]> ranges) {
        for (int[] range : ranges) {
            if (pos < range[0] || pos >= range[1]) continue;
            return true;
        }
        return false;
    }

    public static int findMatchingBrace(String text, int openBraceIndex) {
        List<int[]> excludedRanges = MethodBlock.getExcludedRanges(text);
        int depth = 0;
        for (int i = openBraceIndex; i < text.length(); ++i) {
            if (MethodBlock.isInExcludedRange(i, excludedRanges)) continue;
            char c = text.charAt(i);
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return i;
        }
        return -1;
    }
}

