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
 *  net.minecraft.world.gen.feature.WorldGenBigTree
 *  net.minecraft.world.gen.feature.WorldGenTrees
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.m.WorldGenMahagonyTree;
import JinRyuu.DragonBC.common.m.WorldGenMapleTree;
import JinRyuu.DragonBC.common.m.WorldGenSakuraTree;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
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
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class MySapling
extends BlockSapling {
    public static final String[] saplings = new String[]{"Sakura", "Mahagony", "Maple"};
    private static final IIcon[] iconLength = new IIcon[saplings.length];

    public MySapling() {
        float f = 0.4f;
        this.func_149676_a(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
        this.func_149672_a(field_149779_h);
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K) {
            super.func_149674_a(world, x, y, z, random);
            if (world.func_72957_l(x, y + 1, z) >= 9 && random.nextInt(7) == 0) {
                this.func_149879_c(world, x, y, z, random);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return iconLength[MathHelper.func_76125_a((int)(meta &= 7), (int)0, (int)5)];
    }

    public void func_149879_c(World world, int x, int y, int z, Random random) {
        int l = world.func_72805_g(x, y, z);
        if ((l & 8) == 0) {
            world.func_72921_c(x, y, z, l | 8, 4);
        } else {
            this.func_149878_d(world, x, y, z, random);
        }
    }

    public void func_149878_d(World world, int x, int y, int z, Random random) {
        if (!TerrainGen.saplingGrowTree((World)world, (Random)random, (int)x, (int)y, (int)z)) {
            return;
        }
        int l = world.func_72805_g(x, y, z) & 7;
        Object object = random.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        switch (l) {
            case 0: {
                object = new WorldGenSakuraTree();
                break;
            }
            case 1: {
                object = new WorldGenMahagonyTree();
                break;
            }
            case 2: {
                object = new WorldGenMapleTree();
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
        }
        Block block = Blocks.field_150350_a;
        if (flag) {
            world.func_147465_d(x + i1, y, z + j1, block, 0, 4);
            world.func_147465_d(x + i1 + 1, y, z + j1, block, 0, 4);
            world.func_147465_d(x + i1, y, z + j1 + 1, block, 0, 4);
            world.func_147465_d(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        } else {
            world.func_147465_d(x, y, z, block, 0, 4);
        }
        if (!((WorldGenerator)object).func_76484_a(world, random, x + i1, y, z + j1)) {
            if (flag) {
                world.func_147465_d(x + i1, y, z + j1, (Block)this, l, 4);
                world.func_147465_d(x + i1 + 1, y, z + j1, (Block)this, l, 4);
                world.func_147465_d(x + i1, y, z + j1 + 1, (Block)this, l, 4);
                world.func_147465_d(x + i1 + 1, y, z + j1 + 1, (Block)this, l, 4);
            } else {
                world.func_147465_d(x, y, z, (Block)this, l, 4);
            }
        }
    }

    public boolean func_149880_a(World world, int x, int y, int z, int par1) {
        return world.func_147439_a(x, y, z) == this && (world.func_72805_g(x, y, z) & 7) == par1;
    }

    public int func_149692_a(int p_149692_1_) {
        return MathHelper.func_76125_a((int)(p_149692_1_ & 7), (int)0, (int)5);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < saplings.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        for (int i = 0; i < iconLength.length; ++i) {
            MySapling.iconLength[i] = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_149739_a().substring(5) + saplings[i]);
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
}

