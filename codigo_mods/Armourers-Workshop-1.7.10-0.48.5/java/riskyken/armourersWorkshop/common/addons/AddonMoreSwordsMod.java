/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonMoreSwordsMod
extends ModAddon {
    public AddonMoreSwordsMod() {
        super("MSM3", "More Swords Mod");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "dawnStar");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "vampiric");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "gladiolus");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "draconic");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "ender");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "crystal");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "glacial");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "aether");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "wither");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "admin");
    }
}

