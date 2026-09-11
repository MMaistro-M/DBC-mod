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

public class ModelYakon
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer leftarm;
    ModelRenderer leftarm1;
    ModelRenderer leftarm2;
    ModelRenderer leftarm3;
    ModelRenderer rightarm;
    ModelRenderer rightarm1;
    ModelRenderer rightarm2;
    ModelRenderer rightarm3;
    ModelRenderer leftleg;
    ModelRenderer leftleg1;
    ModelRenderer leftleg2;
    ModelRenderer leftleg3;
    ModelRenderer rightleg;
    ModelRenderer rightleg1;
    ModelRenderer rightleg2;
    ModelRenderer rightleg3;
    ModelRenderer back1;
    ModelRenderer back2;
    ModelRenderer back3;
    ModelRenderer back4;
    ModelRenderer tail;
    private float F = 1.0f;

    public ModelYakon(float f) {
        this();
        this.F = f;
    }

    public ModelYakon() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78789_a(-4.0f, -4.0f, -5.0f, 8, 8, 5);
        this.head.func_78793_a(0.0f, -6.0f, -4.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 29);
        this.body.func_78789_a(-4.0f, 8.0f, -2.0f, 8, 8, 4);
        this.body.func_78793_a(0.0f, -8.0f, 0.0f);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 13);
        this.body2.func_78789_a(-6.0f, 0.0f, -4.0f, 12, 8, 8);
        this.body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 0);
        this.leftarm.func_78789_a(0.0f, -3.0f, -3.0f, 6, 6, 6);
        this.leftarm.func_78793_a(6.0f, -5.0f, 0.0f);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.leftarm1 = new ModelRenderer((ModelBase)this, 40, 12);
        this.leftarm1.func_78789_a(0.0f, 3.0f, -2.0f, 4, 7, 4);
        this.leftarm1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm1.field_78809_i = true;
        this.setRotation(this.leftarm1, 0.0f, 0.0f, 0.0f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.leftarm2.func_78789_a(0.0f, 6.0f, -12.0f, 4, 4, 10);
        this.leftarm2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm2.field_78809_i = true;
        this.setRotation(this.leftarm2, 0.0f, 0.0f, 0.0f);
        this.leftarm3 = new ModelRenderer((ModelBase)this, 56, 14);
        this.leftarm3.func_78789_a(1.0f, 7.0f, -13.0f, 2, 2, 8);
        this.leftarm3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm3.field_78809_i = true;
        this.setRotation(this.leftarm3, -0.1745329f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 0);
        this.rightarm.func_78789_a(-6.0f, -3.0f, -3.0f, 6, 6, 6);
        this.rightarm.func_78793_a(-6.0f, -5.0f, 0.0f);
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.rightarm1 = new ModelRenderer((ModelBase)this, 40, 12);
        this.rightarm1.func_78789_a(-4.0f, 3.0f, -2.0f, 4, 7, 4);
        this.rightarm1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightarm1, 0.0f, 0.0f, 0.0f);
        this.rightarm2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.rightarm2.func_78789_a(-4.0f, 6.0f, -12.0f, 4, 4, 10);
        this.rightarm2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightarm2, 0.0f, 0.0f, 0.0f);
        this.rightarm3 = new ModelRenderer((ModelBase)this, 56, 14);
        this.rightarm3.func_78789_a(-3.0f, 7.0f, -13.0f, 2, 2, 8);
        this.rightarm3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightarm3, -0.1745329f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 41);
        this.leftleg.func_78789_a(0.0f, -2.0f, -10.0f, 4, 4, 12);
        this.leftleg.func_78793_a(4.0f, 8.0f, 0.0f);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.leftleg1 = new ModelRenderer((ModelBase)this, 22, 47);
        this.leftleg1.func_78789_a(0.0f, 4.0f, -9.0f, 4, 4, 10);
        this.leftleg1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftleg1.field_78809_i = true;
        this.setRotation(this.leftleg1, -0.3490659f, 0.0f, 0.0174533f);
        this.leftleg2 = new ModelRenderer((ModelBase)this, 40, 42);
        this.leftleg2.func_78789_a(0.0f, 4.0f, 1.0f, 4, 11, 4);
        this.leftleg2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftleg2.field_78809_i = true;
        this.setRotation(this.leftleg2, -0.3665191f, 0.0f, 0.0174533f);
        this.leftleg3 = new ModelRenderer((ModelBase)this, 20, 39);
        this.leftleg3.func_78789_a(0.0f, 14.0f, -8.0f, 4, 2, 6);
        this.leftleg3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftleg3.field_78809_i = true;
        this.setRotation(this.leftleg3, 0.0f, 0.0f, 0.0174533f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 41);
        this.rightleg.func_78789_a(-4.0f, -2.0f, -10.0f, 4, 4, 12);
        this.rightleg.func_78793_a(-4.0f, 8.0f, 0.0f);
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.rightleg1 = new ModelRenderer((ModelBase)this, 22, 47);
        this.rightleg1.func_78789_a(-4.0f, 4.0f, -9.0f, 4, 4, 10);
        this.rightleg1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightleg1, -0.3490659f, 0.0f, 0.0174533f);
        this.rightleg2 = new ModelRenderer((ModelBase)this, 40, 42);
        this.rightleg2.func_78789_a(-4.0f, 4.0f, 1.0f, 4, 11, 4);
        this.rightleg2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightleg2, -0.3665191f, 0.0f, 0.0174533f);
        this.rightleg3 = new ModelRenderer((ModelBase)this, 20, 39);
        this.rightleg3.func_78789_a(-4.0f, 14.0f, -8.0f, 4, 2, 6);
        this.rightleg3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightleg3, 0.0f, 0.0f, 0.0174533f);
        this.back1 = new ModelRenderer((ModelBase)this, 40, 32);
        this.back1.func_78789_a(1.0f, 1.0f, 1.0f, 4, 4, 6);
        this.back1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.back1, 0.5235988f, 0.2617994f, 0.0f);
        this.back2 = new ModelRenderer((ModelBase)this, 40, 32);
        this.back2.func_78789_a(-5.0f, 1.0f, 1.0f, 4, 4, 6);
        this.back2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.back2, 0.5235988f, -0.2617994f, 0.0f);
        this.back3 = new ModelRenderer((ModelBase)this, 40, 23);
        this.back3.func_78789_a(1.0f, 5.0f, 1.0f, 3, 3, 6);
        this.back3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.back3, 0.2617994f, 0.2617994f, 0.0f);
        this.back4 = new ModelRenderer((ModelBase)this, 40, 23);
        this.back4.func_78789_a(-4.0f, 5.0f, 2.0f, 3, 3, 6);
        this.back4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.back4, 0.2617994f, -0.2617994f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 56, 38);
        this.tail.func_78789_a(-2.0f, 8.0f, -3.0f, 4, 12, 4);
        this.tail.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail, 0.3316126f, 0.0f, 0.0f);
        this.leftarm.func_78792_a(this.leftarm1);
        this.leftarm.func_78792_a(this.leftarm2);
        this.leftarm.func_78792_a(this.leftarm3);
        this.rightarm.func_78792_a(this.rightarm1);
        this.rightarm.func_78792_a(this.rightarm2);
        this.rightarm.func_78792_a(this.rightarm3);
        this.leftleg.func_78792_a(this.leftleg1);
        this.leftleg.func_78792_a(this.leftleg2);
        this.leftleg.func_78792_a(this.leftleg3);
        this.rightleg.func_78792_a(this.rightleg1);
        this.rightleg.func_78792_a(this.rightleg2);
        this.rightleg.func_78792_a(this.rightleg3);
        this.body.func_78792_a(this.body2);
        this.body.func_78792_a(this.back1);
        this.body.func_78792_a(this.back2);
        this.body.func_78792_a(this.back3);
        this.body.func_78792_a(this.back4);
        this.body.func_78792_a(this.tail);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.F, (float)this.F, (float)this.F);
        GL11.glTranslatef((float)0.0f, (float)((this.F - 1.0f) * -0.74f), (float)0.0f);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        GL11.glPopMatrix();
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

