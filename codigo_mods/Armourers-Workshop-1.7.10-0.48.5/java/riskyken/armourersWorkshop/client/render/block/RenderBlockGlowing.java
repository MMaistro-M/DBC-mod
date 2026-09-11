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
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.RenderBridge;

@SideOnly(value=Side.CLIENT)
public class RenderBlockGlowing
implements ISimpleBlockRenderingHandler {
    public static int renderId = 0;

    public RenderBlockGlowing() {
        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        boolean glowing;
        this.renderItem(block, metadata, renderer);
        boolean bl = glowing = block.func_149750_m() > 0;
        if (glowing) {
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)8192);
            GL11.glScalef((float)1.1f, (float)1.1f, (float)1.1f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.6f);
            GL11.glEnable((int)3042);
            ModRenderHelper.enableAlphaBlend();
            GL11.glDisable((int)2896);
            this.renderItem(block, metadata, renderer);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    private void renderItem(Block block, int metadata, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.field_78398_a;
        block.func_149683_g();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, 0.0, 0.0, 0.0, block.func_149691_a(255, metadata));
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (block instanceof IPantableBlock) {
            int light = block.getLightValue(world, x, y, z);
            Tessellator tessellator = Tessellator.field_78398_a;
            ICubeColour colour = ((IPantableBlock)block).getColour(world, x, y, z);
            boolean rendered = false;
            renderer.field_147837_f = false;
            rendered = light > 1 ? this.renderFaces(world, x, y, z, colour, block, renderer) : this.renderFacesWithLighting(world, x, y, z, colour, block, renderer);
            return rendered;
        }
        renderer.field_147837_f = false;
        return renderer.func_147784_q(block, x, y, z);
    }

    private boolean renderFaces(IBlockAccess world, int x, int y, int z, ICubeColour colour, Block block, RenderBlocks renderer) {
        int pt;
        boolean rendered = false;
        int meta = world.func_72805_g(x, y, z);
        IRenderBuffer renderBuffer = RenderBridge.INSTANCE;
        renderBuffer.setBrightness(0xF000F0);
        double faceOffset = 0.02;
        if (renderer.field_147837_f || block.func_149646_a(world, x, y - 1, z, 0)) {
            pt = colour.getPaintType(0) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(0), colour.getGreen(0), colour.getBlue(0));
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 1) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147768_a(block, (double)x, (double)y - faceOffset, (double)z, block.func_149691_a(0, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y + 1, z, 1)) {
            pt = colour.getPaintType(1) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(1), colour.getGreen(1), colour.getBlue(1));
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 2) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147806_b(block, (double)x, (double)y + faceOffset, (double)z, block.func_149691_a(1, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z - 1, 2)) {
            pt = colour.getPaintType(2) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(2), colour.getGreen(2), colour.getBlue(2));
            renderer.func_147761_c(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 3) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147761_c(block, (double)x, (double)y, (double)z - faceOffset, block.func_149691_a(2, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z + 1, 3)) {
            pt = colour.getPaintType(3) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(3), colour.getGreen(3), colour.getBlue(3));
            renderer.func_147734_d(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 4) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147734_d(block, (double)x, (double)y, (double)z + faceOffset, block.func_149691_a(3, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x - 1, y, z, 4)) {
            pt = colour.getPaintType(4) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(4), colour.getGreen(4), colour.getBlue(4));
            renderer.func_147798_e(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 5) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147798_e(block, (double)x - faceOffset, (double)y, (double)z, block.func_149691_a(4, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x + 1, y, z, 5)) {
            pt = colour.getPaintType(5) & 0xFF;
            renderBuffer.setColorOpaque_B(colour.getRed(5), colour.getGreen(5), colour.getBlue(5));
            renderer.func_147764_f(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 6) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147764_f(block, (double)x + faceOffset, (double)y, (double)z, block.func_149691_a(5, meta));
            }
            rendered = true;
        }
        return rendered;
    }

    private boolean renderFacesWithLighting(IBlockAccess world, int x, int y, int z, ICubeColour colour, Block block, RenderBlocks renderer) {
        int pt;
        boolean rendered = false;
        int meta = world.func_72805_g(x, y, z);
        IRenderBuffer renderBuffer = RenderBridge.INSTANCE;
        double faceOffset = 0.02;
        float yNegLight = 0.5f;
        float yPosLight = 1.0f;
        float zLight = 0.8f;
        float xLight = 0.6f;
        float yNegR = yNegLight * ((float)(colour.getRed(0) & 0xFF) / 255.0f);
        float yNegG = yNegLight * ((float)(colour.getGreen(0) & 0xFF) / 255.0f);
        float yNegB = yNegLight * ((float)(colour.getBlue(0) & 0xFF) / 255.0f);
        float yPosR = yPosLight * ((float)(colour.getRed(1) & 0xFF) / 255.0f);
        float yPosG = yPosLight * ((float)(colour.getGreen(1) & 0xFF) / 255.0f);
        float yPosB = yPosLight * ((float)(colour.getBlue(1) & 0xFF) / 255.0f);
        float zNegR = zLight * ((float)(colour.getRed(2) & 0xFF) / 255.0f);
        float zNegG = zLight * ((float)(colour.getGreen(2) & 0xFF) / 255.0f);
        float zNegB = zLight * ((float)(colour.getBlue(2) & 0xFF) / 255.0f);
        float zPosR = zLight * ((float)(colour.getRed(3) & 0xFF) / 255.0f);
        float zPosG = zLight * ((float)(colour.getGreen(3) & 0xFF) / 255.0f);
        float zPosB = zLight * ((float)(colour.getBlue(3) & 0xFF) / 255.0f);
        float xNegR = xLight * ((float)(colour.getRed(4) & 0xFF) / 255.0f);
        float xNegG = xLight * ((float)(colour.getGreen(4) & 0xFF) / 255.0f);
        float xNegB = xLight * ((float)(colour.getBlue(4) & 0xFF) / 255.0f);
        float xPosR = xLight * ((float)(colour.getRed(5) & 0xFF) / 255.0f);
        float xPosG = xLight * ((float)(colour.getGreen(5) & 0xFF) / 255.0f);
        float xPosB = xLight * ((float)(colour.getBlue(5) & 0xFF) / 255.0f);
        if (renderer.field_147837_f || block.func_149646_a(world, x, y - 1, z, 0)) {
            pt = colour.getPaintType(0) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x, y - 1, z));
            renderBuffer.setColorOpaque_F(yNegR, yNegG, yNegB);
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 1) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147768_a(block, (double)x, (double)y - faceOffset, (double)z, block.func_149691_a(0, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y + 1, z, 1)) {
            pt = colour.getPaintType(1) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x, y + 1, z));
            renderBuffer.setColorOpaque_F(yPosR, yPosG, yPosB);
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 2) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147806_b(block, (double)x, (double)y + faceOffset, (double)z, block.func_149691_a(1, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z - 1, 2)) {
            pt = colour.getPaintType(2) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x, y, z - 1));
            renderBuffer.setColorOpaque_F(zNegR, zNegG, zNegB);
            renderer.func_147761_c(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 3) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147761_c(block, (double)x, (double)y, (double)z - faceOffset, block.func_149691_a(2, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z + 1, 3)) {
            pt = colour.getPaintType(3) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x, y, z + 1));
            renderBuffer.setColorOpaque_F(zPosR, zPosG, zPosB);
            renderer.func_147734_d(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 4) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147734_d(block, (double)x, (double)y, (double)z + faceOffset, block.func_149691_a(3, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x - 1, y, z, 4)) {
            pt = colour.getPaintType(4) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x - 1, y, z));
            renderBuffer.setColorOpaque_F(xNegR, xNegG, xNegB);
            renderer.func_147798_e(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 5) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147798_e(block, (double)x - faceOffset, (double)y, (double)z, block.func_149691_a(4, meta));
            }
            rendered = true;
        }
        if (renderer.field_147837_f || block.func_149646_a(world, x + 1, y, z, 5)) {
            pt = colour.getPaintType(5) & 0xFF;
            renderBuffer.setBrightness(block.func_149677_c(world, x + 1, y, z));
            renderBuffer.setColorOpaque_F(xPosR, xPosG, xPosB);
            renderer.func_147764_f(block, (double)x, (double)y, (double)z, block.func_149691_a(pt, 0));
            if (meta == 6) {
                renderBuffer.setColorOpaque_B((byte)-1, (byte)-1, (byte)-1);
                renderer.func_147764_f(block, (double)x + faceOffset, (double)y, (double)z, block.func_149691_a(5, meta));
            }
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

