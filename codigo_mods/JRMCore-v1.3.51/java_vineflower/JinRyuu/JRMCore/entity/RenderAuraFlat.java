package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAuraFlat extends RenderJRMC {
   public RenderAuraFlat(ModelBase model, float f) {
      super(model, f);
   }

   public void doRender(EntityAuraFlat entity, double d, double d1, double d2, float d3, float f) {
      float f5 = 0.0625F;
      this.func_110776_a(this.func_110775_a(entity));
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      entity.update();
      float scale = 0.5F;
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
      GL11.glColor4f(entity.aura.getRed() / 255.0F, entity.aura.getGreen() / 255.0F, entity.aura.getBlue() / 255.0F, entity.transparency.lastValue);
      GL11.glTranslatef((float)entity.field_70165_t, (float)entity.field_70163_u, (float)entity.field_70161_v);
      GL11.glRotatef((int)entity.field_70165_t * 30, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef((int)entity.field_70161_v * 30, 1.0F, 0.0F, 0.0F);
      this.field_77045_g.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f5);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   @Override
   protected ResourceLocation func_110775_a(Entity entity) {
      return ((EntityAuraFlat)entity).getTexture();
   }

   @Override
   public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
      this.doRender((EntityAuraFlat)entity, d0, d1, d2, f, f1);
   }
}
