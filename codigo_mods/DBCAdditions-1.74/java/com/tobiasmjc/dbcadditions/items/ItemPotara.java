/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package com.tobiasmjc.dbcadditions.items;

import com.tobiasmjc.dbcadditions.DBCAdditions;
import net.minecraft.item.Item;

public class ItemPotara
extends Item {
    public String color;

    public ItemPotara(String color) {
        this.func_77625_d(2);
        this.color = color;
        this.func_77655_b("potara_" + color);
        this.func_111206_d("dbcadditions:potara_" + color);
        this.func_77637_a(DBCAdditions.CREATIVE_TAB);
    }
}

