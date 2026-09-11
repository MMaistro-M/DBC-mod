package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPrjtls_3 extends ModelBase {
   public ModelRenderer Base;
   public ModelRenderer Back;
   public ModelRenderer Front;
   public ModelRenderer Back_1;
   public ModelRenderer Back_2;
   public ModelRenderer Back_3;
   public ModelRenderer Back_4;
   public ModelRenderer Back_5;
   public ModelRenderer Back_6;
   public ModelRenderer Back_7;
   public ModelRenderer Back_8;
   public ModelRenderer Back_9;
   public ModelRenderer Front_1;
   public ModelRenderer Front_2;
   public ModelRenderer Front_3;

   public ModelPrjtls_3() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.Back_8 = new ModelRenderer(this, 49, 27);
      this.Back_8.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_8.func_78790_a(1.8F, -0.7F, 13.2F, 4, 1, 3, 0.0F);
      this.Back_2 = new ModelRenderer(this, 41, 14);
      this.Back_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_2.func_78790_a(-0.5F, -4.15F, 9.2F, 1, 2, 4, 0.0F);
      this.Front_1 = new ModelRenderer(this, 52, 0);
      this.Front_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front_1.func_78790_a(-2.5F, -2.7F, -8.1F, 5, 5, 1, 0.0F);
      this.Back_9 = new ModelRenderer(this, 49, 27);
      this.Back_9.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_9.func_78790_a(-5.6F, -0.7F, 13.2F, 4, 1, 3, 0.0F);
      this.Back_5 = new ModelRenderer(this, 49, 21);
      this.Back_5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_5.func_78790_a(-3.7F, -0.7F, 9.2F, 2, 1, 4, 0.0F);
      this.Back_3 = new ModelRenderer(this, 41, 14);
      this.Back_3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_3.func_78790_a(-0.5F, 1.55F, 9.2F, 1, 2, 4, 0.0F);
      this.Back_1 = new ModelRenderer(this, 25, 16);
      this.Back_1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_1.func_78790_a(-2.0F, -2.25F, 10.6F, 4, 4, 5, 0.0F);
      this.Back_4 = new ModelRenderer(this, 49, 21);
      this.Back_4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_4.func_78790_a(1.8F, -0.7F, 9.2F, 2, 1, 4, 0.0F);
      this.Front_2 = new ModelRenderer(this, 54, 7);
      this.Front_2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front_2.func_78790_a(-2.0F, -2.25F, -9.0F, 4, 4, 1, 0.0F);
      this.Back_7 = new ModelRenderer(this, 43, 6);
      this.Back_7.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_7.func_78790_a(-0.5F, 1.35F, 13.2F, 1, 4, 3, 0.0F);
      this.Front_3 = new ModelRenderer(this, 52, 13);
      this.Front_3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front_3.func_78790_a(-1.5F, -1.7F, -12.0F, 3, 3, 3, 0.0F);
      this.Front = new ModelRenderer(this, 1, 1);
      this.Front.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Front.func_78790_a(-3.0F, -3.1F, -7.2F, 6, 6, 3, 0.0F);
      this.Back_6 = new ModelRenderer(this, 43, 6);
      this.Back_6.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back_6.func_78790_a(-0.5F, -5.85F, 13.2F, 1, 4, 3, 0.0F);
      this.Back = new ModelRenderer(this, 0, 13);
      this.Back.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Back.func_78790_a(-2.5F, -2.7F, -2.3F, 5, 5, 13, 0.0F);
      this.Base = new ModelRenderer(this, 21, 0);
      this.Base.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Base.func_78790_a(-3.5F, -3.5F, -4.3F, 7, 7, 3, 0.0F);
      this.Back_4.func_78792_a(this.Back_8);
      this.Back_1.func_78792_a(this.Back_2);
      this.Front.func_78792_a(this.Front_1);
      this.Back_5.func_78792_a(this.Back_9);
      this.Back_1.func_78792_a(this.Back_5);
      this.Back_1.func_78792_a(this.Back_3);
      this.Back.func_78792_a(this.Back_1);
      this.Back_1.func_78792_a(this.Back_4);
      this.Front_1.func_78792_a(this.Front_2);
      this.Back_3.func_78792_a(this.Back_7);
      this.Front_2.func_78792_a(this.Front_3);
      this.Base.func_78792_a(this.Front);
      this.Back_2.func_78792_a(this.Back_6);
      this.Base.func_78792_a(this.Back);
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
