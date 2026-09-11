/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileShelf;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.ModelShelf;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockShelfRenderer
extends BlockRendererInterface {
    private final ModelShelf model = new ModelShelf();

    public BlockShelfRenderer() {
        ((BlockRotated)CustomItems.shelf).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        boolean drawLeft = true;
        boolean drawRight = true;
        if (tile.rotation == 3) {
            drawLeft = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c, var1.field_145848_d, var1.field_145849_e - 1, 3);
            drawRight = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c, var1.field_145848_d, var1.field_145849_e + 1, 3);
        } else if (tile.rotation == 1) {
            drawLeft = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c, var1.field_145848_d, var1.field_145849_e + 1, 1);
            drawRight = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c, var1.field_145848_d, var1.field_145849_e - 1, 1);
        } else if (tile.rotation == 0) {
            drawLeft = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c + 1, var1.field_145848_d, var1.field_145849_e, 0);
            drawRight = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c - 1, var1.field_145848_d, var1.field_145849_e, 0);
        } else if (tile.rotation == 2) {
            drawLeft = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c - 1, var1.field_145848_d, var1.field_145849_e, 2);
            drawRight = this.shouldDraw(var1.func_145831_w(), var1.field_145851_c + 1, var1.field_145848_d, var1.field_145849_e, 2);
        }
        this.model.SupportLeft1.field_78806_j = this.model.SupportLeft2.field_78806_j = drawLeft;
        this.model.SupportRight1.field_78806_j = this.model.SupportRight2.field_78806_j = drawRight;
        BlockShelfRenderer.setWoodTexture(var1.func_145832_p());
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    private boolean shouldDraw(World world, int x, int y, int z, int rotation) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile == null || !(tile instanceof TileShelf)) {
            return true;
        }
        return ((TileShelf)tile).rotation != rotation;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.6f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        BlockShelfRenderer.setWoodTexture(metadata);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.model.SupportLeft2.field_78806_j = true;
        this.model.SupportLeft1.field_78806_j = true;
        this.model.SupportRight2.field_78806_j = true;
        this.model.SupportRight1.field_78806_j = true;
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.shelf.func_149645_b();
    }
}

