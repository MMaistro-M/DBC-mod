/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Entitys.EntityEnergyFM;
import JinRyuu.DragonBC.common.Npcs.EntityAura2;
import JinRyuu.DragonBC.common.Npcs.EntityAuraRing;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import JinRyuu.JRMCore.p.DBC.DBCPascend;
import JinRyuu.JRMCore.p.DBC.DBCPascendsound;
import JinRyuu.JRMCore.p.DBC.DBCPchargesound;
import JinRyuu.JRMCore.p.DBC.DBCPdescend;
import JinRyuu.JRMCore.p.DBC.DBCPdescendsound;
import JinRyuu.JRMCore.p.DBC.DBCPduo;
import JinRyuu.JRMCore.p.DBC.DBCPenergy;
import JinRyuu.JRMCore.p.JRMCorePTri;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DBCKiTech {
    public static int ascend = 0;
    public static int pup = 0;
    public static int pup2 = 0;
    public static int ptime = 0;
    public static int inSuperTime = 0;
    private static int time = 0;
    private static int time2 = 0;
    private static int time3 = 0;
    private static int partnorm = 0;
    private static int power = 0;
    private static int jump;
    public static boolean jumping;
    public static boolean jumpRel;
    public static boolean floating;
    public static boolean releasing;
    public static boolean ascending;
    public static boolean ascendingK;
    private static float sec;
    private static boolean jumpToFly;
    private static Instant quickTransformTimer;
    private static int quickTransformCount;
    private static boolean quickTransformAdded;
    private static Instant singleDescendTimer;
    private static int singleDescendCount;
    private static boolean singleDescendAdded;
    private static Instant holdingTransformTimer;
    private static boolean holdingForTransform;
    private static boolean sentAbsorption;
    public static float flyY;
    public static int floatTime;
    public static boolean floatMultAdded;
    private static boolean dodge_forwDash_STE;
    private static float sent;
    public static final int SWOOP_FORWARD = 0;
    public static final int SWOOP_LEFT = 1;
    public static final int SWOOP_BACK = 2;
    public static final int SWOOP_RIGHT = 3;
    public static int swoopDirection;
    public static int tickTime;
    public static int prevTickTime;
    public static int dash;
    public static int dodge_per;
    public static long dodge_recently;
    public static boolean dodge_forwHold;
    public static int dodge_forwSwTm;
    public static boolean turbo;
    public static int ton;
    public static boolean wasTransformPressed;
    public static HashMap<String, EntityAura2> ra;

    public static void ChargeKi() {
        block11: {
            block14: {
                float re;
                float en;
                block13: {
                    block12: {
                        boolean b;
                        if (++partnorm >= 5) {
                            partnorm = 0;
                            DBCKiTech.chargePart(false);
                        }
                        if (++power >= (int)(JRMCoreH.State == 10 && JRMCoreH.State2 > 0 ? JRMCoreCliTicH.counterValue * 3.0f : JRMCoreCliTicH.counterValue * 2.5f)) {
                            power = 0;
                            DBCKiTech.chargePart(true);
                        }
                        boolean bl = b = DBCKiTech.isPressed(JRMCoreKeyHandler.KiCharge) && !DBCKiTech.isPressed(JRMCoreKeyHandler.KiAscend) && !JRMCoreH.StusEfctsMe(11);
                        if (b) {
                            if (!releasing) {
                                releasing = true;
                                JRMCoreH.Skll((byte)5, (byte)0, (byte)4);
                            }
                        } else if (releasing) {
                            releasing = false;
                            JRMCoreH.Skll((byte)5, (byte)1, (byte)4);
                        }
                        if (!b) break block11;
                        ++time;
                        ++time2;
                        ++time3;
                        if (!DBCKiTech.isPressed(JRMCoreKeyHandler.Fn) || (JRMCoreH.Race == 0 || JRMCoreH.Race == 3) && JRMCoreH.State > 0) break block12;
                        if (time3 >= 10) {
                            time3 = 0;
                            JRMCoreH.Rls((byte)2);
                        }
                        break block11;
                    }
                    if (JRMCoreH.curRelease >= 50 + JRMCoreH.SklLvl(5, (byte)1) * 5) break block11;
                    if (time < (JRMCoreH.curRelease >= 50 ? (turbo ? 15 : 30) : (turbo ? 5 : 10))) break block11;
                    time = 0;
                    en = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                    re = JRMCoreH.curRelease;
                    float f = en = en > 75.0f ? 75.0f : en;
                    if (!(re >= 50.0f)) break block13;
                    if (!(re - 50.0f < (en - 50.0f) * 2.0f)) break block11;
                    break block14;
                }
                float f = en <= 10.0f ? en * 5.0f : 50.0f;
                if (!(re < f)) break block11;
            }
            JRMCoreH.Rls((byte)1);
        }
    }

    public static void JumpKi(KeyBinding keyBindJump) {
        boolean relStp;
        EntityClientPlayerMP var4 = DBCClient.mc.field_71439_g;
        float t = turbo ? 1.0f : 0.8f;
        float n = JRMCoreH.SklLvl(1, (byte)1);
        float max = 0.22f;
        float prc = 0.3f + 0.05f * n + (float)(JRMCoreH.PlyrAttrbts(null)[1] / DBCKiTech.gYZc2f()) * 0.2f;
        float rel = (float)JRMCoreH.curRelease * 0.01f;
        float add = max * prc * t * rel;
        int cost = 1 + (int)(add * 30.0f);
        boolean able = true;
        if (JRMCoreH.curEnergy < cost) {
            able = false;
        }
        boolean bl = JRMCoreConfig.releaseStop ? !releasing : (relStp = true);
        if (DBCKiTech.isPressed(keyBindJump) && !relStp) {
            KeyBinding.func_74510_a((int)keyBindJump.func_151463_i(), (boolean)false);
            if (JRMCoreClient.mc.field_71439_g.field_70181_x > 0.0) {
                JRMCoreClient.mc.field_71439_g.field_70181_x = 0.0;
            }
        }
        if (DBCKiTech.isPressed(keyBindJump) && relStp && !jumpRel && able) {
            jumping = true;
            if (jump >= 10) {
                jumping = false;
            }
            if (jump >= 1 && jump < 10 && jump / 5 * 5 == jump) {
                JRMCoreH.Cost(cost);
            }
            if (jumping) {
                DBCClient.mc.field_71439_g.field_70181_x += 0.05 + (double)add;
            }
        } else {
            jumpRel = true;
        }
        if (!jumpRel && !floating && ++jump == 4) {
            JRMCoreH.KAsounds((Entity)var4, 0);
        }
        if (var4.field_70122_E) {
            jump = 0;
            jumpRel = false;
        }
    }

    public static long dtap1() {
        return System.currentTimeMillis() + 50L;
    }

    public static long dtap4() {
        return System.currentTimeMillis() + 250L;
    }

    public static void FloatKi(KeyBinding kiFlight, KeyBinding keyBindJump, KeyBinding keyBindSneak) {
        boolean b;
        boolean isSwooping;
        boolean dodge;
        boolean forw;
        EntityClientPlayerMP p = DBCClient.mc.field_71439_g;
        int n = JRMCoreH.SklLvl(3, (byte)1);
        int st = JRMCoreH.StusEfctsMe(13) ? (JRMCoreH.rc_sai(JRMCoreH.Race) ? JRMCoreH.mstc_sai(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) - 1) : (JRMCoreH.rc_arc(JRMCoreH.Race) ? JRMCoreH.mstc_arc() : (JRMCoreH.rc_humNam(JRMCoreH.Race) ? JRMCoreH.mstc_humnam() : 1))) : JRMCoreH.State;
        float inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 11, 100, JRMCoreH.Race, JRMCoreH.Class, 0.0f) * 0.01f;
        float add = JRMCoreH.spdFrm(JRMCoreH.PlyrAttrbts(null)[4], n, JRMCoreH.curRelease, turbo, true, st, JRMCoreH.State2, inc);
        boolean pressingRightClick = DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74313_G);
        boolean pressingForward = DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74351_w);
        boolean pressingLeft = DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74370_x);
        boolean pressingBack = DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74368_y);
        boolean pressingRight = DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74366_z);
        boolean isAnyDirectionKeyPressing = !JRMCoreH.StusEfctsMe(4) && !pressingRightClick && (pressingForward || pressingLeft || pressingBack || pressingRight);
        boolean bl = forw = isAnyDirectionKeyPressing && DBCKiTech.isPressed(JRMCoreKeyHandler.Fn);
        if (dodge_forwSwTm == 0 && DBCKiTech.isPressed(JRMCoreKeyHandler.Fn) && !DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74313_G) && !DBCKiTech.isPressed(JRMCoreKeyHandler.KiCharge) && !DBCKiTech.isPressed(JRMCoreKeyHandler.KiAscend)) {
            int n2 = dodge_forwSwTm = forw ? 1 : 0;
        }
        if (dodge_forwSwTm > 0 && isAnyDirectionKeyPressing && !JRMCoreClient.mc.field_71439_g.field_70122_E) {
            dodge_forwHold = true;
            ++dodge_forwSwTm;
        } else {
            dodge_forwHold = false;
            dodge_forwSwTm = 0;
        }
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts();
        byte pwr = JRMCoreH.Pwrtyp;
        byte rce = JRMCoreH.Race;
        byte cls = JRMCoreH.Class;
        int maxEnergy = JRMCoreH.stat((Entity)p, 5, pwr, 5, PlyrAttrbts[5], rce, cls, 0.0f);
        int ce2 = JRMCoreH.curEnergy;
        int cst2 = (int)((float)maxEnergy * 0.1f - (float)n * 0.005f);
        int maxStam = JRMCoreH.stat((Entity)p, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
        int ce = JRMCoreH.curStamina;
        int cst = (int)((float)maxStam * (0.2f - (float)n * 0.005f));
        boolean bl2 = dodge = !JRMCoreH.PlyrSettingsB(2);
        if (ce > cst && n > 0 && dodge && ce2 > cst2 && !p.func_70115_ae()) {
            if (dodge_forwHold && !p.field_70122_E && dodge_forwHold) {
                if (!dodge_forwDash_STE) {
                    dodge_forwDash_STE = true;
                }
                if (floating) {
                    floating = false;
                }
                float s = add * 1.5f;
                float w = JRMCoreH.weightPerc(1);
                s *= w;
                double motionX = 0.0;
                double motionY = 0.0;
                double motionZ = 0.0;
                float yaw = p.field_70177_z;
                float pitch = p.field_70125_A;
                if (pressingLeft) {
                    yaw -= 90.0f / (pressingForward || pressingBack ? 2.0f : 1.0f) * (pressingBack ? -1.0f : 1.0f);
                    pitch = 0.0f;
                } else if (pressingRight) {
                    yaw += 90.0f / (pressingForward || pressingBack ? 2.0f : 1.0f) * (pressingBack ? -1.0f : 1.0f);
                    pitch = 0.0f;
                }
                if (pressingForward) {
                    pitch = p.field_70125_A;
                } else if (pressingBack) {
                    yaw -= 180.0f;
                    pitch = p.field_70125_A * -1.0f;
                }
                motionX = -MathHelper.func_76126_a((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
                motionZ = MathHelper.func_76134_b((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
                motionY = -MathHelper.func_76126_a((float)(pitch / 180.0f * (float)Math.PI));
                DBCKiTech.setThrowableHeading((Entity)p, motionX, motionY, motionZ, s * (float)JRMCoreConfig.Flngspd, 0.0f);
                if (sec <= 0.0f || dodge_forwSwTm == 2) {
                    sec = 10.0f;
                    DBCKiTech.triForce(4, 0, dodge_forwSwTm == 2 ? 5 : 4);
                    DBCKiTech.chargePart(false);
                }
                if (dodge_forwSwTm == 2) {
                    KeyBinding.func_74510_a((int)JRMCoreKeyHandler.Fn.func_151463_i(), (boolean)false);
                }
            }
            if (dodge_forwDash_STE && !dodge_forwHold) {
                dodge_forwDash_STE = false;
                floating = true;
                dodge_forwSwTm = 0;
                KeyBinding.func_74510_a((int)JRMCoreKeyHandler.Fn.func_151463_i(), (boolean)false);
            }
        } else if (dodge_forwHold && dodge_forwDash_STE) {
            dodge_forwDash_STE = false;
            floating = true;
            dodge_forwSwTm = 0;
            KeyBinding.func_74510_a((int)JRMCoreKeyHandler.Fn.func_151463_i(), (boolean)false);
        }
        if (sec > 0.0f) {
            sec -= 1.0f;
        }
        if ((isSwooping = JRMCoreH.StusEfctsMe(7)) && !dodge_forwDash_STE && sent <= 0.0f) {
            sent = JRMCoreCliTicH.counterValue * 0.5f;
            JRMCoreH.Skll((byte)5, (byte)1, (byte)7);
        } else if (!isSwooping && dodge_forwDash_STE && sent <= 0.0f) {
            sent = JRMCoreCliTicH.counterValue * 0.5f;
            JRMCoreH.Skll((byte)5, (byte)0, (byte)7);
        } else if (sent > 0.0f && sent <= 1.0f && !isSwooping && !dodge_forwDash_STE) {
            sent = 0.0f;
        } else if (sent > 0.0f && sent <= 1.0f && isSwooping && dodge_forwDash_STE) {
            sent = 0.0f;
        }
        if (sent > 0.0f) {
            sent -= 1.0f;
        }
        int cost = (int)(1.0f + add);
        boolean able = true;
        boolean bl3 = b = n == 0;
        if (DBCConfig.flyAnyLvl) {
            b = false;
        }
        if (JRMCoreH.curEnergy < cost || b) {
            able = false;
        }
        if (DBCConfig.oldFly) {
            if (DBCKiTech.isPressed(kiFlight) || DBCKiTech.isPressed(JRMCoreKeyHandler.Fn) && DBCKiTech.isPressed(kiFlight) && able) {
                if (++floatTime >= 20) {
                    JRMCoreH.Cost(cost);
                    floatTime = 0;
                }
                DBCClient.mc.field_71439_g.field_70181_x = DBCKiTech.isPressed(JRMCoreKeyHandler.Fn) ? (DBCClient.mc.field_71439_g.field_70181_x /= 15.15) : (double)(0.6f * add * (float)JRMCoreConfig.Flngspd);
                float par1 = p.field_70702_br;
                float par2 = p.field_70701_bs;
                if (DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74351_w) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74368_y) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74370_x) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74366_z)) {
                    DBCKiTech.mv(par1, par2, (EntityPlayer)p, add);
                    floatMultAdded = true;
                } else {
                    p.field_70159_w = 0.0;
                    p.field_70179_y = 0.0;
                    floatMultAdded = false;
                }
            }
        } else {
            if (DBCKiTech.isPressed(kiFlight)) {
                KeyBinding.func_74510_a((int)kiFlight.func_151463_i(), (boolean)false);
                if (able) {
                    if (!floating) {
                        if (p.field_70122_E) {
                            DBCClient.mc.field_71439_g.field_70181_x = 0.5;
                            jumpToFly = true;
                        }
                    } else {
                        floatMultAdded = false;
                    }
                    boolean bl4 = floating = !floating;
                }
            }
            if (floating && able && !p.field_70122_E) {
                if (++floatTime >= 20) {
                    JRMCoreH.Cost(cost);
                    floatTime = 0;
                }
                if (DBCKiTech.isPressed(keyBindJump)) {
                    jumpRel = true;
                    DBCClient.mc.field_71439_g.field_70181_x = 0.25f * add * (float)JRMCoreConfig.Flngspd;
                } else if (DBCKiTech.isPressed(keyBindSneak)) {
                    if (DBCClient.mc.field_71439_g.field_70181_x > (double)(-(0.25f * add))) {
                        DBCClient.mc.field_71439_g.field_70181_x = -(0.25f * add) * (float)JRMCoreConfig.Flngspd;
                    }
                } else if (p.field_70181_x < 0.0) {
                    DBCClient.mc.field_71439_g.field_70181_x = DBCClient.mc.field_71439_g.field_70181_x / (JRMCoreH.isShtng || !JRMCoreConfig.PlayerFlyingDragDownOn ? 150.15 : 15.15);
                }
                float par1 = p.field_70702_br;
                float par2 = p.field_70701_bs;
                if (DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74351_w) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74368_y) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74370_x) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74366_z)) {
                    if (!(!JRMCoreH.StusEfctsMe(9) || JRMCoreH.StusEfctsMe(4) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74368_y) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74370_x) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74366_z))) {
                        float s = add;
                        float wei = JRMCoreH.weightPerc(1);
                        s *= wei;
                        boolean back = false;
                        boolean right = false;
                        boolean left = false;
                        double motionX = -MathHelper.func_76126_a((float)((p.field_70177_z + (float)back + (float)left + (float)right) / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(p.field_70125_A / 180.0f * (float)Math.PI));
                        double motionZ = MathHelper.func_76134_b((float)((p.field_70177_z + (float)back + (float)left + (float)right) / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(p.field_70125_A / 180.0f * (float)Math.PI));
                        double motionY = -MathHelper.func_76126_a((float)((p.field_70125_A + (float)back) / 180.0f * (float)Math.PI));
                        DBCKiTech.setThrowableHeading((Entity)p, motionX, motionY, motionZ, s * (float)JRMCoreConfig.Flngspd, 0.0f);
                    } else {
                        DBCKiTech.mv(par1, par2, (EntityPlayer)p, add * (float)JRMCoreConfig.Flngspd);
                    }
                    floatMultAdded = true;
                } else {
                    p.field_70159_w = 0.0;
                    p.field_70179_y = 0.0;
                    floatMultAdded = false;
                }
            }
            if (floating && p.field_70122_E) {
                if (jumpToFly) {
                    jumpToFly = false;
                } else {
                    floating = false;
                    floatMultAdded = false;
                }
            }
        }
    }

    public static void mv(float strafe, float frward, EntityPlayer var4, float add) {
        float f3 = strafe * strafe + frward * frward;
        if (f3 >= 1.0E-4f) {
            if ((f3 = MathHelper.func_76129_c((float)f3)) < 1.0f) {
                f3 = 1.0f;
            }
            f3 = add / f3;
            float f4 = MathHelper.func_76126_a((float)(var4.field_70177_z * (float)Math.PI / 180.0f));
            float f5 = MathHelper.func_76134_b((float)(var4.field_70177_z * (float)Math.PI / 180.0f));
            float pitch = MathHelper.func_76126_a((float)(var4.field_70125_A * (float)Math.PI / 180.0f));
            float speedY = 1.0f - (pitch < 0.0f ? -pitch : pitch);
            float speed = var4.field_70122_E ? 0.25f : 0.25f;
            var4.field_70159_w = ((strafe *= f3) * f5 - (frward *= f3) * f4) * speed;
            var4.field_70179_y = (frward * f5 + strafe * f4) * speed;
        }
    }

    public static void DashKi(boolean sprint) {
        EntityClientPlayerMP var4 = DBCClient.mc.field_71439_g;
        WorldClient var3 = DBCClient.mc.field_71441_e;
        int n = JRMCoreH.SklLvl(2, (byte)1);
        int st = JRMCoreH.StusEfctsMe(13) ? (JRMCoreH.rc_sai(JRMCoreH.Race) ? JRMCoreH.mstc_sai(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) - 1) : (JRMCoreH.rc_arc(JRMCoreH.Race) ? JRMCoreH.mstc_arc() : (JRMCoreH.rc_humNam(JRMCoreH.Race) ? JRMCoreH.mstc_humnam() : 1))) : JRMCoreH.State;
        float inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 7, 100, JRMCoreH.Race, JRMCoreH.Class, 0.0f) * 0.01f;
        float add = JRMCoreH.spdFrm(JRMCoreH.PlyrAttrbts(null)[1], n, JRMCoreH.curRelease, turbo, false, st, JRMCoreH.State2, inc);
        int cost = (int)(1.0f + add);
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts();
        byte pwr = JRMCoreH.Pwrtyp;
        byte rce = JRMCoreH.Race;
        byte cls = JRMCoreH.Class;
        int maxStam = JRMCoreH.stat((Entity)var4, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
        int ce = JRMCoreH.curStamina;
        int cst = (int)((float)maxStam * 0.2f - (float)n * 0.01f);
        boolean left = JRMCoreClient.mc.field_71474_y.field_74370_x.func_151468_f();
        boolean right = JRMCoreClient.mc.field_71474_y.field_74366_z.func_151468_f();
        boolean back = JRMCoreClient.mc.field_71474_y.field_74368_y.func_151468_f();
        boolean dodge = !JRMCoreH.PlyrSettingsB(2);
        long ctm = System.currentTimeMillis() / 1000L;
        if (dodge_per == 0 && dodge_recently != ctm && DBCKiTech.isPressed(JRMCoreKeyHandler.Fn) && !DBCKiTech.isPressed(JRMCoreClient.mc.field_71474_y.field_74313_G) && !DBCKiTech.isPressed(JRMCoreKeyHandler.KiCharge) && !DBCKiTech.isPressed(JRMCoreKeyHandler.KiAscend)) {
            dodge_per = left || right || back ? 1 : 0;
        } else if (dodge_recently == ctm) {
            dodge_per = 0;
        }
        if (dodge_per > 0 && dodge_recently != ctm && ce > cst && n > 0 && dodge && JRMCoreH.curRelease > 0) {
            double r;
            double e;
            int y;
            int n2 = y = JRMCoreClient.mc.field_71439_g.field_70122_E ? 2 : 1;
            if (left) {
                dodge_recently = ctm;
                e = Math.cos((double)(JRMCoreClient.mc.field_71439_g.field_70177_z - 90.0f) * Math.PI / 180.0) * 1.5 * (double)y;
                r = Math.sin((double)(JRMCoreClient.mc.field_71439_g.field_70177_z - 90.0f) * Math.PI / 180.0) * -1.5 * (double)y;
                JRMCoreClient.mc.field_71439_g.field_70179_y = e;
                JRMCoreClient.mc.field_71439_g.field_70159_w = r;
                DBCKiTech.triForce(4, 0, 0);
            }
            if (right) {
                dodge_recently = ctm;
                e = Math.cos((double)(JRMCoreClient.mc.field_71439_g.field_70177_z + 90.0f) * Math.PI / 180.0) * 1.5 * (double)y;
                r = Math.sin((double)(JRMCoreClient.mc.field_71439_g.field_70177_z + 90.0f) * Math.PI / 180.0) * -1.5 * (double)y;
                JRMCoreClient.mc.field_71439_g.field_70179_y = e;
                JRMCoreClient.mc.field_71439_g.field_70159_w = r;
                DBCKiTech.triForce(4, 0, 1);
            }
            if (back) {
                dodge_recently = ctm;
                e = Math.cos((double)JRMCoreClient.mc.field_71439_g.field_70177_z * Math.PI / 180.0) * -1.0 * (double)y;
                r = Math.sin((double)JRMCoreClient.mc.field_71439_g.field_70177_z * Math.PI / 180.0) * 1.0 * (double)y;
                JRMCoreClient.mc.field_71439_g.field_70179_y = e;
                JRMCoreClient.mc.field_71439_g.field_70159_w = r;
                DBCKiTech.triForce(4, 0, 3);
            }
            dodge_per = 0;
            KeyBinding.func_74510_a((int)JRMCoreKeyHandler.Fn.func_151463_i(), (boolean)false);
        }
        boolean able = true;
        if (JRMCoreH.curEnergy < cost || dodge_forwHold) {
            able = false;
        }
        if (able && sprint && !floating && JRMCoreH.curRelease > 0) {
            double par32;
            double par22;
            double par12;
            if (++dash >= 20) {
                JRMCoreH.Cost(cost);
                dash = 0;
            }
            if (var3.func_147439_a((int)(par12 = var4.field_70165_t), (int)(par22 = var4.field_70163_u) - 2, (int)(par32 = var4.field_70161_v)) != Blocks.field_150432_aD) {
                float par1 = var4.field_70702_br;
                float par2 = var4.field_70701_bs;
                if (DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74351_w) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74368_y) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74370_x) || DBCKiTech.isPressed(DBCClient.mc.field_71474_y.field_74366_z)) {
                    DBCKiTech.mv(par1, par2, (EntityPlayer)var4, add);
                    floatMultAdded = true;
                } else {
                    var4.field_70159_w = 0.0;
                    var4.field_70179_y = 0.0;
                    floatMultAdded = false;
                }
            }
        }
    }

    public static void setThrowableHeading(Entity e, double par1, double par3, double par5, float par7, float par8) {
        Random rand = e.field_70170_p.field_73012_v;
        float s = e.field_70122_E ? 0.25f : 0.25f;
        e.field_70159_w = (par1 *= (double)par7) * (double)s;
        e.field_70181_x = (par3 *= (double)par7) * (double)s;
        e.field_70179_y = (par5 *= (double)par7) * (double)s;
    }

    public static void TurboMode(KeyBinding key) {
        EntityClientPlayerMP var4 = DBCClient.mc.field_71439_g;
        if (DBCKiTech.isPressed(key)) {
            KeyBinding.func_74510_a((int)key.func_151463_i(), (boolean)false);
            if (!turbo) {
                turbo = true;
                JRMCoreH.Skll((byte)5, (byte)0, (byte)3);
                JRMCoreH.jrmct(3);
            } else if (turbo) {
                turbo = false;
                JRMCoreH.Skll((byte)5, (byte)1, (byte)3);
                JRMCoreH.jrmct(3);
            }
        }
        if (JRMCoreH.curEnergy <= 0) {
            turbo = false;
            if (++ton > 10) {
                JRMCoreH.Skll((byte)5, (byte)1, (byte)3);
                JRMCoreH.jrmct(3);
                ton = 0;
            }
        }
    }

    public static void EnAt(byte selc) {
        PD.sendToServer(new DBCPenergy(selc, 0));
    }

    public static void EnAt(byte selc, byte p) {
        PD.sendToServer(new DBCPenergy(selc, p));
    }

    public static void EnAtSlct(byte b) {
        byte selct = JRMCoreH.EnAtSlct;
        if ((JRMCoreH.EnAtSlct = (byte)(selct + (b == 1 ? -1 : 1))) > (JRMCoreH.mrAtts ? (byte)7 : 3)) {
            JRMCoreH.EnAtSlct = 0;
        } else if (JRMCoreH.EnAtSlct < 0) {
            JRMCoreH.EnAtSlct = (byte)(JRMCoreH.mrAtts ? 7 : 3);
        }
    }

    public static boolean KAkiEn(byte s, byte r, byte p) {
        String[] tech = JRMCoreH.tech(s);
        short cost = (short)(JRMCoreH.maxEnergy + 1);
        if (tech != null) {
            cost = s < 4 ? (short)((float)(JRMCoreH.costEnAt(tech) * r) * 0.02f * ((float)p * 0.02f)) : (short)((float)(Short.parseShort(tech[7]) * r) * 0.02f * ((float)p * 0.02f) * (float)(JRMCoreH.State + 1));
            if (JRMCoreH.curEnergy >= cost) {
                return JRMCoreH.curEnergy >= cost;
            }
            DBCClientTickHandler.nuller();
            KeyBinding cfr_ignored_0 = DBCClient.mc.field_71474_y.field_74313_G;
            KeyBinding.func_74510_a((int)DBCClient.mc.field_71474_y.field_74313_G.func_151463_i(), (boolean)false);
        }
        return false;
    }

    public static boolean isPressed(KeyBinding k) {
        return k.func_151470_d();
    }

    public static void Ascend(KeyBinding k) {
        boolean isTransformKeyPressed;
        boolean canUseQuickTransform;
        block138: {
            boolean ascended;
            block146: {
                block148: {
                    block160: {
                        int s;
                        int p;
                        int racialSkillLvl;
                        block161: {
                            block163: {
                                float re;
                                float en;
                                block162: {
                                    block156: {
                                        int s2;
                                        int p2;
                                        boolean isAbsorption;
                                        int racialSkillLvl2;
                                        block157: {
                                            block159: {
                                                float re2;
                                                float en2;
                                                block158: {
                                                    boolean canAttempt;
                                                    boolean isMajinAbsorptionModeOn;
                                                    int godSkillLevel;
                                                    boolean playerSettingsGodOn;
                                                    block152: {
                                                        int s3;
                                                        int p3;
                                                        boolean isOozaru;
                                                        int racialSkillLvl3;
                                                        boolean playerSettingsBlueOnInBlue;
                                                        boolean playerSettingsBlueOn;
                                                        boolean useOozaru;
                                                        block153: {
                                                            block155: {
                                                                float re3;
                                                                float en3;
                                                                block154: {
                                                                    boolean isBlue;
                                                                    block147: {
                                                                        int s4;
                                                                        int p4;
                                                                        int racialSkillLvl4;
                                                                        block149: {
                                                                            block151: {
                                                                                float re4;
                                                                                float en4;
                                                                                block150: {
                                                                                    boolean useKaioken;
                                                                                    block145: {
                                                                                        int s5;
                                                                                        int p5;
                                                                                        boolean useMajinAbsorption;
                                                                                        block144: {
                                                                                            boolean canTurnUI;
                                                                                            boolean isInBase;
                                                                                            boolean isInMystic;
                                                                                            boolean useUltraInstinct2;
                                                                                            boolean isInUltraInstinct;
                                                                                            block143: {
                                                                                                boolean canTurnGoD;
                                                                                                boolean isInUI;
                                                                                                boolean isInKaioken;
                                                                                                boolean useGodOfDestruction;
                                                                                                block139: {
                                                                                                    int s6;
                                                                                                    block140: {
                                                                                                        block142: {
                                                                                                            float re5;
                                                                                                            float en5;
                                                                                                            block141: {
                                                                                                                useOozaru = false;
                                                                                                                boolean rotPit = DBCClient.mc.field_71439_g.field_70125_A <= -90.0f;
                                                                                                                canUseQuickTransform = false;
                                                                                                                if (!DBCKiTech.isPressed(k)) {
                                                                                                                    wasTransformPressed = false;
                                                                                                                }
                                                                                                                if (rotPit) {
                                                                                                                    int z;
                                                                                                                    int x;
                                                                                                                    int n = 200;
                                                                                                                    EntityClientPlayerMP pl = DBCClient.mc.field_71439_g;
                                                                                                                    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(pl.field_70165_t - (double)n), (double)200.0, (double)(pl.field_70161_v - (double)n), (double)(pl.field_70165_t + (double)n), (double)255.0, (double)(pl.field_70161_v + (double)n));
                                                                                                                    List l = pl.field_70170_p.func_72872_a(EntityEnergyFM.class, aabb);
                                                                                                                    boolean pwbl = !l.isEmpty();
                                                                                                                    boolean night = JRMCoreH.StusEfctsMe(8) || pwbl;
                                                                                                                    boolean tm = JRMCoreH.tailHas(JRMCoreH.TlMd);
                                                                                                                    boolean sky = false;
                                                                                                                    for (int i = (int)pl.field_70163_u; i < 256 && (sky = DBCClient.mc.field_71441_e.func_147437_c((x = (int)pl.field_70165_t) < 0 ? x - 1 : x, i, (z = (int)pl.field_70161_v) < 0 ? z - 1 : z)); ++i) {
                                                                                                                    }
                                                                                                                    useOozaru = sky && tm && JRMCoreH.State == 0 && JRMCoreH.rSai() && night && rotPit;
                                                                                                                }
                                                                                                                isTransformKeyPressed = useOozaru ? true : DBCKiTech.isPressed(k);
                                                                                                                useKaioken = JRMCoreH.PlyrSettingsB(0) && JRMCoreH.SklLvl(8) > 0;
                                                                                                                isInUltraInstinct = JRMCoreH.StusEfctsMe(19) && JRMCoreH.SklLvl(16) < JRMCoreH.State2 && JRMCoreH.State2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS;
                                                                                                                useUltraInstinct2 = JRMCoreH.SklLvl(16) > JRMCoreH.State2 && JRMCoreH.State2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS;
                                                                                                                useGodOfDestruction = JRMCoreH.SklLvl(18) > 0;
                                                                                                                playerSettingsBlueOn = JRMCoreH.PlyrSettingsI(1, 2) && JRMCoreH.State == 0;
                                                                                                                playerSettingsBlueOnInBlue = JRMCoreH.PlyrSettingsI(1, 2) && (JRMCoreH.State == 10 || JRMCoreH.State == 9);
                                                                                                                playerSettingsGodOn = JRMCoreH.PlyrSettingsI(1, 1) && (JRMCoreH.State == 9 || JRMCoreH.State == 10);
                                                                                                                godSkillLevel = JRMCoreH.SklLvl(9);
                                                                                                                isInKaioken = JRMCoreH.StusEfctsMe(5);
                                                                                                                isInMystic = JRMCoreH.StusEfctsMe(13);
                                                                                                                boolean isInGoD = JRMCoreH.StusEfctsMe(20);
                                                                                                                isInUI = JRMCoreH.StusEfctsMe(19);
                                                                                                                isInBase = JRMCoreH.State == (JRMCoreH.isRaceArcosian() ? (byte)4 : 0);
                                                                                                                isMajinAbsorptionModeOn = JRMCoreH.StusEfctsMe(21);
                                                                                                                ascended = false;
                                                                                                                boolean bl = useMajinAbsorption = JGConfigRaces.CONFIG_MAJIN_ENABLED && JRMCoreH.isRaceMajin() && isMajinAbsorptionModeOn;
                                                                                                                if (isTransformKeyPressed) {
                                                                                                                    if (!holdingForTransform) {
                                                                                                                        if (holdingTransformTimer == null) {
                                                                                                                            holdingTransformTimer = Instant.now();
                                                                                                                        } else if (Duration.between(holdingTransformTimer, Instant.now()).toMillis() > 200L) {
                                                                                                                            holdingForTransform = true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    holdingTransformTimer = null;
                                                                                                                    holdingForTransform = false;
                                                                                                                    sentAbsorption = false;
                                                                                                                }
                                                                                                                if (!useMajinAbsorption && DBCKiTech.isPressed(k) && (isInMystic && (!DBCConfig.MysticKaiokenOn || !useKaioken) || isInGoD)) break block138;
                                                                                                                if (useMajinAbsorption || !DBCKiTech.isPressed(k) || !JRMCoreH.PlyrSettingsB(6) || JRMCoreH.StusEfctsMe(13)) break block139;
                                                                                                                if (!DBCConfig.MysticKaiokenOn && (JRMCoreH.StusEfctsMe(5) || JRMCoreH.State2 != 0)) {
                                                                                                                    return;
                                                                                                                }
                                                                                                                if (DBCConfig.IsInstantTransformEnabled[2]) {
                                                                                                                    canUseQuickTransform = true;
                                                                                                                }
                                                                                                                if (!holdingForTransform) break block138;
                                                                                                                int p6 = 12;
                                                                                                                if (++ptime >= p6) {
                                                                                                                    ptime = 0;
                                                                                                                    JRMCoreH.Cost(1);
                                                                                                                    int ic = 10;
                                                                                                                    ic = ic > 100 ? 100 : ic;
                                                                                                                    DBCKiTech.triForce(1, 0, ic);
                                                                                                                }
                                                                                                                if (JRMCoreH.curRelease >= 50 || ++time < 10) break block140;
                                                                                                                time = 0;
                                                                                                                en5 = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                                                                                                                re5 = JRMCoreH.curRelease;
                                                                                                                float f = en5 = en5 > 75.0f ? 75.0f : en5;
                                                                                                                if (!(re5 >= 50.0f)) break block141;
                                                                                                                if (!(re5 - 50.0f < (en5 - 50.0f) * 2.0f)) break block140;
                                                                                                                break block142;
                                                                                                            }
                                                                                                            float f = en5 <= 10.0f ? en5 * 5.0f : 50.0f;
                                                                                                            if (!(re5 < f)) break block140;
                                                                                                        }
                                                                                                        JRMCoreH.Rls((byte)1);
                                                                                                    }
                                                                                                    if (++pup >= 33 || ++pup2 == 1) {
                                                                                                        String ar = "jinryuudragonbc:DBC.aura";
                                                                                                        DBCKiTech.soundAsc(ar);
                                                                                                        pup = 0;
                                                                                                    }
                                                                                                    if (++ascend >= (s6 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                                                                        DBCKiTech.Ascndng();
                                                                                                        JRMCoreH.Rls((byte)1);
                                                                                                        DBCKiTech.soundAsc(4);
                                                                                                        ascend = 0;
                                                                                                        ptime = 0;
                                                                                                        partnorm = 0;
                                                                                                        pup = 0;
                                                                                                        pup2 = 0;
                                                                                                    }
                                                                                                    if (ascended = true) {
                                                                                                        if (!ascending) {
                                                                                                            ascending = true;
                                                                                                            JRMCoreH.Skll((byte)5, (byte)0, (byte)1);
                                                                                                        }
                                                                                                    } else if (ascending) {
                                                                                                        ascending = false;
                                                                                                        JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                                    }
                                                                                                    break block138;
                                                                                                }
                                                                                                if (useMajinAbsorption || !DBCKiTech.isPressed(k) || !JRMCoreH.PlyrSettingsB(16) || JRMCoreH.StusEfctsMe(20)) break block143;
                                                                                                if (DBCKiTech.isClientMoving()) {
                                                                                                    if (JRMCoreH.StusEfctsMe(1)) {
                                                                                                        ascending = false;
                                                                                                        JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                                    }
                                                                                                    return;
                                                                                                }
                                                                                                boolean bl = isInBase && useGodOfDestruction && !isInMystic && !isInUI && !isInKaioken && JRMCoreH.SklLvl(9) > (JRMCoreH.rSai(JRMCoreH.Race) ? 1 : 0) ? true : (canTurnGoD = false);
                                                                                                if (canTurnGoD) {
                                                                                                    if (DBCConfig.IsInstantTransformEnabled[3]) {
                                                                                                        canUseQuickTransform = true;
                                                                                                    }
                                                                                                    if (holdingForTransform) {
                                                                                                        int s7;
                                                                                                        int p7 = 10;
                                                                                                        if (++ptime >= p7) {
                                                                                                            ptime = 0;
                                                                                                            JRMCoreH.Cost(1);
                                                                                                            int ic = 10;
                                                                                                            ic = ic > 100 ? 100 : ic;
                                                                                                            DBCKiTech.triForce(1, 0, ic);
                                                                                                        }
                                                                                                        if (++ascend >= (s7 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                                                                            DBCKiTech.Ascndng();
                                                                                                            JRMCoreH.Rls((byte)1);
                                                                                                            DBCKiTech.soundAsc(4);
                                                                                                            ascend = 0;
                                                                                                            ptime = 0;
                                                                                                            partnorm = 0;
                                                                                                            pup = 0;
                                                                                                            pup2 = 0;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (holdingForTransform) {
                                                                                                    ascended = true;
                                                                                                    if (ascended) {
                                                                                                        if (!ascending) {
                                                                                                            ascending = true;
                                                                                                            JRMCoreH.Skll((byte)5, (byte)0, (byte)1);
                                                                                                        }
                                                                                                    } else if (ascending) {
                                                                                                        ascending = false;
                                                                                                        JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                                    }
                                                                                                }
                                                                                                break block138;
                                                                                            }
                                                                                            if (useMajinAbsorption || !DBCKiTech.isPressed(k) || !JRMCoreH.PlyrSettingsB(11) || isInUltraInstinct) break block144;
                                                                                            if (DBCKiTech.isClientMoving()) {
                                                                                                if (JRMCoreH.StusEfctsMe(1)) {
                                                                                                    ascending = false;
                                                                                                    JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                                }
                                                                                                return;
                                                                                            }
                                                                                            boolean bl = isInBase && !isInMystic && !JRMCoreH.StusEfctsMe(12) && !JRMCoreH.StusEfctsMe(5) && !JRMCoreH.pnh && !JRMCoreH.StusEfctsMe(18) && JRMCoreH.SklLvl(9) > (JRMCoreH.rSai(JRMCoreH.Race) ? 1 : 0) && useUltraInstinct2 ? true : (canTurnUI = false);
                                                                                            if (canTurnUI) {
                                                                                                if (DBCConfig.IsInstantTransformEnabled[1]) {
                                                                                                    canUseQuickTransform = true;
                                                                                                }
                                                                                                if (holdingForTransform) {
                                                                                                    int s8;
                                                                                                    int p8 = 10;
                                                                                                    if (++ptime >= p8) {
                                                                                                        ptime = 0;
                                                                                                        JRMCoreH.Cost(1);
                                                                                                        int ic = 10;
                                                                                                        ic = ic > 100 ? 100 : ic;
                                                                                                        DBCKiTech.triForce(1, 0, ic);
                                                                                                        DBCKiTech.triForce(5, 0, 0);
                                                                                                    }
                                                                                                    if (++ascend >= (s8 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                                                                        DBCKiTech.Ascndng();
                                                                                                        JRMCoreH.Rls((byte)1);
                                                                                                        DBCKiTech.soundAsc(4);
                                                                                                        ascend = 0;
                                                                                                        ptime = 0;
                                                                                                        partnorm = 0;
                                                                                                        pup = 0;
                                                                                                        pup2 = 0;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (holdingForTransform) {
                                                                                                ascended = true;
                                                                                                if (ascended) {
                                                                                                    if (!ascending) {
                                                                                                        ascending = true;
                                                                                                        JRMCoreH.Skll((byte)5, (byte)0, (byte)1);
                                                                                                    }
                                                                                                } else if (ascending) {
                                                                                                    ascending = false;
                                                                                                    JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                                }
                                                                                            }
                                                                                            break block138;
                                                                                        }
                                                                                        if (useMajinAbsorption || !DBCKiTech.isPressed(k) || !JRMCoreH.PlyrSettingsB(4) || JRMCoreH.StusEfctsMe(10) || JRMCoreH.StusEfctsMe(11)) break block145;
                                                                                        if (!wasTransformPressed) {
                                                                                            if (JRMCoreH.PlyrSettingsB(4)) {
                                                                                                String ar = "jinryuudragonbc:DBC.fusestrt";
                                                                                                DBCKiTech.soundAsc(ar);
                                                                                            }
                                                                                            wasTransformPressed = true;
                                                                                        }
                                                                                        if (++ptime >= (p5 = 12)) {
                                                                                            ptime = 0;
                                                                                            JRMCoreH.Cost(1);
                                                                                            int ic = 10;
                                                                                            ic = ic > 100 ? 100 : ic;
                                                                                            DBCKiTech.triForce(1, 0, ic);
                                                                                        }
                                                                                        if (++ascend >= (s5 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                                                            DBCKiTech.Ascndng();
                                                                                            JRMCoreH.Rls((byte)1);
                                                                                            ascend = 0;
                                                                                            ptime = 0;
                                                                                            partnorm = 0;
                                                                                            pup = 0;
                                                                                            pup2 = 0;
                                                                                        }
                                                                                        break block138;
                                                                                    }
                                                                                    if (useKaioken && !useOozaru) break block146;
                                                                                    if (!isTransformKeyPressed || !JRMCoreH.isRaceArcosian() || !DBCKiTech.canAttemptTransformation() || JRMCoreH.isInState(5) || JRMCoreH.isInState(6)) break block147;
                                                                                    if (!DBCKiTech.hasRacialSkillLevel()) break block148;
                                                                                    racialSkillLvl4 = DBCKiTech.getClientRacialSkillLevel();
                                                                                    if (JRMCoreH.State >= 4 && (racialSkillLvl4 < 3 || JRMCoreH.State != 4)) break block148;
                                                                                    if (DBCKiTech.isClientMoving()) {
                                                                                        if (JRMCoreH.StusEfctsMe(1)) {
                                                                                            ascending = false;
                                                                                            JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                                        }
                                                                                        return;
                                                                                    }
                                                                                    canUseQuickTransform = true;
                                                                                    if (!holdingForTransform) break block148;
                                                                                    ascended = true;
                                                                                    if (JRMCoreH.curRelease >= 50 || ++time < 10) break block149;
                                                                                    time = 0;
                                                                                    en4 = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                                                                                    re4 = JRMCoreH.curRelease;
                                                                                    float f = en4 = en4 > 75.0f ? 75.0f : en4;
                                                                                    if (!(re4 >= 50.0f)) break block150;
                                                                                    if (!(re4 - 50.0f < (en4 - 50.0f) * 2.0f)) break block149;
                                                                                    break block151;
                                                                                }
                                                                                float f = en4 <= 10.0f ? en4 * 5.0f : 50.0f;
                                                                                if (!(re4 < f)) break block149;
                                                                            }
                                                                            JRMCoreH.Rls((byte)1);
                                                                        }
                                                                        if (++pup >= 33 || ++pup2 == 1) {
                                                                            DBCKiTech.soundAsc(2);
                                                                            pup = 0;
                                                                        }
                                                                        int n = racialSkillLvl4 >= 3 ? 10 : (p4 = racialSkillLvl4 >= 2 ? 13 : 15);
                                                                        if (++ptime >= p4) {
                                                                            ptime = 0;
                                                                            JRMCoreH.Cost(1);
                                                                            int ic = (racialSkillLvl4 >= 3 ? 15 : (racialSkillLvl4 >= 2 ? 10 : 5)) * (JRMCoreH.State > 3 ? 1 : 4);
                                                                            ic = ic > 100 ? 100 : ic;
                                                                            DBCKiTech.triForce(1, 0, ic);
                                                                        }
                                                                        if (++ascend >= (s4 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                                            DBCKiTech.Ascndng();
                                                                            DBCKiTech.soundAsc(4);
                                                                            ascended = true;
                                                                            ascend = 0;
                                                                            ptime = 0;
                                                                            partnorm = 0;
                                                                            pup = 0;
                                                                            pup2 = 0;
                                                                            JRMCoreH.kiInSuper = racialSkillLvl4 >= 2 ? 2 : 1;
                                                                        }
                                                                        break block148;
                                                                    }
                                                                    if (!isTransformKeyPressed || !JRMCoreH.isRaceSaiyan() || !DBCKiTech.canAttemptTransformation() || JRMCoreH.isInState(3) || JRMCoreH.isInState(6) || JRMCoreH.isInState(13) || !(godSkillLevel > 2 ? !JRMCoreH.isInState(15) : (godSkillLevel > 1 ? !JRMCoreH.isInState(10) : !JRMCoreH.isInState(9)))) break block152;
                                                                    if (!DBCKiTech.hasRacialSkillLevel()) break block148;
                                                                    racialSkillLvl3 = DBCKiTech.getClientRacialSkillLevel();
                                                                    isOozaru = racialSkillLvl3 >= 1 && JRMCoreH.isInState(7);
                                                                    boolean isSuperOozaru = racialSkillLvl3 >= 6 && JRMCoreH.isInState(8);
                                                                    boolean bl = isBlue = JRMCoreH.isInState(10) && godSkillLevel > 2 && racialSkillLvl3 >= 6;
                                                                    if (!useOozaru && !isOozaru && !isSuperOozaru && !isBlue && (racialSkillLvl3 < 1 || JRMCoreH.State >= racialSkillLvl3 && (1 >= racialSkillLvl3 || !playerSettingsGodOn) && (1 >= racialSkillLvl3 || !playerSettingsBlueOnInBlue) && (!JRMCoreH.isInState(4) || 1 >= racialSkillLvl3))) break block148;
                                                                    if (DBCKiTech.isClientMoving()) {
                                                                        if (JRMCoreH.StusEfctsMe(1)) {
                                                                            ascending = false;
                                                                            JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                                        }
                                                                        return;
                                                                    }
                                                                    canUseQuickTransform = true;
                                                                    if (!holdingForTransform) break block148;
                                                                    ascended = true;
                                                                    if (JRMCoreH.curRelease >= 50 || ++time < 10) break block153;
                                                                    time = 0;
                                                                    en3 = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                                                                    re3 = JRMCoreH.curRelease;
                                                                    float f = en3 = en3 > 75.0f ? 75.0f : en3;
                                                                    if (!(re3 >= 50.0f)) break block154;
                                                                    if (!(re3 - 50.0f < (en3 - 50.0f) * 2.0f)) break block153;
                                                                    break block155;
                                                                }
                                                                float f = en3 <= 10.0f ? en3 * 5.0f : 50.0f;
                                                                if (!(re3 < f)) break block153;
                                                            }
                                                            JRMCoreH.Rls((byte)1);
                                                        }
                                                        if (++pup >= 33 || ++pup2 == 1) {
                                                            String ar = "jinryuudragonbc:DBC.aura";
                                                            if (!useOozaru && JRMCoreH.StusEfctsMe(14)) {
                                                                ar = "jinryuudragonbc:DBC.aura";
                                                            } else if (!useOozaru && (playerSettingsGodOn || playerSettingsBlueOn || playerSettingsBlueOnInBlue)) {
                                                                ar = "jinryuudragonbc:1610.aurab";
                                                            } else if (useOozaru) {
                                                                ar = "jinryuudragonbc:1610.oozt";
                                                            }
                                                            DBCKiTech.soundAsc(ar);
                                                            pup = 0;
                                                        }
                                                        int n = useOozaru ? 15 : (racialSkillLvl3 >= 3 ? 5 : (p3 = racialSkillLvl3 >= 2 ? 7 : 10));
                                                        if (++ptime >= p3) {
                                                            ptime = 0;
                                                            JRMCoreH.Cost(1);
                                                            int ic = useOozaru ? 15 : (isOozaru ? 5 : (racialSkillLvl3 >= 4 ? 15 : (racialSkillLvl3 >= 2 ? 10 : 5)));
                                                            ic = ic > 100 ? 100 : ic;
                                                            DBCKiTech.triForce(1, 0, ic);
                                                        }
                                                        if (++ascend >= (s3 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                                            DBCKiTech.Ascndng();
                                                            String ar = "jinryuudragonbc:DBC.ascend";
                                                            ar = useOozaru ? "jinryuudragonbc:DBC3.force" : "jinryuudragonbc:1610.sss";
                                                            DBCKiTech.soundAsc(ar);
                                                            ascended = true;
                                                            ascend = 0;
                                                            ptime = 0;
                                                            partnorm = 0;
                                                            pup = 0;
                                                            pup2 = 0;
                                                            JRMCoreH.kiInSuper = racialSkillLvl3 >= 2 ? 2 : 1;
                                                        }
                                                        break block148;
                                                    }
                                                    if (!isTransformKeyPressed || !JRMCoreH.isRaceMajin() || !DBCKiTech.canAttemptTransformation() || JRMCoreH.State >= (godSkillLevel > 0 ? (byte)4 : 3) && !isMajinAbsorptionModeOn) break block156;
                                                    if (!JGConfigRaces.CONFIG_MAJIN_ENABLED || !DBCKiTech.hasRacialSkillLevel()) break block148;
                                                    racialSkillLvl2 = DBCKiTech.getClientRacialSkillLevel();
                                                    isAbsorption = JGConfigRaces.CONFIG_MAJIN_ABSORPTION_ENABLED && racialSkillLvl2 >= 4 && isMajinAbsorptionModeOn;
                                                    boolean isPureSelected = JRMCoreH.PlyrSettingsI(1, 0);
                                                    boolean bl = isMajinAbsorptionModeOn ? isAbsorption && !sentAbsorption : (JRMCoreH.isInState(0) ? (playerSettingsGodOn ? godSkillLevel > 0 && JRMCoreHDBC.hasMajinGodRacialRequirement(racialSkillLvl2) : racialSkillLvl2 >= (isPureSelected ? 5 : 2)) : (isPureSelected ? false : (canAttempt = racialSkillLvl2 >= 3 && !JRMCoreH.isInState(2))));
                                                    if (!canAttempt) break block148;
                                                    boolean bl2 = canUseQuickTransform = !isAbsorption;
                                                    if (DBCKiTech.isClientMoving()) {
                                                        if (JRMCoreH.StusEfctsMe(1)) {
                                                            ascending = false;
                                                            JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                                        }
                                                        return;
                                                    }
                                                    if (!holdingForTransform) break block148;
                                                    if (!isAbsorption) {
                                                        ascended = true;
                                                    }
                                                    if (++time < 7) break block157;
                                                    time = 0;
                                                    en2 = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                                                    re2 = JRMCoreH.curRelease;
                                                    float f = en2 = en2 > 75.0f ? 75.0f : en2;
                                                    if (isAbsorption) break block157;
                                                    if (!(re2 >= 50.0f)) break block158;
                                                    if (!(re2 - 50.0f < (en2 - 50.0f) * 2.0f)) break block157;
                                                    break block159;
                                                }
                                                float f = en2 <= 10.0f ? en2 * 5.0f : 50.0f;
                                                if (!(re2 < f)) break block157;
                                            }
                                            JRMCoreH.Rls((byte)1);
                                        }
                                        if (++pup >= 33 || ++pup2 == 1) {
                                            if (!isAbsorption) {
                                                DBCKiTech.soundAsc(2);
                                            }
                                            pup = 0;
                                        }
                                        if (++ptime >= (p2 = (racialSkillLvl2 >= 3 ? 5 : (racialSkillLvl2 >= 2 ? 7 : 10)) * (isAbsorption ? 3 : 1))) {
                                            ptime = 0;
                                            if (!isAbsorption) {
                                                JRMCoreH.Cost(1);
                                            }
                                            int ic = (racialSkillLvl2 >= 3 ? 15 : (racialSkillLvl2 >= 2 ? 10 : 5)) * 2;
                                            ic = ic > 100 ? 100 : ic;
                                            DBCKiTech.triForce(1, 0, ic);
                                        }
                                        if (++ascend >= (s2 = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                                            if (isAbsorption) {
                                                DBCKiTech.EnAt(DBCPacketHandlerServer.MAJIN_ABSORPTION, (byte)0);
                                                sentAbsorption = true;
                                            } else {
                                                DBCKiTech.Ascndng();
                                                DBCKiTech.soundAsc(4);
                                            }
                                            ascended = true;
                                            ascend = 0;
                                            ptime = 0;
                                            partnorm = 0;
                                            pup = 0;
                                            pup2 = 0;
                                        }
                                        break block148;
                                    }
                                    if (!isTransformKeyPressed || !JRMCoreH.isRaceHumanOrNamekian() || !DBCKiTech.canAttemptTransformation() || !JRMCoreH.isInState(0)) break block160;
                                    if (!DBCKiTech.hasRacialSkillLevel() || (racialSkillLvl = DBCKiTech.getClientRacialSkillLevel()) == 0 || racialSkillLvl < 1) break block148;
                                    canUseQuickTransform = true;
                                    if (DBCKiTech.isClientMoving()) {
                                        if (JRMCoreH.StusEfctsMe(1)) {
                                            ascending = false;
                                            JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                                        }
                                        return;
                                    }
                                    if (!holdingForTransform) break block148;
                                    ascended = true;
                                    if (++time < 7) break block161;
                                    time = 0;
                                    en = 100.0f / (float)JRMCoreH.maxEnergy * (float)JRMCoreH.curEnergy;
                                    re = JRMCoreH.curRelease;
                                    float f = en = en > 75.0f ? 75.0f : en;
                                    if (!(re >= 50.0f)) break block162;
                                    if (!(re - 50.0f < (en - 50.0f) * 2.0f)) break block161;
                                    break block163;
                                }
                                float f = en <= 10.0f ? en * 5.0f : 50.0f;
                                if (!(re < f)) break block161;
                            }
                            JRMCoreH.Rls((byte)1);
                        }
                        if (++pup >= 33 || ++pup2 == 1) {
                            DBCKiTech.soundAsc(2);
                            pup = 0;
                        }
                        int n = racialSkillLvl >= 3 ? 5 : (p = racialSkillLvl >= 2 ? 7 : 10);
                        if (++ptime >= p) {
                            ptime = 0;
                            JRMCoreH.Cost(1);
                            int ic = (racialSkillLvl >= 3 ? 15 : (racialSkillLvl >= 2 ? 10 : 5)) * 2;
                            ic = ic > 100 ? 100 : ic;
                            DBCKiTech.triForce(1, 0, ic);
                        }
                        if (++ascend >= (s = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                            DBCKiTech.Ascndng();
                            DBCKiTech.soundAsc(4);
                            ascended = true;
                            ascend = 0;
                            ptime = 0;
                            partnorm = 0;
                            pup = 0;
                            pup2 = 0;
                        }
                        break block148;
                    }
                    if (JRMCoreH.TransSaiCurRg > 0 && ++ptime >= 40) {
                        DBCKiTech.triForce(1, 1, 100);
                        ptime = 0;
                    }
                }
                if (ascended) {
                    if (!ascending) {
                        ascending = true;
                        JRMCoreH.Skll((byte)5, (byte)0, (byte)1);
                    }
                } else if (ascending) {
                    ascending = false;
                    JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
                } else if (ascendingK) {
                    ascendingK = false;
                    JRMCoreH.Skll((byte)5, (byte)1, (byte)5);
                }
                break block138;
            }
            boolean bl = ascended = isTransformKeyPressed && (float)JRMCoreH.curBody > (float)JRMCoreH.maxBody * 0.05f && JRMCoreH.SklLvl(8) > JRMCoreH.State2 && JRMCoreH.State2 < JRMCoreH.TransKaiDmg.length - 1;
            if (ascended) {
                if (!ascendingK) {
                    ascendingK = true;
                    JRMCoreH.Skll((byte)5, (byte)0, (byte)5);
                }
            } else if (ascendingK) {
                ascendingK = false;
                JRMCoreH.Skll((byte)5, (byte)1, (byte)5);
            } else if (ascending) {
                ascending = false;
                JRMCoreH.Skll((byte)5, (byte)1, (byte)1);
            }
            if (ascended) {
                if (DBCConfig.IsInstantTransformEnabled[0]) {
                    canUseQuickTransform = true;
                }
                if (holdingForTransform) {
                    if (++pup >= 33 || ++pup2 == 1) {
                        DBCKiTech.soundAsc(2);
                        pup = 0;
                    }
                    int s = 10;
                    if (++ascend >= 20 + (10 - JRMCoreH.SklLvl(8)) * 10) {
                        DBCKiTech.Ascndng();
                        boolean kk = JRMCoreH.State2 > 0 && JRMCoreH.StusEfctsMe(5);
                        String ar = "jinryuudragonbc:DBC3.force";
                        if (kk && JRMCoreH.rc_sai(JRMCoreH.Race) && JRMCoreH.State == 10) {
                            ar = "jinryuudragonbc:1610.aurabks";
                        }
                        DBCKiTech.soundAsc(ar);
                        ascend = 0;
                        ptime = 0;
                        partnorm = 0;
                        pup = 0;
                        pup2 = 0;
                    }
                }
            } else if (!isTransformKeyPressed) {
                ascend = 0;
            }
        }
        if (JRMCoreH.State2 > 0 && !ascendingK) {
            ascendingK = true;
            if (!JRMCoreH.StusEfctsMe(19)) {
                JRMCoreH.Skll((byte)5, (byte)0, (byte)5);
            }
        }
        if (DBCConfig.InstantTransformOn) {
            if (isTransformKeyPressed) {
                if (canUseQuickTransform && !quickTransformAdded) {
                    quickTransformAdded = true;
                    if (quickTransformTimer == null) {
                        quickTransformTimer = Instant.now();
                    } else if (Duration.between(quickTransformTimer, Instant.now()).toMillis() < 700L) {
                        quickTransformTimer = Instant.now();
                        if (++quickTransformCount >= 1) {
                            quickTransformTimer = null;
                            quickTransformCount = 0;
                            DBCKiTech.quickTransform();
                        }
                    }
                }
            } else {
                quickTransformAdded = false;
                if ((quickTransformTimer != null || quickTransformCount > 0) && Duration.between(quickTransformTimer, Instant.now()).toMillis() > 1000L) {
                    quickTransformTimer = null;
                    quickTransformCount = 0;
                }
            }
        }
    }

    private static void quickTransform() {
        if (DBCConfig.InstantTransformOn) {
            ascend = 0;
            ptime = 0;
            partnorm = 0;
            pup = 0;
            pup2 = 0;
            String ar = "jinryuudragonbc:DBC3.force";
            DBCKiTech.soundAsc(ar);
            int dbcascend = -1;
            PD.sendToServer(new DBCPascend(-1));
        }
    }

    public static void ssj2() {
        JRMCoreH.kiInSuper = 2;
        if (++pup == 50) {
            pup = 0;
        }
    }

    public static void Descend(KeyBinding key) {
        boolean isDescendingKeyPressed = DBCKiTech.isPressed(key);
        boolean fullDescending = false;
        boolean singleDescend = false;
        if (isDescendingKeyPressed && DBCConfig.SingleFormDescendOn) {
            if (!singleDescendAdded) {
                singleDescendAdded = true;
                if (singleDescendTimer == null) {
                    singleDescendTimer = Instant.now();
                } else if (Duration.between(singleDescendTimer, Instant.now()).toMillis() < 700L) {
                    singleDescendTimer = Instant.now();
                    if (++singleDescendCount >= 1) {
                        singleDescendTimer = null;
                        singleDescendCount = 0;
                        singleDescend = true;
                    }
                }
            }
            if (singleDescendTimer != null && !singleDescend && Duration.between(singleDescendTimer, Instant.now()).toMillis() > 1000L) {
                fullDescending = true;
                singleDescendCount = 0;
                singleDescendAdded = true;
                singleDescendTimer = null;
            }
        } else {
            singleDescendAdded = false;
            if ((singleDescendTimer != null || singleDescendCount > 0) && Duration.between(singleDescendTimer, Instant.now()).toMillis() > 1000L) {
                singleDescendTimer = null;
                singleDescendCount = 0;
            }
        }
        if (isDescendingKeyPressed && (!DBCConfig.SingleFormDescendOn || (fullDescending || singleDescend) && singleDescendAdded)) {
            int racialSkillLevel;
            singleDescendTimer = null;
            singleDescendCount = 0;
            singleDescendAdded = false;
            if (!JRMCoreH.StusEfctsMe(13) && !JRMCoreH.StusEfctsMe(20) && !JRMCoreH.StusEfctsMe(19) && (JRMCoreH.State2 == 0 && JRMCoreH.State == 0 || JRMCoreH.State2 == 0 && JRMCoreH.Race == 4 && JRMCoreH.State <= 4)) {
                JRMCoreH.Rls((byte)0);
            }
            JRMCoreH.kiInSuper = 0;
            ascend = 0;
            ptime = 0;
            partnorm = 0;
            pup = 0;
            if (JRMCoreH.State2 > 0 || JRMCoreH.State == 7) {
                DBCKiTech.soundAsc(3);
                DBCKiTech.Dscndng(singleDescend ? -1 : 1);
            } else if (JRMCoreH.StusEfctsMe(13) || JRMCoreH.StusEfctsMe(20) || JRMCoreH.StusEfctsMe(19)) {
                String ar = "jinryuudragonbc:DBC.descend";
                DBCKiTech.soundAsc(ar);
                DBCKiTech.Dscndng(3);
            } else if ((JRMCoreH.Race != 4 || JRMCoreH.Race == 4 && JRMCoreH.State > 4) && JRMCoreH.PlyrSkillX != null && DBCKiTech.hasRacialSkillLevel() && (racialSkillLevel = DBCKiTech.getClientRacialSkillLevel()) != 0) {
                String ar = "jinryuudragonbc:DBC.descend";
                if (JRMCoreH.rc_sai(JRMCoreH.Race)) {
                    ar = "jinryuudragonbc:1610.sse";
                }
                DBCKiTech.soundAsc(ar);
                DBCKiTech.Dscndng(singleDescend ? -1 : 1);
            }
        }
    }

    public static int Vqfj3D(String l) {
        String w = "0123456789ABCDEF";
        l = l.toUpperCase();
        int a = 0;
        for (int i = 0; i < l.length(); ++i) {
            char c = l.charAt(i);
            int d = w.indexOf(c);
            a = 16 * a + d;
        }
        return a;
    }

    public static int gYZc2f() {
        int b = JRMCoreConfig.tmx;
        String r = "64";
        String k = "3B9ACA00";
        return b > DBCKiTech.Vqfj3D(k) ? DBCKiTech.Vqfj3D(k) : (b < DBCKiTech.Vqfj3D(r) ? 0 : b);
    }

    public static void triForce(int i, int j, int k) {
        PD.sendToServer(new JRMCorePTri((byte)i, (byte)j, (byte)k));
    }

    private static void Ascndng() {
        boolean dbcascend = true;
        PD.sendToServer(new DBCPascend(1));
    }

    public static void Dscndng(int i) {
        PD.sendToServer(new DBCPdescend((byte)i));
    }

    public static void Dscndng() {
        boolean dbcascend = true;
        DBCKiTech.Dscndng(1);
    }

    public static void soundAsc(String s) {
        int e = DBCClient.mc.field_71439_g.func_145782_y();
        PD.sendToServer(new DBCPchargesound(e, s));
    }

    public static void soundAsc(int i) {
        Object d;
        int dbcascendsound = DBCClient.mc.field_71439_g.func_145782_y();
        switch (i) {
            case 1: {
                d = new DBCPascendsound(dbcascendsound);
                break;
            }
            case 2: {
                d = new DBCPchargesound(dbcascendsound, "jinryuudragonbc:DBC.aura");
                break;
            }
            case 3: {
                d = new DBCPdescendsound(dbcascendsound);
                break;
            }
            case 4: {
                d = new DBCPduo(300, dbcascendsound);
                break;
            }
            default: {
                d = new DBCPdescendsound(dbcascendsound);
            }
        }
        PD.sendToServer(d);
    }

    public static void chargePart(boolean b) {
        if (JRMCoreClient.mc.func_147113_T()) {
            return;
        }
        World w = DBCClient.mc.field_71439_g.field_70170_p;
        for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
            EntityPlayer e;
            if (!JRMCoreH.dnn(19) || !JRMCoreH.dnn(2) || !JRMCoreH.dnn(1) || !JRMCoreH.dnn(5)) continue;
            String s = JRMCoreH.StusEfctsClient(pl);
            String[] st = JRMCoreH.data2[pl].split(";");
            int k = Integer.parseInt(st[1]);
            if (!s.contains(JRMCoreH.StusEfcts[4]) && !s.contains(JRMCoreH.StusEfcts[1]) && !s.contains(JRMCoreH.StusEfcts[3]) && !s.contains(JRMCoreH.StusEfcts[5]) && !s.contains(JRMCoreH.StusEfcts[7]) || !((e = w.func_72924_a(JRMCoreH.plyrs[pl])) instanceof EntityPlayer)) continue;
            String[] a = JRMCoreH.data1[pl].split(";");
            int r = Integer.parseInt(a[0]);
            String[] dat5 = JRMCoreH.data5[pl].split(";");
            int al = Integer.parseInt(dat5[0]);
            int kc = Integer.parseInt(dat5[1]);
            DBCKiTech.chargePart(e, r, al, kc, Integer.parseInt(st[0]), k, b, s);
        }
    }

    public static void chargePart(EntityPlayer p, int r, int a, int c, int s, int k, boolean b, String se) {
        int dbcchargepart = 0;
        boolean kk = k > 0 || JRMCoreH.StusEfcts(5, se);
        boolean l = JRMCoreH.rc_sai(r) && JRMCoreH.StusEfcts(14, se);
        boolean ma = JRMCoreH.StusEfcts(12, se);
        boolean ui = JRMCoreH.StusEfcts(19, se);
        boolean gd = JRMCoreH.StusEfcts(20, se);
        boolean auraOn = JRMCoreH.StusEfcts(4, se);
        boolean auraTransformOn = JRMCoreH.StusEfcts(1, se);
        boolean turbo = JRMCoreH.StusEfcts(3, se);
        boolean swoop = JRMCoreH.StusEfcts(7, se);
        if (!b) {
            partnorm = 0;
            dbcchargepart = kk && JRMCoreH.rc_sai(r) && (s == 10 || s == 15) ? 9 : (kk ? 4 : (s > 0 && JRMCoreH.rc_sai(r) && s != 7 || JRMCoreHDBC.godKiUser(r, s) ? 2 : 1));
        } else {
            String ar = "jinryuudragonbc:DBC.aura";
            if (JRMCoreH.rc_sai(r) && (s == 10 || s == 15)) {
                ar = "jinryuudragonbc:1610.aurab";
            }
            if (JRMCoreH.rc_sai(r) && s != 10 && s != 15 && JRMCoreHDBC.godKiUser(r, s)) {
                ar = "jinryuudragonbc:1610.aurag";
            }
            if (kk && JRMCoreH.rc_sai(r) && (s == 10 || s == 15)) {
                ar = "jinryuudragonbc:1610.aurabk";
            }
            if (gd) {
                ar = "jinryuudragonbc:DBC5.aura_destroyer";
            }
            if (ui) {
                ar = "jinryuudragonbc:DBC5.aura_ui";
            }
            if (JRMCoreH.isRaceMajin(r) && !swoop) {
                if (!turbo && (auraOn || auraTransformOn)) {
                    ar = "jinryuudragonbc:DBC5.majin_cattle";
                } else if (turbo && auraOn && !auraTransformOn) {
                    DBCKiTech.soundAsc("jinryuudragonbc:DBC5.majin_cattle");
                }
            }
            DBCKiTech.soundAsc(ar);
            if (!JRMCoreH.StusEfcts(3, se) && !JRMCoreH.StusEfcts(7, se)) {
                dbcchargepart = k > 0 ? 8 : (s <= 0 || !JRMCoreH.rc_sai(r) || s != 7 || JRMCoreHDBC.godKiUser(r, s) ? 6 : 5);
            }
            power = 0;
        }
        if (dbcchargepart > 0) {
            boolean plyrSP;
            String dbcCharger = p.func_70005_c_();
            double dbcChargerY = p.field_70163_u;
            EntityPlayer other = p.field_70170_p.func_72924_a(dbcCharger);
            Random rand = new Random();
            Entity aura = null;
            EntityAura2 aura2 = null;
            float state = 0.0f;
            float state2 = 0.0f;
            int cr = 0;
            if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreH.dnn(2) && JRMCoreH.dnn(10)) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                    if (!JRMCoreH.plyrs[pl].equals(dbcCharger)) continue;
                    String[] states = JRMCoreH.data2[pl].split(";");
                    state = Integer.parseInt(states[0]);
                    state2 = Integer.parseInt(states[1]);
                    cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
                }
            }
            c = c > 0 ? c : JRMCoreH.Algnmnt_rc(a);
            boolean w = JRMCoreH.StusEfcts(7, se) || JRMCoreH.StusEfcts(9, se) && JRMCoreH.data(dbcCharger, 3, "0").contains("1") && !JRMCoreH.StusEfctsMe(4);
            boolean ssg = JRMCoreHDBC.godKiUserBase(r, s);
            boolean ssb = JRMCoreH.rc_sai(r) && s == 10;
            boolean ssbs = JRMCoreH.rc_sai(r) && s == 15;
            boolean auf = JRMCoreH.rc_arc(r) && s == 6;
            boolean v = JRMCoreH.StusEfcts(17, se);
            boolean lsa = JRMCoreH.lgndb(se, r, s);
            int sacol = JRMCoreHDBC.getPlayerColor2(2, c, 1, r, s, v, lsa, ui, gd);
            state = ssg || ssb ? 0.0f : state;
            state = ssbs ? 3.0f : state;
            boolean oozar = JRMCoreH.rc_sai(r) && (state == 7.0f || state == 8.0f);
            state = oozar ? state * 3.0f : state;
            state = JRMCoreH.rc_nam(r) && state == 2.0f ? 22.0f : state;
            boolean bl = plyrSP = DBCClient.mc.field_71439_g.func_70005_c_().equals(dbcCharger) && DBCClient.mc.field_71474_y.field_74320_O == 0;
            if (dbcchargepart >= 1 && dbcchargepart <= 4) {
                aura = new EntityAura2(p.field_70170_p, dbcCharger, state2 > 0.0f ? 0xFE0000 : sacol, state, state2, cr, w);
            }
            if (dbcchargepart >= 5 && dbcchargepart <= 8 && !ui && !gd) {
                aura = new EntityAuraRing(p.field_70170_p, dbcCharger, state2 > 0.0f ? 0xFE0000 : sacol, state, state2, cr);
            }
            if (dbcchargepart == 9) {
                aura = new EntityAura2(p.field_70170_p, dbcCharger, sacol, 0.0f, 0.0f, cr, w);
                if (state2 > 0.0f) {
                    aura2 = new EntityAura2(p.field_70170_p, dbcCharger, 0xFE0000, 2.0f + state, state2 * 1.5f, cr, w);
                }
            }
            if (aura != null && other != null) {
                if (aura instanceof EntityAura2) {
                    if (ssg) {
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.2f);
                        aura.setTex("aurai");
                        ((EntityAura2)aura).setTexL2("aurai2");
                        ((EntityAura2)aura).setColL2(16747301);
                    } else if (ssbs && v) {
                        aura.setSpd(30);
                        aura.setAlp(plyrSP ? 0.05f : 0.2f);
                        ((EntityAura2)aura).setTex("aurai");
                        ((EntityAura2)aura).setTexL2("aurai2");
                        ((EntityAura2)aura).setColL2(8592109);
                    } else if (ssbs) {
                        aura.setSpd(40);
                        aura.setAlp(plyrSP ? 0.05f : 0.5f);
                        ((EntityAura2)aura).setTex("aurag");
                        ((EntityAura2)aura).setColL3(12310271);
                        ((EntityAura2)aura).setTexL3("auragb");
                    } else if (ssb && v) {
                        aura.setSpd(30);
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.2f);
                        ((EntityAura2)aura).setTex("aurai");
                        ((EntityAura2)aura).setTexL2("aurai2");
                        ((EntityAura2)aura).setColL2(7872713);
                    } else if (ssb) {
                        aura.setSpd(40);
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.5f);
                        ((EntityAura2)aura).setTex("aurag");
                        ((EntityAura2)aura).setColL3(0xEFFAFA);
                        ((EntityAura2)aura).setTexL3("auragb");
                    } else if (auf) {
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.5f);
                        ((EntityAura2)aura).setTex("aurau");
                        ((EntityAura2)aura).setTexL2("aurau2");
                        ((EntityAura2)aura).setColL2(16776724);
                    } else if (ui) {
                        ((EntityAura2)aura).setSpd(100);
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.005f : 0.15f);
                        ((EntityAura2)aura).setTex("auras");
                        ((EntityAura2)aura).setCol(0xF0F0F0);
                        ((EntityAura2)aura).setColL3(4746495);
                        ((EntityAura2)aura).setTexL3("auragb");
                    } else if (gd) {
                        ((EntityAura2)aura).setSpd(30);
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.2f);
                        ((EntityAura2)aura).setTex("aurag");
                        ((EntityAura2)aura).setTexL3("auragb");
                        ((EntityAura2)aura).setColL2(12464847);
                    } else {
                        ((EntityAura2)aura).setAlp(plyrSP ? 0.05f : 0.2f);
                    }
                }
                if (aura != null && aura instanceof EntityAura2) {
                    int bol6;
                    aura.setBol(JRMCoreH.StusEfcts(1, se));
                    ((EntityAura2)aura).setBol2(JRMCoreH.StusEfcts(4, se));
                    ((EntityAura2)aura).setBol3(JRMCoreH.StusEfcts(3, se));
                    ((EntityAura2)aura).setBol4(ui && !gd);
                    boolean Bol4a = JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0 ? JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[JRMCoreH.state2UltraInstinct(!ui, (byte)state2)] : false;
                    ((EntityAura2)aura).setBol4a(Bol4a);
                    int n = gd ? 6 : (ssg ? 0 : (v && ssb ? 3 : (v && ssbs ? 5 : (ssb ? 1 : (ssbs ? 2 : (bol6 = auf ? 4 : -1))))));
                    if (ui) {
                        bol6 = 0;
                    }
                    ((EntityAura2)aura).setBol6(bol6);
                    ((EntityAura2)aura).setBol7(ma || kk);
                    ((EntityAura2)aura).kettleMode = (byte)(JRMCoreH.isRaceMajin(r) && (auraOn || auraTransformOn) && !swoop ? (turbo || auraTransformOn || kk ? 2 : 1) : 0);
                }
                p.field_70170_p.func_72838_d(aura);
            }
            if (aura2 != null && other != null) {
                if (aura2 instanceof EntityAura2) {
                    ((EntityAura2)aura2).setSpd(40);
                    ((EntityAura2)aura2).setAlp(plyrSP ? 0.025f : 0.3f);
                    ((EntityAura2)aura2).setTex("aurak");
                    ((EntityAura2)aura2).setInner(false);
                    ((EntityAura2)aura2).setRendPass(0);
                }
                aura2.func_70012_b(other.field_70165_t - 0.0, other.field_70163_u - 2.0 + (double)rand.nextInt(5) * 0.03, other.field_70161_v - 0.0, rand.nextFloat(), 0.0f);
                p.field_70170_p.func_72838_d((Entity)aura2);
            }
        }
    }

    private static int getClientRacialSkillLevel() {
        return Integer.parseInt(JRMCoreH.PlyrSkillX.replaceAll("TR", ""));
    }

    private static boolean canAttemptTransformation() {
        return JRMCoreH.PlyrSkillX != null && JRMCoreH.curEnergy > 1;
    }

    private static boolean hasRacialSkillLevel() {
        return JRMCoreH.PlyrSkillX.contains("TR");
    }

    private static boolean isClientMoving() {
        if (holdingForTransform) {
            return DBCConfig.MoveWhileTransforming ? false : !JGMathHelper.isMotionSmallerThanN((Entity)DBCClient.mc.field_71439_g, 0.05, true, false, true, true);
        }
        return DBCConfig.MoveWhileInstantTransforming ? false : !JGMathHelper.isMotionSmallerThanN((Entity)DBCClient.mc.field_71439_g, 0.05, true, false, true, true);
    }

    static {
        jumping = false;
        jumpRel = false;
        floating = false;
        releasing = false;
        ascending = false;
        ascendingK = false;
        sec = 0.0f;
        jumpToFly = false;
        quickTransformCount = 0;
        quickTransformAdded = false;
        singleDescendCount = 0;
        singleDescendAdded = false;
        holdingForTransform = false;
        sentAbsorption = false;
        flyY = 0.0f;
        floatTime = 0;
        floatMultAdded = false;
        swoopDirection = -1;
        tickTime = 0;
        prevTickTime = 0;
        dash = 0;
        turbo = false;
        ton = 0;
        wasTransformPressed = false;
        ra = new HashMap();
    }
}

