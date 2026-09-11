/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlockWithMetadata
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Items.m;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class WoodSaplingItem
extends ItemBlockWithMetadata {
    public static final String[] saplings = new String[]{"Sakura", "Mahagony", "Maple"};

    public WoodSaplingItem(Block block) {
        super(block, block);
        this.func_77627_a(true);
    }

    public String func_77667_c(ItemStack itemstack) {
        int i = itemstack.func_77960_j();
        if (i < 0 || i >= saplings.length) {
            i = 0;
        }
        return super.func_77658_a() + "." + saplings[i];
    }

    public int func_77647_b(int meta) {
        return meta;
    }
}

