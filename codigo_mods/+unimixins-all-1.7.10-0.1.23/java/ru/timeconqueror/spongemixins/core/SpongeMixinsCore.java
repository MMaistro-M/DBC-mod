/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$Name
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$Name
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package ru.timeconqueror.spongemixins.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="SpongeMixin Core Plugin")
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="SpongeMixin Core Plugin")
public class SpongeMixinsCore
implements IFMLLoadingPlugin {
    public static final String PLUGIN_NAME = "SpongeMixin Core Plugin";
    public static final Logger LOGGER = LogManager.getLogger((String)"SpongeMixin Core Plugin");

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }

    static {
        LOGGER.info("Initializing SpongeMixinsCore");
    }
}

