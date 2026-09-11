package JinRyuu.JRMCore.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPrjtls_1 extends Render {
   private ModelPrjtls_1 mod1;
   private ModelPrjtls_2 mod2;
   private ModelPrjtls_3 mod3;
   String[] wt = new String[]{"Rocket1", "Rocket2", "Rocket3", "Rocket2", "Rocket2", "Rocket2", "Rocket2"};

   public RenderPrjtls_1() {
      this.mod1 = new ModelPrjtls_1();
      this.mod2 = new ModelPrjtls_2();
      this.mod3 = new ModelPrjtls_3();
   }

   protected ResourceLocation func_110775_a(Entity entity) {
      return null;
   }

   public void renderItemKunai(EntityPrjtls_1 Entity, double par2, double par4, double par6, float par8, float par9) {
      int wt = Entity.getWpnTyp();
      if (wt > -1) {
         ResourceLocation txx = new ResourceLocation("jinryuudragonbc:projectiles/" + this.wt[wt] + ".png");
         this.field_76990_c.field_78724_e.func_110577_a(txx);
         GL11.glPushMatrix();
         GL11.glEnable(2977);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDepthMask(true);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-Entity.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(Entity.field_70125_A - 90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
         float sc = 0.7F;
         if (wt == 0) {
            sc = 0.7F;
         } else if (wt == 1 || wt == 5) {
            sc = 1.3F;
         } else if (wt == 2) {
            sc = 2.0F;
         } else {
            sc = 0.2F;
         }

         GL11.glScalef(sc, sc, sc);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         if (wt == 0) {
            this.mod1.func_78088_a(Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
         } else if (wt == 1 || wt == 5) {
            this.mod2.func_78088_a(Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
         } else if (wt == 2) {
            this.mod3.func_78088_a(Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
         } else {
            this.mod2.func_78088_a(Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
         }

         GL11.glDisable(3042);
         GL11.glAlphaFunc(516, 0.1F);
         GL11.glDepthMask(true);
         GL11.glPopMatrix();
      }
   }

   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderItemKunai((EntityPrjtls_1)par1Entity, par2, par4, par6, par8, par9);
   }
}
