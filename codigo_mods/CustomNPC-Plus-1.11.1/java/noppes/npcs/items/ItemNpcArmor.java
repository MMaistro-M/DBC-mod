/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;

public class ItemNpcArmor
extends ItemArmor {
    private String texture;

    public ItemNpcArmor(int par1, ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par4, String texture) {
        super(par2EnumArmorMaterial, 0, par4);
        this.texture = texture;
        this.func_77637_a(CustomItems.tabArmor);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (this.field_77881_a == 2) {
            return "customnpcs:textures/armor/" + this.texture + "_2.png";
        }
        return "customnpcs:textures/armor/" + this.texture + "_1.png";
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

