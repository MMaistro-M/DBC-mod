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

public class ModelPamput
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Hair;
    public ModelRenderer HairFront;
    public ModelRenderer HairBack;
    public ModelRenderer HairTop;
    public ModelRenderer HairSides;
    public ModelRenderer HairBack2;
    public ModelRenderer HairTop2;
    public ModelRenderer HairSides2;
    public ModelRenderer HairSides3;

    public ModelPamput() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.HairSides = new ModelRenderer((ModelBase)this, 90, 25);
        this.HairSides.func_78793_a(0.0f, -0.4f, 1.2f);
        this.HairSides.func_78790_a(-5.0f, -3.0f, -0.9f, 10, 6, 4, 0.0f);
        this.HairTop = new ModelRenderer((ModelBase)this, 94, 2);
        this.HairTop.func_78793_a(0.0f, 0.3f, 0.0f);
        this.HairTop.func_78790_a(-3.5f, -5.0f, -4.0f, 7, 2, 8, 0.0f);
        this.HairBack = new ModelRenderer((ModelBase)this, 68, 16);
        this.HairBack.func_78793_a(0.0f, 0.0f, 4.9f);
        this.HairBack.func_78790_a(-3.5f, -3.0f, -0.2f, 7, 6, 1, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 26, 19);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 26, 19);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 65, 1);
        this.Hair.func_78793_a(0.0f, -4.7f, 0.0f);
        this.Hair.func_78790_a(-4.5f, -4.0f, 0.0f, 9, 8, 5, 0.0f);
        this.HairBack2 = new ModelRenderer((ModelBase)this, 68, 24);
        this.HairBack2.func_78793_a(0.0f, 0.0f, 1.0f);
        this.HairBack2.func_78790_a(-2.5f, -2.0f, -0.7f, 5, 4, 1, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 35);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.HairFront = new ModelRenderer((ModelBase)this, 34, 3);
        this.HairFront.func_78793_a(0.0f, -0.8f, -2.6f);
        this.HairFront.func_78790_a(-4.5f, -3.0f, -2.4f, 9, 4, 5, 0.0f);
        this.HairSides3 = new ModelRenderer((ModelBase)this, 91, 44);
        this.HairSides3.func_78793_a(0.0f, 1.0f, 0.4f);
        this.HairSides3.func_78790_a(-5.5f, -3.0f, -0.9f, 11, 4, 3, 0.0f);
        this.HairSides2 = new ModelRenderer((ModelBase)this, 90, 36);
        this.HairSides2.func_78793_a(0.0f, 0.0f, -1.7f);
        this.HairSides2.func_78790_a(-5.0f, -3.0f, -4.2f, 10, 2, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 35);
        this.LegR.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.HairTop2 = new ModelRenderer((ModelBase)this, 95, 14);
        this.HairTop2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairTop2.func_78790_a(-2.0f, -5.5f, -2.5f, 4, 2, 5, 0.0f);
        this.Hair.func_78792_a(this.HairSides);
        this.Hair.func_78792_a(this.HairTop);
        this.Hair.func_78792_a(this.HairBack);
        this.Head.func_78792_a(this.Hair);
        this.HairBack.func_78792_a(this.HairBack2);
        this.Hair.func_78792_a(this.HairFront);
        this.HairSides.func_78792_a(this.HairSides3);
        this.HairSides.func_78792_a(this.HairSides2);
        this.HairTop.func_78792_a(this.HairTop2);
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

