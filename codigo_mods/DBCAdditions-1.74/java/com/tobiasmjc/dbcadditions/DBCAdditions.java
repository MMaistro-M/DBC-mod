/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartedEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 */
package com.tobiasmjc.dbcadditions;

import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.DBCUtilsTab;
import com.tobiasmjc.dbcadditions.blocks.BlocksDBCAdditions;
import com.tobiasmjc.dbcadditions.client.ClientProxy;
import com.tobiasmjc.dbcadditions.client.gui.DBCAGuiHandler;
import com.tobiasmjc.dbcadditions.command.DimensionCommand;
import com.tobiasmjc.dbcadditions.command.FormMasteryCommand;
import com.tobiasmjc.dbcadditions.command.SkillCommand;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.dimensions.BeerusWorld;
import com.tobiasmjc.dbcadditions.dimensions.SacredWorldKai;
import com.tobiasmjc.dbcadditions.dimensions.UniversalArenaWorld;
import com.tobiasmjc.dbcadditions.dimensions.WorldGeneratorDBUtils;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;
import com.tobiasmjc.dbcadditions.entities.DBASpawnCheck;
import com.tobiasmjc.dbcadditions.entities.EntitiesDBCA;
import com.tobiasmjc.dbcadditions.event.DBCABlockBreakHandler;
import com.tobiasmjc.dbcadditions.event.DBCAClientHandler;
import com.tobiasmjc.dbcadditions.event.DBCAFMLHandler;
import com.tobiasmjc.dbcadditions.event.DBCALivingEvents;
import com.tobiasmjc.dbcadditions.event.DBCAPlayerEvents;
import com.tobiasmjc.dbcadditions.event.DBCAWorldEvents;
import com.tobiasmjc.dbcadditions.items.ItemsDBCUtils;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid="dbcadditions", version="1.0", dependencies="after:ninjinentities")
public class DBCAdditions {
    public static DBCAConfig DBCAConfig;
    public static final DBCAdditions instance;
    public static final String MODID = "dbcadditions";
    public static final String VERSION = "1.0";
    private final DBCAGuiHandler guiHandler = new DBCAGuiHandler();
    public static final Side side;
    public static final DBCUtilsTab CREATIVE_TAB;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemsDBCUtils.registerAll();
        DBCAForms.loadForms();
        DBCAForms.loadAuras();
        FormItemsDBA.loadFormItems();
        DBCARaces.registerRaces();
        DBCASkills.registerSkills();
        File configFile = event.getSuggestedConfigurationFile();
        DBCAConfig = new DBCAConfig(new Configuration(configFile), event.getModConfigurationDirectory().getPath() + "/dbcadditions/custom_forms", event.getModConfigurationDirectory().getPath() + "/dbcadditions/skills.cfg");
    }

    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        DBCAForms.FORMS = DBCAForms.HOST_FORMS;
        DBCASkills.SKILLS = DBCASkills.HOST_SKILLS;
        FormItemsDBA.FormItems = FormItemsDBA.FormItemsHost;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        DBUPackets.registerPackets();
        FMLCommonHandler.instance().bus().register((Object)new DBCAFMLHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DBCABlockBreakHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DBCAWorldEvents());
        MinecraftForge.EVENT_BUS.register((Object)new DBCALivingEvents());
        MinecraftForge.EVENT_BUS.register((Object)new DBCAPlayerEvents());
        GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGeneratorDBUtils(), (int)0);
        DimensionManager.registerProviderType((int)98, SacredWorldKai.class, (boolean)true);
        DimensionManager.registerDimension((int)98, (int)98);
        DimensionManager.registerProviderType((int)99, BeerusWorld.class, (boolean)true);
        DimensionManager.registerDimension((int)99, (int)99);
        DimensionManager.registerProviderType((int)100, UniversalArenaWorld.class, (boolean)true);
        DimensionManager.registerDimension((int)100, (int)100);
        BlocksDBCAdditions.registerAll();
        BiomeDBCUtils.registerAll();
        EntitiesDBCA.registerEntities();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)this.guiHandler);
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register((Object)new DBCAClientHandler());
            ClientProxy.registerRender();
        }
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new FormMasteryCommand());
        event.registerServerCommand((ICommand)new DimensionCommand());
        event.registerServerCommand((ICommand)new SkillCommand());
        DBASpawnCheck.initMasters();
    }

    static {
        instance = new DBCAdditions();
        side = FMLCommonHandler.instance().getEffectiveSide();
        CREATIVE_TAB = new DBCUtilsTab();
    }
}

