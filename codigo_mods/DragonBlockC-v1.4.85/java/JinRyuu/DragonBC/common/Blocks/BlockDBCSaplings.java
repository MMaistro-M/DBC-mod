/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Worlds.WorldGenNamekTrees;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockDBCSaplings
extends BlockSapling {
    public static final String[] field_149882_a = new String[]{"Namek"};
    private static final IIcon[] field_149881_b = new IIcon[field_149882_a.length];

    protected BlockDBCSaplings() {
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
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        if ((p_149691_2_ &= 7) != 0) {
            return field_149881_b[0];
        }
        return field_149881_b[MathHelper.func_76125_a((int)p_149691_2_, (int)0, (int)5)];
    }

    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
        int l = p_149879_1_.func_72805_g(p_149879_2_, p_149879_3_, p_149879_4_);
        if ((l & 8) == 0) {
            p_149879_1_.func_72921_c(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        } else {
            this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public void func_149878_d(World p_149878_1_, int p_149878_2_, int p_149878_3_, int p_149878_4_, Random p_149878_5_) {
        if (!TerrainGen.saplingGrowTree((World)p_149878_1_, (Random)p_149878_5_, (int)p_149878_2_, (int)p_149878_3_, (int)p_149878_4_)) {
            return;
        }
        int l = p_149878_1_.func_72805_g(p_149878_2_, p_149878_3_, p_149878_4_) & 7;
        WorldGenNamekTrees object = p_149878_5_.nextInt(10) == 0 ? new WorldGenNamekTrees(true) : new WorldGenNamekTrees(true);
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        switch (l) {
            default: {
                object = new WorldGenNamekTrees(true);
            }
            case 1: 
        }
        Block block = Blocks.field_150350_a;
        if (flag) {
            p_149878_1_.func_147465_d(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, block, 0, 4);
            p_149878_1_.func_147465_d(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, block, 0, 4);
            p_149878_1_.func_147465_d(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, block, 0, 4);
            p_149878_1_.func_147465_d(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, block, 0, 4);
        } else {
            p_149878_1_.func_147465_d(p_149878_2_, p_149878_3_, p_149878_4_, block, 0, 4);
        }
        if (!((WorldGenerator)object).func_76484_a(p_149878_1_, p_149878_5_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1)) {
            if (flag) {
                p_149878_1_.func_147465_d(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, (Block)this, l, 4);
                p_149878_1_.func_147465_d(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, (Block)this, l, 4);
                p_149878_1_.func_147465_d(p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, (Block)this, l, 4);
                p_149878_1_.func_147465_d(p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, (Block)this, l, 4);
            } else {
                p_149878_1_.func_147465_d(p_149878_2_, p_149878_3_, p_149878_4_, (Block)this, l, 4);
            }
        }
    }

    public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_, int p_149880_5_) {
        return p_149880_1_.func_147439_a(p_149880_2_, p_149880_3_, p_149880_4_) == this && (p_149880_1_.func_72805_g(p_149880_2_, p_149880_3_, p_149880_4_) & 7) == p_149880_5_;
    }

    public int func_149692_a(int p_149692_1_) {
        return MathHelper.func_76125_a((int)(p_149692_1_ & 7), (int)0, (int)5);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < field_149881_b.length; ++i) {
            BlockDBCSaplings.field_149881_b[i] = par1IconRegister.func_94245_a("jinryuudragonbc:tile.Block" + field_149882_a[i] + "Sapling");
        }
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

    protected boolean func_149854_a(Block p_149854_1_) {
        return p_149854_1_ == BlocksDBC.BlockNamekGrass;
    }
}

