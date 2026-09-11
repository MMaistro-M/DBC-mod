/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public class ModelSkinBow
extends AbstractModelSkin {
    public int frame = 0;

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
            this.field_78091_s = player.func_70631_g_();
            this.field_78120_m = 0;
            if (player.func_70694_bm() != null) {
                this.field_78120_m = 1;
            }
        }
        if (ClientProxy.isJrbaClientLoaded()) {
            this.field_78091_s = false;
        }
        ApiRegistrar.INSTANCE.onRenderEquipment(entity, SkinTypeRegistry.skinBow);
        if (this.frame > parts.size() - 1) {
            this.frame = parts.size() - 1;
        }
        if (this.frame < 0 | this.frame > parts.size() - 1) {
            ModLogger.log("wow");
            return;
        }
        SkinPart part = parts.get(this.frame);
        GL11.glPushMatrix();
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * SCALE), (float)0.0f);
        }
        ApiRegistrar.INSTANCE.onRenderEquipmentPart(entity, part.getPartType());
        this.renderRightArm(part, SCALE, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.frame = 0;
    }

    private void renderRightArm(SkinPart part, float scale, ISkinDye skinDye, byte[] extraColour, double distance, boolean doLodLoading) {
        GL11.glPushMatrix();
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78115_e.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)((float)Math.toDegrees(this.field_78112_f.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
        this.renderPart(part, scale, skinDye, extraColour, distance, doLodLoading);
        GL11.glPopMatrix();
    }
}

