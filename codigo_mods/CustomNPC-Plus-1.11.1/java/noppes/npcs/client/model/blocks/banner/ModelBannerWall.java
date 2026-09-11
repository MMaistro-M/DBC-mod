/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.banner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBannerWall
extends ModelBase {
    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r1;

    public ModelBannerWall() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.bb_main = new ModelRenderer((ModelBase)this);
        this.bb_main.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0f, -32.0f, 5.0f, 16, 3, 3, 0.0f));
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 8, -6.0f, -36.0f, 6.5f, 4, 4, 0, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(4.0f, -34.0f, 6.5f);
        this.bb_main.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 3.1416f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 8, -2.0f, -2.0f, 0.0f, 4, 4, 0, 0.0f));
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

