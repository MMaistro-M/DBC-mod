/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Items.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class WoodSlabItem
extends ItemSlab {
    public static final String[] slabs = new String[]{"Sakura", "Mahagony", "Maple"};

    public WoodSlabItem(Block block) {
        super(block, ModdedBlocks.SakuraSlabsSingle, ModdedBlocks.SakuraSlabsDouble, block == ModdedBlocks.SakuraSlabsDouble);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public String func_77667_c(ItemStack itemstack) {
        int i = itemstack.func_77960_j();
        if (i < 0 || i >= slabs.length) {
            i = 0;
        }
        return super.func_77658_a() + "." + slabs[i];
    }

    public int func_77647_b(int meta) {
        return meta;
    }
}

