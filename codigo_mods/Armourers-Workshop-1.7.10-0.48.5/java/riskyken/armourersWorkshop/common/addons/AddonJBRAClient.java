/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonJBRAClient
extends ModAddon {
    public AddonJBRAClient() {
        super(null, "JRBA Client");
    }

    @Override
    protected boolean setIsModLoaded() {
        try {
            Class.forName("JinRyuu.JBRA.JBRA");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}

