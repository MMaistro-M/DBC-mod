/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class Model2DRenderer
extends ModelRenderer {
    private boolean compiledModel;
    private int displayListModel;
    private float x1;
    private float x2;
    private float y1;
    private float y2;
    private int width;
    private int height;
    private float rotationOffsetX;
    private float rotationOffsetY;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float thickness = 1.0f;

    public Model2DRenderer(ModelBase par1ModelBase, float x, float y, int width, int height, float textureWidth, float textureHeight) {
        super(par1ModelBase);
        this.width = width;
        this.height = height;
        this.field_78801_a = textureWidth;
        this.field_78799_b = textureHeight;
        this.x1 = x / textureWidth;
        this.y1 = y / textureHeight;
        this.x2 = (x + (float)width) / textureWidth;
        this.y2 = (y + (float)height) / textureHeight;
    }

    public Model2DRenderer(ModelBase base, int x, int y, int width, int height) {
        this(base, x, y, width, height, width, height);
    }

    public void func_78785_a(float par1) {
        if (!this.field_78806_j || this.field_78807_k) {
            return;
        }
        if (!this.compiledModel) {
            this.compileDisplayListModel(par1);
        }
        GL11.glPushMatrix();
        this.func_78794_c(par1);
        GL11.glCallList((int)this.displayListModel);
        GL11.glPopMatrix();
    }

    public void setRotationOffset(float x, float y) {
        this.rotationOffsetX = x;
        this.rotationOffsetY = y;
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }

    public void setScale(float x, float y) {
        this.scaleX = x;
        this.scaleY = y;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    @SideOnly(value=Side.CLIENT)
    private void compileDisplayListModel(float par1) {
        this.displayListModel = GLAllocation.func_74526_a((int)1);
        GL11.glNewList((int)this.displayListModel, (int)4864);
        GL11.glScalef((float)(this.scaleX * (float)this.width / (float)this.height), (float)this.scaleY, (float)this.thickness);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (this.field_78809_i) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-1.0f * par1));
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glTranslated((double)(this.rotationOffsetX * par1), (double)(this.rotationOffsetY * par1), (double)0.0);
        Model2DRenderer.renderItemIn2D(this.x1, this.y1, this.x2, this.y2, this.width, this.height, par1);
        GL11.glEndList();
        this.compiledModel = true;
    }

    public static void renderItemIn2D(float p_78439_1_, float p_78439_2_, float p_78439_3_, float p_78439_4_, int p_78439_5_, int p_78439_6_, float p_78439_7_) {
        float f9;
        float f8;
        float f7;
        int k;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(0.0, 0.0, 0.0, (double)p_78439_1_, (double)p_78439_4_);
        tessellator.func_78374_a(1.0, 0.0, 0.0, (double)p_78439_3_, (double)p_78439_4_);
        tessellator.func_78374_a(1.0, 1.0, 0.0, (double)p_78439_3_, (double)p_78439_2_);
        tessellator.func_78374_a(0.0, 1.0, 0.0, (double)p_78439_1_, (double)p_78439_2_);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(0.0, 1.0, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)p_78439_2_);
        tessellator.func_78374_a(1.0, 1.0, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)p_78439_2_);
        tessellator.func_78374_a(1.0, 0.0, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)p_78439_4_);
        tessellator.func_78374_a(0.0, 0.0, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)p_78439_4_);
        tessellator.func_78381_a();
        float f5 = 0.5f * (p_78439_1_ - p_78439_3_) / (float)p_78439_5_;
        float f6 = 0.5f * (p_78439_4_ - p_78439_2_) / (float)p_78439_6_;
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        for (k = 0; k < p_78439_5_; ++k) {
            f7 = (float)k / (float)p_78439_5_;
            f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
            tessellator.func_78374_a((double)f7, 0.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_4_);
            tessellator.func_78374_a((double)f7, 0.0, 0.0, (double)f8, (double)p_78439_4_);
            tessellator.func_78374_a((double)f7, 1.0, 0.0, (double)f8, (double)p_78439_2_);
            tessellator.func_78374_a((double)f7, 1.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_2_);
        }
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        for (k = 0; k < p_78439_5_; ++k) {
            f7 = (float)k / (float)p_78439_5_;
            f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
            f9 = f7 + 1.0f / (float)p_78439_5_;
            tessellator.func_78374_a((double)f9, 1.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_2_);
            tessellator.func_78374_a((double)f9, 1.0, 0.0, (double)f8, (double)p_78439_2_);
            tessellator.func_78374_a((double)f9, 0.0, 0.0, (double)f8, (double)p_78439_4_);
            tessellator.func_78374_a((double)f9, 0.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_4_);
        }
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        for (k = 0; k < p_78439_6_; ++k) {
            f7 = (float)k / (float)p_78439_6_;
            f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
            f9 = f7 + 1.0f / (float)p_78439_6_;
            tessellator.func_78374_a(0.0, (double)f9, 0.0, (double)p_78439_1_, (double)f8);
            tessellator.func_78374_a(1.0, (double)f9, 0.0, (double)p_78439_3_, (double)f8);
            tessellator.func_78374_a(1.0, (double)f9, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)f8);
            tessellator.func_78374_a(0.0, (double)f9, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)f8);
        }
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        for (k = 0; k < p_78439_6_; ++k) {
            f7 = (float)k / (float)p_78439_6_;
            f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
            tessellator.func_78374_a(1.0, (double)f7, 0.0, (double)p_78439_3_, (double)f8);
            tessellator.func_78374_a(0.0, (double)f7, 0.0, (double)p_78439_1_, (double)f8);
            tessellator.func_78374_a(0.0, (double)f7, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)f8);
            tessellator.func_78374_a(1.0, (double)f7, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)f8);
        }
        tessellator.func_78381_a();
    }
}

