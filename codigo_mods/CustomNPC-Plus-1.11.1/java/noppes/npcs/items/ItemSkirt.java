/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;

public class ItemSkirt
extends ItemArmor {
    private String texture;

    public ItemSkirt(ItemArmor.ArmorMaterial par2EnumArmorMaterial, String texture) {
        super(par2EnumArmorMaterial, 0, 2);
        this.texture = texture;
        this.func_77637_a(CustomItems.tabArmor);
        this.func_77625_d(1);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        int j = this.func_82814_b(par1ItemStack);
        if (j < 0) {
            j = 0xFFFFFF;
        }
        return j;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (type != null && type.equals("overlay")) {
            return null;
        }
        return this.texture;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return CustomNpcs.proxy.getSkirtModel();
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return super.func_77617_a(par1);
    }
}

