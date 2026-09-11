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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JBRA.ModelRendererJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBipedBody
extends ModelBiped {
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_78112_f;
    public ModelRenderer field_78113_g;
    public ModelRenderer field_78123_h;
    public ModelRenderer field_78124_i;
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
    public float rot1;
    public float rot4;
    public float rot3;
    public float rot2;
    public float rot5;
    public float rot6;
    public Entity Entity;
    public static float f = 1.0f;
    public static int g = 1;
    public static int y = 1;
    public static int animation = 0;
    public static int p = 0;
    public ModelRenderer RA;
    public ModelRenderer LA;
    public ModelRenderer RL;
    public ModelRenderer LL;
    public ModelRenderer B;
    public ModelRenderer B1;
    public ModelRenderer B2;
    public ModelRenderer B3;
    public ModelRenderer B4;
    public ModelRenderer B5;
    public ModelRenderer B7;
    public ModelRenderer B9;
    public int b;
    public boolean blk = false;
    public boolean instantTransmission = false;
    public int KiAttack = 0;
    public static final int y_isFlying = 2;
    public static final int y_notFlying = 1;
    public static final int y_isKO = 3;
    public static final int y_isDodging1 = 4;
    public static final int y_isDodging2 = 5;
    public static final int y_isAttacking1 = 6;
    public static final int y_isAttacking2 = 7;

    public ModelBipedBody() {
        this(0.0f);
    }

    public ModelBipedBody(float par1) {
        this(par1, 0.0f, 64, 32);
    }

    public ModelBipedBody(float par1, float par2, int par3, int par4) {
        this.field_78090_t = par3;
        this.field_78089_u = par4;
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
        this.rot1 = par2;
        this.rot2 = par3;
        this.rot3 = par4;
        this.rot4 = par5;
        this.rot5 = par6;
        this.rot6 = par7;
        this.Entity = par1Entity;
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        this.renderBody(par7);
    }

    public void renderBody(float par7) {
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
            int b = 6;
            String[] s = JRMCoreH.data(this.Entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0").split(";");
            String dns = s[1];
            b = JRMCoreH.dnsBreast(dns);
            float scale = (float)b * 0.03f;
            float br = 0.4235988f + scale;
            float bs = 0.8f + scale;
            float bsY = 0.85f + scale * 0.5f;
            float bt = 0.1f * scale;
            boolean bl = bounce = this.Entity.field_70122_E || this.Entity.func_70090_H();
            float bspeed = this.Entity.func_70051_ag() ? 1.5f : (this.Entity.func_70093_af() ? 0.5f : 1.0f);
            float bbY = (bounce ? MathHelper.func_76126_a((float)(this.rot1 * 0.6662f * bspeed * 1.5f + (float)Math.PI)) * this.rot2 * 0.03f : 0.0f) * ((float)b * 0.1119f);
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f + bbY), (float)(0.015f + bt));
            GL11.glScalef((float)1.0f, (float)bsY, (float)bs);
            this.setRotation(this.breast, -br, 0.0f, 0.0f);
            this.setRotation(this.breast2, br, 3.141593f, 0.0f);
            if (bounce) {
                this.breast.field_78795_f += -MathHelper.func_76134_b((float)(this.rot1 * 0.6662f * bspeed + (float)Math.PI)) * this.rot2 * 0.05f * ((float)b * 0.1119f);
                this.breast.field_78796_g += MathHelper.func_76134_b((float)(this.rot1 * 0.6662f * bspeed + (float)Math.PI)) * this.rot2 * 0.02f * ((float)b * 0.1119f);
                this.breast2.field_78795_f += MathHelper.func_76134_b((float)(this.rot1 * 0.6662f * bspeed + (float)Math.PI)) * this.rot2 * 0.05f * ((float)b * 0.1119f);
                this.breast2.field_78796_g += MathHelper.func_76134_b((float)(this.rot1 * 0.6662f * bspeed + (float)Math.PI)) * this.rot2 * 0.02f * ((float)b * 0.1119f);
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
        boolean isClientPlayerInFPSView;
        EntityPlayer p = null;
        if (par7Entity instanceof EntityPlayer) {
            p = (EntityPlayer)par7Entity;
            if (par7Entity != JRMCoreClient.mc.field_71439_g || par7Entity == JRMCoreClient.mc.field_71439_g && JRMCoreClient.mc.field_71474_y.field_74320_O != 0) {
                ExtendedPlayer props = ExtendedPlayer.get(p);
                boolean block = props.getBlocking() == 1;
                boolean instantTransmissionOn = props.getBlocking() == 2;
                int kishoot = props.getAnimKiShoot();
                this.blk = block;
                this.instantTransmission = instantTransmissionOn;
                this.KiAttack = kishoot;
            } else {
                this.blk = false;
                this.instantTransmission = false;
                this.KiAttack = 0;
            }
        }
        int pwr = 0;
        if (p != null && JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && !p.func_82150_aj() && JRMCoreH.dnn(1)) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                if (!JRMCoreH.plyrs[pl].equals(p.func_70005_c_()) || JRMCoreH.data1.length < JRMCoreH.plyrs.length) continue;
                String[] s = JRMCoreH.data1[pl].split(";");
                pwr = Integer.parseInt(s[2]);
                break;
            }
        }
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
        if (y == 3) {
            p.field_70761_aq = 0.0f;
            this.field_78116_c.field_78795_f = -0.17453294f;
            this.field_78116_c.field_78796_g = -0.17453294f;
        }
        this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
        if (y == 4 || y == 5) {
            this.field_78116_c.field_78796_g = this.field_78116_c.field_78796_g + (y == 4 ? 0.8f : -0.8f);
            this.RA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
            this.LA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        } else if (y == 6 || y == 7) {
            this.field_78116_c.field_78796_g = this.field_78116_c.field_78796_g + (y == 6 ? 0.7f : -0.7f);
            float animation_helper = -0.7f + (50.0f - (float)animation) * 0.025f;
            float animation_extra = 0.4f - animation_helper;
            this.RA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
            this.LA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f - (y == 6 ? animation_extra : animation_extra + 0.3f);
        } else if (y == 3) {
            this.RA.field_78795_f = 0.0f;
            this.LA.field_78795_f = 0.0f;
            this.RA.field_78808_h = 0.2f;
            this.LA.field_78808_h = -0.2f;
        } else if (y == 1) {
            this.field_78116_c.field_78795_f = par5 / 57.295776f;
            if (pwr == 2 && par2 > 0.9f) {
                this.RA.field_78795_f = 0.7f;
                this.LA.field_78795_f = 0.7f;
            } else {
                this.RA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
                this.LA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
            }
        } else {
            this.field_78116_c.field_78795_f = -1.0471976f;
            this.RA.field_78795_f = 0.0f;
            this.LA.field_78795_f = 0.0f;
            this.RA.field_78808_h = 0.2f;
            this.LA.field_78808_h = -0.2f;
        }
        this.RA.field_78808_h = 0.0f;
        this.LA.field_78808_h = 0.0f;
        if (y == 4 || y == 5) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
            this.RL.field_78808_h = 0.0f;
            this.LL.field_78808_h = 0.0f;
        } else if (y == 6 || y == 7) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
            this.RL.field_78808_h = 0.2f;
            this.LL.field_78808_h = -0.2f;
        } else if (y == 3) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)0.0f) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)3.8077927f) * 1.4f * par2;
            this.RL.field_78808_h = 0.1f;
            this.LL.field_78808_h = -0.2f;
        } else if (y == 1) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
            this.RL.field_78808_h = 0.0f;
            this.LL.field_78808_h = 0.0f;
        } else {
            this.RL.field_78795_f = 0.0f;
            this.LL.field_78795_f = 0.0f;
            this.RL.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LL.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RL.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LL.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.RL.field_78808_h = 0.2f;
            this.LL.field_78808_h = -0.2f;
        }
        this.RL.field_78796_g = 0.0f;
        this.LL.field_78796_g = 0.0f;
        boolean bl = isClientPlayerInFPSView = par7Entity.func_70005_c_().equals(JRMCoreClient.mc.field_71439_g.func_70005_c_()) && JRMCoreClient.mc.field_71474_y.field_74320_O == 0;
        if (this.field_78093_q) {
            if (!isClientPlayerInFPSView) {
                this.RA.field_78795_f += -0.62831855f;
                this.LA.field_78795_f += -0.62831855f;
            }
            this.RL.field_78795_f = -1.2566371f;
            this.LL.field_78795_f = -1.2566371f;
            this.RL.field_78796_g = 0.31415927f;
            this.LL.field_78796_g = -0.31415927f;
        }
        if (this.field_78119_l != 0) {
            this.LA.field_78795_f = this.LA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78119_l;
        }
        if (!(this.field_78120_m == 0 || pwr == 2 && par2 > 0.9f)) {
            this.RA.field_78795_f = this.RA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78120_m;
        }
        this.RA.field_78796_g = 0.0f;
        this.LA.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float var8 = this.field_78095_p;
            if (pwr != 3 || this.field_78120_m <= 9) {
                this.B7.field_78796_g = this.B9.field_78796_g = (this.B.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var8) * (float)Math.PI * 2.0f)) * 0.2f);
                this.B5.field_78796_g = this.B9.field_78796_g;
                this.B4.field_78796_g = this.B9.field_78796_g;
                this.B3.field_78796_g = this.B9.field_78796_g;
                this.B2.field_78796_g = this.B9.field_78796_g;
                this.B1.field_78796_g = this.B9.field_78796_g;
            }
            this.RA.field_78798_e = MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78800_c = -MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78798_e = -MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78800_c = MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78796_g += this.B.field_78796_g;
            this.LA.field_78796_g += this.B.field_78796_g;
            var8 = 1.0f - this.field_78095_p;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            float var9 = MathHelper.func_76126_a((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.field_78116_c.field_78795_f - 0.7f) * 0.75f;
            if (pwr == 2 && par2 > 0.9f && var9 != 0.0f) {
                this.RA.field_78795_f = 0.0f;
                this.RA.field_78795_f = (float)((double)this.RA.field_78795_f - ((double)var9 * 1.2 + (double)var10));
                this.RA.field_78796_g += this.B.field_78796_g * 2.0f;
                this.RA.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
            } else {
                this.RA.field_78795_f = (float)((double)this.RA.field_78795_f - ((double)var9 * 1.2 + (double)var10));
                this.RA.field_78796_g += this.B.field_78796_g * 2.0f;
                if (y == 3) {
                    this.RA.field_78795_f = 0.0f;
                    this.LA.field_78795_f = 0.0f;
                    this.RA.field_78808_h = 0.5f;
                    this.LA.field_78808_h = -0.9f;
                } else if (y == 1) {
                    this.RA.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
                } else if (!isClientPlayerInFPSView) {
                    this.RA.field_78808_h = 0.2f;
                    this.LA.field_78808_h = -0.2f;
                }
            }
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
            this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d = 1.0f;
        } else if (pwr == 2 && par2 > 0.9f) {
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
            this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d = 1.0f;
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
            this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d = 0.0f;
        }
        this.field_78117_n = false;
        if (y != 3) {
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if (this.field_78118_o) {
            float var8 = 0.0f;
            float var9 = 0.0f;
            this.RA.field_78808_h = 0.0f;
            this.LA.field_78808_h = 0.0f;
            this.RA.field_78796_g = -(0.1f - var8 * 0.6f) + this.field_78116_c.field_78796_g;
            this.LA.field_78796_g = 0.1f - var8 * 0.6f + this.field_78116_c.field_78796_g + 0.4f;
            this.RA.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.LA.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.RA.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.LA.field_78795_f -= var8 * 1.2f - var9 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        this.field_78118_o = false;
        float f6 = 0.0f;
        float f7 = 0.0f;
        if (pwr == 3 && this.field_78120_m > 9) {
            float var8 = this.field_78095_p;
            float var10 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * 0.7f * 0.75f;
            var8 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var8) * (float)Math.PI * 2.0f));
            if (this.field_78120_m == 10) {
                this.RA.field_78808_h = 0.0f;
                this.LA.field_78808_h = 0.0f;
                this.RA.field_78796_g = -0.2f;
                this.LA.field_78796_g = 0.0f;
                this.RA.field_78795_f = -0.5f + (var10 != 0.0f ? -0.5f - var8 : 0.0f);
                this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.RA.field_78808_h -= 0.15f;
                this.LA.field_78808_h -= 0.25f;
            } else if (this.field_78120_m == 11) {
                this.B7.field_78796_g = this.B9.field_78796_g = (this.B.field_78796_g = -0.2f);
                this.B5.field_78796_g = this.B9.field_78796_g;
                this.B4.field_78796_g = this.B9.field_78796_g;
                this.B3.field_78796_g = this.B9.field_78796_g;
                this.B2.field_78796_g = this.B9.field_78796_g;
                this.B1.field_78796_g = this.B9.field_78796_g;
                this.RA.field_78808_h = 0.0f;
                this.LA.field_78808_h = -0.3f;
                this.RA.field_78796_g = 0.2f;
                this.LA.field_78796_g = 0.5f;
                this.RA.field_78795_f = -0.9f + (var10 != 0.0f ? var8 : 0.0f);
                this.LA.field_78795_f = -0.5f + (var10 != 0.0f ? var8 : 0.0f);
                this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.RA.field_78808_h -= 0.85f;
                this.LA.field_78808_h -= -0.15f;
            }
            if (this.field_78095_p > -9990.0f) {
                this.RA.field_78796_g += this.B.field_78796_g * 2.0f;
            }
        }
        if (this.field_78120_m == 0) {
            if (this.blk) {
                this.RA.field_78808_h = 0.0f;
                this.LA.field_78808_h = 0.0f;
                this.RA.field_78796_g = -(0.1f - f6 * 0.6f) + (this.field_78116_c.field_78796_g < -0.2f ? -0.2f : this.field_78116_c.field_78796_g) - 0.8f;
                this.LA.field_78796_g = 0.1f - f6 * 0.6f + (this.field_78116_c.field_78796_g > 0.2f ? 0.2f : this.field_78116_c.field_78796_g) + 0.8f;
                this.RA.field_78795_f = -1.5707964f + (this.field_78116_c.field_78795_f < -0.5f ? -0.5f : (this.field_78116_c.field_78795_f > 0.5f ? 0.5f : this.field_78116_c.field_78795_f));
                this.LA.field_78795_f = -1.5707964f + (this.field_78116_c.field_78795_f < -0.5f ? -0.5f : (this.field_78116_c.field_78795_f > 0.5f ? 0.5f : this.field_78116_c.field_78795_f));
                this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
                this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.45f;
                this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f - 0.55f;
                this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
                this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            } else if (this.instantTransmission) {
                this.RA.field_78808_h = 0.45f;
                this.RA.field_78796_g = -0.2f + (this.field_78116_c.field_78795_f > 0.0f ? -this.field_78116_c.field_78795_f * 0.3f : 0.0f);
                this.RA.field_78795_f = -2.5f + (this.field_78116_c.field_78795_f < -0.5f ? -0.5f : (this.field_78116_c.field_78795_f > 0.9f ? 0.9f : this.field_78116_c.field_78795_f));
            }
        }
        if ((this.KiAttack == 1 || this.KiAttack == 8 || this.KiAttack == 9) && this.field_78120_m == 0) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.RA.field_78808_h = 0.0f;
            this.LA.field_78808_h = 0.0f;
            this.RA.field_78796_g = -(0.1f - f6 * 0.6f) + (this.field_78116_c.field_78796_g < -0.2f ? -0.2f : this.field_78116_c.field_78796_g) - 0.5f;
            this.LA.field_78796_g = 0.1f - f6 * 0.6f + (this.field_78116_c.field_78796_g > 0.2f ? 0.2f : this.field_78116_c.field_78796_g) + 0.5f;
            this.RA.field_78795_f = -1.5707964f + (this.field_78116_c.field_78795_f < -0.5f ? -0.5f : (this.field_78116_c.field_78795_f > 0.5f ? 0.5f : this.field_78116_c.field_78795_f));
            this.LA.field_78795_f = -1.5707964f + (this.field_78116_c.field_78795_f < -0.5f ? -0.5f : (this.field_78116_c.field_78795_f > 0.5f ? 0.5f : this.field_78116_c.field_78795_f));
            this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if ((this.KiAttack == 2 || this.KiAttack == 5 || this.KiAttack == 4 || this.KiAttack == 7) && this.field_78120_m == 0) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.RA.field_78808_h = 0.0f;
            this.RA.field_78796_g = -(0.1f - f6 * 0.6f) + (this.field_78116_c.field_78796_g < -0.2f ? -0.2f : this.field_78116_c.field_78796_g) - 0.1f;
            this.RA.field_78795_f = -1.5707964f + this.field_78116_c.field_78795_f;
            this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if (this.KiAttack == 3 && this.field_78120_m == 0) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.RA.field_78808_h = -0.3f;
            this.RA.field_78795_f = -3.0f;
            this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if (this.KiAttack == 6 && this.field_78120_m == 0) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.RA.field_78808_h = -0.3f;
            this.RA.field_78795_f = -3.0f;
            this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.LA.field_78808_h = 0.3f;
            this.LA.field_78795_f = -3.0f;
            this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
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

    public ModelRendererJBRA getRandomModelBox2(Random p_85181_1_) {
        return (ModelRendererJBRA)this.field_78092_r.get(p_85181_1_.nextInt(this.field_78092_r.size()));
    }
}

