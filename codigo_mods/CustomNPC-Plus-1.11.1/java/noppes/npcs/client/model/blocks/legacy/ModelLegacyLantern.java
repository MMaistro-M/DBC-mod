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

public class ModelLegacyLantern
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 0, 6);
    ModelRenderer Top1;
    ModelRenderer Top2;
    ModelRenderer Top3;
    ModelRenderer Handle;
    ModelRenderer Shape1;

    public ModelLegacyLantern() {
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 4, 7, 4);
        this.Base.func_78793_a(-2.0f, 16.0f, -2.0f);
        this.Top1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 5);
        this.Top1.func_78793_a(-2.5f, 16.0f, -2.5f);
        this.Top2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Top2.func_78793_a(-2.0f, 15.5f, -2.0f);
        this.Top3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.Top3.func_78793_a(-1.5f, 15.0f, -1.5f);
        this.Handle = new ModelRenderer((ModelBase)this, 24, 0);
        this.Handle.func_78789_a(0.0f, 0.0f, 0.0f, 3, 0, 3);
        this.Handle.func_78793_a(0.0f, 15.0f, 0.0f);
        this.setRotation(this.Handle, 0.296706f, 0.1745329f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Shape1.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.Shape1.func_78793_a(0.0f, 23.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Top1.func_78785_a(f5);
        this.Top2.func_78785_a(f5);
        this.Top3.func_78785_a(f5);
        this.Handle.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

