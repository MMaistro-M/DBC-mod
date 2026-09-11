/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.controllers.data;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.RecipeCarpentry;

public class RecipesDefault {
    public static void addRecipe(String name, Object ob, boolean isGlobal, Object ... recipe) {
        ItemStack item = ob instanceof Item ? new ItemStack((Item)ob) : (ob instanceof Block ? new ItemStack((Block)ob) : (ItemStack)ob);
        RecipeCarpentry recipeCarpentry = new RecipeCarpentry(name);
        recipeCarpentry.isGlobal = isGlobal;
        recipeCarpentry = RecipeCarpentry.saveRecipe(recipeCarpentry, item, recipe);
        RecipeController.Instance.addRecipe(recipeCarpentry);
    }

    public static void loadDefaultRecipes(int i) {
        if (i < 0) {
            RecipesDefault.addRecipe("Npc Wand", CustomItems.wand, true, "XX", " Y", " Y", Character.valueOf('X'), Items.field_151025_P, Character.valueOf('Y'), Items.field_151055_y);
            RecipesDefault.addRecipe("Mob Cloner", CustomItems.cloner, true, "XX", "XY", " Y", Character.valueOf('X'), Items.field_151025_P, Character.valueOf('Y'), Items.field_151055_y);
            RecipesDefault.addRecipe("Carpentry Bench", CustomItems.carpentyBench, true, "XYX", "Z Z", "Z Z", Character.valueOf('X'), Blocks.field_150344_f, Character.valueOf('Z'), Items.field_151055_y, Character.valueOf('Y'), Blocks.field_150462_ai);
            ItemStack anvil = new ItemStack(CustomItems.carpentyBench);
            anvil.func_77964_b(1);
            RecipesDefault.addRecipe("Anvil", anvil, true, "XXX", "Z Z", "Z Z", Character.valueOf('X'), Blocks.field_150339_S, Character.valueOf('Z'), Items.field_151042_j);
            if (!ConfigItem.DisableExtraItems) {
                RecipesDefault.addRecipe("Mana", CustomItems.mana, true, "XY", Character.valueOf('X'), Items.field_151137_ax, Character.valueOf('Y'), Items.field_151114_aO);
                RecipesDefault.addRecipe("Gun Wooden", CustomItems.gunWood, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Gun Stone", CustomItems.gunStone, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Gun Iron", CustomItems.gunIron, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Gun Gold", CustomItems.gunGold, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Gun Diamond", CustomItems.gunDiamond, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Gun Emerald", CustomItems.gunEmerald, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), Items.field_151166_bC);
                RecipesDefault.addRecipe("Gun Bronze", CustomItems.gunBronze, false, "XXXY", " ZM ", "  M ", Character.valueOf('Y'), Blocks.field_150442_at, Character.valueOf('M'), Items.field_151055_y, Character.valueOf('Z'), Blocks.field_150430_aB, Character.valueOf('X'), CustomItems.bronze_ingot);
                RecipesDefault.addRecipe("Bullet Wooden", CustomItems.bulletWood, true, "X", Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Bullet Stone", CustomItems.bulletStone, true, "X", Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Bullet Iron", CustomItems.bulletIron, true, "X", Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Bullet Gold", CustomItems.bulletGold, true, "X", Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Bullet Diamond", CustomItems.bulletDiamond, true, "X", Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Bullet Emerald", CustomItems.bulletEmerald, true, "X", Character.valueOf('X'), Items.field_151166_bC);
                RecipesDefault.addRecipe("Bullet Bronze", CustomItems.bulletBronze, true, "X", Character.valueOf('X'), CustomItems.bronze_ingot);
            }
        }
        if (i < 1) {
            if (!ConfigItem.DisableExtraBlock) {
                RecipesDefault.addRecipe("WallBanner Wooden", new ItemStack(CustomItems.wallBanner, 1, 0), false, "XXX", "ZZZ", "ZZZ", "Z Z", Character.valueOf('Z'), Blocks.field_150325_L, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("WallBanner Stone", new ItemStack(CustomItems.wallBanner, 1, 1), false, "XXX", "ZZZ", "ZZZ", "Z Z", Character.valueOf('Z'), Blocks.field_150325_L, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("WallBanner Iron", new ItemStack(CustomItems.wallBanner, 1, 2), false, "XXX", "ZZZ", "ZZZ", "Z Z", Character.valueOf('Z'), Blocks.field_150325_L, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("WallBanner Gold", new ItemStack(CustomItems.wallBanner, 1, 3), false, "XXX", "ZZZ", "ZZZ", "Z Z", Character.valueOf('Z'), Blocks.field_150325_L, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("WallBanner Diamond", new ItemStack(CustomItems.wallBanner, 1, 4), false, "XXX", "ZZZ", "ZZZ", "Z Z", Character.valueOf('Z'), Blocks.field_150325_L, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Banner Wooden", new ItemStack(CustomItems.banner, 1, 0), false, " X ", " Z ", " Z ", "ZZZ", Character.valueOf('X'), new ItemStack(CustomItems.wallBanner, 1, 0), Character.valueOf('Z'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Banner Stone", new ItemStack(CustomItems.banner, 1, 1), false, " X ", " Z ", " Z ", "ZZZ", Character.valueOf('X'), new ItemStack(CustomItems.wallBanner, 1, 1), Character.valueOf('Z'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Banner Iron", new ItemStack(CustomItems.banner, 1, 2), false, " X ", " Z ", " Z ", "ZZZ", Character.valueOf('X'), new ItemStack(CustomItems.wallBanner, 1, 2), Character.valueOf('Z'), Items.field_151042_j);
                RecipesDefault.addRecipe("Banner Gold", new ItemStack(CustomItems.banner, 1, 3), false, " X ", " Z ", " Z ", "ZZZ", Character.valueOf('X'), new ItemStack(CustomItems.wallBanner, 1, 3), Character.valueOf('Z'), Items.field_151043_k);
                RecipesDefault.addRecipe("Banner Diamond", new ItemStack(CustomItems.banner, 1, 4), false, " X ", " Z ", " Z ", "ZZZ", Character.valueOf('X'), new ItemStack(CustomItems.wallBanner, 1, 4), Character.valueOf('Z'), Items.field_151045_i);
                RecipesDefault.addRecipe("Lamp Wooden", new ItemStack(CustomItems.tallLamp, 1, 0), false, "YXY", " Z ", " Z ", "ZZZ", Character.valueOf('X'), Blocks.field_150478_aa, Character.valueOf('Y'), Blocks.field_150325_L, Character.valueOf('Z'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Lamp Stone", new ItemStack(CustomItems.tallLamp, 1, 1), false, "YXY", " Z ", " Z ", "ZZZ", Character.valueOf('X'), Blocks.field_150478_aa, Character.valueOf('Y'), Blocks.field_150325_L, Character.valueOf('Z'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Lamp Iron", new ItemStack(CustomItems.tallLamp, 1, 2), false, "YXY", " Z ", " Z ", "ZZZ", Character.valueOf('X'), Blocks.field_150478_aa, Character.valueOf('Y'), Blocks.field_150325_L, Character.valueOf('Z'), Items.field_151042_j);
                RecipesDefault.addRecipe("Lamp Gold", new ItemStack(CustomItems.tallLamp, 1, 3), false, "YXY", " Z ", " Z ", "ZZZ", Character.valueOf('X'), Blocks.field_150478_aa, Character.valueOf('Y'), Blocks.field_150325_L, Character.valueOf('Z'), Items.field_151043_k);
                RecipesDefault.addRecipe("Lamp Diamond", new ItemStack(CustomItems.tallLamp, 1, 4), false, "YXY", " Z ", " Z ", "ZZZ", Character.valueOf('X'), Blocks.field_150478_aa, Character.valueOf('Y'), Blocks.field_150325_L, Character.valueOf('Z'), Items.field_151045_i);
                RecipesDefault.addRecipe("Chair Wooden1", new ItemStack(CustomItems.chair, 1, 0), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Chair Wooden2", new ItemStack(CustomItems.chair, 1, 1), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Chair Wooden3", new ItemStack(CustomItems.chair, 1, 2), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Chair Wooden4", new ItemStack(CustomItems.chair, 1, 3), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Chair Wooden5", new ItemStack(CustomItems.chair, 1, 4), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Chair Wooden6", new ItemStack(CustomItems.chair, 1, 5), false, "  X", "  X", "XXX", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Crate Wooden1", new ItemStack(CustomItems.crate, 1, 0), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Crate Wooden2", new ItemStack(CustomItems.crate, 1, 1), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Crate Wooden3", new ItemStack(CustomItems.crate, 1, 2), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Crate Wooden4", new ItemStack(CustomItems.crate, 1, 3), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Crate Wooden5", new ItemStack(CustomItems.crate, 1, 4), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Crate Wooden6", new ItemStack(CustomItems.crate, 1, 5), false, "XXXX", "X  X", "X  X", "XXXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("WeaponRack Wooden1", new ItemStack(CustomItems.weaponsRack, 1, 0), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("WeaponRack Wooden2", new ItemStack(CustomItems.weaponsRack, 1, 1), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("WeaponRack Wooden3", new ItemStack(CustomItems.weaponsRack, 1, 2), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("WeaponRack Wooden4", new ItemStack(CustomItems.weaponsRack, 1, 3), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("WeaponRack Wooden5", new ItemStack(CustomItems.weaponsRack, 1, 4), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("WeaponRack Wooden6", new ItemStack(CustomItems.weaponsRack, 1, 5), false, "XXX", "XYX", "XYX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5), Character.valueOf('Y'), Items.field_151055_y);
                RecipesDefault.addRecipe("Couch Wooden1", new ItemStack(CustomItems.couchWood, 1, 0), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Couch Wooden2", new ItemStack(CustomItems.couchWood, 1, 1), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Couch Wooden3", new ItemStack(CustomItems.couchWood, 1, 2), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Couch Wooden4", new ItemStack(CustomItems.couchWood, 1, 3), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Couch Wooden5", new ItemStack(CustomItems.couchWood, 1, 4), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Couch Wooden6", new ItemStack(CustomItems.couchWood, 1, 5), false, "   X", "   X", "XXXX", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Couch Wool1", new ItemStack(CustomItems.couchWool, 1, 0), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Couch Wool2", new ItemStack(CustomItems.couchWool, 1, 1), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Couch Wool3", new ItemStack(CustomItems.couchWool, 1, 2), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Couch Wool4", new ItemStack(CustomItems.couchWool, 1, 3), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Couch Wool5", new ItemStack(CustomItems.couchWool, 1, 4), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Couch Wool6", new ItemStack(CustomItems.couchWool, 1, 5), false, "   Z", "   Z", "ZZZZ", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood1", new ItemStack(CustomItems.table, 1, 0), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood2", new ItemStack(CustomItems.table, 1, 1), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood3", new ItemStack(CustomItems.table, 1, 2), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood4", new ItemStack(CustomItems.table, 1, 3), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood5", new ItemStack(CustomItems.table, 1, 4), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("Table Wood6", new ItemStack(CustomItems.table, 1, 5), false, "XXXX", "X  X", "X  X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5), Character.valueOf('Z'), Blocks.field_150325_L);
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood1", new ItemStack(CustomItems.stool, 1, 0), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood2", new ItemStack(CustomItems.stool, 1, 1), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood3", new ItemStack(CustomItems.stool, 1, 2), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood4", new ItemStack(CustomItems.stool, 1, 3), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood5", new ItemStack(CustomItems.stool, 1, 4), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("noppes.npcs.client.model.blocks.Stool Wood6", new ItemStack(CustomItems.stool, 1, 5), false, "XXX", " X ", "X X", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Barrel Wood1", new ItemStack(CustomItems.barrel, 1, 0), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Barrel Wood2", new ItemStack(CustomItems.barrel, 1, 1), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Barrel Wood3", new ItemStack(CustomItems.barrel, 1, 2), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Barrel Wood4", new ItemStack(CustomItems.barrel, 1, 3), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Barrel Wood5", new ItemStack(CustomItems.barrel, 1, 4), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Barrel Wood6", new ItemStack(CustomItems.barrel, 1, 5), false, "XXX", "X X", "X X", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Shelf Wood1", new ItemStack(CustomItems.shelf, 2, 0), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Shelf Wood2", new ItemStack(CustomItems.shelf, 2, 1), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Shelf Wood3", new ItemStack(CustomItems.shelf, 2, 2), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Shelf Wood4", new ItemStack(CustomItems.shelf, 2, 3), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Shelf Wood5", new ItemStack(CustomItems.shelf, 2, 4), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Shelf Wood6", new ItemStack(CustomItems.shelf, 2, 5), false, "XXX", "XY ", "X  ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Beam Wood1", new ItemStack(CustomItems.beam, 2, 0), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0));
                RecipesDefault.addRecipe("Beam Wood2", new ItemStack(CustomItems.beam, 2, 1), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1));
                RecipesDefault.addRecipe("Beam Wood3", new ItemStack(CustomItems.beam, 2, 2), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2));
                RecipesDefault.addRecipe("Beam Wood4", new ItemStack(CustomItems.beam, 2, 3), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3));
                RecipesDefault.addRecipe("Beam Wood5", new ItemStack(CustomItems.beam, 2, 4), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4));
                RecipesDefault.addRecipe("Beam Wood6", new ItemStack(CustomItems.beam, 2, 5), false, "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5));
                RecipesDefault.addRecipe("Sign Wood1", new ItemStack(CustomItems.sign, 1, 0), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 0), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Sign Wood2", new ItemStack(CustomItems.sign, 1, 1), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 1), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Sign Wood3", new ItemStack(CustomItems.sign, 1, 2), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 2), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Sign Wood4", new ItemStack(CustomItems.sign, 1, 3), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 3), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Sign Wood5", new ItemStack(CustomItems.sign, 1, 4), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 4), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Sign Wood6", new ItemStack(CustomItems.sign, 1, 5), false, "YYY", "XXX", "XXX", Character.valueOf('X'), new ItemStack(Blocks.field_150344_f, 1, 5), Character.valueOf('Y'), Items.field_151042_j);
                RecipesDefault.addRecipe("Lamp", new ItemStack(CustomItems.lantern), false, "XXX", "YZY", "XXX", Character.valueOf('X'), Items.field_151042_j, Character.valueOf('Y'), Blocks.field_150359_w, Character.valueOf('Z'), Blocks.field_150478_aa);
                RecipesDefault.addRecipe("Candle", new ItemStack(CustomItems.candle), false, "XZX", " X ", Character.valueOf('X'), Items.field_151042_j, Character.valueOf('Z'), Blocks.field_150478_aa);
                RecipesDefault.addRecipe("BigSign", new ItemStack(CustomItems.bigsign, 2), false, "XXX", "XXX", "XXX", Character.valueOf('X'), Blocks.field_150344_f);
            }
            if (!ConfigItem.DisableExtraItems) {
                RecipesDefault.addRecipe("Battle Axe1", CustomItems.battleAxeWood, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Battle Axe2", CustomItems.battleAxeStone, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Battle Axe3", CustomItems.battleAxeIron, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Battle Axe4", CustomItems.battleAxeGold, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Battle Axe5", CustomItems.battleAxeDiamond, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Battle Axe6", CustomItems.battleAxeBronze, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), CustomItems.bronze_ingot);
                RecipesDefault.addRecipe("Battle Axe7", CustomItems.battleAxeEmerald, false, "XX", "XY", " Y", " Y", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151166_bC);
                RecipesDefault.addRecipe("Halberd1", CustomItems.halberdWood, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Halberd2", CustomItems.halberdStone, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Halberd3", CustomItems.halberdIron, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Halberd4", CustomItems.halberdGold, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Halberd5", CustomItems.halberdDiamond, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Halberd6", CustomItems.halberdBronze, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), CustomItems.bronze_ingot);
                RecipesDefault.addRecipe("Halberd7", CustomItems.halberdEmerald, false, " X ", "XYX", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151166_bC);
                RecipesDefault.addRecipe("Glaive1", CustomItems.glaiveWood, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Glaive2", CustomItems.glaiveStone, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Glaive3", CustomItems.glaiveIron, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Glaive4", CustomItems.glaiveGold, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Glaive5", CustomItems.glaiveDiamond, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Glaive6", CustomItems.glaiveBronze, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), CustomItems.bronze_ingot);
                RecipesDefault.addRecipe("Glaive7", CustomItems.glaiveEmerald, false, "X   ", " Y  ", "  Y ", "   X", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151166_bC);
                RecipesDefault.addRecipe("Trident1", CustomItems.tridentWood, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150344_f);
                RecipesDefault.addRecipe("Trident2", CustomItems.tridentStone, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Blocks.field_150347_e);
                RecipesDefault.addRecipe("Trident3", CustomItems.tridentIron, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151042_j);
                RecipesDefault.addRecipe("Trident4", CustomItems.tridentGold, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151043_k);
                RecipesDefault.addRecipe("Trident5", CustomItems.tridentDiamond, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151045_i);
                RecipesDefault.addRecipe("Trident6", CustomItems.tridentBronze, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), CustomItems.bronze_ingot);
                RecipesDefault.addRecipe("Trident7", CustomItems.tridentEmerald, false, "X X", " X ", " Y ", " Y ", Character.valueOf('Y'), Items.field_151055_y, Character.valueOf('X'), Items.field_151166_bC);
            }
        }
    }
}

