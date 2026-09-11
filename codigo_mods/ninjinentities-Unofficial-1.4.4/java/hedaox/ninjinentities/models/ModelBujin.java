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

public class ModelBujin
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

    public ModelBujin(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 30, -4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer headhair = new ModelRenderer((ModelBase)this);
        headhair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(headhair);
        headhair.field_78804_l.add(new ModelBox(headhair, 0, 0, -5.0f, -5.0f, -5.0f, 10, 10, 10, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 32, 32, -4.2f, -1.4f, -1.8f, 8, 10, 4, 0.0f));
        ModelRenderer bb_main = new ModelRenderer((ModelBase)this);
        bb_main.func_78793_a(-0.2f, 18.6f, 0.2f);
        this.Body.func_78792_a(bb_main);
        bb_main.field_78804_l.add(new ModelBox(bb_main, 34, 49, -4.5f, -10.6f, -2.4f, 9, 7, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 0, 46, -4.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(4.0f, 22.0f, 0.0f);
        this.RArm.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 0, 21, -9.0f, -21.0f, -3.0f, 5, 3, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 16, 46, 8.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LArm.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 0, 21, 8.0f, 1.0f, -3.0f, 5, 3, 6, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 40, 16, 2.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 40, 0, -2.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
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

