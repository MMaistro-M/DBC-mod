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

public class ModelInk
extends ModelBase {
    ModelRenderer InkMid = new ModelRenderer((ModelBase)this, 0, 25);
    ModelRenderer InkTop;
    ModelRenderer InkBottom;
    ModelRenderer Shape1;
    ModelRenderer InkBottom2;

    public ModelInk() {
        this.InkMid.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.InkMid.func_78793_a(5.0f, 21.0f, 3.5f);
        this.InkTop = new ModelRenderer((ModelBase)this, 0, 22);
        this.InkTop.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.InkTop.func_78793_a(4.5f, 20.0f, 3.0f);
        this.InkBottom = new ModelRenderer((ModelBase)this, 3, 16);
        this.InkBottom.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.InkBottom.func_78793_a(4.0f, 22.0f, 2.5f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 0, 13, 3);
        this.Shape1.func_78793_a(5.5f, 10.0f, 2.5f);
        this.InkBottom2 = new ModelRenderer((ModelBase)this, 0, 27);
        this.InkBottom2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.InkBottom2.func_78793_a(4.0f, 23.0f, 2.5f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.func_78785_a(f5);
        this.InkMid.func_78785_a(f5);
        this.InkTop.func_78785_a(f5);
        this.InkBottom2.func_78785_a(f5);
        this.InkBottom.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

