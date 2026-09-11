/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.Color;
import com.eliotlash.mclib.utils.resources.FilteredResourceLocation;
import com.eliotlash.mclib.utils.resources.MultiResourceLocation;
import com.eliotlash.mclib.utils.resources.Pixels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;

@SideOnly(value=Side.CLIENT)
public class TextureProcessor {
    public static Pixels pixels = new Pixels();
    public static Pixels target = new Pixels();

    public static BufferedImage postProcess(MultiResourceLocation multi) {
        BufferedImage image = TextureProcessor.process(multi);
        return image;
    }

    public static BufferedImage process(MultiResourceLocation multi) {
        IResourceManager manager = Minecraft.func_71410_x().func_110442_L();
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        int w = 0;
        int h = 0;
        for (int i = 0; i < multi.children.size(); ++i) {
            FilteredResourceLocation child = multi.children.get(i);
            BufferedImage image = null;
            try {
                IResource resource = manager.func_110536_a(child.path);
                image = ImageIO.read(resource.func_110527_b());
                w = Math.max(w, child.getWidth(image.getWidth()));
                h = Math.max(h, child.getHeight(image.getHeight()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            images.add(image);
        }
        BufferedImage image = new BufferedImage(w, h, 2);
        Graphics g = image.getGraphics();
        for (int i = 0; i < multi.children.size(); ++i) {
            BufferedImage child = (BufferedImage)images.get(i);
            if (child == null) continue;
            FilteredResourceLocation filter = multi.children.get(i);
            int iw = child.getWidth();
            int ih = child.getHeight();
            if (filter.scaleToLargest) {
                iw = w;
                ih = h;
            } else if (filter.scale != 0.0f && filter.scale > 0.0f) {
                iw = (int)((float)iw * filter.scale);
                ih = (int)((float)ih * filter.scale);
            }
            if (iw <= 0 || ih <= 0) continue;
            if (filter.erase) {
                TextureProcessor.processErase(image, child, filter, iw, ih);
                continue;
            }
            if (filter.color != 0xFFFFFF || filter.pixelate > 1) {
                TextureProcessor.processImage(child, filter);
            }
            g.drawImage(child, filter.shiftX, filter.shiftY, iw, ih, null);
        }
        g.dispose();
        return image;
    }

    private static void processErase(BufferedImage image, BufferedImage child, FilteredResourceLocation filter, int iw, int ih) {
        BufferedImage mask = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics g2 = mask.getGraphics();
        g2.drawImage(child, filter.shiftX, filter.shiftY, iw, ih, null);
        g2.dispose();
        target.set(mask);
        pixels.set(image);
        int c = target.getCount();
        for (int p = 0; p < c; ++p) {
            Color pixel = target.getColor(p);
            if (!(pixel.a > 0.999f)) continue;
            pixel = pixels.getColor(p);
            pixel.a = 0.0f;
            pixels.setColor(p, pixel);
        }
    }

    private static void processImage(BufferedImage child, FilteredResourceLocation frl) {
        pixels.set(child);
        Color filter = new Color().set(frl.color);
        Color pixel = new Color();
        int c = pixels.getCount();
        for (int i = 0; i < c; ++i) {
            pixel.copy(pixels.getColor(i));
            if (pixels.hasAlpha() && pixel.a <= 0.0f) continue;
            if (frl.pixelate > 1) {
                int x = pixels.toX(i);
                int y = pixels.toY(i);
                boolean origin = x % frl.pixelate == 0 && y % frl.pixelate == 0;
                x -= x % frl.pixelate;
                y -= y % frl.pixelate;
                pixel.copy(pixels.getColor(x, y));
                pixels.setColor(i, pixel);
                if (!origin) continue;
            }
            pixel.r *= filter.r;
            pixel.g *= filter.g;
            pixel.b *= filter.b;
            pixel.a *= filter.a;
            pixels.setColor(i, pixel);
        }
    }
}

