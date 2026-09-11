/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.config;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.Point2D;
import java.lang.ref.WeakReference;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.LogWriter;
import noppes.npcs.config.GlyphCache;
import org.lwjgl.opengl.GL11;

public class StringCache {
    private static final int BASELINE_OFFSET = 7;
    private static final int UNDERLINE_OFFSET = 1;
    private static final int UNDERLINE_THICKNESS = 2;
    private static final int STRIKETHROUGH_OFFSET = -6;
    private static final int STRIKETHROUGH_THICKNESS = 2;
    private static final float BASE_RENDER_SCALE = 0.5f;
    private static final boolean FONT_DEBUG_LOG = Boolean.getBoolean("cnpc.font.debug");
    private static final boolean FONT_DEBUG_DISABLE_INK_OFFSET = Boolean.getBoolean("cnpc.font.debug.disableInkOffset");
    private int baselineOffset = 7;
    private int underlineOffset = 1;
    private int strikethroughOffset = -6;
    private GlyphCache glyphCache;
    private int[] colorTable;
    private WeakHashMap<Key, Entry> stringCache = new WeakHashMap();
    private WeakHashMap<String, Key> weakRefCache = new WeakHashMap();
    private Key lookupKey = new Key();
    private Glyph[][] digitGlyphs = new Glyph[4][];
    private boolean digitGlyphsReady = false;
    private boolean antiAliasEnabled = false;
    private Thread mainThread = Thread.currentThread();
    public int fontHeight;

    private static boolean isLineBreak(char c) {
        return c == '\n' || c == '\r';
    }

    private static boolean isSpacingDebugGlyph(char c) {
        return c == '(' || c == '\"' || c == 't' || c == '.';
    }

    private static String debugGlyphLabel(char c) {
        return c == '\"' ? "\\\"" : String.valueOf(c);
    }

    private float renderScale() {
        return 0.5f / (float)this.glyphCache.getGuiScaleFactor();
    }

    private float pixelSnap(float coord) {
        float scale = this.renderScale();
        float invScale = 1.0f / scale;
        return (float)Math.floor(coord * invScale + 0.5f) * scale;
    }

