/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockCouchWool;
import noppes.npcs.blocks.tiles.TileCouchWool;
import noppes.npcs.client.model.blocks.couch.ModelCouchCorner;
import noppes.npcs.client.model.blocks.couch.ModelCouchLeft;
import noppes.npcs.client.model.blocks.couch.ModelCouchMiddle;
import noppes.npcs.client.model.blocks.couch.ModelCouchRight;
import noppes.npcs.client.model.blocks.couch.ModelCouchSingle;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchCorner;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchCornerWool;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchLeft;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchLeftWool;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchMiddle;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchMiddleWool;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchRight;
import noppes.npcs.client.model.blocks.legacy.couch.ModelLegacyCouchRightWool;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.client.renderer.blocks.BlockTallLampRenderer;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockCouchWoolRenderer
extends BlockRendererInterface {
    private final ModelCouchLeft modelCouchLeft = new ModelCouchLeft();
    private final ModelCouchRight modelCouchRight = new ModelCouchRight();
    private final ModelCouchCorner modelCouchCorner = new ModelCouchCorner();
    public static final ModelCouchMiddle modelCouch = new ModelCouchMiddle();
    private final ModelCouchSingle modelCouchSingle = new ModelCouchSingle();
    public static final ModelBase modelLegacyCouchMiddle = new ModelLegacyCouchMiddle();
    public static final ModelBase modelLegacyCouchMiddleWool = new ModelLegacyCouchMiddleWool();
    private final ModelBase modelLegacyCouchLeft = new ModelLegacyCouchLeft();
    private final ModelBase modelLegacyCouchLeftWool = new ModelLegacyCouchLeftWool();
    private final ModelBase modelLegacyCouchRight = new ModelLegacyCouchRight();
    private final ModelBase modelLegacyCouchRightWool = new ModelLegacyCouchRightWool();
    private final ModelBase modelLegacyCouchCorner = new ModelLegacyCouchCorner();
    private final ModelBase modelLegacyCouchCornerWool = new ModelLegacyCouchCornerWool();
    public static final ResourceLocation oak = new ResourceLocation("customnpcs", "textures/models/couch/oak.png");
    public static final ResourceLocation spruce = new ResourceLocation("customnpcs", "textures/models/couch/spruce.png");
    public static final ResourceLocation birch = new ResourceLocation("customnpcs", "textures/models/couch/birch.png");
    public static final ResourceLocation jungle = new ResourceLocation("customnpcs", "textures/models/couch/jungle.png");
    public static final ResourceLocation acacia = new ResourceLocation("customnpcs", "textures/models/couch/acacia.png");
    public static final ResourceLocation dark_oak = new ResourceLocation("customnpcs", "textures/models/couch/dark_oak.png");
    public static final ResourceLocation wool = new ResourceLocation("customnpcs", "textures/models/couch/wool.png");

    public BlockCouchWoolRenderer() {
        ((BlockCouchWool)CustomItems.couchWool).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileCouchWool tile = (TileCouchWool)var1;
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3008);
        if (ConfigClient.LegacyCouch) {
            BlockCouchWoolRenderer.setWoodTexture(var1.func_145832_p());
            if (tile.hasCornerLeft) {
                this.modelLegacyCouchCorner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasCornerRight) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelLegacyCouchCorner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasLeft && tile.hasRight) {
                modelLegacyCouchMiddle.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasLeft) {
                this.modelLegacyCouchLeft.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasRight) {
                this.modelLegacyCouchRight.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                modelLegacyCouchMiddle.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
            this.func_147499_a(BlockTallLampRenderer.resourceTop);
            float[] color = ColorUtil.hexToRGB(tile.color);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            if (tile.hasCornerLeft || tile.hasCornerRight) {
                this.modelLegacyCouchCornerWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasLeft && tile.hasRight) {
                modelLegacyCouchMiddleWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasLeft) {
                this.modelLegacyCouchLeftWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.hasRight) {
                this.modelLegacyCouchRightWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                modelLegacyCouchMiddleWool.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        } else {
            this.setCouchWood(var1.func_145832_p());
            if (tile.hasCornerLeft) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelCouchCorner.CouchBack.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            } else if (tile.hasCornerRight) {
                this.modelCouchCorner.CouchBack.func_78785_a(0.0625f);
            } else if (tile.hasLeft && tile.hasRight) {
                BlockCouchWoolRenderer.modelCouch.CouchBack.func_78785_a(0.0625f);
            } else if (tile.hasLeft) {
                this.modelCouchLeft.CouchBack.func_78785_a(0.0625f);
            } else if (tile.hasRight) {
                this.modelCouchRight.CouchBack.func_78785_a(0.0625f);
            } else {
                this.modelCouchSingle.CouchBack.func_78785_a(0.0625f);
            }
            Minecraft.func_71410_x().func_110434_K().func_110577_a(wool);
            float[] color = ColorUtil.hexToRGB(tile.color);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            if (tile.hasCornerLeft) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelCouchCorner.Cussion.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            } else if (tile.hasCornerRight) {
                this.modelCouchCorner.Cussion.func_78785_a(0.0625f);
            } else if (tile.hasLeft && tile.hasRight) {
                BlockCouchWoolRenderer.modelCouch.Cussion.func_78785_a(0.0625f);
            } else if (tile.hasLeft) {
                this.modelCouchLeft.Cussion.func_78785_a(0.0625f);
            } else if (tile.hasRight) {
                this.modelCouchRight.Cussion.func_78785_a(0.0625f);
            } else {
                this.modelCouchSingle.Cussion.func_78785_a(0.0625f);
            }
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void setCouchWood(int meta) {
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

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public int getRenderId() {
        return CustomItems.couchWool.func_149645_b();
    }
}

