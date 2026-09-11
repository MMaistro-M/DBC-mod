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

public class ModelLegacyCeiling
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 0, 6);
    ModelRenderer Top1;
    ModelRenderer Top2;
    ModelRenderer Top3;
    ModelRenderer Chain8;
    ModelRenderer Chain1;
    ModelRenderer Chain2;
    ModelRenderer Chain3;
    ModelRenderer Chain4;
    ModelRenderer Chain5;
    ModelRenderer Chain6;
    ModelRenderer Chain7;
    ModelRenderer TippyTop1;
    ModelRenderer TippyTop2;
    ModelRenderer Shape3;
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public ModelLegacyCeiling() {
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 4, 7, 4);
        this.Base.func_78793_a(-2.0f, 17.0f, -2.0f);
        this.Top1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 5);
        this.Top1.func_78793_a(-2.5f, 17.0f, -2.5f);
        this.Top2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Top2.func_78793_a(-2.0f, 16.5f, -2.0f);
        this.Top3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.Top3.func_78793_a(-1.5f, 16.0f, -1.5f);
        this.Chain8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain8.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Chain8.func_78793_a(-0.5f, 14.0f, -1.5f);
        this.Chain1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain1.func_78793_a(0.5f, 8.0f, -0.5f);
        this.Chain2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain2.func_78793_a(-1.5f, 8.0f, -0.5f);
        this.Chain3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain3.func_78793_a(-0.5f, 10.0f, 0.5f);
        this.Chain4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain4.func_78793_a(-0.5f, 10.0f, -1.5f);
        this.Chain5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain5.func_78793_a(-1.5f, 12.0f, -0.5f);
        this.Chain6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain6.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Chain6.func_78793_a(0.5f, 12.0f, -0.5f);
        this.Chain7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chain7.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Chain7.func_78793_a(-0.5f, 14.0f, 0.5f);
        this.TippyTop1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.TippyTop1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.TippyTop1.func_78793_a(-2.8f, 8.0f, 0.0f);
        this.setRotation(this.TippyTop1, 0.0f, 0.7853982f, 0.0f);
        this.TippyTop2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.TippyTop2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.TippyTop2.func_78793_a(-2.1f, 8.5f, 0.0f);
        this.setRotation(this.TippyTop2, 0.0f, 0.7853982f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Shape3.func_78793_a(-0.5f, 14.0f, -0.5f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Shape1.func_78793_a(-0.5f, 10.0f, -0.5f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Shape2.func_78793_a(-0.5f, 12.0f, -0.5f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Top1.func_78785_a(f5);
        this.Top2.func_78785_a(f5);
        this.Top3.func_78785_a(f5);
        this.Chain8.func_78785_a(f5);
        this.Chain1.func_78785_a(f5);
        this.Chain2.func_78785_a(f5);
        this.Chain3.func_78785_a(f5);
        this.Chain4.func_78785_a(f5);
        this.Chain5.func_78785_a(f5);
        this.Chain6.func_78785_a(f5);
        this.Chain7.func_78785_a(f5);
        this.TippyTop1.func_78785_a(f5);
        this.TippyTop2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

