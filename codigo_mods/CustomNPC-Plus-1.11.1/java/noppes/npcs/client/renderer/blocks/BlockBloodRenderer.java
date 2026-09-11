/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;

public class BlockBloodRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        renderer.func_147784_q(block, 0, 0, 0);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (!this.shouldDraw(world, x, y, z, block)) {
            return false;
        }
        renderer.func_147786_a(true);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147786_a(false);
        return true;
    }

    private boolean shouldDraw(IBlockAccess world, int x, int y, int z, Block block) {
        return block.func_149646_a(world, x + 1, y, z, 0) || block.func_149646_a(world, x - 1, y, z, 0) || block.func_149646_a(world, x, y + 1, z, 0) || block.func_149646_a(world, x, y - 1, z, 0) || block.func_149646_a(world, x, y, z + 1, 0) || block.func_149646_a(world, x, y, z - 1, 0);
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return CustomItems.blood.func_149645_b();
    }
}

