package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPG extends ModelBase {
   private int id;
   public ModelRenderer shape1;
   public ModelRenderer shape2;
   public ModelRenderer shape3;
   public ModelRenderer shape4;
   public ModelRenderer shape_1_1;
   public ModelRenderer shape_1_2;
   public ModelRenderer shape_1_3;
   public ModelRenderer shape_1_4;
   public ModelRenderer shape_1_5;
   public ModelRenderer shape_2_1;
   public ModelRenderer shape_2_2;
   public ModelRenderer shape_2_3;
   public ModelRenderer shape_2_4;
   public ModelRenderer shape_2_5;
   public ModelRenderer shape_3_1;
   public ModelRenderer shape_3_2;
   public ModelRenderer shape_3_3;
   public ModelRenderer shape_3_4;
   public ModelRenderer shape_3_5;
   public ModelRenderer shape_4_1;
   public ModelRenderer shape_4_2;
   public ModelRenderer shape_4_3;
   public ModelRenderer shape_4_4;
   public ModelRenderer shape_4_5;

   public ModelPG(int id) {
      this.id = id;
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      if (id == 0) {
         this.shape3 = new ModelRenderer(this, 26, 0);
         this.shape3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape3.func_78790_a(-0.8F, -7.8F, -1.0F, 2, 2, 2, 0.0F);
         this.shape1 = new ModelRenderer(this, 0, 0);
         this.shape1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 5, 2, 0.0F);
         this.shape4 = new ModelRenderer(this, 41, 0);
         this.shape4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape4.func_78790_a(-2.4F, 3.8F, -1.0F, 2, 2, 2, 0.0F);
         this.shape2 = new ModelRenderer(this, 13, 0);
         this.shape2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape2.func_78790_a(0.4F, -5.9F, -1.0F, 2, 5, 2, 0.0F);
      } else if (id == 1) {
         this.shape_1_4 = new ModelRenderer(this, 36, 0);
         this.shape_1_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_4.func_78790_a(0.1F, 3.8F, -1.0F, 2, 2, 2, 0.0F);
         this.shape_1_1 = new ModelRenderer(this, 0, 0);
         this.shape_1_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 5, 2, 0.0F);
         this.shape_1_3 = new ModelRenderer(this, 24, 0);
         this.shape_1_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_3.func_78790_a(-0.8F, -6.3F, -1.0F, 2, 2, 2, 0.0F);
         this.shape_1_5 = new ModelRenderer(this, 49, 0);
         this.shape_1_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_5.func_78790_a(1.6F, 5.8F, -1.0F, 2, 2, 2, 0.0F);
         this.shape_1_2 = new ModelRenderer(this, 12, 0);
         this.shape_1_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_2.func_78790_a(0.4F, -4.6F, -1.0F, 2, 4, 2, 0.0F);
      } else if (id == 2) {
         this.shape_2_5 = new ModelRenderer(this, 39, 0);
         this.shape_2_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_5.func_78790_a(1.3F, 5.4F, 1.4F, 2, 2, 3, 0.0F);
         this.shape_2_1 = new ModelRenderer(this, 0, 0);
         this.shape_2_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 5, 2, 0.0F);
         this.shape_2_3 = new ModelRenderer(this, 19, 0);
         this.shape_2_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_3.func_78790_a(-0.8F, -6.3F, -3.6F, 2, 2, 2, 0.0F);
         this.shape_2_2 = new ModelRenderer(this, 9, 0);
         this.shape_2_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_2.func_78790_a(0.4F, -4.6F, -2.1F, 2, 4, 2, 0.0F);
         this.shape_2_4 = new ModelRenderer(this, 29, 0);
         this.shape_2_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_4.func_78790_a(0.0F, 3.7F, 0.2F, 2, 2, 2, 0.0F);
      } else if (id == 3) {
         this.shape_3_1 = new ModelRenderer(this, 0, 0);
         this.shape_3_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 5, 2, 0.0F);
         this.shape_3_5 = new ModelRenderer(this, 39, 0);
         this.shape_3_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_5.func_78790_a(1.0F, 6.3F, 1.3F, 2, 4, 2, 0.0F);
         this.shape_3_3 = new ModelRenderer(this, 19, 0);
         this.shape_3_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_3.func_78790_a(-0.8F, -5.3F, -4.1F, 2, 4, 2, 0.0F);
         this.shape_3_4 = new ModelRenderer(this, 29, 0);
         this.shape_3_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_4.func_78790_a(-0.1F, 3.5F, 0.1F, 2, 3, 2, 0.0F);
         this.shape_3_2 = new ModelRenderer(this, 9, 0);
         this.shape_3_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_2.func_78790_a(0.4F, -2.2F, -2.8F, 2, 2, 3, 0.0F);
      } else if (id == 4) {
         this.shape_4_3 = new ModelRenderer(this, 25, 0);
         this.shape_4_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_3.func_78790_a(-0.8F, -3.5F, -4.1F, 2, 2, 2, 0.0F);
         this.shape_4_1 = new ModelRenderer(this, 0, 0);
         this.shape_4_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
         this.shape_4_2 = new ModelRenderer(this, 12, 0);
         this.shape_4_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_2.func_78790_a(0.4F, -2.2F, -2.8F, 2, 2, 3, 0.0F);
         this.shape_4_5 = new ModelRenderer(this, 52, 0);
         this.shape_4_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_5.func_78790_a(1.0F, 3.9F, 2.4F, 2, 2, 2, 0.0F);
         this.shape_4_4 = new ModelRenderer(this, 37, 0);
         this.shape_4_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_4.func_78790_a(-0.1F, 2.3F, 0.1F, 2, 2, 3, 0.0F);
      }
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      if (this.id == 0) {
         this.shape1.func_78785_a(f5);
         this.shape2.func_78785_a(f5);
         this.shape3.func_78785_a(f5);
         this.shape4.func_78785_a(f5);
      }

      if (this.id == 1) {
         this.shape_1_1.func_78785_a(f5);
         this.shape_1_2.func_78785_a(f5);
         this.shape_1_3.func_78785_a(f5);
         this.shape_1_4.func_78785_a(f5);
         this.shape_1_5.func_78785_a(f5);
      }

      if (this.id == 2) {
         this.shape_2_1.func_78785_a(f5);
         this.shape_2_2.func_78785_a(f5);
         this.shape_2_3.func_78785_a(f5);
         this.shape_2_4.func_78785_a(f5);
         this.shape_2_5.func_78785_a(f5);
      }

      if (this.id == 3) {
         this.shape_3_1.func_78785_a(f5);
         this.shape_3_2.func_78785_a(f5);
         this.shape_3_3.func_78785_a(f5);
         this.shape_3_4.func_78785_a(f5);
         this.shape_3_5.func_78785_a(f5);
      }

      if (this.id == 4) {
         this.shape_4_1.func_78785_a(f5);
         this.shape_4_2.func_78785_a(f5);
         this.shape_4_3.func_78785_a(f5);
         this.shape_4_4.func_78785_a(f5);
         this.shape_4_5.func_78785_a(f5);
      }
   }

   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }
}
