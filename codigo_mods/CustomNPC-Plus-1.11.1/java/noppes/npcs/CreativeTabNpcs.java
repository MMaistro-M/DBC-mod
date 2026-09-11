/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 */
package noppes.npcs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabNpcs
extends CreativeTabs {
    public Item item = Items.field_151054_z;
    public int meta = 0;

    public CreativeTabNpcs(String label) {
        super(label);
    }

    public Item func_78016_d() {
        return this.item;
    }

    public int func_151243_f() {
        return this.meta;
    }
}

