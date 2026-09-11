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
import java.util.ArrayList;
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
public class ModelSkinFeet
extends AbstractModelSkin {
    @Override
    public void render(Entity entity, Skin armourData, boolean showSkinPaint, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        if (armourData == null) {
            return;
        }
        ArrayList<SkinPart> parts = armourData.getParts();
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
        ApiRegistrar.INSTANCE.onRenderEquipment(entity, SkinTypeRegistry.skinFeet);
        RenderHelper.func_74520_c();
        if (armourData.hasPaintData() & showSkinPaint) {
            if (extraColour == null) {
                extraColour = PaintingHelper.getLocalPlayerExtraColours();
            }
            SkinModelTexture st = ClientSkinPaintCache.INSTANCE.getTextureForSkin(armourData, skinDye, extraColour);
            st.bindTexture();
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3008);
            if (!itemRender) {
                // empty if block
            }
            this.field_78124_i.func_78785_a(SCALE);
            this.field_78123_h.func_78785_a(SCALE);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        for (int i = 0; i < parts.size(); ++i) {
            SkinPart part = parts.get(i);
            GL11.glPushMatrix();
            if (this.field_78091_s) {
                float f6 = 2.0f;
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * SCALE), (float)0.0f);
            }
            ApiRegistrar.INSTANCE.onRenderEquipmentPart(entity, part.getPartType());
            if (part.getPartType().getPartName().equals("leftFoot")) {
                this.renderLeftFoot(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            } else if (part.getPartType().getPartName().equals("rightFoot")) {
                this.renderRightFoot(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            }
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderLeftFoot(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        if (this.field_78117_n) {
            GL11.glTranslated((double)0.0, (double)(-3.0f * scale), (double)(4.0f * scale));
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * scale), (double)0.0);
        GL11.glTranslated((double)(2.0f * scale), (double)0.0, (double)0.0);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78124_i.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78124_i.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78124_i.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }

    private void renderRightFoot(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        if (this.field_78117_n) {
            GL11.glTranslated((double)0.0, (double)(-3.0f * scale), (double)(4.0f * scale));
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * scale), (double)0.0);
        GL11.glTranslated((double)(-2.0f * scale), (double)0.0, (double)0.0);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78123_h.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78123_h.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78123_h.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }
}

