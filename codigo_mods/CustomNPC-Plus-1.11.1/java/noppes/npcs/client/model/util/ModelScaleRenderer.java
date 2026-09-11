/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import noppes.npcs.entity.data.ModelScalePart;
import org.lwjgl.opengl.GL11;

public class ModelScaleRenderer
extends ModelRenderer {
    public boolean compiledModel;
    public int displayListModel;
    protected ModelScalePart config;
    public float x;
    public float y;
    public float z;

    public ModelScaleRenderer(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public ModelScaleRenderer(ModelBase par1ModelBase, int par2, int par3) {
        this(par1ModelBase);
        this.func_78784_a(par2, par3);
    }

    public void setConfig(ModelScalePart config, float x, float y, float z) {
        this.config = config;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78785_a(float par1) {
        if (!this.field_78806_j || this.field_78807_k) {
            return;
        }
        if (!this.compiledModel) {
            this.compileDisplayListModel(par1);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.x, (float)this.y, (float)this.z);
        if (this.config != null) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
        }
        this.func_78794_c(par1);
        if (this.config != null) {
            GL11.glScalef((float)this.config.scaleX, (float)this.config.scaleY, (float)this.config.scaleZ);
        }
        GL11.glCallList((int)this.displayListModel);
        if (this.field_78805_m != null) {
            for (int i = 0; i < this.field_78805_m.size(); ++i) {
                ((ModelRenderer)this.field_78805_m.get(i)).func_78785_a(par1);
            }
        }
        GL11.glPopMatrix();
    }

    public void compileDisplayListModel(float par1) {
        this.displayListModel = GLAllocation.func_74526_a((int)1);
        GL11.glNewList((int)this.displayListModel, (int)4864);
        Tessellator tessellator = Tessellator.field_78398_a;
        for (int i = 0; i < this.field_78804_l.size(); ++i) {
            ((ModelBox)this.field_78804_l.get(i)).func_78245_a(tessellator, par1);
        }
        GL11.glEndList();
        this.compiledModel = true;
    }
}

