/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.SkinManager
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.renderer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import noppes.npcs.client.renderer.RenderNPCInterface;

public class ImageData {
    private final ResourceLocation location;
    private final boolean isUrl;
    private ImageDownloadAlt imageDownloadAlt = null;
    private BufferedImage bufferedImage = null;
    private int totalWidth;
    private int totalHeight;
    private boolean gotWidthHeight;
    private boolean invalid;
    private boolean animated = false;
    private int frameCount = 1;
    private int frametime = 2;
    private int[] frames = null;
    private boolean interpolate = false;
    private boolean mcmetaChecked = false;

    public ImageData(String directory) {
        this.location = new ResourceLocation(directory);
        if (directory.startsWith("https://") || directory.startsWith("http://")) {
            this.isUrl = true;
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            this.imageDownloadAlt = new ImageDownloadAlt(null, directory, new ResourceLocation("customnpcs:textures/gui/invisible.png"), (IImageBuffer)new ImageBufferDownloadAlt(true, false));
            texturemanager.func_110579_a(this.location, (ITextureObject)this.imageDownloadAlt);
        } else {
            this.isUrl = false;
        }
    }

    public ImageData(String directory, boolean x64, ResourceLocation resource) {
        this.location = resource;
        this.isUrl = true;
        TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
        this.imageDownloadAlt = new ImageDownloadAlt(null, directory, SkinManager.field_152793_a, (IImageBuffer)new ImageBufferDownloadAlt(x64));
        texturemanager.func_110579_a(this.location, (ITextureObject)this.imageDownloadAlt);
    }

    public boolean imageLoaded() {
        if (!this.gotWidthHeight) {
            try {
                if (!this.isUrl) {
                    this.getWidthHeight();
                } else {
                    this.getURLWidthHeight();
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return !this.invalid && this.location != null && this.gotWidthHeight;
    }

    public boolean invalid() {
        return this.invalid;
    }

    public void bindTexture() {
        ResourceLocation location = this.getLocation();
        if (location != null && !this.invalid) {
            try {
                Minecraft.func_71410_x().func_110434_K().func_110577_a(location);
            }
            catch (Exception exception) {
                this.invalid = true;
            }
        }
    }

    public void renderEngineBind() {
        ResourceLocation location = this.getLocation();
        if (location != null && !this.invalid) {
            try {
                RenderNPCInterface.staticRenderManager.field_78724_e.func_110577_a(location);
            }
            catch (Exception exception) {
                this.invalid = true;
            }
        }
    }

    public ResourceLocation getLocation() {
        return this.isUrl && this.imageDownloadAlt.getBufferedImage() == null ? null : this.location;
    }

    public BufferedImage getBufferedImage() {
        if (this.bufferedImage == null) {
            if (!this.isUrl) {
                try {
                    IResource iresource = Minecraft.func_71410_x().func_110442_L().func_110536_a(this.location);
                    try (InputStream inputstream = iresource.func_110527_b();){
                        this.bufferedImage = ImageIO.read(inputstream);
                    }
                }
                catch (IOException iOException) {}
            } else {
                this.bufferedImage = this.imageDownloadAlt.getBufferedImage();
            }
        }
        return this.bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void getWidthHeight() throws IOException {
        if (this.invalid) {
            return;
        }
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

    private void getURLWidthHeight() {
        if (this.imageDownloadAlt.getBufferedImage() != null && !this.invalid) {
            this.gotWidthHeight = true;
            this.totalWidth = this.imageDownloadAlt.getBufferedImage().getWidth();
            this.totalHeight = this.imageDownloadAlt.getBufferedImage().getHeight();
            this.correctWidthHeight();
        }
    }

    private void correctWidthHeight() {
        this.totalWidth = Math.max(this.totalWidth, 1);
        this.totalHeight = Math.max(this.totalHeight, 1);
        if (!this.isUrl && !this.mcmetaChecked) {
            this.mcmetaChecked = true;
            this.tryLoadMcmeta();
        }
    }

    public int getTotalWidth() {
        return this.gotWidthHeight ? this.totalWidth : -1;
    }

    public int getTotalHeight() {
        return this.gotWidthHeight ? this.totalHeight : -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void tryLoadMcmeta() {
        try {
            ResourceLocation mcmetaLoc = new ResourceLocation(this.location.func_110624_b(), this.location.func_110623_a() + ".mcmeta");
            IResource resource = Minecraft.func_71410_x().func_110442_L().func_110536_a(mcmetaLoc);
            try (InputStream stream = resource.func_110527_b();){
                InputStreamReader reader = new InputStreamReader(stream);
                JsonObject root = new JsonParser().parse((Reader)reader).getAsJsonObject();
                if (root.has("animation")) {
                    JsonObject anim = root.getAsJsonObject("animation");
                    this.animated = true;
                    if (anim.has("frametime")) {
                        this.frametime = Math.max(1, anim.get("frametime").getAsInt());
                    }
                    if (anim.has("interpolate")) {
                        this.interpolate = anim.get("interpolate").getAsBoolean();
                    }
                    if (anim.has("frames")) {
                        JsonArray framesArray = anim.getAsJsonArray("frames");
                        this.frames = new int[framesArray.size()];
                        int maxFrame = 0;
                        for (int i = 0; i < framesArray.size(); ++i) {
                            this.frames[i] = framesArray.get(i).getAsInt();
                            if (this.frames[i] <= maxFrame) continue;
                            maxFrame = this.frames[i];
                        }
                        this.frameCount = maxFrame + 1;
                    } else if (this.totalWidth > 0) {
                        this.frameCount = Math.max(1, this.totalHeight / this.totalWidth);
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void setAnimation(int frameCount, int frametime) {
        if (frameCount > 1) {
            this.animated = true;
            this.frameCount = Math.max(1, frameCount);
            this.frametime = Math.max(1, frametime);
        }
    }

    public boolean isAnimated() {
        return this.animated && this.frameCount > 1;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public int getFrameTime() {
        return this.frametime;
    }

    public int getFrameHeight() {
        if (this.animated && this.frameCount > 1) {
            return Math.max(1, this.totalHeight / this.frameCount);
        }
        return this.gotWidthHeight ? this.totalHeight : -1;
    }

    public float getCurrentFrameVOffset() {
        int frameIdx;
        if (!this.animated || this.frameCount <= 1 || this.totalHeight <= 0) {
            return 0.0f;
        }
        long millis = Minecraft.func_71386_F();
        int tick = (int)(millis / 50L);
        if (this.frames != null && this.frames.length > 0) {
            frameIdx = tick / this.frametime % this.frames.length;
            frameIdx = this.frames[frameIdx];
        } else {
            frameIdx = tick / this.frametime % this.frameCount;
        }
        int frameHeight = this.totalHeight / this.frameCount;
        return (float)(frameIdx * frameHeight) / (float)this.totalHeight;
    }
}

