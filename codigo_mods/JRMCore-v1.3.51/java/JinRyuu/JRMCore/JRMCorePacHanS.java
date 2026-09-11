package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.entity.EntityNPCshadow;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.p.JRMCorePData2;
import JinRyuu.JRMCore.p.JRMCorePExpl;
import JinRyuu.JRMCore.p.JRMCorePQuad;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.JGEntityHelper;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameAirBoxing;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameConcentration;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import com.google.gson.Gson;
import cpw.mods.fml.common.FMLCommonHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class JRMCorePacHanS {
   public static final int HALF_RELEASE = 50;
   public static final int MAX_RELEASE = 100;
   public static final int RELEASE_PER_POTENTIAL_UNLOCK = 5;
   public static final ChatStyle chatStyle = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
   public static final int DATA_TYPE_SOUND = 1;
   public static final int MISSION_DATA = 2;
   public static final int GROUP_DATA = 3;
   public static final int TRAINING = 10;
   public static final int FAMILYC_FAMILY_DATA = 200;
   public static final int FORM_GROUP = 1;
   public static final int ACCEPT_GROUP = 2;
   public static final int DECLINE_GROUP = 3;
   public static final int DISBAND_GROUP = 4;
   public static final int LEAVE_GROUP = 5;
   public static final int INVITE_TO_GROUP = 6;
   public static final int SET_LEADER_GROUP = 7;
   public static final int KICK_FROM_GROUP = 8;
   public static final int SPAWN_DUMMY = 0;
   public static final int MINIGAME_CONCENTRATION = 1;
   public static final int MINIGAME_AIRBOXING = 2;
   public static final int TP_LIMIT_CHECK_REQUEST = -1;
   public static final int MINIGAME_TP_COST = 0;
   public static final int MINIGAME_COLLECT_TP_REWARD = 1;
   public static final int RAGE_GAIN = 1;
   public static final int BLOCKING = 2;
   public static final int KI_CHARGE_EFFECT = 3;
   public static final int TECHNIQUE = 4;
   public static final int HEAT_GAIN = 5;
   public static final int SKILL_ADD = 1;
   public static final int SKILL_DELETE = 2;
   public static final int SKILL_INCREASE = 3;
   public static final int SKILL_DECREASE = 4;
   public static final int STATUS_EFFECT = 5;
   public static final int PLAYER_SETTINGS_ONOFF = 6;
   public static final int PLAYER_SETTINGS_SET = 8;
   public static final int STATUS_EFFECT_ON = 0;
   public static final int STATUS_EFFECT_OFF = 1;
   public static int RAGE_GAIN_PLUS = 0;
   public static int RAGE_GAIN_MINUS = 1;
   public double explosionX;
   public double explosionY;
   public double explosionZ;
   public float explosionSize;
   public List chunkPositionRecords;
   public float playerVelocityX;
   public float playerVelocityY;
   public float playerVelocityZ;
   public boolean expGriOff;
   public double expDam;
   public Entity origin;
   public byte type;
   public static HashMap<String, Long> ckr = new HashMap<>();
   public static String R = "jrmcRace";
   public static String P = "jrmcPwrtyp";
   public static String Cl = "jrmcClass";
   public static String Acc = "jrmcAccept";
   public static String St = "jrmcState";
   public static String Diff = "jrmcDiff";
   public static String Ptch = "jrmcPtch";

   public void handleExpl(
      double explosionX,
      double explosionY,
      double explosionZ,
      float explosionSize,
      boolean expGriOff,
      double expDam,
      Entity origin,
      List chunkPositionRecords,
      float playerVelocityX,
      float playerVelocityY,
      float playerVelocityZ,
      EntityPlayer p,
      byte type
   ) {
      this.explosionX = explosionX;
      this.explosionY = explosionY;
      this.explosionZ = explosionZ;
      this.explosionSize = explosionSize;
      this.expGriOff = expGriOff;
      this.expDam = expDam;
      this.chunkPositionRecords = chunkPositionRecords;
      int var3x = (int)this.explosionX;
      int var4 = (int)this.explosionY;
      int var5x = (int)this.explosionZ;
      this.playerVelocityX = playerVelocityX;
      this.playerVelocityY = playerVelocityY;
      this.playerVelocityZ = playerVelocityZ;
      this.type = type;
      PD.sendToDimension(
         new JRMCorePExpl(
            explosionX,
            explosionY,
            explosionZ,
            explosionSize,
            expGriOff,
            expDam,
            origin,
            chunkPositionRecords,
            playerVelocityX,
            playerVelocityY,
            playerVelocityZ,
            type
         ),
         origin.field_71093_bK
      );
   }

   public void handleData(int c, String d, EntityPlayer p) {
      if (c == 0) {
         byte acc = JRMCoreH.getByte(p, Acc);
         if (acc == 0) {
            int race = JRMCoreH.getByte(p, R);
            if (JRMCoreH.isRaceMajin(race)) {
               int hc = JRMCoreH.dnsHairC(d);
               int bodyColor = JRMCoreH.dnsBodyCM(d);
               if (hc != bodyColor) {
                  hc = bodyColor;
                  d = JRMCoreH.dnsHairCSet(d, hc);
               }
            }

            JRMCoreH.setString(d, p, "jrmcDNS");
         } else {
            String dnsCur = JRMCoreH.getString(p, "jrmcDNS");
            String dnsTemp = d;
            int hb = JRMCoreH.dnsHairB(dnsCur);
            int hf = JRMCoreH.dnsHairF(dnsCur);
            int hc = JRMCoreH.dnsHairC(dnsCur);
            int race = JRMCoreH.getByte(p, R);
            if (JRMCoreH.isRaceMajin(race)) {
               int bodyColor = JRMCoreH.dnsBodyCM(dnsCur);
               if (hc != bodyColor) {
                  hc = bodyColor;
               }
            }

            dnsTemp = JRMCoreH.dnsHairBSet(dnsTemp, hb);
            dnsTemp = JRMCoreH.dnsHairFSet(dnsTemp, hf);
            dnsTemp = JRMCoreH.dnsHairCSet(dnsTemp, hc);
            if (dnsCur.equals(dnsTemp)) {
               JRMCoreH.setString(d, p, "jrmcDNS");
            }
         }
      }

      if (c == 1) {
         byte acc = JRMCoreH.getByte(p, Acc);
         JRMCoreH.setString(d, p, "jrmcDNSH");
      }

      if (c == 2) {
         JRMCoreH.setInt(Integer.parseInt(d), p, "jrmcAuraColor");
      }

      if (c == 3) {
         byte acc = JRMCoreH.getByte(p, Acc);
         int rc = JRMCoreH.getByte(p, R);
         String dnsau = JRMCoreH.getString(p, "jrmcDNSAU");
         if (acc == 1 && JRMCoreH.rc_arc(rc) && JRMCoreHDBC.auc(JRMCoreH.SklLvlX(p)) && !dnsau.contains(";")) {
            JRMCoreH.setString(d, p, "jrmcDNSAU");
         }
      }

      if (c == 4) {
         byte acc = JRMCoreH.getByte(p, Acc);
         int rc = JRMCoreH.getByte(p, R);
         String dnsau = JRMCoreH.getString(p, "jrmcDNSAU");
         if (acc == 1 && JRMCoreH.rc_arc(rc) && JRMCoreHDBC.auc(JRMCoreH.SklLvlX(p)) && !dnsau.contains(";")) {
            JRMCoreH.setString(";" + d, p, "jrmcDNSAU");
         }
      }

      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
      String SenderName = server.func_71266_T() ? p.func_146103_bH().getId() + ";" + p.func_70005_c_() : p.func_70005_c_();
      NBTTagCompound nbt = this.nbt(p, "pres");
      if (c == 80 && JRMCoreConfig.ssurl.contains("http://")) {
         String[] ssurl = JRMCoreConfig.ssurl.split(",");
         String o = getUrlContents(ssurl[0] + ssurl[1] + SenderName);
         if (d.contains("q")) {
            JRMCoreH.jrmcDataToP(c, o, p);
         }

         if (d.contains("r") && !o.equalsIgnoreCase("error")) {
            String o1 = o.isEmpty() ? "0" : o;
            int q = Integer.parseInt(o1);
            int r = Integer.parseInt(d.substring(1));
            String ssc = JRMCoreConfig.ssc;
            if (ssc.contains("http://")) {
               ssc = getUrlContents(ssc);
            }

            String[] s1 = ssc.split(";");
            String uid = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
            mod_JRMCore.logger.info("PWSPlog: Phase1! " + p.func_70005_c_() + " issued purchase for localID " + (r + 1) + "! UniqueID=" + uid);
            SenderName = SenderName + ";" + uid;
            if (!ssc.contains("=")) {
               mod_JRMCore.logger.info("PWSPlog: " + p.func_70005_c_() + " something went wrong!");
               p.func_145747_a(new ChatComponentText("something went wrong, get in contact with an admin!").func_150255_a(chatStyle));
            } else {
               String[] sn = new String[s1.length];
               String[] sc = new String[s1.length];

               for (int i = 0; i < s1.length; i++) {
                  String[] s2 = s1[i].split("=");
                  sn[i] = s2[0];
                  sc[i] = s2[1];
               }

               int curcost = Integer.parseInt(sc[r]);
               int a = q - curcost;
               mod_JRMCore.logger.info("PWSPlog: Phase2!");
               if (a >= 0) {
                  String cost = getUrlContents(ssurl[0] + ssurl[1] + SenderName + ssurl[2] + curcost + ssurl[3] + (r + 1) + ssurl[4]);
                  mod_JRMCore.logger.info("PWSPlog: Phase3!");
                  if (cost.contains("has used up")) {
                     mod_JRMCore.logger.info("PWSPlog: Begin!");
                     if (sn[r].substring(0, 2).contains("TP")) {
                        int curtp = nbt.func_74762_e("jrmcTpint");
                        int addtp = Integer.parseInt(sn[r].substring(2));
                        int tp = curtp + addtp;
                        int wast = 0;
                        if (tp > JRMCoreH.getMaxTP()) {
                           wast = tp - JRMCoreH.getMaxTP();
                           tp = JRMCoreH.getMaxTP();
                        }

                        nbt.func_74768_a("jrmcTpint", tp);
                        JRMCoreH.jrmcDataToP(c, a + "", p);
                        mod_JRMCore.logger
                           .info(
                              "PWSPlog: "
                                 + p.func_70005_c_()
                                 + " successfully bought "
                                 + addtp
                                 + " TP and now has "
                                 + tp
                                 + (wast > 0 ? " and " + wast + " has gone wasted!" : ".")
                           );
                        p.func_145747_a(
                           new ChatComponentText(
                                 "You have successfully bought " + addtp + " TP and now has " + tp + (wast > 0 ? " and " + wast + " has gone wasted!" : ".")
                              )
                              .func_150255_a(chatStyle)
                        );
                     } else if (sn[r].substring(0, 2).contains("CM")) {
                        String it = "";
                        if (!sn[r].contains("||")) {
                           String name = sn[r].split("!")[0].substring(2);
                           String com = sn[r].split("!")[1];
                           String s = String.format(com.replace("@p", "%s"), p.func_70005_c_());
                           p.func_145747_a(new ChatComponentText("You have successfully bought " + name + "!").func_150255_a(chatStyle));
                           mod_JRMCore.logger.info("PWSPlog: " + p.func_70005_c_() + " successfully bought " + name + "!");
                           p.field_70170_p.func_147449_b(0, 254, 0, Blocks.field_150483_bI);
                           TileEntity tileentity = p.field_70170_p.func_147438_o(0, 254, 0);
                           CommandBlockLogic commandblocklogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
                           commandblocklogic.func_145752_a(s);
                           commandblocklogic.func_145755_a(p.field_70170_p);
                           p.field_70170_p.func_147468_f(0, 254, 0);
                        } else {
                           String[] ssa = sn[r].split("\\|\\|");
                           it = ssa[0].substring(2);

                           for (int i = 1; i < ssa.length; i++) {
                              String nam = ssa[i];
                              String name = nam.split("!")[0].substring(2);
                              String com = nam.split("!")[1];
                              String s = String.format(com.replace("@p", "%s"), p.func_70005_c_());
                              p.field_70170_p.func_147449_b(0, 254, 0, Blocks.field_150483_bI);
                              TileEntity tileentity = p.field_70170_p.func_147438_o(0, 254, 0);
                              CommandBlockLogic commandblocklogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
                              commandblocklogic.func_145752_a(s);
                              commandblocklogic.func_145755_a(p.field_70170_p);
                              p.field_70170_p.func_147468_f(0, 254, 0);
                           }

                           p.func_145747_a(new ChatComponentText("You have successfully bought " + it + "!").func_150255_a(chatStyle));
                           mod_JRMCore.logger.info("PWSPlog: " + p.func_70005_c_() + " successfully bought " + it + "!");
                        }
                     } else if (sn[r].substring(0, 2).contains("IT")) {
                        String it = "";
                        if (sn[r].contains("||")) {
                           String[] ssa = sn[r].split("\\|\\|");
                           it = ssa[0].substring(2);

                           for (int i = 1; i < ssa.length; i++) {
                              String nam = ssa[i];
                              String n1 = "";
                              int n2 = 1;
                              int n3 = 0;
                              if (i > 0) {
                                 String[] s2 = nam.split(",");
                                 if (s2.length > 1) {
                                    n2 = Integer.parseInt(s2[1]);
                                 }

                                 if (s2[0].contains("::")) {
                                    String[] s3 = s2[0].split("::");
                                    n1 = s3[0];
                                    n3 = Integer.parseInt(s3[1]);
                                 } else {
                                    n1 = s2[0];
                                 }

                                 Item item = JRMCoreH.getItemByText(n1);
                                 if (item != null) {
                                    ItemStack itemstack = new ItemStack(item, n2, n3);
                                    EntityItem entityitem = p.func_71019_a(itemstack, false);
                                    entityitem.field_145804_b = 0;
                                 }
                              } else {
                                 it = nam.substring(2);
                              }
                           }
                        } else {
                           String n1 = "";
                           int n2 = 1;
                           int n3 = 0;
                           String[] s2 = sn[r].split(",");
                           if (s2.length > 1) {
                              n2 = Integer.parseInt(s2[1]);
                           }

                           if (s2[0].contains("::")) {
                              String[] s3 = s2[0].split("::");
                              n1 = s3[0].substring(2);
                              n3 = Integer.parseInt(s3[1]);
                           } else {
                              n1 = s2[0].substring(2);
                           }

                           Item item = JRMCoreH.getItemByText(n1);
                           if (item != null) {
                              ItemStack itemstack = new ItemStack(item, n2, n3);
                              EntityItem entityitem = p.func_71019_a(itemstack, false);
                              entityitem.field_145804_b = 0;
                              it = itemstack.func_82833_r();
                           }
                        }

                        mod_JRMCore.logger.info("PWSPlog: " + p.func_70005_c_() + " successfully bought " + it);
                        p.func_145747_a(new ChatComponentText("You have successfully bought " + it).func_150255_a(chatStyle));
                     }
                  }
               }
            }

            mod_JRMCore.logger.info("PWSPlog: END");
         }
      } else if (c == 80) {
         mod_JRMCore.logger.info("PWSPlog: " + p.func_70005_c_() + " something terribly went wrong!");
         p.func_145747_a(new ChatComponentText("something terribly went wrong, get in contact with an admin!").func_150255_a(chatStyle));
      }
   }

   public void spawcha(EntityPlayer p, World w, String n, String h, String a, String m, String pr, double x, double y, double z, int g) {
      JGEntityHelper.spawcha(p, w, n, h, a, m, pr, x, y, z, g);
   }

   public void handleData2(String c, String d, EntityPlayer p) {
      if (c.length() <= 1 && d.length() <= 1) {
         NBTTagCompound nbt = this.nbt(p, "pres");
         String msd = nbt.func_74779_i("JRMCmissionSync");
         String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
         String msdO = msd;
         String msdVO = msdV;
         HashMap<String, Integer> h = new HashMap<>();
         if (msd.length() > 3) {
            for (int i = 0; i < JRMCoreM.getSydaAmount(msd); i++) {
               int mid = JRMCoreM.getMda_Mission(msd, i);
               String sid = JRMCoreM.getMda_Series(msd, i);
               h.put(sid, mid);
            }
         }

         int pw = nbt.func_74771_c(P);
         int rc = nbt.func_74771_c(R);
         int cl = nbt.func_74771_c(Cl);

         for (String seriesID : JRMCoreM.missions.keySet()) {
            JRMCoreMsnBundle M = JRMCoreM.missions.get(seriesID);
            if (M != null) {
               List<JRMCoreMsn> msnl = M.getMissions();
               boolean unlocked = true;
               if (M.getSettings().getUnlock().length() > 0) {
                  String[] sidd = M.getSettings().getUnlock().split(",");

                  for (int i = 0; i < sidd.length; i++) {
                     unlocked = JRMCoreM.isUnlocked(sidd[i], msd, msdV);
                     if (!unlocked) {
                        break;
                     }
                  }
               }

               boolean reset = true;
               String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
               String rp = JRMCoreM.getSydaV(sydaV, 2);
               boolean canRepeat = rp.equals("0");
               if (sydaV.length > 1) {
                  reset = !M.getVersion().equalsIgnoreCase(sydaV[1]);
               }

               rp = reset ? "0" : rp;
               String cc = JRMCoreM.getSydaV(sydaV, 3);
               String ccv = reset ? "0" : JRMCoreM.getSydaV(sydaV, 4);
               msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), rp, cc, ccv);
               int msnToSendID = 0;
               boolean hasSyda = false;
               if (h.containsKey(seriesID)) {
                  if (!reset) {
                     msnToSendID = h.get(seriesID);
                  }

                  hasSyda = true;
               }

               for (int j = 0; j < msnl.size(); j++) {
                  JRMCoreMsn msn = msnl.get(j);
                  if (hasSyda && reset) {
                     JRMCoreM.resetMsnBndl(hasSyda, reset, msn, msnToSendID, pw, rc, cl, msd, msdV, msdO, msdVO, seriesID, nbt, M, p);
                  } else if (msn.getId() == msnToSendID) {
                     ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                     int size = o.size();
                     if (hasSyda) {
                        if (JRMCoreM.getMda_Con(JRMCoreM.getMda(msd, seriesID), size - 1).equals("") || reset) {
                           String[] ar = new String[]{"0"};
                           String[] var105 = new String[size];

                           for (int i = 0; i < size; i++) {
                              var105[i] = "0";
                           }

                           msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, var105);
                           rp = reset ? "0" : rp;
                           cc = JRMCoreM.getSydaV(sydaV, 3);
                           ccv = reset ? "0" : JRMCoreM.getSydaV(sydaV, 4);
                           msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), rp, cc, ccv);
                           if (!msd.equalsIgnoreCase(msdO)) {
                              nbt.func_74778_a("JRMCmissionSync", msd);
                           }

                           if (!msdV.equalsIgnoreCase(msdVO)) {
                              nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                           }
                        }
                     } else {
                        String[] ar = new String[size];

                        for (int i = 0; i < ar.length; i++) {
                           ar[i] = "0";
                        }

                        msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, ar);
                        rp = reset ? "0" : rp;
                        cc = JRMCoreM.getSydaV(sydaV, 3);
                        ccv = reset ? "0" : JRMCoreM.getSydaV(sydaV, 4);
                        msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), rp, cc, ccv);
                        if (!msd.equalsIgnoreCase(msdO)) {
                           nbt.func_74778_a("JRMCmissionSync", msd);
                        }

                        if (!msdV.equalsIgnoreCase(msdVO)) {
                           nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                        }
                     }

                     PD.sendTo(
                        new JRMCorePData2(
                           seriesID
                              + ";"
                              + M.getName()
                              + ";"
                              + M.getDesc()
                              + ";"
                              + M.getAuthor()
                              + ";"
                              + M.getVersion()
                              + ";"
                              + M.getMods()
                              + ";"
                              + M.settings.repeat
                              + ";"
                              + M.settings.unlock,
                           new Gson().toJson(msn, JRMCoreM.JSN_TYPE_MSN)
                        ),
                        (EntityPlayerMP)p
                     );
                     break;
                  }
               }
            }
         }
      } else {
         NBTTagCompound nbt = this.nbt(p, "pres");
         if (c.length() > 0) {
            String msd = nbt.func_74779_i("JRMCmissionSync");
            String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
            String msdVO = msdV;
            HashMap<String, Integer> h = new HashMap<>();
            if (msd.length() > 3) {
               for (int i = 0; i < JRMCoreM.getSydaAmount(msd); i++) {
                  String sid = JRMCoreM.getMda_Series(msd, i);
                  int mid = JRMCoreM.getMda_Mission(msd, i);
                  h.put(sid, mid);
               }
            }

            int pw = nbt.func_74771_c(P);
            int rc = nbt.func_74771_c(R);
            int cl = nbt.func_74771_c(Cl);
            int al = nbt.func_74771_c("jrmcAlign");
            ArrayList<EntityPlayer> gp = JRMCoreM.prog(p, true);
            int g = gp.size();
            String seriesID = c;
            JRMCoreMsnBundle M = JRMCoreM.missions.get(seriesID);
            List<JRMCoreMsn> msnl = M.getMissions();
            boolean unlocked = true;
            if (M.getSettings().getUnlock().length() > 0) {
               String[] sidd = M.getSettings().getUnlock().split(",");

               for (int i = 0; i < sidd.length; i++) {
                  unlocked = JRMCoreM.isUnlocked(sidd[i], msd, msdV);
                  if (!unlocked) {
                     break;
                  }
               }
            }

            String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
            String rp = JRMCoreM.getSydaV(sydaV, 2);
            boolean canRepeat = rp.equals("0");
            int msnToSendID = 0;
            if (h.containsKey(seriesID)) {
               msnToSendID = h.get(seriesID);
            }

            if (canRepeat && unlocked) {
               for (int j = 0; j < msnl.size(); j++) {
                  JRMCoreMsn msn = msnl.get(j);
                  if (msn.getId() == msnToSendID) {
                     gp = JRMCoreM.prog(p, true, c, msnToSendID);
                     ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                     int size = o.size();
                     boolean start = false;
                     String ps = msn.getProps().get(0);

                     for (int i = 0; i < o.size(); i++) {
                        String os = o.get(i);
                        String t = JRMCoreM.getMCo_type(os);
                        String d1 = JRMCoreM.getMCo_data(os, "N");
                        if (t.equalsIgnoreCase("item")) {
                           String[] d2 = d1.split("::");
                           Item im = JRMCoreH.getItemByText(d2.length > 1 ? d2[0] : d1);
                           ItemStack is = im != null ? new ItemStack(im, 1, d2.length > 1 ? Integer.parseInt(d2[1]) : 0) : null;
                           String en = is != null ? is.func_77977_a() : "ERROR";
                           int cnt = 0;

                           for (EntityPlayer p1 : gp) {
                              for (int l = 0; l < p1.field_71071_by.field_70462_a.length; l++) {
                                 if (p1.field_71071_by.field_70462_a[l] != null && p1.field_71071_by.field_70462_a[l].func_77977_a().equalsIgnoreCase(en)) {
                                    cnt += p1.field_71071_by.field_70462_a[l].field_77994_a;
                                 }
                              }
                           }

                           JRMCoreM.prog(p, seriesID, msnToSendID, size, i, "" + cnt);
                           msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, i, "" + cnt);
                        }
                     }

                     if (JRMCoreM.getMda_ObjComp_all(o, JRMCoreM.getMda(msd, seriesID), g)) {
                        for (int i = 0; i < o.size(); i++) {
                           String os = o.get(i);
                           String t = JRMCoreM.getMCo_type(os);
                           String d1 = JRMCoreM.getMCo_data(os, "N");
                           if (t.equalsIgnoreCase("item")) {
                              int da = (int)(JRMCoreM.getMCo_dataI(os, "M") * JRMCoreM.gm(g));
                              String[] d2 = d1.split("::");
                              Item im = JRMCoreH.getItemByText(d2.length > 1 ? d2[0] : d1);
                              ItemStack is = im != null ? new ItemStack(im, 1, d2.length > 1 ? Integer.parseInt(d2[1]) : 0) : null;
                              String en = is != null ? is.func_77977_a() : "ERROR";
                              int cnt = da;

                              for (EntityPlayer p1 : gp) {
                                 for (int l = 0; l < p1.field_71071_by.field_70462_a.length; l++) {
                                    if (cnt > 0
                                       && p1.field_71071_by.field_70462_a[l] != null
                                       && p1.field_71071_by.field_70462_a[l].func_77977_a().equalsIgnoreCase(en)) {
                                       int ss = p1.field_71071_by.field_70462_a[l].field_77994_a;
                                       if (cnt < ss) {
                                          p1.field_71071_by.field_70462_a[l].field_77994_a = ss - cnt;
                                          cnt -= ss;
                                       } else if (cnt >= ss) {
                                          p1.field_71071_by.field_70462_a[l] = null;
                                          cnt -= ss;
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        ArrayList<String> r = msn.getRewards(pw, rc, cl);
                        int btnID = Integer.parseInt(d);
                        if (btnID >= 0) {
                           if (ps.equalsIgnoreCase("randrew")) {
                              btnID = new Random().nextInt(r.size());
                           }

                           String[] rw = r.get(btnID).split(";");
                           String[] rws = rw[0].split("\\|\\|");

                           for (EntityPlayer p1 : gp) {
                              for (int i = 0; i < rws.length; i++) {
                                 String[] srw = rws[i].split("!");
                                 if (srw[0].equalsIgnoreCase("tp")) {
                                    String tptype = srw[1];
                                    double tpamount = Double.parseDouble(srw[2]);
                                    if (tptype.equals("fix")) {
                                       JRMCoreH.tpalgn(p1, (int)tpamount, al);
                                    } else if (tptype.equalsIgnoreCase("lvl")) {
                                       int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p1);
                                       JRMCoreH.tpalgn(p1, (int)(tpamount * JRMCoreH.getPlayerLevel(PlyrAttrbts)), 0);
                                    } else if (tptype.equalsIgnoreCase("align")) {
                                       int at = JRMCoreH.Algnmnt(al);
                                       int ma = JRMCoreM.getMAlgnmnt_Num(msn.getAlign(pw, rc, cl));
                                       double tpres = ma == at ? tpamount : (ma != at + 1 && ma != at - 1 ? 0.0 : tpamount / 2.0);
                                       JRMCoreH.tpalgn(p1, (int)tpres, al);
                                    } else if (tptype.equalsIgnoreCase("lvlalign")) {
                                       int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p1);
                                       int lvl = JRMCoreH.getPlayerLevel(PlyrAttrbts);
                                       int at = JRMCoreH.Algnmnt(al);
                                       int ma = JRMCoreM.getMAlgnmnt_Num(msn.getAlign(pw, rc, cl));
                                       double tpres = ma == at ? tpamount : (ma != at + 1 && ma != at - 1 ? 0.0 : tpamount / 2.0);
                                       JRMCoreH.tpalgn(p1, (int)(tpres * lvl), al);
                                    }
                                 } else if (srw[0].equalsIgnoreCase("align")) {
                                    int alamount = Integer.parseInt(srw[1]);
                                    int b = al + alamount < 0 ? 0 : (al + alamount > 100 ? 100 : al + alamount);
                                    if (alamount == 0) {
                                       if (al >= 55) {
                                          b = al - 10 < 0 ? 0 : al - 10;
                                       } else if (al <= 45) {
                                          b = al + 10 > 100 ? 100 : al + 10;
                                       } else {
                                          b = 50;
                                       }
                                    }

                                    JRMCoreH.tpalgn(p1, 0, b);
                                 } else if (srw[0].equalsIgnoreCase("item")) {
                                    String nam = srw[1];
                                    String n1 = "";
                                    int n2 = 1;
                                    int n3 = 0;
                                    String[] s2 = nam.split(",");
                                    if (s2.length > 1) {
                                       n2 = Integer.parseInt(s2[1]);
                                    }

                                    if (s2[0].contains("::")) {
                                       String[] s3 = s2[0].split("::");
                                       n1 = s3[0];
                                       n3 = Integer.parseInt(s3[1]);
                                    } else {
                                       n1 = s2[0];
                                    }

                                    Item item = JRMCoreH.getItemByText(n1);
                                    if (item != null) {
                                       ItemStack itemstack = new ItemStack(item, n2, n3);
                                       EntityItem entityitem = p1.func_71019_a(itemstack, false);
                                       entityitem.field_145804_b = 0;
                                    }
                                 } else if (srw[0].equalsIgnoreCase("com")) {
                                    String com = srw[1];
                                    com = com.replace("@p", "%s");
                                    String s = String.format(com, p1.func_70005_c_());
                                    p1.field_70170_p.func_147449_b(0, 254, 0, Blocks.field_150483_bI);
                                    TileEntity tileentity = p1.field_70170_p.func_147438_o(0, 254, 0);
                                    CommandBlockLogic commandblocklogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
                                    commandblocklogic.func_145752_a(s);
                                    commandblocklogic.func_145755_a(p1.field_70170_p);
                                    p1.field_70170_p.func_147468_f(0, 254, 0);
                                    mod_JRMCore.logger
                                       .info("MSNlog: " + p1.func_70005_c_() + " successfully earned reward for quest " + seriesID + "/" + msn.getId() + "!");
                                 }
                              }
                           }

                           msd = JRMCoreM.setToNextMsn(msd, rw[2], msnl, seriesID, pw, rc, cl);
                           JRMCoreM.prog((EntityPlayerMP)p, M, msn, rw[2], msnl, seriesID, pw, rc, cl);
                        }
                     } else {
                        String ft = JRMCoreM.getMCo_type(o.get(0));
                        String fd1 = JRMCoreM.getMCo_data(o.get(0), "N");
                        boolean db = d.equals("-3");
                        if (size == 1) {
                           msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, "1");
                        }

                        if (!JRMCoreM.getMda(msd, seriesID)[2].equals("0")) {
                           if (JRMCoreM.getMda(msd, seriesID)[2].equals("1")) {
                              String os = o.get(0);
                              String t = JRMCoreM.getMCo_type(os);
                              if (t.equalsIgnoreCase("skip")) {
                                 ArrayList<String> r = msn.getRewards(pw, rc, cl);
                                 String[] rw = r.get(0).split(";");
                                 msd = JRMCoreM.setToNextMsn(msd, rw[2], msnl, seriesID, pw, rc, cl);
                                 JRMCoreM.prog((EntityPlayerMP)p, M, msn, rw[2], msnl, seriesID, pw, rc, cl);
                              } else if (t.equalsIgnoreCase("restart")) {
                                 rp = M.getSettings().getRepeat().contains("-") ? "-1" : "" + Integer.parseInt(M.getSettings().getRepeat()) * 12;
                                 String cc = "" + (Integer.parseInt(JRMCoreM.getSydaV(sydaV, 3)) + 1);
                                 String ccv = "" + (Integer.parseInt(JRMCoreM.getSydaV(sydaV, 4)) + 1);
                                 msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), rp, cc, ccv);
                                 JRMCoreM.prog(p, seriesID, M.getVersion(), rp, cc, ccv);
                                 ArrayList<String> r = msn.getRewards(pw, rc, cl);
                                 String[] rw = r.get(0).split(";");
                                 msd = JRMCoreM.setToNextMsn(msd, "0", msnl, seriesID, pw, rc, cl);
                                 JRMCoreM.prog((EntityPlayerMP)p, M, msn, "0", msnl, seriesID, pw, rc, cl);
                              }
                           }
                        } else {
                           for (int i = 0; i < o.size(); i++) {
                              String os = o.get(i);
                              String t = JRMCoreM.getMCo_type(os);
                              String d1 = JRMCoreM.getMCo_data(os, "N");
                              if (t.equalsIgnoreCase("kill")) {
                                 boolean spwn = JRMCoreM.getMCo_data(os, "P").equalsIgnoreCase("spwn");
                                 if (!spwn) {
                                    float angle = p.field_70177_z;
                                    double rtx = Math.sin(angle / (180.0F / (float)Math.PI));
                                    double rty = Math.cos(angle / (180.0F / (float)Math.PI));
                                    int Y = p.field_70170_p.func_72976_f((int)(p.field_70165_t - 3.0 * rtx), (int)(p.field_70161_v + 3.0 * rty));
                                    String dtr = JRMCoreM.getMCo_data(os, "T");
                                    this.spawcha(
                                       p,
                                       p.field_70170_p,
                                       JRMCoreM.getMCo_data(os, "N"),
                                       JRMCoreM.getMCo_data(os, "H"),
                                       JRMCoreM.getMCo_data(os, "A"),
                                       dtr,
                                       seriesID + ";" + msnToSendID + ";" + p.func_70005_c_(),
                                       p.field_70165_t - 3.0 * rtx,
                                       Y,
                                       p.field_70161_v + 3.0 * rty,
                                       g
                                    );
                                    JRMCoreM.prog(p, seriesID, msnToSendID, size, JRMCoreM.SYNC_COND_data_REV(2), "1");
                                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, JRMCoreM.SYNC_COND_data_REV(2), "1");
                                    String md = JRMCoreM.getMCo_data(os, "S");
                                    List pl = p.field_70170_p.func_72872_a(EntityPlayer.class, p.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
                                    if (pl.size() > 0 && md.length() > 1) {
                                       for (int v = 0; v < pl.size(); v++) {
                                          EntityPlayer va = (EntityPlayer)pl.get(v);
                                          ChatComponentTranslation chat = new ChatComponentTranslation(md, new Object[0]);
                                          if (chat.func_150260_c().length() > 0) {
                                             va.func_145747_a(chat.func_150255_a(JRMCoreH2.styl_wht));
                                          }

                                          String dsnS = JRMCoreM.getMCo_data(os, "O");
                                          if (dsnS.length() > 1) {
                                             if (dsnS.contains(",")) {
                                                String[] dsnSa = dsnS.split(",");
                                                va.field_70170_p.func_72956_a(va, dsnSa[0], Float.parseFloat(dsnSa[1]), 1.0F);
                                             } else {
                                                va.field_70170_p.func_72956_a(va, dsnS, 1.0F, 1.0F);
                                             }
                                          }
                                       }
                                    }
                                 }
                              } else if (!t.equalsIgnoreCase("killsame")) {
                                 if (t.equalsIgnoreCase("talk") && db) {
                                    JRMCoreM.prog(p, seriesID, msnToSendID, size, i, "1");
                                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, i, "1");
                                 } else if (t.equalsIgnoreCase("skip")) {
                                    ArrayList<String> r = msn.getRewards(pw, rc, cl);
                                    String[] rw = r.get(0).split(";");
                                    msd = JRMCoreM.setToNextMsn(msd, rw[2], msnl, seriesID, pw, rc, cl);
                                    JRMCoreM.prog((EntityPlayerMP)p, M, msn, rw[2], msnl, seriesID, pw, rc, cl);
                                 } else if (t.equalsIgnoreCase("restart")) {
                                    rp = M.getSettings().getRepeat().contains("-") ? "-1" : "" + Integer.parseInt(M.getSettings().getRepeat()) * 12;
                                    String cc = "" + (Integer.parseInt(JRMCoreM.getSydaV(sydaV, 3)) + 1);
                                    String ccv = "" + (Integer.parseInt(JRMCoreM.getSydaV(sydaV, 4)) + 1);
                                    msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), rp, cc, ccv);
                                    JRMCoreM.prog(p, seriesID, M.getVersion(), rp, cc, ccv);
                                    ArrayList<String> r = msn.getRewards(pw, rc, cl);
                                    String[] rw = r.get(0).split(";");
                                    msd = JRMCoreM.setToNextMsn(msd, "0", msnl, seriesID, pw, rc, cl);
                                    JRMCoreM.prog((EntityPlayerMP)p, M, msn, "0", msnl, seriesID, pw, rc, cl);
                                 }
                              } else {
                                 boolean spwn = JRMCoreM.getMCo_data(os, "P").equalsIgnoreCase("spwn");
                                 if (!spwn) {
                                    String dtr = JRMCoreM.getMCo_data(os, "T");

                                    for (int i1 = 0; i1 < (int)(JRMCoreM.getMCo_dataI(os, "M") * JRMCoreM.gm(g)); i1++) {
                                       float angle = p.field_70177_z;
                                       double rtx = Math.sin(angle / (180.0F / (float)Math.PI));
                                       double rty = Math.cos(angle / (180.0F / (float)Math.PI));
                                       int Y = p.field_70170_p.func_72976_f((int)(p.field_70165_t - 3.0 * rtx), (int)(p.field_70161_v + 3.0 * rty));
                                       this.spawcha(
                                          p,
                                          p.field_70170_p,
                                          JRMCoreM.getMCo_data(os, "N"),
                                          JRMCoreM.getMCo_data(os, "H"),
                                          JRMCoreM.getMCo_data(os, "A"),
                                          dtr,
                                          seriesID + ";" + msnToSendID + ";" + p.func_70005_c_(),
                                          p.field_70165_t - 3.0 * rtx,
                                          Y,
                                          p.field_70161_v + 3.0 * rty,
                                          1
                                       );
                                    }

                                    JRMCoreM.prog(p, seriesID, msnToSendID, size, JRMCoreM.SYNC_COND_data_REV(2), "1");
                                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, JRMCoreM.SYNC_COND_data_REV(2), "1");
                                    String md = JRMCoreM.getMCo_data(os, "S");
                                    List pl = p.field_70170_p.func_72872_a(EntityPlayer.class, p.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
                                    if (pl.size() > 0 && md.length() > 1) {
                                       for (int v = 0; v < pl.size(); v++) {
                                          EntityPlayer va = (EntityPlayer)pl.get(v);
                                          ChatComponentTranslation chat = new ChatComponentTranslation(md, new Object[0]);
                                          if (chat.func_150254_d().length() > 0) {
                                             va.func_145747_a(chat.func_150255_a(JRMCoreH2.styl_wht));
                                          }

                                          String dsnS = JRMCoreM.getMCo_data(os, "O");
                                          if (dsnS.length() > 1) {
                                             if (dsnS.contains(",")) {
                                                String[] dsnSa = dsnS.split(",");
                                                va.field_70170_p.func_72956_a(va, dsnSa[0], Float.parseFloat(dsnSa[1]), 1.0F);
                                             } else {
                                                va.field_70170_p.func_72956_a(va, dsnS, 1.0F, 1.0F);
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }

                     nbt.func_74778_a("JRMCmissionSync", msd);
                     if (!msdV.equalsIgnoreCase(msdVO)) {
                        nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                     }

                     PD.sendTo(
                        new JRMCorePData2(
                           seriesID
                              + ";"
                              + M.getName()
                              + ";"
                              + M.getDesc()
                              + ";"
                              + M.getAuthor()
                              + ";"
                              + M.getVersion()
                              + ";"
                              + M.getMods()
                              + ";"
                              + M.settings.repeat
                              + ";"
                              + M.settings.unlock,
                           new Gson().toJson(msn, JRMCoreM.JSN_TYPE_MSN)
                        ),
                        (EntityPlayerMP)p
                     );
                     break;
                  }
               }
            }
         }
      }
   }

   public static String getUrlContents(String theUrl) {
      StringBuilder content = new StringBuilder();

      try {
         URL url = new URL(theUrl);
         URLConnection urlConnection = url.openConnection();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

         String line;
         while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
         }

         bufferedReader.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return content.toString();
   }

   public static boolean respc(String theUrl) {
      long ct = System.currentTimeMillis() / 1000L;
      if (ckr.get("guc") == null || ct > ckr.get("guc")) {
         try {
            URL url = new URL(theUrl);
            HttpURLConnection connect = (HttpURLConnection)url.openConnection();
            connect.setConnectTimeout(10000);
            connect.setReadTimeout(10000);
            connect.setDoOutput(true);
            connect.setInstanceFollowRedirects(false);
            connect.setRequestMethod("HEAD");
            connect.setUseCaches(false);

            try {
               int responseCode = connect.getResponseCode();
               if (responseCode == 200) {
                  ckr.remove("guc");
                  return true;
               }
            } catch (SocketTimeoutException var6) {
            }

            connect.disconnect();
         } catch (IOException var7) {
         }

         ckr.put("guc", ct + 10L);
      }

      return false;
   }

   public static String update() {
      String s = "nothing";

      try {
         String[] ssurl = JRMCoreConfig.ssurl.split(",");
         URL url = new URL(ssurl[0]);
         HttpURLConnection connect = (HttpURLConnection)url.openConnection();
         connect.setConnectTimeout(10000);
         connect.setReadTimeout(10000);
         connect.setDoOutput(true);
         connect.setInstanceFollowRedirects(false);
         connect.setRequestMethod("HEAD");
         connect.setUseCaches(false);

         try {
            int responseCode = connect.getResponseCode();
            if (responseCode == 200) {
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
               String var6 = bufferedReader.readLine();
            }
         } catch (SocketTimeoutException var7) {
         }
      } catch (IOException var8) {
      }

      return s;
   }

   public void handleQuadI(byte b1, int b2, int b3, int b4) {
   }

   public void handleQuad(int b1, int b2, int b3, int b4, EntityPlayer player) {
      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
      if (b1 == 1) {
         player.field_70170_p.func_72956_a(player, "jinryuudragonbc:" + JRMCoreH.techSnds(b2, b3, b4), 1.0F, 1.0F);
      }

      if (b1 == 2 && JRMCoreConfig.DebugInfo) {
         String info = "JRMC has found Potential hacking at ID:01 by player: %s";
         mod_JRMCore.logger.info(String.format(info, player.func_70005_c_()));
         CommandBase.func_152373_a(player, null, info, new Object[]{player.func_70005_c_()});
      }

      if (b1 == 3) {
         if (b2 == 1) {
            Random ran = new Random();
            int r = ran.nextInt(1000000);
            JRMCoreH.setInt(r, player, "JRMCGID");
            JRMCoreH.setString(player.func_70005_c_(), player, "JRMCGLIDs");
         } else if (b2 == 2) {
            String l = JRMCoreH.getString(player, "JRMCGIDis");
            EntityPlayer le = JRMCoreH.getPlayerForUsername(server, l);
            if (le != null) {
               int gid = JRMCoreH.getInt(le, "JRMCGID");
               String lsl = JRMCoreH.getString(le, "JRMCGLIDs");
               int gmn = 0;
               if (server.func_71213_z() != null && server.func_71213_z().length > 0) {
                  for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                     EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                     int egid = JRMCoreH.getInt(e, "JRMCGID");
                     if (egid == gid) {
                        gmn++;
                     }
                  }
               }

               if (lsl.equalsIgnoreCase(l) && gmn < 10) {
                  JRMCoreH.setString(l, player, "JRMCGLIDs");
                  JRMCoreH.setInt(gid, player, "JRMCGID");
                  JRMCoreH.setString(" ", player, "JRMCGIDis");
               } else {
                  JRMCoreH.setString(" ", player, "JRMCGLIDs");
                  JRMCoreH.setInt(0, player, "JRMCGID");
                  JRMCoreH.setString(" ", player, "JRMCGIDis");
               }
            } else {
               JRMCoreH.setString(" ", player, "JRMCGIDis");
            }
         } else if (b2 == 3) {
            JRMCoreH.setString(" ", player, "JRMCGIDis");
         } else if (b2 == 4) {
            int gid = JRMCoreH.getInt(player, "JRMCGID");
            String lid = JRMCoreH.getString(player, "JRMCGLIDs");
            if (player.func_70005_c_().equalsIgnoreCase(lid) && server.func_71213_z() != null && server.func_71213_z().length > 0) {
               for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                  EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                  int egid = JRMCoreH.getInt(e, "JRMCGID");
                  if (egid == gid) {
                     JRMCoreH.setString(" ", e, "JRMCGLIDs");
                     JRMCoreH.setInt(0, e, "JRMCGID");
                     JRMCoreH.setString(" ", e, "JRMCGIDis");
                  }
               }
            }
         } else if (b2 == 5) {
            int gid = JRMCoreH.getInt(player, "JRMCGID");
            String lid = JRMCoreH.getString(player, "JRMCGLIDs");
            if (player.func_70005_c_().equalsIgnoreCase(lid)) {
               String i = " ";
               if (server.func_71213_z() != null && server.func_71213_z().length > 0) {
                  for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                     EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                     int egid = JRMCoreH.getInt(e, "JRMCGID");
                     if (gid == egid && player.func_145782_y() != e.func_145782_y()) {
                        i = e.func_70005_c_();
                        break;
                     }
                  }

                  for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                     EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                     int egid = JRMCoreH.getInt(e, "JRMCGID");
                     if (egid == gid && i.length() > 2) {
                        JRMCoreH.setString(i, e, "JRMCGLIDs");
                     }
                  }
               }
            }

            JRMCoreH.setString(" ", player, "JRMCGLIDs");
            JRMCoreH.setInt(0, player, "JRMCGID");
            JRMCoreH.setString(" ", player, "JRMCGIDis");
         } else if (b2 == 6) {
            EntityPlayerMP inv = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[b3 * 100 + b4]);
            if (inv != null && JRMCoreH.getInt(inv, "JRMCGID") == 0) {
               JRMCoreH.setString(player.func_70005_c_(), inv, "JRMCGIDis");
            }
         } else if (b2 == 7) {
            int gid = JRMCoreH.getInt(player, "JRMCGID");
            if (server.func_71213_z() != null && server.func_71213_z().length > 0) {
               int i = 0;
               Entity nl = null;

               for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                  EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                  int egid = JRMCoreH.getInt(e, "JRMCGID");
                  if (egid == gid) {
                     if (i == b3) {
                        nl = e;
                     }

                     i++;
                  }
               }

               for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                  EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                  int egid = JRMCoreH.getInt(e, "JRMCGID");
                  if (egid == gid) {
                     JRMCoreH.setString(nl.func_70005_c_(), e, "JRMCGLIDs");
                  }
               }
            }
         } else if (b2 == 8) {
            int gid = JRMCoreH.getInt(player, "JRMCGID");
            int i = 0;
            if (server.func_71213_z() != null && server.func_71213_z().length > 0) {
               for (int pl = 0; pl < server.func_71213_z().length; pl++) {
                  EntityPlayerMP e = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                  int egid = JRMCoreH.getInt(e, "JRMCGID");
                  if (egid == gid) {
                     if (i == b3 && !e.func_70005_c_().equalsIgnoreCase(player.func_70005_c_())) {
                        JRMCoreH.setString(" ", e, "JRMCGLIDs");
                        JRMCoreH.setInt(0, e, "JRMCGID");
                     }

                     i++;
                  }
               }
            }
         }
      } else if (b1 == 10) {
         NBTTagCompound nbt = this.nbt(player, "pres");
         int tp = nbt.func_74762_e("jrmcTpint");
         if (b2 == 0) {
            if (!JRMCoreH.isFusionSpectator(player)) {
               if (tp > 0) {
                  nbt.func_74768_a("jrmcTpint", tp - 1);
                  byte acc = nbt.func_74771_c(Acc);
                  byte pwrtyp = nbt.func_74771_c(P);
                  byte rce = nbt.func_74771_c(R);
                  byte cls = nbt.func_74771_c(Cl);
                  byte st = nbt.func_74771_c(St);
                  int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
                  int melee = JRMCoreH.stat(player, 0, pwrtyp, 0, PlyrAttrbts[0], rce, cls, 0.0F);
                  int maxBody = JRMCoreH.stat(player, 2, pwrtyp, 2, PlyrAttrbts[2], rce, cls, 0.0F);
                  EntityNPCshadow var8 = new EntityNPCshadow(player.field_70170_p, player, maxBody, melee, player);
                  var8.func_70012_b(
                     player.field_70165_t - 0.0, player.field_70163_u + 1.5, player.field_70161_v - 0.0, player.field_70177_z, player.field_70125_A
                  );
                  player.field_70170_p.func_72838_d(var8);
               } else {
                  player.func_145747_a(new ChatComponentText(JRMCoreH.cly + "You need more TP to Start training"));
               }
            }
         } else if (b2 == 1) {
            if (JGConfigMiniGameConcentration.TPGainOn) {
               if (b3 == -1) {
                  PD.sendTo(new JRMCorePQuad(b1, b2, 0, nbt.func_74762_e("jrmcTPlimit")), (EntityPlayerMP)player);
               } else if (b3 == 0) {
                  nbt.func_74768_a("jrmcTpint", tp - 1);
               } else if (b3 == 1) {
                  int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
                  int possibleReward = (int)(b4 * JGConfigMiniGameConcentration.TPMultiplier);
                  int dailyLimit = (int)(
                     JGConfigMiniGameConcentration.TPDailyLimit
                        * (
                           JGConfigMiniGameConcentration.TPlimitIncreasesWithPlayerLevel > 0.0F
                              ? JRMCoreH.getPlayerLevel(PlyrAttrbts) * JGConfigMiniGameConcentration.TPlimitIncreasesWithPlayerLevel
                              : 1.0F
                        )
                  );
                  int limitAlreadyReached = nbt.func_74762_e("jrmcTPlimit");
                  int actualReward = limitAlreadyReached + possibleReward;
                  int reward = possibleReward;
                  if (dailyLimit < actualReward) {
                     reward = possibleReward - (actualReward - dailyLimit);
                     actualReward = dailyLimit;
                  }

                  if (reward > 0) {
                     int result = tp + reward;
                     if (result > JRMCoreH.getMaxTP()) {
                        result = JRMCoreH.getMaxTP();
                     }

                     nbt.func_74768_a("jrmcTpint", result);
                     nbt.func_74768_a("jrmcTPlimit", actualReward);
                     player.func_145747_a(new ChatComponentText(JRMCoreH.cly + "Reward from Minigame " + reward + " TP"));
                     String info = "JRMC given reward of %sTP from minigame at ID:02 by player: %s";
                     if (JRMCoreConfig.DebugInfo) {
                        mod_JRMCore.logger.info(String.format(info, reward, player.func_70005_c_()));
                     }
                  }

                  PD.sendTo(new JRMCorePQuad(b1, b2, b3, nbt.func_74762_e("jrmcTPlimit")), (EntityPlayerMP)player);
               }
            }
         } else if (b2 == 2 && JGConfigMiniGameAirBoxing.TPGainOn) {
            if (b3 == -1) {
               PD.sendTo(new JRMCorePQuad(b1, b2, 1, nbt.func_74762_e("jrmcTPlimit2")), (EntityPlayerMP)player);
            } else if (b3 == 0) {
               nbt.func_74768_a("jrmcTpint", tp - 1);
            } else if (b3 == 1) {
               int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
               int possibleReward = (int)(b4 * JGConfigMiniGameAirBoxing.TPMultiplier);
               int dailyLimit = (int)(
                  JGConfigMiniGameAirBoxing.TPDailyLimit
                     * (
                        JGConfigMiniGameAirBoxing.TPlimitIncreasesWithPlayerLevel > 0.0F
                           ? JRMCoreH.getPlayerLevel(PlyrAttrbts) * JGConfigMiniGameAirBoxing.TPlimitIncreasesWithPlayerLevel
                           : 1.0F
                     )
               );
               int limitAlreadyReached = nbt.func_74762_e("jrmcTPlimit2");
               int actualReward = limitAlreadyReached + possibleReward;
               int reward = possibleReward;
               if (dailyLimit < actualReward) {
                  reward = possibleReward - (actualReward - dailyLimit);
                  actualReward = dailyLimit;
               }

               if (reward > 0) {
                  int result = tp + reward;
                  if (result > JRMCoreH.getMaxTP()) {
                     result = JRMCoreH.getMaxTP();
                  }

                  nbt.func_74768_a("jrmcTpint", result);
                  nbt.func_74768_a("jrmcTPlimit2", actualReward);
                  player.func_145747_a(new ChatComponentText(JRMCoreH.cly + "Reward from Minigame " + reward + " TP"));
                  String info = "JRMC given reward of %sTP from minigame at ID:02 by player: %s";
                  if (JRMCoreConfig.DebugInfo) {
                     mod_JRMCore.logger.info(String.format(info, reward, player.func_70005_c_()));
                  }
               }

               PD.sendTo(new JRMCorePQuad(b1, b2, b3, nbt.func_74762_e("jrmcTPlimit2")), (EntityPlayerMP)player);
            }
         }
      }
   }

   public void handleFall(byte b, EntityPlayer p) {
      if (b == 1 || b == 2) {
         JRMCoreH.setByte(b == 1 ? 1 : 0, p, "jrmcStnd");
      }
   }

   public void handleTech(byte b, String s, EntityPlayer p) {
      if (JRMCoreConfig.osbic > 0 && JRMCoreH.osbic.get(p.func_70005_c_()) < JRMCoreConfig.osbic * 20) {
         p.func_145747_a(
            new ChatComponentTranslation(
                  "Offline Protection: " + ((int)((JRMCoreConfig.osbic * 20 - JRMCoreH.osbic.get(p.func_70005_c_())) * 0.05F) + 1) + "s left", new Object[0]
               )
               .func_150255_a(chatStyle)
         );
      } else {
         int pwrtyp = JRMCoreH.getByte(p, P);
         String te = "te;;;";
         String ug = "ug;;;";
         String up = "up;;;";
         String dn = "dn;;;";
         if (!s.contains(dn) && !s.contains(up)) {
            if (s.contains(ug)) {
               if (b >= 0 && b <= 3) {
                  int id = Integer.parseInt(s.replaceAll(ug, ""));
                  String s1 = JRMCoreH.getString(p, JRMCoreH.techNbt[b]);
                  JRMCoreH.setString(JRMCoreH.tech_upgrd(s1, id), p, JRMCoreH.techNbt[b]);
               }
            } else if (s.contains(te)) {
               if (b >= 0 && b <= 3) {
                  EntityPlayer va = JRMCoreH.getClosestPlayerToEntity(p, 8.0);
                  if (va != null) {
                     String toteach = JRMCoreH.tech_teach(JRMCoreH.getString(p, JRMCoreH.techNbt[b]));
                     String[] tn = JRMCoreH.tech_conv(toteach.split(";"));
                     int costTp = JRMCoreH.techDBCtpc(tn, true) * 2;
                     va.func_145747_a(
                        new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "teachingOffer"), new Object[]{p.func_70005_c_(), tn[0], costTp})
                           .func_150255_a(JRMCoreH2.styl_ylw)
                     );
                     JRMCoreH.setString(toteach + ";:;" + p.func_70005_c_(), va, "jrmcTechLearn");
                  } else {
                     p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "noOneToTeach"), new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
                  }
               }
            } else if (b >= 0 && b <= 7) {
               if (!s.contains(";") && s.length() > 0) {
                  if (s.equals(" ")) {
                     String s1 = JRMCoreH.getString(p, JRMCoreH.techNbt[b]);
                     if (!s1.equals(" ") && !s1.equals("") && pwrtyp == 1) {
                        if (b >= 4) {
                           int s2 = Integer.parseInt(s1);
                           String[][] PMA = JRMCoreH.pmdbc;
                           int costTp = (int)(JRMCoreH.techDBCtpc(PMA[s2], false) * 0.9F) / 2;
                           int tp = JRMCoreH.getInt(p, "jrmcTpint");
                           JRMCoreH.setInt(tp + costTp, p, "jrmcTpint");
                        } else if (s1.contains(";")) {
                           String[] s2 = s1.toString().split(";");
                           int costTp = JRMCoreH.techDBCtpc(s2, false) / 2;
                           int tp = JRMCoreH.getInt(p, "jrmcTpint");
                           JRMCoreH.setInt(tp + costTp, p, "jrmcTpint");
                        }
                     }

                     JRMCoreH.setString(" ", p, JRMCoreH.techNbt[b]);
                  } else {
                     int s2 = Integer.parseInt(s);
                     String[][] PMA = (String[][])null;
                     if (pwrtyp == 2) {
                        PMA = JRMCoreH.pmj;
                        int costP = JRMCoreH.techNCCostP[Integer.parseInt(PMA[s2][7])];
                        float cost1000 = costP * 0.01F * 1000.0F;
                        int sped = Integer.parseInt(PMA[s2][4]);
                        int type = Integer.parseInt(PMA[s2][3]);
                        int dmg1000 = (int)(cost1000 - cost1000 * 0.25F * sped + (type == 0 ? cost1000 * 0.2F : 0.0F));
                        int costTp = (int)(20.0F + cost1000 / 2.0F);
                        int cost = (int)(costTp * 1.5F);
                        int tp = JRMCoreH.getInt(p, "jrmcTpint");
                        if (tp - cost >= 0) {
                           JRMCoreH.setInt(tp - cost, p, "jrmcTpint");
                           JRMCoreH.setString("" + s2, p, JRMCoreH.techNbt[b]);
                           p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("nc", "LearnedJutsu"), new Object[0]).func_150255_a(chatStyle));
                        }
                     } else {
                        boolean may = false;
                        if (b == 4) {
                           for (int i = 0; i < 4; i++) {
                              String s1 = JRMCoreH.getString(p, JRMCoreH.techNbt[4 + i]);
                              if (!NumberUtils.isNumber(s1) && !s1.contains(",")) {
                                 b = (byte)(4 + i);
                                 may = true;
                                 break;
                              }

                              may = false;
                           }
                        }

                        PMA = JRMCoreH.pmdbc;
                        int costTp = (int)(JRMCoreH.techDBCtpc(PMA[s2], false) * 0.9F);
                        int cost = costTp;
                        int tp = JRMCoreH.getInt(p, "jrmcTpint");
                        if (tp - cost >= 0 && may) {
                           JRMCoreH.setInt(tp - cost, p, "jrmcTpint");
                           JRMCoreH.setString("" + s2, p, JRMCoreH.techNbt[b]);
                        } else {
                           p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "noslotleft"), new Object[0]).func_150255_a(chatStyle));
                        }
                     }
                  }
               } else {
                  String[] s2 = s.contains(";") ? s.toString().split(";") : null;
                  if (s2 == null) {
                     JRMCoreH.setString(" ", p, JRMCoreH.techNbt[b]);
                  } else {
                     int costTp = JRMCoreH.techDBCtpc(s2, false);
                     int tp = JRMCoreH.getInt(p, "jrmcTpint");
                     if (tp - costTp >= 0) {
                        JRMCoreH.setInt(tp - costTp, p, "jrmcTpint");
                        JRMCoreH.setString(s, p, JRMCoreH.techNbt[b]);
                        JRMCoreH.Tech(p, b, s);
                     }
                  }
               }
            }
         } else {
            int b2 = s.contains(up) ? (b - 1 < (b > 3 ? 4 : 0) ? (b > 3 ? 7 : 3) : b - 1) : (b + 1 > (b > 3 ? 7 : 3) ? (b > 3 ? 4 : 0) : b + 1);
            String s1 = JRMCoreH.getString(p, JRMCoreH.techNbt[b]);
            String s2 = JRMCoreH.getString(p, JRMCoreH.techNbt[b2]);
            JRMCoreH.setString(s1, p, JRMCoreH.techNbt[b2]);
            JRMCoreH.setString(s2, p, JRMCoreH.techNbt[b]);
         }
      }
   }

   public void handleAttck(byte b, EntityPlayer p) {
   }

   public void handleCost(short s, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
      byte pwr = nbt.func_74771_c(P);
      byte rce = nbt.func_74771_c(R);
      byte cls = nbt.func_74771_c(Cl);
      int maxBody = JRMCoreH.stat(p, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0F);
      int curBody = JRMCoreH.getInt(p, "jrmcBdy");
      int maxEnergy = JRMCoreH.stat(p, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(p, pwr));
      int curEnergy = JRMCoreH.getInt(p, "jrmcEnrgy");
      int fc = curEnergy - s;
      if (fc > 0 && !JRMCoreH.isInCreativeMode(p)) {
         JRMCoreH.setInt(fc, p, "jrmcEnrgy");
      }

      if (fc <= 0) {
         JRMCoreH.setInt(0, p, "jrmcEnrgy");
         JRMCoreH.setByte(0, p, "jrmcRelease");
         JRMCoreH.setInt(curBody + fc, p, "jrmcBdy");
      }

      curBody = JRMCoreH.getInt(p, "jrmcBdy");
      if (curBody < 0) {
         JRMCoreH.setInt(0, p, "jrmcBdy");
      }

      JRMCoreH.Stats(
         p,
         nbt.func_74762_e("jrmcBdy"),
         nbt.func_74762_e("jrmcEnrgy"),
         nbt.func_74762_e("jrmcStamina"),
         nbt.func_74771_c("jrmcRelease"),
         nbt.func_74771_c("jrmcSaiRg")
      );
   }

   public void handleRls(byte b, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
      String[] PlyrSkills = nbt.func_74779_i("jrmcSSlts").split(",");
      byte pwr = nbt.func_74771_c(P);
      byte rce = nbt.func_74771_c(R);
      byte cls = nbt.func_74771_c(Cl);
      byte rls = JRMCoreH.getByte(p, "jrmcRelease");
      byte st2 = nbt.func_74771_c("jrmcState2");
      if (!JRMCoreConfig.release) {
         JRMCoreH.setByte(50, p, "jrmcRelease");
      } else {
         int mr = JRMCoreH.SklLvl(pwr == 1 ? 5 : (pwr == 2 ? 10 : 0), pwr, PlyrSkills) * 5;
         if (b == 0 && st2 <= 0) {
            JRMCoreH.setByte(0, p, "jrmcRelease");
         }

         if (b == 1 && rls < 100) {
            int relmax = 50 + mr;
            byte var13 = (byte)(rls + 5 > 100 ? 100 : rls + 5);
            rls = (byte)(var13 > relmax ? relmax : var13);
            JRMCoreH.setByte(rls, p, "jrmcRelease");
         }

         if (b == 2 && rls > 0 && st2 <= 0) {
            JRMCoreH.setByte(rls - 5 < 0 ? 0 : rls - 5, p, "jrmcRelease");
         }
      }
   }

   public void handleTri(byte b, byte b2, byte b3, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      if (b == 1) {
         if (b2 == RAGE_GAIN_MINUS) {
            int rg = nbt.func_74771_c("jrmcSaiRg") - b3;
            nbt.func_74774_a("jrmcSaiRg", rg < 0 ? 0 : (byte)rg);
         }

         if (b2 == RAGE_GAIN_PLUS) {
            int rg = nbt.func_74771_c("jrmcSaiRg") + b3;
            nbt.func_74774_a("jrmcSaiRg", rg > 100 ? 100 : (byte)rg);
         }
      }

      if (b == 2) {
         ExtendedPlayer props = ExtendedPlayer.get(p);
         props.setBlocking(b2);
      }

      if (b == 3) {
         ExtendedPlayer props = ExtendedPlayer.get(p);
         props.setAnimKiShoot(b2);
         props.setAnimKiShootOn(1);
         int color = 0;
         int align = 0;
         String StE = nbt.func_74779_i("jrmcStatusEff");
         boolean setGoDOn = JRMCoreH.StusEfcts(20, StE);
         float density = 1.0F;
         float dam = 1.0F;
         int part = 0;
         String data = "";
         byte[] sts = new byte[0];
         byte perc = 100;
         int dam1 = 1;
         boolean isCustomAttack = false;

         try {
            String s1 = JRMCoreH.tech_conv(nbt.func_74779_i(JRMCoreH.techNbt[b3]));
            if (s1.split(";").length > 1) {
               isCustomAttack = true;
               color = Integer.parseInt(s1.split(";")[10]);
               density = Float.parseFloat(s1.split(";")[11]);
               dam = Float.parseFloat(s1.split(";")[5]);
               data = s1.split(";")[0];
               sts = new byte[0];
               sts = JRMCoreH.tech_statmods(s1.split(";")[19]);
               perc = 100;
            } else if (!s1.equals(" ") && s1 != null) {
               color = Integer.parseInt(JRMCoreH.pmdbc[Integer.parseInt(s1)][10]);
               density = Float.parseFloat(JRMCoreH.pmdbc[Integer.parseInt(s1)][11]);
               dam = Float.parseFloat(JRMCoreH.pmdbc[Integer.parseInt(s1)][5]);
               data = JRMCoreH.pmdbc[Integer.parseInt(s1)][0];
            }

            dam1 = JRMCoreH.getEnegyDamage(p, s1.split(";"), sts);
            align = JRMCoreH.getByte(p, "jrmcAlign");
         } catch (Exception var22) {
         }

         if (color == 0) {
            if (align > 66) {
               color = 2;
            }

            if (align <= 66 && align >= 33) {
               color = 3;
            }

            if (align < 33) {
               color = 4;
            }
         }

         props.setGoDOn(setGoDOn && isCustomAttack && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENABLED ? 1 : 0);
         props.setKiShotCol(color);
         float size = JRMCoreH.calculateEnergyScale(dam1, JRMCoreH.getMaxEnergyDamage(), perc, sts, (byte)density, 0.01F, 0.1F);
         props.setKiShotSiz(size);
         if (data.toLowerCase().contains("spiritbomb") || data.toLowerCase().contains("spirit bomb")) {
            part = 1;
         } else if (data.toLowerCase().contains("kahame") || data.toLowerCase().contains("kamehame") || data.toLowerCase().contains("kame hame")) {
            part = 2;
         } else if (data.toLowerCase().contains("galic")) {
            part = 4;
         } else {
            try {
               String s1 = JRMCoreH.tech_conv(nbt.func_74779_i(JRMCoreH.techNbt[b3]));
               if (s1.split(";").length > 1) {
                  if (s1 != null && Integer.parseInt(s1.split(";")[3]) == 8 && Integer.parseInt(s1.split(";")[6]) == 1) {
                     part = 3;
                  }
               } else if (!s1.equals(" ")
                  && JRMCoreH.pmdbc[Integer.parseInt(s1)] != null
                  && Integer.parseInt(JRMCoreH.pmdbc[Integer.parseInt(s1)][3]) == 8
                  && Integer.parseInt(JRMCoreH.pmdbc[Integer.parseInt(s1)][6]) == 1) {
                  part = 3;
               }
            } catch (Exception var21) {
            }
         }

         props.setKiShotPart(part);
      }

      if (b == 4) {
         int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
         byte pwr = JRMCoreH.getByte(p, "jrmcPwrtyp");
         byte rce = JRMCoreH.getByte(p, "jrmcRace");
         byte cls = JRMCoreH.getByte(p, "jrmcClass");
         int maxEnergy = JRMCoreH.stat(p, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(p, pwr));
         int ce = JRMCoreH.getInt(p, "jrmcEnrgy");
         int maxStam = JRMCoreH.stat(p, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0F);
         int cs = JRMCoreH.getInt(p, "jrmcStamina");
         int n = b3 >= 4 ? JRMCoreH.SklLvl(3, p) : JRMCoreH.SklLvl(2, p);
         int cst = (int)(maxStam * (b3 == 5 ? 0.1F - 0.005F * n : 0.2F - (b == 4 ? 0.005F : 0.01F) * n) * DBCConfig.cnfFlncst);
         if (cs > cst) {
            if (!JRMCoreH.isInCreativeMode(p)) {
               JRMCoreH.setInt(cs - cst, p, "jrmcStamina");
            }

            if (b3 != 4) {
               p.field_70170_p
                  .func_72956_a(
                     p, b3 == 5 ? "jinryuudragonbc:DBC2.swoop" : "jinryuudragonbc:DBC2.tp", 0.25F, p.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F
                  );
            }
         }

         if (b3 >= 4) {
            cst = (int)((12 - n) * DBCConfig.cnfFlnck);
            if (ce > cst && !JRMCoreH.isInCreativeMode(p)) {
               JRMCoreH.setInt(ce - cst, p, "jrmcEnrgy");
            }
         }
      }

      if (b == 5 && b2 == 0 && JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
         byte st2 = nbt.func_74771_c("jrmcState2");
         if (JGConfigUltraInstinct.CONFIG_UI_HEAT_DURATION[st2 > 0 ? st2 - 1 : 0] > 0) {
            int heat = nbt.func_74762_e("jrmcEf8slc");
            double heatD = nbt.func_74769_h("jrmcEf8slcD");
            int heatGain = 1;
            if (JGConfigDBCFormMastery.FM_Enabled) {
               JGPlayerMP jgPlayer = new JGPlayerMP(p);
               jgPlayer.setNBT(nbt);
               int race = jgPlayer.getRace();
               int formID = JRMCoreH.getFormID("UltraInstict", race);
               double masteryLevel = JRMCoreH.getFormMasteryValue(p, formID);
               float costMulti = (float)JGConfigDBCFormMastery.getCostMulti(masteryLevel, race, formID, JGConfigDBCFormMastery.DATA_ID_UI_HEAT_MULTI);
               heatGain = (int)(heatGain * costMulti);
            }

            heatD += heatGain;
            heat += (int)heatD;
            heatD -= (int)heatD;
            nbt.func_74780_a("jrmcEf8slcD", heatD);
            int heat_max = JGConfigUltraInstinct.CONFIG_UI_HEAT_DURATION[st2 > 0 ? st2 - 1 : 0];
            nbt.func_74768_a("jrmcEf8slc", heat > heat_max ? heat_max : heat);
         }
      }
   }

   public void handleStats3(byte b, byte b2, byte b3, EntityPlayer p) {
      if (JRMCoreConfig.osbic > 0 && JRMCoreH.osbic.get(p.func_70005_c_()) < JRMCoreConfig.osbic * 20) {
         p.func_145747_a(
            new ChatComponentTranslation(
                  "Offline Protection: " + ((int)((JRMCoreConfig.osbic * 20 - JRMCoreH.osbic.get(p.func_70005_c_())) * 0.05F) + 1) + "s left", new Object[0]
               )
               .func_150255_a(chatStyle)
         );
      } else {
         NBTTagCompound nbt = this.nbt(p, "pres");
         JGPlayerMP jgPlayer = new JGPlayerMP(p);
         jgPlayer.setNBT(nbt);
         byte powerType = jgPlayer.getPowerType();
         if (JRMCoreH.isPowerTypeKi(powerType) && (b == 1 || b == 2 || b == 3 || b == 4) && JRMCoreH.isFused(p)) {
            p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc", "cantupgradef"), new Object[0]).func_150255_a(chatStyle));
         } else {
            int[] playerAttributes = jgPlayer.getAttributes();
            String[] playerSkills = jgPlayer.getSkills();
            byte race = jgPlayer.getRace();
            int playerSkillsCount = playerSkills.length;
            String PlyrSkillX = jgPlayer.getSkillsX();
            String PlyrSkillY = jgPlayer.getSkillsY();
            int[][] rSklsMR = (int[][])null;
            int[][] cSklsMR = (int[][])null;
            int[][] rSklsLvl;
            String[] cSkls;
            int[][] cSklsLvl;
            String[] skls;
            int[] sklsUps;
            int[][] sklsLvl;
            int[][] sklsMR;
            if (JRMCoreH.isPowerTypeChakra(powerType)) {
               String[] rSkls = JRMCoreH.ncRSkls;
               rSklsLvl = JRMCoreH.ncRSklsLvl;
               String[] rSklsNms = JRMCoreH.ncRSklsNms;
               cSkls = JRMCoreH.ncCSkls;
               cSklsLvl = JRMCoreH.NCRacialSkillTPCost;
               String[] cSklsNms = JRMCoreH.NCRacialSkillAbilityNames;
               cSklsMR = JRMCoreH.NCRacialSkillMindCost;
               skls = JRMCoreH.NCSkillIDs;
               sklsUps = JRMCoreH.ncSklsUps;
               sklsLvl = JRMCoreH.NCSkillTPCost;
               String[] sklsNms = JRMCoreH.NCSkillNames;
               sklsMR = JRMCoreH.NCSkillMindCost;
               String mod = "nc";
            } else {
               String[] rSkls = JRMCoreH.vlblRSkls;
               rSklsLvl = JRMCoreH.DBCRacialSkillTPCost;
               String[] rSklsNms = JRMCoreH.vlblRSklsNms;
               rSklsMR = JRMCoreH.DBCRacialSkillMindCost;
               cSkls = JRMCoreH.vlblCSkls;
               cSklsLvl = JRMCoreH.vlblCSklsLvl;
               String[] cSklsNms = JRMCoreH.vlblCSklsNms;
               skls = JRMCoreH.DBCSkillsIDs;
               sklsUps = JRMCoreH.vlblSklsUps;
               sklsLvl = JRMCoreH.DBCSkillTPCost;
               String[] sklsNms = JRMCoreH.DBCSkillNames;
               sklsMR = JRMCoreH.DBCSkillMindCost;
               String mod = "dbc";
            }

            int skillLevels = JRMCoreH.skillSlot_SpentMindRequirement(playerSkills, skls, sklsMR)
               + JRMCoreH.skillSlot_SpentMindRequirement_X(PlyrSkillX, race, rSklsMR)
               + JRMCoreH.skillSlot_SpentMindRequirement(PlyrSkillY, cSkls, cSklsMR);
            boolean SklSlt = JRMCoreH.canAffordSkill(playerAttributes[4], skillLevels);
            if (b == 1) {
               boolean doit = true;
               if (b2 == 16 && JGConfigUltraInstinct.CONFIG_UI_LEVELS == 0) {
                  doit = false;
               }

               if (doit) {
                  int currentTP = nbt.func_74762_e("jrmcTpint");
                  int tpCost = JRMCoreH.getSkillTPCost(b2, 0, JRMCoreH.isPowerTypeKi(powerType));
                  boolean bln = true;
                  boolean tc = false;
                  if (JRMCoreH.isPowerTypeKi(powerType) && b2 == 10) {
                     bln = false;
                     tc = currentTP >= tpCost;
                  }

                  if (bln && tpCost != -1 && currentTP >= tpCost || !bln && tc) {
                     boolean saiNotMxdSkl = !JRMCoreH.isPowerTypeKi(powerType) || b2 != 9
                        ? true
                        : JRMCoreHDBC.DBCgetConfigGodform() && JRMCoreHDBC.godKiAble(race, JRMCoreH.SklLvlX(p));
                     if (!saiNotMxdSkl) {
                        p.func_145747_a(
                           new ChatComponentTranslation(JRMCoreH.trlai("jrmc", JRMCoreH.rc_sai(race) ? "needRacialSkillMax" : "cantLearnThis"), new Object[0])
                              .func_150255_a(chatStyle)
                        );
                        return;
                     }

                     boolean stop = false;
                     int haveSkills = 0;

                     for (byte i = 0; i < playerSkillsCount; i++) {
                        if (playerSkills[i].contains(skls[b2])) {
                           stop = true;
                        }

                        if (playerSkills[i].length() > 2) {
                           haveSkills++;
                        }
                     }

                     int mindRequirement = JRMCoreH.skillMindRequirement(skls[b2], skls, sklsMR);
                     int mindRequirementResult = skillLevels + mindRequirement;
                     SklSlt = JRMCoreH.canAffordSkill(playerAttributes[4], mindRequirementResult);
                     String StE = nbt.func_74779_i("jrmcStatusEff");
                     boolean isFused = JRMCoreH.isFused(p);
                     if (isFused) {
                        p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc", "cantupgradef"), new Object[0]).func_150255_a(chatStyle));
                     } else if (JRMCoreH.isPowerTypeKi(powerType) && b2 == 0 && !JRMCoreConfig.fuzn) {
                        p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "skilldisabled"), new Object[0]).func_150255_a(chatStyle));
                     } else if (stop) {
                        p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "alreadyhaveskill"), new Object[0]).func_150255_a(chatStyle));
                     } else if (!SklSlt) {
                        p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "nomindleft"), new Object[0]).func_150255_a(chatStyle));
                     } else {
                        nbt.func_74778_a("jrmcSSlts", JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts") + "," + skls[b2] + "0"));
                        int tpResult = currentTP - tpCost;
                        nbt.func_74768_a("jrmcTpint", tpResult);
                     }
                  }
               }
            } else if (b == 2) {
               if (playerSkills[b2].contains(skls[8])) {
                  JRMCoreH.PlyrSettingsRem(p, 0);
               }

               String sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(playerSkills[b2], ""));
               nbt.func_74778_a("jrmcSSlts", sn2.length() < 3 ? "," : sn2);
            } else if (b == 3) {
               boolean doit = true;
               String StE = nbt.func_74779_i("jrmcStatusEff");
               boolean f = JRMCoreH.StusEfcts(10, StE) || JRMCoreH.StusEfcts(11, StE);
               if (f) {
                  p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc", "cantupgradef"), new Object[0]).func_150255_a(chatStyle));
                  return;
               }

               String skill = b2 == 100 ? "jrmcSSltX" : (b2 == 101 ? "jrmcSSltY" : (b2 == 102 ? "jrmcSSltZ" : "jrmcSSlts"));
               String sklnm = nbt.func_74779_i(skill);
               if (skill.equals("jrmcSSlts")) {
                  int mindRequirement = JRMCoreH.skillMindRequirement(playerSkills[b2], skls, sklsMR);
                  int mindRequirementResult = skillLevels + mindRequirement;
                  SklSlt = JRMCoreH.canAffordSkill(playerAttributes[4], mindRequirementResult);
                  sklnm = playerSkills[b2];
                  if (!SklSlt) {
                     p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "nomindleft"), new Object[0]).func_150255_a(chatStyle));
                  }
               } else if (b2 == 100) {
                  int mindRequirement = JRMCoreH.skillMindRequirement_X(sklnm, race, rSklsMR);
                  int mindRequirementResult = skillLevels + mindRequirement;
                  SklSlt = JRMCoreH.canAffordSkill(playerAttributes[4], mindRequirementResult);
                  doit = JRMCoreConfig.dat5711;
               } else if (b2 == 101) {
                  int mindRequirement = JRMCoreH.skillMindRequirement(sklnm, cSkls, cSklsMR);
                  int mindRequirementResult = skillLevels + mindRequirement;
                  SklSlt = JRMCoreH.canAffordSkill(playerAttributes[4], mindRequirementResult);
                  doit = JRMCoreConfig.dat5711;
               }

               if (!sklnm.equals("pty") && doit) {
                  int skillLvl = Integer.parseInt(sklnm.substring(2)) + 1;
                  int d = JRMCoreH.pwr_ki(powerType) && !JRMCoreH.rSai(race) && sklnm.contains(skls[9]) ? -1 : JRMCoreH.SklInit(sklnm, skls, sklsUps);
                  if (skillLvl - 1 <= d) {
                     if (b3 == 1) {
                        int tpCost = 0;
                        if (b2 == 100) {
                           tpCost = JRMCoreH.skillTPCost_X(PlyrSkillX, race, rSklsLvl);
                        } else if (b2 == 101) {
                           tpCost = JRMCoreH.skillTPCost_X(PlyrSkillY, race, cSklsLvl);
                        } else {
                           tpCost = JRMCoreH.skillTPCost(sklnm, skls, sklsLvl);
                        }

                        int currentTP = nbt.func_74762_e("jrmcTpint");
                        if (JRMCoreH.rSai(race) && b2 == 100) {
                           skillLvl = skillLvl > 7 ? 7 : skillLvl;
                        } else if (race == 4 && b2 == 100) {
                           skillLvl = skillLvl > 6 ? 6 : skillLvl;
                        } else if (race != 4 && b2 == 100) {
                           skillLvl = skillLvl > 5 ? 5 : skillLvl;
                        } else if (JRMCoreH.isPowerTypeKi(powerType) && b2 < 100 && sklnm.contains(skls[9]) && skillLvl > d + 1) {
                           skillLvl = skillLvl > d + 1 ? d + 1 : skillLvl;
                        }

                        if (tpCost != -1 && currentTP >= tpCost && SklSlt) {
                           String nskl = sklnm.substring(0, 2) + (skillLvl >= 10 ? 9 : skillLvl);
                           if (skill.equals("jrmcSSlts")) {
                              String sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(sklnm, nskl));
                              nbt.func_74778_a(skill, sn2);
                           } else {
                              nbt.func_74778_a(skill, nskl);
                           }

                           if (!sklnm.equalsIgnoreCase(nskl)) {
                              nbt.func_74768_a("jrmcTpint", currentTP - tpCost);
                           }
                        }
                     } else if (JRMCoreConfig.DebugInfo) {
                        String info = "JRMC has found Potential hacking at ID:05 by player: %s";
                        mod_JRMCore.logger.info(String.format(info, p.func_70005_c_()));
                        CommandBase.func_152373_a(p, null, info, new Object[]{p.func_70005_c_()});
                     }
                  } else {
                     String info = "JRMC has blocked an action at ID:10 by player: %s for: %s";
                     mod_JRMCore.logger.info(String.format(info, p.func_70005_c_(), sklnm));
                  }
               } else if (!sklnm.equals("pty") && !doit) {
                  String info = "JRMC has found Potential hacking at ID:15 by player: %s";
                  mod_JRMCore.logger.info(String.format(info, p.func_70005_c_(), sklnm));
               }
            } else if (b == 4) {
               String skl = b2 == 100 ? "jrmcSSltX" : (b2 == 101 ? "jrmcSSltY" : "jrmcSSlts");
               String sklnm = nbt.func_74779_i(skl);
               if (skl.equals("jrmcSSlts")) {
                  sklnm = playerSkills[b2];
               }

               int n = Integer.parseInt(sklnm.substring(2)) - 1;
               String nskl = sklnm.substring(0, 2) + (n <= -1 ? 0 : n);
               if (skl.equals("jrmcSSlts")) {
                  String sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(sklnm, nskl));
                  nbt.func_74778_a(skl, sn2);
               } else {
                  nbt.func_74778_a(skl, nskl);
               }
            } else if (b == 5) {
               String StE = nbt.func_74779_i("jrmcStatusEff");
               String bns = JRMCoreH.StusEfcts[b3];
               if (b2 == 0) {
                  nbt.func_74778_a("jrmcStatusEff", StE.contains(bns) ? StE : StE + bns);
               }

               if (b2 == 1) {
                  nbt.func_74778_a("jrmcStatusEff", StE.contains(bns) ? StE.replace(bns, "") : StE);
               }

               if (b3 == 3 && b2 == 0 && !JRMCoreH.isInCreativeMode(p)) {
                  boolean hasGoD = jgPlayer.hasStatusEffect(20, StE);
                  if (hasGoD && JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED && JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED_WITH_AURA && JGConfigDBCGoD.CONFIG_GOD_ENABLED
                     )
                   {
                     int curEnergy = jgPlayer.getEnergy();
                     byte classID = jgPlayer.getClassID();
                     int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(playerSkills, powerType));
                     float cost = JGConfigDBCGoD.CONFIG_GOD_AURA_KI_COST[1] * maxEnergy;
                     cost += JGConfigDBCGoD.CONFIG_GOD_AURA_KI_COST[0];
                     if (curEnergy > (int)cost) {
                        curEnergy -= (int)cost;
                     } else {
                        StE = JRMCoreH.StusEfcts(3, StE, nbt, false);
                     }

                     JRMCoreH.setInt(curEnergy, p, "jrmcEnrgy");
                  }
               }
            } else if (b == 6) {
               if (b3 == 0) {
                  JRMCoreH.PlyrSettingsOn(p, b2);
               }

               if (b3 == 1) {
                  JRMCoreH.PlyrSettingsRem(p, b2);
               }
            } else if (b == 8) {
               JRMCoreH.PlyrSettingsSet(nbt, b2, b3);
            }
         }
      }
   }

   public void handleStats2(int curTP, int curExp, byte align, int[] PlyrAttrbts) {
   }

   public void handleStats(int curBody, int curEnergy, byte curRelease, byte b) {
   }

   public void handleUpgrade(byte b, EntityPlayer p) {
      if (JRMCoreConfig.osbic > 0 && JRMCoreH.osbic.get(p.func_70005_c_()) < JRMCoreConfig.osbic * 20) {
         p.func_145747_a(
            new ChatComponentTranslation(
                  "Offline Protection: " + ((int)((JRMCoreConfig.osbic * 20 - JRMCoreH.osbic.get(p.func_70005_c_())) * 0.05F) + 1) + "s left", new Object[0]
               )
               .func_150255_a(chatStyle)
         );
      } else {
         NBTTagCompound nbt = this.nbt(p, "pres");
         JGPlayerMP jgPlayer = new JGPlayerMP(p);
         jgPlayer.setNBT(nbt);
         byte powerType = jgPlayer.getPowerType();
         if (JRMCoreH.isPowerTypeKi(powerType) && JRMCoreH.isFused(p)) {
            p.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc", "cantupgradef"), new Object[0]).func_150255_a(chatStyle));
         } else {
            int[] playerAttributes = jgPlayer.getAttributes();
            String[] playerSkills = jgPlayer.getSkills();
            byte race = jgPlayer.getRace();
            byte classID = jgPlayer.getClassID();
            int curBody = jgPlayer.getHealth();
            int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
            int curEnergy = jgPlayer.getEnergy();
            int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(playerSkills, powerType));
            int curStam = jgPlayer.getStamina();
            int maxStam = jgPlayer.getStaminaMax(race, classID, powerType, playerAttributes);
            if (JRMCoreH.isPowerTypeKi(powerType) || JRMCoreH.isPowerTypeChakra(powerType)) {
               int ma = b / 6;
               int cost = JRMCoreH.attrCst(playerAttributes, ma);
               if (cost > 0 && nbt.func_74762_e("jrmcTpint") >= cost && nbt.func_74762_e(JRMCoreH.AttrbtNbtI[b % 6]) < hdN7rK()) {
                  int pr = nbt.func_74762_e(JRMCoreH.AttrbtNbtI[b % 6]);
                  pr = pr < 1 ? (pr + 32768) / 2 + 32768 : pr;
                  int to = pr + 1 * JRMCoreH.attributeMultiplier(ma);
                  if (to > JRMCoreConfig.tmx) {
                     return;
                  }

                  nbt.func_74768_a("jrmcTpint", nbt.func_74762_e("jrmcTpint") - cost);
                  nbt.func_74768_a(JRMCoreH.AttrbtNbtI[b % 6], v2tBFN(to));
                  double incBody = JRMCoreH.statInc(powerType, 2, 1, race, classID, 0.0F);
                  JRMCoreH.setInt((int)(curBody + incBody > maxBody ? maxBody : curBody + incBody), p, "jrmcBdy");
                  double incEnergy = JRMCoreH.statInc(powerType, 5, 1, race, classID, JRMCoreH.SklLvl_KiBs(playerSkills, powerType));
                  JRMCoreH.setInt((int)(curEnergy + incEnergy > maxEnergy ? maxEnergy : curEnergy + incEnergy), p, "jrmcEnrgy");
                  double incStam = JRMCoreH.statInc(powerType, 3, 1, race, classID, 0.0F);
                  JRMCoreH.setInt((int)(curStam + incStam > maxStam ? maxStam : curStam + incStam), p, "jrmcStamina");
               }
            } else if (JRMCoreH.isPowerTypeSAO(powerType)) {
               int ap = nbt.func_74762_e("saocAp");
               if (ap > 0) {
                  nbt.func_74768_a(JRMCoreH.AttrbtNbtI[b], (short)(nbt.func_74762_e(JRMCoreH.AttrbtNbtI[b]) + 1));
                  nbt.func_74768_a("saocAp", nbt.func_74762_e("saocAp") - 1);
                  if (b == 2) {
                     playerAttributes = JRMCoreH.PlyrAttrbts(p);
                     maxBody = JRMCoreH.stat(p, 2, powerType, 2, playerAttributes[2], race, classID, 0.0F);
                     double incBody = JRMCoreH.statInc(powerType, 2, 1, race, classID, 0.0F);
                     JRMCoreH.setInt((int)(curBody + incBody > maxBody ? maxBody : curBody + incBody), p, "jrmcBdy");
                  }
               }
            }

            JRMCoreH.Stats2(p, nbt.func_74762_e("jrmcTpint"), nbt.func_74771_c("jrmcExp"), nbt.func_74771_c("jrmcAlign"), playerAttributes);
         }
      }
   }

   public void handleChar(byte b, int b2, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      int i = 20;
      byte acc = nbt.func_74771_c(Acc);
      byte pwrtyp = nbt.func_74771_c(P);
      byte rce = nbt.func_74771_c(R);
      byte cls = nbt.func_74771_c(Cl);
      byte st = nbt.func_74771_c(St);
      if (acc == 0 && b == this.b(0)) {
         nbt.func_74774_a(R, (byte)b2);
         nbt.func_74774_a(St, (byte)(rce == 4 ? 4 : 0));
      }

      if (acc == 0 && b == 106) {
         nbt.func_74774_a(St, (byte)(rce == 4 ? b2 : 0));
      }

      if (acc == 0 && b == this.b(7)) {
         nbt.func_74776_a("JRYCAge", JRMCoreH.YearsD[b2]);
      }

      if (acc == 0 && b == this.b(2)) {
         nbt.func_74774_a(P, (byte)b2);
      }

      if (acc == 0 && b == this.b(3)) {
         nbt.func_74774_a(Cl, (byte)b2);
      }

      if (acc == 0 && b == this.b(4)) {
         boolean doit = true;
         if (JRMCoreH.DBC()) {
            doit = !JRMCoreH.isFused(p);
         }

         if (doit) {
            nbt.func_74774_a(Acc, (byte)b2);
            int[] PlyrAttrbts;
            if (pwrtyp != 1 && pwrtyp != 2) {
               for (int j = 0; j < 3; j++) {
                  nbt.func_74768_a(JRMCoreH.AttrbtNbtI[j], JRMCoreH.attributeStart(pwrtyp, j, rce, cls));
               }

               PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
               int maxBody = JRMCoreH.stat(p, 2, pwrtyp, 2, PlyrAttrbts[2], rce, cls, 0.0F);
               JRMCoreH.setInt(maxBody, p, "jrmcBdy");
            } else {
               nbt.func_74774_a(St, (byte)(rce == 4 ? 4 : 0));
               int r = JRMCoreH.getInt(p, "jrmcRencrnt");
               if (r == 1) {
                  JRMCoreH.setInt(0, p, "jrmcRencrnt");

                  for (int j = 0; j < 6; j++) {
                     int a = JRMCoreH.getInt(p, JRMCoreH.AttrbtNbtR[j]);
                     int s = JRMCoreH.attributeStart(pwrtyp, j, rce, cls);
                     nbt.func_74768_a(JRMCoreH.AttrbtNbtI[j], a > s ? a : s);
                     JRMCoreH.setInt(0, p, JRMCoreH.AttrbtNbtR[j]);
                  }
               } else {
                  for (int j = 0; j < 6; j++) {
                     nbt.func_74768_a(JRMCoreH.AttrbtNbtI[j], JRMCoreH.attributeStart(pwrtyp, j, rce, cls));
                  }
               }

               PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
               int maxBody = JRMCoreH.stat(p, 2, pwrtyp, 2, PlyrAttrbts[2], rce, cls, 0.0F);
               JRMCoreH.setInt(maxBody, p, "jrmcBdy");
               int maxEnergy = JRMCoreH.stat(p, 5, pwrtyp, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(p, pwrtyp));
               JRMCoreH.setInt(maxEnergy, p, "jrmcEnrgy");
            }

            JRMCoreH.Stats2(p, nbt.func_74762_e("jrmcTpint"), nbt.func_74771_c("jrmcExp"), nbt.func_74771_c("jrmcAlign"), PlyrAttrbts);
            nbt.func_74778_a("jrmcSSltX", JRMCoreH.SklGveX(nbt.func_74771_c(R), nbt.func_74771_c(Cl), pwrtyp));
            nbt.func_74778_a("jrmcSSltY", JRMCoreH.SklGveY(nbt.func_74771_c(R), nbt.func_74771_c(Cl), pwrtyp));
            nbt.func_74768_a("jrmcArcRsrv", 0);
            nbt.func_74778_a("jrmcMajinAbsorptionData", "0,0,0+0");
         }
      }

      if (b == this.b(5) && nbt.func_74771_c(Diff) < b2) {
         nbt.func_74774_a(Diff, (byte)b2);
      }

      if (b == this.b(6)) {
         nbt.func_74774_a(Ptch, (byte)b2);
      }

      if (b == 100 && !JRMCoreH.isFused(p)) {
         JRMCoreH.resetDedSer();
         JRMCoreH.resetChar(p);
      }

      if (b == 101 && nbt.func_74771_c(Diff) >= 1 && nbt.func_74762_e("jrmcDiffRed") <= 1) {
         nbt.func_74768_a("jrmcDiffRed", 2400 * nbt.func_74771_c(Diff));
      }

      if (b == 102) {
         if (nbt.func_74771_c("jrmcTlmd") == 0 || nbt.func_74771_c("jrmcTlmd") == -1) {
            nbt.func_74774_a("jrmcTlmd", (byte)1);
         } else if (nbt.func_74771_c("jrmcTlmd") == 1) {
            nbt.func_74774_a("jrmcTlmd", (byte)0);
         }
      }

      if (acc == 0 && b == 103) {
         if (b2 == 0) {
            nbt.func_74774_a("jrmcTlmd", (byte)2);
         }

         if (b2 == 1) {
            nbt.func_74774_a("jrmcTlmd", (byte)0);
         }
      }

      if (b == 104) {
         if (nbt.func_74771_c("jrmcTlmd") == 0 || nbt.func_74771_c("jrmcTlmd") == -1 || nbt.func_74771_c("jrmcTlmd") == 1) {
            nbt.func_74774_a("jrmcTlmd", (byte)3);
         }

         int state = JRMCoreH.getByte(p, "jrmcState");
         if (state == 7 || state == 8 || state == 14) {
            JRMCoreH.setByte(0, p, "jrmcState");
         }
      }

      if (b == 105) {
         int tp = nbt.func_74762_e("jrmcTpint");
         int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
         int maxBody = JRMCoreH.stat(p, 2, pwrtyp, 2, PlyrAttrbts[2], rce, cls, 0.0F);
         if ((nbt.func_74771_c("jrmcTlmd") == 3 || nbt.func_74771_c("jrmcTlmd") == 4) && tp >= 20) {
            nbt.func_74768_a("jrmcTpint", tp - 20);
            JRMCoreH.jrmcDam(p, (int)(maxBody * 0.1F), null);
            nbt.func_74774_a("jrmcTlmd", (byte)0);
         }
      }
   }

   public void handleCol(int i, byte b, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
   }

   public void handleTick(int jrmcpg, String jrmcp, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      if (jrmcpg == 1) {
         if (nbt.func_74781_a("jrmcAlign") == null) {
            nbt.func_74774_a("jrmcAlign", (byte)67);
         }

         if (nbt.func_74771_c("jrmcAlign") > 100) {
            nbt.func_74774_a("jrmcAlign", (byte)100);
         }

         if (nbt.func_74771_c("jrmcAlign") < 0) {
            nbt.func_74774_a("jrmcAlign", (byte)0);
         }

         int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(p);
         String[] PlyrSkills = nbt.func_74779_i("jrmcSSlts").split(",");

         for (byte i = 0; i < JRMCoreH.AttrbtNbtI.length; i++) {
            if (nbt.func_74781_a(JRMCoreH.AttrbtNbtI[i]) == null) {
               nbt.func_74768_a(JRMCoreH.AttrbtNbtI[i], 1);
            }

            PlyrAttrbts[i] = nbt.func_74762_e(JRMCoreH.AttrbtNbtI[i]);
         }

         JRMCoreH.Stats2(p, nbt.func_74762_e("jrmcTpint"), nbt.func_74771_c("jrmcExp"), nbt.func_74771_c("jrmcAlign"), PlyrAttrbts);
      }

      if (jrmcpg == 3) {
         if (nbt.func_74781_a("jrmcSSltX") == null) {
            nbt.func_74778_a("jrmcSSltX", "pty");
         }

         if (nbt.func_74781_a("jrmcSSltY") == null) {
            nbt.func_74778_a("jrmcSSltY", "pty");
         }

         nbt.func_74778_a("jrmcSSltX", JRMCoreH.SklGveX(nbt.func_74771_c(R), nbt.func_74771_c(Cl), nbt.func_74771_c(P)));
         nbt.func_74778_a("jrmcSSltY", JRMCoreH.SklGveY(nbt.func_74771_c(R), nbt.func_74771_c(Cl), nbt.func_74771_c(P)));
         JRMCoreH.Stats3(p, nbt.func_74779_i("jrmcSSlts"), nbt.func_74779_i("jrmcSSltX"), nbt.func_74779_i("jrmcSSltY"), nbt.func_74779_i("jrmcSSltZ"));
      }

      if (jrmcpg == -1) {
         for (byte i = 0; i < 4; i++) {
            String s = JRMCoreH.getString(p, JRMCoreH.techNbt[i]);
            JRMCoreH.Tech(p, i, s);
         }
      }
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

   public static int dS9XgQ(String l) {
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

   public static int hdN7rK() {
      int b = JRMCoreConfig.tmx;
      String r = "64";
      String k = "3B9ACA00";
      return b > dS9XgQ(k) ? dS9XgQ(k) : (b < dS9XgQ(r) ? 0 : b);
   }

   public static int v2tBFN(int b) {
      String k = "3B9ACA00";
      return b > dS9XgQ(k) ? dS9XgQ(k) : b;
   }

   public byte b(int n) {
      return (byte)n;
   }

   public NBTTagCompound nbt(EntityPlayer p, String s) {
      return JRMCoreH.nbt(p, s);
   }

   public void jrmct(int tick, String s, EntityPlayer p) {
      JRMCoreH.jrmct(tick, s, p);
   }
}
