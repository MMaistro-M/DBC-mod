/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelScaleRenderer;

public class ModelHeadwear
extends ModelScaleRenderer {
    public ModelHeadwear(ModelBase base) {
        super(base);
        Model2DRenderer right = new Model2DRenderer(base, 32.0f, 8.0f, 8, 8, 64.0f, 64.0f);
        right.func_78793_a(-4.641f, 0.8f, 4.64f);
        right.setScale(0.58f);
        right.setThickness(0.65f);
        this.setRotation(right, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 48.0f, 8.0f, 8, 8, 64.0f, 64.0f);
        left.func_78793_a(4.639f, 0.8f, -4.64f);
        left.setScale(0.58f);
        left.setThickness(0.65f);
        this.setRotation(left, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer front = new Model2DRenderer(base, 40.0f, 8.0f, 8, 8, 64.0f, 64.0f);
        front.func_78793_a(-4.64f, 0.801f, -4.641f);
        front.setScale(0.58f);
        front.setThickness(0.65f);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 56.0f, 8.0f, 8, 8, 64.0f, 64.0f);
        back.func_78793_a(4.64f, 0.801f, 4.639f);
        back.setScale(0.58f);
        back.setThickness(0.65f);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer top = new Model2DRenderer(base, 40.0f, 0.0f, 8, 8, 64.0f, 64.0f);
        top.func_78793_a(-4.64f, -8.5f, -4.64f);
        top.setScale(0.5799f);
        top.setThickness(0.65f);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 48.0f, 0.0f, 8, 8, 64.0f, 64.0f);
        bottom.func_78793_a(-4.64f, 0.0f, -4.64f);
        bottom.setScale(0.5799f);
        bottom.setThickness(0.65f);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public ModelHeadwear(ModelBase base, boolean steve32) {
        super(base);
        Model2DRenderer right = new Model2DRenderer(base, 32.0f, 8.0f, 8, 8, 64.0f, 32.0f);
        right.func_78793_a(-4.641f, 0.8f, 4.64f);
        right.setScale(0.58f);
        right.setThickness(0.65f);
        this.setRotation(right, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 48.0f, 8.0f, 8, 8, 64.0f, 32.0f);
        left.func_78793_a(4.639f, 0.8f, -4.64f);
        left.setScale(0.58f);
        left.setThickness(0.65f);
        this.setRotation(left, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer front = new Model2DRenderer(base, 40.0f, 8.0f, 8, 8, 64.0f, 32.0f);
        front.func_78793_a(-4.64f, 0.801f, -4.641f);
        front.setScale(0.58f);
        front.setThickness(0.65f);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 56.0f, 8.0f, 8, 8, 64.0f, 32.0f);
        back.func_78793_a(4.64f, 0.801f, 4.639f);
        back.setScale(0.58f);
        back.setThickness(0.65f);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer top = new Model2DRenderer(base, 40.0f, 0.0f, 8, 8, 64.0f, 32.0f);
        top.func_78793_a(-4.64f, -8.5f, -4.64f);
        top.setScale(0.5799f);
        top.setThickness(0.65f);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 48.0f, 0.0f, 8, 8, 64.0f, 32.0f);
        bottom.func_78793_a(-4.64f, 0.0f, -4.64f);
        bottom.setScale(0.5799f);
        bottom.setThickness(0.65f);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    @Override
    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

