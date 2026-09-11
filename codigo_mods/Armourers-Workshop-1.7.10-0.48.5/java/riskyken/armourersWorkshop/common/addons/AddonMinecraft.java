/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.addons;

import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonMinecraft
extends ModAddon {
    public AddonMinecraft() {
        super("minecraft", "Minecraft");
    }

    @Override
    protected boolean setIsModLoaded() {
        return true;
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "wooden_sword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "stone_sword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "iron_sword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "golden_sword");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "diamond_sword");
        this.addItemOverride(ModAddon.ItemOverrideType.BOW, "bow");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "wooden_pickaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "stone_pickaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "iron_pickaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "golden_pickaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.PICKAXE, "diamond_pickaxe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "wooden_axe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "stone_axe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "iron_axe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "golden_axe");
        this.addItemOverride(ModAddon.ItemOverrideType.AXE, "diamond_axe");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "wooden_shovel");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "stone_shovel");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "iron_shovel");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "golden_shovel");
        this.addItemOverride(ModAddon.ItemOverrideType.SHOVEL, "diamond_shovel");
        this.addItemOverride(ModAddon.ItemOverrideType.HOE, "wooden_hoe");
        this.addItemOverride(ModAddon.ItemOverrideType.HOE, "stone_hoe");
        this.addItemOverride(ModAddon.ItemOverrideType.HOE, "iron_hoe");
        this.addItemOverride(ModAddon.ItemOverrideType.HOE, "golden_hoe");
        this.addItemOverride(ModAddon.ItemOverrideType.HOE, "diamond_hoe");
    }
}

