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

public class ModelBeam
extends ModelBase {
    ModelRenderer Bar = new ModelRenderer((ModelBase)this, 6, 6);

    public ModelBeam() {
        this.Bar.func_78789_a(0.0f, 0.0f, 0.0f, 5, 5, 12);
        this.Bar.func_78793_a(-2.5f, 13.5f, -4.0f);
        this.Bar.func_78787_b(64, 32);
        this.Bar.field_78809_i = true;
        this.setRotation(this.Bar, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Bar.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

