package JinRyuu.JRMCore;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class ComJrmcRei extends CommandBase {
   private final String[] VALUES = new String[]{"1"};
   private final String[] BOOLEANS = new String[]{"true", "false"};

   public String func_71517_b() {
      return "jrmcrei";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      if (stringArray.length < 3) {
         throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
      }

      EntityPlayerMP entityplayermp;
      if (stringArray.length > 0) {
         entityplayermp = func_82359_c(commandSender, stringArray[0]);
      } else {
         entityplayermp = func_71521_c(commandSender);
      }

      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(commandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var12) {
      }

      boolean n = entitycommansender.equals("Console")
         ? JRMCoreConfig.ComSENAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComSENAS : JRMCoreConfig.ComSENAO);
      boolean keepSkills = false;
      if (stringArray.length > 2) {
         keepSkills = stringArray[2].toLowerCase().contentEquals("true");
      }

      boolean keepTechs = false;
      if (stringArray.length > 3) {
         keepTechs = stringArray[3].toLowerCase().contentEquals("true");
      }

      boolean keepMasteries = false;
      if (stringArray.length > 4) {
         keepMasteries = stringArray[4].toLowerCase().contentEquals("true");
      }

      float per = 0.1F;
      if (stringArray.length > 1) {
         per = Integer.parseInt(stringArray[1]) * 0.01001F;
         per = per > 1.0F ? 1.0F : (per < 0.0F ? 0.0F : per);
      }

      if (entityplayermp != null) {
         JRMCoreH.setInt(1, entityplayermp, "jrmcRencrnt");

         for (int j = 0; j < 6; j++) {
            int i = (int)(JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[j]) * per);
            JRMCoreH.setInt(i, entityplayermp, JRMCoreH.AttrbtNbtR[j]);
         }

         JRMCoreH.resetChar(entityplayermp, keepSkills, keepTechs, keepMasteries, per);
         String t = "You have been reincarnated, you kept "
            + (int)(per * 100.0F)
            + "%% of attributes and learnable skills "
            + (keepSkills ? "have been kept" : "has been removed")
            + ".";
         entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
         if (n) {
            this.notifyAdmins(
               commandSender,
               "%s has been reincarnated with %s%% of attributes and learnable skills " + (keepSkills ? "have been kept" : "has been removed") + ".",
               new Object[]{entityplayermp.func_70005_c_(), (int)(per * 100.0F)}
            );
         }
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcrei (playerName) [keepAttributePercentage] [keepSkillsBoolean] [keepTechsBoolean] [KeepFormMasteriesBoolean]";
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.getListOfPlayers());
         case 2:
            return func_71530_a(stringArray, this.VALUES);
         case 3:
            return func_71530_a(stringArray, this.BOOLEANS);
         case 4:
            return func_71530_a(stringArray, this.BOOLEANS);
         case 5:
            return func_71530_a(stringArray, this.BOOLEANS);
         default:
            return null;
      }
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 0;
   }
}
