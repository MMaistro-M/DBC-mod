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

public class ModelLegacyWallBanner
extends ModelBase {
    ModelRenderer MiddleStick = new ModelRenderer((ModelBase)this, 56, 0);
    ModelRenderer StickDecoration;
    ModelRenderer TopDecoration;
    ModelRenderer FlagPole1;
    ModelRenderer FlagPole2;

    public ModelLegacyWallBanner() {
        this.MiddleStick.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.MiddleStick.func_78793_a(0.0f, -9.0f, 6.5f);
        this.StickDecoration = new ModelRenderer((ModelBase)this, 11, 12);
        this.StickDecoration.func_78789_a(0.0f, 0.0f, 0.0f, 16, 3, 3);
        this.StickDecoration.func_78793_a(-8.0f, -7.5f, 5.0f);
        this.TopDecoration = new ModelRenderer((ModelBase)this, 45, 19);
        this.TopDecoration.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.TopDecoration.func_78793_a(-0.5f, -10.0f, 6.0f);
        this.FlagPole1 = new ModelRenderer((ModelBase)this, 45, 19);
        this.FlagPole1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.FlagPole1.func_78793_a(-7.0f, -6.5f, 4.0f);
        this.FlagPole2 = new ModelRenderer((ModelBase)this, 45, 19);
        this.FlagPole2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.FlagPole2.func_78793_a(6.0f, -6.5f, 4.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.MiddleStick.func_78785_a(f5);
        this.StickDecoration.func_78785_a(f5);
        this.TopDecoration.func_78785_a(f5);
        this.FlagPole1.func_78785_a(f5);
        this.FlagPole2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

