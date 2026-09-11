package JinRyuu.JRMCore;

import JinRyuu.JRMCore.server.JGPlayerMP;
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

public class ComJrmcFormMasteryCheck extends CommandBase {
   private final String name = "jrmcformmasterycheck";

   public String func_71517_b() {
      return "jrmcformmasterycheck";
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcformmasterycheck [playerName]. ";
   }

   public int func_82362_a() {
      return 0;
   }

   public boolean func_71519_b(ICommandSender par1ICommandSender) {
      return true;
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.getListOfPlayers());
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

      int playerNameID = 0;
      EntityPlayerMP player;
      if (stringArray.length > 0) {
         player = func_82359_c(commandSender, stringArray[0]);
      } else {
         player = func_71521_c(commandSender);
      }

      String entitycommansender = "Console";
      EntityPlayerMP commansender = null;

      try {
         commansender = func_71521_c(commandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var19) {
      }

      if (player != null) {
         JGPlayerMP playerMP = new JGPlayerMP(player);
         NBTTagCompound nbt = this.nbt(player, "pres");
         playerMP.setNBT(nbt);
         int race = playerMP.getRace();
         String[] masteries = JRMCoreH.getFormMasteryData(player).split(";");
         String masteryValues = "[Form Mastery Points]:";
         int length = masteries.length;
         int i = 0;

         for (String s : masteries) {
            String[] values = s.split(",");
            if (!JRMCoreH.isRaceSaiyan(race) || !values[0].equals(JRMCoreH.trans[race][12]) && !values[0].equals(JRMCoreH.trans[race][13])) {
               masteryValues = masteryValues + " (" + values[0] + " Lvl: " + values[1] + ")" + (i + 1 < length ? "," : "");
               i++;
            } else {
               i++;
            }
         }

         if (commansender != null) {
            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
            commansender.func_145747_a(new ChatComponentText(masteryValues).func_150255_a(color));
         } else {
            JRMCoreH.log(masteryValues);
         }
      }
   }
}
