/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonTwilightForest
extends ModAddon {
    public AddonTwilightForest() {
        super("TwilightForest", "Twilight Forest");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.ironwoodSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.fierySword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.steeleafSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.knightlySword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.iceSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "item.glassSword");
    }
}

