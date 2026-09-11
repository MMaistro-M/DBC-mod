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

public class ModelTombstone1
extends ModelBase {
    ModelRenderer Mid = new ModelRenderer((ModelBase)this, 36, 0);
    ModelRenderer OuterEdge1;
    ModelRenderer OuterEdge2;
    ModelRenderer OuterEdgeTop;

    public ModelTombstone1() {
        this.Mid.func_78789_a(0.0f, 0.0f, 0.0f, 10, 14, 3);
        this.Mid.func_78793_a(-5.0f, 10.0f, -1.5f);
        this.OuterEdge1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.OuterEdge1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 16, 4);
        this.OuterEdge1.func_78793_a(-7.0f, 8.0f, -2.0f);
        this.OuterEdge2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.OuterEdge2.field_78809_i = true;
        this.OuterEdge2.func_78789_a(1.0f, 0.0f, 0.0f, 2, 16, 4);
        this.OuterEdge2.func_78793_a(4.0f, 8.0f, -2.0f);
        this.OuterEdgeTop = new ModelRenderer((ModelBase)this, 0, 22);
        this.OuterEdgeTop.func_78789_a(0.0f, 0.0f, 0.0f, 10, 2, 4);
        this.OuterEdgeTop.func_78793_a(-5.0f, 8.0f, -2.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Mid.func_78785_a(f5);
        this.OuterEdge1.func_78785_a(f5);
        this.OuterEdge2.func_78785_a(f5);
        this.OuterEdgeTop.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

