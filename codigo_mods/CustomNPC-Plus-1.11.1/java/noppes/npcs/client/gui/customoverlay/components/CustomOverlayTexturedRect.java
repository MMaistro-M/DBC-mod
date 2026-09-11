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
package noppes.npcs.client.gui.customoverlay.components;

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
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.gui.customoverlay.OverlayCustom;
import noppes.npcs.client.gui.customoverlay.interfaces.IOverlayComponent;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import noppes.npcs.scripted.overlay.ScriptOverlayTexturedRect;
import org.lwjgl.opengl.GL11;

public class CustomOverlayTexturedRect
extends Gui
implements IOverlayComponent {
    OverlayCustom parent;
    int alignment = 0;
    int id;
    ResourceLocation location;
    int x;
    int y;
    int width;
    int height;
    int textureX;
    int textureY;
    float scale = 1.0f;
    int color;
    float alpha;
    float rotation;
    private int totalWidth;
    private int totalHeight;
    private ImageDownloadAlt imageDownloadAlt = null;
    private boolean isUrl = false;
    private boolean gotWidthHeight = false;

    public CustomOverlayTexturedRect(int id, String texture, int x, int y, int width, int height) {
        this(id, texture, x, y, width, height, 0, 0);
    }

    public CustomOverlayTexturedRect(int id, String directory, int x, int y, int width, int height, int textureX, int textureY) {
        this.id = id;
        this.location = new ResourceLocation(directory);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureX = textureX;
        this.textureY = textureY;
        if (directory.startsWith("https://")) {
            this.isUrl = true;
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            this.imageDownloadAlt = new ImageDownloadAlt(null, directory, new ResourceLocation("customnpcs:textures/gui/invisible.png"), (IImageBuffer)new ImageBufferDownloadAlt(true, false));
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

    public void setParent(OverlayCustom parent) {
        this.parent = parent;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public void onRender(Minecraft mc, float partialTicks) {
        mc.func_110434_K().func_110577_a(this.location);
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
        GL11.glTranslatef((float)((float)(this.alignment % 3) * ((float)OverlayCustom.scaledWidth / 2.0f)), (float)((float)(Math.floor(this.alignment / 3) * (double)((float)OverlayCustom.scaledHeight / 2.0f))), (float)0.0f);
        GL11.glTranslatef((float)((float)this.x - u1 * (float)this.totalWidth), (float)((float)this.y - v1 * (float)this.totalHeight), (float)this.id);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
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

    public ICustomOverlayComponent toComponent() {
        ScriptOverlayTexturedRect component = new ScriptOverlayTexturedRect(this.id, this.location.toString(), this.x, this.y, this.width, this.height, this.textureX, this.textureY);
        component.setScale(this.scale);
        component.setAlignment(this.alignment);
        component.setAlpha(this.alpha);
        component.setColor(this.color);
        component.setRotation(this.rotation);
        return component;
    }

    public static CustomOverlayTexturedRect fromComponent(ScriptOverlayTexturedRect component) {
        CustomOverlayTexturedRect rect = component.getTextureX() >= 0 && component.getTextureY() >= 0 ? new CustomOverlayTexturedRect(component.getID(), component.getTexture(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight(), component.getTextureX(), component.getTextureY()) : new CustomOverlayTexturedRect(component.getID(), component.getTexture(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight());
        rect.scale = component.getScale();
        rect.alignment = component.getAlignment();
        rect.alpha = component.getAlpha();
        rect.color = component.getColor();
        rect.rotation = component.getRotation();
        return rect;
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

