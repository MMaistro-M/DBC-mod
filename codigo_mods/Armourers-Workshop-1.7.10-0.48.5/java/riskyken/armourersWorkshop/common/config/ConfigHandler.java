/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 */
package riskyken.armourersWorkshop.common.config;

import java.io.File;
import java.util.UUID;
import net.minecraftforge.common.config.Configuration;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.common.update.UpdateCheck;

public class ConfigHandler {
    public static String CATEGORY_RECIPE = "recipe";
    public static String CATEGORY_GENERAL = "general";
    public static String CATEGORY_COMPATIBILITY = "compatibility";
    public static String CATEGORY_SERVER = "server";
    public static String CATEGORY_ENTITY_SKINS = "entity_skins";
    public static Configuration config;
    public static boolean disableRecipes;
    public static boolean disableDollRecipe;
    public static boolean disableSkinningRecipes;
    public static boolean hideDollFromCreativeTabs;
    public static boolean enableRecoveringSkins;
    public static int serverModelCacheTime;
    public static int serverSkinSendRate;
    public static boolean serverCompressesSkins;
    public static boolean extractOfficialSkins;
    public static boolean allowEquipmentWardrobe;
    public static boolean allowClientsToDownloadSkins;
    public static boolean allowClientsToUploadSkins;
    public static boolean enableHolidayEvents;
    public static int startingWardrobeSlots;
    public static boolean libraryShowsModelPreviews;
    public static boolean lockDyesOnSkins;
    public static boolean instancedDyeTable;
    public static int enitiySpawnWithSkinsChance;
    public static int entityDropSkinChance;
    public static String enitiySpawnSkinTargetPath;
    public static boolean allowModsToRegisterWithAPI;
    public static UUID remotePlayerId;
    public static int dropSkinsOnDeath;

    public static void init(File file) {
        if (config == null) {
            config = new Configuration(file, "1");
            ConfigHandler.loadConfigFile();
        }
    }

