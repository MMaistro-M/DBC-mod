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

public class ModelLegacyBarrel
extends ModelBase {
    ModelRenderer Plank1 = new ModelRenderer((ModelBase)this, 10, 0);
    ModelRenderer Plank2;
    ModelRenderer Plank3;
    ModelRenderer Plank4;
    ModelRenderer Plank5;
    ModelRenderer Plank6;
    ModelRenderer Plank7;
    ModelRenderer Plank8;
    ModelRenderer Plank9;
    ModelRenderer Plank10;
    ModelRenderer Plank11;
    ModelRenderer Plank12;

    public ModelLegacyBarrel() {
        this.Plank1.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank1.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank1, 0.0f, 0.0f, 1.570796f);
        this.Plank2 = new ModelRenderer((ModelBase)this, 10, 8);
        this.Plank2.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank2.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank2, 0.0f, 0.5235988f, 1.570796f);
        this.Plank3 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank3.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank3.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank3, 0.0f, 1.047198f, 1.570796f);
        this.Plank4 = new ModelRenderer((ModelBase)this, 10, 8);
        this.Plank4.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank4.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank4, 0.0f, 1.570796f, 1.570796f);
        this.Plank5 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank5.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank5.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank5, 0.0f, 2.094395f, 1.570796f);
        this.Plank6 = new ModelRenderer((ModelBase)this, 10, 8);
        this.Plank6.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank6.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank6, 0.0f, 2.617994f, 1.570796f);
        this.Plank7 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank7.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank7.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank7, 0.0f, 3.150901f, 1.570796f);
        this.Plank8 = new ModelRenderer((ModelBase)this, 10, 8);
        this.Plank8.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank8.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank8, 0.0f, -2.617994f, 1.570796f);
        this.Plank9 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank9.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank9.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank9, 0.0f, -2.094395f, 1.570796f);
        this.Plank10 = new ModelRenderer((ModelBase)this, 10, 8);
        this.Plank10.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank10.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank10, 0.0f, -1.570796f, 1.570796f);
        this.Plank11 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank11.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank11.func_78793_a(0.0f, 7.01f, 0.0f);
        this.setRotation(this.Plank11, 0.0f, -1.047198f, 1.570796f);
        this.Plank12 = new ModelRenderer((ModelBase)this, 10, 0);
        this.Plank12.func_78789_a(0.0f, 6.5f, -2.0f, 17, 1, 4);
        this.Plank12.func_78793_a(0.0f, 7.0f, 0.0f);
        this.setRotation(this.Plank12, 0.0f, -0.5235988f, 1.570796f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Plank1.func_78785_a(f5);
        this.Plank2.func_78785_a(f5);
        this.Plank3.func_78785_a(f5);
        this.Plank4.func_78785_a(f5);
        this.Plank5.func_78785_a(f5);
        this.Plank6.func_78785_a(f5);
        this.Plank7.func_78785_a(f5);
        this.Plank8.func_78785_a(f5);
        this.Plank9.func_78785_a(f5);
        this.Plank10.func_78785_a(f5);
        this.Plank11.func_78785_a(f5);
        this.Plank12.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = y;
        model.field_78796_g = x;
        model.field_78808_h = z;
    }
}

