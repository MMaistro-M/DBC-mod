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
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelNPCNormal
extends ModelBiped {
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78114_d;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_78112_f;
    public ModelRenderer field_78113_g;
    public ModelRenderer field_78123_h;
    public ModelRenderer field_78124_i;
    public ModelRenderer field_78121_j;
    public ModelRenderer field_78122_k;
    public int field_78119_l = 0;
    public int field_78120_m = 0;
    public boolean field_78117_n = false;
    public boolean field_78118_o = false;

    public ModelNPCNormal() {
        this(0.0f);
    }

    public ModelNPCNormal(float par1) {
        this(par1, 0.0f, 64, 32);
    }

    public ModelNPCNormal(float par1, float par2, int par3, int par4) {
        this.field_78090_t = par3;
        this.field_78089_u = par4;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, par1);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, par1);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78112_f.func_78793_a(-5.0f, 2.0f + par2, 0.0f);
        this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78113_g.func_78793_a(5.0f, 2.0f + par2, 0.0f);
        this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78123_h.func_78793_a(-1.9f, 12.0f + par2, 0.0f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par2, 0.0f);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        if (this.field_78091_s) {
            float var8 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / var8), (float)(1.5f / var8), (float)(1.5f / var8));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * par7), (float)0.0f);
            this.field_78116_c.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / var8), (float)(1.0f / var8), (float)(1.0f / var8));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * par7), (float)0.0f);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78116_c.func_78785_a(par7);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float var9;
        float var8;
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
            var8 = this.field_78095_p;
            this.field_78115_e.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var8) * (float)Math.PI * 2.0f)) * 0.2f;
            this.field_78112_f.field_78798_e = MathHelper.func_76126_a((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78112_f.field_78800_c = -MathHelper.func_76134_b((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78113_g.field_78798_e = -MathHelper.func_76126_a((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78113_g.field_78800_c = MathHelper.func_76134_b((float)this.field_78115_e.field_78796_g) * 5.0f;
            this.field_78112_f.field_78796_g += this.field_78115_e.field_78796_g;
            this.field_78113_g.field_78796_g += this.field_78115_e.field_78796_g;
            this.field_78113_g.field_78795_f += this.field_78115_e.field_78796_g;
            var8 = 1.0f - this.field_78095_p;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            var9 = MathHelper.func_76126_a((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.field_78116_c.field_78795_f - 0.7f) * 0.75f;
            this.field_78112_f.field_78795_f = (float)((double)this.field_78112_f.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.field_78112_f.field_78796_g += this.field_78115_e.field_78796_g * 2.0f;
            this.field_78112_f.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.field_78117_n) {
            this.field_78115_e.field_78795_f = 0.5f;
            this.field_78112_f.field_78795_f += 0.4f;
            this.field_78113_g.field_78795_f += 0.4f;
            this.field_78123_h.field_78798_e = 4.0f;
            this.field_78124_i.field_78798_e = 4.0f;
            this.field_78123_h.field_78797_d = 9.0f;
            this.field_78124_i.field_78797_d = 9.0f;
            this.field_78116_c.field_78797_d = 1.0f;
            this.field_78114_d.field_78797_d = 1.0f;
        } else {
            this.field_78115_e.field_78795_f = 0.0f;
            this.field_78123_h.field_78798_e = 0.1f;
            this.field_78124_i.field_78798_e = 0.1f;
            this.field_78123_h.field_78797_d = 12.0f;
            this.field_78124_i.field_78797_d = 12.0f;
            this.field_78116_c.field_78797_d = 0.0f;
            this.field_78114_d.field_78797_d = 0.0f;
        }
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        if (this.field_78118_o) {
            var8 = 0.0f;
            var9 = 0.0f;
            this.field_78112_f.field_78808_h = 0.0f;
            this.field_78113_g.field_78808_h = 0.0f;
            this.field_78112_f.field_78796_g = -(0.1f - var8 * 0.6f) + this.field_78116_c.field_78796_g;
            this.field_78113_g.field_78796_g = 0.1f - var8 * 0.6f + this.field_78116_c.field_78796_g + 0.4f;
            this.field_78112_f.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.field_78113_g.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.field_78112_f.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.field_78113_g.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
    }

    public void func_78110_b(float par1) {
        this.field_78121_j.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78121_j.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78121_j.field_78800_c = 0.0f;
        this.field_78121_j.field_78797_d = 0.0f;
        this.field_78121_j.func_78785_a(par1);
    }

    public void func_78111_c(float par1) {
        this.field_78122_k.func_78785_a(par1);
    }
}

