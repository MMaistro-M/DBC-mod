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
import noppes.npcs.blocks.BlockChair;
import noppes.npcs.blocks.tiles.TileChair;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.chair.ModelChair;
import noppes.npcs.client.model.blocks.chair.ModelChairSpoof;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyChair;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockChairRenderer
extends BlockRendererInterface {
    private final ModelLegacyChair legacyModel = new ModelLegacyChair();
    private final ModelChairSpoof chairSpoof = new ModelChairSpoof();
    private final ModelChair chair = new ModelChair();
    private static final ResourceLocation oak = new ResourceLocation("customnpcs", "textures/models/chair/oak.png");
    private static final ResourceLocation spruce = new ResourceLocation("customnpcs", "textures/models/chair/spruce.png");
    private static final ResourceLocation birch = new ResourceLocation("customnpcs", "textures/models/chair/birch.png");
    private static final ResourceLocation jungle = new ResourceLocation("customnpcs", "textures/models/chair/jungle.png");
    private static final ResourceLocation dark_oak = new ResourceLocation("customnpcs", "textures/models/chair/dark_oak.png");
    private static final ResourceLocation acacia = new ResourceLocation("customnpcs", "textures/models/chair/acacia.png");

    public BlockChairRenderer() {
        ((BlockChair)CustomItems.chair).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileChair chairTile;
        TileVariant tile = (TileVariant)var1;
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (tile instanceof TileChair && (chairTile = (TileChair)tile).isPushed()) {
            float pushDistance = 0.6f;
            switch (tile.rotation) {
                case 0: {
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-pushDistance));
                    break;
                }
                case 1: {
                    GL11.glTranslatef((float)(-pushDistance), (float)0.0f, (float)0.0f);
                    break;
                }
                case 2: {
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)pushDistance);
                    break;
                }
                case 3: {
                    GL11.glTranslatef((float)pushDistance, (float)0.0f, (float)0.0f);
                }
            }
        }
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3008);
        BlockChairRenderer.setWoodTexture(var1.func_145832_p());
        if (ConfigClient.LegacyChair) {
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else if (ConfigClient.WoodTextures) {
            this.chairSpoof.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.setChairTexture(var1.func_145832_p());
            this.chair.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (ConfigClient.LegacyChair) {
            BlockChairRenderer.setWoodTexture(metadata);
            GL11.glScalef((float)1.2f, (float)1.1f, (float)1.2f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)-0.15f, (float)0.0f);
            GL11.glScalef((float)1.0f, (float)0.9f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            if (ConfigClient.WoodTextures) {
                this.chairSpoof.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.setChairTexture(metadata);
                this.chair.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        }
        GL11.glPopMatrix();
    }

    public void setChairTexture(int meta) {
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
        return CustomItems.chair.func_149645_b();
    }
}

