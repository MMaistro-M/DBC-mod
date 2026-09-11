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

public class ModelCooler
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private final ModelRenderer FroB;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelCooler(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(-4.0f, -4.0f, -1.0f);
        this.Head.func_78792_a(earR);
        earR.field_78804_l.add(new ModelBox(earR, 0, 0, -1.0f, -1.0f, -1.0f, 1, 3, 2, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(5.0f, -4.0f, -1.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 0, 0, -1.0f, -1.0f, -1.0f, 1, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer breasts = new ModelRenderer((ModelBase)this);
        breasts.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(breasts, -0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(breasts);
        breasts.field_78804_l.add(new ModelBox(breasts, 64, 12, -3.5f, 0.9566f, -1.6871f, 7, 4, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(0.0f, 10.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 38, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.renderHairs(0.0625f, "FR", f2);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 57.295776f;
        this.Head.field_78795_f = f4 / 57.295776f;
        this.RArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.LArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.RArm.field_78808_h = 0.0f;
        this.LArm.field_78808_h = 0.0f;
        this.RLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 1.4f * f1;
        this.LLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.RLeg.field_78796_g = 0.0f;
        this.LLeg.field_78796_g = 0.0f;
    }

    private void transRot(float f5, ModelRenderer m) {
        GL11.glTranslatef((float)(m.field_78800_c * f5), (float)(m.field_78797_d * f5), (float)(m.field_78798_e * f5));
        if (m.field_78808_h != 0.0f) {
            GL11.glRotatef((float)(m.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (m.field_78796_g != 0.0f) {
            GL11.glRotatef((float)(m.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (m.field_78795_f != 0.0f) {
            GL11.glRotatef((float)(m.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public String renderHairs(float par1, String hair, float par3) {
        if (hair.equals("FR")) {
            GL11.glPushMatrix();
            this.transRot(par1, this.Body);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            this.FroB.func_78785_a(par1);
            float r = MathHelper.func_76126_a((float)(par3 * 0.02f)) * 0.1f;
            float r2 = MathHelper.func_76134_b((float)(par3 * 0.02f)) * 0.1f;
            float r3 = MathHelper.func_76134_b((float)(par3 * 0.14f)) * 0.1f;
            this.ftailS1.field_78796_g = 0.2f;
            this.ftailS1.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r;
            this.ftailS1.field_78795_f = -0.3f;
            this.ftailS2.field_78796_g = 0.2f;
            this.ftailS2.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            this.ftailS2.field_78795_f = 0.4f;
            this.ftailS3.field_78796_g = 0.1f;
            this.ftailS3.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            this.ftailS3.field_78795_f = 0.6f;
            this.ftailS3.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f + 0.3f;
            this.ftailS4.field_78796_g = 0.1f;
            this.ftailS4.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.1f + r2;
            this.ftailS4.field_78795_f = 0.3f;
            this.ftailS4.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.2f;
            this.ftailS5.field_78796_g = 0.2f;
            this.ftailS5.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            this.ftailS5.field_78795_f = -0.2f;
            this.ftailS5.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.3f;
            this.ftailS6.field_78796_g = 0.2f;
            this.ftailS6.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r2 + r3;
            this.ftailS6.field_78795_f = -0.4f;
            this.ftailS6.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f - 0.4f;
            GL11.glPopMatrix();
        }
        return "";
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

