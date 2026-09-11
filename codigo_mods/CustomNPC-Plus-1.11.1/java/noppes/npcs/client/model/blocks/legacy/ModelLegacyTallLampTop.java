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

public class ModelLegacyTallLampTop
extends ModelBase {
    ModelRenderer LampShade1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer LampShade3;
    ModelRenderer LampShade2;
    ModelRenderer LampShade4;

    public ModelLegacyTallLampTop() {
        this.LampShade1.func_78789_a(-0.5f, -6.0f, -6.0f, 1, 12, 12);
        this.LampShade1.func_78793_a(6.0f, -1.0f, 0.0f);
        this.LampShade3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.LampShade3.func_78789_a(-6.0f, -6.0f, -0.5f, 12, 12, 1);
        this.LampShade3.func_78793_a(0.0f, -1.0f, -6.0f);
        this.LampShade2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.LampShade2.func_78789_a(-0.5f, -6.0f, -6.0f, 1, 12, 12);
        this.LampShade2.func_78793_a(-6.0f, -1.0f, 0.0f);
        this.LampShade4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.LampShade4.func_78789_a(-6.0f, -6.0f, -0.5f, 12, 12, 1);
        this.LampShade4.func_78793_a(0.0f, -1.0f, 6.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.LampShade1.func_78785_a(f5);
        this.LampShade3.func_78785_a(f5);
        this.LampShade2.func_78785_a(f5);
        this.LampShade4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

