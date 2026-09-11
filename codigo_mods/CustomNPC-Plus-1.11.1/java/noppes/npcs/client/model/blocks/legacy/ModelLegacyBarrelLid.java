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

public class ModelLegacyBarrelLid
extends ModelBase {
    ModelRenderer Top = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Bottom;

    public ModelLegacyBarrelLid() {
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 16, 0, 16);
        this.Top.func_78793_a(-8.0f, 9.0f, -8.0f);
        this.Bottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bottom.func_78789_a(0.0f, 0.0f, 0.0f, 16, 0, 16);
        this.Bottom.func_78793_a(-8.0f, 23.0f, -8.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Bottom.func_78785_a(f5);
        this.Top.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = y;
        model.field_78796_g = x;
        model.field_78808_h = z;
    }
}

