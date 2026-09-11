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

public class ModelLegacyBanner
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 3, 1);
    ModelRenderer MiddleStick;
    ModelRenderer StickDecoration;
    ModelRenderer TopDecoration;
    ModelRenderer FlagPole1;
    ModelRenderer FlagPole2;
    ModelRenderer BaseDeco1;
    ModelRenderer BaseDeco2;
    ModelRenderer BaseDeco3;
    ModelRenderer BaseDeco4;

    public ModelLegacyBanner() {
        this.Base.func_78789_a(-7.0f, 0.0f, -7.0f, 14, 1, 14);
        this.Base.func_78793_a(0.0f, 23.0f, 0.0f);
        this.MiddleStick = new ModelRenderer((ModelBase)this, 12, 2);
        this.MiddleStick.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 32, 2);
        this.MiddleStick.func_78793_a(0.0f, -9.0f, 0.0f);
        this.StickDecoration = new ModelRenderer((ModelBase)this, 11, 12);
        this.StickDecoration.func_78789_a(0.0f, 0.0f, 0.0f, 16, 3, 3);
        this.StickDecoration.func_78793_a(-8.0f, -7.5f, -1.5f);
        this.TopDecoration = new ModelRenderer((ModelBase)this, 45, 19);
        this.TopDecoration.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.TopDecoration.func_78793_a(-0.5f, -10.0f, -0.5f);
        this.FlagPole1 = new ModelRenderer((ModelBase)this, 45, 19);
        this.FlagPole1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.FlagPole1.func_78793_a(-7.0f, -6.5f, -2.5f);
        this.FlagPole2 = new ModelRenderer((ModelBase)this, 45, 19);
        this.FlagPole2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.FlagPole2.func_78793_a(6.0f, -6.5f, -2.5f);
        this.BaseDeco1 = new ModelRenderer((ModelBase)this, 1, 14);
        this.BaseDeco1.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.BaseDeco1.func_78793_a(-6.0f, 23.0f, -8.0f);
        this.BaseDeco2 = new ModelRenderer((ModelBase)this, 1, 14);
        this.BaseDeco2.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.BaseDeco2.func_78793_a(-6.0f, 23.0f, 7.0f);
        this.BaseDeco3 = new ModelRenderer((ModelBase)this, 2, 2);
        this.BaseDeco3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 12);
        this.BaseDeco3.func_78793_a(-8.0f, 23.0f, -6.0f);
        this.BaseDeco4 = new ModelRenderer((ModelBase)this, 2, 2);
        this.BaseDeco4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 12);
        this.BaseDeco4.func_78793_a(7.0f, 23.0f, -6.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.MiddleStick.func_78785_a(f5);
        this.StickDecoration.func_78785_a(f5);
        this.TopDecoration.func_78785_a(f5);
        this.FlagPole1.func_78785_a(f5);
        this.FlagPole2.func_78785_a(f5);
        this.BaseDeco1.func_78785_a(f5);
        this.BaseDeco2.func_78785_a(f5);
        this.BaseDeco3.func_78785_a(f5);
        this.BaseDeco4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

