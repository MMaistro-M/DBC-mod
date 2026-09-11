/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ImageBufferDownload
 */
package noppes.npcs.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import net.minecraft.client.renderer.ImageBufferDownload;

public class ImageBufferDownloadAlt
extends ImageBufferDownload {
    private int[] imageData;
    private int imageWidth;
    private int imageHeight;
    private final boolean version;
    public boolean skinTexture = true;

    public ImageBufferDownloadAlt(boolean ver) {
        this.version = ver;
    }

    public ImageBufferDownloadAlt(boolean ver, boolean skinTexture) {
        this.version = ver;
        this.skinTexture = skinTexture;
    }

    public BufferedImage func_78432_a(BufferedImage bufferedimage) {
        if (this.version) {
            this.imageWidth = bufferedimage.getWidth(null);
            this.imageHeight = bufferedimage.getHeight(null);
            BufferedImage bufferedimage1 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
            Graphics g = bufferedimage1.getGraphics();
            g.drawImage(bufferedimage, 0, 0, null);
            g.dispose();
            this.imageData = ((DataBufferInt)bufferedimage1.getRaster().getDataBuffer()).getData();
            if (this.skinTexture) {
                this.setAreaTransparent(this.imageWidth / 2, 0, this.imageWidth, this.imageHeight / 4);
            }
            return bufferedimage1;
        }
        this.imageWidth = bufferedimage.getWidth(null);
        this.imageHeight = this.imageWidth / 2;
        BufferedImage bufferedimage1 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
        Graphics g = bufferedimage1.getGraphics();
        g.drawImage(bufferedimage, 0, 0, null);
        g.dispose();
        this.imageData = ((DataBufferInt)bufferedimage1.getRaster().getDataBuffer()).getData();
        this.setAreaTransparent(this.imageWidth / 2, 0, this.imageWidth, this.imageHeight / 2);
        return bufferedimage1;
    }

    private void setAreaTransparent(int par1, int par2, int par3, int par4) {
        if (!this.hasTransparency(par1, par2, par3, par4)) {
            for (int i1 = par1; i1 < par3; ++i1) {
                for (int j1 = par2; j1 < par4; ++j1) {
                    int n = i1 + j1 * this.imageWidth;
                    this.imageData[n] = this.imageData[n] & 0xFFFFFF;
                }
            }
        }
    }

    private boolean hasTransparency(int par1, int par2, int par3, int par4) {
        for (int i1 = par1; i1 < par3; ++i1) {
            for (int j1 = par2; j1 < par4; ++j1) {
                int k1 = this.imageData[i1 + j1 * this.imageWidth];
                if ((k1 >> 24 & 0xFF) >= 128) continue;
                return true;
            }
        }
        return false;
    }
}

