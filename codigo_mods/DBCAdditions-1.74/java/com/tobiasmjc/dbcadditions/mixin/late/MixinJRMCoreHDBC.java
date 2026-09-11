/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.client.ClientProxy;
import com.tobiasmjc.dbcadditions.common.CommonProxy;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={JRMCoreHDBC.class}, remap=false)
public class MixinJRMCoreHDBC {
    @Inject(method={"getPlayerColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetPlayerColor(int type, int def, int p, int r, int s, boolean divine, boolean y, boolean ui, boolean ui2, boolean gd, CallbackInfoReturnable<Integer> ci) {
        int barColor;
        if (!DBCAConfig.CustomForms) {
            return;
        }
        if (!ClientProxy.isRenderingKiBar) {
            return;
        }
        EntityClientPlayerMP pl = Minecraft.func_71410_x().field_71439_g;
        DBCAForm form = DBCAForms.getForm(DataUtils.getDBCAState((EntityPlayer)pl));
        if (form != null && (barColor = form.getBarColor(DataUtils.legendary((EntityPlayer)pl), DataUtils.divine((EntityPlayer)pl))) != -1) {
            ci.setReturnValue(barColor);
        }
    }

    @Inject(method={"DBCsizeBasedOnRace(IIZ)F"}, at={@At(value="TAIL")}, cancellable=true)
    private static void applyFormBulk(int race, int state, boolean divine, CallbackInfoReturnable<Float> cir, @Local(name={"f2"}) LocalFloatRef size) {
        if (CommonProxy.CurrentPlayerDBC != null) {
            DBCAForm form = DBCAForms.getForm(DataUtils.getDBCAState(CommonProxy.CurrentPlayerDBC));
            if (form == null) {
                return;
            }
            cir.setReturnValue(Float.valueOf(size.get() * form.getBulkMultiplier()));
        }
    }

    @Inject(method={"action"}, at={@At(value="HEAD")}, cancellable=true)
    private static void applyCustomActions(int d, boolean action, boolean black, CallbackInfoReturnable<String> ci) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (DataUtils.getDBCARace((EntityPlayer)player) == DBCARaces.BIO_ANDROID.ID && d == 6) {
            if (JGConfigClientSettings.CLIENT_GR13) {
                black = true;
            }
            if (action) {
                JRMCoreH.Skll((byte)5, (byte)(JRMCoreH.StusEfctsMe(21) ? 1 : 0), (byte)21);
            } else {
                String opt1 = JGConfigClientSettings.CLIENT_GR12 ? "Enable" : "Off";
                String opt2 = JGConfigClientSettings.CLIENT_GR12 ? "Disable" : "On";
                boolean bo = JRMCoreH.StusEfctsMe(21);
                String enable = JRMCoreH.trl("jrmc", opt1);
                String disable = JRMCoreH.trl("jrmc", opt2);
                String s = DBCAUtils.translate("absorption") + ": " + (!bo ? (black ? "" : "\u00a74") + enable : (black ? "" : "\u00a72") + disable);
                ci.setReturnValue(s);
                ci.cancel();
            }
        }
    }

    @Inject(method={"DBCsizeBasedOnRace2(IIZ)F"}, at={@At(value="TAIL")}, cancellable=true)
    private static void applyFormSize(int race, int state, boolean divine, CallbackInfoReturnable<Float> cir, @Local(name={"f3"}) LocalFloatRef size) {
        if (CommonProxy.CurrentPlayerDBC != null) {
            DBCAForm form = DBCAForms.getForm(DataUtils.getDBCAState(CommonProxy.CurrentPlayerDBC));
            if (form == null) {
                return;
            }
            cir.setReturnValue(Float.valueOf(size.get() * form.getSizeMultiplier()));
        }
    }
}

