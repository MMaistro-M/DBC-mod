/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.candle;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCandleCeiling
extends ModelBase {
    public final ModelRenderer Candle;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    public final ModelRenderer Holder;
    public final ModelRenderer Base;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    public final ModelRenderer Chain;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;

    public ModelCandleCeiling() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Candle = new ModelRenderer((ModelBase)this);
        this.Candle.func_78793_a(0.0f, 23.0f, 0.0f);
        this.Candle.field_78804_l.add(new ModelBox(this.Candle, 28, 1, -1.0f, -9.0f, 5.0f, 2, 5, 2, 0.0f));
        this.Candle.field_78804_l.add(new ModelBox(this.Candle, 28, 1, -7.0f, -9.0f, -1.0f, 2, 5, 2, 0.0f));
        this.Candle.field_78804_l.add(new ModelBox(this.Candle, 28, 1, 5.0f, -9.0f, -1.0f, 2, 5, 2, 0.0f));
        this.Candle.field_78804_l.add(new ModelBox(this.Candle, 28, 1, -1.0f, -9.0f, -7.0f, 2, 5, 2, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, -9.5f, -6.0f);
        this.Candle.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 2.3562f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -9.5f, -6.0f);
        this.Candle.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.7854f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(6.0f, -9.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, 2.3562f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(6.0f, -9.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.0f, 0.7854f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r5 = new ModelRenderer((ModelBase)this);
        this.cube_r5.func_78793_a(-6.0f, -9.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r5);
        this.setRotationAngle(this.cube_r5, 0.0f, 2.3562f, 0.0f);
        this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r6 = new ModelRenderer((ModelBase)this);
        this.cube_r6.func_78793_a(-6.0f, -9.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r6);
        this.setRotationAngle(this.cube_r6, 0.0f, 0.7854f, 0.0f);
        this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r7 = new ModelRenderer((ModelBase)this);
        this.cube_r7.func_78793_a(0.0f, -9.5f, 6.0f);
        this.Candle.func_78792_a(this.cube_r7);
        this.setRotationAngle(this.cube_r7, 0.0f, 2.3562f, 0.0f);
        this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r8 = new ModelRenderer((ModelBase)this);
        this.cube_r8.func_78793_a(0.0f, -9.5f, 6.0f);
        this.Candle.func_78792_a(this.cube_r8);
        this.setRotationAngle(this.cube_r8, 0.0f, 0.7854f, 0.0f);
        this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.Holder = new ModelRenderer((ModelBase)this);
        this.Holder.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 0, -2.0f, -5.0f, 4.0f, 4, 1, 4, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 0, -8.0f, -5.0f, -2.0f, 4, 1, 4, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 0, 4.0f, -5.0f, -2.0f, 4, 1, 4, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 0, -2.0f, -5.0f, -8.0f, 4, 1, 4, 0.0f));
        this.Base = new ModelRenderer((ModelBase)this);
        this.Base.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Base.field_78804_l.add(new ModelBox(this.Base, 12, 0, -2.0f, -2.0f, -2.0f, 4, 1, 4, 0.0f));
        this.Base.field_78804_l.add(new ModelBox(this.Base, 12, 0, 0.0f, -4.0f, 2.0f, 0, 3, 5, 0.0f));
        this.cube_r9 = new ModelRenderer((ModelBase)this);
        this.cube_r9.func_78793_a(4.5f, -2.5f, 0.0f);
        this.Base.func_78792_a(this.cube_r9);
        this.setRotationAngle(this.cube_r9, 0.0f, 1.5708f, 0.0f);
        this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 12, 0, 0.0f, -1.5f, -2.5f, 0, 3, 5, 0.0f));
        this.cube_r10 = new ModelRenderer((ModelBase)this);
        this.cube_r10.func_78793_a(-7.0f, -2.5f, 0.0f);
        this.Base.func_78792_a(this.cube_r10);
        this.setRotationAngle(this.cube_r10, 0.0f, -1.5708f, 0.0f);
        this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 12, 0, 0.0f, -1.5f, -5.0f, 0, 3, 5, 0.0f));
        this.cube_r11 = new ModelRenderer((ModelBase)this);
        this.cube_r11.func_78793_a(0.0f, -2.5f, -4.5f);
        this.Base.func_78792_a(this.cube_r11);
        this.setRotationAngle(this.cube_r11, 0.0f, 3.1416f, 0.0f);
        this.cube_r11.field_78804_l.add(new ModelBox(this.cube_r11, 12, 0, 0.0f, -1.5f, -2.5f, 0, 3, 5, 0.0f));
        this.Chain = new ModelRenderer((ModelBase)this);
        this.Chain.func_78793_a(0.0f, 18.0f, 0.0f);
        this.cube_r12 = new ModelRenderer((ModelBase)this);
        this.cube_r12.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Chain.func_78792_a(this.cube_r12);
        this.setRotationAngle(this.cube_r12, 0.0f, 0.7854f, 0.0f);
        this.cube_r12.field_78804_l.add(new ModelBox(this.cube_r12, 6, -1, 0.0f, -7.0f, -1.5f, 0, 14, 3, 0.0f));
        this.cube_r13 = new ModelRenderer((ModelBase)this);
        this.cube_r13.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Chain.func_78792_a(this.cube_r13);
        this.setRotationAngle(this.cube_r13, 0.0f, -0.7854f, 0.0f);
        this.cube_r13.field_78804_l.add(new ModelBox(this.cube_r13, 0, -1, 0.0f, -7.0f, -1.5f, 0, 14, 3, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Candle.func_78785_a(f5);
        this.Holder.func_78785_a(f5);
        this.Base.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

