/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.models;

import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelPotara
extends ModelBipedBody {
    public static final ModelPotara RIGHT_EAR = new ModelPotara(1);
    public static final ModelPotara LEFT_EAR = new ModelPotara(0);
    private final ModelRenderer Head;
    private final ModelRenderer earringRight;
    private final ModelRenderer earringLeft;

    private ModelPotara(int side) {
        this();
        if (side == 0) {
            this.earringLeft.field_78807_k = true;
        }
        if (side == 1) {
            this.earringRight.field_78807_k = true;
        }
    }

    private ModelPotara() {
        this.field_78090_t = 16;
        this.field_78089_u = 16;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earringRight = new ModelRenderer((ModelBase)this);
        this.earringRight.field_78804_l.add(new ModelBox(this.earringRight, 4, 2, -4.75f, -3.5f, -1.0f, 1, 2, 1, -0.25f));
        this.earringRight.field_78804_l.add(new ModelBox(this.earringRight, 0, 2, -5.0f, -2.5f, -1.0f, 1, 1, 1, 0.0f));
        this.earringLeft = new ModelRenderer((ModelBase)this);
        this.earringLeft.field_78804_l.add(new ModelBox(this.earringLeft, 4, 2, 3.75f, -3.5f, -1.0f, 1, 2, 1, -0.25f));
        this.earringLeft.field_78804_l.add(new ModelBox(this.earringLeft, 0, 2, 4.0f, -2.5f, -1.0f, 1, 1, 1, 0.0f));
        this.Head.func_78792_a(this.earringLeft);
        this.Head.func_78792_a(this.earringRight);
    }

    private void copyRotationData(ModelRenderer model, ModelRenderer modelToCopy) {
        model.field_78800_c = modelToCopy.field_78800_c;
        model.field_78797_d = modelToCopy.field_78797_d;
        model.field_78798_e = modelToCopy.field_78798_e;
        this.rot(model, modelToCopy);
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.copyRotationData(this.Head, this.field_78116_c);
        this.field_78117_n = entity.func_70093_af();
        this.renderHeadpiece(f5);
    }

    private void renderHeadpiece(float scale) {
        if (g <= 1) {
            if (this.field_78091_s) {
                float scalar = 2.0f;
                GL11.glPushMatrix();
                GL11.glScalef((float)(1.5f / scalar), (float)(1.5f / scalar), (float)(1.5f / scalar));
                GL11.glTranslatef((float)0.0f, (float)(16.0f * scale), (float)0.0f);
                this.field_78116_c.func_78785_a(scale);
                GL11.glPopMatrix();
            } else {
                float scalar = f;
                GL11.glPushMatrix();
                GL11.glScalef((float)(0.5f + 0.5f / scalar), (float)(0.5f + 0.5f / scalar), (float)(0.5f + 0.5f / scalar));
                GL11.glTranslatef((float)0.0f, (float)((scalar - 1.0f) / scalar * (2.0f - (scalar >= 1.5f && scalar <= 2.0f ? (2.0f - scalar) / 2.5f : (scalar < 1.5f && scalar >= 1.0f ? (scalar * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
                this.Head.func_78785_a(scale);
                GL11.glPopMatrix();
            }
        } else {
            float scalar = f;
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / scalar) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / scalar), (float)((0.5f + 0.5f / scalar) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((scalar - 1.0f) / scalar * (2.0f - (scalar >= 1.5f && scalar <= 2.0f ? (2.0f - scalar) / 2.5f : (scalar < 1.5f && scalar >= 1.0f ? (scalar * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.Head.func_78785_a(scale);
            GL11.glPopMatrix();
        }
    }
}

