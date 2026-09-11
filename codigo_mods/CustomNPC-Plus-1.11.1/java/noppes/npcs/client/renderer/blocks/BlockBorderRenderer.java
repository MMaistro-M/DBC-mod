/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockBorder;
import noppes.npcs.blocks.tiles.TileBorder;
import org.lwjgl.opengl.GL11;

public class BlockBorderRenderer
implements ISimpleBlockRenderingHandler {
    public BlockBorderRenderer() {
        ((BlockBorder)CustomItems.border).renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileBorder tile = (TileBorder)world.func_147438_o(x, y, z);
        GL11.glPushMatrix();
        if (tile.rotation == 1) {
            renderer.field_147867_u = 1;
        } else if (tile.rotation == 3) {
            renderer.field_147867_u = 2;
        } else if (tile.rotation == 2) {
            renderer.field_147867_u = 3;
        }
        renderer.func_147784_q(CustomItems.border, x, y, z);
        renderer.field_147867_u = 0;
        GL11.glPopMatrix();
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return CustomItems.border.func_149645_b();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }
}

