/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package com.tobiasmjc.dbcadditions;

import com.tobiasmjc.dbcadditions.items.ItemsDBCUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DBCUtilsTab
extends CreativeTabs {
    public DBCUtilsTab() {
        super("DBCAdditions");
    }

    public Item func_78016_d() {
        return ItemsDBCUtils.potara_yellow;
    }
}

