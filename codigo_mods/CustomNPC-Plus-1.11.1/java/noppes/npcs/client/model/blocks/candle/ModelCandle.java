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

public class ModelCandle
extends ModelBase {
    public final ModelRenderer Candle;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    public final ModelRenderer Holder;

    public ModelCandle() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Candle = new ModelRenderer((ModelBase)this);
        this.Candle.func_78793_a(0.0f, 23.0f, 0.0f);
        this.Candle.field_78804_l.add(new ModelBox(this.Candle, 28, 1, -1.0f, -5.0f, -1.0f, 2, 5, 2, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, -5.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 2.3562f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -5.5f, 0.0f);
        this.Candle.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.7854f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 31, -1, 0.0f, -0.5f, -0.5f, 0, 1, 1, 0.0f));
        this.Holder = new ModelRenderer((ModelBase)this);
        this.Holder.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 0, -2.0f, -1.0f, -2.0f, 4, 1, 4, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 36, 0, 2.0f, -2.0f, -3.0f, 1, 1, 6, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 36, 0, -3.0f, -2.0f, -3.0f, 1, 1, 6, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 36, 7, -2.0f, -2.0f, -3.0f, 4, 1, 1, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 36, 7, -2.0f, -2.0f, 2.0f, 4, 1, 1, 0.0f));
        this.Holder.field_78804_l.add(new ModelBox(this.Holder, 12, 11, 2.0f, -4.0f, 0.0f, 3, 3, 0, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Candle.func_78785_a(f5);
        this.Holder.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

