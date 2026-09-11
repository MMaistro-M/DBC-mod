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
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBipedJFCA
extends ModelBiped {
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_78112_f;
    public ModelRenderer field_78113_g;
    public ModelRenderer field_78123_h;
    public ModelRenderer field_78124_i;
    public ModelRenderer field_78121_j;
    public ModelRenderer field_78122_k;
    public ModelRenderer leftarmshoulder;
    public ModelRenderer rightarmshoulder;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer Brightarm;
    public ModelRenderer Bleftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer skirt1;
    public ModelRenderer skirt2;
    public ModelRenderer hip;
    public ModelRenderer waist;
    public ModelRenderer Bbreast;
    public ModelRenderer breast;
    public ModelRenderer bottom;
    public ModelRenderer hip2;
    public ModelRenderer breast2;
    public ModelRenderer bottom2;
    public ModelRenderer Bbreast2;
    public int field_78119_l = 0;
    public int field_78120_m = 0;
    public boolean field_78117_n = false;
    public boolean field_78118_o = false;
    private String name;
    private String dns;
    private float age;
    public static float f = 1.0f;
    public static int g = 1;
    public static int y = 1;
    public static int p = 0;
    ModelRenderer RA;
    ModelRenderer LA;
    ModelRenderer RL;
    ModelRenderer LL;
    ModelRenderer B;
    ModelRenderer B1;
    ModelRenderer B2;
    ModelRenderer B3;
    ModelRenderer B4;
    ModelRenderer B5;
    ModelRenderer B7;
    ModelRenderer B9;
    public int b = 0;

    public ModelBipedJFCA() {
        this(0.0f);
    }

    public ModelBipedJFCA(float par1) {
        this(par1, 0.0f, 128, 64);
    }

    public ModelBipedJFCA(float par1, float par2, int par3, int par4) {
        this.field_78090_t = par3;
        this.field_78089_u = par4;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, par1);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, par1);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + par2, 0.0f);
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
        this.rightarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
        this.rightarmshoulder.func_78789_a(-6.0f, -4.0f, -3.0f, 7, 4, 6);
        this.rightarmshoulder.func_78793_a(-5.0f, 3.0f, 0.0f);
        this.rightarmshoulder.func_78787_b(128, 64);
        this.leftarmshoulder = new ModelRenderer((ModelBase)this, 40, 32);
        this.leftarmshoulder.field_78809_i = true;
        this.leftarmshoulder.func_78789_a(-1.0f, -4.0f, -3.0f, 7, 4, 6);
        this.leftarmshoulder.func_78793_a(5.0f, 3.0f, 0.0f);
        this.leftarmshoulder.func_78787_b(128, 64);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.rightarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.122173f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.leftarm, 0.0f, 0.0f, -0.122173f);
        this.Brightarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Brightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f);
        this.Brightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Bleftarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bleftarm.field_78809_i = true;
        this.Bleftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f);
        this.Bleftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.rightleg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.skirt1 = new ModelRenderer((ModelBase)this, 16, 18);
        this.skirt1.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 2, 4, par1 * 0.5f);
        this.skirt1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt1, 0.0f, 0.0f, 0.0f);
        this.skirt2 = new ModelRenderer((ModelBase)this, 16, 20);
        this.skirt2.func_78790_a(-4.0f, 11.0f, -2.0f, 8, 1, 4, par1 * 0.5f);
        this.skirt2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt2, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 4, 4, par1 * 0.5f);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.hip2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.hip2.func_78789_a(-4.0f, 7.0f, -2.0f, 8, 2, 4);
        this.hip2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hip2, 0.0f, 0.0f, 0.0f);
        this.hip = new ModelRenderer((ModelBase)this, 16, 23);
        this.hip.func_78790_a(-4.0f, 7.0f, -2.0f, 8, 2, 4, par1 * 0.5f);
        this.hip.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hip, 0.0f, 0.0f, 0.0f);
        this.waist = new ModelRenderer((ModelBase)this, 16, 20);
        this.waist.func_78790_a(-4.0f, 4.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
        this.waist.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.waist, 0.0f, 0.0f, 0.0f);
        this.Bbreast = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bbreast.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f);
        this.Bbreast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.breast = new ModelRenderer((ModelBase)this, 17, 18);
        this.breast.func_78790_a(-4.0f, 2.266667f, -1.0f, 8, 3, 3, par1 * 0.5f);
        this.breast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.breast, -0.5235988f, 0.0f, 0.0f);
        this.Bbreast2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bbreast2.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f);
        this.Bbreast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.breast2 = new ModelRenderer((ModelBase)this, 9, 23);
        this.breast2.field_78809_i = true;
        this.breast2.func_78790_a(-4.0f, 2.266667f, -2.0f, 8, 3, 3, par1 * 0.5f);
        this.breast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.breast2, 0.5235988f, 3.141593f, 0.0f);
        this.bottom2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.bottom2.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
        this.bottom2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.bottom2, 0.0f, 0.0f, 0.0f);
        this.bottom = new ModelRenderer((ModelBase)this, 16, 25);
        this.bottom.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
        this.bottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        this.Bbreast.func_78792_a(this.breast);
        this.Bbreast2.func_78792_a(this.breast2);
        this.Bleftarm.func_78792_a(this.leftarm);
        this.Brightarm.func_78792_a(this.rightarm);
    }

    public void rot(ModelRenderer var7, ModelRenderer var1) {
        var7.field_78795_f = var1.field_78795_f;
        var7.field_78796_g = var1.field_78796_g;
        var7.field_78808_h = var1.field_78808_h;
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (par1Entity instanceof EntityNPC) {
            EntityNPC e = (EntityNPC)par1Entity;
            this.dns = e.getDNS();
            g = JRMCoreH.dnsGender(this.dns) + 1;
            this.age = e.getNPCgrw();
            this.b = JRMCoreH.dnsBreast(this.dns);
            f = this.age;
        }
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        float f5 = par7;
        if (g <= 1) {
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
                this.leftarmshoulder.func_78785_a(f5);
                this.rightarmshoulder.func_78785_a(f5);
                GL11.glPopMatrix();
            } else {
                float f6 = f;
                GL11.glPushMatrix();
                GL11.glScalef((float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6));
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
                this.field_78116_c.func_78785_a(par7);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
                this.field_78115_e.func_78785_a(par7);
                this.field_78112_f.func_78785_a(par7);
                this.field_78113_g.func_78785_a(par7);
                this.field_78123_h.func_78785_a(par7);
                this.field_78124_i.func_78785_a(par7);
                this.leftarmshoulder.func_78785_a(f5);
                this.rightarmshoulder.func_78785_a(f5);
                GL11.glPopMatrix();
            }
        } else {
            boolean bounce;
            float f6 = f;
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.field_78116_c.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.Brightarm.func_78785_a(f5);
            this.Bleftarm.func_78785_a(f5);
            this.leftarmshoulder.func_78785_a(f5);
            this.rightarmshoulder.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.775f)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)-0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.0f);
            } else {
                GL11.glTranslatef((float)-0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.015f);
            }
            this.rightleg.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.775f)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.0f);
            } else {
                GL11.glTranslatef((float)0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.015f);
            }
            this.leftleg.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.675f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.8f)));
            float scale = (float)this.b * 0.03f;
            float br = 0.4235988f + scale;
            float bs = 0.8f + scale;
            float bsY = 0.85f + scale * 0.5f;
            float bt = 0.1f * scale;
            boolean bl = bounce = par1Entity.field_70122_E || par1Entity.func_70090_H();
            float bspeed = par1Entity.func_70051_ag() ? 1.5f : (par1Entity.func_70093_af() ? 0.5f : 1.0f);
            float bbY = (bounce ? MathHelper.func_76126_a((float)(par2 * 0.6662f * bspeed * 1.5f + (float)Math.PI)) * par3 * 0.03f : 0.0f) * ((float)this.b * 0.1119f);
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f + bbY), (float)(0.015f + bt));
            GL11.glScalef((float)1.0f, (float)bsY, (float)bs);
            this.setRotation(this.breast, -br, 0.0f, 0.0f);
            this.setRotation(this.breast2, br, 3.141593f, 0.0f);
            if (bounce) {
                this.breast.field_78795_f += -MathHelper.func_76134_b((float)(par2 * 0.6662f * bspeed + (float)Math.PI)) * par3 * 0.05f * ((float)this.b * 0.1119f);
                this.breast.field_78796_g += MathHelper.func_76134_b((float)(par2 * 0.6662f * bspeed + (float)Math.PI)) * par3 * 0.02f * ((float)this.b * 0.1119f);
                this.breast2.field_78795_f += MathHelper.func_76134_b((float)(par2 * 0.6662f * bspeed + (float)Math.PI)) * par3 * 0.05f * ((float)this.b * 0.1119f);
                this.breast2.field_78796_g += MathHelper.func_76134_b((float)(par2 * 0.6662f * bspeed + (float)Math.PI)) * par3 * 0.02f * ((float)this.b * 0.1119f);
            }
            this.Bbreast.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.body.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.75f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.75f) * (1.0f + 0.005f * (float)p)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(-0.02f - 5.0E-4f * (float)p));
            }
            this.hip.func_78785_a(f5);
            GL11.glPopMatrix();
            if (p >= 30) {
                // empty if block
            }
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.65f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.65f) * (1.0f + 0.001f * (float)p)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(-0.04f - 1.0E-4f * (float)p));
            }
            this.waist.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f) * (1.0f + 0.005f * (float)p)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(0.0f - 5.0E-4f * (float)p));
            this.bottom.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.675f) - 0.001f), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.8f) - 0.001f));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f + 0.001f + bbY), (float)(0.015f + bt));
            GL11.glScalef((float)1.0f, (float)bsY, (float)bs);
            this.Bbreast2.func_78785_a(f5);
            GL11.glPopMatrix();
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float var9;
        float var8;
        if (g >= 2) {
            this.RA = this.Brightarm;
            this.LA = this.Bleftarm;
            this.RL = this.rightleg;
            this.LL = this.leftleg;
            this.B = this.Bbreast;
            this.B1 = this.body;
            this.B2 = this.hip;
            this.B3 = this.waist;
            this.B4 = this.bottom;
            this.B5 = this.Bbreast2;
            this.B7 = this.hip2;
            this.B9 = this.bottom2;
        } else {
            this.RA = this.field_78112_f;
            this.LA = this.field_78113_g;
            this.RL = this.field_78123_h;
            this.LL = this.field_78124_i;
            this.B7 = this.B9 = this.field_78115_e;
            this.B5 = this.B9;
            this.B4 = this.B9;
            this.B3 = this.B9;
            this.B2 = this.B9;
            this.B1 = this.B9;
            this.B = this.B9;
        }
        this.field_78116_c.field_78796_g = par4 / 57.295776f;
        this.field_78116_c.field_78795_f = par5 / 57.295776f;
        if (y == 1) {
            this.RA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
            this.LA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        } else {
            this.RA.field_78795_f = 0.0f;
            this.LA.field_78795_f = 0.0f;
        }
        this.RA.field_78808_h = 0.0f;
        this.LA.field_78808_h = 0.0f;
        if (y == 1) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        } else {
            this.RL.field_78795_f = 0.0f;
            this.LL.field_78795_f = 0.0f;
        }
        this.RL.field_78796_g = 0.0f;
        this.LL.field_78796_g = 0.0f;
        this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
        this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
        this.rightarmshoulder.field_78808_h = this.RA.field_78808_h;
        this.leftarmshoulder.field_78808_h = this.LA.field_78808_h;
        if (this.field_78093_q) {
            this.RA.field_78795_f += -0.62831855f;
            this.LA.field_78795_f += -0.62831855f;
            this.RL.field_78795_f = -1.2566371f;
            this.LL.field_78795_f = -1.2566371f;
            this.RL.field_78796_g = 0.31415927f;
            this.LL.field_78796_g = -0.31415927f;
            this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
            this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
        }
        if (this.field_78119_l != 0) {
            this.LA.field_78795_f = this.LA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78119_l;
        }
        if (this.field_78120_m != 0) {
            this.RA.field_78795_f = this.RA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78120_m;
        }
        this.RA.field_78796_g = 0.0f;
        this.LA.field_78796_g = 0.0f;
        this.rightarmshoulder.field_78796_g = this.RA.field_78796_g;
        this.leftarmshoulder.field_78796_g = this.LA.field_78796_g;
        if (this.field_78095_p > -9990.0f) {
            var8 = this.field_78095_p;
            this.B7.field_78796_g = this.B9.field_78796_g = (this.B.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var8) * (float)Math.PI * 2.0f)) * 0.2f);
            this.B5.field_78796_g = this.B9.field_78796_g;
            this.B4.field_78796_g = this.B9.field_78796_g;
            this.B3.field_78796_g = this.B9.field_78796_g;
            this.B2.field_78796_g = this.B9.field_78796_g;
            this.B1.field_78796_g = this.B9.field_78796_g;
            this.RA.field_78798_e = MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78800_c = -MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78798_e = -MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78800_c = MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78796_g += this.B.field_78796_g;
            this.LA.field_78796_g += this.B.field_78796_g;
            this.LA.field_78795_f += this.B.field_78796_g;
            this.rightarmshoulder.field_78798_e = MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.rightarmshoulder.field_78800_c = -MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.leftarmshoulder.field_78798_e = -MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.leftarmshoulder.field_78800_c = MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.rightarmshoulder.field_78796_g += this.B.field_78796_g;
            this.leftarmshoulder.field_78796_g += this.B.field_78796_g;
            this.leftarmshoulder.field_78795_f += this.B.field_78796_g;
            var8 = 1.0f - this.field_78095_p;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            var9 = MathHelper.func_76126_a((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.field_78116_c.field_78795_f - 0.7f) * 0.75f;
            this.RA.field_78795_f = (float)((double)this.RA.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.RA.field_78796_g += this.B.field_78796_g * 2.0f;
            this.RA.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
            this.rightarmshoulder.field_78795_f = (float)((double)this.RA.field_78795_f - ((double)var9 * 1.2 + (double)var10));
            this.rightarmshoulder.field_78796_g += this.B.field_78796_g * 2.0f;
            this.rightarmshoulder.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.field_78117_n) {
            this.B7.field_78795_f = this.B9.field_78795_f = (this.B.field_78795_f = 0.5f);
            this.B5.field_78795_f = this.B9.field_78795_f;
            this.B4.field_78795_f = this.B9.field_78795_f;
            this.B3.field_78795_f = this.B9.field_78795_f;
            this.B2.field_78795_f = this.B9.field_78795_f;
            this.B1.field_78795_f = this.B9.field_78795_f;
            this.RA.field_78795_f += 0.4f;
            this.LA.field_78795_f += 0.4f;
            this.RL.field_78798_e = 4.0f;
            this.LL.field_78798_e = 4.0f;
            this.RL.field_78797_d = 9.0f;
            this.LL.field_78797_d = 9.0f;
            this.field_78116_c.field_78797_d = 1.0f;
            this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
            this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
        } else {
            this.B7.field_78795_f = this.B9.field_78795_f = (this.B.field_78795_f = 0.0f);
            this.B5.field_78795_f = this.B9.field_78795_f;
            this.B4.field_78795_f = this.B9.field_78795_f;
            this.B3.field_78795_f = this.B9.field_78795_f;
            this.B2.field_78795_f = this.B9.field_78795_f;
            this.B1.field_78795_f = this.B9.field_78795_f;
            this.RL.field_78798_e = 0.1f;
            this.LL.field_78798_e = 0.1f;
            this.RL.field_78797_d = 12.0f;
            this.LL.field_78797_d = 12.0f;
            this.field_78116_c.field_78797_d = 0.0f;
        }
        this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.rightarmshoulder.field_78808_h = this.RA.field_78808_h;
        this.leftarmshoulder.field_78808_h = this.LA.field_78808_h;
        this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
        this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
        if (this.field_78118_o) {
            var8 = 0.0f;
            var9 = 0.0f;
            this.RA.field_78808_h = 0.0f;
            this.LA.field_78808_h = 0.0f;
            this.rightarmshoulder.field_78808_h = this.RA.field_78808_h;
            this.leftarmshoulder.field_78808_h = this.LA.field_78808_h;
            this.RA.field_78796_g = -(0.1f - var8 * 0.6f) + this.field_78116_c.field_78796_g;
            this.LA.field_78796_g = 0.1f - var8 * 0.6f + this.field_78116_c.field_78796_g + 0.4f;
            this.RA.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.LA.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.RA.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.LA.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
            this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.rightarmshoulder.field_78796_g = this.RA.field_78796_g;
            this.leftarmshoulder.field_78796_g = this.LA.field_78796_g;
            this.rightarmshoulder.field_78808_h = this.RA.field_78808_h;
            this.leftarmshoulder.field_78808_h = this.LA.field_78808_h;
            this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
            this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
        }
        this.field_78118_o = false;
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

    public void renderHairs(float par1, String hair) {
        float f6 = f;
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        if (hair.contains("SC")) {
            this.field_78116_c.field_78796_g = this.field_78116_c.field_78796_g;
            this.field_78116_c.field_78795_f = this.field_78116_c.field_78795_f;
            this.field_78116_c.field_78800_c = this.field_78116_c.field_78800_c;
            this.field_78116_c.field_78797_d = this.field_78116_c.field_78797_d;
            this.field_78116_c.func_78785_a(par1);
        }
        GL11.glPopMatrix();
    }
}

