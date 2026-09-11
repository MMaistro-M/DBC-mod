/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.data.BipedRotations;

@SideOnly(value=Side.CLIENT)
public class ModelMannequin
extends ModelBiped {
    private boolean slim = false;

    public ModelMannequin(boolean slim) {
        super(0.0f, 0.0f, 64, 32);
        this.slim = slim;
        this.field_78091_s = false;
        if (slim) {
            this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-2.0f, -1.5f, -2.0f, 3, 12, 4, 0.0f);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f, 0.0f);
            this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
            this.field_78113_g.field_78809_i = true;
            this.field_78113_g.func_78790_a(-1.0f, -1.5f, -2.0f, 3, 12, 4, 0.0f);
            this.field_78113_g.func_78793_a(5.0f, 2.0f, 0.0f);
        }
    }

    public boolean isSlim() {
        return this.slim;
    }

    private void resetRotationsOnPart(ModelRenderer mr) {
        mr.field_78795_f = 0.0f;
        mr.field_78796_g = 0.0f;
        mr.field_78808_h = 0.0f;
    }

    private void resetRotations() {
        this.resetRotationsOnPart(this.field_78116_c);
        this.resetRotationsOnPart(this.field_78114_d);
        this.resetRotationsOnPart(this.field_78115_e);
        this.resetRotationsOnPart(this.field_78113_g);
        this.resetRotationsOnPart(this.field_78112_f);
        this.resetRotationsOnPart(this.field_78124_i);
        this.resetRotationsOnPart(this.field_78123_h);
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale, boolean headOverlay) {
        this.resetRotations();
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, p_78088_1_);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.field_78116_c.func_78785_a(scale);
        this.field_78115_e.func_78785_a(scale);
        this.field_78112_f.func_78785_a(scale);
        this.field_78113_g.func_78785_a(scale);
        this.field_78123_h.func_78785_a(scale);
        this.field_78124_i.func_78785_a(scale);
        if (headOverlay) {
            GL11.glDisable((int)2884);
            this.field_78114_d.func_78785_a(scale);
            GL11.glEnable((int)2884);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void render(BipedRotations bipedRotations, boolean headOverlay, float scale) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        bipedRotations.applyRotationsToBiped(this);
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * scale), (float)0.0f);
            this.field_78116_c.func_78785_a(scale);
            if (headOverlay) {
                GL11.glDisable((int)2884);
                this.field_78114_d.func_78785_a(scale);
                GL11.glEnable((int)2884);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * scale), (float)0.0f);
            this.field_78115_e.func_78785_a(scale);
            this.field_78112_f.func_78785_a(scale);
            this.field_78113_g.func_78785_a(scale);
            this.field_78123_h.func_78785_a(scale);
            this.field_78124_i.func_78785_a(scale);
            GL11.glPopMatrix();
        } else {
            this.field_78116_c.func_78785_a(scale);
            this.field_78115_e.func_78785_a(scale);
            this.field_78112_f.func_78785_a(scale);
            this.field_78113_g.func_78785_a(scale);
            this.field_78123_h.func_78785_a(scale);
            this.field_78124_i.func_78785_a(scale);
            if (headOverlay) {
                GL11.glDisable((int)2884);
                this.field_78114_d.func_78785_a(scale);
                GL11.glEnable((int)2884);
            }
        }
    }
}

