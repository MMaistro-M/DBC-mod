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

public class ModelSlugGiant
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

    public ModelSlugGiant(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer head2 = new ModelRenderer((ModelBase)this);
        head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(head2);
        head2.field_78804_l.add(new ModelBox(head2, 0, 16, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(-4.0f, -3.0f, -3.0f);
        this.setRotationAngle(earR, 0.0f, -0.4363f, 0.0f);
        this.Head.func_78792_a(earR);
        earR.field_78804_l.add(new ModelBox(earR, 24, 16, 0.4226f, -4.0f, 0.9063f, 0, 4, 4, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(4.0f, -3.0f, -3.0f);
        this.setRotationAngle(earL, 0.0f, 0.4363f, 0.0f);
        this.Head.func_78792_a(earL);
        earL.field_78809_i = true;
        earL.field_78804_l.add(new ModelBox(earL, 24, 16, -0.4226f, -4.0f, 0.9063f, 0, 4, 4, 0.0f));
        ModelRenderer antennaR = new ModelRenderer((ModelBase)this);
        antennaR.func_78793_a(-1.25f, -6.0f, -4.5f);
        this.setRotationAngle(antennaR, 0.1745f, 0.6981f, 0.0f);
        this.Head.func_78792_a(antennaR);
        antennaR.field_78804_l.add(new ModelBox(antennaR, 28, 16, -1.0f, -1.0f, -2.0f, 1, 1, 3, 0.0f));
        ModelRenderer antennaL = new ModelRenderer((ModelBase)this);
        antennaL.func_78793_a(1.25f, -6.0f, -4.5f);
        this.setRotationAngle(antennaL, 0.1745f, -0.6981f, 0.0f);
        this.Head.func_78792_a(antennaL);
        antennaL.field_78809_i = true;
        antennaL.field_78804_l.add(new ModelBox(antennaL, 28, 16, 0.0f, -1.0f, -2.0f, 1, 1, 3, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 28, 28, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 32, 44, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 32, 0, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 32, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 16, 40, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

