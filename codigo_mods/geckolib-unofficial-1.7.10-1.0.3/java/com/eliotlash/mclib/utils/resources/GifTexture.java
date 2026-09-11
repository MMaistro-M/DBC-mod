/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.MathUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class GifTexture
extends AbstractTexture {
    public static int globalTick = 0;
    public static int entityTick = -1;
    public static boolean tried = false;
    public static Field fieldMultiTex = null;
    public ResourceLocation base;
    public ResourceLocation[] frames;
    public int[] delays;
    public int duration;

    public static void bindTexture(ResourceLocation location, int ticks, float partialTicks) {
        ITextureObject object;
        TextureManager textures = Minecraft.func_71410_x().field_71446_o;
        if (location.func_110623_a().endsWith("gif") && (object = textures.func_110581_b(location)) instanceof GifTexture) {
            GifTexture texture = (GifTexture)object;
            location = texture.getFrame(ticks, partialTicks);
        }
        textures.func_110577_a(location);
    }

    public static void updateTick() {
        ++globalTick;
    }

    public GifTexture(ResourceLocation texture, int[] delays, ResourceLocation[] frames) {
        this.base = texture;
        this.delays = Arrays.copyOf(delays, delays.length);
        this.frames = frames;
    }

    public void calculateDuration() {
        this.duration = 0;
        for (int delay : this.delays) {
            this.duration += delay;
        }
    }

    public void func_110551_a(IResourceManager resourceManager) throws IOException {
    }

    public int func_110552_b() {
        Minecraft mc = Minecraft.func_71410_x();
        TextureManager textures = mc.field_71446_o;
        ResourceLocation rl = this.getFrame(entityTick > -1 ? entityTick : globalTick, mc.field_71428_T.field_74281_c);
        textures.func_110577_a(rl);
        ITextureObject texture = textures.func_110581_b(rl);
        this.updateMultiTex(texture);
        return texture.func_110552_b();
    }

    public void func_147631_c() {
    }

    public ResourceLocation getFrame(int ticks, float partialTicks) {
        int tick = (int)(((float)ticks + partialTicks) * 5.0f % (float)this.duration);
        int duration = 0;
        int index = 0;
        for (int delay : this.delays) {
            if (tick < (duration += delay)) break;
            ++index;
        }
        index = MathUtils.clamp(index, 0, this.frames.length - 1);
        return this.frames[index];
    }

    private void updateMultiTex(ITextureObject texture) {
        if (!tried) {
            try {
                fieldMultiTex = AbstractTexture.class.getField("multiTex");
            }
            catch (NoSuchFieldException | SecurityException e) {
                fieldMultiTex = null;
            }
            tried = true;
        }
        if (texture instanceof AbstractTexture && fieldMultiTex != null) {
            try {
                Object obj = fieldMultiTex.get(texture);
                fieldMultiTex.set((Object)this, obj);
            }
            catch (IllegalAccessException | IllegalArgumentException exception) {
                // empty catch block
            }
        }
    }
}

