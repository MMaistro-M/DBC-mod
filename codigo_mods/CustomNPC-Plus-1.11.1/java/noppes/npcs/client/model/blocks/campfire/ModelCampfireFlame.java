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

public class ModelCampfireFlame
extends ModelBase {
    private final ModelRenderer[] frames;
    private static final int frameDuration = 2;

    public ModelCampfireFlame() {
        this.field_78090_t = 16;
        this.field_78089_u = 144;
        this.frames = new ModelRenderer[9];
        this.frames[0] = new ModelRenderer((ModelBase)this);
        this.frames[0].func_78793_a(0.0f, 16.5f, 0.0f);
        this.frames[0].field_78804_l.add(new ModelBox(this.frames[0], 0, 0, -8.0f, -8.5f, 0.0f, 16, 16, 0, 0.0f));
        ModelRenderer cube_r1 = new ModelRenderer((ModelBase)this);
        cube_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.frames[0].func_78792_a(cube_r1);
        this.setRotationAngle(cube_r1, 0.0f, -1.5708f, 0.0f);
        cube_r1.field_78804_l.add(new ModelBox(cube_r1, 0, 0, -8.0f, -8.5f, 0.0f, 16, 16, 0, 0.0f));
        for (int i = 1; i < 9; ++i) {
            this.frames[i] = new ModelRenderer((ModelBase)this);
            this.frames[i].func_78793_a(0.0f, 16.5f, 0.0f);
            this.frames[i].field_78804_l.add(new ModelBox(this.frames[i], 0, i * 16 + 1, -8.0f, -8.5f, 0.0f, 16, 16, 0, 0.0f));
            ModelRenderer cube_r = new ModelRenderer((ModelBase)this);
            cube_r.func_78793_a(0.0f, 0.0f, 0.0f);
            this.frames[i].func_78792_a(cube_r);
            this.setRotationAngle(cube_r, 0.0f, -1.5708f, 0.0f);
            cube_r.field_78804_l.add(new ModelBox(cube_r, 0, i * 16 + 1, -8.0f, -8.5f, 0.0f, 16, 16, 0, 0.0f));
        }
    }

    public void func_78088_a(Entity entity, float world_time, float f1, float f2, float f3, float f4, float f5) {
        int frameIndex = (int)world_time / 2 % this.frames.length;
        this.frames[frameIndex].func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

