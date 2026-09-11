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

public class ModelWeaponRack
extends ModelBase {
    ModelRenderer Support_1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Support_2;
    ModelRenderer Support_3;
    ModelRenderer Support_4;
    ModelRenderer Support_5;
    ModelRenderer Support_6;
    ModelRenderer Rung_1_A;
    ModelRenderer Rung_1_B;
    ModelRenderer Rung_1_C;
    ModelRenderer Rung_2_A;
    ModelRenderer Rung_2_B;
    ModelRenderer Rung_2_C;
    ModelRenderer Rung_3_A;
    ModelRenderer Rung_3_B;
    ModelRenderer Rung_3_C;
    ModelRenderer Cross_Top_1;
    ModelRenderer Cross_Top_2;
    ModelRenderer Bottom_Support_1;
    ModelRenderer Bottom_Support_2;
    ModelRenderer Middle_Support_1;

    public ModelWeaponRack() {
        this.Support_1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_1.func_78793_a(-5.0f, -6.9f, 5.0f);
        this.Support_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Support_2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_2.func_78793_a(-8.01f, -6.9f, 5.0f);
        this.Support_3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Support_3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_3.func_78793_a(-2.0f, -6.9f, 5.0f);
        this.Support_4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Support_4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_4.func_78793_a(1.0f, -6.9f, 5.0f);
        this.Support_5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Support_5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_5.func_78793_a(4.0f, -6.9f, 5.0f);
        this.Support_6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Support_6.func_78789_a(0.0f, 0.0f, 0.0f, 1, 30, 2);
        this.Support_6.func_78793_a(7.01f, -6.9f, 5.0f);
        this.Rung_1_A = new ModelRenderer((ModelBase)this, 0, 22);
        this.Rung_1_A.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 0);
        this.Rung_1_A.func_78793_a(-8.0f, 11.0f, 3.99f);
        this.Rung_1_B = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_1_B.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_1_B.func_78793_a(-8.0f, 11.0f, 4.0f);
        this.Rung_1_C = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_1_C.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_1_C.func_78793_a(-5.0f, 11.0f, 4.0f);
        this.Rung_2_A = new ModelRenderer((ModelBase)this, 0, 22);
        this.Rung_2_A.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 0);
        this.Rung_2_A.func_78793_a(-2.0f, 11.0f, 3.99f);
        this.Rung_2_B = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_2_B.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_2_B.func_78793_a(-2.0f, 11.0f, 4.0f);
        this.Rung_2_C = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_2_C.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_2_C.func_78793_a(1.0f, 11.0f, 4.0f);
        this.Rung_3_A = new ModelRenderer((ModelBase)this, 0, 22);
        this.Rung_3_A.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 0);
        this.Rung_3_A.func_78793_a(4.0f, 11.0f, 3.99f);
        this.Rung_3_B = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_3_B.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_3_B.func_78793_a(4.0f, 11.0f, 4.0f);
        this.Rung_3_C = new ModelRenderer((ModelBase)this, 0, 24);
        this.Rung_3_C.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Rung_3_C.func_78793_a(7.0f, 11.0f, 4.0f);
        this.Cross_Top_1 = new ModelRenderer((ModelBase)this, 6, 0);
        this.Cross_Top_1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 1);
        this.Cross_Top_1.func_78793_a(-8.0f, -8.6f, 6.0f);
        this.setRotation(this.Cross_Top_1, -0.5235988f, 0.0f, 0.0f);
        this.Cross_Top_2 = new ModelRenderer((ModelBase)this, 6, 0);
        this.Cross_Top_2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 1);
        this.Cross_Top_2.func_78793_a(-8.0f, -8.6f, 6.01f);
        this.Bottom_Support_1 = new ModelRenderer((ModelBase)this, 6, 0);
        this.Bottom_Support_1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 1);
        this.Bottom_Support_1.func_78793_a(-8.0f, 23.0f, 6.0f);
        this.setRotation(this.Bottom_Support_1, -1.570796f, 0.0f, 0.0f);
        this.Bottom_Support_2 = new ModelRenderer((ModelBase)this, 6, 0);
        this.Bottom_Support_2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 1);
        this.Bottom_Support_2.func_78793_a(-8.0f, 23.0f, 8.0f);
        this.setRotation(this.Bottom_Support_2, -1.570796f, 0.0f, 0.0f);
        this.Middle_Support_1 = new ModelRenderer((ModelBase)this, 6, 3);
        this.Middle_Support_1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 3);
        this.Middle_Support_1.func_78793_a(-8.0f, 10.0f, 7.01f);
        this.setRotation(this.Middle_Support_1, -1.570796f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Support_1.func_78785_a(f5);
        this.Support_2.func_78785_a(f5);
        this.Support_3.func_78785_a(f5);
        this.Support_4.func_78785_a(f5);
        this.Support_5.func_78785_a(f5);
        this.Support_6.func_78785_a(f5);
        this.Rung_1_A.func_78785_a(f5);
        this.Rung_1_B.func_78785_a(f5);
        this.Rung_1_C.func_78785_a(f5);
        this.Rung_2_A.func_78785_a(f5);
        this.Rung_2_B.func_78785_a(f5);
        this.Rung_2_C.func_78785_a(f5);
        this.Rung_3_A.func_78785_a(f5);
        this.Rung_3_B.func_78785_a(f5);
        this.Rung_3_C.func_78785_a(f5);
        this.Cross_Top_1.func_78785_a(f5);
        this.Cross_Top_2.func_78785_a(f5);
        this.Bottom_Support_1.func_78785_a(f5);
        this.Bottom_Support_2.func_78785_a(f5);
        this.Middle_Support_1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

