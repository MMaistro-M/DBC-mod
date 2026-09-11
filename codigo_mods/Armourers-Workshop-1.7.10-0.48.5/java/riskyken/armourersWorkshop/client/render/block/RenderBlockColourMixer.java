/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class RenderBlockColourMixer
implements ISimpleBlockRenderingHandler {
    public static int renderId = 0;

    public RenderBlockColourMixer() {
        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        ClientProxy.renderPass = 0;
        this.renderItem(block, metadata, renderer, 0);
        ClientProxy.renderPass = 1;
        this.renderItem(block, metadata, renderer, 0);
    }

    private void renderItem(Block block, int metadata, RenderBlocks renderer, int pass) {
        Tessellator tessellator = Tessellator.field_78398_a;
        int rawColors = pass;
        float blockColorRed = (float)(rawColors >> 16 & 0xFF) / 255.0f;
        float blockColorGreen = (float)(rawColors >> 8 & 0xFF) / 255.0f;
        float blockColorBlue = (float)(rawColors & 0xFF) / 255.0f;
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        block.func_149683_g();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, 0.0, 0.0, 0.0, block.func_149691_a(0, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, 0.0, 0.0, 0.0, block.func_149691_a(1, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, 0.0, 0.0, 0.0, block.func_149691_a(2, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, 0.0, 0.0, 0.0, block.func_149691_a(3, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, 0.0, 0.0, 0.0, block.func_149691_a(4, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, 0.0, 0.0, 0.0, block.func_149691_a(5, metadata));
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean rendered = false;
        ClientProxy.renderPass = 0;
        if (renderer.func_147784_q(block, x, y, z)) {
            rendered = true;
        }
        ClientProxy.renderPass = 1;
        if (renderer.func_147784_q(block, x, y, z)) {
            rendered = true;
        }
        return rendered;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return renderId;
    }
}

