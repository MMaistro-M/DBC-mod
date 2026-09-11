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

public class CapsuleCorp
extends WorldGenerator {
    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        if (!this.isSuitableLocation(world, x, y, z)) {
            return false;
        }
        int radius = 20;
        Block wallBlock = Blocks.field_150406_ce;
        int wallMeta = 4;
        Block glassBlock = Blocks.field_150359_w;
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dz = -radius; dz <= radius; ++dz) {
                for (int dy = 0; dy <= radius; ++dy) {
                    double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    if (!(distance <= (double)radius)) continue;
                    int blockY = y + dy;
                    int blockX = x + dx;
                    int blockZ = z + dz;
                    if (distance > (double)(radius - 2)) {
                        world.func_147465_d(blockX, blockY, blockZ, wallBlock, wallMeta, 2);
                        continue;
                    }
                    world.func_147468_f(blockX, blockY, blockZ);
                }
            }
        }
        this.addWindows(world, x, y, z, radius, glassBlock);
        this.addLogo(world, x, y + radius / 2, z, radius);
        return true;
    }

    private boolean isSuitableLocation(World world, int x, int y, int z) {
        return true;
    }

    private void addWindows(World world, int x, int y, int z, int radius, Block glassBlock) {
        int windowHeight = 4;
        for (int dy = 10; dy <= 20; dy += windowHeight + 2) {
            for (int angle = 0; angle < 360; angle += 30) {
                double radian = Math.toRadians(angle);
                int windowX = x + (int)((double)radius * 0.9 * Math.cos(radian));
                int windowZ = z + (int)((double)radius * 0.9 * Math.sin(radian));
                for (int wy = 0; wy < windowHeight; ++wy) {
                    world.func_147449_b(windowX, y + dy + wy, windowZ, glassBlock);
                }
            }
        }
    }

    private void addLogo(World world, int x, int y, int z, int radius) {
        Block textBlock = Blocks.field_150325_L;
        int blackMeta = 15;
        String logoText = "CAPSULE";
        int textHeight = 5;
        int textWidth = logoText.length() * 4;
        int startX = x - textWidth / 2;
        int startY = y;
        int startZ = z + radius - 2;
        for (int i = 0; i < logoText.length(); ++i) {
            char c = logoText.charAt(i);
            this.addCharacter(world, c, startX + i * 4, startY, startZ, textBlock, blackMeta, textHeight);
        }
    }

    private void addCharacter(World world, char c, int x, int y, int z, Block block, int meta, int height) {
        for (int dx = 0; dx < 3; ++dx) {
            for (int dy = 0; dy < height; ++dy) {
                if (dx != 0 && dx != 2 && dy != 0 && dy != height - 1) continue;
                world.func_147465_d(x + dx, y + dy, z, block, meta, 2);
            }
        }
    }
}

