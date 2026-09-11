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

public class ModelBido
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer LLeg;
    private final ModelRenderer RLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelBido(float _scaleX, float _scaleY, float _scaleZ) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 30, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earR, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(earR);
        earR.field_78809_i = true;
        earR.field_78804_l.add(new ModelBox(earR, 25, 19, -4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earL, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 25, 19, 4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer headhair = new ModelRenderer((ModelBase)this);
        headhair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(headhair);
        headhair.field_78804_l.add(new ModelBox(headhair, 0, 0, -5.0f, -9.0f, -5.0f, 10, 9, 10, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(1.0f, -8.0f, 0.0f);
        this.setRotationAngle(hair1, 0.7854f, 0.3491f, -0.6109f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 0, 0, -2.0f, -4.0f, 0.0f, 2, 5, 2, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(0.0f, -7.0f, -2.0f);
        this.setRotationAngle(hair2, 1.3963f, 0.5236f, 0.3491f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 0, 0, -2.0f, -4.0f, 0.0f, 2, 5, 2, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(1.0f, -7.0f, 3.0f);
        this.setRotationAngle(hair3, 0.7854f, 0.3491f, -0.0873f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 0, 0, -1.658f, -4.6645f, -1.6645f, 2, 4, 3, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 32, 32, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        ModelRenderer bb_main = new ModelRenderer((ModelBase)this);
        bb_main.func_78793_a(-0.2f, 18.6f, 0.2f);
        this.Body.func_78792_a(bb_main);
        bb_main.field_78804_l.add(new ModelBox(bb_main, 34, 49, -4.5f, -14.6f, -2.4f, 9, 7, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 0, 46, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 16, 46, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 40, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 40, 0, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

