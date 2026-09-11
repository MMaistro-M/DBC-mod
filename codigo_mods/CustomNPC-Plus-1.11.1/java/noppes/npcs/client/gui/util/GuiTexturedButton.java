/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import org.lwjgl.opengl.GL11;

public class GuiTexturedButton
extends GuiNpcButton {
    ResourceLocation location;
    public int textureX;
    public int textureY;
    boolean field_146123_n;
    String label;
    public int color;
    public float alpha;
    public float scale;
    private int totalWidth;
    private int totalHeight;
    private ImageDownloadAlt imageDownloadAlt = null;
    private boolean isUrl = false;
    private boolean gotWidthHeight = false;

    public GuiTexturedButton(int id, String buttonText, int x, int y) {
        this(id, buttonText, x, y, 200, 20);
    }

    public GuiTexturedButton(int id, String buttonText, int x, int y, int width, int height) {
        this(id, buttonText, x, y, width, height, "");
    }

    public GuiTexturedButton(int buttonId, String buttonText, int x, int y, int width, int height, String texture) {
        this(buttonId, buttonText, x, y, width, height, texture, 0, 0);
    }

    public GuiTexturedButton(int buttonId, String buttonText, int x, int y, int width, int height, String texture, int textureX, int textureY) {
        super(buttonId, x, y, width, height, buttonText);
        this.textureX = textureX;
        this.textureY = textureY;
        this.label = StatCollector.func_74838_a((String)buttonText);
        this.scale = 1.0f;
        this.color = 0xFFFFFF;
        this.alpha = 1.0f;
        if (texture != null && !texture.isEmpty()) {
            this.location = new ResourceLocation(texture);
            if (texture.startsWith("https://")) {
                TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
                ImageDownloadAlt object = new ImageDownloadAlt(null, texture, new ResourceLocation("customnpcs:textures/gui/invisible.png"), (IImageBuffer)new ImageBufferDownloadAlt(true, false));
                texturemanager.func_110579_a(this.location, (ITextureObject)object);
            } else {
                try {
                    this.getWidthHeight();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            this.location = null;
        }
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        boolean bl = this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && (float)mouseX < (float)this.field_146128_h + (float)this.field_146120_f * this.scale && (float)mouseY < (float)this.field_146129_i + (float)this.field_146121_g * this.scale;
        if (this.imageDownloadAlt != null && this.isUrl && !this.gotWidthHeight) {
            this.getURLWidthHeight();
        }
        if (this.location == null) {
            this.totalHeight = 256;
            this.totalWidth = 256;
            this.textureY = 0;
            this.textureX = 0;
        }
        GL11.glPushMatrix();
        if (this.location != null) {
            mc.func_110434_K().func_110577_a(this.location);
            this.func_73729_b(0, 0, this.textureX, this.textureY, this.field_146120_f, this.field_146121_g);
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.1);
            this.func_73732_a(mc.field_71466_p, this.label, (int)((float)this.field_146128_h + (float)this.field_146120_f * this.scale / 2.0f), (int)((float)this.field_146129_i + ((float)this.field_146121_g * this.scale - (float)ClientProxy.Font.height() + 4.0f) / 2.0f), this.color);
        } else {
            FontRenderer fontrenderer = mc.field_71466_p;
            mc.func_110434_K().func_110577_a(field_146122_a);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int k = this.func_146114_a(this.field_146123_n);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            this.func_73729_b(0, 0, 0, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(this.field_146120_f / 2, 0, 200 - this.field_146120_f / 2, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_146119_b(mc, mouseX, mouseY);
            int l = 0xE0E0E0;
            if (this.packedFGColour != 0) {
                l = this.packedFGColour;
            } else if (!this.field_146124_l) {
                l = 0xA0A0A0;
            } else if (this.field_146123_n) {
                l = 0xFFFFA0;
            }
            this.func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, l);
        }
        GL11.glPopMatrix();
    }

    public void func_73729_b(int x, int y, int textureX, int textureY, int width, int height) {
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        float u1 = (float)textureX / (float)this.totalWidth;
        float u2 = u1 + (float)width / (float)this.totalWidth;
        float v1 = (float)textureY / (float)this.totalHeight;
        float v2 = v1 + (float)height / (float)this.totalHeight;
        if (this.location != null && this.field_146124_l) {
            v1 = this.field_146123_n ? (float)(textureY + 2 * this.field_146121_g) / (float)this.totalHeight : (float)(textureY + this.field_146121_g) / (float)this.totalHeight;
            v2 = v1 + (float)height / (float)this.totalHeight;
        }
        GL11.glTranslatef((float)((float)(x + this.field_146128_h) - u1 * (float)this.totalWidth * this.scale), (float)((float)(y + this.field_146129_i) - v1 * (float)this.totalHeight * this.scale), (float)this.field_73735_i);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)1.0f);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        tessellator.func_78369_a(red, green, blue, this.alpha);
        tessellator.func_78374_a((double)(u2 * (float)this.totalWidth), (double)(v2 * (float)this.totalHeight), 0.0, (double)u2, (double)v2);
        tessellator.func_78374_a((double)(u2 * (float)this.totalWidth), (double)(v1 * (float)this.totalHeight), 0.0, (double)u2, (double)v1);
        tessellator.func_78374_a((double)(u1 * (float)this.totalWidth), (double)(v1 * (float)this.totalHeight), 0.0, (double)u1, (double)v1);
        tessellator.func_78374_a((double)(u1 * (float)this.totalWidth), (double)(v2 * (float)this.totalHeight), 0.0, (double)u1, (double)v2);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    @Override
    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        boolean bo;
        boolean bl = bo = this.field_146124_l && this.field_146125_m && i >= this.field_146128_h && j >= this.field_146129_i && (float)i < (float)this.field_146128_h + (float)this.field_146120_f * this.scale && (float)j < (float)this.field_146129_i + (float)this.field_146121_g * this.scale;
        if (bo && this.display != null && this.display.length != 0) {
            this.setDisplay((this.getValue() + 1) % this.display.length);
        }
        return bo;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void getWidthHeight() throws IOException {
        try (InputStream inputstream = null;){
            IResource iresource = Minecraft.func_71410_x().func_110442_L().func_110536_a(this.location);
            inputstream = iresource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            this.gotWidthHeight = true;
            this.totalWidth = bufferedimage.getWidth();
            this.totalHeight = bufferedimage.getHeight();
            this.correctWidthHeight();
        }
    }

    public void getURLWidthHeight() {
        if (this.imageDownloadAlt.getBufferedImage() != null) {
            this.gotWidthHeight = true;
            this.totalWidth = this.imageDownloadAlt.getBufferedImage().getWidth();
            this.totalHeight = this.imageDownloadAlt.getBufferedImage().getHeight();
            this.correctWidthHeight();
        }
    }

    public void correctWidthHeight() {
        this.totalWidth = Math.max(this.totalWidth, 1);
        this.totalHeight = Math.max(this.totalHeight, 1);
        this.field_146120_f = this.field_146120_f < 0 ? this.totalWidth : this.field_146120_f;
        this.field_146121_g = this.field_146121_g < 0 ? this.totalHeight : this.field_146121_g;
    }
}

