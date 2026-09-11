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

public class ModelFusion_Mara
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer bone2;
    private final ModelRenderer Head_r1;
    private final ModelRenderer Head_r2;
    private final ModelRenderer bone;
    private final ModelRenderer Head_r3;
    private final ModelRenderer Head_r4;
    private final ModelRenderer Body;
    private final ModelRenderer bone3;
    private final ModelRenderer Body_r1;
    private final ModelRenderer Body_r2;
    private final ModelRenderer Body_r3;
    private final ModelRenderer Body_r4;
    private final ModelRenderer Body_r5;
    private final ModelRenderer Body_r6;
    private final ModelRenderer Body_r7;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftArm1;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightLeg;
    private float scale = 1.0f;

    public ModelFusion_Mara(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, -1.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -7.75f, -3.25f, 8, 8, 8, -0.2f));
        this.bone2 = new ModelRenderer((ModelBase)this);
        this.bone2.func_78793_a(-4.5f, 25.0f, 0.0f);
        this.Head.func_78792_a(this.bone2);
        this.setRotationAngle(this.bone2, 0.0f, -0.1745f, 0.1745f);
        this.Head_r1 = new ModelRenderer((ModelBase)this);
        this.Head_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.Head_r1);
        this.setRotationAngle(this.Head_r1, 0.6563f, -0.0692f, -0.0532f);
        this.Head_r1.field_78804_l.add(new ModelBox(this.Head_r1, 40, 17, -5.7411f, -23.5995f, 15.1368f, 2, 2, 5, 0.0f));
        this.Head_r2 = new ModelRenderer((ModelBase)this);
        this.Head_r2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.Head_r2);
        this.setRotationAngle(this.Head_r2, 0.0f, 0.0f, 0.0436f);
        this.Head_r2.field_78804_l.add(new ModelBox(this.Head_r2, 24, 0, -8.6735f, -30.9924f, 1.0152f, 3, 2, 2, 0.0f));
        this.bone = new ModelRenderer((ModelBase)this);
        this.bone.func_78793_a(4.5f, 25.0f, 0.0f);
        this.Head.func_78792_a(this.bone);
        this.setRotationAngle(this.bone, 0.0f, 0.1745f, -0.1745f);
        this.Head_r3 = new ModelRenderer((ModelBase)this);
        this.Head_r3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.Head_r3);
        this.setRotationAngle(this.Head_r3, 0.6563f, 0.0692f, 0.0532f);
        this.Head_r3.field_78804_l.add(new ModelBox(this.Head_r3, 43, 44, 3.7411f, -23.5995f, 15.1368f, 2, 2, 5, 0.0f));
        this.Head_r4 = new ModelRenderer((ModelBase)this);
        this.Head_r4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.Head_r4);
        this.setRotationAngle(this.Head_r4, 0.0f, 0.0f, -0.0436f);
        this.Head_r4.field_78804_l.add(new ModelBox(this.Head_r4, 44, 0, 5.6735f, -30.9924f, 1.0152f, 3, 2, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -4.0f, 0.0f, -2.0f, 8, 6, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 20, 22, -4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 26, -3.5f, 5.0f, -1.5f, 7, 7, 3, 0.0f));
        this.bone3 = new ModelRenderer((ModelBase)this);
        this.bone3.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Body.func_78792_a(this.bone3);
        this.Body_r1 = new ModelRenderer((ModelBase)this);
        this.Body_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r1);
        this.setRotationAngle(this.Body_r1, 0.0873f, 0.0f, 0.0f);
        this.Body_r1.field_78804_l.add(new ModelBox(this.Body_r1, 0, 0, -1.0f, -24.0f, 3.0f, 2, 5, 2, 0.0f));
        this.Body_r2 = new ModelRenderer((ModelBase)this);
        this.Body_r2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r2);
        this.setRotationAngle(this.Body_r2, 0.0873f, -0.0873f, 0.0436f);
        this.Body_r2.field_78804_l.add(new ModelBox(this.Body_r2, 0, 0, -3.0f, -24.0f, 3.0f, 2, 5, 2, 0.0f));
        this.Body_r3 = new ModelRenderer((ModelBase)this);
        this.Body_r3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r3);
        this.setRotationAngle(this.Body_r3, 0.0873f, -0.1309f, 0.1309f);
        this.Body_r3.field_78804_l.add(new ModelBox(this.Body_r3, 0, 0, -7.0f, -23.25f, 3.1f, 2, 3, 2, 0.0f));
        this.Body_r4 = new ModelRenderer((ModelBase)this);
        this.Body_r4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r4);
        this.setRotationAngle(this.Body_r4, 0.0873f, 0.1309f, -0.0873f);
        this.Body_r4.field_78804_l.add(new ModelBox(this.Body_r4, 0, 0, 3.0f, -23.75f, 3.1f, 2, 4, 2, 0.0f));
        this.Body_r5 = new ModelRenderer((ModelBase)this);
        this.Body_r5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r5);
        this.setRotationAngle(this.Body_r5, 0.0873f, 0.0873f, -0.0436f);
        this.Body_r5.field_78804_l.add(new ModelBox(this.Body_r5, 0, 0, 1.0f, -24.0f, 3.0f, 2, 5, 2, 0.0f));
        this.Body_r6 = new ModelRenderer((ModelBase)this);
        this.Body_r6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r6);
        this.setRotationAngle(this.Body_r6, 0.0873f, 0.1309f, -0.1309f);
        this.Body_r6.field_78804_l.add(new ModelBox(this.Body_r6, 0, 0, 5.0f, -23.25f, 3.1f, 2, 3, 2, 0.0f));
        this.Body_r7 = new ModelRenderer((ModelBase)this);
        this.Body_r7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r7);
        this.setRotationAngle(this.Body_r7, 0.0873f, -0.1309f, 0.0873f);
        this.Body_r7.field_78804_l.add(new ModelBox(this.Body_r7, 0, 0, -5.0f, -23.75f, 3.1f, 2, 4, 2, 0.0f));
        this.RightArm = new ModelRenderer((ModelBase)this);
        this.RightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RightArm);
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 44, 7, -3.0f, -2.0f, -2.0f, 4, 6, 4, 0.0f));
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 28, 12, -3.0f, 3.75f, -2.0f, 4, 6, 4, -0.1f));
        this.LeftArm = new ModelRenderer((ModelBase)this);
        this.LeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.LeftArm.field_78809_i = true;
        this.Body.func_78792_a(this.LeftArm);
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 44, 7, -1.0f, -2.0f, -2.0f, 4, 6, 4, 0.0f));
        this.LeftArm1 = new ModelRenderer((ModelBase)this);
        this.LeftArm1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LeftArm.func_78792_a(this.LeftArm1);
        this.LeftArm1.field_78804_l.add(new ModelBox(this.LeftArm1, 0, 36, -1.0f, 3.75f, -2.0f, 4, 6, 4, -0.1f));
        this.LeftLeg = new ModelRenderer((ModelBase)this);
        this.LeftLeg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LeftLeg);
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 32, 0, -1.9f, 0.0f, -2.0f, 4, 7, 4, 0.0f));
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 32, 39, -2.0f, 6.0f, -2.0f, 4, 6, 4, -0.2f));
        this.RightLeg = new ModelRenderer((ModelBase)this);
        this.RightLeg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RightLeg);
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 20, 29, -2.1f, 0.0f, -2.0f, 4, 7, 4, 0.0f));
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 36, 29, -2.0f, 6.0f, -2.0f, 4, 6, 4, -0.2f));
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
        this.RightArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.LeftArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.RightArm.field_78808_h = 0.0f;
        this.LeftArm.field_78808_h = 0.0f;
        this.RightLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 1.4f * f1;
        this.LeftLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.RightLeg.field_78796_g = 0.0f;
        this.LeftLeg.field_78796_g = 0.0f;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

