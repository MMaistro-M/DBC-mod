package JinRyuu.JRMCore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Version {
   public static final String MOD_ID = "jinryuujrmcore";
   public static final String MOD_NAME = "JinRyuu's JRMCore";
   public static final String VERSION = "1.3.51";
   public static final String REMOTE_VERSION_FILE = "http://jingames.net/public/JRMCore.txt";
   public static final String REMOTE_MASSAGE_FILE = "http://jingames.net/public/JRMCoremassage.txt";
   public static final byte UNINITIALIZED = 0;
   public static final byte CURRENT = 1;
   public static final byte OUTDATED = 2;
   public static final byte CONNECTION_ERROR = 3;
   private static final String UNINITIALIZED_MESSAGE = "Version Check was Uninitialized!";
   private static final String CONNECTION_ERROR_MESSAGE = "Connection Error when trying to get latest version!";
   public static byte result = 0;
   public static String line = null;
   public static String ammv = null;
   public static String news = null;

   public static void checkVersion() {
      try {
         URL url = new URL("http://jingames.net/public/JRMCore.txt");
         InputStreamReader isr = new InputStreamReader(url.openStream());
         BufferedReader reader = new BufferedReader(isr);

         while ((line = reader.readLine()) != null) {
            if (line.startsWith("jinryuujrmcore") && line.endsWith("1.3.51")) {
               result = 1;
               reader.close();
               isr.close();
               return;
            }
         }

         result = 2;
         reader.close();
         isr.close();
      } catch (Exception e) {
         e.printStackTrace(System.err);
         result = 3;
      }
   }

   public static String getResultMessage() {
      if (result == 0) {
         return "Version Check was Uninitialized!";
      } else if (result == 1) {
         return "Your Version Is Updated";
      } else if (result == 2) {
         return "There is a New Version Available!";
      } else {
         return result == 3 ? "Connection Error when trying to get latest version!" : null;
      }
   }
}
