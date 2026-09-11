/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;

public class ModThreadDownloadImageData
extends ThreadDownloadImageData {
    private PlayerTexture playerTexture;

    public ModThreadDownloadImageData(File file, String imageUrl, ResourceLocation resourceLocation, IImageBuffer imageBuffer, PlayerTexture playerTexture) {
        super(file, imageUrl, resourceLocation, imageBuffer);
        this.playerTexture = playerTexture;
    }

    public void func_147641_a(BufferedImage bufferedImage) {
        int rgb;
        super.func_147641_a(bufferedImage);
        boolean slimModel = false;
        if (bufferedImage != null && (rgb = bufferedImage.getRGB(54, 20)) == -16777216) {
            slimModel = true;
        }
        this.playerTexture.textureDownloaded(slimModel);
    }
}

