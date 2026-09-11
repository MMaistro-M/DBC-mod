package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.m.mEnergy4;
import JinRyuu.JRMCore.m.mEnergy6;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEnergyAttJ3 extends RenderJRMC {
   private mEnergy4 ener;
   private mEnergy6 ener2;
   private final String[] jutsus = new String[]{"", "", "", "Earth_Wall", "EarthStyle_MudWall", ""};
   private double dist;

   public RenderEnergyAttJ3() {
      super(new mEnergy4(), 0.5F);
      this.ener = new mEnergy4();
      this.ener2 = new mEnergy6();
   }

   public void renderEnergy(EntityEnergyAttJ3 par1Entity, double par2, double par4, double par6, float par8, float par9) {
      if (par1Entity.getjtsre() == 4 || par1Entity.getjtsre() == 5) {
         byte type = par1Entity.getType();
         byte perc = par1Entity.getPerc();
         int dam = par1Entity.getDam();
         float size = par1Entity.getSizePerc();
         double x = par1Entity.field_70165_t;
         double y = par1Entity.field_70163_u;
         double z = par1Entity.field_70161_v;
         if (type == 1) {
            GL11.glPushMatrix();
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glTranslatef((float)par2, (float)par4, (float)par6);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glRotatef(par1Entity.getrota(), 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            float seb_szaz = 5.0F;
            float seb_one = seb_szaz / 100.0F;
            float max = 1.0F;
            float ti = par1Entity.field_70173_aa;
            float curr = ti * seb_one;
            curr = curr >= max ? max : curr;
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(0.0F, max - curr, 0.0F);
            GL11.glTranslatef(0.0F, -1.2F, 0.0F);
            ResourceLocation tx = new ResourceLocation("jinryuunarutoc:jutsu/" + this.jutsus[par1Entity.getjtsre() - 1] + ".png");
            this.field_76990_c.field_78724_e.func_110577_a(tx);
            if (par1Entity.getjtsre() == 4) {
               this.ener.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            } else if (par1Entity.getjtsre() == 5) {
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
      return par2;
   }

   @Override
   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderEnergy((EntityEnergyAttJ3)par1Entity, par2, par4, par6, par8, par9);
   }
}
