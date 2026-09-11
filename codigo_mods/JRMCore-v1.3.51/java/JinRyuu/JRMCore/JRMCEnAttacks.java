/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCEnAttacks {
    public static int maxChrgLmt = 200;
    public static int maxChrgcnt = 20;
    public static double motX = 0.5;
    public static double motY = 0.01;
    public static double motZ = 0.5;
    public static int time2 = 2;
    public static int time40 = 40;
    public static int time50 = 50;
    public static int time70 = 70;
    public static int time100 = 120;
    public static int time120 = 200;
    public static int BigBangCosts = 20;
    public static String BigBangHeart = "7,5";
    public static double BigBangDam = 15.0;
    public static float BigBangExpl = 5.0f;
    public static String BigBangExplSound = "jinryuudragonbc:DBC.expl";
    public static String BigBangAirSound = "jinryuudragonbc:DBC.hafire";
    public static String BigBangFired = "jinryuudragonbc:DBC2.bigbang_fire";
    public static String BigBangCharg = "jinryuudragonbc:DBC3.cbigbang";
    public static int BurningAttCosts = 15;
    public static String BurningAttHeart = "7,5";
    public static double BurningAttDam = 13.0;
    public static float BurningAttExpl = 3.0f;
    public static String BurningAttExplSound = "jinryuudragonbc:DBC.expl";
    public static String BurningAttAirSound = "jinryuudragonbc:DBC.hafire";
    public static String BurningAttFired = "jinryuudragonbc:DBC3.fburning";
    public static String BurningAttFired2 = "jinryuudragonbc:DBC3.ffburning";
    public static String BurningAttCharg = "jinryuudragonbc:DBC3.cburning";
    public static int BlastCosts = 3;
    public static String BlastHeart = "1";
    public static double BlastDam = 2.0;
    public static float BlastExpl = 0.0f;
    public static String BlastExplSound = "";
    public static String BlastAirSound = "jinryuudragonbc:DBC.hafire";
    public static String BlastFired = "jinryuudragonbc:DBC2.blast";
    public static String BlastCharg = "";
    public static int DeathBeamCosts = 8;
    public static String DeathBeamHeart = "5,5";
    public static double DeathBeamDam = 5.0;
    public static float DeathBeamExpl = 2.0f;
    public static String DeathBeamExplSound = "jinryuudragonbc:DBC.expl";
    public static String DeathBeamAirSound = "jinryuudragonbc:DBC.hafire";
    public static String DeathBeamFired = "jinryuudragonbc:DBC2.basicbeam_fire";
    public static String DeathBeamCharg = "";
    public static int DodonCosts = 5;
    public static String DodonHeart = "2";
    public static double DodonDam = 4.0;
    public static float DodonExpl = 0.0f;
    public static String DodonExplSound = "";
    public static String DodonAirSound = "";
    public static String DodonFired = "jinryuudragonbc:DBC2.kiball_release";
    public static String DodonCharg = "";
    public static int EnergyDiskCosts = 10;
    public static String EnergyDiskHeart = "4";
    public static double EnergyDiskDam = 8.0;
    public static float EnergyDiskExpl = 0.0f;
    public static String EnergyDiskExplSound = "";
    public static String EnergyDiskAirSound = "";
    public static String EnergyDiskFired = "jinryuudragonbc:DBC2.disc_fire";
    public static String EnergyDiskCharg = "jinryuudragonbc:DBC3.ckidisc";
    public static int FinalFlashCosts = 16;
    public static String FinalFlashHeart = "9";
    public static double FinalFlashDam = 9.0;
    public static float FinalFlashExpl = 7.0f;
    public static String FinalFlashExplSound = "jinryuudragonbc:DBC.expl";
    public static String FinalFlashAirSound = "jinryuudragonbc:DBC.hafire";
    public static String FinalFlashFired = "jinryuudragonbc:DBC3.ffinalflash";
    public static String FinalFlashCharg = "jinryuudragonbc:DBC2.finalflash_charge";
    public static int FingerLeserCosts = 6;
    public static String FingerLeserHeart = "2,5";
    public static double FingerLeserDam = 5.0;
    public static float FingerLeserExpl = 0.0f;
    public static String FingerLeserExplSound = "";
    public static String FingerLeserAirSound = "";
    public static String FingerLeserFired = "jinryuudragonbc:DBC3.fingerleser";
    public static String FingerLeserCharg = "";
    public static int GalicGunCosts = 9;
    public static String GalicGunHeart = "6";
    public static double GalicGunDam = 6.0;
    public static float GalicGunExpl = 5.0f;
    public static String GalicGunExplSound = "jinryuudragonbc:DBC.expl";
    public static String GalicGunAirSound = "jinryuudragonbc:DBC.hafire";
    public static String GalicGunFired = "jinryuudragonbc:DBC3.fgallitgun";
    public static String GalicGunCharg = "jinryuudragonbc:DBC3.cgallitgun";
    public static int KameHameCosts = 8;
    public static String KameHameHeart = "5";
    public static double KameHameDam = 5.0;
    public static float KameHameExpl = 4.0f;
    public static String KameHameExplSound = "jinryuudragonbc:DBC.expl";
    public static String KameHameAirSound = "jinryuudragonbc:DBC.hafire";
    public static String KameHameFired = "jinryuudragonbc:DBC.ha";
    public static String KameHameCharg = "jinryuudragonbc:DBC.hame";
    public static int KameHame10xCosts = 19;
    public static String KameHame10xHeart = "12,5";
    public static double KameHame10xDam = 10.0;
    public static float KameHame10xExpl = 10.0f;
    public static String KameHame10xExplSound = "jinryuudragonbc:DBC.expl";
    public static String KameHame10xAirSound = "jinryuudragonbc:DBC.hafire";
    public static String KameHame10xFired = "jinryuudragonbc:DBC.ha10x";
    public static String KameHame10xCharg = "jinryuudragonbc:DBC.hame";
    public static int MakankoCosts = 11;
    public static String MakankoHeart = "7,5";
    public static double MakankoDam = 7.0;
    public static float MakankoExpl = 4.0f;
    public static String MakankoExplSound = "jinryuudragonbc:DBC.expl";
    public static String MakankoAirSound = "jinryuudragonbc:DBC.hafire";
    public static String MakankoFired = "jinryuudragonbc:DBC3.fspecialbeamcannon";
    public static String MakankoCharg = "jinryuudragonbc:DBC3.cspecialbeamcannon";
    public static int MasenkoCosts = 9;
    public static String MasenkoHeart = "5";
    public static double MasenkoDam = 5.0;
    public static float MasenkoExpl = 2.0f;
    public static String MasenkoExplSound = "jinryuudragonbc:DBC.expl";
    public static String MasenkoAirSound = "jinryuudragonbc:DBC.hafire";
    public static String MasenkoFired = "jinryuudragonbc:DBC3.fmasenko";
    public static String MasenkoCharg = "jinryuudragonbc:DBC3.cmasenko";
    public static int PlanetDestCosts = 100;
    public static String PlanetDestHeart = "25";
    public static double PlanetDestDam = 50.0;
    public static float PlanetDestExpl = 17.0f;
    public static String PlanetDestExplSound = "jinryuudragonbc:DBC.expl";
    public static String PlanetDestAirSound = "";
    public static String PlanetDestFired = "jinryuudragonbc:DBC2.deathball_fire";
    public static String PlanetDestCharg = "jinryuudragonbc:DBC2.deathball_charge";
    public static int SpiritBombCosts = 80;
    public static String SpiritBombHeart = "25";
    public static double SpiritBombDam = 50.0;
    public static float SpiritBombExpl = 15.0f;
    public static String SpiritBombExplSound = "jinryuudragonbc:DBC.expl";
    public static String SpiritBombAirSound = "";
    public static String SpiritBombFired = "jinryuudragonbc:DBC3.fspiritbomb";
    public static String SpiritBombCharg = "jinryuudragonbc:DBC3.cspiritbomb";
    public static int PunchCosts = 10;
    public static double PunchDam = 15.0;
    public static float PunchExpl = 5.0f;
    public static String PunchExplSound = "jinryuudragonbc:DBC2.strongpunch";
    public static String PunchAirSound = "jinryuudragonbc:DBC2.hafire";
    public static String PunchFired = "jinryuudragonbc:DBC2.strongpunch";
    public static String PunchCharg = "";
    public double explosionX;
    public double explosionY;
    public double explosionZ;
    public float explosionSize;
    public List chunkPositionRecords;
    private float playerVelocityX;
    private float playerVelocityY;
    private float playerVelocityZ;

    public static void KASlct() {
        int selct = 1;
        if (JRMCoreH.KABigBang == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 2;
        if (JRMCoreH.KABlast == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 3;
        if (JRMCoreH.KABurningAtt == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 4;
        if (JRMCoreH.KADeathBeam == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 5;
        if (JRMCoreH.KADodon == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 6;
        if (JRMCoreH.KAEnergyDisk == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 7;
        if (JRMCoreH.KAFinalFlash == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 8;
        if (JRMCoreH.KAFingerLaser == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 9;
        if (JRMCoreH.KAGalicGun == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 10;
        if (JRMCoreH.KAKameHame == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 11;
        if (JRMCoreH.KAKameHame10x == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 12;
        if (JRMCoreH.KAMakanko == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 13;
        if (JRMCoreH.KAMasenko == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 14;
        if (JRMCoreH.KAPlanetDest == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 15;
        if (JRMCoreH.KASpiritBomb == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 16;
        if (JRMCoreH.KTKaioken == 1 && JRMCoreH.KASelected < selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
        selct = 0;
        if (JRMCoreH.KASelected > selct) {
            JRMCoreH.KASelected = selct;
            return;
        }
    }

    public static boolean KAkiEn(EntityPlayer thePlayer) {
        int explevel = JRMCoreH.kiAscPow;
        if (JRMCoreH.armTypSS1On(thePlayer)) {
            explevel *= 2;
        }
        if (JRMCoreH.armTypSS2On(thePlayer)) {
            explevel *= 3;
        }
        if (JRMCoreH.armTypSS3On(thePlayer)) {
            explevel *= 4;
        }
        int evil = JRMCoreH.dbcEvilness;
        double evl2 = (double)(100 - evil) * 0.01;
        double good2 = (double)evil * 0.01;
        double evl = evl2 + 0.2;
        double good = good2 + 0.2;
        double neu = 1.0 - (good2 - evl2 < 0.0 ? (good2 - evl2) * -1.0 : good2 - evl2) + 0.2;
        double ret = 0.0;
        int selct = JRMCoreH.KASelected;
        double cost = 0.0;
        int maxki = JRMCoreH.kiMax;
        if (selct == 1 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * neu * (double)BigBangCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 2 && JRMCoreH.kiAmount < ((int)(cost = (double)(explevel * BlastCosts)) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 3 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * good * (double)BurningAttCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 4 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * evl * (double)DeathBeamCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 5 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * neu * (double)DodonCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 6 && JRMCoreH.kiAmount < ((int)(cost = (double)(explevel * EnergyDiskCosts)) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 7 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * neu * (double)FinalFlashCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 8 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * evl * (double)FingerLeserCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 9 && JRMCoreH.kiAmount < ((int)(cost = (double)((int)((double)explevel * neu * (double)GalicGunCosts))) > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 10) {
            cost = (int)((double)explevel * good * (double)KameHameCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 11) {
            cost = (int)((double)explevel * good * (double)KameHame10xCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 12) {
            cost = (int)((double)explevel * neu * (double)MakankoCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 13) {
            cost = (int)((double)explevel * good * (double)MasenkoCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 14) {
            cost = (int)((double)explevel * evl * (double)PlanetDestCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 15) {
            cost = (int)((double)explevel * good * (double)SpiritBombCosts);
        }
        if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)((double)maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
        }
        if (selct == 16 && JRMCoreH.kiAmount < 1) {
            return false;
        }
        return selct != 0;
    }
}

