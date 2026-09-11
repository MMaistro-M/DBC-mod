/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGRaceHelper;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={JGRaceHelper.class}, remap=false)
public class MixinJGRaceHelper {
    @Inject(method={"getMaxRacialSkillLevel(ZZB)B"}, at={@At(value="HEAD")}, cancellable=true)
    private static void setMaxRacial(boolean dbc, boolean nc, byte race, CallbackInfoReturnable<Byte> ci) {
        if (!dbc) {
            return;
        }
        if (JRMCoreH.isRaceSaiyan(race)) {
            ci.setReturnValue(DBCAConfig.SaiyanMaxRacial);
        }
        if (JRMCoreH.isRaceArcosian(race)) {
            ci.setReturnValue(DBCAConfig.ArcosianMaxRacial);
        }
        if (JRMCoreH.isRaceHuman(race)) {
            ci.setReturnValue(DBCAConfig.HumanMaxRacial);
        }
        if (JRMCoreH.isRaceNamekian(race)) {
            ci.setReturnValue(DBCAConfig.NamekianMaxRacial);
        }
        if (JRMCoreH.isRaceMajin(race)) {
            ci.setReturnValue(DBCAConfig.MajinMaxRacial);
        }
    }
}

