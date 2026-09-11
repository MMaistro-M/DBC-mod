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

public class ModelEbifurya
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelEbifurya(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair = new ModelRenderer((ModelBase)this);
        hair.func_78793_a(0.0f, 1.0f, -3.0f);
        this.Head.func_78792_a(hair);
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(0.0f, 0.0f, 0.0f);
        hair.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 43, 16, -1.0f, -5.0152f, 7.8263f, 2, 2, 0, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        hair.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 47, 47, -1.0f, -11.8264f, 6.9848f, 2, 7, 3, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(0.0f, 0.0f, 0.0f);
        hair.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 24, 22, -1.0f, -12.8264f, -1.0152f, 2, 4, 8, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(0.0f, 0.0f, 0.0f);
        hair.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 42, 13, -1.0f, -10.8264f, -1.0152f, 2, 2, 9, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(0.0f, 0.0f, 0.0f);
        hair.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 49, 20, -1.0f, -10.8264f, 5.9848f, 2, 6, 2, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earL, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 0, 52, 4.0f, -7.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earR, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(earR);
        earR.field_78804_l.add(new ModelBox(earR, 0, 52, -4.0f, -7.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer ponyTail = new ModelRenderer((ModelBase)this);
        ponyTail.func_78793_a(0.0f, 23.3125f, 0.4375f);
        this.Head.func_78792_a(ponyTail);
        ModelRenderer PT1 = new ModelRenderer((ModelBase)this);
        PT1.func_78793_a(0.0f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT1, 0.1745f, 0.0f, 0.0f);
        ponyTail.func_78792_a(PT1);
        PT1.field_78804_l.add(new ModelBox(PT1, 43, 18, -1.0f, -0.5f, -0.5f, 2, 9, 1, 0.0f));
        ModelRenderer PT2 = new ModelRenderer((ModelBase)this);
        PT2.func_78793_a(0.125f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT2, 0.1745f, 0.0f, -0.2618f);
        ponyTail.func_78792_a(PT2);
        PT2.field_78804_l.add(new ModelBox(PT2, 43, 18, -1.0f, -0.5f, -0.5f, 2, 8, 1, 0.0f));
        ModelRenderer PT3 = new ModelRenderer((ModelBase)this);
        PT3.func_78793_a(-0.125f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT3, 0.1745f, 0.0f, 0.2618f);
        ponyTail.func_78792_a(PT3);
        PT3.field_78804_l.add(new ModelBox(PT3, 43, 18, -1.0f, -0.5f, -0.5f, 2, 6, 1, 0.0f));
        ModelRenderer PT4 = new ModelRenderer((ModelBase)this);
        PT4.func_78793_a(0.0f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT4, 0.3491f, 0.0f, 0.0f);
        ponyTail.func_78792_a(PT4);
        PT4.field_78804_l.add(new ModelBox(PT4, 43, 18, -1.0f, -0.5f, -0.5f, 2, 7, 1, 0.0f));
        ModelRenderer PT5 = new ModelRenderer((ModelBase)this);
        PT5.func_78793_a(0.0f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT5, 0.0f, 0.0f, 0.1745f);
        ponyTail.func_78792_a(PT5);
        PT5.field_78804_l.add(new ModelBox(PT5, 43, 18, -1.0f, -0.5f, -0.5f, 2, 8, 1, 0.0f));
        ModelRenderer PT6 = new ModelRenderer((ModelBase)this);
        PT6.func_78793_a(0.125f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT6, 0.1745f, 0.0f, -0.5236f);
        ponyTail.func_78792_a(PT6);
        PT6.field_78804_l.add(new ModelBox(PT6, 43, 18, -1.0f, -0.5f, -0.5f, 2, 6, 1, 0.0f));
        ModelRenderer PT7 = new ModelRenderer((ModelBase)this);
        PT7.func_78793_a(-0.125f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT7, 0.1745f, 0.0f, 0.5236f);
        ponyTail.func_78792_a(PT7);
        PT7.field_78804_l.add(new ModelBox(PT7, 43, 18, -1.0f, -0.5f, -0.5f, 2, 5, 1, 0.0f));
        ModelRenderer PT8 = new ModelRenderer((ModelBase)this);
        PT8.func_78793_a(0.0f, -24.7582f, 4.5047f);
        this.setRotationAngle(PT8, 0.5236f, 0.0f, 0.0f);
        ponyTail.func_78792_a(PT8);
        PT8.field_78804_l.add(new ModelBox(PT8, 43, 18, -1.0f, -0.5f, -0.5f, 2, 4, 1, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer armor = new ModelRenderer((ModelBase)this);
        armor.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(armor);
        ModelRenderer bodyChild_1 = new ModelRenderer((ModelBase)this);
        bodyChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        armor.func_78792_a(bodyChild_1);
        ModelRenderer bodyChild_3 = new ModelRenderer((ModelBase)this);
        bodyChild_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_3, 0.5236f, 0.0f, 0.0f);
        armor.func_78792_a(bodyChild_3);
        ModelRenderer bodyChild_2 = new ModelRenderer((ModelBase)this);
        bodyChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_2, -0.3491f, 0.0f, 0.0f);
        armor.func_78792_a(bodyChild_2);
        ModelRenderer bodyChild = new ModelRenderer((ModelBase)this);
        bodyChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild, -0.1745f, 0.0f, 0.0f);
        armor.func_78792_a(bodyChild);
        ModelRenderer bone5 = new ModelRenderer((ModelBase)this);
        bone5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bone5, 0.0f, 0.0f, 0.1745f);
        armor.func_78792_a(bone5);
        bone5.field_78804_l.add(new ModelBox(bone5, 24, 0, 4.0f, -3.0f, -2.0f, 1, 3, 1, 0.0f));
        ModelRenderer bone6 = new ModelRenderer((ModelBase)this);
        bone6.func_78793_a(0.0f, 0.0f, 4.0f);
        this.setRotationAngle(bone6, 0.0f, 0.0f, 0.1745f);
        armor.func_78792_a(bone6);
        bone6.field_78804_l.add(new ModelBox(bone6, 0, 16, 4.0f, -3.0f, -3.0f, 1, 3, 1, 0.0f));
        ModelRenderer bone7 = new ModelRenderer((ModelBase)this);
        bone7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bone7, 0.0f, 0.0f, -0.1745f);
        armor.func_78792_a(bone7);
        bone7.field_78804_l.add(new ModelBox(bone7, 3, 3, -5.0f, -3.0f, -2.0f, 1, 3, 1, 0.0f));
        bone7.field_78804_l.add(new ModelBox(bone7, 0, 0, -5.0f, -3.0f, 2.0f, 1, 3, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 0, 37, -3.0031f, -2.079f, -2.0018f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 32, 0, -0.9938f, -2.158f, -1.9964f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 18, 34, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 34, 34, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
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

