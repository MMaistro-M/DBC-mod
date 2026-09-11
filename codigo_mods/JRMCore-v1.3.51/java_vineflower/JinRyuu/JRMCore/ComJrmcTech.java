package JinRyuu.JRMCore;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.math.NumberUtils;

public class ComJrmcTech extends CommandBase {
   public static final String[] MODES = new String[]{"give", "take"};
   private final String NAME = "jrmctech";

   public String func_71517_b() {
      return "jrmctech";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      if (par2ArrayOfStr.length < 2) {
         String listdbc = "";

         for (int i = 0; i < JRMCoreH.pmdbc.length; i++) {
            listdbc = listdbc + ", " + JRMCoreH.pmdbc[i][0];
         }

         String listnc = "";

         for (int i = 0; i < JRMCoreH.pmj.length; i++) {
            listnc = listnc + ", " + JRMCoreH.pmj[i][0];
         }

         throw new WrongUsageException(
            "jrmctech (give or take) (techName) [playerName]"
               + (JRMCoreH.DBC() ? " --> techNames for DBC can be: " + listdbc : "")
               + (JRMCoreH.NC() ? " --> techNames for NC can be: " + listnc : ""),
            new Object[0]
         );
      } else {
         String s = par2ArrayOfStr[0];
         boolean give = s.toLowerCase().contentEquals("give");
         boolean take = s.toLowerCase().contentEquals("take");
         int id = -1;

         for (int i = 0; i < JRMCoreH.DBCSkillNames.length; i++) {
            if (par2ArrayOfStr[1].toLowerCase().equals(JRMCoreH.DBCSkillNames[i].toLowerCase())) {
               break;
            }
         }

         EntityPlayerMP entityplayermp;
         if (par2ArrayOfStr.length > 2) {
            entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[2]);
         } else {
            entityplayermp = func_71521_c(par1ICommandSender);
         }

         NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
         EntityPlayer p = entityplayermp;
         int pwrtyp = JRMCoreH.getByte(p, "jrmcPwrtyp");
         String[][] PMA = (String[][])null;
         int b = 0;
         PMA = pwrtyp == 2 ? JRMCoreH.pmj : JRMCoreH.pmdbc;
         int id1 = -1;

         for (int i = 0; i < PMA.length; i++) {
            if (par2ArrayOfStr[1].toLowerCase().equals(PMA[i][0].toLowerCase())) {
               id1 = i;
               break;
            }
         }

         if (id1 >= 0) {
            if (take) {
               for (int i = 4; i < 8; i++) {
                  String t = JRMCoreH.getString(p, JRMCoreH.techNbt[i]);
                  if (NumberUtils.isNumber(t) && Integer.parseInt(t) == id1) {
                     JRMCoreH.setString(" ", p, JRMCoreH.techNbt[i]);
                     String skl = PMA[id1][0];
                     String t1 = "Skill " + skl + " removed";
                     ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                     entityplayermp.func_145747_a(new ChatComponentText(t1).func_150255_a(color));
                     this.notifyAdmins(par1ICommandSender, "%s's tech %s has been removed!", new Object[]{entityplayermp.func_70005_c_(), skl});
                     break;
                  }
               }
            }

            if (give) {
               int s2 = id1;
               boolean may = false;

               for (int i = 0; i < 4; i++) {
                  String s1 = JRMCoreH.getString(p, JRMCoreH.techNbt[4 + i]);
                  if (!NumberUtils.isNumber(s1) && !s1.contains(",")) {
                     b = (byte)(4 + i);
                     may = true;
                     break;
                  }

                  may = false;
               }

               if (may) {
                  JRMCoreH.setString("" + s2, p, JRMCoreH.techNbt[b]);
                  String t = JRMCoreH.trlai("jrmc", "techadded");
                  ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                  entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{PMA[id1][0]}).func_150255_a(color));
                  this.notifyAdmins(par1ICommandSender, "%s has received tech %s", new Object[]{entityplayermp.func_70005_c_(), PMA[id1][0]});
               } else {
                  String t = JRMCoreH.trlai("jrmc", "noslotleft");
                  ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                  entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{PMA[id1][0]}).func_150255_a(color));
                  this.notifyAdmins(par1ICommandSender, "%s has received skill %s", new Object[]{entityplayermp.func_70005_c_(), PMA[id1][0]});
               }
            }
         }
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/dbcsaga reset (playerName)";
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, MODES);
         case 2:
            String[] listOfTechs = new String[JRMCoreH.pmdbc.length + JRMCoreH.pmj.length];
            int id = 0;

            for (int i = 0; i < JRMCoreH.pmdbc.length; i++) {
               listOfTechs[id] = JRMCoreH.pmdbc[i][0];
               id++;
            }

            for (int i = 0; i < JRMCoreH.pmj.length; i++) {
               listOfTechs[id] = JRMCoreH.pmj[i][0];
               id++;
            }

            return func_71530_a(stringArray, listOfTechs);
         case 3:
            return func_71530_a(stringArray, this.getListOfPlayers());
         default:
            return null;
      }
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 2;
   }
}
