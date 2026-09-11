/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.text.ArabicShaping
 *  com.ibm.icu.text.ArabicShapingException
 *  com.ibm.icu.text.Bidi
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiLabel;
import org.lwjgl.opengl.GL11;

public class CustomGuiLabel
extends Gui
implements IGuiComponent {
    int x;
    int y;
    int width;
    int height;
    GuiCustom parent;
    String fullLabel;
    String[] hoverText;
    float scale;
    int id;
    int border = 2;
    boolean labelBgEnabled = true;
    boolean labelShadowEnabled = false;
    int color;
    float alpha;
    float rotation;
    protected int field_146167_a;
    protected int field_146161_f;
    public int field_146162_g;
    public int field_146174_h;
    private boolean randomStyle = false;
    private boolean boldStyle = false;
    private boolean italicStyle = false;
    private boolean underlineStyle = false;
    private boolean strikethroughStyle = false;
    private boolean bidiFlag = false;
    private boolean unicodeFlag = false;
    private static final ResourceLocation[] unicodePageLocations = new ResourceLocation[256];
    protected final ResourceLocation locationFontTexture;
    protected int[] charWidth = new int[256];
    protected byte[] glyphWidth = new byte[65536];
    private final int[] colorCode = new int[32];
    private float red;
    private float green;
    private float blue;
    private float posX;
    private float posY;

    public CustomGuiLabel(int id, String fullLabel, int x, int y, int width, int height, boolean shadow) {
        this.id = id;
        this.fullLabel = fullLabel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.labelShadowEnabled = shadow;
        this.locationFontTexture = new ResourceLocation("textures/font/ascii.png");
        this.readFontTexture();
        this.readGlyphSizes();
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
            this.colorCode[i] = (k & 0xFF) << 16 | (l & 0xFF) << 8 | i1 & 0xFF;
        }
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)this.id);
        this.drawLabel(mouseX, mouseY);
        GL11.glPopMatrix();
    }

    @Override
    public int getID() {
        return this.id;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public static CustomGuiLabel fromComponent(ScriptGuiLabel component) {
        CustomGuiLabel lbl = new CustomGuiLabel(component.getID(), component.getText(), GuiCustom.guiLeft + component.getPosX(), GuiCustom.guiTop + component.getPosY(), component.getWidth(), component.getHeight(), component.getShadow());
        lbl.scale = 1.0f;
        lbl.color = component.getColor();
        lbl.setScale(component.getScale());
        if (component.hasHoverText()) {
            lbl.hoverText = component.getHoverText();
        }
        lbl.labelShadowEnabled = component.getShadow();
        lbl.color = component.getColor();
        lbl.alpha = component.getAlpha();
        lbl.rotation = component.getRotation();
        lbl.field_146167_a = component.getWidth();
        lbl.field_146161_f = component.getHeight();
        return lbl;
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiLabel component = new ScriptGuiLabel(this.id, this.fullLabel, this.field_146162_g, this.field_146174_h, this.field_146167_a, this.field_146161_f, this.color);
        component.setHoverText(this.hoverText);
        component.setShadow(this.labelShadowEnabled);
        component.setColor(this.color);
        component.setAlpha(this.alpha);
        component.setRotation(this.rotation);
        return component;
    }

    public void drawLabel(int mouseX, int mouseY) {
        GL11.glPushMatrix();
        this.red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        this.green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        this.blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glTranslatef((float)this.x, (float)this.y, (float)0.0f);
        GL11.glRotated((double)this.rotation, (double)0.0, (double)0.0, (double)1.0);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        this.drawString(this.fullLabel, 0, 0, this.color, this.labelShadowEnabled, mouseX, mouseY);
        GL11.glPopMatrix();
    }

    public int drawString(String p_85187_1_, int p_85187_2_, int p_85187_3_, int p_85187_4_, boolean p_85187_5_, int mouseX, int mouseY) {
        int l;
        this.resetStyles();
        if (p_85187_5_) {
            l = this.renderString(p_85187_1_, p_85187_2_ + 1, p_85187_3_ + 1, p_85187_4_, true, mouseX, mouseY);
            l = Math.max(l, this.renderString(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false, mouseX, mouseY));
        } else {
            l = this.renderString(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false, mouseX, mouseY);
        }
        return l;
    }

    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    private int renderString(String p_78258_1_, int p_78258_2_, int p_78258_3_, int p_78258_4_, boolean p_78258_5_, int mouseX, int mouseY) {
        boolean hovered;
        if (p_78258_1_ == null) {
            return 0;
        }
        if (this.bidiFlag) {
            p_78258_1_ = this.bidiReorder(p_78258_1_);
        }
        float endX = this.posX;
        float endY = this.posY;
        int height = 0;
        StringBuilder formatting = new StringBuilder();
        while (!p_78258_1_.isEmpty()) {
            int i;
            StringBuilder s = new StringBuilder();
            for (i = 0; i < p_78258_1_.length(); ++i) {
                boolean formatSymbol;
                char c = p_78258_1_.charAt(i);
                boolean bl = formatSymbol = c == '&' && i < p_78258_1_.length() - 1 && "0123456789abcdefklmnor".indexOf(p_78258_1_.toLowerCase().charAt(i + 1)) != -1;
                if (formatSymbol) {
                    String format = "" + c + p_78258_1_.toLowerCase().charAt(i + 1);
                    if (!format.equals("&r")) {
                        formatting.append(format);
                    } else {
                        formatting = new StringBuilder();
                    }
                    ++i;
                    continue;
                }
                if (formatting.length() > 0) {
                    s.append((CharSequence)formatting);
                }
                s.append(c);
                if (!Character.isLetterOrDigit(c) && ClientProxy.Font.width(s.toString()) >= this.width) break;
            }
            this.setColor(this.red, this.green, this.blue, this.alpha);
            this.posX = p_78258_2_;
            this.posY = (float)p_78258_3_ + (float)(height * ClientProxy.Font.height()) * 0.75f;
            endX = Math.max(endX, this.posX + (float)ClientProxy.Font.width(s.toString()));
            endY = this.posY + (float)ClientProxy.Font.height() * 0.75f;
            this.renderStringAtPos(s.toString().trim(), p_78258_5_);
            if (i >= p_78258_1_.length() - 1 || (float)(++height * ClientProxy.Font.height()) * 0.75f >= (float)this.height) break;
            p_78258_1_ = p_78258_1_.substring(i + 1);
        }
        float textWidth = this.scale * endX;
        float textHeight = this.scale * endY;
        boolean bl = hovered = mouseX >= this.x && mouseY >= this.y && (float)mouseX < (float)this.x + textWidth && (float)mouseY < (float)this.y + textHeight;
        if (hovered && this.hoverText != null && this.hoverText.length > 0) {
            this.parent.hoverText = this.hoverText;
        }
        return (int)this.posX;
    }

    private void renderStringAtPos(String p_78255_1_, boolean p_78255_2_) {
        for (int i = 0; i < p_78255_1_.length(); ++i) {
            boolean flag1;
            int k;
            int j;
            char c0 = p_78255_1_.charAt(i);
            if (c0 == '&' && i + 1 < p_78255_1_.length()) {
                j = "0123456789abcdefklmnor".indexOf(p_78255_1_.toLowerCase().charAt(i + 1));
                if (j < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (j < 0 || j > 15) {
                        j = 15;
                    }
                    if (p_78255_2_) {
                        j += 16;
                    }
                    k = this.colorCode[j];
                    this.setColor((float)(k >> 16) / 255.0f, (float)(k >> 8 & 0xFF) / 255.0f, (float)(k & 0xFF) / 255.0f, this.alpha);
                } else if (j == 16) {
                    this.randomStyle = true;
                } else if (j == 17) {
                    this.boldStyle = true;
                } else if (j == 18) {
                    this.strikethroughStyle = true;
                } else if (j == 19) {
                    this.underlineStyle = true;
                } else if (j == 20) {
                    this.italicStyle = true;
                } else if (j == 21) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                }
                ++i;
                continue;
            }
            j = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);
            if (this.randomStyle && j != -1) {
                while (this.charWidth[j] != this.charWidth[k = Minecraft.func_71410_x().field_71466_p.field_78289_c.nextInt(this.charWidth.length)]) {
                }
                j = k;
            }
            float f1 = this.unicodeFlag ? 0.5f : 1.0f;
            boolean bl = flag1 = (c0 == '\u0000' || j == -1 || this.unicodeFlag) && p_78255_2_;
            if (flag1) {
                this.posX -= f1;
                this.posY -= f1;
            }
            float f = this.renderCharAtPos(j, c0, this.italicStyle);
            if (flag1) {
                this.posX += f1;
                this.posY += f1;
            }
            if (this.boldStyle) {
                this.posX += f1;
                if (flag1) {
                    this.posX -= f1;
                    this.posY -= f1;
                }
                this.renderCharAtPos(j, c0, this.italicStyle);
                this.posX -= f1;
                if (flag1) {
                    this.posX += f1;
                    this.posY += f1;
                }
                f += 1.0f;
            }
            this.doDraw(f);
        }
    }

    protected void doDraw(float f) {
        Tessellator tessellator;
        if (this.strikethroughStyle) {
            tessellator = Tessellator.field_78398_a;
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            tessellator.func_78377_a((double)this.posX, (double)(this.posY + (float)(Minecraft.func_71410_x().field_71466_p.field_78288_b / 2)), 0.0);
            tessellator.func_78377_a((double)(this.posX + f), (double)(this.posY + (float)(Minecraft.func_71410_x().field_71466_p.field_78288_b / 2)), 0.0);
            tessellator.func_78377_a((double)(this.posX + f), (double)(this.posY + (float)(Minecraft.func_71410_x().field_71466_p.field_78288_b / 2) - 1.0f), 0.0);
            tessellator.func_78377_a((double)this.posX, (double)(this.posY + (float)(Minecraft.func_71410_x().field_71466_p.field_78288_b / 2) - 1.0f), 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
        }
        if (this.underlineStyle) {
            tessellator = Tessellator.field_78398_a;
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            int l = this.underlineStyle ? -1 : 0;
            tessellator.func_78377_a((double)(this.posX + (float)l), (double)(this.posY + (float)Minecraft.func_71410_x().field_71466_p.field_78288_b), 0.0);
            tessellator.func_78377_a((double)(this.posX + f), (double)(this.posY + (float)Minecraft.func_71410_x().field_71466_p.field_78288_b), 0.0);
            tessellator.func_78377_a((double)(this.posX + f), (double)(this.posY + (float)Minecraft.func_71410_x().field_71466_p.field_78288_b - 1.0f), 0.0);
            tessellator.func_78377_a((double)(this.posX + (float)l), (double)(this.posY + (float)Minecraft.func_71410_x().field_71466_p.field_78288_b - 1.0f), 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
        }
        this.posX += (float)((int)f);
    }

    private float renderCharAtPos(int p_78278_1_, char p_78278_2_, boolean p_78278_3_) {
        return p_78278_2_ == ' ' ? 4.0f : ("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_78278_2_) != -1 && !this.unicodeFlag ? this.renderDefaultChar(p_78278_1_, p_78278_3_) : this.renderUnicodeChar(p_78278_2_, p_78278_3_));
    }

    protected float renderDefaultChar(int p_78266_1_, boolean p_78266_2_) {
        float f = p_78266_1_ % 16 * 8;
        float f1 = p_78266_1_ / 16 * 8;
        float f2 = p_78266_2_ ? 1.0f : 0.0f;
        this.bindTexture(this.locationFontTexture);
        float f3 = (float)this.charWidth[p_78266_1_] - 0.01f;
        GL11.glBegin((int)5);
        GL11.glTexCoord2f((float)(f / 128.0f), (float)(f1 / 128.0f));
        GL11.glVertex3f((float)(this.posX + f2), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)(f / 128.0f), (float)((f1 + 7.99f) / 128.0f));
        GL11.glVertex3f((float)(this.posX - f2), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glTexCoord2f((float)((f + f3 - 1.0f) / 128.0f), (float)(f1 / 128.0f));
        GL11.glVertex3f((float)(this.posX + f3 - 1.0f + f2), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)((f + f3 - 1.0f) / 128.0f), (float)((f1 + 7.99f) / 128.0f));
        GL11.glVertex3f((float)(this.posX + f3 - 1.0f - f2), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glEnd();
        return this.charWidth[p_78266_1_];
    }

    private ResourceLocation getUnicodePageLocation(int p_111271_1_) {
        if (unicodePageLocations[p_111271_1_] == null) {
            CustomGuiLabel.unicodePageLocations[p_111271_1_] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", p_111271_1_));
        }
        return unicodePageLocations[p_111271_1_];
    }

    private void loadGlyphTexture(int p_78257_1_) {
        this.bindTexture(this.getUnicodePageLocation(p_78257_1_));
    }

    protected float renderUnicodeChar(char p_78277_1_, boolean p_78277_2_) {
        if (this.glyphWidth[p_78277_1_] == 0) {
            return 0.0f;
        }
        int i = p_78277_1_ / 256;
        this.loadGlyphTexture(i);
        int j = this.glyphWidth[p_78277_1_] >>> 4;
        int k = this.glyphWidth[p_78277_1_] & 0xF;
        float f = j;
        float f1 = k + 1;
        float f2 = (float)(p_78277_1_ % 16 * 16) + f;
        float f3 = (p_78277_1_ & 0xFF) / 16 * 16;
        float f4 = f1 - f - 0.02f;
        float f5 = p_78277_2_ ? 1.0f : 0.0f;
        GL11.glBegin((int)5);
        GL11.glTexCoord2f((float)(f2 / 256.0f), (float)(f3 / 256.0f));
        GL11.glVertex3f((float)(this.posX + f5), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)(f2 / 256.0f), (float)((f3 + 15.98f) / 256.0f));
        GL11.glVertex3f((float)(this.posX - f5), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glTexCoord2f((float)((f2 + f4) / 256.0f), (float)(f3 / 256.0f));
        GL11.glVertex3f((float)(this.posX + f4 / 2.0f + f5), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)((f2 + f4) / 256.0f), (float)((f3 + 15.98f) / 256.0f));
        GL11.glVertex3f((float)(this.posX + f4 / 2.0f - f5), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glEnd();
        return (f1 - f) / 2.0f + 1.0f;
    }

    protected void setColor(float r, float g, float b, float a) {
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
    }

    protected void bindTexture(ResourceLocation location) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(location);
    }

    private void readFontTexture() {
        BufferedImage bufferedimage;
        try (InputStream stream = this.getResourceInputStream(this.locationFontTexture);){
            bufferedimage = ImageIO.read(stream);
        }
        catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }
        int i = bufferedimage.getWidth();
        int j = bufferedimage.getHeight();
        int[] aint = new int[i * j];
        bufferedimage.getRGB(0, 0, i, j, aint, 0, i);
        int k = j / 16;
        int l = i / 16;
        int b0 = 1;
        float f = 8.0f / (float)l;
        for (int i1 = 0; i1 < 256; ++i1) {
            int l1;
            int j1 = i1 % 16;
            int k1 = i1 / 16;
            if (i1 == 32) {
                this.charWidth[i1] = 3 + b0;
            }
            for (l1 = l - 1; l1 >= 0; --l1) {
                int i2 = j1 * l + l1;
                boolean flag = true;
                for (int j2 = 0; j2 < k && flag; ++j2) {
                    int k2 = (k1 * l + j2) * i;
                    if ((aint[i2 + k2] >> 24 & 0xFF) == 0) continue;
                    flag = false;
                }
                if (!flag) break;
            }
            this.charWidth[i1] = (int)(0.5 + (double)((float)(++l1) * f)) + b0;
        }
    }

    private void readGlyphSizes() {
        try (InputStream inputstream = this.getResourceInputStream(new ResourceLocation("font/glyph_sizes.bin"));){
            inputstream.read(this.glyphWidth);
        }
        catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }
    }

    protected InputStream getResourceInputStream(ResourceLocation location) throws IOException {
        return Minecraft.func_71410_x().func_110442_L().func_110536_a(location).func_110527_b();
    }

    private String bidiReorder(String p_147647_1_) {
        try {
            Bidi bidi = new Bidi(new ArabicShaping(8).shape(p_147647_1_), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        }
        catch (ArabicShapingException arabicshapingexception) {
            return p_147647_1_;
        }
    }
}

