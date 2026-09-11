/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.WorldGenBeerusTree;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BeerusPlanetBiome
extends BiomeDBCUtils {
    public BeerusPlanetBiome(int id) {
        super(id);
        this.func_76735_a("BeerusPlanet");
        this.field_76752_A = BlocksDBC.BlockNamekGrass;
        this.field_76753_B = BlocksDBC.BlockNamekDirt;
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76759_H = 7320274;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        this.generateCustomTrees(x, z, random, world, world.func_72807_a(x, z));
        super.func_76728_a(world, random, x, z);
    }

    private void generateCustomTrees(int chunkX, int chunkZ, Random random, World world, BiomeGenBase biome) {
        if (chunkZ < -100 || chunkX > 90 || chunkX < -90 || chunkZ > 100) {
            return;
        }
        for (int i = 0; i < 1; ++i) {
            Block groundBlock;
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = 0;
            if (Math.abs(x - 7) < 3 && Math.abs(z + 7) < 3 || Math.abs(x - 7) < 3 && Math.abs(z) < 3) break;
            for (int i1 = 100; i1 > 0; --i1) {
                if (world.func_147439_a(x, i1, z) == Blocks.field_150350_a) continue;
                y = i1;
                break;
            }
            if ((groundBlock = world.func_147439_a(x, y, z)) != BlocksDBC.BlockNamekGrass) continue;
            WorldGenBeerusTree treeGen = new WorldGenBeerusTree(true);
            treeGen.func_76484_a(world, random, x, y, z);
        }
    }

    public int func_150571_c(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        return 7320274;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int par1, int par2, int par3) {
        return 7320274;
    }

    public int func_76731_a(float p_76731_1_) {
        return super.func_76731_a(p_76731_1_);
    }
}

