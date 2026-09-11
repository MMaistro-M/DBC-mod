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
package noppes.npcs.client.model.part.tails;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMonkeyTail
extends ModelRenderer {
    public boolean isAnimated = true;
    public ModelRenderer monkey;
    public ModelRenderer m1;
    public ModelRenderer m2;
    public ModelRenderer m3;
    public ModelRenderer m4;
    public ModelRenderer m5;
    public ModelRenderer m6;
    public ModelRenderer monkey_wrapped;
    public ModelRenderer mw1;
    public ModelRenderer mw2;
    public ModelRenderer mw3;
    public ModelRenderer mw4;
    public ModelRenderer monkey_large;
    public ModelRenderer ml1;
    public ModelRenderer ml2;
    public ModelRenderer ml3;
    public ModelRenderer ml4;
    public ModelRenderer ml5;

    public ModelMonkeyTail(ModelBiped base) {
        super((ModelBase)base);
        float heightFactor = -9.0f;
        this.monkey_large = new ModelRenderer((ModelBase)base, 38, 54);
        this.monkey_large.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.monkey_large, -0.5235988f, 0.0f, 0.0f);
        this.ml1 = new ModelRenderer((ModelBase)base, 38, 54);
        this.ml1.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.ml1, 0.5235988f, 8.727E-4f, 0.0f);
        this.ml2 = new ModelRenderer((ModelBase)base, 38, 54);
        this.ml2.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.ml2, 0.0f, 0.0f, 0.0f);
        this.ml3 = new ModelRenderer((ModelBase)base, 38, 54);
        this.ml3.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.ml3, 0.0f, 0.0f, 0.0f);
        this.ml4 = new ModelRenderer((ModelBase)base, 38, 54);
        this.ml4.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.ml4, 0.0f, 0.0f, 0.0f);
        this.ml5 = new ModelRenderer((ModelBase)base, 38, 54);
        this.ml5.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.setRotation(this.ml5, 0.0f, 0.0f, 0.0f);
        this.monkey_large.field_78795_f = 0.65f;
        this.monkey_large.field_78797_d = -2.0f;
        this.ml1.field_78798_e = 5.0f;
        this.ml2.field_78798_e = 5.0f;
        this.ml3.field_78798_e = 5.0f;
        this.ml4.field_78798_e = 5.0f;
        this.ml5.field_78798_e = 5.0f;
        this.ml4.func_78792_a(this.ml5);
        this.ml3.func_78792_a(this.ml4);
        this.ml2.func_78792_a(this.ml3);
        this.ml1.func_78792_a(this.ml2);
        this.monkey_large.func_78792_a(this.ml1);
        this.monkey = new ModelRenderer((ModelBase)base, 0, 0);
        this.monkey.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.m1 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m1, -0.5235988f, 0.0f, 0.0f);
        this.m2 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m2, 0.5235988f, 8.727E-4f, 0.0f);
        this.m3 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m3, 0.0f, 0.0f, 0.0f);
        this.m4 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m4, 0.0f, 0.0f, 0.0f);
        this.m5 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m5, 0.0f, 0.0f, 0.0f);
        this.m6 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.setRotation(this.m6, 0.0f, 0.0f, 0.0f);
        this.m5.func_78792_a(this.m6);
        this.m4.func_78792_a(this.m5);
        this.m3.func_78792_a(this.m4);
        this.m2.func_78792_a(this.m3);
        this.m1.func_78792_a(this.m2);
        this.monkey.func_78792_a(this.m1);
        this.monkey.field_78800_c = 1.0f;
        this.monkey.field_78797_d = 10.0f + heightFactor;
        this.monkey.field_78798_e = 2.0f;
        this.m1.field_78800_c = -1.0f;
        this.m1.field_78797_d = -1.0f;
        this.m2.field_78798_e = 4.0f;
        this.m3.field_78798_e = 4.0f;
        this.m4.field_78798_e = 4.0f;
        this.m5.field_78798_e = 4.0f;
        this.m6.field_78798_e = 4.0f;
        this.monkey_wrapped = new ModelRenderer((ModelBase)base, 0, 0);
        this.monkey_wrapped.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.monkey_wrapped.func_78793_a(0.0f, 0.0f + heightFactor, 0.0f);
        this.mw1 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(3.5f, 8.0f, -2.5f, 1, 2, 5);
        this.mw1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.mw1, 0.0f, 0.0f, 0.0f);
        this.mw2 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-4.433333f, 8.0f, -2.5f, 1, 2, 5);
        this.mw2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.mw2, 0.0f, 0.0f, 0.0f);
        this.mw3 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-3.433333f, 8.0f, 1.5f, 7, 2, 1);
        this.mw3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.mw3, 0.0f, 0.0f, 0.0f);
        this.mw4 = new ModelRenderer((ModelBase)base, 32, 48).func_78789_a(-3.433333f, 8.0f, -2.5f, 7, 2, 1);
        this.mw4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.mw4, 0.0f, 0.0f, 0.0f);
        this.monkey_wrapped.field_78798_e = -0.5f;
        this.monkey_wrapped.func_78792_a(this.mw1);
        this.monkey_wrapped.func_78792_a(this.mw2);
        this.monkey_wrapped.func_78792_a(this.mw3);
        this.monkey_wrapped.func_78792_a(this.mw4);
        this.func_78792_a(this.monkey);
        this.func_78792_a(this.monkey_wrapped);
        this.func_78792_a(this.monkey_large);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float scale = 0.18f;
        float damping = 0.6f;
        double motionX = entity.field_70159_w;
        double motionY = -entity.field_70181_x;
        if (!this.monkey_large.field_78807_k) {
            this.monkey_large.field_78796_g = 0.2f;
            this.monkey_large.field_78795_f = -0.3f;
            this.ml1.field_78796_g = 0.2f;
            this.ml1.field_78795_f = 0.4f;
            this.ml2.field_78796_g = 0.1f;
            this.ml2.field_78795_f = 0.6f;
            this.ml3.field_78796_g = 0.1f;
            this.ml3.field_78795_f = 0.3f;
            this.ml4.field_78796_g = 0.2f;
            this.ml4.field_78795_f = -0.2f;
            this.ml5.field_78796_g = 0.2f;
            this.ml5.field_78795_f = -0.4f;
            if (this.isAnimated) {
                float angleOffset = 0.01f;
                float r = MathHelper.func_76126_a((float)(f2 * 0.02f)) * angleOffset;
                float r2 = MathHelper.func_76134_b((float)(f2 * 0.02f)) * angleOffset;
                float r3 = MathHelper.func_76134_b((float)(f2 * 0.14f)) * angleOffset;
                float angleSpeed = 0.05f;
                float xMotionReducer = 0.2f;
                float yMotionReducer = 0.4f;
                this.monkey_large.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.2f - 0.2f + r);
                this.ml1.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.2f - 0.2f + r2 + r3);
                this.ml2.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.1f - 0.1f + r + r3);
                this.ml2.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.4f + 0.3f);
                this.ml3.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.1f + r2);
                this.ml3.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.1f - 0.2f);
                this.ml4.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.2f + r + r3);
                this.ml4.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.1f - 0.3f);
                this.ml5.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.2f + r2 + r3);
                this.ml5.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.4f - 0.4f);
            }
            double deltaY = motionY * (double)scale - (double)this.monkey.field_78795_f;
            double deltaX = motionX * (double)scale - (double)this.monkey.field_78796_g;
            this.monkey_large.field_78795_f = (float)((double)this.monkey_large.field_78795_f + deltaY * (double)damping);
            this.ml1.field_78795_f = (float)((double)this.ml1.field_78795_f + deltaY * (double)damping);
            this.ml2.field_78795_f = (float)((double)this.ml2.field_78795_f + deltaY * (double)damping);
            this.ml3.field_78795_f = (float)((double)this.ml3.field_78795_f + deltaY * (double)damping);
            this.ml4.field_78795_f = (float)((double)this.ml4.field_78795_f + deltaY * (double)damping);
            this.ml5.field_78795_f = (float)((double)this.ml5.field_78795_f + deltaY * (double)damping);
            this.monkey_large.field_78796_g = (float)((double)this.monkey_large.field_78796_g + deltaX * (double)damping);
            this.ml1.field_78796_g = (float)((double)this.ml1.field_78796_g + deltaX * (double)damping);
            this.ml2.field_78796_g = (float)((double)this.ml2.field_78796_g + deltaX * (double)damping);
            this.ml3.field_78796_g = (float)((double)this.ml3.field_78796_g + deltaX * (double)damping);
            this.ml4.field_78796_g = (float)((double)this.ml4.field_78796_g + deltaX * (double)damping);
            this.ml5.field_78796_g = (float)((double)this.ml5.field_78796_g + deltaX * (double)damping);
        } else if (!this.monkey.field_78807_k) {
            this.m1.field_78795_f = -0.3f;
            this.m1.field_78796_g = 0.2f;
            this.m2.field_78795_f = 0.4f;
            this.m2.field_78796_g = 0.2f;
            this.m3.field_78795_f = 0.6f;
            this.m3.field_78796_g = 0.1f;
            this.m4.field_78795_f = 0.3f;
            this.m4.field_78796_g = 0.1f;
            this.m5.field_78795_f = -0.2f;
            this.m5.field_78796_g = 0.2f;
            this.m6.field_78795_f = -0.4f;
            this.m6.field_78796_g = 0.2f;
            if (this.isAnimated) {
                float angleOffset = 0.01f;
                float r4 = MathHelper.func_76126_a((float)(f2 * 0.02f)) * angleOffset;
                float r5 = MathHelper.func_76134_b((float)(f2 * 0.02f)) * angleOffset;
                float r6 = MathHelper.func_76134_b((float)(f2 * 0.14f)) * angleOffset;
                float angleSpeed = 0.05f;
                float xMotionReducer = 0.4f;
                float yMotionReducer = 0.4f;
                this.m1.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.2f - 0.2f + r4);
                this.m2.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.2f - 0.2f + r5 + r6);
                this.m3.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.4f + 0.1f);
                this.m3.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.1f - 0.1f + r4 + r6);
                this.m4.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.1f - 0.2f);
                this.m4.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.1f + r5);
                this.m5.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.1f - 0.1f);
                this.m5.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.2f + r4 + r6);
                this.m6.field_78795_f += xMotionReducer * (MathHelper.func_76126_a((float)(f2 * 0.05f)) * 0.4f - 0.4f);
                this.m6.field_78796_g += yMotionReducer * (MathHelper.func_76134_b((float)(f2 * 0.05f)) * 0.4f - 0.2f + r5 + r6);
            }
            double deltaY = motionY * (double)scale - (double)this.monkey.field_78795_f;
            double deltaX = motionX * (double)scale - (double)this.monkey.field_78796_g;
            this.m1.field_78795_f = (float)((double)this.m1.field_78795_f + deltaY * (double)damping);
            this.m2.field_78795_f = (float)((double)this.m2.field_78795_f + deltaY * (double)damping);
            this.m3.field_78795_f = (float)((double)this.m3.field_78795_f + deltaY * (double)damping);
            this.m4.field_78795_f = (float)((double)this.m4.field_78795_f + deltaY * (double)damping);
            this.m5.field_78795_f = (float)((double)this.m5.field_78795_f + deltaY * (double)damping);
            this.m6.field_78795_f = (float)((double)this.m6.field_78795_f + deltaY * (double)damping);
            this.m1.field_78796_g = (float)((double)this.m1.field_78796_g + deltaX * (double)damping);
            this.m2.field_78796_g = (float)((double)this.m2.field_78796_g + deltaX * (double)damping);
            this.m3.field_78796_g = (float)((double)this.m3.field_78796_g + deltaX * (double)damping);
            this.m4.field_78796_g = (float)((double)this.m4.field_78796_g + deltaX * (double)damping);
            this.m5.field_78796_g = (float)((double)this.m5.field_78796_g + deltaX * (double)damping);
            this.m6.field_78796_g = (float)((double)this.m6.field_78796_g + deltaX * (double)damping);
        }
    }
}

