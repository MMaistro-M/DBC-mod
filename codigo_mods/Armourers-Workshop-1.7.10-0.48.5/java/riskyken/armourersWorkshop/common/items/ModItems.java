/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.items;

import net.minecraft.item.Item;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.items.ItemArmourContainer;
import riskyken.armourersWorkshop.common.items.ItemArmourContainerItem;
import riskyken.armourersWorkshop.common.items.ItemArmourersHammer;
import riskyken.armourersWorkshop.common.items.ItemBlockMarker;
import riskyken.armourersWorkshop.common.items.ItemDebugTool;
import riskyken.armourersWorkshop.common.items.ItemDyeBottle;
import riskyken.armourersWorkshop.common.items.ItemGuideBook;
import riskyken.armourersWorkshop.common.items.ItemLinkingTool;
import riskyken.armourersWorkshop.common.items.ItemMannequinTool;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;
import riskyken.armourersWorkshop.common.items.ItemSkinUnlock;
import riskyken.armourersWorkshop.common.items.ItemSoap;
import riskyken.armourersWorkshop.common.items.ItemWandOfStyle;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemBlendingTool;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemBurnTool;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemColourNoiseTool;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemColourPicker;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemDodgeTool;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemHueTool;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemPaintRoller;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemPaintbrush;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemShadeNoiseTool;
import riskyken.armourersWorkshop.utils.ModLogger;

public class ModItems {
    public static Item equipmentSkinTemplate;
    public static Item equipmentSkin;
    public static Item paintbrush;
    public static Item paintRoller;
    public static Item colourPicker;
    public static Item burnTool;
    public static Item dodgeTool;
    public static Item colourNoiseTool;
    public static Item shadeNoiseTool;
    public static Item hueTool;
    public static Item blendingTool;
    public static Item blockMarker;
    public static Item mannequinTool;
    public static Item wandOfStyle;
    public static Item soap;
    public static Item dyeBottle;
    public static Item guideBook;
    public static Item armourersHammer;
    public static Item debugTool;
    public static Item skinUnlock;
    public static Item linkingTool;
    public static Item armourContainerItem;
    public static Item[] armourContainer;

    public ModItems() {
        equipmentSkinTemplate = new ItemSkinTemplate();
        this.setEquipmentSkinType();
        paintbrush = new ItemPaintbrush();
        paintRoller = new ItemPaintRoller();
        colourPicker = new ItemColourPicker();
        burnTool = new ItemBurnTool();
        dodgeTool = new ItemDodgeTool();
        colourNoiseTool = new ItemColourNoiseTool();
        shadeNoiseTool = new ItemShadeNoiseTool();
        hueTool = new ItemHueTool();
        blockMarker = new ItemBlockMarker();
        blendingTool = new ItemBlendingTool();
        mannequinTool = new ItemMannequinTool();
        wandOfStyle = new ItemWandOfStyle();
        soap = new ItemSoap();
        dyeBottle = new ItemDyeBottle();
        guideBook = new ItemGuideBook();
        armourersHammer = new ItemArmourersHammer();
        debugTool = new ItemDebugTool();
        skinUnlock = new ItemSkinUnlock();
        linkingTool = new ItemLinkingTool();
        armourContainerItem = new ItemArmourContainerItem();
        armourContainer = new Item[4];
        ModItems.armourContainer[0] = new ItemArmourContainer("armourContainerHead", 0);
        ModItems.armourContainer[1] = new ItemArmourContainer("armourContainerChest", 1);
        ModItems.armourContainer[2] = new ItemArmourContainer("armourContainerLegs", 2);
        ModItems.armourContainer[3] = new ItemArmourContainer("armourContainerFeet", 3);
    }

    private void setEquipmentSkinType() {
        boolean skinTypeSet;
        block6: {
            skinTypeSet = true;
            if (ModAddonManager.addonBuildCraft.isSkinCompatibleVersion()) {
                try {
                    Class<?> c = Class.forName("riskyken.armourersWorkshop.common.items.ItemSkinRobotOverlay");
                    Object classObject = c.newInstance();
                    if (classObject instanceof ItemSkin) {
                        equipmentSkin = (ItemSkin)classObject;
                        break block6;
                    }
                    skinTypeSet = false;
                }
                catch (Exception e) {
                    ModLogger.log(Level.WARN, "Failed to load BuildCraft skinned item.");
                    e.printStackTrace();
                    skinTypeSet = false;
                }
            } else {
                skinTypeSet = false;
            }
        }
        if (!skinTypeSet) {
            equipmentSkin = new ItemSkin();
        }
    }
}

