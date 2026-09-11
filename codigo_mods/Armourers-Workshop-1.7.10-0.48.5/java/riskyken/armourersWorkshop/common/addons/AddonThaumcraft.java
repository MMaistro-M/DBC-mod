/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonThaumcraft
extends ModAddon {
    public AddonThaumcraft() {
        super("Thaumcraft", "Thaumcraft");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "ItemSwordElemental");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "ItemSwordThaumium");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "ItemSwordVoid");
    }
}

