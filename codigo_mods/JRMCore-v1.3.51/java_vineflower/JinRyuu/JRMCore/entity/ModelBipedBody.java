package JinRyuu.JRMCore.entity;

import JinRyuu.JBRA.ModelRendererJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelBipedBody extends ModelBiped {
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
   public int field_78119_l;
   public int field_78120_m;
   public boolean field_78117_n;
   public boolean field_78118_o;
   public float rot1;
   public float rot4;
   public float rot3;
   public float rot2;
   public float rot5;
   public float rot6;
   public Entity Entity;
   public static float f = 1.0F;
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
      this(0.0F);
   }

   public ModelBipedBody(float par1) {
      this(par1, 0.0F, 64, 32);
   }

   public ModelBipedBody(float par1, float par2, int par3, int par4) {
      this.field_78119_l = 0;
      this.field_78120_m = 0;
      this.field_78117_n = false;
      this.field_78118_o = false;
      this.field_78090_t = par3;
      this.field_78089_u = par4;
      this.field_78116_c = new ModelRenderer(this, 0, 0);
      this.field_78116_c.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
      this.field_78116_c.func_78793_a(0.0F, 0.0F + par2, 0.0F);
      this.field_78115_e = new ModelRenderer(this, 16, 16);
      this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
      this.field_78115_e.func_78793_a(0.0F, 0.0F + par2, 0.0F);
      this.field_78112_f = new ModelRenderer(this, 40, 16);
      this.field_78112_f.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
      this.field_78112_f.func_78793_a(-5.0F, 2.0F + par2, 0.0F);
      this.field_78113_g = new ModelRenderer(this, 40, 16);
      this.field_78113_g.field_78809_i = true;
      this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
      this.field_78113_g.func_78793_a(5.0F, 2.0F + par2, 0.0F);
      this.field_78123_h = new ModelRenderer(this, 0, 16);
      this.field_78123_h.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
      this.field_78123_h.func_78793_a(-1.9F, 12.0F + par2, 0.0F);
      this.field_78124_i = new ModelRenderer(this, 0, 16);
      this.field_78124_i.field_78809_i = true;
      this.field_78124_i.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
      this.field_78124_i.func_78793_a(1.9F, 12.0F + par2, 0.0F);
      this.rightarm = new ModelRenderer(this, 40, 16);
      this.rightarm.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1 * 0.5F);
      this.rightarm.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.rightarm, 0.0F, 0.0F, 0.122173F);
      this.leftarm = new ModelRenderer(this, 40, 16);
      this.leftarm.field_78809_i = true;
      this.leftarm.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1 * 0.5F);
      this.leftarm.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.leftarm, 0.0F, 0.0F, -0.122173F);
      this.Brightarm = new ModelRenderer(this, 0, 0);
      this.Brightarm.func_78790_a(-3.0F, -2.0F, -2.0F, 0, 0, 0, par1 * 0.5F);
      this.Brightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
      this.Bleftarm = new ModelRenderer(this, 0, 0);
      this.Bleftarm.field_78809_i = true;
      this.Bleftarm.func_78790_a(-1.0F, -2.0F, -2.0F, 0, 0, 0, par1 * 0.5F);
      this.Bleftarm.func_78793_a(5.0F, 2.0F, 0.0F);
      this.rightleg = new ModelRenderer(this, 0, 16);
      this.rightleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1 * 0.5F);
      this.rightleg.func_78793_a(-2.0F, 12.0F, 0.0F);
      this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
      this.leftleg = new ModelRenderer(this, 0, 16);
      this.leftleg.field_78809_i = true;
      this.leftleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1 * 0.5F);
      this.leftleg.func_78793_a(2.0F, 12.0F, 0.0F);
      this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
      this.skirt1 = new ModelRenderer(this, 16, 18);
      this.skirt1.func_78790_a(-4.0F, 9.0F, -2.0F, 8, 2, 4, par1 * 0.5F);
      this.skirt1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.skirt1, 0.0F, 0.0F, 0.0F);
      this.skirt2 = new ModelRenderer(this, 16, 20);
      this.skirt2.func_78790_a(-4.0F, 11.0F, -2.0F, 8, 1, 4, par1 * 0.5F);
      this.skirt2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.skirt2, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 16, 16);
      this.body.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 4, 4, par1 * 0.5F);
      this.body.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.hip2 = new ModelRenderer(this, 16, 16);
      this.hip2.func_78789_a(-4.0F, 7.0F, -2.0F, 8, 2, 4);
      this.hip2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.hip2, 0.0F, 0.0F, 0.0F);
      this.hip = new ModelRenderer(this, 16, 23);
      this.hip.func_78790_a(-4.0F, 7.0F, -2.0F, 8, 2, 4, par1 * 0.5F);
      this.hip.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.hip, 0.0F, 0.0F, 0.0F);
      this.waist = new ModelRenderer(this, 16, 20);
      this.waist.func_78790_a(-4.0F, 4.0F, -2.0F, 8, 3, 4, par1 * 0.5F);
      this.waist.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.waist, 0.0F, 0.0F, 0.0F);
      this.Bbreast = new ModelRenderer(this, 0, 0);
      this.Bbreast.func_78790_a(-4.0F, 2.266667F, -1.0F, 0, 0, 0, par1 * 0.5F);
      this.Bbreast.func_78793_a(0.0F, 0.0F, 0.0F);
      this.breast = new ModelRenderer(this, 17, 18);
      this.breast.func_78790_a(-4.0F, 2.266667F, -1.0F, 8, 3, 3, par1 * 0.5F);
      this.breast.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.breast, (float) (-Math.PI / 6), 0.0F, 0.0F);
      this.Bbreast2 = new ModelRenderer(this, 0, 0);
      this.Bbreast2.func_78790_a(-4.0F, 2.266667F, -1.0F, 0, 0, 0, par1 * 0.5F);
      this.Bbreast2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.breast2 = new ModelRenderer(this, 9, 23);
      this.breast2.field_78809_i = true;
      this.breast2.func_78790_a(-4.0F, 2.266667F, -2.0F, 8, 3, 3, par1 * 0.5F);
      this.breast2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.breast2, (float) (Math.PI / 6), 3.141593F, 0.0F);
      this.bottom2 = new ModelRenderer(this, 16, 16);
      this.bottom2.func_78790_a(-4.0F, 9.0F, -2.0F, 8, 3, 4, par1 * 0.5F);
      this.bottom2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.bottom2, 0.0F, 0.0F, 0.0F);
      this.bottom = new ModelRenderer(this, 16, 25);
      this.bottom.func_78790_a(-4.0F, 9.0F, -2.0F, 8, 3, 4, par1 * 0.5F);
      this.bottom.func_78793_a(0.0F, 0.0F, 0.0F);
      this.setRotation(this.bottom, 0.0F, 0.0F, 0.0F);
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
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
            GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
            this.field_78116_c.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            GL11.glPopMatrix();
         } else {
            float f6 = f;
            GL11.glPushMatrix();
            GL11.glScalef(0.5F + 0.5F / f6, 0.5F + 0.5F / f6, 0.5F + 0.5F / f6);
            GL11.glTranslatef(
               0.0F,
               (f6 - 1.0F) / f6 * (2.0F - (f6 >= 1.5F && f6 <= 2.0F ? (2.0F - f6) / 2.5F : (f6 < 1.5F && f6 >= 1.0F ? (f6 * 2.0F - 2.0F) * 0.2F : 0.0F))),
               0.0F
            );
            this.field_78116_c.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            GL11.glPopMatrix();
         }
      } else {
         float f6 = f;
         GL11.glPushMatrix();
         GL11.glScalef((0.5F + 0.5F / f6) * (g <= 1 ? 1.0F : 0.85F), 0.5F + 0.5F / f6, (0.5F + 0.5F / f6) * (g <= 1 ? 1.0F : 0.85F));
         GL11.glTranslatef(
            0.0F,
            (f6 - 1.0F) / f6 * (2.0F - (f6 >= 1.5F && f6 <= 2.0F ? (2.0F - f6) / 2.5F : (f6 < 1.5F && f6 >= 1.0F ? (f6 * 2.0F - 2.0F) * 0.2F : 0.0F))),
            0.0F
         );
         this.field_78116_c.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.7F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.7F));
         GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F);
         this.Brightarm.func_78785_a(f5);
         this.Bleftarm.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.85F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.775F));
         if (this.field_78117_n) {
            GL11.glTranslatef(-0.015F, (f6 - 1.0F) * 1.5F, -0.0F);
         } else {
            GL11.glTranslatef(-0.015F, (f6 - 1.0F) * 1.5F, -0.015F);
         }

         this.rightleg.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.85F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.775F));
         if (this.field_78117_n) {
            GL11.glTranslatef(0.015F, (f6 - 1.0F) * 1.5F, -0.0F);
         } else {
            GL11.glTranslatef(0.015F, (f6 - 1.0F) * 1.5F, -0.015F);
         }

         this.leftleg.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.675F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.8F));
         int b = 6;
         String[] s = JRMCoreH.data(this.Entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0").split(";");
         String dns = s[1];
         b = JRMCoreH.dnsBreast(dns);
         float scale = b * 0.03F;
         float br = 0.4235988F + scale;
         float bs = 0.8F + scale;
         float bsY = 0.85F + scale * 0.5F;
         float bt = 0.1F * scale;
         boolean bounce = this.Entity.field_70122_E || this.Entity.func_70090_H();
         float bspeed = this.Entity.func_70051_ag() ? 1.5F : (this.Entity.func_70093_af() ? 0.5F : 1.0F);
         float bbY = (bounce ? MathHelper.func_76126_a(this.rot1 * 0.6662F * bspeed * 1.5F + (float) Math.PI) * this.rot2 * 0.03F : 0.0F) * (b * 0.1119F);
         GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F + bbY, 0.015F + bt);
         GL11.glScalef(1.0F, bsY, bs);
         this.setRotation(this.breast, -br, 0.0F, 0.0F);
         this.setRotation(this.breast2, br, 3.141593F, 0.0F);
         if (bounce) {
            this.breast.field_78795_f = this.breast.field_78795_f
               + -MathHelper.func_76134_b(this.rot1 * 0.6662F * bspeed + (float) Math.PI) * this.rot2 * 0.05F * (b * 0.1119F);
            this.breast.field_78796_g = this.breast.field_78796_g
               + MathHelper.func_76134_b(this.rot1 * 0.6662F * bspeed + (float) Math.PI) * this.rot2 * 0.02F * (b * 0.1119F);
            this.breast2.field_78795_f = this.breast2.field_78795_f
               + MathHelper.func_76134_b(this.rot1 * 0.6662F * bspeed + (float) Math.PI) * this.rot2 * 0.05F * (b * 0.1119F);
            this.breast2.field_78796_g = this.breast2.field_78796_g
               + MathHelper.func_76134_b(this.rot1 * 0.6662F * bspeed + (float) Math.PI) * this.rot2 * 0.02F * (b * 0.1119F);
         }

         this.Bbreast.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.7F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.7F));
         GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F);
         this.body.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.75F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.75F) * (1.0F + 0.005F * p));
         if (this.field_78117_n) {
            GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F);
         } else {
            GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, -0.02F - 5.0E-4F * p);
         }

         this.hip.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.65F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.65F) * (1.0F + 0.001F * p));
         if (this.field_78117_n) {
            GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F);
         } else {
            GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, -0.04F - 1.0E-4F * p);
         }

         this.waist.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.85F), 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.85F) * (1.0F + 0.005F * p));
         GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F, 0.0F - 5.0E-4F * p);
         this.bottom.func_78785_a(f5);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / f6 * (g <= 1 ? 1.0F : 0.675F) - 0.001F, 1.0F / f6, 1.0F / f6 * (g <= 1 ? 1.0F : 0.8F) - 0.001F);
         GL11.glTranslatef(0.0F, (f6 - 1.0F) * 1.5F + 0.001F + bbY, 0.015F + bt);
         GL11.glScalef(1.0F, bsY, bs);
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
         for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
            if (JRMCoreH.plyrs[pl].equals(p.func_70005_c_()) && JRMCoreH.data1.length >= JRMCoreH.plyrs.length) {
               String[] s = JRMCoreH.data1[pl].split(";");
               pwr = Integer.parseInt(s[2]);
               break;
            }
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
         this.B = this.B1 = this.B2 = this.B3 = this.B4 = this.B5 = this.B7 = this.B9 = this.field_78115_e;
      }

      this.field_78116_c.field_78796_g = par4 / (180.0F / (float)Math.PI);
      this.field_78116_c.field_78795_f = par5 / (180.0F / (float)Math.PI);
      if (y == 3) {
         p.field_70761_aq = 0.0F;
         this.field_78116_c.field_78795_f = -0.17453294F;
         this.field_78116_c.field_78796_g = -0.17453294F;
      }

      this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
      this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
      if (y == 4 || y == 5) {
         this.field_78116_c.field_78796_g = this.field_78116_c.field_78796_g + (y == 4 ? 0.8F : -0.8F);
         this.RA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
         this.LA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
      } else if (y == 6 || y == 7) {
         this.field_78116_c.field_78796_g = this.field_78116_c.field_78796_g + (y == 6 ? 0.7F : -0.7F);
         float animation_helper = -0.7F + (50.0F - animation) * 0.025F;
         float animation_extra = 0.4F - animation_helper;
         this.RA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
         this.LA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F - (y == 6 ? animation_extra : animation_extra + 0.3F);
      } else if (y == 3) {
         this.RA.field_78795_f = 0.0F;
         this.LA.field_78795_f = 0.0F;
         this.RA.field_78808_h = 0.2F;
         this.LA.field_78808_h = -0.2F;
      } else if (y == 1) {
         this.field_78116_c.field_78795_f = par5 / (180.0F / (float)Math.PI);
         if (pwr == 2 && par2 > 0.9F) {
            this.RA.field_78795_f = 0.7F;
            this.LA.field_78795_f = 0.7F;
         } else {
            this.RA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
            this.LA.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
         }
      } else {
         this.field_78116_c.field_78795_f = (float) (-Math.PI / 3);
         this.RA.field_78795_f = 0.0F;
         this.LA.field_78795_f = 0.0F;
         this.RA.field_78808_h = 0.2F;
         this.LA.field_78808_h = -0.2F;
      }

      this.RA.field_78808_h = 0.0F;
      this.LA.field_78808_h = 0.0F;
      if (y == 4 || y == 5) {
         this.RL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
         this.LL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
         this.RL.field_78808_h = 0.0F;
         this.LL.field_78808_h = 0.0F;
      } else if (y == 6 || y == 7) {
         this.RL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
         this.LL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
         this.RL.field_78808_h = 0.2F;
         this.LL.field_78808_h = -0.2F;
      } else if (y == 3) {
         this.RL.field_78795_f = MathHelper.func_76134_b(0.0F) * 1.4F * par2;
         this.LL.field_78795_f = MathHelper.func_76134_b(3.8077927F) * 1.4F * par2;
         this.RL.field_78808_h = 0.1F;
         this.LL.field_78808_h = -0.2F;
      } else if (y == 1) {
         this.RL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
         this.LL.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
         this.RL.field_78808_h = 0.0F;
         this.LL.field_78808_h = 0.0F;
      } else {
         this.RL.field_78795_f = 0.0F;
         this.LL.field_78795_f = 0.0F;
         this.RL.field_78808_h = this.RL.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.LL.field_78808_h = this.LL.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RL.field_78795_f = this.RL.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.LL.field_78795_f = this.LL.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.RL.field_78808_h = 0.2F;
         this.LL.field_78808_h = -0.2F;
      }

      this.RL.field_78796_g = 0.0F;
      this.LL.field_78796_g = 0.0F;
      boolean isClientPlayerInFPSView = par7Entity.func_70005_c_().equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())
         && JRMCoreClient.mc.field_71474_y.field_74320_O == 0;
      if (this.field_78093_q) {
         if (!isClientPlayerInFPSView) {
            this.RA.field_78795_f += (float) (-Math.PI / 5);
            this.LA.field_78795_f += (float) (-Math.PI / 5);
         }

         this.RL.field_78795_f = (float) (-Math.PI * 2.0 / 5.0);
         this.LL.field_78795_f = (float) (-Math.PI * 2.0 / 5.0);
         this.RL.field_78796_g = (float) (Math.PI / 10);
         this.LL.field_78796_g = (float) (-Math.PI / 10);
      }

      if (this.field_78119_l != 0) {
         this.LA.field_78795_f = this.LA.field_78795_f * 0.5F - (float) (Math.PI / 10) * this.field_78119_l;
      }

      if (this.field_78120_m != 0 && (pwr != 2 || !(par2 > 0.9F))) {
         this.RA.field_78795_f = this.RA.field_78795_f * 0.5F - (float) (Math.PI / 10) * this.field_78120_m;
      }

      this.RA.field_78796_g = 0.0F;
      this.LA.field_78796_g = 0.0F;
      if (this.field_78095_p > -9990.0F) {
         float var8 = this.field_78095_p;
         if (pwr != 3 || this.field_78120_m <= 9) {
            this.B.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(var8) * (float) Math.PI * 2.0F) * 0.2F;
            this.B1.field_78796_g = this.B2.field_78796_g = this.B3.field_78796_g = this.B4.field_78796_g = this.B5.field_78796_g = this.B7.field_78796_g = this.B9
               .field_78796_g = this.B.field_78796_g;
         }

         this.RA.field_78798_e = MathHelper.func_76126_a(this.B.field_78796_g) * 5.0F;
         this.RA.field_78800_c = -MathHelper.func_76134_b(this.B.field_78796_g) * 5.0F;
         this.LA.field_78798_e = -MathHelper.func_76126_a(this.B.field_78796_g) * 5.0F;
         this.LA.field_78800_c = MathHelper.func_76134_b(this.B.field_78796_g) * 5.0F;
         this.RA.field_78796_g = this.RA.field_78796_g + this.B.field_78796_g;
         this.LA.field_78796_g = this.LA.field_78796_g + this.B.field_78796_g;
         var8 = 1.0F - this.field_78095_p;
         var8 *= var8;
         var8 *= var8;
         var8 = 1.0F - var8;
         float var9 = MathHelper.func_76126_a(var8 * (float) Math.PI);
         float var10 = MathHelper.func_76126_a(this.field_78095_p * (float) Math.PI) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
         if (pwr == 2 && par2 > 0.9F && var9 != 0.0F) {
            this.RA.field_78795_f = 0.0F;
            this.RA.field_78795_f = (float)(this.RA.field_78795_f - (var9 * 1.2 + var10));
            this.RA.field_78796_g = this.RA.field_78796_g + this.B.field_78796_g * 2.0F;
            this.RA.field_78808_h = MathHelper.func_76126_a(this.field_78095_p * (float) Math.PI) * -0.4F;
         } else {
            this.RA.field_78795_f = (float)(this.RA.field_78795_f - (var9 * 1.2 + var10));
            this.RA.field_78796_g = this.RA.field_78796_g + this.B.field_78796_g * 2.0F;
            if (y == 3) {
               this.RA.field_78795_f = 0.0F;
               this.LA.field_78795_f = 0.0F;
               this.RA.field_78808_h = 0.5F;
               this.LA.field_78808_h = -0.9F;
            } else if (y == 1) {
               this.RA.field_78808_h = MathHelper.func_76126_a(this.field_78095_p * (float) Math.PI) * -0.4F;
            } else if (!isClientPlayerInFPSView) {
               this.RA.field_78808_h = 0.2F;
               this.LA.field_78808_h = -0.2F;
            }
         }
      }

      if (this.field_78117_n) {
         this.B.field_78795_f = 0.5F;
         this.B1.field_78795_f = this.B2.field_78795_f = this.B3.field_78795_f = this.B4.field_78795_f = this.B5.field_78795_f = this.B7.field_78795_f = this.B9
            .field_78795_f = this.B.field_78795_f;
         this.RA.field_78795_f += 0.4F;
         this.LA.field_78795_f += 0.4F;
         this.RL.field_78798_e = 4.0F;
         this.LL.field_78798_e = 4.0F;
         this.RL.field_78797_d = 9.0F;
         this.LL.field_78797_d = 9.0F;
         this.field_78116_c.field_78797_d = 1.0F;
         this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d;
      } else if (pwr == 2 && par2 > 0.9F) {
         this.B.field_78795_f = 0.5F;
         this.B1.field_78795_f = this.B2.field_78795_f = this.B3.field_78795_f = this.B4.field_78795_f = this.B5.field_78795_f = this.B7.field_78795_f = this.B9
            .field_78795_f = this.B.field_78795_f;
         this.RA.field_78795_f += 0.4F;
         this.LA.field_78795_f += 0.4F;
         this.RL.field_78798_e = 4.0F;
         this.LL.field_78798_e = 4.0F;
         this.RL.field_78797_d = 9.0F;
         this.LL.field_78797_d = 9.0F;
         this.field_78116_c.field_78797_d = 1.0F;
         this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d;
      } else {
         this.B.field_78795_f = 0.0F;
         this.B1.field_78795_f = this.B2.field_78795_f = this.B3.field_78795_f = this.B4.field_78795_f = this.B5.field_78795_f = this.B7.field_78795_f = this.B9
            .field_78795_f = this.B.field_78795_f;
         this.RL.field_78798_e = 0.1F;
         this.LL.field_78798_e = 0.1F;
         this.RL.field_78797_d = 12.0F;
         this.LL.field_78797_d = 12.0F;
         this.field_78116_c.field_78797_d = 0.0F;
         this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d;
      }

      this.field_78117_n = false;
      if (y != 3) {
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.LA.field_78808_h = this.LA.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.LA.field_78795_f = this.LA.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }

      if (this.field_78118_o) {
         float var8 = 0.0F;
         float var9 = 0.0F;
         this.RA.field_78808_h = 0.0F;
         this.LA.field_78808_h = 0.0F;
         this.RA.field_78796_g = -(0.1F - var8 * 0.6F) + this.field_78116_c.field_78796_g;
         this.LA.field_78796_g = 0.1F - var8 * 0.6F + this.field_78116_c.field_78796_g + 0.4F;
         this.RA.field_78795_f = (float) (-Math.PI / 2) + this.field_78116_c.field_78795_f;
         this.LA.field_78795_f = (float) (-Math.PI / 2) + this.field_78116_c.field_78795_f;
         this.RA.field_78795_f -= var8 * 1.2F - var9 * 0.4F;
         this.LA.field_78795_f -= var8 * 1.2F - var9 * 0.4F;
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.LA.field_78808_h = this.LA.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.LA.field_78795_f = this.LA.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }

      this.field_78118_o = false;
      float f6 = 0.0F;
      float f7 = 0.0F;
      if (pwr == 3 && this.field_78120_m > 9) {
         float var8 = this.field_78095_p;
         float var10 = MathHelper.func_76126_a(this.field_78095_p * (float) Math.PI) * 0.7F * 0.75F;
         var8 = MathHelper.func_76126_a(MathHelper.func_76129_c(var8) * (float) Math.PI * 2.0F);
         if (this.field_78120_m == 10) {
            this.RA.field_78808_h = 0.0F;
            this.LA.field_78808_h = 0.0F;
            this.RA.field_78796_g = -0.2F;
            this.LA.field_78796_g = 0.0F;
            this.RA.field_78795_f = -0.5F + (var10 != 0.0F ? -0.5F - var8 : 0.0F);
            this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.LA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.RA.field_78808_h -= 0.15F;
            this.LA.field_78808_h -= 0.25F;
         } else if (this.field_78120_m == 11) {
            this.B.field_78796_g = -0.2F;
            this.B1.field_78796_g = this.B2.field_78796_g = this.B3.field_78796_g = this.B4.field_78796_g = this.B5.field_78796_g = this.B7.field_78796_g = this.B9
               .field_78796_g = this.B.field_78796_g;
            this.RA.field_78808_h = 0.0F;
            this.LA.field_78808_h = -0.3F;
            this.RA.field_78796_g = 0.2F;
            this.LA.field_78796_g = 0.5F;
            this.RA.field_78795_f = -0.9F + (var10 != 0.0F ? var8 : 0.0F);
            this.LA.field_78795_f = -0.5F + (var10 != 0.0F ? var8 : 0.0F);
            this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.LA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.RA.field_78808_h -= 0.85F;
            this.LA.field_78808_h -= -0.15F;
         }

         if (this.field_78095_p > -9990.0F) {
            this.RA.field_78796_g = this.RA.field_78796_g + this.B.field_78796_g * 2.0F;
         }
      }

      if (this.field_78120_m == 0) {
         if (this.blk) {
            this.RA.field_78808_h = 0.0F;
            this.LA.field_78808_h = 0.0F;
            this.RA.field_78796_g = -(0.1F - f6 * 0.6F) + (this.field_78116_c.field_78796_g < -0.2F ? -0.2F : this.field_78116_c.field_78796_g) - 0.8F;
            this.LA.field_78796_g = 0.1F - f6 * 0.6F + (this.field_78116_c.field_78796_g > 0.2F ? 0.2F : this.field_78116_c.field_78796_g) + 0.8F;
            this.RA.field_78795_f = (float) (-Math.PI / 2)
               + (this.field_78116_c.field_78795_f < -0.5F ? -0.5F : (this.field_78116_c.field_78795_f > 0.5F ? 0.5F : this.field_78116_c.field_78795_f));
            this.LA.field_78795_f = (float) (-Math.PI / 2)
               + (this.field_78116_c.field_78795_f < -0.5F ? -0.5F : (this.field_78116_c.field_78795_f > 0.5F ? 0.5F : this.field_78116_c.field_78795_f));
            this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.LA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.45F);
            this.LA.field_78808_h = this.LA.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F - 0.55F);
            this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
            this.LA.field_78795_f = this.LA.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         } else if (this.instantTransmission) {
            this.RA.field_78808_h = 0.45F;
            this.RA.field_78796_g = -0.2F + (this.field_78116_c.field_78795_f > 0.0F ? -this.field_78116_c.field_78795_f * 0.3F : 0.0F);
            this.RA.field_78795_f = -2.5F
               + (this.field_78116_c.field_78795_f < -0.5F ? -0.5F : (this.field_78116_c.field_78795_f > 0.9F ? 0.9F : this.field_78116_c.field_78795_f));
         }
      }

      if ((this.KiAttack == 1 || this.KiAttack == 8 || this.KiAttack == 9) && this.field_78120_m == 0) {
         f6 = 0.0F;
         f7 = 0.0F;
         this.RA.field_78808_h = 0.0F;
         this.LA.field_78808_h = 0.0F;
         this.RA.field_78796_g = -(0.1F - f6 * 0.6F) + (this.field_78116_c.field_78796_g < -0.2F ? -0.2F : this.field_78116_c.field_78796_g) - 0.5F;
         this.LA.field_78796_g = 0.1F - f6 * 0.6F + (this.field_78116_c.field_78796_g > 0.2F ? 0.2F : this.field_78116_c.field_78796_g) + 0.5F;
         this.RA.field_78795_f = (float) (-Math.PI / 2)
            + (this.field_78116_c.field_78795_f < -0.5F ? -0.5F : (this.field_78116_c.field_78795_f > 0.5F ? 0.5F : this.field_78116_c.field_78795_f));
         this.LA.field_78795_f = (float) (-Math.PI / 2)
            + (this.field_78116_c.field_78795_f < -0.5F ? -0.5F : (this.field_78116_c.field_78795_f > 0.5F ? 0.5F : this.field_78116_c.field_78795_f));
         this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.LA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.LA.field_78808_h = this.LA.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.LA.field_78795_f = this.LA.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }

      if ((this.KiAttack == 2 || this.KiAttack == 5 || this.KiAttack == 4 || this.KiAttack == 7) && this.field_78120_m == 0) {
         f6 = 0.0F;
         f7 = 0.0F;
         this.RA.field_78808_h = 0.0F;
         this.RA.field_78796_g = -(0.1F - f6 * 0.6F) + (this.field_78116_c.field_78796_g < -0.2F ? -0.2F : this.field_78116_c.field_78796_g) - 0.1F;
         this.RA.field_78795_f = (float) (-Math.PI / 2) + this.field_78116_c.field_78795_f;
         this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }

      if (this.KiAttack == 3 && this.field_78120_m == 0) {
         f6 = 0.0F;
         f7 = 0.0F;
         this.RA.field_78808_h = -0.3F;
         this.RA.field_78795_f = -3.0F;
         this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }

      if (this.KiAttack == 6 && this.field_78120_m == 0) {
         f6 = 0.0F;
         f7 = 0.0F;
         this.RA.field_78808_h = -0.3F;
         this.RA.field_78795_f = -3.0F;
         this.RA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.LA.field_78808_h = 0.3F;
         this.LA.field_78795_f = -3.0F;
         this.LA.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
         this.RA.field_78808_h = this.RA.field_78808_h + (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.RA.field_78795_f = this.RA.field_78795_f + MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
         this.LA.field_78808_h = this.LA.field_78808_h - (MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F);
         this.LA.field_78795_f = this.LA.field_78795_f - MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      }
   }

   public void func_78110_b(float par1) {
      this.field_78121_j.field_78796_g = this.field_78116_c.field_78796_g;
      this.field_78121_j.field_78795_f = this.field_78116_c.field_78795_f;
      this.field_78121_j.field_78800_c = 0.0F;
      this.field_78121_j.field_78797_d = 0.0F;
      this.field_78121_j.func_78785_a(par1);
   }

   public void func_78111_c(float par1) {
      this.field_78122_k.func_78785_a(par1);
   }

   public void renderHairs(float par1, String hair) {
      float f6 = f;
      GL11.glPushMatrix();
      GL11.glScalef((0.5F + 0.5F / f6) * (g <= 1 ? 1.0F : 0.85F), 0.5F + 0.5F / f6, (0.5F + 0.5F / f6) * (g <= 1 ? 1.0F : 0.85F));
      GL11.glTranslatef(
         0.0F, (f6 - 1.0F) / f6 * (2.0F - (f6 >= 1.5F && f6 <= 2.0F ? (2.0F - f6) / 2.5F : (f6 < 1.5F && f6 >= 1.0F ? (f6 * 2.0F - 2.0F) * 0.2F : 0.0F))), 0.0F
      );
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
