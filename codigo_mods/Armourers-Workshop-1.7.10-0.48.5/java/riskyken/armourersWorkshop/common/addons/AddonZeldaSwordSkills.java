/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonZeldaSwordSkills
extends ModAddon {
    public AddonZeldaSwordSkills() {
        super("zeldaswordskills", "Zelda Sword Skills");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_darknut");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_kokiri");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_ordon");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_giant");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_biggoron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_master");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_tempered");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_golden");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "zss.sword_master_true");
    }
}

