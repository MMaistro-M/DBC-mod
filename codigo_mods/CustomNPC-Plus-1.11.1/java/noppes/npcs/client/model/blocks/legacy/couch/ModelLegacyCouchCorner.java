/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.legacy.couch;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLegacyCouchCorner
extends ModelBase {
    ModelRenderer Leg1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Leg5;
    ModelRenderer Back;
    ModelRenderer Back2;
    ModelRenderer Bottom;
    ModelRenderer Bottom2;

    public ModelLegacyCouchCorner() {
        this.Leg1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Leg1.func_78793_a(7.0f, 23.0f, 6.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.Leg2.func_78793_a(4.0f, 23.0f, -8.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.Leg3.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Leg4.func_78793_a(-8.0f, 23.0f, 6.0f);
        this.Leg5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Leg5.func_78793_a(7.0f, 23.0f, -6.0f);
        this.Back = new ModelRenderer((ModelBase)this, 1, 1);
        this.Back.func_78789_a(0.0f, 0.0f, 0.0f, 1, 15, 15);
        this.Back.func_78793_a(-8.0f, 6.0f, -8.0f);
        this.Back2 = new ModelRenderer((ModelBase)this, 14, 15);
        this.Back2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 15, 1);
        this.Back2.func_78793_a(-8.0f, 6.0f, 7.0f);
        this.Bottom = new ModelRenderer((ModelBase)this, 4, 0);
        this.Bottom.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 14);
        this.Bottom.func_78793_a(-8.0f, 21.0f, -6.0f);
        this.Bottom2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bottom2.func_78789_a(0.0f, 0.0f, 0.0f, 14, 2, 2);
        this.Bottom2.func_78793_a(-8.0f, 21.0f, -8.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Leg1.func_78785_a(f5);
        this.Leg2.func_78785_a(f5);
        this.Leg3.func_78785_a(f5);
        this.Leg4.func_78785_a(f5);
        this.Leg5.func_78785_a(f5);
        this.Back.func_78785_a(f5);
        this.Back2.func_78785_a(f5);
        this.Bottom.func_78785_a(f5);
        this.Bottom2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

