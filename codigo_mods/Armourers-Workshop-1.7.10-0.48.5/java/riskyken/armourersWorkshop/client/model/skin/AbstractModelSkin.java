/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.ModelMannequin;
import riskyken.armourersWorkshop.client.model.skin.IEquipmentModel;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;

@SideOnly(value=Side.CLIENT)
public abstract class AbstractModelSkin
extends ModelBiped
implements IEquipmentModel {
    public Skin npcSkinData = null;
    public ISkinDye npcDyeData = null;
    protected boolean slim;
    protected static float SCALE = 0.0625f;

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    protected void setRotation(ModelRenderer targetModel, ModelRenderer sourceModel) {
        targetModel.field_78795_f = sourceModel.field_78795_f;
        targetModel.field_78796_g = sourceModel.field_78796_g;
        targetModel.field_78808_h = sourceModel.field_78808_h;
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.field_78093_q = false;
        this.field_78117_n = false;
        this.field_78118_o = false;
        this.field_78091_s = false;
        this.slim = false;
        this.field_78120_m = 0;
        this.field_78124_i.field_78808_h = 0.0f;
        this.field_78123_h.field_78808_h = 0.0f;
        this.field_78116_c.field_78808_h = 0.0f;
        this.field_78114_d.field_78808_h = 0.0f;
    }

    public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        if (this.npcSkinData != null) {
            this.field_78093_q = false;
            this.field_78117_n = false;
            this.field_78118_o = false;
            this.field_78091_s = false;
            this.slim = false;
            this.field_78120_m = 0;
            if (entity instanceof EntityLivingBase) {
                if (((EntityLivingBase)entity).func_70694_bm() != null) {
                    this.field_78120_m = 1;
                }
                if (((EntityLivingBase)entity).func_70115_ae()) {
                    this.field_78093_q = true;
                }
                if (((EntityLivingBase)entity).func_70093_af()) {
                    this.field_78117_n = true;
                }
                if (((EntityLivingBase)entity).func_70631_g_()) {
                    this.field_78091_s = true;
                }
            }
            this.field_78124_i.field_78808_h = 0.0f;
            this.field_78123_h.field_78808_h = 0.0f;
            this.field_78116_c.field_78808_h = 0.0f;
            this.field_78114_d.field_78808_h = 0.0f;
            super.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
            GL11.glPushAttrib((int)8192);
            GL11.glEnable((int)2884);
            ModRenderHelper.enableAlphaBlend();
            this.render(entity, this.npcSkinData, false, this.npcDyeData, null, false, 0.0, true);
            ModRenderHelper.disableAlphaBlend();
            GL11.glPopAttrib();
            this.npcSkinData = null;
            this.npcDyeData = null;
        }
    }

    @Override
    public void render(Entity entity, ModelBiped modelBiped, Skin armourData, boolean showSkinPaint, ISkinDye skinDye, byte[] extraColour, boolean itemRender, double distance, boolean doLodLoading) {
        this.setRotationFromModelBiped(modelBiped);
        this.render(entity, armourData, showSkinPaint, skinDye, extraColour, itemRender, distance, doLodLoading);
    }

    @Override
    public void render(Entity entity, Skin armourData, float limb1, float limb2, float limb3, float headY, float headX) {
        this.func_78087_a(limb1, limb2, limb3, headY, headX, SCALE, entity);
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)2884);
        ModRenderHelper.enableAlphaBlend();
        this.render(entity, armourData, false, null, null, false, 0.0, true);
        ModRenderHelper.disableAlphaBlend();
        GL11.glPopAttrib();
    }

    public abstract void render(Entity var1, Skin var2, boolean var3, ISkinDye var4, byte[] var5, boolean var6, double var7, boolean var9);

    protected void setRotationFromModelBiped(ModelBiped modelBiped) {
        this.field_78093_q = false;
        this.field_78117_n = false;
        this.field_78118_o = false;
        this.field_78120_m = 0;
        this.slim = false;
        if (modelBiped == null) {
            this.setRotation(this.field_78116_c, 0.0f, 0.0f, 0.0f);
            this.setRotation(this.field_78115_e, 0.0f, 0.0f, 0.0f);
            this.setRotation(this.field_78113_g, 0.0f, 0.0f, 0.0f);
            this.setRotation(this.field_78112_f, 0.0f, 0.0f, 0.0f);
            this.setRotation(this.field_78124_i, 0.0f, 0.0f, 0.0f);
            this.setRotation(this.field_78123_h, 0.0f, 0.0f, 0.0f);
            this.field_78091_s = false;
        } else {
            this.setRotation(this.field_78116_c, modelBiped.field_78116_c);
            this.setRotation(this.field_78115_e, modelBiped.field_78115_e);
            this.setRotation(this.field_78113_g, modelBiped.field_78113_g);
            this.setRotation(this.field_78112_f, modelBiped.field_78112_f);
            this.setRotation(this.field_78124_i, modelBiped.field_78124_i);
            this.setRotation(this.field_78123_h, modelBiped.field_78123_h);
            this.field_78091_s = modelBiped.field_78091_s;
            this.slim = modelBiped instanceof ModelMannequin ? ((ModelMannequin)modelBiped).isSlim() : false;
        }
    }

    protected void renderPart(SkinPartRenderData renderData) {
        SkinPartRenderer.INSTANCE.renderPart(renderData);
    }

    protected void renderPart(SkinPart skinPart, float scale, ISkinDye skinDye, byte[] extraColour, double distance, boolean doLodLoading) {
        SkinPartRenderer.INSTANCE.renderPart(new SkinPartRenderData(skinPart, scale, skinDye, extraColour, distance, doLodLoading, false, false, null));
    }
}

