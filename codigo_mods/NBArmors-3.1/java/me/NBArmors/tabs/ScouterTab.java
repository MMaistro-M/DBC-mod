/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package me.NBArmors.tabs;

import me.NBArmors.items.NewScouter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ScouterTab
extends CreativeTabs {
    public ScouterTab(String label) {
        super(label);
    }

    public Item func_78016_d() {
        return NewScouter.BScoutern;
    }
}

