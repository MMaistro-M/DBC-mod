/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import noppes.npcs.CustomItems;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.client.model.animation.AniCrawling;
import noppes.npcs.client.model.animation.AniHug;
import noppes.npcs.client.model.part.ModelBeard;
import noppes.npcs.client.model.part.ModelBodywear;
import noppes.npcs.client.model.part.ModelBreasts;
import noppes.npcs.client.model.part.ModelClaws;
import noppes.npcs.client.model.part.ModelEars;
import noppes.npcs.client.model.part.ModelFin;
import noppes.npcs.client.model.part.ModelHair;
import noppes.npcs.client.model.part.ModelHeadwear;
import noppes.npcs.client.model.part.ModelHorns;
import noppes.npcs.client.model.part.ModelLegs;
import noppes.npcs.client.model.part.ModelLimbWear;
import noppes.npcs.client.model.part.ModelMohawk;
import noppes.npcs.client.model.part.ModelSkirt;
import noppes.npcs.client.model.part.ModelSnout;
import noppes.npcs.client.model.part.ModelTail;
import noppes.npcs.client.model.part.ModelWings;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.client.model.util.ModelScaleRenderer;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;
import noppes.npcs.entity.data.ModelScalePart;
import org.lwjgl.opengl.GL11;

public class ModelMPM
extends ModelNPCMale {
    private ModelPartInterface wings;
    private ModelPartInterface mohawk;
    private ModelPartInterface hair;
    private ModelPartInterface beard;
    private ModelPartInterface breasts;
    private ModelPartInterface snout;
    private ModelPartInterface ears;
    private ModelPartInterface fin;
    private ModelPartInterface skirt;
    private ModelPartInterface horns;
    private ModelPartInterface clawsR;
    private ModelPartInterface clawsL;
    public ModelRenderer bipedBodywear;
    public ModelRenderer bipedRightArmWear;
    public ModelRenderer bipedLeftArmWear;
    public ModelRenderer bipedRightLegWear;
    public ModelRenderer bipedLeftLegWear;
    private ModelScaleRenderer headwear;
    private ModelScaleRenderer bodywear;
    private final ModelScaleRenderer solidLeftArmWear;
    private final ModelScaleRenderer solidRightArmWear;
    private final ModelScaleRenderer solidLeftLegWear;
    private final ModelScaleRenderer solidRightLegWear;
    public ModelLegs legs;
    public ModelTail tail;
    public ModelBase entityModel;
    public EntityLivingBase entity;
    public EntityCustomNpc npc;
    public boolean currentlyPlayerTexture;
    public boolean isArmor;
    public boolean isAlexArmor;
    public float alpha = 1.0f;

    public ModelMPM(float par1, boolean alex) {
        super(par1, alex);
        this.isArmor = par1 > 0.0f;
        float par2 = 0.0f;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78787_b(64, 32);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, par1);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, par1);
        this.field_78116_c = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78114_d = new ModelScaleRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78115_e = new ModelScaleRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedBodywear = new ModelScaleRenderer((ModelBase)this, 16, 32);
        this.bipedBodywear.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1 + 0.5f);
        this.field_78115_e.func_78792_a(this.bipedBodywear);
        this.bodywear = new ModelBodywear((ModelBase)this, 64, 64);
        this.field_78115_e.func_78792_a((ModelRenderer)this.bodywear);
        if (alex) {
            this.field_78112_f = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-2.0f, -2.0f, -2.0f, 3, 12, 4, par1);
            this.field_78113_g = new ModelScaleRenderer((ModelBase)this, 32, 48);
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 3, 12, 4, par1);
            this.field_78113_g.func_78793_a(5.0f, 2.5f + par2, 0.0f);
            this.bipedRightArmWear = new ModelScaleRenderer((ModelBase)this, 40, 32);
            this.bipedRightArmWear.func_78790_a(-2.0f, -2.0f, -2.0f, 3, 12, 4, par1 + 0.25f);
            this.field_78112_f.func_78792_a(this.bipedRightArmWear);
            this.solidRightArmWear = new ModelLimbWear((ModelBase)this, "arm", "right", "Alex");
            this.field_78112_f.func_78792_a((ModelRenderer)this.solidRightArmWear);
            this.bipedLeftArmWear = new ModelScaleRenderer((ModelBase)this, 48, 48);
            this.bipedLeftArmWear.func_78790_a(-1.0f, -2.0f, -2.0f, 3, 12, 4, par1 + 0.25f);
            this.field_78113_g.func_78792_a(this.bipedLeftArmWear);
            this.solidLeftArmWear = new ModelLimbWear((ModelBase)this, "arm", "left", "Alex");
            this.field_78113_g.func_78792_a((ModelRenderer)this.solidLeftArmWear);
        } else {
            this.field_78112_f = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f + par2, 0.0f);
            this.field_78113_g = new ModelScaleRenderer((ModelBase)this, 32, 48);
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
            this.field_78113_g.func_78793_a(5.0f, 2.0f + par2, 0.0f);
            this.bipedRightArmWear = new ModelScaleRenderer((ModelBase)this, 40, 32);
            this.bipedRightArmWear.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1 + 0.25f);
            this.field_78112_f.func_78792_a(this.bipedRightArmWear);
            this.solidRightArmWear = new ModelLimbWear((ModelBase)this, "arm", "right", "Steve");
            this.field_78112_f.func_78792_a((ModelRenderer)this.solidRightArmWear);
            this.bipedLeftArmWear = new ModelScaleRenderer((ModelBase)this, 48, 48);
            this.bipedLeftArmWear.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 + 0.25f);
            this.field_78113_g.func_78792_a(this.bipedLeftArmWear);
            this.solidLeftArmWear = new ModelLimbWear((ModelBase)this, "arm", "left", "Steve");
            this.field_78113_g.func_78792_a((ModelRenderer)this.solidLeftArmWear);
        }
        this.field_78123_h = new ModelScaleRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.08f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78123_h.func_78793_a(-1.9f, 12.0f + par2, 0.0f);
        this.field_78124_i = new ModelScaleRenderer((ModelBase)this, 16, 48);
        this.field_78124_i.func_78790_a(-1.92f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par2, 0.0f);
        this.bipedRightLegWear = new ModelScaleRenderer((ModelBase)this, 0, 32);
        this.bipedRightLegWear.func_78790_a(-2.08f, 0.0f, -2.0f, 4, 12, 4, par1 + 0.25f);
        this.field_78123_h.func_78792_a(this.bipedRightLegWear);
        this.solidRightLegWear = new ModelLimbWear((ModelBase)this, "leg", "right", "Steve");
        this.field_78123_h.func_78792_a((ModelRenderer)this.solidRightLegWear);
        this.bipedLeftLegWear = new ModelScaleRenderer((ModelBase)this, 0, 48);
        this.bipedLeftLegWear.func_78790_a(-1.92f, 0.0f, -2.0f, 4, 12, 4, par1 + 0.25f);
        this.field_78124_i.func_78792_a(this.bipedLeftLegWear);
        this.solidLeftLegWear = new ModelLimbWear((ModelBase)this, "leg", "left", "Steve");
        this.field_78124_i.func_78792_a((ModelRenderer)this.solidLeftLegWear);
        this.headwear = new ModelHeadwear((ModelBase)this);
        this.legs = new ModelLegs(this, (ModelScaleRenderer)this.field_78123_h, (ModelScaleRenderer)this.field_78124_i, 64, 64);
        this.breasts = new ModelBreasts(this, 64, 64);
        this.field_78115_e.func_78792_a((ModelRenderer)this.breasts);
        if (!this.isArmor) {
            this.ears = new ModelEars(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.ears);
            this.mohawk = new ModelMohawk(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.mohawk);
            this.hair = new ModelHair(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.hair);
            this.beard = new ModelBeard(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.beard);
            this.snout = new ModelSnout(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.snout);
            this.horns = new ModelHorns(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.horns);
            this.tail = new ModelTail(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.tail);
            this.wings = new ModelWings(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.wings);
            this.fin = new ModelFin(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.fin);
            this.skirt = new ModelSkirt(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.skirt);
            this.clawsL = new ModelClaws(this, false);
            this.field_78113_g.func_78792_a((ModelRenderer)this.clawsL);
            this.clawsR = new ModelClaws(this, true);
            this.field_78112_f.func_78792_a((ModelRenderer)this.clawsR);
        }
    }

    public ModelMPM(float par1, int alexArms) {
        super(par1);
        boolean bl = this.isArmor = par1 > 0.0f;
        if (this.isArmor && alexArms == 1) {
            this.isAlexArmor = true;
        }
        float par2 = 0.0f;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78787_b(64, 32);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, par1);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, par1);
        this.field_78116_c = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78114_d = new ModelScaleRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78115_e = new ModelScaleRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        if (alexArms == 0) {
            this.field_78112_f = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f + par2, 0.0f);
            this.field_78113_g = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78113_g.field_78809_i = true;
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
            this.field_78113_g.func_78793_a(5.0f, 2.0f + par2, 0.0f);
        } else {
            this.field_78112_f = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78113_g = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78113_g.field_78809_i = true;
            if (this.isArmor) {
                this.field_78113_g.func_78793_a(5.0f, 2.5f + par2, 0.0f);
                this.field_78112_f.func_78790_a(-4.5f, -2.0f, -2.0f, 4, 12, 4, par1);
                this.field_78113_g.func_78790_a(0.25f, -2.0f, -2.0f, 4, 12, 4, par1);
            } else {
                this.field_78113_g.func_78793_a(5.0f, 2.5f + par2, 0.0f);
                this.field_78112_f.func_78790_a(-2.0f, -2.0f, -2.0f, 3, 12, 4, par1);
                this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 3, 12, 4, par1);
            }
        }
        this.field_78123_h = new ModelScaleRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78123_h.func_78793_a(-1.9f, 12.0f + par2, 0.0f);
        this.field_78124_i = new ModelScaleRenderer((ModelBase)this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par2, 0.0f);
        this.bipedBodywear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78115_e.func_78792_a(this.bipedBodywear);
        this.bodywear = new ModelBodywear((ModelBase)this, 0, 0);
        this.field_78115_e.func_78792_a((ModelRenderer)this.bodywear);
        this.bipedRightArmWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78112_f.func_78792_a(this.bipedRightArmWear);
        this.solidRightArmWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78112_f.func_78792_a((ModelRenderer)this.solidRightArmWear);
        this.bipedLeftArmWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78113_g.func_78792_a(this.bipedLeftArmWear);
        this.solidLeftArmWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78113_g.func_78792_a((ModelRenderer)this.solidLeftArmWear);
        this.bipedRightLegWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78123_h.func_78792_a(this.bipedRightLegWear);
        this.solidRightLegWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78123_h.func_78792_a((ModelRenderer)this.solidRightLegWear);
        this.bipedLeftLegWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78124_i.func_78792_a(this.bipedLeftLegWear);
        this.solidLeftLegWear = new ModelScaleRenderer((ModelBase)this, 0, 0);
        this.field_78124_i.func_78792_a((ModelRenderer)this.solidLeftLegWear);
        this.headwear = new ModelHeadwear((ModelBase)this, true);
        this.legs = new ModelLegs(this, (ModelScaleRenderer)this.field_78123_h, (ModelScaleRenderer)this.field_78124_i, 64, 32);
        this.breasts = new ModelBreasts(this, 64, 32);
        this.field_78115_e.func_78792_a((ModelRenderer)this.breasts);
        if (!this.isArmor) {
            this.ears = new ModelEars(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.ears);
            this.mohawk = new ModelMohawk(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.mohawk);
            this.hair = new ModelHair(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.hair);
            this.beard = new ModelBeard(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.beard);
            this.snout = new ModelSnout(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.snout);
            this.horns = new ModelHorns(this);
            this.field_78116_c.func_78792_a((ModelRenderer)this.horns);
            this.tail = new ModelTail(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.tail);
            this.wings = new ModelWings(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.wings);
            this.fin = new ModelFin(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.fin);
            this.skirt = new ModelSkirt(this);
            this.field_78115_e.func_78792_a((ModelRenderer)this.skirt);
            this.clawsL = new ModelClaws(this, false);
            this.field_78113_g.func_78792_a((ModelRenderer)this.clawsL);
            this.clawsR = new ModelClaws(this, true);
            this.field_78112_f.func_78792_a((ModelRenderer)this.clawsR);
        }
    }

    private void setPlayerData(EntityCustomNpc entity) {
        if (!this.isArmor) {
            this.mohawk.setData(entity.modelData, entity);
            this.beard.setData(entity.modelData, entity);
            this.hair.setData(entity.modelData, entity);
            this.snout.setData(entity.modelData, entity);
            this.tail.setData(entity);
            this.fin.setData(entity.modelData, entity);
            this.wings.setData(entity.modelData, entity);
            this.ears.setData(entity.modelData, entity);
            this.clawsL.setData(entity.modelData, entity);
            this.clawsR.setData(entity.modelData, entity);
            this.skirt.setData(entity.modelData, entity);
            this.horns.setData(entity.modelData, entity);
        }
        this.breasts.setData(entity.modelData, entity);
        this.legs.setData(entity);
    }

    @Override
    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.npc = (EntityCustomNpc)par1Entity;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (this.npc.scriptInvisibleToPlayer((EntityPlayer)Minecraft.func_71410_x().field_71439_g) && (player.func_70694_bm() == null || player.func_70694_bm().func_77973_b() != CustomItems.wand)) {
            return;
        }
        if (this.entityModel != null) {
            GL11.glPushMatrix();
            AnimationData animationData = this.npc.display.animationData;
            if (animationData.isActive()) {
                Animation animation = animationData.animation;
                Frame frame = animation.frames.get(animation.currentFrame);
                if (frame.frameParts.containsKey((Object)EnumAnimationPart.FULL_MODEL)) {
                    FramePart part = frame.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
                    if (!this.isArmor) {
                        part.interpolateOffset();
                        part.interpolateAngles();
                    }
                    float pi = 57.295776f;
                    GL11.glTranslatef((float)part.prevPivots[0], (float)(-part.prevPivots[1]), (float)part.prevPivots[2]);
                    GL11.glRotatef((float)(part.prevRotations[0] * pi), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)(part.prevRotations[1] * pi), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(part.prevRotations[2] * pi), (float)0.0f, (float)0.0f, (float)1.0f);
                }
            }
            if (!this.isArmor) {
                this.entityModel.field_78091_s = this.entity.func_70631_g_();
                this.entityModel.field_78095_p = this.field_78095_p;
                this.entityModel.field_78093_q = this.field_78093_q;
                if (this.entityModel instanceof ModelBiped) {
                    ModelBiped biped = (ModelBiped)this.entityModel;
                    biped.field_78118_o = this.field_78118_o;
                    biped.field_78119_l = this.field_78119_l;
                    biped.field_78120_m = this.field_78120_m;
                    biped.field_78117_n = this.field_78117_n;
                }
                this.entityModel.func_78088_a((Entity)this.entity, par2, par3, par4, par5, par6, par7);
                if (!this.npc.display.skinOverlayData.overlayList.isEmpty()) {
                    for (ISkinOverlay overlayData : this.npc.display.skinOverlayData.overlayList.values()) {
                        try {
                            ImageData imageData;
                            if (((SkinOverlay)overlayData).texture.isEmpty() || !(imageData = ClientCacheHandler.getImageData(((SkinOverlay)overlayData).texture)).imageLoaded()) continue;
                            try {
                                imageData.renderEngineBind();
                            }
                            catch (Exception e) {
                                continue;
                            }
                            GL11.glEnable((int)3042);
                            if (overlayData.getBlend()) {
                                GL11.glBlendFunc((int)1, (int)1);
                            } else {
                                GL11.glBlendFunc((int)770, (int)771);
                            }
                            GL11.glAlphaFunc((int)516, (float)0.003921569f);
                            if (overlayData.getGlow()) {
                                GL11.glDisable((int)2896);
                                Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0);
                            }
                            ModelMPM.glColor(overlayData.getColor(), overlayData.getAlpha());
                            GL11.glDepthMask((!this.npc.func_82150_aj() ? 1 : 0) != 0);
                            GL11.glPushMatrix();
                            GL11.glMatrixMode((int)5890);
                            GL11.glLoadIdentity();
                            GL11.glTranslatef((float)((float)this.npc.display.overlayRenderTicks * 0.001f * overlayData.getSpeedX()), (float)((float)this.npc.display.overlayRenderTicks * 0.001f * overlayData.getSpeedY()), (float)0.0f);
                            GL11.glScalef((float)overlayData.getTextureScaleX(), (float)overlayData.getTextureScaleY(), (float)1.0f);
                            GL11.glMatrixMode((int)5888);
                            float scale = 1.005f * overlayData.getSize();
                            GL11.glTranslatef((float)overlayData.getOffsetX(), (float)overlayData.getOffsetY(), (float)overlayData.getOffsetZ());
                            GL11.glScalef((float)scale, (float)scale, (float)scale);
                            this.entityModel.func_78088_a((Entity)this.entity, par2, par3, par4, par5, par6, par7);
                            GL11.glPopMatrix();
                            GL11.glMatrixMode((int)5890);
                            GL11.glLoadIdentity();
                            GL11.glMatrixMode((int)5888);
                            GL11.glEnable((int)2896);
                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                            GL11.glDepthFunc((int)515);
                            GL11.glDisable((int)3042);
                            GL11.glAlphaFunc((int)516, (float)0.1f);
                            Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0);
                        }
                        catch (Exception exception) {}
                    }
                    ++this.npc.display.overlayRenderTicks;
                }
            }
            GL11.glPopMatrix();
        } else {
            this.alpha = this.npc.func_82150_aj() && !this.npc.func_98034_c((EntityPlayer)player) ? 0.15f : 1.0f;
            this.setPlayerData(this.npc);
            this.currentlyPlayerTexture = true;
            this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
            if (this.npc.modelData.enableRotation && !this.npc.display.animationData.isActive() && this.isRotationActive(this.npc)) {
                float pi = (float)Math.PI;
                if (!this.npc.modelData.rotation.head.disabled) {
                    this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f = this.npc.modelData.rotation.head.rotationX * pi;
                    this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g = this.npc.modelData.rotation.head.rotationY * pi;
                    this.field_78114_d.field_78808_h = this.field_78116_c.field_78808_h = this.npc.modelData.rotation.head.rotationZ * pi;
                }
                if (!this.npc.modelData.rotation.body.disabled) {
                    this.field_78115_e.field_78795_f = this.npc.modelData.rotation.body.rotationX * pi;
                    this.field_78115_e.field_78796_g = this.npc.modelData.rotation.body.rotationY * pi;
                    this.field_78115_e.field_78808_h = this.npc.modelData.rotation.body.rotationZ * pi;
                }
                if (!this.npc.modelData.rotation.larm.disabled) {
                    this.field_78113_g.field_78795_f = this.npc.modelData.rotation.larm.rotationX * pi;
                    this.field_78113_g.field_78796_g = this.npc.modelData.rotation.larm.rotationY * pi;
                    this.field_78113_g.field_78808_h = this.npc.modelData.rotation.larm.rotationZ * pi;
                    if (!this.npc.display.disableLivingAnimation) {
                        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
                        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
                    }
                }
                if (!this.npc.modelData.rotation.rarm.disabled) {
                    this.field_78112_f.field_78795_f = this.npc.modelData.rotation.rarm.rotationX * pi;
                    this.field_78112_f.field_78796_g = this.npc.modelData.rotation.rarm.rotationY * pi;
                    this.field_78112_f.field_78808_h = this.npc.modelData.rotation.rarm.rotationZ * pi;
                    if (!this.npc.display.disableLivingAnimation) {
                        this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
                        this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
                    }
                }
                if (!this.npc.modelData.rotation.rleg.disabled) {
                    this.field_78123_h.field_78795_f = this.npc.modelData.rotation.rleg.rotationX * pi;
                    this.field_78123_h.field_78796_g = this.npc.modelData.rotation.rleg.rotationY * pi;
                    this.field_78123_h.field_78808_h = this.npc.modelData.rotation.rleg.rotationZ * pi;
                }
                if (!this.npc.modelData.rotation.lleg.disabled) {
                    this.field_78124_i.field_78795_f = this.npc.modelData.rotation.lleg.rotationX * pi;
                    this.field_78124_i.field_78796_g = this.npc.modelData.rotation.lleg.rotationY * pi;
                    this.field_78124_i.field_78808_h = this.npc.modelData.rotation.lleg.rotationZ * pi;
                }
            }
            this.renderHead(this.npc, par7);
            this.renderArms(this.npc, par7, false);
            this.renderBody(this.npc, par7);
            this.renderLegs(this.npc, par7);
            this.renderCloak(this.npc, par7);
        }
    }

    private static void glColor(int color, float alpha) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
    }

    @Override
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        AnimationData animationData;
        EntityCustomNpc npc = (EntityCustomNpc)entity;
        this.field_78093_q = npc.func_70115_ae();
        if (this.field_78117_n && (npc.currentAnimation == EnumAnimation.CRAWLING || npc.currentAnimation == EnumAnimation.LYING)) {
            this.field_78117_n = false;
        }
        this.field_78115_e.field_78800_c = 0.0f;
        this.field_78115_e.field_78797_d = 0.0f;
        this.field_78115_e.field_78798_e = 0.0f;
        this.field_78115_e.field_78795_f = 0.0f;
        this.field_78115_e.field_78796_g = 0.0f;
        this.field_78115_e.field_78808_h = 0.0f;
        this.bipedBodywear.field_78800_c = 0.0f;
        this.bipedBodywear.field_78797_d = 0.0f;
        this.bipedBodywear.field_78798_e = 0.0f;
        this.bipedBodywear.field_78795_f = 0.0f;
        this.bipedBodywear.field_78796_g = 0.0f;
        this.bipedBodywear.field_78808_h = 0.0f;
        this.field_78116_c.field_78800_c = 0.0f;
        this.field_78116_c.field_78797_d = 0.0f;
        this.field_78116_c.field_78798_e = 0.0f;
        this.field_78116_c.field_78795_f = 0.0f;
        this.field_78116_c.field_78796_g = 0.0f;
        this.field_78116_c.field_78808_h = 0.0f;
        this.field_78114_d.field_78800_c = 0.0f;
        this.field_78114_d.field_78797_d = 0.0f;
        this.field_78114_d.field_78798_e = 0.0f;
        this.field_78114_d.field_78795_f = 0.0f;
        this.field_78114_d.field_78796_g = 0.0f;
        this.field_78114_d.field_78808_h = 0.0f;
        this.field_78124_i.field_78800_c = 1.9f;
        this.field_78124_i.field_78797_d = 12.0f;
        this.field_78124_i.field_78798_e = 0.0f;
        this.field_78124_i.field_78795_f = 0.0f;
        this.field_78124_i.field_78796_g = 0.0f;
        this.field_78124_i.field_78808_h = 0.0f;
        this.bipedLeftLegWear.field_78800_c = 0.0f;
        this.bipedLeftLegWear.field_78797_d = 0.0f;
        this.bipedLeftLegWear.field_78798_e = 0.0f;
        this.bipedLeftLegWear.field_78795_f = 0.0f;
        this.bipedLeftLegWear.field_78796_g = 0.0f;
        this.bipedLeftLegWear.field_78808_h = 0.0f;
        this.field_78123_h.field_78800_c = -1.9f;
        this.field_78123_h.field_78797_d = 12.0f;
        this.field_78123_h.field_78798_e = 0.0f;
        this.field_78123_h.field_78795_f = 0.0f;
        this.field_78123_h.field_78796_g = 0.0f;
        this.field_78123_h.field_78808_h = 0.0f;
        this.bipedRightLegWear.field_78800_c = 0.0f;
        this.bipedRightLegWear.field_78797_d = 0.0f;
        this.bipedRightLegWear.field_78798_e = 0.0f;
        this.bipedRightLegWear.field_78795_f = 0.0f;
        this.bipedRightLegWear.field_78796_g = 0.0f;
        this.bipedRightLegWear.field_78808_h = 0.0f;
        this.field_78113_g.field_78800_c = 5.0f;
        this.field_78113_g.field_78797_d = 2.0f;
        this.field_78113_g.field_78798_e = 0.0f;
        this.field_78113_g.field_78795_f = 0.0f;
        this.field_78113_g.field_78796_g = 0.0f;
        this.field_78113_g.field_78808_h = 0.0f;
        this.bipedLeftArmWear.field_78800_c = 0.0f;
        this.bipedLeftArmWear.field_78797_d = 0.0f;
        this.bipedLeftArmWear.field_78798_e = 0.0f;
        this.bipedLeftArmWear.field_78795_f = 0.0f;
        this.bipedLeftArmWear.field_78796_g = 0.0f;
        this.bipedLeftArmWear.field_78808_h = 0.0f;
        this.field_78112_f.field_78800_c = -5.0f;
        this.field_78112_f.field_78797_d = 2.0f;
        this.field_78112_f.field_78798_e = 0.0f;
        this.field_78112_f.field_78795_f = 0.0f;
        this.field_78112_f.field_78796_g = 0.0f;
        this.field_78112_f.field_78808_h = 0.0f;
        this.bipedRightArmWear.field_78800_c = 0.0f;
        this.bipedRightArmWear.field_78797_d = 0.0f;
        this.bipedRightArmWear.field_78798_e = 0.0f;
        this.bipedRightArmWear.field_78795_f = 0.0f;
        this.bipedRightArmWear.field_78796_g = 0.0f;
        this.bipedRightArmWear.field_78808_h = 0.0f;
        super.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        if (!this.isArmor) {
            this.hair.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
            this.beard.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
            this.wings.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
            this.tail.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
            this.skirt.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        }
        this.legs.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        if (this.isSleeping(entity)) {
            if (this.field_78116_c.field_78795_f < 0.0f) {
                this.field_78116_c.field_78795_f = 0.0f;
                this.field_78114_d.field_78795_f = 0.0f;
            }
        } else if (npc.currentAnimation == EnumAnimation.CRY) {
            this.field_78116_c.field_78795_f = 0.7f;
            this.field_78114_d.field_78795_f = 0.7f;
        } else if (npc.currentAnimation == EnumAnimation.HUG) {
            AniHug.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity, this);
        } else if (npc.currentAnimation == EnumAnimation.CRAWLING) {
            AniCrawling.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity, this);
        } else if (npc.currentAnimation == EnumAnimation.WAVING) {
            this.field_78112_f.field_78795_f = -0.1f;
            this.field_78112_f.field_78796_g = 0.0f;
            this.field_78112_f.field_78808_h = (float)(2.141592653589793 - Math.sin((float)entity.field_70173_aa * 0.27f) * 0.5);
        } else if (this.field_78117_n) {
            this.field_78115_e.field_78795_f = 0.5f / npc.modelData.modelScale.body.scaleY;
        }
        if ((animationData = npc.display.animationData).isActive()) {
            ModelRenderer modelRenderer;
            FramePart[] frameParts;
            Animation animation = animationData.animation;
            Frame frame = animation.frames.get(animation.currentFrame);
            HashMap<EnumAnimationPart, ModelRenderer> animPartToModel = new HashMap<EnumAnimationPart, ModelRenderer>();
            animPartToModel.put(EnumAnimationPart.HEAD, this.field_78116_c);
            animPartToModel.put(EnumAnimationPart.BODY, this.field_78115_e);
            animPartToModel.put(EnumAnimationPart.LEFT_ARM, this.field_78113_g);
            animPartToModel.put(EnumAnimationPart.RIGHT_ARM, this.field_78112_f);
            animPartToModel.put(EnumAnimationPart.LEFT_LEG, this.field_78124_i);
            animPartToModel.put(EnumAnimationPart.RIGHT_LEG, this.field_78123_h);
            for (FramePart part : frameParts = frame.frameParts.values().toArray(new FramePart[0])) {
                if (part == null) continue;
                if (part.part != EnumAnimationPart.FULL_MODEL) {
                    modelRenderer = (ModelRenderer)animPartToModel.get((Object)part.part);
                    if (!this.isArmor) {
                        part.interpolateAngles();
                    }
                    modelRenderer.field_78795_f = part.prevRotations[0];
                    modelRenderer.field_78796_g = part.prevRotations[1];
                    modelRenderer.field_78808_h = part.prevRotations[2];
                    continue;
                }
                if (this.isArmor) continue;
                part.interpolateAngles();
            }
            for (FramePart part : frameParts = frame.frameParts.values().toArray(new FramePart[0])) {
                if (part == null) continue;
                if (part.part != EnumAnimationPart.FULL_MODEL) {
                    modelRenderer = (ModelRenderer)animPartToModel.get((Object)part.part);
                    if (!this.isArmor) {
                        part.interpolateOffset();
                    }
                    modelRenderer.field_78800_c += part.prevPivots[0];
                    modelRenderer.field_78797_d += part.prevPivots[1];
                    modelRenderer.field_78798_e += part.prevPivots[2];
                    continue;
                }
                if (this.isArmor) continue;
                part.interpolateOffset();
            }
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.FULL_MODEL) && !this.isArmor) {
                FramePart part = frame.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
                if (!this.isArmor) {
                    part.interpolateOffset();
                    part.interpolateAngles();
                }
                float pi = 57.295776f;
                GL11.glTranslatef((float)part.prevPivots[0], (float)(-part.prevPivots[1]), (float)part.prevPivots[2]);
                GL11.glRotatef((float)(part.prevRotations[0] * pi), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(part.prevRotations[1] * pi), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(part.prevRotations[2] * pi), (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }

    @Override
    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        if (this.entityModel != null) {
            this.entityModel.func_78086_a(this.entity, par2, par3, par4);
        } else {
            ModelPartData partData;
            EntityCustomNpc npc = (EntityCustomNpc)par1EntityLivingBase;
            if (!this.isArmor && (partData = npc.modelData.getPartData("tail")) != null) {
                this.tail.setLivingAnimations(partData, par1EntityLivingBase, par2, par3, par4);
            }
        }
    }

    public void loadPlayerTexture(EntityCustomNpc npc) {
        if (!this.isArmor && !this.currentlyPlayerTexture) {
            ClientProxy.bindTexture(npc.textureLocation);
            this.currentlyPlayerTexture = true;
        }
    }

    public void copyAnglesPivots(ModelRenderer to, ModelRenderer from) {
        if (to == null || from == null) {
            return;
        }
        to.field_78795_f = from.field_78795_f;
        to.field_78796_g = from.field_78796_g;
        to.field_78808_h = from.field_78808_h;
        to.field_78800_c = from.field_78800_c;
        to.field_78797_d = from.field_78797_d;
        to.field_78798_e = from.field_78798_e;
    }

    public void renderHead(EntityCustomNpc entity, float f) {
        this.loadPlayerTexture(entity);
        float x = 0.0f;
        float y = entity.modelData.getBodyY();
        float z = 0.0f;
        GL11.glPushMatrix();
        if (entity.currentAnimation == EnumAnimation.DANCING) {
            float dancing = (float)entity.field_70173_aa / 4.0f;
            GL11.glTranslatef((float)((float)Math.sin(dancing) * 0.075f), (float)((float)Math.abs(Math.cos(dancing)) * 0.125f - 0.02f), (float)((float)(-Math.abs(Math.cos(dancing))) * 0.075f));
        }
        ModelScalePart head = entity.modelData.modelScale.head;
        this.copyAnglesPivots(this.headwear, this.field_78116_c);
        this.copyAnglesPivots(this.field_78114_d, this.field_78116_c);
        boolean bl = ((ModelScaleRenderer)this.field_78116_c).field_78807_k = entity.modelData.hideHead == 1;
        if (this.field_78114_d.field_78806_j && !this.field_78114_d.field_78807_k) {
            if (entity.modelData.headwear == 1 || this.isArmor) {
                ((ModelScaleRenderer)this.field_78114_d).setConfig(head, x, y, z);
                ((ModelScaleRenderer)this.field_78114_d).func_78785_a(f);
            } else if (entity.modelData.headwear == 2) {
                this.headwear.setConfig(head, x, y, z);
                this.headwear.func_78785_a(f);
            }
        }
        ((ModelScaleRenderer)this.field_78116_c).setConfig(head, x, y, z);
        ((ModelScaleRenderer)this.field_78116_c).func_78785_a(f);
        GL11.glPopMatrix();
    }

    public void renderBody(EntityCustomNpc entity, float f) {
        this.loadPlayerTexture(entity);
        float x = 0.0f;
        float y = entity.modelData.getBodyY();
        float z = 0.0f;
        GL11.glPushMatrix();
        if (entity.currentAnimation == EnumAnimation.DANCING) {
            float dancing = (float)entity.field_70173_aa / 4.0f;
            GL11.glTranslatef((float)((float)Math.sin(dancing) * 0.015f), (float)0.0f, (float)0.0f);
        }
        ModelScalePart body = entity.modelData.modelScale.body;
        ((ModelScaleRenderer)this.field_78115_e).field_78807_k = entity.modelData.hideBody == 1;
        this.bipedBodywear.field_78807_k = entity.modelData.bodywear != 1;
        this.bodywear.field_78807_k = entity.modelData.bodywear != 2;
        ((ModelScaleRenderer)this.field_78115_e).setConfig(body, x, y, z);
        ((ModelScaleRenderer)this.field_78115_e).func_78785_a(f);
        GL11.glPopMatrix();
    }

    public void renderArms(EntityCustomNpc entity, float f, boolean bo) {
        ModelScalePart arms = entity.modelData.modelScale.arms;
        float x = (1.0f - entity.modelData.modelScale.body.scaleX) * 0.25f + (1.0f - arms.scaleX) * 0.075f;
        float y = entity.modelData.getBodyY() + (1.0f - arms.scaleY) * -0.1f;
        float z = 0.0f;
        GL11.glPushMatrix();
        if (this.isAlexArmor) {
            GL11.glScalef((float)0.75f, (float)1.0f, (float)1.0f);
        }
        if (entity.currentAnimation == EnumAnimation.DANCING) {
            float dancing = (float)entity.field_70173_aa / 4.0f;
            GL11.glTranslatef((float)((float)Math.sin(dancing) * 0.025f), (float)((float)Math.abs(Math.cos(dancing)) * 0.125f - 0.02f), (float)0.0f);
        }
        if (entity.modelData.hideArms == 1) {
            ((ModelScaleRenderer)this.field_78112_f).field_78807_k = true;
            ((ModelScaleRenderer)this.field_78113_g).field_78807_k = true;
        } else if (entity.modelData.hideArms == 2) {
            ((ModelScaleRenderer)this.field_78112_f).field_78807_k = true;
            ((ModelScaleRenderer)this.field_78113_g).field_78807_k = false;
        } else if (entity.modelData.hideArms == 3) {
            ((ModelScaleRenderer)this.field_78112_f).field_78807_k = false;
            ((ModelScaleRenderer)this.field_78113_g).field_78807_k = true;
        } else {
            ((ModelScaleRenderer)this.field_78112_f).field_78807_k = false;
            ((ModelScaleRenderer)this.field_78113_g).field_78807_k = false;
        }
        if (entity.modelData.armwear == 1) {
            ((ModelScaleRenderer)this.bipedRightArmWear).field_78807_k = entity.modelData.solidArmwear == 1 || entity.modelData.solidArmwear == 3;
            ((ModelScaleRenderer)this.bipedLeftArmWear).field_78807_k = entity.modelData.solidArmwear == 1 || entity.modelData.solidArmwear == 2;
            this.solidRightArmWear.field_78807_k = entity.modelData.solidArmwear == 0 || entity.modelData.solidArmwear == 2;
            this.solidLeftArmWear.field_78807_k = entity.modelData.solidArmwear == 0 || entity.modelData.solidArmwear == 3;
        } else if (entity.modelData.armwear == 2) {
            ((ModelScaleRenderer)this.bipedRightArmWear).field_78807_k = true;
            ((ModelScaleRenderer)this.bipedLeftArmWear).field_78807_k = entity.modelData.solidArmwear == 1 || entity.modelData.solidArmwear == 2;
            this.solidRightArmWear.field_78807_k = true;
            this.solidLeftArmWear.field_78807_k = entity.modelData.solidArmwear == 0 || entity.modelData.solidArmwear == 3;
        } else if (entity.modelData.armwear == 3) {
            ((ModelScaleRenderer)this.bipedRightArmWear).field_78807_k = entity.modelData.solidArmwear == 1 || entity.modelData.solidArmwear == 3;
            ((ModelScaleRenderer)this.bipedLeftArmWear).field_78807_k = true;
            this.solidRightArmWear.field_78807_k = entity.modelData.solidArmwear == 0 || entity.modelData.solidArmwear == 2;
            this.solidLeftArmWear.field_78807_k = true;
        } else {
            ((ModelScaleRenderer)this.bipedRightArmWear).field_78807_k = true;
            ((ModelScaleRenderer)this.bipedLeftArmWear).field_78807_k = true;
            this.solidRightArmWear.field_78807_k = true;
            this.solidLeftArmWear.field_78807_k = true;
        }
        this.loadPlayerTexture(entity);
        if (!bo) {
            ((ModelScaleRenderer)this.field_78113_g).setConfig(arms, -x, y, z);
            ((ModelScaleRenderer)this.field_78113_g).func_78785_a(f);
            this.loadPlayerTexture(entity);
            ((ModelScaleRenderer)this.field_78112_f).setConfig(arms, x, y, z);
            ((ModelScaleRenderer)this.field_78112_f).func_78785_a(f);
        } else {
            ((ModelScaleRenderer)this.field_78112_f).setConfig(arms, 0.0f, 0.0f, 0.0f);
            ((ModelScaleRenderer)this.field_78112_f).func_78785_a(f);
        }
        GL11.glPopMatrix();
    }

    public void renderLegs(EntityCustomNpc entity, float f) {
        this.loadPlayerTexture(entity);
        ModelScalePart legs = entity.modelData.modelScale.legs;
        float x = (1.0f - legs.scaleX) * 0.125f;
        float y = entity.modelData.getLegsY();
        float z = 0.0f;
        GL11.glPushMatrix();
        if (entity.modelData.hideLegs == 1) {
            ((ModelScaleRenderer)this.field_78123_h).field_78807_k = true;
            ((ModelScaleRenderer)this.field_78124_i).field_78807_k = true;
        } else if (entity.modelData.hideLegs == 2) {
            ((ModelScaleRenderer)this.field_78123_h).field_78807_k = true;
            ((ModelScaleRenderer)this.field_78124_i).field_78807_k = false;
        } else if (entity.modelData.hideLegs == 3) {
            ((ModelScaleRenderer)this.field_78123_h).field_78807_k = false;
            ((ModelScaleRenderer)this.field_78124_i).field_78807_k = true;
        } else {
            ((ModelScaleRenderer)this.field_78123_h).field_78807_k = false;
            ((ModelScaleRenderer)this.field_78124_i).field_78807_k = false;
        }
        if (entity.modelData.legwear == 1) {
            ((ModelScaleRenderer)this.bipedRightLegWear).field_78807_k = entity.modelData.solidLegwear == 1 || entity.modelData.solidLegwear == 3;
            ((ModelScaleRenderer)this.bipedLeftLegWear).field_78807_k = entity.modelData.solidLegwear == 1 || entity.modelData.solidLegwear == 2;
            this.solidRightLegWear.field_78807_k = entity.modelData.solidLegwear == 0 || entity.modelData.solidLegwear == 2;
            this.solidLeftLegWear.field_78807_k = entity.modelData.solidLegwear == 0 || entity.modelData.solidLegwear == 3;
        } else if (entity.modelData.legwear == 2) {
            ((ModelScaleRenderer)this.bipedRightLegWear).field_78807_k = true;
            ((ModelScaleRenderer)this.bipedLeftLegWear).field_78807_k = entity.modelData.solidLegwear == 1 || entity.modelData.solidLegwear == 2;
            this.solidRightLegWear.field_78807_k = true;
            this.solidLeftLegWear.field_78807_k = entity.modelData.solidLegwear == 0 || entity.modelData.solidLegwear == 3;
        } else if (entity.modelData.legwear == 3) {
            ((ModelScaleRenderer)this.bipedRightLegWear).field_78807_k = entity.modelData.solidLegwear == 1 || entity.modelData.solidLegwear == 3;
            ((ModelScaleRenderer)this.bipedLeftLegWear).field_78807_k = true;
            this.solidRightLegWear.field_78807_k = entity.modelData.solidLegwear == 0 || entity.modelData.solidLegwear == 2;
            this.solidLeftLegWear.field_78807_k = true;
        } else {
            ((ModelScaleRenderer)this.bipedRightLegWear).field_78807_k = true;
            ((ModelScaleRenderer)this.bipedLeftLegWear).field_78807_k = true;
            this.solidRightLegWear.field_78807_k = true;
            this.solidLeftLegWear.field_78807_k = true;
        }
        this.legs.setConfig(legs, x, y, z);
        this.legs.func_78785_a(f);
        if (!this.isArmor) {
            this.tail.setConfig(legs, 0.0f, y, z);
        }
        GL11.glPopMatrix();
    }

    public void renderCloak(EntityCustomNpc npc, float f) {
        if (!npc.display.cloakTexture.isEmpty() && !this.isArmor) {
            ImageData imageData = ClientCacheHandler.getImageData(npc.display.cloakTexture);
            if (!imageData.imageLoaded()) {
                return;
            }
            try {
                imageData.bindTexture();
            }
            catch (Exception e) {
                return;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
            double d = npc.field_20066_r + (npc.field_20063_u - npc.field_20066_r) * (double)f - (npc.field_70169_q + (npc.field_70165_t - npc.field_70169_q) * (double)f);
            double d1 = npc.field_20065_s + (npc.field_20062_v - npc.field_20065_s) * (double)f - (npc.field_70167_r + (npc.field_70163_u - npc.field_70167_r) * (double)f);
            double d2 = npc.field_20064_t + (npc.field_20061_w - npc.field_20064_t) * (double)f - (npc.field_70166_s + (npc.field_70161_v - npc.field_70166_s) * (double)f);
            float f11 = npc.field_70760_ar + (npc.field_70761_aq - npc.field_70760_ar) * f;
            double d3 = MathHelper.func_76126_a((float)(f11 * 3.141593f / 180.0f));
            double d4 = -MathHelper.func_76134_b((float)(f11 * 3.141593f / 180.0f));
            float f14 = (float)(d * d3 + d2 * d4) * 100.0f;
            float f15 = (float)(d * d4 - d2 * d3) * 100.0f;
            if (f14 < 0.0f) {
                f14 = 0.0f;
            }
            float f16 = npc.field_70126_B + (npc.field_70177_z - npc.field_70126_B) * f;
            float f13 = 5.0f;
            if (npc.func_70093_af()) {
                f13 += 25.0f;
            }
            GL11.glRotatef((float)(6.0f + f14 / 2.0f + f13), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(f15 / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(-f15 / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            super.func_78111_c(0.0625f);
            GL11.glPopMatrix();
        }
    }

    public ModelRenderer func_85181_a(Random par1Random) {
        int random = par1Random.nextInt(5);
        switch (random) {
            case 0: {
                return this.field_78123_h;
            }
            case 1: {
                return this.field_78116_c;
            }
            case 2: {
                return this.field_78113_g;
            }
            case 3: {
                return this.field_78112_f;
            }
            case 4: {
                return this.field_78124_i;
            }
        }
        return this.field_78115_e;
    }

    public boolean isRotationActive(EntityCustomNpc npc) {
        if (!npc.func_70089_S()) {
            return false;
        }
        return npc.modelData.rotation.whileAttacking && npc.isAttacking() || npc.modelData.rotation.whileMoving && npc.isWalking() || npc.modelData.rotation.whileStanding && !npc.isWalking();
    }
}

