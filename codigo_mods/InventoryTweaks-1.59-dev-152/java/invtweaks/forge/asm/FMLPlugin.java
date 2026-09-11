/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$TransformerExclusions
 */
package invtweaks.forge.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions(value={"invtweaks.forge.asm", "invtweaks.forge.asm.compatibility"})
@IFMLLoadingPlugin.MCVersion(value="")
public class FMLPlugin
implements IFMLLoadingPlugin {
    public static boolean runtimeDeobfEnabled = false;

    public String[] getASMTransformerClass() {
        return new String[]{"invtweaks.forge.asm.ContainerTransformer"};
    }

    public String getAccessTransformerClass() {
        return "invtweaks.forge.asm.ITAccessTransformer";
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        runtimeDeobfEnabled = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }
}

