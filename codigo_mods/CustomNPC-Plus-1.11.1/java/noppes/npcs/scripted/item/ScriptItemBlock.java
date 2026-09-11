/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.scripted.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.api.item.IItemBlock;
import noppes.npcs.scripted.item.ScriptItemStack;

public class ScriptItemBlock
extends ScriptItemStack
implements IItemBlock {
    protected String blockName;

    public ScriptItemBlock(ItemStack item) {
        super(item);
        Block b = Block.func_149634_a((Item)item.func_77973_b());
        this.blockName = Block.field_149771_c.func_148750_c((Object)b);
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String getBlockName() {
        return this.blockName;
    }
}

