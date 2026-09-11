/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.skin.SkinModelTexture;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinPaintCache;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class ModelSkinHead
extends AbstractModelSkin {
    @Override
    public void render(Entity entity, Skin skin, boolean showSkinPaint, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        if (skin == null) {
            return;
        }
        if (entity != null && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.field_78117_n = player.func_70093_af();
            this.field_78093_q = player.func_70115_ae();
            this.field_78120_m = 0;
            if (player.func_70694_bm() != null) {
                this.field_78120_m = 1;
            }
        }
        if (ClientProxy.isJrbaClientLoaded()) {
            this.field_78091_s = false;
        }
        ApiRegistrar.INSTANCE.onRenderEquipment(entity, SkinTypeRegistry.skinHead);
        RenderHelper.func_74520_c();
        if (skin.hasPaintData() & showSkinPaint) {
            if (extraColour == null) {
                extraColour = PaintingHelper.getLocalPlayerExtraColours();
            }
            SkinModelTexture st = ClientSkinPaintCache.INSTANCE.getTextureForSkin(skin, skinDye, extraColour);
            st.bindTexture();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            this.field_78116_c.func_78785_a(SCALE);
            GL11.glPopAttrib();
        }
        if (skin.getParts().size() > 0) {
            ApiRegistrar.INSTANCE.onRenderEquipmentPart(entity, skin.getParts().get(0).getPartType());
            GL11.glPushMatrix();
            if (this.field_78091_s) {
                float f6 = 2.0f;
                GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
                GL11.glTranslatef((float)0.0f, (float)(16.0f * SCALE), (float)0.0f);
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glRotated((double)Math.toDegrees(this.field_78116_c.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)Math.toDegrees(this.field_78116_c.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)Math.toDegrees(this.field_78116_c.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
            if (this.field_78117_n) {
                GL11.glTranslated((double)0.0, (double)(1.0f * SCALE), (double)0.0);
            }
            this.renderHead(skin.getParts().get(0), SCALE, skinDye, extraColour, distance, doLodLoading);
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderHead(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColours, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.renderPart(part, scale, skinDye, extraColours, distance, doLodLoading);
        GL11.glPopMatrix();
    }
}

