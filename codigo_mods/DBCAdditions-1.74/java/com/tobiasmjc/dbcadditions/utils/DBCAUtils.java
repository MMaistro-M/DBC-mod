/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.LanguageManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package com.tobiasmjc.dbcadditions.utils;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class DBCAUtils {
    public static boolean isBaseDBC(EntityPlayer player) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            JGPlayerMP jgPlayer = new JGPlayerMP(player);
            jgPlayer.connectBaseNBT();
            byte state = jgPlayer.getState();
            byte state2 = jgPlayer.getState2();
            String sE = jgPlayer.getStatusEffects();
            boolean v = jgPlayer.hasStatusEffect(17, sE);
            boolean l = jgPlayer.hasStatusEffect(19, sE);
            boolean gd = jgPlayer.hasStatusEffect(20, sE);
            boolean ultra_instinct_color = false;
            if (JRMCoreH.DBC() && l && JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
                byte id = JGConfigUltraInstinct.CONFIG_UI_LEVELS < state2 ? JGConfigUltraInstinct.CONFIG_UI_LEVELS : state2;
                int ultra_instinct_level = JRMCoreH.state2UltraInstinct(false, id);
                ultra_instinct_color = JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[ultra_instinct_level];
            }
            return !ultra_instinct_color && !gd && !v && (long)state == 0L;
        }
        int pl = 0;
        byte state = 0;
        byte state2 = 0;
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!player.func_70005_c_().equalsIgnoreCase(JRMCoreH.plyrs[i])) continue;
            state = JRMCoreH.data2 == null ? (byte)0 : Byte.parseByte(JRMCoreH.data2[i].split(";")[0]);
            state2 = JRMCoreH.data2 == null ? (byte)0 : Byte.parseByte(JRMCoreH.data2[i].split(";")[1]);
            pl = i;
        }
        boolean v = JRMCoreH.StusEfctsClient(17, pl);
        boolean l = JRMCoreH.StusEfctsClient(19, pl);
        boolean gd = JRMCoreH.StusEfctsClient(20, pl);
        boolean ultra_instinct_color = false;
        if (JRMCoreH.DBC() && l && JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
            byte id = JGConfigUltraInstinct.CONFIG_UI_LEVELS < state2 ? JGConfigUltraInstinct.CONFIG_UI_LEVELS : state2;
            int ultra_instinct_level = JRMCoreH.state2UltraInstinct(false, id);
            ultra_instinct_color = JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[ultra_instinct_level];
        }
        return !v && !gd && !ultra_instinct_color && state == 0;
    }

    public static boolean modifyMindOrTPS(int race, int lvl) {
        if (JRMCoreH.isRaceSaiyan(race)) {
            return lvl >= 8 && lvl <= 9;
        }
        if (JRMCoreH.isRaceArcosian(race)) {
            return lvl >= 7 && lvl <= 9;
        }
        if (JRMCoreH.isRaceHuman(race)) {
            return lvl >= 6 && lvl <= 9;
        }
        if (JRMCoreH.isRaceMajin(race)) {
            return lvl >= 6 && lvl <= 9;
        }
        return lvl >= 6 && lvl <= 9;
    }

    public static int getMindCost(int race, int lvl) {
        if (JRMCoreH.isRaceSaiyan(race)) {
            return DBCAConfig.SaiyanMindCosts[lvl - 8];
        }
        if (JRMCoreH.isRaceArcosian(race)) {
            return DBCAConfig.ArcosianMindCosts[lvl - 7];
        }
        if (JRMCoreH.isRaceHuman(race)) {
            return DBCAConfig.HumanMindCosts[lvl - 6];
        }
        if (JRMCoreH.isRaceMajin(race)) {
            return DBCAConfig.MajinMindCosts[lvl - 6];
        }
        return DBCAConfig.NamekianMindCosts[lvl - 6];
    }

    public static int getTPCost(int race, int lvl) {
        if (JRMCoreH.isRaceSaiyan(race)) {
            return DBCAConfig.SaiyanTPCosts[lvl - 8];
        }
        if (JRMCoreH.isRaceArcosian(race)) {
            return DBCAConfig.ArcosianTPCosts[lvl - 7];
        }
        if (JRMCoreH.isRaceHuman(race)) {
            return DBCAConfig.HumanTPCosts[lvl - 6];
        }
        if (JRMCoreH.isRaceMajin(race)) {
            return DBCAConfig.MajinTPCosts[lvl - 6];
        }
        return DBCAConfig.NamekianTPCosts[lvl - 6];
    }

    public static String translateCustomForm(String formName) {
        Minecraft mc = Minecraft.func_71410_x();
        LanguageManager languageManager = mc.func_135016_M();
        return languageManager.func_135041_c().func_135034_a();
    }

    public static String translate(String text) {
        return StatCollector.func_74838_a((String)("dbcadditions." + text));
    }
}

