package JinRyuu.JRMCore;

import java.util.HashMap;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JRMCoreConfig {
   public static final String CATEGORY_SERVERSIDED = "general";
   public static final String CATEGORY_CLIENTSIDED = "Client Sided Configs";
   public static final String CATEGORY_JBRA = "JBRA Configs";
   public static final String CATEGORY_DBC_SERVERSIDED = "DBC Server Sided Configs";
   public static final String CATEGORY_DBC_KIATTACK_SERVERSIDED = "DBC Ki Attack Server Sided Configs";
   public static final String CATEGORY_NC_JUTSU_SERVERSIDED = "NC Jutsu Server Sided Configs";
   public static final String CATEGORY_COMMAND = "Command Configs";
   public static final String CATEGORY_ENERGYATTACKS = "Energy Attack Configs";
   public static final String CATEGORY_NC_SERVERSIDED = "NarutoC Server Sided Configs";
   public static final String CATEGORY_CONSOLE = "Console Configs";
   public static final String CATEGORY_FOOD = "Food Configs";
   public static final int ACTIVE = 0;
   public static final int PASSVIE = 1;
   public static boolean regen;
   public static boolean hregen;
   public static boolean release;
   public static int tpgn;
   public static int tmx = 500;
   public static String regenRate;
   public static int rgnRt;
   public static String hRegenRate;
   public static int hRgnRt;
   public static int playerCount;
   public static boolean sizes;
   public static String ssurl;
   public static String ssurl2;
   public static String ssc;
   public static boolean sfzns;
   public static int eaei;
   public static double eaes;
   public static int eaesl;
   public static float eadm;
   public static float ealbm;
   public static int buildingSpawnAreaSize = 100;
   public static boolean NPCSpawnCheck = true;
   public static boolean BuildingSpawnCheck = true;
   public static int RegnTime = 10;
   public static boolean KillAlgnmntChnge;
   public static boolean expGriOff = false;
   public static boolean cexpGriOff = false;
   public static boolean cregen;
   public static boolean chregen;
   public static boolean crelease;
   public static int ctpgn;
   public static int ctmx;
   public static String cregenRate;
   public static int crgnRt;
   public static String chRegenRate;
   public static int chRgnRt;
   public static int cplayerCount;
   public static boolean csizes;
   public static String cssurl;
   public static String cssurl2;
   public static String cssc;
   public static boolean csfzns;
   public static int ceaei;
   public static double ceaes;
   public static int ceaesl;
   public static float ceadm;
   public static float cealbm;
   public static int cbuildingSpawnAreaSize = 100;
   public static boolean cNPCSpawnCheck = true;
   public static boolean cBuildingSpawnCheck = true;
   public static int cRegnTime;
   public static boolean cKillAlgnmntChnge;
   public static boolean HHWHO;
   public static boolean OverAtrLimit = false;
   public static boolean OverAtrLimitO = false;
   public static boolean forceJBRA;
   public static String[] sfzna;
   public static String[] csfzna;
   public static int cSklMedCat;
   public static int SklMedCat;
   public static float cSklMedRate;
   public static float SklMedRate;
   public static boolean creleaseStop;
   public static boolean releaseStop;
   public static float cTpgnRt;
   public static float TpgnRt;
   public static boolean csskai;
   public static boolean sskai;
   public static int cStatPasDef;
   public static int StatPasDef;
   public static boolean cfuzn;
   public static boolean fuzn;
   public static int cmjn;
   public static int mjn;
   public static int cselgnd;
   public static int selgnd;
   public static int cselgndc;
   public static int selgndc;
   public static int cselgndc2;
   public static int selgndc2;
   public static int cosbic;
   public static int osbic;
   public static int clgnd;
   public static int lgnd;
   public static String clgndb;
   public static String lgndb;
   public static double cappm;
   public static double appm;
   public static double catcm;
   public static double atcm;
   public static int chedp;
   public static int hedp;
   public static boolean clockon;
   public static boolean lockon;
   public static boolean cComHealNAS;
   public static boolean ComHealNAS;
   public static boolean cComHealNAC;
   public static boolean ComHealNAC;
   public static boolean cComHealNAO;
   public static boolean ComHealNAO;
   public static boolean cComTPNAS;
   public static boolean ComTPNAS;
   public static boolean cComTPNAC;
   public static boolean ComTPNAC;
   public static boolean cComTPNAO;
   public static boolean ComTPNAO;
   public static boolean cComANAS;
   public static boolean ComANAS;
   public static boolean cComANAC;
   public static boolean ComANAC;
   public static boolean cComANAO;
   public static boolean ComANAO;
   public static boolean cComSENAS;
   public static boolean ComSENAS;
   public static boolean cComSENAC;
   public static boolean cComSENAO;
   public static boolean ComSENAC;
   public static boolean ComSENAO;
   public static boolean cComMNAS;
   public static boolean ComMNAS;
   public static boolean cComMNAC;
   public static boolean cComMNAO;
   public static boolean ComMNAC;
   public static boolean ComMNAO;
   public static boolean cComRSNAS;
   public static boolean ComRSNAS;
   public static boolean cComRSNAC;
   public static boolean ComRSNAC;
   public static boolean cComRSNAO;
   public static boolean ComRSNAO;
   public static int FznTime;
   public static int FznOverTime;
   public static int cuilr;
   public static int uilr;
   public static int cuidr;
   public static int uidr;
   public static int cuipd;
   public static int uipd;
   public static int cuirlr;
   public static int uirlr;
   public static double cnfPnchc;
   public static double ccnfPnchc;
   static final int attack_types = 9;
   public static boolean[] cdat5695 = new boolean[9];
   public static boolean[] dat5695 = new boolean[9];
   static final int attack_datas = 3;
   public static final int ATTACK_SPEED = 0;
   public static final int ATTACK_DAMAGE = 1;
   public static final int ATTACK_COST = 2;
   public static double[][] cdat5696 = new double[9][3];
   public static double[][] dat5696 = new double[9][3];
   public static int cdat5697;
   public static int dat5697;
   public static double cdat5698;
   public static double dat5698;
   public static double cdat5699;
   public static double dat5699;
   public static double cdat5700;
   public static double dat5700;
   public static double cdat5701;
   public static double dat5701;
   public static HashMap<String, Byte> dat5702 = new HashMap<>();
   public static HashMap<String, Byte> dat5703 = new HashMap<>();
   public static boolean cdat5704;
   public static boolean dat5704;
   public static double cdat5705;
   public static double dat5705;
   public static boolean[] cdat5706 = new boolean[7];
   public static boolean[] dat5706 = new boolean[7];
   public static byte cdat5707;
   public static byte dat5707;
   public static boolean[] cdat5708 = new boolean[2];
   public static boolean[] dat5708 = new boolean[2];
   public static boolean cdat5710;
   public static boolean dat5710;
   public static boolean[] cdat5709 = new boolean[9];
   public static boolean[] dat5709 = new boolean[9];
   public static boolean cdat5711;
   public static boolean dat5711;
   public static double cMystTim;
   public static double MystTim;
   public static double cFlngspd;
   public static double Flngspd;
   public static byte cExtendedPlayerBlockID;
   public static byte cExtendedPlayerOtherID;
   public static byte cExtendedPlayerHairID;
   public static byte ExtendedPlayerBlockID;
   public static byte ExtendedPlayerOtherID;
   public static byte ExtendedPlayerHairID;
   public static double[] cKiSizeMin = new double[7];
   public static double[] cKiSizeMax = new double[7];
   public static double[] cJutsuSizeMin = new double[3];
   public static double[] cJutsuSizeMax = new double[3];
   public static boolean[] cContinuesKiAttacks = new boolean[6];
   public static boolean[] cContinuesJutsuAttacks = new boolean[3];
   public static boolean cWavesDieWhenTargetAway;
   public static boolean cWavesShrinkOnceLetGo;
   public static boolean cKiAttackScalesWithUser;
   public static boolean cJutsuScalesWithUser;
   public static String cKiAttackPowerFormula;
   public static double[] KiSizeMin = new double[7];
   public static double[] KiSizeMax = new double[7];
   public static double[] JutsuSizeMin = new double[3];
   public static double[] JutsuSizeMax = new double[3];
   public static boolean[] ContinuesKiAttacks = new boolean[6];
   public static boolean[] ContinuesJutsuAttacks = new boolean[3];
   public static boolean WavesDieWhenTargetAway;
   public static boolean WavesShrinkOnceLetGo;
   public static String KiAttackPowerFormula;
   public static boolean KiAttackScalesWithUser;
   public static boolean JutsuScalesWithUser;
   public static int[] cTPGainEnergy = new int[8];
   public static int[] TPGainEnergy = new int[8];
   public static int cContinuesEnergyAttackTimer;
   public static boolean cContinuesEnergyAttackMoveOnLostTarget;
   public static boolean cContinuesEnergyAttackEnemyLock;
   public static int ContinuesEnergyAttackTimer;
   public static boolean ContinuesEnergyAttackMoveOnLostTarget;
   public static boolean ContinuesEnergyAttackEnemyLock;
   public static int cEnergyAttackMaxLifeTick;
   public static int cEnergyAttackMaxLifeTickPercMulti;
   public static int EnergyAttackMaxLifeTick;
   public static int EnergyAttackMaxLifeTickPercMulti;
   public static boolean cContinuesEnergyAttackTargetSlowdown;
   public static boolean ContinuesEnergyAttackTargetSlowdown;
   public static boolean cShieldsMoveWithUser;
   public static boolean cExplosionsMoveWithUser;
   public static boolean ShieldsMoveWithUser;
   public static boolean ExplosionsMoveWithUser;
   public static int plUpd;
   public static int cplUpd;
   public static boolean cPlayerFlyingDragDownOn;
   public static boolean PlayerFlyingDragDownOn;
   public static double cExplosionSizeLimit;
   public static double ExplosionSizeLimit;
   public static byte KiClosestEntityCheckSize;
   public static byte cKiClosestEntityCheckSize;
   public static boolean cCommandNotifyAdminJRMCABonusSelf;
   public static boolean CommandNotifyAdminJRMCABonusSelf;
   public static boolean cCommandNotifyAdminJRMCABonusConsole;
   public static boolean CommandNotifyAdminJRMCABonusConsole;
   public static boolean cCommandNotifyAdminJRMCABonusOthers;
   public static boolean CommandNotifyAdminJRMCABonusOthers;
   public static boolean cJRMCABonusOn;
   public static boolean JRMCABonusOn;
   public static boolean cJRMCABonusCheckOnOthersWithoutOP;
   public static boolean JRMCABonusCheckOnOthersWithoutOP;
   public static boolean cJRMCCheckOnOthersWithoutOP;
   public static boolean JRMCCheckOnOthersWithoutOP;
   public static boolean cShadowDummyScaleToTarget;
   public static boolean ShadowDummyScaleToTarget;
   public static byte cSpiralWeakensAfterHit;
   public static byte SpiralWeakensAfterHit;
   public static boolean cSpiralWeakensBasedOnStartDamage;
   public static boolean SpiralWeakensBasedOnStartDamage;
   public static boolean cCanEatWhileKOd;
   public static boolean CanEatWhileKOd;
   public static boolean cCanUseSenzuWhileKOd;
   public static boolean CanUseSenzuWhileKOd;
   public static float cGlobalFoodHealMultiHealth;
   public static float GlobalFoodHealMultiHealth;
   public static float cGlobalFoodHealMultiEnergy;
   public static float GlobalFoodHealMultiEnergy;
   public static HashMap<String, float[]> cFoodHealMulti = new HashMap<>();
   public static HashMap<String, float[]> FoodHealMulti = new HashMap<>();
   public static boolean cBuildingBlocksRenderAsNormalBlock;
   public static boolean BuildingBlocksRenderAsNormalBlock;
   public static boolean NCExplosionTagTickTimerExplode;
   public static boolean cNCExplosionTagTickTimerExplode;
   public static int NCExplosionTagTickTimer;
   public static int cNCExplosionTagTickTimer;
   public static float NCExplosionTagDetectionRange;
   public static float cNCExplosionTagDetectionRange;
   public static float NCExplosionTagExplosionSize;
   public static float cNCExplosionTagExplosionSize;
   public static float[] MysticDamMulti = new float[JRMCoreH.Races.length];
   public static float[] cMysticDamMulti = new float[JRMCoreH.Races.length];
   public static int[] ArcosianPPMax = new int[JRMCoreH.ArcRsrvMaxSkll.length];
   public static int[] cArcosianPPMax = new int[JRMCoreH.ArcRsrvMaxSkll.length];
   public static int[] ArcosianPPGrowth = new int[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static int[] cArcosianPPGrowth = new int[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static int[] ArcosianPPCost = new int[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static int[] cArcosianPPCost = new int[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static float[] ArcosianPPDamMulti = new float[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static float[] cArcosianPPDamMulti = new float[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static float[] ArcosianPPDamMultiPoint = new float[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static float[] cArcosianPPDamMultiPoint = new float[JRMCoreH.trans[4].length + (JRMCoreH.transNonRacial.length - 1)];
   public static int ArcosianPPDamMultiHighest;
   public static int cArcosianPPDamMultiHighest;
   public static HashMap<String, Boolean> SafeZoneEntityBlacklist = new HashMap<>();
   public static HashMap<String, Boolean> SafeZoneEntityWhitelist = new HashMap<>();
   public static float[] TPGainRateRace = new float[JRMCoreH.Races.length];
   public static float[] cTPGainRateRace = new float[JRMCoreH.Races.length];
   public static float[] TPGainRace = new float[JRMCoreH.Races.length];
   public static float[] cTPGainRace = new float[JRMCoreH.Races.length];
   public static final String[] NON_RACIAL_FORMS = new String[]{"Mystic"};
   public static float[][] KaiokenFormHealthCost = new float[JRMCoreH.Races.length][JRMCoreH.trans.length + NON_RACIAL_FORMS.length];
   public static float[][] cKaiokenFormHealthCost = new float[JRMCoreH.Races.length][JRMCoreH.trans.length + NON_RACIAL_FORMS.length];
   public static int cAttributeUpgradeCost_StartMinus = 140;
   public static int AttributeUpgradeCost_StartMinus = 140;
   public static int cAttributeUpgradeCost_Min = 16;
   public static int AttributeUpgradeCost_Min = 16;
   public static final double ATTR_COST_MULTI_D = 0.75;
   public static final float ATTR_COST_MULTI_F = 0.75F;
   public static float[] cAttributeUpgradeCost_AttributeMulti = new float[]{0.75F, 0.75F, 0.75F, 0.75F, 0.75F, 0.75F};
   public static float[] AttributeUpgradeCost_AttributeMulti = new float[]{0.75F, 0.75F, 0.75F, 0.75F, 0.75F, 0.75F};
   public static float[][] AttibuteBonusPerRacialSkill = new float[JRMCoreH.Races.length][];
   public static float[][] cAttibuteBonusPerRacialSkill = new float[JRMCoreH.Races.length][];
   public static final int serverPlayerUpdateTick = 100;
   public static final int serverPlayerUpdateTick_Meditation = 50;
   public static final int serverPlayerUpdateTick_Data = 10;
   public static int serverPlayerUpdateTick_DataFamilyC = 100;
   public static boolean runFamilyCDataUpdateAsThread = true;
   public static final int fusionAttributeMulti = 100;
   public static final float FUSION_ATTR_MULTI = 2.0F;
   public static final float[] fusionAttributeMultis = new float[]{2.0F, 2.0F, 2.0F, 2.0F, 2.0F, 2.0F};
   public static final float FUSION_MASTERY_MULTI = 1.0F;
   public static final int regenerationStartTimer = 30;
   public static boolean cDebugInfo = true;
   public static boolean DebugInfo = true;

   public static int getTPGainEnergy(int type) {
      return type == 7 ? 1 : (type == 8 ? TPGainEnergy[type - 1] : TPGainEnergy[type]);
   }

   public static void init(Configuration config) {
      config.load();
      Property property = config.get("JBRA Configs", "Force JBRA", true);
      property.comment = "Client Sided! If 'true' it will force JBRA over other mods, if 'false' then not.";
      forceJBRA = property.getBoolean(true);
      property = config.get("general", "NPC Spawn Check", true);
      property.comment = "Server Sided! If 'true' NPCs will go through a Check for every player, to make sure they spawned, if 'false' then no check.  (may reduce lag if false)";
      cNPCSpawnCheck = property.getBoolean(true);
      NPCSpawnCheck = cNPCSpawnCheck;
      property = config.get("general", "Building Spawn Check", true);
      property.comment = "Server Sided! If 'true' Buildings will spawn, if 'false' then they will never spawn.  (may reduce lag if false)";
      cBuildingSpawnCheck = property.getBoolean(true);
      BuildingSpawnCheck = cBuildingSpawnCheck;
      Property BuildingSpawnAreaSize = config.get("general", "Blocks Between Random Buildings", 100);
      BuildingSpawnAreaSize.comment = "Server Sided! Size of the Spawn area between random buildings. From default 100 blocks between each other and can be reduced till 20 for more frequent spawns! (from 100 to 20)";
      cbuildingSpawnAreaSize = BuildingSpawnAreaSize.getInt(100);
      if (cbuildingSpawnAreaSize < 20) {
         cbuildingSpawnAreaSize = 20;
      }

      if (cbuildingSpawnAreaSize > 100) {
         cbuildingSpawnAreaSize = 100;
      }

      buildingSpawnAreaSize = cbuildingSpawnAreaSize;
      Property expGriOff1 = config.get("general", "Explosion Griefing Off", false);
      expGriOff1.comment = "Server Sided! If 'false' then Explosion Griefing is on, If 'true' then its off. (may reduce lag if true)";
      cexpGriOff = expGriOff1.getBoolean(false);
      expGriOff = cexpGriOff;
      Property re = config.get("general", "Energy Regen", true);
      re.comment = "Server Sided! If 'true' then the current Energy Regeneration Over Time will be used, If 'false' then no Energy Regenartion will be made. (may reduce lag if false)";
      cregen = re.getBoolean(true);
      regen = cregen;
      Property hre = config.get("general", "Health Regen", true);
      hre.comment = "Server Sided! If 'true' then the current Health Regeneration Over Time will be used, If 'false' then no Health Regenartion will be made. (may reduce lag if false)";
      hregen = hre.getBoolean(true);
      chregen = hregen;
      Property rer = config.get("general", "Energy Regen Rate", "normal");
      rer.comment = "Server Sided! Energy Rate can be, 'normal', 'slow', 'fast','faster'.";
      cregenRate = rer.getString();
      regenRate = cregenRate;
      rgnRt = cregenRate.equals("slow") ? 1 : (cregenRate.equals("fast") ? 4 : (cregenRate.equals("faster") ? 8 : 2));
      crgnRt = rgnRt;
      Property hrr = config.get("general", "Health Regen Rate", "normal");
      hrr.comment = "Server Sided! Health Regen can be, 'normal', 'slow', 'fast','faster'.";
      chRegenRate = hrr.getString();
      hRegenRate = chRegenRate;
      hRgnRt = chRegenRate.equals("slow") ? 1 : (chRegenRate.equals("fast") ? 4 : (chRegenRate.equals("faster") ? 8 : 2));
      chRgnRt = hRgnRt;
      Property rs = config.get("general", "Power Release", true);
      rs.comment = "Server Sided! If 'true' then the current Power Release will be used with percetage from 0-100%, If 'false' then the power will be always at 50%. (may reduce lag if false)";
      crelease = rs.getBoolean(true);
      release = crelease;
      Property ac = config.get("general", "Attribute Maximum", 10000);
      ac.comment = "Server Sided! Maximum Attribute a player can have. Maximum Attribute can be set between 100 and 1000000000. USING BIG NUMBERS CAN CAUSE ISSUES.";
      ctmx = ac.getInt();
      ctmx = ctmx < 100 ? 100 : (ctmx > 1000000000 ? 1000000000 : ctmx);
      tmx = ctmx;
      Property hs = config.get("general", "Player Size Change", true);
      hs.comment = "Server Sided! If 'true' then the size of the players will change depending on the attributs as planned, If 'false' then the size of the players will remain the Minecraft default (2 block height and 1 block width)";
      csizes = hs.getBoolean(true);
      sizes = csizes;
      Property ssu = config.get("general", "SSURL", "empty");
      ssu.comment = "Server Sided! Function not available yet, Don't Change!";
      cssurl = ssu.getString();
      ssurl = cssurl;
      Property ssu2 = config.get("general", "SSURL2", "empty");
      ssu2.comment = "Server Sided! Function not available yet, Don't Change!";
      cssurl2 = ssu2.getString();
      ssurl2 = cssurl2;
      Property ssco = config.get("general", "SSC", "empty");
      ssco.comment = "Server Sided! Function not available yet, Don't Change!";
      cssc = ssco.getString();
      ssc = cssc;
      Property sze = config.get("general", "Safe Zones Enabled", true);
      sze.comment = "Server Sided! If 'true' then Safe Zones around main NPCs will be enabled, If 'false' then Safe Zones will be disabled. (may reduce lag if false)";
      csfzns = sze.getBoolean(true);
      sfzns = csfzns;
      String[] da = new String[]{"wooden_door", "jinryuudragonblockc:tile.BlockHealingPodDoor", "crafting_table", "lever", "stone_button", "wooden_button"};
      sze = config.get("general", "Safe Zone RightClick Access", da);
      sze.comment = "Server Sided! List the Block names you want to be allowed to right click in Safe Zones. (may reduce lag if empty)";
      csfzna = sze.getStringList();
      sfzna = csfzna;
      Property kiadp = config.get("Energy Attack Configs", "Energy Attack Explosion Intensity", 4);
      kiadp.comment = "Server Sided! Energy Attacks like Ki or Ninjutsu. From 1 to 10 (default: 4) where 1 will get players less lag but ugly explosions and 10 will get players probably the best look but with the most lagg. (lower number will reduce lag)";
      ceaei = kiadp.getInt(4);
      if (ceaei < 1) {
         ceaei = 1;
      }

      if (ceaei > 10) {
         ceaei = 10;
      }

      eaei = ceaei;
      kiadp = config.get("Energy Attack Configs", "Energy Attack Explosion Size", 4);
      kiadp.comment = "Server Sided! Energy Attacks like Ki or Ninjutsu. From 0 to 100 (default: 4.0) where 1 will get players less lag but very small explosions and higher number will result in a very large explosion but with the most lagg, use it with caution! (lower number will reduce lag)";
      ceaes = kiadp.getDouble(4.0);
      if (ceaes < 0.0) {
         ceaes = 0.0;
      }

      if (ceaes > 100.0) {
         ceaes = 100.0;
      }

      eaes = ceaes;
      kiadp = config.get("Energy Attack Configs", "Energy Attack Size Limit", 5);
      kiadp.comment = "Server Sided! Energy Attacks like Ki or Ninjutsu. From 0 to 100 (default: 5). The higher number the larger explosions allowed, the more lagg will happen. 0 means No Limit, use it with caution!";
      ceaesl = kiadp.getInt(5);
      if (ceaesl < 0) {
         ceaesl = 0;
      }

      if (ceaesl > 100) {
         ceaesl = 100;
      }

      eaesl = ceaesl;
      kiadp = config.get("Energy Attack Configs", "Energy Attack Density Modifier", 5.0);
      kiadp.comment = "Server Sided! Energy Attacks like Ki. From 0.0 to 10.0 (default: 5.0). Explosions size with increased density stat will be affected by this modifier! Lower will reduce the explosion size while Higher numbers will increase the size based on density stat. 0 will disable explosions getting large! (lower number will reduce lag)";
      ceadm = (float)kiadp.getDouble(5.0);
      if (ceadm < 0.0F) {
         ceadm = 0.0F;
      }

      if (ceadm > 10.0F) {
         ceadm = 10.0F;
      }

      eadm = ceadm;
      kiadp = config.get("Energy Attack Configs", "Energy Attack Large Blast Size Modifier", 3.0);
      kiadp.comment = "Server Sided! Energy Attacks like Ki. From 1.0 to 10.0 (default: 3.0). Large Blasts are regular Blasts that have increased Size! Change this modifier to change the Large Blasts Size. (lower number will reduce lag)";
      cealbm = (float)kiadp.getDouble(3.0);
      if (cealbm < 1.0F) {
         cealbm = 1.0F;
      }

      if (cealbm > 10.0F) {
         cealbm = 10.0F;
      }

      ealbm = cealbm;
      Property DebugInfo = config.get("general", "Debug Info", false);
      DebugInfo.comment = "Server Sided! If 'true' the server will print informations like how much TP gained the players and so you will be able to search hackers or rule breakers";
      cDebugInfo = DebugInfo.getBoolean(false);
      JRMCoreConfig.DebugInfo = cDebugInfo;
      DebugInfo = config.get("general", "Player Kill Alignment Changing", true);
      DebugInfo.comment = "Server Sided! If 'true'(default) the PKer will suffer from alignment change depending on the one who was killed what alignment had at the time.";
      cKillAlgnmntChnge = DebugInfo.getBoolean(true);
      KillAlgnmntChnge = cKillAlgnmntChnge;
      DebugInfo = config.get("JBRA Configs", "Hide Hair When Helmet On", true);
      DebugInfo.comment = "Client Sided! If 'true' the you wont see hairs when someone wears a helmet, if 'false' you will always see everyones hair, regarless of helmet. (default: true)";
      HHWHO = DebugInfo.getBoolean(false);
      property = config.get("DBC Server Sided Configs", "Attributes Over Limit", true, "Server Sided! Allow Attributes Over Max Attribute limit with powerups");
      OverAtrLimit = property.getBoolean(true);
      OverAtrLimitO = property.getBoolean(true);
      String[] l = new String[]{"x2", "x3", "x4", "x5", "x10", "x20"};
      property = config.get("DBC Server Sided Configs", "Skill Kaioken - Name Changer", l, "Server Sided! Change only to your own responsibility!");
      String[] fls = new String[l.length + 1];
      fls[0] = "1";
      String[] sa = new String[l.length];
      int len = property.getStringList().length;
      String[] s = new String[l.length];

      for (int j = 0; j < l.length; j++) {
         s[j] = l[j];
      }

      for (int j = 0; j < l.length; j++) {
         String k = len > j ? property.getStringList()[j] : l[j];
         sa[j] = k;
         fls[j + 1] = k;
      }

      JRMCoreH.TransKaiNms = fls;
      JRMCoreH.TransKaiNmsO = fls;
      property.set(sa);
      int[] i = new int[]{100, 110, 120, 130, 140, 150, 160};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.TransKaiNms[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Skill Kaioken - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 10000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      float[] flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < 10 ? 10 : (k > 10000 ? 10000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransKaiDmg = flt;
      JRMCoreH.TransKaiDmgO = flt;
      property.set(sa);
      i = new int[]{100, 100, 100, 100, 100, 100};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.Races[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Skill Kaioken - Race Health Drain multiplier",
         s,
         "Server Sided! The numbers are meant to be in percentage. (From 0.0 to 10000.0)%."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < 0 ? 0 : (k > 10000 ? 10000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransKaiDrainRace = flt;
      JRMCoreH.TransKaiDrainORace = flt;
      property.set(sa);
      i = new int[]{50, 50, 50, 50, 50, 50, 50};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = "" + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Skill Kaioken - Level Health Drain multiplier",
         s,
         "Server Sided! The numbers are meant to be in percentage. (From 0.0 to 10000.0)%."
      );
      flt = new float[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j]) : i[j];
         k = k < 0 ? 0 : (k > 10000 ? 10000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
      }

      JRMCoreH.TransKaiDrainLevel = flt;
      JRMCoreH.TransKaiDrainOLevel = flt;
      i = new int[]{-1, -1, -1, -1, -1, -1};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.Races[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Skill OldKaiUnlock damage multiplier",
         s,
         "Server Sided! Mystic Form damage multiplier. -1 = Use old System (Power based off on Racial Skill level and power). The numbers are meant to be in percentage. (From 10.0 to 10000.0)%."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < 10 && k != -1 ? 10 : (k > 10000 ? 10000 : k);
         i[j] = k;
         flt[j] = k * (k == -1 ? 1.0F : 0.01F);
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      MysticDamMulti = flt;
      cMysticDamMulti = flt;
      property.set(sa);
      i = JRMCoreH.ArcRsrvMaxSkll;
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = "" + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs", "Racial Skill Arcosian - Power Point Max", s, "Server Sided! Arcosian Power Reserver Max. (From 0 to 1000000000)."
      );
      int[] flt3 = new int[i.length];
      len = property.getStringList().length;
      int highest = 0;
      cArcosianPPDamMultiHighest = 0;

      for (int j = 0; j < i.length; j++) {
         int value = len > j ? Integer.parseInt(property.getStringList()[j]) : i[j];
         value = value < 0 ? 0 : (value > 1000000000 ? 1000000000 : value);
         flt3[j] = value;
         if (value > highest) {
            highest = value;
            cArcosianPPDamMultiHighest = j;
         }
      }

      ArcosianPPMax = flt3;
      cArcosianPPMax = flt3;
      ArcosianPPDamMultiHighest = cArcosianPPDamMultiHighest;
      i = JRMCoreH.ArcRsrvGrowth;
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         int forms = JRMCoreH.trans[4].length;
         s[j] = (j - forms + 1 > 0 ? JRMCoreH.transNonRacial[j - forms + 1] : JRMCoreH.trans[4][j]) + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Power Point Growth (for Build 1.131 and above)",
         s,
         "Server Sided! Arcosian Power Reserver Gain. (0 = Disabled) (From 0 to 1000000000)."
      );
      flt3 = new int[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int value = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         value = value < 0 ? 0 : (value > 1000000000 ? 1000000000 : value);
         flt3[j] = value;
      }

      ArcosianPPGrowth = flt3;
      cArcosianPPGrowth = flt3;
      i = JRMCoreH.ArcRsrvPowCst;
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         int forms = JRMCoreH.trans[4].length;
         s[j] = (j - forms + 1 > 0 ? JRMCoreH.transNonRacial[j - forms + 1] : JRMCoreH.trans[4][j]) + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Power Point Cost (for Build 1.131 and above)",
         s,
         "Server Sided! Arcosian Power Reserver Cost. (0 = Disabled). (From 0 to 1000000000)."
      );
      flt3 = new int[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int value = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         value = value < 0 ? 0 : (value > 1000000000 ? 1000000000 : value);
         flt3[j] = value;
      }

      ArcosianPPCost = flt3;
      cArcosianPPCost = flt3;
      double[] i2 = new double[]{1.0, 1.0, 1.0, 1.0, 1.15, 1.15, 1.15, 1.15, 1.15, 1.15, 1.15};
      s = new String[i2.length];

      for (int j = 0; j < s.length; j++) {
         int forms = JRMCoreH.trans[4].length;
         s[j] = (j - forms + 1 > 0 ? JRMCoreH.transNonRacial[j - forms + 1] : JRMCoreH.trans[4][j]) + " " + i2[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Power Point damage multiplier (for Build 1.131 and above)",
         s,
         "Server Sided! Arcosian Power Reserver Damage Multiplier (From 0 to 1000000)."
      );
      flt = new float[i2.length];
      len = property.getStringList().length;

      for (int j = 0; j < i2.length; j++) {
         float value = (float)(len > j ? Float.parseFloat(property.getStringList()[j].split(" ")[1]) : i2[j]);
         value = value < 0.0F ? 0.0F : (value > 1000000.0F ? 1000000.0F : value);
         flt[j] = value;
      }

      ArcosianPPDamMulti = flt;
      cArcosianPPDamMulti = flt;
      i2 = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
      s = new String[i2.length];

      for (int j = 0; j < s.length; j++) {
         int forms = JRMCoreH.trans[4].length;
         s[j] = (j - forms + 1 > 0 ? JRMCoreH.transNonRacial[j - forms + 1] : JRMCoreH.trans[4][j]) + " " + i2[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Power Point damage multiplier from Points (for Build 1.131 and above)",
         s,
         "Server Sided! Arcosian Power Reserver Damage Multiplier based off on Points (-1 = Disabled)\nDefault Example: (0 Points = (1 + 0) * 1.15, max Points (2500) = This Config's Value (1 + 1) * 1.15) (From 0 to 1000000)."
      );
      flt = new float[i2.length];
      len = property.getStringList().length;

      for (int j = 0; j < i2.length; j++) {
         float value = (float)(len > j ? Float.parseFloat(property.getStringList()[j].split(" ")[1]) : i2[j]);
         value = value < 0.0F && value != -1.0F ? 0.0F : (value > 1000000.0F ? 1000000.0F : value);
         flt[j] = value;
      }

      ArcosianPPDamMultiPoint = flt;
      cArcosianPPDamMultiPoint = flt;
      double[][] AttibuteBonusPerRacialSkillFinals = new double[][]{
         {0.0, 0.1, 0.1, 0.0, 0.1},
         {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
         {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
         {0.0, 0.1, 0.1, 0.0, 0.12},
         {0.0, 0.0, 0.0, 0.0, 0.0, 0.01, 0.01, 0.0, 0.02},
         {0.0, 0.075, 0.075, 0.075, 0.0, 0.12}
      };

      for (int raceID = 0; raceID < JRMCoreH.Races.length; raceID++) {
         int nonRacialForms = NON_RACIAL_FORMS.length;
         int racialForms = JRMCoreH.trans[raceID].length;
         i2 = new double[racialForms + nonRacialForms];

         for (int j = 0; j < i2.length; j++) {
            i2[j] = 100.0;
         }

         s = new String[i2.length];

         for (int j = 0; j < s.length; j++) {
            s[j] = (j < racialForms ? JRMCoreH.trans[raceID][j] : NON_RACIAL_FORMS[j - racialForms]) + " " + (int)i2[j];
         }

         property = config.get(
            "DBC Server Sided Configs",
            "Skill Kaioken - " + JRMCoreH.Races[raceID] + " Race Form Health Drain multiplier",
            s,
            "Server Sided! The numbers are meant to be in percentage. (From 0.0 to 1000000.0)%."
         );
         flt = new float[i2.length];
         sa = new String[i2.length];
         len = property.getStringList().length;

         for (int j = 0; j < i2.length; j++) {
            float value = (float)(len > j ? Double.parseDouble(property.getStringList()[j].split(" ")[1]) : i2[j]);
            value = value < 0.0F ? 0.0F : (value > 1000000.0F ? 1000000.0F : value);
            i2[j] = value;
            flt[j] = value * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i2[j]);
         }

         KaiokenFormHealthCost[raceID] = flt;
         cKaiokenFormHealthCost[raceID] = flt;
         property.set(sa);
         i2 = new double[racialForms + NON_RACIAL_FORMS.length];

         for (int j = 0; j < i2.length; j++) {
            i2[j] = AttibuteBonusPerRacialSkillFinals[raceID][j];
         }

         s = new String[i2.length];

         for (int j = 0; j < s.length; j++) {
            int forms = JRMCoreH.trans[raceID].length;
            s[j] = (j - forms >= 0 ? NON_RACIAL_FORMS[j - forms] : JRMCoreH.trans[raceID][j]) + " " + i2[j];
         }

         property = config.get(
            "DBC Server Sided Configs",
            JRMCoreH.Races[raceID] + " Race Bonus Attribute Multiplier Per Racial Skill Level",
            s,
            "Server Sided! This System was originally only for Humans, Namekians and Majins to balance their Form Attribute multipliers with Saiyans due to only having a few Forms (From 0.0 to 1000000)."
         );
         flt = new float[i2.length];
         len = property.getStringList().length;

         for (int j = 0; j < i2.length; j++) {
            float value = (float)(len > j ? Double.parseDouble(property.getStringList()[j].split(" ")[1]) : i2[j]);
            value = value < 0.0F ? 0.0F : (value > 1000000.0F ? 1000000.0F : value);
            i2[j] = value;
            flt[j] = value;
         }

         AttibuteBonusPerRacialSkill[raceID] = flt;
         cAttibuteBonusPerRacialSkill[raceID] = flt;
      }

      String[] TransKaiNms = new String[]{"UI"};
      i = new int[]{300};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = TransKaiNms[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Skill Ultra Instinct - Attribute multiplier",
         s,
         "(UNUSED!) Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransMngDmg = flt;
      JRMCoreH.TransMngDmgO = flt;
      property.set(sa);
      i = new int[]{100, 180, 220, 240, 200, 270, 300, 130, 210, 320, 360, 330, 220, 270, 330, 380};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[1][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Saiyan - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      float[][] flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            flt2[j][j2] = k * (j == 3 && j2 == 1 ? 0.88F : 1.0F) * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransSaiStBnP = flt2;
      JRMCoreH.TransSaiStBnPO = flt2;
      property.set(sa);
      i = new int[]{100, 180, 220, 240, 200, 270, 300, 130, 210, 320, 360, 330, 220, 270, 330, 380};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[1][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Half-Saiyan - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            flt2[j][j2] = k * (j == 3 && j2 == 1 ? 0.88F : 1.0F) * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransHalfSaiStBnP = flt2;
      JRMCoreH.TransHalfSaiStBnPO = flt2;
      property.set(sa);
      i = new int[]{30, 40, 60, 80, 100, 220, 270, 360};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[4][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            flt2[j][j2] = k * (j == 3 && j2 == 1 ? 0.88F : 1.0F) * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransFrStBnP = flt2;
      JRMCoreH.TransFrStBnPO = flt2;
      property.set(sa);
      i = new int[]{100, 170, 200, 310};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[0][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Human - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            float sc = 1.0F;
            if (j == 2 && j2 == 1) {
               sc = 0.8F;
            } else if (j != 0 && j2 == 1) {
               sc = 1.131F;
            }

            flt2[j][j2] = k * sc * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransHmStBnP = flt2;
      JRMCoreH.TransHmStBnPO = flt2;
      property.set(sa);
      i = new int[]{100, 170, 200, 310};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[3][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Namekian - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            float sc = 1.0F;
            if (j == 2 && j2 == 1) {
               sc = 0.8F;
            } else if (j == 2 && j2 == 3) {
               sc = 1.0F;
            } else if (j != 0 && j2 == 3) {
               sc = 1.131F;
            }

            flt2[j][j2] = k * sc * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransNaStBnP = flt2;
      JRMCoreH.TransNaStBnPO = flt2;
      property.set(sa);
      i = new int[]{100, 160, 200, 175, 280};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[5][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Majin - Damage multiplier",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!"
      );
      flt2 = new float[i.length][6];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         for (int j2 = 0; j2 < 6; j2++) {
            int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
            k = k < 10 ? 10 : (k > 100000 ? 100000 : k);
            i[j] = k;
            float sc = 1.0F;
            flt2[j][j2] = k * sc * 0.01F;
            sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
         }
      }

      JRMCoreH.TransMaStBnP = flt2;
      JRMCoreH.TransMaStBnPO = flt2;
      property.set(sa);
      i = new int[]{100, -20, -30, -40, -5, -20, -100, -10, -40, -20, -40, -30, -20, -20, -50, -40};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[1][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Saiyan - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000%. The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransSaiRgn = flt;
      JRMCoreH.TransSaiRgnO = flt;
      property.set(sa);
      i = new int[]{100, -20, -30, -40, -5, -20, -100, -10, -40, -20, -40, -30, -20, -20, -50, -40};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[1][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Half-Saiyan - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000%. The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransHalfSaiRgn = flt;
      JRMCoreH.TransHalfSaiRgnO = flt;
      property.set(sa);
      i = new int[]{300, 250, 200, 150, 100, -50, -100, -40};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[4][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Arcosian - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000% The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransFrRgn = flt;
      JRMCoreH.TransFrRgnO = flt;
      property.set(sa);
      i = new int[]{100, -50, -25, -20};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[0][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Human - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000% The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransHmRgn = flt;
      JRMCoreH.TransHmRgnO = flt;
      property.set(sa);
      i = new int[]{100, -50, -25, -20};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[3][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Namekian - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000% The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransNaRgn = flt;
      JRMCoreH.TransNaRgnO = flt;
      property.set(sa);
      i = new int[]{100, 0, 0, 50, -20};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.trans[5][j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Racial Skill Majin - Ki Regeneration multipliers",
         s,
         "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000% The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int k = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         k = k < -1000 ? -1000 : (k > 1000 ? 1000 : k);
         i[j] = k;
         flt[j] = k * 0.01F;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      JRMCoreH.TransMaRgn = flt;
      JRMCoreH.TransMaRgnO = flt;
      property.set(sa);
      property = config.get("general", "Skill Meditation - category", "active");
      property.comment = "Server Sided! It can be either 'passive' or 'active'. If it is 'passive' upgrading this skill will increase the regen rate for Body, Energy and Stamina (Survival Oriented). If it is 'active' upgrading this skill will only work if Release key is pressed and basicly functions as an energy recharge (Fighter/Console Oriented)";
      String tempcSklMedCat = property.getString();
      cSklMedCat = tempcSklMedCat.equals("active") ? 0 : 1;
      SklMedCat = cSklMedCat;
      property = config.get("general", "Skill Meditation - max rate at lvl 10", 75);
      property.comment = "Server Sided! The maximum rate at skill level 10 in percentage. Rate can be from 0 to 500 (default: 75) It will increase the regen or recharge with the max rate devided by 10 multiplied with skill level. (default 50% max rate at lvl 1 will become 5%)";
      cSklMedRate = property.getInt() * 0.01F;
      if (cSklMedRate < 0.0F) {
         cSklMedRate = 0.0F;
      }

      if (cSklMedRate > 5.0F) {
         cSklMedRate = 5.0F;
      }

      SklMedRate = cSklMedRate;
      property = config.get("general", "Power Release - Still Stand", true);
      property.comment = "Server Sided! If 'true' then people wont be able to move while Releasing, If 'false' then players can move while Releasing";
      creleaseStop = property.getBoolean(true);
      releaseStop = creleaseStop;
      property = config.get("general", "Training Point Gain - TP amount gained", 2);
      property.comment = "Server Sided! Tp gain can be, from 1 to 100. (default: 2)";
      ctpgn = property.getInt();
      if (ctpgn < 0) {
         ctpgn = 0;
      }

      if (ctpgn > 100) {
         ctpgn = 100;
      }

      tpgn = ctpgn;
      property = config.get("general", "Training Point Gain - 'TP gain / melee' rate", 5);
      property.comment = "Server Sided! Based on Mind Attribute increase. With every 'configured amount' in Mind attribute the TP gain will increase by 1 OR with the amount configured at 'Training Point Gain - TP amount gained'. Rate can be from 1 to 10000 (default: 5) ";
      cTpgnRt = property.getInt();
      if (cTpgnRt < 1.0F) {
         cTpgnRt = 1.0F;
      }

      if (cTpgnRt > 10000.0F) {
         cTpgnRt = 10000.0F;
      }

      TpgnRt = cTpgnRt;
      property = config.get("DBC Server Sided Configs", "Kaioken - Sustainable Super", false);
      property.comment = "Server Sided! If 'true' then the Kaioken will be sustainable in any transformation, If 'false' then Kaioken will be hard to maintain with every transformation. (This WILL make the game more unbalanced. default: false)";
      csskai = property.getBoolean(false);
      sskai = csskai;
      property = config.get("DBC Server Sided Configs", "Fusion - Dance fusion", true);
      property.comment = "Server Sided! If 'true' then Fusion will be available to use, If 'false' then Fusion wont be usable. (For balancing reasons default: true)";
      cfuzn = property.getBoolean(true);
      fuzn = cfuzn;
      property = config.get("DBC Server Sided Configs", "Fusion - Dance fusion - NoFuse Time", 10);
      property.comment = "Server Sided! You can change the duration in minutes after a fusion has ended, how long one can't perform fustion again. Time in minutes can be from 1 to 1000 (default: 10) ";
      int tm = property.getInt();
      if (tm < 1) {
         int var415 = true;
      } else if (tm > 1000) {
         int var416 = 1000;
      }

      FznOverTime = property.getInt() * 12;
      property = config.get("DBC Server Sided Configs", "Fusion - Dance fusion - Fuse Time", 5);
      property.comment = "Server Sided! You can change the duration in minutes that a fusion can last. Time in minutes can be from 1 to 30 (default: 5) ";
      tm = property.getInt();
      if (tm < 1) {
         int var418 = true;
      } else if (tm > 30) {
         int var419 = 30;
      }

      FznTime = property.getInt() * 12;
      property = config.get("DBC Server Sided Configs", "Lock On", true);
      property.comment = "Server Sided! If 'true' then Locking On is enabled, If 'false' then Locking On is disabled. (default: true)";
      clockon = property.getBoolean(true);
      lockon = clockon;
      property = config.get("general", "Stat Defense - Passive output", 20);
      property.comment = "Server Sided! With this you can change the passive defense output to an extend, numbers are meant to be in percentage. Rate can be from 0 to 50 (default: 20) ";
      cStatPasDef = property.getInt();
      if (cStatPasDef < 0) {
         cStatPasDef = 0;
      }

      if (cStatPasDef > 50) {
         cStatPasDef = 50;
      }

      StatPasDef = cStatPasDef;
      property = config.get("DBC Server Sided Configs", "Status Effect - Majin", 10);
      property.comment = "Server Sided! With this you can change the bonus power percentage for the Majin seal status effect. Rate can be from 1 to 100000 (default: 10) ";
      cmjn = property.getInt();
      if (cmjn < 1) {
         cmjn = 1;
      }

      if (cmjn > 100000) {
         cmjn = 100000;
      }

      mjn = cmjn;
      property = config.get("DBC Server Sided Configs", "Status Effect - Legendary - Transformation Boost", 20);
      property.comment = "Server Sided! With this you can change the bonus power percentage for the Legendary status effect. Only applied when transformed! Rate can be from 1 to 100000 (default: 20) ";
      clgnd = property.getInt();
      if (clgnd < 1) {
         clgnd = 1;
      }

      if (clgnd > 100000) {
         clgnd = 100000;
      }

      lgnd = clgnd;
      String legendaryForms = "SS,SSG2,SSG3,SSFullPow,SS2,SS3,Golden,SS4,Full,Buffed,Giant,Form5,Ultimate,Evil,Pure";
      property = config.get(
         "DBC Server Sided Configs",
         "Status Effect - Legendary - Boosted Transformations",
         "SS,SSG2,SSG3,SSFullPow,SS2,SS3,Golden,SS4,Full,Buffed,Giant,Form5,Ultimate,Evil,Pure"
      );
      property.comment = "Server Sided! The listed transformation initials given the Legendary Boost only! (default: SS,SSG2,SSG3,SSFullPow,SS2,SS3,Golden,SS4,Full,Buffed,Giant,Form5,Ultimate,Evil,Pure)";
      clgndb = property.getString();
      lgndb = clgndb;
      property = config.get("DBC Server Sided Configs", "Status Effect - Legendary - Server limit", 1);
      property.comment = "Server Sided! With this you can change how many players may use this state. Can be from 0 to 1000 (default: 1) ";
      cselgnd = property.getInt();
      if (cselgnd < 0) {
         cselgnd = 0;
      }

      if (cselgnd > 1000) {
         cselgnd = 1000;
      }

      selgnd = cselgnd;
      property = config.get("DBC Server Sided Configs", "Status Effect - Legendary - Chance to get it", 10);
      property.comment = "Server Sided! The chance (in percentage) for a player to receive the Legendary Status Effect every 20-30 mins, regardless of race. Can be from 0 to 100 (default: 10) and 0 will result in close to never. (This config will only be enabled if the 'For every day a Lucky player' config conditions are not met, furthermore setting this to 100 will result in the same effect as the 'For every day a Lucky player' config)";
      cselgndc = property.getInt();
      if (cselgndc < 0) {
         cselgndc = 0;
      }

      if (cselgndc > 100) {
         cselgndc = 100;
      }

      selgndc = cselgndc;
      property = config.get("DBC Server Sided Configs", "Status Effect - Legendary - For every day a Lucky player", 20);
      property.comment = "Server Sided! Above this amount a player will be always selected to receive the Legendary status effect, regardless of race. Can be from 0 to 500 (default: 20) and 0 will result in always a player to receive it every 20-30 mins. (Setting this to 0 will disable the 'Chance to get it' config.)";
      cselgndc2 = property.getInt();
      if (cselgndc2 < 0) {
         cselgndc2 = 0;
      }

      if (cselgndc2 > 500) {
         cselgndc2 = 500;
      }

      selgndc2 = cselgndc2;
      property = config.get("general", "Offline Server Protector", 0);
      property.comment = "Server Sided! This will prevent some gui interactions like attribute, skill upgrades or deleting or tech stuff for a configured time. The time is in seconds! Can be from 0 to 300 (default: 0) !For normal servers this should never be used!";
      cosbic = property.getInt();
      if (cosbic < 0) {
         cosbic = 0;
      }

      if (cosbic > 300) {
         cosbic = 300;
      }

      osbic = cosbic;
      property = config.get("DBC Server Sided Configs", "Racial Skill Arcosian - Power Point multiplier", 1.0);
      property.comment = "Server Sided! You can multiply the base PP generation for the Arcosian Power Reserves. Can be from 0.5 to 50.0 (default: 1.0)";
      cappm = property.getDouble();
      if (cappm < 0.5) {
         cappm = 0.5;
      }

      if (cappm > 50.0) {
         cappm = 50.0;
      }

      appm = cappm;
      property = config.get("general", "Core System - Attribute Cost mulipier", 0.5);
      property.comment = "Server Sided! Attribute's TP cost mulipier. The higher amount the higher will be the increase after each upgrade.Can be from 0 to 1000.0 (default: 0.5)";
      catcm = property.getDouble();
      if (catcm < 0.0) {
         catcm = 0.0;
      }

      if (catcm > 1000.0) {
         catcm = 1000.0;
      }

      atcm = catcm;
      property = config.get("NarutoC Server Sided Configs", "Clan Skill Hyuuga - Energy Damage percentage", 10);
      property.comment = "Server Sided! Energy Damage percentage, multiplied with each Hyuuha clan skill level! So if Hyuuga clan skill level is 3 and the this config is 10, the energy damage will be 30% of the player melee damage.Can be from 1 to 100 (default: 10)";
      chedp = property.getInt(10);
      if (chedp < 1) {
         chedp = 1;
      }

      if (chedp > 100) {
         chedp = 100;
      }

      hedp = chedp;
      property = config.get("general", "Player Punch stamina cost multiplier", 1.0);
      property.comment = "Server Sided! Player Punch stamina cost multiplier value from 0 to 30. (default: 1.00)";
      ccnfPnchc = property.getDouble(1.0);
      if (ccnfPnchc < 0.0) {
         ccnfPnchc = 0.0;
      }

      if (ccnfPnchc > 30.0) {
         ccnfPnchc = 30.0;
      }

      cnfPnchc = ccnfPnchc;
      s = new String[9];

      for (int j = 0; j < s.length; j++) {
         s[j] = "true";
      }

      property = config.get(
         "DBC Ki Attack Server Sided Configs",
         "Enabled Ki Attack Types (Wave, Ball, Disk, Laser, Spiral, Large Ball, Barrage, Shield, Explosion)",
         s,
         "Server Sided! Disabled ki attack types will make them instantly die when spawned."
      );

      for (int j = 0; j < s.length; j++) {
         cdat5695[j] = Boolean.parseBoolean(property.getStringList()[j]);
         dat5695[j] = cdat5695[j];
      }

      String[] names = new String[]{"Wave", "Ball", "Disk", "Laser", "Spiral", "Large Ball", "Barrage", "Shield", "Explosion"};
      String[] names2 = new String[]{"Wave", "Ball", "Disk", "Laser", "Spiral", "Large_Ball", "Barrage", "Shield", "Explosion"};

      for (int k = 0; k < names.length; k++) {
         s = new String[3];

         for (int j = 0; j < s.length; j++) {
            s[j] = "1.0";
         }

         property = config.get(
            "DBC Ki Attack Server Sided Configs",
            "Ki Attack stat multipliers for (" + names[k] + ") (Speed, Damage, Cost) value from 0 to 1000 (defaults: 1.00).",
            s,
            "Server Sided! Stats are multiplied after attack is created."
         );

         for (int j = 0; j < s.length; j++) {
            cdat5696[k][j] = Double.parseDouble(property.getStringList()[j]);
            if (cdat5696[k][j] < 0.0) {
               cdat5696[k][j] = 0.0;
            }

            if (cdat5696[k][j] > 1000.0) {
               cdat5696[k][j] = 1000.0;
            }

            dat5696[k][j] = cdat5696[k][j];
         }
      }

      String[] data = new String[]{"Snowball 2", "Arrow 2", "SmallFireball 2", "Fireball 2"};
      property = config.get(
         "DBC Ki Attack Server Sided Configs",
         "List of Entities EVERY Ki Attacks react to. (Name, Reaction)(Reactions: Ki Attack will... 1 = Die from it | 2 = Destroy it | 3 = Destroy it IF effect is on | 4 = Damage it | 5 = Damage it IF effect is on)",
         data,
         "Server Sided! Ki Attacks React to these Entities."
      );
      String[] datasListNames = property.getStringList();

      for (int k = 0; k < datasListNames.length; k++) {
         if (datasListNames[k].split(" ").length > 1) {
            dat5702.put(datasListNames[k].split(" ")[0], Byte.parseByte(datasListNames[k].split(" ")[1]));
         }
      }

      for (int k = 0; k < names.length; k++) {
         String[] datas2 = new String[]{""};
         property = config.get(
            "DBC Ki Attack Server Sided Configs",
            "List of Entities '"
               + names[k]
               + "' Ki Attacks react to. (Name, Reaction)(Reactions: Ki Attack will... 1 = Die from it | 2 = Destroy it | 3 = Destroy it IF effect is on | 4 = Damage it | 5 = Damage it IF effect is on)",
            datas2,
            "Server Sided! Ki Attacks React to these Entities."
         );
         String[] datasListNames2 = property.getStringList();

         for (int j = 0; j < datasListNames2.length; j++) {
            if (datasListNames2[j].split(" ").length > 1) {
               dat5703.put(k + "." + datasListNames2[j].split(" ")[0], Byte.parseByte(datasListNames2[j].split(" ")[1]));
            }
         }
      }

      property = config.get("Console Configs", "Entity Hit by Ki Attack Console message", false);
      property.comment = "Server Sided! If true it will type in the Names of Entities that got hit by a Ki Attack and can be used for the 'List of Entities Ki Attacks react to' config. (default: false)";
      cdat5704 = property.getBoolean();
      dat5704 = cdat5704;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Clash Destroy weaker one", 50.0);
      property.comment = "Server Sided! When Ki Attacks clash if one is x (config amount) times weaker than the other one destroy it. Value from 1 to 1000. IF value is 1 then the config is off. (default: 50.00)";
      cdat5705 = property.getDouble(50.0);
      if (cdat5705 < 1.0) {
         cdat5705 = 1.0;
      }

      if (cdat5705 > 1000.0) {
         cdat5705 = 1000.0;
      }

      dat5705 = cdat5705;
      data = new String[names.length - 2];

      for (int k = 0; k < names.length - 2; k++) {
         if (k == 6) {
            data[k] = names2[k] + " false";
         } else {
            data[k] = names2[k] + " true";
         }
      }

      property = config.get(
         "DBC Ki Attack Server Sided Configs", "Ki Attack knockback", data, "Server Sided! If true Ki Attacks will knockback hit targets as normal."
      );
      data = property.getStringList();

      for (int k = 0; k < names.length - 2; k++) {
         cdat5706[k] = Boolean.parseBoolean(data[k].split(" ")[1]);
         dat5706[k] = cdat5706[k];
      }

      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Shields and Explosions give Ki Tech experience percentage", 0);
      property.comment = "Server Sided! Ki Shields and Explosions give Ki Tech experience percentage. Value from 0 to 100. IF value is 0 then the config is off. (default: 0)";
      cdat5707 = (byte)property.getInt(0);
      if (cdat5707 < 0) {
         cdat5707 = 0;
      }

      if (cdat5707 > 100) {
         cdat5707 = 100;
      }

      dat5707 = cdat5707;
      String[] finalDatas = new String[]{"EFFECT_OFF true", "EFFECT_ON true"};
      property = config.get("DBC Ki Attack Server Sided Configs", "Spirals go through entities", finalDatas);
      property.comment = "Server Sided! If true Spirals will go through entities.";
      String[] datasListNames2 = property.getStringList();
      cdat5708[0] = Boolean.parseBoolean(datasListNames2[0].split(" ")[1]);
      cdat5708[1] = Boolean.parseBoolean(datasListNames2[1].split(" ")[1]);
      dat5708[0] = cdat5708[0];
      dat5708[1] = cdat5708[1];
      property = config.get("DBC Ki Attack Server Sided Configs", "Final explosion vanish on death", true);
      property.comment = "Server Sided! If true Final explosion will vanish if the user is dead. (default: true)";
      cdat5710 = property.getBoolean();
      dat5710 = cdat5710;
      data = new String[names.length];

      for (int k = 0; k < names.length; k++) {
         data[k] = names2[k] + " true";
      }

      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Attack Effect can be used", data, "Server Sided! If true Ki Attack Effect can be used.");
      data = property.getStringList();

      for (int k = 0; k < names.length; k++) {
         cdat5709[k] = Boolean.parseBoolean(data[k].split(" ")[1]);
         dat5709[k] = cdat5709[k];
      }

      property = config.get("general", "Racial Skill from tp", true);
      property.comment = "Server Sided! If true Racial Skill can be leveled up using tp. (default: true)";
      cdat5711 = property.getBoolean();
      dat5711 = cdat5711;
      property = config.get("DBC Ki Attack Server Sided Configs", "Explosion Max Age Ticks", 60);
      property.comment = "Server Sided! Explosion Max Age Ticks value from 0 to 1000. (default: 60)";
      cdat5697 = property.getInt(60);
      if (cdat5697 < 0) {
         cdat5697 = 0;
      }

      if (cdat5697 > 1000) {
         cdat5697 = 1000;
      }

      dat5697 = cdat5697;
      property = config.get("DBC Ki Attack Server Sided Configs", "Explosion Self Harm Damage Percentage", 25);
      property.comment = "Server Sided! Explosion Self Harm Damage Percentage value from 0 to 100. (default: 25)";
      cdat5701 = property.getInt(25);
      if (cdat5701 < 0.0) {
         cdat5701 = 0.0;
      }

      if (cdat5701 > 100.0) {
         cdat5701 = 100.0;
      }

      dat5701 = cdat5701;
      property = config.get("general", "Meditation Stamina Drain multiplier", 0.1);
      property.comment = "Server Sided! Meditation Stamina Drain multiplier value from 0 to 10000. (default: 0.1)";
      cdat5698 = property.getDouble(0.1);
      if (cdat5698 < 0.0) {
         cdat5698 = 0.0;
      }

      if (cdat5698 > 10000.0) {
         cdat5698 = 10000.0;
      }

      dat5698 = cdat5698;
      property = config.get("general", "Ki Blade and Scythe Ki Fist Damage multiplier", 1.0);
      property.comment = "Server Sided! Ki Blade and Scythe Ki Fist Damage multiplier value from 0 to 100. (default: 1.00)";
      cdat5699 = property.getDouble(1.0);
      if (cdat5699 < 0.0) {
         cdat5699 = 0.0;
      }

      if (cdat5699 > 100.0) {
         cdat5699 = 100.0;
      }

      dat5699 = cdat5699;
      property = config.get("general", "Ki Blade and Scythe Ki Infuse Damage multiplier", 1.0);
      property.comment = "Server Sided! Ki Blade and Scythe Ki Infuse Damage multiplier value from 0 to 100. (default: 1.00)";
      cdat5700 = property.getDouble(1.0);
      if (cdat5700 < 0.0) {
         cdat5700 = 0.0;
      }

      if (cdat5700 > 100.0) {
         cdat5700 = 100.0;
      }

      dat5700 = cdat5700;
      property = config.get("general", "Mystic Form Level Loss Timer multiplier", 2.0);
      property.comment = "Server Sided! Mystic Form Level Loss Timer multiplier value from 0 to 100 (0 = timer disabled). (default: 2.00)";
      cMystTim = property.getDouble(2.0);
      if (cMystTim < 0.0) {
         cMystTim = 0.0;
      }

      if (cMystTim > 100.0) {
         cMystTim = 100.0;
      }

      MystTim = cMystTim;
      property = config.get("general", "Player Flying Speed multiplier", 2.0);
      property.comment = "Server Sided! Player Flying Speed multiplier value from 0 to 100. (default: 2.0)";
      cFlngspd = property.getDouble(2.0);
      if (cFlngspd < 0.0) {
         cFlngspd = 0.0;
      }

      if (cFlngspd > 100.0) {
         cFlngspd = 100.0;
      }

      Flngspd = cFlngspd;
      property = config.get("general", "Player Update Timer (Tick)", 100);
      property.comment = "Server Sided! Player Update Timer (for things like receiving missions from Server, and entering the teleporter blocks for the Hyperbolic Time Chamber) value from 1 to 10000. (Lower number can cause more lag) (default: 100)";
      cplUpd = property.getInt(100);
      if (cplUpd < 1) {
         cplUpd = 1;
      }

      if (cplUpd > 10000) {
         cplUpd = 10000;
      }

      plUpd = cplUpd;
      property = config.get("general", "ExtendedPlayer - Blocking Variable ID", 20);
      property.comment = "Server Sided! ExtendedPlayer - Blocking Variable. Has possibility of causing issues once changed. (default: 20)";
      cExtendedPlayerBlockID = (byte)property.getInt(20);
      if (cExtendedPlayerBlockID < 0) {
         cExtendedPlayerBlockID = 0;
      }

      if (cExtendedPlayerBlockID > 100) {
         cExtendedPlayerBlockID = 100;
      }

      ExtendedPlayerBlockID = cExtendedPlayerBlockID;
      property = config.get("general", "ExtendedPlayer - Othercode Variable ID", 21);
      property.comment = "Server Sided! ExtendedPlayer - Othercode Variable. Has possibility of causing issues once changed. (default: 21)";
      cExtendedPlayerOtherID = (byte)property.getInt(21);
      if (cExtendedPlayerOtherID < 0) {
         cExtendedPlayerOtherID = 0;
      }

      if (cExtendedPlayerOtherID > 100) {
         cExtendedPlayerOtherID = 100;
      }

      ExtendedPlayerOtherID = cExtendedPlayerOtherID;
      property = config.get("general", "ExtendedPlayer - Haircode Variable ID", 22);
      property.comment = "Server Sided! ExtendedPlayer - Haircode Variable. Has possibility of causing issues once changed. (default: 22)";
      cExtendedPlayerHairID = (byte)property.getInt(22);
      if (cExtendedPlayerHairID < 0) {
         cExtendedPlayerHairID = 0;
      }

      if (cExtendedPlayerHairID > 100) {
         cExtendedPlayerHairID = 100;
      }

      ExtendedPlayerHairID = cExtendedPlayerHairID;
      data = new String[names.length - 2];

      for (int k = 0; k < names.length - 2; k++) {
         data[k] = names2[k] + " 0.01 0.1";
      }

      property = config.get(
         "DBC Ki Attack Server Sided Configs",
         "Ki Attack Sizes (Min Max)",
         data,
         "Server Sided! Ki Attack Hitbox and Render Sizes (Min Max) (default: 0.01 0.1)."
      );
      data = property.getStringList();

      for (int k = 0; k < names.length - 2; k++) {
         String[] line = data[k].split(" ");
         cKiSizeMin[k] = Double.parseDouble(line[1]);
         if (cKiSizeMin[k] < 0.0) {
            cKiSizeMin[k] = 0.0;
         }

         if (cKiSizeMin[k] > 10000.0) {
            cKiSizeMin[k] = 10000.0;
         }

         KiSizeMin[k] = cKiSizeMin[k];
         cKiSizeMax[k] = Double.parseDouble(line[2]);
         if (cKiSizeMax[k] < 0.0) {
            cKiSizeMax[k] = 0.0;
         }

         if (cKiSizeMax[k] > 10000.0) {
            cKiSizeMax[k] = 10000.0;
         }

         KiSizeMax[k] = cKiSizeMax[k];
      }

      data = new String[3];

      for (int k = 0; k < 3; k++) {
         data[k] = names2[k] + " 0.03 0.1";
      }

      property = config.get(
         "NC Jutsu Server Sided Configs",
         "Jutsu Attack Sizes (Min Max)",
         data,
         "Server Sided! Jutsu Attack Hitbox and Render Sizes (Min Max) (default: 0.01 0.1)."
      );
      data = property.getStringList();

      for (int k = 0; k < 3; k++) {
         String[] line = data[k].split(" ");
         cJutsuSizeMin[k] = Double.parseDouble(line[1]);
         if (cJutsuSizeMin[k] < 0.0) {
            cJutsuSizeMin[k] = 0.0;
         }

         if (cJutsuSizeMin[k] > 10000.0) {
            cJutsuSizeMin[k] = 10000.0;
         }

         JutsuSizeMin[k] = cJutsuSizeMin[k];
         cJutsuSizeMax[k] = Double.parseDouble(line[2]);
         if (cJutsuSizeMax[k] < 0.0) {
            cJutsuSizeMax[k] = 0.0;
         }

         if (cJutsuSizeMax[k] > 10000.0) {
            cJutsuSizeMax[k] = 10000.0;
         }

         JutsuSizeMax[k] = cJutsuSizeMax[k];
      }

      data = new String[names.length - 3];

      for (int k = 0; k < names.length - 3; k++) {
         data[k] = names2[k] + " " + (k == 0 ? "true" : "false");
      }

      property = config.get(
         "DBC Ki Attack Server Sided Configs",
         "Continues Ki Attacks. User must look forward to hold an attack or it will hit entities once.",
         data,
         "Server Sided! Continues Ki Attacks."
      );
      data = property.getStringList();

      for (int k = 0; k < names.length - 3; k++) {
         String[] line = data[k].split(" ");
         cContinuesKiAttacks[k] = Boolean.parseBoolean(line[1]);
         ContinuesKiAttacks[k] = cContinuesKiAttacks[k];
      }

      data = new String[3];

      for (int k = 0; k < 3; k++) {
         data[k] = names2[k] + " " + (k == 0 ? "true" : "false");
      }

      property = config.get(
         "NC Jutsu Server Sided Configs",
         "Continues Jutsu Attacks.  User must look forward to hold an attack or it will hit entities once.",
         data,
         "Server Sided! Continues Jutsu Attacks."
      );
      data = property.getStringList();

      for (int k = 0; k < 3; k++) {
         String[] line = data[k].split(" ");
         cContinuesJutsuAttacks[k] = Boolean.parseBoolean(line[1]);
         ContinuesJutsuAttacks[k] = cContinuesJutsuAttacks[k];
      }

      property = config.get("Energy Attack Configs", "Continues Energy Attacks Die if Target Moves Away", false);
      property.comment = "Server Sided! Continues Energy Attacks Die if Target Moves Away. (default: false)";
      cWavesDieWhenTargetAway = property.getBoolean(false);
      WavesDieWhenTargetAway = cWavesDieWhenTargetAway;
      property = config.get("Energy Attack Configs", "Continues Energy Attacks Shrink if User Moves or Looks Away", true);
      property.comment = "Server Sided! Continues Energy Attacks Shrink if User Moves or Looks Away. (default: true)";
      cWavesShrinkOnceLetGo = property.getBoolean(true);
      WavesShrinkOnceLetGo = cWavesShrinkOnceLetGo;
      String powerFormula = "(Damage/2)+(Speed*2)+(Density*10)+(1)";
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Attack Power Formula", "(Damage/2)+(Speed*2)+(Density*10)+(1)");
      property.comment = "Server Sided! Ki Attack Power Formula. Used to calculate an attack's power when clashing with another ki attack (default: (Damage/2)+(Speed*2)+(Density*10)+(1))";
      cKiAttackPowerFormula = property.getString();
      KiAttackPowerFormula = cKiAttackPowerFormula;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Attacks scale with user's height", true);
      property.comment = "Server Sided! Ki Attacks scale with user's height. (default: true)";
      cKiAttackScalesWithUser = property.getBoolean(true);
      KiAttackScalesWithUser = cKiAttackScalesWithUser;
      property = config.get("NC Jutsu Server Sided Configs", "Jutsus scale with user's height", true);
      property.comment = "Server Sided! Jutsus scale with user's height. (default: true)";
      cJutsuScalesWithUser = property.getBoolean(true);
      JutsuScalesWithUser = cJutsuScalesWithUser;
      String[] names3 = new String[]{"Wave", "Ball", "Disk", "Laser", "Spiral", "Large_Ball", "Barrage", "Explosion"};
      data = new String[names3.length];

      for (int k = 0; k < names3.length; k++) {
         data[k] = names3[k] + " " + (k == 6 ? 1 : (k > 4 ? 3 : 2));
      }

      property = config.get(
         "Energy Attack Configs",
         "Training Point Gain - TP amount gained from Energy Attacks.",
         data,
         "Server Sided! Tp gain can be, from 1 to 100. (default: 2 2 2 2 2 3 1 3)."
      );
      data = property.getStringList();

      for (int k = 0; k < names3.length; k++) {
         String[] line = data[k].split(" ");
         cTPGainEnergy[k] = Integer.parseInt(line[1]);
         if (cTPGainEnergy[k] < 0) {
            cTPGainEnergy[k] = 0;
         }

         if (cTPGainEnergy[k] > 100) {
            cTPGainEnergy[k] = 100;
         }

         TPGainEnergy[k] = cTPGainEnergy[k];
      }

      property = config.get("Energy Attack Configs", "Continues Energy Attack Lock Timer", 5);
      property.comment = "Server Sided! Continues Energy Attack Lock Timer. Setting it to 0 makes it lock the Enemy while the user has Ki. (default: 5)";
      cContinuesEnergyAttackTimer = property.getInt(5);
      ContinuesEnergyAttackTimer = cContinuesEnergyAttackTimer;
      property = config.get("Energy Attack Configs", "Continues Energy Attacks Move On Lost Target", true);
      property.comment = "Server Sided! Continues Energy Attacks start to move forward once hit entity moves away. (default: true)";
      cContinuesEnergyAttackMoveOnLostTarget = property.getBoolean(true);
      ContinuesEnergyAttackMoveOnLostTarget = cContinuesEnergyAttackMoveOnLostTarget;
      property = config.get("Energy Attack Configs", "Continues Energy Attacks Lock Target", false);
      property.comment = "Server Sided! Continues Energy Attacks Lock the hit entity. (default: false)";
      cContinuesEnergyAttackEnemyLock = property.getBoolean(false);
      ContinuesEnergyAttackEnemyLock = cContinuesEnergyAttackEnemyLock;
      property = config.get("Energy Attack Configs", "Energy Attack Max Life Tick 1", 2000);
      property.comment = "Server Sided! Energy Attack Max Life Tick 1. Setting it to 0 makes it infinite. (default: 2000)";
      cEnergyAttackMaxLifeTick = property.getInt(2000);
      EnergyAttackMaxLifeTick = cEnergyAttackMaxLifeTick;
      property = config.get("Energy Attack Configs", "Energy Attack Max Life Tick 2 - Percentage Multiplier", 500);
      property.comment = "Server Sided! Energy Attack Max Life Tick 2 - Percentage Multiplier (formula: config*attack charge percentage*0.02). Setting it to 0 makes it infinite. (default: 500)";
      cEnergyAttackMaxLifeTickPercMulti = property.getInt(500);
      EnergyAttackMaxLifeTickPercMulti = cEnergyAttackMaxLifeTickPercMulti;
      property = config.get("Energy Attack Configs", "Continues Energy Attacks Slow Down with a Target", true);
      property.comment = "Server Sided! Continues Energy Attacks Slow Down once it hits an entity. (default: true)";
      cContinuesEnergyAttackTargetSlowdown = property.getBoolean(true);
      ContinuesEnergyAttackTargetSlowdown = cContinuesEnergyAttackTargetSlowdown;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Shields move with the user", false);
      property.comment = "Server Sided! Ki Shields move with the user. (default: false)";
      cShieldsMoveWithUser = property.getBoolean(false);
      ShieldsMoveWithUser = cShieldsMoveWithUser;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Explosions move with the user", false);
      property.comment = "Server Sided! Ki Explosions move with the user. (default: false)";
      cExplosionsMoveWithUser = property.getBoolean(false);
      ExplosionsMoveWithUser = cExplosionsMoveWithUser;
      property = config.get("DBC Ki Attack Server Sided Configs", "Spirals Weaken going through enemies by x Percentage", 30);
      property.comment = "Server Sided! Spirals Damage weakens after going through targets. Value is a Percentage, for example 30 will be 30% minus power after each hit. Setting to 0 will disable the config. From 0 to 100. (default: 30)";
      cSpiralWeakensAfterHit = (byte)property.getInt(30);
      if (cSpiralWeakensAfterHit > 100) {
         cSpiralWeakensAfterHit = 100;
      } else if (cSpiralWeakensAfterHit < 0) {
         cSpiralWeakensAfterHit = 0;
      }

      SpiralWeakensAfterHit = cSpiralWeakensAfterHit;
      property = config.get("DBC Ki Attack Server Sided Configs", "Spirals Weaken going through enemies based off on their Start Damage", true);
      property.comment = "Server Sided! Spirals Weaken going through enemies based off on their Start Damage (true) OR their new divided damage (false). (default: true)";
      cSpiralWeakensBasedOnStartDamage = property.getBoolean(true);
      SpiralWeakensBasedOnStartDamage = cSpiralWeakensBasedOnStartDamage;
      property = config.get("DBC Server Sided Configs", "Player Fly mode descending On", true);
      property.comment = "Server Sided! Players in Fly mode will slowly move downwards constantly. (default: true)";
      cPlayerFlyingDragDownOn = property.getBoolean(false);
      PlayerFlyingDragDownOn = cPlayerFlyingDragDownOn;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Attack Explosion Size Limit", 15);
      property.comment = "Server Sided! Setting it to 0 will disable limits. From 0 to 10000 (default: 15.0) (lower number will reduce lag)";
      cExplosionSizeLimit = property.getDouble(15.0);
      if (cExplosionSizeLimit < 0.0) {
         cExplosionSizeLimit = 0.0;
      }

      if (cExplosionSizeLimit > 10000.0) {
         cExplosionSizeLimit = 10000.0;
      }

      ExplosionSizeLimit = cExplosionSizeLimit;
      property = config.get("DBC Ki Attack Server Sided Configs", "Ki Attack Closest Entity Check Size Mode", 4);
      property.comment = "Server Sided! Changes what the size of the bounding box is in which it can detect entities. The higher values may be less likely to Freeze the game.\n\tFrom 0 to 5 (default: 4).\n\tMode 0 = Old, (Size + Motion + Extra 0.5 Blocks)\n\tMode 1 = (Size + Motion)\n\tMode 2 = (Size + Extra 0.5 Blocks)\n\tMode 3 = (Size + Motion Version 2)\n\tMode 4 = (Size)\n\tMode 5 = (DISABLED! Doesn't check what entity is closest in it.";
      cKiClosestEntityCheckSize = (byte)property.getInt(4);
      if (cKiClosestEntityCheckSize < 0) {
         cKiClosestEntityCheckSize = 0;
      }

      if (cKiClosestEntityCheckSize > 5) {
         cKiClosestEntityCheckSize = 5;
      }

      KiClosestEntityCheckSize = cKiClosestEntityCheckSize;
      property = config.get("general", "Bonus Attributes On", false);
      property.comment = "Server Sided! Bonus Attributes are enabled from the 'jrmcabonus' command. (default: false)";
      cJRMCABonusOn = property.getBoolean();
      JRMCABonusOn = cJRMCABonusOn;
      property = config.get("general", "Shadow Dummy Scales to the Summoner On", true);
      property.comment = "Server Sided! Shadow Dummy's size becomes the same as the Summoner's. (default: true)";
      cShadowDummyScaleToTarget = property.getBoolean();
      ShadowDummyScaleToTarget = cShadowDummyScaleToTarget;
      property = config.get("general", "Server - Family C Player Data Update Timer in Seconds", 5);
      property.comment = "Server Sided! Change how often the Server updates each Players' Family Data. Family C data can become laggy on servers with hundreds or thousands of Families. (From 1 to 1000000) Lower numbers equals more lag. (default: 5)";
      serverPlayerUpdateTick_DataFamilyC = property.getInt(5);
      if (serverPlayerUpdateTick_DataFamilyC < 1) {
         serverPlayerUpdateTick_DataFamilyC = 1;
      }

      if (serverPlayerUpdateTick_DataFamilyC > 1000000) {
         serverPlayerUpdateTick_DataFamilyC = 1000000;
      }

      property = config.get("general", "Server - Family C Player Data Updater Run as Java Thread On", true);
      property.comment = "Server Sided! Family C player data updates are made in a new Thread, potentially increasing server performace, but also causing unexpected minor bugs. ('true' or 'false') (default: true)";
      runFamilyCDataUpdateAsThread = property.getBoolean();
      property = config.get("Food Configs", "Can Eat While KOd On", false);
      property.comment = "Server Sided! Player can eat items while under the KO status effect. (default: false)";
      cCanEatWhileKOd = property.getBoolean();
      CanEatWhileKOd = cCanEatWhileKOd;
      property = config.get("Food Configs", "Can Eat Senzu While KOd On", false);
      property.comment = "Server Sided! Player can eat senzu beans while under the KO status effect. (default: false)";
      cCanUseSenzuWhileKOd = property.getBoolean();
      CanUseSenzuWhileKOd = cCanUseSenzuWhileKOd;
      property = config.get("Food Configs", "Global Food Health Heal Multiplier", 0.03);
      property.comment = "Server Sided! Formula: (Global Multi * Max Body * Food Heal Amount * Unique Multiplier). From 0 to 10000. (default: 0.03)";
      cGlobalFoodHealMultiHealth = (float)property.getDouble(0.03);
      if (cGlobalFoodHealMultiHealth < 0.0F) {
         cGlobalFoodHealMultiHealth = 0.0F;
      }

      if (cGlobalFoodHealMultiHealth > 10000.0F) {
         cGlobalFoodHealMultiHealth = 10000.0F;
      }

      GlobalFoodHealMultiHealth = cGlobalFoodHealMultiHealth;
      property = config.get("Food Configs", "Global Food Energy Heal Multiplier", 0.0175);
      property.comment = "Server Sided! Formula: (Global Multi * Max Energy * Food Heal Amount * Unique Multiplier). From 0 to 10000. (default: 0.175)";
      cGlobalFoodHealMultiEnergy = (float)property.getDouble(0.0175);
      if (cGlobalFoodHealMultiEnergy < 0.0F) {
         cGlobalFoodHealMultiEnergy = 0.0F;
      }

      if (cGlobalFoodHealMultiEnergy > 10000.0F) {
         cGlobalFoodHealMultiEnergy = 10000.0F;
      }

      GlobalFoodHealMultiEnergy = cGlobalFoodHealMultiEnergy;
      String[] names4 = new String[]{"item.ItemDinoMeatCooked 0.95 0.95", "item.ItemDinoMeatCookedBig 0.95 0.95"};
      property = config.get(
         "Food Configs",
         "Unique Food Heal Multiplier",
         names4,
         "Server Sided! Food items in this list receive a unique heal multiplier. From 0 to 10000. (default: item.ItemDinoMeatCooked 0.95 0.95 item.ItemDinoMeatCooked 0.95 0.95).\nEach line should contain: (itemName) (healthHeal) (energyHeal)"
      );
      data = property.getStringList();

      for (String line : data) {
         String[] lineContent = line.split(" ");
         float valueHealth = Float.parseFloat(lineContent[1]);
         if (valueHealth < 0.0F) {
            valueHealth = 0.0F;
         }

         if (valueHealth > 10000.0F) {
            valueHealth = 10000.0F;
         }

         float valueEnergy = Float.parseFloat(lineContent[2]);
         if (valueEnergy < 0.0F) {
            valueEnergy = 0.0F;
         }

         if (valueEnergy > 10000.0F) {
            valueEnergy = 10000.0F;
         }

         cFoodHealMulti.put(lineContent[0], new float[]{valueHealth, valueEnergy});
         FoodHealMulti.put(lineContent[0], new float[]{valueHealth, valueEnergy});
      }

      property = config.get("general", "Render Building Blocks as Normal Block On", false);
      property.comment = "Server Sided! If enabled then you can place blocks (for example levers) on the Building Blocks, but they will create shadows and could cause more lagg. (default: false)";
      cBuildingBlocksRenderAsNormalBlock = property.getBoolean();
      BuildingBlocksRenderAsNormalBlock = cBuildingBlocksRenderAsNormalBlock;
      property = config.get("NarutoC Server Sided Configs", "Explosion Tags Explode when Tick Timer is Over On", false);
      property.comment = "Server Sided! Naruto C Explosion Tags Explode when Tick Timer is Over On. (default: false)";
      cNCExplosionTagTickTimerExplode = property.getBoolean();
      NCExplosionTagTickTimerExplode = cNCExplosionTagTickTimerExplode;
      property = config.get("NarutoC Server Sided Configs", "Explosion Tag Tick Timer", 10000);
      property.comment = "Server Sided! Naruto C Exploding Tags explode after x Minecraft Ticks. 0 = disabled. From 0 to 1000000. (default: 1000000)";
      cNCExplosionTagTickTimer = property.getInt(10000);
      if (cNCExplosionTagTickTimer < 0) {
         cNCExplosionTagTickTimer = 0;
      }

      if (cNCExplosionTagTickTimer > 1000000) {
         cNCExplosionTagTickTimer = 1000000;
      }

      NCExplosionTagTickTimer = cNCExplosionTagTickTimer;
      property = config.get("NarutoC Server Sided Configs", "Explosion Tag Detection Range Multiplier", 1.0);
      property.comment = "Server Sided! Naruto C Exploding Tags Detection Range multiplier to explode if someone is near it. (Bigger numbers can cause more lagg). From 0 to 100. (default: 1.0)";
      cNCExplosionTagDetectionRange = (float)property.getDouble(1.0);
      if (cNCExplosionTagDetectionRange < 0.0F) {
         cNCExplosionTagDetectionRange = 0.0F;
      }

      if (cNCExplosionTagDetectionRange > 100.0F) {
         cNCExplosionTagDetectionRange = 100.0F;
      }

      NCExplosionTagDetectionRange = cNCExplosionTagDetectionRange;
      property = config.get("NarutoC Server Sided Configs", "Explosion Tag Explosion Size Multiplier", 1.0);
      property.comment = "Server Sided! Naruto C Exploding Tags Explosion Size multiplier. (Bigger numbers can cause more lagg). From 0 to 15. (default: 1.0)";
      cNCExplosionTagExplosionSize = (float)property.getDouble(1.0);
      if (cNCExplosionTagExplosionSize < 0.0F) {
         cNCExplosionTagExplosionSize = 0.0F;
      }

      if (cNCExplosionTagExplosionSize > 15.0F) {
         cNCExplosionTagExplosionSize = 15.0F;
      }

      NCExplosionTagExplosionSize = cNCExplosionTagExplosionSize;
      String[] defSafeZone = new String[]{
         "net.minecraft.entity.monster.EntityMob",
         "net.minecraft.entity.IProjectile",
         "net.minecraft.entity.EntityFlying",
         "net.minecraft.entity.passive.EntityBat",
         "JinRyuu.DragonBC.common.Npcs.EntityDBCNeut",
         "JinRyuu.DragonBC.common.Npcs.EntityDBCEvil",
         "JinRyuu.DragonBC.common.Npcs.EntityDBCWildlife",
         "JinRyuu.NarutoC.common.Npcs.EntityNCEvil"
      };
      property = config.get("general", "Safe Zone Entity Blacklist", defSafeZone, "Server Sided! List down the Class paths and name of the Entities.");
      data = property.getStringList();

      for (String line : data) {
         SafeZoneEntityBlacklist.put(line, true);
      }

      String[] defSafeZone2 = new String[]{"JinRyuu.DragonBC.common.Entitys.EntityInstantTransmission"};
      property = config.get("general", "Safe Zone Entity Whitelist", defSafeZone2, "Server Sided! List down the Class paths and name of the Entities.");
      data = property.getStringList();

      for (String line : data) {
         SafeZoneEntityWhitelist.put(line, true);
      }

      i = new int[]{1, 1, 1, 1, 1, 1};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.Races[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Training Points Gain per Race - 'TP gain / melee' rate",
         s,
         "Server Sided! Multiplies the Default 'Training Points Gain - 'TP gain / melee' rate' Config by this values depending on the Player's race (From 0 to 10000)."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int value = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         value = value < 0 ? 0 : (value > 10000 ? 10000 : value);
         i[j] = value;
         flt[j] = value;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      TPGainRateRace = flt;
      cTPGainRateRace = flt;
      property.set(sa);
      i = new int[]{1, 1, 1, 1, 1, 1};
      s = new String[i.length];

      for (int j = 0; j < s.length; j++) {
         s[j] = JRMCoreH.Races[j] + " " + i[j];
      }

      property = config.get(
         "DBC Server Sided Configs",
         "Training Point Gain per Race - TP amount gained",
         s,
         "Server Sided! Multiplies the Default 'Training Point Gain - TP amount gained' Config by this values depending on the Player's race (From 0 to 10000)."
      );
      flt = new float[i.length];
      sa = new String[i.length];
      len = property.getStringList().length;

      for (int j = 0; j < i.length; j++) {
         int value = len > j ? Integer.parseInt(property.getStringList()[j].split(" ")[1]) : i[j];
         value = value < 0 ? 0 : (value > 10000 ? 10000 : value);
         i[j] = value;
         flt[j] = value;
         sa[j] = s[j].split(" ")[0] + " " + (len > j ? property.getStringList()[j].split(" ")[1] : i[j]);
      }

      TPGainRace = flt;
      cTPGainRace = flt;
      property.set(sa);
      property = config.get("general", "Attribute Upgrade Cost - Start Minus", 140);
      property.comment = "Server Sided! Divides Attribute Upgrade Costs (UC) by this value. If result is below Minimum value then use that instead. From 0 to 1000000000. (default: 140)";
      cAttributeUpgradeCost_StartMinus = property.getInt();
      if (cAttributeUpgradeCost_StartMinus < 0) {
         cAttributeUpgradeCost_StartMinus = 0;
      }

      if (cAttributeUpgradeCost_StartMinus > 1000000000) {
         cAttributeUpgradeCost_StartMinus = 1000000000;
      }

      AttributeUpgradeCost_StartMinus = cAttributeUpgradeCost_StartMinus;
      property = config.get("general", "Attribute Upgrade Cost - Minimum value", 16);
      property.comment = "Server Sided! Minimum value for Attribute Upgrade Costs (UC). From 0 to 1000000000. (default: 16)";
      cAttributeUpgradeCost_Min = property.getInt();
      if (cAttributeUpgradeCost_Min < 0) {
         cAttributeUpgradeCost_Min = 0;
      }

      if (cAttributeUpgradeCost_Min > 1000000000) {
         cAttributeUpgradeCost_Min = 1000000000;
      }

      AttributeUpgradeCost_Min = cAttributeUpgradeCost_Min;
      double[] DEF_AttributeUpgradeCost_AttributeMulti = new double[]{0.75, 0.75, 0.75, 0.75, 0.75, 0.75};
      property = config.get("general", "Attribute Upgrade Cost - Attribute Multiplier", DEF_AttributeUpgradeCost_AttributeMulti);
      property.comment = "Server Sided! Multiplies your Attributes by this value when calculating the Attribute Upgrade Costs (UC). From 0 to 1000000000. (default: 0.75 0.75 0.75 0.75 0.75 0.75)";
      double[] dList = property.getDoubleList();

      for (int j = 0; j < cAttributeUpgradeCost_AttributeMulti.length; j++) {
         cAttributeUpgradeCost_AttributeMulti[j] = (float)(dList.length <= j ? cAttributeUpgradeCost_AttributeMulti[j] : dList[j]);
         if (cAttributeUpgradeCost_AttributeMulti[j] < 0.0F) {
            cAttributeUpgradeCost_AttributeMulti[j] = 0.0F;
         }

         if (cAttributeUpgradeCost_AttributeMulti[j] > 1000000.0F) {
            cAttributeUpgradeCost_AttributeMulti[j] = 1000000.0F;
         }

         AttributeUpgradeCost_AttributeMulti[j] = cAttributeUpgradeCost_AttributeMulti[j];
      }

      property = config.get("Command Configs", "jrmcheal - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComHealNAS = property.getBoolean();
      ComHealNAS = cComHealNAS;
      property = config.get("Command Configs", "jrmcheal - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComHealNAC = property.getBoolean();
      ComHealNAC = cComHealNAC;
      property = config.get("Command Configs", "jrmcheal - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComHealNAO = property.getBoolean();
      ComHealNAO = cComHealNAO;
      property = config.get("Command Configs", "jrmctp - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComTPNAS = property.getBoolean();
      ComTPNAS = cComTPNAS;
      property = config.get("Command Configs", "jrmctp - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComTPNAC = property.getBoolean();
      ComTPNAC = cComTPNAC;
      property = config.get("Command Configs", "jrmctp - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComTPNAO = property.getBoolean();
      ComTPNAO = cComTPNAO;
      property = config.get("Command Configs", "jrmca - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComANAS = property.getBoolean();
      ComANAS = cComANAS;
      property = config.get("Command Configs", "jrmca - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComANAC = property.getBoolean();
      ComANAC = cComANAC;
      property = config.get("Command Configs", "jrmca - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComANAO = property.getBoolean();
      ComANAO = cComANAO;
      property = config.get("Command Configs", "jrmcse - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComSENAS = property.getBoolean();
      ComSENAS = cComSENAS;
      property = config.get("Command Configs", "jrmcse - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComSENAC = property.getBoolean();
      ComSENAC = cComSENAC;
      property = config.get("Command Configs", "jrmcse - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComSENAO = property.getBoolean();
      ComSENAO = cComSENAO;
      property = config.get("Command Configs", "jrmcm - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComMNAS = property.getBoolean();
      ComMNAS = cComMNAS;
      property = config.get("Command Configs", "jrmcm - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComSENAC = property.getBoolean();
      ComMNAC = cComMNAC;
      property = config.get("Command Configs", "jrmcm - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComMNAO = property.getBoolean();
      ComMNAO = cComMNAO;
      property = config.get("Command Configs", "jrmcracialskill - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cComRSNAS = property.getBoolean();
      ComRSNAS = cComRSNAS;
      property = config.get("Command Configs", "jrmcracialskill - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cComRSNAC = property.getBoolean();
      ComRSNAC = cComRSNAC;
      property = config.get("Command Configs", "jrmcracialskill - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cComRSNAO = property.getBoolean();
      ComRSNAO = cComRSNAO;
      property = config.get("Command Configs", "jrmcabonus - Notify admins if used on Self", true);
      property.comment = "Server Sided! Means notify ops when command is used on self. (default: true)";
      cCommandNotifyAdminJRMCABonusSelf = property.getBoolean();
      CommandNotifyAdminJRMCABonusSelf = cCommandNotifyAdminJRMCABonusSelf;
      property = config.get("Command Configs", "jrmcabonus - Notify admins if used by Console", true);
      property.comment = "Server Sided! Means notify ops when command is used by console. (default: true)";
      cCommandNotifyAdminJRMCABonusConsole = property.getBoolean();
      CommandNotifyAdminJRMCABonusConsole = cCommandNotifyAdminJRMCABonusConsole;
      property = config.get("Command Configs", "jrmcabonus - Notify admins if used by Others", true);
      property.comment = "Server Sided! Means notify ops when command is used by someone on someone else. (default: true)";
      cCommandNotifyAdminJRMCABonusOthers = property.getBoolean();
      CommandNotifyAdminJRMCABonusOthers = cCommandNotifyAdminJRMCABonusOthers;
      property = config.get("Command Configs", "jrmcabonuscheck - Check other player's sheet without OP On", true);
      property.comment = "Server Sided! jrmcabonuscheck - Allows non OP Players to check other player's sheet. (default: true)";
      cJRMCABonusCheckOnOthersWithoutOP = property.getBoolean();
      JRMCABonusCheckOnOthersWithoutOP = cJRMCABonusCheckOnOthersWithoutOP;
      property = config.get("Command Configs", "jrmccheck - Check other player's sheet without OP On", true);
      property.comment = "Server Sided! jrmccheck - Allows non OP Players to check other player's sheet. (default: true)";
      cJRMCCheckOnOthersWithoutOP = property.getBoolean();
      JRMCCheckOnOthersWithoutOP = cJRMCCheckOnOthersWithoutOP;
      config.save();
   }
}
