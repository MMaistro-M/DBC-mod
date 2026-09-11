/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Villages;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Npcs.EntityNamekian01;
import JinRyuu.DragonBC.common.Npcs.EntityNamekian03;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NamekianHouse1
extends WorldGenerator {
    public boolean func_76484_a(World world, Random rand, int i, int j, int k) {
        Block bID = BlocksDBC.BlockNamekGrass;
        if (world.func_147439_a(i, j, k) == bID && world.func_147439_a(i, j + 1, k).func_149688_o() == Material.field_151579_a && world.func_147439_a(i + 7, j, k) == bID && world.func_147439_a(i + 7, j, k + 7) == bID && world.func_147439_a(i, j, k + 7) == bID && world.func_147439_a(i + 7, j + 1, k).func_149688_o() == Material.field_151579_a && world.func_147439_a(i + 7, j + 1, k + 7).func_149688_o() == Material.field_151579_a && world.func_147439_a(i, j + 1, k + 7).func_149688_o() == Material.field_151579_a) {
            this.setBlock(world, i + 0, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 7, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 6, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 5, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 4, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 3, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 2, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 1, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 1, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 1, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 2, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 2, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 3, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 3, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 4, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 4, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 5, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 5, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 6, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 6, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 7, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 7, j + 0, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 8, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 7, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 6, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 5, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 4, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 3, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 2, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 1, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 8, j + 0, k + 0, BlocksDBC.BlockNamekGrass);
            this.setBlock(world, i + 0, j + 1, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 1, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 1, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 1, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 1, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 1, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 1, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 1, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 1, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 1, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 1, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 1, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 1, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 1, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 1, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 1, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 1, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 1, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 1, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 1, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 1, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 1, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 1, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 2, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 2, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 2, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 0, j + 2, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 2, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 2, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 2, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 2, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 2, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 2, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 2, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 2, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 2, k + 8, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 2, k + 0, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 2, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 2, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 2, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 2, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 2, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 8, j + 2, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 3, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 3, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 3, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 3, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 1, j + 3, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 3, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 3, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 3, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 3, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 3, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 3, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 3, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 3, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 3, k + 7, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 3, k + 1, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 3, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 3, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 3, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 3, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 7, j + 3, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 4, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 4, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 4, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 4, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 2, j + 4, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 4, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 4, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 4, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 4, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 4, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 4, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 4, k + 6, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 4, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 4, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 4, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 6, j + 4, k + 2, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 5, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 5, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 3, j + 5, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 5, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 5, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 5, k + 5, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 5, k + 4, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 5, j + 5, k + 3, BlocksDBC.BlockNamekStone);
            this.setBlock(world, i + 4, j + 5, k + 4, BlocksDBC.BlockNamekStone);
            EntityNamekian03 namekian03 = new EntityNamekian03(world);
            namekian03.initCreature();
            for (int t = 0; t < 3; ++t) {
                EntityNamekian01 namekian01 = new EntityNamekian01(world);
                namekian01.func_70012_b((double)i - 4.0 + (double)rand.nextInt(5) - (double)rand.nextInt(5), j + 2, (double)k - 4.0 + (double)rand.nextInt(5) - (double)rand.nextInt(5), 0.0f, 0.0f);
                world.func_72838_d((Entity)namekian01);
            }
            namekian03.func_70012_b((double)i + 4.0, j + 2, (double)k + 4.0, 0.0f, 0.0f);
            world.func_72838_d((Entity)namekian03);
        }
        return true;
    }

    private void setBlock(World world, int i, int j, int k, Block b) {
        this.func_150516_a(world, i, j, k, b, 0);
    }
}

