/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.campfire;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCampfireLog
extends ModelBase {
    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;

    public ModelCampfireLog() {
        this.field_78090_t = 16;
        this.field_78089_u = 16;
        this.bb_main = new ModelRenderer((ModelBase)this);
        this.bb_main.func_78793_a(0.0f, 24.0f, 0.0f);
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(1.25f, -6.3f, 1.25f);
        this.bb_main.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.5449f, -1.0854f, -0.744f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -1.0f, -2.0f, -1.0f, 3, 8, 3, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(-1.5f, -6.0f, -0.25f);
        this.bb_main.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, 0.3927f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, -2.0f, -2.0f, -1.0f, 3, 8, 3, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(1.5f, -6.0f, -2.25f);
        this.bb_main.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, -0.3295f, 0.7268f, -0.4754f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 0, -2.0f, -2.5f, -1.0f, 3, 8, 3, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bb_main.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

