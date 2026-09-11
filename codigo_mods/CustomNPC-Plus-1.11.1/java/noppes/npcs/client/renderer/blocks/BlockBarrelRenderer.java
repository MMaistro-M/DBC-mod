/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockBarrel;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.barrel.ModelBarrel;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyBarrel;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyBarrelLid;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockBarrelRenderer
extends BlockRendererInterface {
    private final ModelLegacyBarrel legacyModel = new ModelLegacyBarrel();
    private final ModelLegacyBarrelLid legacyLid = new ModelLegacyBarrelLid();
    private static final ResourceLocation legacy_texture = new ResourceLocation("customnpcs", "textures/models/Barrel.png");
    private final ModelBarrel barrel = new ModelBarrel();
    private static final ResourceLocation trimTexture = new ResourceLocation("customnpcs", "textures/models/barrel/trim.png");
    private static final ResourceLocation oak_lid = new ResourceLocation("customnpcs", "textures/models/barrel/oak_lid.png");
    private static final ResourceLocation spruce_lid = new ResourceLocation("customnpcs", "textures/models/barrel/spruce_lid.png");
    private static final ResourceLocation birch_lid = new ResourceLocation("customnpcs", "textures/models/barrel/birch_lid.png");
    private static final ResourceLocation jungle_lid = new ResourceLocation("customnpcs", "textures/models/barrel/jungle_lid.png");
    private static final ResourceLocation acacia_lid = new ResourceLocation("customnpcs", "textures/models/barrel/acacia_lid.png");
    private static final ResourceLocation dark_oak_lid = new ResourceLocation("customnpcs", "textures/models/barrel/dark_oak_lid.png");

    public BlockBarrelRenderer() {
        ((BlockBarrel)CustomItems.barrel).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        if (ConfigClient.LegacyBarrel) {
            GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.42f), (float)((float)var6 + 0.5f));
            GL11.glScalef((float)1.2f, (float)0.94f, (float)1.2f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2884);
            BlockBarrelRenderer.setWoodTexture(var1.func_145832_p());
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(legacy_texture);
            this.legacyLid.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2884);
            BlockBarrelRenderer.setWoodTexture(var1.func_145832_p());
            this.barrel.renderWall(0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(trimTexture);
            this.barrel.renderTrim(0.0625f);
            this.setLidTexture(var1.func_145832_p());
            this.barrel.renderBase(0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        if (ConfigClient.LegacyBarrel) {
            GL11.glTranslatef((float)0.0f, (float)0.75f, (float)0.0f);
            GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            BlockBarrelRenderer.setWoodTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(legacy_texture);
            this.legacyLid.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)0.9f, (float)0.0f);
            GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            BlockBarrelRenderer.setWoodTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.barrel.renderWall(0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(trimTexture);
            this.barrel.renderTrim(0.0625f);
            this.setLidTexture(metadata);
            this.barrel.renderBase(0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void setLidTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(spruce_lid);
        } else if (meta == 2) {
            manager.func_110577_a(birch_lid);
        } else if (meta == 3) {
            manager.func_110577_a(jungle_lid);
        } else if (meta == 4) {
            manager.func_110577_a(acacia_lid);
        } else if (meta == 5) {
            manager.func_110577_a(dark_oak_lid);
        } else {
            manager.func_110577_a(oak_lid);
        }
    }

    public int getRenderId() {
        return CustomItems.barrel.func_149645_b();
    }
}

