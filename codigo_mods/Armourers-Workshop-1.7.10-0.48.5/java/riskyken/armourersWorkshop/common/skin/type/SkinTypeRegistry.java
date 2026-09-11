/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.type;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.arrow.SkinArrow;
import riskyken.armourersWorkshop.common.skin.type.block.SkinBlock;
import riskyken.armourersWorkshop.common.skin.type.bow.SkinBow;
import riskyken.armourersWorkshop.common.skin.type.chest.SkinChest;
import riskyken.armourersWorkshop.common.skin.type.feet.SkinFeet;
import riskyken.armourersWorkshop.common.skin.type.head.SkinHead;
import riskyken.armourersWorkshop.common.skin.type.item.SkinItem;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinLegs;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinSkirt;
import riskyken.armourersWorkshop.common.skin.type.outfit.SkinOutfit;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SkinTypeRegistry
implements ISkinTypeRegistry {
    public static SkinTypeRegistry INSTANCE;
    public static ISkinType skinHead;
    public static ISkinType skinChest;
    public static ISkinType skinLegs;
    public static ISkinType skinFeet;
    public static ISkinType skinWings;
    public static ISkinType skinSword;
    public static ISkinType skinShield;
    public static ISkinType skinBow;
    public static ISkinType skinPickaxe;
    public static ISkinType skinAxe;
    public static ISkinType skinShovel;
    public static ISkinType skinHoe;
    public static ISkinType skinItem;
    public static ISkinType skinBlock;
    public static ISkinType skinOutfit;
    public static ISkinType oldSkinSkirt;
    public static ISkinType oldSkinArrow;
    private LinkedHashMap<String, ISkinType> skinTypeMap;
    private HashMap<String, ISkinPartType> skinPartMap;

    public static void init() {
        INSTANCE = new SkinTypeRegistry();
    }

    public SkinTypeRegistry() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.skinTypeMap = new LinkedHashMap();
        this.skinPartMap = new HashMap();
        this.registerSkins();
    }

    private void registerSkins() {
        skinHead = new SkinHead();
        skinChest = new SkinChest();
        skinLegs = new SkinLegs();
        skinFeet = new SkinFeet();
        skinWings = new SkinWings();
        skinSword = new SkinItem("Sword");
        skinShield = new SkinItem("Shield");
        skinBow = new SkinBow();
        skinPickaxe = new SkinItem("Pickaxe");
        skinAxe = new SkinItem("Axe");
        skinShovel = new SkinItem("Shovel");
        skinHoe = new SkinItem("Hoe");
        skinItem = new SkinItem("Item");
        skinBlock = new SkinBlock();
        skinOutfit = new SkinOutfit(skinHead, skinChest, skinLegs, skinFeet, skinWings);
        oldSkinSkirt = new SkinSkirt();
        oldSkinArrow = new SkinArrow();
        this.registerSkin(skinHead);
        this.registerSkin(skinChest);
        this.registerSkin(skinLegs);
        this.registerSkin(skinFeet);
        this.registerSkin(skinWings);
        this.registerSkin(skinSword);
        this.registerSkin(skinShield);
        this.registerSkin(skinBow);
        this.registerSkin(skinPickaxe);
        this.registerSkin(skinAxe);
        this.registerSkin(skinShovel);
        this.registerSkin(skinHoe);
        this.registerSkin(skinItem);
        this.registerSkin(skinBlock);
        this.registerSkin(skinOutfit);
    }

    @Override
    public boolean registerSkin(ISkinType skinType) {
        if (skinType == null) {
            ModLogger.log(Level.WARN, "A mod tried to register a null skin type.");
            return false;
        }
        if (skinType.getRegistryName() == null || skinType.getRegistryName().trim().isEmpty()) {
            ModLogger.log(Level.WARN, "A mod tried to register a skin type with an invalid registry name.");
            return false;
        }
        if (this.skinPartMap.containsKey(skinType.getRegistryName())) {
            ModLogger.log(Level.WARN, "A mod tried to register a skin type with a registry name that is in use.");
            return false;
        }
        if (skinType.getSkinParts() == null || skinType.getSkinParts().size() == 0) {
            ModLogger.log(Level.WARN, "A mod tried to register a skin type no skin type parts.");
            return false;
        }
        ModLogger.log(String.format("Registering skin: %s", skinType.getRegistryName()));
        this.skinTypeMap.put(skinType.getRegistryName(), skinType);
        ArrayList<ISkinPartType> skinParts = skinType.getSkinParts();
        for (int i = 0; i < skinParts.size(); ++i) {
            ISkinPartType skinPart = skinParts.get(i);
            this.skinPartMap.put(skinPart.getRegistryName(), skinPart);
        }
        return true;
    }

    @Override
    public ISkinType getSkinTypeFromRegistryName(String registryName) {
        if (registryName == null | registryName.trim().isEmpty()) {
            return null;
        }
        if (registryName.equals(oldSkinSkirt.getRegistryName())) {
            return skinLegs;
        }
        if (registryName.equals(oldSkinArrow.getRegistryName())) {
            return skinBow;
        }
        ISkinType skinType = this.skinTypeMap.get(registryName);
        return skinType;
    }

    public ISkinType getSkinTypeFromLegacyId(int legacyId) {
        switch (legacyId) {
            case 0: {
                return this.getSkinTypeFromRegistryName("armourers:head");
            }
            case 1: {
                return this.getSkinTypeFromRegistryName("armourers:chest");
            }
            case 2: {
                return this.getSkinTypeFromRegistryName("armourers:legs");
            }
            case 3: {
                return this.getSkinTypeFromRegistryName("armourers:legs");
            }
            case 4: {
                return this.getSkinTypeFromRegistryName("armourers:feet");
            }
            case 5: {
                return this.getSkinTypeFromRegistryName("armourers:sword");
            }
            case 6: {
                return this.getSkinTypeFromRegistryName("armourers:bow");
            }
            case 7: {
                return this.getSkinTypeFromRegistryName("armourers:bow");
            }
        }
        return null;
    }

    @Override
    public ISkinPartType getSkinPartFromRegistryName(String registryName) {
        if (registryName == null | registryName.trim().isEmpty()) {
            return null;
        }
        return this.skinPartMap.get(registryName);
    }

    public ISkinPartType getSkinPartFromLegacyId(int legacyId) {
        switch (legacyId) {
            case 0: {
                return this.getSkinPartFromRegistryName("armourers:head.base");
            }
            case 1: {
                return this.getSkinPartFromRegistryName("armourers:chest.base");
            }
            case 2: {
                return this.getSkinPartFromRegistryName("armourers:chest.leftArm");
            }
            case 3: {
                return this.getSkinPartFromRegistryName("armourers:chest.rightArm");
            }
            case 4: {
                return this.getSkinPartFromRegistryName("armourers:legs.leftLeg");
            }
            case 5: {
                return this.getSkinPartFromRegistryName("armourers:legs.rightLeg");
            }
            case 6: {
                return this.getSkinPartFromRegistryName("armourers:legs.skirt");
            }
            case 7: {
                return this.getSkinPartFromRegistryName("armourers:feet.leftFoot");
            }
            case 8: {
                return this.getSkinPartFromRegistryName("armourers:feet.rightFoot");
            }
            case 9: {
                return this.getSkinPartFromRegistryName("armourers:sword.base");
            }
            case 10: {
                return this.getSkinPartFromRegistryName("armourers:bow.base");
            }
        }
        return null;
    }

    @Override
    public ArrayList<ISkinType> getRegisteredSkinTypes() {
        ArrayList<ISkinType> skinTypes = new ArrayList<ISkinType>();
        for (int i = 0; i < this.skinTypeMap.size(); ++i) {
            String registryName = (String)this.skinTypeMap.keySet().toArray()[i];
            ISkinType skinType = this.getSkinTypeFromRegistryName(registryName);
            if (skinType == null) continue;
            skinTypes.add(skinType);
        }
        return skinTypes;
    }

    public int getNumberOfSkinRegistered() {
        return this.skinTypeMap.size();
    }

    public String getLocalizedSkinTypeName(ISkinType skinType) {
        String localizedName = "skinType." + skinType.getRegistryName() + ".name";
        return StatCollector.func_74838_a((String)localizedName);
    }

    @SideOnly(value=Side.CLIENT)
    public String getLocalizedSkinPartTypeName(ISkinPartType skinPartType) {
        String localizedName = "skinPartType." + skinPartType.getRegistryName() + ".name";
        return StatCollector.func_74838_a((String)localizedName);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        if (event.map.func_130086_a() == 1) {
            for (int i = 0; i < this.skinTypeMap.size(); ++i) {
                String registryName = (String)this.skinTypeMap.keySet().toArray()[i];
                ISkinType skinType = this.getSkinTypeFromRegistryName(registryName);
                if (skinType == null) continue;
                skinType.registerIcon((IIconRegister)event.map);
            }
        }
    }

    @Override
    public ISkinType getSkinTypeHead() {
        return skinHead;
    }

    @Override
    public ISkinType getSkinTypeChest() {
        return skinChest;
    }

    @Override
    public ISkinType getSkinTypeLegs() {
        return skinLegs;
    }

    @Override
    public ISkinType getSkinTypeSkirt() {
        return oldSkinSkirt;
    }

    @Override
    public ISkinType getSkinTypeFeet() {
        return skinFeet;
    }
}

