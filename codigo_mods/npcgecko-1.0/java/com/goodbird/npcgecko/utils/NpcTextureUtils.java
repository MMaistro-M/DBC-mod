/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.SkinManager
 *  net.minecraft.util.ResourceLocation
 */
package com.goodbird.npcgecko.utils;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

public class NpcTextureUtils {
    public static ResourceLocation getNpcTexture(EntityNPCInterface npc) {
        if (npc.textureLocation == null) {
            if (npc.display.skinType == 0) {
                if (npc instanceof EntityCustomNpc && ((EntityCustomNpc)npc).modelData.entityClass == null) {
                    if (!npc.display.texture.isEmpty()) {
                        try {
                            npc.textureLocation = NpcTextureUtils.adjustLocalTexture(npc, new ResourceLocation(npc.display.texture));
                        }
                        catch (IOException iOException) {}
                    }
                } else {
                    npc.textureLocation = new ResourceLocation(npc.display.texture);
                }
            } else {
                if (RenderNPCInterface.LastTextureTick < 5L) {
                    return AbstractClientPlayer.field_110314_b;
                }
                if (npc.display.skinType == 1 && npc.display.playerProfile != null) {
                    Minecraft minecraft = Minecraft.func_71410_x();
                    Map map = minecraft.func_152342_ad().func_152788_a(npc.display.playerProfile);
                    if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                        npc.textureLocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
                    }
                    RenderNPCInterface.LastTextureTick = 0L;
                } else if (npc.display.skinType == 2 || npc.display.skinType == 3) {
                    try {
                        MessageDigest digest = MessageDigest.getInstance("MD5");
                        byte[] hash = digest.digest(npc.display.url.getBytes(StandardCharsets.UTF_8));
                        StringBuilder sb = new StringBuilder(2 * hash.length);
                        for (byte b : hash) {
                            sb.append(String.format("%02x", b & 0xFF));
                        }
                        if (npc.display.skinType == 2) {
                            npc.textureLocation = new ResourceLocation("skins/" + sb);
                            ClientCacheHandler.getNPCTexture(npc.display.url, false, npc.textureLocation);
                        } else {
                            npc.textureLocation = new ResourceLocation("skins64/" + sb);
                            ClientCacheHandler.getNPCTexture(npc.display.url, true, npc.textureLocation);
                        }
                        RenderNPCInterface.LastTextureTick = 0L;
                    }
                    catch (Exception exception) {}
                } else {
                    return AbstractClientPlayer.field_110314_b;
                }
            }
        }
        return npc.textureLocation;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static ResourceLocation adjustLocalTexture(EntityNPCInterface npc, ResourceLocation location) throws IOException {
        ResourceLocation var22;
        try (InputStream inputstream = null;){
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            texturemanager.func_147645_c(location);
            IResource iresource = Minecraft.func_71410_x().func_110442_L().func_110536_a(location);
            inputstream = iresource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            int totalWidth = bufferedimage.getWidth();
            int totalHeight = bufferedimage.getHeight();
            if (totalHeight > 32 && npc.display.modelType == 0) {
                bufferedimage = bufferedimage.getSubimage(0, 0, totalWidth, 32);
            }
            ImageDownloadAlt object = new ImageDownloadAlt(null, npc.display.texture, SkinManager.field_152793_a, (IImageBuffer)new ImageBufferDownloadAlt(false));
            object.setBufferedImage(bufferedimage);
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hash = digest.digest(npc.display.texture.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder(2 * hash.length);
                byte[] var13 = hash;
                int var14 = hash.length;
                for (int var15 = 0; var15 < var14; ++var15) {
                    byte b = var13[var15];
                    sb.append(String.format("%02x", b & 0xFF));
                }
                location = totalHeight > 32 && npc.display.modelType == 0 ? new ResourceLocation("skin/" + sb.toString()) : new ResourceLocation("skin64/" + sb.toString());
            }
            catch (Exception exception) {
                // empty catch block
            }
            texturemanager.func_110579_a(location, (ITextureObject)object);
            var22 = location;
        }
        return var22;
    }
}

