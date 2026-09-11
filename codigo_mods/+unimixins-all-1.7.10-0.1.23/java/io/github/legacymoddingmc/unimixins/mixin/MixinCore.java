/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.mixin;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import io.github.legacymoddingmc.unimixins.mixin.MixinModidDecorator;
import io.github.legacymoddingmc.unimixins.mixin.MixinSanityCheck;
import io.github.legacymoddingmc.unimixins.mixin.repackage.common.sanitycheck.SanityCheckHelper;
import java.util.Arrays;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
public class MixinCore
implements IFMLLoadingPlugin {
    public static final Logger LOGGER = LogManager.getLogger((String)"unimixins");

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
        MixinModidDecorator.apply();
    }

    public String getAccessTransformerClass() {
        return null;
    }

    static {
        if (SanityCheckHelper.isEnabled()) {
            SanityCheckHelper.showBigWarning(MixinSanityCheck.checkMixinHasInitialized(), MixinSanityCheck.checkMixinContainer(), SanityCheckHelper.checkIfJarPrefixesExist(Arrays.asList("gasstation-", "mixinbooterlegacy-", "spongemixins-")));
        }
    }
}

