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

public class ModelShelf
extends ModelBase {
    public ModelRenderer SupportLeft2 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Top;
    public ModelRenderer SupportLeft1;
    public ModelRenderer SupportRight1;
    public ModelRenderer SupportRight2;

    public ModelShelf() {
        this.SupportLeft2.field_78809_i = true;
        this.SupportLeft2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 2);
        this.SupportLeft2.func_78793_a(-7.498f, 9.5f, -0.5f);
        this.setRotation(this.SupportLeft2, 0.7853982f, 0.0f, 0.0f);
        this.Top = new ModelRenderer((ModelBase)this, 5, 0);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 11);
        this.Top.func_78793_a(-8.0f, 8.0f, -3.0f);
        this.SupportLeft1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.SupportLeft1.field_78809_i = true;
        this.SupportLeft1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.SupportLeft1.func_78793_a(-7.5f, 10.0f, 6.0f);
        this.SupportRight1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.SupportRight1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.SupportRight1.func_78793_a(5.5f, 10.0f, 6.0f);
        this.SupportRight2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.SupportRight2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 2);
        this.SupportRight2.func_78793_a(5.498f, 9.5f, -0.5f);
        this.setRotation(this.SupportRight2, 0.7853982f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.SupportLeft2.func_78785_a(f5);
        this.Top.func_78785_a(f5);
        this.SupportLeft1.func_78785_a(f5);
        this.SupportRight1.func_78785_a(f5);
        this.SupportRight2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

