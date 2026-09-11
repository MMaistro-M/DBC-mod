/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.model.block.ModelBlockArmourer;

public class RenderItemBlockMiniArmourer
implements IItemRenderer {
    private static final ModelBlockArmourer modelArmourer = new ModelBlockArmourer();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        float scale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON | type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslatef((float)0.5f, (float)-0.8f, (float)-0.5f);
        }
        modelArmourer.render(null, 0.0f, scale);
        GL11.glPopMatrix();
    }
}

