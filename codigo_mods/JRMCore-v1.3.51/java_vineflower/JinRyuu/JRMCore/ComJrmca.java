package JinRyuu.JRMCore;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmca extends CommandBase {
   private final String[] MODES = new String[]{"set", "add"};
   private final String[] ATTRIBUTES_LONG = new String[]{"alignment", "strength", "dexterity", "constitution", "willpower", "mind", "spirit", "all"};
   public static final String[] ATTRIBUTES_SHORT = new String[]{"alig", "str", "dex", "con", "wil", "mnd", "spi", "all"};
   private final String[] VALUES = new String[]{"100", "max", "reset", "-100"};

   public String func_71517_b() {
      return "jrmca";
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmca (Set or Add) (Attribute) (Amount) [playerName]   ,where Attribute can be Alignment, Strength, Dexterity, Constitution, Willpower, Mind, Spirit or use /dbca Set All Max or Reset .";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      if (stringArray.length <= 0) {
         throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
      }

      String s0 = stringArray[0].toLowerCase();
      String s1 = stringArray[1];
      String s2 = stringArray[2];
      boolean set = s0.contains("set");
      boolean add = s0.contains("add");
      boolean alignment = s1.toLowerCase().contains("alignment") || s1.equalsIgnoreCase("alig") || s1.equalsIgnoreCase("align");
      boolean stre = s1.toLowerCase().contains("strength") || s1.equalsIgnoreCase("str");
      boolean dext = s1.toLowerCase().contains("dexterity") || s1.equalsIgnoreCase("dex");
      boolean cons = s1.toLowerCase().contains("constitution") || s1.equalsIgnoreCase("con");
      boolean will = s1.toLowerCase().contains("willpower") || s1.equalsIgnoreCase("wil");
      boolean mind = s1.toLowerCase().contains("mind") || s1.equalsIgnoreCase("mnd");
      boolean conc = s1.toLowerCase().contains("spirit") || s1.equalsIgnoreCase("spi");
      boolean all = s1.toLowerCase().contains("all");
      boolean max = s2.toLowerCase().contains("max");
      boolean reset = s2.toLowerCase().contains("reset");
      boolean num = false;
      if (!max && !reset) {
         num = true;
      }

      boolean amount = s2.length() > 0;
      int i = 1;

      try {
         i = func_71526_a(commandSender, s2);
      } catch (Exception exception) {
         i = 1;
      }

      int am = NKfw4V();
      if (set && i < 1) {
         i = 1;
      }

      if (i > am) {
         i = am;
      }

      EntityPlayerMP entityplayermp;
      if (stringArray.length > 3) {
         entityplayermp = func_82359_c(commandSender, stringArray[3]);
      } else {
         entityplayermp = func_71521_c(commandSender);
      }

      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(commandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var42) {
      }

      boolean n = entitycommansender.equals("Console")
         ? JRMCoreConfig.ComANAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComANAS : JRMCoreConfig.ComANAO);
      int str = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[0]);
      int dex = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[1]);
      int cns = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[2]);
      int wil = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[3]);
      int min = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[4]);
      int cnc = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[5]);
      String att = "";
      String attnam = "";
      if (stre) {
         att = JRMCoreH.AttrbtNbtI[0];
         int cur = str;
         attnam = "Strength";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      if (dext) {
         att = JRMCoreH.AttrbtNbtI[1];
         int cur = dex;
         attnam = "Dexterity";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      if (cons) {
         att = JRMCoreH.AttrbtNbtI[2];
         int cur = cns;
         attnam = "Constitution";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      if (will) {
         att = JRMCoreH.AttrbtNbtI[3];
         int cur = wil;
         attnam = "Willpower";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      if (mind) {
         att = JRMCoreH.AttrbtNbtI[4];
         int cur = min;
         attnam = "Mind";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      if (conc) {
         att = JRMCoreH.AttrbtNbtI[5];
         int cur = cnc;
         attnam = "Spirit";
         i = set && i > am ? am : (add && cur + i > am ? am - cur : i);
      }

      byte pwr = JRMCoreH.getByte(entityplayermp, "jrmcPwrtyp");
      byte rce = JRMCoreH.getByte(entityplayermp, "jrmcRace");
      byte cls = JRMCoreH.getByte(entityplayermp, "jrmcClass");
      if ((stre || dext || cons || will || mind || conc) && amount) {
         int j = 0;
         if (dext) {
            j = 1;
         }

         if (cons) {
            j = 2;
         }

         if (will) {
            j = 3;
         }

         if (mind) {
            j = 4;
         }

         if (conc) {
            j = 5;
         }

         int s = JRMCoreH.attributeStart(pwr, j, rce, cls);
         int i2 = i > s ? i : s;
         int cur = JRMCoreH.getInt(entityplayermp, att);
         JRMCoreH.setInt(max ? am : (add ? (cur + i < 1 ? 1 : cur + i) : (set ? (i2 < 1 ? 1 : i2) : 1)), entityplayermp, att);
         if (n) {
            this.notifyAdmins(
               commandSender,
               "Attribute "
                  + (add && cur + i >= 1 ? "Adding " + i + " to " + attnam : (set ? attnam + " was set to " + i : attnam + " was set to " + (max ? am : 1)))
                  + " Successfully",
               new Object[]{i, entityplayermp.func_70005_c_()}
            );
         }
      } else if (alignment && amount) {
         int cur = JRMCoreH.getByte(entityplayermp, "jrmcAlign");
         if (alignment) {
            att = "jrmcAlign";
            attnam = "Alignment";
            i = set && i > 100 ? 100 : (add && cur + i > 100 ? 100 - cur : i);
         }

         JRMCoreH.setByte(max ? am : (add ? (cur + i < 1 ? 1 : cur + i) : (set ? (i < 1 ? 1 : i) : 1)), entityplayermp, att);
         if (n) {
            this.notifyAdmins(
               commandSender,
               "Attribute "
                  + (add && cur + i >= 1 ? "Adding " + i + " to " + attnam : (set ? attnam + " was set to " + i : attnam + " was set to " + (max ? 100 : 1)))
                  + " Successfully",
               new Object[]{i, entityplayermp.func_70005_c_()}
            );
         }
      } else if (add && all && num) {
         for (int i1 = 0; i1 < JRMCoreH.AttrbtNbtI.length; i1++) {
            int s = JRMCoreH.attributeStart(pwr, i1, rce, cls);
            int i2 = i > s ? i : s;
            int cur = JRMCoreH.getInt(entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
            int newatt = cur + i < 1 ? 1 : cur + i;
            JRMCoreH.setInt(newatt > am ? am : newatt, entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
         }

         if (n) {
            this.notifyAdmins(
               commandSender,
               "Attribute Everything for " + entityplayermp.func_70005_c_() + " has received " + i + " Successfully",
               new Object[]{i, entityplayermp.func_70005_c_()}
            );
         }
      } else if (set && all && num) {
         for (int i1 = 0; i1 < JRMCoreH.AttrbtNbtI.length; i1++) {
            int s = JRMCoreH.attributeStart(pwr, i1, rce, cls);
            int i2 = i > s ? i : s;
            JRMCoreH.setInt(i2, entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
         }

         if (n) {
            this.notifyAdmins(
               commandSender,
               "Attribute Everything for " + entityplayermp.func_70005_c_() + " was set to " + i + " Successfully",
               new Object[]{i, entityplayermp.func_70005_c_()}
            );
         }
      } else {
         if (!set || !all || !max && !reset) {
            throw new WrongUsageException("Attribute Change Failure", new Object[0]);
         }

         if (max) {
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[0]);
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[1]);
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[2]);
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[3]);
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[4]);
            JRMCoreH.setInt(NKfw4V(), entityplayermp, JRMCoreH.AttrbtNbtI[5]);
         }

         if (reset) {
            int STR = JRMCoreH.attributeStart(pwr, 0, rce, cls);
            int DEX = JRMCoreH.attributeStart(pwr, 1, rce, cls);
            int CON = JRMCoreH.attributeStart(pwr, 2, rce, cls);
            int WIL = JRMCoreH.attributeStart(pwr, 3, rce, cls);
            int MND = JRMCoreH.attributeStart(pwr, 4, rce, cls);
            int SPI = JRMCoreH.attributeStart(pwr, 5, rce, cls);
            JRMCoreH.setInt(STR, entityplayermp, JRMCoreH.AttrbtNbtI[0]);
            JRMCoreH.setInt(DEX, entityplayermp, JRMCoreH.AttrbtNbtI[1]);
            JRMCoreH.setInt(CON, entityplayermp, JRMCoreH.AttrbtNbtI[2]);
            JRMCoreH.setInt(WIL, entityplayermp, JRMCoreH.AttrbtNbtI[3]);
            JRMCoreH.setInt(MND, entityplayermp, JRMCoreH.AttrbtNbtI[4]);
            JRMCoreH.setInt(SPI, entityplayermp, JRMCoreH.AttrbtNbtI[5]);
         }

         if (n) {
            this.notifyAdmins(
               commandSender,
               "Attribute Everything for " + entityplayermp.func_70005_c_() + " has been " + (max ? "Maxed" : (reset ? "Reset" : "failed")),
               new Object[]{i, entityplayermp.func_70005_c_()}
            );
         }
      }

      EntityPlayerMP player = entityplayermp;
      int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
      int maxBody = JRMCoreH.stat(player, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0F);
      int maxEnergy = JRMCoreH.stat(player, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(player, pwr));
      int maxStam = JRMCoreH.stat(player, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0F);
      JRMCoreH.setInt(maxBody, player, "jrmcBdy");
      JRMCoreH.setInt(maxEnergy, player, "jrmcEnrgy");
      JRMCoreH.setInt(maxStam, player, "jrmcStamina");
   }

   int calc1(int j, int l) {
      int i = 0;

      for (int k = 0; k < j; k++) {
         i += i + l;
      }

      return i;
   }

   int calc2(int j, int l) {
      int i = 0;

      for (int k = 0; k < j / l; k++) {
         int z = k * l / 50 <= 0 ? 1 : k * l / 50;
         i += z;
      }

      return i;
   }

   private NBTTagCompound nbt(EntityPlayer p, String s) {
      return JRMCoreH.nbt(p, s);
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public static int MCajr(String l) {
      String w = "0123456789ABCDEF";
      l = l.toUpperCase();
      int a = 0;

      for (int i = 0; i < l.length(); i++) {
         char c = l.charAt(i);
         int d = w.indexOf(c);
         a = 16 * a + d;
      }

      return a;
   }

   public static int NKfw4V() {
      int b = JRMCoreConfig.tmx;
      String r = "64";
      String k = "3B9ACA00";
      return b > MCajr(k) ? MCajr(k) : (b < MCajr(r) ? 0 : b);
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.MODES);
         case 2:
            return func_71530_a(stringArray, this.ATTRIBUTES_LONG);
         case 3:
            return func_71530_a(stringArray, this.VALUES);
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
      return par1 == 3;
   }
}
