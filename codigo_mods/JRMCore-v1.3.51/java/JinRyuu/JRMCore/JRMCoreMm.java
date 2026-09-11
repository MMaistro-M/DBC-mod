package JinRyuu.JRMCore;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;

public class JRMCoreMm {
   public static final int MISSION_TP_REWARD_ID_MULTI = 15;
   public static final int MISSION_TP_REWARD_LEVEL_MULTI = 10;
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
   public static final String MISSION_TalkTo_Series = "series";
   public static final String MISSION_TalkTo_translated = "translated";
   public static final String MISSION_CONDS_Transformations = "T";
   public static final String MISSION_CONDS_MsgSpawnSnd = "O";
   public static final String MISSION_CONDS_MsgDeathSnd = "U";
   public static final int MISSION_INFO_SeID = 0;
   public static final int MISSION_INFO_Name = 1;
   public static final int MISSION_INFO_Desc = 2;
   public static final int MISSION_INFO_Auth = 3;
   public static final int MISSION_INFO_Vers = 4;
   public static final int MISSION_INFO_Mods = 5;
   public static final int MISSION_INFO_Rept = 6;
   public static final int MISSION_INFO_Unlo = 7;
   public static final String MISSION_PROP_Default = "default";
   public static final String MISSION_PROP_RandomReward = "randrew";
   public static final String SERIES_DBC_MainID = "mainDBC";
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
   public static final String REWARD_TYPE_NOTHING = "nothing";
   public static final String REWARD_TYPE_Item = "item";
   public static final String REWARD_TYPE_TP = "tp";
   public static final String REWARD_TYPE_Alignment = "align";
   public static final String REWARD_TYPE_Command = "com";
   public static final String REWARD_TYPE_TP_FIX = "fix";
   public static final String REWARD_TYPE_TP_ALIGN = "align";
   public static final String REWARD_TYPE_TP_LVL = "lvl";
   public static final String REWARD_TYPE_TP_LVLALIGN = "lvlalign";
   static final float POWER_MULTI = 1.5F;
   static int allMsn = 0;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaObjs;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaRwrds;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaObjs1;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaRwrds1;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaObjs2;
   static HashMap<Integer, ArrayList<String>> DBCmainSagaRwrds2;
   static int allMissionsDBSBroly = 0;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyObjs;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyRwrds;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyObjs1;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyRwrds1;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyObjs2;
   static HashMap<Integer, ArrayList<String>> DBCDBSBrolyRwrds2;
   static int allMsnNC = 0;
   static HashMap<Integer, ArrayList<String>> NCmainSagaObjs;
   static HashMap<Integer, ArrayList<String>> NCmainSagaRwrds;
   static HashMap<Integer, String> NCmainSagaProps;
   static HashMap<Integer, String> NCmainSagaArcs;
   static HashMap<Integer, ArrayList<String>> NCmainSagaObjs1;
   static HashMap<Integer, ArrayList<String>> NCmainSagaRwrds1;
   static HashMap<Integer, ArrayList<String>> NCmainSagaObjs2;
   static HashMap<Integer, ArrayList<String>> NCmainSagaRwrds2;
   static final int DB_END = 47;

   public static ArrayList<String> missionReset() {
      return Lists.newArrayList();
   }

   public static void missionRestart(ArrayList<String> l) {
      l.add("restart");
   }

   public static void missionClickNext(ArrayList<String> l) {
      l.add("next");
   }

   public static void missionClickStart(ArrayList<String> l) {
      l.add("start");
   }

   public static void missionKill(ArrayList<String> l, String enemy, float damage, float health, String startLine, String endLine) {
      l.add("kill;N" + enemy + ";" + "H" + (int)health + ";" + "A" + (int)damage + ";" + "S" + startLine + ";" + "D" + endLine);
   }

   public static void missionKillMoreOfSame(ArrayList<String> l, String enemy, int count, float damage, float health) {
      l.add("killsame;N" + enemy + ";" + "M" + count + ";" + "H" + (int)damage + ";" + "A" + (int)health);
   }

   public static int missionRewardNothingRestart(ArrayList<String> l, int i) {
      l.add("nothing;jinryuujrmcore.Restart;" + ++i);
      return i;
   }

   public static int missionRewardNothingSkip(ArrayList<String> l, int i) {
      l.add("nothing;jinryuujrmcore.Skip;" + ++i);
      return i;
   }

   public static int missionRewardNothing(ArrayList<String> l, int i) {
      l.add("nothing;jinryuujrmcore.Next;" + ++i);
      return i;
   }

   public static int missionRewardTPLevelAligned(ArrayList<String> l, int i) {
      return missionRewardTPLevelAligned2(l, i, 10.0);
   }

