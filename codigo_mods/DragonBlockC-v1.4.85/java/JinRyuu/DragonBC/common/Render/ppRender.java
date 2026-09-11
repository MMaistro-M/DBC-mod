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

import JinRyuu.DragonBC.common.Render.ppModel;
import JinRyuu.DragonBC.common.Render.ppTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ppRender
extends TileEntitySpecialRenderer {
    private ppModel aModel = new ppModel();

    public void renderAModelAt(ppTileEntity tileentity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        boolean s = true;
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 0.0f + (float)s), (float)((float)d2 + 0.5f));
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:textures/blades/P.png");
        this.func_147499_a(tx);
        GL11.glPushMatrix();
        GL11.glScaled((double)1.0, (double)((double)s), (double)1.0);
        this.aModel.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((ppTileEntity)tileentity, d, d1, d2, f);
    }
}

