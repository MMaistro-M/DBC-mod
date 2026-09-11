/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.chair;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChairSpoof
extends ModelBase {
    private final ModelRenderer chairBase;

    public ModelChairSpoof() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.chairBase = new ModelRenderer((ModelBase)this);
        this.chairBase.func_78793_a(0.0f, 24.0f, 0.0f);
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 0, 4.0f, -6.0f, 4.0f, 2, 6, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 0, -6.0f, -6.0f, 4.0f, 2, 6, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 0, -6.0f, -6.0f, -6.0f, 2, 6, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 0, 4.0f, -6.0f, -6.0f, 2, 6, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 0, -6.0f, -8.0f, -6.0f, 12, 2, 12, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 12, 0, 4.0f, -20.0f, 4.0f, 2, 12, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 30, 0, -6.0f, -20.0f, 4.0f, 2, 12, 2, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 2, -4.0f, -19.0f, 5.0f, 8, 3, 0, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, -5, -6, 5.0f, -4.0f, -4.0f, 0, 2, 8, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 1, 2, -4.0f, -4.0f, -5.0f, 8, 2, 0, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, -5, -6, -5.0f, -4.0f, -4.0f, 0, 2, 8, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, -3, 2, -4.0f, -4.0f, 5.0f, 8, 2, 0, 0.0f));
        this.chairBase.field_78804_l.add(new ModelBox(this.chairBase, 0, 2, -4.0f, -13.0f, 5.0f, 8, 3, 0, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.chairBase.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