   public static int missionRewardTPLevelAligned2(ArrayList<String> l, int i, double multi) {
      l.add("tp!lvlalign!" + multi + "||" + "align" + "!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1));
      l.add("tp!lvlalign!" + multi + "||" + "align" + "!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
      l.add("tp!lvlalign!" + multi + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
      return i + 1;
   }

   public static int missionRewardTP(ArrayList<String> l, int i) {
      return missionRewardTP2(l, i, 15);
   }

   public static int missionRewardTP2(ArrayList<String> l, int i, int multi) {
      l.add("tp!fix!" + multi * i + "||" + "align" + "!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1));
      l.add("tp!fix!" + multi * i + "||" + "align" + "!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
      l.add("tp!fix!" + multi * i + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
      return i + 1;
   }

   public static int missionRewardTP(ArrayList<String> l, int i, int tpReward) {
      l.add("tp!fix!" + tpReward + "||" + "align" + "!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1));
      l.add("tp!fix!" + tpReward + "||" + "align" + "!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
      l.add("tp!fix!" + tpReward + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
      return i + 1;
   }

   public static int missionRewardItem(ArrayList<String> l, int i, String item, int itemCount) {
      return missionRewardItem(l, i, item, itemCount, 15);
   }

   public static int missionRewardItem(ArrayList<String> l, int i, String item, int itemCount, int multi) {
      l.add(
         "item!" + item + "," + itemCount + "||" + "tp" + "!" + "fix" + "!" + multi * i + "||" + "align" + "!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1)
      );
      l.add("item!" + item + "," + itemCount + "||" + "tp" + "!" + "fix" + "!" + multi * i + "||" + "align" + "!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
      l.add("item!" + item + "," + itemCount + "||" + "tp" + "!" + "fix" + "!" + multi * i + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
      return i + 1;
   }

   public static int missionRewardItems(ArrayList<String> l, int i, String[] items, int[] itemCounts) {
      return missionRewardItems(l, i, items, itemCounts, 15);
   }

   public static int missionRewardItems(ArrayList<String> l, int i, String[] items, int[] itemCounts, int multi) {
      String result = "";
      int id = 0;

      for (String item : items) {
         result = result + "item!" + item + "," + itemCounts[id] + "||";
         id++;
      }

      l.add(result + "tp" + "!" + "fix" + "!" + 10 * i + "||" + "align" + "!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1));
      l.add(result + "tp" + "!" + "fix" + "!" + 10 * i + "||" + "align" + "!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
      l.add(result + "tp" + "!" + "fix" + "!" + 10 * i + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
      return i + 1;
   }

   public static int missionRewardItem(ArrayList<String> l, int i, String itemGood, String itemNeutral, String itemEvil, int itemCount) {
      return missionRewardItem(l, i, itemGood, itemNeutral, itemEvil, itemCount, 15);
   }

   public static int missionRewardItem(ArrayList<String> l, int i, String itemGood, String itemNeutral, String itemEvil, int itemCount, int multi) {
      l.add(
         "item!"
            + itemGood
            + ","
            + itemCount
            + "||"
            + "tp"
            + "!"
            + "fix"
            + "!"
            + multi * i
            + "||"
            + "align"
            + "!+10;jinryuujrmcore.missionSys.Protect;"
            + (i + 1)
      );
      l.add(
         "item!"
            + itemNeutral
            + ","
            + itemCount
            + "||"
            + "tp"
            + "!"
            + "fix"
            + "!"
            + multi * i
            + "||"
            + "align"
            + "!0;jinryuujrmcore.missionSys.Myself;"
            + (i + 1)
      );
      l.add(
         "item!" + itemEvil + "," + itemCount + "||" + "tp" + "!" + "fix" + "!" + multi * i + "||" + "align" + "!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1)
      );
      return i + 1;
   }

   public static void missionGoToDimension(ArrayList<String> l, String dimension) {
      l.add("dim;N" + dimension);
   }

   public static void missionBeInDimension(ArrayList<String> l, String dimension) {
      l.add("dim2;N" + dimension);
   }

   public static void missionBeInBiome(ArrayList<String> l, String biome) {
      l.add("biome2;N" + biome);
   }

   public static void missionGatherItem(ArrayList<String> l, String item, int count) {
      l.add("item;N" + item + ";" + "M" + count);
   }

   public static void missionTalkTo(ArrayList<String> l, String master, String text, String text2) {
      l.add("talk;N" + master + ";" + "G" + text + ";" + "B" + text2);
   }

   public static void init() {
      DBCmainSagaObjs = new HashMap<>();
      DBCmainSagaRwrds = new HashMap<>();
      DBCmainSagaObjs1 = new HashMap<>();
      DBCmainSagaRwrds1 = new HashMap<>();
      DBCmainSagaObjs2 = new HashMap<>();
      DBCmainSagaRwrds2 = new HashMap<>();
      ArrayList<String> l = Lists.newArrayList();
      int i = 0;
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      missionTalkTo(l, "jinryuudragonblockc.roshi", "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:KintounItem", "jinryuudragonblockc:KintounItem", "jinryuudragonblockc:KintounBlackItem", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Yamcha", 45.0F, 150.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBMai", 15.0F, 45.0F, "dbc.sagasdb." + i + ".3", "dbc.sagasdb." + i + ".d3");
      missionKill(l, "jinryuudragonblockc.DBShu", 7.5F, 45.0F, "dbc.sagasdb." + i + ".2", "dbc.sagasdb." + i + ".d2");
      missionKill(l, "jinryuudragonblockc.DBPilaf", 7.5F, 30.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBMaiMecha", 45.0F, 165.0F, "dbc.sagasdb." + i + ".3", "dbc.sagasdb." + i + ".d3");
      missionKill(l, "jinryuudragonblockc.DBShuMecha", 45.0F, 150.0F, "dbc.sagasdb." + i + ".2", "dbc.sagasdb." + i + ".d2");
      missionKill(l, "jinryuudragonblockc.DBPilafMecha", 30.0F, 120.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBPilafMechaCombined", 60.0F, 225.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItems(
         l,
         i,
         new String[]{
            "jinryuudragonblockc:ItemDragonBlock",
            "jinryuudragonblockc:GiFighterTorso1",
            "jinryuudragonblockc:GiFighterLeg1",
            "jinryuudragonblockc:GiFighterBoots1"
         },
         new int[]{1, 1, 1, 1}
      );
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Bacterian", 22.5F, 150.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Yamcha2", 75.0F, 255.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.JackieChun", 105.0F, 270.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.JackieChun2", 135.0F, 300.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.ColonelSilver", 45.0F, 225.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.MajorMetallitron", 75.0F, 300.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.NinjaMurasaki", 45.0F, 300.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemKatana", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Android8", 75.0F, 300.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Buyon", 135.0F, 450.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.GeneralWhite", 45.0F, 300.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemDragonBlock", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.GeneralBlue", 165.0F, 525.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.MercenaryTao", 225.0F, 675.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      int count = 2;
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldier", 2, 22.5F, 120.0F);
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldier2", 2, 30.0F, 120.0F);
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldier3", 2, 60.0F, 120.0F);
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldierB", 2, 22.5F, 120.0F);
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldierB2", 2, 30.0F, 120.0F);
      missionKillMoreOfSame(l, "jinryuudragonblockc.RedRibbonSoldierB3", 2, 60.0F, 120.0F);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.OfficerBlack3", 255.0F, 720.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemDragonBlock", 2);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.FangsTheVampire", 165.0F, 525.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.SeeThroughTheInvisibleMan", 165.0F, 525.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.BandagesTheMummy", 225.0F, 675.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.SpikeTheDevil", 255.0F, 720.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.GrandpaGohan2", 270.0F, 825.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemPP", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Yamcha3", 255.0F, 525.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Chiaotzu", 75.0F, 225.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Krillin", 300.0F, 675.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.TienShinhan", 375.0F, 1050.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Tambourine", 420.0F, 1110.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Cymbal", 120.0F, 450.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Tambourine", 435.0F, 1170.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemDragonBlock", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.KingPiccolo", 450.0F, 1260.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Drum", 405.0F, 1200.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.KingPiccolo2", 495.0F, 1500.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemDragonBlock", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      missionTalkTo(l, "jinryuudragonblockc.kami", "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.MercenaryTao2", 420.0F, 1125.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.TienShinhan3", 525.0F, 1800.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Piccolo", 600.0F, 2250.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Piccolo2", 750.0F, 2400.0F, "dbc.sagasdb." + i + ".1", "dbc.sagasdb." + i + ".d");
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCmainSagaObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("killsame;Njinryuudragonblockc.Saibaiman;M6;H1500;A300");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Raditz;H3000;A900;Sdbc.itemSaiySagaRad.line1;Ddbc.itemSaiySagaRad.d.line1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome2;NDirty Stony");
      l.add("item;Njinryuudragonblockc:ItemDinoMeatCooked;M10");
      l.add("killsame;Njinryuudragonblockc.Saibaiman;M12;H1500;A300");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome2;NDirty Stony");
      l.add("kill;Njinryuudragonblockc.Nappa;H3750;A975;Sdbc.itemSaiySagaVeg.line1;Ddbc.itemSaiySagaVeg.d.line2");
      l.add("kill;Njinryuudragonblockc.Vegeta;H4500;A1050;Sdbc.itemSaiySagaVeg.line2;Ddbc.itemSaiySagaVeg.d.line1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:SpacePod01Item", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim;NNamek");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier1;M3;H2250;A450");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier2;M3;H2250;A450");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier3;M3;H2250;A450");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add("kill;Njinryuudragonblockc.Kiwi;H4800;A1095");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add("kill;Njinryuudragonblockc.Dodoria;H5250;A1125");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemNamekDragonBlock", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add("kill;Njinryuudragonblockc.Zarbon;H5700;A1200");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemDragonRadar", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add("kill;Njinryuudragonblockc.Guldo;H2250;A900;Sdbc.itemFreeSagaGin.4.line1");
      l.add("kill;Njinryuudragonblockc.Recoome;H7200;A1500;Sdbc.itemFreeSagaGin.1.line1;Ddbc.itemFreeSagaGin.d.1.line1");
      l.add("kill;Njinryuudragonblockc.Barta;H6300;A1275;Sdbc.itemFreeSagaGin.2.line1");
      l.add("kill;Njinryuudragonblockc.Jeice;H6300;A1350;Sdbc.itemFreeSagaGin.3.line1");
      l.add("kill;Njinryuudragonblockc.Ginyu;H7800;A1800;Sdbc.itemFreeSagaGin.5.line1;Ddbc.itemFreeSagaGin.d.5.line1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemNamekDragonBlock", 2);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNamek");
      l.add(
         "kill;Njinryuudragonblockc.Freeza1;H9000;A2250;Tjinryuudragonblockc.Freeza2|300|1.1||jinryuudragonblockc.Freeza3|300|1.1||jinryuudragonblockc.Freeza4|300|1.1||jinryuudragonblockc.Freeza5|300|1.1||jinryuudragonblockc.Freeza6|600|1.2"
      );
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemNamekDragonBlock", 2);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier1;M3;H2700;A525");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier2;M3;H2700;A525");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier3;M3;H2700;A525");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.MechaFreeza;H15000;A4500");
      l.add("kill;Njinryuudragonblockc.KingCold;H12000;A3000");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("skip");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingSkip(l, i);
      DBCmainSagaRwrds.put(i, l);
      l = missionReset();
      l.add("next");
      l.add("state;NSS");
      DBCmainSagaObjs1.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds1.put(i++, l);
      l = missionReset();
      l.add("next");
      l.add("talk;Njinryuudragonblockc.kami;Gdbc.MainSaga.17.m;Bdbc.MainSaga.17.b");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome;NDirty Stony");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cyborg19;H18000;A4950");
      l.add("kill;Njinryuudragonblockc.Cyborg20;H19500;A5250");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim;NTimeChamber");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cyborg18;H21750;A5700");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cell1;H24000;A6300;Tjinryuudragonblockc.Cell2|400|1.1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("biome;NDirty Stony");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cyborg16;H27000;A6900");
      l.add("kill;Njinryuudragonblockc.Cyborg17;H25500;A6450");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cell2;H27000;A7200;Tjinryuudragonblockc.Cell3|300|1.1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("skip");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingSkip(l, i);
      DBCmainSagaRwrds.put(i, l);
      l = missionReset();
      l.add("next");
      l.add("state;NSS2");
      DBCmainSagaObjs1.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds1.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("killsame;Njinryuudragonblockc.CellJr;M7;H10500;A2250;");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cell3;H33000;A7350;Tjinryuudragonblockc.Cell4|400|1.2");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("next");
      l.add("talk;Njinryuudragonblockc.masterbabidi;Gdbc.MainSaga." + (i - 47) + ".m;" + "B" + "dbc.MainSaga." + (i - 47) + ".b");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Puipui;H22500;A1500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Yakon;H27000;A4500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Darbura;H27000;A5250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardItem(l, i, "jinryuudragonblockc:ItemEvilSpear", 1);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuFat;H39000;A7500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("skip");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingSkip(l, i);
      DBCmainSagaRwrds.put(i, l);
      l = missionReset();
      l.add("start");
      l.add("state;NSS3");
      DBCmainSagaObjs1.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds1.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuFat;H48000;A7950;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuSuper;H51000;A8250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuFusion;H57000;A9000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuPiccolo;H52500;A8250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuUltimate;H63000;A9750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BuuKid;H67500;A11250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Beerus;H75000;A12000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("skip");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingSkip(l, i);
      DBCmainSagaRwrds.put(i, l);
      l = missionReset();
      l.add("start");
      l.add("state;NSSGod");
      DBCmainSagaObjs1.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds1.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Beerus;H78000;A12450;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Whis;H82500;A12750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier1;M10;H7500;A2700");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier2;M10;H7500;A2700");
      l.add("killsame;Njinryuudragonblockc.FreezaSoldier3;M10;H7500;A2700");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Shisami;H18000;A4500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Tagoma;H45000;A9000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Freeza6;H87000;A13500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("skip");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingSkip(l, i);
      DBCmainSagaRwrds.put(i, l);
      l = missionReset();
      l.add("start");
      l.add("state;NSSB");
      DBCmainSagaObjs1.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds1.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.FreezaGolden;H93000;A14250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Botamo;H33000;A1500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Frost1;H30000;A7500;Tjinryuudragonblockc.Frost3|150|1.1||jinryuudragonblockc.Frost4|150|1.1");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Magetta;H97500;A13500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Cabba;H30000;A9000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.CabbaSSJ;H42000;A10500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Hit;H105000;A14250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.BeerusMonaka;H112500;A14700;Tjinryuudragonblockc.BeerusMonaka2|3000|1.2||jinryuudragonblockc.BeerusMonaka3|3000|1.2");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.VegetaCopy;H115500;A15000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.VegetaCopyBlue;H120000;A18000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("biome;NPlains");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.GokuBlack;H121500;A18750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Zamasu;H45000;A6750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.GokuBlack;H123000;A19200;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.GokuBlackRose;H126000;A19500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Zamasu;H45000;A6750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      l.add("kill;Njinryuudragonblockc.GokuBlackRose;H126000;A19500;Sdbc.MainSaga." + (i - 47) + ".2;" + "D" + "dbc.MainSaga." + (i - 47) + ".2d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Zamasu_fused;H129000;A22500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Zamasu_fused2;H130500;A23250;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Zamasu_fused3;H132000;A24000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Hit;H105000;A25500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Basil;H97500;A21000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Lavender;H99000;A22500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Bergamo;H112500;A27000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Toppo;H135000;A30000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Gohan_enemy2_1;H120000;A33000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NOverworld");
      l.add("kill;Njinryuudragonblockc.Krillin;H67500;A24000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Basil;H97500;A21000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      l.add("kill;Njinryuudragonblockc.Lavender;H99000;A22500;Sdbc.MainSaga." + (i - 47) + ".2;" + "D" + "dbc.MainSaga." + (i - 47) + ".2d");
      l.add("kill;Njinryuudragonblockc.Bergamo;H112500;A27000;Sdbc.MainSaga." + (i - 47) + ".3;" + "D" + "dbc.MainSaga." + (i - 47) + ".3d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Caulifla;H117000;A27750;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      l.add("kill;Njinryuudragonblockc.Kale;H97500;A25500;Sdbc.MainSaga." + (i - 47) + ".2;" + "D" + "dbc.MainSaga." + (i - 47) + ".2d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Caulifla2;H127500;A36000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Kale2;H142500;A45000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Brianne;H97500;A30000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Dyspo;H165000;A48000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Toppo;H210000;A51000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Jiren;H240000;A54000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Kefla;H270000;A57000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Kefla2;H330000;A60000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Toppo2;H360000;A64500;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Jiren;H405000;A69000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      l.add("kill;Njinryuudragonblockc.Jiren2;H480000;A75000;Sdbc.MainSaga." + (i - 47) + ".1;" + "D" + "dbc.MainSaga." + (i - 47) + ".d");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardTP(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("start");
      l.add("dim2;NNull Realm");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothing(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      l.add("restart");
      DBCmainSagaObjs.put(i, l);
      l = Lists.newArrayList();
      missionRewardNothingRestart(l, i);
      DBCmainSagaRwrds.put(i++, l);
      l = missionReset();
      allMsn = i + 1;
   }

   public static void initDBCDBSBroly() {
      DBCDBSBrolyObjs = new HashMap<>();
      DBCDBSBrolyRwrds = new HashMap<>();
      DBCDBSBrolyObjs1 = new HashMap<>();
      DBCDBSBrolyRwrds1 = new HashMap<>();
      DBCDBSBrolyObjs2 = new HashMap<>();
      DBCDBSBrolyRwrds2 = new HashMap<>();
      ArrayList<String> l = Lists.newArrayList();
      int i = 0;
      missionClickNext(l);
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionKill(l, "jinryuudragonblockc.Vegeta_enemy", 28500.0F, 285000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.Goku_enemy", 30000.0F, 300000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBSBroly4", 15000.0F, 225000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBSBrolyBuff", 45000.0F, 375000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBSBrolyBuffSSJ", 90000.0F, 825000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickStart(l);
      missionBeInDimension(l, "Overworld");
      missionKill(l, "jinryuudragonblockc.DBSBrolyLegendary", 105000.0F, 975000.0F, "dbc.sagadbsbroly." + i + ".1", "dbc.sagadbsbroly." + i + ".d");
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardTPLevelAligned(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionClickNext(l);
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardNothing(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      missionRestart(l);
      DBCDBSBrolyObjs.put(i, l);
      l = missionReset();
      missionRewardNothingRestart(l, i);
      DBCDBSBrolyRwrds.put(i++, l);
      l = missionReset();
      allMissionsDBSBroly = i + 1;
   }

   public static void initNC() {
      NCmainSagaObjs = new HashMap<>();
      NCmainSagaRwrds = new HashMap<>();
      NCmainSagaProps = new HashMap<>();
      NCmainSagaArcs = new HashMap<>();
      NCmainSagaObjs1 = new HashMap<>();
      NCmainSagaRwrds1 = new HashMap<>();
      NCmainSagaObjs2 = new HashMap<>();
      NCmainSagaRwrds2 = new HashMap<>();
      String arc = "nc.sagasystem.about";
      int id = 0;
      objs(id, "start");
      rews(id, rew("jinryuujrmcore.Next", id + 1, r("nothing")));
      othrs(id, arc, "default");
      arc = "nc.sagasystem.prologue";
      int var5 = 1;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilIruka", "H400", "A100"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 2;
      objs(var5, "start", obj("biome2", "NRoofed Forest"), obj("kill", "Njinryuunarutoc.EntityNCEvilMizuki1", "H600", "A120"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 3;
      objs(var5, "next");
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("item", JRMCoreH.tjnc + ":ItemScrollOfSeals,1")));
      othrs(var5, arc, "default");
      var5 = 4;
      objs(var5, "next");
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      var5 = 5;
      objs(var5, "start", obj("killsame", "NOzelot", "M6", "H100", "A20"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 6;
      objs(var5, "next", obj("item", "Nwheat_seeds", "M48"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 7;
      objs(var5, "next", obj("item", "Nyellow_flower", "M10"), obj("item", "Nred_flower", "M12"), obj("item", "Nred_flower::1", "M4"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 8;
      objs(var5, "start", obj("biome2", "NRoofed Forest"));
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      var5 = 9;
      objs(
         var5,
         "start",
         obj("biome2", "NRoofed Forest"),
         obj("kill", "Njinryuunarutoc.EntityNCEvilZabuza1", "H1200", "A240"),
         obj("kill", "Njinryuunarutoc.EntityNCEvilHaku1", "H1000", "A200")
      );
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 10;
      objs(var5, "start", obj("biome2", "NRoofed Forest"), obj("kill", "Njinryuunarutoc.EntityNCEvilZabuza2", "H1600", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 11;
      objs(var5, "next");
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      arc = "nc.sagasystem.chuuninexams";
      var5 = 12;
      objs(var5, "next");
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      var5 = 13;
      objs(var5, "start", obj("biome2", "NNinja Forest"), obj("kill", "Njinryuunarutoc.EntityNCEvilShiroe", "H1800", "A320"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 14;
      objs(var5, "next", obj("biome2", "NPlains"));
      rews(
         var5,
         rew("jinryuujrmcore.Continue", 14, r("nothing")),
         rew("emty", 15, r("nothing")),
         rew("emty", 16, r("nothing")),
         rew("emty", 17, r("nothing")),
         rew("emty", 18, r("nothing")),
         rew("emty", 19, r("nothing")),
         rew("emty", 20, r("nothing")),
         rew("emty", 21, r("nothing")),
         rew("emty", 22, r("nothing")),
         rew("emty", 23, r("nothing")),
         rew("emty", 24, r("nothing")),
         rew("emty", 25, r("nothing")),
         rew("emty", 26, r("nothing")),
         rew("emty", 27, r("nothing"))
      );
      othrs(var5, arc, "randrew");
      var5 = 15;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilSasuke1", "H2400", "A350"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 16;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilSakura1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 17;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilIno1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 18;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilShika1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 19;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilChoji1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 20;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilHinata1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 21;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilKiba1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 22;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilShino1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 23;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilNeji1", "H2500", "A360"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 24;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilTenten1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 25;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilLee1", "H2500", "A360"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 26;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilTemari1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 27;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilKankuro1", "H2000", "A300"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", 28, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", 28, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", 28, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 28;
      objs(var5, "next", obj("biome2", "NPlains"));
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      var5 = 29;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilNaruto1", "H2200", "A280"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 30;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilNaruto2", "H3000", "A400"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 31;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilGaara1", "H3000", "A500"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 32;
      objs(var5, "start", obj("biome2", "NPlains"), obj("kill", "Njinryuunarutoc.EntityNCEvilShukaku", "H5000", "A1000"));
      rews(
         var5,
         rew("jinryuujrmcore.missionSys.Protect", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "+10")),
         rew("jinryuujrmcore.missionSys.Myself", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "0")),
         rew("jinryuujrmcore.missionSys.Evil", var5 + 1, r("tp", "fix", "" + 10 * var5), r("align", "-10"))
      );
      othrs(var5, arc, "default");
      var5 = 33;
      objs(var5, "next");
      rews(var5, rew("jinryuujrmcore.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      arc = "nc.sagasystem.credits";
      var5 = 34;
      objs(var5, "restart");
      rews(var5, rew("jinryuujrmcore.missionSys.Next", var5 + 1, r("nothing")));
      othrs(var5, arc, "default");
      allMsnNC = var5 + 1;
   }

   public static String obj(String... arg) {
      int k = 4;
      int m = 6;
      k = m + k - k;
      String s = "";

      for (int i = 0; i < arg.length; i++) {
         s = s + ";" + arg[i];
      }

      return s.substring(1);
   }

   public static void objs(int id, String... o) {
      ArrayList<String> l = Lists.newArrayList();

      for (int i = 0; i < o.length; i++) {
         l.add(o[i]);
         NCmainSagaObjs.put(id, l);
      }
   }

   public static void othrs(int id, String a, String p) {
      NCmainSagaArcs.put(id, a);
      NCmainSagaProps.put(id, p);
   }

   public static void rews(int id, String... o) {
      ArrayList<String> l = Lists.newArrayList();

      for (int i = 0; i < o.length; i++) {
         l.add(o[i]);
         NCmainSagaRwrds.put(id, l);
      }
   }

   public static String rew(String buttonName, int nextMissionID, String... reward) {
      String s = "";

      for (int i = 0; i < reward.length; i++) {
         s = s + "||" + reward[i];
      }

      return (s.length() > 2 ? s.substring(2) : s) + ";" + buttonName + ";" + nextMissionID;
   }

   public static String r(String... r) {
      if (r.length == 3 && r[0].equalsIgnoreCase("tp")) {
         return r[0] + "!" + r[1] + "!" + r[2];
      } else {
         return r.length == 1 && r[0].equalsIgnoreCase("nothing") ? r[0] : r[0] + "!" + r[1];
      }
   }

   public static String sagaTitle(int i) {
      if (i <= 5) {
         return "dbc.sagasystem.emperorpilaf";
      } else if (i <= 10) {
         return "dbc.sagasystem.dbtournament";
      } else if (i <= 21) {
         return "dbc.sagasystem.redribbon";
      } else if (i <= 27) {
         return "dbc.sagasystem.fortunetellerbaba";
      } else if (i <= 32) {
         return "dbc.sagasystem.tienshinhan";
      } else if (i <= 40) {
         return "dbc.sagasystem.kingpiccolo";
      } else if (i <= 47) {
         return "dbc.sagasystem.piccolojr";
      } else if (i <= 52) {
         return "dbc.sagasystem.saiyan";
      } else if (i <= 59) {
         return "dbc.sagasystem.freeza";
      } else if (i <= 77) {
         return "dbc.sagasystem.android";
      } else if (i <= 91) {
         return "dbc.sagasystem.buu";
      } else if (i <= 95) {
         return "dbc.sagasystem.beerus";
      } else if (i <= 102) {
         return "dbc.sagasystem.gfreeza";
      } else if (i <= 110) {
         return "dbc.sagasystem.universe6";
      } else if (i <= 113) {
         return "dbc.sagasystem.cvegeta";
      } else if (i <= 128) {
         return "dbc.sagasystem.dbsftrunks";
      } else {
         return i <= 151 ? "dbc.sagasystem.dbsuniversesurvival" : "dbc.sagasystem.credits";
      }
   }

   public static ArrayList<String> al(String... s) {
      ArrayList<String> l = Lists.newArrayList();

      for (int i = 0; i < s.length; i++) {
         l.add(s[i]);
      }

      return l;
   }

   public static ArrayList<ArrayList<String>> al(ArrayList<String>... s) {
      ArrayList<ArrayList<String>> l = Lists.newArrayList();

      for (int i = 0; i < s.length; i++) {
         l.add(s[i]);
      }

      return l;
   }

   public static JRMCoreMsnBundle msnGen() {
      init();
      int DBCmainSagaLength = allMsn;
      JRMCoreMsnBundle mb = new JRMCoreMsnBundle();
      mb.setName("Dragon Block C Main Saga");
      mb.setDesc("An alternate story for the DBC mod based off on the Dragon Ball series.");
      mb.setAuthor("JinRyuu, Ben");
      mb.setVersion("1.4");
      mb.setMods("DBC");
      mb.settings.repeat = "0";
      mb.settings.unlock = "";
      mb.settings.vars = "";
      ArrayList<JRMCoreMsn> ml = new ArrayList<>();
      new JRMCoreMsn();

      for (int i = 0; i < DBCmainSagaLength; i++) {
         JRMCoreMsn m = new JRMCoreMsn();
         m.setId(i);
         m.setTranslated(true);
         ArrayList<String> l = Lists.newArrayList();
         String key = i > 47 ? "dbc.sagasystem." : "dbc.sagasdb.";
         int keyID = i > 47 ? i - 47 : i;
         if (i != 63 && i != 74 && i != 83 && i != 94 && i != 101) {
            m.setProps(al("default"));
            m.setAlign(al("neutral"));
            m.setTitle(al(sagaTitle(i)));
            m.setSubtitle(al(key + keyID + ".title"));
            m.setDescription(al(key + keyID + ".desc"));
            m.setObjectives(al(DBCmainSagaObjs.get(i)));
            m.setRewards(al(DBCmainSagaRwrds.get(i)));
         } else {
            m.setProps(al("default", "saiyan"));
            m.setAlign(al("neutral", "neutral"));
            m.setTitle(al(sagaTitle(i), sagaTitle(i)));
            m.setSubtitle(al(key + keyID + ".title", key + keyID + ".title"));
            m.setDescription(al(key + keyID + ".desc", key + keyID + ".desc"));
            m.setObjectives(al(DBCmainSagaObjs.get(i), DBCmainSagaObjs1.get(i)));
            m.setRewards(al(DBCmainSagaRwrds.get(i), DBCmainSagaRwrds1.get(i)));
         }

         ml.add(m);
      }

      mb.setMissions(ml);
      return mb;
   }

   public static JRMCoreMsnBundle msnGenNC() {
      initNC();
      int NCmainSagaLength = allMsnNC;
      JRMCoreMsnBundle mb = new JRMCoreMsnBundle();
      mb.setName("Naruto C Main Saga");
      mb.setDesc("An alternate story for the Naruto C mod based off on the Naruto series.");
      mb.setAuthor("JinRyuu, Ben");
      mb.setVersion("0.5");
      mb.setMods("NC");
      mb.settings.repeat = "0";
      mb.settings.unlock = "";
      mb.settings.vars = "";
      ArrayList<JRMCoreMsn> ml = new ArrayList<>();
      new JRMCoreMsn();

      for (int i = 0; i < NCmainSagaLength; i++) {
         JRMCoreMsn m = new JRMCoreMsn();
         m.setId(i);
         m.setTranslated(true);
         ArrayList<String> l = Lists.newArrayList();
         m.setProps(al(NCmainSagaProps.get(i)));
         m.setAlign(al("neutral"));
         m.setTitle(al(NCmainSagaArcs.get(i)));
         m.setSubtitle(al("nc.sagasystem." + i + ".title"));
         m.setDescription(al("nc.sagasystem." + i + ".desc"));
         m.setObjectives(al(NCmainSagaObjs.get(i)));
         m.setRewards(al(NCmainSagaRwrds.get(i)));
         ml.add(m);
      }

      mb.setMissions(ml);
      return mb;
   }

   public static JRMCoreMsnBundle missionSideDBSBroly() {
      initDBCDBSBroly();
      int missionsLength = allMissionsDBSBroly;
      JRMCoreMsnBundle mb = new JRMCoreMsnBundle();
      mb.setName("Dragon Block C - DBS Broly Movie");
      mb.setDesc("An alternate story for the DBC mod based off on the Dragon Ball Super Broly movie.");
      mb.setAuthor("Ben");
      mb.setVersion("1.0");
      mb.setMods("DBC");
      mb.settings.repeat = "0";
      mb.settings.unlock = "";
      mb.settings.vars = "";
      ArrayList<JRMCoreMsn> ml = new ArrayList<>();
      new JRMCoreMsn();

      for (int i = 0; i < missionsLength; i++) {
         JRMCoreMsn m = new JRMCoreMsn();
         m.setId(i);
         m.setTranslated(true);
         m.setProps(al("default"));
         m.setAlign(al("neutral"));
         m.setTitle(al("dbc.sagadbsbroly.dbsbroly"));
         m.setSubtitle(al("dbc.sagadbsbroly." + i + ".title"));
         m.setDescription(al("dbc.sagadbsbroly." + i + ".desc"));
         m.setObjectives(al(DBCDBSBrolyObjs.get(i)));
         m.setRewards(al(DBCDBSBrolyRwrds.get(i)));
         ml.add(m);
      }

      mb.setMissions(ml);
      return mb;
   }
}
