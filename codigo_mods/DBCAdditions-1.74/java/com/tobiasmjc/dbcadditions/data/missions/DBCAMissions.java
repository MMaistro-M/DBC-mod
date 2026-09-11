/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.Gson
 *  com.google.gson.stream.JsonReader
 */
package com.tobiasmjc.dbcadditions.data.missions;

import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.JRMCoreMsn;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

public class DBCAMissions {
    static HashMap<Integer, ArrayList<String>> DBSSHObjs;
    static HashMap<Integer, ArrayList<String>> DBSSHRwrds;
    static HashMap<Integer, ArrayList<String>> DBSSHObjs1;
    static HashMap<Integer, ArrayList<String>> DBSSHRwrds1;
    static HashMap<Integer, ArrayList<String>> DBSSHObjs2;
    static HashMap<Integer, ArrayList<String>> DBSSHRwrds2;
    static HashMap<Integer, String[]> DBSSHTITLES;
    static HashMap<Integer, String[]> DBSSHDESCRIPTIONS;
    static int allMissionsDBSBroly;

    public static void missionClickNext(ArrayList<String> l) {
        l.add("next");
    }

    public static void missionClickStart(ArrayList<String> l) {
        l.add("start");
    }

    public static void missionKill(ArrayList<String> l, String enemy, float damage, float health, String startLine, String endLine) {
        l.add("kill;N" + enemy + ";H" + (int)health + ";A" + (int)damage + ";S" + startLine + ";D" + endLine);
    }

    public static void missionBeInDimension(ArrayList<String> l, String dimension) {
        l.add("dim2;N" + dimension);
    }

    public static ArrayList<String> missionReset() {
        return Lists.newArrayList();
    }

    public static void missionRestart(ArrayList<String> l) {
        l.add("restart");
    }

    public static int missionRewardNothing(ArrayList<String> l, int i) {
        l.add("nothing;jinryuujrmcore.Next;" + ++i);
        return i;
    }

    public static int missionRewardTPLevelAligned2(ArrayList<String> l, int i, double multi) {
        l.add("tp!lvlalign!" + multi + "||align!+10;jinryuujrmcore.missionSys.Protect;" + (i + 1));
        l.add("tp!lvlalign!" + multi + "||align!0;jinryuujrmcore.missionSys.Myself;" + (i + 1));
        l.add("tp!lvlalign!" + multi + "||align!-10;jinryuujrmcore.missionSys.Evil;" + (i + 1));
        return i + 1;
    }

    public static int missionRewardTPLevelAligned(ArrayList<String> l, int i) {
        return DBCAMissions.missionRewardTPLevelAligned2(l, i, 10.0);
    }

    public static int missionRewardNothingRestart(ArrayList<String> l, int i) {
        l.add("nothing;jinryuujrmcore.Restart;" + ++i);
        return i;
    }

