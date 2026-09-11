/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemPotion
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.util.EnumHelper
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.GiTurtle;
import JinRyuu.DragonBC.common.Items.ItemAnyBase;
import JinRyuu.DragonBC.common.Items.ItemArmorNormal;
import JinRyuu.DragonBC.common.Items.ItemBodyDBC;
import JinRyuu.DragonBC.common.Items.ItemBraveSword;
import JinRyuu.DragonBC.common.Items.ItemBraveSwordBlade;
import JinRyuu.DragonBC.common.Items.ItemBraveSwordHandle;
import JinRyuu.DragonBC.common.Items.ItemDBCBucket;
import JinRyuu.DragonBC.common.Items.ItemDBCRender;
import JinRyuu.DragonBC.common.Items.ItemDBCRender1;
import JinRyuu.DragonBC.common.Items.ItemDBCRender2;
import JinRyuu.DragonBC.common.Items.ItemDBCRenderRad;
import JinRyuu.DragonBC.common.Items.ItemDinoMeat;
import JinRyuu.DragonBC.common.Items.ItemDragonRadar;
import JinRyuu.DragonBC.common.Items.ItemHeadDBC;
import JinRyuu.DragonBC.common.Items.ItemKatana;
import JinRyuu.DragonBC.common.Items.ItemKatanaBlade;
import JinRyuu.DragonBC.common.Items.ItemKatanaHandle;
import JinRyuu.DragonBC.common.Items.ItemKatchin;
import JinRyuu.DragonBC.common.Items.ItemMedMoss;
import JinRyuu.DragonBC.common.Items.ItemRec;
import JinRyuu.DragonBC.common.Items.ItemSaibaiSeed;
import JinRyuu.DragonBC.common.Items.ItemSenzu;
import JinRyuu.DragonBC.common.Items.ItemSwordBase;
import JinRyuu.DragonBC.common.Items.ItemToolAxe;
import JinRyuu.DragonBC.common.Items.ItemToolPickaxe;
import JinRyuu.DragonBC.common.Items.ItemWarenai;
import JinRyuu.DragonBC.common.Items.ItemWeapon;
import JinRyuu.DragonBC.common.Items.ItemWeaponRender2;
import JinRyuu.DragonBC.common.Items.ItemWeight;
import JinRyuu.DragonBC.common.Items.ItemZSword;
import JinRyuu.DragonBC.common.Items.ItemZSwordBlade;
import JinRyuu.DragonBC.common.Items.ItemZSwordHandle;
import JinRyuu.DragonBC.common.Items.VanityColor;
import JinRyuu.DragonBC.common.Items.m.ModdedItems;
import JinRyuu.DragonBC.common.Render.DragonBlock01Item;
import JinRyuu.DragonBC.common.Render.DragonBlock01ItemR;
import JinRyuu.DragonBC.common.Render.KintounBlackItem;
import JinRyuu.DragonBC.common.Render.KintounItem;
import JinRyuu.DragonBC.common.Render.SpacePod01Item;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH2;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPotion;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsDBC {
    public static Item.ToolMaterial KATANA = EnumHelper.addToolMaterial((String)"KATANA", (int)1, (int)500, (float)6.0f, (float)10.0f, (int)20);
    public static Item.ToolMaterial ZSWORD = EnumHelper.addToolMaterial((String)"ZSWORD", (int)3, (int)2000, (float)8.0f, (float)20.0f, (int)5);
    public static Item.ToolMaterial BSWORD = EnumHelper.addToolMaterial((String)"BSWORD", (int)2, (int)1000, (float)12.0f, (float)15.0f, (int)25);
    public static Item.ToolMaterial HandleSWORD = EnumHelper.addToolMaterial((String)"HandleSWORD", (int)1, (int)100, (float)1.0f, (float)1.0f, (int)1);
    public static Item.ToolMaterial BladeSWORD = EnumHelper.addToolMaterial((String)"BladeSWORD", (int)1, (int)100, (float)2.0f, (float)1.0f, (int)1);
    public static Item.ToolMaterial STAFFPP = EnumHelper.addToolMaterial((String)"STAFFPP", (int)1, (int)1000, (float)8.0f, (float)10.0f, (int)1);
    public static Item.ToolMaterial EVILSPEAR = EnumHelper.addToolMaterial((String)"EVILSPEAR", (int)1, (int)1000, (float)6.0f, (float)10.0f, (int)20);
    public static Item.ToolMaterial DIMENSIONSWORD = EnumHelper.addToolMaterial((String)"DIMENSIONSWORD", (int)1, (int)100, (float)12.0f, (float)20.0f, (int)20);
    public static Item.ToolMaterial SMALLCLUB = EnumHelper.addToolMaterial((String)"SMALLCLUB", (int)1, (int)70, (float)1.0f, (float)1.0f, (int)1);
    public static Item ItemEvilSpear;
    public static Item ItemDimensionSword;
    public static Item ItemJanembaEssence;
    public static Item ItemSmallClub;
    public static ItemArmor.ArmorMaterial GI2;
    public static ItemArmor.ArmorMaterial GI;
    public static ItemArmor.ArmorMaterial WARENAI;
    public static ItemArmor.ArmorMaterial tier0;
    public static ItemArmor.ArmorMaterial tier1;
    public static ItemArmor.ArmorMaterial tier2;
    public static ItemArmor.ArmorMaterial tier3;
    public static ItemArmor.ArmorMaterial scouter1;
    public static ItemArmor.ArmorMaterial scouter2;
    public static ItemArmor.ArmorMaterial scouter3;
    public static Item SpacePod01Item;
    public static Item SpacePodVegetaChip;
    public static Item KintounItem;
    public static Item KintounBlackItem;
    public static Item ItemAlienTechChipTier1;
    public static Item ItemChipTier2;
    public static Item ItemChipTier3;
    public static Item ItemDragonRadar;
    public static Item ItemDragonBlock;
    public static Item ItemNamekDragonBlock;
    public static Item ItemSaibaiSeed;
    public static Item ItemSenzu;
    public static Item ItemWarenai;
    public static Item ItemWarenaiFabric;
    public static Item ItemStrongFabric;
    public static Item ItemKatana;
    public static Item ItemNamekIT;
    public static Item ItemKatchin;
    public static Item ItemZSword;
    public static Item ItemBraveSword;
    public static Item ItemKatanaHandle;
    public static Item ItemBraveSwordHandle;
    public static Item ItemZSwordHandle;
    public static Item ItemKatanaSwordBlade;
    public static Item ItemBraveSwordBlade;
    public static Item ItemZSwordBlade;
    public static Item ItemPP;
    public static Item ItemDinoMeat;
    public static Item ItemDinoMeatCooked;
    public static Item ItemDinoMeatBig;
    public static Item ItemDinoMeatCookedBig;
    public static Item ItemBucketMedLiq;
    public static Item ItemMedMoss;
    public static ItemPotion Bloods;
    public static ArrayList<Item> BattleArmors;
    public static ArrayList<Item> OutfitGis;
    public static Item BattleArmorHelmet00;
    public static Item BattleArmorChest00;
    public static Item BattleArmorLegs00;
    public static Item BattleArmorBoots00;
    public static Item BattleArmorHelmet01;
    public static Item BattleArmorChest01;
    public static Item BattleArmorLegs01;
    public static Item BattleArmorBoots01;
    public static Item BattleArmorHelmet02;
    public static Item BattleArmorChest02;
    public static Item BattleArmorLegs02;
    public static Item BattleArmorBoots02;
    public static Item BattleArmorHelmet03;
    public static Item BattleArmorChest03;
    public static Item BattleArmorLegs03;
    public static Item BattleArmorBoots03;
    public static Item BattleArmorHelmet04;
    public static Item BattleArmorChest04;
    public static Item BattleArmorLegs04;
    public static Item BattleArmorBoots04;
    public static Item BattleArmorHelmet05;
    public static Item BattleArmorChest05;
    public static Item BattleArmorLegs05;
    public static Item BattleArmorBoots05;
    public static Item BattleArmorHelmet00a;
    public static Item BattleArmorHelmet01a;
    public static Item BattleArmorHelmet02a;
    public static Item BattleArmorHelmet03a;
    public static Item BattleArmorHelmet04a;
    public static Item BattleArmorHelmet05a;
    public static Item BattleArmorHelmet00b;
    public static Item BattleArmorHelmet01b;
    public static Item BattleArmorHelmet02b;
    public static Item BattleArmorHelmet03b;
    public static Item BattleArmorHelmet04b;
    public static Item BattleArmorHelmet05b;
    public static Item BattleArmorHelmet06;
    public static Item BattleArmorChest06;
    public static Item BattleArmorLegs06;
    public static Item BattleArmorBoots06;
    public static Item BattleArmorHelmet07;
    public static Item BattleArmorChest07;
    public static Item BattleArmorBoots07;
    public static Item BattleArmorHelmet08;
    public static Item BattleArmorChest08;
    public static Item BattleArmorBoots08;
    public static Item BattleArmorHelmet09;
    public static Item BattleArmorChest09;
    public static Item BattleArmorLegs09;
    public static Item BattleArmorBoots09;
    public static Item Coat;
    public static Item Coat_2;
    public static Item GiFighter1Torso;
    public static Item GiFighter1Leg;
    public static Item GiFighter1Boots;
    public static Item GiNamekTorso;
    public static Item GiNamekLeg;
    public static Item GiNamekBoots;
    public static Item GiFutureTorso;
    public static Item GiFutureLeg;
    public static Item GiFutureBoots;
    public static Item GiFighter2Torso;
    public static Item GiFighter2Leg;
    public static Item GiFighter2Boots;
    public static Item JinTorso;
    public static Item JinLeg;
    public static Item JinBoots;
    public static Item KameTorso1;
    public static Item KameBoots1;
    public static Item GiFighter3Torso;
    public static Item ItemWeightShell;
    public static Item ItemWeightShirt;
    public static Item ItemWeightHandLeg;
    public static Item ItemWeightCape;
    public static Item ItemWeightHeavySuit;
    public static final String[] ItemsOutfitnams;
    public static final String[] ItemsOutfitType;
    public static final String[] ItemsGiType;
    public static final String[] ItemsGiType2;
    public static final int outfit_c16 = 0;
    public static final int outfit_c17 = 1;
    public static final int outfit_c18 = 2;
    public static final int outfit_c19 = 3;
    public static final int outfit_c20 = 4;
    public static final int outfit_darbura = 5;
    public static final int outfit_majinbuufat = 6;
    public static final int outfit_majinbuu = 7;
    public static final int outfit_tien = 8;
    public static final int outfit_chichi = 9;
    public static final int outfit_genblue = 10;
    public static final int outfit_highschool = 11;
    public static final int outfit_karinga = 12;
    public static final int outfit_pilaf = 13;
    public static final int outfit_saiyaman = 14;
    public static final int outfit_supkai = 15;
    public static final int outfit_black = 16;
    public static final int outfit_fusionDance = 17;
    public static final int outfit_broly = 18;
    public static final int outfit_demonclan = 19;
    public static final int outfit_hercules = 20;
    public static final int outfit_kingkai = 21;
    public static final int outfit_lordslug = 22;
    public static final int outfit_mrpopo = 23;
    public static final int outfit_orintemp = 24;
    public static final int outfit_uub = 25;
    public static final int outfit_videl2 = 26;
    public static final int outfit_videl3 = 27;
    public static final int outfit_videl = 28;
    public static final int outfit_yamcha = 29;
    public static final int outfit_superKaiBlack = 30;
    public static final int outfit_tracksuit = 31;
    public static Item[] ItemsOutfit0;
    public static Item[] ItemsOutfit1;
    public static Item[] ItemsOutfit2;
    public static Item[] ItemsOutfit3;
    public static final int ItemGis = 12;
    public static final int ItemGis2 = 2;
    public static Item[] ItemsGi0;
    public static Item[] ItemsGi1;
    public static Item[] ItemsGi2;
    public static Item[] ItemsGi3;
    public static Item[] ItemsGi1_2;
    public static final String[] ItemsBodynams;
    public static Item[] ItemsBody;
    public static final String[] ItemsHeadnams;
    public static Item[] ItemsHead;
    public static final int ItemBodysuitAmount = 8;
    public static ItemBodyDBC[] ItemBodysuits;
    public static final int ItemGiAmount = 3;
    public static final int[] ItemGiType;
    public static VanityColor[] ItemGiBody;
    public static ArrayList<Item> ItemGi;
    public static final String[] ItemsRecnams;
    public static Item[] ItemsRec;
    public static final int ItemLehnoriIngot = 0;
    public static final int ItemDlogIngot = 1;
    public static final int ItemDnomaidIngot = 2;
    public static final int Itemjjay = 3;
    public static final int ItemDlogSword = 4;
    public static final int ItemDnomaidSword = 5;
    public static final int ItemLehnoriSword = 6;
    public static final int ItemDlogPickaxe = 7;
    public static final int ItemDnomaidPickaxe = 8;
    public static final int ItemLehnoriPickaxe = 9;
    public static final int ItemDlogAxe = 10;
    public static final int ItemDnomaidAxe = 11;
    public static final int ItemLehnoriAxe = 12;
    public static final int ItemLehnoriHelm = 13;
    public static final int ItemLehnoriChest = 14;
    public static final int ItemLehnoriLeg = 15;
    public static final int ItemLehnoriBoot = 16;
    public static final int ItemDlogHelm = 17;
    public static final int ItemDlogChest = 18;
    public static final int ItemDlogLeg = 19;
    public static final int ItemDlogBoot = 20;
    public static final int ItemDnomaidHelm = 21;
    public static final int ItemDnomaidChest = 22;
    public static final int ItemDnomaidLeg = 23;
    public static final int ItemDnomaidBoot = 24;
    public static final String[] ItemOWnams;
    public static final Item[] ItemOWtypes;
    public static Item[] ItemsOW;
    public static final int[] ItemsVanityNum;
    public static final int[] ItemVanity3;
    public static Item[] ItemsVanity;

    public static void init() {
        int id;
        int iddd;
        int j;
        String[] s;
        int i;
        SpacePod01Item = GameRegistry.registerItem((Item)new SpacePod01Item(BlocksDBC.SpacePod01Block, GI, 0, 0).func_77655_b("SpacePod01Item"), (String)"SpacePod01Item", null);
        KintounItem = GameRegistry.registerItem((Item)new KintounItem().func_77655_b("KintounItem"), (String)"KintounItem", null);
        KintounBlackItem = GameRegistry.registerItem((Item)new KintounBlackItem().func_77655_b("KintounBlackItem"), (String)"KintounBlackItem", null);
        ItemBucketMedLiq = GameRegistry.registerItem((Item)new ItemDBCBucket(BlocksDBC.BlockHealingPods).func_77655_b("ItemBucketMedLiq"), (String)"ItemBucketMedLiq", null);
        ItemMedMoss = GameRegistry.registerItem((Item)new ItemMedMoss(1, 0.1f).func_77655_b("ItemMedMoss"), (String)"ItemMedMoss", null);
        ItemSenzu = GameRegistry.registerItem((Item)new ItemSenzu(20, 10.0f, true).func_77655_b("ItemSenzu"), (String)"ItemSenzu", null);
        ItemSaibaiSeed = GameRegistry.registerItem((Item)new ItemSaibaiSeed().func_77655_b("ItemSaibaiSeed"), (String)"ItemSaibaiSeed", null);
        ItemWarenai = GameRegistry.registerItem((Item)new ItemWarenai().func_77655_b("ItemWarenai"), (String)"ItemWarenai", null);
        ItemKatana = GameRegistry.registerItem((Item)new ItemKatana(KATANA).func_77655_b("ItemKatana"), (String)"ItemKatana", null);
        ItemKatchin = GameRegistry.registerItem((Item)new ItemKatchin().func_77655_b("ItemKatchin"), (String)"ItemKatchin", null);
        ItemBraveSword = GameRegistry.registerItem((Item)new ItemBraveSword(BSWORD).func_77655_b("ItemBraveSword"), (String)"ItemBraveSword", null);
        ItemZSword = GameRegistry.registerItem((Item)new ItemZSword(ZSWORD).func_77655_b("ItemZSword"), (String)"ItemZSword", null);
        ItemKatanaHandle = GameRegistry.registerItem((Item)new ItemKatanaHandle(HandleSWORD).func_77655_b("ItemKatanaHandle"), (String)"ItemKatanaHandle", null);
        ItemBraveSwordHandle = GameRegistry.registerItem((Item)new ItemBraveSwordHandle(HandleSWORD).func_77655_b("ItemBraveSwordHandle"), (String)"ItemBraveSwordHandle", null);
        ItemZSwordHandle = GameRegistry.registerItem((Item)new ItemZSwordHandle(HandleSWORD).func_77655_b("ItemZSwordHandle"), (String)"ItemZSwordHandle", null);
        ItemKatanaSwordBlade = GameRegistry.registerItem((Item)new ItemKatanaBlade(BladeSWORD).func_77655_b("ItemKatanaSwordBlade"), (String)"ItemKatanaSwordBlade", null);
        ItemBraveSwordBlade = GameRegistry.registerItem((Item)new ItemBraveSwordBlade(BladeSWORD).func_77655_b("ItemBraveSwordBlade"), (String)"ItemBraveSwordBlade", null);
        ItemZSwordBlade = GameRegistry.registerItem((Item)new ItemZSwordBlade(BladeSWORD).func_77655_b("ItemZSwordBlade"), (String)"ItemZSwordBlade", null);
        ItemEvilSpear = GameRegistry.registerItem((Item)new ItemSwordBase(EVILSPEAR).func_77655_b("ItemEvilSpear"), (String)"ItemEvilSpear", null);
        ItemDimensionSword = GameRegistry.registerItem((Item)new ItemSwordBase(DIMENSIONSWORD).func_77655_b("ItemDimensionSword"), (String)"ItemDimensionSword", null);
        ItemJanembaEssence = GameRegistry.registerItem((Item)new ItemAnyBase().func_77655_b("ItemJanembaEssence"), (String)"ItemJanembaEssence", null);
        ItemSmallClub = GameRegistry.registerItem((Item)new ItemAnyBase().func_77625_d(1).func_77655_b("ItemSmallClub"), (String)"ItemSmallClub", null);
        ItemPP = GameRegistry.registerItem((Item)new ItemWeapon(STAFFPP).func_77655_b("ItemPP"), (String)"ItemPP", null);
        for (i = 0; i < ItemOWnams.length; ++i) {
            ItemsDBC.ItemsOW[i] = GameRegistry.registerItem((Item)ItemOWtypes[i].func_77655_b(ItemOWnams[i]), (String)ItemOWnams[i], null);
        }
        for (i = 0; i < ItemsRecnams.length; ++i) {
            ItemsDBC.ItemsRec[i] = GameRegistry.registerItem((Item)new ItemRec(ItemsRecnams[i]).func_77655_b("record." + ItemsRecnams[i]), (String)("record." + ItemsRecnams[i]), null);
        }
        for (i = 0; i < ItemsOutfitnams.length; ++i) {
            s = ItemsOutfitType[i].split(",");
            block18: for (j = 0; j < s.length; ++j) {
                int id2 = Integer.parseInt(s[j]);
                switch (id2) {
                    case 0: {
                        ItemsDBC.ItemsOutfit0[i] = GameRegistry.registerItem((Item)new GiTurtle(GI2, 0, id2, ItemsOutfitnams[i]).func_77655_b(ItemsOutfitnams[i] + "Head"), (String)(ItemsOutfitnams[i] + "Head"), null);
                        OutfitGis.add(ItemsDBC.ItemsOutfit0[i]);
                        continue block18;
                    }
                    case 1: {
                        ItemsDBC.ItemsOutfit1[i] = GameRegistry.registerItem((Item)new GiTurtle(GI2, 0, id2, ItemsOutfitnams[i]).func_77655_b(ItemsOutfitnams[i] + "Chest"), (String)(ItemsOutfitnams[i] + "Chest"), null);
                        OutfitGis.add(ItemsDBC.ItemsOutfit1[i]);
                        continue block18;
                    }
                    case 2: {
                        ItemsDBC.ItemsOutfit2[i] = GameRegistry.registerItem((Item)new GiTurtle(GI2, 0, id2, ItemsOutfitnams[i]).func_77655_b(ItemsOutfitnams[i] + "Leg"), (String)(ItemsOutfitnams[i] + "Leg"), null);
                        OutfitGis.add(ItemsDBC.ItemsOutfit2[i]);
                        continue block18;
                    }
                    case 3: {
                        ItemsDBC.ItemsOutfit3[i] = GameRegistry.registerItem((Item)new GiTurtle(GI2, 0, id2, ItemsOutfitnams[i]).func_77655_b(ItemsOutfitnams[i] + "Boots"), (String)(ItemsOutfitnams[i] + "Boots"), null);
                        OutfitGis.add(ItemsDBC.ItemsOutfit3[i]);
                    }
                }
            }
        }
        for (i = 0; i < 12; ++i) {
            s = ItemsGiType[i].split(",");
            block20: for (j = 0; j < s.length; ++j) {
                iddd = 0;
                id = Integer.parseInt(s[j]);
                switch (id) {
                    case 0: {
                        ItemsDBC.ItemsGi0[i] = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, id, "gi_color_" + i).func_77655_b("gi_color_" + i + "Head"), (String)("gi_color_" + i + "Head"), null);
                        ItemGi.add(ItemsDBC.ItemsGi0[i]);
                        continue block20;
                    }
                    case 1: {
                        ItemsDBC.ItemsGi1[i] = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, id, "gi_color_" + i).func_77655_b("gi_color_" + i + "Chest"), (String)("gi_color_" + i + "Chest"), null);
                        ItemGi.add(ItemsDBC.ItemsGi1[i]);
                        continue block20;
                    }
                    case 2: {
                        ItemsDBC.ItemsGi2[i] = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, id, "gi_color_" + i).func_77655_b("gi_color_" + i + "Leg"), (String)("gi_color_" + i + "Leg"), null);
                        ItemGi.add(ItemsDBC.ItemsGi2[i]);
                        continue block20;
                    }
                    case 3: {
                        ItemsDBC.ItemsGi3[i] = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, id, "gi_color_" + i).func_77655_b("gi_color_" + i + "Boots"), (String)("gi_color_" + i + "Boots"), null);
                        ItemGi.add(ItemsDBC.ItemsGi3[i]);
                    }
                }
            }
        }
        for (i = 0; i < 8; ++i) {
            ItemsDBC.ItemBodysuits[i] = (ItemBodyDBC)GameRegistry.registerItem((Item)new ItemBodyDBC(JRMCoreH2.cols[15], "body" + i).func_77655_b("bodysuit" + i), (String)("bodysuit" + i), null);
        }
        for (i = 0; i < 3; ++i) {
            ItemsDBC.ItemGiBody[i] = (VanityColor)GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, ItemGiType[i], "gi" + i).func_77655_b("gi" + i), (String)("gi" + i), null);
        }
        ItemWarenaiFabric = GameRegistry.registerItem((Item)new ItemAnyBase().func_77655_b("ItemWarenaiFabric"), (String)"ItemWarenaiFabric", null);
        ItemStrongFabric = GameRegistry.registerItem((Item)new ItemAnyBase().func_77655_b("ItemStrongFabric"), (String)"ItemStrongFabric", null);
        ItemAlienTechChipTier1 = GameRegistry.registerItem((Item)new ItemKatchin().func_77655_b("ItemAlienTechChipTier1"), (String)"ItemAlienTechChipTier1", null);
        ItemChipTier2 = GameRegistry.registerItem((Item)new ItemKatchin().func_77655_b("ItemChipTier2"), (String)"ItemChipTier2", null);
        ItemChipTier3 = GameRegistry.registerItem((Item)new ItemKatchin().func_77655_b("ItemChipTier3"), (String)"ItemChipTier3", null);
        ItemDinoMeat = GameRegistry.registerItem((Item)new ItemDinoMeat(2, 0.0f, 1.0f).func_77655_b("ItemDinoMeat"), (String)"ItemDinoMeat", null);
        ItemDinoMeatCooked = GameRegistry.registerItem((Item)new ItemDinoMeat(5, 0.0f, 1.6f).func_77655_b("ItemDinoMeatCooked"), (String)"ItemDinoMeatCooked", null);
        ItemDinoMeatBig = GameRegistry.registerItem((Item)new ItemDinoMeat(3, 0.0f, 1.5f).func_77655_b("ItemDinoMeatBig"), (String)"ItemDinoMeatBig", null);
        ItemDinoMeatCookedBig = GameRegistry.registerItem((Item)new ItemDinoMeat(8, 0.0f, 2.4f).func_77655_b("ItemDinoMeatCookedBig"), (String)"ItemDinoMeatCookedBig", null);
        ItemWeightShell = GameRegistry.registerItem((Item)new ItemWeight(5).func_77655_b("ItemWeightShell"), (String)"ItemWeightShell", null);
        ItemWeightShirt = GameRegistry.registerItem((Item)new ItemWeight(5).func_77655_b("ItemWeightShirt"), (String)"ItemWeightShirt", null);
        ItemWeightHandLeg = GameRegistry.registerItem((Item)new ItemWeight(5).func_77655_b("ItemWeightHandLeg"), (String)"ItemWeightHandLeg", null);
        ItemWeightCape = GameRegistry.registerItem((Item)new ItemWeight(5).func_77655_b("ItemWeightCape"), (String)"ItemWeightCape", null);
        ItemWeightHeavySuit = GameRegistry.registerItem((Item)new ItemWeight(5).func_77655_b("ItemWeightHeavySuit"), (String)"ItemWeightHeavySuit", null);
        ItemDragonRadar = GameRegistry.registerItem((Item)new ItemDragonRadar().func_77655_b("ItemDragonRadar"), (String)"ItemDragonRadar", null);
        ItemDragonBlock = GameRegistry.registerItem((Item)new DragonBlock01Item(BlocksDBC.BlockDragonBall).func_77655_b("ItemDragonBlock"), (String)"ItemDragonBlock", null);
        ItemNamekDragonBlock = GameRegistry.registerItem((Item)new DragonBlock01Item(BlocksDBC.BlockNamekDragonBall).func_77655_b("ItemNamekDragonBlock"), (String)"ItemNamekDragonBlock", null);
        BattleArmorHelmet00 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier0", "1").func_77655_b("YellowScouter"), (String)"YellowScouter", null);
        BattleArmors.add(BattleArmorHelmet00);
        BattleArmorHelmet01 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier1", "1").func_77655_b("RedScouter"), (String)"RedScouter", null);
        BattleArmors.add(BattleArmorHelmet01);
        BattleArmorHelmet02 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier2", "1").func_77655_b("PurpleScouter"), (String)"PurpleScouter", null);
        BattleArmors.add(BattleArmorHelmet02);
        BattleArmorHelmet03 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier3", "1").func_77655_b("BlueScouter"), (String)"BlueScouter", null);
        BattleArmors.add(BattleArmorHelmet03);
        BattleArmorHelmet04 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier4", "1").func_77655_b("GreenScouter"), (String)"GreenScouter", null);
        BattleArmors.add(BattleArmorHelmet04);
        BattleArmorHelmet05 = GameRegistry.registerItem((Item)new ItemHeadDBC("tier5", "1").func_77655_b("PinkScouter"), (String)"PinkScouter", null);
        BattleArmors.add(BattleArmorHelmet05);
        BattleArmorChest00 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 1, "tier0").func_77655_b("BattleArmorChest00"), (String)"BattleArmorChest00", null);
        BattleArmors.add(BattleArmorChest00);
        BattleArmorLegs00 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 2, "tier0").func_77655_b("BattleArmorLegs00"), (String)"BattleArmorLegs00", null);
        BattleArmors.add(BattleArmorLegs00);
        BattleArmorBoots00 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 3, "tier0").func_77655_b("BattleArmorBoots00"), (String)"BattleArmorBoots00", null);
        BattleArmors.add(BattleArmorBoots00);
        BattleArmorChest01 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 1, "tier1").func_77655_b("BattleArmorChest01"), (String)"BattleArmorChest01", null);
        BattleArmors.add(BattleArmorChest01);
        BattleArmorLegs01 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 2, "tier1").func_77655_b("BattleArmorLegs01"), (String)"BattleArmorLegs01", null);
        BattleArmors.add(BattleArmorLegs01);
        BattleArmorBoots01 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 3, "tier1").func_77655_b("BattleArmorBoots01"), (String)"BattleArmorBoots01", null);
        BattleArmors.add(BattleArmorBoots01);
        BattleArmorChest02 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 1, "tier2").func_77655_b("BattleArmorChest02"), (String)"BattleArmorChest02", null);
        BattleArmors.add(BattleArmorChest02);
        BattleArmorLegs02 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 2, "tier2").func_77655_b("BattleArmorLegs02"), (String)"BattleArmorLegs02", null);
        BattleArmors.add(BattleArmorLegs02);
        BattleArmorBoots02 = GameRegistry.registerItem((Item)new GiTurtle(tier0, 0, 3, "tier2").func_77655_b("BattleArmorBoots02"), (String)"BattleArmorBoots02", null);
        BattleArmors.add(BattleArmorBoots02);
        BattleArmorChest03 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 1, "tier3").func_77655_b("BattleArmorChest03"), (String)"BattleArmorChest03", null);
        BattleArmors.add(BattleArmorChest03);
        BattleArmorLegs03 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 2, "tier3").func_77655_b("BattleArmorLegs03"), (String)"BattleArmorLegs03", null);
        BattleArmors.add(BattleArmorLegs03);
        BattleArmorBoots03 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 3, "tier3").func_77655_b("BattleArmorBoots03"), (String)"BattleArmorBoots03", null);
        BattleArmors.add(BattleArmorBoots03);
        BattleArmorChest04 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 1, "tier4").func_77655_b("BattleArmorChest04"), (String)"BattleArmorChest04", null);
        BattleArmors.add(BattleArmorChest04);
        BattleArmorLegs04 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 2, "tier4").func_77655_b("BattleArmorLegs04"), (String)"BattleArmorLegs04", null);
        BattleArmors.add(BattleArmorLegs04);
        BattleArmorBoots04 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier4").func_77655_b("BattleArmorBoots04"), (String)"BattleArmorBoots04", null);
        BattleArmors.add(BattleArmorBoots04);
        BattleArmorChest05 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 1, "tier5").func_77655_b("BattleArmorChest05"), (String)"BattleArmorChest05", null);
        BattleArmors.add(BattleArmorChest05);
        BattleArmorLegs05 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 2, "tier5").func_77655_b("BattleArmorLegs05"), (String)"BattleArmorLegs05", null);
        BattleArmors.add(BattleArmorLegs05);
        BattleArmorBoots05 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier5").func_77655_b("BattleArmorBoots05"), (String)"BattleArmorBoots05", null);
        BattleArmors.add(BattleArmorBoots05);
        BattleArmorChest06 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 1, "tier6").func_77655_b("BattleArmorChest06"), (String)"BattleArmorChest06", null);
        BattleArmors.add(BattleArmorChest06);
        BattleArmorLegs06 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 2, "tier6").func_77655_b("BattleArmorLegs06"), (String)"BattleArmorLegs06", null);
        BattleArmors.add(BattleArmorLegs06);
        BattleArmorBoots06 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier6").func_77655_b("BattleArmorBoots06"), (String)"BattleArmorBoots06", null);
        BattleArmors.add(BattleArmorBoots06);
        BattleArmorChest07 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 1, "tier7").func_77655_b("BattleArmorChest07"), (String)"BattleArmorChest07", null);
        BattleArmors.add(BattleArmorChest07);
        BattleArmorBoots07 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier7").func_77655_b("BattleArmorBoots07"), (String)"BattleArmorBoots07", null);
        BattleArmors.add(BattleArmorBoots07);
        BattleArmorChest08 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 1, "tier8").func_77655_b("BattleArmorChest08"), (String)"BattleArmorChest08", null);
        BattleArmors.add(BattleArmorChest08);
        BattleArmorBoots08 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier8").func_77655_b("BattleArmorBoots08"), (String)"BattleArmorBoots08", null);
        BattleArmors.add(BattleArmorBoots08);
        BattleArmorChest09 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 1, "tier9").func_77655_b("BattleArmorChest09"), (String)"BattleArmorChest09", null);
        BattleArmors.add(BattleArmorChest09);
        BattleArmorLegs09 = GameRegistry.registerItem((Item)new GiTurtle(tier2, 0, 2, "tier9").func_77655_b("BattleArmorLegs09"), (String)"BattleArmorLegs09", null);
        BattleArmors.add(BattleArmorLegs09);
        BattleArmorBoots09 = GameRegistry.registerItem((Item)new GiTurtle(tier3, 0, 3, "tier9").func_77655_b("BattleArmorBoots09"), (String)"BattleArmorBoots09", null);
        BattleArmors.add(BattleArmorBoots09);
        Coat = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, 5, "gi_color_" + (ItemGi.size() - 1)).func_77655_b("gi_color_" + (ItemGi.size() - 1) + "Chest"), (String)("gi_color_" + (ItemGi.size() - 1) + "Chest"), null);
        ItemGi.add(Coat);
        Coat_2 = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, 5, "gi_color_" + (ItemGi.size() - 1)).func_77655_b("gi_color_" + (ItemGi.size() - 1) + "Chest"), (String)("gi_color_" + (ItemGi.size() - 1) + "Chest"), null);
        ItemGi.add(Coat_2);
        for (i = 0; i < 2; ++i) {
            s = ItemsGiType2[i].split(",");
            for (j = 0; j < s.length; ++j) {
                iddd = ItemsGiType.length + 3 + i;
                id = Integer.parseInt(s[j]);
                switch (id) {
                    case 1: {
                        ItemsDBC.ItemsGi1_2[i] = GameRegistry.registerItem((Item)new VanityColor(JRMCoreH2.cols[15], GI2, id, "gi_color_" + iddd).func_77655_b("gi_color_" + iddd + "Chest"), (String)("gi_color_" + iddd + "Chest"), null);
                        ItemGi.add(ItemsDBC.ItemsGi1_2[i]);
                    }
                }
            }
        }
        GiFighter1Torso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "zfighter1").func_77655_b("GiFighterTorso1"), (String)"GiFighterTorso1", null);
        OutfitGis.add(GiFighter1Torso);
        GiFighter1Leg = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 2, "zfighter1").func_77655_b("GiFighterLeg1"), (String)"GiFighterLeg1", null);
        OutfitGis.add(GiFighter1Leg);
        GiFighter1Boots = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "zfighter1").func_77655_b("GiFighterBoots1"), (String)"GiFighterBoots1", null);
        OutfitGis.add(GiFighter1Boots);
        GiNamekTorso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "namek").func_77655_b("GiNamekTorso0").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiNamekTorso0", null);
        OutfitGis.add(GiNamekTorso);
        GiNamekLeg = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 2, "namek").func_77655_b("GiNamekLeg0").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiNamekLeg0", null);
        OutfitGis.add(GiNamekLeg);
        GiNamekBoots = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "namek").func_77655_b("GiNamekBoots0").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiNamekBoots0", null);
        OutfitGis.add(GiNamekBoots);
        GiFutureTorso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "future").func_77655_b("GiFutureTorso0"), (String)"GiFutureTorso0", null);
        OutfitGis.add(GiFutureTorso);
        GiFutureLeg = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 2, "future").func_77655_b("GiFutureLeg0"), (String)"GiFutureLeg0", null);
        OutfitGis.add(GiFutureLeg);
        GiFutureBoots = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "future").func_77655_b("GiFutureBoots0"), (String)"GiFutureBoots0", null);
        OutfitGis.add(GiFutureBoots);
        GiFighter2Torso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "zfighter2").func_77655_b("GiFighterTorso2").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiFighterTorso2", null);
        OutfitGis.add(GiFighter2Torso);
        GiFighter2Leg = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 2, "zfighter2").func_77655_b("GiFighterLeg2").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiFighterLeg2", null);
        OutfitGis.add(GiFighter2Leg);
        GiFighter2Boots = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "zfighter2").func_77655_b("GiFighterBoots2").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiFighterBoots2", null);
        OutfitGis.add(GiFighter2Boots);
        JinTorso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "jin").func_77655_b("JinTorso").func_77637_a(mod_DragonBC.DragonBlockC), (String)"JinTorso", null);
        OutfitGis.add(JinTorso);
        JinLeg = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 2, "jin").func_77655_b("JinLeg").func_77637_a(mod_DragonBC.DragonBlockC), (String)"JinLeg", null);
        OutfitGis.add(JinLeg);
        JinBoots = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "jin").func_77655_b("JinBoots").func_77637_a(mod_DragonBC.DragonBlockC), (String)"JinBoots", null);
        OutfitGis.add(JinBoots);
        KameTorso1 = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "kame").func_77655_b("KameTorso1").func_77637_a(mod_DragonBC.DragonBlockC), (String)"KameTorso1", null);
        OutfitGis.add(KameTorso1);
        KameBoots1 = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 3, "kame").func_77655_b("KameBoots1").func_77637_a(mod_DragonBC.DragonBlockC), (String)"KameBoots1", null);
        OutfitGis.add(KameBoots1);
        GiFighter3Torso = GameRegistry.registerItem((Item)new GiTurtle(GI, 0, 1, "zfighter3").func_77655_b("GiFighterTorso3").func_77637_a(mod_DragonBC.DragonBlockC), (String)"GiFighterTorso3", null);
        OutfitGis.add(GiFighter3Torso);
        BattleArmorHelmet00a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier0", "2").func_77655_b("YellowScoutera"), (String)"YellowScoutera", null);
        BattleArmors.add(BattleArmorHelmet00a);
        BattleArmorHelmet01a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier1", "2").func_77655_b("RedScoutera"), (String)"RedScoutera", null);
        BattleArmors.add(BattleArmorHelmet01a);
        BattleArmorHelmet02a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier2", "2").func_77655_b("PurpleScoutera"), (String)"PurpleScoutera", null);
        BattleArmors.add(BattleArmorHelmet02a);
        BattleArmorHelmet03a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier3", "2").func_77655_b("BlueScoutera"), (String)"BlueScoutera", null);
        BattleArmors.add(BattleArmorHelmet03a);
        BattleArmorHelmet04a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier4", "2").func_77655_b("GreenScoutera"), (String)"GreenScoutera", null);
        BattleArmors.add(BattleArmorHelmet04a);
        BattleArmorHelmet05a = GameRegistry.registerItem((Item)new ItemHeadDBC("tier5", "2").func_77655_b("PinkScoutera"), (String)"PinkScoutera", null);
        BattleArmors.add(BattleArmorHelmet05a);
        BattleArmorHelmet00b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier0", "3").func_77655_b("YellowScouterb"), (String)"YellowScouterb", null);
        BattleArmors.add(BattleArmorHelmet00b);
        BattleArmorHelmet01b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier1", "3").func_77655_b("RedScouterb"), (String)"RedScouterb", null);
        BattleArmors.add(BattleArmorHelmet01b);
        BattleArmorHelmet02b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier2", "3").func_77655_b("PurpleScouterb"), (String)"PurpleScouterb", null);
        BattleArmors.add(BattleArmorHelmet02b);
        BattleArmorHelmet03b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier3", "3").func_77655_b("BlueScouterb"), (String)"BlueScouterb", null);
        BattleArmors.add(BattleArmorHelmet03b);
        BattleArmorHelmet04b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier4", "3").func_77655_b("GreenScouterb"), (String)"GreenScouterb", null);
        BattleArmors.add(BattleArmorHelmet04b);
        BattleArmorHelmet05b = GameRegistry.registerItem((Item)new ItemHeadDBC("tier5", "3").func_77655_b("PinkScouterb"), (String)"PinkScouterb", null);
        BattleArmors.add(BattleArmorHelmet05b);
        for (i = 0; i < ItemsVanityNum.length; ++i) {
            ItemsDBC.ItemsVanity[i] = GameRegistry.registerItem((Item)new VanityColor(0xFFFFFF, GI2, ItemsVanityNum[i], "vanity_d_" + i, ItemVanity3[i]).func_77655_b("vanity_d_" + i), (String)("vanity_d_" + i), null);
        }
        ModdedItems.init();
    }

    @SideOnly(value=Side.CLIENT)
    public static void client() {
        MinecraftForgeClient.registerItemRenderer((Item)ItemKatana, (IItemRenderer)new ItemDBCRender("K"));
        MinecraftForgeClient.registerItemRenderer((Item)ItemBraveSword, (IItemRenderer)new ItemDBCRender("S"));
        MinecraftForgeClient.registerItemRenderer((Item)ItemZSword, (IItemRenderer)new ItemDBCRender("Z"));
        MinecraftForgeClient.registerItemRenderer((Item)ItemPP, (IItemRenderer)new ItemWeaponRender2());
        MinecraftForgeClient.registerItemRenderer((Item)ItemDragonRadar, (IItemRenderer)new ItemDBCRenderRad());
        MinecraftForgeClient.registerItemRenderer((Item)ItemDimensionSword, (IItemRenderer)new ItemDBCRender1());
        MinecraftForgeClient.registerItemRenderer((Item)ItemEvilSpear, (IItemRenderer)new ItemDBCRender2());
        MinecraftForgeClient.registerItemRenderer((Item)ItemDragonBlock, (IItemRenderer)new DragonBlock01ItemR(1.0f));
        MinecraftForgeClient.registerItemRenderer((Item)ItemNamekDragonBlock, (IItemRenderer)new DragonBlock01ItemR(2.0f));
    }

    static {
        GI2 = EnumHelper.addArmorMaterial((String)"GI", (int)250, (int[])new int[]{3, 5, 5, 5}, (int)20);
        GI = EnumHelper.addArmorMaterial((String)"GI", (int)150, (int[])new int[]{3, 5, 5, 5}, (int)20);
        WARENAI = EnumHelper.addArmorMaterial((String)"WARENAI", (int)300, (int[])new int[]{3, 8, 6, 3}, (int)10);
        tier0 = EnumHelper.addArmorMaterial((String)"tier0", (int)300, (int[])new int[]{1, 10, 6, 3}, (int)10);
        tier1 = EnumHelper.addArmorMaterial((String)"tier1", (int)300, (int[])new int[]{1, 10, 4, 3}, (int)10);
        tier2 = EnumHelper.addArmorMaterial((String)"tier2", (int)300, (int[])new int[]{1, 10, 2, 3}, (int)10);
        tier3 = EnumHelper.addArmorMaterial((String)"tier3", (int)300, (int[])new int[]{1, 8, 4, 3}, (int)10);
        scouter1 = EnumHelper.addArmorMaterial((String)"scouter1", (int)300, (int[])new int[]{1, 10, 6, 3}, (int)10);
        scouter2 = EnumHelper.addArmorMaterial((String)"scouter2", (int)300, (int[])new int[]{2, 10, 6, 3}, (int)10);
        scouter3 = EnumHelper.addArmorMaterial((String)"scouter3", (int)300, (int[])new int[]{3, 10, 6, 3}, (int)10);
        BattleArmors = new ArrayList();
        OutfitGis = new ArrayList();
        ItemsOutfitnams = new String[]{"c16", "c17", "c18", "c19", "c20", "darbura", "majinbuufat", "majinbuu", "tien", "chichi", "genblue", "highschool", "karinga", "pilaf", "saiyaman", "supkai", "black", "fusiond", "broly", "demonclan", "hercules", "kingkai", "lordslug", "mrpopo", "orintemp", "uub", "videl2", "videl3", "videl", "yamcha", "supkaiblack", "tracksuit", "cabba", "caulifla", "gokugtgi", "gokus", "hit", "kale", "pridetrooper", "vegetas", "whis", "c18s", "superc17", "c18-2", "tienb", "tiens", "pan", "c17s", "xenogoku01"};
        ItemsOutfitType = new String[]{"1,2,3", "1,2,3", "1,2,3", "0,1,2,3", "0,1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "0,1,2,3", "0,1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2", "1,3", "1", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3", "1,2,3"};
        ItemsGiType = new String[]{"1,2,3", "2", "3", "1", "1", "1", "1", "1", "0", "0", "1", "1"};
        ItemsGiType2 = new String[]{"1", "1"};
        ItemsOutfit0 = new Item[ItemsOutfitnams.length];
        ItemsOutfit1 = new Item[ItemsOutfitnams.length];
        ItemsOutfit2 = new Item[ItemsOutfitnams.length];
        ItemsOutfit3 = new Item[ItemsOutfitnams.length];
        ItemsGi0 = new Item[12];
        ItemsGi1 = new Item[12];
        ItemsGi2 = new Item[12];
        ItemsGi3 = new Item[12];
        ItemsGi1_2 = new Item[2];
        ItemsBodynams = new String[]{""};
        ItemsBody = new Item[ItemsBodynams.length];
        ItemsHeadnams = new String[]{""};
        ItemsHead = new Item[ItemsHeadnams.length];
        ItemBodysuits = new ItemBodyDBC[8];
        ItemGiType = new int[]{1, 1, 1};
        ItemGiBody = new VanityColor[3];
        ItemGi = new ArrayList();
        ItemsRecnams = new String[]{"DBC1", "DBC2", "DBC3", "DBC4", "DBC5"};
        ItemsRec = new Item[ItemsRecnams.length];
        ItemOWnams = new String[]{"LehnoriIngot", "DlogIngot", "DnomaidIngot", "JJay", "DlogSword", "DnomaidSword", "LehnoriSword", "DlogPick", "DnomaidPick", "LehnoriPick", "DlogAxe", "DnomaidAxe", "LehnoriAxe", "LehnoriHelmet", "LehnoriChest", "LehnoriPants", "LehnoriBoots", "DlogHelmet", "DlogChest", "DlogPants", "DlogBoots", "DnomaidHelmet", "DnomaidChest", "DnomaidPants", "DnomaidBoots"};
        ItemOWtypes = new Item[]{new ItemAnyBase(), new ItemAnyBase(), new ItemAnyBase(), new ItemAnyBase(), new ItemSwordBase(Item.ToolMaterial.WOOD), new ItemSwordBase(Item.ToolMaterial.EMERALD), new ItemSwordBase(Item.ToolMaterial.IRON), new ItemToolPickaxe(Item.ToolMaterial.WOOD), new ItemToolPickaxe(Item.ToolMaterial.EMERALD), new ItemToolPickaxe(Item.ToolMaterial.IRON), new ItemToolAxe(Item.ToolMaterial.WOOD), new ItemToolAxe(Item.ToolMaterial.EMERALD), new ItemToolAxe(Item.ToolMaterial.IRON), new ItemArmorNormal(ItemArmor.ArmorMaterial.IRON, 0, 0, "lehnorilayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.IRON, 0, 1, "lehnorilayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.IRON, 0, 2, "lehnorilayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.IRON, 0, 3, "lehnorilayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.GOLD, 0, 0, "dloglayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.GOLD, 0, 1, "dloglayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.GOLD, 0, 2, "dloglayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.GOLD, 0, 3, "dloglayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.DIAMOND, 0, 0, "dnomaidlayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.DIAMOND, 0, 1, "dnomaidlayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.DIAMOND, 0, 2, "dnomaidlayer"), new ItemArmorNormal(ItemArmor.ArmorMaterial.DIAMOND, 0, 3, "dnomaidlayer")};
        ItemsOW = new Item[ItemOWnams.length];
        ItemsVanityNum = new int[]{1, 1, 1, 2, 3, 0, 0, 0};
        ItemVanity3 = new int[]{0, -1, -1, -1, 1, 2, 3, 4};
        ItemsVanity = new Item[ItemsVanityNum.length];
    }
}

