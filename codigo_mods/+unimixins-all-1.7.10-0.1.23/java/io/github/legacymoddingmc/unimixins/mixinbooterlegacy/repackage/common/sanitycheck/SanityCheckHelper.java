/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.mixinbooterlegacy.repackage.common.sanitycheck;

import io.github.legacymoddingmc.unimixins.mixinbooterlegacy.repackage.common.config.ConfigUtil;
import io.github.legacymoddingmc.unimixins.mixinbooterlegacy.repackage.common.sanitycheck.SanityCheckConfig;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SanityCheckHelper {
    private static final Logger LOGGER = LogManager.getLogger((String)"unimixins");
    private static final Pattern LETTER = Pattern.compile("[a-z]");

    public static boolean isEnabled() {
        if (!SanityCheckConfig.enableIntegrityChecks && !Launch.blackboard.containsKey("unimixins.warnedAboutDisabledIntegrityChecks")) {
            LOGGER.debug("Skipping sanity checks because integrity checks are disabled in the config.");
            Launch.blackboard.put("unimixins.warnedAboutDisabledIntegrityChecks", "true");
        }
        return SanityCheckConfig.enableIntegrityChecks;
    }

    public static void showBigWarning(String warning) {
        SanityCheckHelper.showBigWarning(Arrays.asList(warning));
    }

    public static void showBigWarning(List<String> ... warningses) {
        ArrayList<String> allWarnings = new ArrayList<String>();
        for (List<String> l : warningses) {
            allWarnings.addAll(l);
        }
        if (!allWarnings.isEmpty()) {
            LOGGER.warn("=======================================================================================");
            LOGGER.warn("WARNING / WARNING / WARNING / WARNING / WARNING / WARNING / WARNING / WARNING / WARNING");
            LOGGER.warn("=======================================================================================");
            for (String w : allWarnings) {
                LOGGER.error(" - " + w);
            }
            LOGGER.error("The game will almost certainly crash!");
            LOGGER.fatal("======================================================================================");
            throw new Error(String.join((CharSequence)"; ", allWarnings));
        }
    }

    public static void warnIfJarPrefixesExist(List<String> concerningPrefixes) {
        SanityCheckHelper.showBigWarning(SanityCheckHelper.checkIfJarPrefixesExist(concerningPrefixes));
    }

    public static List<String> checkIfJarPrefixesExist(List<String> concerningPrefixes) {
        List<String> concerningJars = SanityCheckHelper.getJarsMatchingPrefix(concerningPrefixes);
        if (!concerningJars.isEmpty()) {
            return Arrays.asList("Detected incompatible jars: " + concerningJars);
        }
        return Arrays.asList(new String[0]);
    }

    private static List<String> getJarsMatchingPrefix(List<String> concerningPrefixes) {
        ArrayList<String> matches = new ArrayList<String>();
        for (URL url : Launch.classLoader.getSources()) {
            int firstLetterIndex;
            String[] components;
            String name;
            Matcher matcher;
            String path = url.getPath();
            if (!path.endsWith(".jar") || !(matcher = LETTER.matcher(name = (components = path.split("/"))[components.length - 1].toLowerCase())).find() || !SanityCheckHelper.anyPrefixesMatch(name.substring(firstLetterIndex = matcher.start()), concerningPrefixes)) continue;
            matches.add(name);
        }
        return matches;
    }

    private static boolean anyPrefixesMatch(String s, Collection<String> prefixes) {
        for (String p : prefixes) {
            if (!s.startsWith(p)) continue;
            return true;
        }
        return false;
    }

    static {
        ConfigUtil.load(SanityCheckConfig.class);
    }
}

