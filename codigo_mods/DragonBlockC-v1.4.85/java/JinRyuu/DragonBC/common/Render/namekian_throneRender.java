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

import JinRyuu.DragonBC.common.Render.namekian_throneModel;
import JinRyuu.DragonBC.common.Render.namekian_throneTileEntity;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class namekian_throneRender
extends TileEntitySpecialRenderer {
    private namekian_throneModel aModel = new namekian_throneModel();

    public void renderAModelAt(namekian_throneTileEntity tileentity, double d, double d1, double d2, float f) {
        int id = tileentity.func_145831_w().func_72805_g(tileentity.field_145851_c, tileentity.field_145848_d, tileentity.field_145849_e);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * id), (float)0.0f, (float)1.0f, (float)0.0f);
        ResourceLocation tx = new ResourceLocation(JRMCoreH.tjdbcAssts + ":textures/tile/namekian_throne.png");
        this.func_147499_a(tx);
        GL11.glPushMatrix();
        this.aModel.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((namekian_throneTileEntity)tileentity, d, d1, d2, f);
    }
}

