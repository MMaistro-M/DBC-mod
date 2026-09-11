/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.gasstation;

import io.github.legacymoddingmc.unimixins.gasstation.repackage.common.sanitycheck.SanityCheckHelper;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GasStationModule {
    private static final Logger LOGGER = LogManager.getLogger((String)"unimixins-gasstation");

    public static void init() {
        if (SanityCheckHelper.isEnabled()) {
            SanityCheckHelper.warnIfJarPrefixesExist(Arrays.asList("gasstation-", "mixinbooterlegacy-", "spongemixins-", "mixingasm-"));
            GasStationModule.checkComponentIntegrity();
        }
    }

    private static void checkComponentIntegrity() {
        ArrayList<String> missingComponents = new ArrayList<String>();
        if (!GasStationModule.classExists("com.falsepattern.gasstation.core.GasStationCore")) {
            missingComponents.add("GasStation");
        }
        if (!GasStationModule.classExists("makamys.mixingasm.api.TransformerInclusions")) {
            missingComponents.add("Mixingasm");
        }
        if (!GasStationModule.classExists("ru.timeconqueror.spongemixins.core.SpongeMixinsCore")) {
            missingComponents.add("SpongeMixins");
        }
        if (!GasStationModule.classExists("io.github.tox1cozz.mixinbooterlegacy.MixinBooterLegacyPlugin")) {
            missingComponents.add("MixinBooterLegacy");
        }
        if (!GasStationModule.classExists("org.spongepowered.asm.lib.Opcodes") || GasStationModule.classExists("org.spongepowered.libraries.org.objectweb.asm.Opcodes")) {
            missingComponents.add("MixinBooterLegacy");
        }
        if (!missingComponents.isEmpty()) {
            LOGGER.error("The following missing components were detected: " + missingComponents);
            LOGGER.error("Please obtain mods which provide them.");
            throw new RuntimeException("Missing components detected");
        }
    }

    public static boolean classExists(String string) {
        return GasStationModule.class.getResource("/" + string.replaceAll("\\.", "/") + ".class") != null;
    }
}

