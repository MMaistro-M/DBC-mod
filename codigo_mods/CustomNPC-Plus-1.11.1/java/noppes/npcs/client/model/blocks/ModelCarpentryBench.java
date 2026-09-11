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

public class ModelCarpentryBench
extends ModelBase {
    private final ModelRenderer blueprint;
    private final ModelRenderer chissle;
    private final ModelRenderer saw;
    private final ModelRenderer bb_main;

    public ModelCarpentryBench() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.blueprint = new ModelRenderer((ModelBase)this);
        this.blueprint.func_78793_a(-6.4564f, 9.3925f, -0.75f);
        this.setRotationAngle(this.blueprint, 0.0f, -0.3054f, 0.0f);
        this.blueprint.field_78804_l.add(new ModelBox(this.blueprint, 47, 27, -0.5436f, 0.5075f, -3.5f, 7, 0, 7, 0.0f));
        ModelRenderer blueprint_r1 = new ModelRenderer((ModelBase)this);
        blueprint_r1.func_78793_a(-0.5436f, 0.5075f, 0.0f);
        this.blueprint.func_78792_a(blueprint_r1);
        this.setRotationAngle(blueprint_r1, 0.0f, 0.0f, 0.7854f);
        blueprint_r1.field_78804_l.add(new ModelBox(blueprint_r1, 45, 27, -1.0f, 0.0f, -3.5f, 1, 0, 7, 0.0f));
        ModelRenderer blueprint_r2 = new ModelRenderer((ModelBase)this);
        blueprint_r2.func_78793_a(-1.2507f, -0.1996f, 0.0f);
        this.blueprint.func_78792_a(blueprint_r2);
        this.setRotationAngle(blueprint_r2, 0.0f, 0.0f, 1.9635f);
        blueprint_r2.field_78804_l.add(new ModelBox(blueprint_r2, 43, 27, -1.0f, 0.0f, -3.5f, 1, 0, 7, 0.0f));
        this.chissle = new ModelRenderer((ModelBase)this);
        this.chissle.func_78793_a(1.356f, 18.6543f, -2.5f);
        this.setRotationAngle(this.chissle, 0.0f, -0.7854f, 0.0f);
        this.chissle.field_78804_l.add(new ModelBox(this.chissle, 17, 53, -0.356f, -0.6543f, -0.5f, 3, 1, 1, 0.0f));
        ModelRenderer cube_r1 = new ModelRenderer((ModelBase)this);
        cube_r1.func_78793_a(-1.606f, 0.3457f, 0.0f);
        this.chissle.func_78792_a(cube_r1);
        this.setRotationAngle(cube_r1, 0.0f, 0.0f, -0.3927f);
        cube_r1.field_78804_l.add(new ModelBox(cube_r1, 18, 52, -0.5f, 0.0f, -0.5f, 2, 0, 1, 0.0f));
        this.saw = new ModelRenderer((ModelBase)this);
        this.saw.func_78793_a(-2.65f, 18.5f, -2.7f);
        this.setRotationAngle(this.saw, -3.096f, -0.3051f, 3.1241f);
        this.saw.field_78804_l.add(new ModelBox(this.saw, 16, 46, -1.6f, -0.5f, 0.7f, 1, 1, 1, 0.0f));
        this.saw.field_78804_l.add(new ModelBox(this.saw, 16, 48, 0.4f, -0.5f, 0.7f, 1, 1, 1, 0.0f));
        this.saw.field_78804_l.add(new ModelBox(this.saw, 8, 36, -1.6f, 0.0f, -9.3f, 4, 0, 9, 0.0f));
        this.saw.field_78804_l.add(new ModelBox(this.saw, 20, 46, -1.6f, -0.5f, -0.3f, 3, 1, 1, 0.0f));
        this.saw.field_78804_l.add(new ModelBox(this.saw, 20, 48, -1.6f, -0.5f, 1.7f, 3, 1, 1, 0.0f));
        this.bb_main = new ModelRenderer((ModelBase)this);
        this.bb_main.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 50, 3.0f, -12.0f, -6.0f, 2, 12, 2, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 50, -7.0f, -12.0f, -6.0f, 2, 12, 2, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 36, -7.0f, -12.0f, 4.0f, 2, 12, 2, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 36, 3.0f, -12.0f, 4.0f, 2, 12, 2, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -9.0f, -14.0f, -7.0f, 16, 2, 14, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 16, -9.0f, -17.0f, 5.0f, 16, 3, 2, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 24, -6.0f, -5.0f, -5.0f, 10, 1, 10, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 46, 17, 8.0f, -14.0f, -4.0f, 2, 3, 7, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 29, 16, 6.0f, -12.0f, -4.0f, 2, 1, 7, 0.0f));
        ModelRenderer cube_r2 = new ModelRenderer((ModelBase)this);
        cube_r2.func_78793_a(10.5f, -12.5f, -0.5f);
        this.bb_main.func_78792_a(cube_r2);
        this.setRotationAngle(cube_r2, 0.7854f, 0.0f, 0.0f);
        cube_r2.field_78804_l.add(new ModelBox(cube_r2, 47, 17, -1.0f, -0.5f, -0.5f, 1, 1, 1, 0.0f));
        cube_r2.field_78804_l.add(new ModelBox(cube_r2, 41, 17, 0.0f, -0.5f, -1.5f, 1, 1, 3, 0.0f));
        ModelRenderer hammer_r1 = new ModelRenderer((ModelBase)this);
        hammer_r1.func_78793_a(4.75f, -14.9f, 1.0f);
        this.bb_main.func_78792_a(hammer_r1);
        this.setRotationAngle(hammer_r1, 2.9697f, -0.7703f, -2.8972f);
        hammer_r1.field_78804_l.add(new ModelBox(hammer_r1, 44, 39, -2.75f, -0.5f, -0.5f, 3, 1, 1, 0.0f));
        hammer_r1.field_78804_l.add(new ModelBox(hammer_r1, 52, 35, 0.25f, -1.0f, -2.0f, 2, 2, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.blueprint.func_78785_a(f5);
        this.chissle.func_78785_a(f5);
        this.saw.func_78785_a(f5);
        this.bb_main.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

