package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPrjtls_1 extends ModelBase {
   public ModelRenderer Base;
   public ModelRenderer Front;
   public ModelRenderer Back;
   public ModelRenderer Front_1;
   public ModelRenderer Front_2;
   public ModelRenderer Back_1;
   public ModelRenderer Back_2;
   public ModelRenderer Back_3;
   public ModelRenderer Back_4;
   public ModelRenderer Back_5;

   public ModelPrjtls_1() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.Back_1 = new ModelRenderer(this, 29, 22);
      this.Back_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_1.func_78790_a(-2.0F, -2.25F, 12.7F, 4, 4, 5, 0.0F);
      this.Back_2 = new ModelRenderer(this, 25, 0);
      this.Back_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_2.func_78790_a(-0.5F, -4.15F, 9.7F, 1, 2, 9, 0.0F);
      this.Front = new ModelRenderer(this, 48, 0);
      this.Front.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front.func_78790_a(-2.5F, -2.7F, -9.8F, 5, 5, 3, 0.0F);
      this.Front_2 = new ModelRenderer(this, 52, 17);
      this.Front_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front_2.func_78790_a(-1.5F, -1.7F, -15.6F, 3, 3, 3, 0.0F);
      this.Front_1 = new ModelRenderer(this, 50, 9);
      this.Front_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front_1.func_78790_a(-2.0F, -2.25F, -12.5F, 4, 4, 3, 0.0F);
      this.Back_5 = new ModelRenderer(this, 28, 11);
      this.Back_5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_5.func_78790_a(-3.7F, -0.7F, 9.7F, 2, 1, 9, 0.0F);
      this.Base = new ModelRenderer(this, 0, 0);
      this.Base.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Base.func_78790_a(-3.0F, -3.1F, -7.2F, 6, 6, 12, 0.0F);
      this.Back = new ModelRenderer(this, 0, 18);
      this.Back.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back.func_78790_a(-2.5F, -2.7F, 4.1F, 5, 5, 9, 0.0F);
      this.Back_3 = new ModelRenderer(this, 25, 0);
      this.Back_3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_3.func_78790_a(-0.5F, 1.55F, 9.7F, 1, 2, 9, 0.0F);
      this.Back_4 = new ModelRenderer(this, 28, 11);
      this.Back_4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_4.func_78790_a(1.8F, -0.7F, 9.7F, 2, 1, 9, 0.0F);
      this.Back.func_78792_a(this.Back_1);
      this.Back_1.func_78792_a(this.Back_2);
      this.Base.func_78792_a(this.Front);
      this.Front_1.func_78792_a(this.Front_2);
      this.Front.func_78792_a(this.Front_1);
      this.Back_1.func_78792_a(this.Back_5);
      this.Base.func_78792_a(this.Back);
      this.Back_1.func_78792_a(this.Back_3);
      this.Back_1.func_78792_a(this.Back_4);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.Base.func_78785_a(f5);
   }

   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }
}
