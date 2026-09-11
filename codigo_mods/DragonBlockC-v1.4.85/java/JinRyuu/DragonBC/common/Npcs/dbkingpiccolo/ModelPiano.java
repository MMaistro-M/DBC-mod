/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbkingpiccolo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPiano
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Head2;
    public ModelRenderer Beak;
    public ModelRenderer Head3;
    public ModelRenderer Beak2;
    public ModelRenderer Cloth;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;
    public ModelRenderer FeetR;
    public ModelRenderer FeetL;

    public ModelPiano() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmR = new ModelRenderer((ModelBase)this, 27, 24);
        this.ArmR.func_78793_a(-4.6f, 10.4f, 0.1f);
        this.ArmR.func_78790_a(-3.0f, -1.7f, -2.0f, 4, 7, 4, -0.3f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 40);
        this.LegR.func_78793_a(-1.9f, 17.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 5, 4, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 23);
        this.Body.func_78793_a(0.0f, 9.0f, 0.1f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.5f, 8, 8, 5, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 27, 38);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(1.1f, 4.4f, 0.1f);
        this.ArmL2.func_78790_a(-1.6f, 0.0f, -1.5f, 3, 2, 3, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 9.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -6.9f, -4.0f, 8, 8, 8, -1.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 27, 24);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.6f, 10.4f, 0.1f);
        this.ArmL.func_78790_a(-1.0f, -1.7f, -2.0f, 4, 7, 4, -0.3f);
        this.Head2 = new ModelRenderer((ModelBase)this, 51, 2);
        this.Head2.func_78793_a(0.0f, -3.6f, 0.8f);
        this.Head2.func_78790_a(-0.5f, -3.6f, -2.3f, 1, 3, 5, 0.0f);
        this.setRotateAngle(this.Head2, 0.4553564f, 0.0f, 0.0f);
        this.FeetL = new ModelRenderer((ModelBase)this, 1, 54);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetL.func_78790_a(-1.5f, 5.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Beak = new ModelRenderer((ModelBase)this, 34, 5);
        this.Beak.func_78793_a(0.0f, -1.7f, -2.3f);
        this.Beak.func_78790_a(-2.5f, -1.5f, -2.6f, 5, 3, 3, 0.0f);
        this.setRotateAngle(this.Beak, 0.13962634f, 0.0f, 0.0f);
        this.Head3 = new ModelRenderer((ModelBase)this, 51, 11);
        this.Head3.func_78793_a(0.0f, -1.6f, 2.7f);
        this.Head3.func_78790_a(-0.5f, -2.0f, 0.0f, 1, 2, 4, 0.0f);
        this.FeetR = new ModelRenderer((ModelBase)this, 1, 54);
        this.FeetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetR.func_78790_a(-1.5f, 5.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Beak2 = new ModelRenderer((ModelBase)this, 34, 12);
        this.Beak2.func_78793_a(0.0f, 0.5f, -2.9f);
        this.Beak2.func_78790_a(-2.0f, -1.0f, -2.7f, 4, 2, 3, 0.0f);
        this.Cloth = new ModelRenderer((ModelBase)this, 45, 23);
        this.Cloth.func_78793_a(0.0f, 0.0f, -2.7f);
        this.Cloth.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 5, 0, 0.0f);
        this.setRotateAngle(this.Cloth, -0.05235988f, 0.0f, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 27, 38);
        this.ArmR2.func_78793_a(-1.0f, 4.5f, 0.1f);
        this.ArmR2.func_78790_a(-1.6f, 0.0f, -1.5f, 3, 2, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 40);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 17.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 5, 4, 0.0f);
        this.ArmL.func_78792_a(this.ArmL2);
        this.Head.func_78792_a(this.Head2);
        this.LegL.func_78792_a(this.FeetL);
        this.Head.func_78792_a(this.Beak);
        this.Head2.func_78792_a(this.Head3);
        this.LegR.func_78792_a(this.FeetR);
        this.Beak.func_78792_a(this.Beak2);
        this.Body.func_78792_a(this.Cloth);
        this.ArmR.func_78792_a(this.ArmR2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
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
        this.LegL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

