package JinRyuu.JRMCore;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class ComJrmcStatusEffect extends CommandBase {
   private final String[] MODES = new String[]{"set"};
   private final String[] STATUS = new String[]{
      "Strain", "GodPower", "Fatigue", "NoFuse", "Majin", "Legendary", "Divine", "Pain", "Heat", "KO", "GodOfDestruction"
   };
   private final String[] TIME = new String[]{"0", "1", "100"};
   private final String[] statusEffectNBT = new String[]{
      "jrmcStrain", "jrmcGodPwr", "jrmcGodStrain", "jrmcFuzion", "jrmcMajin", "", "", "jrmcGyJ7dp", "jrmcEf8slc", "jrmcHar4va"
   };

   public String func_71517_b() {
      return "jrmcse";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender par1ICommandSender, String[] stringArray) {
      if (stringArray.length < 3) {
         String list = "";

         for (int i = 0; i < this.STATUS.length; i++) {
            list = list + ", " + this.STATUS[i];
         }

         throw new WrongUsageException("/jrmcse set (statusEffectName) (Minutes) [playerName] --> StatusEffectName can be: " + list, new Object[0]);
      } else {
         EntityPlayerMP entityplayermp;
         if (stringArray.length > 3) {
            entityplayermp = func_82359_c(par1ICommandSender, stringArray[3]);
         } else {
            entityplayermp = func_71521_c(par1ICommandSender);
         }

         String entitycommansender = "Console";

         try {
            EntityPlayerMP commansender = func_71521_c(par1ICommandSender);
            entitycommansender = commansender.func_70005_c_();
         } catch (Exception var11) {
         }

         boolean n = entitycommansender.equals("Console")
            ? JRMCoreConfig.ComSENAC
            : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComSENAS : JRMCoreConfig.ComSENAO);
         String s = stringArray[0];
         boolean set = s.toLowerCase().contentEquals("set");
         s = stringArray[2];
         float value = Float.parseFloat(s);
         if (set) {
            int id = -1;

            for (int i = 0; i < this.STATUS.length; i++) {
               if (stringArray[1].toLowerCase().equals(this.STATUS[i].toLowerCase())) {
                  id = i;
                  break;
               }
            }

            if (id == 5) {
               JRMCoreH.StusEfcts(14, entityplayermp, value != 0.0F);
               String t = "Status Effect " + this.STATUS[id] + " has been " + (value == 0.0F ? "removed" : "added") + ".";
               entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
               if (n) {
                  this.notifyAdmins(
                     par1ICommandSender,
                     "%s's Status Effect %s has been " + (value == 0.0F ? "removed" : "added") + ".",
                     new Object[]{entityplayermp.func_70005_c_(), this.STATUS[id], value}
                  );
               }
            } else if (id == 4) {
               JRMCoreH.StusEfcts(12, entityplayermp, value != 0.0F);
               String t = "Status Effect " + this.STATUS[id] + " has been " + (value == 0.0F ? "removed" : "added") + ".";
               entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
               if (n) {
                  this.notifyAdmins(
                     par1ICommandSender,
                     "%s's Status Effect %s has been " + (value == 0.0F ? "removed" : "added") + ".",
                     new Object[]{entityplayermp.func_70005_c_(), this.STATUS[id], value}
                  );
               }
            } else if (id == 6) {
               JRMCoreH.StusEfcts(17, entityplayermp, value != 0.0F);
               String t = "Status Effect " + this.STATUS[id] + " has been " + (value == 0.0F ? "removed" : "added") + ".";
               entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
               if (n) {
                  this.notifyAdmins(
                     par1ICommandSender,
                     "%s's Status Effect %s has been " + (value == 0.0F ? "removed" : "added") + ".",
                     new Object[]{entityplayermp.func_70005_c_(), this.STATUS[id], value}
                  );
               }
            } else if (id == 10) {
               JRMCoreH.StusEfcts(20, entityplayermp, value != 0.0F);
               String t = "Status Effect " + this.STATUS[id] + " has been " + (value == 0.0F ? "removed" : "added") + ".";
               entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
               if (n) {
                  this.notifyAdmins(
                     par1ICommandSender,
                     "%s's Status Effect %s has been " + (value == 0.0F ? "removed" : "added") + ".",
                     new Object[]{entityplayermp.func_70005_c_(), this.STATUS[id], value}
                  );
               }
            } else if (id >= 0) {
               if (id == 3) {
                  String f = JRMCoreH.getString(entityplayermp, this.statusEffectNBT[id]);
                  if (!f.contains(",")) {
                     JRMCoreH.setString("" + (int)(value * 60.0F / 5.0F), entityplayermp, this.statusEffectNBT[id]);
                  }
               } else {
                  JRMCoreH.setInt(value * 60.0F / 5.0F, entityplayermp, this.statusEffectNBT[id]);
               }

               if (this.statusEffectNBT[id].equals("jrmcGyJ7dp")) {
                  NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
                  if (value > 0.0F) {
                     nbt.func_74768_a("jrmcUIWasInPainDuration", 0);
                     nbt.func_74774_a("jrmcUIWasInPain", (byte)1);
                  }
               } else if (this.statusEffectNBT[id].equals("jrmcEf8slc")) {
                  NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
                  nbt.func_74780_a("jrmcEf8slcD", 0.0);
               }

               String t = "Status Effect " + this.STATUS[id] + " has been set to " + value + " mins.";
               entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
               if (n) {
                  this.notifyAdmins(
                     par1ICommandSender, "%s's Status Effect %s has been set to %s mins.", new Object[]{entityplayermp.func_70005_c_(), this.STATUS[id], value}
                  );
               }
            }
         }
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcse set (statusEffectName) (TimeValue) [playerName]";
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.MODES);
         case 2:
            return func_71530_a(stringArray, this.STATUS);
         case 3:
            return func_71530_a(stringArray, this.TIME);
         case 4:
            return func_71530_a(stringArray, this.getListOfPlayers());
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
