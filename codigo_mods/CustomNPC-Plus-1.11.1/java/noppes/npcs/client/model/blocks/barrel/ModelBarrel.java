/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.blocks.barrel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelBarrel
extends ModelBase {
    private final ModelRenderer Wall;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer Trim;
    private final ModelRenderer Base;

    public ModelBarrel() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Wall = new ModelRenderer((ModelBase)this);
        this.Wall.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Wall.field_78804_l.add(new ModelBox(this.Wall, 0, 0, 5.0f, -16.0f, 5.0f, 1, 16, 1, 0.0f));
        this.Wall.field_78804_l.add(new ModelBox(this.Wall, 0, 0, 5.0f, -16.0f, -6.0f, 1, 16, 1, 0.0f));
        this.Wall.field_78804_l.add(new ModelBox(this.Wall, 0, 0, -6.0f, -16.0f, -6.0f, 1, 16, 1, 0.0f));
        this.Wall.field_78804_l.add(new ModelBox(this.Wall, 0, 0, -6.0f, -16.0f, 5.0f, 1, 16, 1, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(-6.5f, -8.0f, 0.0f);
        this.Wall.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 1.5708f, 0.0f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -0.5f, -6.0f, -8.0f, 1, 12, 16, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -8.0f, 6.5f);
        this.Wall.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, 1.5708f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 14, -8.0f, -6.0f, -0.5f, 16, 12, 1, 0.0f));
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 14, -8.0f, -6.0f, -13.5f, 16, 12, 1, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(6.5f, -8.0f, 0.0f);
        this.Wall.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, -1.5708f, 0.0f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 0, -0.5f, -6.0f, -8.0f, 1, 12, 16, 0.0f));
        this.Trim = new ModelRenderer((ModelBase)this);
        this.Trim.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 4, 28, -6.5f, -5.0f, 5.5f, 13, 2, 2, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 34, 17, 5.5f, -5.0f, -6.5f, 2, 2, 13, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 4, 28, -6.5f, -5.0f, -7.5f, 13, 2, 2, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 34, 17, -7.5f, -5.0f, -6.5f, 2, 2, 13, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 4, 28, -6.5f, -13.0f, -7.5f, 13, 2, 2, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 4, 28, -6.5f, -13.0f, 5.5f, 13, 2, 2, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 34, 17, -7.5f, -13.0f, -6.5f, 2, 2, 13, 0.0f));
        this.Trim.field_78804_l.add(new ModelBox(this.Trim, 34, 17, 5.5f, -13.0f, -6.5f, 2, 2, 13, 0.0f));
        this.Base = new ModelRenderer((ModelBase)this);
        this.Base.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Base.field_78804_l.add(new ModelBox(this.Base, 16, 2, -6.0f, -15.0f, -6.0f, 12, 1, 12, 0.0f));
        this.Base.field_78804_l.add(new ModelBox(this.Base, 16, 2, -6.0f, -2.0f, -6.0f, 12, 1, 12, 0.0f));
    }

    public void renderBase(float f5) {
        this.Base.func_78785_a(f5);
    }

    public void renderTrim(float f5) {
        this.Trim.func_78785_a(f5);
    }

    public void renderWall(float f5) {
        this.Wall.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

