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

public class ModelBojack
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

    public ModelBojack(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 30, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earR, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(earR);
        earR.field_78809_i = true;
        earR.field_78804_l.add(new ModelBox(earR, 61, 27, -4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earL, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 61, 27, 4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(-2.0f, 0.0f, 5.5f);
        this.setRotationAngle(hair2, 0.2618f, 0.0f, 0.6109f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 0, 18, -3.7208f, -2.448f, -2.2857f, 8, 4, 2, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(2.0f, 0.0f, 5.5f);
        this.setRotationAngle(hair3, 0.2618f, 0.0f, -0.6109f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 0, 18, -4.2792f, -2.448f, -2.2857f, 8, 4, 2, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(2.0f, -1.0f, 5.5f);
        this.setRotationAngle(hair8, 0.349f, 0.0f, 0.0f);
        this.Head.func_78792_a(hair8);
        hair8.field_78804_l.add(new ModelBox(hair8, 0, 16, -6.0f, -3.0f, -2.5f, 8, 7, 3, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(2.0f, 5.0f, 6.5f);
        this.setRotationAngle(hair9, 0.0873f, 0.0f, 0.0f);
        this.Head.func_78792_a(hair9);
        hair9.field_78804_l.add(new ModelBox(hair9, 0, 16, -6.0f, -3.0f, -2.5f, 8, 7, 3, 0.0f));
        ModelRenderer hair12 = new ModelRenderer((ModelBase)this);
        hair12.func_78793_a(-2.0f, 0.0f, 6.5f);
        this.setRotationAngle(hair12, 0.0873f, 0.0f, 0.6109f);
        this.Head.func_78792_a(hair12);
        hair12.field_78804_l.add(new ModelBox(hair12, 0, 18, -2.0f, 0.0f, -2.5f, 8, 4, 2, 0.0f));
        ModelRenderer hair13 = new ModelRenderer((ModelBase)this);
        hair13.func_78793_a(2.0f, 0.0f, 6.5f);
        this.setRotationAngle(hair13, 0.0873f, 0.0f, -0.6109f);
        this.Head.func_78792_a(hair13);
        hair13.field_78804_l.add(new ModelBox(hair13, 0, 18, -6.0f, 0.0f, -2.5f, 8, 4, 2, 0.0f));
        ModelRenderer hair14 = new ModelRenderer((ModelBase)this);
        hair14.func_78793_a(2.0f, 5.0f, 7.5f);
        this.setRotationAngle(hair14, 0.0873f, 0.0f, -0.6109f);
        this.Head.func_78792_a(hair14);
        hair14.field_78804_l.add(new ModelBox(hair14, 0, 17, -6.0f, 0.0f, -3.5f, 8, 4, 3, 0.0f));
        ModelRenderer hair15 = new ModelRenderer((ModelBase)this);
        hair15.func_78793_a(-2.0f, 5.0f, 7.5f);
        this.setRotationAngle(hair15, 0.0873f, 0.0f, 0.6109f);
        this.Head.func_78792_a(hair15);
        hair15.field_78804_l.add(new ModelBox(hair15, 0, 17, -2.0f, 0.0f, -3.5f, 8, 4, 3, 0.0f));
        ModelRenderer bandanaStripR = new ModelRenderer((ModelBase)this);
        bandanaStripR.func_78793_a(0.0f, -4.0f, 4.0f);
        this.setRotationAngle(bandanaStripR, 0.0f, 0.0f, -0.3491f);
        this.Head.func_78792_a(bandanaStripR);
        bandanaStripR.field_78804_l.add(new ModelBox(bandanaStripR, 8, 11, -7.0f, -2.0f, 0.0f, 7, 2, 0, 0.0f));
        ModelRenderer bandanaStripL = new ModelRenderer((ModelBase)this);
        bandanaStripL.func_78793_a(0.0f, -4.0f, 4.0f);
        this.setRotationAngle(bandanaStripL, 0.0f, 0.0f, 0.3491f);
        this.Head.func_78792_a(bandanaStripL);
        bandanaStripL.field_78809_i = true;
        bandanaStripL.field_78804_l.add(new ModelBox(bandanaStripL, 8, 11, 0.0f, -2.0f, 0.0f, 7, 2, 0, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 40, 0, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        ModelRenderer bb_main = new ModelRenderer((ModelBase)this);
        bb_main.func_78793_a(-0.2f, 18.6f, 0.2f);
        this.Body.func_78792_a(bb_main);
        bb_main.field_78804_l.add(new ModelBox(bb_main, 32, 32, -4.5f, -13.6f, -2.4f, 9, 10, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 54, 54, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(4.0f, 22.0f, 0.0f);
        this.RArm.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 40, 16, -9.0f, -25.0f, -3.0f, 5, 4, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 56, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(4.0f, 22.0f, 0.0f);
        this.LArm.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 0, 46, 4.0f, -25.0f, -3.0f, 5, 4, 6, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 22, 47, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 38, 47, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

