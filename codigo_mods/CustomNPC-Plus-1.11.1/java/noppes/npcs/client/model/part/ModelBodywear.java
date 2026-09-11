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

public class ModelBodywear
extends ModelScaleRenderer {
    public ModelBodywear(ModelBase base, int height, int width) {
        super(base);
        float thick = 0.5f;
        Model2DRenderer front = new Model2DRenderer(base, 20.0f, 36.0f, 8, 12, width, height);
        front.func_78793_a(-4.53f, 12.535f, -2.52f);
        front.setScale(0.85f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 32.0f, 36.0f, 8, 12, width, height);
        back.func_78793_a(4.53f, 12.535f, 2.52f);
        back.setScale(0.85f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 28.0f, 36.0f, 4, 12, width, height);
        right.func_78793_a(4.03f, 12.55f, 2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 16.0f, 36.0f, 4, 12, width, height);
        left.func_78793_a(-4.03f, 12.55f, -2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 20.0f, 32.0f, 8, 4, width, height);
        top.func_78793_a(-4.5f, -0.6f, -2.525f);
        top.setScale(0.2815f, 0.315f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 28.0f, 32.0f, 8, 4, width, height);
        bottom.func_78793_a(-4.5f, 12.11f, -2.525f);
        bottom.setScale(0.2815f, 0.315f);
        bottom.setThickness(thick);
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

