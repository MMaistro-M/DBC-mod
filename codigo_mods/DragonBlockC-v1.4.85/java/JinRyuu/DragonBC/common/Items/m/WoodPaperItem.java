/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Items.m;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class WoodPaperItem
extends ItemBlock {
    public static final String[] walls = new String[]{"White", "Orange", "Magenta", "Light_Blue", "Yellow", "Lime", "Pink", "Gray", "Light_Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};

    public WoodPaperItem(Block block) {
        super(block);
        this.func_77627_a(true);
    }

    public String func_77667_c(ItemStack itemstack) {
        int i = itemstack.func_77960_j();
        if (i < 0 || i >= walls.length) {
            i = 0;
        }
        return super.func_77658_a() + "." + walls[i];
    }

    public int func_77647_b(int meta) {
        return meta;
    }
}

