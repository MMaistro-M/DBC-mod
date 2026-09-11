package JinRyuu.JRMCore;

import JinRyuu.JRMCore.server.JGMathHelper;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComJrmcaBonus extends CommandBase {
   public static final String NBT1 = "nbt_";
   public static final String NBT2 = "NBT_";
   private final String name = "jrmcabonus";
   private final byte MODE_ADD = 0;
   private final byte MODE_ADD_TO = 1;
   private final byte MODE_SET = 2;
   private final byte MODE_GET = 3;
   private final byte MODE_REMOVE = 4;
   private final byte MODE_CLEAR = 5;
   private final byte MODE_BAD_MODE = -1;
   private final byte MODE_STR = 0;
   private final byte MODE_DEX = 1;
   private final byte MODE_CON = 2;
   private final byte MODE_WILL = 3;
   private final byte MODE_MIND = 4;
   private final byte MODE_SPI = 5;
   private final String[] ATTRIBUTES_LONG = new String[]{"strength", "dexterity", "constitution", "willpower", "mind", "spirit"};
   public static final String[] ATTRIBUTES_SHORT = new String[]{"str", "dex", "con", "wil", "mnd", "spi"};
   private final String[] MODES = new String[]{"add", "addto", "set", "get", "remove", "clear"};
   private final String[] OPERATIONS = new String[]{"+1", "-1", "*1", "/1", "%1", "nbt_nbtValueName"};
   private final String[] END = new String[]{"true", "false"};

   public String func_71517_b() {
      return "jrmcabonus";
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcabonus (Add/AddTo/Set/Get/Remove/clear) (Attribute) (BonusName or ID) (Math Operation - Amount (USE IF: mode is ADD or SET!)) (Add To Top/Start(false) or Bottom/End(true) of the List (USE IF: mode is ADD)) [playerName]. Attribute can be Strength, Dexterity, Constitution, Willpower, Mind, Spirit. Example: '/jrmcabonus add Strength saiyanRage *2.0 false @p' OR '/jrmcabonus get Strength'";
   }

   public int func_82362_a() {
      return 2;
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      String modeSting = stringArray[0].toLowerCase();
      byte mode = (byte)(modeSting.equals("add")
         ? 0
         : (
            modeSting.equals("addto")
               ? 1
               : (
                  modeSting.equals("set")
                     ? 2
                     : (modeSting.equals("get") ? 3 : (modeSting.equals("clear") ? 5 : (!modeSting.equals("remove") && !modeSting.equals("delete") ? -1 : 4)))
               )
         ));
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.MODES);
         case 2:
            return func_71530_a(stringArray, this.ATTRIBUTES_LONG);
         case 3:
            return func_71530_a(stringArray, mode != 3 && mode != 5 ? new String[]{"nameid"} : this.getListOfPlayers());
         case 4:
            return mode != 3 && mode != 5 ? func_71530_a(stringArray, this.OPERATIONS) : null;
         case 5:
            return mode != 3 && mode != 5 ? func_71530_a(stringArray, mode == 0 ? this.END : this.getListOfPlayers()) : null;
         case 6:
            return mode == 0 ? func_71530_a(stringArray, this.getListOfPlayers()) : null;
         default:
            return null;
      }
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   private NBTTagCompound nbt(EntityPlayer p, String s) {
      return JRMCoreH.nbt(p, s);
   }

   private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
      func_152373_a(commandSender, this, string, objects);
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      if (stringArray.length <= 0) {
         throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
      }

      String modeSting = stringArray[0].toLowerCase();
      String attributeString = stringArray[1].toLowerCase();
      String bonusNameIDString = "";
      String bonusAmountString = "";
      String endOfTheList = "";
      byte mode = (byte)(modeSting.equals("add")
         ? 0
         : (
            modeSting.equals("addto")
               ? 1
               : (
                  modeSting.equals("set")
                     ? 2
                     : (modeSting.equals("get") ? 3 : (modeSting.equals("clear") ? 5 : (!modeSting.equals("remove") && !modeSting.equals("delete") ? -1 : 4)))
               )
         ));
      byte attribute = 0;

      for (byte i = 0; i < this.ATTRIBUTES_LONG.length; i++) {
         if (attributeString.equals(this.ATTRIBUTES_LONG[i]) || attributeString.equals(ATTRIBUTES_SHORT[i])) {
            attribute = i;
         }
      }

      if (mode == 0 || mode == 2 || mode == 4 || mode == 1) {
         bonusNameIDString = stringArray[2];
         if (mode == 0 || mode == 2 || mode == 1) {
            bonusAmountString = stringArray[3];
            if (mode == 0) {
               endOfTheList = stringArray[4].toLowerCase();
            }
         }
      }

      int playerNameID = mode != 3 && mode != 5 ? (mode == 4 ? 3 : (mode == 0 ? 5 : 4)) : 2;
      EntityPlayerMP entityplayermp;
      if (stringArray.length > playerNameID) {
         entityplayermp = func_82359_c(commandSender, stringArray[playerNameID]);
      } else {
         entityplayermp = func_71521_c(commandSender);
      }

      String entitycommansender = "Console";
      EntityPlayerMP commansender = null;

      try {
         commansender = func_71521_c(commandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var36) {
      }

      boolean notify = entitycommansender.equals("Console")
         ? JRMCoreConfig.CommandNotifyAdminJRMCABonusConsole
         : (
            entitycommansender.equals(entityplayermp.func_70005_c_())
               ? JRMCoreConfig.CommandNotifyAdminJRMCABonusSelf
               : JRMCoreConfig.CommandNotifyAdminJRMCABonusOthers
         );
      boolean run = false;
      NBTTagCompound nbt = this.nbt(entityplayermp, "pres");
      String startString = nbt.func_74779_i("jrmcAttrBonus" + ATTRIBUTES_SHORT[attribute]);
      String[] bonus = startString.split("\\|");
      String[][] bonusValues = new String[bonus.length][2];
      if (bonus.length > 0 && bonus[0].length() > 0) {
         for (int i = 0; i < bonus.length; i++) {
            String[] bonusValue = bonus[i].split("\\;");
            bonusValues[i][0] = bonusValue[0];
            bonusValues[i][1] = bonusValue[1];
         }
      }

      if (mode == 0) {
         boolean doesItContainElement = false;

         for (int i = 0; i < bonus.length; i++) {
            String[] bonusValue = bonus[i].split("\\;");
            bonusValues[i][0] = bonusValue[0];
            if (bonusValues[i][0].equals(bonusNameIDString)) {
               doesItContainElement = true;
               break;
            }
         }

         if (!doesItContainElement) {
            boolean addToEnd = Boolean.parseBoolean(endOfTheList);
            if (addToEnd) {
               String data = bonusNameIDString + ";" + bonusAmountString;
               if (startString.length() == 0) {
                  startString = data;
               } else {
                  startString = startString + "|" + data;
               }
            } else {
               String data = bonusNameIDString + ";" + bonusAmountString;
               if (startString.length() == 0) {
                  startString = data;
               } else {
                  startString = data + "|" + startString;
               }
            }

            bonus = startString.split("\\|");
            bonusValues = new String[bonus.length][2];

            for (int i = 0; i < bonus.length; i++) {
               String[] bonusValue = bonus[i].split("\\;");
               bonusValues[i][0] = bonusValue[0];
               bonusValues[i][1] = bonusValue[1];
            }

            run = true;
         } else if (notify) {
            this.notifyAdmins(
               commandSender,
               "Bonus Attribute Failed to be added for "
                  + entityplayermp.func_70005_c_()
                  + ", Reason: Bonus list already contains a value with this ID: "
                  + bonusNameIDString,
               new Object[0]
            );
         }
      } else if (mode == 1) {
         boolean nbtFail = false;
         int id = -1;

         boolean number;
         try {
            id = Integer.parseInt(bonusNameIDString);
            number = true;
         } catch (Exception e) {
            number = false;
         }

         for (int i = 0; i < bonus.length; i++) {
            String[] bonusValue = bonus[i].split("\\;");
            bonusValues[i][0] = bonusValue[0];
            if (number && i == id || !number && bonusValues[i][0].equals(bonusNameIDString)) {
               if (!bonusValues[i][1].contains("nbt_")
                  && !bonusValues[i][1].contains("NBT_")
                  && !bonusAmountString.contains("nbt_")
                  && !bonusAmountString.contains("NBT_")) {
                  double value = Double.parseDouble(bonusValues[i][1].substring(1));
                  double value2 = Double.parseDouble(bonusAmountString.substring(1));
                  double resultValue = JGMathHelper.StringMethod(bonusAmountString.substring(0, 1), value, value2);
                  String result = bonusValues[i][1].substring(0, 1) + resultValue;
                  String data = bonusValues[i][0] + ";" + result;
                  bonus[i] = data;
                  bonusValue = bonus[i].split("\\;");
                  bonusValues[i][0] = bonusValue[0];
                  bonusValues[i][1] = bonusValue[1];
                  run = true;
               } else {
                  nbtFail = true;
               }
               break;
            }
         }

         if (!run && notify) {
            if (nbtFail) {
               this.notifyAdmins(
                  commandSender,
                  "Bonus Attribute Failed to be added to for " + entityplayermp.func_70005_c_() + ", Reason: One of the Bonus values is an NBT value name.",
                  new Object[0]
               );
            } else {
               this.notifyAdmins(
                  commandSender,
                  "Bonus Attribute Failed to be added to for "
                     + entityplayermp.func_70005_c_()
                     + ", Reason: Bonus list didn't contain a value with this ID: "
                     + bonusNameIDString,
                  new Object[0]
               );
            }
         }
      } else if (mode == 2) {
         int id = -1;

         boolean number;
         try {
            id = Integer.parseInt(bonusNameIDString);
            number = true;
         } catch (Exception e) {
            number = false;
         }

         for (int i = 0; i < bonus.length; i++) {
            String[] bonusValue = bonus[i].split("\\;");
            bonusValues[i][0] = bonusValue[0];
            if (number && i == id || !number && bonusValues[i][0].equals(bonusNameIDString)) {
               String data = bonusValues[i][0] + ";" + bonusAmountString;
               bonus[i] = "";
               run = true;
               bonus[i] = data;
               bonusValue = bonus[i].split("\\;");
               bonusValues[i][0] = bonusValue[0];
               bonusValues[i][1] = bonusValue[1];
               break;
            }
         }

         if (!run && notify) {
            this.notifyAdmins(
               commandSender,
               "Bonus Attribute Failed to be added to for "
                  + entityplayermp.func_70005_c_()
                  + ", Reason: Bonus list didn't contain a value with this "
                  + (number ? "ID" : "Name")
                  + ": "
                  + bonusNameIDString,
               new Object[0]
            );
         }
      } else if (mode == 3) {
         ChatStyle colorG = new ChatStyle().func_150238_a(EnumChatFormatting.GOLD);
         ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
         if (commansender != null) {
            commansender.func_145747_a(
               new ChatComponentText(entityplayermp.getDisplayName() + " " + this.ATTRIBUTES_LONG[attribute] + " Bonus Attributes:").func_150255_a(colorG)
            );
         } else if (entitycommansender.equals("Console")) {
            JRMCoreH.log(entityplayermp.getDisplayName() + " " + this.ATTRIBUTES_LONG[attribute] + " Bonus Attributes:");
         }

         if (bonus.length > 0 && bonus[0].length() > 0) {
            for (int i = 0; i < bonus.length; i++) {
               String[] bonusValue = bonus[i].split("\\;");
               bonusValues[i][0] = bonusValue[0];
               bonusValues[i][1] = bonusValue[1];
               if (bonusValues[i][1].contains("nbt_") || bonusValues[i][1].contains("NBT_")) {
                  String noNBTText = bonusValues[i][1].replace("nbt_", "").replace("NBT_", "");
                  double value = nbt.func_74769_h(noNBTText.substring(1));
                  bonusValues[i][1] = noNBTText.substring(0, 1) + "(" + value + ")";
               }

               if (commansender != null) {
                  commansender.func_145747_a(new ChatComponentText("ID " + bonusValues[i][0] + " | VALUE: " + bonusValues[i][1]).func_150255_a(color));
               } else if (entitycommansender.equals("Console")) {
                  JRMCoreH.log("ID " + bonusValues[i][0] + " | VALUE: " + bonusValues[i][1]);
               }
            }
         } else if (commansender != null) {
            commansender.func_145747_a(new ChatComponentText("EMPTY").func_150255_a(color));
         } else if (entitycommansender.equals("Console")) {
            JRMCoreH.log("EMPTY");
         }
      } else if (mode == 4) {
         int id = -1;

         boolean number;
         try {
            id = Integer.parseInt(bonusNameIDString);
            number = true;
         } catch (Exception e) {
            number = false;
         }

         for (int i = 0; i < bonus.length; i++) {
            String[] bonusValue = bonus[i].split("\\;");
            bonusValues[i][0] = bonusValue[0];
            if (number && i == id || !number && bonusValues[i][0].equals(bonusNameIDString)) {
               bonus[i] = "";
               run = true;
               break;
            }
         }

         if (!run && notify) {
            this.notifyAdmins(
               commandSender,
               "Bonus Attribute Failed to be added to for "
                  + entityplayermp.func_70005_c_()
                  + ", Reason: Bonus list didn't contain a value with this "
                  + (number ? "ID" : "Name")
                  + ": "
                  + bonusNameIDString,
               new Object[0]
            );
         }
      } else if (mode == 5) {
         for (int i = 0; i < bonus.length; i++) {
            bonus[i] = "";
         }

         run = true;
      }

      if (run) {
         startString = "";

         for (int i = 0; i < bonus.length; i++) {
            if (bonus[i] != null && bonus[i].length() > 0) {
               startString = startString + bonus[i] + (bonus.length - 1 == i ? "" : "|");
            }
         }

         nbt.func_74778_a("jrmcAttrBonus" + ATTRIBUTES_SHORT[attribute], startString);
      }

      if (notify && run) {
         this.notifyAdmins(
            commandSender, "Bonus Attribute " + this.MODES[mode].toUpperCase() + " finished for " + entityplayermp.func_70005_c_(), new Object[0]
         );
      }

      if (!JRMCoreConfig.JRMCABonusOn) {
         if (commansender != null) {
            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.RED);
            commansender.func_145747_a(new ChatComponentText("JRMCABonus Attributes are DISABLED in the configs!").func_150255_a(color));
         } else if (entitycommansender.equals("Console")) {
            JRMCoreH.log("JRMCABonus Attributes are DISABLED in the configs!");
         }
      }
   }
}
