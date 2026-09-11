package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPS extends ModelBase {
   private int id;
   public ModelRenderer shape1;
   public ModelRenderer shape2;
   public ModelRenderer shape3;
   public ModelRenderer shape_1_1;
   public ModelRenderer shape_1_2;
   public ModelRenderer shape_1_3;
   public ModelRenderer shape_1_4;
   public ModelRenderer shape_1_5;
   public ModelRenderer shape_1_6;
   public ModelRenderer shape_2_1;
   public ModelRenderer shape_2_2;
   public ModelRenderer shape_2_3;
   public ModelRenderer shape_2_4;
   public ModelRenderer shape_2_5;
   public ModelRenderer shape_2_6;
   public ModelRenderer shape_3_1;
   public ModelRenderer shape_3_2;
   public ModelRenderer shape_3_3;
   public ModelRenderer shape_3_4;
   public ModelRenderer shape_3_5;
   public ModelRenderer shape_3_6;
   public ModelRenderer shape_4_1;
   public ModelRenderer shape_4_2;
   public ModelRenderer shape_4_3;
   public ModelRenderer shape_4_4;
   public ModelRenderer shape_4_5;
   public ModelRenderer shape_4_6;

   public ModelPS(int id) {
      this.id = id;
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      if (id == 0) {
         this.shape1 = new ModelRenderer(this, 0, 0);
         this.shape1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape1.func_78790_a(-1.4F, -2.0F, -1.5F, 3, 3, 3, 0.0F);
         this.shape3 = new ModelRenderer(this, 40, 0);
         this.shape3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape3.func_78790_a(-1.1F, 0.8F, -0.7F, 2, 3, 2, 0.0F);
         this.shape2 = new ModelRenderer(this, 21, 0);
         this.shape2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape2.func_78790_a(-0.6F, -4.8F, -1.1F, 2, 3, 2, 0.0F);
      } else if (id == 1) {
         this.shape_1_1 = new ModelRenderer(this, 0, 0);
         this.shape_1_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_1.func_78790_a(-1.4F, -2.0F, -1.5F, 3, 3, 3, 0.0F);
         this.shape_1_2 = new ModelRenderer(this, 21, 0);
         this.shape_1_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_2.func_78790_a(-0.6F, -3.9F, -1.1F, 2, 2, 2, 0.0F);
         this.shape_1_3 = new ModelRenderer(this, 40, 0);
         this.shape_1_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_3.func_78790_a(-1.1F, 0.8F, -0.7F, 2, 1, 2, 0.0F);
         this.shape_1_5 = new ModelRenderer(this, 23, 15);
         this.shape_1_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_5.func_78790_a(-0.3F, -5.4F, -0.4F, 1, 2, 1, 0.0F);
         this.shape_1_4 = new ModelRenderer(this, 2, 14);
         this.shape_1_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_4.func_78790_a(1.0F, -3.4F, -0.7F, 1, 4, 1, 0.0F);
         this.shape_1_6 = new ModelRenderer(this, 43, 16);
         this.shape_1_6.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_1_6.func_78790_a(-0.6F, 1.6F, -0.6F, 1, 1, 1, 0.0F);
      } else if (id == 2) {
         this.shape_2_4 = new ModelRenderer(this, 2, 14);
         this.shape_2_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_4.func_78790_a(1.0F, -3.0F, -0.7F, 1, 1, 1, 0.0F);
         this.shape_2_1 = new ModelRenderer(this, 0, 0);
         this.shape_2_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_1.func_78790_a(-1.4F, -2.0F, -1.5F, 4, 4, 4, 0.0F);
         this.shape_2_2 = new ModelRenderer(this, 21, 0);
         this.shape_2_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_2.func_78790_a(-0.6F, -4.6F, -1.3F, 2, 3, 3, 0.0F);
         this.shape_2_6 = new ModelRenderer(this, 43, 16);
         this.shape_2_6.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_6.func_78790_a(-0.8F, -1.7F, -2.5F, 2, 3, 1, 0.0F);
         this.shape_2_3 = new ModelRenderer(this, 40, 0);
         this.shape_2_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_3.func_78790_a(-1.1F, 1.9F, -0.7F, 2, 1, 3, 0.0F);
         this.shape_2_5 = new ModelRenderer(this, 23, 15);
         this.shape_2_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_2_5.func_78790_a(-0.5F, -5.4F, -1.0F, 1, 1, 1, 0.0F);
      } else if (id == 3) {
         this.shape_3_4 = new ModelRenderer(this, 2, 14);
         this.shape_3_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_4.func_78790_a(0.5F, -5.0F, 1.5F, 1, 1, 1, 0.0F);
         this.shape_3_3 = new ModelRenderer(this, 40, 0);
         this.shape_3_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_3.func_78790_a(-1.1F, 1.9F, -0.7F, 3, 3, 2, 0.0F);
         this.shape_3_1 = new ModelRenderer(this, 0, 0);
         this.shape_3_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_1.func_78790_a(-1.4F, -2.0F, -1.5F, 4, 4, 3, 0.0F);
         this.shape_3_2 = new ModelRenderer(this, 21, 0);
         this.shape_3_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_2.func_78790_a(-0.6F, -4.6F, -1.3F, 2, 3, 3, 0.0F);
         this.shape_3_6 = new ModelRenderer(this, 43, 16);
         this.shape_3_6.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_6.func_78790_a(-0.2F, -1.1F, -2.3F, 2, 2, 1, 0.0F);
         this.shape_3_5 = new ModelRenderer(this, 23, 15);
         this.shape_3_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_3_5.func_78790_a(-0.4F, -5.5F, 0.7F, 1, 2, 2, 0.0F);
      } else if (id == 4) {
         this.shape_4_4 = new ModelRenderer(this, 2, 14);
         this.shape_4_4.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_4.func_78790_a(0.5F, -2.6F, 1.1F, 1, 3, 2, 0.0F);
         this.shape_4_1 = new ModelRenderer(this, 0, 0);
         this.shape_4_1.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_1.func_78790_a(-1.4F, -2.0F, -1.5F, 4, 5, 4, 0.0F);
         this.shape_4_6 = new ModelRenderer(this, 43, 16);
         this.shape_4_6.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_6.func_78790_a(-0.2F, -1.1F, -2.3F, 2, 5, 1, 0.0F);
         this.shape_4_2 = new ModelRenderer(this, 21, 0);
         this.shape_4_2.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_2.func_78790_a(-0.6F, -3.8F, -1.3F, 4, 2, 3, 0.0F);
         this.shape_4_3 = new ModelRenderer(this, 40, 0);
         this.shape_4_3.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_3.func_78790_a(-1.1F, 1.9F, -1.5F, 3, 5, 2, 0.0F);
         this.shape_4_5 = new ModelRenderer(this, 23, 15);
         this.shape_4_5.func_78793_a(0.0F, 0.0F, 0.0F);
         this.shape_4_5.func_78790_a(0.9F, -5.6F, -0.9F, 2, 2, 2, 0.0F);
      }
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      if (this.id == 0) {
         this.shape1.func_78785_a(f5);
         this.shape2.func_78785_a(f5);
         this.shape3.func_78785_a(f5);
      }

      if (this.id == 1) {
         this.shape_1_1.func_78785_a(f5);
         this.shape_1_2.func_78785_a(f5);
         this.shape_1_3.func_78785_a(f5);
         this.shape_1_4.func_78785_a(f5);
         this.shape_1_5.func_78785_a(f5);
         this.shape_1_6.func_78785_a(f5);
      }

      if (this.id == 2) {
         this.shape_2_1.func_78785_a(f5);
         this.shape_2_2.func_78785_a(f5);
         this.shape_2_3.func_78785_a(f5);
         this.shape_2_4.func_78785_a(f5);
         this.shape_2_5.func_78785_a(f5);
         this.shape_2_6.func_78785_a(f5);
      }

      if (this.id == 3) {
         this.shape_3_1.func_78785_a(f5);
         this.shape_3_2.func_78785_a(f5);
         this.shape_3_3.func_78785_a(f5);
         this.shape_3_4.func_78785_a(f5);
         this.shape_3_5.func_78785_a(f5);
         this.shape_3_6.func_78785_a(f5);
      }

      if (this.id == 4) {
         this.shape_4_1.func_78785_a(f5);
         this.shape_4_2.func_78785_a(f5);
         this.shape_4_3.func_78785_a(f5);
         this.shape_4_4.func_78785_a(f5);
         this.shape_4_5.func_78785_a(f5);
         this.shape_4_6.func_78785_a(f5);
      }
   }

   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }
}
