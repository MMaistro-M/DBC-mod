/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCampfire
extends ModelBase {
    ModelRenderer Rock1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Rock2;
    ModelRenderer Rock3;
    ModelRenderer Rock4;
    ModelRenderer Rock5;
    ModelRenderer Rock6;
    ModelRenderer Rock7;
    ModelRenderer Rock8;
    ModelRenderer Log3;
    ModelRenderer Log1;
    ModelRenderer Log4;
    ModelRenderer Log2;

    public ModelCampfire() {
        this.Rock1.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Rock1.func_78793_a(5.0f, 22.0f, 3.0f);
        this.setRotation(this.Rock1, 0.0f, -0.7435722f, 0.0f);
        this.Rock2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 6);
        this.Rock2.func_78793_a(5.0f, 21.0f, -3.0f);
        this.Rock3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock3.func_78789_a(0.0f, 0.0f, 0.0f, 5, 3, 3);
        this.Rock3.func_78793_a(2.5f, 21.0f, -8.0f);
        this.setRotation(this.Rock3, 0.0f, -0.5576792f, 0.0f);
        this.Rock4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.Rock4.func_78793_a(-2.0f, 22.0f, -7.5f);
        this.Rock5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock5.func_78789_a(0.0f, 0.0f, -2.0f, 7, 2, 2);
        this.Rock5.func_78793_a(-3.5f, 22.0f, 7.8f);
        this.Rock6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock6.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 3);
        this.Rock6.func_78793_a(-5.0f, 21.0f, 3.0f);
        this.setRotation(this.Rock6, 0.0f, -1.003822f, 0.0f);
        this.Rock7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock7.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 3);
        this.Rock7.func_78793_a(-7.0f, 21.0f, -4.5f);
        this.setRotation(this.Rock7, 0.0f, 0.8551081f, 0.0f);
        this.Rock8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rock8.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 6);
        this.Rock8.func_78793_a(-8.0f, 22.0f, -3.0f);
        this.Log3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.Log3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.Log3.func_78793_a(0.0f, 16.0f, -1.0f);
        this.setRotation(this.Log3, 0.3717861f, -1.487144f, -0.1487144f);
        this.Log1 = new ModelRenderer((ModelBase)this, 8, 21);
        this.Log1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.Log1.func_78793_a(0.0f, 16.0f, -1.0f);
        this.setRotation(this.Log1, -0.1487144f, 0.0f, -0.3717861f);
        this.Log4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.Log4.func_78789_a(0.0f, 0.0f, -2.0f, 2, 9, 2);
        this.Log4.func_78793_a(1.0f, 16.0f, 1.0f);
        this.setRotation(this.Log4, -0.3346075f, 3.141593f, 0.0f);
        this.Log2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.Log2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.Log2.func_78793_a(1.0f, 16.0f, -1.0f);
        this.setRotation(this.Log2, 0.2974289f, 3.141593f, 0.0f);
    }

    public void renderRock(float f5) {
        this.Rock1.func_78785_a(f5);
        this.Rock2.func_78785_a(f5);
        this.Rock3.func_78785_a(f5);
        this.Rock4.func_78785_a(f5);
        this.Rock5.func_78785_a(f5);
        this.Rock6.func_78785_a(f5);
        this.Rock7.func_78785_a(f5);
        this.Rock8.func_78785_a(f5);
    }

    public void renderLog(float f5) {
        this.Log3.func_78785_a(f5);
        this.Log1.func_78785_a(f5);
        this.Log4.func_78785_a(f5);
        this.Log2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

