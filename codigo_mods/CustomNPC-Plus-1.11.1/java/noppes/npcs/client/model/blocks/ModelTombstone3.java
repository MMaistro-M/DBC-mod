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

public class ModelTombstone3
extends ModelBase {
    ModelRenderer Bottom = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Piece5;
    ModelRenderer Piece2;
    ModelRenderer Piece1;
    ModelRenderer Piece4;
    ModelRenderer Piece3;
    ModelRenderer Piece7;

    public ModelTombstone3() {
        this.Bottom.func_78789_a(0.0f, 0.0f, 0.0f, 12, 5, 4);
        this.Bottom.func_78793_a(-6.0f, 19.0f, -2.0f);
        this.Piece5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece5.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Piece5.func_78793_a(-4.0f, 16.0f, -2.0f);
        this.Piece2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.Piece2.func_78793_a(2.0f, 17.0f, -2.0f);
        this.Piece1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece1.func_78789_a(0.0f, 0.0f, 0.0f, 6, 2, 4);
        this.Piece1.func_78793_a(-5.0f, 17.0f, -2.0f);
        this.Piece4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 4);
        this.Piece4.func_78793_a(-5.0f, 14.0f, -2.0f);
        this.Piece3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 4);
        this.Piece3.func_78793_a(3.0f, 16.0f, -2.0f);
        this.Piece7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Piece7.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 4);
        this.Piece7.func_78793_a(-4.0f, 15.0f, -2.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Bottom.func_78785_a(f5);
        this.Piece5.func_78785_a(f5);
        this.Piece2.func_78785_a(f5);
        this.Piece1.func_78785_a(f5);
        this.Piece4.func_78785_a(f5);
        this.Piece3.func_78785_a(f5);
        this.Piece7.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

