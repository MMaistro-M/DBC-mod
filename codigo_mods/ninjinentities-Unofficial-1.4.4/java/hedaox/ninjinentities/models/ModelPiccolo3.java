/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package hedaox.ninjinentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelPiccolo3
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer ArmL;
    private final ModelRenderer ArmR;
    private final ModelRenderer LegL;
    private final ModelRenderer LegR;
    private final float scale;

    public ModelPiccolo3(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        ModelRenderer tentacleL = new ModelRenderer((ModelBase)this, 25, -1);
        tentacleL.field_78809_i = true;
        tentacleL.func_78793_a(1.5f, -5.5f, -4.0f);
        tentacleL.func_78790_a(0.0f, -2.0f, -4.0f, 0, 4, 4, 0.0f);
        this.setRotateAngle(tentacleL, 0.0f, -0.6981317f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        ModelRenderer tentacleR = new ModelRenderer((ModelBase)this, 25, -1);
        tentacleR.func_78793_a(-1.5f, -5.5f, -4.0f);
        tentacleR.func_78790_a(0.0f, -2.0f, -4.0f, 0, 4, 4, 0.0f);
        this.setRotateAngle(tentacleR, 0.0f, 0.6981317f, 0.0f);
        ModelRenderer body2 = new ModelRenderer((ModelBase)this, 1, 41);
        body2.func_78793_a(0.0f, 9.5f, 0.0f);
        body2.func_78790_a(-4.5f, -0.5f, -2.5f, 9, 2, 5, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 40, 40);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.5f, 8.0f, 0.0f);
        this.LegL.func_78790_a(-2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 40, 40);
        this.LegR.func_78793_a(-2.5f, 8.0f, 0.0f);
        this.LegR.func_78790_a(-2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f);
        ModelRenderer feetR = new ModelRenderer((ModelBase)this, 64, 41);
        feetR.func_78793_a(0.0f, 0.0f, 0.0f);
        feetR.func_78790_a(-2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f);
        ModelRenderer earL = new ModelRenderer((ModelBase)this, 34, 1);
        earL.field_78809_i = true;
        earL.func_78793_a(3.8f, -4.5f, -1.0f);
        earL.func_78790_a(0.0f, -3.5f, 0.0f, 3, 6, 0, 0.0f);
        this.setRotateAngle(earL, 0.0f, -0.87266463f, 0.04363323f);
        ModelRenderer body3 = new ModelRenderer((ModelBase)this, 1, 50);
        body3.func_78793_a(0.0f, 11.0f, 0.0f);
        body3.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 4, 6, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 41, 19);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.8f, -5.1f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.5f, 5, 15, 5, -0.1f);
        ModelRenderer head2 = new ModelRenderer((ModelBase)this, 33, 1);
        head2.func_78793_a(0.0f, -5.6f, 1.7f);
        head2.func_78790_a(-3.5f, -3.0f, -5.0f, 7, 8, 8, 0.0f);
        ModelRenderer earR = new ModelRenderer((ModelBase)this, 34, 1);
        earR.func_78793_a(-3.5f, -4.5f, -1.0f);
        earR.func_78790_a(-3.5f, -3.5f, 0.0f, 3, 6, 0, 0.0f);
        this.setRotateAngle(earR, 0.0f, 0.87266463f, -0.04363323f);
        this.ArmR = new ModelRenderer((ModelBase)this, 41, 19);
        this.ArmR.func_78793_a(-5.8f, -5.1f, 0.0f);
        this.ArmR.func_78790_a(-4.0f, -2.0f, -2.5f, 5, 15, 5, -0.1f);
        ModelRenderer feetL = new ModelRenderer((ModelBase)this, 64, 41);
        feetL.field_78809_i = true;
        feetL.func_78793_a(0.0f, 0.0f, 0.0f);
        feetL.func_78790_a(-2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 24);
        this.Body.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Body.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 9, 6, 0.0f);
        this.Head.func_78792_a(tentacleL);
        this.Head.func_78792_a(tentacleR);
        this.Body.func_78792_a(body2);
        this.LegR.func_78792_a(feetR);
        this.Head.func_78792_a(earL);
        this.Body.func_78792_a(body3);
        this.Head.func_78792_a(head2);
        this.Head.func_78792_a(earR);
        this.LegL.func_78792_a(feetL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        GL11.glPopMatrix();
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
        this.Head.field_78796_g = par4 / (r2 / (float)Math.PI);
        this.Head.field_78795_f = par5 / (r2 / (float)Math.PI);
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

