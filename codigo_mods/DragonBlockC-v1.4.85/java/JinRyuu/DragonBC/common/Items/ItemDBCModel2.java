/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package JinRyuu.DragonBC.common.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ItemDBCModel2
extends ModelBase {
    public ModelRenderer Handle;
    public ModelRenderer Handle2;
    public ModelRenderer Handle3;
    public ModelRenderer Handle4;
    public ModelRenderer Blade;
    public ModelRenderer Blade2;
    public ModelRenderer Blade3;
    public ModelRenderer Blade4;
    public ModelRenderer Blade5;

    public ItemDBCModel2() {
        this.field_78090_t = 64;
        this.field_78089_u = 256;
        this.Handle = new ModelRenderer((ModelBase)this, 0, 0);
        this.Handle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Handle.func_78790_a(-0.5f, 68.0f, -0.5f, 7, 2, 5, 0.0f);
        this.Handle2 = new ModelRenderer((ModelBase)this, 19, 0);
        this.Handle2.func_78793_a(1.0f, 20.0f, 0.5f);
        this.Handle2.func_78790_a(0.0f, 50.0f, 0.0f, 4, 2, 3, 0.0f);
        this.Blade = new ModelRenderer((ModelBase)this, 0, 17);
        this.Blade.func_78793_a(-11.0f, 1.0f, 1.0f);
        this.Blade.func_78790_a(-50.0f, -70.0f, 0.0f, 11, 11, 2, 0.0f);
        this.setRotateAngle(this.Blade, 0.0f, 0.0f, 0.7853982f);
        this.Blade4 = new ModelRenderer((ModelBase)this, 22, 5);
        this.Blade4.func_78793_a(2.0f, -77.0f, 1.0f);
        this.Blade4.func_78790_a(0.1f, 6.0f, 0.0f, 2, 8, 2, 0.0f);
        this.setRotateAngle(this.Blade4, 0.0f, 0.0f, 0.6265732f);
        this.Handle3 = new ModelRenderer((ModelBase)this, 33, 0);
        this.Handle3.func_78793_a(1.0f, -72.0f, 0.5f);
        this.Handle3.func_78790_a(0.0f, 10.0f, 0.0f, 4, 130, 3, 0.0f);
        this.Handle4 = new ModelRenderer((ModelBase)this, 0, 7);
        this.Handle4.func_78793_a(0.3f, -77.0f, -0.5f);
        this.Handle4.func_78790_a(0.0f, 10.0f, 0.0f, 5, 5, 5, 0.0f);
        this.Blade2 = new ModelRenderer((ModelBase)this, 47, 0);
        this.Blade2.func_78793_a(24.0f, 1.5f, 6.0f);
        this.Blade2.func_78790_a(-1.3f, -80.0f, -5.0f, 5, 9, 2, 0.0f);
        this.setRotateAngle(this.Blade2, 0.0f, 0.0f, -0.31869712f);
        this.Blade3 = new ModelRenderer((ModelBase)this, 47, 11);
        this.Blade3.func_78793_a(-20.0f, 1.5f, 6.0f);
        this.Blade3.func_78790_a(-1.7f, -80.0f, -5.0f, 5, 9, 2, 0.0f);
        this.setRotateAngle(this.Blade3, 0.0f, 0.0f, 0.31869712f);
        this.Blade5 = new ModelRenderer((ModelBase)this, 47, 22);
        this.Blade5.func_78793_a(2.0f, -76.0f, 1.0f);
        this.Blade5.func_78790_a(0.1f, 6.0f, 0.0f, 2, 8, 2, 0.0f);
        this.setRotateAngle(this.Blade5, 0.0f, 0.0f, -0.6265732f);
        this.Handle.func_78792_a(this.Handle2);
        this.Handle.func_78792_a(this.Blade);
        this.Handle.func_78792_a(this.Blade4);
        this.Handle.func_78792_a(this.Handle3);
        this.Handle.func_78792_a(this.Handle4);
        this.Handle.func_78792_a(this.Blade2);
        this.Handle.func_78792_a(this.Blade3);
        this.Handle.func_78792_a(this.Blade5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void render() {
        float par1 = 0.0625f;
        this.Handle.func_78785_a(par1);
    }
}

