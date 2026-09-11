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

public class ModelHirudegarnBottom
extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer Bone;
    private final ModelRenderer Tail;
    private final ModelRenderer Tail2;
    private final ModelRenderer bodyChildChild;
    private final ModelRenderer Portal;
    private final ModelRenderer RLeg;
    private final ModelRenderer RLeg2;
    private final ModelRenderer LLeg;
    private final ModelRenderer LeftLeg3;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelHirudegarnBottom(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 102, 29, -7.0f, -34.0f, -4.0f, 14, 5, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 98, 106, -6.0f, -29.0f, -4.0f, 12, 7, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 137, -2.0f, -23.0f, -4.0f, 4, 3, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 58, 131, -3.0f, -20.0f, -4.0f, 6, 3, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 120, -9.0f, -26.0f, -5.0f, 7, 7, 10, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 117, 42, 2.0f, -26.0f, -5.0f, 7, 7, 10, 0.0f));
        this.Bone = new ModelRenderer((ModelBase)this);
        this.Bone.func_78793_a(2.0f, -48.0f, -4.0f);
        this.Body.func_78792_a(this.Bone);
        this.Tail = new ModelRenderer((ModelBase)this);
        this.Tail.func_78793_a(0.0f, -15.0f, 0.0f);
        this.setRotationAngle(this.Tail, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.Tail);
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 210, 54, -3.0f, -10.0611f, 1.3468f, 6, 5, 17, 0.0f));
        this.Tail2 = new ModelRenderer((ModelBase)this);
        this.Tail2.func_78793_a(0.0f, -17.0f, 0.0f);
        this.setRotationAngle(this.Tail2, 0.5236f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.Tail2);
        this.Tail2.field_78804_l.add(new ModelBox(this.Tail2, 204, 0, -3.0f, 7.5096f, 14.4904f, 6, 5, 20, 0.0f));
        this.bodyChildChild = new ModelRenderer((ModelBase)this);
        this.bodyChildChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Tail2.func_78792_a(this.bodyChildChild);
        this.bodyChildChild.field_78804_l.add(new ModelBox(this.bodyChildChild, 227, 56, -2.0f, 8.6436f, 34.8564f, 4, 3, 6, 0.0f));
        this.Portal = new ModelRenderer((ModelBase)this);
        this.Portal.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body.func_78792_a(this.Portal);
        this.Portal.field_78804_l.add(new ModelBox(this.Portal, 44, 221, -26.0f, -44.0f, -15.0f, 50, 1, 30, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-7.0f, -18.0f, -1.0f);
        this.setRotationAngle(this.RLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 97, 121, -3.0f, -1.388f, -2.3382f, 6, 15, 6, 0.0f));
        this.RLeg2 = new ModelRenderer((ModelBase)this);
        this.RLeg2.func_78793_a(-6.0f, 27.4131f, 6.6975f);
        this.setRotationAngle(this.RLeg2, 0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(this.RLeg2);
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 129, 60, 3.0f, -16.2611f, -6.6831f, 6, 15, 6, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 140, 120, 2.0f, -1.9051f, -8.8096f, 3, 1, 8, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 26, 71, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 141, 121, 7.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(7.0f, -18.0f, 0.0f);
        this.setRotationAngle(this.LLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 97, 121, -3.0f, -1.2575f, -3.3296f, 6, 15, 6, 0.0f));
        this.LeftLeg3 = new ModelRenderer((ModelBase)this);
        this.LeftLeg3.func_78793_a(-6.0f, 27.5436f, 5.7061f);
        this.setRotationAngle(this.LeftLeg3, 0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(this.LeftLeg3);
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 129, 60, 3.0f, -16.2611f, -6.6831f, 6, 15, 6, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 140, 120, 2.0f, -1.9051f, -8.8096f, 3, 1, 8, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 26, 71, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 141, 121, 7.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
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