    public static void loadConfigFile() {
        ConfigHandler.loadCategoryGeneral();
        ConfigHandler.loadCategoryRecipe();
        ConfigHandler.loadCategoryCompatibility();
        ConfigHandler.loadCategoryServer();
        ConfigHandler.loadCategoryEntitySkins();
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static void loadCategoryEntitySkins() {
        enitiySpawnWithSkinsChance = config.getInt("enitiySpawnWithSkinsChance", CATEGORY_ENTITY_SKINS, 0, 0, 100, "Percentage change that entities will spawn with skins equipped.");
        entityDropSkinChance = config.getInt("entityDropSkinChance", CATEGORY_ENTITY_SKINS, 0, 0, 100, "Percentage change that entities will drop equipped skins when killed.");
        enitiySpawnSkinTargetPath = "/" + config.getString("enitiySpawnSkinTargetPath", CATEGORY_ENTITY_SKINS, "", "Target library path for skin spawned on entities.\nExamples: 'official/' for only skins in the official folder or 'downloads/' for skins in the downloads folder.\nLeave black for all skins.");
    }

    private static void loadCategoryGeneral() {
        allowClientsToDownloadSkins = config.get(CATEGORY_GENERAL, "allowClientsToDownloadSkins", false, "Allows clients to save skins from a server to their local computer using the library.").getBoolean(false);
        allowClientsToUploadSkins = config.get(CATEGORY_GENERAL, "allowClientsToUploadSkins", true, "Allows clients to load skins from their local computer onto the server using the library.").getBoolean(true);
        extractOfficialSkins = config.get(CATEGORY_GENERAL, "extractOfficialSkins", true, "Allow the mod to extract the official skins that come with the mod into the library folder.").getBoolean(true);
        UndoManager.maxUndos = config.get(CATEGORY_GENERAL, "maxUndos", 100, "Max number of undos a player has for block painting.").getInt(100);
        UpdateCheck.checkForUpdates = config.get(CATEGORY_GENERAL, "checkForUpdates", true, "Should the mod check for new versions?").getBoolean(true);
        dropSkinsOnDeath = config.get(CATEGORY_GENERAL, "dropSkinsOnDeath", 0, "Should skins be dropped on player death.\n0 = use keep inventory rule\n1 = never drop\n2 = always drop").getInt(0);
        allowEquipmentWardrobe = config.get(CATEGORY_GENERAL, "allowEquipmentWardrobe", true, "Allow the player to open the equipment wardrobe GUI.").getBoolean(true);
        enableHolidayEvents = config.get(CATEGORY_GENERAL, "enableHolidayEvents", true, "Setting to false will disable holiday events. What's wrong with you!").getBoolean(true);
        startingWardrobeSlots = config.getInt("startingWardrobeSlots", CATEGORY_GENERAL, 3, 1, 8, "Number of slot columns the player starts with for skins.");
        libraryShowsModelPreviews = config.getBoolean("libraryShowsModelPreviews", CATEGORY_GENERAL, true, "Shows model previews in the library.\nCauses a lot of extra load on servers.\nBest to turn off on high population servers");
        lockDyesOnSkins = config.getBoolean("lockDyesOnSkins", CATEGORY_GENERAL, false, "When enabled players will not be able to remove dyes from skins in the dye table.");
        instancedDyeTable = config.getBoolean("instancedDyeTable", CATEGORY_GENERAL, false, "If true the dye table will be instanced for each player. Items will be dropped when the table is closed.");
    }

    private static void loadCategoryRecipe() {
        disableRecipes = config.get(CATEGORY_RECIPE, "disableRecipes", false, "Disable vanilla recipes. Use if you want to manually add recipes for a mod pack.").getBoolean(false);
        disableDollRecipe = config.get(CATEGORY_RECIPE, "disableDollRecipe", false, "Disable hidden in world doll recipe.").getBoolean(false);
        disableSkinningRecipes = config.get(CATEGORY_RECIPE, "disableSkinningRecipes", false, "Disable skinning table recipes.").getBoolean(false);
        hideDollFromCreativeTabs = config.get(CATEGORY_RECIPE, "hideDollFromCreativeTabs", true, "Hides the doll block from the creative tab and NEI.").getBoolean(true);
        enableRecoveringSkins = config.get(CATEGORY_RECIPE, "enableRecoveringSkins", false, "Enable copying the skin off an item in the skinning table.").getBoolean(false);
    }

    private static void loadCategoryCompatibility() {
        allowModsToRegisterWithAPI = config.get(CATEGORY_COMPATIBILITY, "allowModsToRegisterWithAPI", true, "Allow other mods to register with the Armourer's Workshop API.").getBoolean(true);
    }

    private static void loadCategoryServer() {
        serverModelCacheTime = config.get(CATEGORY_SERVER, "serverModelCacheTime", 600000, "How long in ms the server will keep skins in it's cache.\nDefault 600000 ms is 10 minutes.").getInt(600000);
        serverSkinSendRate = config.getInt("serverModelSendRate", CATEGORY_SERVER, 4000, 0, 8000, "The maximum number of skins the server is allow to send every minute.\nLess that 1 equals unlimited. (not recommended may cause bandwidth and cpu spikes on the server)");
        serverCompressesSkins = config.getBoolean("serverCompressesSkins", CATEGORY_SERVER, true, "If enabled the server will compress skins before sending them to clients.\nHighly recommended unless the server has a very slow CPU.");
    }

    static {
        serverModelCacheTime = 600000;
        serverSkinSendRate = 4000;
        serverCompressesSkins = true;
        allowEquipmentWardrobe = true;
        allowClientsToDownloadSkins = false;
        allowClientsToUploadSkins = true;
        enableHolidayEvents = true;
        startingWardrobeSlots = 3;
        libraryShowsModelPreviews = true;
        lockDyesOnSkins = false;
        instancedDyeTable = false;
        enitiySpawnWithSkinsChance = 75;
        entityDropSkinChance = 10;
        enitiySpawnSkinTargetPath = "/";
        allowModsToRegisterWithAPI = true;
        dropSkinsOnDeath = 0;
    }
}

