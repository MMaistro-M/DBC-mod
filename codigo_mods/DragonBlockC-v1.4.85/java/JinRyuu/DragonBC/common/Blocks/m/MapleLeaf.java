/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MapleLeaf
extends BlockLeaves {
    public static final String[][] leaftypes = new String[][]{{"LeafMaple"}, {"LeafMapleOpaque"}};
    public static final String[] leaves = new String[]{"Maple"};

    public int func_149635_D() {
        double d0 = 0.5;
        double d1 = 1.0;
        return 0xFFFFFF;
    }

    public int func_149741_i(int p_149741_1_) {
        return 0xFFFFFF;
    }

    public int func_149692_a(int i) {
        return super.func_149692_a(i) + 2;
    }

    public int func_149643_k(World world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) & 3;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < leaves.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        for (int i = 0; i < leaftypes.length; ++i) {
            this.field_150129_M[i] = new IIcon[leaftypes[i].length];
            for (int j = 0; j < leaftypes[i].length; ++j) {
                this.field_150129_M[i][j] = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + leaftypes[i][j]);
            }
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        this.func_150122_b(Minecraft.func_71410_x().field_71474_y.field_74347_j);
        return (meta & 3) == 1 ? this.field_150129_M[this.field_150127_b][1] : this.field_150129_M[this.field_150127_b][0];
    }

    public String[] func_150125_e() {
        return leaves;
    }

    public boolean func_149646_a(IBlockAccess blockacces, int x, int y, int z, int side) {
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)ModdedBlocks.SakuraSaplings);
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 30;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 10;
    }

    public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return 0xFFFFFF;
    }
}

