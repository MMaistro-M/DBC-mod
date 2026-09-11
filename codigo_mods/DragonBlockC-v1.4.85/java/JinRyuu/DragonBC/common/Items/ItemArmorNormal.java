/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemArmorNormal
extends ItemArmor {
    public String armorNamePrefix;
    public ItemArmor.ArmorMaterial field_77878_bZ;
    @SideOnly(value=Side.CLIENT)
    public IIcon field_94604_cx1;
    private IIcon overlayIcon;
    private IIcon emptySlotIcon;
    private static final String[] CLOTH_OVERLAY_NAMES = new String[]{"leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};

    public ItemArmorNormal(ItemArmor.ArmorMaterial par2ArmorMaterial, int par3, int par4, String armornamePrefix) {
        super(par2ArmorMaterial, par3, par4);
        this.field_77878_bZ = par2ArmorMaterial;
        par2ArmorMaterial.func_78044_b(par4);
        this.func_77656_e(par2ArmorMaterial.func_78046_a(par4));
        this.field_77777_bU = 1;
        this.armorNamePrefix = armornamePrefix;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a().replaceAll("item.", "").toLowerCase());
        this.field_94604_cx1 = par1IconRegister.func_94245_a(field_94603_a[this.field_77881_a]);
        if (this.field_77878_bZ == ItemArmor.ArmorMaterial.CLOTH) {
            this.overlayIcon = par1IconRegister.func_94245_a(CLOTH_OVERLAY_NAMES[this.field_77881_a]);
        }
        this.emptySlotIcon = par1IconRegister.func_94245_a(field_94603_a[this.field_77881_a]);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String armor = "";
        armor = stack.toString().toLowerCase().contains("pants") || stack.toString().toLowerCase().contains("leg") ? "jinryuudragonbc:armor/" + this.armorNamePrefix + "_2.png" : "jinryuudragonbc:armor/" + this.armorNamePrefix + "_1.png";
        return armor;
    }
}

