/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.JRMCore.blocks.BlocksJRMC;
import com.tobiasmjc.dbcadditions.blocks.BlocksDBCAdditions;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class UniversalArena {
    public void generate(World world, int x, int y, int z) {
        int j;
        int i;
        int platformSize = 50;
        int seatsDistance = 15;
        int ovalHeight = 40;
        for (i = -platformSize / 2; i <= platformSize / 2; ++i) {
            for (j = -platformSize / 2; j <= platformSize / 2; ++j) {
                world.setBlock(x + i, y, z + j, BlocksJRMC.BlockColoredStone);
            }
        }
        this.generateSeats(world, x - platformSize / 2 - seatsDistance, y, z, platformSize, "left");
        this.generateSeats(world, x + platformSize / 2 + seatsDistance, y, z, platformSize, "right");
        this.generateSeats(world, x, y, z - platformSize / 2 - seatsDistance, platformSize, "front");
        this.generateOval(world, x - platformSize - 15, y + ovalHeight, z, 10, 15, BlocksDBC.BlockKachiKachin[11]);
        this.generateOval(world, x + platformSize + 15, y + ovalHeight, z, 10, 15, BlocksDBC.BlockKachiKachin[14]);
        this.buildPixelArt(world, x + platformSize + 15, y + ovalHeight, z, this.generateChampaPixelArt(), false);
        this.buildPixelArt(world, x - platformSize - 15, y + ovalHeight, z, this.generateBeerusPixelArt(), true);
        for (i = -platformSize / 2 - 3; i <= platformSize / 2 + 3; ++i) {
            for (j = -platformSize / 2 - 3; j <= platformSize / 2 + 3; ++j) {
                for (int yi = -1; yi > -3; --yi) {
                    if (!world.isAirBlock(x + i, y + yi, z + j)) continue;
                    world.setBlock(x + i, y + yi, z + j, BlocksDBC.BlockKachiKachin[3]);
                }
            }
        }
        this.generateSphere(world, x, y, z, 100);
    }

    public void generateSphere(World world, int x, int y, int z, int radius) {
        int outerRadiusSquared = radius * radius;
        int innerRadiusSquared = (radius - 1) * (radius - 1);
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -2; dy <= radius; ++dy) {
                int horizontalDistanceSquared = outerRadiusSquared - dx * dx - dy * dy;
                if (horizontalDistanceSquared < 0) {
                    continue;
                }
                int outerZ = (int)Math.floor(Math.sqrt(horizontalDistanceSquared));
                int innerDistanceSquared = innerRadiusSquared - dx * dx - dy * dy;
                int innerZ = innerDistanceSquared < 0 ? -1 : (int)Math.floor(Math.sqrt(innerDistanceSquared));
                for (int dz = Math.max(0, innerZ + 1); dz <= outerZ; ++dz) {
                    world.setBlock(x + dx, y + dy, z + dz, (Block)BlocksDBCAdditions.MagicGlass);
                    if (dz > 0) {
                        world.setBlock(x + dx, y + dy, z - dz, (Block)BlocksDBCAdditions.MagicGlass);
                    }
                }
            }
        }
    }

    private void generateSeats(World world, int x, int y, int z, int ancho, String posicion) {
        int niveles = 5;
        int profundidad = 10;
        int alturaBase = 6;
        for (int nivel = 0; nivel < niveles; ++nivel) {
            int alturaActual = y + nivel + alturaBase;
            int inicio = nivel;
            for (int dx = -ancho / 2; dx <= ancho / 2; ++dx) {
                for (int dz = inicio; dz < profundidad; ++dz) {
                    if (posicion.equals("left")) {
                        world.setBlock(x - dz, alturaActual, z + dx, BlocksDBC.BlockKachiKachin[0]);
                        continue;
                    }
                    if (posicion.equals("right")) {
                        world.setBlock(x + dz, alturaActual, z + dx, BlocksDBC.BlockKachiKachin[0]);
                        continue;
                    }
                    if (!posicion.equals("front")) continue;
                    world.setBlock(x + dx, alturaActual, z - dz, BlocksDBC.BlockKachiKachin[0]);
                }
            }
        }
    }

    private void generateOval(World mundo, int x, int y, int z, int radioX, int radioY, Block material) {
        for (int i = -radioX; i <= radioX; ++i) {
            for (int j = -radioY; j <= radioY; ++j) {
                if (!((double)(i * i) / (double)(radioX * radioX) + (double)(j * j) / (double)(radioY * radioY) <= 1.0)) continue;
                mundo.setBlock(x, y + j, z + i, material);
            }
        }
    }

    public int[][] generateChampaPixelArt() {
        return new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 2}, {0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 1, 3, 3, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 3, 2, 2, 1, 1, 1, 1, 0, 0}, {1, 1, 3, 4, 2, 1, 1, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};
    }

    public int[][] generateBeerusPixelArt() {
        return new int[][]{{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {2, 2, 1, 0, 0, 0, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1}, {0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 3, 3, 1, 1, 1, 1, 0}, {0, 1, 1, 3, 2, 2, 1, 1, 1, 1, 0}, {1, 1, 3, 4, 2, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}};
    }

    private void buildPixelArt(World world, int x, int y, int z, int[][] pixelArt, boolean rotated) {
        Block[] colors = new Block[]{Blocks.air, BlocksDBC.BlockKachiKachin[10], BlocksDBC.BlockKachiKachin[4], BlocksDBC.BlockKachiKachin[15], BlocksDBC.BlockKachiKachin[7]};
        int multiplier = rotated ? -1 : 1;
        for (int i = 0; i < pixelArt.length; ++i) {
            for (int j = 0; j < pixelArt[i].length; ++j) {
                int color = pixelArt[i][j];
                if (color <= 0 || color >= colors.length || colors[color] == Blocks.air) continue;
                world.setBlock(x, y - i + pixelArt.length / 2, z + (j - pixelArt[i].length / 2) * multiplier, colors[color]);
            }
        }
    }
}
