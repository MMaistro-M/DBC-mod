/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocReturnTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocSeeTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTypeTag;

public class DTSJSDocParser {
    private static final Pattern JSDOC_BLOCK_PATTERN = Pattern.compile("/\\*\\*\\s*(.*?)\\*/", 32);
    private static final Pattern TAG_PATTERN = Pattern.compile("@(\\w+)(?:\\s+\\{([^}]+)\\})?(?:\\s+(.*))?");
    private static final Pattern PARAM_PATTERN = Pattern.compile("@param(?:\\s+\\{([^}]+)\\})?(?:\\s+(\\w+))?(?:\\s+(.*))?");
    private static final Pattern RETURN_PATTERN = Pattern.compile("@returns?\\s+(?:\\{([^}]+)\\}\\s*)?(.*)");
    private static final Pattern TYPE_PATTERN = Pattern.compile("@type(?:\\s+\\{([^}]+)\\})?(?:\\s+(.*))?");

    public static JSDocInfo parseJSDocBlock(String jsDocContent) {
        if (jsDocContent == null || jsDocContent.isEmpty()) {
            return null;
        }
        JSDocInfo info = new JSDocInfo(jsDocContent, -1, -1);
        String[] lines = jsDocContent.split("\\r?\\n");
        StringBuilder descriptionBuilder = new StringBuilder();
        boolean foundFirstTag = false;
        JSDocTag lastTag = null;
        for (String line : lines) {
            if ((line = DTSJSDocParser.cleanLine(line)).startsWith("@")) {
                foundFirstTag = true;
                lastTag = DTSJSDocParser.parseTag(line, info);
                continue;
            }
            if (!foundFirstTag) {
                if (line.isEmpty()) {
                    if (descriptionBuilder.length() <= 0) continue;
                    descriptionBuilder.append("\n");
                    continue;
                }
                if (descriptionBuilder.length() > 0) {
                    descriptionBuilder.append("\n");
                }
                descriptionBuilder.append(line);
                continue;
            }
            if (lastTag == null || line.isEmpty()) continue;
            String existing = lastTag.getDescription();
            if (existing == null || existing.isEmpty()) {
                lastTag.setDescription(line);
                continue;
            }
            lastTag.setDescription(existing + "\n" + line);
        }
        if (descriptionBuilder.length() > 0) {
            info.setDescription(descriptionBuilder.toString().trim());
        }
        return info;
    }

    private static String cleanLine(String line) {
        int end;
        if ((line = line.trim()).startsWith("/**")) {
            line = line.substring(3).trim();
        }
        if (line.endsWith("*/")) {
            line = line.substring(0, line.length() - 2);
            for (end = line.length(); end > 0 && Character.isWhitespace(line.charAt(end - 1)); --end) {
            }
            line = line.substring(0, end);
        }
        if (line.startsWith("*")) {
            if (!(line = line.substring(1)).isEmpty() && line.charAt(0) == ' ') {
                line = line.substring(1);
            }
            for (end = line.length(); end > 0 && Character.isWhitespace(line.charAt(end - 1)); --end) {
            }
            line = line.substring(0, end);
        }
        return line;
    }

    private static JSDocTag parseTag(String line, JSDocInfo info) {
        Matcher paramMatcher = PARAM_PATTERN.matcher(line);
        if (paramMatcher.find()) {
            String type = paramMatcher.group(1);
            String name = paramMatcher.group(2);
            String desc = paramMatcher.group(3);
            if (desc == null && type == null && name != null) {
                desc = name;
                name = null;
            }
            if (desc != null && (desc = desc.trim()).isEmpty()) {
                desc = null;
            }
            JSDocParamTag tag = JSDocParamTag.create(-1, -1, -1, type, null, -1, -1, name, -1, -1, desc);
            info.addParamTag(tag);
            return tag;
        }
        Matcher returnMatcher = RETURN_PATTERN.matcher(line);
        if (returnMatcher.find()) {
            String type = returnMatcher.group(1);
            String desc = returnMatcher.group(2);
            if (desc != null && (desc = desc.trim()).isEmpty()) {
                desc = null;
            }
            JSDocReturnTag tag = JSDocReturnTag.create("return", -1, -1, -1, type, null, -1, -1, desc);
            info.setReturnTag(tag);
            return tag;
        }
        Matcher typeMatcher = TYPE_PATTERN.matcher(line);
        if (typeMatcher.find()) {
            String type = typeMatcher.group(1);
            String desc = typeMatcher.group(2);
            if (desc != null && (desc = desc.trim()).isEmpty()) {
                desc = null;
            }
            JSDocTypeTag tag = JSDocTypeTag.create(-1, -1, -1, type, null, -1, -1, desc);
            info.setTypeTag(tag);
            return tag;
        }
        Matcher tagMatcher = TAG_PATTERN.matcher(line);
        if (tagMatcher.find()) {
            String tagName = tagMatcher.group(1);
            String typeName = tagMatcher.group(2);
            String rest = tagMatcher.group(3);
            switch (tagName) {
                case "see": {
                    JSDocSeeTag seeTag = JSDocSeeTag.createSimple(rest != null ? rest.trim() : "");
                    info.addSeeTag(seeTag);
                    return seeTag;
                }
            }
            JSDocTag genericTag = new JSDocTag(tagName, -1, -1, -1);
            if (typeName != null) {
                typeName = typeName.trim();
                genericTag.setType(typeName, null, -1, -1);
            }
            genericTag.setDescription(rest != null ? rest.trim() : "");
            info.addTag(genericTag);
            return genericTag;
        }
        return null;
    }

    public static String extractJSDocBefore(String content, int elementStart) {
        if (elementStart <= 0) {
            return null;
        }
        String searchArea = content.substring(0, elementStart);
        int lastJSDocEnd = searchArea.lastIndexOf("*/");
        if (lastJSDocEnd < 0) {
            return null;
        }
        int jsDocStart = searchArea.lastIndexOf("/**", lastJSDocEnd);
        if (jsDocStart < 0) {
            return null;
        }
        String between = searchArea.substring(lastJSDocEnd + 2).trim();
        if (between.isEmpty() || DTSJSDocParser.isOnlyWhitespaceOrModifiers(between)) {
            return searchArea.substring(jsDocStart, lastJSDocEnd + 2);
        }
        return null;
    }

    private static boolean isOnlyWhitespaceOrModifiers(String text) {
        String cleaned = text.replaceAll("\\s+", " ").trim();
        return cleaned.isEmpty() || cleaned.matches("^(export\\s*)?(readonly\\s*)?(interface|class|type|function)?\\s*$");
    }

    public static List<JSDocBlock> extractAllJSDocBlocks(String content) {
        ArrayList<JSDocBlock> blocks = new ArrayList<JSDocBlock>();
        Matcher matcher = JSDOC_BLOCK_PATTERN.matcher(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String docContent = matcher.group(0);
            blocks.add(new JSDocBlock(start, end, docContent));
        }
        return blocks;
    }

    public static class JSDocBlock {
        public final int start;
        public final int end;
        public final String content;

        public JSDocBlock(int start, int end, String content) {
            this.start = start;
            this.end = end;
            this.content = content;
        }

        public JSDocInfo parse() {
            return DTSJSDocParser.parseJSDocBlock(this.content);
        }
    }
}

