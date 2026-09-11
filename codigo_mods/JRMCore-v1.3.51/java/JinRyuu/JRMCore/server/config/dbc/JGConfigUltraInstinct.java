package JinRyuu.JRMCore.server.config.dbc;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigUltraInstinct extends JGConfigBase {
   public static final String CATEGORY_FORM_UI_SERVERSIDED = "Ultra Instinct";
   public static final String CATEGORY_FORM_UI_LVL_SERVERSIDED = " Level ";
   public static byte CONFIG_UI_LEVELS;
   public static byte CONFIG_UI_EXPERIENCE_PAIN_MODE;
   public static int CONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION;
   public static String[] CONFIG_UI_CHAT_SUCCEED;
   public static boolean[] CONFIG_UI_SKIP;
   public static int[] CONFIG_UI_LEVEL_REQUIREMENT;
   public static int[] CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT;
   public static int[] CONFIG_UI_TP_COST;
   public static int[] CONFIG_UI_MIND_REQUIREMENT;
   public static byte[] CONFIG_UI_HEALTH_PERCENTAGE;
   public static int[] CONFIG_UI_HEAT_DURATION;
   public static int[] CONFIG_UI_PAIN_DURATION;
   public static boolean[] CONFIG_UI_HAIR_WHITE;
   public static int[] CONFIG_UI_ATTRIBUTE_MULTI;
   public static byte[][] CONFIG_UI_DODGE_RATE;
   public static byte[][] CONFIG_UI_ATTACK_RATE;
   public static int[] CONFIG_UI_ATTACK_DAMAGE_PERCENTAGE;
   public static String CONFIG_UI_CANT_DODGE;
   public static String CONFIG_UI_CAN_COUNTER;
   public static int[] CONFIG_UI_DODGE_STAMINA_COST;
   public static int[] CONFIG_UI_COUNTER_STAMINA_COST;
   public static boolean CONFIG_UI_PERCENTAGE_STAMINA_COST;
   public static byte CONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL;
   public static final byte CHECK_EACH_LEVEL = 0;
   public static final byte CHECK_LAST_SKIPPABLE = 1;
   public static boolean CONFIG_UI_IGNORE_BASE_CONFIG;
   public static boolean CONFIG_UI_IGNORE_BASE_KI_REGEN_CONFIG;
   public static float[][] CONFIG_UI_ATTRIBUTE_MULTI_RACE;
   public static byte cCONFIG_UI_LEVELS;
   public static byte cCONFIG_UI_EXPERIENCE_PAIN_MODE;
   public static int cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION;
   public static String[] cCONFIG_UI_CHAT_SUCCEED;
   public static boolean[] cCONFIG_UI_SKIP;
   public static int[] cCONFIG_UI_LEVEL_REQUIREMENT;
   public static int[] cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT;
   public static int[] cCONFIG_UI_TP_COST;
   public static int[] cCONFIG_UI_MIND_REQUIREMENT;
   public static byte[] cCONFIG_UI_HEALTH_PERCENTAGE;
   public static int[] cCONFIG_UI_HEAT_DURATION;
   public static int[] cCONFIG_UI_PAIN_DURATION;
   public static boolean[] cCONFIG_UI_HAIR_WHITE;
   public static int[] cCONFIG_UI_ATTRIBUTE_MULTI;
   public static byte[][] cCONFIG_UI_DODGE_RATE;
   public static byte[][] cCONFIG_UI_ATTACK_RATE;
   public static int[] cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE;
   public static String cCONFIG_UI_CANT_DODGE;
   public static String cCONFIG_UI_CAN_COUNTER;
   public static int[] cCONFIG_UI_DODGE_STAMINA_COST;
   public static int[] cCONFIG_UI_COUNTER_STAMINA_COST;
   public static boolean cCONFIG_UI_PERCENTAGE_STAMINA_COST;
   public static byte cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL;
   public static boolean cCONFIG_UI_IGNORE_BASE_CONFIG;
   public static boolean cCONFIG_UI_IGNORE_BASE_KI_REGEN_CONFIG;
   public static float[][] cCONFIG_UI_ATTRIBUTE_MULTI_RACE;

   public static void init(Configuration config) {
      config.load();
      init_form_ultra_instinct(config);
      config.save();
   }

   private static void init_form_ultra_instinct(Configuration config) {
      String form = "Ultra Instinct";
      String CATEGORY = "Ultra Instinct";
      String percentage = " (Percentage)";
      String warning = " Change only to your own responsibility! Having too high multiplier will cause glitches!";
      String server = "Server Sided!";
      int min = 0;
      int max = 10;
      Property property = config.get(CATEGORY, "Ultra Instinct Levels", 3);
      property.comment = "Server Sided! Ultra Instinct Skill Levels" + getDefault("" + min, "" + max) + " (Setting to 0 disables Ultra instinct)";
      cCONFIG_UI_LEVELS = (byte)property.getInt();
      if (cCONFIG_UI_LEVELS < min) {
         cCONFIG_UI_LEVELS = (byte)min;
      } else if (cCONFIG_UI_LEVELS > max) {
         cCONFIG_UI_LEVELS = (byte)max;
      }

      CONFIG_UI_LEVELS = cCONFIG_UI_LEVELS;
      int var87 = 0;
      int var104 = 3;
      String title = "Was In Pain Requirement";
      property = config.get(CATEGORY, "Ultra Instinct " + title, 1);
      property.comment = "Server Sided! Ultra Instinct "
         + title
         + "(0 = Disable, 1 = Once, 2 = Every time) (Gives a requirement to enter a White Hair level. The player must receive the pain Status effect)";
      cCONFIG_UI_EXPERIENCE_PAIN_MODE = (byte)property.getInt();
      if (cCONFIG_UI_EXPERIENCE_PAIN_MODE < var87) {
         cCONFIG_UI_EXPERIENCE_PAIN_MODE = (byte)var87;
      } else if (cCONFIG_UI_EXPERIENCE_PAIN_MODE > var104) {
         cCONFIG_UI_EXPERIENCE_PAIN_MODE = (byte)var104;
      }

      CONFIG_UI_EXPERIENCE_PAIN_MODE = cCONFIG_UI_EXPERIENCE_PAIN_MODE;
      var87 = 0;
      var104 = 1000;
      title = "Was In Pain Reset Duration";
      property = config.get(CATEGORY, "Ultra Instinct " + title, 50);
      property.comment = "Server Sided! Ultra Instinct "
         + title
         + getDefault("" + var87, "" + var104)
         + " (Time until the Was in Pain vanishes (ONLY WORKS WITH Was in Pain MODE 2))";
      cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION = property.getInt();
      if (cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION < var87) {
         cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION = var87;
      } else if (cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION > var104) {
         cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION = var104;
      }

      CONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION = cCONFIG_UI_EXPERIENCE_PAIN_RESET_DURATION;
      String[] default01 = new String[]{"outOfWorld", "fall", "drown", "starve", "cactus", "UICounter"};
      title = "Can't dodge these Damage Sources";
      property = config.get(CATEGORY, "Ultra Instinct " + title, default01);
      property.comment = "Server Sided! " + title;
      String[] content_list1 = property.getStringList();

      for (int i = 0; i < content_list1.length; i++) {
         cCONFIG_UI_CANT_DODGE = cCONFIG_UI_CANT_DODGE + content_list1[i] + (i < content_list1.length - 1 ? ";" : "");
      }

      CONFIG_UI_CANT_DODGE = cCONFIG_UI_CANT_DODGE;
      String[] default02 = new String[]{"mob", "player"};
      title = "Can Counter these Damage Sources";
      property = config.get(CATEGORY, "Ultra Instinct " + title, default02);
      property.comment = "Server Sided! " + title;
      String[] content_list2 = property.getStringList();

      for (int i = 0; i < content_list2.length; i++) {
         cCONFIG_UI_CAN_COUNTER = cCONFIG_UI_CAN_COUNTER + content_list2[i] + (i < content_list2.length - 1 ? ";" : "");
      }

      CONFIG_UI_CAN_COUNTER = cCONFIG_UI_CAN_COUNTER;
      title = "Stamina Costs are Percentage On";
      property = config.get(CATEGORY, "Ultra Instinct " + title, true);
      property.comment = "Server Sided! " + title + " (Counter and Dodge Stamina Cost Config values will be become percentage values)";
      cCONFIG_UI_PERCENTAGE_STAMINA_COST = property.getBoolean();
      CONFIG_UI_PERCENTAGE_STAMINA_COST = cCONFIG_UI_PERCENTAGE_STAMINA_COST;
      title = "Ignore Base Attribute Multiplier Config while in UI - On";
      property = config.get(CATEGORY, "Ultra Instinct " + title, true);
      property.comment = "Server Sided! " + title;
      cCONFIG_UI_IGNORE_BASE_CONFIG = property.getBoolean();
      CONFIG_UI_IGNORE_BASE_CONFIG = cCONFIG_UI_IGNORE_BASE_CONFIG;
      title = "Ignore Base Ki Regen Config Multiplier while in UI - On";
      property = config.get(CATEGORY, "Ultra Instinct " + title, true);
      property.comment = "Server Sided! " + title;
      cCONFIG_UI_IGNORE_BASE_KI_REGEN_CONFIG = property.getBoolean();
      CONFIG_UI_IGNORE_BASE_KI_REGEN_CONFIG = cCONFIG_UI_IGNORE_BASE_KI_REGEN_CONFIG;
      var87 = 0;
      int var106 = 1;
      title = "Check Skippable Level Requirement for each level";
      property = config.get(CATEGORY, "Ultra Instinct " + title, 0);
      property.comment = "Server Sided! Ultra Instinct "
         + title
         + getDefault("" + var87, "" + var106)
         + " (0 - Checks each level | 1 - Checks last skippable level only)";
      cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL = (byte)property.getInt();
      if (cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL < var87) {
         cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL = (byte)var87;
      } else if (cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL > var106) {
         cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL = (byte)var106;
      }

      CONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL = cCONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL;
      cCONFIG_UI_CHAT_SUCCEED = new String[CONFIG_UI_LEVELS];
      cCONFIG_UI_SKIP = new boolean[CONFIG_UI_LEVELS];
      cCONFIG_UI_LEVEL_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_TP_COST = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_MIND_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_HEALTH_PERCENTAGE = new byte[CONFIG_UI_LEVELS];
      cCONFIG_UI_HEAT_DURATION = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_PAIN_DURATION = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_HAIR_WHITE = new boolean[CONFIG_UI_LEVELS];
      cCONFIG_UI_ATTRIBUTE_MULTI = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_DODGE_RATE = new byte[CONFIG_UI_LEVELS][2];
      cCONFIG_UI_ATTACK_RATE = new byte[CONFIG_UI_LEVELS][2];
      cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_DODGE_STAMINA_COST = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_COUNTER_STAMINA_COST = new int[CONFIG_UI_LEVELS];
      cCONFIG_UI_ATTRIBUTE_MULTI_RACE = new float[CONFIG_UI_LEVELS][JRMCoreH.Races.length];
      CONFIG_UI_CHAT_SUCCEED = new String[CONFIG_UI_LEVELS];
      CONFIG_UI_SKIP = new boolean[CONFIG_UI_LEVELS];
      CONFIG_UI_LEVEL_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_TP_COST = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_MIND_REQUIREMENT = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_HEALTH_PERCENTAGE = new byte[CONFIG_UI_LEVELS];
      CONFIG_UI_HEAT_DURATION = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_PAIN_DURATION = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_HAIR_WHITE = new boolean[CONFIG_UI_LEVELS];
      CONFIG_UI_ATTRIBUTE_MULTI = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_DODGE_RATE = new byte[CONFIG_UI_LEVELS][2];
      CONFIG_UI_ATTACK_RATE = new byte[CONFIG_UI_LEVELS][2];
      CONFIG_UI_ATTACK_DAMAGE_PERCENTAGE = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_DODGE_STAMINA_COST = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_COUNTER_STAMINA_COST = new int[CONFIG_UI_LEVELS];
      CONFIG_UI_ATTRIBUTE_MULTI_RACE = new float[CONFIG_UI_LEVELS][JRMCoreH.Races.length];
      int max_id = 2;

      for (int i = 0; i < CONFIG_UI_LEVELS; i++) {
         int level_id = i + 1;
         CATEGORY = "Ultra Instinct Level " + level_id;
         String[] default00 = new String[]{"A sign of Ultra Instinct", "@p is Near completion", "This is Ultra Instinct"};
         title = "Successful transformation Chat Message";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default00[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (Percentage)" + " (Writes the message out for the player only)";
         cCONFIG_UI_CHAT_SUCCEED[i] = property.getString();
         CONFIG_UI_CHAT_SUCCEED[i] = cCONFIG_UI_CHAT_SUCCEED[i];
         boolean[] default0 = new boolean[]{true, false, false};
         title = "Skippable Level On";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default0[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (If the user has learned a higher level then it will jump straight to it)";
         cCONFIG_UI_SKIP[i] = property.getBoolean();
         CONFIG_UI_SKIP[i] = cCONFIG_UI_SKIP[i];
         var87 = 0;
         var106 = 1000000;
         int[] default1 = new int[]{200, 250, 300};
         title = "Level Requirement";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default1[getID(i, max_id)]);
         property.comment = "Server Sided! " + title;
         cCONFIG_UI_LEVEL_REQUIREMENT[i] = property.getInt();
         if (cCONFIG_UI_LEVEL_REQUIREMENT[i] < var87) {
            cCONFIG_UI_LEVEL_REQUIREMENT[i] = var87;
         }

         CONFIG_UI_LEVEL_REQUIREMENT[i] = cCONFIG_UI_LEVEL_REQUIREMENT[i];
         var87 = 0;
         int var108 = 100;
         int[] default2 = new int[]{10, 8, 6};
         title = "Regardless Level Requirement";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default2[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (Percentage)" + getDefault("" + var87, "" + var108);
         cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] = property.getInt();
         if (cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] < var87) {
            cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] = var87;
         } else if (cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] > var108) {
            cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] = var108;
         }

         CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i] = cCONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[i];
         var87 = -1;
         var108 = 2000000000;
         int[] default3 = new int[]{50000, 25000, 25000};
         title = "TP Cost";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default3[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + getDefault("" + default3[getID(i, max_id)]);
         cCONFIG_UI_TP_COST[i] = property.getInt();
         if (cCONFIG_UI_TP_COST[i] < var87) {
            cCONFIG_UI_TP_COST[i] = var87;
         }

         CONFIG_UI_TP_COST[i] = cCONFIG_UI_TP_COST[i];
         var87 = 0;
         var108 = 2000000000;
         default3 = new int[]{10, 5, 5};
         title = "Mind Cost/Requirement";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default3[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + getDefault("" + default3[getID(i, max_id)]);
         cCONFIG_UI_MIND_REQUIREMENT[i] = property.getInt();
         if (cCONFIG_UI_MIND_REQUIREMENT[i] < var87) {
            cCONFIG_UI_MIND_REQUIREMENT[i] = var87;
         }

         CONFIG_UI_MIND_REQUIREMENT[i] = cCONFIG_UI_MIND_REQUIREMENT[i];
         var87 = 0;
         int var111 = 100;
         int[] default5 = new int[]{60, 50, 40};
         title = "Health Requirement";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default5[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (Percentage)" + getDefault("" + var87, "" + var111);
         cCONFIG_UI_HEALTH_PERCENTAGE[i] = (byte)property.getInt();
         if (cCONFIG_UI_HEALTH_PERCENTAGE[i] < var87) {
            cCONFIG_UI_HEALTH_PERCENTAGE[i] = (byte)var87;
         } else if (cCONFIG_UI_HEALTH_PERCENTAGE[i] > var111) {
            cCONFIG_UI_HEALTH_PERCENTAGE[i] = (byte)var111;
         }

         CONFIG_UI_HEALTH_PERCENTAGE[i] = cCONFIG_UI_HEALTH_PERCENTAGE[i];
         var87 = 0;
         var111 = (short)1000;
         int[] default6 = new int[]{150, 100, 75};
         title = "Heat Timer";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default6[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + getDefault("" + var87, "" + var111);
         cCONFIG_UI_HEAT_DURATION[i] = property.getInt();
         if (cCONFIG_UI_HEAT_DURATION[i] < var87) {
            cCONFIG_UI_HEAT_DURATION[i] = var87;
         } else if (cCONFIG_UI_HEAT_DURATION[i] > var111) {
            cCONFIG_UI_HEAT_DURATION[i] = var111;
         }

         CONFIG_UI_HEAT_DURATION[i] = cCONFIG_UI_HEAT_DURATION[i];
         var87 = 0;
         var111 = (short)1000;
         int[] default7 = new int[]{10, 15, 20};
         title = "Pain Duration";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default7[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + getDefault("" + var87, "" + var111);
         cCONFIG_UI_PAIN_DURATION[i] = property.getInt();
         if (cCONFIG_UI_PAIN_DURATION[i] < var87) {
            cCONFIG_UI_PAIN_DURATION[i] = var87;
         } else if (cCONFIG_UI_PAIN_DURATION[i] > var111) {
            cCONFIG_UI_PAIN_DURATION[i] = var111;
         }

         CONFIG_UI_PAIN_DURATION[i] = cCONFIG_UI_PAIN_DURATION[i];
         boolean[] default8 = new boolean[]{false, false, true};
         title = "White Hair On";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default8[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (This will also set whenever the form is the Complete version of Ultra Instinct)";
         cCONFIG_UI_HAIR_WHITE[i] = property.getBoolean();
         CONFIG_UI_HAIR_WHITE[i] = cCONFIG_UI_HAIR_WHITE[i];
         var87 = 0;
         var111 = 100000;
         int[] default9 = new int[]{370, 400, 440};
         title = "Attribute Multiplier";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default9[getID(i, max_id)]);
         property.comment = "Server Sided! "
            + title
            + " (Percentage)"
            + " Change only to your own responsibility! Having too high multiplier will cause glitches!"
            + getDefault("" + var87, "" + var111);
         cCONFIG_UI_ATTRIBUTE_MULTI[i] = property.getInt();
         if (cCONFIG_UI_ATTRIBUTE_MULTI[i] < var87) {
            cCONFIG_UI_ATTRIBUTE_MULTI[i] = var87;
         } else if (cCONFIG_UI_ATTRIBUTE_MULTI[i] > var111) {
            cCONFIG_UI_ATTRIBUTE_MULTI[i] = var111;
         }

         CONFIG_UI_ATTRIBUTE_MULTI[i] = cCONFIG_UI_ATTRIBUTE_MULTI[i];
         var87 = 0;
         var111 = 100000;
         String defS = "";
         String[] default13 = new String[]{"1.0", "1.0", "1.0", "1.0", "1.0", "0.75"};

         for (int j = 0; j < default13.length; j++) {
            default13[j] = JRMCoreH.Races[j] + " " + default13[j];
            defS = defS + default13[j];
         }

         title = "Attribute Multiplier per Race";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default13);
         property.comment = "Server Sided! "
            + title
            + " (Percentage)"
            + " Change only to your own responsibility! Having too high multiplier will cause glitches!"
            + getDefault(defS);
         String[] listString = property.getStringList();

         for (int j = 0; j < listString.length; j++) {
            String[] values = listString[j].split(" ");
            float value = Float.parseFloat(values[1]);
            cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] = value;
            if (cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] < var87) {
               cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] = var87;
            } else if (cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] > var111) {
               cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] = var111;
            }

            CONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] = cCONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j];
         }

         var87 = 0;
         int var116 = 1000;
         int[] default10 = new int[]{0, 0, 80};
         title = "Counter Attack Damage";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default10[getID(i, max_id)]);
         property.comment = "Server Sided! " + title + " (Percentage)" + getDefault("" + var87, "" + var116);
         cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] = property.getInt();
         if (cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] < var87) {
            cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] = var87;
         } else if (cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] > var116) {
            cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] = var116;
         }

         CONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i] = cCONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[i];
         var87 = 0;
         var116 = 1000;
         int[] default11 = new int[]{0, 0, 0};
         title = "Dodge Stamina Cost";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default11[getID(i, max_id)]);
         property.comment = "Server Sided! "
            + title
            + " [IF 'Stamina Costs are Percentage On' is true then "
            + getDefault("" + var87, "100")
            + " OTHERWISE "
            + getDefault("" + var87, "" + var116)
            + "]";
         cCONFIG_UI_DODGE_STAMINA_COST[i] = property.getInt();
         if (cCONFIG_UI_DODGE_STAMINA_COST[i] < var87) {
            cCONFIG_UI_DODGE_STAMINA_COST[i] = var87;
         }

         CONFIG_UI_DODGE_STAMINA_COST[i] = cCONFIG_UI_DODGE_STAMINA_COST[i];
         var87 = 0;
         var116 = 1000;
         int[] default12 = new int[]{0, 0, 0};
         title = "Counter Attack Stamina Cost";
         property = config.get(CATEGORY, "Ultra Instinct " + title, default12[getID(i, max_id)]);
         property.comment = "Server Sided! "
            + title
            + " [IF 'Stamina Costs are Percentage On' is true then "
            + getDefault("" + var87, "100")
            + " OTHERWISE "
            + getDefault("" + var87, "" + var116)
            + "]";
         cCONFIG_UI_COUNTER_STAMINA_COST[i] = property.getInt();
         if (cCONFIG_UI_COUNTER_STAMINA_COST[i] < var87) {
            cCONFIG_UI_COUNTER_STAMINA_COST[i] = var87;
         }

         CONFIG_UI_COUNTER_STAMINA_COST[i] = cCONFIG_UI_COUNTER_STAMINA_COST[i];

         for (int j = 0; j < 2; j++) {
            String[][] dodge_rates = new String[][]{{"10", "40"}, {"15", "60"}, {"20", "80"}};
            String[][] attack_rates = new String[][]{{"0", "0"}, {"0", "0"}, {"10", "30"}};
            var87 = 0;
            int var119 = 100;
            title = "Dodge Rate";
            property = config.get(
               CATEGORY,
               "Ultra Instinct " + title,
               dodge_rates[getID(i, max_id)],
               "Server Sided! " + title + " (Percentage)" + " (Min Max)" + getDefault("" + var87, "" + var119)
            );
            int[] list = property.getIntList();
            cCONFIG_UI_DODGE_RATE[i][j] = (byte)list[j];
            if (cCONFIG_UI_DODGE_RATE[i][j] < var87) {
               cCONFIG_UI_DODGE_RATE[i][j] = (byte)var87;
            } else if (cCONFIG_UI_DODGE_RATE[i][j] > var119) {
               cCONFIG_UI_DODGE_RATE[i][j] = (byte)var119;
            }

            CONFIG_UI_DODGE_RATE[i][j] = cCONFIG_UI_DODGE_RATE[i][j];
            var87 = 0;
            var119 = 100;
            title = "Counter Attack Rate";
            property = config.get(
               CATEGORY,
               "Ultra Instinct " + title,
               attack_rates[getID(i, max_id)],
               "Server Sided! " + title + " (Percentage)" + " (Min Max)" + getDefault("" + var87, "" + var119)
            );
            list = property.getIntList();
            cCONFIG_UI_ATTACK_RATE[i][j] = (byte)list[j];
            if (cCONFIG_UI_ATTACK_RATE[i][j] < var87) {
               cCONFIG_UI_ATTACK_RATE[i][j] = (byte)var87;
            } else if (cCONFIG_UI_ATTACK_RATE[i][j] > var119) {
               cCONFIG_UI_ATTACK_RATE[i][j] = (byte)var119;
            }

            CONFIG_UI_ATTACK_RATE[i][j] = cCONFIG_UI_ATTACK_RATE[i][j];
         }
      }
   }
}
