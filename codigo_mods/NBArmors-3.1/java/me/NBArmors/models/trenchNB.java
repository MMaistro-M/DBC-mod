/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package me.NBArmors.models;

import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class trenchNB
extends ModelBipedBody {
    public int type = 0;
    public ModelRenderer skirt1 = new ModelRenderer((ModelBase)this, 56, 4);
    public ModelRenderer skirt2;
    public ModelRenderer skirt3;
    public ModelRenderer skirt4;
    public ModelRenderer skirt5;
    public ModelRenderer skirt6;
    public ModelRenderer skirt7;
    public ModelRenderer skirt8;
    public ModelRenderer skirt9;
    public ModelRenderer skirt10;
    public ModelRenderer skirt11;
    public ModelRenderer skirt12;
    public ModelRenderer skirt13;
    public ModelRenderer skirt14;
    public ModelRenderer skirt15;
    public ModelRenderer skirt16;
    private ModelRenderer field_78113_g1;
    public ModelRenderer bipedLeftArm1;
    double yPos = 0.56;
    double width = 0.27;
    double widthTop = 0.261;
    double zF = -0.131;
    double zB = 0.131;
    double xDou = 0.0;
    double yDou = -0.1f;
    double zDou = 0.25;
    private int alpha = 255;

    public trenchNB(float par1) {
        this(par1, 0.0f, 64, 32);
    }

    public trenchNB(float par1, float par2, int par3, int par4) {
        super(par1, 0.0f, 128, 64);
        this.skirt1.func_78790_a(-8.0f, 0.0f, -2.0f, 16, 24, 4, 0.58f);
        this.skirt1.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt1, -0.0872665f, 0.0f, 0.0f);
        this.skirt2 = new ModelRenderer((ModelBase)this, 0, 36);
        this.skirt2.func_78790_a(-8.0f, 0.0f, -2.0f, 16, 24, 4, 0.58f);
        this.skirt2.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt2, 0.0872665f, 0.0f, 0.0f);
        this.skirt3 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt3.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt3.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt3, -0.0698132f, 0.0f, 0.0f);
        this.skirt4 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt4.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt4.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt4, -0.0698132f, 0.0f, 0.0f);
        this.skirt5 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt5.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt5.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt5, 0.0698132f, 0.0174533f, 0.0f);
        this.skirt6 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt6.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt6.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt6, 0.0698132f, 0.0174533f, 0.0f);
        this.skirt7 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt7.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt7.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt7, -0.0698132f, 0.0f, 0.0f);
        this.skirt8 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt8.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt8.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt8, -0.0698132f, 0.0f, 0.0f);
        this.skirt9 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt9.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt9.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt9, -0.0698132f, 0.0f, 0.0f);
        this.skirt10 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt10.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt10.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt10, -0.0698132f, 0.0f, 0.0f);
        this.skirt11 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt11.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt11.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt11, -0.0698132f, 0.0f, 0.0f);
        this.skirt12 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt12.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt12.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt12, -0.0698132f, 0.0f, 0.0f);
        this.skirt13 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt13.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt13.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt13, -0.0698132f, 0.0f, 0.0f);
        this.skirt14 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt14.func_78790_a(-7.0f, 0.0f, -2.0f, 16, 24, 4, 0.4f);
        this.skirt14.func_78793_a(0.0f, 0.2f, 0.0f);
        this.setRotation(this.skirt14, -0.0698132f, 0.0f, 0.0f);
        this.skirt15 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt15.func_78790_a(-8.6f, 0.0f, -2.0f, 16, 24, 4, 0.5f);
        this.skirt15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt15, 0.0f, 0.0f, 0.0f);
        this.skirt16 = new ModelRenderer((ModelBase)this, 40, 36);
        this.skirt16.func_78790_a(-7.4f, 0.0f, -2.0f, 16, 24, 4, 0.5f);
        this.skirt16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt16, 0.0f, 0.0f, 0.0f);
        this.field_78113_g1 = new ModelRenderer((ModelBase)this, 112, 48);
        this.field_78113_g1.field_78809_i = true;
        this.field_78113_g1.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78113_g1.func_78793_a(5.0f, 2.0f + par2, 0.0f);
        this.field_78113_g = new ModelRenderer((ModelBase)this, 112, 48);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0f, -2.1f, -2.0f, 4, 12, 4, par1);
        this.field_78113_g.func_78793_a(5.0f, 2.0f + par1, 0.0f);
        this.Bleftarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bleftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f);
        this.Bleftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 112, 48);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.leftarm, 0.0f, 0.0f, -0.122173f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 96, 48);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par2, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 96, 48);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.body.func_78792_a(this.skirt1);
        this.field_78115_e.func_78792_a(this.skirt1);
        this.body.func_78792_a(this.skirt2);
        this.field_78115_e.func_78792_a(this.skirt2);
        this.body.func_78792_a(this.skirt3);
        this.field_78115_e.func_78792_a(this.skirt3);
        this.body.func_78792_a(this.skirt4);
        this.field_78115_e.func_78792_a(this.skirt4);
        this.body.func_78792_a(this.skirt5);
        this.field_78115_e.func_78792_a(this.skirt5);
        this.body.func_78792_a(this.skirt6);
        this.field_78115_e.func_78792_a(this.skirt6);
        this.body.func_78792_a(this.skirt7);
        this.field_78115_e.func_78792_a(this.skirt7);
        this.body.func_78792_a(this.skirt8);
        this.field_78115_e.func_78792_a(this.skirt8);
        this.body.func_78792_a(this.skirt9);
        this.field_78115_e.func_78792_a(this.skirt9);
        this.body.func_78792_a(this.skirt10);
        this.field_78115_e.func_78792_a(this.skirt10);
        this.body.func_78792_a(this.skirt11);
        this.field_78115_e.func_78792_a(this.skirt11);
        this.body.func_78792_a(this.skirt12);
        this.field_78115_e.func_78792_a(this.skirt12);
        this.body.func_78792_a(this.skirt13);
        this.field_78115_e.func_78792_a(this.skirt13);
        this.body.func_78792_a(this.skirt14);
        this.field_78115_e.func_78792_a(this.skirt14);
        this.body.func_78792_a(this.skirt15);
        this.field_78115_e.func_78792_a(this.skirt15);
        this.body.func_78792_a(this.skirt16);
        this.field_78115_e.func_78792_a(this.skirt16);
        this.Bleftarm.func_78792_a(this.leftarm);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationPub(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float s3;
        float s2;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        float s = 0.1070796f;
        float d = -0.1070796f;
        if (this.skirt1 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.24f * par2;
                this.skirt1.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt1.field_78795_f = s;
            }
        }
        if (this.skirt3 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.74f * par2;
                this.skirt3.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt3.field_78795_f = s;
            }
        }
        if (this.skirt5 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 2.8f * par2;
                this.skirt5.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt5.field_78795_f = s;
            }
        }
        if (this.skirt7 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 6.0f * par2;
                this.skirt7.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt7.field_78795_f = s;
            }
        }
        if (this.skirt9 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.74f * par2;
                this.skirt9.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt9.field_78795_f = s;
            }
        }
        if (this.skirt11 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 2.8f * par2;
                this.skirt11.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt11.field_78795_f = s;
            }
        }
        if (this.skirt13 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 0.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 6.0f * par2;
                this.skirt13.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt13.field_78795_f = s;
            }
        }
        if (this.skirt2 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.76f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt2.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt2.field_78795_f = s;
            }
        }
        if (this.skirt4 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.51f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt4.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt4.field_78795_f = s;
            }
        }
        if (this.skirt6 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.28f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt6.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt6.field_78795_f = s;
            }
        }
        if (this.skirt8 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.12f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt8.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt8.field_78795_f = s;
            }
        }
        if (this.skirt10 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.51f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt10.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt10.field_78795_f = s;
            }
        }
        if (this.skirt12 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.28f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt12.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt12.field_78795_f = s;
            }
        }
        if (this.skirt14 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.12f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.skirt14.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt14.field_78795_f = s;
            }
        }
    }
}

