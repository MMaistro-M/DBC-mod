/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSign
extends ModelBase {
    public ModelRenderer Sign = new ModelRenderer((ModelBase)this, 0, 22);
    ModelRenderer Chain2;
    ModelRenderer Bar;
    ModelRenderer Chain1;

    public ModelSign() {
        this.Sign.func_78789_a(0.0f, 0.0f, 0.0f, 14, 9, 1);
        this.Sign.func_78793_a(-7.0f, 12.0f, -0.5f);
        this.setRotation(this.Sign, 0.0174533f, 0.0f, 0.0f);
        this.Chain2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 2);
        this.Chain2.func_78793_a(5.0f, 11.0f, -1.0f);
        this.Bar = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 1);
        this.Bar.func_78793_a(-8.0f, 10.0f, -0.5f);
        this.Chain1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 2);
        this.Chain1.func_78793_a(-6.0f, 11.0f, -1.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Chain2.func_78785_a(f5);
        this.Bar.func_78785_a(f5);
        this.Chain1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

