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

public class ModelPaparoni
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair1;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Beard;
    public ModelRenderer Hair2;
    public ModelRenderer HairR1;
    public ModelRenderer HairL1;
    public ModelRenderer HairR2;
    public ModelRenderer HairR3;
    public ModelRenderer HairL2;
    public ModelRenderer HairL3;
    public ModelRenderer Body3;
    public ModelRenderer Body2;
    public ModelRenderer Body4;
    public ModelRenderer Neckerchief;

    public ModelPaparoni() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, -2.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 7, 4, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 1, 29);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 7.0f, -1.6f, 7, 3, 3, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 26, 36);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.6f, -0.5f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -1.8f, -2.0f, 4, 14, 4, -0.3f);
        this.HairR2 = new ModelRenderer((ModelBase)this, 56, 13);
        this.HairR2.func_78793_a(0.0f, 2.8f, 0.0f);
        this.HairR2.func_78790_a(-0.7f, -0.3f, -0.8f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.HairR2, -0.13665928f, 0.0f, 0.045553092f);
        this.ArmR = new ModelRenderer((ModelBase)this, 26, 36);
        this.ArmR.func_78793_a(-4.6f, -0.5f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -1.8f, -2.0f, 4, 14, 4, -0.3f);
        this.HairR3 = new ModelRenderer((ModelBase)this, 57, 18);
        this.HairR3.func_78793_a(0.0f, 1.9f, 0.0f);
        this.HairR3.func_78790_a(-0.6f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HairR3, -0.13665928f, 0.0f, 0.045553092f);
        this.LegL = new ModelRenderer((ModelBase)this, 1, 45);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 11.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.HairL3 = new ModelRenderer((ModelBase)this, 57, 18);
        this.HairL3.func_78793_a(0.0f, 1.9f, 0.0f);
        this.HairL3.func_78790_a(-0.6f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HairL3, -0.13665928f, 0.0f, -0.045553092f);
        this.HairL1 = new ModelRenderer((ModelBase)this, 57, 8);
        this.HairL1.func_78793_a(4.2f, 2.8f, -0.7f);
        this.HairL1.func_78790_a(-0.6f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HairL1, -0.13665928f, 0.0f, -0.045553092f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -2.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 50, 1);
        this.Hair2.func_78793_a(0.0f, 0.0f, -3.9f);
        this.Hair2.func_78790_a(-1.5f, -1.4f, -2.9f, 3, 3, 3, 0.0f);
        this.HairL2 = new ModelRenderer((ModelBase)this, 56, 13);
        this.HairL2.func_78793_a(0.0f, 2.8f, 0.0f);
        this.HairL2.func_78790_a(-0.5f, -0.3f, -0.8f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.HairL2, -0.13665928f, 0.0f, -0.045553092f);
        this.EarL = new ModelRenderer((ModelBase)this, 27, 3);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.6f, -3.5f, -0.5f);
        this.EarL.func_78790_a(-0.5f, -1.5f, 0.0f, 5, 3, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.43633232f, -0.2268928f);
        this.Neckerchief = new ModelRenderer((ModelBase)this, 28, 29);
        this.Neckerchief.func_78793_a(0.0f, 0.0f, -1.4f);
        this.Neckerchief.func_78790_a(-1.5f, -0.1f, -0.2f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.Neckerchief, -0.06981317f, 0.0f, 0.0f);
        this.Beard = new ModelRenderer((ModelBase)this, 33, 12);
        this.Beard.func_78793_a(0.0f, -0.7f, -4.1f);
        this.Beard.func_78790_a(-1.5f, 0.0f, 0.0f, 3, 2, 0, 0.0f);
        this.setRotateAngle(this.Beard, -0.02094395f, 0.0f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 27, 3);
        this.EarR.func_78793_a(-3.6f, -3.5f, -0.5f);
        this.EarR.func_78790_a(-4.4f, -1.5f, 0.0f, 5, 3, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.43633232f, 0.2268928f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 33, 1);
        this.Hair1.func_78793_a(0.0f, -8.0f, 0.0f);
        this.Hair1.func_78790_a(-2.0f, -2.8f, -4.0f, 4, 3, 7, 0.0f);
        this.HairR1 = new ModelRenderer((ModelBase)this, 57, 8);
        this.HairR1.func_78793_a(-4.0f, 2.8f, -0.7f);
        this.HairR1.func_78790_a(-0.6f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HairR1, -0.13665928f, 0.0f, 0.045553092f);
        this.Body2 = new ModelRenderer((ModelBase)this, 25, 18);
        this.Body2.func_78793_a(0.0f, 0.0f, -0.2f);
        this.Body2.func_78790_a(-3.5f, 0.0f, -1.5f, 7, 7, 3, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 1, 45);
        this.LegR.func_78793_a(-2.0f, 11.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 0, 36);
        this.Body4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body4.func_78790_a(-4.0f, 10.0f, -2.0f, 8, 3, 4, 0.0f);
        this.Body1.func_78792_a(this.Body3);
        this.HairR1.func_78792_a(this.HairR2);
        this.HairR2.func_78792_a(this.HairR3);
        this.HairL2.func_78792_a(this.HairL3);
        this.Hair1.func_78792_a(this.HairL1);
        this.Hair1.func_78792_a(this.Hair2);
        this.HairL1.func_78792_a(this.HairL2);
        this.Head.func_78792_a(this.EarL);
        this.Body2.func_78792_a(this.Neckerchief);
        this.Head.func_78792_a(this.Beard);
        this.Head.func_78792_a(this.EarR);
        this.Head.func_78792_a(this.Hair1);
        this.Hair1.func_78792_a(this.HairR1);
        this.Body1.func_78792_a(this.Body2);
        this.Body3.func_78792_a(this.Body4);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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

