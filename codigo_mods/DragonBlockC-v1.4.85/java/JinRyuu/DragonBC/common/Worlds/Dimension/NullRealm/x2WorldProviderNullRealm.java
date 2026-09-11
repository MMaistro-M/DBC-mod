/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package JinRyuu.DragonBC.common.Worlds.Dimension.NullRealm;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import JinRyuu.DragonBC.common.Worlds.Dimension.NullRealm.x2ChunkProviderNullRealm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class x2WorldProviderNullRealm
extends WorldProvider {
    public static final String name = "Null Realm";
    public static final int bgColorGreen = 2309901;
    public static final int bgColorPurple = 2427193;

    public int registerWorld() {
        return 24;
    }

    public boolean renderClouds() {
        return false;
    }

    public boolean renderEndSky() {
        return false;
    }

    public boolean renderVoidFog() {
        return true;
    }

    public float setMoonSize() {
        return 0.0f;
    }

    public float setSunSize() {
        return 0.0f;
    }

    public String getSunTexture() {
        return "jinryuudragonbc:sun.png";
    }

    public String getMoonTexture() {
        return "jinryuudragonbc:moon_phases.png";
    }

    public boolean renderStars() {
        return true;
    }

    public boolean darkenSkyDuringRain() {
        return false;
    }

    public String getRespawnMessage() {
        return " Leaving Null Realm";
    }

    public boolean hasMultipleBiomes() {
        return false;
    }

    public void func_76572_b() {
        this.field_76578_c = new WorldChunkManagerHell(BiomeGenBaseDBC.NR, 0.8f);
    }

    public IChunkProvider func_76555_c() {
        return new x2ChunkProviderNullRealm(this.field_76579_a, this.field_76579_a.func_72905_C(), false);
    }

    public boolean func_76567_e() {
        return true;
    }

    public float func_76563_a(long par1, float par3) {
        return 1.0f;
    }

    public String getSaveFolder() {
        return this.func_80007_l();
    }

    public String func_80007_l() {
        return name;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_76571_f() {
        return this.getCloudHeight_();
    }

    public int func_76557_i() {
        return this.getMinimumSpawnHeight(this.field_76579_a);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76564_j() {
        return this.hasVoidParticles(this.field_76576_e);
    }

    @SideOnly(value=Side.CLIENT)
    public double func_76565_k() {
        return this.voidFadeMagnitude();
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
        int i = x2WorldProviderNullRealm.getBGColor();
        float f3 = (float)(i >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(i & 0xFF) / 255.0f;
        return Vec3.func_72443_a((double)f3, (double)f4, (double)f5);
    }

    public double getHorizon() {
        return this.getHorizon(this.field_76579_a);
    }

    public int getMinimumSpawnHeight(World world) {
        return 4;
    }

    public float getCloudHeight_() {
        return 98.0f;
    }

    public double getHorizon(World world) {
        return 0.0;
    }

    public boolean hasVoidParticles(boolean flag) {
        return false;
    }

    public double voidFadeMagnitude() {
        return 1.0;
    }

    public boolean canDoLightning(Chunk chunk) {
        return true;
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return true;
    }

    public static int getBGColor() {
        if (DBCConfig.NullRealmBGColorNodeGreen) {
            return 2309901;
        }
        return 2427193;
    }
}

