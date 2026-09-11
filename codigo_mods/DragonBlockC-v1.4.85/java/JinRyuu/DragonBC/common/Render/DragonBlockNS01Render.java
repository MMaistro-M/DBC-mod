/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Render.DragonBlock01Model;
import JinRyuu.DragonBC.common.Render.DragonBlockNS01TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DragonBlockNS01Render
extends TileEntitySpecialRenderer {
    private DragonBlock01Model aModel = new DragonBlock01Model();

    public void renderAModelAt(DragonBlockNS01TileEntity tileentity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 3.01f), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:DragonBlockStone.png");
        this.func_147499_a(tx);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        this.aModel.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((DragonBlockNS01TileEntity)tileentity, d, d1, d2, f);
    }
}

