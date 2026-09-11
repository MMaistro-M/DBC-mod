/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.lantern;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LanternCeiling
extends ModelBase {
    public final ModelRenderer Light;
    public final ModelRenderer Lantern;
    public final ModelRenderer Chain;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;

    public LanternCeiling() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Light = new ModelRenderer((ModelBase)this);
        this.Light.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Light.field_78804_l.add(new ModelBox(this.Light, 28, 8, -3.0f, -9.0f, -3.0f, 6, 7, 6, 0.0f));
        this.Lantern = new ModelRenderer((ModelBase)this);
        this.Lantern.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Lantern.field_78804_l.add(new ModelBox(this.Lantern, 28, 0, -3.0f, -2.0f, -3.0f, 6, 2, 6, 0.0f));
        this.Lantern.field_78804_l.add(new ModelBox(this.Lantern, 28, 21, -2.0f, -11.0f, -2.0f, 4, 2, 4, 0.0f));
        this.Lantern.field_78804_l.add(new ModelBox(this.Lantern, 46, 21, -5.0f, -11.0f, 0.0f, 3, 11, 0, 0.0f));
        this.Lantern.field_78809_i = true;
        this.Lantern.field_78804_l.add(new ModelBox(this.Lantern, 46, 21, 2.0f, -11.0f, 0.0f, 3, 11, 0, 0.0f));
        this.Lantern.field_78809_i = false;
        this.Chain = new ModelRenderer((ModelBase)this);
        this.Chain.func_78793_a(0.0f, 24.0f, 0.0f);
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, -18.0f, 0.0f);
        this.Chain.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, -0.7854f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, -1, 0.0f, 2.0f, -1.5f, 0, 5, 3, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -18.0f, 0.0f);
        this.Chain.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.7854f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 6, -1, 0.0f, 2.0f, -1.5f, 0, 5, 3, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Light.func_78785_a(f5);
        this.Lantern.func_78785_a(f5);
        this.Chain.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

