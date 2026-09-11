/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.block.ModelBlockArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;

@SideOnly(value=Side.CLIENT)
public class RenderBlockMiniArmourer
extends TileEntitySpecialRenderer {
    private static final ModelBlockArmourer modelArmourer = new ModelBlockArmourer();

    public void renderTileEntityAt(TileEntityMiniArmourer tileEntity, double x, double y, double z, float tickTime) {
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71424_I.func_76320_a("armourersMiniArmourer");
        float scale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        modelArmourer.render(tileEntity, tickTime, scale);
        GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
        ISkinType skinType = tileEntity.getSkinType();
        if (skinType != null) {
            float rotation = (float)((double)System.currentTimeMillis() / 25.0 % 360.0);
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            this.func_147499_a(Minecraft.func_71410_x().field_71439_g.func_110306_p());
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        mc.field_71424_I.func_76319_b();
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float tickTime) {
        this.renderTileEntityAt((TileEntityMiniArmourer)tileEntity, x, y, z, tickTime);
    }
}

