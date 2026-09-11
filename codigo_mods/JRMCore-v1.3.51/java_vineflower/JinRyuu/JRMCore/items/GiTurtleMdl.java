package JinRyuu.JRMCore.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GiTurtleMdl extends ModelBiped {
   ModelRenderer leftarmshoulder;
   ModelRenderer rightarmshoulder = new ModelRenderer(this, 40, 32);
   ModelRenderer cape;
   ModelRenderer c20;
   ModelRenderer c19;

   public GiTurtleMdl(float s) {
      super(s, 0.0F, 128, 64);
      this.rightarmshoulder.func_78789_a(-3.0F, -5.0F, -3.0F, 7, 4, 6);
      this.rightarmshoulder.func_78793_a(-5.0F, 2.0F, 0.0F);
      this.rightarmshoulder.func_78787_b(128, 64);
      this.setRotation(this.rightarmshoulder, 0.0F, 0.0F, 0.1570796F);
      this.leftarmshoulder = new ModelRenderer(this, 40, 32);
      this.leftarmshoulder.field_78809_i = true;
      this.leftarmshoulder.func_78789_a(-4.0F, -5.0F, -3.0F, 7, 4, 6);
      this.leftarmshoulder.func_78793_a(5.0F, 2.0F, 0.0F);
      this.leftarmshoulder.func_78787_b(128, 64);
      this.setRotation(this.leftarmshoulder, 0.0F, 0.0F, -0.1570796F);
      this.cape = new ModelRenderer(this, 100, 0);
      this.cape.func_78790_a(-7.0F, 1.0F, 2.0F, 14, 20, 0, s);
      this.cape.func_78793_a(0.0F, 0.0F, 0.0F);
      this.cape.func_78787_b(128, 64);
      this.setRotation(this.cape, 0.1570796F, 0.0F, 0.0F);
      this.c20 = new ModelRenderer(this, 76, 35);
      this.c20.func_78790_a(-4.0F, -12.0F, -4.0F, 8, 4, 8, s);
      this.c20.func_78793_a(0.0F, 0.0F, 0.0F);
      this.c20.func_78787_b(128, 64);
      this.c20.field_78809_i = true;
      this.setRotation(this.c20, 0.0F, 0.0F, 0.0F);
      this.c19 = new ModelRenderer(this, 106, 29);
      this.c19.func_78790_a(-1.0F, -11.0F, -0.5F, 2, 4, 2, s);
      this.c19.func_78793_a(0.0F, 0.0F, 0.0F);
      this.c19.func_78787_b(128, 64);
      this.c19.field_78809_i = true;
      this.setRotation(this.c19, 0.0F, 0.0F, 0.0F);
      this.field_78116_c.func_78792_a(this.c20);
      this.field_78116_c.func_78792_a(this.c19);
      this.field_78115_e.func_78792_a(this.cape);
      this.field_78113_g.func_78792_a(this.leftarmshoulder);
      this.field_78112_f.func_78792_a(this.rightarmshoulder);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.field_78795_f = x;
      model.field_78796_g = y;
      model.field_78808_h = z;
   }

   public void setRotationPub(ModelRenderer model, float x, float y, float z) {
      model.field_78795_f = x;
      model.field_78796_g = y;
      model.field_78808_h = z;
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
   }
}
