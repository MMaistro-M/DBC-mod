package JinRyuu.JRMCore;

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
   public static float BigBangExpl = 5.0F;
   public static String BigBangExplSound = "jinryuudragonbc:DBC.expl";
   public static String BigBangAirSound = "jinryuudragonbc:DBC.hafire";
   public static String BigBangFired = "jinryuudragonbc:DBC2.bigbang_fire";
   public static String BigBangCharg = "jinryuudragonbc:DBC3.cbigbang";
   public static int BurningAttCosts = 15;
   public static String BurningAttHeart = "7,5";
   public static double BurningAttDam = 13.0;
   public static float BurningAttExpl = 3.0F;
   public static String BurningAttExplSound = "jinryuudragonbc:DBC.expl";
   public static String BurningAttAirSound = "jinryuudragonbc:DBC.hafire";
   public static String BurningAttFired = "jinryuudragonbc:DBC3.fburning";
   public static String BurningAttFired2 = "jinryuudragonbc:DBC3.ffburning";
   public static String BurningAttCharg = "jinryuudragonbc:DBC3.cburning";
   public static int BlastCosts = 3;
   public static String BlastHeart = "1";
   public static double BlastDam = 2.0;
   public static float BlastExpl = 0.0F;
   public static String BlastExplSound = "";
   public static String BlastAirSound = "jinryuudragonbc:DBC.hafire";
   public static String BlastFired = "jinryuudragonbc:DBC2.blast";
   public static String BlastCharg = "";
   public static int DeathBeamCosts = 8;
   public static String DeathBeamHeart = "5,5";
   public static double DeathBeamDam = 5.0;
   public static float DeathBeamExpl = 2.0F;
   public static String DeathBeamExplSound = "jinryuudragonbc:DBC.expl";
   public static String DeathBeamAirSound = "jinryuudragonbc:DBC.hafire";
   public static String DeathBeamFired = "jinryuudragonbc:DBC2.basicbeam_fire";
   public static String DeathBeamCharg = "";
   public static int DodonCosts = 5;
   public static String DodonHeart = "2";
   public static double DodonDam = 4.0;
   public static float DodonExpl = 0.0F;
   public static String DodonExplSound = "";
   public static String DodonAirSound = "";
   public static String DodonFired = "jinryuudragonbc:DBC2.kiball_release";
   public static String DodonCharg = "";
   public static int EnergyDiskCosts = 10;
   public static String EnergyDiskHeart = "4";
   public static double EnergyDiskDam = 8.0;
   public static float EnergyDiskExpl = 0.0F;
   public static String EnergyDiskExplSound = "";
   public static String EnergyDiskAirSound = "";
   public static String EnergyDiskFired = "jinryuudragonbc:DBC2.disc_fire";
   public static String EnergyDiskCharg = "jinryuudragonbc:DBC3.ckidisc";
   public static int FinalFlashCosts = 16;
   public static String FinalFlashHeart = "9";
   public static double FinalFlashDam = 9.0;
   public static float FinalFlashExpl = 7.0F;
   public static String FinalFlashExplSound = "jinryuudragonbc:DBC.expl";
   public static String FinalFlashAirSound = "jinryuudragonbc:DBC.hafire";
   public static String FinalFlashFired = "jinryuudragonbc:DBC3.ffinalflash";
   public static String FinalFlashCharg = "jinryuudragonbc:DBC2.finalflash_charge";
   public static int FingerLeserCosts = 6;
   public static String FingerLeserHeart = "2,5";
   public static double FingerLeserDam = 5.0;
   public static float FingerLeserExpl = 0.0F;
   public static String FingerLeserExplSound = "";
   public static String FingerLeserAirSound = "";
   public static String FingerLeserFired = "jinryuudragonbc:DBC3.fingerleser";
   public static String FingerLeserCharg = "";
   public static int GalicGunCosts = 9;
   public static String GalicGunHeart = "6";
   public static double GalicGunDam = 6.0;
   public static float GalicGunExpl = 5.0F;
   public static String GalicGunExplSound = "jinryuudragonbc:DBC.expl";
   public static String GalicGunAirSound = "jinryuudragonbc:DBC.hafire";
   public static String GalicGunFired = "jinryuudragonbc:DBC3.fgallitgun";
   public static String GalicGunCharg = "jinryuudragonbc:DBC3.cgallitgun";
   public static int KameHameCosts = 8;
   public static String KameHameHeart = "5";
   public static double KameHameDam = 5.0;
   public static float KameHameExpl = 4.0F;
   public static String KameHameExplSound = "jinryuudragonbc:DBC.expl";
   public static String KameHameAirSound = "jinryuudragonbc:DBC.hafire";
   public static String KameHameFired = "jinryuudragonbc:DBC.ha";
   public static String KameHameCharg = "jinryuudragonbc:DBC.hame";
   public static int KameHame10xCosts = 19;
   public static String KameHame10xHeart = "12,5";
   public static double KameHame10xDam = 10.0;
   public static float KameHame10xExpl = 10.0F;
   public static String KameHame10xExplSound = "jinryuudragonbc:DBC.expl";
   public static String KameHame10xAirSound = "jinryuudragonbc:DBC.hafire";
   public static String KameHame10xFired = "jinryuudragonbc:DBC.ha10x";
   public static String KameHame10xCharg = "jinryuudragonbc:DBC.hame";
   public static int MakankoCosts = 11;
   public static String MakankoHeart = "7,5";
   public static double MakankoDam = 7.0;
   public static float MakankoExpl = 4.0F;
   public static String MakankoExplSound = "jinryuudragonbc:DBC.expl";
   public static String MakankoAirSound = "jinryuudragonbc:DBC.hafire";
   public static String MakankoFired = "jinryuudragonbc:DBC3.fspecialbeamcannon";
   public static String MakankoCharg = "jinryuudragonbc:DBC3.cspecialbeamcannon";
   public static int MasenkoCosts = 9;
   public static String MasenkoHeart = "5";
   public static double MasenkoDam = 5.0;
   public static float MasenkoExpl = 2.0F;
   public static String MasenkoExplSound = "jinryuudragonbc:DBC.expl";
   public static String MasenkoAirSound = "jinryuudragonbc:DBC.hafire";
   public static String MasenkoFired = "jinryuudragonbc:DBC3.fmasenko";
   public static String MasenkoCharg = "jinryuudragonbc:DBC3.cmasenko";
   public static int PlanetDestCosts = 100;
   public static String PlanetDestHeart = "25";
   public static double PlanetDestDam = 50.0;
   public static float PlanetDestExpl = 17.0F;
   public static String PlanetDestExplSound = "jinryuudragonbc:DBC.expl";
   public static String PlanetDestAirSound = "";
   public static String PlanetDestFired = "jinryuudragonbc:DBC2.deathball_fire";
   public static String PlanetDestCharg = "jinryuudragonbc:DBC2.deathball_charge";
   public static int SpiritBombCosts = 80;
   public static String SpiritBombHeart = "25";
   public static double SpiritBombDam = 50.0;
   public static float SpiritBombExpl = 15.0F;
   public static String SpiritBombExplSound = "jinryuudragonbc:DBC.expl";
   public static String SpiritBombAirSound = "";
   public static String SpiritBombFired = "jinryuudragonbc:DBC3.fspiritbomb";
   public static String SpiritBombCharg = "jinryuudragonbc:DBC3.cspiritbomb";
   public static int PunchCosts = 10;
   public static double PunchDam = 15.0;
   public static float PunchExpl = 5.0F;
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
      } else {
         int var1 = 2;
         if (JRMCoreH.KABlast == 1 && JRMCoreH.KASelected < var1) {
            JRMCoreH.KASelected = var1;
         } else {
            var1 = 3;
            if (JRMCoreH.KABurningAtt == 1 && JRMCoreH.KASelected < var1) {
               JRMCoreH.KASelected = var1;
            } else {
               var1 = 4;
               if (JRMCoreH.KADeathBeam == 1 && JRMCoreH.KASelected < var1) {
                  JRMCoreH.KASelected = var1;
               } else {
                  var1 = 5;
                  if (JRMCoreH.KADodon == 1 && JRMCoreH.KASelected < var1) {
                     JRMCoreH.KASelected = var1;
                  } else {
                     var1 = 6;
                     if (JRMCoreH.KAEnergyDisk == 1 && JRMCoreH.KASelected < var1) {
                        JRMCoreH.KASelected = var1;
                     } else {
                        var1 = 7;
                        if (JRMCoreH.KAFinalFlash == 1 && JRMCoreH.KASelected < var1) {
                           JRMCoreH.KASelected = var1;
                        } else {
                           var1 = 8;
                           if (JRMCoreH.KAFingerLaser == 1 && JRMCoreH.KASelected < var1) {
                              JRMCoreH.KASelected = var1;
                           } else {
                              var1 = 9;
                              if (JRMCoreH.KAGalicGun == 1 && JRMCoreH.KASelected < var1) {
                                 JRMCoreH.KASelected = var1;
                              } else {
                                 var1 = 10;
                                 if (JRMCoreH.KAKameHame == 1 && JRMCoreH.KASelected < var1) {
                                    JRMCoreH.KASelected = var1;
                                 } else {
                                    var1 = 11;
                                    if (JRMCoreH.KAKameHame10x == 1 && JRMCoreH.KASelected < var1) {
                                       JRMCoreH.KASelected = var1;
                                    } else {
                                       var1 = 12;
                                       if (JRMCoreH.KAMakanko == 1 && JRMCoreH.KASelected < var1) {
                                          JRMCoreH.KASelected = var1;
                                       } else {
                                          var1 = 13;
                                          if (JRMCoreH.KAMasenko == 1 && JRMCoreH.KASelected < var1) {
                                             JRMCoreH.KASelected = var1;
                                          } else {
                                             var1 = 14;
                                             if (JRMCoreH.KAPlanetDest == 1 && JRMCoreH.KASelected < var1) {
                                                JRMCoreH.KASelected = var1;
                                             } else {
                                                var1 = 15;
                                                if (JRMCoreH.KASpiritBomb == 1 && JRMCoreH.KASelected < var1) {
                                                   JRMCoreH.KASelected = var1;
                                                } else {
                                                   var1 = 16;
                                                   if (JRMCoreH.KTKaioken == 1 && JRMCoreH.KASelected < var1) {
                                                      JRMCoreH.KASelected = var1;
                                                   } else {
                                                      var1 = 0;
                                                      if (JRMCoreH.KASelected > var1) {
                                                         JRMCoreH.KASelected = var1;
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
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
      double evl2 = (100 - evil) * 0.01;
      double good2 = evil * 0.01;
      double evl = evl2 + 0.2;
      double good = good2 + 0.2;
      double neu = 1.0 - (good2 - evl2 < 0.0 ? (good2 - evl2) * -1.0 : good2 - evl2) + 0.2;
      double ret = 0.0;
      int selct = JRMCoreH.KASelected;
      double cost = 0.0;
      int maxki = JRMCoreH.kiMax;
      if (selct == 1) {
         cost = (int)(explevel * neu * BigBangCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 2) {
         cost = explevel * BlastCosts;
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 3) {
         cost = (int)(explevel * good * BurningAttCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 4) {
         cost = (int)(explevel * evl * DeathBeamCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 5) {
         cost = (int)(explevel * neu * DodonCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 6) {
         cost = explevel * EnergyDiskCosts;
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 7) {
         cost = (int)(explevel * neu * FinalFlashCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 8) {
         cost = (int)(explevel * evl * FingerLeserCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 9) {
         cost = (int)(explevel * neu * GalicGunCosts);
         if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
            return false;
         }
      }

      if (selct == 10) {
         cost = (int)(explevel * good * KameHameCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      }

      if (selct == 11) {
         cost = (int)(explevel * good * KameHame10xCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      }

      if (selct == 12) {
         cost = (int)(explevel * neu * MakankoCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      }

      if (selct == 13) {
         cost = (int)(explevel * good * MasenkoCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      }

      if (selct == 14) {
         cost = (int)(explevel * evl * PlanetDestCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      }

      if (selct == 15) {
         cost = (int)(explevel * good * SpiritBombCosts);
      }

      if (JRMCoreH.kiAmount < ((int)cost > maxki ? (int)(maxki * 0.9) : (int)cost) + JRMCoreH.minKi) {
         return false;
      } else {
         return selct == 16 && JRMCoreH.kiAmount < 1 ? false : selct != 0;
      }
   }
}
