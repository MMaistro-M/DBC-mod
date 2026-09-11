/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonBotania
extends ModAddon {
    public AddonBotania() {
        super("Botania", "Botania");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "manasteelSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "elementiumSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "terraSword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "starSword");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "manasteelPick");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "elementiumPick");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "terraPick");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "manasteelAxe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "elementiumAxe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "terraAxe");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "manasteelShovel");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "elementiumShovel");
    }
}

