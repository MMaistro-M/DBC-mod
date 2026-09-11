/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.client.model.part.legs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMermaidLegs2
extends ModelRenderer {
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer Tail7;
    ModelRenderer Tail8;
    public boolean isRiding = false;
    public boolean isSneaking = false;
    public boolean isSleeping = false;
    public boolean isCrawling = false;

    public ModelMermaidLegs2(ModelBase base) {
        super(base);
        this.field_78801_a = 64.0f;
        this.field_78799_b = 32.0f;
        this.Tail1 = new ModelRenderer(base, 0, 18);
        this.Tail1.func_78787_b(64, 32);
        this.Tail1.func_78789_a(0.0f, 0.0f, 0.0f, 8, 6, 4);
        this.Tail1.func_78793_a(-4.0f, 12.0f, -2.0f);
        this.setRotation(this.Tail1, 0.075f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(base, 0, 18);
        this.Tail2.func_78787_b(64, 32);
        this.Tail2.func_78789_a(0.0f, 0.0f, 0.0f, 6, 5, 3);
        this.Tail2.func_78793_a(1.0f, 5.5f, 0.3f);
        this.setRotation(this.Tail2, 0.56f, 0.0f, 0.0f);
        this.Tail1.func_78792_a(this.Tail2);
        this.Tail3 = new ModelRenderer(base, 0, 18);
        this.Tail3.func_78787_b(64, 32);
        this.Tail3.func_78789_a(0.0f, 0.0f, 0.0f, 5, 5, 2);
        this.Tail3.func_78793_a(5.5f, 4.0f, 2.5f);
        this.setRotation(this.Tail3, -0.37818f, 3.141593f, 0.0f);
        this.Tail2.func_78792_a(this.Tail3);
        this.Tail4 = new ModelRenderer(base, 0, 20);
        this.Tail4.func_78787_b(64, 32);
        this.Tail4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 1);
        this.Tail4.func_78793_a(0.5f, 4.5f, 0.5f);
        this.setRotation(this.Tail4, -0.1f, 0.0f, 0.0f);
        this.Tail3.func_78792_a(this.Tail4);
        this.Tail5 = new ModelRenderer(base, 0, 20);
        this.Tail5.func_78787_b(64, 32);
        this.Tail5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Tail5.func_78793_a(-1.0f, 1.5f, 0.0f);
        this.setRotation(this.Tail5, 0.0f, 0.0f, 0.0f);
        this.Tail4.func_78792_a(this.Tail5);
        this.Tail6 = new ModelRenderer(base, 0, 20);
        this.Tail6.func_78787_b(64, 32);
        this.Tail6.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Tail6.func_78793_a(-2.0f, 3.0f, 0.0f);
        this.setRotation(this.Tail6, 0.0f, 0.0f, 0.0f);
        this.Tail4.func_78792_a(this.Tail6);
        this.Tail7 = new ModelRenderer(base, 0, 20);
        this.Tail7.func_78787_b(64, 32);
        this.Tail7.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Tail7.func_78793_a(4.0f, 1.5f, 0.0f);
        this.setRotation(this.Tail7, 0.0f, 0.0f, 0.0f);
        this.Tail4.func_78792_a(this.Tail7);
        this.Tail8 = new ModelRenderer(base, 0, 20);
        this.Tail8.func_78787_b(64, 32);
        this.Tail8.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Tail8.func_78793_a(5.0f, 3.0f, 0.0f);
        this.setRotation(this.Tail8, 0.0f, 0.0f, 0.0f);
        this.Tail4.func_78792_a(this.Tail8);
    }

    public void func_78785_a(float f5) {
        if (this.field_78807_k || !this.field_78806_j) {
            return;
        }
        this.Tail1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.Tail1.func_78793_a(-4.0f, 12.0f, -2.0f);
        float ani = MathHelper.func_76126_a((float)(par1 * 0.6662f));
        if ((double)ani > 0.2) {
            ani /= 3.0f;
        }
        if (this.isSleeping || this.isCrawling) {
            this.Tail4.field_78795_f = 0.0f;
            this.Tail3.field_78795_f = 0.0f;
            this.Tail2.field_78795_f = 0.0f;
            this.Tail1.field_78795_f = 0.0f;
        } else {
            this.Tail1.field_78795_f = 0.2f - ani * 0.2f * par2;
            this.Tail2.field_78795_f = 0.56f - ani * 0.24f * par2;
            this.Tail3.field_78795_f = -0.4f + ani * 0.24f * par2;
            this.Tail4.field_78795_f = -0.1f + ani * 0.1f * par2;
            if (entity.func_70093_af()) {
                this.Tail1.func_78793_a(-4.0f, 10.0f, 3.0f);
            }
        }
    }
}

