/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.mod_JYearsC;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemWatch
extends Item {
    public ItemWatch() {
        this.func_77637_a(mod_JYearsC.JYearsC);
    }

    public String getTextureFile() {
        return "jinryuujyearsc:";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuujyearsc:" + this.func_77658_a());
    }
}

