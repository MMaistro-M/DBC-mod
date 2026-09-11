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

public class ModelGohanKidArmor
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
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer RShoulderArmor;
    private final ModelRenderer LArm;
    private final ModelRenderer LShoulderArmor;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelGohanKidArmor(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Hair1 = new ModelRenderer((ModelBase)this);
        this.Hair1.func_78793_a(-3.0f, -9.0f, 0.0f);
        this.setRotationAngle(this.Hair1, -0.0873f, 0.0f, -2.4434f);
        this.Head.func_78792_a(this.Hair1);
        this.Hair1.field_78804_l.add(new ModelBox(this.Hair1, 42, 2, -1.5133f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        this.Hair2 = new ModelRenderer((ModelBase)this);
        this.Hair2.func_78793_a(3.0f, -9.0f, 0.0f);
        this.setRotationAngle(this.Hair2, 0.0872f, 0.0f, 2.3562f);
        this.Head.func_78792_a(this.Hair2);
        this.Hair2.field_78804_l.add(new ModelBox(this.Hair2, 42, 2, -0.4867f, -4.474f, -1.6714f, 2, 6, 3, 0.0f));
        this.Hair3 = new ModelRenderer((ModelBase)this);
        this.Hair3.func_78793_a(3.4f, -9.0f, -1.6f);
        this.setRotationAngle(this.Hair3, 0.5236f, -0.3491f, 2.1816f);
        this.Head.func_78792_a(this.Hair3);
        this.Hair3.field_78804_l.add(new ModelBox(this.Hair3, 42, 2, -0.4867f, -3.474f, -2.0714f, 2, 5, 3, 0.0f));
        this.Hair4 = new ModelRenderer((ModelBase)this);
        this.Hair4.func_78793_a(-3.4f, -9.0f, -1.6f);
        this.setRotationAngle(this.Hair4, 0.4363f, 0.3491f, -2.3561f);
        this.Head.func_78792_a(this.Hair4);
        this.Hair4.field_78804_l.add(new ModelBox(this.Hair4, 42, 2, -1.5133f, -4.474f, -2.0714f, 2, 7, 3, 0.0f));
        this.Hair5 = new ModelRenderer((ModelBase)this);
        this.Hair5.func_78793_a(-2.8f, -9.0f, 2.9f);
        this.setRotationAngle(this.Hair5, -0.0873f, 0.0f, -2.3561f);
        this.Head.func_78792_a(this.Hair5);
        this.Hair5.field_78804_l.add(new ModelBox(this.Hair5, 42, 2, -1.5133f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        this.Hair6 = new ModelRenderer((ModelBase)this);
        this.Hair6.func_78793_a(2.8f, -9.0f, 2.9f);
        this.setRotationAngle(this.Hair6, -0.0873f, 0.0f, 2.3561f);
        this.Head.func_78792_a(this.Hair6);
        this.Hair6.field_78804_l.add(new ModelBox(this.Hair6, 42, 2, -0.4867f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        this.Hair7 = new ModelRenderer((ModelBase)this);
        this.Hair7.func_78793_a(2.8f, -9.0f, 2.9f);
        this.setRotationAngle(this.Hair7, -0.611f, -0.6108f, 3.0543f);
        this.Head.func_78792_a(this.Hair7);
        this.Hair7.field_78804_l.add(new ModelBox(this.Hair7, 42, 2, -0.4867f, -4.474f, -2.0714f, 3, 6, 2, 0.0f));
        this.Hair8 = new ModelRenderer((ModelBase)this);
        this.Hair8.func_78793_a(-2.8f, -9.0f, 2.9f);
        this.setRotationAngle(this.Hair8, -0.611f, 0.5236f, -3.0543f);
        this.Head.func_78792_a(this.Hair8);
        this.Hair8.field_78804_l.add(new ModelBox(this.Hair8, 42, 2, -2.5133f, -4.474f, -2.0714f, 3, 6, 2, 0.0f));
        this.Hair9 = new ModelRenderer((ModelBase)this);
        this.Hair9.func_78793_a(-0.4f, -5.0f, -0.6f);
        this.setRotationAngle(this.Hair9, -0.6109f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.Hair9);
        this.Hair9.field_78804_l.add(new ModelBox(this.Hair9, 42, 2, -2.9133f, -2.474f, -5.0714f, 3, 4, 1, 0.0f));
        this.Hair10 = new ModelRenderer((ModelBase)this);
        this.Hair10.func_78793_a(2.6f, -5.0f, -0.6f);
        this.setRotationAngle(this.Hair10, -0.6109f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.Hair10);
        this.Hair10.field_78804_l.add(new ModelBox(this.Hair10, 42, 2, -2.5133f, -2.474f, -5.0714f, 3, 4, 1, 0.0f));
        this.Hair11 = new ModelRenderer((ModelBase)this);
        this.Hair11.func_78793_a(2.6f, -5.0f, -0.6f);
        this.Head.func_78792_a(this.Hair11);
        this.Hair11.field_78804_l.add(new ModelBox(this.Hair11, 42, 2, -4.0133f, -5.674f, -2.0714f, 3, 2, 6, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RShoulderArmor = new ModelRenderer((ModelBase)this);
        this.RShoulderArmor.func_78793_a(-3.0f, -1.7f, 0.0f);
        this.setRotationAngle(this.RShoulderArmor, -1.5708f, 0.0f, 0.1047f);
        this.RArm.func_78792_a(this.RShoulderArmor);
        this.RShoulderArmor.field_78804_l.add(new ModelBox(this.RShoulderArmor, 24, 0, -3.0f, -3.0f, -1.0f, 6, 6, 2, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LShoulderArmor = new ModelRenderer((ModelBase)this);
        this.LShoulderArmor.func_78793_a(11.0f, -1.7f, 0.0f);
        this.LShoulderArmor.field_78809_i = true;
        this.setRotationAngle(this.LShoulderArmor, -1.5708f, 0.0f, -0.1047f);
        this.LArm.func_78792_a(this.LShoulderArmor);
        this.LShoulderArmor.field_78804_l.add(new ModelBox(this.LShoulderArmor, 24, 0, -3.0f, -3.0f, -1.0f, 6, 6, 2, 0.0f));
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

