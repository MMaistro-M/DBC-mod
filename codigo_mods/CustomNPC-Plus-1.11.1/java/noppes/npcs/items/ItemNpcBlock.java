/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemNpcBlock
extends ItemBlock {
    public String[] names;

    public ItemNpcBlock(Block block) {
        super(block);
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        if (this.names != null && par1ItemStack.func_77960_j() < this.names.length) {
            return this.names[par1ItemStack.func_77960_j()];
        }
        return this.field_150939_a.func_149739_a();
    }
}

