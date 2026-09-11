/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.banner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBannerFloor
extends ModelBase {
    private final ModelRenderer FloorBanner;
    private final ModelRenderer Leg4_r1;
    private final ModelRenderer Leg3_r1;
    private final ModelRenderer Leg2_r1;
    private final ModelRenderer Leg1_r1;
    private final ModelRenderer Point1_r1;

    public ModelBannerFloor() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.FloorBanner = new ModelRenderer((ModelBase)this);
        this.FloorBanner.func_78793_a(0.0f, 24.0f, 0.0f);
        this.FloorBanner.field_78804_l.add(new ModelBox(this.FloorBanner, 0, 0, -8.0f, -32.0f, -1.0f, 16, 3, 3, 0.0f));
        this.FloorBanner.field_78804_l.add(new ModelBox(this.FloorBanner, 0, 8, -6.0f, -36.0f, 0.5f, 4, 4, 0, 0.0f));
        this.FloorBanner.field_78804_l.add(new ModelBox(this.FloorBanner, 0, 13, -1.0f, -29.0f, -0.5f, 2, 26, 2, 0.0f));
        this.FloorBanner.field_78804_l.add(new ModelBox(this.FloorBanner, 18, 28, -6.0f, -2.0f, -5.0f, 12, 2, 11, 0.0f));
        this.FloorBanner.field_78804_l.add(new ModelBox(this.FloorBanner, 26, 19, -4.0f, -4.0f, -3.0f, 8, 2, 7, 0.0f));
        this.Leg4_r1 = new ModelRenderer((ModelBase)this);
        this.Leg4_r1.func_78793_a(-3.5f, -2.1f, 0.5f);
        this.FloorBanner.func_78792_a(this.Leg4_r1);
        this.setRotationAngle(this.Leg4_r1, 0.0f, 0.0f, 1.1781f);
        this.Leg4_r1.field_78804_l.add(new ModelBox(this.Leg4_r1, 10, 29, -1.0f, -3.0f, -1.0f, 2, 6, 2, 0.0f));
        this.Leg3_r1 = new ModelRenderer((ModelBase)this);
        this.Leg3_r1.func_78793_a(0.0f, -2.1f, 4.0f);
        this.FloorBanner.func_78792_a(this.Leg3_r1);
        this.setRotationAngle(this.Leg3_r1, 1.1781f, 0.0f, 0.0f);
        this.Leg3_r1.field_78804_l.add(new ModelBox(this.Leg3_r1, 10, 29, -1.0f, -3.0f, -1.0f, 2, 6, 2, 0.0f));
        this.Leg2_r1 = new ModelRenderer((ModelBase)this);
        this.Leg2_r1.func_78793_a(0.0f, -2.1f, -3.0f);
        this.FloorBanner.func_78792_a(this.Leg2_r1);
        this.setRotationAngle(this.Leg2_r1, -1.1781f, 0.0f, 0.0f);
        this.Leg2_r1.field_78804_l.add(new ModelBox(this.Leg2_r1, 10, 29, -1.0f, -3.0f, -1.0f, 2, 6, 2, 0.0f));
        this.Leg1_r1 = new ModelRenderer((ModelBase)this);
        this.Leg1_r1.func_78793_a(3.5f, -2.1f, 0.5f);
        this.FloorBanner.func_78792_a(this.Leg1_r1);
        this.setRotationAngle(this.Leg1_r1, 0.0f, 0.0f, -1.1781f);
        this.Leg1_r1.field_78804_l.add(new ModelBox(this.Leg1_r1, 10, 29, -1.0f, -3.0f, -1.0f, 2, 6, 2, 0.0f));
        this.Point1_r1 = new ModelRenderer((ModelBase)this);
        this.Point1_r1.func_78793_a(4.0f, -34.0f, 0.5f);
        this.FloorBanner.func_78792_a(this.Point1_r1);
        this.setRotationAngle(this.Point1_r1, 0.0f, 3.1416f, 0.0f);
        this.Point1_r1.field_78804_l.add(new ModelBox(this.Point1_r1, 0, 8, -2.0f, -2.0f, 0.0f, 4, 4, 0, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.FloorBanner.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

