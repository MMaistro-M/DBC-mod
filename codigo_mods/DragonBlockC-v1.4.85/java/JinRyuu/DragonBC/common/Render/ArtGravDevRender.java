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

import JinRyuu.DragonBC.common.Render.ArtGravDevModel;
import JinRyuu.DragonBC.common.Render.ArtGravDevTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ArtGravDevRender
extends TileEntitySpecialRenderer {
    private ArtGravDevModel aModel = new ArtGravDevModel();

    public void renderAModelAt(ArtGravDevTileEntity tileentity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:textures/blockmodels/GravDev" + (tileentity.isBurning() && tileentity.getGravity() > 1.0f ? "On" : "") + ".png");
        this.func_147499_a(tx);
        GL11.glPushMatrix();
        this.aModel.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((ArtGravDevTileEntity)tileentity, d, d1, d2, f);
    }
}

