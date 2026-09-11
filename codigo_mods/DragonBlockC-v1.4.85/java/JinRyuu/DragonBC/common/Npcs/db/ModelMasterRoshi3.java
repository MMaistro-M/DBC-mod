/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.db;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMasterRoshi3
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Beard;
    public ModelRenderer Beard2;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer FeetR;
    public ModelRenderer FeetL;

    public ModelMasterRoshi3() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 4.7f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.7f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 43, 43);
        this.Hair2.func_78793_a(0.0f, -6.0f, 0.0f);
        this.Hair2.func_78790_a(-2.0f, -2.7f, -3.6f, 4, 3, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegR.func_78793_a(-1.9f, 14.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 6, 35);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.1f, 5.5f, -1.5f, 6, 2, 3, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 28, 23);
        this.ArmR.func_78793_a(-3.9f, 5.4f, 0.1f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 11, 4, -0.6f);
        this.Beard = new ModelRenderer((ModelBase)this, 27, 2);
        this.Beard.func_78793_a(0.0f, -2.0f, -3.4f);
        this.Beard.func_78790_a(-3.0f, -1.1f, 0.0f, 6, 4, 0, 0.0f);
        this.FeetR = new ModelRenderer((ModelBase)this, 17, 53);
        this.FeetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetR.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.FeetL = new ModelRenderer((ModelBase)this, 17, 53);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetL.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 3, 23);
        this.Body.func_78793_a(0.0f, 4.0f, 0.1f);
        this.Body.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 6, 4, 0.0f);
        this.Beard2 = new ModelRenderer((ModelBase)this, 33, 9);
        this.Beard2.func_78793_a(0.0f, 0.7f, 0.15f);
        this.Beard2.func_78790_a(-2.0f, -0.6f, -0.1f, 4, 4, 0, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 4, 41);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 7.0f, -2.0f, 7, 3, 4, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 40, 56);
        this.Hair1.func_78793_a(0.0f, -5.3f, 2.7f);
        this.Hair1.func_78790_a(-3.5f, -2.5f, -2.1f, 7, 4, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 14.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 28, 23);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(3.9f, 5.4f, 0.1f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 11, 4, -0.6f);
        this.Head.func_78792_a(this.Hair2);
        this.Body.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.Beard);
        this.LegR.func_78792_a(this.FeetR);
        this.LegL.func_78792_a(this.FeetL);
        this.Beard.func_78792_a(this.Beard2);
        this.Body.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.Hair1);
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
    }
}

