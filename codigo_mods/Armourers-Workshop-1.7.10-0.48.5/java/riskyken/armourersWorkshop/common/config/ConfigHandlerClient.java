/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 */
package riskyken.armourersWorkshop.common.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandlerClient {
    public static String CATEGORY_CLIENT = "client";
    public static String CATEGORY_SKIN_PREVIEW = "skin-preview";
    public static String CATEGORY_DEBUG = "debug";
    public static int clientModelCacheTime = 600000;
    public static int clientTextureCacheTime = 600000;
    public static int fastCacheSize;
    public static int maxSkinRenderDistance;
    public static int maxModelBakingThreads;
    public static boolean multipassSkinRendering;
    public static int mannequinMaxEquipmentRenderDistance;
    public static int blockSkinMaxRenderDistance;
    public static double lodDistance;
    public static int skinLoadAnimationTime;
    public static int maxLodLevels;
    public static boolean skinPreEnabled;
    public static boolean skinPreDrawBackground;
    public static float skinPreSize;
    public static float skinPreLocHorizontal;
    public static float skinPreLocVertical;
    public static boolean skinPreLocFollowMouse;
    public static boolean skinTextureRenderOverride;
    public static int skinRenderType;
    public static boolean showF3DebugInfo;
    public static boolean showSkinTooltipDebugInfo;
    public static boolean showArmourerDebugRender;
    public static boolean wireframeRender;
    public static int texturePainting;
    public static boolean showLodLevels;
    public static boolean showSkinBlockBounds;
    public static boolean showSkinRenderBounds;
    public static Configuration config;

    public static void init(File file) {
        if (config == null) {
            config = new Configuration(file, "2");
            if (config.getLoadedConfigVersion().equals("1")) {
                config.getCategory(CATEGORY_DEBUG).remove((Object)"disableTexturePainting");
            }
            ConfigHandlerClient.loadConfigFile();
        }
    }

    public static void loadConfigFile() {
        ConfigHandlerClient.loadCategoryClient();
        ConfigHandlerClient.loadCategorySkinPreview();
        ConfigHandlerClient.loadCategoryDebug();
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static void loadCategoryClient() {
        maxSkinRenderDistance = config.get(CATEGORY_CLIENT, "maxSkinRenderDistance", 8192, "The max distance away squared that skins will render.").getInt(8192);
        maxModelBakingThreads = config.getInt("maxModelBakingThreads", CATEGORY_CLIENT, 1, 1, 20, "The maximum number of threads that will be used to bake models.");
        clientModelCacheTime = config.get(CATEGORY_CLIENT, "clientModelCacheTime", 600000, "How long in ms the client will keep skins in it's cache.\nDefault 600000 ms is 10 minutes.").getInt(600000);
        clientTextureCacheTime = config.getInt("clientTextureCacheTime", CATEGORY_CLIENT, 600, 1, 3600, "How long in seconds the client will keep textures in it's cache.\nDefault 600 seconds is 10 minutes.");
        fastCacheSize = config.getInt("fastCacheSize", CATEGORY_CLIENT, 5000, 0, Integer.MAX_VALUE, "Size of client size cache.\nSetting to 0 turns off this option.");
        multipassSkinRendering = config.getBoolean("multipassSkinRendering", CATEGORY_CLIENT, true, "When enabled skin will render in multiple passes to reduce visual artifacts.\nDisabling this will improve skin rendering performance at the cost of visual quality.");
        mannequinMaxEquipmentRenderDistance = config.getInt("mannequinMaxEquipmentRenderDistance", CATEGORY_CLIENT, 2048, 1, 4096, "The max distance squared that equipment will be rendered on mannequins.");
        blockSkinMaxRenderDistance = config.getInt("blockSkinMaxRenderDistance", CATEGORY_CLIENT, 8192, 1, 65536, "The max distance squared that block skins will be rendered.");
        lodDistance = config.getFloat("lodDistance", CATEGORY_CLIENT, 32.0f, 8.0f, 128.0f, "Distance away that skins will have lod applied to them.");
        skinLoadAnimationTime = config.getInt("skinLoadAnimationTime", CATEGORY_CLIENT, 500, 0, 10000, "How long skins will display their loading animation for in milliseconds\nSettings this to 0 will disable loading animations.");
        maxLodLevels = config.getInt("maxLodLevels", CATEGORY_CLIENT, 4, 0, 4, "Number of LOD models to create. Higher number should give a boost to framerate at a small cost to VRAM.");
    }

    private static void loadCategorySkinPreview() {
        skinPreEnabled = config.getBoolean("skinPreEnabled", CATEGORY_SKIN_PREVIEW, true, "Enables a larger skin preview box when hovering the mouse over a skin.");
        skinPreDrawBackground = config.getBoolean("skinPreDrawBackground", CATEGORY_SKIN_PREVIEW, true, "Draw a background box for the skin preview.");
        skinPreSize = config.getFloat("skinPreSize", CATEGORY_SKIN_PREVIEW, 96.0f, 16.0f, 256.0f, "Size of the skin preview.");
        skinPreLocHorizontal = config.getFloat("skinPreLocHorizontal", CATEGORY_SKIN_PREVIEW, 0.0f, 0.0f, 1.0f, "Horizontal location of the skin preview: 0 = left, 1 = right.");
        skinPreLocVertical = config.getFloat("skinPreLocVertical", CATEGORY_SKIN_PREVIEW, 0.5f, 0.0f, 1.0f, "Vertical location of the skin preview: 0 = top, 1 = bottom.");
        skinPreLocFollowMouse = config.getBoolean("skinPreLocFollowMouse", CATEGORY_SKIN_PREVIEW, true, "Skin preview will be rendered next to the mouse.");
    }

    private static void loadCategoryDebug() {
        skinRenderType = config.getInt("skinRenderType", CATEGORY_DEBUG, 0, 0, 2, "Only change this if you are having rendering issues with skins on players.(normally fixes skins not rotating on players)\n\n0 = auto\n1 = render event\n2 = model attachment\n");
        skinTextureRenderOverride = config.get(CATEGORY_DEBUG, "skinTextureRenderOverride", false, "Only enable this if you are having rendering issues with skins. (normally fixes lighting issues)\nThis option is force on if Shaders Mod or Colored Lights mod is installed.").getBoolean(false);
        showF3DebugInfo = config.get(CATEGORY_DEBUG, "showF3DebugInfo", true, "Shows extra info on the F3 debug screen.").getBoolean(true);
        showSkinTooltipDebugInfo = config.get(CATEGORY_DEBUG, "showSkinTooltipDebugInfo", false, "Shows extra debug info on skin tooltips.").getBoolean(false);
        texturePainting = config.getInt("texturePainting", CATEGORY_DEBUG, 0, 0, 2, "Texture painting replacing the players texture with a painted version.\nTurning this off may fix issues with the players texture rendering\nincorrectly or showing the steve skin.\n\n0 = auto\n1 = on\n2 = off\n");
    }

    static {
        maxSkinRenderDistance = 128;
        maxModelBakingThreads = 1;
        multipassSkinRendering = true;
        mannequinMaxEquipmentRenderDistance = 1024;
        blockSkinMaxRenderDistance = 2500;
        lodDistance = 32.0;
        skinLoadAnimationTime = 500;
        maxLodLevels = 4;
        skinPreEnabled = false;
        skinPreDrawBackground = true;
        skinPreSize = 96.0f;
        skinPreLocHorizontal = 1.0f;
        skinPreLocVertical = 0.5f;
        skinPreLocFollowMouse = false;
    }
}

