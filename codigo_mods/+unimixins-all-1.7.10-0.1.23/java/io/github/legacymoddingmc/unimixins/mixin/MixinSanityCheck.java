/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 */
package io.github.legacymoddingmc.unimixins.mixin;

import io.github.legacymoddingmc.unimixins.mixin.MixinCore;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import net.minecraft.launchwrapper.Launch;

public class MixinSanityCheck {
    static List<String> checkMixinHasInitialized() {
        if (!Launch.blackboard.containsKey("mixin.initialised")) {
            return Arrays.asList("Mixin has not been initialized! If this is a dev environment, make sure org.spongepowered.asm.launch.MixinTweaker was added via the --tweakClass program argument.");
        }
        return Arrays.asList(new String[0]);
    }

    static List<String> checkMixinContainer() {
        String mixinCoreJarName = MixinSanityCheck.getJarName(MixinSanityCheck.class.getResource("/io/github/legacymoddingmc/unimixins/mixin/MixinCore.class"));
        String mixinTweakerJarName = MixinSanityCheck.getJarName(MixinSanityCheck.class.getResource("/org/spongepowered/asm/launch/MixinTweaker.class"));
        MixinCore.LOGGER.debug("Name of jar containing MixinCore: " + mixinCoreJarName);
        MixinCore.LOGGER.debug("Name of jar containing MixinTweaker: " + mixinTweakerJarName);
        if (!mixinCoreJarName.equals(mixinTweakerJarName)) {
            boolean isSuspectedToBeDueToNaming = mixinCoreJarName != null && mixinTweakerJarName != null && mixinCoreJarName.compareToIgnoreCase(mixinTweakerJarName) > 0;
            return Arrays.asList("A different version of Mixin (the one inside " + MixinSanityCheck.stringOr(mixinTweakerJarName, "unknown") + ") is getting loaded instead of UniMixins's one (inside " + MixinSanityCheck.stringOr(mixinCoreJarName, "unknown") + ")!" + (isSuspectedToBeDueToNaming ? " This is probably because because UniMixins's jar name comes later alphabetically. Try renaming it to come first (for example, by adding a '!' character at the beginning of the file name)." : ""));
        }
        return Arrays.asList(new String[0]);
    }

    private static String stringOr(String s, String alternative) {
        return s == null ? alternative : s;
    }

    private static String getJarName(URL url) {
        int exclamationIdx;
        String preExclamationSubstring;
        int lastSlash;
        String urlStr;
        if (url != null && (urlStr = url.toString()).contains("!/") && (lastSlash = (preExclamationSubstring = urlStr.substring(0, exclamationIdx = urlStr.indexOf("!/"))).lastIndexOf(47)) != -1) {
            return preExclamationSubstring.substring(lastSlash + 1);
        }
        return null;
    }
}

