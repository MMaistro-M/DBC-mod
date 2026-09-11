/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreMm;
import JinRyuu.JRMCore.JRMCoreMsn;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import java.util.ArrayList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={JRMCoreMm.class}, remap=false)
public class MixinJRMCoreMm {
    @Inject(method={"msnGen"}, at={@At(value="TAIL")}, cancellable=true)
    private static void modifySaga(CallbackInfoReturnable<JRMCoreMsnBundle> ci) {
        JRMCoreMsnBundle msnBundle = ci.getReturnValue();
        for (JRMCoreMsn msn : msnBundle.getMissions()) {
            String dimensionName = null;
            if (msn.getId() >= 104 && msn.getId() <= 109) {
                dimensionName = "UniversalArena";
            }
            if (msn.getId() == 117 || msn.getId() == 90) {
                dimensionName = "SacredWorld";
            }
            if (msn.getId() == 96) {
                dimensionName = "BeerusPlanet";
            }
            if (dimensionName == null) continue;
            String objective1 = msn.getObjectives().get(0).get(0);
            String objective2 = "dim2;N" + dimensionName;
            String objective3 = msn.getObjectives().get(0).get(2);
            ArrayList<String> objectives = new ArrayList<String>();
            objectives.add(objective1);
            objectives.add(objective2);
            objectives.add(objective3);
            ArrayList<ArrayList<String>> obj2 = new ArrayList<ArrayList<String>>();
            obj2.add(objectives);
            msn.setObjectives(obj2);
        }
        ci.setReturnValue(msnBundle);
    }
}

