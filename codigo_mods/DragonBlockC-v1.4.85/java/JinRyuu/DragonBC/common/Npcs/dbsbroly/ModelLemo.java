/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbsbroly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLemo
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Ear;
    public ModelRenderer Ear_1;
    public ModelRenderer Scouter;
    public ModelRenderer Body2;
    public ModelRenderer Body3;

    public ModelLemo() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 30);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 5.8f, -1.6f, 6, 3, 3, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Body1.func_78793_a(0.0f, 1.2f, 0.0f);
        this.Body1.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 6, 4, -0.1f);
        this.ArmR = new ModelRenderer((ModelBase)this, 26, 21);
        this.ArmR.func_78793_a(-4.6f, 2.7f, 0.0f);
        this.ArmR.func_78790_a(-1.7f, -1.4f, -1.8f, 3, 11, 4, -0.1f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, (float)Math.PI / 90);
        this.ArmL = new ModelRenderer((ModelBase)this, 26, 21);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.6f, 2.7f, 0.0f);
        this.ArmL.func_78790_a(-1.3f, -1.4f, -1.8f, 3, 11, 4, -0.1f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, (float)(-Math.PI) / 90);
        this.Scouter = new ModelRenderer((ModelBase)this, 25, 0);
        this.Scouter.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Scouter.func_78790_a(2.2f, -6.5f, -1.8f, 2, 4, 3, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, -0.3f, -2.0f, 4, 13, 4, -0.3f);
        this.Ear_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Ear_1.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Ear_1.func_78790_a(2.8f, -5.9f, -1.2f, 1, 3, 2, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, -0.3f, -2.0f, 4, 13, 4, -0.3f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.5f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 38);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 8.8f, -2.0f, 7, 2, 4, 0.0f);
        this.Ear = new ModelRenderer((ModelBase)this, 0, 0);
        this.Ear.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Ear.func_78790_a(-4.0f, -5.9f, -1.2f, 1, 3, 2, 0.0f);
        this.Body1.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.Scouter);
        this.Head.func_78792_a(this.Ear_1);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.Ear);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
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

