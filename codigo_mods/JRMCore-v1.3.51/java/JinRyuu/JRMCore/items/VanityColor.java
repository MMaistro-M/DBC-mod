/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.items.ItemVanity;
import JinRyuu.JRMCore.mod_JRMCore;
import net.minecraft.item.ItemArmor;

public class VanityColor
extends ItemVanity {
    public VanityColor(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int par3, String armornamePrefix) {
        super(defcol, par3, armornamePrefix);
        this.rl = par2ArmorMaterial;
        this.func_77637_a(mod_JRMCore.JRMCore);
        this.modid = JRMCoreH.tjjrmc;
    }

    public VanityColor(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int par3, String armornamePrefix, int type) {
        super(defcol, par3, armornamePrefix, type);
        this.rl = par2ArmorMaterial;
        this.func_77637_a(mod_JRMCore.JRMCore);
        this.modid = JRMCoreH.tjjrmc;
    }
}

