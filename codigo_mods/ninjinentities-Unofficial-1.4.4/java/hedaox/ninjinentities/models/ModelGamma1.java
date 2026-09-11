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

public class ModelGamma1
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer LArm;
    private final ModelRenderer RArm;
    private final ModelRenderer LLeg;
    private final ModelRenderer RLeg;
    private float scale = 1.0f;

    public ModelGamma1(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer tb = new ModelRenderer((ModelBase)this);
        tb.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(tb);
        tb.field_78804_l.add(new ModelBox(tb, 44, 2, -0.5f, -12.87f, 0.0f, 1, 5, 3, 0.0f));
        ModelRenderer cube_r1 = new ModelRenderer((ModelBase)this);
        cube_r1.func_78793_a(0.0f, -7.316f, -3.4661f);
        tb.func_78792_a(cube_r1);
        this.setRotationAngle(cube_r1, -1.2654f, 0.0f, 0.0f);
        cube_r1.field_78804_l.add(new ModelBox(cube_r1, 44, 3, -0.5f, -1.0f, -1.5f, 1, 2, 3, 0.0f));
        ModelRenderer cube_r2 = new ModelRenderer((ModelBase)this);
        cube_r2.func_78793_a(0.0f, -9.5f, -3.5f);
        tb.func_78792_a(cube_r2);
        this.setRotationAngle(cube_r2, -1.0472f, 0.0f, 0.0f);
        cube_r2.field_78804_l.add(new ModelBox(cube_r2, 42, 0, -0.5f, -4.75f, -1.15f, 1, 3, 5, 0.0f));
        cube_r2.field_78804_l.add(new ModelBox(cube_r2, 43, 1, -0.5f, -2.75f, -1.15f, 1, 3, 4, 0.0f));
        ModelRenderer cube_r3 = new ModelRenderer((ModelBase)this);
        cube_r3.func_78793_a(0.0f, -9.5f, -3.5f);
        tb.func_78792_a(cube_r3);
        this.setRotationAngle(cube_r3, -0.3054f, 0.0f, 0.0f);
        cube_r3.field_78804_l.add(new ModelBox(cube_r3, 44, 2, -0.5f, -0.6f, -1.0f, 1, 2, 3, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        ModelRenderer cube_r4 = new ModelRenderer((ModelBase)this);
        cube_r4.func_78793_a(-0.5f, -4.9f, 3.0f);
        this.Body.func_78792_a(cube_r4);
        this.setRotationAngle(cube_r4, 0.2182f, 0.0f, 0.0f);
        cube_r4.field_78804_l.add(new ModelBox(cube_r4, 98, 0, -7.5f, -0.6f, -0.7f, 15, 21, 0, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(4.8f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(0.8f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -1.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

