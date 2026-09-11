/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 */
package me.NBArmors.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class FabricCoreColor
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] field_150920_d;
    private static final String __OBFID = "CL_00007112";

    public FabricCoreColor() {
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_) {
        int j = MathHelper.func_76125_a((int)p_77617_1_, (int)0, (int)15);
        return this.field_150920_d[j];
    }

    public String func_77667_c(ItemStack p_77667_1_) {
        int i = MathHelper.func_76125_a((int)p_77667_1_.func_77960_j(), (int)0, (int)15);
        return super.func_77658_a() + "." + ItemDye.field_150923_a[FabricCoreColor.func_150031_c(i)];
    }

    public static int func_150031_c(int p_150031_0_) {
        return ~p_150031_0_ & 0xF;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < 16; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_) {
        this.field_150920_d = new IIcon[ItemDye.field_150921_b.length];
        for (int i = 0; i < ItemDye.field_150921_b.length; ++i) {
            this.field_150920_d[i] = p_94581_1_.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", "") + "_" + ItemDye.field_150921_b[FabricCoreColor.func_150031_c(i)]);
        }
    }
}

