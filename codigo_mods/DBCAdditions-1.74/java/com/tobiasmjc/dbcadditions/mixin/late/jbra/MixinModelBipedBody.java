/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late.jbra;

import JinRyuu.JRMCore.entity.ModelBipedBody;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ModelBipedBody.class}, remap=false)
public class MixinModelBipedBody
extends ModelBiped {
    public ModelRenderer biohead;
    public ModelRenderer biohead1;
    public ModelRenderer biohead2;
    public ModelRenderer wing;
    public ModelRenderer wing2;
    public ModelRenderer BioT;
    @Unique
    public ModelRenderer btailS1;
    @Unique
    public ModelRenderer btailS2;
    @Unique
    public ModelRenderer btailS3;
    @Unique
    public ModelRenderer btailS4;
    @Unique
    public ModelRenderer btailS5;
    @Unique
    public ModelRenderer btailS6;
    @Unique
    public ModelRenderer BioTM;
    @Unique
    public ModelRenderer btailS1M;
    @Unique
    public ModelRenderer btailS2M;
    @Unique
    public ModelRenderer btailS3M;
    @Unique
    public ModelRenderer btailS4M;
    @Unique
    public ModelRenderer btailS5M;
    @Unique
    public ModelRenderer btailS6M;
    public boolean isAbsorbing;

    @Inject(method={"<init>(FFII)V"}, at={@At(value="TAIL")})
    private void constructor(float par1, float par2, int par3, int par4, CallbackInfo ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        this.wing = new ModelRenderer((ModelBase)this, 0, 0);
        this.wing.func_78789_a(-1.0f, 2.0f, 2.0f, 7, 20, 1);
        this.wing.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.wing, 0.1570796f, 0.0349066f, -0.2792527f);
        this.wing2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.wing2.field_78809_i = true;
        this.wing2.func_78789_a(-6.0f, 2.0f, 2.0f, 7, 20, 1);
        this.wing2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.wing2, 0.1570796f, -0.0349066f, 0.2792527f);
        this.biohead = new ModelRenderer((ModelBase)this, 0, 0);
        this.biohead.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.biohead.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.biohead1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.biohead1.func_78789_a(-2.5f, -14.0f, -3.5f, 3, 7, 7);
        this.biohead1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.biohead1, 0.0f, 0.0f, -0.2094395f);
        this.biohead2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.biohead2.field_78809_i = true;
        this.biohead2.func_78789_a(-0.5f, -14.0f, -3.5f, 3, 7, 7);
        this.biohead2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.biohead2, 0.0f, 0.0f, 0.2094395f);
        this.BioTM = new ModelRenderer((ModelBase)this);
        this.BioTM.func_78793_a(0.0f, 5.0f, 2.0f);
        this.btailS1M = new ModelRenderer((ModelBase)this);
        this.btailS1M.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BioTM.func_78792_a(this.btailS1M);
        this.btailS1M.field_78804_l.add(new ModelBox(this.btailS1M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS2M = new ModelRenderer((ModelBase)this);
        this.btailS2M.func_78793_a(0.0f, 0.0f, 5.0f);
        this.btailS1M.func_78792_a(this.btailS2M);
        this.btailS2M.field_78804_l.add(new ModelBox(this.btailS2M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS3M = new ModelRenderer((ModelBase)this);
        this.btailS3M.func_78793_a(0.0f, 0.0f, 5.0f);
        this.btailS2M.func_78792_a(this.btailS3M);
        this.btailS3M.field_78804_l.add(new ModelBox(this.btailS3M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS4M = new ModelRenderer((ModelBase)this);
        this.btailS4M.func_78793_a(0.0f, 0.0f, 5.0f);
        this.btailS3M.func_78792_a(this.btailS4M);
        this.btailS4M.field_78804_l.add(new ModelBox(this.btailS4M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS5M = new ModelRenderer((ModelBase)this);
        this.btailS5M.func_78793_a(0.0f, 0.0f, 5.0f);
        this.btailS4M.func_78792_a(this.btailS5M);
        this.btailS5M.field_78804_l.add(new ModelBox(this.btailS5M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS6M = new ModelRenderer((ModelBase)this, 0, 0);
        this.btailS6M.func_78793_a(0.0f, 0.0f, 5.0f);
        this.btailS5M.func_78792_a(this.btailS6M);
        this.btailS6M.field_78804_l.add(new ModelBox(this.btailS6M, 0, 0, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.btailS6M.field_78804_l.add(new ModelBox(this.btailS6M, 20, 16, -3.5f, -3.5f, 6.0f, 7, 7, 6, 0.0f));
        this.BioT = new ModelRenderer((ModelBase)this);
        this.BioT.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 12, 0, 0.02f);
        this.BioT.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.btailS1 = new ModelRenderer((ModelBase)this);
        this.btailS1.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.btailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.btailS1, -0.5235988f, 0.0f, 0.0f);
        this.btailS2 = new ModelRenderer((ModelBase)this);
        this.btailS2.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.btailS2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.btailS2, 0.5235988f, 8.727E-4f, 0.0f);
        this.btailS3 = new ModelRenderer((ModelBase)this);
        this.btailS3.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.btailS3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.btailS3, 0.0f, 0.0f, 0.0f);
        this.btailS4 = new ModelRenderer((ModelBase)this);
        this.btailS4.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.btailS4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.btailS4, 0.0f, 0.0f, 0.0f);
        this.btailS5 = new ModelRenderer((ModelBase)this);
        this.btailS5.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.btailS5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.btailS5, 0.0f, 0.0f, 0.0f);
        this.btailS6 = new ModelRenderer((ModelBase)this, 44, 16);
        this.btailS6.func_78789_a(-1.0f, -1.5f, -0.5f, 2, 2, 6);
        this.setRotation(this.btailS6, 0.0f, 0.0f, 0.0f);
        this.biohead.func_78792_a(this.biohead1);
        this.biohead.func_78792_a(this.biohead2);
        this.BioT.func_78792_a(this.btailS1);
        this.BioT.field_78800_c = 2.0f;
        this.BioT.field_78797_d = 10.0f;
        this.BioT.field_78798_e = 2.0f;
        this.btailS5.func_78792_a(this.btailS6);
        this.btailS4.func_78792_a(this.btailS5);
        this.btailS3.func_78792_a(this.btailS4);
        this.btailS2.func_78792_a(this.btailS3);
        this.btailS1.func_78792_a(this.btailS2);
        this.BioT.field_78800_c = 2.0f;
        this.BioT.field_78797_d = 10.0f;
        this.BioT.field_78798_e = 2.0f;
        this.btailS1.field_78800_c = -2.0f;
        this.btailS1.field_78797_d = -2.0f;
        this.btailS1.field_78798_e = 0.0f;
        this.btailS2.field_78800_c = 0.0f;
        this.btailS2.field_78797_d = 0.0f;
        this.btailS2.field_78798_e = 5.0f;
        this.btailS3.field_78800_c = 0.0f;
        this.btailS3.field_78797_d = 0.0f;
        this.btailS3.field_78798_e = 5.0f;
        this.btailS4.field_78800_c = 0.0f;
        this.btailS4.field_78797_d = 0.0f;
        this.btailS4.field_78798_e = 5.0f;
        this.btailS5.field_78800_c = 0.0f;
        this.btailS5.field_78797_d = 0.0f;
        this.btailS5.field_78798_e = 5.0f;
        this.btailS6.field_78800_c = 0.0f;
        this.btailS6.field_78797_d = 0.0f;
        this.btailS6.field_78798_e = 4.0f;
    }

    private void absorbingAnim(Entity entity) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer p = (EntityPlayer)entity;
        if (p.field_70737_aN > 0 && p != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        this.isAbsorbing = p != Minecraft.func_71410_x().field_71439_g || p == Minecraft.func_71410_x().field_71439_g && Minecraft.func_71410_x().field_71474_y.field_74320_O != 0 ? DBCAAbilities.isAbsorbing(p) : false;
        byte formID = DataUtils.getDBCAState(p);
        if (this.isAbsorbing && (formID <= 0 || formID == DBCAForms.SemiPerfect.getID())) {
            this.btailS1.field_78796_g = 0.85f;
            this.btailS1.field_78795_f = 0.5f;
            this.btailS2.field_78796_g = 0.9f;
            this.btailS2.field_78795_f = 0.4f;
            this.btailS3.field_78796_g = 0.1f;
            this.btailS3.field_78795_f = 0.8f;
            this.btailS4.field_78796_g = 0.85f;
            this.btailS4.field_78795_f = 0.3f;
            this.btailS5.field_78796_g = 0.72f;
            this.btailS5.field_78795_f = -0.2f;
            this.btailS6.field_78796_g = 0.75f;
            this.btailS6.field_78795_f = 0.4f;
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    @Inject(method={"setRotationAngles"}, at={@At(value="HEAD")}, remap=true)
    private void injectRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity, CallbackInfo ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        this.absorbingAnim(par7Entity);
    }
}

