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
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinRenderHelper;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.data.SkinTexture;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class RenderBlockArmourer
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntityArmourer te, double x, double y, double z, float tickTime) {
        PlayerTexture playerTexture;
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71424_I.func_76320_a("armourersArmourer");
        float scale = 0.0625f;
        ISkinType skinType = te.getSkinType();
        mc.field_71424_I.func_76320_a("textureBuild");
        if (te.skinTexture == null) {
            te.skinTexture = new SkinTexture();
        }
        if (!(playerTexture = ClientProxy.playerTextureDownloader.getPlayerTexture(te.getTexture())).isDownloaded()) {
            playerTexture = ClientProxy.playerTextureDownloader.getPlayerTexture(te.getTextureOld());
        }
        te.skinTexture.updateForResourceLocation(playerTexture.getResourceLocation());
        te.skinTexture.updatePaintData(te.getPaintData());
        mc.field_71424_I.func_76319_b();
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glColor3f((float)0.77f, (float)0.77f, (float)0.77f);
        ModRenderHelper.disableLighting();
        GL11.glDisable((int)2896);
        GL11.glTranslated((double)x, (double)y, (double)z);
        if (te.getDirection() != null) {
            switch (te.getDirection()) {
                case EAST: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case SOUTH: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case WEST: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
            }
        }
        GL11.glTranslated((double)0.0, (double)te.getHeightOffset(), (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glScalef((float)16.0f, (float)16.0f, (float)16.0f);
        if (skinType != null) {
            mc.field_71424_I.func_76320_a("modelRender");
            GL11.glPolygonOffset((float)3.0f, (float)3.0f);
            GL11.glEnable((int)32823);
            long time = System.currentTimeMillis();
            int fadeTime = 1000;
            int fade = (int)(time - playerTexture.getDownloadTime());
            if (playerTexture.isDownloaded() & fade < fadeTime) {
                PlayerTexture oldTexture = te.getTextureOld();
                oldTexture = ClientProxy.playerTextureDownloader.getPlayerTexture(oldTexture);
                this.func_147499_a(oldTexture.getResourceLocation());
                SkinRenderHelper.renderBuildingGuide(skinType, scale, te.getSkinProps(), te.isShowHelper());
                te.skinTexture.bindTexture();
                ModRenderHelper.enableAlphaBlend();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)fade / 1000.0f));
                GL11.glPolygonOffset((float)-6.0f, (float)-6.0f);
                SkinRenderHelper.renderBuildingGuide(skinType, scale, te.getSkinProps(), te.isShowHelper());
                GL11.glPolygonOffset((float)3.0f, (float)3.0f);
                ModRenderHelper.disableAlphaBlend();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                te.skinTexture.bindTexture();
                SkinRenderHelper.renderBuildingGuide(skinType, scale, te.getSkinProps(), te.isShowHelper());
            }
            GL11.glPolygonOffset((float)-3.0f, (float)-3.0f);
            mc.field_71424_I.func_76319_b();
            mc.field_71424_I.func_76320_a("renderGuideGrid");
            SkinRenderHelper.renderBuildingGrid(skinType, scale, te.isShowGuides(), te.getSkinProps(), SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(te.getSkinProps()));
            mc.field_71424_I.func_76319_b();
            GL11.glPolygonOffset((float)0.0f, (float)0.0f);
            GL11.glDisable((int)32823);
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ModRenderHelper.enableLighting();
        GL11.glEnable((int)2896);
        mc.field_71424_I.func_76319_b();
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float tickTime) {
        this.renderTileEntityAt((TileEntityArmourer)tileEntity, x, y, z, tickTime);
    }
}

