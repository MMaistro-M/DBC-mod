/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package me.NBArmors.blocks;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import me.NBArmors.blocks.NBblocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class CoreGeneration
implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.field_73011_w.field_76574_g) {
            case 1: {
                this.genEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.genOverworld(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case -1: {
                this.genNether(world, random, chunkX * 16, chunkZ * 16);
            }
        }
    }

    private void genEnd(World world, Random rand, int x, int z) {
    }

    private void genOverworld(World world, Random rand, int x, int z) {
        this.addOreSpawn(NBblocks.blockCore, world, rand, x, z, 16, 16, 5, 6, 50, 128);
    }

    private void genNether(World world, Random rand, int x, int z) {
    }

    private void genAll(World world, Random rand, int x, int z) {
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVienSize, int chance, int minY, int maxY) {
        for (int i = 0; i < chance; ++i) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);
            new WorldGenMinable(block, maxVienSize + random.nextInt(2)).func_76484_a(world, random, posX, posY, posZ);
        }
    }

    public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
        int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
        int heightRange = maxY - minY;
        WorldGenMinable gen = new WorldGenMinable(block, vienSize, generateIn);
        for (int i = 0; i < chance; ++i) {
            int xrand = chunkX * 16 + random.nextInt(16);
            int yrand = random.nextInt(heightRange) + minY;
            int zrand = chunkZ * 16 + random.nextInt(16);
            gen.func_76484_a(world, random, xrand, yrand, zrand);
        }
    }
}

