/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.gtnhmixins;

import io.github.legacymoddingmc.unimixins.gtnhmixins.GTNHMixinsConfig;
import io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.abstraction.ComparableVersion;
import io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.config.ConfigUtil;
import io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.sanitycheck.SanityCheckHelper;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GTNHMixinsModule {
    public static final Logger LOGGER = LogManager.getLogger((String)"unimixins-gtnhmixins");

    public static void init() {
        if (SanityCheckHelper.isEnabled()) {
            SanityCheckHelper.warnIfJarPrefixesExist(Arrays.asList("gasstation-", "mixinbooterlegacy-", "spongemixins-"));
            GTNHMixinsModule.checkComponentIntegrity();
        }
        ConfigUtil.load(GTNHMixinsConfig.class);
        if (GTNHMixinsModule.isLegacyGTNHMixinExtrasEnabled()) {
            Launch.classLoader.registerTransformer("io.github.legacymoddingmc.unimixins.gtnhmixins.asm.LegacyGTNHMixinExtrasGenerator");
            try {
                Class.forName("com.gtnewhorizon.mixinextras.MixinExtrasBootstrap").getMethod("init", new Class[0]).invoke(null, new Object[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize MixinExtrasBootstrap");
            }
        }
    }

    public static boolean isLegacyGTNHMixinExtrasEnabled() {
        if (!GTNHMixinsConfig.enableLegacyGTNHMixinExtrasPackage) {
            return false;
        }
        String requiredVersion = "0.8.5";
        String mixinVersion = (String)Launch.blackboard.get("mixin.initialised");
        if (mixinVersion != null && new ComparableVersion(mixinVersion).compareTo(new ComparableVersion(requiredVersion)) >= 0) {
            LOGGER.debug("Initializing MixinExtras");
            return true;
        }
        if (!SanityCheckHelper.isEnabled()) {
            LOGGER.warn("Skipping MixinExtras because Mixin version (" + mixinVersion + ") is lower than the required (" + requiredVersion + ")");
            return false;
        }
        throw new RuntimeException("Cannot load MixinExtras because Mixin version (" + mixinVersion + ") is lower than the required (" + requiredVersion + ")");
    }

    private static void checkComponentIntegrity() {
        ArrayList<String> missingComponents = new ArrayList<String>();
        if (!GTNHMixinsModule.classExists("org.spongepowered.asm.launch.MixinBootstrap")) {
            missingComponents.add("Mixin");
        }
        if (!GTNHMixinsModule.classExists("ru.timeconqueror.spongemixins.SpongeMixins")) {
            missingComponents.add("SpongeMixins");
        }
        if (!missingComponents.isEmpty()) {
            LOGGER.error("The following missing components were detected: " + missingComponents);
            LOGGER.error("Please obtain mods which provide them.");
            throw new RuntimeException("Missing components detected");
        }
    }

    public static boolean classExists(String string) {
        return GTNHMixinsModule.class.getResource("/" + string.replaceAll("\\.", "/") + ".class") != null;
    }
}

