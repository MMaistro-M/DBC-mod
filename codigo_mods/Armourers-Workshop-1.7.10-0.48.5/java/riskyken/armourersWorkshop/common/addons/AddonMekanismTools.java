/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonMekanismTools
extends ModAddon {
    public AddonMekanismTools() {
        super("MekanismTools", "Mekanism Tools");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "ObsidianSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "LapisLazuliSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "OsmiumSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "BronzeSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "GlowstoneSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "SteelSword");
    }
}

