/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockWood
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class MyWoodPlanks
extends BlockWood {
    public static final String[] planks = new String[]{"PlanksSakura", "PlanksMahagony", "PlanksMaple"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] field_150095_b;

    public MyWoodPlanks() {
        this.func_149647_a(mod_DragonBC.DragonBlockC);
        this.func_149711_c(2.0f);
        this.func_149752_b(2.0f);
        this.func_149672_a(field_149766_f);
        this.setHarvestLevel("axe", 0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        if (p_149691_2_ < 0 || p_149691_2_ >= this.field_150095_b.length) {
            p_149691_2_ = 0;
        }
        return this.field_150095_b[p_149691_2_];
    }

    public int func_149692_a(int p_149692_1_) {
        return p_149692_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < planks.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.field_150095_b = new IIcon[planks.length];
        for (int i = 0; i < this.field_150095_b.length; ++i) {
            this.field_150095_b[i] = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + planks[i]);
        }
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 40;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }
}

