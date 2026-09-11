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

public class ModelEldKaioshin
extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer Body_r1;
    private final ModelRenderer RArm;
    private final ModelRenderer RArm1_r1;
    private final ModelRenderer LArm;
    private final ModelRenderer LArm1_r1;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer Head;
    private final ModelRenderer Ear2_r1;
    private final ModelRenderer Ear1_r1;
    private final ModelRenderer Head_r1;
    private final float scale = 1.0f;

    public ModelEldKaioshin(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 24, 0, -4.2f, 2.6f, -1.8f, 8, 4, 4, 0.01f));
        this.Body_r1 = new ModelRenderer((ModelBase)this);
        this.Body_r1.func_78793_a(-0.2f, 3.9f, 0.4f);
        this.Body.func_78792_a(this.Body_r1);
        this.setRotationAngle(this.Body_r1, 0.2705f, 0.0f, 0.0f);
        this.Body_r1.field_78804_l.add(new ModelBox(this.Body_r1, 0, 16, -4.0f, -8.7f, -1.83f, 8, 8, 4, -0.07f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-3.8639f, -3.5778f, -1.0958f);
        this.Body.func_78792_a(this.RArm);
        this.setRotationAngle(this.RArm, 0.7462f, 0.0891f, -0.096f);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 36, 8, -4.2607f, -1.8179f, -1.8301f, 4, 6, 4, -0.1f));
        this.RArm1_r1 = new ModelRenderer((ModelBase)this);
        this.RArm1_r1.func_78793_a(-2.2607f, 4.0821f, 0.1699f);
        this.RArm.func_78792_a(this.RArm1_r1);
        this.setRotationAngle(this.RArm1_r1, 0.0f, 0.0f, -0.5236f);
        this.RArm1_r1.field_78804_l.add(new ModelBox(this.RArm1_r1, 40, 18, -1.7454f, -1.0443f, -1.996f, 4, 6, 4, -0.11f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(3.4639f, -3.5778f, -1.0958f);
        this.Body.func_78792_a(this.LArm);
        this.setRotationAngle(this.LArm, 0.7462f, -0.0891f, 0.096f);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 36, 8, 0.2607f, -1.8179f, -1.8301f, 4, 6, 4, -0.1f));
        this.LArm1_r1 = new ModelRenderer((ModelBase)this);
        this.LArm1_r1.func_78793_a(2.2607f, 4.0821f, 0.1699f);
        this.LArm.func_78792_a(this.LArm1_r1);
        this.setRotationAngle(this.LArm1_r1, 0.0f, 0.0f, 0.5236f);
        this.LArm1_r1.field_78809_i = true;
        this.LArm1_r1.field_78804_l.add(new ModelBox(this.LArm1_r1, 40, 18, -2.2446f, -1.0543f, -1.996f, 4, 6, 4, -0.11f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 24, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 24, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.2f, 0.0f);
        this.Ear2_r1 = new ModelRenderer((ModelBase)this);
        this.Ear2_r1.func_78793_a(2.8f, -1.0f, -3.0f);
        this.Head.func_78792_a(this.Ear2_r1);
        this.setRotationAngle(this.Ear2_r1, 0.0f, -0.5236f, 0.0f);
        this.Ear2_r1.field_78809_i = true;
        this.Ear2_r1.field_78804_l.add(new ModelBox(this.Ear2_r1, 0, 30, 0.1f, -4.0f, -0.4f, 5, 5, 0, -0.19f));
        this.Ear1_r1 = new ModelRenderer((ModelBase)this);
        this.Ear1_r1.func_78793_a(-2.8f, -1.0f, -3.0f);
        this.Head.func_78792_a(this.Ear1_r1);
        this.setRotationAngle(this.Ear1_r1, 0.0f, 0.5236f, 0.0f);
        this.Ear1_r1.field_78804_l.add(new ModelBox(this.Ear1_r1, 0, 30, -5.1f, -4.0f, -0.4f, 5, 5, 0, -0.19f));
        this.Head_r1 = new ModelRenderer((ModelBase)this);
        this.Head_r1.func_78793_a(0.0f, -3.0f, -1.7f);
        this.Head.func_78792_a(this.Head_r1);
        this.setRotationAngle(this.Head_r1, 0.0436f, 0.0f, 0.0f);
        this.Head_r1.field_78804_l.add(new ModelBox(this.Head_r1, 0, 0, -4.0f, -3.7f, -4.1f, 8, 8, 8, -0.19f));
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

