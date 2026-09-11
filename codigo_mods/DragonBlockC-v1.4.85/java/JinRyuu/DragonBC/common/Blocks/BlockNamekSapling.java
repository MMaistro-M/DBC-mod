/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Worlds.WorldGenNamekTrees;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockNamekSapling
extends BlockSapling {
    private IIcon BSide;
    private IIcon BTop;
    private IIcon BBottom;
    private String side = "ajisasapling";
    private String top = "namekgrasstop";
    private String bottom = "lightdirt";
    public static final String[] WOOD_TYPES = new String[]{"namek"};

    public BlockNamekSapling() {
        float f = 0.4f;
        this.func_149676_a(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        if (!p_149674_1_.field_72995_K) {
            super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
            if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_.nextInt(7) == 0) {
                this.func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.func_147439_a(par2, par3, par4) == this && (par1World.func_72805_g(par2, par3, par4) & 3) == par5;
    }

    protected boolean func_149854_a(Block p_149854_1_) {
        return p_149854_1_ == BlocksDBC.BlockNamekGrass;
    }

    protected boolean canThisPlantGrowOnThisBlockID(Block i) {
        return i == BlocksDBC.BlockNamekGrass;
    }

    public void func_149878_d(World world, int i, int j, int k, Random random) {
        if (!TerrainGen.saplingGrowTree((World)world, (Random)random, (int)i, (int)j, (int)k)) {
            return;
        }
        int l = world.func_72805_g(i, j, k) & 3;
        WorldGenNamekTrees obj = null;
        obj = new WorldGenNamekTrees(true);
        if (!obj.func_76484_a(world, random, i, j, k)) {
            // empty if block
        }
    }

    public int func_149692_a(int par1) {
        return par1 & 3;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
        int l = p_149879_1_.func_72805_g(p_149879_2_, p_149879_3_, p_149879_4_);
        if ((l & 8) == 0) {
            p_149879_1_.func_72921_c(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        } else {
            this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public int func_149645_b() {
        return 1;
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
        return (double)p_149852_1_.field_73012_v.nextFloat() < 0.45;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
        this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
}

