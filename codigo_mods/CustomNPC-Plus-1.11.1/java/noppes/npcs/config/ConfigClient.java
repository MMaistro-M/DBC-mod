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
import noppes.npcs.CustomNpcs;
import noppes.npcs.config.legacy.LegacyConfig;
import org.apache.logging.log4j.Level;

public class ConfigClient {
    public static Configuration config;
    public static final String GENERAL = "General";
    public static final String VISUAL = "Visual";
    public static final String MODEL = "Model";
    public static final String TEXTURE = "Texture";
    public static final String HUD = "Hud";
    public static final String RENDERING = "Rendering";
    public static Property AllowClientScriptsProperty;
    public static boolean AllowClientScripts;
    public static Property CacheLifeProperty;
    public static int CacheLife;
    public static Property EnableChatBubblesProperty;
    public static boolean EnableChatBubbles;
    public static Property InventoryGuiEnabledProperty;
    public static boolean InventoryGuiEnabled;
    public static Property enableFactionTabProperty;
    public static boolean enableFactionTab;
    public static Property FontTypeProperty;
    public static String FontType;
    public static Property FontSizeProperty;
    public static int FontSize;
    public static Property ChatBubblesFontTypeProperty;
    public static boolean ChatBubblesFontType;
    public static Property HideEffectsBarProperty;
    public static boolean HideEffectsBar;
    public static Property LowResExplosionProperty;
    public static boolean LowResExplosion;
    public static Property ProximityAlphaMinProperty;
    public static float ProximityAlphaMin;
    public static Property ProximityAlphaDistanceProperty;
    public static float ProximityAlphaDistance;
    public static Property ProximityAlphaAgeTicksProperty;
    public static int ProximityAlphaAgeTicks;
    public static Property DialogSpeedProperty;
    public static int DialogSpeed;
    public static Property DialogSoundProperty;
    public static boolean DialogSound;
    public static Property ChatAlertsProperty;
    public static boolean ChatAlerts;
    public static Property BannerAlertsProperty;
    public static boolean BannerAlerts;
    public static Property QuestOverlayXProperty;
    public static float QuestOverlayX;
    public static Property QuestOverlayYProperty;
    public static float QuestOverlayY;
    public static Property QuestOverlayScaleProperty;
    public static int QuestOverlayScale;
    public static Property QuestOverlayTextAlignProperty;
    public static int QuestOverlayTextAlign;
    public static Property CompassEnabledProperty;
    public static boolean CompassEnabled;
    public static Property CompassOverlayXProperty;
    public static float CompassOverlayX;
    public static Property CompassOverlayYProperty;
    public static float CompassOverlayY;
    public static Property CompassOverlayScaleProperty;
    public static int CompassOverlayScale;
    public static Property CompassOverlayWidthProperty;
    public static int CompassOverlayWidth;
    public static Property AbilityHotbarEnabledProperty;
    public static boolean AbilityHotbarEnabled;
    public static Property AbilityHotbarXProperty;
    public static float AbilityHotbarX;
    public static Property AbilityHotbarYProperty;
    public static float AbilityHotbarY;
    public static Property AbilityHotbarScaleProperty;
    public static int AbilityHotbarScale;
    public static Property AbilityHotbarHorizontalProperty;
    public static boolean AbilityHotbarHorizontal;
    public static Property AbilityHotbarAltTextureProperty;
    public static boolean AbilityHotbarAltTexture;
    public static Property AbilityHotbarTextPositionProperty;
    public static int AbilityHotbarTextPosition;
    public static Property AbilityHotbarVisibleSlotsProperty;
    public static int AbilityHotbarVisibleSlots;
    public static Property AbilityHotbarShowAlwaysProperty;
    public static boolean AbilityHotbarShowAlways;
    public static Property AbilityHotbarTextVisibilityProperty;
    public static int AbilityHotbarTextVisibility;
    public static boolean WoodTextures;
    public static boolean LegacyCampfire;
    public static boolean LegacyBanner;
    public static boolean LegacyChair;
    public static boolean LegacyStool;
    public static boolean LegacyCouch;
    public static boolean LegacyTable;
    public static boolean LegacyBarrel;
    public static boolean LegacyCarpentryBench;
    public static boolean LegacyAnvil;
    public static boolean LegacyLantern;
    public static boolean LegacyCandle;
    public static boolean LegacyTallLamp;
    public static boolean LegacyPedestal;
    public static boolean LegacyMailbox;
    public static boolean ImprovedImageDownloadConnection;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            QuestOverlayXProperty = config.get(HUD, "Quest Hud X", 0.0, "X position of the quest overlay.");
            QuestOverlayX = (float)QuestOverlayXProperty.getDouble(0.0);
            QuestOverlayYProperty = config.get(HUD, "Quest Hud Y", 14.0, "Y position of the quest overlay.");
            QuestOverlayY = (float)QuestOverlayYProperty.getDouble(14.0);
            QuestOverlayScaleProperty = config.get(HUD, "Quest Hud Scale", 246, "Scale percentage of the quest overlay.");
            QuestOverlayScale = QuestOverlayScaleProperty.getInt(246);
            QuestOverlayTextAlignProperty = config.get(HUD, "Quest Hud Text Alignment", 0, "Text alignment in quest overlay (0: Left, 1: Center, 2: Right).");
            QuestOverlayTextAlign = QuestOverlayTextAlignProperty.getInt(0);
            CompassEnabledProperty = config.get(HUD, "Compass Hud Enabled", true, "Enable Compass Hud Component");
            CompassEnabled = CompassEnabledProperty.getBoolean();
            CompassOverlayXProperty = config.get(HUD, "Compass Hud X", 36.0, "Horizontal position of compass overlay (0-100 percentage)");
            CompassOverlayX = (float)CompassOverlayXProperty.getDouble();
            CompassOverlayYProperty = config.get(HUD, "Compass Hud Y", 0.0, "Vertical position of compass overlay (0-100 percentage)");
            CompassOverlayY = (float)CompassOverlayYProperty.getDouble();
            CompassOverlayScaleProperty = config.get(HUD, "Compass Hud Scale", 344, "Scale percentage of compass overlay", 50, 500);
            CompassOverlayScale = CompassOverlayScaleProperty.getInt();
            CompassOverlayWidthProperty = config.get(HUD, "Compass Hud Width", 158, "Base width of compass bar in pixels", 50, 1000);
            CompassOverlayWidth = CompassOverlayWidthProperty.getInt();
            AbilityHotbarEnabledProperty = config.get(HUD, "Ability Hotbar Enabled", true, "Enable Ability Hotbar HUD Component");
            AbilityHotbarEnabled = AbilityHotbarEnabledProperty.getBoolean();
            AbilityHotbarXProperty = config.get(HUD, "Ability Hotbar X", 0.0, "Horizontal position (0-100 percentage)");
            AbilityHotbarX = (float)AbilityHotbarXProperty.getDouble();
            AbilityHotbarYProperty = config.get(HUD, "Ability Hotbar Y", 43.0, "Vertical position (0-100 percentage)");
            AbilityHotbarY = (float)AbilityHotbarYProperty.getDouble();
            AbilityHotbarScaleProperty = config.get(HUD, "Ability Hotbar Scale", 227, "Scale percentage", 50, 500);
            AbilityHotbarScale = AbilityHotbarScaleProperty.getInt();
            AbilityHotbarHorizontalProperty = config.get(HUD, "Ability Hotbar Horizontal", false, "Display horizontally instead of vertically");
            AbilityHotbarHorizontal = AbilityHotbarHorizontalProperty.getBoolean();
            AbilityHotbarAltTextureProperty = config.get(HUD, "Ability Hotbar Alt Texture", true, "Use rounded square instead of circle slots");
            AbilityHotbarAltTexture = AbilityHotbarAltTextureProperty.getBoolean();
            AbilityHotbarTextPositionProperty = config.get(HUD, "Ability Hotbar Text Position", 2, "Text label position (1=Above/Left, 2=Below/Right)");
            AbilityHotbarTextPosition = AbilityHotbarTextPositionProperty.getInt(2);
            AbilityHotbarVisibleSlotsProperty = config.get(HUD, "Ability Hotbar Visible Slots", 5, "Max visible slots in hotbar (3, 5, or 7)");
            AbilityHotbarVisibleSlots = AbilityHotbarVisibleSlotsProperty.getInt(5);
            AbilityHotbarShowAlwaysProperty = config.get(HUD, "Ability Hotbar Show Always", true, "Always show hotbar (false = only while HUD key is held)");
            AbilityHotbarShowAlways = AbilityHotbarShowAlwaysProperty.getBoolean(true);
            AbilityHotbarTextVisibilityProperty = config.get(HUD, "Ability Hotbar Text Visibility", 0, "Text visibility (0=Shown, 1=Hidden, 2=Held)");
            AbilityHotbarTextVisibility = AbilityHotbarTextVisibilityProperty.getInt(0);
            AllowClientScriptsProperty = config.get(GENERAL, "Allow Client Scripts", true, "Allow the server to run scripts on the client. If disabled, no server scripts will execute client-side.");
            AllowClientScripts = AllowClientScriptsProperty.getBoolean(true);
            CacheLifeProperty = config.get(GENERAL, "Cache Life", 10, "How long should downloaded imagery data be saved client side? (In minutes)");
            CacheLife = CacheLifeProperty.getInt(10);
            EnableChatBubblesProperty = config.get(VISUAL, "Enable Chat Bubbles", true, "Enable/Disable Chat Bubbles");
            EnableChatBubbles = EnableChatBubblesProperty.getBoolean(true);
            InventoryGuiEnabledProperty = config.get(VISUAL, "Enable Inventory Tabs", true, "Enable/Disable Inventory Tabs");
            InventoryGuiEnabled = InventoryGuiEnabledProperty.getBoolean(true);
            enableFactionTabProperty = config.get(VISUAL, "Enable Faction Tab", true, "Enable the Faction Tab");
            enableFactionTab = enableFactionTabProperty.getBoolean(true);
            FontTypeProperty = config.get(VISUAL, "Font Type", "Default", "When set to Minecraft it will use minecrafts font, when Default it will use OpenSans. Can only use fonts installed on your PC");
            FontType = FontTypeProperty.getString();
            FontSizeProperty = config.get(VISUAL, "Font Size", 18, "Font size for custom fonts (doesn't work with minecrafts font)");
            FontSize = FontSizeProperty.getInt(18);
            ChatBubblesFontTypeProperty = config.get(VISUAL, "Chat Bubbles Font Type", false, "Enable the use of Custom Font for Chat Bubbles");
            ChatBubblesFontType = ChatBubblesFontTypeProperty.getBoolean(false);
            HideEffectsBarProperty = config.get(VISUAL, "Hide Effects Bar", false, "Hides CNPC+ Inventory Effects Bar");
            HideEffectsBar = HideEffectsBarProperty.getBoolean(false);
            LowResExplosionProperty = config.get(RENDERING, "Low Res Explosion", false, "Disables most Energy Explosion voxel rendering and keeps particle effects only.");
            LowResExplosion = LowResExplosionProperty.getBoolean(false);
            ProximityAlphaMinProperty = config.get(RENDERING, "Proximity Alpha Min", 0.15, "Minimum alpha for owner's energy projectiles when very close to camera. Set to 1.0 to disable proximity fade.");
            ProximityAlphaMin = (float)Math.max(0.0, Math.min(1.0, ProximityAlphaMinProperty.getDouble(0.15)));
            ProximityAlphaDistanceProperty = config.get(RENDERING, "Proximity Alpha Distance", 7.0, "Distance in blocks at which owner's energy projectiles reach full alpha.");
            ProximityAlphaDistance = (float)Math.max(1.0, ProximityAlphaDistanceProperty.getDouble(10.0));
            ProximityAlphaAgeTicksProperty = config.get(RENDERING, "Proximity Alpha Age Ticks", 60, "After this many ticks alive, proximity alpha fade is disabled (projectile always renders at full alpha). Does not apply while charging or to attached beams/lasers.");
            ProximityAlphaAgeTicks = Math.max(0, ProximityAlphaAgeTicksProperty.getInt(60));
            DialogSpeedProperty = config.get(VISUAL, "Dialog Speed", true, "Only set for gradual dialogs");
            DialogSpeed = DialogSpeedProperty.getInt(10);
            DialogSoundProperty = config.get(VISUAL, "Dialog Sound", true, "Only for dialogs with sounds");
            DialogSound = DialogSoundProperty.getBoolean(true);
            ChatAlertsProperty = config.get(VISUAL, "All Chat Alerts", true, "Universal enable/disable for Banner Alerts");
            ChatAlerts = ChatAlertsProperty.getBoolean(true);
            BannerAlertsProperty = config.get(VISUAL, "All Banner Alerts", true, "Universal enable/disable for Banner Alerts");
            BannerAlerts = BannerAlertsProperty.getBoolean(true);
            WoodTextures = config.get(TEXTURE, "Wood Textures", false, "Models like Chairs and Stools will use default MC Wood Textures").getBoolean(false);
            ImprovedImageDownloadConnection = config.get(TEXTURE, "DEBUG: Better handling of image downloads: ", true).getBoolean(true);
            LegacyCampfire = config.get(MODEL, "Legacy Campfire Model", false).getBoolean(false);
            LegacyBanner = config.get(MODEL, "Legacy Banner Model", false).getBoolean(false);
            LegacyBarrel = config.get(MODEL, "Legacy Barrel Model", false).getBoolean(false);
            LegacyChair = config.get(MODEL, "Legacy Chair Model", false).getBoolean(false);
            LegacyStool = config.get(MODEL, "Legacy Stool Model", false).getBoolean(false);
            LegacyCouch = config.get(MODEL, "Legacy Couch Model", false).getBoolean(false);
            LegacyTable = config.get(MODEL, "Legacy Table Model", false).getBoolean(false);
            LegacyAnvil = config.get(MODEL, "Legacy Anvil Model", false).getBoolean(false);
            LegacyCarpentryBench = config.get(MODEL, "Legacy Carpentry Bench Model", false).getBoolean(false);
            LegacyLantern = config.get(MODEL, "Legacy Lantern Model", false).getBoolean(false);
            LegacyCandle = config.get(MODEL, "Legacy Candle Model", false).getBoolean(false);
            LegacyTallLamp = config.get(MODEL, "Legacy Tall Lamp Model", false).getBoolean(false);
            LegacyPedestal = config.get(MODEL, "Legacy Pedestal Model", false).getBoolean(false);
            LegacyMailbox = config.get(MODEL, "Legacy Mailbox Model", false).getBoolean(false);
            if (CustomNpcs.legacyExist) {
                EnableChatBubbles = LegacyConfig.EnableChatBubbles;
                EnableChatBubblesProperty.set(EnableChatBubbles);
                FontType = LegacyConfig.FontType;
                FontTypeProperty.set(FontType);
                InventoryGuiEnabled = LegacyConfig.InventoryGuiEnabled;
                InventoryGuiEnabledProperty.set(InventoryGuiEnabled);
                FontSize = LegacyConfig.FontSize;
                FontSizeProperty.set(FontSize);
            }
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its client configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        AllowClientScripts = true;
        CacheLife = 10;
        EnableChatBubbles = true;
        InventoryGuiEnabled = true;
        enableFactionTab = true;
        FontType = "Default";
        FontSize = 18;
        ChatBubblesFontType = false;
        HideEffectsBar = false;
        LowResExplosion = false;
        ProximityAlphaMin = 0.15f;
        ProximityAlphaDistance = 10.0f;
        ProximityAlphaAgeTicks = 60;
        DialogSpeed = 10;
        DialogSound = true;
        ChatAlerts = true;
        BannerAlerts = true;
        QuestOverlayX = 0.0f;
        QuestOverlayY = 14.0f;
        QuestOverlayScale = 246;
        QuestOverlayTextAlign = 0;
        CompassEnabled = true;
        CompassOverlayX = 36.0f;
        CompassOverlayY = 0.0f;
        CompassOverlayScale = 344;
        CompassOverlayWidth = 158;
        AbilityHotbarEnabled = true;
        AbilityHotbarX = 0.0f;
        AbilityHotbarY = 43.0f;
        AbilityHotbarScale = 227;
        AbilityHotbarHorizontal = false;
        AbilityHotbarAltTexture = true;
        AbilityHotbarTextPosition = 2;
        AbilityHotbarVisibleSlots = 5;
        AbilityHotbarShowAlways = true;
        AbilityHotbarTextVisibility = 0;
        WoodTextures = false;
        LegacyCampfire = false;
        LegacyBanner = false;
        LegacyChair = false;
        LegacyStool = false;
        LegacyCouch = false;
        LegacyTable = false;
        LegacyBarrel = false;
        LegacyCarpentryBench = false;
        LegacyAnvil = false;
        LegacyLantern = false;
        LegacyCandle = false;
        LegacyTallLamp = false;
        LegacyPedestal = false;
        LegacyMailbox = false;
        ImprovedImageDownloadConnection = true;
    }
}

