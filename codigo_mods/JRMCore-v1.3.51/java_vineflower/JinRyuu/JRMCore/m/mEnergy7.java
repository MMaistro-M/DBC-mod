package JinRyuu.JRMCore.m;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class mEnergy7 extends ModelBase {
   public ModelRenderer Base;
   public ModelRenderer BaseFront;
   public ModelRenderer BaseBack;
   public ModelRenderer BaseSide_Ball;
   public ModelRenderer BaseSide_Jobb;
   public ModelRenderer BaseTop;
   public ModelRenderer BaseBottom;
   public ModelRenderer Tail2;
   public ModelRenderer Tail1;

   public mEnergy7() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.Tail1 = new ModelRenderer(this, 22, 0);
      this.Tail1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Tail1.func_78790_a(3.1F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
      this.BaseFront = new ModelRenderer(this, 0, 9);
      this.BaseFront.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseFront.func_78790_a(-3.4F, -1.5F, -1.5F, 1, 3, 3, 0.0F);
      this.Base = new ModelRenderer(this, 0, 0);
      this.Base.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Base.func_78790_a(-2.6F, -1.9F, -2.0F, 5, 4, 4, 0.0F);
      this.Tail2 = new ModelRenderer(this, 15, 0);
      this.Tail2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Tail2.func_78790_a(4.6F, -0.7F, -0.5F, 2, 1, 1, 0.0F);
      this.BaseBottom = new ModelRenderer(this, 20, 15);
      this.BaseBottom.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseBottom.func_78790_a(-2.0F, 1.4F, -1.5F, 3, 1, 3, 0.0F);
      this.BaseSide_Jobb = new ModelRenderer(this, 11, 15);
      this.BaseSide_Jobb.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseSide_Jobb.func_78790_a(-2.0F, -1.5F, 1.4F, 3, 3, 1, 0.0F);
      this.BaseTop = new ModelRenderer(this, 20, 10);
      this.BaseTop.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseTop.func_78790_a(-2.0F, -2.2F, -1.5F, 3, 1, 3, 0.0F);
      this.BaseBack = new ModelRenderer(this, 0, 16);
      this.BaseBack.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseBack.func_78790_a(2.2F, -1.5F, -1.5F, 1, 3, 3, 0.0F);
      this.BaseSide_Ball = new ModelRenderer(this, 11, 10);
      this.BaseSide_Ball.func_78793_a(0.0F, 0.0F, 0.0F);
      this.BaseSide_Ball.func_78790_a(-2.0F, -1.5F, -2.4F, 3, 3, 1, 0.0F);
      this.Tail2.func_78792_a(this.Tail1);
      this.Base.func_78792_a(this.BaseFront);
      this.BaseBack.func_78792_a(this.Tail2);
      this.Base.func_78792_a(this.BaseBottom);
      this.Base.func_78792_a(this.BaseSide_Jobb);
      this.Base.func_78792_a(this.BaseTop);
      this.Base.func_78792_a(this.BaseBack);
      this.Base.func_78792_a(this.BaseSide_Ball);
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
