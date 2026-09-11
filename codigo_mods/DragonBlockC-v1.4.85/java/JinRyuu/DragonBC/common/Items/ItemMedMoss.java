/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.items.ItemJRMCSeedFood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;

public class ItemMedMoss
extends ItemJRMCSeedFood {
    public ItemMedMoss(int f, float g) {
        super(f, g, BlocksDBC.BlockCropMedMoss, Blocks.field_150458_ak);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a().replaceAll("item.", ""));
    }
}

