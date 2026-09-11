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

public class ModelStool
extends ModelBase {
    private final ModelRenderer stool;

    public ModelStool() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.stool = new ModelRenderer((ModelBase)this);
        this.stool.func_78793_a(0.0f, 24.0f, 0.0f);
        this.stool.field_78804_l.add(new ModelBox(this.stool, 0, 0, -5.0f, -9.0f, -5.0f, 10, 2, 10, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 0, 12, 2.0f, -7.0f, -4.0f, 2, 7, 2, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 8, 8, 3.0f, -5.0f, -2.0f, 0, 2, 4, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 8, 12, -2.0f, -5.0f, -3.0f, 4, 2, 0, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 0, 12, -4.0f, -7.0f, -4.0f, 2, 7, 2, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 0, 12, -4.0f, -7.0f, 2.0f, 2, 7, 2, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 8, 8, -3.0f, -5.0f, -2.0f, 0, 2, 4, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 0, 12, 2.0f, -7.0f, 2.0f, 2, 7, 2, 0.0f));
        this.stool.field_78804_l.add(new ModelBox(this.stool, 8, 12, -2.0f, -5.0f, 3.0f, 4, 2, 0, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.stool.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

