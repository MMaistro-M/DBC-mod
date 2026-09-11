/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
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
package com.gtnewhorizon.gtnhmixins.core;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Runnables;
import com.gtnewhorizon.gtnhmixins.GTNHMixins;
import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.Reflection;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import io.github.legacymoddingmc.unimixins.gtnhmixins.GTNHMixinsModule;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.service.MixinService;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="GTNHMixins Core Plugin")
@IFMLLoadingPlugin.TransformerExclusions(value={"com.gtnewhorizon.gtnhmixins.core"})
@IFMLLoadingPlugin.SortingIndex(value=-2147483643)
@IFMLLoadingPlugin.Name(value="GTNHMixins Core Plugin")
@IFMLLoadingPlugin.TransformerExclusions(value={"com.gtnewhorizon.gtnhmixins.core"})
public class GTNHMixinsCore
implements IFMLLoadingPlugin {
    public static final String PLUGIN_NAME = "GTNHMixins Core Plugin";
    public static final Logger LOGGER = LogManager.getLogger((String)"GTNHMixins Core Plugin");
    private static boolean isObf;
    public static final Map<String, String> MANUALLY_IDENTIFIED_COREMODS;

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    private Set<String> getLoadedCoremods(List<?> coremodList) {
        HashSet<String> loadedCoremods = new HashSet<String>();
        MANUALLY_IDENTIFIED_COREMODS.forEach((clsName, coreModIdentifier) -> {
            try {
                MixinService.getService().getBytecodeProvider().getClassNode((String)clsName, false);
                loadedCoremods.add((String)coreModIdentifier);
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
        for (Object tweak : (ArrayList)Launch.blackboard.get("Tweaks")) {
            try {
                Object obj = Reflection.pluginWrapperClass.isInstance(tweak) ? Reflection.coreModInstanceField.get(tweak) : tweak;
                loadedCoremods.add(obj.toString().split("@")[0]);
            }
            catch (Exception exception) {}
        }
        for (Object coremod : coremodList) {
            try {
                Object theMod = Reflection.coreModInstanceField.get(coremod);
                loadedCoremods.add(theMod.toString().split("@")[0]);
            }
            catch (Exception exception) {}
        }
        return loadedCoremods;
    }

    public void injectData(Map<String, Object> data) {
        isObf = (Boolean)data.get("runtimeDeobfuscationEnabled");
        GTNHMixins.log("Searching coremodList for IEarlyMixinLoaders");
        Object coremodList = data.get("coremodList");
        if (coremodList instanceof List) {
            Set<String> loadedCoremods = this.getLoadedCoremods((List)coremodList);
            GTNHMixins.log("LoadedCoreMods {}", loadedCoremods.toString());
            for (Object coremod : (List)coremodList) {
                try {
                    Object theMod = Reflection.coreModInstanceField.get(coremod);
                    if (!(theMod instanceof IEarlyMixinLoader)) continue;
                    GTNHMixins.log("Loading mixins from IEarlyMixinLoader [{}]", theMod.getClass().getName());
                    IEarlyMixinLoader loader = (IEarlyMixinLoader)theMod;
                    String mixinConfig = loader.getMixinConfig();
                    Config config = Config.create(mixinConfig, null);
                    List<String> mixins = loader.getMixins(loadedCoremods);
                    for (String mixin : mixins) {
                        GTNHMixins.log("Loading [{}] {}", mixinConfig, mixin);
                    }
                    Reflection.mixinClassesField.set(Reflection.configField.get(config), mixins);
                    Reflection.registerConfigurationMethod.invoke(null, config);
                }
                catch (ReflectiveOperationException e) {
                    GTNHMixins.LOGGER.error("Unexpected error", (Throwable)e);
                }
            }
        }
        Launch.blackboard.getOrDefault("unimixins.mixinModidDecorator.refresh", Runnables.doNothing()).run();
    }

    public String getAccessTransformerClass() {
        return null;
    }

    public static boolean isObf() {
        return isObf;
    }

    static {
        MANUALLY_IDENTIFIED_COREMODS = ImmutableMap.builder().put((Object)"optifine.OptiFineForgeTweaker", (Object)"optifine.OptiFineForgeTweaker").put((Object)"codechicken.lib.asm.ModularASMTransformer", (Object)"codechicken.lib").put((Object)"org.bukkit.World", (Object)"Bukkit").build();
        GTNHMixins.LOGGER.debug("Initializing GTNHMixins Core");
        GTNHMixinsModule.init();
    }
}

