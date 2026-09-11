/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package me.NBArmors.tabs;

import me.NBArmors.armors.db;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class dbTab
extends CreativeTabs {
    public dbTab(String label) {
        super(label);
    }

    public Item func_78016_d() {
        return db.c08_chest;
    }
}

