/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;

public class ModelBreasts
extends ModelPartInterface {
    private Model2DRenderer breasts;
    private ModelRenderer breasts2;
    private ModelRenderer breasts3;

    public ModelBreasts(ModelMPM base, int width, int height) {
        super(base);
        this.breasts = new Model2DRenderer((ModelBase)base, 20.0f, 22.0f, 8, 3, width, height);
        this.breasts.func_78787_b(width, height);
        this.breasts.func_78793_a(-3.6f, 5.2f, -3.0f);
        this.breasts.setScale(0.17f, 0.19f);
        this.breasts.setThickness(1.0f);
        this.func_78792_a(this.breasts);
        this.breasts2 = new ModelRenderer((ModelBase)base);
        this.breasts2.func_78787_b(width, height);
        this.func_78792_a(this.breasts2);
        Model2DRenderer bottom = new Model2DRenderer((ModelBase)base, 20.0f, 22.0f, 8, 4, width, height);
        bottom.func_78787_b(width, height);
        bottom.func_78793_a(-3.6f, 5.0f, -3.1f);
        bottom.setScale(0.225f, 0.2f);
        bottom.setThickness(2.0f);
        bottom.field_78795_f = -0.31415927f;
        this.breasts2.func_78792_a((ModelRenderer)bottom);
        this.breasts3 = new ModelRenderer((ModelBase)base);
        this.breasts3.func_78787_b(width, height);
        this.func_78792_a(this.breasts3);
        Model2DRenderer right = new Model2DRenderer((ModelBase)base, 20.0f, 22.0f, 3, 2, width, height);
        right.func_78787_b(width, height);
        right.func_78793_a(-3.8f, 5.3f, -3.6f);
        right.setScale(0.12f, 0.14f);
        right.setThickness(1.75f);
        this.breasts3.func_78792_a((ModelRenderer)right);
        Model2DRenderer right2 = new Model2DRenderer((ModelBase)base, 20.0f, 22.0f, 3, 1, width, height);
        right2.func_78787_b(width, height);
        right2.func_78793_a(-3.8f, 4.1f, -3.14f);
        right2.setScale(0.06f, 0.07f);
        right2.setThickness(1.75f);
        right2.field_78795_f = 0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)right2);
        Model2DRenderer right3 = new Model2DRenderer((ModelBase)base, 20.0f, 24.0f, 3, 1, width, height);
        right3.func_78787_b(width, height);
        right3.func_78793_a(-3.8f, 5.3f, -3.6f);
        right3.setScale(0.06f, 0.07f);
        right3.setThickness(1.75f);
        right3.field_78795_f = -0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)right3);
        Model2DRenderer right4 = new Model2DRenderer((ModelBase)base, 23.0f, 22.0f, 1, 2, width, height);
        right4.func_78787_b(width, height);
        right4.func_78793_a(-1.8f, 5.3f, -3.14f);
        right4.setScale(0.12f, 0.14f);
        right4.setThickness(1.75f);
        right4.field_78796_g = 0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)right4);
        Model2DRenderer left = new Model2DRenderer((ModelBase)base, 25.0f, 22.0f, 3, 2, width, height);
        left.func_78787_b(width, height);
        left.func_78793_a(0.8f, 5.3f, -3.6f);
        left.setScale(0.12f, 0.14f);
        left.setThickness(1.75f);
        this.breasts3.func_78792_a((ModelRenderer)left);
        Model2DRenderer left2 = new Model2DRenderer((ModelBase)base, 25.0f, 22.0f, 3, 1, width, height);
        left2.func_78787_b(width, height);
        left2.func_78793_a(0.8f, 4.1f, -3.18f);
        left2.setScale(0.06f, 0.07f);
        left2.setThickness(1.75f);
        left2.field_78795_f = 0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)left2);
        Model2DRenderer left3 = new Model2DRenderer((ModelBase)base, 25.0f, 24.0f, 3, 1, width, height);
        left3.func_78787_b(width, height);
        left3.func_78793_a(0.8f, 5.3f, -3.6f);
        left3.setScale(0.06f, 0.07f);
        left3.setThickness(1.75f);
        left3.field_78795_f = -0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)left3);
        Model2DRenderer left4 = new Model2DRenderer((ModelBase)base, 24.0f, 22.0f, 1, 2, width, height);
        left4.func_78787_b(width, height);
        left4.func_78793_a(0.8f, 5.3f, -3.6f);
        left4.setScale(0.12f, 0.14f);
        left4.setThickness(1.75f);
        left4.field_78796_g = -0.34906584f;
        this.breasts3.func_78792_a((ModelRenderer)left4);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    }

    @Override
    public void initData(ModelData data) {
        this.field_78807_k = data.breasts == 0;
        this.breasts.field_78807_k = data.breasts != 1;
        this.breasts2.field_78807_k = data.breasts != 2;
        this.breasts3.field_78807_k = data.breasts != 3;
    }
}

