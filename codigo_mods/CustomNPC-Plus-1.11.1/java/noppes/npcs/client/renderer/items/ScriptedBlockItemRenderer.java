/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.CustomItems;
import org.lwjgl.opengl.GL11;

public class ScriptedBlockItemRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        RenderBlocks renderBlocks = (RenderBlocks)data[0];
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
            this.renderBlock(item, renderBlocks);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslatef((float)0.5f, (float)0.4f, (float)0.5f);
            this.renderBlock(item, renderBlocks);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            this.renderBlock(item, renderBlocks);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.5f, (float)0.4f, (float)0.5f);
            this.renderBlock(item, renderBlocks);
        }
    }

    private void renderBlock(ItemStack item, RenderBlocks renderBlocks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        Block block = CustomItems.scripted;
        int p_147800_2_ = item.func_77960_j();
        block.func_149683_g();
        renderBlocks.func_147775_a(block);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.4f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderBlocks.func_147768_a(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 0, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderBlocks.func_147806_b(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 1, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderBlocks.func_147761_c(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 2, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderBlocks.func_147734_d(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 3, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderBlocks.func_147798_e(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 4, p_147800_2_));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderBlocks.func_147764_f(block, 0.0, 0.0, 0.0, renderBlocks.func_147787_a(block, 5, p_147800_2_));
        tessellator.func_78381_a();
    }
}

