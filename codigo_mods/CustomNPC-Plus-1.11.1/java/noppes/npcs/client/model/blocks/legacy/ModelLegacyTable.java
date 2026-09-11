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

public class ModelLegacyTable
extends ModelBase {
    public ModelRenderer Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
    public ModelRenderer Table;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;

    public ModelLegacyTable() {
        this.Shape1.field_78809_i = true;
        this.Shape1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 14, 2);
        this.Shape1.func_78793_a(-6.0f, 10.0f, 6.0f);
        this.Table = new ModelRenderer((ModelBase)this, 0, 0);
        this.Table.func_78789_a(0.0f, -2.0f, 0.0f, 16, 2, 16);
        this.Table.func_78793_a(-8.0f, 10.0f, -8.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 14, 2);
        this.Shape3.func_78793_a(6.0f, 10.0f, -6.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape4.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 14, 2);
        this.Shape4.func_78793_a(6.0f, 10.0f, 6.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.field_78809_i = true;
        this.Shape5.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 14, 2);
        this.Shape5.func_78793_a(-6.0f, 10.0f, -6.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.func_78785_a(f5);
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

