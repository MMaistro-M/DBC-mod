/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.npcs.client.model.util.ModelPlaneRenderer;
import org.lwjgl.opengl.GL11;

public class ModelSkirtArmor
extends ModelBiped {
    private ModelPlaneRenderer Shape1;

    public ModelSkirtArmor() {
        float pi = 0.62831855f;
        this.Shape1 = new ModelPlaneRenderer((ModelBase)this, 4, 20);
        this.Shape1.addSidePlane(0.0f, 0.0f, 0.0f, 9, 2);
        ModelPlaneRenderer part1 = new ModelPlaneRenderer((ModelBase)this, 6, 20);
        part1.addSidePlane(2.0f, 0.0f, 0.0f, 9, 2);
        part1.field_78796_g = -1.5707964f;
        this.Shape1.func_78792_a(part1);
        this.Shape1.func_78793_a(2.4f, 8.8f, 0.0f);
        this.setRotation(this.Shape1, 0.3f, -0.2f, -0.2f);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(this.field_78123_h.field_78798_e * par7));
        GL11.glScalef((float)1.6f, (float)1.04f, (float)1.6f);
        for (int i = 0; i < 10; ++i) {
            GL11.glRotatef((float)36.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.Shape1.func_78785_a(par7);
        }
        GL11.glPopMatrix();
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.setRotation(this.Shape1, 0.3f, -0.2f, -0.2f);
        this.field_78117_n = par7Entity.func_70093_af();
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        this.Shape1.field_78795_f += this.field_78113_g.field_78795_f * 0.02f;
        this.Shape1.field_78808_h += this.field_78113_g.field_78795_f * 0.06f;
        this.Shape1.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.02f - 0.05f;
    }
}

