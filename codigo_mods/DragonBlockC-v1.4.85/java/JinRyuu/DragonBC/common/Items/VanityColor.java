/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreHJBRA;
import JinRyuu.JRMCore.items.ItemVanity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;

public class VanityColor
extends ItemVanity {
    private final int VANITY_ANDROID_21_COAT = 0;
    private final int VANITY_ANDROID_21_BOOT = 1;
    private int type = -1;

    public VanityColor(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int par3, String armornamePrefix) {
        super(defcol, par3, armornamePrefix);
        this.rl = par2ArmorMaterial;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.modid = "jinryuudragonbc";
    }

    public VanityColor(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int par3, String armornamePrefix, int type) {
        super(defcol, par3, armornamePrefix);
        this.rl = par2ArmorMaterial;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.modid = "jinryuudragonbc";
        this.type = type;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped giMdl(int slt, EntityLivingBase e) {
        if (this.wear(e)) {
            boolean has = false;
            for (int i = 0; i < ItemsDBC.ItemsVanityNum.length; ++i) {
                if (ItemsDBC.ItemVanity3[i] <= -1 || this.type != ItemsDBC.ItemVanity3[i]) continue;
                has = true;
                return JRMCoreHJBRA.DBC_GiTurtleMdl2[this.type];
            }
            if (!has) {
                if (slt != 5) {
                    if (this.func_77658_a().contains("Head")) {
                        return JRMCoreHJBRA.GiTurtleMdl2;
                    }
                    return JRMCoreHJBRA.GiTurtleMdl3;
                }
                return JRMCoreHJBRA.GiTurtleMdl2;
            }
        }
        return null;
    }
}

