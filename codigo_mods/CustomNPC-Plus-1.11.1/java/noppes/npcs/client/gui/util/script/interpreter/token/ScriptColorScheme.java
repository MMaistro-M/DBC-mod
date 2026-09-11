/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.gui.util.script.interpreter.token;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.LogWriter;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;

public final class ScriptColorScheme {
    static volatile StyleEntry[] styles = ScriptColorScheme.buildDefaults();
    private static volatile BackgroundStyleEntry background = BackgroundStyleEntry.DEFAULT;

    private ScriptColorScheme() {
    }

    public static BackgroundStyleEntry getBackgroundStyle() {
        return background;
    }

    public static void reloadColorScheme(IResourceManager resourceManager) {
        ResourceLocation loc = new ResourceLocation("customnpcs", "colorscheme/script_editor_scheme.json");
        try {
            List resources = resourceManager.func_135056_b(loc);
            IResource top = (IResource)resources.get(resources.size() - 1);
            try (InputStream is = top.func_110527_b();
                 InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);){
                ScriptColorScheme.loadFromJson(reader);
                LogWriter.info("[ColorScheme] Loaded script_editor_scheme.json successfully");
                ScriptColorScheme.logLoadedSample();
            }
        }
        catch (FileNotFoundException e) {
            LogWriter.info("[ColorScheme] No script_editor_scheme.json found, using defaults");
            ScriptColorScheme.resetToDefaults();
        }
        catch (Exception e) {
            LogWriter.error("[ColorScheme] Failed to load script_editor_scheme.json: " + e.getMessage());
            ScriptColorScheme.resetToDefaults();
        }
    }

    private static void logLoadedSample() {
        TokenType[] samples;
        StyleEntry[] current = styles;
        for (TokenType tt : samples = new TokenType[]{TokenType.COMMENT, TokenType.KEYWORD, TokenType.STRING, TokenType.METHOD_CALL, TokenType.DEFAULT}) {
            StyleEntry e = current[tt.ordinal()];
            LogWriter.info("[ColorScheme]   " + tt.name() + " -> color=#" + String.format("%08X", e.hexColor) + ", bold=" + e.bold + ", italic=" + e.italic);
        }
        BackgroundStyleEntry bg = background;
        LogWriter.info("[ColorScheme]   background -> bg=#" + String.format("%08X", bg.getBackgroundColor()) + ", gutter=#" + String.format("%08X", bg.getGutterColor()));
    }

    public static void loadFromJson(Reader reader) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(reader);
            if (!root.isJsonObject()) {
                LogWriter.error("[ColorScheme] script_editor_scheme.json root is not a JSON object, using defaults");
                return;
            }
            JsonObject obj = root.getAsJsonObject();
            background = ScriptColorScheme.parseBackground(obj);
            TokenType[] all = TokenType.values();
            StyleEntry[] next = new StyleEntry[all.length];
            for (TokenType tt : all) {
                next[tt.ordinal()] = ScriptColorScheme.parseEntry(obj, tt);
            }
            styles = next;
        }
        catch (Exception e) {
            LogWriter.error("[ColorScheme] Failed to parse script_editor_scheme.json, using defaults: " + e.getMessage());
        }
    }

    public static void resetToDefaults() {
        styles = ScriptColorScheme.buildDefaults();
        background = BackgroundStyleEntry.DEFAULT;
    }

    private static StyleEntry parseEntry(JsonObject root, TokenType tt) {
        if (!root.has(tt.name())) {
            return ScriptColorScheme.defaultEntry(tt);
        }
        try {
            JsonElement elem = root.get(tt.name());
            if (!elem.isJsonObject()) {
                LogWriter.error("[ColorScheme] Entry for " + tt.name() + " is not an object, skipping");
                return ScriptColorScheme.defaultEntry(tt);
            }
            JsonObject entry = elem.getAsJsonObject();
            int defaultColor = tt.getDefaultHexColor();
            if ((defaultColor & 0xFF000000) == 0) {
                defaultColor |= 0xFF000000;
            }
            int color = entry.has("color") ? ScriptColorScheme.parseColor(entry.get("color"), defaultColor, tt.name()) : defaultColor;
            boolean bold = entry.has("bold") ? entry.get("bold").getAsBoolean() : tt.getDefaultBold();
            boolean italic = entry.has("italic") ? entry.get("italic").getAsBoolean() : tt.getDefaultItalic();
            return new StyleEntry(color, bold, italic);
        }
        catch (Exception e) {
            LogWriter.error("[ColorScheme] Error parsing token " + tt.name() + ", using defaults: " + e.getMessage());
            return ScriptColorScheme.defaultEntry(tt);
        }
    }

    private static StyleEntry defaultEntry(TokenType tt) {
        int color = tt.getDefaultHexColor();
        if ((color & 0xFF000000) == 0) {
            color |= 0xFF000000;
        }
        return new StyleEntry(color, tt.getDefaultBold(), tt.getDefaultItalic());
    }

    private static BackgroundStyleEntry parseBackground(JsonObject root) {
        if (!root.has("background")) {
            return BackgroundStyleEntry.DEFAULT;
        }
        try {
            JsonElement elem = root.get("background");
            if (!elem.isJsonObject()) {
                LogWriter.error("[ColorScheme] 'background' is not an object, using defaults");
                return BackgroundStyleEntry.DEFAULT;
            }
            JsonObject bg = elem.getAsJsonObject();
            BackgroundStyleEntry d = BackgroundStyleEntry.DEFAULT;
            int backgroundColor = bg.has("backgroundColor") ? ScriptColorScheme.parseColor(bg.get("backgroundColor"), d.getBackgroundColor(), "background.backgroundColor") : d.getBackgroundColor();
            int gutterColor = bg.has("gutterColor") ? ScriptColorScheme.parseColor(bg.get("gutterColor"), d.getGutterColor(), "background.gutterColor") : d.getGutterColor();
            int gutterSeparatorColor = bg.has("gutterSeparatorColor") ? ScriptColorScheme.parseColor(bg.get("gutterSeparatorColor"), d.getGutterSeparatorColor(), "background.gutterSeparatorColor") : d.getGutterSeparatorColor();
            int lineNumberColor = bg.has("lineNumberColor") ? ScriptColorScheme.parseColor(bg.get("lineNumberColor"), d.getLineNumberColor(), "background.lineNumberColor") : d.getLineNumberColor();
            int lineNumberActiveColor = bg.has("lineNumberActiveColor") ? ScriptColorScheme.parseColor(bg.get("lineNumberActiveColor"), d.getLineNumberActiveColor(), "background.lineNumberActiveColor") : d.getLineNumberActiveColor();
            int scrollbarColor = bg.has("scrollbarColor") ? ScriptColorScheme.parseColor(bg.get("scrollbarColor"), d.getScrollbarColor(), "background.scrollbarColor") : d.getScrollbarColor();
            int borderColor = bg.has("borderColor") ? ScriptColorScheme.parseColor(bg.get("borderColor"), d.getBorderColor(), "background.borderColor") : d.getBorderColor();
            int caretColor = bg.has("caretColor") ? ScriptColorScheme.parseColor(bg.get("caretColor"), d.getCaretColor(), "background.caretColor") : d.getCaretColor();
            return new BackgroundStyleEntry(backgroundColor, gutterColor, gutterSeparatorColor, lineNumberColor, lineNumberActiveColor, scrollbarColor, borderColor, caretColor);
        }
        catch (Exception e) {
            LogWriter.error("[ColorScheme] Error parsing 'background', using defaults: " + e.getMessage());
            return BackgroundStyleEntry.DEFAULT;
        }
    }

    private static StyleEntry[] buildDefaults() {
        TokenType[] all = TokenType.values();
        StyleEntry[] defaults = new StyleEntry[all.length];
        for (TokenType tt : all) {
            defaults[tt.ordinal()] = ScriptColorScheme.defaultEntry(tt);
        }
        return defaults;
    }

    private static int parseColor(JsonElement element, int fallback, String tokenName) {
        try {
            if (element.isJsonPrimitive()) {
                if (element.getAsJsonPrimitive().isString()) {
                    String s = element.getAsString().trim();
                    if (s.startsWith("#")) {
                        s = s.substring(1);
                    }
                    int color = (int)Long.parseLong(s, 16);
                    if (s.length() <= 6) {
                        color |= 0xFF000000;
                    }
                    return color;
                }
                if (element.getAsJsonPrimitive().isNumber()) {
                    return element.getAsInt();
                }
            }
        }
        catch (NumberFormatException e) {
            LogWriter.error("[ColorScheme] Invalid color for " + tokenName + ": " + element + ", using default");
        }
        return fallback;
    }

    public static final class BackgroundStyleEntry {
        static final BackgroundStyleEntry DEFAULT = new BackgroundStyleEntry(-16777216, -16777216, -12828863, -10460314, -4601898, -2039584, -6250336, -1);
        private final int backgroundColor;
        private final int gutterColor;
        private final int gutterSeparatorColor;
        private final int lineNumberColor;
        private final int lineNumberActiveColor;
        private final int scrollbarColor;
        private final int borderColor;
        private final int caretColor;

        BackgroundStyleEntry(int backgroundColor, int gutterColor, int gutterSeparatorColor, int lineNumberColor, int lineNumberActiveColor, int scrollbarColor, int borderColor, int caretColor) {
            this.backgroundColor = backgroundColor;
            this.gutterColor = gutterColor;
            this.gutterSeparatorColor = gutterSeparatorColor;
            this.lineNumberColor = lineNumberColor;
            this.lineNumberActiveColor = lineNumberActiveColor;
            this.scrollbarColor = scrollbarColor;
            this.borderColor = borderColor;
            this.caretColor = caretColor;
        }

        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public int getGutterColor() {
            return this.gutterColor;
        }

        public int getGutterSeparatorColor() {
            return this.gutterSeparatorColor;
        }

        public int getLineNumberColor() {
            return this.lineNumberColor;
        }

        public int getLineNumberActiveColor() {
            return this.lineNumberActiveColor;
        }

        public int getScrollbarColor() {
            return this.scrollbarColor;
        }

        public int getBorderColor() {
            return this.borderColor;
        }

        public int getCaretColor() {
            return this.caretColor;
        }
    }

    static final class StyleEntry {
        final int hexColor;
        final boolean bold;
        final boolean italic;

        StyleEntry(int hexColor, boolean bold, boolean italic) {
            this.hexColor = hexColor;
            this.bold = bold;
            this.italic = italic;
        }
    }
}

