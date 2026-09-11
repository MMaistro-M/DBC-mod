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

public class ModelCellmax
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Head2;
    private final ModelRenderer Head1;
    private final ModelRenderer FroB;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer Body;
    private final ModelRenderer leftwing_r1;
    private final ModelRenderer rightwing_r1;
    private final ModelRenderer LLeg;
    private final ModelRenderer RLeg;
    public int heldItemRight;
    private float scale = 1.0f;

    public ModelCellmax(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Head2 = new ModelRenderer((ModelBase)this);
        this.Head2.func_78793_a(1.8f, -12.3f, 0.0f);
        this.Head.func_78792_a(this.Head2);
        this.setRotationAngle(this.Head2, 0.0f, 0.0f, 0.2705f);
        this.Head2.field_78804_l.add(new ModelBox(this.Head2, 108, 50, 0.7f, -1.7f, -3.5f, 3, 7, 7, 0.0f));
        this.Head1 = new ModelRenderer((ModelBase)this);
        this.Head1.func_78793_a(-1.5f, -10.5f, 0.0f);
        this.Head.func_78792_a(this.Head1);
        this.setRotationAngle(this.Head1, 0.0f, 0.0f, -0.2705f);
        this.Head1.field_78804_l.add(new ModelBox(this.Head1, 108, 50, -3.5f, -3.5f, -3.5f, 3, 7, 7, 0.0f));
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(0.0f, 5.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 71, 54, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 18, 44, -3.5f, -3.5f, 6.0f, 7, 7, 6, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(3.5f, 0.0f, 6.0f);
        this.ftailS6.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.7418f, 0.0f, -1.5708f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 22, 48, -2.0f, -2.0268f, 0.0118f, 4, 2, 2, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(-1.0f, 3.4142f, 6.0f);
        this.ftailS6.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.7854f, 0.0f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 22, 48, -1.0f, -1.9272f, -0.0429f, 4, 2, 2, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(-3.5f, 0.0f, 6.0f);
        this.ftailS6.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.829f, 0.0f, -1.5708f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 22, 48, -2.0f, 0.0f, -2.0f, 4, 2, 2, 0.0f));
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(0.0f, -2.0858f, 6.0f);
        this.ftailS6.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.7854f, 0.0f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 22, 48, -2.0f, -1.0f, -1.0f, 4, 2, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.leftwing_r1 = new ModelRenderer((ModelBase)this);
        this.leftwing_r1.func_78793_a(2.8f, -1.4f, 2.2f);
        this.Body.func_78792_a(this.leftwing_r1);
        this.setRotationAngle(this.leftwing_r1, 0.1745f, 0.0f, -0.2182f);
        this.leftwing_r1.field_78804_l.add(new ModelBox(this.leftwing_r1, 114, 20, -2.3f, -3.2411f, 0.6314f, 7, 20, 0, 0.0f));
        this.rightwing_r1 = new ModelRenderer((ModelBase)this);
        this.rightwing_r1.field_78809_i = true;
        this.rightwing_r1.func_78793_a(-7.2f, -1.4f, 2.2f);
        this.Body.func_78792_a(this.rightwing_r1);
        this.setRotationAngle(this.rightwing_r1, 0.1309f, 0.0f, 0.2182f);
        this.rightwing_r1.field_78804_l.add(new ModelBox(this.rightwing_r1, 114, 20, -0.7f, -4.15f, 0.6314f, 7, 20, 0, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.0f, 2.0f, 0.0f);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(4.0f, 2.0f, 0.0f);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 0.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.field_78809_i = true;
        this.LLeg.func_78793_a(0.8f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -1.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.RArm.func_78785_a(f5);
        this.LArm.func_78785_a(f5);
        this.renderHairs(0.0625f, "FR", f2);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 70.028175f;
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

