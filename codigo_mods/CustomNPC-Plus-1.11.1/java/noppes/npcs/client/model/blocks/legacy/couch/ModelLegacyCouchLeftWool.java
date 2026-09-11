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

public class ModelLegacyCouchLeftWool
extends ModelBase {
    ModelRenderer Wool1 = new ModelRenderer((ModelBase)this, 3, 0);
    ModelRenderer Wool2;

    public ModelLegacyCouchLeftWool() {
        this.Wool1.func_78789_a(0.0f, 0.0f, 0.0f, 14, 5, 13);
        this.Wool1.func_78793_a(-6.0f, 16.0f, -6.0f);
        this.Wool2 = new ModelRenderer((ModelBase)this, 14, 0);
        this.Wool2.func_78789_a(0.0f, 0.0f, 0.0f, 14, 10, 2);
        this.Wool2.func_78793_a(-6.0f, 6.0f, 5.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Wool1.func_78785_a(f5);
        this.Wool2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

