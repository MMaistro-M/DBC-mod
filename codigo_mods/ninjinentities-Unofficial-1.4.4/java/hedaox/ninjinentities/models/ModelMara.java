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

public class ModelMara
extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer bone3;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer Head;
    private final ModelRenderer bone;
    private final ModelRenderer Head_r1;
    private final ModelRenderer bone2;
    private final ModelRenderer Head_r2;
    private float scale = 1.0f;

    public ModelMara(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 24, -4.0f, 0.0f, -2.0f, 8, 6, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 24, 0, -4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 34, -3.0f, 6.0f, -1.5f, 6, 6, 3, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -4.5f, 9.0f, -2.25f, 9, 3, 5, 0.02f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 24, 19, -4.5f, 11.0f, -2.25f, 5, 6, 5, 0.0f));
        this.bone3 = new ModelRenderer((ModelBase)this);
        this.bone3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.bone3);
        this.bone3.field_78809_i = true;
        this.bone3.field_78804_l.add(new ModelBox(this.bone3, 24, 19, -0.5f, 11.0f, -2.25f, 5, 6, 5, 0.0f));
        this.RightLeg = new ModelRenderer((ModelBase)this);
        this.RightLeg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RightLeg);
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 32, 7, -2.0f, 0.0f, -2.0f, 4, 7, 4, 0.0f));
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 52, 45, -1.5f, 7.0f, -1.5f, 3, 5, 3, 0.0f));
        this.RightLeg.field_78809_i = true;
        this.LeftLeg = new ModelRenderer((ModelBase)this);
        this.LeftLeg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LeftLeg);
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 32, 7, -2.0f, 0.0f, -2.0f, 4, 7, 4, 0.0f));
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 52, 45, -1.5f, 7.0f, -1.5f, 3, 5, 3, 0.0f));
        this.RightArm = new ModelRenderer((ModelBase)this);
        this.RightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RightArm);
        this.RightArm.field_78809_i = true;
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 44, 14, -3.0f, -2.25f, -2.0f, 4, 6, 4, 0.0f));
        this.RightArm.field_78809_i = true;
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 36, 38, -2.5f, 0.0f, -1.5f, 3, 12, 3, 0.0f));
        this.LeftArm = new ModelRenderer((ModelBase)this);
        this.LeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LeftArm);
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 44, 14, -1.0f, -2.25f, -2.0f, 4, 6, 4, 0.0f));
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 36, 38, -0.5f, 0.0f, -1.5f, 3, 12, 3, 0.0f));
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, -1.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -7.75f, -3.25f, 8, 8, 8, -0.2f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 48, 37, -3.5f, 0.0f, -3.0f, 7, 7, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 43, -1.5f, -2.75f, -5.75f, 3, 3, 4, -0.25f));
        this.bone = new ModelRenderer((ModelBase)this);
        this.bone.func_78793_a(-0.5f, 25.0f, 0.0f);
        this.Head.func_78792_a(this.bone);
        this.bone.field_78804_l.add(new ModelBox(this.bone, 48, 10, 3.5f, -31.0f, 2.0f, 3, 2, 2, 0.0f));
        this.Head_r1 = new ModelRenderer((ModelBase)this);
        this.Head_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.Head_r1);
        this.setRotationAngle(this.Head_r1, 0.6563f, 0.0692f, 0.0532f);
        this.Head_r1.field_78804_l.add(new ModelBox(this.Head_r1, 42, 47, 3.5f, -23.0f, 14.9f, 2, 2, 6, 0.0f));
        this.bone2 = new ModelRenderer((ModelBase)this);
        this.bone2.func_78793_a(1.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.bone2);
        this.bone2.field_78804_l.add(new ModelBox(this.bone2, 48, 10, -6.5f, -31.0f, 2.0f, 3, 2, 2, 0.0f));
        this.Head_r2 = new ModelRenderer((ModelBase)this);
        this.Head_r2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.Head_r2);
        this.setRotationAngle(this.Head_r2, 0.6563f, -0.0692f, -0.0532f);
        this.Head_r2.field_78804_l.add(new ModelBox(this.Head_r2, 42, 47, -5.5f, -23.0f, 14.9f, 2, 2, 6, 0.0f));
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

