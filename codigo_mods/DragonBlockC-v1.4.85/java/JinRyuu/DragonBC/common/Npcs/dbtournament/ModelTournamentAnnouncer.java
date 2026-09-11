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

public class ModelTournamentAnnouncer
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Arm;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Glasses;
    public ModelRenderer Body2;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer Mic1;
    public ModelRenderer Mic2;

    public ModelTournamentAnnouncer() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.3f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 34);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 43, 43);
        this.Hair2.func_78793_a(0.0f, -6.0f, 0.0f);
        this.Hair2.func_78790_a(-2.0f, -2.5f, -4.2f, 4, 3, 6, 0.0f);
        this.Glasses = new ModelRenderer((ModelBase)this, 32, 0);
        this.Glasses.func_78793_a(0.0f, 0.3f, 0.0f);
        this.Glasses.func_78790_a(-4.0f, -6.2f, -3.9f, 8, 3, 8, 0.0f);
        this.Arm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Arm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Arm.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 34);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 18, 53);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 6, 4, 0.0f);
        this.setRotateAngle(this.ArmR2, -0.5462881f, -0.5009095f, 0.0f);
        this.Mic1 = new ModelRenderer((ModelBase)this, 49, 14);
        this.Mic1.func_78793_a(0.7f, 5.1f, -0.2f);
        this.Mic1.func_78790_a(-0.5f, -2.2f, -0.5f, 1, 5, 1, 0.0f);
        this.setRotateAngle(this.Mic1, 1.5025539f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 27, 18);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 0.1f, -1.8f, 7, 7, 3, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 40, 56);
        this.Hair1.func_78793_a(0.0f, -5.3f, 3.1f);
        this.Hair1.func_78790_a(-4.0f, -2.7f, -3.0f, 8, 4, 4, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 0, 53);
        this.ArmR3.func_78793_a(-1.0f, 4.0f, 0.0f);
        this.ArmR3.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.setRotateAngle(this.ArmR3, -1.0927507f, -0.22759093f, 0.0f);
        this.Mic2 = new ModelRenderer((ModelBase)this, 54, 13);
        this.Mic2.func_78793_a(0.0f, -2.6f, 0.0f);
        this.Mic2.func_78790_a(-1.0f, -1.5f, -1.0f, 2, 2, 2, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 18, 34);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Head.func_78792_a(this.Hair2);
        this.Head.func_78792_a(this.Glasses);
        this.Arm.func_78792_a(this.ArmR2);
        this.ArmR3.func_78792_a(this.Mic1);
        this.Body.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.Hair1);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Mic1.func_78792_a(this.Mic2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.Arm.func_78785_a(f5);
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
        this.ArmL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
    }
}

