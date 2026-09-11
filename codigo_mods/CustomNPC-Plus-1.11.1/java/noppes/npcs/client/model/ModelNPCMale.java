/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.model.util.ModelScaleRenderer;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class ModelNPCMale
extends ModelBiped {
    public boolean isDancing;
    public boolean isSleeping;
    public float animationTick;
    public float dancingTicks;
    public float invisible;

    public ModelNPCMale(float f) {
        this.init(f, 0.0f);
    }

    public ModelNPCMale(float f, boolean alex) {
        super(f, 0.0f, 64, 64);
        this.init(f, 0.0f, alex);
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float f6, float f5, float par9) {
        this.animationTick += par9;
        this.dancingTicks = (float)CustomNpcs.ticks / 3.978873f;
    }

    public void init(float f, float f1, boolean arms) {
        this.field_78119_l = 0;
        this.field_78120_m = 0;
        this.field_78117_n = false;
        this.field_78118_o = false;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78787_b(64, 32);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, f);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78787_b(64, 32);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, f);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        if (arms) {
            this.field_78112_f = new ModelScaleRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-2.0f, -2.0f, -2.0f, 3, 12, 4, f);
            this.field_78112_f.func_78793_a(-5.0f, 2.5f + f1, 0.0f);
            this.field_78113_g = new ModelScaleRenderer((ModelBase)this, 32, 48);
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 3, 12, 4, f);
            this.field_78113_g.func_78793_a(5.0f, 2.5f + f1, 0.0f);
        } else {
            this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
            this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f + f1, 0.0f);
            this.field_78113_g = new ModelRenderer((ModelBase)this, 32, 48);
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
            this.field_78113_g.func_78793_a(5.0f, 2.0f + f1, 0.0f);
        }
        this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.field_78123_h.func_78793_a(-2.0f, 12.0f + f1, 0.0f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 16, 48);
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + f1, 0.0f);
    }

    public void init(float f, float f1) {
        this.field_78119_l = 0;
        this.field_78120_m = 0;
        this.field_78117_n = false;
        this.field_78118_o = false;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.field_78799_b = 32.0f;
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, f);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, f);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + f1, 0.0f);
        this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        this.field_78112_f.func_78793_a(-5.0f, 2.0f + f1, 0.0f);
        this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        this.field_78113_g.func_78793_a(5.0f, 2.0f + f1, 0.0f);
        this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.field_78123_h.func_78793_a(-2.0f, 12.0f + f1, 0.0f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.field_78124_i.func_78793_a(2.0f, 12.0f + f1, 0.0f);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        if (!this.isDancing) {
            this.field_78116_c.func_78785_a(par7);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
        } else {
            this.renderHead(par1Entity, par7);
            this.renderArms(par1Entity, par7);
            this.renderBody(par1Entity, par7);
            this.renderLegs(par1Entity, par7);
        }
    }

    public void renderHead(Entity entityliving, float par7) {
        if (this.isDancing) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)Math.sin(this.dancingTicks) * 0.075f), (float)((float)Math.abs(Math.cos(this.dancingTicks)) * 0.125f - 0.02f), (float)((float)(-Math.abs(Math.cos(this.dancingTicks))) * 0.075f));
            this.field_78116_c.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78116_c.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
        }
    }

    public void renderLeftArm(Entity entityliving, float par7) {
        if (this.isDancing) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)Math.sin(this.dancingTicks) * 0.025f), (float)((float)Math.abs(Math.cos(this.dancingTicks)) * 0.125f - 0.02f), (float)0.0f);
            this.field_78113_g.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78113_g.func_78785_a(par7);
        }
    }

    public void renderArms(Entity entity, float par7) {
        this.renderLeftArm(entity, par7);
        this.renderRightArm(entity, par7);
    }

    public void renderRightArm(Entity entityliving, float par7) {
        if (this.isDancing) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)Math.sin(this.dancingTicks) * 0.025f), (float)((float)Math.abs(Math.cos(this.dancingTicks)) * 0.125f - 0.02f), (float)0.0f);
            this.field_78112_f.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78112_f.func_78785_a(par7);
        }
    }

    public void renderBody(Entity entityliving, float par7) {
        if (this.isDancing) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)Math.sin(this.dancingTicks) * 0.015f), (float)0.0f, (float)0.0f);
            this.field_78115_e.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78115_e.func_78785_a(par7);
        }
    }

    public void renderLegs(Entity entityliving, float par7) {
        this.field_78123_h.func_78785_a(par7);
        this.field_78124_i.func_78785_a(par7);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        EntityNPCInterface npc = (EntityNPCInterface)entity;
        this.field_78093_q = npc.func_70115_ae();
        if (this.field_78117_n && (npc.currentAnimation == EnumAnimation.CRAWLING || npc.currentAnimation == EnumAnimation.LYING)) {
            this.field_78117_n = false;
        }
        this.field_78116_c.field_78796_g = par4 / 57.295776f;
        this.field_78116_c.field_78795_f = par5 / 57.295776f;
        this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78112_f.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.field_78113_g.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.field_78112_f.field_78808_h = 0.0f;
        this.field_78113_g.field_78808_h = 0.0f;
        this.field_78123_h.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.field_78124_i.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.field_78123_h.field_78796_g = 0.0f;
        this.field_78124_i.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.field_78112_f.field_78795_f += -0.62831855f;
            this.field_78113_g.field_78795_f += -0.62831855f;
            this.field_78123_h.field_78795_f = -1.2566371f;
            this.field_78124_i.field_78795_f = -1.2566371f;
            this.field_78123_h.field_78796_g = 0.31415927f;
            this.field_78124_i.field_78796_g = -0.31415927f;
        }
        if (this.field_78119_l != 0) {
            this.field_78113_g.field_78795_f = this.field_78113_g.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78119_l;
        }
        if (this.field_78120_m != 0) {
            this.field_78112_f.field_78795_f = this.field_78112_f.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78120_m;
        }
        this.field_78112_f.field_78796_g = 0.0f;
        this.field_78113_g.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float f = this.field_78095_p;
            this.field_78115_e.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f) * (float)Math.PI * 2.0f)) * 0.2f;
            this.field_78112_f.field_78798_e = MathHelper.func_76126_a((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78112_f.field_78800_c = -MathHelper.func_76134_b((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78113_g.field_78798_e = -MathHelper.func_76126_a((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78113_g.field_78800_c = MathHelper.func_76134_b((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78112_f.field_78796_g += this.field_78115_e.field_78796_g;
            this.field_78113_g.field_78796_g += this.field_78115_e.field_78796_g;
            this.field_78113_g.field_78795_f += this.field_78115_e.field_78796_g;
            f = 1.0f - this.field_78095_p;
            f *= f;
            f *= f;
            f = 1.0f - f;
            float f2 = MathHelper.func_76126_a((float)(f * (float)Math.PI));
            float f4 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.field_78116_c.field_78795_f - 0.7f) * 0.75f;
            this.field_78112_f.field_78795_f = (float)((double)this.field_78112_f.field_78795_f - ((double)f2 * 1.2 + (double)f4));
            this.field_78112_f.field_78796_g += this.field_78115_e.field_78796_g * 2.0f;
            this.field_78112_f.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.field_78117_n) {
            this.field_78115_e.field_78795_f = 0.5f;
            this.field_78123_h.field_78795_f -= 0.0f;
            this.field_78124_i.field_78795_f -= 0.0f;
            this.field_78112_f.field_78795_f += 0.4f;
            this.field_78113_g.field_78795_f += 0.4f;
            this.field_78123_h.field_78798_e = 4.0f;
            this.field_78124_i.field_78798_e = 4.0f;
            this.field_78123_h.field_78797_d = 9.0f;
            this.field_78124_i.field_78797_d = 9.0f;
            this.field_78116_c.field_78797_d = 1.0f;
        } else {
            this.field_78115_e.field_78795_f = 0.0f;
            this.field_78123_h.field_78798_e = 0.0f;
            this.field_78124_i.field_78798_e = 0.0f;
            this.field_78123_h.field_78797_d = 12.0f;
            this.field_78124_i.field_78797_d = 12.0f;
            this.field_78116_c.field_78797_d = 0.0f;
        }
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        if (this.field_78118_o) {
            float f1 = 0.0f;
            float f3 = 0.0f;
            this.field_78112_f.field_78808_h = 0.0f;
            this.field_78113_g.field_78808_h = 0.0f;
            this.field_78112_f.field_78796_g = -(0.1f - f1 * 0.6f) + this.field_78116_c.field_78796_g;
            this.field_78113_g.field_78796_g = 0.1f - f1 * 0.6f + this.field_78116_c.field_78796_g + 0.4f;
            this.field_78112_f.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.field_78113_g.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.field_78112_f.field_78795_f -= f1 * 1.2f - f3 * 0.4f;
            this.field_78113_g.field_78795_f -= f1 * 1.2f - f3 * 0.4f;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
    }

    public void func_78110_b(float f) {
        this.field_78121_j.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78121_j.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78121_j.field_78800_c = 0.0f;
        this.field_78121_j.field_78797_d = 0.0f;
        this.field_78121_j.func_78785_a(f);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78111_c(float f) {
        this.field_78122_k.func_78785_a(f);
    }

    public boolean isSleeping(Entity entity) {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).func_70608_bn()) {
            return true;
        }
        return ((EntityNPCInterface)entity).currentAnimation == EnumAnimation.LYING;
    }
}

