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
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockBigSign;
import noppes.npcs.blocks.tiles.TileBigSign;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.model.blocks.ModelBigSign;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockBigSignRenderer
extends BlockRendererInterface {
    private final ModelBigSign model = new ModelBigSign();
    private static final ResourceLocation resource = new ResourceLocation("customnpcs:textures/models/BigSign.png");

    public BlockBigSignRenderer() {
        ((BlockBigSign)CustomItems.bigsign).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        Block block = var1.func_145838_q();
        TileBigSign tile = (TileBigSign)var1;
        Minecraft mc = Minecraft.func_71410_x();
        if (tile.block == null || tile.hasChanged) {
            tile.block = new TextBlockClient(tile.getText(), 112, true, mc.field_71439_g);
            tile.hasChanged = false;
        }
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        float xOffset = 0.0f;
        float yOffset = 0.0f;
        if (tile.rotation == 1) {
            xOffset = -0.44f;
        } else if (tile.rotation == 3) {
            xOffset = 0.44f;
        } else if (tile.rotation == 2) {
            yOffset = -0.44f;
        } else if (tile.rotation == 0) {
            yOffset = 0.44f;
        }
        GL11.glTranslatef((float)((float)var2 + 0.5f + xOffset), (float)((float)var4 + 0.5f), (float)((float)var6 + 0.5f + yOffset));
        float f1 = 0.6666667f;
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (tile.rotation % 2 == 0) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        manager.func_110577_a(resource);
        this.model.renderSign();
        GL11.glPopMatrix();
        if (!tile.block.lines.isEmpty() && !this.playerTooFar(tile)) {
            float f3 = 0.0133f * f1;
            GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.065f);
            GL11.glScalef((float)f3, (float)(-f3), (float)f3);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)(-1.0f * f3));
            GL11.glDepthMask((boolean)false);
            FontRenderer fontrenderer = this.func_147498_b();
            float lineOffset = 0.0f;
            if (tile.block.lines.size() < 14) {
                lineOffset = (14.0f - (float)tile.block.lines.size()) / 2.0f;
            }
            for (int i = 0; i < tile.block.lines.size(); ++i) {
                String text = tile.block.lines.get(i).func_150254_d();
                fontrenderer.func_78276_b(text, -fontrenderer.func_78256_a(text) / 2, (int)((double)(lineOffset + (float)i) * ((double)fontrenderer.field_78288_b - 0.3)), 0);
                if (i == 12) break;
            }
            GL11.glDepthMask((boolean)true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        manager.func_110577_a(resource);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.model.renderSign();
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return CustomItems.bigsign.func_149645_b();
    }
}

