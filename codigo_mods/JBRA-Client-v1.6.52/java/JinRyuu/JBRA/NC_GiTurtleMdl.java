/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JBRA;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class NC_GiTurtleMdl
extends ModelBipedBody {
    private final int VANITY_KONOHAMARU_SCARF = 0;
    private final int VANITY_NARUTO_GOGGLES = 1;
    public int id = -1;
    public ModelRenderer Base1;
    public ModelRenderer Base2;
    public ModelRenderer ScarfTale1;
    public ModelRenderer ScarfTale2;
    public ModelRenderer ScarfTale3;
    public ModelRenderer ScarfTale4;
    public ModelRenderer ScarfTale5;
    public ModelRenderer n_Base;
    public ModelRenderer n_Front1;
    public ModelRenderer n_Eye1;
    public ModelRenderer n_Eye2;
    private float size = 1.0f;

    public NC_GiTurtleMdl(int id) {
        super(0.1f);
        this.id = id;
        if (id == 0) {
            this.field_78090_t = 64;
            this.field_78089_u = 64;
            this.ScarfTale2 = new ModelRenderer((ModelBase)this, 38, 5);
            this.ScarfTale2.func_78793_a(-0.6f, 4.4f, 2.2f);
            this.ScarfTale2.func_78790_a(-2.1f, 0.0f, -0.4f, 4, 3, 1, 0.0f);
            this.setRotateAngle(this.ScarfTale2, -0.18203785f, 0.0f, 0.0f);
            this.ScarfTale3 = new ModelRenderer((ModelBase)this, 38, 10);
            this.ScarfTale3.func_78793_a(0.0f, 3.0f, 0.0f);
            this.ScarfTale3.func_78790_a(-2.1f, -0.1f, -0.4f, 4, 3, 1, 0.0f);
            this.setRotateAngle(this.ScarfTale3, 0.13665928f, 0.0f, 0.0f);
            this.ScarfTale1 = new ModelRenderer((ModelBase)this, 38, 1);
            this.ScarfTale1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.ScarfTale1.func_78790_a(-2.7f, 2.5f, 1.8f, 4, 2, 1, 0.0f);
            this.setRotateAngle(this.ScarfTale1, 0.4098033f, 0.0f, 0.0f);
            this.ScarfTale5 = new ModelRenderer((ModelBase)this, 49, 1);
            this.ScarfTale5.func_78793_a(0.0f, 3.6f, 0.0f);
            this.ScarfTale5.func_78790_a(-2.1f, 0.0f, -0.4f, 4, 6, 1, 0.0f);
            this.setRotateAngle(this.ScarfTale5, 0.13665928f, 0.0f, 0.0f);
            this.Base1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.Base1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Base1.func_78790_a(-5.5f, -0.4f, -4.2f, 11, 2, 8, 0.0f);
            this.setRotateAngle(this.Base1, 0.10995574f, 0.0f, 0.0f);
            this.Base2 = new ModelRenderer((ModelBase)this, 0, 10);
            this.Base2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Base2.func_78790_a(-4.5f, -1.0f, -4.5f, 9, 2, 9, 0.0f);
            this.ScarfTale4 = new ModelRenderer((ModelBase)this, 38, 15);
            this.ScarfTale4.func_78793_a(0.0f, 3.0f, 0.0f);
            this.ScarfTale4.func_78790_a(-2.1f, -0.2f, -0.4f, 4, 4, 1, 0.0f);
            this.setRotateAngle(this.ScarfTale4, 0.13665928f, 0.0f, 0.0f);
            this.ScarfTale1.func_78792_a(this.ScarfTale2);
            this.ScarfTale2.func_78792_a(this.ScarfTale3);
            this.Base2.func_78792_a(this.ScarfTale1);
            this.ScarfTale4.func_78792_a(this.ScarfTale5);
            this.Base1.func_78792_a(this.Base2);
            this.ScarfTale3.func_78792_a(this.ScarfTale4);
            this.field_78116_c.func_78792_a(this.Base1);
        } else if (id == 1) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.n_Base = new ModelRenderer((ModelBase)this, 1, 1);
            this.n_Base.func_78793_a(0.0f, 0.0f, 0.0f);
            this.n_Base.func_78790_a(-5.0f, -8.7f, -5.9f, 10, 3, 10, 0.0f);
            this.setRotateAngle(this.n_Base, -0.18203785f, 0.0f, 0.0f);
            this.n_Eye2 = new ModelRenderer((ModelBase)this, 1, 6);
            this.n_Eye2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.n_Eye2.func_78790_a(1.1f, -8.0f, -7.0f, 3, 2, 1, 0.0f);
            this.n_Eye1 = new ModelRenderer((ModelBase)this, 1, 2);
            this.n_Eye1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.n_Eye1.func_78790_a(-4.2f, -8.0f, -7.0f, 3, 2, 1, 0.0f);
            this.n_Front1 = new ModelRenderer((ModelBase)this, 1, 15);
            this.n_Front1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.n_Front1.func_78790_a(-4.5f, -8.3f, -6.2f, 9, 3, 1, 0.0f);
            this.n_Base.func_78792_a(this.n_Eye2);
            this.n_Base.func_78792_a(this.n_Eye1);
            this.n_Base.func_78792_a(this.n_Front1);
            this.field_78116_c.func_78792_a(this.n_Base);
        }
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        float f6 = this.size;
        if (JRMCoreH.JYC()) {
            float age = JRMCoreHJYC.JYCAge((EntityPlayer)entity);
            float childScl = JRMCoreHJYC.JYCsizeBasedOnAge((EntityPlayer)entity);
            this.size = childScl = 3.0f - childScl * 2.0f;
        }
        if (this.id == 0) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
            this.Base1.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 1) {
            GL11.glScalef((float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            this.n_Base.func_78785_a(f5);
        }
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
        if (this.id == 0) {
            float s3;
            float s2;
            float s = 0.0f;
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.ScarfTale1.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale2.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale3.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale4.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
            } else {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.ScarfTale1.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale1.field_78795_f -= 0.1f;
                this.ScarfTale2.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale2.field_78795_f -= 0.1f;
                this.ScarfTale3.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale3.field_78795_f -= 0.1f;
                this.ScarfTale4.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.ScarfTale4.field_78795_f -= 0.1f;
            }
            if (entity.func_70093_af()) {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.ScarfTale1.field_78795_f += 0.3f;
                this.Base1.field_78795_f = 0.5f;
            } else {
                this.setRotateAngle(this.Base1, 0.10995574f, 0.0f, 0.0f);
                this.setRotateAngle(this.ScarfTale1, 0.3f, -0.0f, 0.0f);
            }
        } else if (this.id == 1) {
            this.n_Base.field_78798_e = this.field_78116_c.field_78798_e;
            this.n_Base.field_78797_d = this.field_78116_c.field_78797_d;
            this.n_Base.field_78800_c = this.field_78116_c.field_78800_c;
            this.n_Base.field_78808_h = this.field_78116_c.field_78808_h;
            this.n_Base.field_78796_g = this.field_78116_c.field_78796_g;
            this.n_Base.field_78795_f = this.field_78116_c.field_78795_f;
        }
    }
}

