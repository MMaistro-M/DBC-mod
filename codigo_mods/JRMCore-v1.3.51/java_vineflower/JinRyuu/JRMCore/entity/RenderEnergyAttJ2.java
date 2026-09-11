package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.m.mEnergy5;
import JinRyuu.JRMCore.m.mEnergy7;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEnergyAttJ2 extends RenderJRMC {
   private mEnergy5 ener;
   private mEnergy7 ener2;
   private final String[] jutsus = new String[]{"rasengan", "chidori", "FireStyle_FireBall", "Earth Wall", "Mud Wall", "Water Pistol"};
   private double dist;

   public RenderEnergyAttJ2() {
      super(new mEnergy5(), 0.5F);
      this.ener = new mEnergy5();
      this.ener2 = new mEnergy7();
   }

   public void renderEnergy(EntityEnergyAttJ2 par1Entity, double par2, double par4, double par6, float par8, float par9) {
      if (par1Entity.getjtsre() > 2) {
         byte type = par1Entity.getType();
         byte perc = par1Entity.getPerc();
         int dam = par1Entity.getDam();
         byte den = par1Entity.getDen();
         int col = par1Entity.getCol();
         float size = par1Entity.getSizePerc();
         boolean b = false;
         if (par1Entity.shootingEntity != null) {
            b = par1Entity.shootingEntity.func_145782_y() == JRMCoreClient.mc.field_71439_g.func_145782_y();
         }

         float var13 = this.handleRotationFloat(par1Entity, par9);
         double sx = par1Entity.strtX();
         double sy = par1Entity.strtY();
         double sz = par1Entity.strtZ();
         double x = sx - par1Entity.field_70165_t;
         double y = sy - par1Entity.field_70163_u;
         double z = sz - par1Entity.field_70161_v;
         double tsx = sx - par1Entity.trgtX();
         double tsy = sy - par1Entity.trgtY();
         double tsz = sz - par1Entity.trgtZ();
         if (par1Entity.getShrink()) {
            this.dist = this.dist - par1Entity.getSpe() * 20.0F;
         } else {
            this.dist = MathHelper.func_76133_a(x * x + y * y + z * z);
         }

         float var20 = size * 2.0F;
         if (var20 > 30.0F) {
            var20 = 30.0F;
         }

         if (type == 1) {
            GL11.glPushMatrix();
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            if (par1Entity.getjtsre() != 3) {
               GL11.glDepthMask(false);
            }

            GL11.glTranslatef((float)par2, (float)par4, (float)par6);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            if (par1Entity.getjtsre() == 3) {
               ResourceLocation tx = new ResourceLocation("jinryuunarutoc:jutsu/" + this.jutsus[par1Entity.getjtsre() - 1] + ".png");
               this.field_76990_c.field_78724_e.func_110577_a(tx);
               float seb_szaz = 10.0F;
               float seb_one = seb_szaz / 100.0F;
               float max = 15.0F;
               float ti = par1Entity.field_70173_aa;
               float curr = ti * seb_one;
               curr = curr >= max ? max : curr;
               GL11.glTranslatef(0.0F, -curr / 2.0F, 0.0F);
               GL11.glScalef(curr, curr, curr);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glRotatef(par1Entity.field_70173_aa * 10, 1.0F, 1.0F, 0.0F);
               this.ener.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
               GL11.glColor4f(1.0F, 0.8F, 0.8F, 0.4F);
               GL11.glScalef(1.2F, 1.2F, 1.2F);
               GL11.glRotatef(par1Entity.field_70173_aa * 5, 0.0F, 1.0F, 1.0F);
               this.ener.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            } else if (par1Entity.getjtsre() == 6) {
               ResourceLocation tx = new ResourceLocation("jinryuumodscore:allw.png");
               this.field_76990_c.field_78724_e.func_110577_a(tx);
               GL11.glDepthMask(true);
               float seb_szaz = 10.0F;
               float seb_one = seb_szaz / 100.0F;
               float max = 1.0F;
               float ti = par1Entity.field_70173_aa;
               float curr = ti * seb_one;
               curr = curr >= max ? max : curr;
               GL11.glTranslatef(0.0F, -curr / 2.0F, 0.0F);
               GL11.glScalef(curr, curr, curr);
               GL11.glColor4f(0.3F, 0.6F, 0.8F, 0.7F);
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-par1Entity.field_70177_z, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(par1Entity.field_70125_A, 0.0F, 0.0F, 1.0F);
               this.ener2.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
               GL11.glDepthMask(true);
               GL11.glColor4f(0.3F, 0.45F, 0.8F, 0.5F);
               GL11.glScalef(curr * 1.2F, curr * 1.2F, curr * 1.2F);
               this.ener2.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            }

            GL11.glDisable(3042);
            GL11.glDisable(2977);
            GL11.glPopMatrix();
         }
      }
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
      this.renderEnergy((EntityEnergyAttJ2)par1Entity, par2, par4, par6, par8, par9);
   }
}
