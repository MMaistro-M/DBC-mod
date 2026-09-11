/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.items.ItemBodysuit;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemBodyDBC
extends ItemBodysuit {
    public String armorNamePrefix;

    public ItemBodyDBC(int defcol, String armornamePrefix) {
        super(defcol);
        this.armorNamePrefix = armornamePrefix;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    @Override
    public String getTextureFile() {
        return JRMCoreH.tjdbcAssts + ":";
    }

    @Override
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_77658_a().replaceAll("item.", ""));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String armor = "";
        armor = "jinryuudragonbc:armor/" + this.armorNamePrefix + ".png";
        return armor;
    }
}

