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

public class ModelLegacyWall
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 0, 6);
    ModelRenderer Top1;
    ModelRenderer Top2;
    ModelRenderer Top3;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public ModelLegacyWall() {
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 4, 7, 4);
        this.Base.func_78793_a(-2.0f, 14.0f, 1.0f);
        this.Top1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 5);
        this.Top1.func_78793_a(-2.5f, 14.0f, 0.5f);
        this.Top2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Top2.func_78793_a(-2.0f, 13.5f, 1.0f);
        this.Top3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.Top3.func_78793_a(-1.5f, 13.0f, 1.5f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.Shape2.func_78793_a(-0.5f, 11.0f, 3.5f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 1);
        this.Shape3.func_78793_a(0.0f, 9.5f, 6.5f);
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.7853982f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape4.func_78793_a(-0.5f, 10.5f, 2.5f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.Shape5.func_78793_a(0.0f, 8.7f, 7.0f);
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.7853982f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Top1.func_78785_a(f5);
        this.Top2.func_78785_a(f5);
        this.Top3.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

