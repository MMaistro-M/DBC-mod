/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonBetterStorage
extends ModAddon {
    public AddonBetterStorage() {
        super("betterstorage", "BetterStorage");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "cardboardSword");
    }
}

