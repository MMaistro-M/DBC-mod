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

public class ModelLegacyTallLamp
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 6, 2);
    ModelRenderer MiddleStick;
    ModelRenderer LampShadeStick1;
    ModelRenderer LampShadeStick2;
    ModelRenderer LampShadeStick3;
    ModelRenderer LampShadeStick4;

    public ModelLegacyTallLamp() {
        this.Base.func_78789_a(-6.0f, 0.0f, -6.0f, 12, 1, 12);
        this.Base.func_78793_a(0.0f, 23.0f, 0.0f);
        this.MiddleStick = new ModelRenderer((ModelBase)this, 12, 2);
        this.MiddleStick.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 28, 2);
        this.MiddleStick.func_78793_a(0.0f, -5.0f, 0.0f);
        this.LampShadeStick1 = new ModelRenderer((ModelBase)this, 0, 30);
        this.LampShadeStick1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.LampShadeStick1.func_78793_a(1.0f, -1.0f, -0.5f);
        this.LampShadeStick2 = new ModelRenderer((ModelBase)this, 0, 30);
        this.LampShadeStick2.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.LampShadeStick2.func_78793_a(-0.5f, -1.0f, -1.0f);
        this.setRotation(this.LampShadeStick2, 0.0f, 1.570796f, 0.0f);
        this.LampShadeStick3 = new ModelRenderer((ModelBase)this, 0, 30);
        this.LampShadeStick3.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.LampShadeStick3.func_78793_a(-1.0f, -1.0f, 0.5f);
        this.setRotation(this.LampShadeStick3, 0.0f, 3.141593f, 0.0f);
        this.LampShadeStick4 = new ModelRenderer((ModelBase)this, 0, 30);
        this.LampShadeStick4.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.LampShadeStick4.func_78793_a(0.5f, -1.0f, 1.0f);
        this.setRotation(this.LampShadeStick4, 0.0f, -1.570796f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.MiddleStick.func_78785_a(f5);
        this.LampShadeStick1.func_78785_a(f5);
        this.LampShadeStick2.func_78785_a(f5);
        this.LampShadeStick3.func_78785_a(f5);
        this.LampShadeStick4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

