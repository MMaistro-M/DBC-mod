package JinRyuu.JRMCore.m;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class mEnergy3 extends ModelBiped {
   ModelRenderer bShape1;
   ModelRenderer bShape2;

   public mEnergy3() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.bShape1 = new ModelRenderer(this, 0, 0);
      this.bShape1.func_78789_a(-10.0F, -10.0F, -10.0F, 20, 20, 20);
      this.bShape1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.bShape1.func_78787_b(128, 128);
      this.setRotation(this.bShape1, 0.0F, 0.0F, 0.0F);
      this.bShape2 = new ModelRenderer(this, 0, 0);
      this.bShape2.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      this.bShape2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.bShape2.func_78787_b(128, 128);
      this.setRotation(this.bShape1, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
   }

   public void render(ModelRenderer a, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      a.func_78785_a(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.field_78795_f = x;
      model.field_78796_g = y;
      model.field_78808_h = z;
   }

   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      float par3 = f2;
      this.bShape1.field_78808_h = par3;
      this.bShape1.field_78796_g = par3;
      this.bShape1.field_78795_f = par3;
      this.bShape2.field_78808_h = par3;
      this.bShape2.field_78796_g = par3;
      this.bShape2.field_78795_f = par3;
   }

   public void renderModel(byte type, Entity entity, float par8, float par9, float f, float r, boolean b) {
      ModelRenderer render = null;
      if (type == 1) {
         render = this.bShape1;
      }

      if (type == 2) {
         render = this.bShape2;
      }

      if (render != null) {
         this.render(render, entity, 0.0F, 0.0F, r, par8, par9, f);
      }
   }
}
