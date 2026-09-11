/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.legacy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLegacyCandle
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Bar1;
    ModelRenderer Bar2;
    ModelRenderer Bar3;
    ModelRenderer Bar4;
    ModelRenderer Wax;

    public ModelLegacyCandle() {
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base.func_78793_a(-2.0f, 23.0f, -2.0f);
        this.Bar1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Bar1.func_78793_a(-3.0f, 22.0f, -3.0f);
        this.Bar2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Bar2.func_78793_a(2.0f, 22.0f, -3.0f);
        this.Bar3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Bar3.func_78793_a(-2.0f, 22.0f, -3.0f);
        this.Bar4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Bar4.func_78793_a(-2.0f, 22.0f, 2.0f);
        this.Wax = new ModelRenderer((ModelBase)this, 16, 0);
        this.Wax.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax.func_78793_a(-1.0f, 19.0f, -1.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Bar1.func_78785_a(f5);
        this.Bar2.func_78785_a(f5);
        this.Bar3.func_78785_a(f5);
        this.Bar4.func_78785_a(f5);
        this.Wax.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

