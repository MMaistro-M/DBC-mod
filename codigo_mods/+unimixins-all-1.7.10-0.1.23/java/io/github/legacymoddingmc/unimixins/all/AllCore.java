/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.all;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
public class AllCore
implements IFMLLoadingPlugin {
    public static final Logger LOGGER = LogManager.getLogger((String)"unimixins");
    private static List<Class<?>> embeddedCorePluginClasses = new ArrayList();
    private static List<IFMLLoadingPlugin> embeddedCorePluginInstances = new ArrayList<IFMLLoadingPlugin>();

    public AllCore() {
        LOGGER.info("Instantiating AllCore");
        for (Class<?> cls : embeddedCorePluginClasses) {
            try {
                embeddedCorePluginInstances.add((IFMLLoadingPlugin)cls.newInstance());
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String[] getASMTransformerClass() {
        ArrayList<String> classes = new ArrayList<String>();
        for (IFMLLoadingPlugin p : embeddedCorePluginInstances) {
            for (String s : p.getASMTransformerClass()) {
                classes.add(s);
            }
        }
        return classes.toArray(new String[0]);
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        for (IFMLLoadingPlugin p : embeddedCorePluginInstances) {
            p.injectData(data);
        }
    }

    public String getAccessTransformerClass() {
        return "io.github.legacymoddingmc.unimixins.all.AllCore$CombinedAccessTransformer";
    }

    static {
        try {
            for (String s : IOUtils.toString((URL)AllCore.class.getResource("/META-INF/unimixins-all.EmbeddedFMLCorePlugins.txt")).split(" ")) {
                Class<?> cls = Class.forName(s);
                embeddedCorePluginClasses.add(cls);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class CombinedAccessTransformer
    implements IClassTransformer {
        private final List<IClassTransformer> delegates = new ArrayList<IClassTransformer>();

        public CombinedAccessTransformer() {
            for (IFMLLoadingPlugin plugin : embeddedCorePluginInstances) {
                String atClass = plugin.getAccessTransformerClass();
                if (atClass == null) continue;
                try {
                    this.delegates.add((IClassTransformer)Class.forName(atClass).newInstance());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public byte[] transform(String name, String transformedName, byte[] basicClass) {
            for (IClassTransformer delegate : this.delegates) {
                basicClass = delegate.transform(name, transformedName, basicClass);
            }
            return basicClass;
        }

        public String toString() {
            return this.delegates.toString();
        }
    }
}

