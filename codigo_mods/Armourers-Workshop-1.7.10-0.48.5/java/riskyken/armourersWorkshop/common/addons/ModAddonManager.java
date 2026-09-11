/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.addons;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.client.render.item.RenderItemBowSkin;
import riskyken.armourersWorkshop.client.render.item.RenderItemEquipmentSkin;
import riskyken.armourersWorkshop.client.render.item.RenderItemSwordSkin;
import riskyken.armourersWorkshop.common.addons.AddonAquaTweaks;
import riskyken.armourersWorkshop.common.addons.AddonBalkonsWeaponMod;
import riskyken.armourersWorkshop.common.addons.AddonBattlegear2;
import riskyken.armourersWorkshop.common.addons.AddonBetterStorage;
import riskyken.armourersWorkshop.common.addons.AddonBotania;
import riskyken.armourersWorkshop.common.addons.AddonBuildCraft;
import riskyken.armourersWorkshop.common.addons.AddonColoredLights;
import riskyken.armourersWorkshop.common.addons.AddonCustomNPCS;
import riskyken.armourersWorkshop.common.addons.AddonGlassShards;
import riskyken.armourersWorkshop.common.addons.AddonJBRAClient;
import riskyken.armourersWorkshop.common.addons.AddonLittleMaidMob;
import riskyken.armourersWorkshop.common.addons.AddonMaplecrafted;
import riskyken.armourersWorkshop.common.addons.AddonMekanismTools;
import riskyken.armourersWorkshop.common.addons.AddonMetallurgy;
import riskyken.armourersWorkshop.common.addons.AddonMinecraft;
import riskyken.armourersWorkshop.common.addons.AddonMinecraftComesAlive;
import riskyken.armourersWorkshop.common.addons.AddonMorePlayerModels;
import riskyken.armourersWorkshop.common.addons.AddonMoreSwordsMod;
import riskyken.armourersWorkshop.common.addons.AddonNEI;
import riskyken.armourersWorkshop.common.addons.AddonOreSpawn;
import riskyken.armourersWorkshop.common.addons.AddonShaders;
import riskyken.armourersWorkshop.common.addons.AddonSmartMoving;
import riskyken.armourersWorkshop.common.addons.AddonThaumcraft;
import riskyken.armourersWorkshop.common.addons.AddonTinkersConstruct;
import riskyken.armourersWorkshop.common.addons.AddonTwilightForest;
import riskyken.armourersWorkshop.common.addons.AddonZeldaSwordSkills;
import riskyken.armourersWorkshop.common.addons.ModAddon;
import riskyken.armourersWorkshop.utils.EventState;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class ModAddonManager {
    private static ArrayList<ModAddon> loadedAddons = new ArrayList();
    public static HashSet<String> itemOverrides = new HashSet();
    public static AddonAquaTweaks addonAquaTweaks;
    public static AddonBalkonsWeaponMod addonBalkonsWeaponMod;
    public static AddonBattlegear2 addonBattlegear2;
    public static AddonBetterStorage addonBetterStorage;
    public static AddonBotania addonBotania;
    public static AddonBuildCraft addonBuildCraft;
    public static AddonColoredLights addonColoredLights;
    public static AddonCustomNPCS addonCustomNPCS;
    public static AddonGlassShards addonGlassShards;
    public static AddonJBRAClient addonJBRAClient;
    public static AddonLittleMaidMob addonLittleMaidMob;
    public static AddonMaplecrafted addonMaplecrafted;
    public static AddonMekanismTools addonMekanismTools;
    public static AddonMetallurgy addonMetallurgy;
    public static AddonMinecraft addonMinecraft;
    public static AddonMinecraftComesAlive addonMinecraftComesAlive;
    public static AddonMorePlayerModels addonMorePlayerModels;
    public static AddonMoreSwordsMod addonMoreSwordsMod;
    public static AddonNEI addonNEI;
    public static AddonOreSpawn addonOreSpawn;
    public static AddonShaders addonShaders;
    public static AddonSmartMoving addonSmartMoving;
    public static AddonThaumcraft addonThaumcraft;
    public static AddonTinkersConstruct addonTinkersConstruct;
    public static AddonTwilightForest addonTwilightForest;
    public static AddonZeldaSwordSkills addonZeldaSwordSkills;

    public static void preInit() {
        ModAddonManager.loadAddons();
        for (int i = 0; i < loadedAddons.size(); ++i) {
            loadedAddons.get(i).preInit();
        }
    }

    private static void loadAddons() {
        ModLogger.log("Loading addons");
        addonAquaTweaks = new AddonAquaTweaks();
        addonBalkonsWeaponMod = new AddonBalkonsWeaponMod();
        addonBattlegear2 = new AddonBattlegear2();
        addonBetterStorage = new AddonBetterStorage();
        addonBotania = new AddonBotania();
        addonBuildCraft = new AddonBuildCraft();
        addonColoredLights = new AddonColoredLights();
        addonCustomNPCS = new AddonCustomNPCS();
        addonGlassShards = new AddonGlassShards();
        addonJBRAClient = new AddonJBRAClient();
        addonLittleMaidMob = new AddonLittleMaidMob();
        addonMaplecrafted = new AddonMaplecrafted();
        addonMekanismTools = new AddonMekanismTools();
        addonMetallurgy = new AddonMetallurgy();
        addonMinecraft = new AddonMinecraft();
        addonMinecraftComesAlive = new AddonMinecraftComesAlive();
        addonMorePlayerModels = new AddonMorePlayerModels();
        addonMoreSwordsMod = new AddonMoreSwordsMod();
        addonNEI = new AddonNEI();
        addonOreSpawn = new AddonOreSpawn();
        addonShaders = new AddonShaders();
        addonSmartMoving = new AddonSmartMoving();
        addonThaumcraft = new AddonThaumcraft();
        addonTinkersConstruct = new AddonTinkersConstruct();
        addonTwilightForest = new AddonTwilightForest();
        addonZeldaSwordSkills = new AddonZeldaSwordSkills();
        loadedAddons.add(addonAquaTweaks);
        loadedAddons.add(addonBalkonsWeaponMod);
        loadedAddons.add(addonBattlegear2);
        loadedAddons.add(addonCustomNPCS);
        loadedAddons.add(addonBetterStorage);
        loadedAddons.add(addonBotania);
        loadedAddons.add(addonBuildCraft);
        loadedAddons.add(addonGlassShards);
        loadedAddons.add(addonJBRAClient);
        loadedAddons.add(addonLittleMaidMob);
        loadedAddons.add(addonMaplecrafted);
        loadedAddons.add(addonMekanismTools);
        loadedAddons.add(addonMetallurgy);
        loadedAddons.add(addonMinecraft);
        loadedAddons.add(addonMinecraftComesAlive);
        loadedAddons.add(addonMoreSwordsMod);
        loadedAddons.add(addonNEI);
        loadedAddons.add(addonOreSpawn);
        loadedAddons.add(addonThaumcraft);
        loadedAddons.add(addonTinkersConstruct);
        loadedAddons.add(addonTwilightForest);
        loadedAddons.add(addonZeldaSwordSkills);
    }

    public static String[] getDefaultOverrides() {
        ArrayList<String> overrides = new ArrayList<String>();
        for (int i = 0; i < loadedAddons.size(); ++i) {
            if (loadedAddons.get(i).getItemOverrides().size() <= 0) continue;
            overrides.addAll(loadedAddons.get(i).getItemOverrides());
            if (i == loadedAddons.size() - 1) continue;
            overrides.add("");
        }
        return overrides.toArray(new String[0]);
    }

    public static void init() {
        for (int i = 0; i < loadedAddons.size(); ++i) {
            loadedAddons.get(i).init();
        }
    }

    public static void postInit() {
        for (int i = 0; i < loadedAddons.size(); ++i) {
            loadedAddons.get(i).postInit();
        }
    }

    public static void onWeaponRender(IItemRenderer.ItemRenderType type, EventState state) {
        for (int i = 0; i < loadedAddons.size(); ++i) {
            loadedAddons.get(i).onWeaponRender(type, state);
        }
    }

    public static void initRenderers() {
        ModAddonManager.overrideItemRenders();
    }

    private static void overrideItemRenders() {
        for (String itemOverride : itemOverrides) {
            int splitterCount = itemOverride.length() - itemOverride.replace(":", "").length();
            if (splitterCount > 1) {
                String type = itemOverride.substring(0, itemOverride.indexOf(":"));
                String item = itemOverride.substring(itemOverride.indexOf(":") + 1);
                String modId = item.substring(0, item.indexOf(":"));
                String itemId = item.substring(item.indexOf(":") + 1);
                if (!(Loader.isModLoaded((String)modId) | modId.equals("minecraft"))) continue;
                if (type.equals("bow")) {
                    ModAddonManager.overrideItemRenderer(modId, itemId, RenderType.BOW);
                    continue;
                }
                ModAddonManager.overrideItemRenderer(modId, itemId, RenderType.SWORD);
                continue;
            }
            if (itemOverride.isEmpty()) continue;
            ModLogger.log(Level.ERROR, String.format("Invalid item override in config file: %s", itemOverride));
        }
    }

    private static void overrideItemRenderer(String modId, String itemName, RenderType renderType) {
        Item item = GameRegistry.findItem((String)modId, (String)itemName);
        if (item != null) {
            ItemStack stack = new ItemStack(item);
            IItemRenderer renderer = ModAddonManager.getItemRenderer(stack);
            if (renderer != null && renderer instanceof RenderItemEquipmentSkin) {
                ModLogger.log(Level.WARN, String.format("Tried to override the render on %s:%s but it has already been overridden.", modId, itemName));
                return;
            }
            ModLogger.log(String.format("Overriding render on %s:%s.", modId, itemName));
            switch (renderType) {
                case SWORD: {
                    MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new RenderItemSwordSkin(renderer));
                    break;
                }
                case BOW: {
                    MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new RenderItemBowSkin(renderer));
                }
            }
        } else {
            ModLogger.log(Level.WARN, String.format("Unable to override item renderer for %s:%s. Can not find item.", modId, itemName));
        }
    }

    private static IItemRenderer getItemRenderer(ItemStack stack) {
        try {
            IdentityHashMap customItemRenderers = null;
            customItemRenderers = (IdentityHashMap)ReflectionHelper.getPrivateValue(MinecraftForgeClient.class, null, (String[])new String[]{"customItemRenderers"});
            IItemRenderer renderer = (IItemRenderer)customItemRenderers.get(stack.func_77973_b());
            if (renderer != null) {
                return renderer;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    public static enum RenderType {
        SWORD,
        BOW;

    }
}

