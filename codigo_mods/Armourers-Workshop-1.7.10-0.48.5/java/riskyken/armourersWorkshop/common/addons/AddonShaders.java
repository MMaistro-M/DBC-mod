/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonShaders
extends ModAddon {
    public AddonShaders() {
        super(null, "Shaders Mod");
    }

    @Override
    protected boolean setIsModLoaded() {
        if (!ArmourersWorkshop.isDedicated()) {
            try {
                Class.forName("shadersmodcore.client.Shaders");
                return true;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }
}

