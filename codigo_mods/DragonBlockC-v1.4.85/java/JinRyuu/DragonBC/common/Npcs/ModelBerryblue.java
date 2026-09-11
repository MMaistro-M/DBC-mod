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

public class ModelBerryblue
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer BodyLower;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Skirt;
    public ModelRenderer UpperBody;
    public ModelRenderer Neck;
    public ModelRenderer GrannyBoobs;
    public ModelRenderer ShoulderL;
    public ModelRenderer ShoulderR;

    public ModelBerryblue() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Skirt = new ModelRenderer((ModelBase)this, 3, 37);
        this.Skirt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Skirt.func_78790_a(-4.5f, 6.0f, -3.5f, 9, 7, 7, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 4, 52);
        this.LegR.func_78793_a(-1.9f, 15.0f, 0.0f);
        this.LegR.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 9, 3, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 33, 9);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-3.6f, -1.2f, -2.0f, 4, 2, 4, 0.0f);
        this.setRotateAngle(this.ShoulderR, 0.0f, 0.0f, -0.10471976f);
        this.LegL = new ModelRenderer((ModelBase)this, 4, 52);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 15.0f, 0.0f);
        this.LegL.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 9, 3, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 46, 21);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.6f, 9.2f, 0.8f);
        this.ArmL.func_78790_a(-0.6f, -0.9f, -1.5f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.1308997f);
        this.Neck = new ModelRenderer((ModelBase)this, 6, 14);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -0.5f, -1.3f, 4, 1, 3, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -6.1f, -3.5f, 8, 6, 7, 0.0f);
        this.BodyLower = new ModelRenderer((ModelBase)this, 1, 27);
        this.BodyLower.func_78793_a(0.0f, 8.0f, 0.0f);
        this.BodyLower.func_78790_a(-4.0f, 3.0f, -3.0f, 8, 3, 6, 0.0f);
        this.GrannyBoobs = new ModelRenderer((ModelBase)this, 24, 24);
        this.GrannyBoobs.func_78793_a(0.0f, 0.0f, 0.0f);
        this.GrannyBoobs.func_78790_a(-3.5f, 0.9f, -0.8f, 7, 4, 3, 0.0f);
        this.setRotateAngle(this.GrannyBoobs, -0.7740535f, 0.0f, 0.0f);
        this.UpperBody = new ModelRenderer((ModelBase)this, 3, 19);
        this.UpperBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.UpperBody.func_78790_a(-4.0f, 0.0f, -1.2f, 8, 3, 4, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 33, 9);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-0.6f, -1.2f, -2.0f, 4, 2, 4, 0.0f);
        this.setRotateAngle(this.ShoulderL, 0.0f, 0.0f, 0.10471976f);
        this.ArmR = new ModelRenderer((ModelBase)this, 46, 21);
        this.ArmR.func_78793_a(-4.4f, 9.2f, 0.8f);
        this.ArmR.func_78790_a(-1.5f, -0.9f, -1.5f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.1308997f);
        this.BodyLower.func_78792_a(this.Skirt);
        this.ArmR.func_78792_a(this.ShoulderR);
        this.UpperBody.func_78792_a(this.Neck);
        this.UpperBody.func_78792_a(this.GrannyBoobs);
        this.BodyLower.func_78792_a(this.UpperBody);
        this.ArmL.func_78792_a(this.ShoulderL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.BodyLower.func_78785_a(f5);
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

