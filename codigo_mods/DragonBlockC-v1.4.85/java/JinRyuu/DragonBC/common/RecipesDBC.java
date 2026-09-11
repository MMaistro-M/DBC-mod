/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemBodyDBC;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Items.VanityColor;
import JinRyuu.DragonBC.common.m.MyRecipes;
import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.items.ItemsJRMC;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesDBC {
    public static final Item[] lesz = new Item[]{ItemsDBC.BattleArmorHelmet00a, ItemsDBC.BattleArmorHelmet01a, ItemsDBC.BattleArmorHelmet02a, ItemsDBC.BattleArmorHelmet03a, ItemsDBC.BattleArmorHelmet04a, ItemsDBC.BattleArmorHelmet05a, ItemsDBC.BattleArmorHelmet00b, ItemsDBC.BattleArmorHelmet01b, ItemsDBC.BattleArmorHelmet02b, ItemsDBC.BattleArmorHelmet03b, ItemsDBC.BattleArmorHelmet04b, ItemsDBC.BattleArmorHelmet05b};
    public static final Item[] comp = new Item[]{ItemsDBC.BattleArmorHelmet00, ItemsDBC.BattleArmorHelmet01, ItemsDBC.BattleArmorHelmet02, ItemsDBC.BattleArmorHelmet03, ItemsDBC.BattleArmorHelmet04, ItemsDBC.BattleArmorHelmet05, ItemsDBC.BattleArmorHelmet00a, ItemsDBC.BattleArmorHelmet01a, ItemsDBC.BattleArmorHelmet02a, ItemsDBC.BattleArmorHelmet03a, ItemsDBC.BattleArmorHelmet04a, ItemsDBC.BattleArmorHelmet05a};
    public static final Item[] tier = new Item[]{ItemsDBC.ItemChipTier2, ItemsDBC.ItemChipTier3};

    public static void init() {
        int i;
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatana, 1), (Object[])new Object[]{"L", "X", Character.valueOf('X'), ItemsDBC.ItemKatanaHandle, Character.valueOf('L'), ItemsDBC.ItemKatanaSwordBlade});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemBraveSword, 1), (Object[])new Object[]{"L", "X", Character.valueOf('X'), ItemsDBC.ItemBraveSwordHandle, Character.valueOf('L'), ItemsDBC.ItemBraveSwordBlade});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemZSword, 1), (Object[])new Object[]{"L", "X", Character.valueOf('X'), ItemsDBC.ItemZSwordHandle, Character.valueOf('L'), ItemsDBC.ItemZSwordBlade});
        int WILDCARD = Short.MAX_VALUE;
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatana, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.ItemKatana, 1, WILDCARD), new ItemStack(Items.field_151042_j, 1, 0)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemBraveSword, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.ItemBraveSword, 1, WILDCARD), new ItemStack(ItemsDBC.ItemKatchin, 1, 0), new ItemStack(Items.field_151042_j, 1, 0)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemZSword, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.ItemZSword, 1, WILDCARD), new ItemStack(ItemsDBC.ItemKatchin, 1, 0), new ItemStack(ItemsDBC.ItemKatchin, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatanaHandle, 1), (Object[])new Object[]{"LXL", Character.valueOf('X'), Items.field_151042_j, Character.valueOf('L'), Items.field_151116_aA});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemBraveSwordHandle, 1), (Object[])new Object[]{"X X", "LKL", Character.valueOf('X'), Items.field_151042_j, Character.valueOf('L'), Items.field_151116_aA, Character.valueOf('K'), ItemsDBC.ItemKatchin});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemZSwordHandle, 1), (Object[])new Object[]{"G G", "LKL", Character.valueOf('K'), ItemsDBC.ItemKatchin, Character.valueOf('L'), Items.field_151116_aA, Character.valueOf('G'), Items.field_151043_k});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatanaSwordBlade, 1), (Object[])new Object[]{"XXX", "XXX", " X ", Character.valueOf('X'), Items.field_151042_j});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemBraveSwordBlade, 1), (Object[])new Object[]{"XKX", Character.valueOf('X'), ItemsDBC.ItemKatanaSwordBlade, Character.valueOf('K'), ItemsDBC.ItemKatchin});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemZSwordBlade, 1), (Object[])new Object[]{" K ", "XKX", " K ", Character.valueOf('X'), ItemsDBC.ItemBraveSwordBlade, Character.valueOf('K'), ItemsDBC.ItemKatchin});
        Item[] scouters = new Item[]{ItemsDBC.BattleArmorHelmet00, ItemsDBC.BattleArmorHelmet01, ItemsDBC.BattleArmorHelmet02, ItemsDBC.BattleArmorHelmet03, ItemsDBC.BattleArmorHelmet04, ItemsDBC.BattleArmorHelmet05, ItemsDBC.BattleArmorHelmet00a, ItemsDBC.BattleArmorHelmet01a, ItemsDBC.BattleArmorHelmet02a, ItemsDBC.BattleArmorHelmet03a, ItemsDBC.BattleArmorHelmet04a, ItemsDBC.BattleArmorHelmet05a, ItemsDBC.BattleArmorHelmet00b, ItemsDBC.BattleArmorHelmet01b, ItemsDBC.BattleArmorHelmet02b, ItemsDBC.BattleArmorHelmet03b, ItemsDBC.BattleArmorHelmet04b, ItemsDBC.BattleArmorHelmet05b};
        Item[] armor = new Item[]{ItemsDBC.BattleArmorChest00, ItemsDBC.BattleArmorChest01, ItemsDBC.BattleArmorChest02, ItemsDBC.BattleArmorChest03, ItemsDBC.BattleArmorChest04, ItemsDBC.BattleArmorChest05, ItemsDBC.BattleArmorLegs00, ItemsDBC.BattleArmorLegs01, ItemsDBC.BattleArmorLegs02, ItemsDBC.BattleArmorLegs03, ItemsDBC.BattleArmorLegs04, ItemsDBC.BattleArmorLegs05, ItemsDBC.BattleArmorBoots00, ItemsDBC.BattleArmorBoots01, ItemsDBC.BattleArmorBoots02, ItemsDBC.BattleArmorBoots03, ItemsDBC.BattleArmorBoots04, ItemsDBC.BattleArmorBoots05};
        for (Item n : scouters) {
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(n, 1), (Object[])new Object[]{new ItemStack(n, 1, WILDCARD), new ItemStack(ItemsDBC.ItemWarenai, 1, 0)});
        }
        for (Item n : armor) {
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(n, 1), (Object[])new Object[]{new ItemStack(n, 1, WILDCARD), new ItemStack(ItemsDBC.ItemWarenai, 1, 0), new ItemStack(ItemsDBC.ItemWarenai, 1, 0)});
        }
        for (i = 0; i < ItemsDBC.OutfitGis.size(); ++i) {
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.OutfitGis.get(i), 1), (Object[])new Object[]{new ItemStack(ItemsDBC.OutfitGis.get(i), 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 13), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 11)});
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.OutfitGis.get(i), 1), (Object[])new Object[]{new ItemStack(ItemsDBC.OutfitGis.get(i), 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 2), new ItemStack(Blocks.field_150325_L, 1, 9), new ItemStack(Blocks.field_150325_L, 1, 4)});
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest00, 1), (Object[])new Object[]{"X X", "XXX", "XXX", Character.valueOf('X'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet00, 1), (Object[])new Object[]{"YC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('Y'), new ItemStack(Items.field_151100_aR, 1, 11), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet01, 1), (Object[])new Object[]{"RC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('R'), new ItemStack(Items.field_151100_aR, 1, 1), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet02, 1), (Object[])new Object[]{"PC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('P'), new ItemStack(Items.field_151100_aR, 1, 5), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet03, 1), (Object[])new Object[]{"BC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet04, 1), (Object[])new Object[]{"gC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('g'), new ItemStack(Items.field_151100_aR, 1, 2), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorHelmet05, 1), (Object[])new Object[]{"PC", "GX", Character.valueOf('X'), ItemsDBC.ItemWarenai, Character.valueOf('G'), Blocks.field_150359_w, Character.valueOf('P'), new ItemStack(Items.field_151100_aR, 1, 9), Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest00, 1), (Object[])new Object[]{"X X", "XXX", "XXX", Character.valueOf('X'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest01, 1), (Object[])new Object[]{"D D", "BXB", "BDB", Character.valueOf('X'), ItemsDBC.BattleArmorChest00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 12)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest02, 1), (Object[])new Object[]{"D D", "BXB", "BDB", Character.valueOf('X'), ItemsDBC.BattleArmorChest00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest03, 1), (Object[])new Object[]{"D D", " X ", " D ", Character.valueOf('X'), ItemsDBC.BattleArmorChest00, Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest04, 1), (Object[])new Object[]{" D ", "BXB", "BDB", Character.valueOf('X'), ItemsDBC.BattleArmorChest00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorChest05, 1), (Object[])new Object[]{" D ", " X ", " D ", Character.valueOf('X'), ItemsDBC.BattleArmorChest00, Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs00, 1), (Object[])new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs01, 1), (Object[])new Object[]{"D D", "BXB", "B B", Character.valueOf('X'), ItemsDBC.BattleArmorLegs00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 12)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs02, 1), (Object[])new Object[]{"D D", "BXB", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorLegs00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs03, 1), (Object[])new Object[]{" D ", "BXB", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorLegs00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs04, 1), (Object[])new Object[]{"D D", "BXB", "B B", Character.valueOf('X'), ItemsDBC.BattleArmorLegs00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorLegs05, 1), (Object[])new Object[]{"D D", "BXB", "B B", Character.valueOf('X'), ItemsDBC.BattleArmorLegs00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots00, 1), (Object[])new Object[]{"X X", "X X", Character.valueOf('X'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots01, 1), (Object[])new Object[]{"DXD", "B B", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorBoots00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 12), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots02, 1), (Object[])new Object[]{"DXD", "B B", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorBoots00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 3), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots03, 1), (Object[])new Object[]{" X ", "B B", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorBoots00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots04, 1), (Object[])new Object[]{"DXD", "B B", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorBoots00, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 2), Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.BattleArmorBoots05, 1), (Object[])new Object[]{"DXD", "D D", "   ", Character.valueOf('X'), ItemsDBC.BattleArmorBoots00, Character.valueOf('D'), new ItemStack(Items.field_151100_aR, 1, 15)});
        for (i = 0; i < lesz.length; ++i) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(lesz[i], 1), (Object[])new Object[]{" C ", "CXC", " C ", Character.valueOf('X'), comp[i], Character.valueOf('C'), tier[i / 6]});
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter3Torso, 1), (Object[])new Object[]{"R R", "RBR", "BRB", Character.valueOf('R'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.KameTorso1, 1), (Object[])new Object[]{"R R", "R R", "RRR", Character.valueOf('R'), new ItemStack(Blocks.field_150325_L, 1, 14)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.KameBoots1, 1), (Object[])new Object[]{"B B", "Y Y", Character.valueOf('Y'), new ItemStack(Blocks.field_150325_L, 1, 15), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 0)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter3Torso, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter3Torso, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.KameTorso1, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.KameTorso1, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 14)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.KameBoots1, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.KameBoots1, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Torso, 1), (Object[])new Object[]{"R R", "RBR", "RRR", Character.valueOf('R'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Leg, 1), (Object[])new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150325_L, 1, 14)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Boots, 1), (Object[])new Object[]{"B B", "Y Y", Character.valueOf('Y'), new ItemStack(Blocks.field_150325_L, 1, 4), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Torso, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter1Torso, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Leg, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter1Leg, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 14)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter1Boots, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter1Boots, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 4)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekTorso, 1), (Object[])new Object[]{"B B", "BBB", "BBB", Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekLeg, 1), (Object[])new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekBoots, 1), (Object[])new Object[]{"B B", "B B", Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 12)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekTorso, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiNamekTorso, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 11), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekLeg, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiNamekLeg, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 11), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiNamekBoots, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiNamekBoots, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 12)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureTorso, 1), (Object[])new Object[]{"B B", "BBB", Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureLeg, 1), (Object[])new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150325_L, 1, 7)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureBoots, 1), (Object[])new Object[]{"B B", "B B", Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 4)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureTorso, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFutureTorso, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 11), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureLeg, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFutureLeg, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 7), new ItemStack(Blocks.field_150325_L, 1, 7)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFutureBoots, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFutureBoots, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 4)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Torso, 1), (Object[])new Object[]{"B B", "BRB", "BBB", Character.valueOf('R'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Leg, 1), (Object[])new Object[]{"BBB", "X X", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150325_L, 1, 11), Character.valueOf('B'), new ItemStack(Blocks.field_150325_L, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Boots, 1), (Object[])new Object[]{"Y Y", "Y Y", Character.valueOf('Y'), new ItemStack(Blocks.field_150325_L, 1, 0)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Torso, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter2Torso, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 11), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Leg, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter2Leg, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 14), new ItemStack(Blocks.field_150325_L, 1, 3)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.GiFighter2Boots, 1), (Object[])new Object[]{new ItemStack(ItemsDBC.GiFighter2Boots, 1, WILDCARD), new ItemStack(Blocks.field_150325_L, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.SpacePod01Item, 1), (Object[])new Object[]{"WWW", "WCW", "WWW", Character.valueOf('C'), ItemsDBC.ItemChipTier2, Character.valueOf('W'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemAlienTechChipTier1, 3), (Object[])new Object[]{"GIR", "CCC", "WWW", Character.valueOf('C'), new ItemStack(Items.field_151100_aR, 1, 2), Character.valueOf('G'), Items.field_151043_k, Character.valueOf('I'), Items.field_151042_j, Character.valueOf('R'), Items.field_151137_ax, Character.valueOf('W'), ItemsDBC.ItemWarenai});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemChipTier2, 1), (Object[])new Object[]{"BGB", "CCC", "GBG", Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('G'), Items.field_151114_aO});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemChipTier3, 1), (Object[])new Object[]{"BDB", "CcC", "DBD", Character.valueOf('C'), ItemsDBC.ItemChipTier2, Character.valueOf('c'), ItemsDBC.ItemAlienTechChipTier1, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('D'), Items.field_151045_i});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.ArtGravDevBlock, 1), (Object[])new Object[]{"IKI", "CCC", "KIK", Character.valueOf('C'), ItemsDBC.ItemChipTier3, Character.valueOf('I'), Items.field_151042_j, Character.valueOf('K'), ItemsDBC.ItemKatchin});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemBucketMedLiq, 1), (Object[])new Object[]{"CCC", "CBC", "CCC", Character.valueOf('C'), ItemsDBC.ItemMedMoss, Character.valueOf('B'), Items.field_151133_ar});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemDragonRadar, 1), (Object[])new Object[]{"ICI", "CWC", "ICI", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('W'), ItemsDBC.ItemWarenai, Character.valueOf('C'), ItemsDBC.ItemAlienTechChipTier1});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150344_f, 2), (Object[])new Object[]{"X", Character.valueOf('X'), BlocksDBC.BlockNamekLog});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150347_e, 1), (Object[])new Object[]{"X", Character.valueOf('X'), BlocksDBC.BlockAlienCobbleStone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150348_b, 1), (Object[])new Object[]{"X", Character.valueOf('X'), BlocksDBC.BlockAlienStone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockNamekStone, 4), (Object[])new Object[]{"XX", "XX", Character.valueOf('X'), BlocksDBC.BlockAlienStone});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[11], 1), (Object[])new Object[]{"II ", "IS ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[2], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[8], 1), (Object[])new Object[]{"III", " S ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[2], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[5], 1), (Object[])new Object[]{" I ", " I ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[2], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[10], 1), (Object[])new Object[]{"II ", "IS ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[1], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[7], 1), (Object[])new Object[]{"III", " S ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[1], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[4], 1), (Object[])new Object[]{" I ", " I ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[1], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[12], 1), (Object[])new Object[]{"II ", "IS ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[0], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[9], 1), (Object[])new Object[]{"III", " S ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[0], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[6], 1), (Object[])new Object[]{" I ", " I ", " S ", Character.valueOf('I'), ItemsDBC.ItemsOW[0], Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockOW[0], 1), (Object[])new Object[]{"III", "III", "III", Character.valueOf('I'), ItemsDBC.ItemsOW[3]});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockOW[1], 1), (Object[])new Object[]{"III", "III", "III", Character.valueOf('I'), ItemsDBC.ItemsOW[1]});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockOW[2], 1), (Object[])new Object[]{"III", "III", "III", Character.valueOf('I'), ItemsDBC.ItemsOW[0]});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockOW[3], 1), (Object[])new Object[]{"III", "III", "III", Character.valueOf('I'), ItemsDBC.ItemsOW[2]});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[3], 9), (Object[])new Object[]{BlocksDBC.BlockOW[0]});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[1], 9), (Object[])new Object[]{BlocksDBC.BlockOW[1]});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[0], 9), (Object[])new Object[]{BlocksDBC.BlockOW[2]});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[2], 9), (Object[])new Object[]{BlocksDBC.BlockOW[3]});
        for (i = 0; i < 3; ++i) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[i * 4 + 13], 1), (Object[])new Object[]{"XXX", "X X", Character.valueOf('X'), ItemsDBC.ItemsOW[i]});
            GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[i * 4 + 14], 1), (Object[])new Object[]{"X X", "XXX", "XXX", Character.valueOf('X'), ItemsDBC.ItemsOW[i]});
            GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[i * 4 + 15], 1), (Object[])new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), ItemsDBC.ItemsOW[i]});
            GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemsOW[i * 4 + 16], 1), (Object[])new Object[]{"X X", "X X", Character.valueOf('X'), ItemsDBC.ItemsOW[i]});
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemWarenaiFabric, 4), (Object[])new Object[]{"WOW", "OWO", "WOW", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('O'), ItemsDBC.ItemWarenai});
        String[] row1 = new String[]{"FFF", "WWW", "FFF", "FWF", "FWF", "   ", "   ", "   "};
        String[] row2 = new String[]{"FFF", "FFF", "WFW", "FFF", "WFW", "FFF", "WFW", "WFW"};
        String[] row3 = new String[]{"W W", "W W", "W W", "W W", "W W", "W W", "W W", "   "};
        for (int i2 = 0; i2 < ItemsDBC.ItemBodysuits.length; ++i2) {
            ItemStack body = new ItemStack((Item)ItemsDBC.ItemBodysuits[i2]);
            GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{row1[i2], row2[i2], row3[i2], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemWarenaiFabric});
            for (int j = 0; j < JRMCoreH2.colNams.length; ++j) {
                body = new ItemStack((Item)ItemsDBC.ItemBodysuits[i2]);
                ((ItemBodyDBC)body.func_77973_b()).setColor(body, JRMCoreH2.cols[j]);
                ItemStack dye = new ItemStack(Items.field_151100_aR, 1, j);
                GameRegistry.addRecipe((ItemStack)((ItemBodyDBC)body.func_77973_b()).setColor(body, JRMCoreH2.cols[j]), (Object[])new Object[]{"DDD", "DXD", "DDD", Character.valueOf('X'), ItemsDBC.ItemBodysuits[i2], Character.valueOf('D'), dye});
            }
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemStrongFabric, 4), (Object[])new Object[]{"WOW", "WOW", "WOW", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('O'), ItemsDBC.ItemWarenai});
        String[] gi_row1 = new String[]{"F F", "F F", "F F"};
        String[] gi_row2 = new String[]{"FWF", "FWF", " W "};
        String[] gi_row3 = new String[]{" F ", "FFF", " F "};
        for (int i3 = 0; i3 < ItemsDBC.ItemGiBody.length; ++i3) {
            ItemStack body = new ItemStack((Item)ItemsDBC.ItemGiBody[i3]);
            GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_row1[i3], gi_row2[i3], gi_row3[i3], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
            for (int j = 0; j < JRMCoreH2.colNams.length; ++j) {
                body = new ItemStack((Item)ItemsDBC.ItemGiBody[i3]);
                ((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[j]);
                ItemStack dye = new ItemStack(Items.field_151100_aR, 1, j);
                GameRegistry.addRecipe((ItemStack)((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[j]), (Object[])new Object[]{"DDD", "DXD", "DDD", Character.valueOf('X'), ItemsDBC.ItemGiBody[i3], Character.valueOf('D'), dye});
            }
        }
        String[] gi_color_Head1 = new String[]{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Head2 = new String[]{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "WFF", "FWF"};
        String[] gi_color_Head3 = new String[]{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Chest1 = new String[]{"W F", "   ", "   ", "   ", "   ", "F F", "FFF", "  F", "   ", "   ", "   ", "  F"};
        String[] gi_color_Chest2 = new String[]{"FFF", "   ", "   ", "FFF", " F ", " W ", "FWF", "FWF", "   ", "   ", "F W", "W F"};
        String[] gi_color_Chest3 = new String[]{"FFF", "   ", "   ", " F ", " F ", "   ", "FFF", "FFF", "   ", "   ", "F  ", "   "};
        String[] gi_color_Legs1 = new String[]{"WFF", "FWF", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Legs2 = new String[]{"F F", "F F", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Legs3 = new String[]{"F F", "F F", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Boots1 = new String[]{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Boots2 = new String[]{"W F", "   ", "F F", "   ", "   ", "   ", "   ", "   "};
        String[] gi_color_Boots3 = new String[]{"F F", "   ", "F F", "   ", "   ", "   ", "   ", "   "};
        int added = 0;
        for (int i4 = 0; i4 < 12; ++i4) {
            for (int j = 0; j < 4; ++j) {
                if (!ItemsDBC.ItemsGiType[i4].contains("" + j)) continue;
                ItemStack body = j == 0 ? new ItemStack(ItemsDBC.ItemsGi0[i4]) : (j == 1 ? new ItemStack(ItemsDBC.ItemsGi1[i4]) : (j == 2 ? new ItemStack(ItemsDBC.ItemsGi2[i4]) : new ItemStack(ItemsDBC.ItemsGi3[i4])));
                if (j == 0) {
                    GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_color_Head1[i4], gi_color_Head2[i4], gi_color_Head3[i4], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
                } else if (j == 1) {
                    GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_color_Chest1[i4], gi_color_Chest2[i4], gi_color_Chest3[i4], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
                } else if (j == 2) {
                    GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_color_Legs1[i4], gi_color_Legs2[i4], gi_color_Legs3[i4], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
                } else {
                    GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_color_Boots1[i4], gi_color_Boots2[i4], gi_color_Boots3[i4], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
                }
                for (int k = 0; k < JRMCoreH2.colNams.length; ++k) {
                    body = new ItemStack(ItemsDBC.ItemGi.get(added));
                    ((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[k]);
                    ItemStack dye = new ItemStack(Items.field_151100_aR, 1, k);
                    GameRegistry.addRecipe((ItemStack)((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[k]), (Object[])new Object[]{"DDD", "DXD", "DDD", Character.valueOf('X'), ItemsDBC.ItemGi.get(added), Character.valueOf('D'), dye});
                }
                ++added;
            }
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.Coat, 1), (Object[])new Object[]{"F W", "WWW", "WWW", Character.valueOf('F'), ItemsJRMC.Custom_fabric, Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.Coat_2, 1), (Object[])new Object[]{"F W", "WWW", "WWW", Character.valueOf('F'), ItemsJRMC.Custom_fabric, Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 13)});
        added += 2;
        String[] gi_color_Chest1_1 = new String[]{"   ", "   "};
        String[] gi_color_Chest2_1 = new String[]{"FFF", "FFF"};
        String[] gi_color_Chest3_1 = new String[]{"   ", "FFF"};
        for (int i5 = 0; i5 < 2; ++i5) {
            int j = 1;
            if (!ItemsDBC.ItemsGiType2[i5].contains("" + j)) continue;
            ItemStack body = new ItemStack(ItemsDBC.ItemsGi1_2[i5]);
            GameRegistry.addRecipe((ItemStack)body, (Object[])new Object[]{gi_color_Chest1_1[i5], gi_color_Chest2_1[i5], gi_color_Chest3_1[i5], Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('F'), ItemsDBC.ItemStrongFabric});
            for (int k = 0; k < JRMCoreH2.colNams.length; ++k) {
                body = new ItemStack(ItemsDBC.ItemGi.get(added));
                ((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[k]);
                ItemStack dye = new ItemStack(Items.field_151100_aR, 1, k);
                GameRegistry.addRecipe((ItemStack)((VanityColor)body.func_77973_b()).setColor(body, JRMCoreH2.cols[k]), (Object[])new Object[]{"DDD", "DXD", "DDD", Character.valueOf('X'), ItemsDBC.ItemGi.get(added), Character.valueOf('D'), dye});
            }
            ++added;
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemDimensionSword, 1), (Object[])new Object[]{"AAA", "ABA", "AAA", Character.valueOf('A'), ItemsDBC.ItemJanembaEssence, Character.valueOf('B'), ItemsDBC.ItemSmallClub});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockKachin[0], 1), (Object[])new Object[]{"AAA", "AAA", "AAA", Character.valueOf('A'), ItemsDBC.ItemKatchin});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatchin, 9), (Object[])new Object[]{"AAA", "AAA", "AAA", Character.valueOf('A'), BlocksDBC.BlockKachin[0]});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemsDBC.ItemKatchin, 9), (Object[])new Object[]{new ItemStack(BlocksDBC.BlockKachin[0], 1, WILDCARD)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockKachin[1], 4), (Object[])new Object[]{"AA", "AA", Character.valueOf('A'), BlocksDBC.BlockKachin[0]});
        GameRegistry.addRecipe((ItemStack)new ItemStack(BlocksDBC.BlockKachin[2], 4), (Object[])new Object[]{"AA", "AA", Character.valueOf('A'), BlocksDBC.BlockKachin[1]});
        GameRegistry.addSmelting((Block)BlocksDBC.BlockDragonBallStone, (ItemStack)new ItemStack(BlocksDBC.BlockDragonBall, 1), (float)0.1f);
        GameRegistry.addSmelting((Block)BlocksDBC.BlockOreWrenai, (ItemStack)new ItemStack(ItemsDBC.ItemWarenai, 1), (float)0.7f);
        GameRegistry.addSmelting((Block)BlocksDBC.BlockAlienCobbleStone, (ItemStack)new ItemStack(BlocksDBC.BlockAlienStone, 1), (float)0.1f);
        GameRegistry.addSmelting((Item)Items.field_151102_aT, (ItemStack)new ItemStack(Items.field_151100_aR, 1, 3), (float)0.2f);
        GameRegistry.addSmelting((Item)ItemsDBC.ItemDinoMeat, (ItemStack)new ItemStack(ItemsDBC.ItemDinoMeatCooked), (float)0.5f);
        GameRegistry.addSmelting((Item)ItemsDBC.ItemDinoMeatBig, (ItemStack)new ItemStack(ItemsDBC.ItemDinoMeatCookedBig), (float)0.5f);
        GameRegistry.addSmelting((Block)BlocksDBC.BlockOreLehnori, (ItemStack)new ItemStack(ItemsDBC.ItemsOW[0]), (float)0.7f);
        GameRegistry.addSmelting((Block)BlocksDBC.BlockOreDlog, (ItemStack)new ItemStack(ItemsDBC.ItemsOW[1]), (float)1.0f);
        GameRegistry.addSmelting((Block)BlocksDBC.BlockOreDnomaid, (ItemStack)new ItemStack(ItemsDBC.ItemsOW[2]), (float)1.0f);
        MyRecipes.init();
    }
}

