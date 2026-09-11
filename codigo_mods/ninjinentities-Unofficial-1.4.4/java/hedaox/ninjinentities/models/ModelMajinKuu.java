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

public class ModelMajinKuu
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer Ears;
    private final ModelRenderer leftEar_r1;
    private final ModelRenderer rightEar_r1;
    private final ModelRenderer bipedBody;
    private final ModelRenderer Chest;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private float scale = 1.0f;

    public ModelMajinKuu(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 2.0f, 1.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, -0.1f));
        this.Ears = new ModelRenderer((ModelBase)this);
        this.Ears.func_78793_a(0.0f, 22.0f, -1.0f);
        this.bipedHead.func_78792_a(this.Ears);
        this.leftEar_r1 = new ModelRenderer((ModelBase)this);
        this.leftEar_r1.func_78793_a(-5.75f, -26.5f, 1.0f);
        this.leftEar_r1.field_78809_i = true;
        this.Ears.func_78792_a(this.leftEar_r1);
        this.setRotationAngle(this.leftEar_r1, 0.0f, 0.5236f, -0.2618f);
        this.leftEar_r1.field_78804_l.add(new ModelBox(this.leftEar_r1, 26, 1, -2.0f, -2.5f, 0.0f, 4, 5, 0, -0.1f));
        this.rightEar_r1 = new ModelRenderer((ModelBase)this);
        this.rightEar_r1.func_78793_a(5.75f, -26.5f, 1.0f);
        this.Ears.func_78792_a(this.rightEar_r1);
        this.setRotationAngle(this.rightEar_r1, 0.0f, -0.5236f, 0.2618f);
        this.rightEar_r1.field_78804_l.add(new ModelBox(this.rightEar_r1, 26, 1, -2.0f, -2.5f, 0.0f, 4, 5, 0, -0.1f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.func_78792_a(this.Chest);
        this.Chest.field_78804_l.add(new ModelBox(this.Chest, 3, 23, -3.5f, -21.7f, -0.55f, 7, 5, 3, 0.1f));
        this.Chest.field_78804_l.add(new ModelBox(this.Chest, 24, 21, -3.0f, -20.75f, -1.25f, 6, 2, 1, 0.0f));
        this.Chest.field_78804_l.add(new ModelBox(this.Chest, 4, 32, -3.0f, -20.0f, -0.5f, 6, 6, 3, 0.0f));
        this.Chest.field_78804_l.add(new ModelBox(this.Chest, 3, 42, -3.0f, -14.0f, -0.5f, 6, 3, 3, 0.0f));
        this.Chest.field_78804_l.add(new ModelBox(this.Chest, 7, 17, -2.0f, -22.0f, -0.5f, 4, 1, 3, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-4.0f, -20.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 30, 26, -2.9f, -2.2f, -0.95f, 4, 12, 4, -0.6f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(4.0f, -20.0f, 0.0f);
        this.bipedLeftArm.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 30, 26, -1.1f, -2.2f, -0.95f, 4, 12, 4, -0.6f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-1.0f, -10.0f, 1.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 17, 48, -2.0f, -1.0f, -1.5f, 3, 11, 3, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(1.0f, -10.0f, 1.0f);
        this.bipedLeftLeg.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 17, 48, -1.0f, -1.0f, -1.5f, 3, 11, 3, 0.0f));
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

