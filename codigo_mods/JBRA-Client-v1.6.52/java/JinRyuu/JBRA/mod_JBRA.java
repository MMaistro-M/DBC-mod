/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraftforge.common.MinecraftForge
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.JBRA;
import JinRyuu.JBRA.JBRAClient;
import JinRyuu.JBRA.JBRAEH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.Properties;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="jinryuubetterrenderaddon", name="JinRyuu's Better Render Addon", version="1.6.52", dependencies="required-after:jinryuujrmcore;after:optifine;after:OptiFine")
public class mod_JBRA {
    private CharSequence u5Mp4X = "cks";
    public static boolean a6P9H9B = true;
    public String Fa3kf = "ze";
    public static boolean skinLoaded = false;
    public static final String MOD = "JinRyuu's Better Render Addon";
    @SidedProxy(clientSide="JinRyuu.JBRA.JBRAClient", serverSide="JinRyuu.JBRA.JBRA")
    public static JBRA proxy;
    public static Properties runtimeIdProperties;
    protected String VsSx2L = "dpa";
    public CharSequence GaShr = "ro-s";
    public Object Sdmj8UG = "mo";

    private String getVersion() {
        return "1.6.52";
    }

    @Mod.EventHandler
    public void PreLoad(FMLPreInitializationEvent event) {
        proxy.registerTicks();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            JBRAEH events = new JBRAEH();
            MinecraftForge.EVENT_BUS.register((Object)events);
            FMLCommonHandler.instance().bus().register((Object)events);
        }
        proxy.registerRenderThings();
    }

    @Mod.EventHandler
    public void PostLoad(FMLPostInitializationEvent event) {
        proxy.postInit();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            String s = this.Sdmj8UG + this.VsSx2L + this.u5Mp4X;
            String f = this.Fa3kf + this.GaShr;
            if (JBRAClient.mc.field_71412_D.getAbsolutePath().contains(s)) {
                a6P9H9B = JBRAClient.mc.field_71412_D.getAbsolutePath().contains(f);
            }
        }
    }

    static {
        runtimeIdProperties = new Properties();
    }
}

