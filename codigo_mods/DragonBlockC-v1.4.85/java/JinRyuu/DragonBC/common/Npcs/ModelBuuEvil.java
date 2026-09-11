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

public class ModelBuuEvil
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer Head2;
    public ModelRenderer Jaw;
    public ModelRenderer Head3;
    public ModelRenderer Head4;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer CapeBase;
    public ModelRenderer Cape3R;
    public ModelRenderer Cape3L;
    public ModelRenderer CapeShoulderR;
    public ModelRenderer CapeShoulderL;
    public ModelRenderer Body3;

    public ModelBuuEvil() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 25);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, -0.5f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -1.5f, -1.8f, 3, 13, 4, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 15, 19);
        this.Body1.func_78793_a(0.0f, -2.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 5, 4, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 25);
        this.ArmR.func_78793_a(-5.0f, -0.5f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -1.5f, -1.8f, 3, 13, 4, 0.0f);
        this.Cape3R = new ModelRenderer((ModelBase)this, 43, 15);
        this.Cape3R.func_78793_a(-0.5f, 1.4f, -1.6f);
        this.Cape3R.func_78790_a(-0.5f, 0.2f, -0.4f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.Cape3R, -0.27314404f, 0.3642502f, 0.8651597f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegR.func_78793_a(-1.9f, 10.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 14, 4, 0.0f);
        this.CapeBase = new ModelRenderer((ModelBase)this, 43, 23);
        this.CapeBase.func_78793_a(0.0f, 0.3f, 2.3f);
        this.CapeBase.func_78790_a(-4.5f, 0.0f, -0.3f, 9, 19, 1, 0.0f);
        this.setRotateAngle(this.CapeBase, 0.090757124f, 0.0f, 0.0f);
        this.CapeShoulderL = new ModelRenderer((ModelBase)this, 44, 15);
        this.CapeShoulderL.field_78809_i = true;
        this.CapeShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeShoulderL.func_78790_a(2.4f, -0.1f, -2.3f, 3, 1, 5, 0.0f);
        this.Jaw = new ModelRenderer((ModelBase)this, 33, 8);
        this.Jaw.func_78793_a(0.0f, -3.1f, 0.0f);
        this.Jaw.func_78790_a(-3.0f, 2.9f, -3.4f, 6, 2, 3, 0.0f);
        this.CapeShoulderR = new ModelRenderer((ModelBase)this, 44, 15);
        this.CapeShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeShoulderR.func_78790_a(-5.4f, -0.1f, -2.3f, 3, 1, 5, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 17, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 5.0f, -1.7f, 6, 4, 3, 0.0f);
        this.Cape3L = new ModelRenderer((ModelBase)this, 43, 15);
        this.Cape3L.field_78809_i = true;
        this.Cape3L.func_78793_a(0.4f, 1.4f, -1.6f);
        this.Cape3L.func_78790_a(-1.6f, 0.2f, -0.4f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.Cape3L, -0.31869712f, -0.40142572f, -0.8196066f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -4.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -6.2f, -4.4f, 8, 7, 8, -1.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 15, 37);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 10.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 14, 4, 0.0f);
        this.Head4 = new ModelRenderer((ModelBase)this, 44, 0);
        this.Head4.func_78793_a(0.0f, -1.6f, -0.2f);
        this.Head4.func_78790_a(-0.5f, -1.9f, -0.8f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Head4, -0.4098033f, 0.0f, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Head2.func_78793_a(0.0f, -4.8f, -2.0f);
        this.Head2.func_78790_a(-1.0f, -3.8f, -1.3f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Head2, -0.22759093f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 2, 19);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -2.8f, -0.5f, 4, 3, 2, 0.0f);
        this.Head3 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Head3.func_78793_a(0.0f, -3.3f, -0.1f);
        this.Head3.func_78790_a(-0.5f, -1.9f, -1.1f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.Head3, -0.4553564f, 0.0f, 0.0f);
        this.Body1.func_78792_a(this.Cape3R);
        this.Body1.func_78792_a(this.CapeBase);
        this.Body1.func_78792_a(this.CapeShoulderL);
        this.Head.func_78792_a(this.Jaw);
        this.Body1.func_78792_a(this.CapeShoulderR);
        this.Body1.func_78792_a(this.Body2);
        this.Body1.func_78792_a(this.Cape3L);
        this.Body2.func_78792_a(this.Body3);
        this.Head3.func_78792_a(this.Head4);
        this.Head.func_78792_a(this.Head2);
        this.Body1.func_78792_a(this.Neck);
        this.Head2.func_78792_a(this.Head3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
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
        float rota = -MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (rota > 0.0f) {
            rota *= -1.0f;
        }
        this.CapeBase.field_78795_f = -0.23f + rota;
        if (0.0f > this.CapeBase.field_78795_f) {
            this.CapeBase.field_78795_f *= -1.0f;
        }
        this.CapeBase.field_78796_g = 0.0f;
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

