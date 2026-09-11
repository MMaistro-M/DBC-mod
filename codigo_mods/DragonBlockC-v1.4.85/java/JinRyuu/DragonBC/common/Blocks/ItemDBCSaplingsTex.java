/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlockDBCSaplings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDBCSaplingsTex
extends ItemBlock {
    protected final String[] field_150942_c = BlockDBCSaplings.field_149882_a;

    public ItemDBCSaplingsTex(Block p_i45346_1_) {
        super(p_i45346_1_);
        this.func_77627_a(true);
    }

    public int func_77647_b(int p_77647_1_) {
        return p_77647_1_;
    }

    public String func_77667_c(ItemStack p_77667_1_) {
        int i = p_77667_1_.func_77960_j();
        if (i < 0 || i >= this.field_150942_c.length) {
            i = 0;
        }
        return "tile.Block" + this.field_150942_c[i] + "Sapling";
    }
}

