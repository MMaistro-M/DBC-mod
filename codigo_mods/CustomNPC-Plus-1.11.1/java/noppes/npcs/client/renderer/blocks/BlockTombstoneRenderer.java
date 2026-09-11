/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockTombstone;
import noppes.npcs.blocks.tiles.TileTombstone;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.model.blocks.ModelTombstone1;
import noppes.npcs.client.model.blocks.ModelTombstone2;
import noppes.npcs.client.model.blocks.ModelTombstone3;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockTombstoneRenderer
extends BlockRendererInterface {
    private final ModelTombstone1 model = new ModelTombstone1();
    private final ModelTombstone2 model2 = new ModelTombstone2();
    private final ModelTombstone3 model3 = new ModelTombstone3();

    public BlockTombstoneRenderer() {
        ((BlockTombstone)CustomItems.tombstone).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileTombstone tile = (TileTombstone)var1;
        int meta = tile.func_145832_p();
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        if (meta == 2) {
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.14f);
        }
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(Stone);
        if (meta == 0) {
            this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else if (meta == 1) {
            this.model2.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.model3.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        if (meta < 2 && !this.playerTooFar(tile)) {
            this.renderText(tile, meta);
        }
        GL11.glPopMatrix();
    }

    private void renderText(TileTombstone tile, int meta) {
        if (tile.block == null || tile.hasChanged) {
            tile.block = new TextBlockClient(tile.getText(), 94, true, Minecraft.func_71410_x().field_71439_g);
            tile.hasChanged = false;
        }
        if (!tile.block.lines.isEmpty()) {
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            float f3 = 0.00665f;
            GL11.glTranslatef((float)0.0f, (float)-0.64f, (float)(meta == 0 ? 0.095f : 0.126f));
            GL11.glScalef((float)f3, (float)(-f3), (float)f3);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)(-1.0f * f3));
            GL11.glDepthMask((boolean)false);
            FontRenderer fontrenderer = this.func_147498_b();
            float lineOffset = 0.0f;
            if (tile.block.lines.size() < 11) {
                lineOffset = (11.0f - (float)tile.block.lines.size()) / 2.0f;
            }
            for (int i = 0; i < tile.block.lines.size(); ++i) {
                String text = tile.block.lines.get(i).func_150254_d();
                fontrenderer.func_78276_b(text, -fontrenderer.func_78256_a(text) / 2, (int)((double)(lineOffset + (float)i) * ((double)fontrenderer.field_78288_b - 0.3)), 0xFFFFFF);
                if (i == 13) break;
            }
            GL11.glDepthMask((boolean)true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(Stone);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (meta == 0) {
            this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else if (meta == 1) {
            this.model2.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.model3.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.tombstone.func_149645_b();
    }
}

