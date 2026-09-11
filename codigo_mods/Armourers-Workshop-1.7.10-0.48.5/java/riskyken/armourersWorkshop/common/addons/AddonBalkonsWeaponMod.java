/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonBalkonsWeaponMod
extends ModAddon {
    public AddonBalkonsWeaponMod() {
        super("weaponmod", "Balkon's Weapon Mod");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "battleaxe.gold");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "warhammer.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "warhammer.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "warhammer.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "warhammer.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "warhammer.gold");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "katana.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "katana.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "katana.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "katana.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "katana.gold");
    }
}

