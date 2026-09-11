/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelVegeta
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarmshoulder;
    ModelRenderer rightarmshoulder;
    ModelRenderer hair1;
    ModelRenderer hair2;
    ModelRenderer hair3;
    ModelRenderer hair4;
    ModelRenderer hea5;
    ModelRenderer hea6;

    public ModelVegeta() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.func_78793_a(0.0f, 2.0f, 0.0f);
        this.head.func_78787_b(128, 64);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 11, 4);
        this.body.func_78793_a(0.0f, 2.0f, 0.0f);
        this.body.func_78787_b(128, 64);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 11, 4);
        this.rightarm.func_78793_a(-5.0f, 4.0f, 0.0f);
        this.rightarm.func_78787_b(128, 64);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 11, 4);
        this.leftarm.func_78793_a(5.0f, 4.0f, 0.0f);
        this.leftarm.func_78787_b(128, 64);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 11, 4);
        this.rightleg.func_78793_a(-2.0f, 13.0f, 0.0f);
        this.rightleg.func_78787_b(128, 64);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 11, 4);
        this.leftleg.func_78793_a(2.0f, 13.0f, 0.0f);
        this.leftleg.func_78787_b(128, 64);
        this.rightarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
        this.rightarmshoulder.func_78789_a(-6.0f, -3.0f, -3.0f, 7, 4, 6);
        this.rightarmshoulder.func_78793_a(-5.0f, 4.0f, 0.0f);
        this.rightarmshoulder.func_78787_b(128, 64);
        this.leftarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
        this.leftarmshoulder.field_78809_i = true;
        this.leftarmshoulder.func_78789_a(-1.0f, -3.0f, -3.0f, 7, 4, 6);
        this.leftarmshoulder.func_78793_a(5.0f, 4.0f, 0.0f);
        this.leftarmshoulder.func_78787_b(128, 64);
        this.hair1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.hair1.func_78789_a(-4.0f, -10.0f, -6.0f, 8, 4, 8);
        this.hair1.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hair1.func_78787_b(128, 64);
        this.hair2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.hair2.func_78789_a(-3.0f, -12.0f, -7.0f, 6, 4, 6);
        this.hair2.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hair2.func_78787_b(128, 64);
        this.hair3 = new ModelRenderer((ModelBase)this, 88, 0);
        this.hair3.func_78789_a(-2.0f, -13.0f, -8.0f, 4, 4, 4);
        this.hair3.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hair3.func_78787_b(128, 64);
        this.hair4 = new ModelRenderer((ModelBase)this, 104, 0);
        this.hair4.func_78789_a(-1.0f, -15.0f, -7.0f, 2, 4, 2);
        this.hair4.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hair4.func_78787_b(128, 64);
        this.hea5 = new ModelRenderer((ModelBase)this, 112, 0);
        this.hea5.func_78789_a(-1.0f, -15.5f, 3.5f, 2, 8, 2);
        this.hea5.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hea5.func_78787_b(128, 64);
        this.hea6 = new ModelRenderer((ModelBase)this, 56, 12);
        this.hea6.func_78789_a(-5.0f, -6.0f, -7.0f, 10, 2, 8);
        this.hea6.func_78793_a(0.0f, 2.0f, 0.0f);
        this.hea6.func_78787_b(128, 64);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        this.leftarmshoulder.func_78785_a(f5);
        this.rightarmshoulder.func_78785_a(f5);
        this.hair1.func_78785_a(f5);
        this.hair2.func_78785_a(f5);
        this.hair3.func_78785_a(f5);
        this.hair4.func_78785_a(f5);
        this.hea5.func_78785_a(f5);
        this.hea6.func_78785_a(f5);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        this.hair1.field_78796_g = this.head.field_78796_g;
        this.hair1.field_78795_f = -0.296706f + this.head.field_78795_f;
        this.hair2.field_78796_g = this.head.field_78796_g;
        this.hair2.field_78795_f = -0.4537856f + this.head.field_78795_f;
        this.hair3.field_78796_g = this.head.field_78796_g;
        this.hair3.field_78795_f = -0.6108652f + this.head.field_78795_f;
        this.hair4.field_78796_g = this.head.field_78796_g;
        this.hair4.field_78795_f = -0.5934119f + this.head.field_78795_f;
        this.hea5.field_78796_g = this.head.field_78796_g;
        this.hea5.field_78795_f = 0.1047198f + this.head.field_78795_f;
        this.hea6.field_78796_g = this.head.field_78796_g;
        this.hea6.field_78795_f = -0.5585054f + this.head.field_78795_f;
        this.rightarmshoulder.field_78795_f = this.rightarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leftarmshoulder.field_78795_f = this.leftarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.rightarmshoulder.field_78808_h = this.rightarm.field_78808_h = 0.0f;
        this.leftarmshoulder.field_78808_h = this.leftarm.field_78808_h = 0.0f;
        this.rightleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.field_78796_g = 0.0f;
        this.leftleg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.rightarm.field_78795_f += -0.62831855f;
            this.rightarmshoulder.field_78795_f = this.rightarm.field_78795_f;
            this.leftarm.field_78795_f += -0.62831855f;
            this.leftarmshoulder.field_78795_f = this.leftarm.field_78795_f;
            this.rightleg.field_78795_f = -1.2566371f;
            this.leftleg.field_78795_f = -1.2566371f;
            this.rightleg.field_78796_g = 0.31415927f;
            this.leftleg.field_78796_g = -0.31415927f;
        }
        this.rightarmshoulder.field_78796_g = this.rightarm.field_78796_g = 0.0f;
        this.leftarmshoulder.field_78796_g = this.leftarm.field_78796_g = 0.0f;
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
            this.rightarmshoulder.field_78798_e = MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.rightarmshoulder.field_78800_c = -MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.leftarmshoulder.field_78798_e = -MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.leftarmshoulder.field_78800_c = MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.rightarmshoulder.field_78796_g += this.body.field_78796_g;
            this.leftarmshoulder.field_78796_g += this.body.field_78796_g;
            this.leftarmshoulder.field_78795_f += this.body.field_78796_g;
            var8 = 1.0f - this.field_78095_p;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            float var9 = MathHelper.func_76126_a((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.head.field_78795_f - 0.7f) * 0.75f;
            this.rightarm.field_78795_f = (float)((double)this.rightarm.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.rightarm.field_78796_g += this.body.field_78796_g * 2.0f;
            this.rightarm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
            this.rightarmshoulder.field_78795_f = (float)((double)this.rightarm.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.rightarmshoulder.field_78796_g += this.body.field_78796_g * 2.0f;
            this.rightarmshoulder.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        this.rightarm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarmshoulder.field_78808_h = this.rightarm.field_78808_h;
        this.leftarm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarmshoulder.field_78808_h = this.leftarm.field_78808_h;
        this.rightarm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.rightarmshoulder.field_78795_f = this.rightarm.field_78795_f;
        this.leftarm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarmshoulder.field_78795_f = this.leftarm.field_78795_f;
    }
}

