/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelKrillin
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer UpperBody;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Belt;
    public ModelRenderer Lowerbody;
    public ModelRenderer Neck;
    public ModelRenderer Belt2;
    public ModelRenderer Belt3;
    public ModelRenderer FeetR;
    public ModelRenderer FeetL;

    public ModelKrillin() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.FeetR = new ModelRenderer((ModelBase)this, 17, 53);
        this.FeetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetR.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 30, 26);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.3f, 5.6f, 0.4f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 11, 4, -0.6f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegR.func_78793_a(-1.9f, 14.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.Belt3 = new ModelRenderer((ModelBase)this, 1, 35);
        this.Belt3.func_78793_a(0.3f, 6.4f, -1.9f);
        this.Belt3.func_78790_a(-0.4f, 0.0f, 0.0f, 1, 5, 0, 0.0f);
        this.setRotateAngle(this.Belt3, -0.28274333f, -0.13665928f, -0.0418879f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 4.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.7f);
        this.ArmR = new ModelRenderer((ModelBase)this, 30, 26);
        this.ArmR.func_78793_a(-4.3f, 5.6f, 0.4f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 11, 4, -0.6f);
        this.Lowerbody = new ModelRenderer((ModelBase)this, 4, 41);
        this.Lowerbody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Lowerbody.func_78790_a(-3.5f, 7.0f, -2.0f, 7, 3, 4, 0.0f);
        this.Belt2 = new ModelRenderer((ModelBase)this, 1, 35);
        this.Belt2.func_78793_a(-0.4f, 6.4f, -1.9f);
        this.Belt2.func_78790_a(-0.6f, 0.0f, 0.0f, 1, 5, 0, 0.0f);
        this.setRotateAngle(this.Belt2, -0.3036873f, 0.0f, 0.0418879f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 14.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.Belt = new ModelRenderer((ModelBase)this, 6, 35);
        this.Belt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Belt.func_78790_a(-3.1f, 5.5f, -1.7f, 6, 2, 3, 0.0f);
        this.FeetL = new ModelRenderer((ModelBase)this, 17, 53);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetL.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 7, 17);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -1.2f, -1.0f, 4, 2, 3, 0.0f);
        this.UpperBody = new ModelRenderer((ModelBase)this, 3, 23);
        this.UpperBody.func_78793_a(0.0f, 4.0f, 0.0f);
        this.UpperBody.func_78790_a(-4.0f, 0.0f, -2.3f, 8, 6, 5, 0.0f);
        this.LegR.func_78792_a(this.FeetR);
        this.Belt.func_78792_a(this.Belt3);
        this.UpperBody.func_78792_a(this.Lowerbody);
        this.Belt.func_78792_a(this.Belt2);
        this.UpperBody.func_78792_a(this.Belt);
        this.LegL.func_78792_a(this.FeetL);
        this.UpperBody.func_78792_a(this.Neck);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.UpperBody.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        int calc = par7Entity.field_70173_aa;
        if (calc > 100) {
            calc -= 100;
        }
        float r = 360.0f;
        float r2 = 180.0f;
        float n4 = par4;
        float n5 = par5;
        this.Head.field_78796_g = n4 / (r2 / (float)Math.PI);
        this.Head.field_78795_f = n5 / (r2 / (float)Math.PI);
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

