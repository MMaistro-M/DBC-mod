/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 */
package riskyken.armourersWorkshop.common.addons;

import cpw.mods.fml.common.Loader;
import java.util.ArrayList;
import net.minecraftforge.client.IItemRenderer;
import riskyken.armourersWorkshop.utils.EventState;
import riskyken.armourersWorkshop.utils.ModLogger;

public abstract class ModAddon {
    private final String modId;
    private final String modName;
    private final boolean isModLoaded;
    private final ArrayList<String> overrrides;

    public ModAddon(String modId, String modName) {
        this.modId = modId;
        this.modName = modName;
        this.isModLoaded = this.setIsModLoaded();
        if (this.isModLoaded) {
            ModLogger.log(String.format("Loading %s Compatibility Addon", this.getModName()));
        }
        this.overrrides = new ArrayList();
    }

    protected boolean setIsModLoaded() {
        return Loader.isModLoaded((String)this.modId);
    }

    public void preInit() {
    }

    public void init() {
    }

    public void postInit() {
    }

    public String getModId() {
        return this.modId;
    }

    public String getModName() {
        return this.modName;
    }

    public boolean isModLoaded() {
        return this.isModLoaded;
    }

    public ArrayList<String> getItemOverrides() {
        return this.overrrides;
    }

    protected void addItemOverride(ItemOverrideType type, String itemName) {
        this.overrrides.add(type.toString().toLowerCase() + ":" + this.getModId() + ":" + itemName);
    }

    public void onWeaponRender(IItemRenderer.ItemRenderType type, EventState state) {
    }

    public static enum ItemOverrideType {
        SWORD,
        ITEM,
        PICKAXE,
        AXE,
        SHOVEL,
        HOE,
        BOW;

    }
}

