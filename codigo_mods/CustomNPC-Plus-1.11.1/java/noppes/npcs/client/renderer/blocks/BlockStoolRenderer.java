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
import noppes.npcs.blocks.BlockStool;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.ModelStool;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyStool;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockStoolRenderer
extends BlockRendererInterface {
    private final ModelLegacyStool legacyStool = new ModelLegacyStool();
    private final ModelStool stool = new ModelStool();
    private static final ResourceLocation oak = new ResourceLocation("customnpcs", "textures/models/stool/oak.png");
    private static final ResourceLocation spruce = new ResourceLocation("customnpcs", "textures/models/stool/spruce.png");
    private static final ResourceLocation birch = new ResourceLocation("customnpcs", "textures/models/stool/birch.png");
    private static final ResourceLocation jungle = new ResourceLocation("customnpcs", "textures/models/stool/jungle.png");
    private static final ResourceLocation dark_oak = new ResourceLocation("customnpcs", "textures/models/stool/dark_oak.png");
    private static final ResourceLocation acacia = new ResourceLocation("customnpcs", "textures/models/stool/acacia.png");

    public BlockStoolRenderer() {
        ((BlockStool)CustomItems.stool).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.65f), (float)((float)var6 + 0.5f));
        GL11.glScalef((float)1.2f, (float)1.1f, (float)1.2f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        if (ConfigClient.LegacyStool) {
            BlockStoolRenderer.setWoodTexture(var1.func_145832_p());
            this.legacyStool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            if (ConfigClient.WoodTextures) {
                BlockStoolRenderer.setWoodTexture(var1.func_145832_p());
            } else {
                this.setStoolTexture(var1.func_145832_p());
            }
            this.stool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)1.2f, (float)1.1f, (float)1.2f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyStool) {
            BlockStoolRenderer.setWoodTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.legacyStool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.setStoolTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.stool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void setStoolTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(spruce);
        } else if (meta == 2) {
            manager.func_110577_a(birch);
        } else if (meta == 3) {
            manager.func_110577_a(jungle);
        } else if (meta == 4) {
            manager.func_110577_a(acacia);
        } else if (meta == 5) {
            manager.func_110577_a(dark_oak);
        } else {
            manager.func_110577_a(oak);
        }
    }

    public int getRenderId() {
        return CustomItems.stool.func_149645_b();
    }
}

