/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package me.NBArmors.tabs;

import me.NBArmors.armors.dbm;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class dbmTab
extends CreativeTabs {
    public dbmTab(String label) {
        super(label);
    }

    public Item func_78016_d() {
        return dbm.dbm0_chest;
    }
}

