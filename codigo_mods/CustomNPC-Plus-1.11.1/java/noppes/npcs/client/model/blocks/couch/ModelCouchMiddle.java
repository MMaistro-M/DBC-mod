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

public class ModelCouchMiddle
extends ModelBase {
    public final ModelRenderer CouchBack;
    public final ModelRenderer Cussion;

    public ModelCouchMiddle() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.CouchBack = new ModelRenderer((ModelBase)this);
        this.CouchBack.func_78793_a(0.0f, 24.0f, 0.0f);
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -3.0f, -8.0f, 16, 1, 15, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 51, -8.0f, -12.0f, 7.0f, 16, 10, 1, 0.0f));
        this.Cussion = new ModelRenderer((ModelBase)this);
        this.Cussion.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 0, 0, -8.0f, -7.0f, -8.0f, 16, 4, 15, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 0, 19, -8.0f, -16.0f, 3.0f, 16, 9, 4, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 0, 32, -8.0f, -16.0f, 7.0f, 16, 4, 1, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

