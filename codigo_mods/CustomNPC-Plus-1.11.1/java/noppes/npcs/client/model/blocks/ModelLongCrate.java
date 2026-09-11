/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLongCrate
extends ModelBase {
    ModelRenderer Vertical1 = new ModelRenderer((ModelBase)this, 80, 0);
    ModelRenderer Horizontal1;
    ModelRenderer Cratebody;
    ModelRenderer Horizontal2;
    ModelRenderer Vertical3;
    ModelRenderer Vertical4;
    ModelRenderer Vertical2;

    public ModelLongCrate() {
        this.Vertical1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 1);
        this.Vertical1.func_78793_a(-12.0f, 11.0f, 8.0f);
        this.Horizontal1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Horizontal1.field_78809_i = true;
        this.Horizontal1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 18);
        this.Horizontal1.func_78793_a(8.0f, 10.0f, -9.0f);
        this.Cratebody = new ModelRenderer((ModelBase)this, 8, 0);
        this.Cratebody.func_78789_a(-16.0f, 0.0f, -8.0f, 32, 13, 16);
        this.Cratebody.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Horizontal2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Horizontal2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 18);
        this.Horizontal2.func_78793_a(-12.0f, 10.0f, -9.0f);
        this.Vertical3 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Vertical3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 1);
        this.Vertical3.func_78793_a(-12.0f, 11.0f, -9.0f);
        this.Vertical4 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Vertical4.field_78809_i = true;
        this.Vertical4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 1);
        this.Vertical4.func_78793_a(8.0f, 11.0f, -9.0f);
        this.Vertical2 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Vertical2.field_78809_i = true;
        this.Vertical2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 1);
        this.Vertical2.func_78793_a(8.0f, 11.0f, 8.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Vertical1.func_78785_a(f5);
        this.Horizontal1.func_78785_a(f5);
        this.Cratebody.func_78785_a(f5);
        this.Horizontal2.func_78785_a(f5);
        this.Vertical3.func_78785_a(f5);
        this.Vertical4.func_78785_a(f5);
        this.Vertical2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

