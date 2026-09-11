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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import noppes.npcs.CustomNpcs;
import noppes.npcs.config.legacy.LegacyConfig;
import org.apache.logging.log4j.Level;

public class ConfigMain {
    public static Configuration config;
    public static final String GENERAL = "General";
    public static final String NPC = "NPC";
    public static final String UPDATE = "Update";
    public static final String PARTY = "PARTY";
    public static final String PROFILES = "Profile";
    public static final String ATTRIBUTES = "Attributes";
    public static Property EnableUpdateCheckerProperty;
    public static boolean EnableUpdateChecker;
    public static Property DisableEnchantsProperty;
    public static boolean DisableEnchants;
    public static Property EnchantStartIdProperty;
    public static int EnchantStartId;
    public static Property LeavesDecayEnabledProperty;
    public static boolean LeavesDecayEnabled;
    public static Property VineGrowthEnabledProperty;
    public static boolean VineGrowthEnabled;
    public static Property IceMeltsEnabledProperty;
    public static boolean IceMeltsEnabled;
    public static Property SoulStoneAnimalsProperty;
    public static boolean SoulStoneAnimals;
    public static Property SoulStoneVillagersProperty;
    public static boolean SoulStoneVillagers;
    public static Property SoulStoneNPCsProperty;
    public static boolean SoulStoneNPCs;
    public static Property SoulStoneFriendlyNPCsProperty;
    public static boolean SoulStoneFriendlyNPCs;
    public static Property DatFormatProperty;
    public static boolean DatFormat;
    public static Property MarketDatFormatProperty;
    public static boolean MarketDatFormat;
    public static Property PartiesEnabledProperty;
    public static boolean PartiesEnabled;
    public static Property PartyFriendlyFireEnabledProperty;
    public static boolean PartyFriendlyFireEnabled;
    public static Property DefaultMinPartySizeProperty;
    public static int DefaultMinPartySize;
    public static Property DefaultMaxPartySizeProperty;
    public static int DefaultMaxPartySize;
    public static boolean AttributesEnabled;
    public static int AttributesCriticalBoost;
    public static boolean ProfilesEnabled;
    public static boolean AllowProfileBackups;
    public static int ProfileBackupAmount;
    public static Property RegionProfileSwitchingProperty;
    public static boolean RegionProfileSwitching;
    public static Property RestrictedProfileRegionsProperty;
    public static List<List<Integer>> RestrictedProfileRegions;
    public static int DefaultProfileSlots;
    public static Property OpsOnlyProperty;
    public static boolean OpsOnly;
    public static Property NpcNavRangeProperty;
    public static int NpcNavRange;
    public static Property NpcSizeLimitProperty;
    public static int NpcSizeLimit;
    public static Property DefaultInteractLineProperty;
    public static String DefaultInteractLine;
    public static Property ChunkLoadersProperty;
    public static int ChunkLoaders;
    public static Property DialogImageLimitProperty;
    public static int DialogImageLimit;
    public static Property SkinOverlayLimitProperty;
    public static int SkinOverlayLimit;
    public static Property NpcUseOpCommandsProperty;
    public static boolean NpcUseOpCommands;
    public static Property HitBoxScaleMaxProperty;
    public static int HitBoxScaleMax;
    public static Property TrackedQuestUpdateFrequencyProperty;
    public static int TrackedQuestUpdateFrequency;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            EnableUpdateCheckerProperty = config.get(GENERAL, "Enables Update Checker", true);
            EnableUpdateChecker = EnableUpdateCheckerProperty.getBoolean(true);
            DisableEnchantsProperty = config.get(GENERAL, "Disable Enchants", false);
            DisableEnchants = DisableEnchantsProperty.getBoolean(false);
            EnchantStartIdProperty = config.get(GENERAL, "Enchant Start ID", 100, "Start ID for enchants. IDs can only range from 0-256");
            EnchantStartId = EnchantStartIdProperty.getInt(100);
            LeavesDecayEnabledProperty = config.get(GENERAL, "Leaves Decay Enabled", true);
            LeavesDecayEnabled = LeavesDecayEnabledProperty.getBoolean(true);
            VineGrowthEnabledProperty = config.get(GENERAL, "Vine Growth Enabled", true);
            VineGrowthEnabled = VineGrowthEnabledProperty.getBoolean(true);
            IceMeltsEnabledProperty = config.get(GENERAL, "Ice Melt Enabled", true);
            IceMeltsEnabled = IceMeltsEnabledProperty.getBoolean(true);
            SoulStoneAnimalsProperty = config.get(GENERAL, "Normal playes can use soulstone on Animals", true);
            SoulStoneAnimals = SoulStoneAnimalsProperty.getBoolean(true);
            SoulStoneVillagersProperty = config.get(GENERAL, "Normal playes can use soulstone on Villagers", true);
            SoulStoneVillagers = SoulStoneVillagersProperty.getBoolean(true);
            SoulStoneNPCsProperty = config.get(GENERAL, "Normal playes can use soulstone on NPCs", false);
            SoulStoneNPCs = SoulStoneNPCsProperty.getBoolean(false);
            SoulStoneFriendlyNPCsProperty = config.get(GENERAL, "Normal playes can use soulstone on Friendly NPCs", false);
            SoulStoneFriendlyNPCs = SoulStoneFriendlyNPCsProperty.getBoolean(false);
            DatFormatProperty = config.get(GENERAL, "Dat Format for PlayerData", false, "You need to use '/kamkeel config playerdata' to convert existing playerdata to new format.");
            DatFormat = DatFormatProperty.getBoolean(false);
            MarketDatFormatProperty = config.get(GENERAL, "Dat Format for Market", false, "You need to use '/kamkeel config market' to convert existing market to new format.");
            MarketDatFormat = MarketDatFormatProperty.getBoolean(false);
            NpcNavRangeProperty = config.get(NPC, "NPC Navigation Range", 32, "Navigation search range for NPCs. Not recommended to increase if you have a slow pc or on a server. Minimum of 16, maximum of 96.");
            NpcNavRange = NpcNavRangeProperty.getInt(32);
            OpsOnlyProperty = config.get(NPC, "Only Ops Edit NPCs", false, "Only ops can create and edit npcs");
            OpsOnly = OpsOnlyProperty.getBoolean(false);
            NpcUseOpCommandsProperty = config.get(NPC, "NPC Use Op Commands", false, "Set to true if you want the dialog command option to be able to use op commands like tp etc");
            NpcUseOpCommands = NpcUseOpCommandsProperty.getBoolean(false);
            DefaultInteractLineProperty = config.get(NPC, "Default Interaction Line", "Hello @p", "Default interact line. Leave empty to not have one");
            DefaultInteractLine = DefaultInteractLineProperty.getString();
            ChunkLoadersProperty = config.get(NPC, "Chunk Loader Limit", 20, "Number of chunk loading npcs that can be active at the same time");
            ChunkLoaders = ChunkLoadersProperty.getInt(20);
            DialogImageLimitProperty = config.get(NPC, "Dialog Image Limit", 10, "The maximum number of images any dialog can hold.");
            DialogImageLimit = DialogImageLimitProperty.getInt(10);
            NpcSizeLimitProperty = config.get(NPC, "NPC Size Limit", 100, "Size limit for NPCs. Default 100, larger sizes may cause lag on clients and servers that can't take it!");
            NpcSizeLimit = NpcSizeLimitProperty.getInt(100);
            SkinOverlayLimitProperty = config.get(NPC, "Skin Overlay Limit", 10, "The maximum number of overlays any npc/player can hold.");
            SkinOverlayLimit = SkinOverlayLimitProperty.getInt(10);
            HitBoxScaleMaxProperty = config.get(NPC, "HitBox Scale Limit", 15, "The maximum scale factor for a custom hitbox");
            HitBoxScaleMax = HitBoxScaleMaxProperty.getInt(15);
            PartiesEnabledProperty = config.get(PARTY, "Parties Enabled", true, "Determines whether the party system is enabled or not.");
            PartiesEnabled = PartiesEnabledProperty.getBoolean();
            PartyFriendlyFireEnabledProperty = config.get(PARTY, "Party Friendly Fire", true, "Determines whether friendly fire can be toggled in parties.");
            PartyFriendlyFireEnabled = PartyFriendlyFireEnabledProperty.getBoolean();
            DefaultMinPartySizeProperty = config.get(PARTY, "Default Min Party Size", 1, "When creating a new Quest sets the default min party size");
            DefaultMinPartySize = DefaultMinPartySizeProperty.getInt(4);
            DefaultMaxPartySizeProperty = config.get(PARTY, "Default Max Party Size", 4, "When creating a new Quest sets the default max party size");
            DefaultMaxPartySize = DefaultMaxPartySizeProperty.getInt(4);
            TrackedQuestUpdateFrequencyProperty = config.get(UPDATE, "Tracked Quest Update Frequency", 5, "How often in seconds to update a players tracked quest. [Only applies to Item Quest currently]");
            TrackedQuestUpdateFrequency = TrackedQuestUpdateFrequencyProperty.getInt(5);
            ProfilesEnabled = config.get(PROFILES, "Enable Profiles", true, "Allow the use of character Profiles").getBoolean(true);
            AllowProfileBackups = config.get(PROFILES, "Enable Profile Backups", true, "Will create backups of profile changes").getBoolean(true);
            ProfileBackupAmount = config.get(PROFILES, "Number of Backups", 5, "How many backups per player to save").getInt(5);
            RegionProfileSwitchingProperty = config.get(PROFILES, "Region Profile Switching", false, "If true, only allows profile switching in certain regions.");
            RegionProfileSwitching = RegionProfileSwitchingProperty.getBoolean(false);
            RestrictedProfileRegionsProperty = config.get(PROFILES, "Restricted Profile Regions", new String[]{"0, 100, 64, 100, 200, 80, 200", "1, 50, 60, 50, 150, 75, 150"}, "List of restricted regions where profile switching is enabled. Format: DIM, X1, Y1, Z1, X2, Y2, Z2");
            DefaultProfileSlots = config.get(PROFILES, "Default Profile Slots", 5, "This is the default amount without any permission handling").getInt(5);
            RestrictedProfileRegions.clear();
            for (String region : RestrictedProfileRegionsProperty.getStringList()) {
                String[] parts = region.split(", ");
                ArrayList<Integer> regionList = new ArrayList<Integer>();
                for (String part : parts) {
                    regionList.add(Integer.parseInt(part));
                }
                RestrictedProfileRegions.add(regionList);
            }
            config.setCategoryPropertyOrder(ATTRIBUTES, new ArrayList<String>(Arrays.asList("Enable Attributes", "Critical Amount")));
            AttributesEnabled = config.get(ATTRIBUTES, "Enable Attributes", true, "Allows Attributes to be applied to Items and Armors").getBoolean(true);
            AttributesCriticalBoost = config.get(ATTRIBUTES, "Critical Amount", 100, "The boost in damage received by achieving a critical hit. Takes an number so 100 is 100% extra damage").getInt(100);
            if (CustomNpcs.legacyExist) {
                EnableUpdateChecker = LegacyConfig.EnableUpdateChecker;
                EnableUpdateCheckerProperty.set(EnableUpdateChecker);
                DisableEnchants = LegacyConfig.DisableEnchants;
                DisableEnchantsProperty.set(DisableEnchants);
                EnchantStartId = LegacyConfig.EnchantStartId;
                EnchantStartIdProperty.set(EnchantStartId);
                LeavesDecayEnabled = LegacyConfig.LeavesDecayEnabled;
                LeavesDecayEnabledProperty.set(LeavesDecayEnabled);
                VineGrowthEnabled = LegacyConfig.VineGrowthEnabled;
                VineGrowthEnabledProperty.set(VineGrowthEnabled);
                IceMeltsEnabled = LegacyConfig.IceMeltsEnabled;
                IceMeltsEnabledProperty.set(IceMeltsEnabled);
                SoulStoneAnimals = LegacyConfig.SoulStoneAnimals;
                SoulStoneAnimalsProperty.set(SoulStoneAnimals);
                SoulStoneVillagers = LegacyConfig.SoulStoneVillagers;
                SoulStoneVillagersProperty.set(SoulStoneVillagers);
                SoulStoneNPCs = LegacyConfig.SoulStoneNPCs;
                SoulStoneNPCsProperty.set(SoulStoneNPCs);
                SoulStoneFriendlyNPCs = LegacyConfig.SoulStoneFriendlyNPCs;
                SoulStoneFriendlyNPCsProperty.set(SoulStoneFriendlyNPCs);
                OpsOnly = LegacyConfig.OpsOnly;
                OpsOnlyProperty.set(OpsOnly);
                NpcNavRange = LegacyConfig.NpcNavRange;
                NpcNavRangeProperty.set(NpcNavRange);
                NpcSizeLimit = LegacyConfig.NpcSizeLimit;
                NpcSizeLimitProperty.set(NpcSizeLimit);
                DefaultInteractLine = LegacyConfig.DefaultInteractLine;
                DefaultInteractLineProperty.set(DefaultInteractLine);
                ChunkLoaders = LegacyConfig.ChunkLoaders;
                ChunkLoadersProperty.set(ChunkLoaders);
                DialogImageLimit = LegacyConfig.DialogImageLimit;
                DialogImageLimitProperty.set(DialogImageLimit);
                SkinOverlayLimit = LegacyConfig.SkinOverlayLimit;
                SkinOverlayLimitProperty.set(SkinOverlayLimit);
                NpcUseOpCommands = LegacyConfig.NpcUseOpCommands;
                NpcUseOpCommandsProperty.set(NpcUseOpCommands);
            }
            if (NpcNavRange < 16) {
                NpcNavRange = 16;
            }
            if (NpcNavRange > 96) {
                NpcNavRange = 96;
            }
            if (NpcSizeLimit < 1) {
                NpcSizeLimit = 1;
            }
            if (DialogImageLimit < 0) {
                DialogImageLimit = 0;
            }
            if (SkinOverlayLimit < 0) {
                SkinOverlayLimit = 0;
            }
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its main configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        EnableUpdateChecker = true;
        DisableEnchants = false;
        EnchantStartId = 100;
        LeavesDecayEnabled = true;
        VineGrowthEnabled = true;
        IceMeltsEnabled = true;
        SoulStoneAnimals = true;
        SoulStoneVillagers = false;
        SoulStoneNPCs = false;
        SoulStoneFriendlyNPCs = false;
        DatFormat = false;
        MarketDatFormat = false;
        DefaultMinPartySize = 1;
        DefaultMaxPartySize = 4;
        AttributesEnabled = true;
        AttributesCriticalBoost = 100;
        ProfilesEnabled = true;
        AllowProfileBackups = true;
        ProfileBackupAmount = 5;
        RegionProfileSwitching = false;
        RestrictedProfileRegions = new ArrayList<List<Integer>>();
        DefaultProfileSlots = 5;
        OpsOnly = false;
        NpcNavRange = 32;
        NpcSizeLimit = 100;
        DefaultInteractLine = "Hello @p";
        ChunkLoaders = 20;
        DialogImageLimit = 10;
        SkinOverlayLimit = 10;
        NpcUseOpCommands = false;
        HitBoxScaleMax = 15;
        TrackedQuestUpdateFrequency = 5;
    }
}

