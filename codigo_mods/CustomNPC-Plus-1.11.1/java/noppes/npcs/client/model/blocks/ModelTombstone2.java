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

public class ModelTombstone2
extends ModelBase {
    ModelRenderer Top = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer mid;

    public ModelTombstone2() {
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 10, 1, 4);
        this.Top.func_78793_a(-5.0f, 9.0f, -2.0f);
        this.mid = new ModelRenderer((ModelBase)this, 0, 0);
        this.mid.func_78789_a(0.0f, 0.0f, 0.0f, 12, 14, 4);
        this.mid.func_78793_a(-6.0f, 10.0f, -2.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Top.func_78785_a(f5);
        this.mid.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

