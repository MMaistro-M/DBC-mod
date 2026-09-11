/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.MapGenBase
 */
package JinRyuu.DragonBC.common.Worlds;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class x1MapGenRavineOW
extends MapGenBase {
    private float[] field_75046_d = new float[1024];
    private static final String __OBFID = "CL_00000390";

    protected void func_151540_a(long p_151540_1_, int p_151540_3_, int p_151540_4_, Block[] p_151540_5_, double p_151540_6_, double p_151540_8_, double p_151540_10_, float p_151540_12_, float p_151540_13_, float p_151540_14_, int p_151540_15_, int p_151540_16_, double p_151540_17_) {
        Random random = new Random(p_151540_1_);
        double d4 = p_151540_3_ * 16 + 8;
        double d5 = p_151540_4_ * 16 + 8;
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (p_151540_16_ <= 0) {
            int j1 = this.field_75040_a * 16 - 16;
            p_151540_16_ = j1 - random.nextInt(j1 / 4);
        }
        boolean flag1 = false;
        if (p_151540_15_ == -1) {
            p_151540_15_ = p_151540_16_ / 2;
            flag1 = true;
        }
        float f5 = 1.0f;
        for (int k1 = 0; k1 < 256; ++k1) {
            if (k1 == 0 || random.nextInt(3) == 0) {
                f5 = 1.0f + random.nextFloat() * random.nextFloat() * 1.0f;
            }
            this.field_75046_d[k1] = f5 * f5;
        }
        while (p_151540_15_ < p_151540_16_) {
            double d12 = 1.5 + (double)(MathHelper.func_76126_a((float)((float)p_151540_15_ * (float)Math.PI / (float)p_151540_16_)) * p_151540_12_ * 1.0f);
            double d6 = d12 * p_151540_17_;
            d12 *= (double)random.nextFloat() * 0.25 + 0.75;
            d6 *= (double)random.nextFloat() * 0.25 + 0.75;
            float f6 = MathHelper.func_76134_b((float)p_151540_14_);
            float f7 = MathHelper.func_76126_a((float)p_151540_14_);
            p_151540_6_ += (double)(MathHelper.func_76134_b((float)p_151540_13_) * f6);
            p_151540_8_ += (double)f7;
            p_151540_10_ += (double)(MathHelper.func_76126_a((float)p_151540_13_) * f6);
            p_151540_14_ *= 0.7f;
            p_151540_14_ += f4 * 0.05f;
            p_151540_13_ += f3 * 0.05f;
            f4 *= 0.8f;
            f3 *= 0.5f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (flag1 || random.nextInt(4) != 0) {
                double d7 = p_151540_6_ - d4;
                double d8 = p_151540_10_ - d5;
                double d9 = p_151540_16_ - p_151540_15_;
                double d10 = p_151540_12_ + 2.0f + 16.0f;
                if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10) {
                    return;
                }
                if (p_151540_6_ >= d4 - 16.0 - d12 * 2.0 && p_151540_10_ >= d5 - 16.0 - d12 * 2.0 && p_151540_6_ <= d4 + 16.0 + d12 * 2.0 && p_151540_10_ <= d5 + 16.0 + d12 * 2.0) {
                    int j3;
                    int k2;
                    int i4 = MathHelper.func_76128_c((double)(p_151540_6_ - d12)) - p_151540_3_ * 16 - 1;
                    int l1 = MathHelper.func_76128_c((double)(p_151540_6_ + d12)) - p_151540_3_ * 16 + 1;
                    int j4 = MathHelper.func_76128_c((double)(p_151540_8_ - d6)) - 1;
                    int i2 = MathHelper.func_76128_c((double)(p_151540_8_ + d6)) + 1;
                    int k4 = MathHelper.func_76128_c((double)(p_151540_10_ - d12)) - p_151540_4_ * 16 - 1;
                    int j2 = MathHelper.func_76128_c((double)(p_151540_10_ + d12)) - p_151540_4_ * 16 + 1;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    if (l1 > 16) {
                        l1 = 16;
                    }
                    if (j4 < 1) {
                        j4 = 1;
                    }
                    if (i2 > 248) {
                        i2 = 248;
                    }
                    if (k4 < 0) {
                        k4 = 0;
                    }
                    if (j2 > 16) {
                        j2 = 16;
                    }
                    boolean flag2 = false;
                    for (k2 = i4; !flag2 && k2 < l1; ++k2) {
                        for (int l2 = k4; !flag2 && l2 < j2; ++l2) {
                            for (int i3 = i2 + 1; !flag2 && i3 >= j4 - 1; --i3) {
                                j3 = (k2 * 16 + l2) * 256 + i3;
                                if (i3 < 0 || i3 >= 256) continue;
                                Block block = p_151540_5_[j3];
                                if (this.isOceanBlock(p_151540_5_, j3, k2, i3, l2, p_151540_3_, p_151540_4_)) {
                                    flag2 = true;
                                }
                                if (i3 == j4 - 1 || k2 == i4 || k2 == l1 - 1 || l2 == k4 || l2 == j2 - 1) continue;
                                i3 = j4;
                            }
                        }
                    }
                    if (!flag2) {
                        for (k2 = i4; k2 < l1; ++k2) {
                            double d13 = ((double)(k2 + p_151540_3_ * 16) + 0.5 - p_151540_6_) / d12;
                            for (j3 = k4; j3 < j2; ++j3) {
                                double d14 = ((double)(j3 + p_151540_4_ * 16) + 0.5 - p_151540_10_) / d12;
                                int k3 = (k2 * 16 + j3) * 256 + i2;
                                boolean flag = false;
                                if (!(d13 * d13 + d14 * d14 < 1.0)) continue;
                                for (int l3 = i2 - 1; l3 >= j4; --l3) {
                                    double d11 = ((double)l3 + 0.5 - p_151540_8_) / d6;
                                    if ((d13 * d13 + d14 * d14) * (double)this.field_75046_d[l3] + d11 * d11 / 6.0 < 1.0) {
                                        Block block1 = p_151540_5_[k3];
                                        if (this.isTopBlock(p_151540_5_, k3, k2, l3, j3, p_151540_3_, p_151540_4_)) {
                                            flag = true;
                                        }
                                        this.digBlock(p_151540_5_, k3, k2, l3, j3, p_151540_3_, p_151540_4_, flag);
                                    }
                                    --k3;
                                }
                            }
                        }
                        if (flag1) break;
                    }
                }
            }
            ++p_151540_15_;
        }
    }

    protected void func_151538_a(World p_151538_1_, int p_151538_2_, int p_151538_3_, int p_151538_4_, int p_151538_5_, Block[] p_151538_6_) {
        if (this.field_75038_b.nextInt(50) == 0) {
            double d0 = p_151538_2_ * 16 + this.field_75038_b.nextInt(16);
            double d1 = this.field_75038_b.nextInt(this.field_75038_b.nextInt(40) + 8) + 20;
            double d2 = p_151538_3_ * 16 + this.field_75038_b.nextInt(16);
            int b0 = 1;
            for (int i1 = 0; i1 < b0; ++i1) {
                float f = this.field_75038_b.nextFloat() * (float)Math.PI * 2.0f;
                float f1 = (this.field_75038_b.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float f2 = (this.field_75038_b.nextFloat() * 2.0f + this.field_75038_b.nextFloat()) * 2.0f;
                this.func_151540_a(this.field_75038_b.nextLong(), p_151538_4_, p_151538_5_, p_151538_6_, d0, d1, d2, f2, f, f1, 0, 0, 3.0);
            }
        }
    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return data[index] == Blocks.field_150355_j || data[index] == Blocks.field_150358_i;
    }

    private boolean isExceptionBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.field_76789_p) {
            return true;
        }
        if (biome == BiomeGenBase.field_76787_r) {
            return true;
        }
        return biome == BiomeGenBase.field_76769_d;
    }

    private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        BiomeGenBase biome = this.field_75039_c.func_72807_a(x + chunkX * 16, z + chunkZ * 16);
        return this.isExceptionBiome(biome) ? data[index] == Blocks.field_150349_c : data[index] == biome.field_76752_A;
    }

    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
        BiomeGenBase biome = this.field_75039_c.func_72807_a(x + chunkX * 16, z + chunkZ * 16);
        BlockGrass top = this.isExceptionBiome(biome) ? Blocks.field_150349_c : biome.field_76752_A;
        Block filler = this.isExceptionBiome(biome) ? Blocks.field_150346_d : biome.field_76753_B;
        Block block = data[index];
        if (block == Blocks.field_150348_b || block == Blocks.field_150346_d || block == filler || block == top) {
            if (y < 10) {
                data[index] = Blocks.field_150356_k;
            } else {
                data[index] = null;
                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }
}

