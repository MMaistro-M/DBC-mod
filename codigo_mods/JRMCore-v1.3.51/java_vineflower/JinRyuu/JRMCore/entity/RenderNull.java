package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNull extends RenderJRMC {
   private static final ResourceLocation texture = new ResourceLocation(JRMCoreH.tjjrmc + ":npcs/TrainingShadowDummyUniform.png");

   public RenderNull(ModelBase model, float f) {
      super(model, f);
   }

   public void doRender(EntityNull entity, double d, double d1, double d2, float d3, float f) {
      float f5 = 0.0625F;
      this.func_110776_a(this.func_110775_a(entity));
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
      this.field_77045_g.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f5);
      GL11.glPopMatrix();
   }

   @Override
   protected ResourceLocation func_110775_a(Entity entity) {
      return texture;
   }

   @Override
   public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
      this.doRender((EntityNull)entity, d0, d1, d2, f, f1);
   }
}
