/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonGlassShards
extends ModAddon {
    public AddonGlassShards() {
        super("glass_shards", "Glass Shards");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "glass_sword");
    }
}

