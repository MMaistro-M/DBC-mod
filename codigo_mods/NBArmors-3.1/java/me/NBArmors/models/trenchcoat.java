/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package me.NBArmors.models;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class trenchcoat
extends ModelBipedBody {
    private final int VANITY_TRENCHCOAT = 0;
    public int id = -1;
    public ModelRenderer skirt1;
    public ModelRenderer skirt2;
    public ModelRenderer skirt3;
    public ModelRenderer skirt4;
    public ModelRenderer skirt5;
    private float size = 1.0f;

    public trenchcoat(int id) {
        super(id, 0.1f, 128, 64);
        this.id = id;
        if (id == 0) {
            this.field_78090_t = 128;
            this.field_78089_u = 64;
            this.skirt1 = new ModelRenderer((ModelBase)this);
            this.skirt1.func_78793_a(0.0f, -1.0f, 0.0f);
            this.setRotation(this.skirt1, -0.001f, 0.0f, 0.0f);
            this.skirt1.field_78804_l.add(new ModelBox(this.skirt1, 32, 0, -8.0f, 0.5f, -2.0f, 16, 24, 4, 0.1f));
            this.skirt2 = new ModelRenderer((ModelBase)this);
            this.skirt2.func_78793_a(0.0f, -1.0f, 0.0f);
            this.setRotation(this.skirt2, 0.001f, 0.0f, 0.0f);
            this.skirt2.field_78804_l.add(new ModelBox(this.skirt2, 72, 0, -8.0f, 0.5f, -2.0f, 16, 24, 4, 0.1f));
            this.skirt3 = new ModelRenderer((ModelBase)this);
            this.skirt3.func_78793_a(0.0f, -1.0f, 0.0f);
            this.setRotation(this.skirt3, -0.001f, 0.0f, 0.0f);
            this.skirt3.field_78804_l.add(new ModelBox(this.skirt3, 0, 28, -8.0f, 0.5f, -2.0f, 16, 24, 4, 0.1f));
            this.skirt4 = new ModelRenderer((ModelBase)this);
            this.skirt4.func_78793_a(0.0f, -1.0f, 0.0f);
            this.skirt4.field_78804_l.add(new ModelBox(this.skirt4, 40, 28, -8.0f, 0.5f, -2.0f, 16, 24, 4, 0.1f));
            this.skirt5 = new ModelRenderer((ModelBase)this);
            this.skirt5.func_78793_a(0.0f, -1.0f, 0.0f);
            this.skirt5.field_78804_l.add(new ModelBox(this.skirt5, 80, 28, -8.0f, 0.5f, -2.0f, 16, 24, 4, 0.1f));
            this.body.func_78792_a(this.skirt1);
            this.field_78115_e.func_78792_a(this.skirt1);
            this.body.func_78792_a(this.skirt2);
            this.field_78115_e.func_78792_a(this.skirt2);
            this.body.func_78792_a(this.skirt4);
            this.field_78115_e.func_78792_a(this.skirt4);
            this.body.func_78792_a(this.skirt5);
            this.field_78115_e.func_78792_a(this.skirt5);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        float s = 0.1070796f;
        float d = -0.1070796f;
        if (this.id == 0) {
            float s3;
            float s2;
            if (this.skirt1 != null) {
                if (y == 1) {
                    s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 2.5f * par2;
                    s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 2.5f * par2;
                    this.skirt1.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 + s : s);
                } else {
                    this.skirt1.field_78795_f = s;
                }
            }
            if (this.skirt2 != null) {
                if (y == 1) {
                    s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.5f * par2;
                    s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 0.5f * par2;
                    this.skirt2.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
                } else {
                    this.skirt2.field_78795_f = s;
                }
            }
            if (this.skirt4 != null) {
                if (y == 1) {
                    s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 1.5f * par2;
                    s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.5f * par2;
                    this.skirt4.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
                } else {
                    this.skirt4.field_78795_f = s;
                }
            }
            if (this.skirt3 != null) {
                if (y == 1) {
                    s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.1f * par2;
                    s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 0.1f * par2;
                    this.skirt3.field_78795_f = s3 + s < s ? s3 + s : (s2 + s < s ? s2 + s : s);
                } else {
                    this.skirt3.field_78795_f = s;
                }
            }
        }
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float f6 = this.size;
        if (JRMCoreH.JYC()) {
            float age = JRMCoreHJYC.JYCAge((EntityPlayer)entity);
            float childScl = JRMCoreHJYC.JYCsizeBasedOnAge((EntityPlayer)entity);
            this.size = childScl = 3.0f - childScl * 2.0f;
        }
        if (this.id == 0) {
            GL11.glPopMatrix();
            GL11.glScalef((float)(0.8f / f6), (float)(1.0f / f6), (float)(0.8f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.3f, (float)1.0f, (float)1.3f);
            this.skirt1.func_78785_a(f5);
            this.skirt2.func_78785_a(f5);
            this.skirt3.func_78785_a(f5);
            this.skirt4.func_78785_a(f5);
            this.skirt5.func_78785_a(f5);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

