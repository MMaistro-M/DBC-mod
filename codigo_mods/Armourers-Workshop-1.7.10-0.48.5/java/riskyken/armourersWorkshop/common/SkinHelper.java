/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package riskyken.armourersWorkshop.common;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public final class SkinHelper {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage getBufferedImageSkin(AbstractClientPlayer player) {
        BufferedImage bufferedImage = null;
        ResourceLocation skinloc = AbstractClientPlayer.field_110314_b;
        InputStream inputStream = null;
        Minecraft mc = Minecraft.func_71410_x();
        skinloc = player.func_110306_p();
        try {
            ITextureObject skintex = mc.func_110434_K().func_110581_b(skinloc);
            if (skintex instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData imageData = (ThreadDownloadImageData)skintex;
                bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)imageData, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(skinloc).func_110527_b();
                bufferedImage = ImageIO.read(inputStream);
            }
            IOUtils.closeQuietly((InputStream)inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
        return bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage getBufferedImageSkin(ResourceLocation resourceLocation) {
        Minecraft mc = Minecraft.func_71410_x();
        BufferedImage bufferedImage = null;
        InputStream inputStream = null;
        try {
            ITextureObject skintex = mc.func_110434_K().func_110581_b(resourceLocation);
            if (skintex instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData imageData = (ThreadDownloadImageData)skintex;
                bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)imageData, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(resourceLocation).func_110527_b();
                bufferedImage = ImageIO.read(inputStream);
            }
            IOUtils.closeQuietly((InputStream)inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
        return bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage getBufferedImageSkin(GameProfile gameProfile) {
        BufferedImage bufferedImage = null;
        ResourceLocation skinloc = AbstractClientPlayer.field_110314_b;
        InputStream inputStream = null;
        Minecraft mc = Minecraft.func_71410_x();
        Map map = mc.func_152342_ad().func_152788_a(gameProfile);
        try {
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                skinloc = mc.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
                ITextureObject skintex = mc.func_110434_K().func_110581_b(skinloc);
                if (skintex instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData imageData = (ThreadDownloadImageData)skintex;
                    bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)imageData, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
                } else {
                    inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(skinloc).func_110527_b();
                    bufferedImage = ImageIO.read(inputStream);
                }
            } else {
                inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(skinloc).func_110527_b();
                bufferedImage = ImageIO.read(inputStream);
            }
            IOUtils.closeQuietly((InputStream)inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
        return bufferedImage;
    }

    public static BufferedImage getBufferedImageSkinNew(GameProfile gameProfile) {
        BufferedImage bufferedImage = null;
        ResourceLocation rl = AbstractClientPlayer.field_110314_b;
        if (gameProfile != null) {
            rl = AbstractClientPlayer.func_110311_f((String)gameProfile.getName());
            AbstractClientPlayer.func_110304_a((ResourceLocation)rl, (String)gameProfile.getName());
        }
        if ((bufferedImage = SkinHelper.getBuffFromResourceLocation(rl)) == null) {
            bufferedImage = SkinHelper.getBuffFromResourceLocation(AbstractClientPlayer.field_110314_b);
        }
        return bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static BufferedImage getBuffFromResourceLocation(ResourceLocation rl) {
        BufferedImage bi = null;
        InputStream inputStream = null;
        try {
            ITextureObject skintex = Minecraft.func_71410_x().func_110434_K().func_110581_b(rl);
            if (skintex instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData imageData = (ThreadDownloadImageData)skintex;
                bi = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)imageData, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                inputStream = Minecraft.func_71410_x().func_110442_L().func_110536_a(rl).func_110527_b();
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
        return bi;
    }

    public static void bindPlayersNormalSkin(GameProfile gameProfile) {
        ResourceLocation resourcelocation = AbstractClientPlayer.field_110314_b;
        if (gameProfile != null) {
            resourcelocation = SkinHelper.getSkinResourceLocation(gameProfile, MinecraftProfileTexture.Type.SKIN);
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(resourcelocation);
    }

    public static ResourceLocation getSkinResourceLocation(GameProfile gameProfile, MinecraftProfileTexture.Type type) {
        Minecraft mc;
        Map map;
        ResourceLocation skin = AbstractClientPlayer.field_110314_b;
        if (gameProfile != null && (map = (mc = Minecraft.func_71410_x()).func_152342_ad().func_152788_a(gameProfile)).containsKey(type)) {
            skin = mc.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(type), type);
        }
        return skin;
    }

    public static BufferedImage deepCopyBufferedImage(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}

