/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class BlockCrystal
extends BlockBreakable {
    public BlockCrystal() {
        super("customnpcs:npcCrystal", Material.field_151592_s, false);
        this.func_149715_a(0.8f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149701_w() {
        return 1;
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 16; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        return this.func_149741_i(world.func_72805_g(x, y, z));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int meta) {
        return MapColor.func_151644_a((int)meta).field_76291_p;
    }

    public MapColor func_149728_f(int p_149728_1_) {
        return MapColor.func_151644_a((int)p_149728_1_);
    }

    public String func_149739_a() {
        return "item.npcCrystal";
    }
}

