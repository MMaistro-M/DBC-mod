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

public class ModelGohanKid1
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Hair1;
    private final ModelRenderer Hair2;
    private final ModelRenderer Hair3;
    private final ModelRenderer Hair4;
    private final ModelRenderer Hair5;
    private final ModelRenderer Hair6;
    private final ModelRenderer Hair7;
    private final ModelRenderer Hair8;
    private final ModelRenderer Hair9;
    private final ModelRenderer Hair10;
    private final ModelRenderer Hair11;
    private final ModelRenderer Hair12;
    private final ModelRenderer Hair13;
    private final ModelRenderer Hair14;
    private final ModelRenderer Hair15;
    private final ModelRenderer Strand1;
    private final ModelRenderer Strand2;
    private final ModelRenderer Strand3;
    private final ModelRenderer Strand4;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelGohanKid1(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Hair1 = new ModelRenderer((ModelBase)this);
        this.Hair1.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.setRotationAngle(this.Hair1, -0.0873f, 0.0f, -0.4363f);
        this.Head.func_78792_a(this.Hair1);
        this.Hair1.field_78804_l.add(new ModelBox(this.Hair1, 42, 2, -0.5133f, -2.474f, -2.0714f, 3, 5, 3, 0.0f));
        this.Hair2 = new ModelRenderer((ModelBase)this);
        this.Hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.setRotationAngle(this.Hair2, 0.0f, 0.0f, -0.6981f);
        this.Head.func_78792_a(this.Hair2);
        this.Hair2.field_78804_l.add(new ModelBox(this.Hair2, 45, 2, -1.9397f, -0.658f, -3.0f, 3, 4, 3, 0.0f));
        this.Hair3 = new ModelRenderer((ModelBase)this);
        this.Hair3.func_78793_a(1.0f, -8.0f, 0.0f);
        this.setRotationAngle(this.Hair3, 0.0f, 0.0f, 0.6981f);
        this.Head.func_78792_a(this.Hair3);
        this.Hair3.field_78804_l.add(new ModelBox(this.Hair3, 45, 2, 0.0603f, -3.342f, -3.0f, 3, 6, 3, 0.0f));
        this.Hair4 = new ModelRenderer((ModelBase)this);
        this.Hair4.func_78793_a(2.0f, -7.0f, 0.0f);
        this.setRotationAngle(this.Hair4, 0.0f, 0.0873f, 0.7854f);
        this.Head.func_78792_a(this.Hair4);
        this.Hair4.field_78804_l.add(new ModelBox(this.Hair4, 45, 2, 1.0603f, -3.342f, -3.0f, 2, 5, 3, 0.0f));
        this.Hair5 = new ModelRenderer((ModelBase)this);
        this.Hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.setRotationAngle(this.Hair5, 2.7925f, 0.0873f, -1.1345f);
        this.Head.func_78792_a(this.Hair5);
        this.Hair5.field_78804_l.add(new ModelBox(this.Hair5, 45, 2, -0.0028f, -1.971f, -3.2188f, 3, 4, 3, 0.0f));
        this.Hair6 = new ModelRenderer((ModelBase)this);
        this.Hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.setRotationAngle(this.Hair6, -0.3491f, 0.0f, -0.7854f);
        this.Head.func_78792_a(this.Hair6);
        this.Hair6.field_78804_l.add(new ModelBox(this.Hair6, 45, 2, 1.8099f, -1.1257f, -2.2188f, 3, 5, 3, 0.0f));
        this.Hair7 = new ModelRenderer((ModelBase)this);
        this.Hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.setRotationAngle(this.Hair7, -0.3491f, 0.0f, 0.6982f);
        this.Head.func_78792_a(this.Hair7);
        this.Hair7.field_78804_l.add(new ModelBox(this.Hair7, 45, 2, 1.8099f, -1.4417f, -5.0982f, 3, 4, 5, 0.0f));
        this.Hair8 = new ModelRenderer((ModelBase)this);
        this.Hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.setRotationAngle(this.Hair8, 2.7925f, 0.0873f, -1.309f);
        this.Head.func_78792_a(this.Hair8);
        this.Hair8.field_78804_l.add(new ModelBox(this.Hair8, 45, 2, -2.0028f, -3.971f, -3.2188f, 3, 4, 3, 0.0f));
        this.Hair9 = new ModelRenderer((ModelBase)this);
        this.Hair9.func_78793_a(3.0f, -8.0f, 2.0f);
        this.setRotationAngle(this.Hair9, 2.7925f, 0.0873f, 0.9599f);
        this.Head.func_78792_a(this.Hair9);
        this.Hair9.field_78804_l.add(new ModelBox(this.Hair9, 45, 2, 0.9972f, -1.971f, -3.2188f, 3, 3, 3, 0.0f));
        this.Hair10 = new ModelRenderer((ModelBase)this);
        this.Hair10.func_78793_a(-0.7f, -4.2f, 1.3f);
        this.setRotationAngle(this.Hair10, 2.8798f, 0.0873f, 0.0f);
        this.Head.func_78792_a(this.Hair10);
        this.Hair10.field_78804_l.add(new ModelBox(this.Hair10, 38, 2, 0.9972f, -2.3391f, -4.0809f, 3, 7, 3, 0.0f));
        this.Hair11 = new ModelRenderer((ModelBase)this);
        this.Hair11.func_78793_a(-3.5f, -6.6f, 1.6f);
        this.setRotationAngle(this.Hair11, 2.9671f, 0.0175f, 0.0524f);
        this.Head.func_78792_a(this.Hair11);
        this.Hair11.field_78804_l.add(new ModelBox(this.Hair11, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.Hair12 = new ModelRenderer((ModelBase)this);
        this.Hair12.func_78793_a(-4.5f, 1.1f, 2.1f);
        this.setRotationAngle(this.Hair12, -2.9671f, 0.0175f, -0.4712f);
        this.Head.func_78792_a(this.Hair12);
        this.Hair12.field_78804_l.add(new ModelBox(this.Hair12, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.Hair13 = new ModelRenderer((ModelBase)this);
        this.Hair13.func_78793_a(4.5f, 1.1f, 2.1f);
        this.setRotationAngle(this.Hair13, -2.9671f, -0.0175f, 0.4712f);
        this.Head.func_78792_a(this.Hair13);
        this.Hair13.field_78804_l.add(new ModelBox(this.Hair13, 38, 2, -3.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.Hair14 = new ModelRenderer((ModelBase)this);
        this.Hair14.func_78793_a(2.5f, 1.1f, 2.1f);
        this.setRotationAngle(this.Hair14, -2.9671f, -0.0175f, 0.1222f);
        this.Head.func_78792_a(this.Hair14);
        this.Hair14.field_78804_l.add(new ModelBox(this.Hair14, 38, 2, -3.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.Hair15 = new ModelRenderer((ModelBase)this);
        this.Hair15.func_78793_a(1.5f, 4.1f, 1.7f);
        this.setRotationAngle(this.Hair15, -2.9671f, -0.0175f, 1.6057f);
        this.Head.func_78792_a(this.Hair15);
        this.Hair15.field_78804_l.add(new ModelBox(this.Hair15, 44, 2, -3.9972f, -6.6289f, -4.1585f, 3, 9, 3, 0.0f));
        this.Strand1 = new ModelRenderer((ModelBase)this);
        this.Strand1.func_78793_a(-4.0f, -8.0f, -2.0f);
        this.setRotationAngle(this.Strand1, -0.3491f, 0.0f, 0.3491f);
        this.Head.func_78792_a(this.Strand1);
        this.Strand1.field_78804_l.add(new ModelBox(this.Strand1, 38, 2, 1.9972f, -1.971f, -3.2188f, 2, 4, 2, 0.0f));
        this.Strand2 = new ModelRenderer((ModelBase)this);
        this.Strand2.func_78793_a(0.0f, -5.0f, -2.0f);
        this.setRotationAngle(this.Strand2, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(this.Strand2);
        this.Strand2.field_78804_l.add(new ModelBox(this.Strand2, 38, 2, 1.9972f, -1.971f, -3.2188f, 2, 3, 2, 0.0f));
        this.Strand3 = new ModelRenderer((ModelBase)this);
        this.Strand3.func_78793_a(2.0f, -5.0f, -1.0f);
        this.setRotationAngle(this.Strand3, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(this.Strand3);
        this.Strand3.field_78804_l.add(new ModelBox(this.Strand3, 38, 2, 1.5441f, -0.9985f, -3.7609f, 2, 3, 2, 0.0f));
        this.Strand4 = new ModelRenderer((ModelBase)this);
        this.Strand4.func_78793_a(-6.0f, -7.5f, -0.9f);
        this.setRotationAngle(this.Strand4, -0.3491f, 0.0f, 0.4363f);
        this.Head.func_78792_a(this.Strand4);
        this.Strand4.field_78804_l.add(new ModelBox(this.Strand4, 38, 2, 1.5441f, -0.9985f, -3.7609f, 2, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.LArm.field_78809_i = true;
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.LLeg.field_78809_i = true;
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
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

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

