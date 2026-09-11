/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package JinRyuu.DragonBC.common.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSakuraTree
extends WorldGenAbstractTree {
    public boolean b = false;

    public WorldGenSakuraTree() {
        super(false);
        this.b = false;
    }

    public WorldGenSakuraTree(boolean b) {
        super(false);
        this.b = b;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        block9: {
            int dec;
            int h;
            int branches;
            block11: {
                block10: {
                    Block block;
                    if (this.b) {
                        while (world.func_147437_c(x, y, z) && y > 2) {
                            --y;
                        }
                    } else {
                        --y;
                    }
                    if ((block = world.func_147439_a(x, y, z)) != Blocks.field_150349_c && block != Blocks.field_150346_d) {
                        return false;
                    }
                    if (world.func_147437_c(x, y, z) && !world.func_147437_c(x, y + 2, z)) {
                        return false;
                    }
                    int baselength = 3 + random.nextInt(4);
                    branches = 1;
                    h = 1;
                    block.onPlantGrow(world, x, y, z, x, y, z);
                    if (!world.func_72904_c(x - 8, y, z - 8, x + 10, y + 8, z + 8)) break block9;
                    for (int i = 0; i < baselength; ++i) {
                        this.buildBlock(world, x, y + h, z, ModdedBlocks.SakuraLogs, 0);
                        ++h;
                    }
                    dec = random.nextInt(3);
                    if (dec != 0) break block10;
                    int c = 1;
                    for (int i = 0; i < branches; ++i) {
                        this.generateTreeOne(world, random, x, y + h, z, c);
                        ++c;
                        h += 2;
                    }
                    break block9;
                }
                if (dec != 1) break block11;
                int c = 1;
                for (int i = 0; i < branches; ++i) {
                    this.generateTreeTwo(world, random, x, y + h, z, c);
                    ++c;
                    h += 2;
                }
                break block9;
            }
            if (dec != 2) break block9;
            int c = 1;
            for (int i = 0; i < branches; ++i) {
                this.generateTreeThree(world, random, x, y + h, z, c);
                ++c;
                h += 2;
            }
        }
        return true;
    }

    public void generateTreeTwo(World world, Random random, int x, int y, int z, int p) {
        for (int h = 0; h < 5; ++h) {
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    this.buildBlock(world, x + i, y + h, z + j, ModdedBlocks.SakuraLeaves, 0);
                }
            }
        }
        this.buildBlock(world, x, y, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 1, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 1, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 1, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 1, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 2, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 1, y + 2, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 2, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 2, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 2, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 3, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 3, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 3, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 4, z + 1, Blocks.field_150350_a, 0);
        this.buildBlock(world, x + 1, y + 4, z + 1, Blocks.field_150350_a, 0);
        this.buildBlock(world, x - 2, y + 4, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
    }

    public void generateTreeOne(World world, Random random, int x, int y, int z, int p) {
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                this.buildBlock(world, x + i, y, z + j, ModdedBlocks.SakuraLeaves, 0);
            }
        }
        this.buildBlock(world, x - 1, y, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 1, y, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 2, y + 1, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 2, y + 1, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 1, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 1, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 2, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
    }

    public void generateTreeThree(World world, Random random, int x, int y, int z, int p) {
        for (int h = 1; h < 8; ++h) {
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    this.buildBlock(world, x + i, y + h, z + j, ModdedBlocks.SakuraLeaves, 0);
                }
            }
        }
        for (int i = 0; i < 7; ++i) {
            this.buildBlock(world, x, y + i, z, ModdedBlocks.SakuraLogs, 0);
        }
        this.buildBlock(world, x + 1, y + 1, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 1, y + 2, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 1, y + 2, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 4, y + 2, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 3, y + 2, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 2, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 3, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 4, y + 3, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 2, y + 3, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 3, z - 3, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 3, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 2, y + 4, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 4, z - 3, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 4, y + 4, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 4, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 2, y + 4, z + 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 5, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x, y + 5, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 4, y + 5, z + 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 1, y + 5, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 5, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 5, z - 1, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 5, z - 2, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x + 3, y + 6, z, ModdedBlocks.SakuraLogs, 0);
        this.buildBlock(world, x - 3, y - 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y - 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y - 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y - 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y - 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 1, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 1, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 1, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 1, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 5, y + 1, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z - 4, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 4, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 2, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 2, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 2, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 5, y + 2, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 2, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 3, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 3, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 3, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 3, z - 4, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 3, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 3, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 3, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 3, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 5, y + 3, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 3, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 4, z + 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 4, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 4, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 4, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 4, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 4, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 4, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 3, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 4, z - 4, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 4, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 4, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 4, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 4, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 4, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 4, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 4, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 4, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 4, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 5, y + 4, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 4, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 5, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 5, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 5, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 5, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 5, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 5, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 5, z - 3, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 5, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 5, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 5, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 5, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 5, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 5, y + 5, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 5, z + 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 6, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 6, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 2, y + 6, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x - 1, y + 6, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 6, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 6, z - 2, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 6, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 6, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 6, z - 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 6, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 6, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 6, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 4, y + 6, z + 1, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 1, y + 7, z + 1, Blocks.field_150350_a, 0);
        this.buildBlock(world, x + 2, y + 7, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 3, y + 7, z, ModdedBlocks.SakuraLeaves, 0);
        this.buildBlock(world, x + 2, y + 7, z - 1, ModdedBlocks.SakuraLeaves, 0);
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.func_147437_c(x, y, z) || world.func_147439_a(x, y, z).isLeaves((IBlockAccess)world, x, y, z)) {
            this.func_150516_a(world, x, y, z, block, meta);
        }
    }
}

