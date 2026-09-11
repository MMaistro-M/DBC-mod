/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.lamp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShortLamp
extends ModelBase {
    public final ModelRenderer Shade;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    public final ModelRenderer Lamp;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    public final ModelRenderer Light;

    public ModelShortLamp() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Shade = new ModelRenderer((ModelBase)this);
        this.Shade.func_78793_a(-4.0f, 12.0f, 0.0f);
        this.Shade.field_78804_l.add(new ModelBox(this.Shade, 0, 8, 0.0f, -4.0f, -4.0f, 8, 8, 0, 0.0f));
        this.Shade.field_78804_l.add(new ModelBox(this.Shade, -8, 0, 0.0f, -4.0f, -4.0f, 8, 0, 8, 0.0f));
        this.Shade.field_78804_l.add(new ModelBox(this.Shade, 0, 8, 0.0f, -4.0f, 4.0f, 8, 8, 0, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shade.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 1.5708f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 8, -4.0f, -4.0f, 0.0f, 8, 8, 0, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(8.0f, 0.0f, 0.0f);
        this.Shade.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 1.5708f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 8, -4.0f, -4.0f, 0.0f, 8, 8, 0, 0.0f));
        this.Lamp = new ModelRenderer((ModelBase)this);
        this.Lamp.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Lamp.field_78804_l.add(new ModelBox(this.Lamp, 28, 8, -3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f));
        this.Lamp.field_78804_l.add(new ModelBox(this.Lamp, 20, 5, -1.0f, -9.0f, -1.0f, 2, 3, 2, 0.0f));
        this.Lamp.field_78804_l.add(new ModelBox(this.Lamp, 20, 0, -1.0f, -16.0f, -1.0f, 2, 3, 2, 0.0f));
        this.Lamp.field_78804_l.add(new ModelBox(this.Lamp, 14, 0, -3.0f, -16.0f, -1.0f, 2, 0, 2, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(0.0f, -16.0f, -2.5f);
        this.Lamp.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, -1.5708f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 14, 0, -0.5f, 0.0f, -1.0f, 2, 0, 2, 0.0f));
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(0.0f, -16.0f, 2.5f);
        this.Lamp.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.0f, 1.5708f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 14, 0, -0.5f, 0.0f, -1.0f, 2, 0, 2, 0.0f));
        this.cube_r5 = new ModelRenderer((ModelBase)this);
        this.cube_r5.func_78793_a(2.5f, -16.0f, 0.0f);
        this.Lamp.func_78792_a(this.cube_r5);
        this.setRotationAngle(this.cube_r5, -3.1416f, 0.0f, 0.0f);
        this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 14, 0, -1.5f, 0.0f, -1.0f, 2, 0, 2, 0.0f));
        this.Light = new ModelRenderer((ModelBase)this);
        this.Light.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Light.field_78804_l.add(new ModelBox(this.Light, 28, 0, -2.0f, -13.0f, -2.0f, 4, 4, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

