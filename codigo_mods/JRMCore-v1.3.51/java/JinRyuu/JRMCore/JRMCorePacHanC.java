package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.client.notification.JGNotification;
import JinRyuu.JRMCore.client.notification.JGNotificationGUI;
import JinRyuu.JRMCore.client.notification.JGNotificationHandlerC;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityJRMCexpl;
import JinRyuu.JRMCore.entity.ExplosionJRMC;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameAirBoxing;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameConcentration;
import JinRyuu.JRMCore.server.config.core.JGConfigSkills;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCAAiDifficulty;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePacHanC {
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
      this.origin = origin;
      if (p != null && this.origin != null && p.field_71093_bK == this.origin.field_71093_bK) {
         this.handleExplosion();
      }
   }

   public void handleExplosion() {
      ExplosionJRMC var2 = new ExplosionJRMC(
         JRMCoreClient.mc.field_71441_e,
         (Entity)null,
         this.explosionX,
         this.explosionY,
         this.explosionZ,
         this.explosionSize,
         this.expGriOff,
         this.expDam,
         this.origin,
         this.type
      );
      var2.field_77281_g = this.chunkPositionRecords;
      var2.func_77279_a(false);
      JRMCoreClient.mc.field_71439_g.field_70159_w = JRMCoreClient.mc.field_71439_g.field_70159_w + this.playerVelocityX;
      JRMCoreClient.mc.field_71439_g.field_70181_x = JRMCoreClient.mc.field_71439_g.field_70181_x + this.playerVelocityY;
      JRMCoreClient.mc.field_71439_g.field_70179_y = JRMCoreClient.mc.field_71439_g.field_70179_y + this.playerVelocityZ;
      EntityJRMCexpl aura = new EntityJRMCexpl(JRMCoreClient.mc.field_71441_e, this.type);
      if (aura != null) {
         aura.func_70012_b(this.explosionX, this.explosionY, this.explosionZ, 0.0F, 0.0F);
         aura.explsiz = this.explosionSize;
         JRMCoreClient.mc.field_71441_e.func_72838_d(aura);
      }
   }

   public void handleQuadI(byte b1, int b2, int b3, int b4) {
   }

   public void handleQuad(int b1, int b2, int b3, int b4, EntityPlayer p) {
      if (b1 == 10) {
         if (b2 == 1 && b3 == 0) {
            JRMCoreH.trngTPlmt = b4;
         } else if (b2 == 2 && b3 == 1) {
            JRMCoreH.trngTPlmt2 = b4;
         }
      }
   }

   public void handleData(int dataID, String d, EntityPlayer p) {
      if (dataID == 80) {
         String o = !d.isEmpty() && !d.equalsIgnoreCase("error") ? d : "0";
         int i = Integer.parseInt(o);
         JRMCoreH.ServerPoints = i;
      }

      if (dataID == 1) {
         if (d == "::") {
            JRMCoreH.plyrsArnd = null;
         } else {
            JRMCoreH.plyrsArnd = d.toString().replaceAll("::", "").split(":");
         }
      }

      if (dataID >= -1 && dataID <= 40) {
         if (dataID != 32) {
            JRMCoreH.rdc(d.toString().replaceAll("::", "").split(":"), dataID);
         } else if (d.startsWith("::")) {
            JRMCoreH.dat32 = null;
            String[] data = d.toString().substring(2).split("::");
            String[] segmentsS = data[0].split("/");
            int segmentID = Integer.parseInt(segmentsS[0]);
            int segments = Integer.parseInt(segmentsS[1]);
            int players = Integer.parseInt(segmentsS[2]);
            int startID = Integer.parseInt(segmentsS[3]);
            if (segmentID == 0) {
               JRMCoreH.dat32Segmented = new String[players];
            }

            String[] dataFinal = data[1].replaceAll("::", "").split(":");

            for (int i = 0; i < dataFinal.length; i++) {
               JRMCoreH.dat32Segmented[startID + i] = dataFinal[i];
            }

            if (segmentID == segments - 1) {
               JRMCoreH.dat32 = JRMCoreH.dat32Segmented;
            }
         } else {
            JRMCoreH.rdc(d.toString().replaceAll("::", "").split(":"), dataID);
         }
      }

      if (dataID == -1) {
         JRMCoreH.plyrs = d.replaceAll("::", "").split(":");
      }

      if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreClient.mc.field_71439_g != null) {
         if (dataID == 1) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.data1[pl].split(";");
                  JRMCoreH.Race = Byte.parseByte(s[0]);
                  JRMCoreH.dns = s[1];
                  JRMCoreH.Pwrtyp = Byte.parseByte(s[2]);
                  JRMCoreH.Class = Byte.parseByte(s[3]);
                  JRMCoreH.Accepted = Byte.parseByte(s[4]);
                  ExtendedPlayer props = ExtendedPlayer.get(JRMCoreClient.mc.field_71439_g);
                  JRMCoreH.dnsH = props.getHairCode();
                  break;
               }
            }
         }

         if (dataID == 2) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.data2[pl].split(";");
                  JRMCoreH.State = Byte.parseByte(s[0]);
                  JRMCoreH.State2 = Byte.parseByte(s[1]);
                  break;
               }
            }
         }

         if (dataID == 7) {
            String s = JRMCoreH.data7[0];
            if (JRMCoreH.Pwrtyp == 3 && JRMCoreH.Accepted == 1) {
               JRMCoreH.sao_col = Integer.parseInt(s);
            } else {
               String[] s3 = s.contains(";;") ? s.toString().split(";;") : null;
               if (s3 != null) {
                  for (int i = 0; i < s3.length; i++) {
                     String[] s2 = s3[i].contains(";") ? s3[i].toString().split(";") : null;
                     switch (i) {
                        case 0:
                           JRMCoreH.tech1 = s2;
                           break;
                        case 1:
                           JRMCoreH.tech2 = s2;
                           break;
                        case 2:
                           JRMCoreH.tech3 = s2;
                           break;
                        case 3:
                           JRMCoreH.tech4 = s2;
                     }
                  }
               } else {
                  JRMCoreH.tech4 = null;
                  JRMCoreH.tech3 = null;
                  JRMCoreH.tech2 = null;
                  JRMCoreH.tech1 = null;
               }
            }
         }

         if (dataID == 15) {
            String s = JRMCoreH.dat15[0];
            if (JRMCoreH.Pwrtyp == 3 && JRMCoreH.Accepted == 1) {
               JRMCoreH.sao_exp = Integer.parseInt(s);
            }
         }

         if (dataID == 6) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_()) && JRMCoreH.data6.length >= JRMCoreH.plyrs.length) {
                  String[] s = JRMCoreH.data6[pl].split(";");
                  JRMCoreH.PlyrSkillX = s[1];
                  JRMCoreH.PlyrSkillY = s[2];
                  JRMCoreH.PlyrSkillZ = s[3];
                  String[] PlyrSkills = s[0].split(",");
                  JRMCoreH.PlyrSkills = PlyrSkills;
                  String[] si = s[4].split(",");
                  int[] i = new int[si.length];

                  for (int a = 0; a < si.length; a++) {
                     i[a] = si[a].length() > 0 && !si[a].equals(" ") ? Integer.parseInt(si[a]) : -1;
                  }

                  JRMCoreH.techPM = i;
                  break;
               }
            }
         }

         if (dataID == 8) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String s = JRMCoreH.data8[pl];
                  JRMCoreH.curBody = Integer.parseInt(s);
                  break;
               }
            }
         }

         if (dataID == 9) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String s = JRMCoreH.data9[pl];
                  JRMCoreH.curEnergy = Integer.parseInt(s);
                  break;
               }
            }
         }

         if (dataID == 10) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.dat10[pl].split(";");
                  JRMCoreH.curRelease = Byte.parseByte(s[0]);
                  JRMCoreH.curStamina = Integer.parseInt(s[1]);
                  break;
               }
            }
         }

         if (dataID == 4) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.data4[pl].split(";");
                  JRMCoreH.TransSaiCurRg = Byte.parseByte(s[0]);
                  JRMCoreH.cura = Integer.parseInt(s[1]);
                  break;
               }
            }
         }

         if (dataID == 5) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.data5[pl].split(";");
                  JRMCoreH.align = Byte.parseByte(s[0]);
                  break;
               }
            }
         }

         if (dataID == 11) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.data1[pl].split(";");
                  int pwr = Byte.parseByte(s[2]);
                  int acc = Byte.parseByte(s[4]);
                  if (pwr == 3 && acc == 1) {
                     s = JRMCoreH.dat11[pl].split(";");
                     JRMCoreH.sao_level = Integer.parseInt(s[0]);
                     JRMCoreH.sao_ap = Integer.parseInt(s[1]);
                     break;
                  }

                  String st = JRMCoreH.dat11[pl];
                  JRMCoreH.curTP = Integer.parseInt(st);
                  break;
               }
            }
         }

         if (dataID == 12) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String s1 = JRMCoreH.dat12[pl];
                  JRMCoreH.curExp = Integer.parseInt(s1);
                  break;
               }
            }
         }

         if (dataID == 14) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.dat14[pl].split(",");
                  int[] PlyrAttrbts = new int[JRMCoreH.PlyrAttrbts.length];

                  for (int i = 0; i < PlyrAttrbts.length; i++) {
                     PlyrAttrbts[i] = Integer.parseInt(s[i]);
                  }

                  JRMCoreH.PlyrAttrbts = PlyrAttrbts;
                  byte pwr = JRMCoreH.Pwrtyp;
                  byte rce = JRMCoreH.Race;
                  byte cls = JRMCoreH.Class;
                  JRMCoreH.maxBody = JRMCoreH.stat(p, 2, pwr, 2, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0F);
                  JRMCoreH.maxEnergy = JRMCoreH.stat(p, 5, pwr, 5, JRMCoreH.PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(pwr));
                  JRMCoreH.maxStamina = JRMCoreH.stat(p, 2, pwr, 3, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0F);
                  break;
               }
            }
         }

         if (dataID == 18) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String s = JRMCoreH.dat18[pl];
                  String[] a = s.split(";");
                  JRMCoreH.Dffclty = Byte.parseByte(a[0]);
                  JRMCoreH.PtchVc = Byte.parseByte(a[1]);
                  JRMCoreH.FznDC = a[2];
                  break;
               }
            }
         }

         if (dataID == 19) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String[] s = JRMCoreH.dat19[pl].split(";");
                  JRMCoreH.TlMd = Byte.parseByte(s[0]);
                  break;
               }
            }
         }

         if (dataID == 20) {
            String[] s = JRMCoreH.dat20[0].split(";");
            JRMCoreH.GTrnngCB = Integer.parseInt(s[0]);
            if (s.length > 1) {
               JRMCoreH.PlyrSettings = s[1];
            }

            JRMCoreH.GravZone = Float.parseFloat(s[2]);
            JRMCoreH.WeightOn = Float.parseFloat(s[3]);
            if (s.length > 7) {
               JRMCoreH.s4ft = Integer.parseInt(s[7]);
            }

            if (s.length > 8) {
               JRMCoreH.pnp = Integer.parseInt(s[8]);
            }

            if (s.length > 9) {
               JRMCoreH.ko = Integer.parseInt(s[9]);
            }

            JRMCoreH.kob = JRMCoreH.ko > 0;
            JRMCoreH.pnh = JRMCoreH.pnp > 0;
         }

         if (dataID == 21) {
            JRMCoreH.MSDV = JRMCoreH.dat21[0];
         }

         if (dataID == 22) {
            JRMCoreH.MSD = JRMCoreH.dat22[0];
         }

         if (dataID == 23) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
               if (JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) {
                  String s = JRMCoreH.dat23[pl];
                  JRMCoreH.GID = Integer.parseInt(s);
                  break;
               }
            }

            int[] j = new int[JRMCoreH.dat23.length];

            for (int i = 0; i < JRMCoreH.dat23.length; i++) {
               j[i] = Integer.parseInt(JRMCoreH.dat23[i]);
            }

            JRMCoreH.GIDs = j;
            JRMCoreH.GMN = 0;
            if (JRMCoreH.GID > 0) {
               for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                  if (JRMCoreH.GIDs[pl] == JRMCoreH.GID) {
                     JRMCoreH.GMN++;
                  }
               }
            }
         }

         if (dataID == 24) {
            String[] s = JRMCoreH.dat24[0].split(";");
            JRMCoreH.GLID = s[0];
            JRMCoreH.GIDi = s[1];
         }

         if (dataID == 25) {
            String s = JRMCoreH.dat25[0];
            JRMCoreH.GIDi = s;
         }

         if (JRMCoreH.JFC()) {
            if (dataID == 26) {
               String[] j = new String[JRMCoreH.dat26.length];

               for (int i = 0; i < JRMCoreH.dat26.length; i++) {
                  j[i] = JRMCoreH.dat26[i];
               }

               FamilyCH.famNams = j;
            }

            if (dataID == 27) {
               String s = JRMCoreH.dat27[0];
               s = s.replaceAll("\\+", ":");
               String[] s1 = new String[]{s};
               String[] s2 = s.contains(";") ? s.split(";") : s1;
               FamilyCH.famMem = s2;
            }

            if (dataID == 28) {
               String s = JRMCoreH.dat28[0];
               String[] s2 = s.contains(";") ? s.toString().split(";") : null;
               if (s2 != null) {
                  String[] s3 = s2[0].length() < 2 ? null : s2[0].split(",");
                  FamilyCH.FamP = s3 != null ? s3[1] : "";
                  FamilyCH.FamID = s2[0].length() < 2 ? 0 : 1;
                  FamilyCH.prop = s2.length >= 2 ? s2[1] : "";
                  FamilyCH.adop = s2.length >= 3 ? s2[2] : "";
               }
            }

            if (dataID == 29) {
               String s = JRMCoreH.dat29[0];
               JRMCoreH.proc = s;
            }

            if (dataID == 30) {
               String[] k = new String[JRMCoreH.dat30.length];

               for (int i = 0; i < JRMCoreH.dat30.length; i++) {
                  k[i] = JRMCoreH.dat30[i];
               }

               JRMCoreH.preg = k;
            }
         }

         if (dataID == 31 && JRMCoreConfig.JRMCABonusOn) {
            JRMCoreH.bonusAttributes = d;
         }
      }
   }

   public void handleData2(String c, String d, EntityPlayer p) {
      String[] r = c.split(";");
      ArrayList<String> a = Lists.newArrayList();

      for (int i = 0; i < r.length; i++) {
         a.add(r[i]);
      }

      JRMCoreM.missionsC.put(r[0], (JRMCoreMsn)new Gson().fromJson(d, JRMCoreM.JSN_TYPE_MSN));
      JRMCoreM.missionsCD.put(r[0], a);
   }

   public void handleTri(ByteBuf buffer) {
      boolean dPnlty = false;
      int maxTrnExp = 0;
      boolean plntVegeta = false;
      boolean flyAnyLvl = false;
      boolean expGriOff = false;
      boolean DeathSystemOff = false;
      boolean DBSpawnEnabled = false;
      String DBSpawnTime = "";
      boolean SagaSystemOn = false;
      boolean SagaSysSpawnPods = false;
      boolean NPCSpawnCheck = false;
      boolean BuildingSpawnCheck = false;
      int buildingSpawnAreaSize = 0;
      int pgut = 0;
      int pt = 0;
      int SklMedCat = 0;
      float SklMedRate = 0.0F;
      int senzuCool = 0;
      float Reinc = 0.0F;
      boolean GodForm = false;
      boolean FreeRev = false;
      int TechExpNeed = 0;
      int TechCostMod = 0;
      String ncCSklsLvlO = "";
      String ncSklsLvlO = "";
      String TransGtsDmgO = "";
      boolean TPGainOn = true;
      float TPlimitIncreasesWithPlayerLevel = 0.5F;
      float TPMultiplier = 0.01F;
      int TPDailyLimit = 100;
      float ComboTimer = 3.0F;
      boolean ConstantClickOn = true;
      int RandomMovementSpeed = 1;
      boolean TPGainOn2 = true;
      float TPlimitIncreasesWithPlayerLevel2 = 0.5F;
      float TPMultiplier2 = 0.01F;
      int TPDailyLimit2 = 100;
      int StartLife = 40;
      float[] KeySpawnSpeed = new float[4];
      float[] KeySpeed = new float[4];
      int[] KeyLifeTaken = new int[4];
      int count = 0;
      int[][] KeyTypeIDs = new int[4][];
      int StatPasDef = 20;
      int mjn = 10;
      int lgnd = 10;
      String lgndb = "";
      double atcm = 1.6;
      int AttributeUpgradeCost_StartMinus = 140;
      int AttributeUpgradeCost_Min = 16;
      float[] AttributeUpgradeCost_AttributeMulti = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
      String s1 = "";
      String s2 = "";
      String s3 = "";
      boolean dat5711 = false;
      String mods = ByteBufUtils.readUTF8String(buffer);
      HashMap<String, Boolean> dataH = new HashMap<>();
      JRMCoreH.modsC = (HashMap<String, Boolean>)new Gson().fromJson(mods, dataH.getClass());
      JRMCoreComTickH.tna3fu = buffer.readBoolean();
      if (JRMCoreH.DBC()) {
         maxTrnExp = buffer.readInt();
         plntVegeta = buffer.readBoolean();
         flyAnyLvl = buffer.readBoolean();
         DeathSystemOff = buffer.readBoolean();
         DBSpawnEnabled = buffer.readBoolean();
         DBSpawnTime = ByteBufUtils.readUTF8String(buffer);
         SagaSystemOn = buffer.readBoolean();
         SagaSysSpawnPods = buffer.readBoolean();
         senzuCool = buffer.readInt();
         Reinc = buffer.readFloat();
         GodForm = buffer.readBoolean();
         FreeRev = buffer.readBoolean();
         TechExpNeed = buffer.readInt();
         TechCostMod = buffer.readInt();
      }

      if (JRMCoreH.JYC()) {
         pgut = buffer.readInt();
      }

      if (JRMCoreH.JFC()) {
         pt = buffer.readInt();
      }

      if (JRMCoreH.NC()) {
         ncCSklsLvlO = ByteBufUtils.readUTF8String(buffer);
         ncSklsLvlO = ByteBufUtils.readUTF8String(buffer);
         TransGtsDmgO = ByteBufUtils.readUTF8String(buffer);
      }

      String vlblRSklsLvlO = ByteBufUtils.readUTF8String(buffer);
      String vlblSklsLvlO = ByteBufUtils.readUTF8String(buffer);
      String TransKaiDmgO = ByteBufUtils.readUTF8String(buffer);
      String TransKaiDrainOLevel = ByteBufUtils.readUTF8String(buffer);
      String TransKaiDrainORace = ByteBufUtils.readUTF8String(buffer);
      String TransMngDmgO = ByteBufUtils.readUTF8String(buffer);
      String TransKaiNmsO = ByteBufUtils.readUTF8String(buffer);
      String TransSaiStBnPO = ByteBufUtils.readUTF8String(buffer);
      String TransHalfSaiStBnPO = ByteBufUtils.readUTF8String(buffer);
      String TransFrStBnPO = ByteBufUtils.readUTF8String(buffer);
      String TransHmStBnPO = ByteBufUtils.readUTF8String(buffer);
      String TransNaStBnPO = ByteBufUtils.readUTF8String(buffer);
      String TransMaStBnPO = ByteBufUtils.readUTF8String(buffer);
      String vlblRSklsMRO = ByteBufUtils.readUTF8String(buffer);
      String vlblSklsMRO = ByteBufUtils.readUTF8String(buffer);
      String ncCSklsMRO = ByteBufUtils.readUTF8String(buffer);
      String ncSklsMRO = ByteBufUtils.readUTF8String(buffer);
      boolean OverAtrLimitO = buffer.readBoolean();
      String MysticDamMulti = ByteBufUtils.readUTF8String(buffer);
      String ArcoPP = ByteBufUtils.readUTF8String(buffer);
      String ArcoPP2 = ByteBufUtils.readUTF8String(buffer);
      String ArcoPP3 = ByteBufUtils.readUTF8String(buffer);
      String ArcoPP4 = ByteBufUtils.readUTF8String(buffer);
      String ArcoPP5 = ByteBufUtils.readUTF8String(buffer);
      String AttrBonusPerRacialSkill = ByteBufUtils.readUTF8String(buffer);
      int ArcoPP6 = buffer.readInt();
      String tpGainRate = ByteBufUtils.readUTF8String(buffer);
      String tpGain = ByteBufUtils.readUTF8String(buffer);
      expGriOff = buffer.readBoolean();
      SklMedCat = buffer.readInt();
      SklMedRate = buffer.readFloat();
      boolean SklMedStop = buffer.readBoolean();
      boolean regen = buffer.readBoolean();
      boolean release = buffer.readBoolean();
      int tpgn = buffer.readInt();
      int attrMx = buffer.readInt();
      String regenRate = ByteBufUtils.readUTF8String(buffer);
      String hRegenRate = ByteBufUtils.readUTF8String(buffer);
      boolean sizes = buffer.readBoolean();
      String ssurl = ByteBufUtils.readUTF8String(buffer);
      String ssurl2 = ByteBufUtils.readUTF8String(buffer);
      String ssc = ByteBufUtils.readUTF8String(buffer);
      boolean sfzns = buffer.readBoolean();
      NPCSpawnCheck = buffer.readBoolean();
      BuildingSpawnCheck = buffer.readBoolean();
      buildingSpawnAreaSize = buffer.readInt();
      TPGainOn = buffer.readBoolean();
      TPlimitIncreasesWithPlayerLevel = buffer.readFloat();
      TPMultiplier = buffer.readFloat();
      TPDailyLimit = buffer.readInt();
      ComboTimer = buffer.readFloat();
      ConstantClickOn = buffer.readBoolean();
      RandomMovementSpeed = buffer.readInt();
      TPGainOn2 = buffer.readBoolean();
      TPlimitIncreasesWithPlayerLevel2 = buffer.readFloat();
      TPMultiplier2 = buffer.readFloat();
      TPDailyLimit2 = buffer.readInt();
      StartLife = buffer.readInt();

      for (int i = 0; i < 4; i++) {
         KeySpawnSpeed[i] = buffer.readFloat();
         KeySpeed[i] = buffer.readFloat();
         KeyLifeTaken[i] = buffer.readInt();
         count = buffer.readInt();
         KeyTypeIDs[i] = new int[count];

         for (int j = 0; j < count; j++) {
            KeyTypeIDs[i][j] = buffer.readInt();
         }
      }

      StatPasDef = buffer.readInt();
      mjn = buffer.readInt();
      atcm = buffer.readDouble();
      AttributeUpgradeCost_StartMinus = buffer.readInt();
      AttributeUpgradeCost_Min = buffer.readInt();

      for (int i = 0; i < JRMCoreConfig.AttributeUpgradeCost_AttributeMulti.length; i++) {
         AttributeUpgradeCost_AttributeMulti[i] = (float)buffer.readDouble();
      }

      lgnd = buffer.readInt();
      lgndb = ByteBufUtils.readUTF8String(buffer);
      boolean lockon = buffer.readBoolean();
      double Flngspd = buffer.readDouble();
      if (JRMCoreH.DBC()) {
         s1 = ByteBufUtils.readUTF8String(buffer);
         s2 = ByteBufUtils.readUTF8String(buffer);
         s3 = ByteBufUtils.readUTF8String(buffer);
      }

      if (JRMCoreH.DBC() || JRMCoreH.NC()) {
         dat5711 = buffer.readBoolean();
      }

      if (JRMCoreH.DBC()) {
         JRMCoreHDBC.DBCsetConfigmaxTrnExp(maxTrnExp);
         JRMCoreHDBC.DBCsetConfigplntVegeta(plntVegeta);
         JRMCoreHDBC.DBCsetConfigflyAnyLvl(flyAnyLvl);
         JRMCoreHDBC.DBCsetConfigDeathSystemOff(DeathSystemOff);
         JRMCoreHDBC.DBCsetConfigDBSpawnEnabled(DBSpawnEnabled);
         JRMCoreHDBC.DBCsetConfigDBSpawnTime(DBSpawnTime);
         JRMCoreHDBC.DBCsetConfigDBSagaSystemOn(SagaSystemOn);
         JRMCoreHDBC.DBCsetConfigDBSagaSysSpawnPods(SagaSysSpawnPods);
         JRMCoreHDBC.DBCsetConfigsenzuCool(senzuCool);
         JRMCoreHDBC.DBCsetConfigReinc(Reinc);
         JRMCoreHDBC.DBCsetConfigGodform(GodForm);
         JRMCoreHDBC.FreeRevSet(FreeRev);
         JRMCoreHDBC.DBCsetConfigTechExpNeed(TechExpNeed);
         JRMCoreHDBC.DBCsetConfigTechCostMod(TechCostMod);
      }

      if (JRMCoreH.JYC()) {
         JRMCoreHJYC.JYCsetConfigpgut(pgut);
      }

      if (JRMCoreH.JFC()) {
         JRMCoreHJFC.setConfigpt(pt);
      }

      int[][] data = new int[20][];
      float[] dataF = new float[80];
      float[][] dataFMatrix = new float[6][17];
      int[] dataI = new int[40];
      String[] dataS = new String[20];
      float[][] dataFF = new float[20][];
      double[][] dataDD = new double[20][];
      if (JRMCoreH.NC()) {
         JRMCoreH.NCRacialSkillTPCost = (int[][])new Gson().fromJson(ncCSklsLvlO, data.getClass());
         JRMCoreH.NCSkillTPCost = (int[][])new Gson().fromJson(ncSklsLvlO, data.getClass());
         JRMCoreH.TransGtsDmg = (float[])new Gson().fromJson(TransGtsDmgO, dataF.getClass());
      }

      JRMCoreH.DBCRacialSkillTPCost = (int[][])new Gson().fromJson(vlblRSklsLvlO, data.getClass());
      JRMCoreH.DBCSkillTPCost = (int[][])new Gson().fromJson(vlblSklsLvlO, data.getClass());
      JRMCoreH.TransMngDmg = (float[])new Gson().fromJson(TransMngDmgO, dataF.getClass());
      JRMCoreH.TransKaiDmg = (float[])new Gson().fromJson(TransKaiDmgO, dataF.getClass());
      JRMCoreH.TransKaiDrainLevel = (float[])new Gson().fromJson(TransKaiDrainOLevel, dataF.getClass());
      JRMCoreH.TransKaiDrainRace = (float[])new Gson().fromJson(TransKaiDrainORace, dataF.getClass());
      JRMCoreH.TransKaiNms = (String[])new Gson().fromJson(TransKaiNmsO, dataS.getClass());
      JRMCoreH.TransSaiStBnP = (float[][])new Gson().fromJson(TransSaiStBnPO, dataFF.getClass());
      JRMCoreH.TransHalfSaiStBnP = (float[][])new Gson().fromJson(TransHalfSaiStBnPO, dataFF.getClass());
      JRMCoreH.TransFrStBnP = (float[][])new Gson().fromJson(TransFrStBnPO, dataFF.getClass());
      JRMCoreH.TransHmStBnP = (float[][])new Gson().fromJson(TransHmStBnPO, dataFF.getClass());
      JRMCoreH.TransNaStBnP = (float[][])new Gson().fromJson(TransNaStBnPO, dataFF.getClass());
      JRMCoreH.TransMaStBnP = (float[][])new Gson().fromJson(TransMaStBnPO, dataFF.getClass());
      JRMCoreH.DBCRacialSkillMindCost = (int[][])new Gson().fromJson(vlblRSklsMRO, data.getClass());
      JRMCoreH.DBCSkillMindCost = (int[][])new Gson().fromJson(vlblSklsMRO, data.getClass());
      JRMCoreH.NCRacialSkillMindCost = (int[][])new Gson().fromJson(ncCSklsMRO, data.getClass());
      JRMCoreH.NCSkillMindCost = (int[][])new Gson().fromJson(ncSklsMRO, data.getClass());
      JRMCoreConfig.OverAtrLimit = OverAtrLimitO;
      JRMCoreConfig.MysticDamMulti = (float[])new Gson().fromJson(MysticDamMulti, dataF.getClass());
      JRMCoreConfig.ArcosianPPMax = (int[])new Gson().fromJson(ArcoPP, dataI.getClass());
      JRMCoreConfig.ArcosianPPGrowth = (int[])new Gson().fromJson(ArcoPP2, dataI.getClass());
      JRMCoreConfig.ArcosianPPCost = (int[])new Gson().fromJson(ArcoPP3, dataI.getClass());
      JRMCoreConfig.ArcosianPPDamMulti = (float[])new Gson().fromJson(ArcoPP4, dataF.getClass());
      JRMCoreConfig.ArcosianPPDamMultiPoint = (float[])new Gson().fromJson(ArcoPP5, dataF.getClass());
      JRMCoreConfig.AttibuteBonusPerRacialSkill = (float[][])new Gson().fromJson(AttrBonusPerRacialSkill, dataFMatrix.getClass());
      JRMCoreConfig.ArcosianPPDamMultiHighest = ArcoPP6;
      JRMCoreConfig.TPGainRateRace = (float[])new Gson().fromJson(tpGainRate, dataF.getClass());
      JRMCoreConfig.TPGainRace = (float[])new Gson().fromJson(tpGain, dataF.getClass());
      JRMCoreConfig.expGriOff = expGriOff;
      JRMCoreConfig.SklMedCat = SklMedCat;
      JRMCoreConfig.SklMedRate = SklMedRate;
      JRMCoreConfig.releaseStop = SklMedStop;
      JRMCoreConfig.regen = regen;
      JRMCoreConfig.release = release;
      JRMCoreConfig.tpgn = tpgn;
      JRMCoreConfig.tmx = nQp65G(attrMx);
      JRMCoreConfig.regenRate = regenRate;
      JRMCoreConfig.hRegenRate = hRegenRate;
      JRMCoreConfig.sizes = sizes;
      JRMCoreConfig.ssurl = ssurl;
      JRMCoreConfig.ssurl2 = ssurl2;
      JRMCoreConfig.ssc = ssc;
      JRMCoreConfig.sfzns = sfzns;
      JRMCoreConfig.NPCSpawnCheck = NPCSpawnCheck;
      JRMCoreConfig.BuildingSpawnCheck = BuildingSpawnCheck;
      JRMCoreConfig.buildingSpawnAreaSize = buildingSpawnAreaSize;
      JGConfigMiniGameConcentration.TPGainOn = TPGainOn;
      JGConfigMiniGameConcentration.TPlimitIncreasesWithPlayerLevel = TPlimitIncreasesWithPlayerLevel;
      JGConfigMiniGameConcentration.TPMultiplier = TPMultiplier;
      JGConfigMiniGameConcentration.TPDailyLimit = TPDailyLimit;
      JGConfigMiniGameConcentration.ComboTimer = ComboTimer;
      JGConfigMiniGameConcentration.ConstantClickOn = ConstantClickOn;
      JGConfigMiniGameConcentration.RandomMovementSpeed = RandomMovementSpeed;
      JGConfigMiniGameAirBoxing.TPGainOn = TPGainOn2;
      JGConfigMiniGameAirBoxing.TPlimitIncreasesWithPlayerLevel = TPlimitIncreasesWithPlayerLevel2;
      JGConfigMiniGameAirBoxing.TPMultiplier = TPMultiplier2;
      JGConfigMiniGameAirBoxing.TPDailyLimit = TPDailyLimit2;
      JGConfigMiniGameAirBoxing.StartLife = StartLife;
      JGConfigMiniGameAirBoxing.KeySpawnSpeed = KeySpawnSpeed;
      JGConfigMiniGameAirBoxing.KeySpeed = KeySpeed;
      JGConfigMiniGameAirBoxing.KeyLifeTaken = KeyLifeTaken;
      JGConfigMiniGameAirBoxing.KeyTypeIDs = KeyTypeIDs;
      JRMCoreConfig.StatPasDef = StatPasDef;
      JRMCoreConfig.mjn = mjn;
      JRMCoreConfig.atcm = atcm;
      JRMCoreConfig.AttributeUpgradeCost_StartMinus = AttributeUpgradeCost_StartMinus;
      JRMCoreConfig.AttributeUpgradeCost_Min = AttributeUpgradeCost_Min;
      JRMCoreConfig.AttributeUpgradeCost_AttributeMulti = AttributeUpgradeCost_AttributeMulti;
      JRMCoreConfig.lgnd = lgnd;
      JRMCoreConfig.lgndb = lgndb;
      JRMCoreConfig.lockon = lockon;
      JRMCoreConfig.Flngspd = Flngspd;
      if (JRMCoreH.DBC()) {
         String[] str = s1.split(" ");

         for (int i = 0; i < JRMCoreConfig.dat5695.length; i++) {
            JRMCoreConfig.dat5695[i] = Boolean.parseBoolean(str[i]);
         }

         String[] str2 = s2.split(";");

         for (int i = 0; i < JRMCoreConfig.dat5696.length; i++) {
            str = str2[i].split(" ");

            for (int j = 0; j < JRMCoreConfig.dat5696[j].length; j++) {
               JRMCoreConfig.dat5696[i][j] = Double.parseDouble(str[j]);
            }
         }

         str = s3.split(" ");

         for (int i = 0; i < JRMCoreConfig.dat5709.length; i++) {
            JRMCoreConfig.dat5709[i] = Boolean.parseBoolean(str[i]);
         }
      }

      if (JRMCoreH.DBC() || JRMCoreH.NC()) {
         JRMCoreConfig.dat5711 = dat5711;
      }

      if (JRMCoreH.DBC()) {
         byte levels = buffer.readByte();
         JGConfigUltraInstinct.CONFIG_UI_LEVELS = levels;
         JGConfigUltraInstinct.CONFIG_UI_HEAT_DURATION = new int[JGConfigUltraInstinct.CONFIG_UI_LEVELS];
         JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE = new boolean[JGConfigUltraInstinct.CONFIG_UI_LEVELS];
         JGConfigUltraInstinct.CONFIG_UI_ATTRIBUTE_MULTI = new int[JGConfigUltraInstinct.CONFIG_UI_LEVELS];
         JGConfigUltraInstinct.CONFIG_UI_ATTRIBUTE_MULTI_RACE = new float[JGConfigUltraInstinct.CONFIG_UI_LEVELS][JRMCoreH.Races.length];
         JGConfigUltraInstinct.CONFIG_UI_DODGE_RATE = new byte[JGConfigUltraInstinct.CONFIG_UI_LEVELS][2];
         JGConfigUltraInstinct.CONFIG_UI_ATTACK_RATE = new byte[JGConfigUltraInstinct.CONFIG_UI_LEVELS][2];

         for (int i = 0; i < JGConfigUltraInstinct.CONFIG_UI_LEVELS; i++) {
            int heat = buffer.readInt();
            JGConfigUltraInstinct.CONFIG_UI_HEAT_DURATION[i] = heat;
            boolean hair_white = buffer.readBoolean();
            JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[i] = hair_white;
            int attribute_multi = buffer.readInt();
            JGConfigUltraInstinct.CONFIG_UI_ATTRIBUTE_MULTI[i] = attribute_multi;

            for (int j = 0; j < JRMCoreH.Races.length; j++) {
               float attribute_multi2 = buffer.readFloat();
               JGConfigUltraInstinct.CONFIG_UI_ATTRIBUTE_MULTI_RACE[i][j] = attribute_multi2;
            }

            for (int j = 0; j < 2; j++) {
               byte dodge_rate = buffer.readByte();
               JGConfigUltraInstinct.CONFIG_UI_DODGE_RATE[i][j] = dodge_rate;
               byte attack_rate = buffer.readByte();
               JGConfigUltraInstinct.CONFIG_UI_ATTACK_RATE[i][j] = attack_rate;
            }
         }
      }

      byte extendedPlayerBlock = buffer.readByte();
      byte extendedPlayerOther = buffer.readByte();
      byte extendedPlayerHair = buffer.readByte();
      JRMCoreConfig.ExtendedPlayerBlockID = extendedPlayerBlock;
      JRMCoreConfig.ExtendedPlayerOtherID = extendedPlayerOther;
      JRMCoreConfig.ExtendedPlayerHairID = extendedPlayerHair;
      if (JRMCoreH.DBC()) {
         boolean canWhisTP = buffer.readBoolean();
         DBCConfig.CanWhisTeleport = canWhisTP;
         float EnmaScale = buffer.readFloat();
         DBCConfig.EnmaScale = EnmaScale;
         float GuruScale = buffer.readFloat();
         DBCConfig.GuruScale = GuruScale;

         for (int i = 0; i < JRMCoreConfig.ContinuesKiAttacks.length; i++) {
            boolean continues = buffer.readBoolean();
            JRMCoreConfig.ContinuesKiAttacks[i] = continues;
         }

         boolean scaleW = buffer.readBoolean();
         JRMCoreConfig.KiAttackScalesWithUser = scaleW;
      }

      if (JRMCoreH.NC()) {
         for (int i = 0; i < JRMCoreConfig.ContinuesJutsuAttacks.length; i++) {
            boolean continues = buffer.readBoolean();
            JRMCoreConfig.ContinuesJutsuAttacks[i] = continues;
         }

         boolean scaleW = buffer.readBoolean();
         JRMCoreConfig.JutsuScalesWithUser = scaleW;
      }

      if (JRMCoreH.DBC() || JRMCoreH.NC()) {
         boolean letgo = buffer.readBoolean();
         JRMCoreConfig.WavesShrinkOnceLetGo = letgo;
         boolean targetSlow = buffer.readBoolean();
         JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown = targetSlow;
         int energyTimer = buffer.readInt();
         JRMCoreConfig.ContinuesEnergyAttackTimer = energyTimer;
      }

      int ceaesl = buffer.readInt();
      float cealbm = buffer.readFloat();
      JRMCoreConfig.eaesl = ceaesl;
      JRMCoreConfig.ealbm = cealbm;
      if (JRMCoreH.DBC()) {
         int nullRealmMin = buffer.readInt();
         DBCConfig.NullRealmMinimumHeight = nullRealmMin;

         for (int i = 0; i < 9; i++) {
            double ContinuesCost = buffer.readDouble();
            JRMCoreConfig.dat5696[i][2] = ContinuesCost;
         }

         boolean NullRealmBGColorNodeGreen = buffer.readBoolean();
         DBCConfig.NullRealmBGColorNodeGreen = NullRealmBGColorNodeGreen;
         boolean PlayerFlyingDragDownOn = buffer.readBoolean();
         JRMCoreConfig.PlayerFlyingDragDownOn = PlayerFlyingDragDownOn;
      }

      if (JRMCoreH.DBC()) {
         for (int k = 0; k < JRMCoreH.Races.length; k++) {
            for (int i = 0; i < JRMCoreH.ClassesDBC.length; i++) {
               for (int j = 0; j < JRMCoreH.attrInit[1].length; j++) {
                  double configValue = buffer.readDouble();
                  JGConfigRaces.CONFIG_RACES_ATTRIBUTE_MULTI[k][i][j] = configValue;
                  int configValue2 = buffer.readInt();
                  JGConfigRaces.CONFIG_RACES_ATTRIBUTE_START[k][i][j] = configValue2;
               }

               for (int j = 0; j < JRMCoreH.statNames[1].length; j++) {
                  float configValue = buffer.readFloat();
                  JGConfigRaces.CONFIG_RACES_STATS_MULTI[k][i][j] = configValue;
                  int configValue2 = buffer.readInt();
                  JGConfigRaces.CONFIG_RACES_STAT_BONUS[k][i][j] = configValue2;
               }
            }
         }

         boolean configValue = buffer.readBoolean();
         JGConfigRaces.CONFIG_MAJIN_ENABLED = configValue;
         boolean configValue2 = buffer.readBoolean();
         JGConfigRaces.CONFIG_MAJIN_ABSORPTION_ENABLED = configValue2;
         boolean configValue5 = buffer.readBoolean();
         JGConfigRaces.CONFIG_MAJIN_PURE_PINK_SKIN = configValue5;
         boolean configValue6 = buffer.readBoolean();
         JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MULTIPLIES_BONUS_ATTRIBUTE_MULTIPLIERS = configValue6;

         for (int i = 0; i < JRMCoreH.TransNms[5].length + 3; i++) {
            float configValue3 = buffer.readFloat();
            JGConfigRaces.CONFIG_MAJIN_ABSORPTON_ATTRIBUTE_MULTI[i] = configValue3;
            float configValue4 = buffer.readFloat();
            JGConfigRaces.CONFIG_MAJIN_ABSORPTON_SPEED_MULTI[i] = configValue4;
         }
      }

      boolean JRMCABonusOn = buffer.readBoolean();
      JRMCoreConfig.JRMCABonusOn = JRMCABonusOn;
      JRMCABonusOn = buffer.readBoolean();
      JRMCoreConfig.ShadowDummyScaleToTarget = JRMCABonusOn;
      if (JRMCoreH.DBC()) {
         JRMCABonusOn = buffer.readBoolean();
         JGConfigUltraInstinct.CONFIG_UI_IGNORE_BASE_CONFIG = JRMCABonusOn;
         boolean CONFIG_GOD_IGNORE_BASE_CONFIG = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_IGNORE_BASE_CONFIG = CONFIG_GOD_IGNORE_BASE_CONFIG;
         int i = buffer.readInt();
         JGConfigDBCGoD.CONFIG_GOD_IGNORED_DAMAGE_SOURCES = new String[i];

         for (int j = 0; j < i; j++) {
            String s = ByteBufUtils.readUTF8String(buffer);
            JGConfigDBCGoD.CONFIG_GOD_IGNORED_DAMAGE_SOURCES[j] = s;
         }

         i = buffer.readInt();
         JGConfigDBCGoD.CONFIG_GOD_IGNORED_ENTITIES = new String[i];

         for (int j = 0; j < i; j++) {
            String s = ByteBufUtils.readUTF8String(buffer);
            JGConfigDBCGoD.CONFIG_GOD_IGNORED_ENTITIES[j] = s;
         }

         float f = buffer.readFloat();
         JGConfigDBCGoD.CONFIG_GOD_IGNORE_DAMAGE_MULTI = f;
         boolean b = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_IGNORE_PROJECTILES_ENABLED = b;
         b = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_ENABLED = b;
         b = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED = b;
         b = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED_WITH_AURA = b;
         b = buffer.readBoolean();
         JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED = b;
         f = buffer.readFloat();
         JGConfigDBCGoD.CONFIG_GOD_ENERGY_DAMAGE_MULTI = f;
         f = buffer.readFloat();
         JGConfigDBCGoD.CONFIG_GOD_ATTRIBUTE_MULTI = f;

         for (int j = 0; j < JRMCoreH.Races.length; j++) {
            f = buffer.readFloat();
            JGConfigDBCGoD.CONFIG_GOD_ATTRIBUTE_MULTI_RACE[j] = f;
         }
      }

      JRMCoreConfig.BuildingBlocksRenderAsNormalBlock = buffer.readBoolean();
      JGConfigSkills.GlobalSkillTPMultiplier = buffer.readFloat();
      JGConfigSkills.GlobalSkillMindMultiplier = buffer.readFloat();
      JGConfigSkills.GlobalSkillTPMultiplierFirst = buffer.readFloat();
      JGConfigSkills.GlobalSkillMindMultiplierFirst = buffer.readFloat();
      JGConfigSkills.GlobalSkillTPMultiplierWithLevel = buffer.readBoolean();
      JGConfigSkills.GlobalSkillMindMultiplierWithLevel = buffer.readBoolean();
      if (JRMCoreH.NC()) {
         JRMCoreConfig.NCExplosionTagTickTimer = buffer.readInt();
      }

      if (JRMCoreH.DBC()) {
         JRMCABonusOn = (boolean)buffer.readInt();
         DBCConfig.AaiForceDifficulty = JRMCABonusOn;
         boolean aai2 = buffer.readBoolean();
         DBCConfig.AaiDisabled = aai2;
         int aai3 = buffer.readInt();
         DBCConfig.EnemyDefaultAttackTimer = aai3;
         int aai4 = buffer.readInt();
         DBCConfig.EnemyDefaultShortRangeAttackTimer = aai4;
         double aai5 = buffer.readDouble();
         DBCConfig.EnemyDefaultMoveSpeed = aai5;
         boolean aai6 = buffer.readBoolean();
         DBCConfig.KiAttackGoThroughInvulnerableEnemies = aai6;
         boolean aai7 = buffer.readBoolean();
         DBCConfig.InstantTransformOn = aai7;
         boolean aai8 = buffer.readBoolean();
         DBCConfig.SingleFormDescendOn = aai8;

         for (int i = 0; i < DBCConfig.IsInstantTransformEnabled.length; i++) {
            boolean aai9 = buffer.readBoolean();
            DBCConfig.IsInstantTransformEnabled[i] = aai9;
         }

         boolean aai10 = buffer.readBoolean();
         DBCConfig.KaiokenSingleFormDescendOn = aai10;
         boolean aai11 = buffer.readBoolean();
         DBCConfig.MoveWhileTransforming = aai11;
         boolean aai12 = buffer.readBoolean();
         DBCConfig.MoveWhileInstantTransforming = aai12;

         for (int i = 0; i < JGConfigDBCAAiDifficulty.DIFFICULTIES.length; i++) {
            double aai13 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.GroundDashSpeedMulti[i] = aai13;
            double aai14 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.GroundDashSpeedMulti2[i] = aai14;
            double aai15 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.GroundDashLimit[i] = aai15;
            double aai16 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.JumpMulti[i] = aai16;
            double aai17 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.JumpMulti2[i] = aai17;
            double aai18 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.JumpLimit[i] = aai18;
            double aai19 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.JumpLimit2[i] = aai19;
            double aai27 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.JumpRate[i] = aai27;
            double aai20 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.FlyingDashMulti[i] = aai20;
            double aai21 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.FlyingDashLimit[i] = aai21;
            double aai22 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.KiAttackChargeMulti[i] = aai22;
            double aai23 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.KiAttackChargeLimit[i] = aai23;
            int aai24 = buffer.readInt();
            JGConfigDBCAAiDifficulty.TeleportRateMin[i] = aai24;
            int aai25 = buffer.readInt();
            JGConfigDBCAAiDifficulty.TeleportRateMax[i] = aai25;
            double aai26 = buffer.readDouble();
            JGConfigDBCAAiDifficulty.SpeedMulti[i] = aai26;
         }

         boolean aai27 = buffer.readBoolean();
         DBCConfig.MysticKaiokenOn = aai27;

         for (int i = 0; i < JRMCoreH.Races.length; i += 1) {
            for (int j = 0; j < JRMCoreConfig.KaiokenFormHealthCost[i].length; j += 1) {
               float aai29 = buffer.readFloat();
               JRMCoreConfig.KaiokenFormHealthCost[i][j] = aai29;
            }
         }

         for (int i = 0; i < 2; i += 1) {
            boolean aai29 = buffer.readBoolean();
            JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[i] = aai29;
         }
      }

      boolean aai29 = buffer.readBoolean();
      JGConfigDBCFormMastery.FM_Enabled = aai29;

      for (int raceID = 0; raceID < JRMCoreH.Races.length; raceID++) {
         int racials = JRMCoreH.trans[raceID].length;

         for (int formID = 0; formID < JGConfigDBCFormMastery.FormMasteries[raceID].length; formID++) {
            boolean racial = formID < racials;
            String form = racial ? JRMCoreH.trans[raceID][formID] : JRMCoreH.transNonRacial[formID - racials];
            if (!racial || !JRMCoreH.isRaceSaiyan(raceID) || !form.equals(JRMCoreH.trans[raceID][12]) && !form.equals(JRMCoreH.trans[raceID][13])) {
               for (int j = 0; j < 3; j++) {
                  double aai30 = buffer.readDouble();
                  JGConfigDBCFormMastery.FormMasteries[raceID][formID].data.get(JGConfigDBCFormMastery.DATA_ID_DAMAGE_MULTI)[j] = aai30 + "";
               }
            }
         }
      }
   }

   public void handleFall(byte b, EntityPlayer p) {
   }

   public void handleRls(byte b, EntityPlayer p) {
   }

   public void handleTech(byte b, String s, EntityPlayer p) {
      if (b >= 0 && b <= 3) {
         String[] s2 = s.contains(";") ? s.toString().split(";") : null;
         if (b == 0) {
            JRMCoreH.tech1 = s2;
         }

         if (b == 1) {
            JRMCoreH.tech2 = s2;
         }

         if (b == 2) {
            JRMCoreH.tech3 = s2;
         }

         if (b == 3) {
            JRMCoreH.tech4 = s2;
         }
      }
   }

   public void handleAttck(byte b, EntityPlayer p) {
   }

   public void handleCost(short s, EntityPlayer p) {
   }

   public void handleStats2(int curTP, int curExp, byte align, int[] plyrAttrbts, EntityPlayer p) {
      JRMCoreH.curTP = curTP;
      JRMCoreH.curExp = curExp;
      JRMCoreH.align = align;
      JRMCoreH.PlyrAttrbts = plyrAttrbts;
      byte pwr = JRMCoreH.Pwrtyp;
      byte rce = JRMCoreH.Race;
      byte cls = JRMCoreH.Class;
      JRMCoreH.maxBody = JRMCoreH.stat(p, 2, pwr, 2, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0F);
      JRMCoreH.maxEnergy = JRMCoreH.stat(p, 5, pwr, 5, JRMCoreH.PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(pwr));
      JRMCoreH.maxStamina = JRMCoreH.stat(p, 2, pwr, 3, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0F);
   }

   public static int aqMWr(String l) {
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

   public static int nQp65G(int b) {
      String r = "64";
      String k = "3B9ACA00";
      return b > aqMWr(k) ? aqMWr(k) : (b < aqMWr(r) ? 0 : b);
   }

   public void handleStats3(String PlyrSkills, String x, String y, String z, EntityPlayer p) {
      JRMCoreH.PlyrSkillX = x;
      JRMCoreH.PlyrSkillY = y;
      JRMCoreH.PlyrSkillZ = z;
      JRMCoreH.PlyrSkills = PlyrSkills.split(",");
   }

   public void handleStats(int curBody, int curEnergy, int curStamina, byte curRelease, byte b) {
      JRMCoreH.curBody = curBody;
      JRMCoreH.curEnergy = curEnergy;
      JRMCoreH.curStamina = curStamina;
      JRMCoreH.curRelease = curRelease;
      JRMCoreH.TransSaiCurRg = b;
   }

   public void handleUpgrade(byte b, EntityPlayer p) {
   }

   public void handleCol(int i, byte b, EntityPlayer p) {
   }

   public void handleChar(byte b, int b2, EntityPlayer p) {
   }

   public void handleTick(int jrmcpg, String jrmcp, EntityPlayer p) {
      if (jrmcpg == 4) {
         String[] s = jrmcp.split(";");
         if (s.length > 2) {
            Entity e = p.field_70170_p.func_73045_a(Integer.parseInt(s[0]));
            if (e != null) {
               if (JGConfigClientSettings.CLIENT_DA15) {
                  float a = 1.0F;
                  float h1 = 1.0F;
                  float scale = e.field_70173_aa * (e.field_70131_O / 100.0F);
                  Entity pl = e;
                  double x = 0.0;
                  double y = pl.field_70131_O * 0.6F;
                  double z = 0.0;
                  Entity entity7 = new EntityCusPar(
                     "jinryuumodscore:bens_particles.png",
                     e.field_70170_p,
                     0.4F,
                     0.4F,
                     pl.field_70165_t,
                     pl.field_70163_u,
                     pl.field_70161_v,
                     x,
                     y,
                     z,
                     0.0,
                     0.0,
                     0.0,
                     0.0F,
                     (int)(Math.random() * 4.0) + 12,
                     12,
                     4,
                     32,
                     true,
                     (float)(Math.random() * 0.3F) + 0.3F,
                     false,
                     0.0F,
                     1,
                     "",
                     35,
                     1,
                     (float)(Math.random() * 0.02F) + 0.04F,
                     (float)(Math.random() * 0.03F) + 0.06F,
                     (float)(Math.random() * 0.003F) + 0.001F,
                     0,
                     1.0F,
                     1.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     3,
                     1.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     -0.05F,
                     false,
                     -1,
                     false,
                     null
                  );
                  ((EntityCusPar)entity7).setdata39((int)(Math.random() * 360.0));
                  e.field_70170_p.func_72838_d(entity7);
                  int num = (int)(Math.random() * 4.0) + 1;

                  for (int i = 0; i < num; i++) {
                     Entity entity7x = new EntityCusPar(
                        "jinryuumodscore:bens_particles.png",
                        e.field_70170_p,
                        0.4F,
                        0.4F,
                        pl.field_70165_t,
                        pl.field_70163_u,
                        pl.field_70161_v,
                        x,
                        y,
                        z,
                        0.0,
                        0.0,
                        0.0,
                        0.0F,
                        (int)(Math.random() * 4.0) + 4,
                        4,
                        4,
                        64,
                        true,
                        (float)(Math.random() * 0.4F) + 0.4F,
                        false,
                        0.0F,
                        1,
                        "",
                        22,
                        1,
                        (float)(Math.random() * 0.02F) + 0.03F,
                        (float)(Math.random() * 0.03F) + 0.05F,
                        (float)(Math.random() * 0.002F) + 0.001F,
                        0,
                        1.0F,
                        1.0F,
                        1.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        3,
                        1.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        -0.05F,
                        false,
                        -1,
                        false,
                        null
                     );
                     ((EntityCusPar)entity7x).setdata39((int)(Math.random() * 360.0));
                     e.field_70170_p.func_72838_d(entity7x);
                  }
               }

               JRMCoreH.damInd.put(e.field_70165_t + ":" + (e.field_70163_u + e.field_70131_O) + ":" + e.field_70161_v, s[2] + ":100");
            }
         }
      } else if (jrmcpg == 50) {
         if (JGConfigClientSettings.instantTransmissionParticles) {
            String[] s = jrmcp.split(";");
            if (s.length > 3) {
               Entity e = p.field_70170_p.func_73045_a(Integer.parseInt(s[0]));
               if (e != null) {
                  float scale = 0.025F * e.field_70131_O;
                  float a = 0.25F;
                  Entity pl = e;
                  double x = 0.0;
                  double y = pl.field_70131_O * 0.5F;
                  double z = 0.0;
                  double x2 = Double.parseDouble(s[1]);
                  double y2 = Double.parseDouble(s[2]);
                  double z2 = Double.parseDouble(s[3]);
                  Entity entity = new EntityCusPar(
                     "jinryuudragonbc:bens_particles2.png",
                     e.field_70170_p,
                     0.4F,
                     0.4F,
                     x2,
                     y2,
                     z2,
                     x,
                     y,
                     z,
                     0.0,
                     0.0,
                     0.0,
                     0.0F,
                     (int)(Math.random() * 3.0),
                     0,
                     0,
                     64,
                     false,
                     0.0F,
                     false,
                     0.0F,
                     1,
                     "",
                     10,
                     1,
                     scale,
                     scale / 50.0F,
                     -scale / 20.0F,
                     0,
                     1.0F,
                     1.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     2,
                     a,
                     0.0F,
                     0.0F,
                     0.0F,
                     a / 5.0F,
                     false,
                     -1,
                     false,
                     null
                  );
                  e.field_70170_p.func_72838_d(entity);
               }
            }
         }
      } else if (jrmcpg != 5) {
         if (jrmcpg == 20) {
            p.openGui(mod_JRMCore.instance, 2, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
            JRMCoreH.ask = jrmcp;
         } else if (jrmcpg == 22) {
            p.openGui(mod_JRMCore.instance, 3, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
            JRMCoreH.ask = jrmcp;
         } else if (jrmcpg == 1) {
            if (jrmcp == "::") {
               JRMCoreH.plyrsArnd = null;
            } else {
               JRMCoreH.plyrsArnd = jrmcp.toString().replaceAll("::", "").split(":");
            }
         }
      }
   }

   public void handleNotification(EntityPlayer p, String title, String description, byte category, byte icon, byte renderLocation, int iconColor) {
      this.handleNotification(title, description, category, icon, renderLocation, iconColor);
   }

   public void handleNotification(String title, String description, byte category, byte icon, byte renderLocation, int iconColor) {
      if (JGNotificationGUI.categoryState[category] != 2 && JGNotificationGUI.categoryState[0] != 2) {
         JGNotificationHandlerC.addNotification(new JGNotification(title, description, category, icon, renderLocation, iconColor));
      }
   }
}
