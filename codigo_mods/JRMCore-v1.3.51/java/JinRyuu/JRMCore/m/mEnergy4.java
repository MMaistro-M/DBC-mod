package JinRyuu.JRMCore.m;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class mEnergy4 extends ModelBase {
   public ModelRenderer Spine1;
   public ModelRenderer Spine2;
   public ModelRenderer Spine3;
   public ModelRenderer Spine4;
   public ModelRenderer Spine5;
   public ModelRenderer FootRubble1;
   public ModelRenderer FootRubble2;
   public ModelRenderer FootRubble3;
   public ModelRenderer HeadRubble1;
   public ModelRenderer HeadRubble2;
   public ModelRenderer FootRubble4;
   public ModelRenderer FootRubble5;
   public ModelRenderer HeadRubble3;

   public mEnergy4() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.FootRubble3 = new ModelRenderer(this, 0, 72);
      this.FootRubble3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.FootRubble3.func_78790_a(-3.4F, 19.3F, -9.6F, 12, 5, 15, 0.0F);
      this.setRotateAngle(this.FootRubble3, 0.08552113F, -0.17296213F, 0.0F);
      this.FootRubble2 = new ModelRenderer(this, 0, 55);
      this.FootRubble2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.FootRubble2.func_78790_a(-2.1F, 14.8F, -7.5F, 7, 8, 8, 0.0F);
      this.setRotateAngle(this.FootRubble2, 0.091106184F, -0.18203785F, -0.13665928F);
      this.Spine3 = new ModelRenderer(this, 70, 57);
      this.Spine3.func_78793_a(0.0F, 0.0F, -0.1F);
      this.Spine3.func_78790_a(-6.7F, -2.7F, 1.6F, 13, 7, 3, 0.0F);
      this.setRotateAngle(this.Spine3, 0.1605703F, 0.0F, 0.0F);
      this.FootRubble4 = new ModelRenderer(this, 0, 36);
      this.FootRubble4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.FootRubble4.func_78790_a(8.3F, 18.4F, -8.5F, 7, 9, 9, 0.0F);
      this.setRotateAngle(this.FootRubble4, 0.0F, -0.18203785F, 0.091106184F);
      this.Spine1 = new ModelRenderer(this, 0, 0);
      this.Spine1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Spine1.func_78790_a(-6.1F, -7.9F, -3.6F, 12, 28, 7, 0.0F);
      this.setRotateAngle(this.Spine1, -0.13788101F, 0.0F, 0.0F);
      this.Spine4 = new ModelRenderer(this, 0, 0);
      this.Spine4.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Spine4.func_78790_a(0.7F, 0.0F, -4.0F, 12, 28, 7, 0.0F);
      this.setRotateAngle(this.Spine4, 0.0017453292F, -0.2443461F, -0.108210415F);
      this.FootRubble5 = new ModelRenderer(this, 0, 55);
      this.FootRubble5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.FootRubble5.func_78790_a(-13.6F, 19.0F, -6.1F, 7, 8, 8, 0.0F);
      this.setRotateAngle(this.FootRubble5, 0.0F, 0.0F, -0.13665928F);
      this.HeadRubble2 = new ModelRenderer(this, 84, 43);
      this.HeadRubble2.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HeadRubble2.func_78790_a(0.5F, -13.6F, 0.6F, 5, 9, 3, 0.0F);
      this.setRotateAngle(this.HeadRubble2, -0.06283186F, 0.0F, -0.091106184F);
      this.HeadRubble1 = new ModelRenderer(this, 80, 30);
      this.HeadRubble1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HeadRubble1.func_78790_a(-6.5F, -12.7F, -0.1F, 8, 7, 4, 0.0F);
      this.setRotateAngle(this.HeadRubble1, -0.06283186F, 0.0F, 0.108210415F);
      this.Spine5 = new ModelRenderer(this, 70, 0);
      this.Spine5.func_78793_a(0.0F, 0.0F, 0.0F);
      this.Spine5.func_78790_a(-16.0F, 4.7F, -2.5F, 14, 22, 7, 0.0F);
      this.setRotateAngle(this.Spine5, -0.03141593F, 0.18849556F, 0.11868239F);
      this.HeadRubble3 = new ModelRenderer(this, 80, 30);
      this.HeadRubble3.func_78793_a(0.0F, 0.0F, 0.0F);
      this.HeadRubble3.func_78790_a(-13.3F, -3.8F, -1.8F, 8, 7, 4, 0.0F);
      this.setRotateAngle(this.HeadRubble3, -0.045553092F, 0.091106184F, -0.22759093F);
      this.Spine2 = new ModelRenderer(this, 70, 0);
      this.Spine2.func_78793_a(0.0F, 0.0F, -0.1F);
      this.Spine2.func_78790_a(-7.1F, 3.2F, -1.7F, 14, 22, 7, 0.0F);
      this.setRotateAngle(this.Spine2, 0.08726646F, 0.0F, 0.0F);
      this.FootRubble1 = new ModelRenderer(this, 0, 36);
      this.FootRubble1.func_78793_a(0.0F, 0.0F, 0.0F);
      this.FootRubble1.func_78790_a(-6.2F, 16.7F, -5.8F, 7, 9, 9, 0.0F);
      this.setRotateAngle(this.FootRubble1, 0.019198623F, 0.17104226F, 0.06632251F);
      this.Spine1.func_78792_a(this.FootRubble3);
      this.Spine1.func_78792_a(this.FootRubble2);
      this.Spine4.func_78792_a(this.FootRubble4);
      this.Spine5.func_78792_a(this.FootRubble5);
      this.Spine2.func_78792_a(this.HeadRubble2);
      this.Spine2.func_78792_a(this.HeadRubble1);
      this.FootRubble5.func_78792_a(this.HeadRubble3);
      this.Spine1.func_78792_a(this.FootRubble1);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.Spine3.func_78785_a(f5);
      this.Spine1.func_78785_a(f5);
      this.Spine4.func_78785_a(f5);
      this.Spine5.func_78785_a(f5);
      this.Spine2.func_78785_a(f5);
   }

   public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.field_78795_f = x;
      modelRenderer.field_78796_g = y;
      modelRenderer.field_78808_h = z;
   }
}
