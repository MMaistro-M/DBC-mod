/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNamekTrees
extends WorldGenerator {
    private final int minTreeHeight;
    private final boolean growVines;
    private final int metaWood;
    private final int metaLeaves;

    public WorldGenNamekTrees(boolean par1) {
        this(par1, 8, 0, 0, false);
    }

    public WorldGenNamekTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
        this.growVines = par5;
    }

    public boolean func_76484_a(World world, Random rand, int i, int j, int k) {
        if (world.func_147437_c(i, j, k) && (world.func_147439_a(i, j - 1, k) == BlocksDBC.BlockNamekGrass || world.func_147439_a(i, j - 1, k) == BlocksDBC.BlockNamekDirt)) {
            this.setBlock(world, i + 0, j + 0, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i + 0, j + 1, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i + 0, j + 2, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i + 0, j + 3, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i + 0, j + 4, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i + 0, j + 5, k + 0, BlocksDBC.BlockNamekLog);
            this.setBlock(world, i - 1, j + 6, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 6, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 6, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 6, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 6, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 6, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 6, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 6, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 6, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 7, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 7, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 7, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 7, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 7, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 7, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 7, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 7, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 7, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 8, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 8, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i - 1, j + 8, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 8, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 8, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 0, j + 8, k - 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 8, k + 1, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 8, k + 0, BlocksDBC.BlockNamekLeaves);
            this.setBlock(world, i + 1, j + 8, k - 1, BlocksDBC.BlockNamekLeaves);
        }
        return true;
    }

    private void setBlock(World world, int i, int j, int k, Block b) {
        world.func_147449_b(i, j, k, b);
    }

    protected boolean func_150523_a(Block p_150523_1_) {
        return p_150523_1_.func_149688_o() == Material.field_151579_a || p_150523_1_.func_149688_o() == Material.field_151584_j || p_150523_1_ == BlocksDBC.BlockNamekGrass || p_150523_1_ == BlocksDBC.BlockNamekGrass || p_150523_1_ == BlocksDBC.BlockNamekLog || p_150523_1_ == BlocksDBC.BlockNamekLog || p_150523_1_ == Blocks.field_150345_g || p_150523_1_ == Blocks.field_150395_bd;
    }

    public void func_150524_b(World p_150524_1_, Random p_150524_2_, int p_150524_3_, int p_150524_4_, int p_150524_5_) {
    }

    protected boolean isReplaceable(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        return block.isAir((IBlockAccess)world, x, y, z) || block.isLeaves((IBlockAccess)world, x, y, z) || block.isWood((IBlockAccess)world, x, y, z) || this.func_150523_a(block);
    }
}

