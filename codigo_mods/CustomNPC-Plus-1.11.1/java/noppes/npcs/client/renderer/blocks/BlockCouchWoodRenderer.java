/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockCouchWood;
import noppes.npcs.blocks.tiles.TileCouchWood;
import noppes.npcs.client.model.blocks.legacy.couch.ModelCouchWoodLeft;
import noppes.npcs.client.model.blocks.legacy.couch.ModelCouchWoodMiddle;
import noppes.npcs.client.model.blocks.legacy.couch.ModelCouchWoodRight;
import noppes.npcs.client.model.blocks.legacy.couch.ModelCouchWoodSingle;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockCouchWoodRenderer
extends BlockRendererInterface {
    private final ModelBase model = new ModelCouchWoodMiddle();
    private final ModelBase modelLeft = new ModelCouchWoodLeft();
    private final ModelBase modelRight = new ModelCouchWoodRight();
    private final ModelBase modelCorner = new ModelCouchWoodSingle();

    public BlockCouchWoodRenderer() {
        ((BlockCouchWood)CustomItems.couchWood).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileCouchWood tile = (TileCouchWood)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        BlockCouchWoodRenderer.setWoodTexture(var1.func_145832_p());
        if (tile.hasLeft && tile.hasRight) {
            this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else if (tile.hasLeft) {
            this.modelLeft.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else if (tile.hasRight) {
            this.modelRight.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.modelCorner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.9f, (float)0.1f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        BlockCouchWoodRenderer.setWoodTexture(metadata);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.modelCorner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.couchWood.func_149645_b();
    }
}

