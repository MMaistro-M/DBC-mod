/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.JYearsCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class JYCTab
extends CreativeTabs {
    public JYCTab(String label) {
        super(label);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_78016_d() {
        return JYearsCItems.ItemWatch;
    }
}

