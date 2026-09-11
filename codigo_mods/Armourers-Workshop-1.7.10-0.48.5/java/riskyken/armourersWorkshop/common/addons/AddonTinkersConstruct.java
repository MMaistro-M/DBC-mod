/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonTinkersConstruct
extends ModAddon {
    public AddonTinkersConstruct() {
        super("TConstruct", "Tinkers' Construct");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "longsword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "broadsword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "cleaver");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "rapier");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "cutlass");
    }
}