    public StringCache() {
        this.glyphCache = new GlyphCache();
        this.colorTable = new int[32];
        for (int i = 0; i < 32; ++i) {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i >> 0 & 1) * 170 + j;
            if (i == 6) {
                k += 85;
            }
            if (Minecraft.func_71410_x().field_71474_y.field_74337_g) {
                int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
                int k1 = (k * 30 + l * 70) / 100;
                int l1 = (k * 30 + i1 * 70) / 100;
                k = j1;
                l = k1;
                i1 = l1;
            }
            if (i >= 16) {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }
            this.colorTable[i] = (k & 0xFF) << 16 | (l & 0xFF) << 8 | i1 & 0xFF;
        }
        this.cacheDightGlyphs();
    }

    public void setDefaultFont(String fontName, int fontSize, boolean antiAlias) {
        this.glyphCache.setDefaultFont(fontName, fontSize, antiAlias);
        this.antiAliasEnabled = antiAlias;
        this.weakRefCache.clear();
        this.stringCache.clear();
        this.cacheDightGlyphs();
        this.updateHeight();
    }

    public Font usedFont() {
        return this.glyphCache.usedFonts.get(0);
    }

    public void setCustomFont(ResourceLocation resource, int fontSize, boolean antiAlias) throws Exception {
        this.glyphCache.setCustomFont(resource, fontSize, antiAlias);
        this.antiAliasEnabled = antiAlias;
        this.weakRefCache.clear();
        this.stringCache.clear();
        this.cacheDightGlyphs();
        this.updateHeight();
    }

    public void updateHeight() {
        int height = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Glyph g : this.cacheString((String)"AaBbCcDdEeHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz").glyphs) {
            if (g.texture.height > height) {
                height = g.texture.height;
            }
            if (g.y < minY) {
                minY = g.y;
            }
            if (g.y <= maxY) continue;
            maxY = g.y;
        }
        int scaleFactor = this.glyphCache.getGuiScaleFactor();
        this.fontHeight = (int)((float)(maxY + height - minY) / 2.0f / (float)scaleFactor);
        this.updateFontMetrics();
    }

    private void updateFontMetrics() {
        LineMetrics lm = this.glyphCache.getLineMetrics("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        float ascent = lm.getAscent();
        float descent = lm.getDescent();
        this.baselineOffset = 7;
        this.underlineOffset = (int)(descent * 0.25f + 0.5f);
        this.strikethroughOffset = -((int)(ascent * 0.35f + 0.5f));
    }

    private void checkForScaleChange() {
        if (this.glyphCache.checkAndUpdateScaleFactor()) {
            this.weakRefCache.clear();
            this.stringCache.clear();
            this.cacheDightGlyphs();
            this.updateHeight();
        }
    }

    private void cacheDightGlyphs() {
        this.digitGlyphsReady = false;
        this.digitGlyphs[0] = this.cacheString((String)"0123456789").glyphs;
        this.digitGlyphs[1] = this.cacheString((String)"\u00a7l0123456789").glyphs;
        this.digitGlyphs[2] = this.cacheString((String)"\u00a7o0123456789").glyphs;
        this.digitGlyphs[3] = this.cacheString((String)"\u00a7l\u00a7o0123456789").glyphs;
        this.digitGlyphsReady = true;
    }

    public int renderString(String str, int startX, int startY, int initialColor, boolean shadowFlag) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        this.checkForScaleChange();
        Entry entry = this.cacheString(str);
        startY += this.baselineOffset;
        if ((initialColor >> 24 & 0xFF) == 0) {
            initialColor -= 0x1000000;
        }
        int color = initialColor;
        int boundTextureName = 0;
        GL11.glTexEnvi((int)8960, (int)8704, (int)8448);
        GL11.glColor4f((float)((float)(color >> 16 & 0xFF) / 255.0f), (float)((float)(color >> 8 & 0xFF) / 255.0f), (float)((float)(color & 0xFF) / 255.0f), (float)1.0f);
        if (this.antiAliasEnabled) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78370_a(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF);
        byte fontStyle = 0;
        int colorIndex = 0;
        for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
            while (colorIndex < entry.colors.length && entry.glyphs[glyphIndex].stringIndex >= entry.colors[colorIndex].stringIndex) {
                color = this.applyColorCode(entry.colors[colorIndex].colorCode, initialColor, shadowFlag);
                fontStyle = entry.colors[colorIndex].fontStyle;
                ++colorIndex;
            }
            Glyph glyph = entry.glyphs[glyphIndex];
            GlyphCache.Entry texture = glyph.texture;
            if (texture == null) continue;
            int glyphX = glyph.x + glyph.inkOffsetX;
            char c = str.charAt(glyph.stringIndex);
            if (StringCache.isLineBreak(c)) continue;
            if (c >= '0' && c <= '9') {
                int oldWidth = texture.width;
                texture = this.digitGlyphs[fontStyle][c - 48].texture;
                int newWidth = texture.width;
                glyphX += oldWidth - newWidth >> 1;
            }
            if (boundTextureName != texture.textureName) {
                tessellator.func_78381_a();
                tessellator.func_78382_b();
                tessellator.func_78370_a(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF);
                GL11.glBindTexture((int)3553, (int)texture.textureName);
                boundTextureName = texture.textureName;
            }
            if (FONT_DEBUG_DISABLE_INK_OFFSET) {
                glyphX = glyph.x;
            }
            float rs = this.renderScale();
            float x1 = this.pixelSnap((float)startX + (float)glyphX * rs);
            float x2 = this.pixelSnap((float)startX + (float)(glyphX + texture.width) * rs);
            float y1 = this.pixelSnap((float)startY + (float)glyph.y * rs);
            float y2 = this.pixelSnap((float)startY + (float)(glyph.y + texture.height) * rs);
            if (FONT_DEBUG_LOG && StringCache.isSpacingDebugGlyph(c)) {
                LogWriter.info("[StringCache glyph-debug] char='" + StringCache.debugGlyphLabel(c) + "' idx=" + glyph.stringIndex + " x=" + glyph.x + " advance=" + glyph.advance + " texW=" + texture.width + " inkOffsetX=" + glyph.inkOffsetX + " quadX1=" + x1 + " quadX2=" + x2 + " y=" + glyph.y + " tex=" + texture.textureName);
            }
            tessellator.func_78374_a((double)x1, (double)y1, 0.0, (double)texture.u1, (double)texture.v1);
            tessellator.func_78374_a((double)x1, (double)y2, 0.0, (double)texture.u1, (double)texture.v2);
            tessellator.func_78374_a((double)x2, (double)y2, 0.0, (double)texture.u2, (double)texture.v2);
            tessellator.func_78374_a((double)x2, (double)y1, 0.0, (double)texture.u2, (double)texture.v1);
        }
        tessellator.func_78381_a();
        if (entry.specialRender) {
            int renderStyle = 0;
            color = initialColor;
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            tessellator.func_78370_a(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF);
            int colorIndex2 = 0;
            for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
                float y2;
                float y1;
                float x2;
                float x1;
                int glyphSpace;
                while (colorIndex2 < entry.colors.length && entry.glyphs[glyphIndex].stringIndex >= entry.colors[colorIndex2].stringIndex) {
                    color = this.applyColorCode(entry.colors[colorIndex2].colorCode, initialColor, shadowFlag);
                    renderStyle = entry.colors[colorIndex2].renderStyle;
                    ++colorIndex2;
                }
                Glyph glyph = entry.glyphs[glyphIndex];
                int n = glyphSpace = glyph.texture != null ? glyph.advance - glyph.texture.width : 0;
                if (renderStyle & true) {
                    float urs = this.renderScale();
                    x1 = this.pixelSnap((float)startX + (float)(glyph.x - glyphSpace) * urs);
                    x2 = this.pixelSnap((float)startX + (float)(glyph.x + glyph.advance) * urs);
                    y1 = this.pixelSnap((float)startY + (float)this.underlineOffset * urs);
                    y2 = this.pixelSnap((float)startY + (float)(this.underlineOffset + 2) * urs);
                    tessellator.func_78377_a((double)x1, (double)y1, 0.0);
                    tessellator.func_78377_a((double)x1, (double)y2, 0.0);
                    tessellator.func_78377_a((double)x2, (double)y2, 0.0);
                    tessellator.func_78377_a((double)x2, (double)y1, 0.0);
                }
                if ((renderStyle & 2) == 0) continue;
                float srs = this.renderScale();
                x1 = this.pixelSnap((float)startX + (float)(glyph.x - glyphSpace) * srs);
                x2 = this.pixelSnap((float)startX + (float)(glyph.x + glyph.advance) * srs);
                y1 = this.pixelSnap((float)startY + (float)this.strikethroughOffset * srs);
                y2 = this.pixelSnap((float)startY + (float)(this.strikethroughOffset + 2) * srs);
                tessellator.func_78377_a((double)x1, (double)y1, 0.0);
                tessellator.func_78377_a((double)x1, (double)y2, 0.0);
                tessellator.func_78377_a((double)x2, (double)y2, 0.0);
                tessellator.func_78377_a((double)x2, (double)y1, 0.0);
            }
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
        }
        return (int)((float)entry.advance * this.renderScale());
    }

    public int getStringWidth(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        this.checkForScaleChange();
        Entry entry = this.cacheString(str);
        return (int)((float)entry.advance * this.renderScale());
    }

    public int getStringWidth(String str, int fontStyle) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        if (fontStyle == 0) {
            return this.getStringWidth(str);
        }
        StringBuilder sb = new StringBuilder();
        if ((fontStyle & 1) != 0) {
            sb.append('\u00a7').append('l');
        }
        if ((fontStyle & 2) != 0) {
            sb.append('\u00a7').append('o');
        }
        sb.append(str);
        return this.getStringWidth(sb.toString());
    }

    private int sizeString(String str, int width, boolean breakAtSpaces) {
        int index;
        if (str == null || str.isEmpty()) {
            return 0;
        }
        width = (int)((float)width / this.renderScale());
        Glyph[] glyphs = this.cacheString((String)str).glyphs;
        int wsIndex = -1;
        int advance = 0;
        for (index = 0; index < glyphs.length && advance <= width; advance += glyphs[index].advance, ++index) {
            if (!breakAtSpaces) continue;
            char c = str.charAt(glyphs[index].stringIndex);
            if (c == ' ') {
                wsIndex = index;
                continue;
            }
            if (c != '\n') continue;
            wsIndex = index;
            break;
        }
        if (index < glyphs.length && wsIndex != -1 && wsIndex < index) {
            index = wsIndex;
        }
        return index < glyphs.length ? glyphs[index].stringIndex : str.length();
    }

    public int sizeStringToWidth(String str, int width) {
        return this.sizeString(str, width, true);
    }

    public String trimStringToWidth(String str, int width, boolean reverse) {
        int length = this.sizeString(str, width, false);
        str = str.substring(0, length);
        if (reverse) {
            str = new StringBuilder(str).reverse().toString();
        }
        return str;
    }

    private int applyColorCode(int colorCode, int color, boolean shadowFlag) {
        if (colorCode != -1) {
            colorCode = shadowFlag ? colorCode + 16 : colorCode;
            color = this.colorTable[colorCode] & 0xFFFFFF | color & 0xFF000000;
        }
        Tessellator.field_78398_a.func_78370_a(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF);
        return color;
    }

    private Entry cacheString(String str) {
        Entry entry = null;
        if (this.mainThread == Thread.currentThread()) {
            this.lookupKey.str = str;
            entry = this.stringCache.get(this.lookupKey);
        }
        if (entry == null) {
            char[] text = str.toCharArray();
            entry = new Entry();
            int length = this.stripColorCodes(entry, str, text);
            ArrayList<Glyph> glyphList = new ArrayList<Glyph>();
            entry.advance = this.layoutBidiString(glyphList, text, 0, length, entry.colors);
            entry.glyphs = new Glyph[glyphList.size()];
            entry.glyphs = glyphList.toArray(entry.glyphs);
            Arrays.sort(entry.glyphs);
            int colorIndex = 0;
            int shift = 0;
            for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
                Glyph glyph = entry.glyphs[glyphIndex];
                while (colorIndex < entry.colors.length && glyph.stringIndex + shift >= entry.colors[colorIndex].stringIndex) {
                    shift += 2;
                    ++colorIndex;
                }
                glyph.stringIndex += shift;
            }
            if (this.mainThread == Thread.currentThread()) {
                Key key = new Key();
                key.str = new String(str);
                entry.keyRef = new WeakReference<Key>(key);
                this.stringCache.put(key, entry);
            }
        }
        if (this.mainThread == Thread.currentThread()) {
            Key oldKey = (Key)entry.keyRef.get();
            if (oldKey != null) {
                this.weakRefCache.put(str, oldKey);
            }
            this.lookupKey.str = null;
        }
        return entry;
    }

    private int stripColorCodes(Entry cacheEntry, String str, char[] text) {
        int next;
        ArrayList<ColorCode> colorList = new ArrayList<ColorCode>();
        int start = 0;
        int shift = 0;
        int fontStyle = 0;
        int renderStyle = 0;
        int colorCode = -1;
        while ((next = str.indexOf(167, start)) != -1 && next + 1 < str.length()) {
            System.arraycopy(text, next - shift + 2, text, next - shift, text.length - next - 2);
            int code = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(str.charAt(next + 1)));
            switch (code) {
                case 16: {
                    break;
                }
                case 17: {
                    fontStyle = (byte)(fontStyle | 1);
                    break;
                }
                case 18: {
                    renderStyle = (byte)(renderStyle | 2);
                    cacheEntry.specialRender = true;
                    break;
                }
                case 19: {
                    renderStyle = (byte)(renderStyle | 1);
                    cacheEntry.specialRender = true;
                    break;
                }
                case 20: {
                    fontStyle = (byte)(fontStyle | 2);
                    break;
                }
                case 21: {
                    fontStyle = 0;
                    renderStyle = 0;
                    colorCode = -1;
                    break;
                }
                default: {
                    if (code < 0 || code > 15) break;
                    colorCode = (byte)code;
                    fontStyle = 0;
                    renderStyle = 0;
                }
            }
            ColorCode entry = new ColorCode();
            entry.stringIndex = next;
            entry.stripIndex = next - shift;
            entry.colorCode = (byte)colorCode;
            entry.fontStyle = (byte)fontStyle;
            entry.renderStyle = (byte)renderStyle;
            colorList.add(entry);
            start = next + 2;
            shift += 2;
        }
        cacheEntry.colors = new ColorCode[colorList.size()];
        cacheEntry.colors = colorList.toArray(cacheEntry.colors);
        return text.length - shift;
    }

    private int layoutBidiString(List<Glyph> glyphList, char[] text, int start, int limit, ColorCode[] colors) {
        int advance = 0;
        if (Bidi.requiresBidi(text, start, limit)) {
            Bidi bidi = new Bidi(text, start, null, 0, limit - start, -2);
            if (bidi.isRightToLeft()) {
                return this.layoutStyle(glyphList, text, start, limit, 1, advance, colors);
            }
            int runCount = bidi.getRunCount();
            byte[] levels = new byte[runCount];
            Object[] ranges = new Integer[runCount];
            for (int index = 0; index < runCount; ++index) {
                levels[index] = (byte)bidi.getRunLevel(index);
                ranges[index] = new Integer(index);
            }
            Bidi.reorderVisually(levels, 0, ranges, 0, runCount);
            for (int visualIndex = 0; visualIndex < runCount; ++visualIndex) {
                int logicalIndex = (Integer)ranges[visualIndex];
                int layoutFlag = (bidi.getRunLevel(logicalIndex) & 1) == 1 ? 1 : 0;
                advance = this.layoutStyle(glyphList, text, start + bidi.getRunStart(logicalIndex), start + bidi.getRunLimit(logicalIndex), layoutFlag, advance, colors);
            }
            return advance;
        }
        return this.layoutStyle(glyphList, text, start, limit, 0, advance, colors);
    }

    private int layoutStyle(List<Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, int advance, ColorCode[] colors) {
        byte currentFontStyle = 0;
        int colorIndex = Arrays.binarySearch(colors, (Object)start);
        if (colorIndex < 0) {
            colorIndex = -colorIndex - 2;
        }
        while (start < limit) {
            int next = limit;
            while (colorIndex >= 0 && colorIndex < colors.length - 1 && colors[colorIndex].stripIndex == colors[colorIndex + 1].stripIndex) {
                ++colorIndex;
            }
            if (colorIndex >= 0 && colorIndex < colors.length) {
                currentFontStyle = colors[colorIndex].fontStyle;
            }
            while (++colorIndex < colors.length) {
                if (colors[colorIndex].fontStyle == currentFontStyle) continue;
                next = colors[colorIndex].stripIndex;
                break;
            }
            advance = this.layoutString(glyphList, text, start, next, layoutFlags, advance, currentFontStyle);
            start = next;
        }
        return advance;
    }

    private int layoutString(List<Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, int advance, int style) {
        if (this.digitGlyphsReady) {
            for (int index = start; index < limit; ++index) {
                if (text[index] < '0' || text[index] > '9') continue;
                text[index] = 48;
            }
        }
        while (start < limit) {
            if (StringCache.isLineBreak(text[start])) {
                ++start;
                continue;
            }
            Font font = this.glyphCache.lookupFont(text, start, limit, style);
            int next = font.canDisplayUpTo(text, start, limit);
            if (next == -1) {
                next = limit;
            }
            if (next == start) {
                ++next;
            }
            advance = this.layoutFont(glyphList, text, start, next, layoutFlags, advance, font);
            start = next;
        }
        return advance;
    }

    private int layoutFont(List<Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, int advance, Font font) {
        if (this.mainThread == Thread.currentThread()) {
            this.glyphCache.cacheGlyphs(font, text, start, limit, layoutFlags);
        }
        GlyphVector vector = this.glyphCache.layoutGlyphVector(font, text, start, limit, layoutFlags);
        FontRenderContext frc = this.glyphCache.getFontRenderContext();
        Glyph glyph = null;
        int numGlyphs = vector.getNumGlyphs();
        int glyphPadding = this.glyphCache.getGlyphPadding();
        for (int index = 0; index < numGlyphs; ++index) {
            int charIndex = start + vector.getGlyphCharIndex(index);
            if (charIndex >= start && charIndex < limit && StringCache.isLineBreak(text[charIndex])) continue;
            Point2D logicalPos = vector.getGlyphPosition(index);
            int logicalX = advance + (int)logicalPos.getX();
            Rectangle inkBounds = vector.getGlyphPixelBounds(index, frc, advance, 0.0f);
            if (glyph != null) {
                glyph.advance = logicalX - glyph.x;
            }
            glyph = new Glyph();
            glyph.stringIndex = charIndex;
            glyph.texture = this.glyphCache.lookupGlyph(font, vector.getGlyphCode(index));
            glyph.x = logicalX;
            glyph.y = inkBounds.y - glyphPadding;
            glyph.inkOffsetX = inkBounds.x - logicalX - glyphPadding;
            glyphList.add(glyph);
        }
        advance += (int)vector.getGlyphPosition(numGlyphs).getX();
        if (glyph != null) {
            glyph.advance = advance - glyph.x;
        }
        return advance;
    }

    private static class Glyph
    implements Comparable<Glyph> {
        public int stringIndex;
        public GlyphCache.Entry texture;
        public int x;
        public int y;
        public int advance;
        public int inkOffsetX;

        private Glyph() {
        }

        @Override
        public int compareTo(Glyph o) {
            return this.stringIndex == o.stringIndex ? 0 : (this.stringIndex < o.stringIndex ? -1 : 1);
        }
    }

    private static class ColorCode
    implements Comparable<Integer> {
        public static final byte UNDERLINE = 1;
        public static final byte STRIKETHROUGH = 2;
        public int stringIndex;
        public int stripIndex;
        public byte colorCode;
        public byte fontStyle;
        public byte renderStyle;

        private ColorCode() {
        }

        @Override
        public int compareTo(Integer i) {
            return this.stringIndex == i ? 0 : (this.stringIndex < i ? -1 : 1);
        }
    }

    private static class Entry {
        public WeakReference<Key> keyRef;
        public int advance;
        public Glyph[] glyphs;
        public ColorCode[] colors;
        public boolean specialRender;

        private Entry() {
        }
    }

    private static class Key {
        public String str;

        private Key() {
        }

        public int hashCode() {
            int code = 0;
            int length = this.str.length();
            boolean colorCode = false;
            for (int index = 0; index < length; ++index) {
                int c = this.str.charAt(index);
                if (c >= 48 && c <= 57 && !colorCode) {
                    c = 48;
                }
                code = code * 31 + c;
                colorCode = c == 167;
            }
            return code;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            String other = o.toString();
            int length = this.str.length();
            if (length != other.length()) {
                return false;
            }
            boolean colorCode = false;
            for (int index = 0; index < length; ++index) {
                char c2;
                char c1 = this.str.charAt(index);
                if (c1 != (c2 = other.charAt(index)) && (c1 < '0' || c1 > '9' || c2 < '0' || c2 > '9' || colorCode)) {
                    return false;
                }
                colorCode = c1 == '\u00a7';
            }
            return true;
        }

        public String toString() {
            return this.str;
        }
    }
}

