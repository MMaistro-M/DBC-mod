/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.items.ItemHeadwear;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemHeadDBC
extends ItemHeadwear {
    private String armorNamePrefix;
    private String tier;

    public ItemHeadDBC(String armornamePrefix, String tier) {
        super(armornamePrefix, tier, Integer.parseInt(tier) * 50 + 200);
        this.armorNamePrefix = armornamePrefix;
        this.tier = tier;
        this.modid = JRMCoreH.tjdbcAssts;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String armor = "";
        String s = "";
        String j = "";
        j = "jbra";
        armor = stack.toString().contains("leg") || stack.toString().contains("Leg") ? this.modid + ":armor/" + this.armorNamePrefix + "_2" + j + s + ".png" : this.modid + ":armor/" + this.armorNamePrefix + "_1" + j + s + ".png";
        return armor;
    }
}

