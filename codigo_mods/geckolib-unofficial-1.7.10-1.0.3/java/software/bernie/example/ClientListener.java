/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package software.bernie.example;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.client.renderer.armor.PotatoArmorRenderer;
import software.bernie.example.client.renderer.entity.BikeGeoRenderer;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.client.renderer.entity.GeoNpcRenderer;
import software.bernie.example.client.renderer.entity.LERenderer;
import software.bernie.example.client.renderer.entity.ReplacedCreeperRenderer;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.example.client.renderer.tile.BotariumTileRenderer;
import software.bernie.example.client.renderer.tile.FertilizerTileRenderer;
import software.bernie.example.entity.BikeEntity;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.example.entity.GeoExampleEntityLayer;
import software.bernie.example.entity.GeoNpcEntity;
import software.bernie.example.entity.ReplacedCreeperEntity;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;
import software.bernie.geckolib3.renderers.geo.RenderBlockItem;

public class ClientListener {
    @SideOnly(value=Side.CLIENT)
    public static void registerRenderers(FMLInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(GeoExampleEntityLayer.class, (Render)new LERenderer());
        RenderingRegistry.registerEntityRenderingHandler(GeoExampleEntity.class, (Render)new ExampleGeoRenderer());
        RenderingRegistry.registerEntityRenderingHandler(BikeEntity.class, (Render)new BikeGeoRenderer());
        RenderingRegistry.registerEntityRenderingHandler(GeoNpcEntity.class, (Render)new GeoNpcRenderer());
        GeoArmorRenderer.registerArmorRenderer(PotatoArmorItem.class, new PotatoArmorRenderer());
        ClientListener.bindRender((Block)BlockRegistry.BOTARIUM_BLOCK, new BotariumTileEntity(), new BotariumTileRenderer());
        ClientListener.bindRender((Block)BlockRegistry.FERTILIZER_BLOCK, new FertilizerTileEntity(), new FertilizerTileRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ItemRegistry.JACK_IN_THE_BOX, (IItemRenderer)new JackInTheBoxRenderer());
    }

    public static void bindRender(Block block, TileEntity tile, TileEntitySpecialRenderer tesr) {
        ClientRegistry.bindTileEntitySpecialRenderer(tile.getClass(), (TileEntitySpecialRenderer)tesr);
        Item blockItem = ItemBlock.func_150898_a((Block)block);
        MinecraftForgeClient.registerItemRenderer((Item)blockItem, (IItemRenderer)new RenderBlockItem(tesr, tile));
    }

    @SideOnly(value=Side.CLIENT)
    public static void registerReplacedRenderers(FMLInitializationEvent event) {
        ReplacedCreeperRenderer creeperRenderer = new ReplacedCreeperRenderer();
        RenderManager.field_78727_a.field_78729_o.put(EntityCreeper.class, creeperRenderer);
        GeoReplacedEntityRenderer.registerReplacedEntity(ReplacedCreeperEntity.class, creeperRenderer);
    }
}

