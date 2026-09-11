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

public class ModelCouchSingle
extends ModelBase {
    public final ModelRenderer CouchBack;
    private final ModelRenderer ArmL;
    private final ModelRenderer ArmR;
    public final ModelRenderer Cussion;

    public ModelCouchSingle() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.CouchBack = new ModelRenderer((ModelBase)this);
        this.CouchBack.func_78793_a(0.0f, 24.0f, 0.0f);
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, -8.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, -8.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 0, 35, 6.0f, -2.0f, 6.0f, 2, 2, 2, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 2, 35, -6.0f, -3.0f, -8.0f, 12, 1, 15, 0.0f));
        this.CouchBack.field_78804_l.add(new ModelBox(this.CouchBack, 2, 51, -6.0f, -12.0f, 7.0f, 12, 10, 1, 0.0f));
        this.ArmL = new ModelRenderer((ModelBase)this);
        this.ArmL.func_78793_a(9.0f, -7.0f, 0.0f);
        this.CouchBack.func_78792_a(this.ArmL);
        this.ArmL.field_78809_i = true;
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 24, 21, -3.0f, -5.0f, -8.0f, 2, 2, 12, 0.0f));
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 14, 17, -2.0f, -3.0f, -6.0f, 0, 4, 10, 0.0f));
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 40, 23, -3.0f, -3.0f, -8.0f, 2, 8, 2, 0.0f));
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 0, 21, -3.0f, 1.0f, -6.0f, 2, 4, 10, 0.0f));
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 22, 48, -3.0f, -5.0f, 4.0f, 2, 10, 4, 0.0f));
        this.ArmL.field_78809_i = false;
        this.ArmR = new ModelRenderer((ModelBase)this);
        this.ArmR.func_78793_a(-9.0f, -7.0f, 0.0f);
        this.CouchBack.func_78792_a(this.ArmR);
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 24, 21, 1.0f, -5.0f, -8.0f, 2, 2, 12, 0.0f));
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 14, 17, 2.0f, -3.0f, -6.0f, 0, 4, 10, 0.0f));
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 0, 21, 1.0f, 1.0f, -6.0f, 2, 4, 10, 0.0f));
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 40, 23, 1.0f, -3.0f, -8.0f, 2, 8, 2, 0.0f));
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 22, 48, 1.0f, -5.0f, 4.0f, 2, 10, 4, 0.0f));
        this.Cussion = new ModelRenderer((ModelBase)this);
        this.Cussion.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 2, 0, -6.0f, -7.0f, -8.0f, 12, 4, 15, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 2, 19, -6.0f, -16.0f, 3.0f, 12, 9, 4, 0.0f));
        this.Cussion.field_78804_l.add(new ModelBox(this.Cussion, 2, 32, -6.0f, -16.0f, 7.0f, 12, 4, 1, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

