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

public class ModelCouchWoodMiddle
extends ModelBase {
    ModelRenderer Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape19;

    public ModelCouchWoodMiddle() {
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.Shape2.func_78793_a(-8.0f, 21.0f, -6.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape4.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 1);
        this.Shape4.func_78793_a(-8.0f, 7.0f, 7.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 2);
        this.Shape5.func_78793_a(-8.0f, 19.0f, 6.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape7.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 10);
        this.Shape7.func_78793_a(-7.0f, 19.0f, -4.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape8.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.Shape8.func_78793_a(-8.0f, 21.0f, 6.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape9.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 10);
        this.Shape9.func_78793_a(5.0f, 19.0f, -4.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape10.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape10.func_78793_a(-7.0f, 9.0f, 7.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape11.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 2);
        this.Shape11.func_78793_a(-8.0f, 19.0f, -6.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape12.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 10);
        this.Shape12.func_78793_a(-3.0f, 19.0f, -4.0f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape13.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 10);
        this.Shape13.func_78793_a(1.0f, 19.0f, -4.0f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape14.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape14.func_78793_a(-3.0f, 9.0f, 7.0f);
        this.Shape15 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape15.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape15.func_78793_a(1.0f, 9.0f, 7.0f);
        this.Shape16 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape16.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape16.func_78793_a(5.0f, 9.0f, 7.0f);
        this.Shape17 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape17.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.Shape17.func_78793_a(7.0f, 21.0f, 6.0f);
        this.Shape19 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape19.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.Shape19.func_78793_a(7.0f, 21.0f, -6.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Shape2.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
        this.Shape7.func_78785_a(f5);
        this.Shape8.func_78785_a(f5);
        this.Shape9.func_78785_a(f5);
        this.Shape10.func_78785_a(f5);
        this.Shape11.func_78785_a(f5);
        this.Shape12.func_78785_a(f5);
        this.Shape13.func_78785_a(f5);
        this.Shape14.func_78785_a(f5);
        this.Shape15.func_78785_a(f5);
        this.Shape16.func_78785_a(f5);
        this.Shape17.func_78785_a(f5);
        this.Shape19.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

