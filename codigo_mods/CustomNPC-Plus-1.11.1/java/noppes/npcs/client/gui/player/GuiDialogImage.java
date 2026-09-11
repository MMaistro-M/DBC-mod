/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.gui.player.GuiDialogInteract;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import noppes.npcs.controllers.data.DialogImage;
import org.lwjgl.opengl.GL11;

public class GuiDialogImage
extends Gui {
    GuiDialogInteract parent;
    ResourceLocation location;
    public int id;
    public String texture;
    public int x;
    public int y;
    public int width;
    public int height;
    public int textureX;
    public int textureY;
    public float scale;
    public int color;
    public int selectedColor;
    public float alpha;
    public float rotation;
    public int imageType = 0;
    public int alignment = 0;
    private int totalWidth;
    private int totalHeight;
    private ImageDownloadAlt imageDownloadAlt = null;
    private boolean isUrl = false;
    private boolean gotWidthHeight = false;

    public GuiDialogImage(DialogImage dialogImage) {
        this.location = new ResourceLocation(dialogImage.texture);
        this.id = dialogImage.id;
        this.texture = dialogImage.texture;
        this.x = dialogImage.x;
        this.y = dialogImage.y;
        this.width = dialogImage.width;
        this.height = dialogImage.height;
        this.textureX = dialogImage.textureX;
        this.textureY = dialogImage.textureY;
        this.scale = dialogImage.scale;
        this.color = dialogImage.color;
        this.selectedColor = dialogImage.selectedColor;
        this.rotation = dialogImage.rotation;
        this.alpha = dialogImage.alpha;
        this.imageType = dialogImage.imageType;
        this.alignment = dialogImage.alignment;
        if (this.texture.startsWith("https://")) {
            this.isUrl = true;
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            this.imageDownloadAlt = new ImageDownloadAlt(null, this.texture, new ResourceLocation("customnpcs:textures/gui/invisible.png"), (IImageBuffer)new ImageBufferDownloadAlt(true, false));
            texturemanager.func_110579_a(this.location, (ITextureObject)this.imageDownloadAlt);
        } else {
            try {
                this.getWidthHeight();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setParent(GuiDialogInteract parent) {
        this.parent = parent;
    }

    public int getID() {
        return this.id;
    }

    public void onRender(Minecraft mc) {
        try {
            mc.func_110434_K().func_110577_a(this.location);
        }
        catch (Exception ignored) {
            return;
        }
        float u1 = (float)this.textureX / (float)this.totalWidth;
        float u2 = u1 + (float)this.width / (float)this.totalWidth;
        float v1 = (float)this.textureY / (float)this.totalHeight;
        float v2 = v1 + (float)this.height / (float)this.totalHeight;
        if (this.imageDownloadAlt != null && this.isUrl && !this.gotWidthHeight) {
            this.getURLWidthHeight();
        }
        GL11.glPushMatrix();
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
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
        this.width = this.width < 0 ? this.totalWidth : this.width;
        this.height = this.height < 0 ? this.totalHeight : this.height;
    }
}

