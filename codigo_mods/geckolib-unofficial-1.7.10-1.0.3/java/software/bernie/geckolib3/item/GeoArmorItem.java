/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 */
package software.bernie.geckolib3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public abstract class GeoArmorItem
extends ItemArmor {
    public GeoArmorItem(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, int slot) {
        super(materialIn, renderIndexIn, slot);
    }

    @Nullable
    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        Class<?> clazz = ((Object)((Object)this)).getClass();
        GeoArmorRenderer renderer = GeoArmorRenderer.getRenderer(clazz);
        renderer.setCurrentItem(entityLiving, itemStack, armorSlot);
        renderer.applyEntityStats(entityLiving).applySlot(armorSlot);
        return renderer;
    }

    @Nullable
    @SideOnly(value=Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        Class<?> clazz = ((Object)((Object)this)).getClass();
        GeoArmorRenderer renderer = GeoArmorRenderer.getRenderer(clazz);
        return renderer.getTextureLocation((ItemArmor)stack.func_77973_b()).toString();
    }
}

