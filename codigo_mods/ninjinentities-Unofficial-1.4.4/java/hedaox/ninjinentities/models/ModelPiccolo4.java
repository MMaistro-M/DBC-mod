/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package hedaox.ninjinentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelPiccolo4
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer ArmL;
    private final ModelRenderer ArmR;
    private final ModelRenderer LegL;
    private final ModelRenderer LegR;
    private final float scale;

    public ModelPiccolo4(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.1f));
        ModelRenderer tentacleL = new ModelRenderer((ModelBase)this);
        tentacleL.field_78809_i = true;
        tentacleL.func_78793_a(1.5f, -7.1f, -4.3f);
        this.Head.func_78792_a(tentacleL);
        this.setRotationAngle(tentacleL, 0.3316f, 0.8727f, -2.9147f);
        tentacleL.field_78804_l.add(new ModelBox(tentacleL, 25, -1, -0.1253f, -0.3779f, -3.8457f, 0, 4, 4, 0.1f));
        ModelRenderer tentacleR = new ModelRenderer((ModelBase)this);
        tentacleR.func_78793_a(-1.5f, -7.1f, -4.3f);
        this.Head.func_78792_a(tentacleR);
        this.setRotationAngle(tentacleR, 0.3316f, -0.8727f, 2.9147f);
        tentacleR.field_78804_l.add(new ModelBox(tentacleR, 25, -1, 0.1253f, -0.3779f, -3.8457f, 0, 4, 4, 0.1f));
        ModelRenderer head2 = new ModelRenderer((ModelBase)this);
        head2.func_78793_a(0.0f, -5.6f, 1.7f);
        this.Head.func_78792_a(head2);
        head2.field_78804_l.add(new ModelBox(head2, 33, 1, -3.5f, -3.0f, -5.0f, 7, 8, 8, 0.1f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(-3.7f, -5.3f, -1.0f);
        this.Head.func_78792_a(earR);
        this.setRotationAngle(earR, -0.0451f, 0.5284f, -0.2625f);
        earR.field_78804_l.add(new ModelBox(earR, 34, 1, -3.5f, -3.5f, 0.0f, 3, 6, 0, 0.1f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.field_78809_i = true;
        earL.func_78793_a(3.7f, -5.3f, -1.0f);
        this.Head.func_78792_a(earL);
        this.setRotationAngle(earL, -0.0451f, -0.5284f, 0.2625f);
        earL.field_78804_l.add(new ModelBox(earL, 34, 1, 0.5f, -3.5f, 0.0f, 3, 6, 0, 0.1f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 1, 24, -5.0f, 0.0f, -3.0f, 10, 9, 6, 0.0f));
        ModelRenderer body2 = new ModelRenderer((ModelBase)this);
        body2.func_78793_a(0.0f, 9.5f, 0.0f);
        this.Body.func_78792_a(body2);
        body2.field_78804_l.add(new ModelBox(body2, 1, 41, -4.5f, -0.5f, -2.5f, 9, 2, 5, 0.0f));
        ModelRenderer body3 = new ModelRenderer((ModelBase)this);
        body3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Body.func_78792_a(body3);
        body3.field_78804_l.add(new ModelBox(body3, 1, 50, -5.0f, 0.0f, -3.0f, 10, 4, 6, 0.0f));
        this.ArmL = new ModelRenderer((ModelBase)this);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.8f, -5.1f, 0.0f);
        this.ArmL.field_78804_l.add(new ModelBox(this.ArmL, 41, 19, -0.8f, -1.9f, -2.5f, 5, 15, 5, 0.0f));
        this.ArmR = new ModelRenderer((ModelBase)this);
        this.ArmR.func_78793_a(-5.8f, -5.1f, 0.0f);
        this.ArmR.field_78804_l.add(new ModelBox(this.ArmR, 41, 19, -4.2f, -1.9f, -2.5f, 5, 15, 5, 0.0f));
        this.LegL = new ModelRenderer((ModelBase)this);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.5f, 8.0f, 0.0f);
        this.LegL.field_78804_l.add(new ModelBox(this.LegL, 40, 40, -2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f));
        ModelRenderer feetL = new ModelRenderer((ModelBase)this);
        feetL.func_78793_a(0.0f, 0.0f, 0.0f);
        feetL.field_78809_i = true;
        this.LegL.func_78792_a(feetL);
        feetL.field_78804_l.add(new ModelBox(feetL, 64, 41, -2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f));
        this.LegR = new ModelRenderer((ModelBase)this);
        this.LegR.func_78793_a(-2.5f, 8.0f, 0.0f);
        this.LegR.field_78804_l.add(new ModelBox(this.LegR, 40, 40, -2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f));
        ModelRenderer feetR = new ModelRenderer((ModelBase)this);
        feetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR.func_78792_a(feetR);
        feetR.field_78804_l.add(new ModelBox(feetR, 64, 41, -2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f));
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

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
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

