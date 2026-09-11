package JinRyuu.JRMCore.m;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class mBlob extends ModelBiped {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;

   public mBlob() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.func_78789_a(-10.0F, -10.0F, -10.0F, 20, 20, 20);
      this.Shape1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape1.func_78787_b(128, 128);
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.func_78789_a(-8.0F, -8.0F, -12.0F, 16, 16, 24);
      this.Shape2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape2.func_78787_b(128, 128);
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.func_78789_a(-4.0F, -4.0F, -14.0F, 8, 8, 28);
      this.Shape3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape3.func_78787_b(128, 128);
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.func_78789_a(-8.0F, -12.0F, -8.0F, 16, 24, 16);
      this.Shape4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape4.func_78787_b(128, 128);
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.func_78789_a(-4.0F, -14.0F, -4.0F, 8, 28, 8);
      this.Shape5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape5.func_78787_b(128, 128);
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.func_78789_a(-12.0F, -8.0F, -8.0F, 24, 16, 16);
      this.Shape6.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape6.func_78787_b(128, 128);
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.func_78789_a(-14.0F, -4.0F, -4.0F, 28, 8, 8);
      this.Shape7.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Shape7.func_78787_b(128, 128);
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape1.func_78792_a(this.Shape2);
      this.Shape1.func_78792_a(this.Shape3);
      this.Shape1.func_78792_a(this.Shape4);
      this.Shape1.func_78792_a(this.Shape5);
      this.Shape1.func_78792_a(this.Shape6);
      this.Shape1.func_78792_a(this.Shape7);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.func_78785_a(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.field_78795_f = x;
      model.field_78796_g = y;
      model.field_78808_h = z;
   }

   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      float par3 = f2;
      float par31 = 1.0F;
      this.Shape1.field_78808_h = par3;
      this.Shape1.field_78796_g = par3;
      this.Shape1.field_78795_f = par3;
   }

   public void renderModel(Entity entity, float par8, float par9, float f, float r) {
      this.func_78088_a(entity, 0.0F, 0.0F, r, par8, par9, f);
   }
}
