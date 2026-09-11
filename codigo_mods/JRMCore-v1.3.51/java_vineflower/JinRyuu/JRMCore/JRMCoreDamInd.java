package JinRyuu.JRMCore;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class JRMCoreDamInd extends EntityAuraFX {
   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
   private double amount = 0.0;
   private double timeleft = 0.0;

   public JRMCoreDamInd(
      double amount, float timeleft, World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ
   ) {
      super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
      this.func_70536_a(82);
      this.field_70544_f = 1.0F;
      this.func_70538_b(136.0F, 0.0F, 136.0F);
      this.amount = amount;
      this.timeleft = timeleft;
      this.field_70547_e = 50;
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.99;
      this.field_70181_x *= 0.99;
      this.field_70179_y *= 0.99;
      this.field_70181_x += 0.00125;
      if (this.field_70547_e-- <= 0) {
         this.func_70106_y();
      }
   }

   public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
      float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * p_70539_2_ - field_70556_an);
      float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * p_70539_2_ - field_70554_ao);
      float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * p_70539_2_ - field_70555_ap);
      p_70539_1_.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
      FontRenderer fontrenderer = JRMCoreClient.mc.field_71466_p;
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef(f11 + 0.0F, f12 - 0.75F + 1.75F, f13);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      boolean ro = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
      GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F * (ro ? -1 : 1), 0.0F, 0.0F);
      float f1 = 0.0516F;
      GL11.glScalef(-f1, -f1, f1);
      GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
      String text = "" + JRMCoreH.numSep((int)this.amount);
      int textWidth = fontrenderer.func_78256_a(text);
      JRMCoreGuiScreen.drawStringWithBorder(fontrenderer, text, -textWidth / 2, -2, JRMCoreH.techCol[4]);
      JRMCoreClient.mc.field_71446_o.func_110577_a(particleTextures);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }
}
