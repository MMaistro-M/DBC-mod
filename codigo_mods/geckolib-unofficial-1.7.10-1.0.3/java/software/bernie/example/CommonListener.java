/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package software.bernie.example;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.block.BotariumBlock;
import software.bernie.example.block.DiagonalBlock;
import software.bernie.example.block.FertilizerBlock;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.entity.BikeEntity;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.example.entity.GeoExampleEntityLayer;
import software.bernie.example.entity.GeoNpcEntity;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.example.registry.ItemRegistry;

public class CommonListener {
    public static void onRegisterBlocks() {
        BlockRegistry.BOTARIUM_BLOCK = new BotariumBlock();
        BlockRegistry.FERTILIZER_BLOCK = new FertilizerBlock();
        BlockRegistry.DIAGONAL_BLOCK = new DiagonalBlock();
        BlockRegistry.BOTARIUM_BLOCK.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
        BlockRegistry.FERTILIZER_BLOCK.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
        BlockRegistry.DIAGONAL_BLOCK.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
        CommonListener.registerBlock((Block)BlockRegistry.BOTARIUM_BLOCK, "botariumblock");
        CommonListener.registerBlock((Block)BlockRegistry.FERTILIZER_BLOCK, "fertilizerblock");
    }

    public static void onRegisterEntities() {
        int id = 0;
        EntityRegistry.registerModEntity(BikeEntity.class, (String)"bike", (int)id++, (Object)GeckoLibMod.instance, (int)160, (int)2, (boolean)false);
        EntityRegistry.registerModEntity(GeoExampleEntity.class, (String)"example", (int)id++, (Object)GeckoLibMod.instance, (int)160, (int)2, (boolean)false);
        EntityRegistry.registerModEntity(GeoExampleEntityLayer.class, (String)"examplelayer", (int)id++, (Object)GeckoLibMod.instance, (int)160, (int)2, (boolean)false);
        EntityRegistry.registerModEntity(GeoNpcEntity.class, (String)"geonpc", (int)id++, (Object)GeckoLibMod.instance, (int)160, (int)2, (boolean)false);
        GameRegistry.registerTileEntity(BotariumTileEntity.class, (String)"botariumtile");
        GameRegistry.registerTileEntity(FertilizerTileEntity.class, (String)"fertilizertile");
    }

    public static void onRegisterItems() {
        ItemRegistry.JACK_IN_THE_BOX = new JackInTheBoxItem();
        ItemRegistry.JACK_IN_THE_BOX.func_77655_b("jackintheboxitem");
        GameRegistry.registerItem((Item)ItemRegistry.JACK_IN_THE_BOX, (String)"jackintheboxitem");
        ItemRegistry.POTATO_HEAD = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, 0), "potato_head");
        ItemRegistry.POTATO_CHEST = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, 1), "potato_chest");
        ItemRegistry.POTATO_LEGGINGS = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, 2), "potato_leggings");
        ItemRegistry.POTATO_BOOTS = CommonListener.registerItem(new PotatoArmorItem(ItemArmor.ArmorMaterial.DIAMOND, 0, 3), "potato_boots");
    }

    private static <T extends Item> T registerItem(T item, String name) {
        GameRegistry.registerItem((Item)item.func_77655_b(name).func_111206_d("geckolib3:" + name), (String)name);
        return item;
    }

    private static void registerBlock(Block block, String name) {
        GameRegistry.registerBlock((Block)block.func_149663_c(name), (String)name);
    }
}

