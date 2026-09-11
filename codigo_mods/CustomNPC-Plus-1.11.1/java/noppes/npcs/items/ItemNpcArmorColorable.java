/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import noppes.npcs.CustomItems;

public class ItemNpcArmorColorable
extends ItemArmor {
    private String texture;

    public ItemNpcArmorColorable(int par1, ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par4, String texture) {
        super(par2EnumArmorMaterial, 0, par4);
        this.texture = texture;
        this.func_77637_a(CustomItems.tabArmor);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (type != null) {
            return "customnpcs:textures/gui/invisible.png";
        }
        if (this.field_77881_a == 2) {
            return "customnpcs:textures/armor/" + this.texture + "_2.png";
        }
        return "customnpcs:textures/armor/" + this.texture + "_1.png";
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        int j = this.func_82814_b(par1ItemStack);
        if (j < 0) {
            return 0xFFFFFF;
        }
        return j;
    }

    public int func_82814_b(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            return 10511680;
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.func_150297_b("color", 3) ? nbttagcompound1.func_74762_e("color") : 10511680);
    }

    public void func_82815_c(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound != null && (nbttagcompound1 = nbttagcompound.func_74775_l("display")).func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
        }
    }

    public void func_82813_b(ItemStack par1ItemStack, int par2) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            par1ItemStack.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        if (!nbttagcompound.func_150297_b("display", 10)) {
            nbttagcompound.func_74782_a("display", (NBTBase)nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", par2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return this.func_77617_a(par1);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.func_111208_A());
    }

    public boolean func_82816_b_(ItemStack par1ItemStack) {
        return !par1ItemStack.func_77942_o() ? false : (!par1ItemStack.func_77978_p().func_150297_b("display", 10) ? false : par1ItemStack.func_77978_p().func_74775_l("display").func_150297_b("color", 3));
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