    public static void initDBSSH() {
        DBSSHObjs = new HashMap();
        DBSSHRwrds = new HashMap();
        DBSSHObjs1 = new HashMap();
        DBSSHRwrds1 = new HashMap();
        DBSSHObjs2 = new HashMap();
        DBSSHRwrds2 = new HashMap();
        DBSSHDESCRIPTIONS = new HashMap();
        DBSSHTITLES = new HashMap();
        ArrayList<String> l = Lists.newArrayList();
        int i = 0;
        DBCAMissions.missionClickNext(l);
        DBSSHTITLES.put(i, new String[]{"Dragon Ball Super: Super Hero Movie Saga"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Goku and Vegeta are training with Whis, and the earth remains peaceful. Until Dr. Hedo appears..."});
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardNothing(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBSSHTITLES.put(i, new String[]{"Sparring with Piccolo"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Piccolo wants to train with you!"});
        DBCAMissions.missionKill(l, "ninjinentities.Piccolo2", 50000.0f, 450000.0f, "Piccolo: This is going to be fun!", "Piccolo: That was a good sparring session.");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBSSHTITLES.put(i, new String[]{"Training with Piccolo and Gohan"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"You managed to convince Gohan to join the training session!"});
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBCAMissions.missionKill(l, "ninjinentities.Piccolo2", 60000.0f, 480000.0f, "Piccolo: Prepare yourself!", "Piccolo: I have to become stronger...");
        DBCAMissions.missionKill(l, "ninjinentities.gohanUltimate", 700000.0f, 500000.0f, "Gohan: I'm not going easy on you! Hehe.", "Gohan: Waah! I'm exhausted!");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBSSHTITLES.put(i, new String[]{"Red Ribborn is back"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"A new android confronts you!"});
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBCAMissions.missionKill(l, "ninjinentities.Gamma1", 90000.0f, 800000.0f, "Gamma 1: I'm a Super Hero!", "Gamma 1: You are pretty strong.");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBSSHTITLES.put(i, new String[]{"Piccolo's new power"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Piccolo asked Shenron to release his full potential."});
        DBCAMissions.missionKill(l, "ninjinentities.Piccolo3", 95000.0f, 855000.0f, "Piccolo: This is my new power!", "Piccolo: I have to become EVEN stronger!");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBSSHTITLES.put(i, new String[]{"Piccolo's new power"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Piccolo asked Shenron to release his full potential."});
        DBCAMissions.missionKill(l, "ninjinentities.Piccolo4", 105000.0f, 975000.0f, "Piccolo: Thank you Shenron. A little extra went a long way in this.", "Piccolo: I'm strong enough now!");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBSSHTITLES.put(i, new String[]{"Red Ribbon is back"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"You, Piccolo and Gohan go to the Red Ribbon basement to rescue Pan."});
        DBCAMissions.missionKill(l, "ninjinentities.Gamma1", 94000.0f, 825000.0f, "Gamma 1: I'm the Super Hero Gamma 1!", "Gamma 1: Oh no. This is bad...");
        DBCAMissions.missionKill(l, "ninjinentities.Gamma2", 96000.0f, 843000.0f, "Gamma 2: I'm the Super Hero Gamma 2!", "Gamma 2: We have to stop Cell Max now!");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionClickStart(l);
        DBCAMissions.missionBeInDimension(l, "Overworld");
        DBSSHTITLES.put(i, new String[]{"Red Ribbon is back"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Cell Max, the perfect android, has been born."});
        DBCAMissions.missionKill(l, "ninjinentities.cellmax", 150000.0f, 1250000.0f, "Cell Max: GRRAAAAAAAAAAAAAAHH!!!", "Cell Max: ...");
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardTPLevelAligned(l, i);
        DBSSHRwrds.put(i++, l);
        DBSSHTITLES.put(i, new String[]{"Red Ribbon is back"});
        DBSSHDESCRIPTIONS.put(i, new String[]{"Cell Max has been defeated!"});
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRestart(l);
        DBSSHObjs.put(i, l);
        l = DBCAMissions.missionReset();
        DBCAMissions.missionRewardNothingRestart(l, i);
        DBSSHRwrds.put(i++, l);
        DBSSHTITLES.put(i, new String[]{"Thanks for playing!"});
        DBSSHDESCRIPTIONS.put(i, new String[]{""});
        l = DBCAMissions.missionReset();
        allMissionsDBSBroly = i + 1;
    }

    public static JRMCoreMsnBundle genSuperHeroSaga() {
        DBCAMissions.initDBSSH();
        int missionsLength = allMissionsDBSBroly;
        JRMCoreMsnBundle mb = new JRMCoreMsnBundle();
        mb.setName("Dragon Block C Additions - DBS: Super Hero Movie");
        mb.setDesc("An alternate story for the DBC Additions mod based off on the Dragon Ball Super: Super Hero movie.");
        mb.setAuthor("enzp912");
        mb.setVersion("1.0");
        mb.setMods("DBC");
        mb.settings.repeat = "0";
        mb.settings.unlock = "";
        mb.settings.vars = "";
        ArrayList<JRMCoreMsn> ml = new ArrayList<JRMCoreMsn>();
        JRMCoreMsn m = new JRMCoreMsn();
        for (int i = 0; i < missionsLength; ++i) {
            m = new JRMCoreMsn();
            m.setId(i);
            m.setTranslated(true);
            m.setProps(DBCAMissions.al("default"));
            m.setAlign(DBCAMissions.al("neutral"));
            m.setTitle(DBCAMissions.al("DBC: Super Hero"));
            m.setSubtitle(DBCAMissions.al(DBSSHTITLES.get(i)));
            m.setDescription(DBCAMissions.al(DBSSHDESCRIPTIONS.get(i)));
            m.setObjectives(DBCAMissions.al(DBSSHObjs.get(i)));
            m.setRewards(DBCAMissions.al(DBSSHRwrds.get(i)));
            ml.add(m);
        }
        mb.setMissions(ml);
        return mb;
    }

    public static JRMCoreMsnBundle rd(File file) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader((Reader)new FileReader(file));
            JRMCoreMsnBundle data = (JRMCoreMsnBundle)gson.fromJson(reader, JRMCoreM.JSN_TYPE_MSNbndl);
            return data;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> al(ArrayList<String> ... s) {
        ArrayList l = Lists.newArrayList();
        for (int i = 0; i < s.length; ++i) {
            l.add(s[i]);
        }
        return l;
    }

    public static ArrayList<String> al(String ... s) {
        ArrayList l = Lists.newArrayList();
        for (int i = 0; i < s.length; ++i) {
            l.add(s[i]);
        }
        return l;
    }

    static {
        allMissionsDBSBroly = 0;
    }
}

