/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Render.DragonBlock01Model;
import JinRyuu.JRMCore.JRMCoreClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class DragonBlock01ItemR
implements IItemRenderer {
    private DragonBlock01Model aModel = new DragonBlock01Model();
    private float t;

    public DragonBlock01ItemR(float t) {
        this.t = t;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type != IItemRenderer.ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:DragonBlock.png");
        JRMCoreClient.mc.func_110434_K().func_110577_a(tx);
        GL11.glScalef((float)this.t, (float)this.t, (float)this.t);
        GL11.glTranslatef((float)((type == IItemRenderer.ItemRenderType.ENTITY ? 0.1f : 0.5f) + -0.08f * this.t), (float)(-0.9f - 0.1f * this.t), (float)0.0f);
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glTranslatef((float)(7.0f - (this.t - 1.0f) * 3.0f), (float)(-15.5f - (this.t - 1.0f) * 2.0f), (float)0.0f);
            GL11.glScalef((float)17.0f, (float)17.0f, (float)17.0f);
        }
        float h1 = 1.0f;
        GL11.glColor3f((float)h1, (float)h1, (float)h1);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2884);
        this.aModel.render();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

