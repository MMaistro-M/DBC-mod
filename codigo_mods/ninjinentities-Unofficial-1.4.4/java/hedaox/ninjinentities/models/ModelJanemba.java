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

public class ModelJanemba
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

    public ModelJanemba(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, -16.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 66, 0, -4.9f, -4.0f, -4.5f, 10, 4, 10, 0.0f));
        ModelRenderer head2 = new ModelRenderer((ModelBase)this);
        head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(head2);
        head2.field_78804_l.add(new ModelBox(head2, 70, 81, -4.0f, -8.0f, -3.5f, 8, 8, 8, 0.0f));
        ModelRenderer lefteyebrow = new ModelRenderer((ModelBase)this);
        lefteyebrow.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(lefteyebrow, 0.0f, -0.5463f, -0.4554f);
        this.Head.func_78792_a(lefteyebrow);
        lefteyebrow.field_78804_l.add(new ModelBox(lefteyebrow, 10, 6, 2.1f, -7.9f, -4.1f, 4, 1, 1, 0.0f));
        ModelRenderer righteyebrow = new ModelRenderer((ModelBase)this);
        righteyebrow.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(righteyebrow, 0.0f, 0.5463f, 0.4554f);
        this.Head.func_78792_a(righteyebrow);
        righteyebrow.field_78804_l.add(new ModelBox(righteyebrow, 12, 3, -6.0f, -8.0f, -4.0f, 4, 1, 1, 0.0f));
        ModelRenderer leftear = new ModelRenderer((ModelBase)this);
        leftear.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(leftear, 0.0f, -0.5463f, -0.4554f);
        this.Head.func_78792_a(leftear);
        leftear.field_78804_l.add(new ModelBox(leftear, 12, 0, 4.0f, -4.0f, -1.0f, 5, 3, 0, 0.0f));
        ModelRenderer rightear = new ModelRenderer((ModelBase)this);
        rightear.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(rightear, 0.0f, 0.5463f, 0.4554f);
        this.Head.func_78792_a(rightear);
        rightear.field_78804_l.add(new ModelBox(rightear, 0, 12, -9.0f, -4.4f, -1.6f, 5, 3, 0, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 0, -11.2f, -8.0f, -10.4f, 22, 18, 22, 0.0f));
        ModelRenderer holeL1 = new ModelRenderer((ModelBase)this);
        holeL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(holeL1);
        holeL1.field_78804_l.add(new ModelBox(holeL1, 0, 121, 5.0f, -17.0f, -2.0f, 4, 1, 4, 0.0f));
        ModelRenderer holeR1 = new ModelRenderer((ModelBase)this);
        holeR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(holeR1);
        holeR1.field_78804_l.add(new ModelBox(holeR1, 0, 121, -9.0f, -17.0f, -2.0f, 4, 1, 4, 0.0f));
        ModelRenderer upperBody = new ModelRenderer((ModelBase)this);
        upperBody.func_78793_a(0.0f, -17.1f, 1.2f);
        this.Body.func_78792_a(upperBody);
        upperBody.field_78804_l.add(new ModelBox(upperBody, 0, 40, -9.6f, 1.0f, -8.9f, 19, 8, 17, 0.0f));
        ModelRenderer breasts = new ModelRenderer((ModelBase)this);
        breasts.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(breasts, -0.4098f, 0.0f, 0.0f);
        this.Body.func_78792_a(breasts);
        breasts.field_78804_l.add(new ModelBox(breasts, 55, 40, -9.0f, -11.0f, -12.5f, 18, 6, 7, 0.0f));
        ModelRenderer junkSide = new ModelRenderer((ModelBase)this);
        junkSide.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(junkSide, 0.4554f, 0.0f, 0.0f);
        this.Body.func_78792_a(junkSide);
        junkSide.field_78804_l.add(new ModelBox(junkSide, 58, 58, -10.6f, 4.5f, -13.5f, 21, 9, 14, 0.0f));
        ModelRenderer ass = new ModelRenderer((ModelBase)this);
        ass.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(ass);
        ass.field_78804_l.add(new ModelBox(ass, 0, 67, -10.6f, 9.0f, -6.3f, 21, 9, 14, 0.0f));
        ModelRenderer holeL2 = new ModelRenderer((ModelBase)this);
        holeL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(holeL2, 0.0f, 0.0f, 0.7285f);
        this.Body.func_78792_a(holeL2);
        holeL2.field_78804_l.add(new ModelBox(holeL2, 0, 0, 0.0f, -7.0f, -12.0f, 4, 4, 2, 0.0f));
        ModelRenderer holeL3 = new ModelRenderer((ModelBase)this);
        holeL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(holeL3, 0.0f, 0.0f, 0.7285f);
        this.Body.func_78792_a(holeL3);
        holeL3.field_78804_l.add(new ModelBox(holeL3, 0, 6, 5.5f, -1.0f, -12.0f, 4, 4, 2, 0.0f));
        ModelRenderer holeR2 = new ModelRenderer((ModelBase)this);
        holeR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(holeR2, 0.0f, 0.0f, -0.7285f);
        this.Body.func_78792_a(holeR2);
        holeR2.field_78804_l.add(new ModelBox(holeR2, 0, 0, -4.0f, -7.0f, -12.0f, 4, 4, 2, 0.0f));
        ModelRenderer holeR3 = new ModelRenderer((ModelBase)this);
        holeR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(holeR3, 0.0f, 0.0f, -0.7285f);
        this.Body.func_78792_a(holeR3);
        holeR3.field_78804_l.add(new ModelBox(holeR3, 0, 6, -9.5f, -1.0f, -12.0f, 4, 4, 2, 0.0f));
        ModelRenderer tail1 = new ModelRenderer((ModelBase)this);
        tail1.func_78793_a(-3.0f, 4.3f, 13.0f);
        this.setRotationAngle(tail1, -0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail1);
        tail1.field_78804_l.add(new ModelBox(tail1, 92, 109, 0.0f, 0.0f, -2.0f, 5, 5, 13, 0.0f));
        ModelRenderer tail2 = new ModelRenderer((ModelBase)this);
        tail2.func_78793_a(-3.0f, 5.2f, 21.0f);
        this.setRotationAngle(tail2, 0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail2);
        tail2.field_78804_l.add(new ModelBox(tail2, 97, 112, 0.0f, 0.9848f, -0.1736f, 5, 5, 10, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-9.0f, -16.0f, 0.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.4887f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 88, 14, -6.0f, 0.0f, -3.0f, 6, 18, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(10.0f, -16.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.4887f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 90, -1.0f, 0.0f, -3.0f, 6, 17, 6, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 24.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        ModelRenderer leg_1 = new ModelRenderer((ModelBase)this);
        leg_1.func_78793_a(-3.3f, -5.8f, 0.0f);
        this.RLeg.func_78792_a(leg_1);
        leg_1.field_78804_l.add(new ModelBox(leg_1, 94, 94, -4.1f, 0.0f, -4.4f, 8, 3, 8, 0.0f));
        ModelRenderer rightfoot = new ModelRenderer((ModelBase)this);
        rightfoot.func_78793_a(-1.3f, -29.8f, 0.0f);
        this.setRotationAngle(rightfoot, 0.0f, 0.4363f, 0.0f);
        this.RLeg.func_78792_a(rightfoot);
        rightfoot.field_78804_l.add(new ModelBox(rightfoot, 95, 43, -4.2289f, 27.0f, -7.5163f, 5, 3, 10, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 24.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        ModelRenderer leg_2 = new ModelRenderer((ModelBase)this);
        leg_2.func_78793_a(3.3f, -5.8f, 0.0f);
        this.LLeg.func_78792_a(leg_2);
        leg_2.field_78809_i = true;
        leg_2.field_78804_l.add(new ModelBox(leg_2, 94, 94, -3.9f, 0.0f, -4.4f, 8, 3, 8, 0.0f));
        ModelRenderer leftfoot = new ModelRenderer((ModelBase)this);
        leftfoot.func_78793_a(1.3f, -29.8f, 0.0f);
        this.setRotationAngle(leftfoot, 0.0f, -0.4363f, 0.0f);
        this.LLeg.func_78792_a(leftfoot);
        leftfoot.field_78809_i = true;
        leftfoot.field_78804_l.add(new ModelBox(leftfoot, 95, 43, -0.7711f, 27.0f, -7.5163f, 5, 3, 10, 0.0f));
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

