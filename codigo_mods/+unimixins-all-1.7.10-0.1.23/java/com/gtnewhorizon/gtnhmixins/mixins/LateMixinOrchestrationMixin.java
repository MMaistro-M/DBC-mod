/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.Runnables
 *  cpw.mods.fml.common.LoadController
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.LoaderState
 *  cpw.mods.fml.common.ModClassLoader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.discovery.ASMDataTable
 *  cpw.mods.fml.common.discovery.ASMDataTable$ASMData
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.commons.lang3.reflect.FieldUtils
 */
package com.gtnewhorizon.gtnhmixins.mixins;

import com.google.common.util.concurrent.Runnables;
import com.gtnewhorizon.gtnhmixins.GTNHMixins;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.Reflection;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.discovery.ASMDataTable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.mixin.transformer.Proxy;

@Mixin(value={LoadController.class}, remap=false)
public class LateMixinOrchestrationMixin {
    @Inject(method={"distributeStateMessage(Lcpw/mods/fml/common/LoaderState;[Ljava/lang/Object;)V"}, at={@At(value="HEAD")})
    private void beforeConstructing(LoaderState state, Object[] eventData, CallbackInfo ci) throws Throwable {
        if (state != LoaderState.CONSTRUCTING) {
            return;
        }
        GTNHMixins.log("Searching ASMData for ILateMixinLoaders");
        ModClassLoader modClassLoader = (ModClassLoader)eventData[0];
        ASMDataTable asmDataTable = (ASMDataTable)eventData[1];
        Loader loader = Loader.instance();
        HashSet<Object> loadedModsTemp = new HashSet<Object>();
        loadedModsTemp.addAll(loader.getIndexedModList().keySet());
        loadedModsTemp.addAll(LateMixinOrchestrationMixin.getLiteLoaderMods());
        Set<String> loadedMods = Collections.unmodifiableSet(loadedModsTemp);
        GTNHMixins.log("LoadedMods {}", loadedMods.toString());
        for (ASMDataTable.ASMData asmData : asmDataTable.getAll(LateMixin.class.getName())) {
            ArrayList<String> mixins;
            modClassLoader.addFile(asmData.getCandidate().getModContainer());
            String mixinClassName = asmData.getClassName().replace('/', '.');
            Class<?> lateMixinClass = Class.forName(mixinClassName);
            if (!ILateMixinLoader.class.isAssignableFrom(lateMixinClass)) {
                GTNHMixins.LOGGER.error("Class {} has the @LateMixin annotation, but does not implement the ILateMixinLoader interface!", new Object[]{mixinClassName});
                continue;
            }
            ILateMixinLoader lateLoader = (ILateMixinLoader)lateMixinClass.newInstance();
            GTNHMixins.log("Loading mixins from ILateMixinLoader [{}]", lateLoader.getClass().getName());
            String mixinConfig = lateLoader.getMixinConfig();
            Config config = Config.create(mixinConfig, null);
            Object o = Reflection.mixinClassesField.get(Reflection.configField.get(config));
            if (o instanceof List) {
                mixins = (ArrayList<String>)o;
            } else {
                mixins = new ArrayList<String>();
                Reflection.mixinClassesField.set(Reflection.configField.get(config), mixins);
            }
            mixins.addAll(lateLoader.getMixins(loadedMods));
            for (String mixin : mixins) {
                GTNHMixins.log("Loading [{}] {}", mixinConfig, mixin);
            }
            Reflection.registerConfigurationMethod.invoke(null, config);
        }
        Launch.blackboard.getOrDefault("unimixins.mixinModidDecorator.refresh", Runnables.doNothing()).run();
        for (ModContainer container : loader.getActiveModList()) {
            modClassLoader.addFile(container.getSource());
        }
        Reflection.setDelegatedTransformersField(null);
        Field transformerField = Proxy.class.getDeclaredField("transformer");
        transformerField.setAccessible(true);
        Object transformer = transformerField.get(Launch.classLoader.getTransformers().stream().filter(Proxy.class::isInstance).findFirst().get());
        MixinEnvironment env = MixinEnvironment.getCurrentEnvironment();
        Reflection.invokeSelectConfigs(transformer, env);
        Reflection.invokePrepareConfigs(transformer, env);
    }

    private static Set<String> getLiteLoaderMods() {
        HashSet<String> mods = new HashSet<String>();
        try {
            Class<?> LiteLoaderTweaker = Class.forName("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
            Method hasValidMetaData = Class.forName("com.mumfrey.liteloader.interfaces.LoadableMod").getMethod("hasValidMetaData", new Class[0]);
            Object instance = FieldUtils.readDeclaredStaticField(LiteLoaderTweaker, (String)"instance", (boolean)true);
            Object bootstrap = FieldUtils.readDeclaredField((Object)instance, (String)"bootstrap", (boolean)true);
            Object enumerator = FieldUtils.readDeclaredField((Object)bootstrap, (String)"enumerator", (boolean)true);
            Map enabledContainers = (Map)FieldUtils.readDeclaredField((Object)enumerator, (String)"enabledContainers", (boolean)true);
            GTNHMixins.log("LiteLoader present, adding its mods to the list");
            for (Map.Entry e : enabledContainers.entrySet()) {
                if (!((Boolean)hasValidMetaData.invoke(e.getValue(), new Object[0])).booleanValue()) continue;
                mods.add((String)e.getKey());
            }
        }
        catch (ClassNotFoundException e) {
            GTNHMixins.log("LiteLoader not present");
        }
        catch (Exception e) {
            GTNHMixins.LOGGER.error("Failed to get LiteLoader mods.", (Throwable)e);
        }
        return mods;
    }
}

