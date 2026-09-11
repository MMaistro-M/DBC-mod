/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBucket
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemDBCBucket
extends ItemBucket {
    public ItemDBCBucket(Block fluidblock) {
        super(fluidblock);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.func_77642_a(Items.field_151133_ar);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a().replaceAll("item.", ""));
    }
}

