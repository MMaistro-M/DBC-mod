package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSafeZone extends RenderJRMC {
   public RenderSafeZone() {
      super(new ModelNPCNormalScaled(), 0.5F);
   }

   public void renderAura(EntitySafeZone entity, double parX, double parY, double parZ, float par8, float par9) {
      if (JRMCoreClient.mc.field_71442_b.func_78758_h()) {
         float f5 = 0.0625F;
         this.field_76989_e = 0.0F;
         GL11.glPushMatrix();
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef((float)(-parX), (float)(-parY) - entity.field_70131_O, (float)parZ);
         GL11.glRotatef(entity.field_70173_aa * 2, 0.0F, 1.0F, 0.0F);
         GL11.glPushMatrix();
         GL11.glEnable(3042);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 771);
         GL11.glAlphaFunc(516, 0.003921569F);
         GL11.glDepthMask(false);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
         ResourceLocation tx = new ResourceLocation(entity.getTexture());
         this.field_76990_c.field_78724_e.func_110577_a(tx);
         this.field_77045_g.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f5);
         GL11.glDisable(3042);
         GL11.glEnable(2896);
         GL11.glDepthMask(true);
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   @Override
   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderAura((EntitySafeZone)entity, par2, par4, par6, par8, par9);
   }
}
