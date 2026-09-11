/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.skin.data;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.SkinHelper;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.utils.BitwiseUtils;

public class SkinTexture {
    public static final int TEXTURE_WIDTH = 64;
    public static final int TEXTURE_HEIGHT = 32;
    public static final int TEXTURE_SIZE = 2048;
    private final Minecraft mc = Minecraft.func_71410_x();
    private BufferedImage bufferedPlayerImage;
    private BufferedImage bufferedSkinImage = new BufferedImage(64, 32, 2);
    private int lastProfileHash = -1;
    private boolean needsUpdate = true;
    private int textureId = -1;
    private int[] paintData = new int[2048];

    protected void finalize() throws Throwable {
        this.deleteTexture();
        super.finalize();
    }

    public void updatePaintData(int[] paintData) {
        if (!Arrays.equals(paintData, this.paintData)) {
            this.paintData = (int[])paintData.clone();
            this.needsUpdate = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateForResourceLocation(ResourceLocation resourceLocation) {
        if (this.lastProfileHash == resourceLocation.hashCode() & this.bufferedPlayerImage != null) {
            return;
        }
        BufferedImage bi = null;
        InputStream inputStream = null;
        try {
            ITextureObject skintex = this.mc.func_110434_K().func_110581_b(resourceLocation);
            if (skintex instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData imageData = (ThreadDownloadImageData)skintex;
                bi = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)imageData, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(resourceLocation).func_110527_b();
                bi = ImageIO.read(inputStream);
            }
            IOUtils.closeQuietly((InputStream)inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
        if (bi == null) {
            return;
        }
        this.bufferedPlayerImage = SkinHelper.deepCopyBufferedImage(bi);
        this.lastProfileHash = resourceLocation.hashCode();
        this.needsUpdate = true;
    }

    private void updateTexture() {
        this.applyPlayerToTexture();
        this.applyPaintToTexture();
        this.uploadTexture();
        this.needsUpdate = false;
    }

    private void applyPlayerToTexture() {
        for (int ix = 0; ix < 64; ++ix) {
            for (int iy = 0; iy < 32 && this.bufferedPlayerImage != null; ++iy) {
                this.bufferedSkinImage.setRGB(ix, iy, this.bufferedPlayerImage.getRGB(ix, iy));
            }
        }
    }

    private void applyPaintToTexture() {
        for (int ix = 0; ix < 64; ++ix) {
            for (int iy = 0; iy < 32; ++iy) {
                int paintColour = this.paintData[ix + iy * 64];
                PaintType paintType = PaintType.getPaintTypeFromColour(paintColour);
                if (paintType == PaintType.NONE) continue;
                this.bufferedSkinImage.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
            }
        }
    }

    private void deleteTexture() {
        if (this.textureId != -1) {
            TextureUtil.func_147942_a((int)this.textureId);
            this.textureId = -1;
        }
    }

    private void uploadTexture() {
        this.deleteTexture();
        this.textureId = TextureUtil.func_110996_a();
        TextureUtil.func_110987_a((int)this.textureId, (BufferedImage)this.bufferedSkinImage);
    }

    public void bindTexture() {
        if (this.needsUpdate) {
            this.updateTexture();
        }
        GL11.glBindTexture((int)3553, (int)this.textureId);
    }
}

