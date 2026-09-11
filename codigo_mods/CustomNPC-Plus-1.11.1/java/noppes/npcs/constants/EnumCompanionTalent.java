/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.constants;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;

public enum EnumCompanionTalent {
    INVENTORY(CustomItems.satchel),
    ARMOR((Item)Items.field_151030_Z),
    SWORD(Items.field_151048_u),
    RANGED((Item)Items.field_151031_f),
    ACROBATS((Item)Items.field_151021_T),
    INTEL(CustomItems.letter);

    public ItemStack item;

    private EnumCompanionTalent(Item item) {
        this.item = new ItemStack(item);
    }
}

