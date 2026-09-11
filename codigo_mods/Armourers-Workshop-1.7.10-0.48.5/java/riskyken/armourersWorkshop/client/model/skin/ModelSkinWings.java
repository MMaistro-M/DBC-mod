/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.skin;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.SkinUtils;

public class ModelSkinWings
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
        ApiRegistrar.INSTANCE.onRenderEquipment(entity, SkinTypeRegistry.skinSword);
        for (int i = 0; i < parts.size(); ++i) {
            SkinPart part = parts.get(i);
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)(SCALE * 2.0f));
            if (this.field_78091_s) {
                float f6 = 2.0f;
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * SCALE), (float)0.0f);
            }
            ApiRegistrar.INSTANCE.onRenderEquipmentPart(entity, part.getPartType());
            double angle = 45.0;
            SkinWings.MovementType movmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skin.getProperties()));
            angle = SkinUtils.getFlapAngleForWings(entity, skin, i);
            if (this.field_78117_n) {
                GL11.glRotated((double)28.0, (double)1.0, (double)0.0, (double)0.0);
            }
            if (part.getPartType().getPartName().equals("leftWing")) {
                this.renderLeftWing(part, SCALE, skinDye, extraColour, distance, angle, doLodLoading, movmentType);
            }
            if (part.getPartType().getPartName().equals("rightWing")) {
                this.renderRightWing(part, SCALE, skinDye, extraColour, distance, -angle, doLodLoading, movmentType);
            }
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
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

