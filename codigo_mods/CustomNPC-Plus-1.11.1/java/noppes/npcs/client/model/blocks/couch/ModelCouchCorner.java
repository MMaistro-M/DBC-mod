/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.couch;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCouchCorner
extends ModelBase {
    public final ModelRenderer CouchBack;
    private final ModelRenderer cube_r1;
    public final ModelRenderer Cussion;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;

    public ModelCouchCorner() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.CouchBack = new ModelRenderer((ModelBase)this);
        this.CouchBack.func_78793_a(0.0f, 24.0f, 0.0f);
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 1, 35, -8.0f, -3.0f, -8.0f, 15, 1, 15, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 51, -8.0f, -12.0f, 7.0f, 16, 10, 1, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(7.5f, -7.5f, -1.0f);
        this.CouchBack.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 1.5708f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 1, 51, -8.0f, -4.5f, -0.5f, 15, 10, 1, 0.0f));
        this.Cussion = new ModelRenderer((ModelBase)this);
        this.Cussion.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 1, 37, -8.0f, -7.0f, -8.0f, 15, 4, 15, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 0, 19, -8.0f, -16.0f, 3.0f, 15, 9, 4, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 0, 32, -8.0f, -16.0f, 7.0f, 16, 4, 1, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(7.5f, -14.0f, -1.0f);
        this.Cussion.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 1.5708f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 1, 32, -8.0f, -2.0f, -0.5f, 15, 4, 1, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(5.5f, -11.5f, -3.5f);
        this.Cussion.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, 1.5708f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 5, 19, -6.5f, -4.5f, -2.5f, 11, 9, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

