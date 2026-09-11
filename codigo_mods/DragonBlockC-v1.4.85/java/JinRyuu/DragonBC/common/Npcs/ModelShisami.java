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

public class ModelShisami
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Chest;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer rhorn;
    public ModelRenderer lhorn;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer rhorn2;
    public ModelRenderer rhorn3;
    public ModelRenderer rhorn4;
    public ModelRenderer lhorn2;
    public ModelRenderer lhorn3;
    public ModelRenderer lhorn4;
    public ModelRenderer Belly;
    public ModelRenderer Neck;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;

    public ModelShisami() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.earL = new ModelRenderer((ModelBase)this, 33, 8);
        this.earL.field_78809_i = true;
        this.earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earL.func_78790_a(2.7f, -5.8f, -3.2f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earL, 0.0f, -0.4098033f, 0.0f);
        this.lhorn4 = new ModelRenderer((ModelBase)this, 58, 1);
        this.lhorn4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.lhorn4.func_78790_a(7.9f, -11.9f, -3.4f, 1, 1, 2, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 42, 15);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-5.0f, -1.7f, -2.4f, 6, 3, 5, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 42, 15);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-1.1f, -1.7f, -2.4f, 6, 3, 5, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 46, 24);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(6.8f, -3.4f, 0.8f);
        this.ArmL.func_78790_a(-1.0f, -1.6f, -2.0f, 5, 14, 4, 0.0f);
        this.lhorn3 = new ModelRenderer((ModelBase)this, 51, 3);
        this.lhorn3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.lhorn3.func_78790_a(6.7f, -11.4f, -2.8f, 2, 2, 2, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 43);
        this.LegR.func_78793_a(-3.0f, 8.8f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 5, 15, 5, 0.0f);
        this.lhorn2 = new ModelRenderer((ModelBase)this, 40, 1);
        this.lhorn2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.lhorn2.func_78790_a(6.2f, -9.9f, -2.3f, 2, 3, 3, 0.0f);
        this.Belly = new ModelRenderer((ModelBase)this, 21, 42);
        this.Belly.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Belly.func_78790_a(-5.0f, 4.8f, -2.0f, 10, 9, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -5.2f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.lhorn = new ModelRenderer((ModelBase)this, 25, 1);
        this.lhorn.func_78793_a(0.0f, 0.0f, 0.0f);
        this.lhorn.func_78790_a(3.5f, -8.7f, -2.0f, 4, 3, 3, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 22, 20);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-3.0f, -0.5f, -1.2f, 6, 1, 4, 0.0f);
        this.rhorn4 = new ModelRenderer((ModelBase)this, 58, 1);
        this.rhorn4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rhorn4.func_78790_a(-8.7f, -11.9f, -3.4f, 1, 1, 2, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 9, 29);
        this.Chest.func_78793_a(0.0f, -5.0f, 0.0f);
        this.Chest.func_78790_a(-6.0f, 0.0f, -2.3f, 12, 5, 6, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 46, 24);
        this.ArmR.func_78793_a(-6.9f, -3.4f, 0.8f);
        this.ArmR.func_78790_a(-4.1f, -1.6f, -2.0f, 5, 14, 4, 0.0f);
        this.rhorn2 = new ModelRenderer((ModelBase)this, 40, 1);
        this.rhorn2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rhorn2.func_78790_a(-8.2f, -9.9f, -2.3f, 2, 3, 3, 0.0f);
        this.rhorn3 = new ModelRenderer((ModelBase)this, 51, 3);
        this.rhorn3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rhorn3.func_78790_a(-8.6f, -11.4f, -2.8f, 2, 2, 2, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 43);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 8.8f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 5, 15, 5, 0.0f);
        this.rhorn = new ModelRenderer((ModelBase)this, 25, 1);
        this.rhorn.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rhorn.func_78790_a(-7.5f, -8.7f, -2.0f, 4, 3, 3, 0.0f);
        this.earR = new ModelRenderer((ModelBase)this, 33, 8);
        this.earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earR.func_78790_a(-6.5f, -5.8f, -3.2f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earR, 0.0f, 0.4098033f, 0.0f);
        this.Head.func_78792_a(this.earL);
        this.lhorn3.func_78792_a(this.lhorn4);
        this.ArmR.func_78792_a(this.ShoulderR);
        this.ArmL.func_78792_a(this.ShoulderL);
        this.lhorn2.func_78792_a(this.lhorn3);
        this.lhorn.func_78792_a(this.lhorn2);
        this.Chest.func_78792_a(this.Belly);
        this.Head.func_78792_a(this.lhorn);
        this.Chest.func_78792_a(this.Neck);
        this.rhorn3.func_78792_a(this.rhorn4);
        this.rhorn.func_78792_a(this.rhorn2);
        this.rhorn2.func_78792_a(this.rhorn3);
        this.Head.func_78792_a(this.rhorn);
        this.Head.func_78792_a(this.earR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Chest.func_78785_a(f5);
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

