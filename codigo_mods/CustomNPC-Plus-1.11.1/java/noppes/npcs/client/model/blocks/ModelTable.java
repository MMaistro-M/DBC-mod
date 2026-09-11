/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTable
extends ModelBase {
    public final ModelRenderer Shape3;
    public final ModelRenderer Shape4;
    private final ModelRenderer cube_r1;
    public final ModelRenderer Shape1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    public final ModelRenderer Shape5;
    private final ModelRenderer cube_r4;
    public final ModelRenderer Table;

    public ModelTable() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Shape3 = new ModelRenderer((ModelBase)this);
        this.Shape3.func_78793_a(-6.0f, 24.0f, 6.0f);
        this.Shape3.field_78804_l.add(new ModelBox(this.Shape3, 8, 19, 6.0f, -13.0f, -12.0f, 5, 5, 0, 0.0f));
        this.Shape3.field_78804_l.add(new ModelBox(this.Shape3, 0, 0, 11.0f, -13.0f, -13.0f, 2, 13, 2, 0.0f));
        this.Shape3.field_78804_l.add(new ModelBox(this.Shape3, 8, 14, 12.0f, -13.0f, -11.0f, 0, 5, 5, 0.0f));
        this.Shape4 = new ModelRenderer((ModelBase)this);
        this.Shape4.func_78793_a(-6.0f, 24.0f, 6.0f);
        this.Shape4.field_78804_l.add(new ModelBox(this.Shape4, 8, 19, 6.0f, -13.0f, 0.0f, 5, 5, 0, 0.0f));
        this.Shape4.field_78804_l.add(new ModelBox(this.Shape4, 0, 0, 11.0f, -13.0f, -1.0f, 2, 13, 2, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(12.0f, -10.5f, -3.5f);
        this.Shape4.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 3.1416f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 14, 0.0f, -2.5f, -2.5f, 0, 5, 5, 0.0f));
        this.Shape1 = new ModelRenderer((ModelBase)this);
        this.Shape1.func_78793_a(-6.0f, 24.0f, 6.0f);
        this.Shape1.field_78804_l.add(new ModelBox(this.Shape1, 0, 0, -1.0f, -13.0f, -1.0f, 2, 13, 2, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -10.5f, -3.5f);
        this.Shape1.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 3.1416f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 8, 14, 0.0f, -2.5f, -2.5f, 0, 5, 5, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(3.5f, -10.5f, 0.0f);
        this.Shape1.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, 3.1416f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 8, 19, -2.5f, -2.5f, 0.0f, 5, 5, 0, 0.0f));
        this.Shape5 = new ModelRenderer((ModelBase)this);
        this.Shape5.func_78793_a(-6.0f, 24.0f, 6.0f);
        this.Shape5.field_78804_l.add(new ModelBox(this.Shape5, 0, 0, -1.0f, -13.0f, -13.0f, 2, 13, 2, 0.0f));
        this.Shape5.field_78804_l.add(new ModelBox(this.Shape5, 8, 14, 0.0f, -13.0f, -11.0f, 0, 5, 5, 0.0f));
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(3.5f, -10.5f, -12.0f);
        this.Shape5.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.0f, 3.1416f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 8, 19, -2.5f, -2.5f, 0.0f, 5, 5, 0, 0.0f));
        this.Table = new ModelRenderer((ModelBase)this);
        this.Table.func_78793_a(-6.0f, 24.0f, 6.0f);
        this.Table.field_78804_l.add(new ModelBox(this.Table, 0, 0, -2.0f, -16.0f, -14.0f, 16, 3, 16, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

