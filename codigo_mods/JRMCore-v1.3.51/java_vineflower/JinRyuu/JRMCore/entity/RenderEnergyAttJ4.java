package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.m.mEnergy5;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEnergyAttJ4 extends RenderJRMC {
   private mEnergy5 ener;
   public final int lvnm = 10;
   public float[][] lightVertRotation = new float[10][3];

   public RenderEnergyAttJ4() {
      super(new mEnergy5(), 0.5F);
      this.ener = new mEnergy5();

      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 3; j++) {
            this.lightVertRotation[i][j] = 0.0F;
         }
      }
   }

   public void renderEnergy(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par8, float par9) {
      byte type = e.getType();
      double x = e.field_70165_t;
      double y = e.field_70163_u;
      double z = e.field_70161_v;
      GL11.glPushMatrix();
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(false);
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      GL11.glRotatef(e.field_70126_B + (e.field_70177_z - e.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(e.field_70127_C + (e.field_70125_A - e.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      this.tex(5220343);
      float seb_szaz = 5.0F;
      float seb_one = seb_szaz / 100.0F;
      float max = 1.0F;
      float ti = e.field_70173_aa;
      float curr = ti * seb_one;
      curr = curr >= max ? max : curr;
      GL11.glScalef(curr, curr, curr);
      GL11.glDisable(3042);
      GL11.glDisable(2977);
      GL11.glPopMatrix();
      if (type == 0) {
         this.fieldPass3(e, par2, par4, par6, par9, curr, 5220343);
      } else {
         this.lightning(e, par2, par4, par6, par9, curr, 5220343);
      }
   }

   private void lightning(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par9, float var20, int col) {
      GL11.glPushMatrix();
      Tessellator tessellator2 = Tessellator.field_78398_a;
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      double[] adouble = new double[8];
      double[] adouble1 = new double[8];
      double d3 = 0.0;
      double d4 = 0.0;
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      float t = e.field_70173_aa;
      float scale = 0.03F * t;
      GL11.glScalef(scale, scale, scale);
      GL11.glRotatef(e.field_70173_aa * 45.0F, 1.0F, 1.0F, 1.0F);
      int k1 = 0;
      float sc = e.getSize() / 2.0F;

      for (int i = 0; i < 10; i++) {
         if (!JRMCoreClient.mc.func_147113_T()) {
            this.lightVertRotation[i][0] = (int)(Math.random() * 11.0) * 36;
            this.lightVertRotation[i][1] = (float)(Math.random() * 2.0) - 1.0F;
            this.lightVertRotation[i][2] = (float)(Math.random() * 2.0) - 1.0F;
         }

         GL11.glRotated(i * (360 / 10), 0.0, 0.0, 1.0);
         GL11.glRotatef(this.lightVertRotation[i][0], this.lightVertRotation[i][1], this.lightVertRotation[i][2], 0.0F);
         new Random(e.lightVert[i]);
         Random random1 = new Random(e.lightVert[i]);

         for (int j = 0; j < 3; j++) {
            int k = 7;
            int l = 0;
            if (j > 0) {
               k = 7 - j;
            }

            if (j > 0) {
               l = k - 2;
            }

            double d5 = adouble[k] - d3;
            double d6 = adouble1[k] - d4;

            for (int i1 = k; i1 >= l; i1--) {
               double d7 = d5;
               double d8 = d6;
               d5 += (random1.nextInt(31) - 15) * 0.07F;
               d6 += (random1.nextInt(31) - 15) * 0.07F;
               tessellator2.func_78371_b(5);
               float f2 = 0.5F;
               tessellator2.func_78369_a(0.9F * f2, 0.9F * f2, 1.0F * f2, 0.3F - e.field_70173_aa * 0.013F);
               double d9 = 0.1 + k1 * 0.2;
               double d10 = 0.1 + k1 * 0.2;

               for (int j1 = 0; j1 < 5; j1++) {
                  double d11 = 0.0 - d9;
                  double d12 = 0.0 - d9;
                  if (j1 == 1 || j1 == 2) {
                     d11 += d9 * 2.0 * sc;
                  }

                  if (j1 == 2 || j1 == 3) {
                     d12 += d9 * 2.0 * sc;
                  }

                  double d13 = 0.0 - d10;
                  double d14 = 0.0 - d10;
                  if (j1 == 1 || j1 == 2) {
                     d13 += d10 * 2.0 * sc;
                  }

                  if (j1 == 2 || j1 == 3) {
                     d14 += d10 * 2.0 * sc;
                  }

                  if (i1 < 8) {
                     tessellator2.func_78377_a(d13 + d5 * sc, -(i1 * 1 - 7) * sc, d14 + d6 * sc);
                     tessellator2.func_78377_a(d11 + d7 * sc, -((i1 + 1) * 1 - 7) * sc, d12 + d8 * sc);
                  }
               }

               tessellator2.func_78381_a();
            }
         }
      }

      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }

   private void fieldPass3(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par9, float var20, int col) {
      GL11.glPushMatrix();
      GL11.glDepthMask(true);
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2896);
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      this.tex(col);
      GL11.glScalef(var20 / 2.0F, var20 / 2.0F, var20 / 2.0F);
      float t = e.field_70173_aa;
      float scale = 0.3F * t;
      GL11.glScalef(scale, scale, scale);
      GL11.glRotatef(e.field_70173_aa * 45.0F, 1.0F, 1.0F, 1.0F);
      float sc2 = 2.6F;
      GL11.glColor4f(0.7F, 0.9F, 1.0F, 0.6F - e.field_70173_aa * 0.03F);
      this.ener.render();
      sc2 = 2.6F;
      GL11.glScalef(sc2, sc2, sc2);
      GL11.glColor4f(0.7F, 0.9F, 1.0F, 0.3F - e.field_70173_aa * 0.015F);
      this.ener.render();
      GL11.glDisable(3042);
      GL11.glDisable(2977);
      GL11.glDisable(2896);
      GL11.glPopMatrix();
   }

   public void tex(int col) {
      float h2 = (col >> 16 & 0xFF) / 255.0F;
      float h3 = (col >> 8 & 0xFF) / 255.0F;
      float h4 = (col & 0xFF) / 255.0F;
      GL11.glColor4f(h2, h3, h4, 0.5F);
      ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
      this.field_76990_c.field_78724_e.func_110577_a(txx);
   }

   protected float handleRotationFloat(Entity par1Entity, float par2) {
      return par1Entity.field_70173_aa + par2;
   }

   @Override
   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderEnergy((EntityEnergyAttJ4)par1Entity, par2, par4, par6, par8, par9);
   }
}
