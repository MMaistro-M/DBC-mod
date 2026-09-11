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
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.NoiseGenerator
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraft.world.gen.feature.WorldGenLakes
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.ChunkProviderEvent$InitNoiseField
 *  net.minecraftforge.event.terraingen.ChunkProviderEvent$ReplaceBiomeBlocks
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Pre
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package com.tobiasmjc.dbcadditions.dimensions.chunkprovider;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.WorldGenBeerusBigTree;
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
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderBW
implements IChunkProvider {
    private Random endRNG;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    public NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    private World endWorld;
    private double[] densities;
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];
    private static final String __OBFID = "CL_00000397";
    private int OFFSET_X = 60;
    private int OFFSET_Z = 60;

    public ChunkProviderBW(World p_i2007_1_, long p_i2007_2_) {
        this.endWorld = p_i2007_1_;
        this.endRNG = new Random(p_i2007_2_);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);
        NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5};
        noiseGens = TerrainGen.getModdedNoiseGenerators((World)p_i2007_1_, (Random)this.endRNG, (NoiseGenerator[])noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorOctaves)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
    }

    public void func_147420_a(int p_147420_1_, int p_147420_2_, Block[] p_147420_3_, BiomeGenBase[] p_147420_4_) {
        int b0 = 2;
        int k = b0 + 1;
        int b1 = 33;
        int l = b0 + 1;
        this.densities = this.initializeNoiseField(this.densities, p_147420_1_ * b0, 0, p_147420_2_ * b0, k, b1, l);
        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 32; ++k1) {
                    double d0 = 0.25;
                    double d1 = this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
                    double d2 = this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
                    double d3 = this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
                    double d4 = this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
                    double d5 = (this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
                    double d6 = (this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
                    double d7 = (this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
                    double d8 = (this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;
                    for (int l1 = 0; l1 < 4; ++l1) {
                        double d9 = 0.125;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 8; ++i2) {
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            int short1 = 128;
                            double d14 = 0.125;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int k2 = 0; k2 < 8; ++k2) {
                                Block block = null;
                                if (d15 > 0.0) {
                                    block = Blocks.field_150348_b;
                                }
                                p_147420_3_[j2] = block;
                                j2 += short1;
                                d15 += d16;
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

    private void generateLakes(int chunkX, int chunkZ, Random random, World world) {
        if (Math.abs(chunkX) <= 1 && Math.abs(chunkZ) <= 1) {
            return;
        }
        for (int i = 0; i < 5; ++i) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(128);
            int z = chunkZ * 16 + random.nextInt(16);
            if (random.nextInt(4) != 0) continue;
            new WorldGenLakes(Blocks.field_150355_j).func_76484_a(world, random, x, y, z);
        }
    }

    public void func_147421_b(int p_147421_1_, int p_147421_2_, Block[] p_147421_3_, BiomeGenBase[] p_147421_4_) {
        this.replaceBiomeBlocks(p_147421_1_, p_147421_2_, p_147421_3_, p_147421_4_, new byte[p_147421_3_.length]);
    }

    public void replaceBiomeBlocks(int p_147421_1_, int p_147421_2_, Block[] p_147421_3_, BiomeGenBase[] p_147421_4_, byte[] meta) {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, p_147421_1_, p_147421_2_, p_147421_3_, meta, p_147421_4_, this.endWorld);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                int b0 = 1;
                int i1 = -1;
                Block block = BlocksDBC.BlockNamekGrass;
                Block block1 = BlocksDBC.BlockNamekDirt;
                for (int j1 = 127; j1 >= 0; --j1) {
                    int k1 = (l * 16 + k) * 128 + j1;
                    Block block2 = p_147421_3_[k1];
                    if (block2 != null && block2.func_149688_o() != Material.field_151579_a) {
                        if (block2 != Blocks.field_150348_b) continue;
                        if (i1 == -1) {
                            if (b0 <= 0) {
                                block = null;
                                block1 = BlocksDBC.BlockNamekGrass;
                            }
                            i1 = b0;
                            if (j1 >= 0) {
                                p_147421_3_[k1] = block;
                                continue;
                            }
                            p_147421_3_[k1] = block1;
                            continue;
                        }
                        if (i1 <= 0) continue;
                        --i1;
                        p_147421_3_[k1] = block1;
                        continue;
                    }
                    i1 = -1;
                }
            }
        }
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
        this.endRNG.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
        Block[] ablock = new Block[32768];
        byte[] meta = new byte[ablock.length];
        this.biomesForGeneration = this.endWorld.func_72959_q().func_76933_b(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        this.func_147420_a(p_73154_1_, p_73154_2_, ablock, this.biomesForGeneration);
        this.replaceBiomeBlocks(p_73154_1_, p_73154_2_, ablock, this.biomesForGeneration, meta);
        Chunk chunk = new Chunk(this.endWorld, ablock, meta, p_73154_1_, p_73154_2_);
        byte[] abyte = chunk.func_76605_m();
        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte)this.biomesForGeneration[k].field_76756_M;
        }
        chunk.func_76603_b();
        return chunk;
    }

    private double[] initializeNoiseField(double[] p_73187_1_, int p_73187_2_, int p_73187_3_, int p_73187_4_, int p_73187_5_, int p_73187_6_, int p_73187_7_) {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField((IChunkProvider)this, p_73187_1_, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return event.noisefield;
        }
        if (p_73187_1_ == null) {
            p_73187_1_ = new double[p_73187_5_ * p_73187_6_ * p_73187_7_];
        }
        double d0 = 684.412;
        double d1 = 684.412;
        this.noiseData4 = this.noiseGen4.func_76305_a(this.noiseData4, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 1.121, 1.121, 0.5);
        this.noiseData5 = this.noiseGen5.func_76305_a(this.noiseData5, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 200.0, 200.0, 0.5);
        this.noiseData1 = this.noiseGen3.func_76304_a(this.noiseData1, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, (d0 *= 2.0) / 80.0, d1 / 160.0, d0 / 80.0);
        this.noiseData2 = this.noiseGen1.func_76304_a(this.noiseData2, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
        this.noiseData3 = this.noiseGen2.func_76304_a(this.noiseData3, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        for (int i2 = 0; i2 < p_73187_5_; ++i2) {
            for (int j2 = 0; j2 < p_73187_7_; ++j2) {
                double d3;
                double d2 = (this.noiseData4[l1] + 256.0) / 512.0;
                if (d2 > 1.0) {
                    d2 = 1.0;
                }
                if ((d3 = this.noiseData5[l1] / 8000.0) < 0.0) {
                    d3 = -d3 * 0.3;
                }
                d3 = d3 * 3.0 - 2.0;
                float f = (float)(i2 + p_73187_2_ - 0) / 1.0f;
                float f1 = (float)(j2 + p_73187_4_ - 0) / 1.0f;
                float f2 = 100.0f - MathHelper.func_76129_c((float)(f * f + f1 * f1)) * 8.0f;
                if (f2 > 80.0f) {
                    f2 = 80.0f;
                }
                if (f2 < -100.0f) {
                    f2 = -100.0f;
                }
                if (d3 > 1.0) {
                    d3 = 1.0;
                }
                d3 /= 8.0;
                d3 = 0.0;
                if (d2 < 0.0) {
                    d2 = 0.0;
                }
                d2 += 0.5;
                d3 = d3 * (double)p_73187_6_ / 16.0;
                ++l1;
                double d4 = (double)p_73187_6_ / 2.0;
                for (int k2 = 0; k2 < p_73187_6_; ++k2) {
                    double d10;
                    double d5 = 0.0;
                    double d6 = ((double)k2 - d4) * 8.0 / d2;
                    if (d6 < 0.0) {
                        d6 *= -1.0;
                    }
                    double d7 = this.noiseData2[k1] / 512.0;
                    double d8 = this.noiseData3[k1] / 512.0;
                    double d9 = (this.noiseData1[k1] / 10.0 + 1.0) / 2.0;
                    d5 = d9 < 0.0 ? d7 : (d9 > 1.0 ? d8 : d7 + (d8 - d7) * d9);
                    d5 -= 8.0;
                    d5 += (double)f2;
                    int b0 = 2;
                    if (k2 > p_73187_6_ / 2 - b0) {
                        d10 = (float)(k2 - (p_73187_6_ / 2 - b0)) / 64.0f;
                        if (d10 < 0.0) {
                            d10 = 0.0;
                        }
                        if (d10 > 1.0) {
                            d10 = 1.0;
                        }
                        d5 = d5 * (1.0 - d10) + -3000.0 * d10;
                    }
                    if (k2 < (b0 = 8)) {
                        d10 = (float)(b0 - k2) / ((float)b0 - 1.0f);
                        d5 = d5 * (1.0 - d10) + -30.0 * d10;
                    }
                    p_73187_1_[k1] = d5;
                    ++k1;
                }
            }
        }
        return p_73187_1_;
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
        return true;
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int x, int z) {
        BlockFalling.field_149832_M = true;
        try {
            MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(p_73153_1_, this.endWorld, this.endWorld.field_73012_v, x, z, false));
            int k = x * 16;
            int l = z * 16;
            BiomeGenBase biomegenbase = this.endWorld.func_72807_a(k + 16, l + 16);
            biomegenbase.func_76728_a(this.endWorld, this.endWorld.field_73012_v, k, l);
            MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(p_73153_1_, this.endWorld, this.endWorld.field_73012_v, x, z, false));
            this.generateLakes(x, z, this.endRNG, this.endWorld);
            if (x == 0 && z == 0) {
                WorldGenBeerusBigTree beerusTree = new WorldGenBeerusBigTree(false);
                for (int i = this.endWorld.func_72800_K(); i > 0; --i) {
                    if (this.endWorld.func_147439_a(x, i, z) == Blocks.field_150350_a) continue;
                    beerusTree.func_76484_a(this.endWorld, this.endRNG, x, i, z);
                    break;
                }
            }
        }
        finally {
            BlockFalling.field_149832_M = false;
        }
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
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
        return "RandomLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        BiomeGenBase biomegenbase = this.endWorld.func_72807_a(p_73155_2_, p_73155_4_);
        return biomegenbase.func_76747_a(p_73155_1_);
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
