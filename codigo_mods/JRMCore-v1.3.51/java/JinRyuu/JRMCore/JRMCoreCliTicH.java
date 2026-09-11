/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovementInputFromOptions
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.JRMCoreHNC;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.MoveInputJRMC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.client.helpmanager.HelpSystem;
import JinRyuu.JRMCore.client.notification.JGNotification;
import JinRyuu.JRMCore.client.notification.JGNotificationGUI;
import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.JRMCore.p.OpenGuiMessage;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.JGMathHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class JRMCoreCliTicH {
    public static final int ANIM_FLY = 1;
    public static final int ANIM_STAND = 2;
    public Minecraft mc = JRMCoreClient.mc;
    private int check = 0;
    private boolean stand = true;
    static int actionSelectID = 0;
    static int actionNPA = 0;
    static boolean actionNBO = false;
    boolean jfc = JRMCoreH.JFC();
    boolean dbc = JRMCoreH.DBC();
    boolean nc = JRMCoreH.NC();
    private boolean actionMenuOpen = false;
    public static EntityLivingBase lockOn;
    private static int dst;
    private HashMap<Integer, KeyBinding> keys = new HashMap();
    public static HashMap<Integer, Integer> DoubleTapPressCounter;
    public static HashMap<Integer, Long> Tapped;
    public boolean wig = true;
    private boolean viewChange = false;
    private int viewPrevious = 0;
    public static int currentTime;
    public static int previousTime;
    public static float countingValue;
    public static float counterValue;
    public static float smod;
    public static int smodr;
    private static int gen;
    public static String[] mp;
    public static int ts;
    public static int ts2;
    public static int ts5;
    static int fnPressed;
    public static float offsetY;
    public static float clientHght;
    public static float yc;
    public static float clientHeight;
    public static float clientEyHeigClc;
    public static float clientDefEyHeig;
    public static float clientyOffset;
    public static int mw;
    private EntityRenderer renderer;
    private EntityRenderer prevRenderer;
    public static ArrayList<JGNotification> notificationPings;
    public Instant updateClient;
    public Instant updateTimer;

    public JRMCoreCliTicH(Minecraft mc) {
        this.mc = mc;
    }

    public static boolean onHotbar(Item item, EntityPlayer player) {
        for (int i = 0; i < 9; ++i) {
            if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != item) continue;
            return true;
        }
        return false;
    }

    private void onRenderTick() {
        if (this.mc.field_71441_e != null && !this.mc.func_147113_T()) {
            HelpSystem.update();
            this.updateReleaseLevel();
        }
        boolean ation = this.actionMenuOpen;
        if (JRMCoreA.pwr_usrs(JRMCoreH.Pwrtyp) && this.mc.field_71439_g != null && this.mc.field_71441_e != null && this.mc.field_71462_r == null && JRMCoreKeyHandler.actionMenu.func_151470_d()) {
            JRMCoreClient.JFCGui.renderActionMenu();
            this.actionMenuOpen = true;
            if (this.mc.field_71415_G) {
                this.mc.field_71415_G = false;
                this.mc.field_71417_B.func_74373_b();
            }
        } else if (this.actionMenuOpen && this.mc.field_71462_r != null) {
            KeyBinding.func_74506_a();
            this.actionMenuOpen = false;
        } else if (this.actionMenuOpen) {
            this.mc.field_71415_G = true;
            this.actionMenuOpen = false;
            this.mc.field_71417_B.func_74372_a();
        }
        if (JRMCoreH.Pwrtyp == 1) {
            JRMCoreA.actions = JRMCoreA.actionsDBC;
            if (ation && !this.actionMenuOpen && JRMCoreA.actions.get(actionSelectID) != null) {
                JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(actionSelectID), true, false);
            }
        }
        if (JRMCoreH.Pwrtyp == 2) {
            JRMCoreA.actions = JRMCoreA.actionsNC;
            if (ation && !this.actionMenuOpen && JRMCoreA.actions.get(actionSelectID) != null) {
                JRMCoreHNC.action((Integer)JRMCoreA.actions.get(actionSelectID), true, false);
            }
        }
        if (!actionNBO && ation && JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d() && actionSelectID % 9 == 4) {
            actionSelectID = -1;
            actionNBO = true;
            actionNPA = actionNPA == 0 ? 1 : 0;
        }
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        dst = 35;
        if (lockOn != null && player != null && JRMCoreConfig.lockon) {
            float rYaw;
            if (JRMCoreCliTicH.lockOn.field_70128_L || player.func_70032_d((Entity)lockOn) > (float)dst) {
                lockOn = null;
                return;
            }
            EntityLivingBase target = lockOn;
            double dx = player.field_70165_t - target.field_70165_t;
            double dz = player.field_70161_v - target.field_70161_v;
            double dy = player.field_70163_u - (target.field_70163_u + (double)(target.field_70131_O / 2.0f) - (double)this.mc.field_71439_g.field_70131_O + (double)1.6f);
            double angle = Math.atan2(dz, dx) * 180.0 / Math.PI;
            double pitch = Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180.0 / Math.PI;
            double distance = player.func_70032_d((Entity)target);
            for (rYaw = (float)(angle - (double)player.field_70177_z); rYaw > 180.0f; rYaw -= 360.0f) {
            }
            while (rYaw < -180.0f) {
                rYaw += 360.0f;
            }
            float rPitch = (float)pitch - (float)(10.0 / Math.sqrt(distance)) + (float)(distance * Math.PI / 90.0);
            player.func_70082_c(rYaw += 90.0f, -(rPitch - player.field_70125_A));
        }
        if (this.mc.field_71439_g != null && this.mc.field_71415_G) {
            JRMCoreCliTicH jRMCoreCliTicH = this;
            if (jRMCoreCliTicH.mc.func_71382_s()) {
                GL11.glPushMatrix();
                if (this.dbc && JGConfigClientSettings.CLIENT_GR2 && JRMCoreH.StusEfctsMe(7)) {
                    JRMCoreClient.bars.swoop();
                }
                if ((this.dbc || this.nc) && JRMCoreH.Accepted == 1 && !this.mc.field_71474_y.field_74321_H.func_151470_d() && (JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2 && JRMCoreH.inIll == null)) {
                    if (JGConfigClientSettings.CLIENT_hud2) {
                        JRMCoreClient.bars.renderCG(0);
                    } else {
                        JRMCoreClient.bars.renderBodyBar();
                    }
                    if ((JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2 && JRMCoreH.inIll == null) && !JGConfigClientSettings.CLIENT_hud2) {
                        JRMCoreClient.bars.renderKiBar();
                    }
                    if (JRMCoreH.Pwrtyp == 1) {
                        if (!JGConfigClientSettings.CLIENT_hud2) {
                            JRMCoreClient.bars.renderRageBar();
                        }
                        if (JRMCoreH.curRelease > 0) {
                            JRMCoreClient.bars.renderEnSideBar();
                        }
                        if (JRMCoreH.isChrgng) {
                            JRMCoreClient.bars.renderEnChrgBar();
                        }
                        if (JRMCoreH.curn > 0) {
                            JRMCoreClient.bars.rendern();
                        }
                        if (JRMCoreH.cura > 0) {
                            JRMCoreClient.bars.rendera();
                        }
                    }
                    if (JRMCoreH.Pwrtyp == 2 && JRMCoreH.curRelease > 0) {
                        JRMCoreClient.bars.renderEnSideBarNC();
                    }
                }
                if (JRMCoreH.Pwrtyp == 3 && JRMCoreH.SAOC() && JRMCoreH.Accepted == 1 && !this.mc.field_71474_y.field_74321_H.func_151470_d()) {
                    JRMCoreClient.bars.renderSAOHealthBar();
                }
                JRMCoreClient.bars.renderSafeZoneIndicator();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopMatrix();
            }
        }
        this.NotificationHandler();
    }

    private void onInputEvent(InputEvent.KeyInputEvent event) {
        EntityClientPlayerMP player = this.mc.field_71439_g;
        if (JRMCoreKeyHandler.lockOn.func_151470_d() && JRMCoreH.SklLvl(6) > 0 && JRMCoreConfig.lockon) {
            Entity rtr = JRMCoreH2.getTarget(1.0f, dst);
            if (lockOn == null && rtr != null) {
                double reachSq = dst * dst;
                double distanceSq = player.func_70068_e(rtr);
                if (reachSq >= distanceSq && rtr instanceof EntityLivingBase) {
                    lockOn = (EntityLivingBase)rtr;
                    player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "jinryuudragonbc:DBC4.lockon", 1.0f, 1.0f, true);
                }
            } else {
                lockOn = null;
            }
        }
        this.keys.put(this.mc.field_71474_y.field_74351_w.func_151463_i(), this.mc.field_71474_y.field_74351_w);
        this.keys.put(this.mc.field_71474_y.field_74368_y.func_151463_i(), this.mc.field_71474_y.field_74368_y);
        this.keys.put(this.mc.field_71474_y.field_74370_x.func_151463_i(), this.mc.field_71474_y.field_74370_x);
        this.keys.put(this.mc.field_71474_y.field_74366_z.func_151463_i(), this.mc.field_71474_y.field_74366_z);
        for (Map.Entry<Integer, KeyBinding> mapEntry : this.keys.entrySet()) {
            int n;
            int k = mapEntry.getKey();
            KeyBinding v = mapEntry.getValue();
            if (!v.func_151470_d()) continue;
            Tapped.put(k, JRMCoreCliTicH.dtap2());
            if (Tapped.get(k) < JRMCoreCliTicH.time()) continue;
            Integer c = DoubleTapPressCounter.get(k);
            Integer n2 = k;
            if (c == null) {
                n = 1;
            } else {
                c = c + 1;
                n = c;
            }
            DoubleTapPressCounter.put(n2, n);
        }
    }

    public static long time() {
        return System.currentTimeMillis();
    }

    public static long dtap2() {
        return System.currentTimeMillis() + 250L;
    }

    public static boolean getDT(KeyBinding k) {
        Integer c = DoubleTapPressCounter.get(k.func_151463_i());
        return c != null && c >= 2;
    }

    public void onTickInGUI() {
        GuiScreen guiscreen = this.mc.field_71462_r;
        EntityClientPlayerMP plyr = this.mc.field_71439_g;
        if (guiscreen instanceof GuiMainMenu && !this.wig) {
            JRMCoreH.resetChar();
            JRMCoreH.resetDedSer();
            this.wig = true;
        }
        if (plyr != null && this.mc.field_71441_e != null && !(guiscreen instanceof JRMCoreGuiScreen)) {
            if (JRMCoreGuiScreen.hairPreview != 0) {
                JRMCoreGuiScreen.hairPreview = 0;
            }
            if (JRMCoreGuiScreen.ufc) {
                JRMCoreGuiScreen.ufc = false;
            }
        }
        if (plyr != null && !plyr.field_70128_L && this.mc.field_71441_e != null && (JRMCoreH.PlyrAttrbts[0] == 0 || this.wig)) {
            this.wig = false;
            JRMCoreH.jrmct(1);
            JRMCoreH.jrmct(-1);
            JRMCoreH.jrmct(3);
        }
    }

    /*
     * Unable to fully structure code
     */
    public void onTickInGame() {
        block56: {
            block57: {
                JRMCoreCliTicH.mw = Mouse.getDWheel();
                plyr = this.mc.field_71439_g;
                if (JRMCoreH.data1 == null) {
                    JRMCoreH.Race = this.b(0);
                    JRMCoreH.dns = "0";
                    JRMCoreH.Pwrtyp = this.b(0);
                    JRMCoreH.Class = this.b(0);
                    JRMCoreH.Accepted = this.b(2);
                }
                v0 = JRMCoreHC.t1s = JRMCoreCliTicH.ts <= 0;
                JRMCoreCliTicH.ts = JRMCoreCliTicH.ts > 0 ? --JRMCoreCliTicH.ts : 20;
                v1 = JRMCoreHC.t2s = JRMCoreCliTicH.ts2 <= 0;
                JRMCoreCliTicH.ts2 = JRMCoreCliTicH.ts2 > 0 ? --JRMCoreCliTicH.ts2 : 40;
                v2 = JRMCoreHC.t5s = JRMCoreCliTicH.ts5 <= 0;
                JRMCoreCliTicH.ts5 = JRMCoreCliTicH.ts5 > 0 ? --JRMCoreCliTicH.ts5 : 100;
                JRMCoreCliTicH.currentTime = (int)(System.currentTimeMillis() / 1000L);
                if (JRMCoreCliTicH.currentTime != JRMCoreCliTicH.previousTime) {
                    JRMCoreCliTicH.previousTime = JRMCoreCliTicH.currentTime;
                    JRMCoreCliTicH.counterValue = JRMCoreCliTicH.countingValue;
                    JRMCoreCliTicH.countingValue = 0.0f;
                }
                if (JRMCoreCliTicH.currentTime == JRMCoreCliTicH.previousTime) {
                    JRMCoreCliTicH.countingValue += 1.0f;
                }
                tapped = (HashMap)JRMCoreCliTicH.Tapped.clone();
                for (Map.Entry<K, V> mapEntry : tapped.entrySet()) {
                    k = (Integer)mapEntry.getKey();
                    v = (Long)mapEntry.getValue();
                    if (v >= JRMCoreCliTicH.time()) continue;
                    JRMCoreCliTicH.Tapped.remove(k);
                    JRMCoreCliTicH.DoubleTapPressCounter.remove(k);
                }
                if (plyr == null || plyr.field_70128_L || this.mc.field_71441_e == null) break block56;
                if (this.wig) {
                    this.wig = false;
                }
                if (JRMCoreCliTicH.smodr > 0) {
                    --JRMCoreCliTicH.smodr;
                }
                if (JRMCoreCliTicH.smodr == 0 && JRMCoreCliTicH.smod != 1.0f) {
                    JRMCoreCliTicH.smod = 1.0f;
                }
                w = JRMCoreH.weightPerc(1) * JRMCoreCliTicH.smod;
                b = JRMCoreConfig.releaseStop != false && JRMCoreKeyHandler.KiCharge.func_151470_d() != false || JRMCoreH.kob != false;
                d18 = JRMCoreH.data(18, "0;0;0;0;0;0;0;0;0").split(";");
                fuse = d18[2].split(",");
                if (fuse.length == 3 && fuse[1].equalsIgnoreCase(this.mc.field_71439_g.func_70005_c_()) || JRMCoreH.kob) {
                    b = true;
                    pl = this.mc.field_71441_e.func_72924_a(fuse[0]);
                    if (pl != null) {
                        if (this.mc.field_71439_g.func_70032_d((Entity)pl) > 5.0f) {
                            this.mc.field_71439_g.func_70012_b(pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, this.mc.field_71439_g.field_70177_z, this.mc.field_71439_g.field_70125_A);
                        }
                        this.mc.field_71474_y.field_74320_O = 1;
                        this.mc.field_71439_g.field_70159_w = 0.0;
                        this.mc.field_71439_g.field_70181_x = 0.0;
                        this.mc.field_71439_g.field_70179_y = 0.0;
                    }
                }
                if (JRMCoreH.kob) {
                    if (!this.viewChange) {
                        this.viewChange = true;
                        this.viewPrevious = this.mc.field_71474_y.field_74320_O;
                    }
                    this.mc.field_71474_y.field_74320_O = 1;
                } else if (this.viewChange) {
                    this.viewChange = false;
                    this.mc.field_71474_y.field_74320_O = this.viewPrevious;
                }
                if (JRMCoreH.WeightOn > 0.0f || b || JRMCoreCliTicH.smod != 1.0f || JRMCoreH.pnh) {
                    if (b) {
                        w = 0.0f;
                    }
                    if (JRMCoreH.pnh && plyr.field_71093_bK != 22) {
                        w *= 0.5f;
                    }
                    if (this.mc.field_71439_g.field_71158_b instanceof MoveInputJRMC && this.mc.field_71439_g.field_70170_p.func_72976_f((int)this.mc.field_71439_g.field_70165_t, (int)this.mc.field_71439_g.field_70161_v) > 0 && this.mc.field_71439_g.field_70181_x > 0.0) {
                        this.mc.field_71439_g.field_70181_x *= (double)w;
                    }
                    if (!(this.mc.field_71439_g.field_71158_b instanceof MoveInputJRMC || this.mc.field_71439_g.field_71158_b.field_78900_b == 0.0f && this.mc.field_71439_g.field_71158_b.field_78902_a == 0.0f)) {
                        this.mc.field_71439_g.field_71158_b = new MoveInputJRMC(this.mc.field_71474_y, w);
                    }
                    if (this.mc.field_71439_g.field_71158_b instanceof MoveInputJRMC && ((MoveInputJRMC)this.mc.field_71439_g.field_71158_b).moveModifier != w) {
                        ((MoveInputJRMC)this.mc.field_71439_g.field_71158_b).moveModifier = w;
                    }
                } else if (!(this.mc.field_71439_g.field_71158_b instanceof MovementInputFromOptions)) {
                    this.mc.field_71439_g.field_71158_b = new MovementInputFromOptions(this.mc.field_71474_y);
                }
                ++this.check;
                if (this.check == 1) {
                    plyr.openGui((Object)mod_JRMCore.instance, 30, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
                    this.check = 2;
                }
                if ((JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2) && JRMCoreKeyHandler.Sagasys.func_151470_d()) {
                    plyr.openGui((Object)mod_JRMCore.instance, 60, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
                }
                if (JRMCoreKeyHandler.infopanel.func_151470_d()) {
                    plyr.openGui((Object)mod_JRMCore.instance, 30, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
                }
                if (JRMCoreKeyHandler.DS.func_151470_d()) {
                    plyr.openGui((Object)mod_JRMCore.instance, 0, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
                }
                if (JRMCoreH.PlyrAttrbts[0] == 0 || this.wig) {
                    this.wig = false;
                    JRMCoreH.jrmct(1);
                    JRMCoreH.jrmct(-1);
                    JRMCoreH.jrmct(3);
                }
                if (!this.mc.func_147113_T()) {
                    for (i = 0; i < JRMCoreH.techniqueCooldowns.length; ++i) {
                        JRMCoreH.techniqueCooldowns[i] = JRMCoreH.techniqueCooldowns[i] > 0.0f ? JRMCoreH.techniqueCooldowns[i] - 0.05f : 0.0f;
                    }
                    JRMCoreH.updateAllOldCooldownValues();
                }
                if (JRMCoreH.Accepted == 2) {
                    JRMCoreH.Race = 0;
                    JRMCoreH.dns = "0";
                    JRMCoreH.Pwrtyp = 0;
                    JRMCoreH.Class = 0;
                    JRMCoreH.State = 0;
                }
                f1 = 1.0f;
                f2 = 1.0f;
                f3 = 1.0f;
                v3 = noC = JRMCoreH.DBC() == false;
                if (JRMCoreH.dnsGender(JRMCoreH.dns) <= 1) {
                    f1 = 0.73f + (noC != false ? 0.27f : 0.0f);
                }
                if (JRMCoreH.dnsGender(JRMCoreH.dns) >= 2) {
                    f1 = 0.7f + (noC != false ? 0.27f : 0.0f);
                }
                if (JRMCoreH.JYC()) {
                    JRMCoreCliTicH.yc = JRMCoreHJYC.JYCsizeBasedOnAge((EntityPlayer)plyr);
                }
                if (JRMCoreH.DBC() && JRMCoreH.PlyrAttrbts[0] != 0 && JRMCoreConfig.sizes) {
                    f1r = f1;
                    JRMCoreHDBC.DBCreleaseZeroTurnOffTurbo();
                    f1 += JRMCoreHDBC.DBCsizeBasedOnCns(JRMCoreH.PlyrAttrbts);
                    if (!JRMCoreH.isPowerTypeChakra()) {
                        divine = JRMCoreH.Race == 3 ? JRMCoreH.StusEfctsMe(17) : false;
                        f2 = JRMCoreHDBC.DBCsizeBasedOnRace(JRMCoreH.Race, JRMCoreH.State, divine);
                        f3 = JRMCoreHDBC.DBCsizeBasedOnRace2(JRMCoreH.Race, JRMCoreH.State, divine);
                    }
                    rls = JRMCoreH.curRelease;
                    if (JRMCoreH.rSai(JRMCoreH.Race) && (JRMCoreH.State == 7 || JRMCoreH.State == 8)) {
                        rls = 50;
                        f1 = f1r;
                    }
                    f3 = (f3a = (f3 - 1.0f) * (float)rls * 0.02f + 1.0f) > f3 ? f3 : (f3 > 1.0f ? f3a : f3);
                    f2a = (f2 - 1.0f) * (float)rls * 0.02f + 1.0f;
                    f2 = f2 > 1.0f ? f2a : f2;
                    f1a1 = (f1 - f1r) * (rls <= 50 ? 0.25f : 0.5f);
                    f1ac = f1a1 * (float)rls * 0.02f;
                    f1 = f1ao = f1 - f1r - f1a1 + f1ac + f1r;
                }
                if (JRMCoreH.dns.length() < 3) {
                    f1 = 0.9375f;
                    f2 = 1.0f;
                    f3 = 1.0f;
                    JRMCoreCliTicH.yc = 1.0f;
                }
                if (!plyr.func_70608_bn() && JRMCoreH.JBRA()) {
                    if (this.mc.field_71474_y.field_74314_A.func_151468_f() && (this.mc.field_71441_e.func_147439_a((int)this.mc.field_71439_g.field_70165_t, (int)this.mc.field_71439_g.field_70163_u - 1, (int)this.mc.field_71439_g.field_70161_v - 1).func_149688_o() == Material.field_151586_h || this.mc.field_71441_e.func_147439_a((int)this.mc.field_71439_g.field_70165_t, (int)this.mc.field_71439_g.field_70163_u - 1, (int)this.mc.field_71439_g.field_70161_v - 1).func_149688_o() == Material.field_151587_i)) {
                        d1 = 0.02;
                        this.mc.field_71439_g.field_70181_x += d1;
                    }
                    clientHght2 = JRMCoreCliTicH.clientHght = JRMCoreComTickH.height * f1 * f3 * JRMCoreCliTicH.yc;
                    clientWdth2 = JRMCoreComTickH.width * f1 * f2 * f3 * JRMCoreCliTicH.yc;
                    JRMCoreComTickH.sS((EntityPlayer)plyr, clientWdth2, clientHght2);
                    transBody = (0.5f - (JRMCoreCliTicH.yc - 0.5f)) * 2.0f;
                    f6 = JRMCoreCliTicH.yc;
                    f6 = 3.0f - f6 * 2.0f;
                    JRMCoreCliTicH.offsetY = JRMCoreCliTicH.clientHght * 0.9f - 1.54f;
                }
                r = 10;
                ab = AxisAlignedBB.func_72330_a((double)(plyr.field_70165_t - (double)r), (double)(plyr.field_70163_u - (double)r), (double)(plyr.field_70161_v - (double)r), (double)(plyr.field_70165_t + (double)r), (double)(plyr.field_70163_u + (double)r), (double)(plyr.field_70161_v + (double)r));
                list = this.mc.field_71439_g.field_70170_p.func_72872_a(EntityPlayer.class, ab);
                for (i = 0; i < list.size(); ++i) {
                    plyr1 = (EntityPlayer)list.get(i);
                    if (plyr.func_70005_c_().equals(plyr1.func_70005_c_())) continue;
                    s = JRMCoreH.data(plyr1.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
                    s2 = JRMCoreH.data(plyr1.func_70005_c_(), 2, "0;0").split(";");
                    state = Integer.parseInt(s2[0]);
                    s14 = JRMCoreH.data(plyr1.func_70005_c_(), 14, "0,0,0,0,0,0").split(",");
                    rls = Integer.parseInt(JRMCoreH.data(plyr1.func_70005_c_(), 10, "0;0").split(";")[0]);
                    race = Integer.parseInt(s[0]);
                    PlyrAttrbts = new int[s14.length];
                    for (i1 = 0; i1 < PlyrAttrbts.length; ++i1) {
                        PlyrAttrbts[i1] = Integer.parseInt(s14[i1]);
                    }
                    f1 = 1.0f;
                    f2 = 1.0f;
                    f3 = 1.0f;
                    v4 = noC = JRMCoreH.DBC() == false;
                    if (JRMCoreH.dnsGender(s[1]) <= 1) {
                        f1 = 0.73f + (noC != false ? 0.27f : 0.0f);
                    }
                    if (JRMCoreH.dnsGender(s[1]) >= 2) {
                        f1 = 0.7f + (noC != false ? 0.27f : 0.0f);
                    }
                    yc = 1.0f;
                    if (JRMCoreH.JYC()) {
                        yc = JRMCoreHJYC.JYCsizeBasedOnAge(plyr1);
                    }
                    if (JRMCoreH.DBC() && PlyrAttrbts[0] != 0 && JRMCoreConfig.sizes) {
                        f1r = f1;
                        f1 += JRMCoreHDBC.DBCsizeBasedOnCns(PlyrAttrbts);
                        divine = race == 3 ? JRMCoreH.StusEfctsClient(17, plyr1) : false;
                        f2 = JRMCoreHDBC.DBCsizeBasedOnRace(race, state, divine);
                        f3 = JRMCoreHDBC.DBCsizeBasedOnRace2(race, state, divine);
                        if (JRMCoreH.rSai(race) && (state == 7 || state == 8)) {
                            rls = 50;
                            f1 = f1r;
                        }
                        f3 = (f3a = (f3 - 1.0f) * (float)rls * 0.02f + 1.0f) > f3 ? f3 : (f3 > 1.0f ? f3a : f3);
                        f2a = (f2 - 1.0f) * (float)rls * 0.02f + 1.0f;
                        f2 = f2 > 1.0f ? f2a : f2;
                        f1a1 = (f1 - f1r) * (rls <= 50 ? 0.25f : 0.5f);
                        f1ac = f1a1 * (float)rls * 0.02f;
                        f1 = f1ao = f1 - f1r - f1a1 + f1ac + f1r;
                    }
                    if (s[1].length() < 3) {
                        f1 = 0.9375f;
                        f2 = 1.0f;
                        f3 = 1.0f;
                        yc = 1.0f;
                    }
                    if (plyr1.func_70608_bn()) continue;
                    DO = true;
                    if (JRMCoreH.JBRA()) {
                        clientHght2 = clientHght = JRMCoreComTickH.height * f1 * f3 * yc;
                        clientWdth2 = JRMCoreComTickH.width * f1 * f2 * f3 * yc;
                        if (fuse.length == 3 && fuse[1].equalsIgnoreCase(plyr1.func_70005_c_())) {
                            JRMCoreComTickH.sS(plyr1, 0.0f, 0.0f);
                            DO = false;
                        } else {
                            JRMCoreComTickH.sS(plyr1, clientWdth2, clientHght2);
                        }
                    }
                    if (!DO || fuse.length != 3 || !fuse[1].equalsIgnoreCase(plyr1.func_70005_c_())) continue;
                    JRMCoreComTickH.sS(plyr1, 0.0f, 0.0f);
                }
                fly = JRMCoreH.StusEfctsMe(9) != false && JRMCoreH.StusEfctsMe(4) == false || JRMCoreH.DBC() != false && JRMCoreHDBC.DBCKiTechFly() != false;
                if ((plyr.field_70159_w < 0.0 ? plyr.field_70159_w * -1.0 : plyr.field_70159_w) > 0.2) break block57;
                v5 = plyr.field_70179_y < 0.0 ? plyr.field_70179_y * -1.0 : plyr.field_70179_y;
                if (!(v5 > 0.2) || !JRMCoreH.inAir((EntityPlayer)plyr)) ** GOTO lbl-1000
            }
            if (fly) {
                if (this.stand) {
                    this.stand = false;
                    JRMCoreH.Anim(1);
                }
            } else if (!this.stand) {
                this.stand = true;
                JRMCoreH.Anim(2);
            }
            if (this.mc.field_71439_g != null && this.mc.field_71441_e != null) {
                if (JRMCoreCliTicH.fnPressed > 0 && (this.mc.field_71462_r instanceof GuiInventory || this.mc.field_71462_r instanceof GuiContainerCreative)) {
                    PD.sendToServer(new OpenGuiMessage(mod_JRMCore.GUI_CUSTOM_INV));
                }
                JRMCoreCliTicH.fnPressed = JRMCoreKeyHandler.Fn.func_151470_d() ? 10 : --JRMCoreCliTicH.fnPressed;
                this.updateMaxStats();
            }
        }
    }

    public void onPreRenderTick() {
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        JRMCoreH.mld();
        if (event.phase.equals((Object)TickEvent.Phase.START) && !(JRMCoreH.paused = this.mc.func_147113_T())) {
            this.onTickInGUI();
            this.onTickInGame();
        }
        if (event.phase.equals((Object)TickEvent.Phase.END)) {
            // empty if block
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.onRenderTick();
        }
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        this.onInputEvent(event);
    }

    private byte b(int n) {
        return (byte)n;
    }

    private byte b(String n) {
        return Byte.parseByte(n);
    }

    private int i(String n) {
        return Integer.parseInt(n);
    }

    private void NotificationHandler() {
        int size = notificationPings.size();
        if (this.mc.field_71439_g != null && size > 0) {
            ResourceLocation txx = new ResourceLocation(JRMCoreH.tjjrmc + ":notification.png");
            ResourceLocation txx2 = new ResourceLocation(JRMCoreH.tjjrmc + ":note_category_icons.png");
            FontRenderer fontRenderer = this.mc.field_71466_p;
            int[] xx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] yy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            Instant now = Instant.now();
            for (int i = size - 1; i >= 0; --i) {
                int renderLocation;
                JGNotification note = notificationPings.get(i);
                if (note == null) {
                    notificationPings.remove(i);
                    continue;
                }
                Duration dur = Duration.between(note.received, now);
                int value = (int)((float)dur.toMillis() / 10.0f);
                float alpha = 1.0f;
                if (value >= 500) {
                    notificationPings.remove(i);
                    continue;
                }
                if (value >= 490) {
                    alpha = 1.0f - ((float)value - 490.0f) / 10.0f;
                }
                int n = renderLocation = note.renderLocation > xx.length - 1 ? xx.length - 1 : note.renderLocation;
                yy[n] = yy[n] + (value > 21 ? 21 : value);
                ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
                int sw = scaledresolution.func_78326_a();
                int sh = scaledresolution.func_78328_b();
                int x = 0;
                int y = 0;
                if (renderLocation == 0) {
                    x = sw - 116 + xx[renderLocation];
                    y = yy[renderLocation] - 21;
                } else if (renderLocation == 1) {
                    x = sw / 2 - 58 + xx[renderLocation];
                    y = yy[renderLocation] - 21;
                } else if (renderLocation == 2) {
                    x = xx[renderLocation];
                    y = yy[renderLocation] - 21;
                } else if (renderLocation == 3) {
                    x = sw - 116 + xx[renderLocation];
                    y = sh / 2 + 21 + yy[renderLocation] - 21;
                } else if (renderLocation == 4) {
                    x = sw / 2 - 58 + xx[renderLocation];
                    y = sh / 2 + 21 + yy[renderLocation] - 21;
                } else if (renderLocation == 5) {
                    x = xx[renderLocation];
                    y = sh / 2 + 21 + yy[renderLocation] - 21;
                } else if (renderLocation == 6) {
                    x = sw - 116 + xx[renderLocation];
                    y = sh - yy[renderLocation];
                } else if (renderLocation == 7) {
                    x = sw / 2 - 58 + xx[renderLocation];
                    y = sh - yy[renderLocation];
                } else if (renderLocation == 8) {
                    x = xx[renderLocation];
                    y = sh - yy[renderLocation];
                }
                this.mc.func_110434_K().func_110577_a(txx);
                byte icon = note.icon;
                int idY = note.icon * 16 / 256;
                int idX = note.icon * 16 - idY * 256;
                idY *= 16;
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                this.drawTexturedModalRect(x, y, 0, 0, 116, 21);
                this.mc.func_110434_K().func_110577_a(txx2);
                JGNotificationGUI.color(note.iconColor, alpha);
                this.drawTexturedModalRect(x + 3, y + 2, idX, idY, 16, 16);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                String text = JRMCoreGuiScreen.getStringShortened(fontRenderer, 94, note.title);
                JRMCoreGuiScreen.drawStringWithBorder(fontRenderer, text, x + 20, y + 5, 0xFFFFFF);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
        }
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int w, int h) {
        float zLevel = 0.0f;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(x + 0), (double)(y + h), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + h) * f1));
        tessellator.func_78374_a((double)(x + w), (double)(y + h), (double)zLevel, (double)((float)(u + w) * f), (double)((float)(v + h) * f1));
        tessellator.func_78374_a((double)(x + w), (double)(y + 0), (double)zLevel, (double)((float)(u + w) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78374_a((double)(x + 0), (double)(y + 0), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78381_a();
    }

    public void updateMaxStats() {
        if (this.updateClient == null || Duration.between(this.updateClient, Instant.now()).toMillis() >= 1000L) {
            this.updateClient = Instant.now();
            EntityClientPlayerMP p = JRMCoreClient.mc.field_71439_g;
            byte pwr = JRMCoreH.Pwrtyp;
            byte rce = JRMCoreH.Race;
            byte cls = JRMCoreH.Class;
            JRMCoreH.maxBody = JRMCoreH.stat((Entity)p, 2, pwr, 2, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0f);
            JRMCoreH.maxEnergy = JRMCoreH.stat((Entity)p, 5, pwr, 5, JRMCoreH.PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(pwr));
            JRMCoreH.maxStamina = JRMCoreH.stat((Entity)p, 2, pwr, 3, JRMCoreH.PlyrAttrbts[2], rce, cls, 0.0f);
        }
    }

    public void updateReleaseLevel() {
        if (JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2) {
            if (JRMCoreHC.smoothReleaseLevel != (float)JRMCoreH.curRelease) {
                float diff = 1.0f;
                if (this.updateTimer == null) {
                    this.updateTimer = Instant.now();
                } else {
                    diff = (float)Duration.between(this.updateTimer, Instant.now()).toMillis() / 1000.0f;
                    if (JGMathHelper.doubleSmallerThan(JRMCoreHC.smoothReleaseLevel - (float)JRMCoreH.curRelease, 2.0)) {
                        JRMCoreHC.smoothReleaseLevel = JRMCoreH.curRelease;
                    } else {
                        boolean smaller = JRMCoreHC.smoothReleaseLevel < (float)JRMCoreH.curRelease;
                        float add = ((float)JRMCoreH.curRelease - JRMCoreHC.smoothReleaseLevel) / 20.0f * (diff + 1.0f);
                        JRMCoreHC.smoothReleaseLevel += add;
                        if (smaller) {
                            if (JRMCoreHC.smoothReleaseLevel > (float)JRMCoreH.curRelease) {
                                JRMCoreHC.smoothReleaseLevel = JRMCoreH.curRelease;
                            }
                        } else if (JRMCoreHC.smoothReleaseLevel < (float)JRMCoreH.curRelease) {
                            JRMCoreHC.smoothReleaseLevel = JRMCoreH.curRelease;
                        }
                    }
                    this.updateTimer = Instant.now();
                }
            } else if (this.updateTimer != null) {
                this.updateTimer = null;
            }
        } else if (JRMCoreHC.smoothReleaseLevel != (float)JRMCoreH.curRelease) {
            JRMCoreHC.smoothReleaseLevel = JRMCoreH.curRelease;
        }
    }

    static {
        dst = 50;
        DoubleTapPressCounter = new HashMap();
        Tapped = new HashMap();
        smod = 1.0f;
        smodr = 0;
        gen = JRMCoreH.pg;
        mp = JRMCoreH.p;
        ts = 0;
        ts2 = 0;
        ts5 = 0;
        fnPressed = 10;
        offsetY = 0.0f;
        clientHght = 1.8f;
        yc = 1.0f;
        clientHeight = JRMCoreComTickH.height - 1.5f;
        clientEyHeigClc = 0.18f;
        clientDefEyHeig = 0.12f;
        clientyOffset = 1.62f;
        notificationPings = new ArrayList();
    }
}

