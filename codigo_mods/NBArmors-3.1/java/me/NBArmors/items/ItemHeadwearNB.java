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

public class ItemHeadwearNB
extends Item {
    public String modid;
    private String armorNamePrefix;
    private String tier;

    public String getTier() {
        return this.tier;
    }

    public ItemHeadwearNB(String armornamePrefix, String tier, int maxDam) {
        this.armorNamePrefix = armornamePrefix;
        this.tier = tier;
        this.func_77656_e(maxDam);
        this.func_77625_d(1);
        this.modid = "NBarmors";
    }

    public String getTextureFile() {
        return this.modid + ":";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(this.modid + ":" + this.func_77658_a().replaceAll("item.", "").replaceAll("Scoutera", "Scouter").replaceAll("Scouterb", "Scouter"));
    }
}

