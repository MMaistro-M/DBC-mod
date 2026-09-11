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

public class ModelCrate
extends ModelBase {
    ModelRenderer sticky1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer sticky2;
    ModelRenderer sticky3;
    ModelRenderer sticky4;
    ModelRenderer core;
    ModelRenderer sticky1top;
    ModelRenderer sticky2top;
    ModelRenderer sticky3top;
    ModelRenderer sticky4top;
    ModelRenderer sidestick2;
    ModelRenderer sidestick3;
    ModelRenderer sidestick1;
    ModelRenderer sidestick4;
    ModelRenderer sidestuff2;
    ModelRenderer sidestuff1;
    ModelRenderer sidestuff3;
    ModelRenderer sidestuff4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public ModelCrate() {
        this.sticky1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 12);
        this.sticky1.func_78793_a(6.0f, 22.0f, -6.0f);
        this.sticky2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky2.func_78789_a(0.0f, 0.0f, 0.0f, 12, 2, 2);
        this.sticky2.func_78793_a(-6.0f, 22.0f, -8.0f);
        this.sticky3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 12);
        this.sticky3.func_78793_a(-8.0f, 22.0f, -6.0f);
        this.sticky4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sticky4.func_78789_a(0.0f, 0.0f, 0.0f, 12, 2, 2);
        this.sticky4.func_78793_a(-6.0f, 22.0f, 6.0f);
        this.core = new ModelRenderer((ModelBase)this, 0, 0);
        this.core.func_78790_a(-8.0f, 0.0f, -8.0f, 16, 16, 16, -1.0f);
        this.core.func_78793_a(0.0f, 8.0f, 0.0f);
        this.sticky1top = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky1top.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 12);
        this.sticky1top.func_78793_a(6.0f, 8.0f, -6.0f);
        this.sticky2top = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky2top.func_78789_a(0.0f, 0.0f, 0.0f, 12, 2, 2);
        this.sticky2top.func_78793_a(-6.0f, 8.0f, 6.0f);
        this.sticky3top = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky3top.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 12);
        this.sticky3top.func_78793_a(-8.0f, 8.0f, -6.0f);
        this.sticky4top = new ModelRenderer((ModelBase)this, 0, 0);
        this.sticky4top.func_78789_a(0.0f, 0.0f, 0.0f, 12, 2, 2);
        this.sticky4top.func_78793_a(-6.0f, 8.0f, -8.0f);
        this.sidestick1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestick1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 16, 2);
        this.sidestick1.func_78793_a(-8.0f, 8.0f, 6.0f);
        this.sidestick2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestick2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 16, 2);
        this.sidestick2.func_78793_a(6.0f, 8.0f, 6.0f);
        this.sidestick3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestick3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 16, 2);
        this.sidestick3.func_78793_a(-8.0f, 8.0f, -8.0f);
        this.sidestick4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestick4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 16, 2);
        this.sidestick4.func_78793_a(6.0f, 8.0f, -8.0f);
        this.sidestuff1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestuff1.func_78789_a(0.0f, 1.0f, 0.0f, 1, 18, 2);
        this.sidestuff1.func_78793_a(6.0f, 8.5f, -6.5f);
        this.setRotation(this.sidestuff1, -0.7853982f, 1.570796f, 0.0f);
        this.sidestuff2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestuff2.func_78789_a(0.0f, -1.0f, 0.0f, 1, 18, 2);
        this.sidestuff2.func_78793_a(-7.5f, 9.5f, 5.0f);
        this.setRotation(this.sidestuff2, -0.7853982f, 0.0f, 0.0f);
        this.sidestuff3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestuff3.func_78789_a(0.0f, 1.0f, 0.0f, 1, 18, 2);
        this.sidestuff3.func_78793_a(7.5f, 8.5f, -6.0f);
        this.setRotation(this.sidestuff3, -0.7853982f, 3.141593f, 0.0f);
        this.sidestuff4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sidestuff4.func_78789_a(0.0f, 1.0f, 0.0f, 1, 18, 2);
        this.sidestuff4.func_78793_a(-6.0f, 8.5f, 6.5f);
        this.setRotation(this.sidestuff4, -0.7853982f, -1.570796f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 18, 1, 2);
        this.Shape1.func_78793_a(-5.5f, 22.5f, -7.0f);
        this.setRotation(this.Shape1, 0.0f, -0.7853982f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 18, 1, 2);
        this.Shape2.func_78793_a(-5.5f, 8.5f, -7.0f);
        this.setRotation(this.Shape2, 0.0f, -0.7853982f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.sticky1.func_78785_a(f5);
        this.sticky2.func_78785_a(f5);
        this.sticky3.func_78785_a(f5);
        this.sticky4.func_78785_a(f5);
        this.core.func_78785_a(f5);
        this.sticky1top.func_78785_a(f5);
        this.sticky2top.func_78785_a(f5);
        this.sticky3top.func_78785_a(f5);
        this.sticky4top.func_78785_a(f5);
        this.sidestick1.func_78785_a(f5);
        this.sidestick2.func_78785_a(f5);
        this.sidestick3.func_78785_a(f5);
        this.sidestick4.func_78785_a(f5);
        this.sidestuff1.func_78785_a(f5);
        this.sidestuff2.func_78785_a(f5);
        this.sidestuff3.func_78785_a(f5);
        this.sidestuff4.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

