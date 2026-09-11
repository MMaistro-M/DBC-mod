/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.FMLLaunchHandler
 */
package noppes.npcs.mixin;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import noppes.npcs.config.ConfigMixin;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class CustomNPCsMixinPlugin
implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        String configPath = "config" + File.separator + "CustomNpcPlus" + File.separator;
        ConfigMixin.init(new File(configPath + "mixin.cfg"));
        boolean client = FMLLaunchHandler.side().isClient();
        ArrayList<String> mixins = new ArrayList<String>();
        if (client) {
            if (ConfigMixin.EntityRendererMixin) {
                mixins.add("MixinEntityRenderer");
            }
            if (ConfigMixin.AnimationMixin) {
                mixins.add("MixinModelRenderer");
                mixins.add("MixinRendererLivingEntity");
            }
            if (ConfigMixin.FirstPersonAnimationMixin) {
                mixins.add("MixinItemRenderer");
            }
            mixins.add("MixinItemStack");
        }
        if (!client && ConfigMixin.EntitySpawnFixMixin) {
            mixins.add("MixinEntityTrackerEntry");
        }
        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}

