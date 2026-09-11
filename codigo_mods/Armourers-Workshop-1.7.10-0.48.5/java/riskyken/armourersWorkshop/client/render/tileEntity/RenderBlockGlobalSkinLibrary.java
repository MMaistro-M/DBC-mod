/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.model.block.ModelBlockGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;

public class RenderBlockGlobalSkinLibrary
extends TileEntitySpecialRenderer {
    private static final ModelBlockGlobalSkinLibrary GLOBE_MODEL = new ModelBlockGlobalSkinLibrary();
    private static final float SCALE = 0.0625f;

    public void renderTileEntityAt(TileEntityGlobalSkinLibrary tileEntity, double x, double y, double z, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 1.5), (double)(z + 0.5));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GLOBE_MODEL.render(tileEntity, partialTickTime, 0.0625f);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        this.renderTileEntityAt((TileEntityGlobalSkinLibrary)tileEntity, x, y, z, partialTickTime);
    }
}

