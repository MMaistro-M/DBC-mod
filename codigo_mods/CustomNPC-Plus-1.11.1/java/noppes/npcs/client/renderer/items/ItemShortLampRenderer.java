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
package noppes.npcs.client.renderer.items;

import kamkeel.npcs.util.ColorUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.client.renderer.blocks.BlockShortLampRenderer;
import org.lwjgl.opengl.GL11;

public class ItemShortLampRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        int meta = item.func_77960_j();
        int colorValue = ColorUtil.colorTableInts[15 - meta];
        if (item.func_77942_o() && item.func_77978_p().func_74764_b("BrushColor")) {
            colorValue = item.func_77978_p().func_74762_e("BrushColor");
        }
        float[] color = ColorUtil.hexToRGB(colorValue);
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        GL11.glTranslatef((float)0.0f, (float)0.42f, (float)0.0f);
        GL11.glScalef((float)0.76f, (float)0.76f, (float)0.76f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        BlockShortLampRenderer.setLampTexture(meta);
        BlockShortLampRenderer.model.Lamp.func_78785_a(0.0625f);
        BlockShortLampRenderer.model.Light.func_78785_a(0.0625f);
        GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
        BlockShortLampRenderer.model.Shade.func_78785_a(0.0625f);
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}

