/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class ReflectionUtils {
    public static Field TEXTURE_MAP;
    private static boolean SHADOW_PASS_CHECK;
    private static Field SHADOW_PASS;

    public static Map<ResourceLocation, ITextureObject> getTextures(TextureManager manager) {
        if (TEXTURE_MAP == null) {
            ReflectionUtils.setupTextureMapField(manager);
        }
        try {
            return (Map)TEXTURE_MAP.get(manager);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void setupTextureMapField(TextureManager manager) {
        for (Field field : manager.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(manager);
                if (!(value instanceof Map) || !(((Map)value).keySet().iterator().next() instanceof ResourceLocation)) continue;
                TEXTURE_MAP = field;
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean registerResourcePack(IResourcePack pack) {
        try {
            Field field = FMLClientHandler.class.getDeclaredField("resourcePackList");
            field.setAccessible(true);
            List packs = (List)field.get(FMLClientHandler.instance());
            packs.add(pack);
            IResourceManager manager = Minecraft.func_71410_x().func_110442_L();
            if (manager instanceof SimpleReloadableResourceManager) {
                ((SimpleReloadableResourceManager)manager).func_110545_a(pack);
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public static boolean isOptifineShadowPass() {
        if (!SHADOW_PASS_CHECK) {
            try {
                Class<?> clazz = Class.forName("net.optifine.shaders.Shaders");
                SHADOW_PASS = clazz.getDeclaredField("isShadowPass");
            }
            catch (Exception exception) {
                // empty catch block
            }
            SHADOW_PASS_CHECK = true;
        }
        if (SHADOW_PASS != null) {
            try {
                return (Boolean)SHADOW_PASS.get(null);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }
}

