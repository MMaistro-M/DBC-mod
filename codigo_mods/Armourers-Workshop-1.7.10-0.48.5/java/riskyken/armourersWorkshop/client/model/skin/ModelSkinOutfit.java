/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.skin;

import java.util.ArrayList;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.skin.SkinModelTexture;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinPaintCache;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.SkinUtils;

public class ModelSkinOutfit
extends AbstractModelSkin {
    @Override
    public void render(Entity entity, Skin skin, boolean showSkinPaint, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        if (skin == null) {
            return;
        }
        ArrayList<SkinPart> parts = skin.getParts();
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
        ApiRegistrar.INSTANCE.onRenderEquipment(entity, SkinTypeRegistry.skinOutfit);
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
            if (!itemRender) {
                GL11.glTranslated((double)0.0, (double)(-12.0f * SCALE), (double)0.0);
            }
            this.field_78116_c.func_78785_a(SCALE);
            this.field_78115_e.func_78785_a(SCALE);
            this.field_78113_g.func_78785_a(SCALE);
            this.field_78112_f.func_78785_a(SCALE);
            this.field_78124_i.func_78785_a(SCALE);
            this.field_78123_h.func_78785_a(SCALE);
            GL11.glPopAttrib();
        }
        boolean overrideLeftArm = SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.getValue(skin.getProperties());
        boolean overrideRightArm = SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skin.getProperties());
        double angle = 45.0;
        SkinWings.MovementType movmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skin.getProperties()));
        for (int i = 0; i < parts.size(); ++i) {
            float f6;
            SkinPart part = parts.get(i);
            GL11.glPushMatrix();
            if (this.field_78091_s) {
                f6 = 2.0f;
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * SCALE), (float)0.0f);
            }
            if (part.getPartType().getRegistryName().equals("armourers:head.base")) {
                GL11.glPushMatrix();
                if (this.field_78091_s) {
                    f6 = 2.0f;
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
            if (part.getPartType().getRegistryName().equals("armourers:chest.base")) {
                this.renderChest(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            } else if (part.getPartType().getRegistryName().equals("armourers:chest.leftArm")) {
                this.renderLeftArm(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading, overrideLeftArm);
            } else if (part.getPartType().getRegistryName().equals("armourers:chest.rightArm")) {
                this.renderRightArm(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading, overrideRightArm);
            }
            if (part.getPartType().getRegistryName().equals("armourers:legs.leftLeg")) {
                this.renderLeftLeg(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            } else if (part.getPartType().getRegistryName().equals("armourers:legs.rightLeg")) {
                this.renderRightLeg(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            } else if (part.getPartType().getRegistryName().equals("armourers:legs.skirt")) {
                this.renderSkirt(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            }
            if (part.getPartType().getRegistryName().equals("armourers:feet.leftFoot")) {
                this.renderLeftFoot(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            } else if (part.getPartType().getRegistryName().equals("armourers:feet.rightFoot")) {
                this.renderRightFoot(part, SCALE, skinDye, extraColour, itemRender, distance, doLodLoading);
            }
            if (this.field_78117_n) {
                GL11.glRotated((double)28.0, (double)1.0, (double)0.0, (double)0.0);
            }
            if (part.getPartType().getRegistryName().equals("armourers:wings.leftWing")) {
                angle = SkinUtils.getFlapAngleForWings(entity, skin, i);
                this.renderLeftWing(part, SCALE, skinDye, extraColour, distance, angle, doLodLoading, movmentType);
            }
            if (part.getPartType().getRegistryName().equals("armourers:wings.rightWing")) {
                angle = SkinUtils.getFlapAngleForWings(entity, skin, i);
                this.renderRightWing(part, SCALE, skinDye, extraColour, distance, -angle, doLodLoading, movmentType);
            }
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

    private void renderChest(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        if (this.field_78117_n) {
            GL11.glRotated((double)28.0, (double)1.0, (double)0.0, (double)0.0);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }

    private void renderLeftArm(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading, boolean override) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(5.0f * scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * scale), (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78113_g.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78113_g.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78113_g.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (this.slim & !override) {
            GL11.glTranslatef((float)(-0.25f * scale), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * scale), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }

    private void renderRightArm(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading, boolean override) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-5.0f * scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * scale), (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        if (this.slim & !override) {
            GL11.glTranslatef((float)(0.25f * scale), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.5f * scale), (float)0.0f);
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }

    private void renderLeftLeg(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
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

    private void renderRightLeg(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
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

    private void renderSkirt(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)(12.0f * scale), (double)0.0);
        if (this.field_78117_n) {
            GL11.glTranslated((double)0.0, (double)(-3.0f * scale), (double)(4.0f * scale));
        }
        if (this.field_78093_q) {
            GL11.glRotated((double)-70.0, (double)1.0, (double)0.0, (double)0.0);
        }
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
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

    private void renderLeftWing(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, double distance, double angle, boolean doLodLoading, SkinWings.MovementType movmentType) {
        GL11.glPushMatrix();
        Point3D point = new Point3D(0, 0, 0);
        ForgeDirection axis = ForgeDirection.DOWN;
        if (part.getMarkerCount() > 0) {
            point = part.getMarker(0);
            axis = part.getMarkerSide(0);
        }
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)(SCALE * 0.5f), (double)(SCALE * 0.5f), (double)(SCALE * 0.5f));
        GL11.glTranslated((double)(SCALE * (float)point.getX()), (double)(SCALE * (float)point.getY()), (double)(SCALE * (float)point.getZ()));
        switch (axis) {
            case UP: {
                GL11.glRotated((double)angle, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)angle, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)angle, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)angle, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(SCALE * (float)(-point.getX())), (double)(SCALE * (float)(-point.getY())), (double)(SCALE * (float)(-point.getZ())));
        GL11.glTranslated((double)(SCALE * -0.5f), (double)(SCALE * -0.5f), (double)(SCALE * -0.5f));
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }

    private void renderRightWing(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, double distance, double angle, boolean doLodLoading, SkinWings.MovementType movmentType) {
        GL11.glPushMatrix();
        Point3D point = new Point3D(0, 0, 0);
        ForgeDirection axis = ForgeDirection.DOWN;
        if (part.getMarkerCount() > 0) {
            point = part.getMarker(0);
            axis = part.getMarkerSide(0);
        }
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)(SCALE * 0.5f), (double)(SCALE * 0.5f), (double)(SCALE * 0.5f));
        GL11.glTranslated((double)(SCALE * (float)point.getX()), (double)(SCALE * (float)point.getY()), (double)(SCALE * (float)point.getZ()));
        switch (axis) {
            case UP: {
                GL11.glRotated((double)angle, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)angle, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)angle, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)angle, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(SCALE * (float)(-point.getX())), (double)(SCALE * (float)(-point.getY())), (double)(SCALE * (float)(-point.getZ())));
        GL11.glTranslated((double)(SCALE * -0.5f), (double)(SCALE * -0.5f), (double)(SCALE * -0.5f));
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }
}

