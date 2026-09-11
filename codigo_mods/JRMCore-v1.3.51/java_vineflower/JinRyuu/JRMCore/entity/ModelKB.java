package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKB extends ModelBase {
   private int type = 0;
   public ModelRenderer KiBlade1;
   public ModelRenderer KiBlade2;
   public ModelRenderer KiBlade3;
   public ModelRenderer KiBlade4;
   public ModelRenderer BaseHandle4;
   public ModelRenderer HandleBump4;
   public ModelRenderer BottomSpike1;
   public ModelRenderer Handle1;
   public ModelRenderer EdgeBase;
   public ModelRenderer BottomSpike2;
   public ModelRenderer HandleBump1;
   public ModelRenderer Handle2;
   public ModelRenderer HandleBump2;
   public ModelRenderer Handle3;
   public ModelRenderer HandleBump3;
   public ModelRenderer TopSpike1;
   public ModelRenderer Edge1;
   public ModelRenderer TopSpike2;
   public ModelRenderer Edge2;
   public ModelRenderer Edge3;
   public ModelRenderer Edge11;
   public ModelRenderer Edge33;
   public ModelRenderer Edge22;
   public ModelRenderer Edge4;
   public ModelRenderer Edge5;
   public ModelRenderer Edge6;
   public ModelRenderer Edge7;

   public ModelKB() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.KiBlade4 = new ModelRenderer(this, 4, 22);
      this.KiBlade4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.KiBlade4.func_78790_a(-1.0F, 13.9F, -1.0F, 2, 2, 2, 0.0F);
      this.KiBlade2 = new ModelRenderer(this, 1, 9);
      this.KiBlade2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.KiBlade2.func_78790_a(-2.0F, 10.1F, -2.0F, 4, 2, 4, 0.0F);
      this.KiBlade1 = new ModelRenderer(this, 0, 0);
      this.KiBlade1.func_78793_a(0.0F, 2.0F, 0.0F);
      this.KiBlade1.func_78790_a(-2.5F, 7.2F, -2.5F, 5, 3, 5, 0.0F);
      this.KiBlade3 = new ModelRenderer(this, 2, 16);
      this.KiBlade3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.KiBlade3.func_78790_a(-1.5F, 12.0F, -1.5F, 3, 2, 3, 0.0F);
      this.KiBlade3.func_78792_a(this.KiBlade4);
      this.KiBlade1.func_78792_a(this.KiBlade2);
      this.KiBlade2.func_78792_a(this.KiBlade3);
      this.HandleBump1 = new ModelRenderer(this, 15, 10);
      this.HandleBump1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HandleBump1.func_78790_a(-1.5F, -18.0F, -1.5F, 3, 1, 3, 0.0F);
      this.Edge3 = new ModelRenderer(this, 50, 14);
      this.Edge3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge3.func_78790_a(23.3F, -26.8F, -0.5F, 6, 6, 1, 0.0F);
      this.Edge11 = new ModelRenderer(this, 48, 30);
      this.Edge11.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge11.func_78790_a(8.3F, -25.2F, -0.5F, 4, 1, 1, 0.0F);
      this.BaseHandle4 = new ModelRenderer(this, 1, 10);
      this.BaseHandle4.func_78793_a(0.0F, -4.1F, 0.0F);
      this.BaseHandle4.func_78790_a(-1.0F, -1.8F, -1.0F, 2, 20, 2, 0.0F);
      this.Handle2 = new ModelRenderer(this, 17, 0);
      this.Handle2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Handle2.func_78790_a(-1.0F, -17.1F, -1.0F, 2, 7, 2, 0.0F);
      this.HandleBump4 = new ModelRenderer(this, 15, 10);
      this.HandleBump4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HandleBump4.func_78790_a(-1.5F, 18.0F, -1.5F, 3, 1, 3, 0.0F);
      this.Edge7 = new ModelRenderer(this, 30, 24);
      this.Edge7.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge7.func_78790_a(33.5F, -3.2F, -0.5F, 3, 7, 1, 0.0F);
      this.Handle1 = new ModelRenderer(this, 17, 0);
      this.Handle1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Handle1.func_78790_a(-1.0F, -24.9F, -1.0F, 2, 7, 2, 0.0F);
      this.EdgeBase = new ModelRenderer(this, 0, 0);
      this.EdgeBase.func_78793_a(0.0F, 0.0F, 0.0F);
      this.EdgeBase.func_78790_a(-1.5F, -31.6F, -1.5F, 3, 7, 3, 0.0F);
      this.Edge2 = new ModelRenderer(this, 40, 7);
      this.Edge2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge2.func_78790_a(12.3F, -29.3F, -0.5F, 11, 6, 1, 0.0F);
      this.Edge4 = new ModelRenderer(this, 48, 21);
      this.Edge4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge4.func_78790_a(27.0F, -20.9F, -0.5F, 7, 4, 1, 0.0F);
      this.Edge5 = new ModelRenderer(this, 36, 16);
      this.Edge5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge5.func_78790_a(30.1F, -17.0F, -0.5F, 5, 7, 1, 0.0F);
      this.Edge6 = new ModelRenderer(this, 38, 24);
      this.Edge6.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge6.func_78790_a(32.0F, -10.1F, -0.5F, 4, 7, 1, 0.0F);
      this.Edge33 = new ModelRenderer(this, 48, 26);
      this.Edge33.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge33.func_78790_a(29.3F, -23.8F, -0.5F, 3, 3, 1, 0.0F);
      this.BottomSpike1 = new ModelRenderer(this, 28, 12);
      this.BottomSpike1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BottomSpike1.func_78790_a(-1.0F, 18.9F, -1.0F, 2, 4, 2, 0.0F);
      this.TopSpike1 = new ModelRenderer(this, 28, 5);
      this.TopSpike1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.TopSpike1.func_78790_a(-1.0F, -35.5F, -1.0F, 2, 4, 2, 0.0F);
      this.Edge1 = new ModelRenderer(this, 40, 0);
      this.Edge1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge1.func_78790_a(1.4F, -31.0F, -0.5F, 11, 6, 1, 0.0F);
      this.BottomSpike2 = new ModelRenderer(this, 29, 19);
      this.BottomSpike2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BottomSpike2.func_78790_a(-0.5F, 22.7F, -0.5F, 1, 3, 1, 0.0F);
      this.Handle3 = new ModelRenderer(this, 17, 0);
      this.Handle3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Handle3.func_78790_a(-1.0F, -9.4F, -1.0F, 2, 7, 2, 0.0F);
      this.Edge22 = new ModelRenderer(this, 48, 30);
      this.Edge22.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Edge22.func_78790_a(19.4F, -23.4F, -0.5F, 4, 1, 1, 0.0F);
      this.HandleBump2 = new ModelRenderer(this, 15, 10);
      this.HandleBump2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HandleBump2.func_78790_a(-1.5F, -10.3F, -1.5F, 3, 1, 3, 0.0F);
      this.HandleBump3 = new ModelRenderer(this, 15, 10);
      this.HandleBump3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HandleBump3.func_78790_a(-1.5F, -2.6F, -1.5F, 3, 1, 3, 0.0F);
      this.TopSpike2 = new ModelRenderer(this, 29, 0);
      this.TopSpike2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.TopSpike2.func_78790_a(-0.5F, -38.4F, -0.5F, 1, 3, 1, 0.0F);
      this.Handle1.func_78792_a(this.HandleBump1);
      this.Edge2.func_78792_a(this.Edge3);
      this.Edge2.func_78792_a(this.Edge11);
      this.Handle1.func_78792_a(this.Handle2);
      this.BaseHandle4.func_78792_a(this.HandleBump4);
      this.Edge6.func_78792_a(this.Edge7);
      this.BaseHandle4.func_78792_a(this.Handle1);
      this.BaseHandle4.func_78792_a(this.EdgeBase);
      this.Edge1.func_78792_a(this.Edge2);
      this.Edge33.func_78792_a(this.Edge4);
      this.Edge4.func_78792_a(this.Edge5);
      this.Edge5.func_78792_a(this.Edge6);
      this.Edge3.func_78792_a(this.Edge33);
      this.BaseHandle4.func_78792_a(this.BottomSpike1);
      this.EdgeBase.func_78792_a(this.TopSpike1);
      this.EdgeBase.func_78792_a(this.Edge1);
      this.BottomSpike1.func_78792_a(this.BottomSpike2);
      this.Handle2.func_78792_a(this.Handle3);
      this.Edge3.func_78792_a(this.Edge22);
      this.Handle2.func_78792_a(this.HandleBump2);
      this.Handle3.func_78792_a(this.HandleBump3);
      this.TopSpike1.func_78792_a(this.TopSpike2);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      if (this.type == 0) {
         this.KiBlade1.func_78785_a(f5);
      } else if (this.type == 1) {
         this.BaseHandle4.func_78785_a(f5);
      }
   }

   public void render(float f5, int type) {
      if (type == 0) {
         this.KiBlade1.func_78785_a(f5);
      } else if (type == 1) {
         this.BaseHandle4.func_78785_a(f5);
      }
   }

   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }
}
