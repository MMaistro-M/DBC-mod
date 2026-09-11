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

public class ModelSorbet
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Head_1;
    public ModelRenderer Skirt;
    public ModelRenderer Cape;
    public ModelRenderer Chest;
    public ModelRenderer ShoulderL;
    public ModelRenderer ShoulderR;

    public ModelSorbet() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.LegL = new ModelRenderer((ModelBase)this, 4, 50);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 15.0f, 0.0f);
        this.LegL.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 9, 3, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 4, 50);
        this.LegR.func_78793_a(-1.9f, 15.0f, 0.0f);
        this.LegR.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 9, 3, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 8.1f, 0.0f);
        this.Head.func_78790_a(-4.0f, -5.3f, -4.1f, 8, 6, 7, -0.2f);
        this.Skirt = new ModelRenderer((ModelBase)this, 1, 30);
        this.Skirt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Skirt.func_78790_a(-4.5f, 3.9f, -3.6f, 9, 9, 7, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 26, 9);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-3.0f, -1.2f, -2.6f, 4, 2, 5, 0.0f);
        this.setRotateAngle(this.ShoulderR, 0.0f, 0.0f, -0.04363323f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 15);
        this.Body.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.3f, -3.0f, 8, 4, 6, 0.0f);
        this.Head_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head_1.func_78790_a(-1.0f, -2.3f, -4.8f, 2, 2, 1, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 26, 9);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-1.1f, -1.2f, -2.5f, 4, 2, 5, 0.0f);
        this.setRotateAngle(this.ShoulderL, 0.0f, 0.0f, 0.04363323f);
        this.ArmR = new ModelRenderer((ModelBase)this, 42, 30);
        this.ArmR.func_78793_a(-5.0f, 9.5f, 0.5f);
        this.ArmR.func_78790_a(-1.5f, -0.6f, -1.4f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.04363323f);
        this.Chest = new ModelRenderer((ModelBase)this, 6, 26);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest.func_78790_a(-3.5f, 1.3f, -3.1f, 7, 2, 1, 0.0f);
        this.Cape = new ModelRenderer((ModelBase)this, 32, 19);
        this.Cape.func_78793_a(0.0f, 2.2f, 3.0f);
        this.Cape.func_78790_a(-4.5f, 0.0f, 0.0f, 9, 9, 0, 0.0f);
        this.setRotateAngle(this.Cape, 0.2635447f, 0.0f, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 42, 30);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.1f, 9.5f, 0.5f);
        this.ArmL.func_78790_a(-0.6f, -0.6f, -1.5f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.04363323f);
        this.Body.func_78792_a(this.Skirt);
        this.ArmR.func_78792_a(this.ShoulderR);
        this.Head.func_78792_a(this.Head_1);
        this.ArmL.func_78792_a(this.ShoulderL);
        this.Body.func_78792_a(this.Chest);
        this.Body.func_78792_a(this.Cape);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body.func_78785_a(f5);
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
        float rota = -MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (rota > 0.0f) {
            rota *= -1.0f;
        }
        this.Cape.field_78795_f = -0.23f + rota;
        if (0.0f > this.Cape.field_78795_f) {
            this.Cape.field_78795_f *= -1.0f;
        }
        this.Cape.field_78796_g = 0.0f;
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

