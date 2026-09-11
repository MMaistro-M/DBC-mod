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

public class ModelLegacyCandleWall
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Bar1;
    ModelRenderer Bar2;
    ModelRenderer Bar3;
    ModelRenderer Bar4;
    ModelRenderer Wax;
    ModelRenderer Wall2;
    ModelRenderer Wall1;
    ModelRenderer Bar5;
    ModelRenderer Bar6;

    public ModelLegacyCandleWall() {
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base.func_78793_a(-2.0f, 13.0f, -4.0f);
        this.Bar1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Bar1.func_78793_a(-3.0f, 12.0f, -5.0f);
        this.Bar2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Bar2.func_78793_a(2.0f, 12.0f, -5.0f);
        this.Bar3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Bar3.func_78793_a(-2.0f, 12.0f, -5.0f);
        this.Bar4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Bar4.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Wax = new ModelRenderer((ModelBase)this, 16, 0);
        this.Wax.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax.func_78793_a(-1.0f, 9.0f, -3.0f);
        this.Wall2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Wall2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 1);
        this.Wall2.func_78793_a(0.0f, 13.7f, -7.5f);
        this.setRotation(this.Wall2, 0.0f, 0.0f, 0.7853982f);
        this.Wall1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Wall1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.Wall1.func_78793_a(0.0f, 13.0f, -8.0f);
        this.setRotation(this.Wall1, 0.0f, 0.0f, 0.7853982f);
        this.Bar5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Bar5.func_78793_a(-0.5f, 13.5f, -2.5f);
        this.Bar6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bar6.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
        this.Bar6.func_78793_a(-0.5f, 15.5f, -6.5f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Bar1.func_78785_a(f5);
        this.Bar2.func_78785_a(f5);
        this.Bar3.func_78785_a(f5);
        this.Bar4.func_78785_a(f5);
        this.Wax.func_78785_a(f5);
        this.Wall2.func_78785_a(f5);
        this.Wall1.func_78785_a(f5);
        this.Bar5.func_78785_a(f5);
        this.Bar6.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

