/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 */
package me.NBArmors.items;

import JinRyuu.JRMCore.items.ItemHeadwear;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemHeadNB
extends ItemHeadwear {
    private String armorNamePrefix;
    private String tier;

    public ItemHeadNB(String armornamePrefix, String tier) {
        super(armornamePrefix, tier, Integer.parseInt(tier) * 500 + 1000);
        this.armorNamePrefix = armornamePrefix;
        this.tier = tier;
        this.modid = "NBArmors";
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String r = "";
        String j = "";
        j = "jbra";
        r = stack.toString().contains("leg") || stack.toString().contains("Leg") ? "NBArmors:textures/armor/" + this.armorNamePrefix + "_2" + j + ".png" : (stack.toString().contains("boot") || stack.toString().contains("Boot") ? "NBArmors:textures/armor/" + this.armorNamePrefix + "_3" + j + ".png" : (stack.toString().contains("head") || stack.toString().contains("Head") ? "NBArmors:textures/armor/" + this.armorNamePrefix + "_0" + j + ".png" : "NBArmors:textures/armor/" + this.armorNamePrefix + "_1" + j + ".png"));
        return r;
    }
}

