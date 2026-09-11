/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.apache.commons.io.IOUtils
 */
package io.github.legacymoddingmc.unimixins.gtnhmixins.asm;

import io.github.legacymoddingmc.unimixins.gtnhmixins.GTNHMixinsModule;
import io.github.legacymoddingmc.unimixins.gtnhmixins.util.LaunchClassLoaderUtils;
import java.io.IOException;
import java.io.InputStream;
import makamys.mixingasm.api.MixinSafeTransformer;
import makamys.mixingasm.api.TransformerInclusions;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.IOUtils;

@MixinSafeTransformer
public class LegacyGTNHMixinExtrasGenerator
implements IClassTransformer {
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null && name.startsWith("com.gtnewhorizon.mixinextras") && !name.startsWith("org.apache.commons.io.")) {
            try {
                GTNHMixinsModule.LOGGER.trace("Generating class " + name);
                byte[] bytes = this.getClassBytes(name);
                if (bytes != null) {
                    LaunchClassLoaderUtils.putInResourceCache(name, bytes);
                    return bytes;
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to generate class " + name);
            }
        }
        return basicClass;
    }

    private byte[] getClassBytes(String name) throws IOException {
        String resourcePath = "/data/legacy_gtnh_mixinextras/" + name.replace('.', '/').concat(".klass");
        return IOUtils.toByteArray((InputStream)LegacyGTNHMixinExtrasGenerator.class.getResourceAsStream(resourcePath));
    }

    static {
        if (LegacyGTNHMixinExtrasGenerator.class.getResource("/makamys/mixingasm/api/TransformerInclusions.class") != null && LegacyGTNHMixinExtrasGenerator.class.getResource("/makamys/mixingasm/api/MixinSafeTransformer.class") == null) {
            TransformerInclusions.getTransformerInclusionList().add("io.github.legacymoddingmc.unimixins.compat.asm.LegacyGTNHMixinExtrasGenerator");
        }
    }
}

