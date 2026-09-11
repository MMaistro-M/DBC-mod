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

import JinRyuu.DragonBC.common.Render.Hair01TileEntity;
import JinRyuu.DragonBC.common.Render.hair;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Hair01Render
extends TileEntitySpecialRenderer {
    private hair aModel = new hair();

    public void renderAModelAt(Hair01TileEntity tileentity1, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 0.5f), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:armor/hair.png");
        this.func_147499_a(tx);
        GL11.glPushMatrix();
        this.aModel.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((Hair01TileEntity)tileentity, d, d1, d2, f);
    }
}

