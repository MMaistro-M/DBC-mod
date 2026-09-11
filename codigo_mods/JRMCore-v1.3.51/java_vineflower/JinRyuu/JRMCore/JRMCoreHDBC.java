package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier1;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier2;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier3;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldiers;
import JinRyuu.DragonBC.common.Npcs.EntityMasterBabidi;
import JinRyuu.DragonBC.common.Npcs.EntityMasterCell;
import JinRyuu.DragonBC.common.Npcs.EntityMasterEnma;
import JinRyuu.DragonBC.common.Npcs.EntityMasterFreeza;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGohan;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGoku;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGuru;
import JinRyuu.DragonBC.common.Npcs.EntityMasterJin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKaio;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKami;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKarin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterRoshi;
import JinRyuu.DragonBC.common.Npcs.EntityMasterTrunksFuture;
import JinRyuu.DragonBC.common.Npcs.EntityMasterWhis;
import JinRyuu.DragonBC.common.Npcs.EntitySaibaiman;
import JinRyuu.DragonBC.common.Villages.builds;
import JinRyuu.DragonBC.common.Worlds.WorldGeneratorDB;
import JinRyuu.DragonBC.common.Worlds.WorldTeleporterDBCTelep;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.p.DBC.DBCPwish;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class JRMCoreHDBC {
   public static final int Skl_GodFormLVLrequHN = 6;
   public static final int Skl_GodFormLVLrequA = 7;
   public static final int GodRacialRequirement = 6;
   public static final int Action_Kaioken = 1;
   public static final int Action_TransformType = 2;
   public static final int Action_GodForm = 3;
   public static final int Action_TailMode = 4;
   public static final int Action_ArcFace = 4;
   public static final int Action_Dodge = 5;
   public static final int Action_Descend = 6;
   public static final int Action_FlyMode = 7;
   public static final int Action_EnergyInfusedProjectile = 8;
   public static final int Action_Fuzion = 9;
   public static final int Action_Mystic = 10;
   public static final int Action_KiFist = 11;
   public static final int Action_KiProtection = 12;
   public static final int Action_UltraInstinct = 13;
   public static final int Action_FriendlyFist = 14;
   public static final int Action_KiSword = 15;
   public static final int Action_InstantTransShortTPMode = 16;
   public static final int Action_InstantTransSurroundMode = 17;
   public static final int Action_GodOfDestruction = 18;
   public static float gravityDev = 1.0F;
   public static float gravityDevCB = 0.0F;
   public static int dragonSum = 100;
   public static String[] ar = new String[]{"a", "i", "u", "e", "o"};

   public static int col_fe(int t, int d, int p, int r, int s, boolean v, boolean y, boolean ui) {
      return getPlayerColor(t, d, p, r, s, v, y, ui, false, false);
   }

   public static int getPlayerColor2(int t, int d, int p, int r, int s, boolean v, boolean y, boolean ui, boolean gd) {
      return getPlayerColor(t, d, p, r, s, v, y, ui, false, gd);
   }

   public static int col_fe(int t, int d, int p, int r, int s, boolean v, boolean y, boolean ui, boolean ui2) {
      return getPlayerColor(t, d, p, r, s, v, y, ui, ui2, false);
   }

   public static int getPlayerColor(int type, int def, int p, int r, int s, boolean divine, boolean y, boolean ui, boolean ui2, boolean gd) {
      boolean ssb = s == 10;
      boolean ssbs = s == 15;
      boolean ss4 = s == 14;
      boolean ssg = godKiUserBase(r, s);
      boolean ss = s > 0 && s != 7;
      int clr = def;
      if (p == 1 && JRMCoreH.rc_sai(r)) {
         if (gd) {
            clr = type == 0 ? 9446263 : (type == 3 ? 12464847 : (type == 1 ? 12976974 : 12464847));
         } else if (ui2) {
            clr = type == 15790320 ? 15790320 : (type == 1 ? 13816530 : 15790320);
         } else if (ui) {
            clr = type == 0 ? 15790320 : (type == 1 ? 13816530 : 15790320);
         } else if (ssb && divine) {
            clr = type != 0 && type != 3 ? (type == 1 ? 11115418 : 7536661) : 14985390;
         } else if (ssbs && divine) {
            clr = type != 0 && type != 3 ? (type == 1 ? 9338493 : 14030412) : 14184352;
         } else if (ssb) {
            clr = type != 0 && type != 3 ? (type == 1 ? 2469062 : 2805230) : 2805230;
         } else if (ssbs) {
            clr = type != 0 && type != 3 ? (type == 1 ? 23039 : 32767) : 32767;
         } else if (ssg) {
            clr = type != 0 && type != 3 ? (type == 1 ? 14943270 : 16761125) : 14028139;
         } else if (ss4) {
            clr = type == 0 ? def : (type == 1 ? 15976455 : (type == 3 ? (y ? 10092390 : 16574610) : (y ? 10092390 : 16701952)));
         } else if (ss) {
            clr = type != 0 && type != 3 ? (type == 1 ? 2988684 : (y ? 10092390 : 16701952)) : (y ? 10092390 : 16574610);
         }
      } else if (p == 1 && JRMCoreH.rc_hum(r) && ui2) {
         if (ui2) {
            clr = type == 15790320 ? 15790320 : (type == 1 ? 13816530 : 15790320);
         }
      } else if (p == 1 && JRMCoreH.rc_humNam(r)) {
         if (gd) {
            if (JRMCoreH.rc_hum(r)) {
               clr = type == 0 ? 9446263 : (type == 3 ? 12464847 : (type == 1 ? 12976974 : 12464847));
            } else {
               clr = type == 3 ? 12464847 : (type == 1 ? 12976974 : 12464847);
            }
         } else if (ui) {
            clr = type == 0 ? def : (type == 1 ? 13816530 : 15790320);
         } else if (ssg) {
            clr = type == 1 ? 14943270 : (type == 2 ? 16761125 : clr);
         }
      } else if (p == 1 && JRMCoreH.rc_arc(r)) {
         if (gd) {
            clr = type == 3 ? 12464847 : (type == 1 ? 12976974 : 12464847);
         } else if (ui) {
            clr = type == 0 ? def : (type == 1 ? 13816530 : 15790320);
         } else if (ssg) {
            clr = type == 1 ? 14943270 : (type == 2 ? 16761125 : clr);
         } else if (s == 6) {
            clr = type == 2 ? 16430355 : clr;
         }
      } else if (p == 1 && JRMCoreH.isRaceMajin(r)) {
         if (gd) {
            clr = type == 3 ? 12464847 : (type == 1 ? 12976974 : 12464847);
         } else if (ui) {
            clr = type == 1 ? 13816530 : 15790320;
         } else if (ssg) {
            clr = type == 1 ? 14943270 : (type == 2 ? 16761125 : clr);
         } else if (s == 1) {
            clr = type == 1 ? 16579836 : (type == 3 ? 12561588 : clr);
         } else if (s == 2) {
            clr = type == 3 ? 16024763 : clr;
         } else if (s == 3) {
            clr = type != 3 && type != 2 ? clr : 16756968;
         }
      }

      return clr;
   }

   public static boolean godKiUser(int r, int s) {
      if (JRMCoreH.rc_sai(r)) {
         return s == 10 || s == 9 || s == 11;
      } else if (JRMCoreH.rc_humNam(r)) {
         return s == 3;
      } else if (JRMCoreH.isRaceMajin(r)) {
         return s == 4;
      } else {
         return JRMCoreH.rc_arc(r) ? s == 7 : s == 10 || s == 9 || s == 11;
      }
   }

   public static boolean godKiUserBase(int r, int s) {
      if (!JRMCoreH.rc_sai(r)) {
         if (DBCgetConfigGodformCosm() && JRMCoreH.rc_humNam(r)) {
            return s == 3;
         } else if (DBCgetConfigGodformCosm() && JRMCoreH.isRaceMajin(r)) {
            return s == 4;
         } else {
            return DBCgetConfigGodformCosm() && JRMCoreH.rc_arc(r) ? s == 7 : false;
         }
      } else {
         return s == 9 || s == 11;
      }
   }

   public static boolean godformslr(int s) {
      return s >= 6;
   }

   public static boolean godformslrHN(int s) {
      return s >= 6;
   }

   public static boolean godformslrA(int s) {
      return s >= 7;
   }

   public static boolean godKiAble() {
      if (JRMCoreH.rc_sai(JRMCoreH.Race) && godformslr(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX))) {
         return true;
      } else if (JRMCoreH.rc_humNam(JRMCoreH.Race) && godformslrHN(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX))) {
         return true;
      } else {
         return JRMCoreH.isRaceMajin(JRMCoreH.Race) && hasMajinGodRacialRequirement(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX))
            ? true
            : JRMCoreH.rc_arc(JRMCoreH.Race) && godformslrA(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX));
      }
   }

   public static boolean godKiAble(int r, int sklX) {
      if (JRMCoreH.rc_sai(r) && godformslr(sklX)) {
         return true;
      } else if (JRMCoreH.rc_humNam(r) && godformslrHN(sklX)) {
         return true;
      } else {
         return JRMCoreH.isRaceMajin(r) && hasMajinGodRacialRequirement(sklX) ? true : JRMCoreH.rc_arc(r) && godformslrA(sklX);
      }
   }

   public static boolean auc(int sklX) {
      return sklX >= 6;
   }

   public static boolean hasMajinGodRacialRequirement(int racialLvl) {
      return racialLvl >= 6;
   }

   public static void actionInitDBC() {
      JRMCoreA.actionsDBC.put(0, 1);
      JRMCoreA.actionsDBC.put(1, 2);
      JRMCoreA.actionsDBC.put(2, 6);
      JRMCoreA.actionsDBC.put(3, 5);
      JRMCoreA.actionsDBC.put(5, 7);
      JRMCoreA.actionsDBC.put(6, 9);
      JRMCoreA.actionsDBC.put(7, 4);
      JRMCoreA.actionsDBC.put(8, 10);
      JRMCoreA.actionsDBC.put(9, 11);
      JRMCoreA.actionsDBC.put(10, 12);
      JRMCoreA.actionsDBC.put(11, 13);
      JRMCoreA.actionsDBC.put(12, 14);
      JRMCoreA.actionsDBC.put(14, 15);
      JRMCoreA.actionsDBC.put(15, 16);
      JRMCoreA.actionsDBC.put(16, 17);
      JRMCoreA.actionsDBC.put(17, 18);
   }

   public static String action(int d, boolean action, boolean black) {
      String opt1 = JGConfigClientSettings.CLIENT_GR12 ? "Enable" : "Off";
      String opt2 = JGConfigClientSettings.CLIENT_GR12 ? "Disable" : "On";
      String clr1 = "§4";
      String clr2 = "§2";
      if (JGConfigClientSettings.CLIENT_GR13) {
         black = true;
      }

      int race = JRMCoreH.Race;
      int pwr = JRMCoreH.Pwrtyp;
      int state = JRMCoreH.State;
      int align = JRMCoreH.align;
      if (JRMCoreH.PlyrSettingsB(6) && JRMCoreH.SklLvl(10, JRMCoreH.Pwrtyp) < 1) {
         JRMCoreH.Skll((byte)6, (byte)6, (byte)1);
      }

      if (JRMCoreH.PlyrSettingsB(11) && JGConfigUltraInstinct.CONFIG_UI_LEVELS == 0) {
         JRMCoreH.Skll((byte)6, (byte)11, (byte)1);
      }

      switch (d) {
         case 1:
            String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[8], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
            int skillLvl = JRMCoreH.SklLvl(8, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(0);
            if (!JRMCoreH.PlyrSettingsB(11) && !JRMCoreH.PlyrSettingsB(16) && !JRMCoreH.PlyrSettingsB(6)) {
               skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[8], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
               skillLvl = JRMCoreH.SklLvl(8, JRMCoreH.Pwrtyp);
               ps = JRMCoreH.PlyrSettingsB(0);
               if (skillLvl > 0) {
                  if (!action) {
                     String enable = JRMCoreH.trl("jrmc", opt1);
                     String disable = JRMCoreH.trl("jrmc", opt2);
                     return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
                  }

                  if (ps) {
                     JRMCoreH.Skll((byte)6, (byte)0, (byte)1);
                  } else {
                     JRMCoreH.Skll((byte)6, (byte)0, (byte)0);
                  }
               }
            }

            if (skillLvl > 0 && !action) {
               return skllName
                  + " is not useable, because "
                  + (JRMCoreH.PlyrSettingsB(11) ? "Ultra instinct" : (JRMCoreH.PlyrSettingsB(16) ? "God of Destruction" : "Mystic form"))
                  + " is enabled!";
            }

            return "";
         case 2:
            int sklX = JRMCoreH.SklLvlX(pwr, JRMCoreH.PlyrSkillX);
            boolean tt = JRMCoreH.PlyrSettingsI(1, 0);
            boolean ttg = JRMCoreH.PlyrSettingsI(1, 1);
            boolean ttb = JRMCoreH.PlyrSettingsI(1, 2);
            boolean tt4 = JRMCoreH.PlyrSettingsI(1, 3);
            int skillLvl = JRMCoreH.SklLvl(9, JRMCoreH.Pwrtyp);
            int skl2 = JRMCoreH.SklLvl(10, JRMCoreH.Pwrtyp);
            if (JRMCoreH.rc_sai(race) && sklX >= 6) {
               String TransNms = JRMCoreH.trl(
                  "jrmc", JRMCoreH.getTransformationName(race, ttb ? 10 : (ttg ? 9 : (tt ? 5 : (tt4 ? 14 : 2))), JRMCoreH.StusEfctsMe(17), false, false, false)
               );
               String name = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race));
               name = name + "/n" + TransNms;
               if (!action) {
                  return name;
               }

               boolean s4ap = sklX >= 7 && JRMCoreH.s4ft > 0 && JRMCoreH.tailHas(JRMCoreH.TlMd);
               byte nag = (byte)(skillLvl > 1 ? 2 : (skillLvl > 0 ? 1 : (s4ap ? 3 : -1)));
               if (ttb) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)1);
               } else if (ttg) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)(s4ap ? 3 : -1));
               } else if (tt4) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)-1);
               } else if (tt) {
                  JRMCoreH.Skll((byte)8, (byte)1, nag);
               } else {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)0);
               }
            }

            if (JRMCoreH.rc_humNam(race) && sklX >= 3) {
               String TransNms = JRMCoreH.trl("jrmc", JRMCoreH.getTransformationName(race, ttg ? 3 : (tt ? 1 : 2), false, false, false, false));
               String name = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race));
               name = name + "/n" + TransNms;
               if (!action) {
                  return name;
               }

               byte nag = (byte)(skillLvl > 0 ? 1 : -1);
               if (ttg) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)-1);
               } else if (tt) {
                  JRMCoreH.Skll((byte)8, (byte)1, nag);
               } else {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)0);
               }
            }

            if (JRMCoreH.isRaceMajin(race) && sklX >= 6) {
               String TransNms = JRMCoreH.trl("jrmc", JRMCoreH.getTransformationName(race, ttg ? 4 : (tt ? 3 : 1), false, false, false, false));
               String name = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race));
               name = name + "/n" + TransNms;
               if (!action) {
                  return name;
               }

               byte nag = (byte)(skillLvl > 0 ? 1 : -1);
               if (ttg) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)-1);
               } else if (tt) {
                  JRMCoreH.Skll((byte)8, (byte)1, nag);
               } else {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)0);
               }
            }

            if (JRMCoreH.rc_arc(race) && sklX >= 7) {
               String TransNms = JRMCoreH.trl("jrmc", JRMCoreH.getTransformationName(race, ttg ? 7 : (tt ? 6 : 5), false, false, false, false));
               String name = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race));
               name = name + "/n" + TransNms;
               if (!action) {
                  return name;
               }

               byte nag = (byte)(skillLvl > 0 ? 1 : -1);
               if (ttg) {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)-1);
               } else if (tt) {
                  JRMCoreH.Skll((byte)8, (byte)1, nag);
               } else {
                  JRMCoreH.Skll((byte)8, (byte)1, (byte)0);
               }
            }

            return "";
         case 3:
         case 8:
         default:
            return "";
         case 4:
            int tlmd = JRMCoreH.TlMd;
            if (action) {
               if ((race == 1 || race == 2) && (tlmd == 1 || tlmd == 0 || tlmd == -1)) {
                  JRMCoreH.Char((byte)102, (byte)0);
               }

               if (race == 4 && state == 5) {
                  JRMCoreH.Skll((byte)5, (byte)(JRMCoreH.StusEfctsMe(6) ? 1 : 0), (byte)6);
               }
            } else {
               if ((race == 1 || race == 2) && (tlmd == 1 || tlmd == 0 || tlmd == -1)) {
                  return JRMCoreH.trl("jrmc", "TailMode");
               }

               if (race == 4 && state == 5) {
                  String n = JRMCoreH.trl("jrmc", "ArcMask");
                  boolean bo = JRMCoreH.StusEfctsMe(6);
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return n + ": " + (!bo ? (black ? "" : "§4") + enable : (black ? "" : "§2") + disable);
               }
            }

            return race != 1 && race != 2 ? (race == 4 ? JRMCoreH.trl("jrmc", "ArcMask") : "") : JRMCoreH.trl("jrmc", "TailMode");
         case 5:
            String skllName = JRMCoreH.trl("dbc", "DodgeSwoop");
            int skl_d = JRMCoreH.SklLvl(2, JRMCoreH.Pwrtyp);
            int skl_f = JRMCoreH.SklLvl(3, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(2);
            if (skl_d > 0 || skl_f > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§4") + enable : (black ? "" : "§2") + disable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)2, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)2, (byte)0);
               }
            }

            return "";
         case 6:
            if (JRMCoreH.isRaceMajin(race)) {
               int sklX = JRMCoreH.SklLvlX(pwr, JRMCoreH.PlyrSkillX);
               if (sklX > 4) {
                  String TransNms = JRMCoreH.trl("jrmc", "MAbsorption");
                  String name = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race));
                  name = name + "/n" + TransNms;
                  if (action) {
                     JRMCoreH.Skll((byte)5, (byte)(JRMCoreH.StusEfctsMe(21) ? 1 : 0), (byte)21);
                     return name;
                  }

                  boolean bo = JRMCoreH.StusEfctsMe(21);
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return TransNms + ": " + (!bo ? (black ? "" : "§4") + enable : (black ? "" : "§2") + disable);
               }
            } else if (JRMCoreH.isRaceArcosian(race) && state >= 1) {
               if (!action) {
                  return JRMCoreH.trl("dbc", "Descend");
               }

               Dscndng(4);
            }

            return race == 4 ? JRMCoreH.trl("dbc", "Descend") : "";
         case 7:
            String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[3], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
            int skillLvl = JRMCoreH.SklLvl(3, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.StusEfctsMe(9);
            if (skillLvl > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", "OnSimple");
                  String disable = JRMCoreH.trl("jrmc", "OnDynamic");
                  return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)5, (byte)1, (byte)9);
               } else {
                  JRMCoreH.Skll((byte)5, (byte)0, (byte)9);
               }
            }

            return "";
         case 9:
            String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[0], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
            int skillLvl = JRMCoreH.SklLvl(0, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(4);
            if (skillLvl > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)4, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)4, (byte)0);
               }
            }

            return "";
         case 11:
            String skllName = JRMCoreH.trl("dbc", "KiFist");
            int skl_f = JRMCoreH.SklLvl(12, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(9);
            if (skl_f > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§4") + enable : (black ? "" : "§2") + disable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)9, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)9, (byte)0);
               }
            }

            return "";
         case 12:
            String skllName = JRMCoreH.trl("dbc", "KiProtection");
            int skl_f = JRMCoreH.SklLvl(11, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(10);
            if (skl_f > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§4") + enable : (black ? "" : "§2") + disable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)10, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)10, (byte)0);
               }
            }

            return "";
         case 13:
            if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
               String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[16], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
               int skillLvl = JRMCoreH.SklLvl(16, JRMCoreH.Pwrtyp);
               boolean ps = JRMCoreH.PlyrSettingsB(11);
               if (!JRMCoreH.PlyrSettingsB(0) && !JRMCoreH.PlyrSettingsB(16) && !JRMCoreH.PlyrSettingsB(6) && skillLvl > 0) {
                  if (!action) {
                     String enable = JRMCoreH.trl("jrmc", opt1);
                     String disable = JRMCoreH.trl("jrmc", opt2);
                     return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
                  }

                  if (ps) {
                     JRMCoreH.Skll((byte)6, (byte)11, (byte)1);
                  } else {
                     JRMCoreH.Skll((byte)6, (byte)11, (byte)0);
                  }
               }

               if (skillLvl > 0 && !action) {
                  return skllName
                     + " is not useable, because "
                     + (JRMCoreH.PlyrSettingsB(0) ? "Kaioken" : (JRMCoreH.PlyrSettingsB(16) ? "God of Destruction" : "Mystic form"))
                     + " is enabled!";
               }

               return "";
            }
         case 10:
            String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[10], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
            int skillLvl = JRMCoreH.SklLvl(10, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(6);
            if (!JRMCoreH.PlyrSettingsB(0) && !JRMCoreH.PlyrSettingsB(16) && !JRMCoreH.PlyrSettingsB(11) && skillLvl > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)6, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)6, (byte)0);
               }
            }

            if (skillLvl > 0 && !action) {
               return skllName
                  + " is not useable, because "
                  + (JRMCoreH.PlyrSettingsB(0) ? "Kaioken" : (JRMCoreH.PlyrSettingsB(16) ? "God of Destruction" : "Ultra instinct"))
                  + " is enabled!";
            }

            return "";
         case 14:
            String skllName = JRMCoreH.trl("dbc", "FriendlyFist");
            int skl_f = 1;
            boolean ps = JRMCoreH.PlyrSettingsB(12);
            if (skl_f > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)12, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)12, (byte)0);
               }
            }

            return "";
         case 15:
            int skl_f = JRMCoreH.SklLvl(12, JRMCoreH.Pwrtyp);
            int skl_f2 = JRMCoreH.SklLvl(15, JRMCoreH.Pwrtyp);
            boolean kwsw = JRMCoreH.PlyrSettingsI(13, 0);
            boolean kssc = JRMCoreH.PlyrSettingsI(13, 1);
            if (skl_f > 0 && skl_f2 > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return JRMCoreH.trl("dbc", "KiWeapon")
                     + ": "
                     + (
                        kwsw
                           ? (black ? "" : "§2") + JRMCoreH.trl("dbc", "KiSword")
                           : (kssc ? (black ? "" : "§2") + JRMCoreH.trl("dbc", "KiScythe") : (black ? "" : "§4") + JRMCoreH.trl("jrmc", "Off"))
                     );
               }

               if (kwsw) {
                  JRMCoreH.Skll((byte)8, (byte)13, (byte)1);
               } else if (kssc) {
                  JRMCoreH.Skll((byte)8, (byte)13, (byte)-1);
               } else {
                  JRMCoreH.Skll((byte)8, (byte)13, (byte)0);
               }
            }

            return "";
         case 16:
            int itLevel = JRMCoreH.SklLvl(17, JRMCoreH.Pwrtyp);
            if (itLevel > 0) {
               if (!action) {
                  int id = JRMCoreH.PlyrSettings(14);
                  String ID = "InstantTransShortTPMode";
                  return JRMCoreH.trl("dbc", "InstantTransShortTPMode")
                     + ": "
                     + (black ? "" : "§2")
                     + JRMCoreH.trl("dbc", "InstantTransShortTPMode" + (id + 1));
               }

               int id = JRMCoreH.PlyrSettings(14);
               if (++id > 1) {
                  id = -1;
               }

               JRMCoreH.Skll((byte)8, (byte)14, (byte)id);
            }

            return "";
         case 17:
            int itLevel = JRMCoreH.SklLvl(17, JRMCoreH.Pwrtyp);
            if (itLevel > 0) {
               if (!action) {
                  int id = JRMCoreH.PlyrSettings(15);
                  String ID = "InstantTransSurroundMode";
                  return JRMCoreH.trl("dbc", "InstantTransSurroundMode")
                     + ": "
                     + (black ? "" : "§2")
                     + JRMCoreH.trl("dbc", "InstantTransSurroundMode" + (id + 1));
               }

               int id = JRMCoreH.PlyrSettings(15);
               if (++id > 1) {
                  id = -1;
               }

               JRMCoreH.Skll((byte)8, (byte)15, (byte)id);
            }

            return "";
         case 18:
            String skllName = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.DBCSkillsIDs[18], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillNames));
            int skillLvl = JRMCoreH.SklLvl(18, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(16);
            if (!JRMCoreH.PlyrSettingsB(0) && !JRMCoreH.PlyrSettingsB(6) && !JRMCoreH.PlyrSettingsB(11) && skillLvl > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return skllName + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               }

               if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)16, (byte)1);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)16, (byte)0);
               }
            }

            return skillLvl > 0 && !action
               ? skllName
                  + " is not useable, because "
                  + (JRMCoreH.PlyrSettingsB(0) ? "Kaioken" : (JRMCoreH.PlyrSettingsB(6) ? "Mystic form" : "Ultra instinct"))
                  + " is enabled!"
               : "";
      }
   }

   public static int ItemWeightOn(ItemStack itemStack) {
      if (itemStack != null) {
         if (itemStack.func_77973_b() == ItemsDBC.ItemWeightShell) {
            return 0;
         }

         if (itemStack.func_77973_b() == ItemsDBC.ItemWeightHandLeg) {
            return 1;
         }

         if (itemStack.func_77973_b() == ItemsDBC.ItemWeightShirt) {
            return 2;
         }

         if (itemStack.func_77973_b() == ItemsDBC.ItemWeightCape) {
            return 3;
         }

         if (itemStack.func_77973_b() == ItemsDBC.ItemWeightHeavySuit) {
            return 4;
         }
      }

      return -1;
   }

   public static float gravity(EntityPlayer player, float g) {
      float G = 1.0F;
      if (player.field_71093_bK == DBCConfig.otherWorld) {
         AxisAlignedBB ab = AxisAlignedBB.func_72330_a(DBCH.KPminX, DBCH.KPminY, DBCH.KPminZ, DBCH.KPmaxX, DBCH.KPmaxY, DBCH.KPmaxZ);
         if (ab.field_72336_d > player.field_70165_t
            && ab.field_72340_a < player.field_70165_t
            && ab.field_72337_e > player.field_70163_u
            && ab.field_72338_b < player.field_70163_u
            && ab.field_72334_f > player.field_70161_v
            && ab.field_72339_c < player.field_70161_v) {
            G = 10.0F;
         }
      }

      if (player.field_71093_bK == DBCConfig.dimTimeChamber) {
         G = 10.0F;
      }

      return G < g ? g : G;
   }

   public static void openGui(int id, EntityPlayer pl) {
      pl.openGui(mod_DragonBC.instance, id, pl.field_70170_p, (int)pl.field_70165_t, (int)pl.field_70163_u, (int)pl.field_70161_v);
   }

   public static void onEventDrop(LivingDropsEvent event) {
      DBCH.onEventDrop(event);
   }

   public static Block getMedBlock() {
      return BlocksDBC.BlockHealingPods;
   }

   public static void DBCDeath(EntityPlayer p) {
      DBCH.DBCDeath(p);
   }

   public static void DBSpawn(EntityPlayer p, long wt) {
      DBCH.DBSpawn(p, wt);
   }

   public static void Dscndng(int i) {
      DBCKiTech.Dscndng(i);
   }

   public static void Dscndng() {
      DBCKiTech.Dscndng();
   }

   public static boolean DBCKiTechFly() {
      return DBCKiTech.floating;
   }

   public static void spawnSaibaimans(EntityPlayer player) {
      int i = MathHelper.func_76128_c(player.field_70165_t);
      int j = MathHelper.func_76128_c(player.field_70163_u);
      int k = MathHelper.func_76128_c(player.field_70161_v);
      int r = 30;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)i - r, 1.0, (double)k - r, (double)i + r, 254.0, (double)k + r);
      List list = player.field_70170_p.func_72872_a(EntitySaibaiman.class, aabb);
      if (list.size() < 3) {
         Entity s = null;

         for (int l = 0; l < 3 - list.size(); l++) {
            for (int i1 = j + 1 > 254 ? 254 : j + 1; i1 > 50; i1--) {
               Random ran1 = new Random();
               int r1 = ran1.nextInt(10);
               new Random();
               int r2 = ran1.nextInt(10);
               Block block = player.field_70170_p.func_147439_a(i - 5 + r1, i1, k - 5 + r2);
               if (block.func_149688_o() == Material.field_151579_a) {
                  EntitySaibaiman sai1 = new EntitySaibaiman(player.field_70170_p);
                  sai1.func_70012_b(i - 5 + r1, i1, k - 5 + r2, 0.0F, 0.0F);
                  sai1.setETA(player);
                  sai1.settarget(player);
                  sai1.setSpwner(player);
                  player.field_70170_p.func_72838_d(sai1);
                  s = sai1;
                  break;
               }
            }
         }

         if (s != null) {
            s.field_70170_p.func_72956_a(player, "jinryuudragonbc:DBC3.teleport", 1.0F, 1.0F);
         }
      }
   }

   public static void spawnFeezahenchmen(EntityPlayer player) {
      int i = MathHelper.func_76128_c(player.field_70165_t);
      int j = MathHelper.func_76128_c(player.field_70163_u);
      int k = MathHelper.func_76128_c(player.field_70161_v);
      int r = 30;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)i - r, 1.0, (double)k - r, (double)i + r, 254.0, (double)k + r);
      List list = player.field_70170_p.func_72872_a(EntityFreezaSoldiers.class, aabb);
      if (list.size() < 2) {
         Entity s = null;

         for (int i1 = j + 1 > 254 ? 254 : j + 1; i1 > 50; i1--) {
            Random ran1 = new Random();
            int r1 = ran1.nextInt(10);
            new Random();
            int r2 = ran1.nextInt(10);
            Block block1 = player.field_70170_p.func_147439_a(i - 5 + r1, i1, k - 5 + r2);
            if (block1.func_149688_o() == Material.field_151579_a) {
               EntityFreezaSoldier1 men1 = new EntityFreezaSoldier1(player.field_70170_p);
               men1.func_70012_b(i - 5 + r1, i1, k - 5 + r2, 0.0F, 0.0F);
               men1.func_70652_k(player);
               men1.setETA(player);
               men1.settarget(player);
               men1.setSpwner(player);
               player.field_70170_p.func_72838_d(men1);
               s = men1;
            }

            Block block2 = player.field_70170_p.func_147439_a(i - 5 + r1 + 1, i1, k - 5 + r2);
            if (block2.func_149688_o() == Material.field_151579_a) {
               EntityFreezaSoldier2 men2 = new EntityFreezaSoldier2(player.field_70170_p);
               men2.func_70012_b(i - 5 + r1 + 1, i1, k - 5 + r2, 0.0F, 0.0F);
               men2.func_70652_k(player);
               men2.setETA(player);
               men2.settarget(player);
               men2.setSpwner(player);
               player.field_70170_p.func_72838_d(men2);
            }

            Block block3 = player.field_70170_p.func_147439_a(i - 5 + r1 - 1, i1, k - 5 + r2);
            if (block3.func_149688_o() == Material.field_151579_a) {
               EntityFreezaSoldier3 men3 = new EntityFreezaSoldier3(player.field_70170_p);
               men3.func_70012_b(i - 5 + r1 - 1, i1, k - 5 + r2, 0.0F, 0.0F);
               men3.func_70652_k(player);
               men3.setETA(player);
               men3.settarget(player);
               men3.setSpwner(player);
               player.field_70170_p.func_72838_d(men3);
               break;
            }
         }

         if (s != null) {
            s.field_70170_p.func_72956_a(player, "jinryuudragonbc:DBC3.teleport", 1.0F, 1.0F);
         }
      }
   }

   public static List listEvilDBCnpcs(EntityPlayer player, AxisAlignedBB aabb) {
      return player.field_70170_p.func_72872_a(EntityDBCEvil.class, aabb);
   }

   public static void ifEvilDBCnpcs(Entity e, EntityPlayer p) {
      boolean b = e instanceof EntityDBCEvil;
      if (b) {
         e.func_70029_a(p.field_70170_p);
         ((EntityDBCEvil)e).setSpwner(p);
      }
   }

   public static void ifEvilDBCnpcs(Entity e, Entity p) {
      boolean b = e instanceof EntityDBCEvil;
      boolean b2 = p instanceof EntityDBCEvil;
      if (b && b2) {
         e.func_70029_a(p.field_70170_p);
         ((EntityDBCEvil)e).setSpwner(((EntityDBCEvil)p).getSpwner());
         ((EntityDBCEvil)e).settarget(((EntityDBCEvil)p).gettarget());
      }
   }

   public static boolean DBCTrainingArea(EntityPlayer thePlayer) {
      int nX = DBCH.KPminX;
      int xX = DBCH.KPmaxX;
      int nY = DBCH.KPminY;
      int xY = DBCH.KPmaxY;
      int nZ = DBCH.KPminZ;
      int xZ = DBCH.KPmaxZ;
      int pX = (int)thePlayer.field_70165_t;
      int pY = (int)thePlayer.field_70163_u;
      int pZ = (int)thePlayer.field_70161_v;
      return pX > nX && pX < xX && pY > nY && pY < xY && pZ > nZ && pZ < xZ;
   }

   public static void DBCreleaseZeroTurnOffTurbo() {
      if (JRMCoreH.curRelease == 0 && DBCKiTech.turbo) {
         DBCKiTech.turbo = false;
         JRMCoreH.Skll((byte)5, (byte)1, (byte)3);
      }
   }

   public static float DBCsizeBasedOnCns(int[] plyrAttrbts) {
      float f1 = 0.0F;
      int c = plyrAttrbts[2];
      int m = nRP9ea();
      if (mod_DragonBC.ConsSizeChangeOn) {
         f1 += 0.192F * (c > m ? m : c) / m;
      }

      return f1;
   }

   public static float DBCsizeBasedOnCns2(int[] plyrAttrbts) {
      float f1 = 0.0F;
      int c = plyrAttrbts[2];
      int m = nRP9ea();
      if (mod_DragonBC.ConsSizeChangeOn) {
         f1 += 0.192F * (c > m ? m : c) / m;
      } else {
         f1 += 0.2F;
      }

      return f1;
   }

   public static float DBCsizeBasedOnRace(int race, int state) {
      return DBCsizeBasedOnRace(race, state, false);
   }

   public static float DBCsizeBasedOnRace(int race, int state, boolean divine) {
      float f2 = 1.0F;
      if (JRMCoreH.rc_sai(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransSaiBlk.length > state) {
         f2 = JRMCoreH.TransSaiBlk[state];
      }

      if (JRMCoreH.rc_hum(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransHmBlk.length > state) {
         f2 = JRMCoreH.TransHmBlk[state];
      }

      if (JRMCoreH.rc_nam(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransNaBlk.length > state) {
         boolean ssg = godKiUserBase(race, state);
         if (ssg && divine) {
            f2 = 1.1F;
         } else {
            f2 = JRMCoreH.TransNaBlk[state];
         }
      }

      if (JRMCoreH.isRaceMajin(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransMaBulk.length > state) {
         f2 = JRMCoreH.TransMaBulk[state];
      }

      if (JRMCoreH.rc_arc(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransFrBlk.length > state) {
         f2 = JRMCoreH.TransFrBlk[state];
      }

      return f2;
   }

   public static float DBCsizeBasedOnRace2(int race, int state) {
      return DBCsizeBasedOnRace2(race, state, false);
   }

   public static float DBCsizeBasedOnRace2(int race, int state, boolean divine) {
      float f3 = 1.0F;
      if (JRMCoreH.rc_sai(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransSaiSz.length > state) {
         f3 = JRMCoreH.TransSaiSz[state];
      }

      if (JRMCoreH.rc_hum(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransHmSz.length > state) {
         f3 = JRMCoreH.TransHmSz[state];
      }

      if (JRMCoreH.rc_nam(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransNaSz.length > state) {
         boolean ssg = godKiUserBase(race, state);
         if (ssg && divine) {
            f3 = 1.5F;
         } else {
            f3 = JRMCoreH.TransNaSz[state];
         }
      }

      if (JRMCoreH.isRaceMajin(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransMaSize.length > state) {
         f3 = JRMCoreH.TransMaSize[state];
      }

      if (JRMCoreH.rc_arc(race) && mod_DragonBC.TransSizeChangeOn && JRMCoreH.TransFrSz.length > state) {
         f3 = JRMCoreH.TransFrSz[state];
      }

      return f3;
   }

   public static boolean DBCboolPlyrDimNotOtherworld(EntityPlayerMP player) {
      return player.field_71093_bK != otherworld();
   }

   public static boolean FreeRev() {
      return DBCConfig.FreeRev;
   }

   public static boolean FreeRevC() {
      return DBCConfig.cFreeRev;
   }

   public static void FreeRevSet(boolean i) {
      DBCConfig.FreeRev = i;
   }

   public static int otherworld() {
      return DBCConfig.otherWorld;
   }

   public static void WorldTper(MinecraftServer s, EntityPlayerMP p, int w) {
      s.func_71203_ab().transferPlayerToDimension(p, w, WorldTper(s, w));
   }

   private static WorldTeleporterDBCTelep WorldTper(MinecraftServer s, int w) {
      return new WorldTeleporterDBCTelep(s.func_71218_a(w));
   }

   public static boolean isPlayerInNullRealmDimension(EntityPlayerMP player) {
      return player.field_71093_bK == nullrealm();
   }

   public static int nullrealm() {
      return DBCConfig.dimNullRealm;
   }

   public static boolean DBCgetConfigGodformCosm() {
      return DBCConfig.GodformCosm;
   }

   public static boolean DBCgetConfigGodform() {
      return DBCConfig.Godform;
   }

   public static boolean DBCgetConfigcGodform() {
      return DBCConfig.cGodform;
   }

   public static void DBCsetConfigGodform(boolean i) {
      DBCConfig.Godform = i;
   }

   public static int DBCgetConfigcsenzuCool() {
      return DBCConfig.csenzuCool;
   }

   public static void DBCsetConfigsenzuCool(int i) {
      DBCConfig.senzuCool = i;
   }

   public static int DBCgetConfigcmaxTrnExp() {
      return DBCConfig.cmaxTrnExp;
   }

   public static boolean DBCgetConfigcplntVegeta() {
      return DBCConfig.cplntVegeta;
   }

   public static boolean DBCgetConfigcflyAnyLvl() {
      return DBCConfig.cflyAnyLvl;
   }

   public static boolean DBCgetConfigcDeathSystemOff() {
      return mod_DragonBC.cDeathSystemOff;
   }

   public static boolean DBCgetConfigcDBSpawnEnabled() {
      return mod_DragonBC.cDBSpawnEnabled;
   }

   public static String DBCgetConfigcDBSpawnTime() {
      return mod_DragonBC.cDBSpawnTime;
   }

   public static boolean DBCgetConfigcSagaSystemOn() {
      return mod_DragonBC.cSagaSystemOn;
   }

   public static boolean DBCgetConfigcSagaSysSpawnPods() {
      return mod_DragonBC.cSagaSysSpawnPods;
   }

   public static void DBCsetConfigmaxTrnExp(int maxTrnExp) {
      DBCConfig.maxTrnExp = maxTrnExp;
   }

   public static void DBCsetConfigplntVegeta(boolean plntVegeta) {
      DBCConfig.plntVegeta = plntVegeta;
   }

   public static void DBCsetConfigflyAnyLvl(boolean flyAnyLvl) {
      DBCConfig.flyAnyLvl = flyAnyLvl;
   }

   public static void DBCsetConfigDeathSystemOff(boolean DeathSystemOff) {
      mod_DragonBC.DeathSystemOff = DeathSystemOff;
   }

   public static void DBCsetConfigDBSpawnEnabled(boolean DBSpawnEnabled) {
      mod_DragonBC.DBSpawnEnabled = DBSpawnEnabled;
   }

   public static void DBCsetConfigDBSpawnTime(String DBSpawnTime) {
      mod_DragonBC.DBSpawnTime = DBSpawnTime;
   }

   public static void DBCsetConfigDBSagaSystemOn(boolean SagaSystemOn) {
      mod_DragonBC.SagaSystemOn = SagaSystemOn;
   }

   public static void DBCsetConfigDBSagaSysSpawnPods(boolean SagaSysSpawnPods) {
      mod_DragonBC.SagaSysSpawnPods = SagaSysSpawnPods;
   }

   public static Block DBCgetBlockTCPort() {
      return BlocksDBC.BlockTCPort;
   }

   public static boolean DBCgetSagaSystemOn() {
      return mod_DragonBC.SagaSystemOn;
   }

   public static boolean DBCgetConfigplntVegeta() {
      return DBCConfig.plntVegeta;
   }

   public static boolean DBCgetConfigflyAnyLvl() {
      return DBCConfig.flyAnyLvl;
   }

   public static int DBCgetConfigeDBrate() {
      return DBCConfig.eDBrate;
   }

   public static int DBCgetConfignDBrate() {
      return DBCConfig.nDBrate;
   }

   public static boolean DBCgetConfigDeathSystemOff() {
      return mod_DragonBC.DeathSystemOff;
   }

   public static boolean DBCgetConfigDBSpawnEnabled() {
      return mod_DragonBC.DBSpawnEnabled;
   }

   public static String DBCgetConfigDBSpawnTime() {
      return mod_DragonBC.DBSpawnTime;
   }

   public static boolean DBCgetConfigSagaSystemOn() {
      return mod_DragonBC.SagaSystemOn;
   }

   public static boolean DBCgetConfigDeadInv() {
      return DBCConfig.DeadInv;
   }

   public static void DBCsetConfigDeadInv(boolean i) {
      DBCConfig.DeadInv = i;
   }

   public static float DBCgetConfigReinc() {
      return DBCConfig.Reinc;
   }

   public static float DBCgetConfigcReinc() {
      return DBCConfig.cReinc;
   }

   public static void DBCsetConfigReinc(float i) {
      DBCConfig.Reinc = i;
   }

   public static boolean DBCgetEntityDBC(Entity shootingEntity) {
      return shootingEntity instanceof EntityDBC;
   }

   public static int DBCgetConfigcwfb() {
      return mod_DragonBC.cwfb;
   }

   public static int DBCgetConfigchfb() {
      return mod_DragonBC.chfb;
   }

   public static int DBCgetConfigcwfn() {
      return mod_DragonBC.cwfn;
   }

   public static int DBCgetConfigchfn() {
      return mod_DragonBC.chfn;
   }

   public static int DBCgetConfighealingpodhealingrate() {
      return DBCConfig.healingpodhealingrate;
   }

   public static boolean DBCgetConfigDBGT() {
      return DBCConfig.DBGT;
   }

   public static int DBCgetConfighPodUpd() {
      return DBCConfig.hPodUpd;
   }

   public static int DBCgetConfighPodRate(int id) {
      return DBCConfig.hPodRate[id];
   }

   public static boolean DBCgetConfighPodPerc(int id) {
      return DBCConfig.hPodPerc[id];
   }

   public static int[][] npc_ps() {
      int[] Kmhs = new int[3];
      if (DBCH.genKH.contains(";")) {
         for (int i = 0; i < 3; i++) {
            Kmhs[i] = Integer.parseInt(DBCH.genKH.split(";")[i]) + DBCH.genKHnpc1[i];
         }
      }

      int[] ClAr = new int[3];
      if (DBCH.genCA.contains(";")) {
         for (int i = 0; i < 3; i++) {
            ClAr[i] = Integer.parseInt(DBCH.genCA.split(";")[i]) + DBCH.genCAnpc1[i];
         }
      }

      int[] Gkhs = new int[3];
      if (DBCH.genGH.contains(";")) {
         for (int i = 0; i < 3; i++) {
            Gkhs[i] = Integer.parseInt(DBCH.genGH.split(";")[i]) + DBCH.genGHnpc1[i];
         }
      }

      int[] Gkhs2 = new int[3];
      if (DBCH.genGH.contains(";")) {
         for (int i = 0; i < 3; i++) {
            Gkhs2[i] = Integer.parseInt(DBCH.genGH.split(";")[i]) + DBCH.genGHnpc2[i];
         }
      }

      int[] FzSp = new int[3];
      if (DBCH.genFS.contains(";")) {
         for (int i = 0; i < 3; i++) {
            FzSp[i] = Integer.parseInt(DBCH.genFS.split(";")[i]) + DBCH.genFSnpc1[i];
         }
      }

      int[] BS = new int[3];
      if (DBCH.genBS.contains(";")) {
         for (int i = 0; i < 3; i++) {
            BS[i] = Integer.parseInt(DBCH.genBS.split(";")[i]) + DBCH.genBSnpc1[i];
         }
      }

      int[] GURU = new int[3];
      if (DBCH.genGuru.contains(";")) {
         for (int i = 0; i < 3; i++) {
            GURU[i] = Integer.parseInt(DBCH.genGuru.split(";")[i]) + DBCH.genGurunpc1[i];
         }
      }

      return new int[][]{Kmhs, DBCH.Kami, DBCH.Karn, DBCH.Enma, DBCH.KaiO, ClAr, Gkhs, Gkhs2, FzSp, BS, DBCH.TrnksF, DBCH.Jin, GURU, DBCH.Whis1, DBCH.Whis2};
   }

   public static EntityDBCKami[] npc_ent(World w) {
      return new EntityDBCKami[]{
         new EntityMasterRoshi(w),
         new EntityMasterKami(w),
         new EntityMasterKarin(w),
         new EntityMasterEnma(w),
         new EntityMasterKaio(w),
         new EntityMasterCell(w),
         new EntityMasterGoku(w),
         new EntityMasterGohan(w),
         new EntityMasterFreeza(w),
         new EntityMasterBabidi(w),
         new EntityMasterTrunksFuture(w),
         new EntityMasterJin(w),
         new EntityMasterGuru(w),
         new EntityMasterWhis(w),
         new EntityMasterWhis(w)
      };
   }

   public static Class[] npc_entclss() {
      return new Class[]{
         EntityMasterRoshi.class,
         EntityMasterKami.class,
         EntityMasterKarin.class,
         EntityMasterEnma.class,
         EntityMasterKaio.class,
         EntityMasterCell.class,
         EntityMasterGoku.class,
         EntityMasterGohan.class,
         EntityMasterFreeza.class,
         EntityMasterBabidi.class,
         EntityMasterTrunksFuture.class,
         EntityMasterJin.class,
         EntityMasterGuru.class,
         EntityMasterWhis.class,
         EntityMasterWhis.class
      };
   }

   public static int[] npc_dims() {
      return new int[]{
         0,
         0,
         0,
         DBCConfig.otherWorld,
         DBCConfig.otherWorld,
         0,
         0,
         0,
         DBCConfig.planetNamek,
         0,
         0,
         DBCConfig.otherWorld,
         DBCConfig.planetNamek,
         0,
         DBCConfig.dimNullRealm
      };
   }

   public static boolean[] npc_spawn() {
      return new boolean[]{
         DBCH.genKH.contains(";"),
         true,
         true,
         true,
         true,
         DBCH.genCA.contains(";"),
         DBCH.genGH.contains(";"),
         DBCH.genGH.contains(";"),
         DBCH.genFS.contains(";"),
         DBCH.genBS.contains(";"),
         true,
         true,
         DBCH.genGuru.contains(";"),
         true,
         true
      };
   }

   public static void DBCCommonTickHandlerNPCSpawnCheck(EntityPlayerMP player) {
      if (JRMCoreConfig.NPCSpawnCheck) {
         int[][] ps = npc_ps();
         EntityDBCKami[] ent = npc_ent(player.field_70170_p);
         Class[] entclss = npc_entclss();
         int[] dims = npc_dims();
         boolean[] spawn = npc_spawn();

         for (int i = 0; i < ps.length; i++) {
            int[] npc = ps[i];
            if (dims[i] == player.field_71093_bK && npc.length > 2) {
               int a = 2;
               AxisAlignedBB ab = AxisAlignedBB.func_72330_a(npc[0] - a, npc[1] - a, npc[2] - a, npc[0] + a, npc[1] + a, npc[2] + a);
               List enma = player.field_70170_p.func_72872_a(entclss[i], ab);
               if (enma.isEmpty() && spawn[i]) {
                  EntityDBCKami en = ent[i];
                  en.func_70012_b(npc[0], npc[1], npc[2], 0.0F, 0.0F);
                  player.field_70170_p.func_72838_d(en);
               }
            }
         }
      }
   }

   public static void DBCCommonTickHandlerWorldGenBuildingsResetted(MinecraftServer server) {
      if (JRMCoreH.DBCresetted) {
         JRMCoreH.DBCresetted = false;
         WorldGenBuildingsSpawnCheck(server);
      }
   }

   public static void WorldGenBuildingsSpawnCheck(MinecraftServer server) {
      DBCH.genKH = DBCH.khrwi(server);
      DBCH.genCA = DBCH.carwi(server);
      DBCH.genGH = DBCH.ghrwi(server);
      DBCH.genFS = DBCH.fsrwi(server);
      DBCH.genBS = DBCH.bsrwi(server);
      DBCH.genGuru = DBCH.guruhrwi(server);
   }

   public static void spawnBuilds(MinecraftServer server) {
      JRMCoreSafe safe = new JRMCoreSafe(server.func_130014_f_());
      safe.saveSpawnList("false ", safe.OWDataDir, "edp.dbc");
      safe.saveSpawnList("false ", safe.OWDataDir, "swk.dbc");
      safe.saveSpawnList("false ", safe.dataDir, "kl.dbc");
      WorldGenBuildingsSpawnCheck(server);
      int[] ClAr = new int[3];
      if (DBCH.genCA.contains(";")) {
         for (int i = 0; i < 3; i++) {
            ClAr[i] = Integer.parseInt(DBCH.genCA.split(";")[i]);
         }
      }

      int[] Gkhs = new int[3];
      if (DBCH.genGH.contains(";")) {
         for (int i = 0; i < 3; i++) {
            Gkhs[i] = Integer.parseInt(DBCH.genGH.split(";")[i]);
         }
      }

      int[] FzSp = new int[3];
      if (DBCH.genFS.contains(";")) {
         for (int i = 0; i < 3; i++) {
            FzSp[i] = Integer.parseInt(DBCH.genFS.split(";")[i]);
         }
      }

      int[] BS = new int[3];
      if (DBCH.genBS.contains(";")) {
         for (int i = 0; i < 3; i++) {
            BS[i] = Integer.parseInt(DBCH.genBS.split(";")[i]);
         }
      }

      int[] GURU = new int[3];
      if (DBCH.genGuru.contains(";")) {
         for (int i = 0; i < 3; i++) {
            GURU[i] = Integer.parseInt(DBCH.genGuru.split(";")[i]);
         }
      }

      int[][] ps = new int[][]{ClAr, Gkhs, FzSp, BS, GURU};

      for (int i = 0; i < ps.length; i++) {
         int[] npc = ps[i];
         if (!server.func_71218_a(WorldGeneratorDB.DBbuildsdim[i]).field_72995_K && npc.length > 2) {
            builds v = WorldGeneratorDB.DBbuilds[i];
            World w = server.func_71218_a(WorldGeneratorDB.DBbuildsdim[i]);
            v.setWorld(w);
            if ((npc[0] != 0 || npc[1] != 0 || npc[2] != 0) && !JRMCoreComTickH.bs.contains(WorldGeneratorDB.DBbuildsNams2(i))) {
               v.func_76484_a(w, w.field_73012_v, npc[0], npc[1], npc[2]);
            }
         }
      }
   }

   public static int d5keKm(String l) {
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

   public static int nRP9ea() {
      int b = JRMCoreConfig.tmx;
      String r = "64";
      String k = "3B9ACA00";
      return b > d5keKm(k) ? d5keKm(k) : (b < d5keKm(r) ? 0 : b);
   }

   public static void JRMCoreEHonPlayerInteract(PlayerInteractEvent event) {
      EntityPlayer p = event.entityPlayer;
      int x = event.x;
      int y = event.y;
      int z = event.z;
      int length = JRMCoreEH.allSafeZones.size();

      for (int i = length - 1; i >= 0; i--) {
         EntitySafeZone sz = (EntitySafeZone)JRMCoreEH.allSafeZones.get(i);
         if (sz == null || sz.field_70128_L) {
            JRMCoreEH.allSafeZones.remove(i);
         } else if (sz.field_71093_bK == p.field_71093_bK) {
            AxisAlignedBB ab = sz.createSafeZoneHitBox();
            if (ab.field_72340_a < x && x < ab.field_72336_d && ab.field_72338_b < y && y < ab.field_72337_e && ab.field_72339_c < z && z < ab.field_72334_f) {
               Block block = p.field_70170_p.func_147439_a(x, y, z);
               boolean door = false;
               if (JRMCoreConfig.sfzna != null) {
                  for (int j = 0; j < JRMCoreConfig.sfzna.length; j++) {
                     if (block == Block.func_149684_b(JRMCoreConfig.sfzna[j])) {
                        door = true;
                        break;
                     }
                  }
               }

               if (block != null && !door && event.isCancelable() || event.action == Action.LEFT_CLICK_BLOCK && block != null && door) {
                  event.setCanceled(true);
                  return;
               }
            }
         }
      }
   }

   public static boolean JRMCoreEHonLivingHurtSafeZone(EntityLivingBase targetEntity) {
      if (targetEntity instanceof EntityPlayer) {
         EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
         int x = (int)targetEntity.field_70165_t;
         int y = (int)targetEntity.field_70163_u;
         int z = (int)targetEntity.field_70161_v;
         int length = JRMCoreEH.allSafeZones.size();

         for (int i = length - 1; i >= 0; i--) {
            EntitySafeZone sz = (EntitySafeZone)JRMCoreEH.allSafeZones.get(i);
            if (sz == null || sz.field_70128_L) {
               JRMCoreEH.allSafeZones.remove(i);
            } else if (sz.field_71093_bK == targetPlayer.field_71093_bK) {
               AxisAlignedBB ab = sz.createSafeZoneHitBox();
               if (ab.field_72340_a < x && x < ab.field_72336_d && ab.field_72338_b < y && y < ab.field_72337_e && ab.field_72339_c < z && z < ab.field_72334_f
                  )
                {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean checkForBuildsAound(World world, int RPX, int RPZ, int s) {
      WorldGenBuildingsSpawnCheck(FMLCommonHandler.instance().getMinecraftServerInstance());
      s = s + 50 + (world.field_73011_w.field_76574_g == 22 ? 20 : 0);
      boolean bo = true;
      int[][] ps = npc_ps();
      int[] dims = npc_dims();

      for (int i = 0; i < ps.length; i++) {
         int[] npc = ps[i];
         if (dims[i] == world.field_73011_w.field_76574_g && npc.length > 2) {
            double genX = npc[0];
            double genY = npc[1];
            double genZ = npc[2];
            AxisAlignedBB ab = AxisAlignedBB.func_72330_a((double)RPX - s, 65.0, (double)RPZ - s, (double)RPX + s, 100.0, (double)RPZ + s);
            AxisAlignedBB ab2 = AxisAlignedBB.func_72330_a(genX - s, 65.0, genZ - s, genX + s, 100.0, genZ + s);
            if (ab.func_72326_a(ab2)) {
               bo = false;
            }
         }
      }

      return bo;
   }

   public static boolean hasHalo(EntityPlayer p) {
      return JRMCoreH.getByte(p, "jrmcAlv") == 1;
   }

   public static boolean isAlive(EntityPlayer p) {
      return JRMCoreH.getByte(p, "jrmcAlv") == 0;
   }

   public static int DBCgetConfigTechExpRate() {
      return DBCConfig.TechExpRate;
   }

   public static int DBCgetConfigTechExpNeed() {
      return DBCConfig.TechExpNeed;
   }

   public static int DBCgetConfigTechCostMod() {
      return DBCConfig.TechCostMod;
   }

   public static int DBCgetConfigcTechExpNeed() {
      return DBCConfig.cTechExpNeed;
   }

   public static int DBCgetConfigcTechCostMod() {
      return DBCConfig.cTechCostMod;
   }

   public static void DBCsetConfigTechExpNeed(int i) {
      DBCConfig.TechExpNeed = i;
   }

   public static void DBCsetConfigTechCostMod(int i) {
      DBCConfig.TechCostMod = i;
   }

   public static void reincarnate(EntityPlayer p) {
      if (!JRMCoreH.isFused(p)) {
         JRMCoreH.setInt(1, p, "jrmcRencrnt");

         for (int j = 0; j < 6; j++) {
            int i = (int)(JRMCoreH.getInt(p, JRMCoreH.AttrbtNbtI[j]) * DBCConfig.Reinc);
            JRMCoreH.setInt(i, p, JRMCoreH.AttrbtNbtR[j]);
         }

         JRMCoreH.resetChar(p);
         if (DBCgetConfigDeadInv()
            && p.field_70170_p.func_82736_K().func_82766_b("keepInventory")
            && !p.field_71075_bZ.field_75098_d
            && JRMCoreH.getByte(p, "jrmcAlv") == 1) {
            JRMCoreH.nbt(p).func_74782_a("InventoryDead", p.field_71071_by.func_70442_a(new NBTTagList()));
            p.field_71071_by.func_70443_b(JRMCoreH.nbt(p).func_150295_c("InventoryLiving", 10));
            p.getEntityData().func_74782_a("Inventory", p.field_71071_by.func_70442_a(new NBTTagList()));
         }

         JRMCoreH.setByte(0, p, "jrmcAlv");
         if (p.field_71093_bK != DBCConfig.planetEarth) {
            FMLCommonHandler.instance()
               .getMinecraftServerInstance()
               .func_71203_ab()
               .transferPlayerToDimension(
                  (EntityPlayerMP)p,
                  DBCConfig.planetEarth,
                  new WorldTeleporterDBCTelep(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetEarth))
               );
            p.func_71023_q(1);
            double[] d = DBCConfig.RevLocG;
            byte by = 0;
            ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1], d[2], (float)DBCConfig.RevAng[0][0], (float)DBCConfig.RevAng[0][1]);
            ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1] + 1.0, d[2], (float)DBCConfig.RevAng[0][0], (float)DBCConfig.RevAng[0][1]);
         }
      }
   }

   public static String f_namgen(String s1, String s2) {
      return s1.substring(0, s1.length() / 2) + s2.substring(s2.length() / 2);
   }

   public static void requestNullRealmKnockout() {
      PD.sendToServer(new DBCPwish(7, ""));
   }
}
