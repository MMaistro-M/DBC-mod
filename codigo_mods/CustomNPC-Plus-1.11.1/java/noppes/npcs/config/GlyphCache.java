/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.config;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GlyphCache {
    private static final int TEXTURE_WIDTH = 512;
    private static final int TEXTURE_HEIGHT = 512;
    private static final int STRING_WIDTH = 256;
    private static final int STRING_HEIGHT = 64;
    private static final int GLYPH_BORDER = 2;
    private static final int GLYPH_PADDING = 2;
    private static final int GLYPH_SPACING_BASE = 5;
    private static Color BACK_COLOR = new Color(255, 255, 255, 0);
    private int fontSize = 18;
    private boolean antiAliasEnabled = false;
    private int guiScaleFactor = 1;
    private BufferedImage stringImage;
    private Graphics2D stringGraphics;
    private BufferedImage glyphCacheImage = new BufferedImage(512, 512, 2);
    private Graphics2D glyphCacheGraphics = this.glyphCacheImage.createGraphics();
    private FontRenderContext fontRenderContext = this.glyphCacheGraphics.getFontRenderContext();
    private int[] imageData = new int[262144];
    private IntBuffer imageBuffer = ByteBuffer.allocateDirect(0x100000).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
    private IntBuffer singleIntBuffer = GLAllocation.func_74527_f((int)1);
    private List<Font> allFonts = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
    protected List<Font> usedFonts = new ArrayList<Font>();
    private int textureName;
    private LinkedHashMap<Font, Integer> fontCache = new LinkedHashMap();
    private LinkedHashMap<Long, Entry> glyphCache = new LinkedHashMap();
    private int cachePosX = 2;
    private int cachePosY = 2;
    private int cacheLineHeight = 0;

    int getGuiScaleFactor() {
        return this.guiScaleFactor;
    }

    FontRenderContext getFontRenderContext() {
        return this.fontRenderContext;
    }

    int getGlyphPadding() {
        return 2;
    }

    private int computeGlyphSpacing(Font font) {
        float italicAngle = font.getItalicAngle();
        if (italicAngle == 0.0f && font.isItalic()) {
            italicAngle = -0.2f;
        }
        if (italicAngle == 0.0f) {
            return 5;
        }
        LineMetrics lm = font.getLineMetrics("I", this.fontRenderContext);
        float lineHeight = lm.getAscent() + lm.getDescent();
        int italicOverhang = (int)Math.ceil(lineHeight * Math.abs(italicAngle) * 1.5f);
        return Math.max(5, 4 + italicOverhang + 1);
    }

    public GlyphCache() {
        this.glyphCacheGraphics.setBackground(BACK_COLOR);
        this.glyphCacheGraphics.setComposite(AlphaComposite.Src);
        this.allocateGlyphCacheTexture();
        this.allocateStringImage(256, 64);
        GraphicsEnvironment.getLocalGraphicsEnvironment().preferLocaleFonts();
        this.usedFonts.add(new Font("SansSerif", 0, 72));
    }

    void setDefaultFont(String name, int size, boolean antiAlias) {
        this.usedFonts.clear();
        this.usedFonts.add(new Font(name, 0, 72));
        this.fontSize = size;
        this.antiAliasEnabled = antiAlias;
        this.guiScaleFactor = GlyphCache.queryGuiScaleFactor();
        this.setRenderingHints();
    }

    void setCustomFont(ResourceLocation location, int size, boolean antiAlias) throws Exception {
        try (InputStream stream = Minecraft.func_71410_x().func_110442_L().func_110536_a(location).func_110527_b();){
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(0, stream);
            ge.registerFont(font);
            font = font.deriveFont(0, 72.0f);
            this.usedFonts.clear();
            this.usedFonts.add(font);
            this.fontSize = size;
            this.antiAliasEnabled = antiAlias;
            this.guiScaleFactor = GlyphCache.queryGuiScaleFactor();
            this.setRenderingHints();
        }
    }

    private static int queryGuiScaleFactor() {
        try {
            Minecraft mc = Minecraft.func_71410_x();
            if (mc != null && mc.field_71443_c > 0 && mc.field_71440_d > 0) {
                ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
                return Math.max(1, sr.func_78325_e());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 1;
    }

    boolean checkAndUpdateScaleFactor() {
        int newScale = GlyphCache.queryGuiScaleFactor();
        if (newScale != this.guiScaleFactor) {
            this.guiScaleFactor = newScale;
            this.invalidateGlyphCache();
            return true;
        }
        return false;
    }

    private void invalidateGlyphCache() {
        this.glyphCache.clear();
        this.fontCache.clear();
        this.cachePosX = 2;
        this.cachePosY = 2;
        this.cacheLineHeight = 0;
        this.allocateGlyphCacheTexture();
        this.allocateStringImage(256, 64);
    }

    int fontHeight(String s) {
        Font font = this.lookupFont(s.toCharArray(), 0, s.length(), 0);
        return (int)font.getLineMetrics(s, this.fontRenderContext).getHeight();
    }

    LineMetrics getLineMetrics(String s) {
        Font font = this.lookupFont(s.toCharArray(), 0, s.length(), 0);
        return font.getLineMetrics(s, this.fontRenderContext);
    }

    GlyphVector layoutGlyphVector(Font font, char[] text, int start, int limit, int layoutFlags) {
        if (!this.fontCache.containsKey(font)) {
            this.fontCache.put(font, this.fontCache.size());
        }
        return font.layoutGlyphVector(this.fontRenderContext, text, start, limit, layoutFlags);
    }

    Font lookupFont(char[] text, int start, int limit, int style) {
        Font font2;
        int renderSize = this.fontSize * this.guiScaleFactor;
        for (Font font2 : this.usedFonts) {
            if (font2.canDisplayUpTo(text, start, limit) == start) continue;
            return font2.deriveFont(style, renderSize);
        }
        for (Font font2 : this.allFonts) {
            if (font2.canDisplayUpTo(text, start, limit) == start) continue;
            this.usedFonts.add(font2);
            return font2.deriveFont(style, renderSize);
        }
        font2 = this.usedFonts.get(0);
        return font2.deriveFont(style, renderSize);
    }

    Entry lookupGlyph(Font font, int glyphCode) {
        long fontKey = (long)this.fontCache.get(font).intValue() << 32;
        return this.glyphCache.get(fontKey | (long)glyphCode);
    }

    void cacheGlyphs(Font font, char[] text, int start, int limit, int layoutFlags) {
        GlyphVector vector = this.layoutGlyphVector(font, text, start, limit, layoutFlags);
        Rectangle vectorBounds = null;
        long fontKey = (long)this.fontCache.get(font).intValue() << 32;
        int numGlyphs = vector.getNumGlyphs();
        Rectangle dirty = null;
        boolean vectorRendered = false;
        int glyphSpacing = this.computeGlyphSpacing(font);
        for (int index = 0; index < numGlyphs; ++index) {
            int glyphCode;
            char c;
            int charIndex = start + vector.getGlyphCharIndex(index);
            if (charIndex >= start && charIndex < limit && ((c = text[charIndex]) == '\n' || c == '\r') || this.glyphCache.containsKey(fontKey | (long)(glyphCode = vector.getGlyphCode(index)))) continue;
            Rectangle glyphBounds = vector.getGlyphPixelBounds(index, this.fontRenderContext, 0.0f, 0.0f);
            if (glyphBounds.width == 0 || glyphBounds.height == 0) continue;
            if (!vectorRendered) {
                vectorRendered = true;
                for (int i = 0; i < numGlyphs; ++i) {
                    Point2D pos = vector.getGlyphPosition(i);
                    pos.setLocation(pos.getX() + (double)(glyphSpacing * i), pos.getY());
                    vector.setGlyphPosition(i, pos);
                }
                vectorBounds = vector.getPixelBounds(this.fontRenderContext, 0.0f, 0.0f);
                vectorBounds.grow(2, 2);
                if (this.stringImage == null || vectorBounds.width > this.stringImage.getWidth() || vectorBounds.height > this.stringImage.getHeight()) {
                    int width = Math.max(vectorBounds.width, this.stringImage.getWidth());
                    int height = Math.max(vectorBounds.height, this.stringImage.getHeight());
                    this.allocateStringImage(width, height);
                }
                this.stringGraphics.clearRect(0, 0, this.stringImage.getWidth(), this.stringImage.getHeight());
                this.stringGraphics.drawGlyphVector(vector, -vectorBounds.x, -vectorBounds.y);
            }
            Rectangle rect = vector.getGlyphPixelBounds(index, this.fontRenderContext, -vectorBounds.x, -vectorBounds.y);
            rect.grow(2, 2);
            if (rect.x < 0) {
                rect.width += rect.x;
                rect.x = 0;
            }
            if (rect.y < 0) {
                rect.height += rect.y;
                rect.y = 0;
            }
            if (rect.x + rect.width > this.stringImage.getWidth()) {
                rect.width = this.stringImage.getWidth() - rect.x;
            }
            if (rect.y + rect.height > this.stringImage.getHeight()) {
                rect.height = this.stringImage.getHeight() - rect.y;
            }
            if (rect.width <= 0 || rect.height <= 0) continue;
            if (this.cachePosX + rect.width + 2 > 512) {
                this.cachePosX = 2;
                this.cachePosY += this.cacheLineHeight + 2;
                this.cacheLineHeight = 0;
            }
            if (this.cachePosY + rect.height + 2 > 512) {
                this.updateTexture(dirty);
                dirty = null;
                this.allocateGlyphCacheTexture();
                this.cachePosX = 2;
                this.cachePosY = 2;
                this.cacheLineHeight = 0;
            }
            if (rect.height > this.cacheLineHeight) {
                this.cacheLineHeight = rect.height;
            }
            this.glyphCacheGraphics.drawImage(this.stringImage, this.cachePosX, this.cachePosY, this.cachePosX + rect.width, this.cachePosY + rect.height, rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, null);
            rect.setLocation(this.cachePosX, this.cachePosY);
            Entry entry = new Entry();
            entry.textureName = this.textureName;
            entry.width = rect.width;
            entry.height = rect.height;
            entry.u1 = (float)rect.x / 512.0f;
            entry.v1 = (float)rect.y / 512.0f;
            entry.u2 = (float)(rect.x + rect.width) / 512.0f;
            entry.v2 = (float)(rect.y + rect.height) / 512.0f;
            this.glyphCache.put(fontKey | (long)glyphCode, entry);
            if (dirty == null) {
                dirty = new Rectangle(this.cachePosX, this.cachePosY, rect.width, rect.height);
            } else {
                dirty.add(rect);
            }
            this.cachePosX += rect.width + 2;
        }
        this.updateTexture(dirty);
    }

    private void updateTexture(Rectangle dirty) {
        if (dirty != null) {
            this.updateImageBuffer(dirty.x, dirty.y, dirty.width, dirty.height);
            GL11.glBindTexture((int)3553, (int)this.textureName);
            GL11.glTexSubImage2D((int)3553, (int)0, (int)dirty.x, (int)dirty.y, (int)dirty.width, (int)dirty.height, (int)6408, (int)5121, (IntBuffer)this.imageBuffer);
        }
    }

    private void allocateStringImage(int width, int height) {
        this.stringImage = new BufferedImage(width, height, 2);
        this.stringGraphics = this.stringImage.createGraphics();
        this.setRenderingHints();
        this.stringGraphics.setBackground(BACK_COLOR);
        this.stringGraphics.setPaint(Color.WHITE);
    }

    private void setRenderingHints() {
        this.stringGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.antiAliasEnabled ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        this.stringGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.stringGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.stringGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        this.fontRenderContext = this.stringGraphics.getFontRenderContext();
    }

    private void allocateGlyphCacheTexture() {
        this.glyphCacheGraphics.clearRect(0, 0, 512, 512);
        this.singleIntBuffer.clear();
        GL11.glGenTextures((IntBuffer)this.singleIntBuffer);
        this.textureName = this.singleIntBuffer.get(0);
        this.updateImageBuffer(0, 0, 512, 512);
        GL11.glBindTexture((int)3553, (int)this.textureName);
        GL11.glTexImage2D((int)3553, (int)0, (int)32828, (int)512, (int)512, (int)0, (int)6408, (int)5121, (IntBuffer)this.imageBuffer);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
    }

    private void updateImageBuffer(int x, int y, int width, int height) {
        this.glyphCacheImage.getRGB(x, y, width, height, this.imageData, 0, width);
        for (int i = 0; i < width * height; ++i) {
            int color = this.imageData[i];
            this.imageData[i] = color << 8 | color >>> 24;
        }
        this.imageBuffer.clear();
        this.imageBuffer.put(this.imageData);
        this.imageBuffer.flip();
    }

    static class Entry {
        public int textureName;
        public int width;
        public int height;
        public float u1;
        public float v1;
        public float u2;
        public float v2;

        Entry() {
        }
    }
}

