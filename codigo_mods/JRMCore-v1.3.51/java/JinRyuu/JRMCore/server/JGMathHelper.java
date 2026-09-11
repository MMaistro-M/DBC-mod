package JinRyuu.JRMCore.server;

import net.minecraft.entity.Entity;

public class JGMathHelper {
   public static double StringMethod(String method, double n1, double n2) {
      if (method.equals("+")) {
         n1 += n2;
      } else if (method.equals("-")) {
         n1 -= n2;
      } else if (method.equals("*")) {
         n1 *= n2;
      } else if (method.equals("/")) {
         n1 /= n2;
      } else if (method.equals("%")) {
         n1 %= n2;
      }

      return n1;
   }

   public static long StringMethod(String method, long n1, long n2) {
      if (method.equals("+")) {
         n1 += n2;
      } else if (method.equals("-")) {
         n1 -= n2;
      } else if (method.equals("*")) {
         n1 *= n2;
      } else if (method.equals("/")) {
         n1 /= n2;
      } else if (method.equals("%")) {
         n1 %= n2;
      }

      return n1;
   }

   public static double doubleLimit(double value, double max) {
      boolean minus = value < 0.0;
      if (minus) {
         value *= -1.0;
      }

      if (value > max) {
         value = max;
      }

      if (minus) {
         value *= -1.0;
      }

      return value;
   }

   public static boolean doubleHigherThan(double value, double min) {
      return value < 0.0 ? -value > min : value > min;
   }

   public static boolean doubleSmallerThan(double value, double min) {
      return value < 0.0 ? -value < min : value < min;
   }

   public static boolean isMotionSmallerThanN(Entity entity, double minMotion, boolean doX, boolean doY, boolean doZ, boolean methodOne) {
      if (!methodOne) {
         double motion = 0.0;
         double x = doX ? entity.field_70159_w : 0.0;
         if (x < 0.0) {
            x *= -1.0;
         }

         double y = doY ? entity.field_70181_x : 0.0;
         if (y < 0.0) {
            y *= -1.0;
         }

         double z = doZ ? entity.field_70179_y : 0.0;
         if (z < 0.0) {
            z *= -1.0;
         }

         motion += x + y + z;
         return motion < minMotion;
      } else {
         double x = entity.field_70159_w;
         double y = entity.field_70181_x;
         double z = entity.field_70179_y;
         boolean canDoX = doX ? x <= minMotion && x >= -minMotion : true;
         boolean canDoY = doY ? y <= minMotion && y >= -minMotion : true;
         boolean canDoZ = doZ ? z <= minMotion && z >= -minMotion : true;
         return canDoX && canDoY && canDoZ;
      }
   }

   public static boolean isMotionBiggerThanN(Entity entity, double minMotion, boolean doX, boolean doY, boolean doZ, boolean methodOne) {
      if (!methodOne) {
         double motion = 0.0;
         double x = doX ? entity.field_70159_w : 0.0;
         if (x < 0.0) {
            x *= -1.0;
         }

         double y = doY ? entity.field_70181_x : 0.0;
         if (y < 0.0) {
            y *= -1.0;
         }

         double z = doZ ? entity.field_70179_y : 0.0;
         if (z < 0.0) {
            z *= -1.0;
         }

         motion += x + y + z;
         return motion > minMotion;
      } else {
         double x = entity.field_70159_w;
         double y = entity.field_70181_x;
         double z = entity.field_70179_y;
         boolean canDoX = doX ? x >= minMotion && x <= -minMotion : false;
         boolean canDoY = doY ? y >= minMotion && y <= -minMotion : false;
         boolean canDoZ = doZ ? z >= minMotion && z <= -minMotion : false;
         return canDoX || canDoY || canDoZ;
      }
   }

   public static boolean isMotionSmallerThanN(Entity entity, double minMotion) {
      return isMotionSmallerThanN(entity, minMotion, true, true, true, true);
   }

   public static boolean isMotionBiggerThanN(Entity entity, double minMotion) {
      return isMotionBiggerThanN(entity, minMotion, true, true, true, true);
   }
}
