/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.tobiasmjc.dbcadditions.dimensions;

import com.tobiasmjc.dbcadditions.dimensions.chunkprovider.ChunkProviderBW;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class BeerusWorld
extends WorldProvider {
    public static final int dimensionID = 99;

    public boolean func_76564_j() {
        return false;
    }

    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    public float setSunSize() {
        return 0.1f;
    }

    public String getSunTexture() {
        return "jinryuudragonbc:sun.png";
    }

    public float func_76563_a(long worldTime, float partialTicks) {
        return 0.2f;
    }

    public String func_80007_l() {
        return "beerusplanet";
    }

    public boolean func_76567_e() {
        return false;
    }

    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return Vec3.func_72443_a((double)0.4, (double)0.05, (double)0.07);
    }

    public float func_76571_f() {
        return 5000.0f;
    }

    protected void func_76572_b() {
        this.field_76578_c = new WorldChunkManagerHell((BiomeGenBase)BiomeDBCUtils.BeerusPlanetBiome, (float)this.field_76574_g);
        this.field_76574_g = 99;
    }

    public IChunkProvider func_76555_c() {
        return new ChunkProviderBW(this.field_76579_a, this.field_76579_a.func_72905_C());
    }

    @SideOnly(value=Side.CLIENT)
    public String getWelcomeMessage() {
        return "Entering Beerus Planet";
    }

    @SideOnly(value=Side.CLIENT)
    public String getDepartMessage() {
        return "Leaving Beerus Planet";
    }

    public Vec3 drawClouds(float partialTicks) {
        return super.drawClouds(partialTicks);
    }

    public boolean func_76561_g() {
        return true;
    }

    public boolean isDaytime() {
        return true;
    }

    public float getSunBrightness(float par1) {
        return super.getSunBrightness(par1);
    }

    public float getStarBrightness(float par1) {
        return super.getStarBrightness(par1) * 3.0f;
    }

    public int func_76559_b(long p_76559_1_) {
        return 0;
    }
}

