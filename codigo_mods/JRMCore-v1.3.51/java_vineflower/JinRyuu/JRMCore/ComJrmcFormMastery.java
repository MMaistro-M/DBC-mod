package JinRyuu.JRMCore;

import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComJrmcFormMastery extends CommandBase {
   public static final ChatStyle styleYellow = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
   public static final ChatStyle styleRed = new ChatStyle().func_150238_a(EnumChatFormatting.RED);
   private final String name = "jrmcformmastery";
   private final String[] IS_RACIAL = new String[]{"true", "false"};
   private final String[] FORM_ID = new String[]{
      "current", "all", "0", "1", "2", "Base", JRMCoreH.transNonRacial[0], JRMCoreH.transNonRacial[1], JRMCoreH.transNonRacial[2], JRMCoreH.transNonRacial[3]
   };
   private final String[] MODE = new String[]{"add", "set"};
   private final String[] AMOUNT = new String[]{"1.0", "100", "-1.0"};

   public String func_71517_b() {
      return "jrmcformmastery";
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcformmastery [playerName] (Add/Set) (formName or nonRacialFormID) (Amount)";
   }

   public int func_82362_a() {
      return 2;
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.getListOfPlayers());
         case 2:
            return func_71530_a(stringArray, this.MODE);
         case 3:
            return func_71530_a(stringArray, this.FORM_ID);
         case 4:
            return func_71530_a(stringArray, this.AMOUNT);
         default:
            return null;
      }
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 0;
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
      func_152373_a(commandSender, this, string, objects);
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      if (!JGConfigDBCFormMastery.FM_Enabled) {
         commandSender.func_145747_a(new ChatComponentText("Form Masteries are disabled!").func_150255_a(styleRed));
      } else {
         if (stringArray.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
         }

         EntityPlayerMP player;
         if (stringArray.length > 0) {
            player = func_82359_c(commandSender, stringArray[0]);
         } else {
            player = func_71521_c(commandSender);
         }

         JGPlayerMP jgPlayer = new JGPlayerMP(player);
         jgPlayer.connectBaseNBT();
         int race = jgPlayer.getRace();
         String mode = stringArray[1].toLowerCase();
         boolean modeAdd = mode.equals("add");
         boolean modeChange = mode.equals("set") || mode.equals("change");
         if (player != null && (modeAdd || modeChange) && !JRMCoreH.isFused(player)) {
            boolean isRacial = true;
            String formName = stringArray[2].toLowerCase();
            if (formName.equals("current")) {
               formName = "-1";
            } else if (formName.equals("all")) {
               formName = "-2";
            } else {
               int id = 0;
               boolean found = false;

               for (String form : JRMCoreH.transNonRacial) {
                  if (form.toLowerCase().equals(formName)) {
                     formName = "" + id;
                     isRacial = false;
                     found = true;
                     break;
                  }

                  id++;
               }

               if (!found) {
                  id = 0;

                  for (String form : JRMCoreH.trans[race]) {
                     if (form.toLowerCase().equals(formName)) {
                        formName = "" + id;
                        isRacial = true;
                        break;
                     }

                     id++;
                  }
               }
            }

            int formID = Integer.parseInt(formName);
            if (formID < -2) {
               formID = -2;
            }

            if (formID >= (isRacial ? JRMCoreH.trans[race].length : JRMCoreH.transNonRacial.length)) {
               formID = (isRacial ? JRMCoreH.trans[race].length : JRMCoreH.transNonRacial.length) - 1;
            }

            double amount = Double.parseDouble(stringArray[3]);
            int powerType = jgPlayer.getPowerType();
            if (!JRMCoreH.isPowerTypeKi(powerType)) {
               commandSender.func_145747_a(new ChatComponentText("Form Masteries are only available for Ki Powertype Players!").func_150255_a(styleRed));
               return;
            }

            if (formID == -1) {
               int state = jgPlayer.getState();
               int state2 = jgPlayer.getState2();
               String statusEffects = jgPlayer.getStatusEffects();
               boolean isKaiokenOn = jgPlayer.hasStatusEffect(5, statusEffects);
               boolean isMysticOn = jgPlayer.hasStatusEffect(13, statusEffects);
               boolean isUltraInstinctOn = jgPlayer.hasStatusEffect(19, statusEffects);
               boolean isGoDOn = jgPlayer.hasStatusEffect(20, statusEffects);
               JRMCoreH.changeFormMasteriesValue(player, amount, amount, modeAdd, race, state, state2, isKaiokenOn, isMysticOn, isUltraInstinctOn, isGoDOn, -1);
               commandSender.func_145747_a(
                  new ChatComponentText("Form Mastery Points " + (modeAdd ? "Received" : "Set to") + ": " + amount + " Form: " + stringArray[2] + "!")
                     .func_150255_a(styleYellow)
               );
            } else if (formID == -2) {
               String[] NBT = JRMCoreH.getNBTFormMasteryRacialKeys(race);
               String[][] forms = new String[NBT.length][];
               int i = 0;

               for (String nbtKey : NBT) {
                  String mastery = jgPlayer.getNBT().func_74779_i(nbtKey);
                  boolean hasNBTOther = jgPlayer.getNBT().func_74764_b(nbtKey)
                     && !jgPlayer.getNBT().func_74779_i(nbtKey).equals("Base,0")
                     && !jgPlayer.getNBT().func_74779_i(nbtKey).equals("Kaioken,0");
                  if (!hasNBTOther) {
                     boolean isRacialOther = nbtKey.equals(JRMCoreH.getNBTFormMasteryRacialKey(race));
                     mastery = isRacialOther ? JRMCoreH.getDefaultFormMasteryRacialText(race) : JRMCoreH.getDefaultFormMasteryNonRacialText();
                     jgPlayer.getNBT().func_74778_a(nbtKey, mastery);
                  }

                  forms[i] = mastery.split(";");
                  int j = 0;

                  for (String form : forms[i]) {
                     String[] formValues = form.split(",");
                     double value = amount;
                     if (modeAdd) {
                        value += Double.parseDouble(formValues[1]);
                     }

                     double FM_LevelMax = JGConfigDBCFormMastery.getDouble(race, j, JGConfigDBCFormMastery.DATA_ID_MAX_LEVEL);
                     if (value > FM_LevelMax) {
                        value = FM_LevelMax;
                     }

                     forms[i][j] = formValues[0] + "," + (value == (int)value ? (int)value : value);
                     j++;
                  }

                  String result = "";
                  j = 0;

                  for (String form : forms[i]) {
                     result = result + form + (j + 1 < forms[i].length ? ";" : "");
                     j++;
                  }

                  jgPlayer.getNBT().func_74778_a(nbtKey, result);
                  i++;
               }

               commandSender.func_145747_a(
                  new ChatComponentText("Form Mastery Points " + (modeAdd ? "Received" : "Set to") + ": " + amount + " All Forms!").func_150255_a(styleYellow)
               );
            } else {
               JRMCoreH.changeFormMasteryValue(player, amount, modeAdd, race, formID + (!isRacial ? JRMCoreH.trans[race].length : 0), isRacial, -1);
               commandSender.func_145747_a(
                  new ChatComponentText("Form Mastery Points " + (modeAdd ? "Received" : "Set to") + ": " + amount + " Form: " + stringArray[2] + "!")
                     .func_150255_a(styleYellow)
               );
            }
         }
      }
   }
}
