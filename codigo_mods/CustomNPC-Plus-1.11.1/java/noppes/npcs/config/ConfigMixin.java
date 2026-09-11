/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  org.apache.logging.log4j.Level
 */
package noppes.npcs.config;

import cpw.mods.fml.common.FMLLog;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;

public class ConfigMixin {
    public static Configuration config;
    public static final String CLIENT = "CLIENT";
    public static final String SERVER = "SERVER";
    public static final String GENERAL = "GENERAL";
    public static Property EntityRendererMixinProperty;
    public static boolean EntityRendererMixin;
    public static Property AnimationMixinProperty;
    public static boolean AnimationMixin;
    public static Property FirstPersonAnimationMixinProperty;
    public static boolean FirstPersonAnimationMixin;
    public static Property EntitySpawnFixMixinProperty;
    public static boolean EntitySpawnFixMixin;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            EntityRendererMixinProperty = config.get(CLIENT, "Entity Render Mixin", true, "Enables Overlay Mixins for Conflicts relating to Optifine or other Skin Renderers. If crashes occur, please disable.");
            EntityRendererMixin = EntityRendererMixinProperty.getBoolean(true);
            AnimationMixinProperty = config.get(CLIENT, "Animation Mixin", true, "Enables mixins for the ModelRenderer and RenderPlayer classes, allowing for additional animation functionality in the API. If crashes or visual errors occur, please disable.");
            AnimationMixin = AnimationMixinProperty.getBoolean(true);
            if (AnimationMixin) {
                FirstPersonAnimationMixinProperty = config.get(CLIENT, "First Person Animation Mixin", true, "Enables mixins for the ItemRenderer class, allowing for animations to be visible in first person. Can only be enabled if the animation mixin is enabled. If crashes or visual errors occur, please disable.");
                FirstPersonAnimationMixin = FirstPersonAnimationMixinProperty.getBoolean(true);
            } else {
                FirstPersonAnimationMixin = false;
            }
            EntitySpawnFixMixinProperty = config.get(SERVER, "Entity Spawn Packet Fix", true, "Fixes a Forge race condition where mod entity spawn packets can be lost or sent to the wrong player. This prevents invisible entities on servers with multiple players.");
            EntitySpawnFixMixin = EntitySpawnFixMixinProperty.getBoolean(true);
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its mixin configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        EntityRendererMixin = true;
        AnimationMixin = true;
        FirstPersonAnimationMixin = true;
        EntitySpawnFixMixin = true;
    }
}

