/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package makamys.mixingasm;

import io.github.legacymoddingmc.unimixins.mixingasm.MixingasmModule;
import io.github.legacymoddingmc.unimixins.mixingasm.repackage.common.abstraction.ComparableVersion;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import makamys.mixingasm.ConfigHelper;
import makamys.mixingasm.api.IMixinSafeTransformer;
import makamys.mixingasm.api.MixinSafeTransformer;
import makamys.mixingasm.api.TransformerInclusions;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.service.ITransformerProvider;
import org.spongepowered.asm.service.MixinService;

public class Mixingasm {
    public static final String MODID = "mixingasm";
    public static final Logger LOGGER = LogManager.getLogger((String)"mixingasm");

    public static void run() {
        MixingasmModule.init();
        List<String> badTransformers = Mixingasm.getBadTransformers();
        LOGGER.debug("Excluding transformers: " + badTransformers);
        for (String badTransformer : badTransformers) {
            Mixingasm.addTransformerExclusion(badTransformer);
        }
    }

    private static void addTransformerExclusion(String transformer) {
        String mixinVersion = (String)Launch.blackboard.get("mixin.initialised");
        if (mixinVersion != null && new ComparableVersion(mixinVersion).compareTo(new ComparableVersion("0.8.5")) >= 0) {
            ITransformerProvider transformers = MixinService.getService().getTransformerProvider();
            if (transformers != null) {
                transformers.addTransformerExclusion(transformer);
            }
        } else {
            MixinEnvironment.getCurrentEnvironment().addTransformerExclusion(transformer);
        }
    }

    private static boolean isValidClassPattern(String pattern) {
        return !pattern.startsWith(":");
    }

    private static List<String> getBadTransformers() {
        List<String> dynamicTransformerInclusionPatterns = TransformerInclusions.getTransformerInclusionList();
        LOGGER.debug("Dynamic transformer inclusion pattern list: " + dynamicTransformerInclusionPatterns);
        ArrayList<String> badTransformers = new ArrayList<String>();
        List transformerInclusionPatterns = Stream.of(Mixingasm.readConfig("transformer_inclusion_list_default.txt").stream(), Mixingasm.readConfig("transformer_inclusion_list.txt").stream(), dynamicTransformerInclusionPatterns.stream()).flatMap(i -> i).filter(Mixingasm::isValidClassPattern).collect(Collectors.toList());
        List transformerExclusionPatterns = Mixingasm.readConfig("transformer_exclusion_list.txt").stream().filter(Mixingasm::isValidClassPattern).collect(Collectors.toList());
        for (IClassTransformer trans : Launch.classLoader.getTransformers()) {
            String name = trans.getClass().getCanonicalName();
            boolean included = false;
            included = transformerInclusionPatterns.stream().anyMatch(p -> Mixingasm.patternMatches(name, p)) || trans instanceof IMixinSafeTransformer || trans.getClass().isAnnotationPresent(MixinSafeTransformer.class);
            if (included && transformerExclusionPatterns.stream().noneMatch(p -> Mixingasm.patternMatches(name, p))) {
                LOGGER.debug("      Trusting transformer " + name);
                continue;
            }
            LOGGER.debug("  Not trusting transformer " + name + (included ? " (because it was excluded via the config)" : ""));
            badTransformers.add(name);
        }
        return badTransformers;
    }

    private static boolean patternMatches(String str, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr.replace(".", "\\.").replace("*", ".*"));
        return pattern.matcher(str).matches();
    }

    private static List<String> readConfig(String name) {
        boolean overwrite;
        ConfigHelper helper = new ConfigHelper(MODID);
        File listFile = new File(Launch.minecraftHome, "config/mixingasm/" + name);
        listFile.getParentFile().mkdirs();
        List<String> lines = listFile.exists() ? Mixingasm.readConfigLines(listFile) : null;
        boolean bl = overwrite = lines != null && lines.contains(":replaceableFile");
        if (lines == null || overwrite) {
            helper.createDefaultConfigFileIfMissing(listFile, overwrite);
            lines = Mixingasm.readConfigLines(listFile);
        }
        return lines;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static List<String> readConfigLines(File file) {
        try (FileReader fr = new FileReader(file);){
            List<String> list = IOUtils.readLines((Reader)fr).stream().map(l -> l.contains("#") ? l.substring(0, l.indexOf(35)) : l).map(l -> l.trim()).filter(l -> !l.isEmpty()).collect(Collectors.toList());
            return list;
        }
        catch (Exception e) {
            System.out.println("Failed to read " + file);
            e.printStackTrace();
            return Arrays.asList(new String[0]);
        }
    }
}

