/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemAxe
 */
package JinRyuu.DragonBC.common.Items.m;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class MyAxes
extends ItemAxe {
    protected MyAxes(String unlocalizedName, Item.ToolMaterial material) {
        super(material);
        this.func_77655_b(unlocalizedName);
        this.func_111206_d(JRMCoreH.tjdbcAssts + ":" + unlocalizedName);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }
}

