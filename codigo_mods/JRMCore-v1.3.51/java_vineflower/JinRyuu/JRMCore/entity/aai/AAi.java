package JinRyuu.JRMCore.entity.aai;

import java.util.Random;

public class AAi {
   public AAiSystem aaiSystem;

   public void update() {
   }

   public boolean checkChanceToUse(double rate) {
      if (rate >= 1.0) {
         return true;
      } else {
         return rate <= 0.0 ? false : new Random().nextInt(100) < (int)(rate * 100.0);
      }
   }
}
