/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.MapGenBase
 *  net.minecraft.world.gen.MapGenCaves
 *  net.minecraft.world.gen.MapGenRavine
 *  net.minecraft.world.gen.NoiseGenerator
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.ChunkProviderEvent$ReplaceBiomeBlocks
 *  net.minecraftforge.event.terraingen.InitMapGenEvent$EventType
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package com.tobiasmjc.dbcadditions.dimensions.chunkprovider;

import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderKai
implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private WorldType worldType;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] doubleArray1;
    double[] doubleArray2;
    double[] doubleArray3;
    double[] doubleArray4;
    int[][] field_73219_j = new int[32][32];

    public ChunkProviderKai(World par1World, long par2, boolean par4) {
        this.caveGenerator = TerrainGen.getModdedMapGen((MapGenBase)this.caveGenerator, (InitMapGenEvent.EventType)InitMapGenEvent.EventType.CAVE);
        this.ravineGenerator = TerrainGen.getModdedMapGen((MapGenBase)this.ravineGenerator, (InitMapGenEvent.EventType)InitMapGenEvent.EventType.RAVINE);
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.worldType = par1World.func_72912_H().func_76067_t();
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f;
                this.parabolicField[j + 2 + (k + 2) * 5] = f = 10.0f / MathHelper.func_76129_c((float)((float)(j * j + k * k) + 0.2f));
            }
        }
        NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators((World)par1World, (Random)this.rand, (NoiseGenerator[])noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    public void generateTerrain(int x, int z, Block[] par3BlockArray) {
        int b0 = 63;
        this.biomesForGeneration = this.worldObj.func_72959_q().func_76937_a(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateNoise(x * 4, 0, z * 4);
        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;
            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;
                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;
                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            int short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;
                            for (int k3 = 0; k3 < 4; ++k3) {
                                double d;
                                d15 += d16;
                                par3BlockArray[j3 += short1] = d > 0.0 ? Blocks.field_150348_b : (k2 * 8 + l2 < b0 - 1 ? Blocks.field_150355_j : null);
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int par1, int par2, Block[] blocks, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, par1, par2, blocks, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        double d0 = 0.03125;
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(par1 * 16), (double)(par2 * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeDBCUtils biomegenbase = (BiomeDBCUtils)par4ArrayOfBiomeGenBase[l + k * 16];
                this.genTerrainBlocks(biomegenbase, this.worldObj, this.rand, blocks, par3ArrayOfByte, par1 * 16 + k, par2 * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    public void genTerrainBlocks(BiomeDBCUtils biomegenbase, World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_) {
        this.genBiomeModdedTerrain(biomegenbase, p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    public void genBiomeModdedTerrain(BiomeDBCUtils bgb, World world, Random random, Block[] replacableBlock, byte[] aByte, int x, int y, double z) {
        Block block = bgb.field_76752_A;
        byte b0 = (byte)(bgb.field_150604_aj & 0xFF);
        Block block1 = bgb.field_76753_B;
        int k = -1;
        int l = (int)(z / 3.0 + 3.0 + random.nextDouble() * 0.25);
        int i1 = x & 0xF;
        int j1 = y & 0xF;
        int k1 = replacableBlock.length / 256;
        for (int l1 = 255; l1 >= 0; --l1) {
            int i2 = (j1 * 16 + i1) * k1 + l1;
            if (l1 <= 0 + random.nextInt(5)) {
                replacableBlock[i2] = Blocks.field_150357_h;
                continue;
            }
            Block block2 = replacableBlock[i2];
            if (block2 != null && block2.func_149688_o() != Material.field_151579_a) {
                if (block2 != Blocks.field_150348_b) continue;
                if (k == -1) {
                    if (l <= 0) {
                        block = null;
                        b0 = 0;
                        block1 = Blocks.field_150348_b;
                    } else if (l1 >= 59 && l1 <= 64) {
                        block = bgb.field_76752_A;
                        b0 = (byte)(bgb.field_150604_aj & 0xFF);
                        block1 = bgb.field_76753_B;
                    }
                    if (l1 < 63 && (block == null || block.func_149688_o() == Material.field_151579_a)) {
                        if (bgb.func_150564_a(x, l1, y) < 0.15f) {
                            block = Blocks.field_150432_aD;
                            b0 = 0;
                        } else {
                            block = Blocks.field_150355_j;
                            b0 = 0;
                        }
                    }
                    k = l;
                    if (l1 >= 61) {
                        replacableBlock[i2] = block;
                        aByte[i2] = b0;
                        continue;
                    }
                    if (l1 < 56 - l) {
                        block = null;
                        block1 = Blocks.field_150348_b;
                        replacableBlock[i2] = Blocks.field_150351_n;
                        continue;
                    }
                    replacableBlock[i2] = block1;
                    continue;
                }
                if (k <= 0) continue;
                replacableBlock[i2] = block1;
                if (--k != 0 || block1 != Blocks.field_150354_m) continue;
                k = random.nextInt(4) + Math.max(0, l1 - 63);
                block1 = Blocks.field_150322_A;
                continue;
            }
            k = -1;
        }
    }

    public Chunk func_73158_c(int x, int z) {
        return this.func_73154_d(x, z);
    }

    public Chunk func_73154_d(int par1, int par2) {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.generateTerrain(par1, par2, ablock);
        this.biomesForGeneration = this.worldObj.func_72959_q().func_76933_b(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ablock, abyte, this.biomesForGeneration);
        this.caveGenerator.func_151539_a((IChunkProvider)this, this.worldObj, par1, par2, ablock);
        this.ravineGenerator.func_151539_a((IChunkProvider)this, this.worldObj, par1, par2, ablock);
        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
        byte[] abyte1 = chunk.func_76605_m();
        for (int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte)this.biomesForGeneration[k].field_76756_M;
        }
        chunk.func_76603_b();
        return chunk;
    }

    private void generateNoise(int x, int y, int z) {
        this.doubleArray4 = this.noiseGen6.func_76305_a(this.doubleArray4, x, z, 5, 5, 200.0, 200.0, 0.5);
        this.doubleArray1 = this.noiseGen3.func_76304_a(this.doubleArray1, x, y, z, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.doubleArray2 = this.noiseGen1.func_76304_a(this.doubleArray2, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        this.doubleArray3 = this.noiseGen2.func_76304_a(this.doubleArray3, x, y, z, 5, 33, 5, 684.412, 684.412, 684.412);
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                int b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.field_76748_D;
                        float f4 = biomegenbase1.field_76749_E;
                        if (this.worldType == WorldType.field_151360_e && f3 > 0.0f) {
                            f3 = 1.0f + f3 * 2.0f;
                            f4 = 1.0f + f4 * 4.0f;
                        }
                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
                        if (biomegenbase1.field_76748_D > biomegenbase.field_76748_D) {
                            f5 /= 2.0f;
                        }
                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }
                f /= f2;
                f1 /= f2;
                f = f * 0.9f + 0.1f;
                f1 = (f1 * 4.0f - 1.0f) / 8.0f;
                double d13 = this.doubleArray4[i1] / 8000.0;
                if (d13 < 0.0) {
                    d13 = -d13 * 0.3;
                }
                if ((d13 = d13 * 3.0 - 2.0) < 0.0) {
                    if ((d13 /= 2.0) < -1.0) {
                        d13 = -1.0;
                    }
                    d13 /= 1.4;
                    d13 /= 2.0;
                } else {
                    if (d13 > 1.0) {
                        d13 = 1.0;
                    }
                    d13 /= 8.0;
                }
                ++i1;
                double d12 = f1;
                double d14 = f;
                d12 += d13 * 0.2;
                d12 = d12 * 8.5 / 8.0;
                double d5 = 8.5 + d12 * 4.0;
                for (int j2 = 0; j2 < 33; ++j2) {
                    double d6 = ((double)j2 - d5) * 12.0 * 128.0 / 256.0 / d14;
                    if (d6 < 0.0) {
                        d6 *= 4.0;
                    }
                    double d7 = this.doubleArray2[l] / 512.0;
                    double d8 = this.doubleArray3[l] / 512.0;
                    double d9 = (this.doubleArray1[l] / 10.0 + 1.0) / 2.0;
                    double d10 = MathHelper.func_151238_b((double)d7, (double)d8, (double)d9) - d6;
                    if (j2 > 29) {
                        double d11 = (float)(j2 - 29) / 3.0f;
                        d10 = d10 * (1.0 - d11) + -10.0 * d11;
                    }
                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }

    public boolean func_73149_a(int x, int z) {
        return true;
    }

    public void func_73153_a(IChunkProvider par1IChunkProvider, int x, int z) {
        BlockFalling.field_149832_M = true;
        try {
            int k = x * 16;
            int l = z * 16;
            BiomeGenBase biomegenbase = this.worldObj.func_72807_a(k + 16, l + 16);
            this.rand.setSeed(this.worldObj.func_72905_C());
            long i1 = this.rand.nextLong() / 2L * 2L + 1L;
            long j1 = this.rand.nextLong() / 2L * 2L + 1L;
            this.rand.setSeed((long)x * i1 + (long)z * j1 ^ this.worldObj.func_72905_C());
            boolean flag = false;
            biomegenbase.func_76728_a(this.worldObj, this.rand, k, l);
            MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(par1IChunkProvider, this.worldObj, this.rand, x, z, flag));
        }
        finally {
            BlockFalling.field_149832_M = false;
        }
    }

    public boolean func_73151_a(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void func_104112_b() {
    }

    public boolean func_73156_b() {
        return false;
    }

    public boolean func_73157_c() {
        return true;
    }

    public String func_73148_d() {
        return "ACLevelSource";
    }

    public List func_73155_a(EnumCreatureType par1EnumCreatureType, int x, int y, int z) {
        BiomeGenBase biome = this.worldObj.func_72807_a(x, z);
        return biome == null ? null : biome.func_76747_a(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public int func_73152_e() {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_) {
    }
}
