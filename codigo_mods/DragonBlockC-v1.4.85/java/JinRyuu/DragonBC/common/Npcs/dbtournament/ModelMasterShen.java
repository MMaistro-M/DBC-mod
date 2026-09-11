/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbtournament;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMasterShen
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair;
    public ModelRenderer Beard;
    public ModelRenderer Hat;
    public ModelRenderer HairR;
    public ModelRenderer HairL;
    public ModelRenderer HairR2;
    public ModelRenderer HairL2;
    public ModelRenderer Hat2;
    public ModelRenderer Hat3;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;
    public ModelRenderer FeetR;
    public ModelRenderer FeetL;

    public ModelMasterShen() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmR = new ModelRenderer((ModelBase)this, 27, 24);
        this.ArmR.func_78793_a(-4.6f, 5.5f, 0.1f);
        this.ArmR.func_78790_a(-3.0f, -1.7f, -2.0f, 4, 9, 4, -0.3f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 4.7f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.7f);
        this.ArmL = new ModelRenderer((ModelBase)this, 27, 24);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.6f, 5.5f, 0.1f);
        this.ArmL.func_78790_a(-1.0f, -1.7f, -2.0f, 4, 9, 4, -0.3f);
        this.Hair = new ModelRenderer((ModelBase)this, 39, 55);
        this.Hair.func_78793_a(0.0f, -3.3f, 3.4f);
        this.Hair.func_78790_a(-4.0f, -2.5f, -2.9f, 8, 4, 4, 0.0f);
        this.HairR = new ModelRenderer((ModelBase)this, 53, 47);
        this.HairR.func_78793_a(-4.4f, -1.1f, -0.9f);
        this.HairR.func_78790_a(-1.0f, -1.5f, -1.5f, 2, 3, 3, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 27, 38);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(1.1f, 6.4f, 0.1f);
        this.ArmL2.func_78790_a(-1.6f, 0.0f, -1.5f, 3, 2, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 40);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 14.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.HairR2 = new ModelRenderer((ModelBase)this, 56, 41);
        this.HairR2.func_78793_a(-1.0f, -1.0f, 0.5f);
        this.HairR2.func_78790_a(-0.5f, -1.5f, -1.5f, 1, 3, 2, 0.0f);
        this.FeetL = new ModelRenderer((ModelBase)this, 1, 54);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetL.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 23);
        this.Body.func_78793_a(0.0f, 4.0f, 0.1f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.5f, 8, 10, 5, 0.0f);
        this.HairL = new ModelRenderer((ModelBase)this, 53, 47);
        this.HairL.func_78793_a(4.4f, -1.3f, -0.9f);
        this.HairL.func_78790_a(-1.0f, -1.5f, -1.5f, 2, 3, 3, 0.0f);
        this.Hat = new ModelRenderer((ModelBase)this, 40, 2);
        this.Hat.func_78793_a(0.0f, -6.7f, 0.1f);
        this.Hat.func_78790_a(-0.5f, -2.0f, -1.0f, 1, 2, 2, 0.0f);
        this.HairL2 = new ModelRenderer((ModelBase)this, 56, 41);
        this.HairL2.func_78793_a(0.9f, -1.0f, 0.5f);
        this.HairL2.func_78790_a(-0.5f, -1.5f, -1.5f, 1, 3, 2, 0.0f);
        this.FeetR = new ModelRenderer((ModelBase)this, 1, 54);
        this.FeetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetR.func_78790_a(-1.5f, 8.0f, -2.5f, 3, 2, 4, 0.0f);
        this.Beard = new ModelRenderer((ModelBase)this, 27, 2);
        this.Beard.func_78793_a(0.0f, -1.6f, -3.4f);
        this.Beard.func_78790_a(-3.0f, -1.1f, 0.0f, 6, 4, 0, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 40);
        this.LegR.func_78793_a(-1.9f, 14.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f);
        this.Hat2 = new ModelRenderer((ModelBase)this, 47, 1);
        this.Hat2.func_78793_a(0.0f, -3.0f, -1.0f);
        this.Hat2.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 2, 3, 0.0f);
        this.Hat3 = new ModelRenderer((ModelBase)this, 54, -1);
        this.Hat3.func_78793_a(0.0f, 0.0f, -2.0f);
        this.Hat3.func_78790_a(0.0f, -1.0f, -1.0f, 0, 2, 2, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 27, 38);
        this.ArmR2.func_78793_a(-1.0f, 6.5f, 0.1f);
        this.ArmR2.func_78790_a(-1.6f, 0.0f, -1.5f, 3, 2, 3, 0.0f);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.HairR);
        this.ArmL.func_78792_a(this.ArmL2);
        this.HairR.func_78792_a(this.HairR2);
        this.LegL.func_78792_a(this.FeetL);
        this.Hair.func_78792_a(this.HairL);
        this.Head.func_78792_a(this.Hat);
        this.HairL.func_78792_a(this.HairL2);
        this.LegR.func_78792_a(this.FeetR);
        this.Head.func_78792_a(this.Beard);
        this.Hat.func_78792_a(this.Hat2);
        this.Hat2.func_78792_a(this.Hat3);
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
    }
}

