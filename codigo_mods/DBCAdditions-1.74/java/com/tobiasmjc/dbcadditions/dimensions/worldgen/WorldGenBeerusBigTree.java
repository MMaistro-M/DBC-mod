/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBeerusBigTree
extends WorldGenerator {
    World wo;
    Random rand;
    private int[][][] check;
    private boolean planted;
    private int baseY;
    int rootRand;
    int rootAlt;
    private Block cyprusWoodBlock;
    private Block cyprusLeafBlock;
    private Block cyprusBaseBlock1;
    private Block cyprusBaseBlock2;
    private int woodMeta;
    private int leafMeta;
    private int stuntmin;
    private int heightmin;
    private int heightmax;

    public WorldGenBeerusBigTree(boolean flag) {
        super(flag);
        this.planted = flag;
        this.check = new int[9][9][9];
        this.rootRand = 0;
        this.rootAlt = 0;
        this.cyprusWoodBlock = Blocks.field_150364_r;
        this.cyprusLeafBlock = Blocks.field_150350_a;
        this.cyprusBaseBlock1 = Blocks.field_150364_r;
        this.cyprusBaseBlock2 = Blocks.field_150364_r;
        this.woodMeta = 0;
        this.leafMeta = 0;
        this.heightmin = 90;
        this.heightmax = 110;
        this.stuntmin = 7;
    }

    void setConfigOptions(Block wood, Block leaf, int woodmeta, int leafmeta, Block Base1, Block Base2, int height1, int height2, int stunt) {
        this.cyprusWoodBlock = wood;
        this.cyprusLeafBlock = leaf;
        this.cyprusBaseBlock1 = Base1;
        this.cyprusBaseBlock2 = Base2;
        this.woodMeta = woodmeta;
        this.leafMeta = leafmeta;
        this.heightmin = height1;
        this.heightmax = height2;
        this.stuntmin = stunt;
    }

    void setBlock(int par1, int par2, int par3, Block par4) {
        try {
            this.wo.func_147465_d(par1, par2, par3, par4, 0, 3);
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    void setBlockAndMetadata(int par1, int par2, int par3, Block par4, int par5) {
        try {
            this.wo.func_147465_d(par1, par2, par3, par4, par5, 3);
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    Block getBlock(int par1, int par2, int par3) {
        try {
            return this.wo.func_147439_a(par1, par2, par3);
        }
        catch (RuntimeException e) {
            return null;
        }
    }

    public boolean func_76484_a(World world, Random random, int i, int j, int k) {
        int n;
        this.wo = world;
        this.rand = random;
        this.baseY = j;
        int l = (random.nextInt(this.heightmax - this.heightmin) + this.heightmin) / 2;
        if (j < 1) {
            return false;
        }
        if (j + l + 1 > 256 && (l = 256 - j - 2) < this.stuntmin) {
            return false;
        }
        double pitch = 1.6566370614359172;
        double dir = this.rand.nextFloat();
        double spin = 3.8830085198369844;
        double rootSlope = -1.0471975511965976;
        this.growRoot(i - 1, j += 4, k, 0.5625, rootSlope);
        this.growRoot(i - 1, j, k + 1, 0.4375, rootSlope);
        this.growRoot(i, j, k + 2, 0.3125, rootSlope);
        this.growRoot(i + 1, j, k + 2, 0.1875, rootSlope);
        this.growRoot(i + 2, j, k + 1, 0.0625, rootSlope);
        this.growRoot(i + 2, j, k, 0.9375, rootSlope);
        this.growRoot(i + 1, j, k - 1, 0.8125, rootSlope);
        this.growRoot(i, j, k - 1, 0.6875, rootSlope);
        this.growRoot(i - 2, j, k, 0.5625, rootSlope);
        this.growRoot(i - 2, j, k + 1, 0.4375, rootSlope);
        this.growRoot(i, j, k + 2, 0.3125, rootSlope);
        this.growRoot(i + 2, j, k, 0.1875, rootSlope);
        this.growRoot(i + 2, j, k + 2, 0.9375, rootSlope);
        this.growRoot(i + 2, j, k - 2, 0.9375, rootSlope);
        this.growRoot(i + 2, j, k - 1, 0.9375, rootSlope);
        this.growRoot(i - 2, j, k + 2, 0.9375, rootSlope);
        this.growRoot(i - 2, j, k - 2, 0.9375, rootSlope);
        this.growRoot(i, j, k + 2, 0.9375, rootSlope);
        this.growRoot(i, j, k - 2, 0.9375, rootSlope);
        this.growRoot(i - 2, j, k - 1, 0.9375, rootSlope);
        this.growRoot(i, j, k - 1, 0.6875, rootSlope);
        j -= 4;
        for (n = 0; n < l; ++n) {
            if (n < l - 2) {
                this.setBlockAndMetadata(i, j + n, k, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
            } else {
                this.setBlockAndMetadata(i, j + n, k, this.cyprusLeafBlock, this.leafMeta);
                this.setBlockAndMetadata(i + 1, j + n, k, this.cyprusLeafBlock, this.leafMeta);
                this.setBlockAndMetadata(i + 1, j + n, k + 1, this.cyprusLeafBlock, this.leafMeta);
                this.setBlockAndMetadata(i, j + n, k + 1, this.cyprusLeafBlock, this.leafMeta);
            }
            if (n <= 8) {
                this.setBlockAndMetadata(i, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 2, j + n, k, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 2, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i - 1, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i - 1, j + n, k, this.cyprusWoodBlock, this.woodMeta);
            }
            if (n <= 3) {
                this.setBlockAndMetadata(i - 1, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i - 1, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 2, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 2, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
            }
            if (n <= 1) {
                this.setBlockAndMetadata(i, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 1, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 3, j + n, k, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i + 3, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i - 2, j + n, k, this.cyprusWoodBlock, this.woodMeta);
                this.setBlockAndMetadata(i - 2, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
            }
            if (n != 0) continue;
            this.setBlockAndMetadata(i - 2, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i - 1, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i - 2, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i - 1, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i + 3, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i + 2, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i + 3, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
            this.setBlockAndMetadata(i + 2, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
        }
        for (n = 5; n < l - 2; ++n) {
            if (this.rand.nextInt(l + 10) < n) {
                this.growBranch(i, j + n, k, (double)(l + n) * 0.318, dir, pitch, 0.0, 0.0, 0);
            }
            dir += spin;
        }
        return true;
    }

    private void growBranch(int i, int j, int k, double len, double dir, double pitch, double pbias, double pbias2, int size) {
        double dx = 0.0;
        double dy = 0.0;
        double dz = 0.0;
        double spin = 0.0;
        double heave = 0.0;
        double blen = len * 0.75;
        this.plotWood(i, j, k, size);
        while (len > 1.0) {
            dy += Math.sin(pitch);
            double dd = Math.cos(pitch);
            dx += Math.cos(dir) * dd;
            dz += Math.sin(dir) * dd;
            boolean step = false;
            if (dx >= 1.0) {
                ++i;
                dx -= 1.0;
                step = true;
            } else if (dx <= -1.0) {
                --i;
                dx += 1.0;
                step = true;
            }
            if (dy >= 1.0) {
                ++j;
                dy -= 1.0;
                step = true;
            } else if (dy <= -1.0) {
                --j;
                dy += 1.0;
                step = true;
            }
            if (dz >= 1.0) {
                ++k;
                dz -= 1.0;
                step = true;
            } else if (dz <= -1.0) {
                --k;
                dz += 1.0;
                step = true;
            }
            if (step) {
                Block id = this.getBlock(i, j, k);
                if (id != Blocks.field_150350_a && id != this.cyprusWoodBlock && id != this.cyprusLeafBlock) break;
                this.plotWood(i, j, k, size);
            }
            if (len > 2.0 && len < blen) {
                int id1 = size == 0 ? this.rand.nextInt(8) : 0;
                if (id1 == 1) {
                    this.growBranch(i, j, k, len, dir + 0.7853981633974483, 0.0, 0.0, 0.0, 1);
                }
                if (id1 == 2) {
                    this.growBranch(i, j, k, len, dir - 0.7853981633974483, 0.0, 0.0, 0.0, 1);
                }
            }
            heave += (double)this.rand.nextFloat() * 0.1 - 0.05;
            dir += (spin += (double)this.rand.nextFloat() * 0.1 - 0.05);
            heave = pitch > pbias ? (heave -= 0.01) : (heave += 0.01);
            if (heave > 0.2) {
                heave = 0.2;
            }
            if (heave < -0.2) {
                heave = -0.2;
            }
            pitch += heave;
            pitch = (pitch - pbias2) * 0.8 + pbias2;
            len -= 1.0;
        }
        this.treeLeaf(i, j, k, 3);
    }

    private void plotWood(int i, int j, int k, int size) {
        this.setBlockAndMetadata(i, j, k, this.cyprusWoodBlock, this.woodMeta);
    }

    void treeLeaf(int i, int j, int k, int r) {
        if (r <= 0) {
            return;
        }
        int rr = r * r + 1;
        for (int ii = -r; ii <= r; ++ii) {
            for (int jj = 0; jj <= 1; ++jj) {
                for (int kk = -r; kk <= r; ++kk) {
                    if (ii * ii + jj * jj + kk * kk > rr || this.getBlock(i + ii, j + jj, k + kk) != Blocks.field_150350_a) continue;
                    this.setBlockAndMetadata(i + ii, j + jj, k + kk, this.cyprusLeafBlock, this.leafMeta);
                }
            }
        }
    }

    private int getMedium(int i, int j, int k) {
        int m;
        Block[] canGrowOpen = new Block[]{Blocks.field_150350_a, Blocks.field_150345_g, Blocks.field_150358_i, Blocks.field_150355_j, Blocks.field_150356_k, Blocks.field_150353_l, Blocks.field_150364_r, Blocks.field_150363_s, Blocks.field_150362_t, Blocks.field_150361_u};
        Block[] canGrowSolid = new Block[]{Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n};
        Block qq = this.getBlock(i, j, k);
        int medium = 0;
        for (m = 0; m < canGrowOpen.length; ++m) {
            if (qq != canGrowOpen[m]) continue;
            medium = 1;
            break;
        }
        if (medium == 0) {
            for (m = 0; m < canGrowSolid.length; ++m) {
                if (qq != canGrowSolid[m]) continue;
                medium = 2;
                break;
            }
        }
        return medium;
    }

    void growRoot(int l, int m, int n, double theta, double phi) {
        if (this.rootAlt == 1) {
            this.rootRand = this.rand.nextInt(2);
            m -= this.rootRand;
            this.rootAlt = 2;
        } else if (this.rootAlt == 2) {
            if (this.rootRand == 0) {
                --m;
            }
            this.rootAlt = 0;
        } else if (this.rootAlt == 10) {
            m -= this.rand.nextInt(2);
        }
        ++m;
        double direction = Math.PI * 2 * (theta += (double)this.rand.nextFloat() * 0.1 - 0.05);
        double curl = this.rand.nextFloat() * 0.4f - 0.2f;
        double pitch = Math.PI * 2 * (phi -= (double)this.rand.nextFloat() * 0.05);
        int length = 20 + this.rand.nextInt(4);
        double x = l > 0 ? (double)l + 0.5 : (double)l - 0.5;
        double y = (double)m + 0.5;
        double z = n > 0 ? (double)n + 0.5 : (double)n - 0.5;
        int i = (int)x;
        int j = (int)y;
        int k = (int)z;
        int med = this.getMedium(i, j, k);
        int cnt = 0;
        while ((double)length > 0.0) {
            --length;
            curl = curl + (double)(this.rand.nextFloat() * 0.06f) - (double)0.03f;
            boolean dug = !(pitch < 0.0);
            pitch = med == 1 ? (pitch + 1.5707963267948966) * 0.7 - 1.5707963267948966 : (pitch - 1.5707963267948966) * 0.7 + 1.5707963267948966;
            double hoz = Math.cos(pitch);
            double x2 = x + Math.cos(direction) * hoz;
            double y2 = y + Math.sin(pitch);
            double z2 = z + Math.sin(direction) * hoz;
            int i2 = (int)x2;
            int j2 = (int)y2;
            int k2 = (int)z2;
            if (i2 == i && j2 == j && k2 == k) continue;
            this.setBlockAndMetadata(i, j, k, this.cyprusWoodBlock, this.woodMeta);
            if (dug) {
                if (this.getBlock(i - 1, j, k) == Blocks.field_150350_a) {
                    return;
                }
                if (this.getBlock(i + 1, j, k) == Blocks.field_150350_a) {
                    return;
                }
                if (this.getBlock(i, j, k - 1) == Blocks.field_150350_a) {
                    return;
                }
                if (this.getBlock(i, j, k + 1) == Blocks.field_150350_a) {
                    return;
                }
            }
            if (++cnt < 4 && (j2 != j - 1 || i2 != i || k2 != k)) {
                this.setBlockAndMetadata(i, j - 1, k, this.cyprusWoodBlock, this.woodMeta);
            }
            if ((med = this.getMedium(i2, j2, k2)) != 0) {
                x = x2;
                y = y2;
                z = z2;
                i = i2;
                j = j2;
                k = k2;
                continue;
            }
            med = this.getMedium(i, j - 1, k);
            if (med != 0) {
                y -= 1.0;
                --j;
                pitch = -1.5707963267948966;
                continue;
            }
            x2 = x + Math.cos(direction);
            i2 = (int)x2;
            med = this.getMedium(i2, j, k2 = (int)(z2 = z + Math.sin(direction)));
            if (med != 0) {
                x = x2;
                z = z2;
                i = i2;
                k = k2;
                pitch = 0.0;
                continue;
            }
            int dir = (int)(direction * 8.0 / Math.PI);
            dir = dir < 0 ? 15 - (15 - dir) % 16 : (dir %= 16);
            int pol = dir % 2;
            int di = i2 - i;
            int dk = k2 - k;
            int[] tdir = new int[]{0, 0, 0, 0};
            if (di == 0 && dk == 0) {
                if (dir < 1) {
                    di = 1;
                    dk = 0;
                } else if (dir < 3) {
                    di = 1;
                    dk = 1;
                } else if (dir < 5) {
                    di = 0;
                    dk = 1;
                } else if (dir < 7) {
                    di = -1;
                    dk = 1;
                } else if (dir < 9) {
                    di = -1;
                    dk = 0;
                } else if (dir < 11) {
                    di = -1;
                    dk = -1;
                } else if (dir < 13) {
                    di = 0;
                    dk = -1;
                } else if (dir < 15) {
                    di = 1;
                    dk = -1;
                } else {
                    di = 1;
                    dk = 0;
                }
            }
            if (dk == 0) {
                if (di > 0) {
                    if (pol == 1) {
                        tdir[0] = 2;
                        tdir[1] = 14;
                        tdir[2] = 4;
                        tdir[3] = 12;
                    } else {
                        tdir[0] = 14;
                        tdir[1] = 2;
                        tdir[2] = 12;
                        tdir[3] = 4;
                    }
                } else if (pol == 1) {
                    tdir[0] = 6;
                    tdir[1] = 10;
                    tdir[2] = 4;
                    tdir[3] = 12;
                } else {
                    tdir[0] = 10;
                    tdir[1] = 6;
                    tdir[2] = 12;
                    tdir[3] = 4;
                }
            } else if (di == 0) {
                if (dk > 0) {
                    if (pol == 1) {
                        tdir[0] = 2;
                        tdir[1] = 6;
                        tdir[2] = 0;
                        tdir[3] = 8;
                    } else {
                        tdir[0] = 6;
                        tdir[1] = 2;
                        tdir[2] = 8;
                        tdir[3] = 0;
                    }
                } else if (pol == 1) {
                    tdir[0] = 10;
                    tdir[1] = 14;
                    tdir[2] = 8;
                    tdir[3] = 0;
                } else {
                    tdir[0] = 14;
                    tdir[1] = 10;
                    tdir[2] = 0;
                    tdir[3] = 8;
                }
            } else if (dk > 0) {
                if (di > 0) {
                    if (pol == 1) {
                        tdir[0] = 0;
                        tdir[1] = 4;
                        tdir[2] = 14;
                        tdir[3] = 6;
                    } else {
                        tdir[0] = 4;
                        tdir[1] = 0;
                        tdir[2] = 6;
                        tdir[3] = 14;
                    }
                } else if (pol == 1) {
                    tdir[0] = 4;
                    tdir[1] = 8;
                    tdir[2] = 2;
                    tdir[3] = 10;
                } else {
                    tdir[0] = 8;
                    tdir[1] = 4;
                    tdir[2] = 10;
                    tdir[3] = 2;
                }
            } else if (di > 0) {
                if (pol == 1) {
                    tdir[0] = 12;
                    tdir[1] = 0;
                    tdir[2] = 10;
                    tdir[3] = 2;
                } else {
                    tdir[0] = 0;
                    tdir[1] = 12;
                    tdir[2] = 2;
                    tdir[3] = 10;
                }
            } else if (pol == 1) {
                tdir[0] = 8;
                tdir[1] = 12;
                tdir[2] = 6;
                tdir[3] = 14;
            } else {
                tdir[0] = 12;
                tdir[1] = 8;
                tdir[2] = 14;
                tdir[3] = 6;
            }
            for (int q = 0; q < 4; ++q) {
                if (tdir[q] == 0) {
                    di = 1;
                    dk = 0;
                } else if (tdir[q] == 2) {
                    di = 1;
                    dk = 1;
                } else if (tdir[q] == 4) {
                    di = 0;
                    dk = 1;
                } else if (tdir[q] == 6) {
                    di = -1;
                    dk = 1;
                } else if (tdir[q] == 8) {
                    di = -1;
                    dk = 0;
                } else if (tdir[q] == 10) {
                    di = -1;
                    dk = -1;
                } else if (tdir[q] == 12) {
                    di = 0;
                    dk = -1;
                } else {
                    di = 1;
                    dk = -1;
                }
                i2 = i + di;
                k2 = k + dk;
                med = this.getMedium(i2, j, k2);
                if (med == 0) continue;
                i = i2;
                k = k2;
                x = (double)i + 0.5;
                z = (double)k + 0.5;
                pitch = 0.0;
                direction = (double)tdir[q] * 2.0 * Math.PI / 16.0;
                break;
            }
            if (med != 0) continue;
            return;
        }
    }
}

