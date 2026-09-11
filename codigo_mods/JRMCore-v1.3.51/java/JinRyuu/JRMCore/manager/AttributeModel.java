package JinRyuu.JRMCore.manager;

import net.minecraft.client.model.ModelRenderer;

public class AttributeModel {
   public static byte X = 0;
   public static byte Y = 1;
   public static byte Z = 2;
   public ModelRenderer model;
   public AttributeArray attributeArray;
   public boolean hasEnded;
   public byte value;

   public AttributeModel(ModelRenderer model, byte value, AttributeArray attributeArray) {
      this.model = model;
      this.value = value;
      this.attributeArray = attributeArray;
      this.hasEnded = false;
   }

   public void update(float age) {
      if (!this.hasEnded) {
         this.attributeArray.update(age);
         if (this.attributeArray.hasEnded) {
            this.hasEnded = true;
         }
      }
   }
}
