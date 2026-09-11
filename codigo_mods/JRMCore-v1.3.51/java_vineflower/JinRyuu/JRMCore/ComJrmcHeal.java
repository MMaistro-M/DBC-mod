package JinRyuu.JRMCore;

import java.util.HashMap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmcHeal extends CommandBase {
   public static HashMap<String, Object[]> SList = new HashMap<>();

   public String func_71517_b() {
      return "jrmcheal";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender par1ICommandSender) {
      return "Usage: '/jrmcheal (energy, body, stamina, food, all) [amount] [playerName]'";
   }

   public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      if (par2ArrayOfStr.length <= 0) {
         throw new WrongUsageException(this.func_71518_a(par1ICommandSender), new Object[0]);
      }

      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(par1ICommandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var23) {
      }

      boolean energy = par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("energy");
      boolean body = par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("body");
      boolean stamina = par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("stamina");
      boolean food = par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("food");
      boolean all = par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("all");
      int amount = 0;
      EntityPlayerMP entityplayermp;
      if (par2ArrayOfStr.length > 2) {
         entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[2]);
      } else {
         entityplayermp = func_71521_c(par1ICommandSender);
      }

      if (par2ArrayOfStr.length > 1) {
         amount = Integer.parseInt(par2ArrayOfStr[1]);
      }

      NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
      if (!body && !all && !stamina && !food && !energy) {
         throw new WrongUsageException("%s failed to use jrmcheal stat!", new Object[]{entitycommansender, par2ArrayOfStr[0]});
      }

      int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(entityplayermp);
      byte pwr = JRMCoreH.getByte(entityplayermp, "jrmcPwrtyp");
      byte rce = JRMCoreH.getByte(entityplayermp, "jrmcRace");
      byte cls = JRMCoreH.getByte(entityplayermp, "jrmcClass");
      int maxBody = JRMCoreH.stat(entityplayermp, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0F);
      int curBody = JRMCoreH.getInt(entityplayermp, "jrmcBdy");
      int maxEnergy = JRMCoreH.stat(entityplayermp, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(entityplayermp, pwr));
      int curEnergy = JRMCoreH.getInt(entityplayermp, "jrmcEnrgy");
      int maxStam = JRMCoreH.stat(entityplayermp, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0F);
      int curStam = JRMCoreH.getInt(entityplayermp, "jrmcStamina");
      if (body || all) {
         JRMCoreH.setInt(amount == 0 ? maxBody : (curBody + amount > maxBody ? maxBody : curBody + amount), entityplayermp, "jrmcBdy");
      }

      if (energy || all) {
         JRMCoreH.setInt(amount == 0 ? maxEnergy : (curEnergy + amount > maxEnergy ? maxEnergy : curEnergy + amount), entityplayermp, "jrmcEnrgy");
      }

      if (stamina || all) {
         JRMCoreH.setInt(amount == 0 ? maxStam : (curStam + amount > maxStam ? maxStam : curStam + amount), entityplayermp, "jrmcStamina");
      }

      if (body || all) {
         entityplayermp.func_70606_j(entityplayermp.func_110138_aP());
      }

      if (food || all) {
         entityplayermp.func_71024_bL().func_75122_a(20, 0.9F);
      }

      boolean n = entitycommansender.equals("Console")
         ? JRMCoreConfig.ComHealNAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComHealNAS : JRMCoreConfig.ComHealNAO);
      if (n) {
         this.notifyAdmins(
            par1ICommandSender,
            "%s was healed %s %s by %s",
            new Object[]{entityplayermp.func_70005_c_(), amount == 0 ? "max" : amount, par2ArrayOfStr[0], entitycommansender}
         );
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 2 ? func_71530_a(par2ArrayOfStr, this.getListOfPlayers()) : null;
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 0;
   }
}
