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

public class ModelMajinDuu
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer Ears;
    private final ModelRenderer leftEar_r1;
    private final ModelRenderer rightEar_r1;
    private final ModelRenderer Antenna;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private float scale = 1.0f;

    public ModelMajinDuu(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 1.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Ears = new ModelRenderer((ModelBase)this);
        this.Ears.func_78793_a(0.0f, 15.0f, -1.0f);
        this.bipedHead.func_78792_a(this.Ears);
        this.leftEar_r1 = new ModelRenderer((ModelBase)this);
        this.leftEar_r1.func_78793_a(-5.25f, -18.5f, 1.0f);
        this.leftEar_r1.field_78809_i = true;
        this.Ears.func_78792_a(this.leftEar_r1);
        this.setRotationAngle(this.leftEar_r1, 0.0f, 0.9163f, -0.3491f);
        this.leftEar_r1.field_78804_l.add(new ModelBox(this.leftEar_r1, 0, 0, -2.0f, -2.5f, 0.0f, 4, 5, 0, 0.0f));
        this.rightEar_r1 = new ModelRenderer((ModelBase)this);
        this.rightEar_r1.func_78793_a(5.25f, -18.5f, 1.0f);
        this.Ears.func_78792_a(this.rightEar_r1);
        this.setRotationAngle(this.rightEar_r1, 0.0f, -0.9163f, 0.3491f);
        this.rightEar_r1.field_78804_l.add(new ModelBox(this.rightEar_r1, 0, 0, -2.0f, -2.5f, 0.0f, 4, 5, 0, 0.0f));
        this.Antenna = new ModelRenderer((ModelBase)this);
        this.Antenna.func_78793_a(0.0f, -12.0f, -1.0f);
        this.bipedHead.func_78792_a(this.Antenna);
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, 4.25f, 1.0f);
        this.Antenna.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, -0.6109f, 0.0f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 40, 0, -1.5f, -2.0f, -1.5f, 3, 3, 3, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, 2.0f, 3.0f);
        this.Antenna.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, -1.0036f, 0.0f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 32, 0, -1.0f, -2.0f, -1.0f, 2, 4, 2, 0.0f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 16, -6.0f, -24.0f, -2.0f, 12, 8, 6, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 30, -7.0f, -16.0f, -3.0f, 14, 8, 8, 0.0f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(7.0f, -21.0f, 0.0f);
        this.bipedLeftArm.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 36, 16, -1.0f, -3.0f, -1.0f, 4, 10, 4, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-7.0f, -21.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 36, 16, -3.0f, -3.0f, -1.0f, 4, 10, 4, 0.0f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-3.0f, -8.0f, 1.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 0, 46, -2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(3.0f, -8.0f, 1.0f);
        this.bipedLeftLeg.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 0, 46, -2.0f, 0.0f, -2.0f, 4, 8, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.bipedHead.func_78785_a(f5);
        this.bipedBody.func_78785_a(f5);
        GL11.glPopMatrix();
        this.bipedHead.field_78796_g = f3 / 57.295776f;
        this.bipedHead.field_78795_f = f4 / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 1.4f * f1;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

