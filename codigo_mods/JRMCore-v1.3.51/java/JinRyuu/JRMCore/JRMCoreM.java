package JinRyuu.JRMCore;

import JinRyuu.JRMCore.p.JRMCorePData2;
import JinRyuu.JRMCore.p.PD;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.FMLCommonHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class JRMCoreM {
   public static HashMap<String, JRMCoreMsnBundle> missions = new HashMap<>();
   public static HashMap<String, JRMCoreMsn> missionsC = new HashMap<>();
   public static HashMap<String, ArrayList<String>> missionsCD = new HashMap<>();
   public static final int MISSION_INFO_SeID = 0;
   public static final int MISSION_INFO_Name = 1;
   public static final int MISSION_INFO_Desc = 2;
   public static final int MISSION_INFO_Auth = 3;
   public static final int MISSION_INFO_Vers = 4;
   public static final int MISSION_INFO_Mods = 5;
   public static final int MISSION_INFO_Rept = 6;
   public static final int MISSION_INFO_Unlo = 7;
   public static final String SERIES_DBC_MainID = "mainDBC";
   public static final String SERIES_DBC_DBSBrolyID = "dbcDBCBroly";
   public static final String SERIES_NC_MainID = "mainNC";
   public static final String MISSION_TYPE_Kill = "kill";
   public static final String MISSION_TYPE_KillMoreOfSamekind = "killsame";
   public static final String MISSION_TYPE_GoToBiome = "biome";
   public static final String MISSION_TYPE_GoToDim = "dim";
   public static final String MISSION_TYPE_ClickNext = "next";
   public static final String MISSION_TYPE_ClickStart = "start";
   public static final String MISSION_TYPE_ClickSkip = "skip";
   public static final String MISSION_TYPE_ClickRestart = "restart";
   public static final String MISSION_TYPE_GatherItem = "item";
   public static final String MISSION_TYPE_TalkTo = "talk";
   public static final String MISSION_TYPE_StateIn = "state";
   public static final String MISSION_TYPE_BeInBiome = "biome2";
   public static final String MISSION_TYPE_BeInDim = "dim2";
   public static final String MISSION_TYPE_Lvl = "lvl";
   public static final String MISSION_PROP_RandomReward = "randrew";
   public static final String REWARD_TYPE_NOTHING = "nothing";
   public static final String REWARD_TYPE_Item = "item";
   public static final String REWARD_TYPE_TP = "tp";
   public static final String REWARD_TYPE_Alignment = "align";
   public static final String REWARD_TYPE_Command = "com";
   public static final String REWARD_TYPE_TP_FIX = "fix";
   public static final String REWARD_TYPE_TP_ALIGN = "align";
   public static final String REWARD_TYPE_TP_LVL = "lvl";
   public static final String REWARD_TYPE_TP_LVLALIGN = "lvlalign";
   public static final Type JSN_TYPE_MSNbndl = (new TypeToken<JRMCoreMsnBundle>() {}).getType();
   public static final Type JSN_TYPE_MSN = (new TypeToken<JRMCoreMsn>() {}).getType();
   public static final int SYNC_DataVersion_ServerID = 0;
   public static final int SYNC_DataVersion_Version = 1;
   public static final int SYNC_DataVersion_Repeat = 2;
   public static final int SYNC_DataVersion_CompleteCounter = 3;
   public static final int SYNC_DataVersion_CompleteCounterLatestVersionOnly = 4;
   public static final int SYNC_COND_SeriesID = 0;
   public static final int SYNC_COND_MissionID = 1;
   public static final int SYNC_COND_Started = 2;
   public static final int SYNC_COND_data1 = 3;
   public static final int SYNC_COND_data2 = 4;
   public static final int SYNC_COND_data3 = 5;
   public static final int SYNC_COND_data4 = 6;
   public static final String MISSION_CONDS_TYPE = "0";
   public static final String MISSION_CONDS_Name = "N";
   public static final String MISSION_CONDS_Health = "H";
   public static final String MISSION_CONDS_Attack = "A";
   public static final String MISSION_CONDS_Amount = "M";
   public static final String MISSION_CONDS_MsgSpawn = "S";
   public static final String MISSION_CONDS_MsgDeath = "D";
   public static final String MISSION_CONDS_Message = "G";
   public static final String MISSION_CONDS_MsgBtn = "B";
   public static final String MISSION_CONDS_Protect = "P";
   public static final String MISSION_CONDS_NoSpawn = "W";
   public static final String MISSION_TalkTo_Series = "series";
   public static final String MISSION_TalkTo_translated = "translated";
   public static final String MISSION_CONDS_Transformations = "T";
   public static final String MISSION_CONDS_MsgSpawnSnd = "O";
   public static final String MISSION_CONDS_MsgDeathSnd = "U";

   public static void msnGenWrt(File dbcMain, Object mb) {
      try {
         dbcMain.getParentFile().mkdirs();
         dbcMain.createNewFile();
         Writer writer = new FileWriter(dbcMain);
         Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
         gson.toJson(mb, JSN_TYPE_MSNbndl, writer);
         writer.flush();
         writer.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static JRMCoreMsnBundle rd(File file) {
      try {
         Gson gson = new Gson();
         JsonReader reader = new JsonReader(new FileReader(file));
         return (JRMCoreMsnBundle)gson.fromJson(reader, JSN_TYPE_MSNbndl);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static void om(MinecraftServer s) {
      if (s != null && s.field_71305_c.length > 0 && s.func_71218_a(0) != null && s.func_71218_a(0).getChunkSaveLocation() != null) {
         String pnt = s.func_71218_a(0).getChunkSaveLocation() + "/data/missions";
         File entitylist = new File(pnt, "EntityList.txt");
         entitylist.delete();

         try {
            ArrayList<String> ar = Lists.newArrayList();

            for (String n : EntityList.field_75625_b.keySet()) {
               Class c = (Class)EntityList.field_75625_b.get(n);
               if (EntityLivingBase.class.isAssignableFrom(c)) {
                  ar.add(n);
               }
            }

            Collections.sort(ar);
            String list = "";

            for (int i = 0; i < ar.size(); i++) {
               list = list + ar.get(i) + "\n";
            }

            FileUtils.writeStringToFile(entitylist, list);
         } catch (IOException e1) {
            e1.printStackTrace();
         }

         File dbcMain = new File(pnt, "mainDBC.json");
         JRMCoreMsnBundle msb = JRMCoreMm.msnGen();
         File dbcDBSBroly = new File(pnt, "dbcDBCBroly.json");
         JRMCoreMsnBundle missionSideDBSBroly = JRMCoreMm.missionSideDBSBroly();
         File ncMain = new File(pnt, "mainNC.json");
         JRMCoreMsnBundle msbNC = JRMCoreMm.msnGenNC();
         missions.clear();
         File dir = new File(pnt);
         File[] list = dir.listFiles();
         if (dir != null && list != null && list.length > 0) {
            if (!dbcMain.exists()) {
               msnGenWrt(dbcMain, msb);
               missions.put("mainDBC", msb);
            } else {
               JRMCoreMsnBundle rms = rd(dbcMain);
               missions.put("mainDBC", msb.getVersion().equalsIgnoreCase(msb.getVersion()) ? rms : msb);
            }

            if (!dbcDBSBroly.exists()) {
               msnGenWrt(dbcDBSBroly, missionSideDBSBroly);
               missions.put("dbcDBCBroly", missionSideDBSBroly);
            } else {
               JRMCoreMsnBundle rms = rd(dbcDBSBroly);
               missions.put("dbcDBCBroly", missionSideDBSBroly.getVersion().equalsIgnoreCase(missionSideDBSBroly.getVersion()) ? rms : missionSideDBSBroly);
            }

            if (!ncMain.exists()) {
               msnGenWrt(ncMain, msbNC);
               missions.put("mainNC", msbNC);
            } else {
               JRMCoreMsnBundle rms = rd(ncMain);
               missions.put("mainNC", msbNC.getVersion().equalsIgnoreCase(msbNC.getVersion()) ? rms : msbNC);
            }

            for (int i = 0; i < list.length; i++) {
               String mss = list[i].getName();
               if (mss.endsWith(".json")) {
                  mss = mss.replace(".json", "");
                  if (!mss.equalsIgnoreCase("mainDBC") && !mss.equalsIgnoreCase("mainNC")) {
                     missions.put(mss, rd(list[i]));
                  }
               }
            }
         } else {
            if (!dbcMain.exists()) {
               msnGenWrt(dbcMain, msb);
               missions.put("mainDBC", msb);
            } else {
               JRMCoreMsnBundle rms = rd(dbcMain);
               missions.put("mainDBC", msb.getVersion().equalsIgnoreCase(msb.getVersion()) ? rms : msb);
            }

            if (!dbcDBSBroly.exists()) {
               msnGenWrt(dbcDBSBroly, missionSideDBSBroly);
               missions.put("dbcDBCBroly", missionSideDBSBroly);
            } else {
               JRMCoreMsnBundle rms = rd(dbcDBSBroly);
               missions.put("dbcDBCBroly", missionSideDBSBroly.getVersion().equalsIgnoreCase(missionSideDBSBroly.getVersion()) ? rms : missionSideDBSBroly);
            }

            if (!ncMain.exists()) {
               msnGenWrt(ncMain, msbNC);
               missions.put("mainNC", msbNC);
            } else {
               JRMCoreMsnBundle rms = rd(ncMain);
               missions.put("mainNC", msbNC.getVersion().equalsIgnoreCase(msbNC.getVersion()) ? rms : msbNC);
            }
         }
      }
   }

   public static String[] getSyda(String s) {
      return s.split(";");
   }

   public static String getSyda(String s, String sid) {
      String[] s1 = getSyda(s);

      for (int i = 0; i < s1.length; i++) {
         String[] sd = s1[i].split(",");
         if (sd[0].equalsIgnoreCase(sid)) {
            return s1[i];
         }
      }

      return "";
   }

   public static int getSydaAmount(String s) {
      return getSyda(s).length;
   }

   public static int getSydaAmountFromData1(String s) {
      return getSyda(s).length - 3;
   }

   public static String[] getMda(String s, int i) {
      return i >= 0 ? getSyda(s)[i].split(",") : new String[]{"", "", ""};
   }

   public static String[] getMda(String s, String i) {
      return getSyda(s, i).split(",");
   }

   public static String getMda_Con(String s, int i, int a) {
      String[] ar = getMda(s, i);
      return ar.length > a ? ar[a] : "";
   }

   public static String getMda_Con(String[] ar, int a) {
      return ar.length > a ? ar[a] : "";
   }

   public static String getMda_Series(String s, int i) {
      return getMda(s, i)[0];
   }

   public static int getMda_Mission(String s, int i) {
      return Integer.parseInt(getMda(s, i)[1]);
   }

   public static int getMda_Mission(String s, String i) {
      return Integer.parseInt(getMda(s, i)[1]);
   }

   public static boolean isUnlocked(String sidd, String MSD, String MSDV) {
      if (!sidd.contains(":")) {
         String[] sydaV = getMda(MSDV, sidd);
         String ccv = getSydaV(sydaV, 4);
         return !ccv.equals("0");
      } else {
         String[] nm = sidd.split(":");
         String[] sydaV = getMda(MSDV, nm[0]);
         String ccv = getSydaV(sydaV, 4);
         int ps = getMda_Mission(MSD, nm[0]);
         return !ccv.equals("0") || ccv.equals("0") && ps > Integer.parseInt(nm[1]);
      }
   }

   public static String setSydaV(String msdv, String sid, String ver, String rep, String cc, String ccv) {
      String[] sda = getSyda(msdv);
      String tr = "";
      boolean hi = false;
      if (msdv.length() > 3) {
         for (int i = 0; i < sda.length; i++) {
            String[] ds = sda[i].split(",");
            if (sid.equalsIgnoreCase(ds[0])) {
               tr = tr + ";" + sid + "," + ver + "," + rep + "," + cc + "," + ccv;
               hi = true;
            } else if (ds.length > 3) {
               tr = tr + ";" + ds[0] + "," + ds[1] + "," + ds[2] + "," + ds[3] + "," + ds[4];
            } else {
               tr = tr + ";" + ds[0] + "," + ds[1] + "," + 0 + "," + 0 + "," + 0;
            }
         }
      }

      if (!hi) {
         tr = tr + ";" + sid + "," + ver + "," + 0 + "," + 0 + "," + 0;
      }

      return tr.substring(1);
   }

   public static String setSyda(String msd, String sid, int mid, int sz, int dc, String ds) {
      String[] ar = new String[sz];
      boolean mk = false;

      for (int i = 0; i < ar.length; i++) {
         String[] mda = getMda(msd, sid);
         int scd = SYNC_COND_data(i);
         if (mda.length <= scd) {
            mk = true;
            break;
         }

         ar[i] = i == dc ? ds : mda[scd];
      }

      if (mk) {
         ar = new String[sz];

         for (int i = 0; i < sz; i++) {
            ar[i] = "0";
         }
      }

      return setSyda(msd, sid, mid, ar);
   }

   public static String setSyda(String sd, String sid, int mid, String... v) {
      String d = "";
      String[] sda = getSyda(sd);
      int asid = 0;
      int i = -1;

      for (int j = 0; j < sda.length; j++) {
         String csid = getMda_Series(sd, j);
         if (csid.equals(sid)) {
            if (++asid == 1) {
               i = j;
            }
         }
      }

      for (int j = 0; j < sda.length; j++) {
         if (i == j) {
            d = d + ";" + parse_Mda(sid, mid, v);
         } else if (sda[j].length() > 3) {
            d = d + ";" + sda[j];
         }
      }

      if (i == -1) {
         d = d + ";" + parse_Mda(sid, mid, v);
      }

      sd = d.length() > 3 ? d.substring(1, d.length()) : d;
      if (asid > 1) {
         d = "";
         sda = getSyda(sd);
         asid = 0;

         for (int j = 0; j < sda.length; j++) {
            String csid = getMda_Series(sd, j);
            if (csid.equals(sid)) {
               asid++;
            }

            if (asid <= 1 && sda[j].length() > 3) {
               d = d + ";" + sda[j];
            }
         }

         sd = d.length() > 3 ? d.substring(1, d.length()) : d;
      }

      return sd;
   }

   public static String parse_Mda(String sid, int mid, String... v) {
      String r = sid + "," + mid;

      for (int i = 0; i < v.length; i++) {
         r = r + "," + v[i];
      }

      return r;
   }

   public static int getMda_SeriesByID(String s, String i) {
      for (int j = 0; j < getSydaAmount(s); j++) {
         if (getMda(s, j)[0].equalsIgnoreCase(i)) {
            return j;
         }
      }

      return -1;
   }

   public static int SYNC_COND_data(int c) {
      return 2 + c;
   }

   public static int SYNC_COND_data_REV(int c) {
      return c - 2;
   }

   public static int getSycoDaam(String s) {
      if (s.equalsIgnoreCase("kill")) {
         return 1;
      } else if (s.equalsIgnoreCase("killsame")) {
         return 1;
      } else if (s.equalsIgnoreCase("biome")) {
         return 1;
      } else if (s.equalsIgnoreCase("dim")) {
         return 1;
      } else {
         return s.equalsIgnoreCase("item") ? 1 : 1;
      }
   }

   public static String[] getMCo_parse(String s) {
      return s.split(";");
   }

   public static String getMCo_type(String s) {
      String[] p = getMCo_parse(s);
      return p[0];
   }

   public static String getMCo_data(String s, String a) {
      String[] p = getMCo_parse(s);
      if (a.equals("0")) {
         return p[0];
      }

      for (int i = 0; i < p.length; i++) {
         if (p[i].toUpperCase().startsWith(a)) {
            return p[i].substring(1);
         }
      }

      return "";
   }

   public static int getMCo_dataI(String s, String a) {
      String n = getMCo_data(s, a);
      return NumberUtils.isNumber(n) ? Integer.parseInt(n) : 0;
   }

   public static double getMCo_dataD(String s, String a) {
      String n = getMCo_data(s, a);
      return NumberUtils.isNumber(n) ? Double.parseDouble(getMCo_data(s, n)) : 0.0;
   }

   public static String[] getMCo_TranSplit(String s) {
      return s.split("\\|\\|");
   }

   public static String getBtn(String s) {
      if (s.equalsIgnoreCase("next")) {
         return JRMCoreH.trl("jrmc", "Next");
      } else if (s.equalsIgnoreCase("start")) {
         return JRMCoreH.trl("jrmc", "start");
      } else if (s.equalsIgnoreCase("skip")) {
         return JRMCoreH.trl("jrmc", "Skip");
      } else {
         return s.equalsIgnoreCase("restart") ? JRMCoreH.trl("jrmc", "Restart") : s;
      }
   }

   public static boolean getMda_btn_Start(ArrayList<String> a, String[] s, int g) {
      int size = a.size();
      if (size != 1 || !a.get(0).equalsIgnoreCase("next") && !a.get(0).equalsIgnoreCase("start")) {
         if (size != 1 || !a.get(0).equalsIgnoreCase("skip") && !a.get(0).equalsIgnoreCase("restart")) {
            int inB = 0;
            int inD = 0;
            boolean all = false;
            boolean cont = true;
            int i = 1;

            for (int j = 1; j < a.size(); j++) {
               String os = a.get(j);
               String t = getMCo_type(os);
               if (cont && t.equalsIgnoreCase("kill")) {
                  all = s[2].equals("0") && s[SYNC_COND_data(i)].equals("0");
                  boolean spwn = getMCo_data(os, "P").equalsIgnoreCase("spwn");
                  if (spwn) {
                     return false;
                  }

                  if (all) {
                     return all;
                  }
               } else if (cont && t.equalsIgnoreCase("killsame")) {
                  int da = (int)(getMCo_dataI(os, "M") * gm(g));
                  all = s[2].equals("0") && da > Integer.parseInt(s[SYNC_COND_data(i)]);
                  boolean spwn = getMCo_data(os, "P").equalsIgnoreCase("spwn");
                  if (spwn) {
                     return false;
                  }

                  if (all) {
                     return all;
                  }
               } else if (t.equalsIgnoreCase("biome2")) {
                  all = s[SYNC_COND_data(i)].equals("1");
                  inB = all ? 1 : 2;
               } else if (t.equalsIgnoreCase("dim2")) {
                  all = s[SYNC_COND_data(i)].equals("1");
                  inD = all ? 1 : 2;
               }

               cont = (inB == 0 || inB == 1) && (inD == 0 || inD == 1);
               i++;
            }

            return all;
         } else {
            return true;
         }
      } else {
         return s[2].equals("0");
      }
   }

   public static String GetWorldNameFromCurDim(int i) {
      WorldProvider p = DimensionManager.createProviderFor(i);
      return p != null ? p.func_80007_l() : "Id " + i;
   }

   public static HashMap<String, String> getMda_Obj_TalkTo() {
      HashMap<String, String> h = null;
      String msd = JRMCoreH.MSD;

      for (String seriesID : missionsC.keySet()) {
         int MsnID = 0;
         if (msd.length() > 3) {
            MsnID = getMda_Mission(msd, seriesID);
         }

         JRMCoreMsn msn = missionsC.get(seriesID);
         int nr = 0;
         if (msn != null && msn.getId() == MsnID) {
            ArrayList<String> a = msn.getObjectives(JRMCoreH.Pwrtyp, JRMCoreH.Race, JRMCoreH.Class);
            String btnN = a.get(0);
            String[] syncMda = getMda(msd, seriesID);
            if (syncMda.length > 1) {
               int size = a.size();
               if (syncMda.length > 3) {
                  for (int i = 1; i < a.size(); i++) {
                     String os = a.get(i);
                     String t = getMCo_type(os);
                     if (os != null && t.equalsIgnoreCase("talk")) {
                        if (syncMda[SYNC_COND_data(i)].equals("1")) {
                           return null;
                        }

                        h = new HashMap<>();
                        h.put("N", getMCo_data(os, "N"));
                        h.put("G", getMCo_data(os, "G"));
                        h.put("B", getMCo_data(os, "B"));
                        h.put("translated", "" + msn.isTranslated());
                        h.put("series", seriesID);
                        return h;
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   public static float gm(int s) {
      return 1.0F + (s > 0 ? s * 0.5F - 0.5F : 0.0F);
   }

   public static boolean getMda_ObjComp_all(ArrayList<String> a, String[] s, int g) {
      int size = a.size();
      if (size != 1 || !a.get(0).equalsIgnoreCase("next") && !a.get(0).equalsIgnoreCase("start")) {
         if (size == 1 && a.get(0).equalsIgnoreCase("skip")) {
            return true;
         }

         if (s.length <= 3) {
            return false;
         }

         boolean all = true;

         for (int i = 1; i < a.size(); i++) {
            String os = a.get(i);
            String t = getMCo_type(os);
            int scd = SYNC_COND_data(i);
            if (s.length <= scd) {
               all = true;
               break;
            }

            if (t.equalsIgnoreCase("kill")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("killsame")) {
               int d1 = (int)(getMCo_dataI(os, "M") * gm(g));
               all = Integer.parseInt(s[scd]) >= d1;
            } else if (t.equalsIgnoreCase("item")) {
               int d1 = (int)(getMCo_dataI(os, "M") * gm(g));
               all = Integer.parseInt(s[scd]) >= d1;
            } else if (t.equalsIgnoreCase("biome")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("dim")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("talk")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("state")) {
               String d1 = getMCo_data(os, "N");
               all = s[scd].equalsIgnoreCase(d1);
            } else if (t.equalsIgnoreCase("biome2")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("dim2")) {
               all = s[scd].equals("1");
            } else if (t.equalsIgnoreCase("lvl")) {
               int d1 = getMCo_dataI(os, "M");
               all = Integer.parseInt(s[scd]) >= d1;
            } else if (t.equalsIgnoreCase("skip")) {
               all = true;
            }

            if (!all) {
               break;
            }
         }

         return all;
      } else {
         return s[2].equals("1");
      }
   }

   public static String getMCo_readable(String os, String nss, int g) {
      String t = getMCo_type(os).toLowerCase();
      String nm = getMCo_data(os, "N");
      if (t.equalsIgnoreCase("kill")) {
         boolean any = getMCo_data(os, "P").equalsIgnoreCase("no");
         boolean spwn = getMCo_data(os, "P").equalsIgnoreCase("spwn");
         Entity e = EntityList.func_75620_a(nm, null);
         String en = e != null ? e.func_70005_c_() : "ERROR";
         String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.defeated") : "" + (gm(g) > 1.0F ? " (x" + gm(g) + ")" : "");
         return JRMCoreH.trl("jrmc", "missionObjType." + t + (any ? "" : ""), en, kld)
            + (!any && !spwn ? "" : JRMCoreH.trl("jrmc", "missionObjType.anywilldo"));
      }

      if (t.equalsIgnoreCase("killsame") && nss.length() > 0) {
         boolean any = getMCo_data(os, "P").equalsIgnoreCase("no");
         boolean spwn = getMCo_data(os, "P").equalsIgnoreCase("spwn");
         int am = (int)(getMCo_dataI(os, "M") * gm(g));
         Entity e = EntityList.func_75620_a(nm, null);
         String en = e != null ? e.func_70005_c_() : "ERROR";
         String kld = Integer.parseInt(nss) >= am
            ? JRMCoreH.trl("jrmc", "missionObjType.completed")
            : " - " + nss + "/" + am + (gm(g) > 1.0F ? " (x" + gm(g) + ")" : "");
         return JRMCoreH.trl("jrmc", "missionObjType." + t, en, kld) + (!any && !spwn ? "" : JRMCoreH.trl("jrmc", "missionObjType.anywilldo"));
      }

      if (t.equalsIgnoreCase("item") && nss.length() > 0) {
         int am = (int)(getMCo_dataI(os, "M") * gm(g));
         String[] nm2 = nm.split("::");
         Item i = JRMCoreH.getItemByText(nm2.length > 1 ? nm2[0] : nm);
         ItemStack is = i != null ? new ItemStack(i, 1, nm2.length > 1 ? Integer.parseInt(nm2[1]) : 0) : null;
         String en = i != null ? is.func_77977_a() : "ERROR";
         String kld = Integer.parseInt(nss) >= am
            ? JRMCoreH.trl("jrmc", "missionObjType.completed")
            : " - " + nss + "/" + am + (gm(g) > 1.0F ? " (x" + gm(g) + ")" : "");
         return JRMCoreH.trl("jrmc", "missionObjType." + t, JRMCoreH.trl(en + ".name"), kld);
      }

      if (t.equalsIgnoreCase("biome")) {
         String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
         return JRMCoreH.trl("jrmc", "missionObjType." + t, nm, kld);
      }

      if (t.equalsIgnoreCase("dim")) {
         if (NumberUtils.isNumber(nm)) {
            nm = GetWorldNameFromCurDim(Integer.parseInt(nm));
         }

         String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
         return JRMCoreH.trl("jrmc", "missionObjType." + t, nm, kld);
      } else {
         if (t.equalsIgnoreCase("talk")) {
            String en = EntityList.func_75620_a(nm, null).func_70005_c_();
            String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
            return JRMCoreH.trl("jrmc", "missionObjType." + t, en, kld);
         }

         if (t.equalsIgnoreCase("state")) {
            String kld = nss.equalsIgnoreCase(nm) ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
            return JRMCoreH.trl("jrmc", "missionObjType." + t, nm, kld);
         }

         if (t.equalsIgnoreCase("biome2")) {
            String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
            return JRMCoreH.trl("jrmc", "missionObjType." + t, nm, kld);
         }

         if (t.equalsIgnoreCase("dim2")) {
            if (NumberUtils.isNumber(nm)) {
               nm = GetWorldNameFromCurDim(Integer.parseInt(nm));
            }

            String kld = nss.equals("1") ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
            return JRMCoreH.trl("jrmc", "missionObjType." + t, nm, kld);
         } else if (t.equalsIgnoreCase("lvl") && nss.length() > 0) {
            int am = getMCo_dataI(os, "M");
            String kld = Integer.parseInt(nss) >= am ? JRMCoreH.trl("jrmc", "missionObjType.completed") : "";
            return JRMCoreH.trl("jrmc", "missionObjType." + t, am, kld);
         } else {
            return JRMCoreH.trl("jrmc", "missionObjType.nothing");
         }
      }
   }

   public static int getMAlgnmnt_Num(String s) {
      for (int i = 0; i < JRMCoreH.AlgnmntNms.length; i++) {
         if (JRMCoreH.AlgnmntNms[i].equalsIgnoreCase(s)) {
            return i;
         }
      }

      return 1;
   }

   public static double getMultFor(Entity f, Entity t) {
      String mt = f.getEntityData().func_74779_i("jrmcSpawnInitiatedCMT");
      String[] amt = mt.split("\\|\\|");

      for (int i = 0; i < amt.length; i++) {
         String[] aamt = amt[i].split("\\|");
         if (aamt.length > 1 && aamt[0].equalsIgnoreCase(EntityList.func_75621_b(t))) {
            return Double.parseDouble(aamt[2]);
         }
      }

      return 0.0;
   }

   public static String[] getMobTranNext(String cmt, EntityLivingBase e) {
      String es = EntityList.func_75621_b(e);
      String[] amt = cmt.split("\\|\\|");
      int inTRLst = -1;

      for (int i = 0; i < amt.length; i++) {
         String[] aamt = amt[i].split("\\|");
         if (aamt.length > 1 && aamt[0].equalsIgnoreCase(es)) {
            inTRLst = i;
            break;
         }
      }

      return cmt.length() > 2 && amt.length > inTRLst + 1 ? amt[inTRLst + 1].split("\\|") : null;
   }

   public static String[] getMobTranDat(String cmt, EntityLivingBase e) {
      String es = EntityList.func_75621_b(e);
      String[] amt = cmt.split("\\|\\|");
      int inTRLst = -1;

      for (int i = 0; i < amt.length; i++) {
         String[] aamt = amt[i].split("\\|");
         if (aamt.length > 1 && aamt[0].equalsIgnoreCase(es)) {
            inTRLst = i;
            break;
         }
      }

      return cmt.length() > 2 && inTRLst >= 0 && amt.length > inTRLst ? amt[inTRLst].split("\\|") : null;
   }

   public static void prog(EntityPlayer Player, String sid, String v, String rp, String cc, String ccv) {
      MinecraftServer server1 = FMLCommonHandler.instance().getMinecraftServerInstance();
      int gid = JRMCoreH.getInt(Player, "JRMCGID");
      if (gid != 0 && server1.func_71203_ab().field_72404_b != null && server1.func_71203_ab().field_72404_b.size() > 0) {
         for (Object n : server1.func_71203_ab().field_72404_b) {
            EntityPlayer en = (EntityPlayer)n;
            if (en != Player) {
               int egid = JRMCoreH.getInt(en, "JRMCGID");
               if (egid == gid) {
                  NBTTagCompound nbt = JRMCoreH.nbt(en, "pres");
                  String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
                  msdV = setSydaV(msdV, sid, v, rp, cc, ccv);
                  nbt.func_74778_a("JRMCmissionSyncVers", msdV);
               }
            }
         }
      }
   }

   private static boolean prog(String msd, String sid, int mid) {
      if (msd.length() > 3) {
         for (int i = 0; i < getSydaAmount(msd); i++) {
            String s = getMda_Series(msd, i);
            int m = getMda_Mission(msd, i);
            if (s.equals(sid) && mid == m) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean prog(EntityPlayer en, String sid, int mid) {
      NBTTagCompound nbt = JRMCoreH.nbt(en, "pres");
      String msd = nbt.func_74779_i("JRMCmissionSync");
      if (msd.length() > 3) {
         for (int i = 0; i < getSydaAmount(msd); i++) {
            String s = getMda_Series(msd, i);
            int m = getMda_Mission(msd, i);
            if (s.equals(sid) && mid == m) {
               return true;
            }
         }
      }

      return false;
   }

   public static void prog(EntityPlayer Player, JRMCoreMsnBundle M, JRMCoreMsn msn, String str, List<JRMCoreMsn> msnl, String sid, int pw, int rc, int cl) {
      MinecraftServer server1 = FMLCommonHandler.instance().getMinecraftServerInstance();
      int gid = JRMCoreH.getInt(Player, "JRMCGID");
      if (gid != 0 && server1.func_71203_ab().field_72404_b != null && server1.func_71203_ab().field_72404_b.size() > 0) {
         for (Object n : server1.func_71203_ab().field_72404_b) {
            EntityPlayer en = (EntityPlayer)n;
            if (en != Player) {
               int egid = JRMCoreH.getInt(en, "JRMCGID");
               if (egid == gid) {
                  NBTTagCompound nbt = JRMCoreH.nbt(en, "pres");
                  String msd = nbt.func_74779_i("JRMCmissionSync");
                  if (prog(msd, sid, msn.getId())) {
                     msd = setToNextMsn(msd, str, msnl, sid, pw, rc, cl);
                     nbt.func_74778_a("JRMCmissionSync", msd);
                     PD.sendTo(
                        new JRMCorePData2(
                           sid
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
                           new Gson().toJson(msn, JSN_TYPE_MSN)
                        ),
                        (EntityPlayerMP)en
                     );
                  }
               }
            }
         }
      }
   }

   public static ArrayList<EntityPlayer> prog(EntityPlayer m, boolean b) {
      MinecraftServer server1 = FMLCommonHandler.instance().getMinecraftServerInstance();
      ArrayList<EntityPlayer> ns = new ArrayList<>();
      int gid = JRMCoreH.getInt(m, "JRMCGID");
      if (gid != 0 && server1.func_71203_ab().field_72404_b != null && server1.func_71203_ab().field_72404_b.size() > 0) {
         for (Object n : server1.func_71203_ab().field_72404_b) {
            EntityPlayer en = (EntityPlayer)n;
            int egid = JRMCoreH.getInt(en, "JRMCGID");
            if (egid == gid && (b || en != m)) {
               ns.add(en);
            }
         }

         return ns;
      } else {
         if (b) {
            ns.add(m);
         }

         return ns;
      }
   }

   public static ArrayList<EntityPlayer> prog(EntityPlayer k, boolean b, String sid, int mid) {
      MinecraftServer server1 = FMLCommonHandler.instance().getMinecraftServerInstance();
      ArrayList<EntityPlayer> ns = new ArrayList<>();
      int gid = JRMCoreH.getInt(k, "JRMCGID");
      if (gid != 0 && server1.func_71203_ab().field_72404_b != null && server1.func_71203_ab().field_72404_b.size() > 0) {
         for (Object n : server1.func_71203_ab().field_72404_b) {
            EntityPlayer en = (EntityPlayer)n;
            int egid = JRMCoreH.getInt(en, "JRMCGID");
            if (egid == gid && (b || en != k) && prog(en, sid, mid)) {
               ns.add(en);
            }
         }

         return ns;
      } else {
         if (b) {
            ns.add(k);
         }

         return ns;
      }
   }

   public static void prog(EntityPlayer p, String sid, int mid, int sz, int dc, String ds) {
      for (EntityPlayer en : prog(p, false)) {
         NBTTagCompound nbt = JRMCoreH.nbt(en, "pres");
         String msd = nbt.func_74779_i("JRMCmissionSync");
         if (prog(msd, sid, mid)) {
            msd = setSyda(msd, sid, mid, sz, dc, ds);
            nbt.func_74778_a("JRMCmissionSync", msd);
         }
      }
   }

   public static String setToNextMsn(String msd, String str, List<JRMCoreMsn> msnl, String sid, int pw, int rc, int cl) {
      int nID = Integer.parseInt(str);
      boolean available = false;

      for (int n = 0; n < msnl.size(); n++) {
         JRMCoreMsn msn2 = msnl.get(n);
         if (msn2.getId() == nID) {
            available = true;
            break;
         }
      }

      if (!available) {
         nID = 0;
      }

      String[] ar = new String[]{"0"};

      for (int j1 = 0; j1 < msnl.size(); j1++) {
         JRMCoreMsn msn2 = msnl.get(j1);
         if (msn2.getId() == nID) {
            ArrayList<String> o2 = msn2.getObjectives(pw, rc, cl);
            int size2 = o2.size();
            ar = new String[size2];

            for (int i = 0; i < ar.length; i++) {
               ar[i] = "0";
            }
         }
      }

      return setSyda(msd, sid, nID, ar);
   }

   public static String getSydaV(String[] s, int i) {
      return s.length > 3 ? s[i] : "0";
   }

   public static boolean resetMsnBndl(
      boolean hasSyda,
      boolean reset,
      JRMCoreMsn msn,
      int msnToSendID,
      int pw,
      int rc,
      int cl,
      String msd,
      String msdV,
      String msdO,
      String msdVO,
      String seriesID,
      NBTTagCompound nbt,
      JRMCoreMsnBundle M,
      EntityPlayer p
   ) {
      if (hasSyda && reset && msn.getId() == msnToSendID) {
         ArrayList<String> o = msn.getObjectives(pw, rc, cl);
         int size = o.size();
         String[] ar = new String[]{"0"};
         String[] arx = new String[size];

         for (int i = 0; i < size; i++) {
            arx[i] = "0";
         }

         msd = setSyda(msd, seriesID, msnToSendID, arx);
         String[] sydaV = getMda(msdV, seriesID);
         msdV = setSydaV(msdV, seriesID, M.getVersion(), "0", getSydaV(sydaV, 3), "0");
         if (!msd.equals(msdO)) {
            nbt.func_74778_a("JRMCmissionSync", msd);
         }

         if (!msdV.equals(msdVO)) {
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
               new Gson().toJson(msn, JSN_TYPE_MSN)
            ),
            (EntityPlayerMP)p
         );
         return true;
      } else {
         return false;
      }
   }
}
