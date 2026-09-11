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
package JinRyuu.DragonBC.common.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import JinRyuu.DragonBC.common.Items.m.ModdedItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MyRecipes {
    public static void init() {
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedItems.SakuraStick, 4), (Object[])new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedItems.MahagonyStick, 4), (Object[])new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedItems.MapleStick, 4), (Object[])new Object[]{"&", "&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedItems.SakuraBranch, 4), (Object[])new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraLogs, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedItems.MahagonyBranch, 4), (Object[])new Object[]{"#", "#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraLogs, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakuraPlank, 4, 0), (Object[])new Object[]{"#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraLogs, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakuraPlank, 4, 1), (Object[])new Object[]{"#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraLogs, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakuraPlank, 4, 2), (Object[])new Object[]{"#", Character.valueOf('#'), new ItemStack(ModdedBlocks.SakuraLogs, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MahagonyFence, 2), (Object[])new Object[]{"&&&", "&&&", Character.valueOf('&'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakuraFence, 2), (Object[])new Object[]{"&&&", "&&&", Character.valueOf('&'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MapleFence, 2), (Object[])new Object[]{"&&&", "&&&", Character.valueOf('&'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)ModdedBlocks.SakuraSlabsSingle, 6, 0), (Object[])new Object[]{"&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)ModdedBlocks.SakuraSlabsSingle, 6, 1), (Object[])new Object[]{"&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)ModdedBlocks.SakuraSlabsSingle, 6, 2), (Object[])new Object[]{"&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 3, 0), (Object[])new Object[]{"&I&", "IOI", "&I&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), Items.field_151121_aF, Character.valueOf('O'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 0), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 15)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 1), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 14)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 2), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 13)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 3), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 12)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 4), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 5), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 10)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 6), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 9)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 7), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 8)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 8), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 7)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 9), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 6)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 10), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 5)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 11), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 4)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 12), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 13), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 14), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallSakura, 1, 15), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallSakura, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 3, 0), (Object[])new Object[]{"&I&", "IOI", "&I&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), Items.field_151121_aF, Character.valueOf('O'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 0), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 15)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 1), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 14)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 2), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 13)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 3), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 12)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 4), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 11)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 5), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 10)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 6), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 9)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 7), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 8)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 8), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 7)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 9), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 6)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 10), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 5)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 11), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 4)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 12), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 3)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 13), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 14), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.PaperWallMahagony, 1, 15), (Object[])new Object[]{" I ", "I&I", " I ", Character.valueOf('&'), ModdedBlocks.PaperWallMahagony, Character.valueOf('I'), new ItemStack(Items.field_151100_aR, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakFenc, 2), (Object[])new Object[]{"I&I", "I&I", Character.valueOf('I'), ModdedItems.SakuraStick, Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MahFenc, 2), (Object[])new Object[]{"I&I", "I&I", Character.valueOf('I'), ModdedItems.MahagonyStick, Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MapFenc, 2), (Object[])new Object[]{"I&I", "I&I", Character.valueOf('I'), ModdedItems.MapleStick, Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.SakuraStairs, 4), (Object[])new Object[]{"&  ", "&& ", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MahagonyStairs, 4), (Object[])new Object[]{"&  ", "&& ", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModdedBlocks.MapleStairs, 4), (Object[])new Object[]{"&  ", "&& ", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150468_ap, 3), (Object[])new Object[]{"& &", "&&&", "& &", Character.valueOf('&'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150468_ap, 3), (Object[])new Object[]{"& &", "&&&", "& &", Character.valueOf('&'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150468_ap, 3), (Object[])new Object[]{"& &", "&&&", "& &", Character.valueOf('&'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), ModdedItems.SakuraStick, Character.valueOf('I'), Items.field_151044_h});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), ModdedItems.MahagonyStick, Character.valueOf('I'), Items.field_151044_h});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), ModdedItems.MapleStick, Character.valueOf('I'), Items.field_151044_h});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), new ItemStack(ModdedItems.MahagonyStick), Character.valueOf('I'), new ItemStack(Items.field_151044_h, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), new ItemStack(ModdedItems.SakuraStick), Character.valueOf('I'), new ItemStack(Items.field_151044_h, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150478_aa, 4), (Object[])new Object[]{"I", "&", Character.valueOf('&'), new ItemStack(ModdedItems.MapleStick), Character.valueOf('I'), new ItemStack(Items.field_151044_h, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150462_ai), (Object[])new Object[]{"&&", "&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)Blocks.field_150486_ae), (Object[])new Object[]{"&&&", "& &", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150462_ai), (Object[])new Object[]{"&&", "&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)Blocks.field_150486_ae), (Object[])new Object[]{"&&&", "& &", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(Blocks.field_150462_ai), (Object[])new Object[]{"&&", "&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Block)Blocks.field_150486_ae), (Object[])new Object[]{"&&&", "& &", "&&&", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2)});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraSword), (Object[])new Object[]{" & ", " & ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraAxe), (Object[])new Object[]{" &&", " I&", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraPickaxe), (Object[])new Object[]{"&&&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraShovel), (Object[])new Object[]{" & ", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraHoe), (Object[])new Object[]{" &&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.SakuraHammer), (Object[])new Object[]{"&I&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 0), Character.valueOf('I'), ModdedItems.SakuraBranch});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MahagonySword), (Object[])new Object[]{" & ", " & ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MahagonyAxe), (Object[])new Object[]{" &&", " I&", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MahagonyPickaxe), (Object[])new Object[]{"&&&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MahagonyShovel), (Object[])new Object[]{" & ", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MahagonyHoe), (Object[])new Object[]{" &&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 1), Character.valueOf('I'), ModdedItems.MahagonyStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MapleSword), (Object[])new Object[]{" & ", " & ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2), Character.valueOf('I'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MapleAxe), (Object[])new Object[]{" &&", " I&", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2), Character.valueOf('I'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MaplePickaxe), (Object[])new Object[]{"&&&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2), Character.valueOf('I'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MapleShovel), (Object[])new Object[]{" & ", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2), Character.valueOf('I'), ModdedItems.MapleStick});
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)ModdedItems.MapleHoe), (Object[])new Object[]{" &&", " I ", " I ", Character.valueOf('&'), new ItemStack(ModdedBlocks.SakuraPlank, 1, 2), Character.valueOf('I'), ModdedItems.MapleStick});
        GameRegistry.addSmelting((Block)ModdedBlocks.SakuraLogs, (ItemStack)new ItemStack(Items.field_151044_h, 1, 1), (float)0.15f);
    }
}

