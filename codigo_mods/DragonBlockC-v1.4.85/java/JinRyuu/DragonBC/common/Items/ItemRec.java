/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemRec
extends ItemRecord {
    public ItemRec(String recordName) {
        super(recordName);
        this.field_77777_bU = 1;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_77658_a());
    }

    public EnumRarity func_77613_e(ItemStack itemStack) {
        return EnumRarity.rare;
    }

    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(JRMCoreH.tjdbcAssts + ":" + name);
    }
}

