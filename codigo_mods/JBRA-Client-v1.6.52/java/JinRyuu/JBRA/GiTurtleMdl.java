/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.JBRA;

import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class GiTurtleMdl
extends ModelBipedBody {
    ModelRenderer leftarmshoulder;
    ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
    ModelRenderer cape;
    ModelRenderer c20;
    ModelRenderer c19;

    public GiTurtleMdl(float s) {
        super(s, 0.0f, 128, 64);
        this.rightarmshoulder.func_78790_a(-3.0f, -5.0f, -3.0f, 7, 4, 6, s);
        this.rightarmshoulder.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarmshoulder.func_78787_b(128, 64);
        this.setRotation(this.rightarmshoulder, 0.0f, 0.0f, 0.1570796f);
        this.leftarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
        this.leftarmshoulder.field_78809_i = true;
        this.leftarmshoulder.func_78790_a(-4.0f, -5.0f, -3.0f, 7, 4, 6, s);
        this.leftarmshoulder.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarmshoulder.func_78787_b(128, 64);
        this.setRotation(this.leftarmshoulder, 0.0f, 0.0f, -0.1570796f);
        this.cape = new ModelRenderer((ModelBase)this, 100, 0);
        this.cape.func_78790_a(-7.0f, 1.0f, 3.0f, 14, 20, 0, s);
        this.cape.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cape.func_78787_b(128, 64);
        this.setRotation(this.cape, 0.1570796f, 0.0f, 0.0f);
        this.c20 = new ModelRenderer((ModelBase)this, 76, 35);
        this.c20.func_78790_a(-4.0f, -12.0f, -4.0f, 8, 4, 8, s);
        this.c20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.c20.func_78787_b(128, 64);
        this.c20.field_78809_i = true;
        this.setRotation(this.c20, 0.0f, 0.0f, 0.0f);
        this.c19 = new ModelRenderer((ModelBase)this, 106, 29);
        this.c19.func_78790_a(-1.0f, -11.0f, -0.5f, 2, 4, 2, s);
        this.c19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.c19.func_78787_b(128, 64);
        this.c19.field_78809_i = true;
        this.setRotation(this.c19, 0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.c20);
        this.field_78116_c.func_78792_a(this.c19);
        this.field_78115_e.func_78792_a(this.cape);
        this.body.func_78792_a(this.cape);
        this.field_78113_g.func_78792_a(this.leftarmshoulder);
        this.field_78112_f.func_78792_a(this.rightarmshoulder);
        this.leftarm.func_78792_a(this.leftarmshoulder);
        this.rightarm.func_78792_a(this.rightarmshoulder);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        float s = 0.1070796f;
        if (this.cape != null) {
            if (y == 1) {
                float s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                float s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.cape.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.cape.field_78795_f = s;
            }
        }
    }
}

