/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class DBCConfig {
    public static int playerCount = 0;
    public static int planetEarth = 0;
    public static int planetNamek = 20;
    public static int planetVegeta = 21;
    public static int otherWorld = 22;
    public static int dimTimeChamber = 23;
    public static int dimNullRealm = 24;
    public static boolean oldDsh;
    public static boolean oldJmp;
    public static boolean oldFly;
    public static boolean plntVegeta;
    public static boolean flyAnyLvl;
    public static int eDBrate;
    public static int nDBrate;
    public static int maxTrnExp;
    public static int healingpodhealingrate;
    public static int senzuFromKarin;
    public static int senzuCool;
    public static boolean coldDsh;
    public static boolean coldJmp;
    public static boolean coldFly;
    public static boolean cplntVegeta;
    public static boolean cflyAnyLvl;
    public static int ceDBrate;
    public static int cnDBrate;
    public static int cmaxTrnExp;
    public static int chealingpodhealingrate;
    public static int csenzuFromKarin;
    public static int csenzuCool;
    public static int spdc;
    public static int mdal;
    public static int mdat;
    public static int NPCOgreHP;
    public static int cNPCOgreHP;
    public static int NPCOgreDam;
    public static int cNPCOgreDam;
    public static int NPC_RRMech1_HP;
    public static int cNPC_RRMech1_HP;
    public static int NPC_RRMech1_Dam;
    public static int cNPC_RRMech1_Dam;
    public static int NPC_RRMech2_HP;
    public static int cNPC_RRMech2_HP;
    public static int NPC_RRMech2_Dam;
    public static int cNPC_RRMech2_Dam;
    public static int NPC_RRMech3_HP;
    public static int cNPC_RRMech3_HP;
    public static int NPC_RRMech3_Dam;
    public static int cNPC_RRMech3_Dam;
    public static int NPC_SaberT_HP;
    public static int cNPC_SaberT_HP;
    public static int NPC_SaberT_Dam;
    public static int cNPC_SaberT_Dam;
    public static int NPC_Dino1_HP;
    public static int cNPC_Dino1_HP;
    public static int NPC_Dino1_Dam;
    public static int cNPC_Dino1_Dam;
    public static int NPC_Dino2_HP;
    public static int cNPC_Dino2_HP;
    public static int NPC_Dino2_Dam;
    public static int cNPC_Dino2_Dam;
    public static int NPC_Dino3_HP;
    public static int cNPC_Dino3_HP;
    public static int NPC_Dino3_Dam;
    public static int cNPC_Dino3_Dam;
    public static int BearThiefDAM;
    public static int cBearThiefDAM;
    public static int BearThiefHP;
    public static int cBearThiefHP;
    public static int TigerBanditDAM;
    public static int cTigerBanditDAM;
    public static int TigerBanditHP;
    public static int cTigerBanditHP;
    public static int RRMajorDAM;
    public static int cRRMajorDAM;
    public static int RRMajorHP;
    public static int cRRMajorHP;
    public static int RRSoldierDAM;
    public static int cRRSoldierDAM;
    public static int RRSoldierHP;
    public static int cRRSoldierHP;
    public static int RRSoldier2DAM;
    public static int cRRSoldier2DAM;
    public static int RRSoldier2HP;
    public static int cRRSoldier2HP;
    public static int RRSoldier3DAM;
    public static int cRRSoldier3DAM;
    public static int RRSoldier3HP;
    public static int cRRSoldier3HP;
    public static int spwnrt_ogre;
    public static int cspwnrt_ogre;
    public static int spwnrt_rrmech1;
    public static int cspwnrt_rrmech1;
    public static int spwnrt_sabert;
    public static int cspwnrt_sabert;
    public static int spwnrt_dino1;
    public static int cspwnrt_dino1;
    public static int spwnrt_dino2;
    public static int cspwnrt_dino2;
    public static int spwnrt_dino3;
    public static int cspwnrt_dino3;
    public static int spwnrt_frg;
    public static int cspwnrt_frg;
    public static int spwnrt_syn;
    public static int cspwnrt_syn;
    public static int spwnrt_syn2;
    public static int cspwnrt_syn2;
    public static int SpawnrateBearThief;
    public static int cSpawnrateBearThief;
    public static int SpawnrateTigerBandit;
    public static int cSpawnrateTigerBandit;
    public static int SpawnrateRRMajor;
    public static int cSpawnrateRRMajor;
    public static int SpawnrateRRSoldiers;
    public static int cSpawnrateRRSoldiers;
    public static boolean DeadInv;
    public static boolean cDeadInv;
    public static boolean FreeRev;
    public static boolean cFreeRev;
    public static float Reinc;
    public static float cReinc;
    public static int RevTm;
    public static int cRevTm;
    public static double[] RevLocG;
    public static double[] cRevLocG;
    public static double[] RevLocN;
    public static double[] cRevLocN;
    public static double[] RevLocE;
    public static double[] cRevLocE;
    public static boolean Godform;
    public static boolean cGodform;
    public static boolean GodformCosm;
    public static boolean cGodformCosm;
    public static int StrainGod;
    public static int cStrainGod;
    public static int SSGHelp;
    public static int cSSGHelp;
    public static int TechExpRate;
    public static int cTechExpRate;
    public static int TechExpNeed;
    public static int cTechExpNeed;
    public static int TechCostMod;
    public static int cTechCostMod;
    public static boolean DsblO;
    public static boolean cDsblO;
    public static boolean cDBGT;
    public static boolean DBGT;
    public static double[][] RevAng;
    public static double[][] cRevAng;
    public static int[] RevDim;
    public static int[] cRevDim;
    public static double[][] DeathDim;
    public static double[][] cDeathDim;
    public static double[] cWhisTeleportLocationBack;
    public static double[] WhisTeleportLocationBack;
    public static double[] cWhisTeleportLocationNull;
    public static double[] WhisTeleportLocationNull;
    public static boolean cCanWhisTeleport;
    public static boolean CanWhisTeleport;
    public static boolean cNullRealmBGColorNodeGreen;
    public static boolean NullRealmBGColorNodeGreen;
    public static final String CATEGORY_SERVERSIDED = "general";
    public static final String CATEGORY_NPCCONFIGS = "NPC Configs";
    public static final String CATEGORY_DEATHSYSTEM = "Death System Configs";
    public static final String CATEGORY_TIMECHAMBER = "Hyperbolic Time Chamber Configs";
    public static final String CATEGORY_CLIENTSIDED = "Client Sided Configs";
    public static final String CATEGORY_TRANSFORMATIONS = "Transformation Configs";
    public static final String CATEGORY_TECHNIQUES = "Techniques Configs";
    public static final String CATEGORY_HEALTHPOD = "Health Pod Configs";
    public static double cnfKFd;
    public static double cnfKFc;
    public static double cnfKId;
    public static double cnfKIc;
    public static double cnfKDd;
    public static double cnfKDc;
    public static double cnfKBld;
    public static double cnfKBlc;
    public static double cnfKCsd;
    public static double cnfKCsc;
    public static double cnfSpc;
    public static double cnfFlnmb;
    public static double cnfFlncst;
    public static double cnfFlnck;
    public static int cnfDrt;
    public static double ccnfKFd;
    public static double ccnfKFc;
    public static double ccnfKId;
    public static double ccnfKIc;
    public static double ccnfKDd;
    public static double ccnfKDc;
    public static double ccnfKBld;
    public static double ccnfKBlc;
    public static double ccnfKCsd;
    public static double ccnfKCsc;
    public static double ccnfSpc;
    public static double ccnfFlnmb;
    public static double ccnfFlncst;
    public static double ccnfFlnck;
    public static int ccnfDrt;
    public static int hPodUpd;
    public static int chPodUpd;
    public static int[] hPodRate;
    public static int[] chPodRate;
    public static boolean[] hPodPerc;
    public static boolean[] chPodPerc;
    public static float cEnmaScale;
    public static float cGuruScale;
    public static float EnmaScale;
    public static float GuruScale;
    public static int cNullRealmMinimumHeight;
    public static int NullRealmMinimumHeight;
    public static int cAaiForceDifficulty;
    public static int AaiForceDifficulty;
    public static boolean cAaiDisabled;
    public static boolean AaiDisabled;
    public static int cEnemyDefaultAttackTimer;
    public static int EnemyDefaultAttackTimer;
    public static int cEnemyDefaultShortRangeAttackTimer;
    public static int EnemyDefaultShortRangeAttackTimer;
    public static double cEnemyDefaultMoveSpeed;
    public static double EnemyDefaultMoveSpeed;
    public static boolean cKiAttackGoThroughInvulnerableEnemies;
    public static boolean KiAttackGoThroughInvulnerableEnemies;
    public static int InstantTransformKiPercentageCost;
    public static int InstantTransformKiCost;
    public static boolean cInstantTransformOn;
    public static boolean InstantTransformOn;
    public static boolean cSingleFormDescendOn;
    public static boolean SingleFormDescendOn;
    public static final int INSTANT_KAIOKEN = 0;
    public static final int INSTANT_UI = 1;
    public static final int INSTANT_MYSTIC = 2;
    public static final int INSTANT_GOD = 3;
    public static boolean[] cIsInstantTransformEnabled;
    public static boolean[] IsInstantTransformEnabled;
    public static boolean cKaiokenSingleFormDescendOn;
    public static boolean KaiokenSingleFormDescendOn;
    public static boolean cMoveWhileTransforming;
    public static boolean MoveWhileTransforming;
    public static boolean cMoveWhileInstantTransforming;
    public static boolean MoveWhileInstantTransforming;
    public static boolean EnemyTeleportOutOfLock;
    public static boolean cMysticKaiokenOn;
    public static boolean MysticKaiokenOn;

    public static void init(Configuration config) {
        int i;
        config.load();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        Property mtp = config.get(CATEGORY_SERVERSIDED, "Max Training Experience Limit", 1000);
        mtp.comment = "Server Sided! Max training experience limit can be set from 0-30000 (default: 1000). This is not TP this is Exp.";
        cmaxTrnExp = mtp.getInt(1000);
        if (cmaxTrnExp < 0) {
            cmaxTrnExp = 0;
        }
        if (cmaxTrnExp > 30000) {
            cmaxTrnExp = 30000;
        }
        maxTrnExp = cmaxTrnExp;
        Property pv = config.get(CATEGORY_SERVERSIDED, "Planet Vegeta", true);
        pv.comment = "Server Sided! If 'false' then Planet Vegeta wont appear, If 'true' then Planet Vegeta will be available.";
        plntVegeta = cplntVegeta = pv.getBoolean(true);
        Property edbsr = config.get(CATEGORY_SERVERSIDED, "Earth Dragon Block Spawn Rate", 2);
        edbsr.comment = "Server Sided! Spawn rate can only be from 1 (everyday 1 DB, around everyplayer somewhere) to 100 (extremly rare spawn, 100 days 1 DB) ";
        ceDBrate = edbsr.getInt(2);
        if (ceDBrate < 1) {
            ceDBrate = 1;
        }
        if (ceDBrate > 100) {
            ceDBrate = 100;
        }
        eDBrate = ceDBrate;
        Property ndbsr = config.get(CATEGORY_SERVERSIDED, "Namek Dragon Block Spawn Rate", 3);
        ndbsr.comment = "Server Sided! Spawn rate can only be from 1 (everyday 1 DB, around everyplayer somewhere) to 100 (extremly rare spawn, 100 days 1 DB) ";
        cnDBrate = ndbsr.getInt(3);
        if (cnDBrate < 1) {
            cnDBrate = 1;
        }
        if (cnDBrate > 100) {
            cnDBrate = 100;
        }
        nDBrate = cnDBrate;
        Property od = config.get(CATEGORY_CLIENTSIDED, "Old Dash", false);
        od.comment = "If 'false' then the current sprint dash will be used, If 'true' then the one key press and hold (R) dash will be available.";
        oldDsh = coldDsh = od.getBoolean(false);
        Property oj = config.get(CATEGORY_CLIENTSIDED, "Old Jump", false);
        oj.comment = "If 'false' then the current space jump will be used, If 'true' then the one key press and hold (X) jump will be available.";
        oldJmp = coldJmp = oj.getBoolean(false);
        Property of = config.get(CATEGORY_CLIENTSIDED, "Old Fly", false);
        of.comment = "If 'false' then the current turn on/off fly will be used, If 'true' then the one key press and hold (F) Fly and (Ctrl) for float will be available.";
        oldFly = coldFly = of.getBoolean(false);
        Property oc = config.get(CATEGORY_SERVERSIDED, "Fly with no Fly Skill", false);
        oc.comment = "Server Sided! If 'false' then one can only fly with Fly Skill, If 'true' then one will be able to Fly without Fly Skill.";
        flyAnyLvl = cflyAnyLvl = oc.getBoolean(false);
        Property p = config.get(CATEGORY_SERVERSIDED, "Spacepods drop timer", 60);
        p.comment = "Server Sided! Spacepods will drop after given seconds if not being ridden. If 0 they wont drop! (Default: 60)";
        spdc = p.getInt(60);
        p = config.get(CATEGORY_SERVERSIDED, "Saga mob despawn area limit", 100);
        p.comment = "Server Sided! Saga mobs will despawn if the mob spawner (a player) is not in the area in specified block radius after specific seconds that can be set in 'Saga mob despawn timer' config option. If 0 Saga mobs wont despawn! (Default: 100)";
        mdal = p.getInt(100);
        p = config.get(CATEGORY_SERVERSIDED, "Saga mob despawn timer", 120);
        p.comment = "Server Sided! Saga mob despawn timer in seconds, if spawner not near it. If 'Saga mob despawn area limit' config option is 0 this wont work! (Default: 120)";
        mdat = p.getInt(120);
        mdat = mdat < 1 ? 1 : mdat;
        Property DeathSystemOff = config.get(CATEGORY_DEATHSYSTEM, "Death System Off", false);
        DeathSystemOff.comment = "Server Sided! If 'false' then Death System is on, If 'true' then its off. (may reduce lag if true)";
        mod_DragonBC.DeathSystemOff = mod_DragonBC.cDeathSystemOff = DeathSystemOff.getBoolean(false);
        Property DBSpawnEnabled = config.get(CATEGORY_SERVERSIDED, "Dragon Blocks Spawn Enabled", true);
        DBSpawnEnabled.comment = "Server Sided! If 'true' Dragon Blocks will spawn, if 'false' then they wont spawn at all. (may reduce lag if false)";
        mod_DragonBC.DBSpawnEnabled = mod_DragonBC.cDBSpawnEnabled = DBSpawnEnabled.getBoolean(true);
        Property DBSpawn = config.get(CATEGORY_SERVERSIDED, "Dragon Blocks Spawn Chance", 4);
        DBSpawn.comment = "Server Sided! The maximum number (default: 4) of Dragon Blocks spawned around the players, in a 64 block radius. (from 0 to 7)";
        mod_DragonBC.DBSpawnChance = mod_DragonBC.cDBSpawnChance = DBSpawn.getInt(4);
        Property DBSpawnTime = config.get(CATEGORY_SERVERSIDED, "Dragon Blocks Spawn Time", "midday");
        DBSpawnTime.comment = "Server Sided! The daytime when the Dragon Blocks will spawn. 3 states, 'morning', 'midday'(default), and 'evening'.";
        mod_DragonBC.DBSpawnTime = mod_DragonBC.cDBSpawnTime = DBSpawnTime.getString();
        Property SagaSystemOn = config.get(CATEGORY_SERVERSIDED, "Saga System On", true);
        SagaSystemOn.comment = "Server Sided! If 'true' Saga System will work, if 'false' then no one will be able to progress in Saga System.  (may reduce lag if false)";
        mod_DragonBC.SagaSystemOn = mod_DragonBC.cSagaSystemOn = SagaSystemOn.getBoolean(true);
        Property SagaSysSpawnPods = config.get(CATEGORY_SERVERSIDED, "Saga System Spawn Spacepods", true);
        SagaSysSpawnPods.comment = "Server Sided! If 'true' Spacepods will spawn in the saga part where it can, if 'false' then Spacepods wont spawn.";
        mod_DragonBC.SagaSysSpawnPods = mod_DragonBC.cSagaSysSpawnPods = SagaSysSpawnPods.getBoolean(true);
        Property senzu = config.get(CATEGORY_SERVERSIDED, "Senzu from Korin", 1);
        senzu.comment = "Server Sided! Number of Senzu from Korin! (from 1 to 10)";
        csenzuFromKarin = senzu.getInt(1);
        if (csenzuFromKarin < 1) {
            csenzuFromKarin = 1;
        }
        if (csenzuFromKarin > 10) {
            csenzuFromKarin = 10;
        }
        senzuFromKarin = csenzuFromKarin;
        Property prop = config.get(CATEGORY_SERVERSIDED, "Senzu from Korin", 1);
        prop.comment = "Server Sided! Number of Senzu from Korin! (from 1 to 10)";
        csenzuFromKarin = prop.getInt(1);
        if (csenzuFromKarin < 1) {
            csenzuFromKarin = 1;
        }
        if (csenzuFromKarin > 10) {
            csenzuFromKarin = 10;
        }
        senzuFromKarin = csenzuFromKarin;
        prop = config.get(CATEGORY_SERVERSIDED, "Senzu Cooldown", 20);
        prop.comment = "Server Sided! Indicates how long senzu can not be used, In seconds! (from 0 to 1200)";
        csenzuCool = prop.getInt(20);
        if (csenzuCool < 0) {
            csenzuCool = 0;
        }
        if (csenzuCool > 1200) {
            csenzuCool = 1200;
        }
        senzuCool = csenzuCool;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Fist Damage per level", 2.5);
        prop.comment = "Server Sided! Ki Fist Damage Percentage multiplier value from 0 to 30. (default: 2.50)";
        ccnfKFd = prop.getDouble(2.5);
        if (ccnfKFd < 0.0) {
            ccnfKFd = 0.0;
        }
        if (ccnfKFd > 30.0) {
            ccnfKFd = 30.0;
        }
        cnfKFd = ccnfKFd;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Fist Cost per level", 1.0);
        prop.comment = "Server Sided! Ki Fist Cost Percentage multiplier value from 0 to 30. (default: 1.00)";
        ccnfKFc = prop.getDouble(1.0);
        if (ccnfKFc < 0.0) {
            ccnfKFc = 0.0;
        }
        if (ccnfKFc > 30.0) {
            ccnfKFc = 30.0;
        }
        cnfKFc = ccnfKFc;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Infuse Damage per level", 2.5);
        prop.comment = "Server Sided! Ki Infuse Damage Percentage multiplier value from 0 to 30. (default: 2.50)";
        ccnfKId = prop.getDouble(2.5);
        if (ccnfKId < 0.0) {
            ccnfKId = 0.0;
        }
        if (ccnfKId > 30.0) {
            ccnfKId = 30.0;
        }
        cnfKId = ccnfKId;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Infuse Cost per level", 1.0);
        prop.comment = "Server Sided! Ki Infuse Cost Percentage multiplier value from 0 to 30. (default: 1.00)";
        ccnfKIc = prop.getDouble(1.0);
        if (ccnfKIc < 0.0) {
            ccnfKIc = 0.0;
        }
        if (ccnfKIc > 30.0) {
            ccnfKIc = 30.0;
        }
        cnfKIc = ccnfKIc;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Protection Armor per level", 2.0);
        prop.comment = "Server Sided! Ki Protection Armor Percentage multiplier value from 0 to 30. (default: 2.00)";
        ccnfKDd = prop.getDouble(2.0);
        if (ccnfKDd < 0.0) {
            ccnfKDd = 0.0;
        }
        if (ccnfKDd > 30.0) {
            ccnfKDd = 30.0;
        }
        cnfKDd = ccnfKDd;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Protection Cost per level", 0.5);
        prop.comment = "Server Sided! Ki Protection Cost Percentage multiplier value from 0 to 30. (default: 0.50)";
        ccnfKDc = prop.getDouble(0.5);
        if (ccnfKDc < 0.0) {
            ccnfKDc = 0.0;
        }
        if (ccnfKDc > 30.0) {
            ccnfKDc = 30.0;
        }
        cnfKDc = ccnfKDc;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Blade Damage multiplier", 1.0);
        prop.comment = "Server Sided! Ki Blade Damage multiplier value from 0 to 30. (default: 1.00)";
        ccnfKBld = prop.getDouble(1.0);
        if (ccnfKBld < 0.0) {
            ccnfKBld = 0.0;
        }
        if (ccnfKBld > 30.0) {
            ccnfKBld = 30.0;
        }
        cnfKBld = ccnfKBld;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Blade Cost multiplier", 1.0);
        prop.comment = "Server Sided! Ki Blade Cost multiplier value from 0 to 30. (default: 1.00)";
        ccnfKBlc = prop.getDouble(1.0);
        if (ccnfKBlc < 0.0) {
            ccnfKBld = 0.0;
        }
        if (ccnfKBlc > 30.0) {
            ccnfKBlc = 30.0;
        }
        cnfKBlc = ccnfKBlc;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Scythe Damage multiplier", 3.0);
        prop.comment = "Server Sided! Ki Scythe Damage multiplier value from 0 to 30. (default: 3.00)";
        ccnfKCsd = prop.getDouble(3.0);
        if (ccnfKCsd < 0.0) {
            ccnfKCsd = 0.0;
        }
        if (ccnfKCsd > 30.0) {
            ccnfKCsd = 30.0;
        }
        cnfKCsd = ccnfKCsd;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Scythe Cost multiplier", 3.0);
        prop.comment = "Server Sided! Ki Scythe Cost multiplier value from 0 to 30. (default: 3.00)";
        ccnfKCsc = prop.getDouble(3.0);
        if (ccnfKCsc < 0.0) {
            ccnfKCsc = 0.0;
        }
        if (ccnfKCsc > 30.0) {
            ccnfKCsc = 30.0;
        }
        cnfKCsc = ccnfKCsc;
        prop = config.get(CATEGORY_SERVERSIDED, "Space pod speed multiplier", 1.75);
        prop.comment = "Server Sided! Space pod speed multiplier value from 0 to 30. (default: 1.75)";
        ccnfSpc = prop.getDouble(1.75);
        if (ccnfSpc < 0.0) {
            ccnfSpc = 0.0;
        }
        if (ccnfSpc > 30.0) {
            ccnfSpc = 30.0;
        }
        cnfSpc = ccnfSpc;
        prop = config.get(CATEGORY_SERVERSIDED, "Flying Nimbus speed multiplier", 2.25);
        prop.comment = "Server Sided! Flying Nimbus speed multiplier value from 0 to 30. (default: 2.25)";
        ccnfFlnmb = prop.getDouble(2.25);
        if (ccnfFlnmb < 0.0) {
            ccnfFlnmb = 0.0;
        }
        if (ccnfFlnmb > 30.0) {
            ccnfFlnmb = 30.0;
        }
        cnfFlnmb = ccnfFlnmb;
        prop = config.get(CATEGORY_SERVERSIDED, "Player Flying, and dodging stamina cost multiplier", 0.1);
        prop.comment = "Server Sided! Player Flying, and dodging stamina cost multiplier value from 0 to 30. (default: 0.1)";
        ccnfFlncst = prop.getDouble(0.1);
        if (ccnfFlncst < 0.0) {
            ccnfFlncst = 0.0;
        }
        if (ccnfFlncst > 30.0) {
            ccnfFlncst = 30.0;
        }
        cnfFlncst = ccnfFlncst;
        prop = config.get(CATEGORY_SERVERSIDED, "Player Flying ki cost multiplier", 0.5);
        prop.comment = "Server Sided! Player Flying ki cost multiplier value from 0 to 30. (default: 0.5)";
        ccnfFlnck = prop.getDouble(0.5);
        if (ccnfFlnck < 0.0) {
            ccnfFlnck = 0.0;
        }
        if (ccnfFlnck > 30.0) {
            ccnfFlnck = 30.0;
        }
        cnfFlnck = ccnfFlnck;
        prop = config.get(CATEGORY_SERVERSIDED, "Dirty Stone biome spawn rate", 15);
        prop.comment = "Server Sided! Dirty Stone biome spawn rate value from 0 to 30. (default: 15)";
        ccnfDrt = prop.getInt(15);
        if (ccnfDrt < 0) {
            ccnfDrt = 0;
        }
        if (ccnfDrt > 30) {
            ccnfDrt = 30;
        }
        cnfDrt = ccnfDrt;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Otherworld Ogre Stat Health", 2000);
        prop.comment = "Server Sided! Otherworld Ogre NPCs Health amount from 10 to 100000. (default: 2000)";
        cNPCOgreHP = prop.getInt(2000);
        if (cNPCOgreHP < 10) {
            cNPCOgreHP = 10;
        }
        if (cNPCOgreHP > 100000) {
            cNPCOgreHP = 100000;
        }
        NPCOgreHP = cNPCOgreHP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Otherworld Ogre Stat Damage", 500);
        prop.comment = "Server Sided! Otherworld Ogre NPCs Damage amount from 10 to 100000. (default: 500)";
        cNPCOgreDam = prop.getInt(500);
        if (cNPCOgreDam < 10) {
            cNPCOgreDam = 10;
        }
        if (cNPCOgreDam > 100000) {
            cNPCOgreDam = 100000;
        }
        NPCOgreDam = cNPCOgreDam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 1 Stat Health", 200);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 1 NPCs Health amount from 10 to 100000. (default: 200)";
        cNPC_RRMech1_HP = prop.getInt(200);
        if (cNPC_RRMech1_HP < 10) {
            cNPC_RRMech1_HP = 10;
        }
        if (cNPC_RRMech1_HP > 100000) {
            cNPC_RRMech1_HP = 100000;
        }
        NPC_RRMech1_HP = cNPC_RRMech1_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 1 Stat Damage", 20);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 1 NPCs Damage amount from 10 to 100000. (default: 20)";
        cNPC_RRMech1_Dam = prop.getInt(20);
        if (cNPC_RRMech1_Dam < 10) {
            cNPC_RRMech1_Dam = 10;
        }
        if (cNPC_RRMech1_Dam > 100000) {
            cNPC_RRMech1_Dam = 100000;
        }
        NPC_RRMech1_Dam = cNPC_RRMech1_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 2 Stat Health", 240);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 2 NPCs Health amount from 10 to 100000. (default: 240)";
        cNPC_RRMech2_HP = prop.getInt(240);
        if (cNPC_RRMech2_HP < 10) {
            cNPC_RRMech2_HP = 10;
        }
        if (cNPC_RRMech2_HP > 100000) {
            cNPC_RRMech2_HP = 100000;
        }
        NPC_RRMech2_HP = cNPC_RRMech2_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 2 Stat Damage", 35);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 2 NPCs Damage amount from 10 to 100000. (default: 35)";
        cNPC_RRMech2_Dam = prop.getInt(35);
        if (cNPC_RRMech2_Dam < 10) {
            cNPC_RRMech2_Dam = 10;
        }
        if (cNPC_RRMech2_Dam > 100000) {
            cNPC_RRMech2_Dam = 100000;
        }
        NPC_RRMech2_Dam = cNPC_RRMech2_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 3 Stat Health", 280);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 3 NPCs Health amount from 10 to 100000. (default: 280)";
        cNPC_RRMech3_HP = prop.getInt(280);
        if (cNPC_RRMech3_HP < 10) {
            cNPC_RRMech3_HP = 10;
        }
        if (cNPC_RRMech3_HP > 100000) {
            cNPC_RRMech3_HP = 100000;
        }
        NPC_RRMech3_HP = cNPC_RRMech3_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 - Type 3 Stat Damage", 50);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 - Type 3 NPCs Damage amount from 10 to 100000. (default: 50)";
        cNPC_RRMech3_Dam = prop.getInt(50);
        if (cNPC_RRMech3_Dam < 10) {
            cNPC_RRMech3_Dam = 10;
        }
        if (cNPC_RRMech3_Dam > 100000) {
            cNPC_RRMech3_Dam = 100000;
        }
        NPC_RRMech3_Dam = cNPC_RRMech3_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Sabertooth Stat Health", 150);
        prop.comment = "Server Sided! Sabertooth NPCs Health amount from 10 to 100000. (default: 150)";
        cNPC_SaberT_HP = prop.getInt(150);
        if (cNPC_SaberT_HP < 10) {
            cNPC_SaberT_HP = 10;
        }
        if (cNPC_SaberT_HP > 100000) {
            cNPC_SaberT_HP = 100000;
        }
        NPC_SaberT_HP = cNPC_SaberT_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Sabertooth Stat Damage", 15);
        prop.comment = "Server Sided! Sabertooth NPCs Damage amount from 10 to 100000. (default: 15)";
        cNPC_SaberT_Dam = prop.getInt(15);
        if (cNPC_SaberT_Dam < 10) {
            cNPC_SaberT_Dam = 10;
        }
        if (cNPC_SaberT_Dam > 100000) {
            cNPC_SaberT_Dam = 100000;
        }
        NPC_SaberT_Dam = cNPC_SaberT_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 1 Stat Health", 1000);
        prop.comment = "Server Sided! Dino 1 NPCs Health amount from 10 to 100000. (default: 1000)";
        cNPC_Dino1_HP = prop.getInt(1000);
        if (cNPC_Dino1_HP < 10) {
            cNPC_Dino1_HP = 10;
        }
        if (cNPC_Dino1_HP > 100000) {
            cNPC_Dino1_HP = 100000;
        }
        NPC_Dino1_HP = cNPC_Dino1_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 1 Stat Damage", 120);
        prop.comment = "Server Sided! Dino 1 NPCs Damage amount from 10 to 100000. (default: 120)";
        cNPC_Dino1_Dam = prop.getInt(120);
        if (cNPC_Dino1_Dam < 10) {
            cNPC_Dino1_Dam = 10;
        }
        if (cNPC_Dino1_Dam > 100000) {
            cNPC_Dino1_Dam = 100000;
        }
        NPC_Dino1_Dam = cNPC_Dino1_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 2 Stat Health", 300);
        prop.comment = "Server Sided! Dino 2 NPCs Health amount from 10 to 100000. (default: 300)";
        cNPC_Dino2_HP = prop.getInt(300);
        if (cNPC_Dino2_HP < 10) {
            cNPC_Dino2_HP = 10;
        }
        if (cNPC_Dino2_HP > 100000) {
            cNPC_Dino2_HP = 100000;
        }
        NPC_Dino2_HP = cNPC_Dino2_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 2 Stat Damage", 40);
        prop.comment = "Server Sided! Dino 2 NPCs Damage amount from 10 to 100000. (default: 40)";
        cNPC_Dino2_Dam = prop.getInt(40);
        if (cNPC_Dino2_Dam < 10) {
            cNPC_Dino2_Dam = 10;
        }
        if (cNPC_Dino2_Dam > 100000) {
            cNPC_Dino2_Dam = 100000;
        }
        NPC_Dino2_Dam = cNPC_Dino2_Dam;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 3 Stat Health", 500);
        prop.comment = "Server Sided! Dino 3 NPCs Health amount from 10 to 100000. (default: 500)";
        cNPC_Dino3_HP = prop.getInt(500);
        if (cNPC_Dino3_HP < 10) {
            cNPC_Dino3_HP = 10;
        }
        if (cNPC_Dino3_HP > 100000) {
            cNPC_Dino3_HP = 100000;
        }
        NPC_Dino3_HP = cNPC_Dino3_HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 3 Stat Damage", 40);
        prop.comment = "Server Sided! Dino 3 NPCs Damage amount from 10 to 100000. (default: 40)";
        cNPC_Dino3_Dam = prop.getInt(40);
        if (cNPC_Dino3_Dam < 10) {
            cNPC_Dino3_Dam = 10;
        }
        if (cNPC_Dino3_Dam > 100000) {
            cNPC_Dino3_Dam = 100000;
        }
        NPC_Dino3_Dam = cNPC_Dino3_Dam;
        int min = 10;
        int max = 100000;
        int DAMDef = 0;
        int HPDef = 0;
        String name = "";
        DAMDef = 35;
        HPDef = 200;
        name = "Bear Thief";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cBearThiefHP = prop.getInt(HPDef);
        if (cBearThiefHP < min) {
            cBearThiefHP = min;
        }
        if (cBearThiefHP > max) {
            cBearThiefHP = max;
        }
        BearThiefHP = cBearThiefHP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cBearThiefDAM = prop.getInt(DAMDef);
        if (cBearThiefDAM < min) {
            cBearThiefDAM = min;
        }
        if (cBearThiefDAM > max) {
            cBearThiefDAM = max;
        }
        BearThiefDAM = cBearThiefDAM;
        DAMDef = 30;
        HPDef = 200;
        name = "Tiger Bandit";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cTigerBanditHP = prop.getInt(HPDef);
        if (cTigerBanditHP < min) {
            cTigerBanditHP = min;
        }
        if (cTigerBanditHP > max) {
            cTigerBanditHP = max;
        }
        TigerBanditHP = cTigerBanditHP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cTigerBanditDAM = prop.getInt(DAMDef);
        if (cTigerBanditDAM < min) {
            cTigerBanditDAM = min;
        }
        if (cTigerBanditDAM > max) {
            cTigerBanditDAM = max;
        }
        TigerBanditDAM = cTigerBanditDAM;
        DAMDef = 50;
        HPDef = 200;
        name = "Major Metallitron";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cRRMajorHP = prop.getInt(HPDef);
        if (cRRMajorHP < min) {
            cRRMajorHP = min;
        }
        if (cRRMajorHP > max) {
            cRRMajorHP = max;
        }
        RRMajorHP = cRRMajorHP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cRRMajorDAM = prop.getInt(DAMDef);
        if (cRRMajorDAM < min) {
            cRRMajorDAM = min;
        }
        if (cRRMajorDAM > max) {
            cRRMajorDAM = max;
        }
        RRMajorDAM = cRRMajorDAM;
        DAMDef = 15;
        HPDef = 80;
        name = "Red Ribbon Soldier type 1";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cRRSoldierHP = prop.getInt(HPDef);
        if (cRRSoldierHP < min) {
            cRRSoldierHP = min;
        }
        if (cRRSoldierHP > max) {
            cRRSoldierHP = max;
        }
        RRSoldierHP = cRRSoldierHP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cRRSoldierDAM = prop.getInt(DAMDef);
        if (cRRSoldierDAM < min) {
            cRRSoldierDAM = min;
        }
        if (cRRSoldierDAM > max) {
            cRRSoldierDAM = max;
        }
        RRSoldierDAM = cRRSoldierDAM;
        DAMDef = 20;
        HPDef = 80;
        name = "Red Ribbon Soldier type 2";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cRRSoldier2HP = prop.getInt(HPDef);
        if (cRRSoldier2HP < min) {
            cRRSoldier2HP = min;
        }
        if (cRRSoldier2HP > max) {
            cRRSoldier2HP = max;
        }
        RRSoldier2HP = cRRSoldier2HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cRRSoldier2DAM = prop.getInt(DAMDef);
        if (cRRSoldier2DAM < min) {
            cRRSoldier2DAM = min;
        }
        if (cRRSoldier2DAM > max) {
            cRRSoldier2DAM = max;
        }
        RRSoldier2DAM = cRRSoldier2DAM;
        DAMDef = 40;
        HPDef = 80;
        name = "Red Ribbon Soldier type 3";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Health", HPDef);
        prop.comment = "Server Sided! " + name + " NPCs Health amount from " + min + " to " + max + ". (default: " + HPDef + ")";
        cRRSoldier3HP = prop.getInt(HPDef);
        if (cRRSoldier3HP < min) {
            cRRSoldier3HP = min;
        }
        if (cRRSoldier3HP > max) {
            cRRSoldier3HP = max;
        }
        RRSoldier3HP = cRRSoldier3HP;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Stat Damage", DAMDef);
        prop.comment = "Server Sided! " + name + " NPCs Damage amount from " + min + " to " + max + ". (default: " + DAMDef + ")";
        cRRSoldier3DAM = prop.getInt(DAMDef);
        if (cRRSoldier3DAM < min) {
            cRRSoldier3DAM = min;
        }
        if (cRRSoldier3DAM > max) {
            cRRSoldier3DAM = max;
        }
        RRSoldier3DAM = cRRSoldier3DAM;
        prop = config.get(CATEGORY_HEALTHPOD, "Health pod Update Timer (Tick)", 100);
        prop.comment = "Server Sided! Health pod Update Timer value from 1 to 10000. (Lower number can cause more lag) (default: 100)";
        chPodUpd = prop.getInt(100);
        if (chPodUpd < 1) {
            chPodUpd = 1;
        }
        if (chPodUpd > 10000) {
            chPodUpd = 10000;
        }
        hPodUpd = chPodUpd;
        boolean[] bo = new boolean[]{true, true, true};
        prop = config.get(CATEGORY_HEALTHPOD, "Healing Pod Percentage healing (Health, Ki, Stamina)", bo, "Server Sided! Healing Pod Percentage healing. Sets whenever the 'Healing Pod Rate (Health, Ki, Stamina)' config heals by percentage amount, rather than a fix one. (default: true true true)");
        chPodPerc = prop.getBooleanList();
        hPodPerc = chPodPerc;
        int[] num = new int[]{5, 5, 5};
        prop = config.get(CATEGORY_HEALTHPOD, "Healing Pod Rate (Health, Ki, Stamina) from 1 to 10000", num, "Server Sided! Healing Pod Rate. Sets the amount it will heal the Players. (default: 5 5 5)");
        chPodRate = prop.getIntList();
        hPodRate = chPodRate;
        for (int i2 = 0; i2 < num.length; ++i2) {
            if (hPodRate[i2] < 1) {
                DBCConfig.hPodRate[i2] = 1;
            } else if (hPodRate[i2] > 10000) {
                DBCConfig.hPodRate[i2] = 10000;
            }
            if (!hPodPerc[i2] || hPodRate[i2] <= 100) continue;
            DBCConfig.hPodRate[i2] = 100;
        }
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Otherworld Ogre Spawn rate", 10);
        prop.comment = "Server Sided! Otherworld Ogre NPCs Spawn rate from 0 to 100. (default: 10)";
        cspwnrt_ogre = prop.getInt(10);
        if (cspwnrt_ogre < 0) {
            cspwnrt_ogre = 0;
        }
        if (cspwnrt_ogre > 100) {
            cspwnrt_ogre = 100;
        }
        spwnrt_ogre = cspwnrt_ogre;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Red Ribbon Mecha 1 Spawn rate", 3);
        prop.comment = "Server Sided! Red Ribbon Mecha 1 NPCs Spawn rate from 0 to 100. (default: 3)";
        cspwnrt_rrmech1 = prop.getInt(3);
        if (cspwnrt_rrmech1 < 0) {
            cspwnrt_rrmech1 = 0;
        }
        if (cspwnrt_rrmech1 > 100) {
            cspwnrt_rrmech1 = 100;
        }
        spwnrt_rrmech1 = cspwnrt_rrmech1;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Sabertooth Spawn rate", 2);
        prop.comment = "Server Sided! Sabertooth NPCs Spawn rate from 0 to 100. (default: 2)";
        cspwnrt_sabert = prop.getInt(2);
        if (cspwnrt_sabert < 0) {
            cspwnrt_sabert = 0;
        }
        if (cspwnrt_sabert > 100) {
            cspwnrt_sabert = 100;
        }
        spwnrt_sabert = cspwnrt_sabert;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 1 Spawn rate", 2);
        prop.comment = "Server Sided! Dino 1 NPCs Spawn rate from 0 to 100. (default: 2)";
        cspwnrt_dino1 = prop.getInt(2);
        if (cspwnrt_dino1 < 0) {
            cspwnrt_dino1 = 0;
        }
        if (cspwnrt_dino1 > 100) {
            cspwnrt_dino1 = 100;
        }
        spwnrt_dino1 = cspwnrt_dino1;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 2 Spawn rate", 10);
        prop.comment = "Server Sided! Dino 2 NPCs Spawn rate from 0 to 100. (default: 10)";
        cspwnrt_dino2 = prop.getInt(10);
        if (cspwnrt_dino2 < 0) {
            cspwnrt_dino2 = 0;
        }
        if (cspwnrt_dino2 > 100) {
            cspwnrt_dino2 = 100;
        }
        spwnrt_dino2 = cspwnrt_dino2;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Dino 3 Spawn rate", 5);
        prop.comment = "Server Sided! Dino 3 NPCs Spawn rate from 0 to 100. (default: 5)";
        cspwnrt_dino3 = prop.getInt(5);
        if (cspwnrt_dino3 < 0) {
            cspwnrt_dino3 = 0;
        }
        if (cspwnrt_dino3 > 100) {
            cspwnrt_dino3 = 100;
        }
        spwnrt_dino3 = cspwnrt_dino3;
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Namekian Frog Spawn rate", 5);
        prop.comment = "Server Sided! Namekian Frog NPCs Spawn rate from 0 to 100. (default: 5)";
        cspwnrt_frg = prop.getInt(5);
        if (cspwnrt_frg < 0) {
            cspwnrt_frg = 0;
        }
        if (cspwnrt_frg > 100) {
            cspwnrt_frg = 100;
        }
        spwnrt_frg = cspwnrt_frg;
        prop = config.get(CATEGORY_NPCCONFIGS, "Planet Vegeta Saiyan 1 Spawn rate", 2);
        prop.comment = "Server Sided! Planet Vegeta Saiyan 1 Spawn rate from 0 to 100. (default: 2)";
        cspwnrt_syn = prop.getInt(2);
        if (cspwnrt_syn < 0) {
            cspwnrt_syn = 0;
        }
        if (cspwnrt_syn > 100) {
            cspwnrt_syn = 100;
        }
        spwnrt_syn = cspwnrt_syn;
        prop = config.get(CATEGORY_NPCCONFIGS, "Planet Vegeta Saiyan 1 Spawn rate", 4);
        prop.comment = "Server Sided! Planet Vegeta Saiyan 1 Spawn rate from 0 to 100. (default: 4)";
        cspwnrt_syn2 = prop.getInt(4);
        if (cspwnrt_syn2 < 0) {
            cspwnrt_syn2 = 0;
        }
        if (cspwnrt_syn2 > 100) {
            cspwnrt_syn2 = 100;
        }
        spwnrt_syn2 = cspwnrt_syn2;
        int spawnrateDef = 0;
        min = 0;
        max = 100;
        spawnrateDef = 1;
        name = "Bear Thief";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Spawn rate", spawnrateDef);
        prop.comment = "Server Sided! " + name + " NPCs Spawn rate from " + min + " to " + max + ". (default: " + spawnrateDef + ")";
        cSpawnrateBearThief = prop.getInt(spawnrateDef);
        if (cSpawnrateBearThief < min) {
            cSpawnrateBearThief = min;
        }
        if (cSpawnrateBearThief > max) {
            cSpawnrateBearThief = max;
        }
        SpawnrateBearThief = cSpawnrateBearThief;
        spawnrateDef = 1;
        name = "Tiger Bandit";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Spawn rate", spawnrateDef);
        prop.comment = "Server Sided! " + name + " NPCs Spawn rate from " + min + " to " + max + ". (default: " + spawnrateDef + ")";
        cSpawnrateTigerBandit = prop.getInt(spawnrateDef);
        if (cSpawnrateTigerBandit < min) {
            cSpawnrateTigerBandit = min;
        }
        if (cSpawnrateTigerBandit > max) {
            cSpawnrateTigerBandit = max;
        }
        SpawnrateTigerBandit = cSpawnrateTigerBandit;
        spawnrateDef = 1;
        name = "Red Ribbon Major Metallitron";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Spawn rate", spawnrateDef);
        prop.comment = "Server Sided! " + name + " NPCs Spawn rate from " + min + " to " + max + ". (default: " + spawnrateDef + ")";
        cSpawnrateRRMajor = prop.getInt(spawnrateDef);
        if (cSpawnrateRRMajor < min) {
            cSpawnrateRRMajor = min;
        }
        if (cSpawnrateRRMajor > max) {
            cSpawnrateRRMajor = max;
        }
        SpawnrateRRMajor = cSpawnrateRRMajor;
        spawnrateDef = 1;
        name = "Red Ribbon Soldiers";
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC " + name + " Spawn rate", spawnrateDef);
        prop.comment = "Server Sided! " + name + " NPCs Spawn rate from " + min + " to " + max + ". (default: " + spawnrateDef + ")";
        cSpawnrateRRSoldiers = prop.getInt(spawnrateDef);
        if (cSpawnrateRRSoldiers < min) {
            cSpawnrateRRSoldiers = min;
        }
        if (cSpawnrateRRSoldiers > max) {
            cSpawnrateRRSoldiers = max;
        }
        SpawnrateRRSoldiers = cSpawnrateRRSoldiers;
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Switch Inventory", false);
        prop.comment = "Server Sided! If 'true' and keepInventory is On then players will have separate inventory for dead and living states (Creative switching while dead will cause issues). If 'false' then like it was before depending on keepInventory's state";
        DeadInv = cDeadInv = prop.getBoolean();
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Free Revive", true);
        prop.comment = "Server Sided! If 'true' players can revive for free, 'Revive Timer' config will be used to determine the revive interval.If 'false' then players wont be able to revive for free even after time, Only way to revive is to wish revival, by Reincarnation, or dbcrevive command.";
        FreeRev = cFreeRev = prop.getBoolean();
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Revive Timer", 1);
        prop.comment = "Server Sided! The time in minutes after a player can use 'Free Revive' if it is 'true'. The time can go from 1 min to 100000 mins (close to a week). (default: 1)";
        cRevTm = prop.getInt() * 12;
        if (cRevTm < 12) {
            cRevTm = 12;
        }
        if (cRevTm > 1200000) {
            cRevTm = 1200000;
        }
        RevTm = cRevTm;
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Reincarnation Penalty", 25);
        prop.comment = "Server Sided! Reincarnation resets everything like starting new, but leaving a percentage of attributes left. Value can go from 0 to 100. (default: 25) #to disable it use 0!";
        cReinc = (float)prop.getInt() * 0.01001f;
        if (cReinc < 0.0f) {
            cReinc = 0.0f;
        }
        if (cReinc > 1.0f) {
            cReinc = 1.0f;
        }
        Reinc = cReinc;
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Transformation - SuperSaiyanGod Helpers needed", 5);
        prop.comment = "Server Sided! The amount of Good Saiyans needed to preform the SSG ritual. The amount can go from 1 to 100. (default: 5)";
        cSSGHelp = prop.getInt();
        if (cSSGHelp < 1) {
            cSSGHelp = 1;
        }
        if (cSSGHelp > 100) {
            cSSGHelp = 100;
        }
        SSGHelp = cSSGHelp;
        prop = config.get(CATEGORY_TECHNIQUES, "Ki Technique - Experience gain rate per hit", 1);
        prop.comment = "Server Sided! Exp gain rate per living entity hit for ki techniques. The higher the more exp will be gained. The amount can go from 1 to 10000. (default: 1)";
        cTechExpRate = prop.getInt();
        if (cTechExpRate < 1) {
            cTechExpRate = 1;
        }
        if (cTechExpRate > 10000) {
            cTechExpRate = 10000;
        }
        TechExpRate = cTechExpRate;
        prop = config.get(CATEGORY_TECHNIQUES, "Ki Technique - Experience needed to upgrade", 15);
        prop.comment = "Server Sided! Exp amount needed to upgrade to first level, the other levels will multiplied with this amount. The higher the more exp will be needed to upgrade. The amount can go from 10 to 1000. (default: 15)";
        cTechExpNeed = prop.getInt();
        if (cTechExpNeed < 10) {
            cTechExpNeed = 10;
        }
        if (cTechExpNeed > 1000) {
            cTechExpNeed = 1000;
        }
        TechExpNeed = cTechExpNeed;
        prop = config.get(CATEGORY_TECHNIQUES, "Ki Technique - Energy cost modifier", 50);
        prop.comment = "Server Sided! Ki cost modifier, with this you can reduce in percentage the costs of ki attacks. So your Ki attacks can cost less or more Ki. The amount can go from 1 to 1000. (default: 100)";
        cTechCostMod = prop.getInt();
        if (cTechCostMod < 1) {
            cTechCostMod = 1;
        }
        if (cTechCostMod > 1000) {
            cTechCostMod = 1000;
        }
        TechCostMod = cTechCostMod;
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Transformation - SuperSaiyanGod Helpers fatigue", 10);
        prop.comment = "Server Sided! How long helper saiyans can't help in SSG ritual again. The time can go from 0 mins to 1000 mins. (default: 10)";
        cStrainGod = prop.getInt() * 12;
        if (cStrainGod < 0) {
            cStrainGod = 0;
        }
        if (cStrainGod > 12000) {
            cStrainGod = 12000;
        }
        StrainGod = cStrainGod;
        double[] d = new double[]{75.0, 220.0, 55.0};
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Good Revive Location", d, "Server Sided! Revive position for players with good alignment.");
        cRevLocG = prop.getDoubleList();
        RevLocG = cRevLocG;
        d = new double[]{58.0, 220.0, 7.0};
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Neutral Revive Location", d, "Server Sided! Revive position for players with neutral alignment.");
        cRevLocN = prop.getDoubleList();
        RevLocN = cRevLocN;
        d = new double[]{96.0, 230.0, 7.0};
        prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - Evil Revive Location", d, "Server Sided! Revive position for players with evil alignment.");
        cRevLocE = prop.getDoubleList();
        RevLocE = cRevLocE;
        String[] datas = new String[]{"Good", "Neutral", "Evil"};
        d = new double[]{0.0, 0.0};
        for (i = 0; i < datas.length; ++i) {
            prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - " + datas[i] + " Revive Rotation", d, "Server Sided! Revive rotation for players with " + datas[i] + " alignment.");
            DBCConfig.cRevAng[i] = prop.getDoubleList();
            DBCConfig.RevAng[i] = cRevAng[i];
        }
        for (i = 0; i < datas.length; ++i) {
            prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - " + datas[i] + " Revive Dimension", 0, "Server Sided! Revive dimension for players with " + datas[i] + " alignment.");
            prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - " + datas[i] + " Revive Dimension", 0, "Server Sided! Revive dimension for players with " + datas[i] + " alignment.");
            DBCConfig.cRevDim[i] = prop.getInt();
            DBCConfig.RevDim[i] = cRevDim[i];
        }
        d = new double[]{75.0, 92.0, 127.0};
        for (i = 0; i < datas.length; ++i) {
            prop = config.get(CATEGORY_DEATHSYSTEM, "Death System - " + datas[i] + " Death Location Otherworld", d, "Server Sided! Death location in the Otherworld for players with " + datas[i] + " alignment. (X, Y, Z)");
            DBCConfig.cDeathDim[i] = prop.getDoubleList();
            DBCConfig.DeathDim[i] = cDeathDim[i];
        }
        d = new double[]{8.0, 135.0, 70.0};
        prop = config.get(CATEGORY_NPCCONFIGS, "Whis Teleportation Location in the Null Realm", d, "Server Sided! Whis Teleportation Location in the Null Realm.");
        cWhisTeleportLocationNull = prop.getDoubleList();
        WhisTeleportLocationNull = cWhisTeleportLocationNull;
        d = new double[]{71.0, 217.0, 60.0};
        prop = config.get(CATEGORY_NPCCONFIGS, "Whis Teleportation Location in the Overworld", d, "Server Sided! Whis Teleportation Location in the Overworld.");
        cWhisTeleportLocationBack = prop.getDoubleList();
        WhisTeleportLocationBack = cWhisTeleportLocationBack;
        prop = config.get(CATEGORY_NPCCONFIGS, "Whis Teleportation can be used", true, "Server Sided! Whis Teleportation can be used.");
        CanWhisTeleport = cCanWhisTeleport = prop.getBoolean();
        prop = config.get(CATEGORY_NPCCONFIGS, "Enma Scale", 1.3);
        prop.comment = "Server Sided! Enma Scale. (default: 1.3)";
        cEnmaScale = (float)prop.getDouble(1.3);
        if (cEnmaScale < 0.0f) {
            cEnmaScale = 0.0f;
        }
        if (cEnmaScale > 10000.0f) {
            cEnmaScale = 10000.0f;
        }
        EnmaScale = cEnmaScale;
        prop = config.get(CATEGORY_NPCCONFIGS, "Guru Scale", 1.25);
        prop.comment = "Server Sided! Guru Scale. (default: 1.25)";
        cGuruScale = (float)prop.getDouble(1.25);
        if (cGuruScale < 0.0f) {
            cGuruScale = 0.0f;
        }
        if (cGuruScale > 10000.0f) {
            cGuruScale = 10000.0f;
        }
        GuruScale = cGuruScale;
        prop = config.get(CATEGORY_SERVERSIDED, "Null Realm Minimum Height", 20);
        prop.comment = "Server Sided! Null Realm Minimum Height. Teleports people below this Y location to Whis. (default: 20)";
        NullRealmMinimumHeight = cNullRealmMinimumHeight = prop.getInt(20);
        prop = config.get(CATEGORY_SERVERSIDED, "Null Realm Background Color Green", true, "Server Sided! Null Realm Background Color is Green (TRUE) or Purple (FALSE).");
        NullRealmBGColorNodeGreen = cNullRealmBGColorNodeGreen = prop.getBoolean();
        prop = config.get(CATEGORY_NPCCONFIGS, "DBC Advanced AI Force Difficulty", -1);
        prop.comment = "Server Sided! Forces a specific AI difficulty for DBC Entities using the AAi System. (default: -1)\n(-1 = Force Disabled | 0 = Easy | 1 = Medium | 2 = Hard | 3 = Insane)";
        cAaiForceDifficulty = prop.getInt(-1);
        if (cAaiForceDifficulty < -1) {
            cAaiForceDifficulty = -1;
        } else if (cAaiForceDifficulty > 3) {
            cAaiForceDifficulty = 3;
        }
        AaiForceDifficulty = cAaiForceDifficulty;
        prop = config.get(CATEGORY_NPCCONFIGS, "DBC AAI Disabled", false, "Server Sided! is DBC Advanced AI System enabled. (true OR false) (default: false)");
        AaiDisabled = cAaiDisabled = prop.getBoolean();
        prop = config.get(CATEGORY_NPCCONFIGS, "DBC Enemy Default Attack Tick Timer", 35);
        prop.comment = "Server Sided! Sets how often a DBC enemy/entity attacks when possible From 0 to 10000. (default: 35)";
        cEnemyDefaultAttackTimer = prop.getInt(35);
        if (cEnemyDefaultAttackTimer < 0) {
            cEnemyDefaultAttackTimer = 0;
        } else if (cEnemyDefaultAttackTimer > 10000) {
            cEnemyDefaultAttackTimer = 10000;
        }
        EnemyDefaultAttackTimer = cEnemyDefaultAttackTimer;
        prop = config.get(CATEGORY_NPCCONFIGS, "DBC Enemy Default Short Range Attack Tick Timer", 15);
        prop.comment = "Server Sided! Sets how often a DBC enemy/entity attacks when possible and close to the target From 0 to 10000.\n(Must be lower than the Default Attack Tick Timer) (default: 15)";
        cEnemyDefaultShortRangeAttackTimer = prop.getInt(15);
        if (cEnemyDefaultShortRangeAttackTimer < 0) {
            cEnemyDefaultShortRangeAttackTimer = 0;
        } else if (cEnemyDefaultShortRangeAttackTimer > EnemyDefaultAttackTimer) {
            cEnemyDefaultShortRangeAttackTimer = EnemyDefaultAttackTimer;
        }
        EnemyDefaultShortRangeAttackTimer = cEnemyDefaultShortRangeAttackTimer;
        prop = config.get(CATEGORY_NPCCONFIGS, "DBC Enemy Default Movement Speed", 0.699);
        prop.comment = "Server Sided! From 0 to 100. (default: 0.699)";
        cEnemyDefaultMoveSpeed = prop.getDouble(0.699);
        if (cEnemyDefaultMoveSpeed < 0.0) {
            cEnemyDefaultMoveSpeed = 0.0;
        } else if (cEnemyDefaultMoveSpeed > 100.0) {
            cEnemyDefaultMoveSpeed = 100.0;
        }
        EnemyDefaultMoveSpeed = cEnemyDefaultMoveSpeed;
        prop = config.get(CATEGORY_SERVERSIDED, "Ki Attacks Go Through Invulnerable Enemies", false, "Server Sided! True = Go through Enemies they can't damage. False = Vanish when hitting Enemies they can't damage. (true OR false) (default: false)");
        KiAttackGoThroughInvulnerableEnemies = cKiAttackGoThroughInvulnerableEnemies = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Instant Transformation Percentage Ki Cost", 3);
        prop.comment = "Server Sided! Instant Transformation Percentage Ki Cost. (default: 3)";
        InstantTransformKiPercentageCost = prop.getInt(3);
        if (InstantTransformKiPercentageCost < 0) {
            InstantTransformKiPercentageCost = 0;
        } else if (InstantTransformKiPercentageCost > 100) {
            InstantTransformKiPercentageCost = 100;
        }
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Instant Transformation Ki Cost", 1000);
        prop.comment = "Server Sided! Instant Transformation Ki Cost. (default: 1000)";
        InstantTransformKiCost = prop.getInt(1000);
        if (InstantTransformKiCost < 0) {
            InstantTransformKiCost = 0;
        }
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Instant Transformation On", true, "Server Sided! True = Instant Transformation On. (true OR false) (default: true)");
        InstantTransformOn = cInstantTransformOn = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Single Form Descend On", true, "Server Sided! True = Single Form Descend On. (true OR false) (default: true)");
        SingleFormDescendOn = cSingleFormDescendOn = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Kaioken Single Form Descend On", true, "Server Sided! True = Kaioken Single Form Descend On. (true OR false) (default: true)");
        KaiokenSingleFormDescendOn = cKaiokenSingleFormDescendOn = prop.getBoolean();
        String[] stringList = new String[]{"Kaioken false", "UltraInstinct false", "Mystic false", "GodOfDestruction false"};
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Other Enabled Instant Transformation Skills and Forms", stringList);
        prop.comment = "Server Sided! A list of Skills and Forms that can be enabled (true) or disabled (false) if players can Instant Transform to them.";
        String[] stringListValues = prop.getStringList();
        for (int i3 = 0; i3 < IsInstantTransformEnabled.length; ++i3) {
            String string = "";
            if (stringListValues.length <= i3) {
                string = stringList[i3];
            } else {
                stringList[i3] = stringListValues[i3];
                string = stringListValues[i3];
            }
            DBCConfig.cIsInstantTransformEnabled[i3] = Boolean.parseBoolean(string.split(" ")[1]);
            DBCConfig.IsInstantTransformEnabled[i3] = cIsInstantTransformEnabled[i3];
        }
        prop.set(stringList);
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Players can Move while Transforming On", false, "Server Sided! True = Players can move while they are Transforming. (true OR false) (default: false)");
        MoveWhileTransforming = cMoveWhileTransforming = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Players can Move while Instant Transforming On", true, "Server Sided! True = Players can move while they are Instant Transforming. (true OR false) (default: true)");
        MoveWhileInstantTransforming = cMoveWhileInstantTransforming = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Mystic Kaioken Enabled", true, "Server Sided! True = Players can use Mystic form together with Kaioken. (true OR false) (default: true)");
        MysticKaiokenOn = cMysticKaiokenOn = prop.getBoolean();
        prop = config.get(CATEGORY_NPCCONFIGS, "Enemies can Teleport out of Ki Wave Locks On", true, "Server Sided! True = DBC Enemies can Teleport out of Continues Ki Wave Locks. (true OR false) (default: true)");
        EnemyTeleportOutOfLock = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Transformations - God Form", true);
        prop.comment = "Server Sided! If 'true' then God transformations will be enabled. If 'false' then god transformations will be disabled";
        Godform = cGodform = prop.getBoolean();
        prop = config.get(CATEGORY_TRANSFORMATIONS, "Transformations - God Forms Cosmetics", true);
        prop.comment = "Client Sided! If 'true' then God transformations will have SSG red eyes and SSG flaming aura. If 'false' then god transformations wont have any difference in eye and aura";
        GodformCosm = cGodformCosm = prop.getBoolean();
        prop = config.get(CATEGORY_NPCCONFIGS, "NPC Otherworld Ogre Disable", false);
        prop.comment = "Server Sided! If 'true' then Ogres in otherworld will get disabled. If 'false' Ogres are enabled. (default: false)";
        DsblO = cDsblO = prop.getBoolean();
        prop = config.get(CATEGORY_SERVERSIDED, "DBGT mode", true);
        prop.comment = "Server Sided! GT mode refers mainly to transformations for now, like the new saiyan form introduced in DBGT. (default: true)";
        DBGT = cDBGT = prop.getBoolean();
        if (side == Side.CLIENT) {
            mod_DragonBC.cwfb = config.get(CATEGORY_CLIENTSIDED, "Custom X pos for Ki Bar", 0).getInt(0);
            mod_DragonBC.chfb = config.get(CATEGORY_CLIENTSIDED, "Custom Y pos for Ki Bar", 0).getInt(0);
            mod_DragonBC.cwfn = config.get(CATEGORY_CLIENTSIDED, "Custom X pos for Ki Number", 0).getInt(0);
            mod_DragonBC.chfn = config.get(CATEGORY_CLIENTSIDED, "Custom Y pos for Ki Number", 0).getInt(0);
        }
        config.save();
        DBCH.wishInit();
        DBCH.plntNmsInit();
    }

    static {
        eDBrate = 2;
        nDBrate = 3;
        maxTrnExp = 1000;
        healingpodhealingrate = 100;
        senzuFromKarin = 1;
        senzuCool = 15;
        ceDBrate = 2;
        cnDBrate = 3;
        cmaxTrnExp = 1000;
        chealingpodhealingrate = 100;
        csenzuFromKarin = 1;
        csenzuCool = 15;
        NPCOgreHP = 2000;
        NPCOgreDam = 500;
        NPC_RRMech1_HP = 200;
        NPC_RRMech1_Dam = 20;
        NPC_RRMech2_HP = 240;
        NPC_RRMech2_Dam = 35;
        NPC_RRMech3_HP = 280;
        NPC_RRMech3_Dam = 50;
        NPC_SaberT_HP = 150;
        NPC_SaberT_Dam = 15;
        NPC_Dino1_HP = 1000;
        NPC_Dino1_Dam = 120;
        NPC_Dino2_HP = 300;
        NPC_Dino2_Dam = 40;
        NPC_Dino3_HP = 500;
        NPC_Dino3_Dam = 40;
        RevAng = new double[3][2];
        cRevAng = new double[3][2];
        RevDim = new int[3];
        cRevDim = new int[3];
        DeathDim = new double[3][3];
        cDeathDim = new double[3][3];
        hPodRate = new int[3];
        chPodRate = new int[3];
        hPodPerc = new boolean[3];
        chPodPerc = new boolean[3];
        cIsInstantTransformEnabled = new boolean[4];
        IsInstantTransformEnabled = new boolean[4];
    }
}

