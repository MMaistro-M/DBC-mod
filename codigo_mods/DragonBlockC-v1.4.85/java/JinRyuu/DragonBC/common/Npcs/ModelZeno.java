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

public class ModelZeno
extends ModelBase {
    public ModelRenderer Body1;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Head;
    public ModelRenderer Body2;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;
    public ModelRenderer EarR;
    public ModelRenderer EarL;

    public ModelZeno() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 8.2f, 0.0f);
        this.Head.func_78790_a(-4.0f, -5.6f, -3.6f, 8, 6, 7, -0.2f);
        this.LegR = new ModelRenderer((ModelBase)this, 30, 22);
        this.LegR.func_78793_a(-1.9f, 17.0f, 0.0f);
        this.LegR.func_78790_a(-1.1f, 0.0f, -1.5f, 2, 7, 3, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 27, 13);
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(3.1f, 1.0f, -1.9f, 3, 2, 4, 0.0f);
        this.setRotateAngle(this.ShoulderL, 0.0f, 0.0f, -0.13665928f);
        this.EarL = new ModelRenderer((ModelBase)this, 0, 0);
        this.EarL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL.func_78790_a(3.7f, -3.1f, -1.0f, 1, 2, 2, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-4.0f, 4.1f, -2.4f, 8, 7, 5, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 0, 0);
        this.EarR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR.func_78790_a(-4.7f, -3.1f, -1.0f, 1, 2, 2, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 27, 13);
        this.ShoulderR.field_78809_i = true;
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-6.2f, 1.0f, -1.9f, 3, 2, 4, 0.0f);
        this.setRotateAngle(this.ShoulderR, 0.0f, 0.0f, 0.13665928f);
        this.ArmL = new ModelRenderer((ModelBase)this, 42, 22);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.2f, 9.5f, 0.1f);
        this.ArmL.func_78790_a(-0.6f, -0.6f, -1.6f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.15358898f);
        this.LegL = new ModelRenderer((ModelBase)this, 30, 22);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 17.0f, 0.0f);
        this.LegL.func_78790_a(-1.1f, 0.0f, -1.5f, 2, 7, 3, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 14);
        this.Body1.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.3f, -2.4f, 8, 4, 5, -0.1f);
        this.ArmR = new ModelRenderer((ModelBase)this, 42, 22);
        this.ArmR.func_78793_a(-4.1f, 9.5f, 0.1f);
        this.ArmR.func_78790_a(-1.5f, -0.6f, -1.4f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.15358898f);
        this.Body1.func_78792_a(this.ShoulderL);
        this.Head.func_78792_a(this.EarL);
        this.Body1.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.EarR);
        this.Body1.func_78792_a(this.ShoulderR);
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

