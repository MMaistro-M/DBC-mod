/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 */
package riskyken.armourersWorkshop.common.addons;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import riskyken.armourersWorkshop.common.addons.ModAddon;
import riskyken.armourersWorkshop.utils.ModLogger;

public class AddonBuildCraft
extends ModAddon {
    public AddonBuildCraft() {
        super("BuildCraft|Core", "BuildCraft");
    }

    public boolean isSkinCompatibleVersion() {
        ModContainer mc;
        if (this.isModLoaded() && (mc = (ModContainer)Loader.instance().getIndexedModList().get(this.getModId())) != null) {
            String version = mc.getVersion();
            String[] versionSplit = version.split("\\.");
            try {
                int majorVersion = Integer.parseInt(versionSplit[0]);
                if (majorVersion > 6) {
                    ModLogger.log("BuildCraft robot skin support active.");
                    return true;
                }
                ModLogger.log("BuildCraft is out of date. Unable to active robot skin support.");
            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}

