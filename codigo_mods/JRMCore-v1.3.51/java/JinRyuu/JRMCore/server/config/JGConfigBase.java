package JinRyuu.JRMCore.server.config;

public class JGConfigBase {
   public static int getID(int id, int max) {
      return id > max ? max : id;
   }

   public static String getDefault(String min, String max) {
      return ". (From " + min + " to " + max + ").";
   }

   public static String getDefault2(String min, String max) {
      return getDefault(min, max).substring(1);
   }

   public static String getDefault(String value) {
      return ". (" + value + ").";
   }

   public static String getDefaultValue(String value) {
      return ". (Default: " + value + ").";
   }

   public static String getDefaultValue2(String value) {
      return getDefaultValue(value).substring(1);
   }

   public static int checkValue(int value, int min, int max) {
      if (value > max) {
         value = max;
      }

      if (value < min) {
         value = min;
      }

      return value;
   }
}
