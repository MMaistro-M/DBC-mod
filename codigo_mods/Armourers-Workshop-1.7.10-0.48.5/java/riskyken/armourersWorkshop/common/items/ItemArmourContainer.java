/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItemArmour;

public class ItemArmourContainer
extends AbstractModItemArmour {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconChest;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconLegs;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconFeet;

    public ItemArmourContainer(String name, int armourType) {
        super(name, ItemArmor.ArmorMaterial.IRON, armourType, false);
        this.func_77637_a(null);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.ARMOUR_CONTAINER_HEAD);
        this.iconChest = register.func_94245_a(LibItemResources.ARMOUR_CONTAINER_CHEST);
        this.iconLegs = register.func_94245_a(LibItemResources.ARMOUR_CONTAINER_LEGS);
        this.iconFeet = register.func_94245_a(LibItemResources.ARMOUR_CONTAINER_FEET);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77650_f(ItemStack stack) {
        if (this.field_77881_a == 1) {
            return this.iconChest;
        }
        if (this.field_77881_a == 2) {
            return this.iconLegs;
        }
        if (this.field_77881_a == 3) {
            return this.iconFeet;
        }
        return this.field_77791_bV;
    }
}

