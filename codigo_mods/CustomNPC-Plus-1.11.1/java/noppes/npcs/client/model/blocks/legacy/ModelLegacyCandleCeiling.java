/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.legacy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLegacyCandleCeiling
extends ModelBase {
    ModelRenderer Wax1 = new ModelRenderer((ModelBase)this, 16, 0);
    ModelRenderer Wax2;
    ModelRenderer Wax3;
    ModelRenderer Wax4;
    ModelRenderer TippyTop1;
    ModelRenderer TippyTop2;
    ModelRenderer Middle;
    ModelRenderer BottomBar1;
    ModelRenderer Rod1;
    ModelRenderer Rod2;
    ModelRenderer Rod3;
    ModelRenderer Rod4;
    ModelRenderer Base1;
    ModelRenderer Base2;
    ModelRenderer Base3;
    ModelRenderer Base4;
    ModelRenderer BottomBar3;
    ModelRenderer BottomBar2;
    ModelRenderer BottomBar4;

    public ModelLegacyCandleCeiling() {
        this.Wax1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax1.func_78793_a(-1.0f, 15.5f, 5.0f);
        this.Wax2 = new ModelRenderer((ModelBase)this, 16, 0);
        this.Wax2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax2.func_78793_a(7.0f, 15.5f, 1.0f);
        this.setRotation(this.Wax2, 0.0f, 3.141593f, 0.0f);
        this.Wax3 = new ModelRenderer((ModelBase)this, 16, 0);
        this.Wax3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax3.func_78793_a(-7.0f, 15.5f, -1.0f);
        this.Wax4 = new ModelRenderer((ModelBase)this, 16, 0);
        this.Wax4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Wax4.func_78793_a(1.0f, 15.5f, -5.0f);
        this.setRotation(this.Wax4, 0.0f, 3.141593f, 0.0f);
        this.TippyTop1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.TippyTop1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.TippyTop1.func_78793_a(-2.8f, 7.5f, 0.0f);
        this.setRotation(this.TippyTop1, 0.0f, 0.7853982f, 0.0f);
        this.TippyTop2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.TippyTop2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.TippyTop2.func_78793_a(-2.1f, 8.0f, 0.0f);
        this.setRotation(this.TippyTop2, 0.0f, 0.7853982f, 0.0f);
        this.Middle = new ModelRenderer((ModelBase)this, 0, 0);
        this.Middle.func_78789_a(0.0f, 0.0f, 0.0f, 1, 13, 1);
        this.Middle.func_78793_a(-0.5f, 9.0f, -0.5f);
        this.BottomBar1 = new ModelRenderer((ModelBase)this, 0, 4);
        this.BottomBar1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
        this.BottomBar1.func_78793_a(-0.5f, 21.0f, 0.5f);
        this.Rod1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rod1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Rod1.func_78793_a(-0.5f, 20.0f, 5.5f);
        this.Rod2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rod2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Rod2.func_78793_a(5.5f, 20.0f, -0.5f);
        this.Rod3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rod3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Rod3.func_78793_a(-6.5f, 20.0f, -0.5f);
        this.Rod4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Rod4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Rod4.func_78793_a(-0.5f, 20.0f, -6.5f);
        this.Base1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base1.func_78793_a(-2.0f, 19.0f, 4.0f);
        this.Base2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base2.func_78793_a(4.0f, 19.0f, -2.0f);
        this.Base3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base3.func_78793_a(-8.0f, 19.0f, -2.0f);
        this.Base4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Base4.func_78793_a(-2.0f, 19.0f, -8.0f);
        this.BottomBar3 = new ModelRenderer((ModelBase)this, 0, 4);
        this.BottomBar3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
        this.BottomBar3.func_78793_a(-0.5f, 21.0f, -0.5f);
        this.setRotation(this.BottomBar3, 0.0f, -1.570796f, 0.0f);
        this.BottomBar2 = new ModelRenderer((ModelBase)this, 0, 4);
        this.BottomBar2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
        this.BottomBar2.func_78793_a(0.5f, 21.0f, 0.5f);
        this.setRotation(this.BottomBar2, 0.0f, 1.570796f, 0.0f);
        this.BottomBar4 = new ModelRenderer((ModelBase)this, 0, 4);
        this.BottomBar4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
        this.BottomBar4.func_78793_a(0.4f, 21.0f, -0.5f);
        this.setRotation(this.BottomBar4, 0.0f, 3.141593f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Wax1.func_78785_a(f5);
        this.Wax2.func_78785_a(f5);
        this.Wax3.func_78785_a(f5);
        this.Wax4.func_78785_a(f5);
        this.TippyTop1.func_78785_a(f5);
        this.TippyTop2.func_78785_a(f5);
        this.Middle.func_78785_a(f5);
        this.BottomBar1.func_78785_a(f5);
        this.Rod1.func_78785_a(f5);
        this.Rod2.func_78785_a(f5);
        this.Rod3.func_78785_a(f5);
        this.Rod4.func_78785_a(f5);
        this.Base1.func_78785_a(f5);
        this.Base2.func_78785_a(f5);
        this.Base3.func_78785_a(f5);
        this.Base4.func_78785_a(f5);
        this.BottomBar3.func_78785_a(f5);
        this.BottomBar2.func_78785_a(f5);
        this.BottomBar4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

