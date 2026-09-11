/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.Runnables
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$Name
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$TransformerExclusions
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$Name
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$TransformerExclusions
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.falsepattern.gasstation.core;

import com.falsepattern.gasstation.IEarlyMixinLoader;
import com.google.common.util.concurrent.Runnables;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import io.github.legacymoddingmc.unimixins.gasstation.GasStationModule;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="GasStation Core Plugin")
@IFMLLoadingPlugin.TransformerExclusions(value={"com.falsepattern.gasstation.core"})
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="GasStation Core Plugin")
@IFMLLoadingPlugin.TransformerExclusions(value={"com.falsepattern.gasstation.core"})
@Deprecated
public class GasStationCore
implements IFMLLoadingPlugin {
    public static final String PLUGIN_NAME = "GasStation Core Plugin";
    public static final Logger LOGGER = LogManager.getLogger((String)"GasStation Core Plugin");

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
        Object coremodList = data.get("coremodList");
        if (coremodList instanceof List) {
            for (Object coremod : (List)coremodList) {
                try {
                    Field field = coremod.getClass().getField("coreModInstance");
                    field.setAccessible(true);
                    Object theMod = field.get(coremod);
                    if (!(theMod instanceof IEarlyMixinLoader)) continue;
                    IEarlyMixinLoader loader = (IEarlyMixinLoader)theMod;
                    for (String mixinConfig : loader.getMixinConfigs()) {
                        if (!loader.shouldMixinConfigQueue(mixinConfig)) continue;
                        LOGGER.info("Adding {} mixin configuration.", new Object[]{mixinConfig});
                        Mixins.addConfiguration(mixinConfig);
                        loader.onMixinConfigQueued(mixinConfig);
                    }
                }
                catch (Exception e) {
                    LOGGER.error("Unexpected error", (Throwable)e);
                }
            }
        }
        Launch.blackboard.getOrDefault("unimixins.mixinModidDecorator.refresh", Runnables.doNothing()).run();
    }

    public String getAccessTransformerClass() {
        return null;
    }

    static {
        LOGGER.info("Initializing GasStationCore");
        GasStationModule.init();
    }
}

