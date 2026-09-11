/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 */
package me.NBArmors.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class FabricCore
extends Item {
    public void func_94581_a(IIconRegister IconRegister) {
        this.field_77791_bV = IconRegister.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", ""));
        this.setNoRepair();
    }
}

