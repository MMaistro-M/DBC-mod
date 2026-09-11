/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSaibaiman
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelSaibaiman() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        float var1 = 8.0f;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.head.func_78793_a(0.0f, 0.0f + var1, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 8, 4, 0.0f);
        this.body.func_78793_a(0.0f, 8.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 8, 4, 0.0f);
        this.rightarm.func_78793_a(-5.0f, 10.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 8, 4, 0.0f);
        this.leftarm.func_78793_a(5.0f, 10.0f, 0.0f);
        this.leftarm.field_78809_i = true;
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.rightleg.func_78793_a(-2.0f, 16.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.leftleg.func_78793_a(2.0f, 16.0f, 0.0f);
        this.leftleg.field_78809_i = true;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        if (this.field_78091_s) {
            float var8 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / var8), (float)(1.5f / var8), (float)(1.5f / var8));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * f5), (float)0.0f);
            this.head.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / var8), (float)(1.0f / var8), (float)(1.0f / var8));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.body.func_78785_a(f5);
            this.rightarm.func_78785_a(f5);
            this.leftarm.func_78785_a(f5);
            this.rightleg.func_78785_a(f5);
            this.leftleg.func_78785_a(f5);
            GL11.glPopMatrix();
        } else {
            this.head.func_78785_a(f5);
            this.body.func_78785_a(f5);
            this.rightarm.func_78785_a(f5);
            this.leftarm.func_78785_a(f5);
            this.rightleg.func_78785_a(f5);
            this.leftleg.func_78785_a(f5);
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        this.rightarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leftarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.rightarm.field_78808_h = 0.0f;
        this.leftarm.field_78808_h = 0.0f;
        this.rightleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.field_78796_g = 0.0f;
        this.leftleg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.rightarm.field_78795_f += -0.62831855f;
            this.leftarm.field_78795_f += -0.62831855f;
            this.rightleg.field_78795_f = -1.2566371f;
            this.leftleg.field_78795_f = -1.2566371f;
            this.rightleg.field_78796_g = 0.31415927f;
            this.leftleg.field_78796_g = -0.31415927f;
        }
        if (this.field_78119_l != 0) {
            this.leftarm.field_78795_f = this.leftarm.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78119_l;
        }
        if (this.field_78120_m != 0) {
            this.rightarm.field_78795_f = this.rightarm.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78120_m;
        }
        this.rightarm.field_78796_g = 0.0f;
        this.leftarm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float var8 = this.field_78095_p;
            this.body.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var8) * (float)Math.PI * 2.0f)) * 0.2f;
            this.rightarm.field_78798_e = MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.rightarm.field_78800_c = -MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.leftarm.field_78798_e = -MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.leftarm.field_78800_c = MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.rightarm.field_78796_g += this.body.field_78796_g;
            this.leftarm.field_78796_g += this.body.field_78796_g;
            this.leftarm.field_78795_f += this.body.field_78796_g;
            var8 = 1.0f - this.field_78095_p;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            float var9 = MathHelper.func_76126_a((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.head.field_78795_f - 0.7f) * 0.75f;
            this.rightarm.field_78795_f = (float)((double)this.rightarm.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.rightarm.field_78796_g += this.body.field_78796_g * 2.0f;
            this.rightarm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        this.rightarm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
    }
}

