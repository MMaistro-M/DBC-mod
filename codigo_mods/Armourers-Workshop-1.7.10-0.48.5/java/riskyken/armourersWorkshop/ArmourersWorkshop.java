/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.event.FMLServerStoppedEvent
 *  net.minecraft.command.ICommand
 *  net.minecraft.creativetab.CreativeTabs
 */
package riskyken.armourersWorkshop;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.command.CommandArmourers;
import riskyken.armourersWorkshop.common.creativetab.CreativeTabArmourersWorkshop;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.proxies.CommonProxy;
import riskyken.armourersWorkshop.utils.ModLogger;

@Mod(modid="armourersWorkshop", name="Armourer's Workshop", version="1.7.10-0.48.5", guiFactory="riskyken.armourersWorkshop.client.gui.ModGuiFactory")
public class ArmourersWorkshop {
    @Mod.Instance(value="armourersWorkshop")
    public static ArmourersWorkshop instance;
    @SidedProxy(clientSide="riskyken.armourersWorkshop.proxies.ClientProxy", serverSide="riskyken.armourersWorkshop.proxies.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabArmourersWorkshop tabArmorersWorkshop;

    @Mod.EventHandler
    public void perInit(FMLPreInitializationEvent event) {
        ModLogger.log(String.format("Loading %s version %s", "Armourer's Workshop", "1.7.10-0.48.5"));
        proxy.preInit(event);
        proxy.initLibraryManager();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.init(event);
        proxy.registerKeyBindings();
        proxy.initRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandArmourers());
        CommonSkinCache.INSTANCE.serverStarted();
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        CommonSkinCache.INSTANCE.serverStopped();
    }

    @Mod.EventHandler
    public void processIMC(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage imcMessage : event.getMessages()) {
            if (!imcMessage.isStringMessage() || !imcMessage.key.equalsIgnoreCase("register")) continue;
            ModLogger.log(String.format("Receiving registration request from %s for class %s", imcMessage.getSender(), imcMessage.getStringValue()));
            ApiRegistrar.INSTANCE.addApiRequest(imcMessage.getSender(), imcMessage.getStringValue());
        }
    }

    public static boolean isDedicated() {
        return proxy.getClass() == CommonProxy.class;
    }

    public static CommonProxy getProxy() {
        return proxy;
    }

    public static ArmourersWorkshop getInstance() {
        return instance;
    }

    static {
        tabArmorersWorkshop = new CreativeTabArmourersWorkshop(CreativeTabs.getNextID(), "armourersWorkshop".toLowerCase());
    }
}

