/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.input.Keyboard
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCEH;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.DBCKeyHandler;
import JinRyuu.DragonBC.common.DBCKiAttacks;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.DragonBC.common.Gui.DBCGuiSpacePod01;
import JinRyuu.DragonBC.common.Gui.ScouterGui;
import JinRyuu.DragonBC.common.Npcs.EntityMasterEnma;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKaio;
import JinRyuu.DragonBC.common.Render.SpacePod01Entity;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.DBC.DBCPscouter1;
import JinRyuu.JRMCore.p.DBC.DBCPscouter2;
import JinRyuu.JRMCore.p.DBC.DBCPscouter3;
import JinRyuu.JRMCore.p.DBC.DBCPscouter4;
import JinRyuu.JRMCore.p.DBC.DBCPspacepod1;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class DBCClientTickHandler {
    private int kibar;
    public static int countT = 0;
    public static int warnT = 0;
    public static int startcountT = 0;
    public static int ScFunc0 = 0;
    public static int ScFunc00 = 0;
    public static int ScFunc01 = 0;
    public static int ScFunc02 = 0;
    public static int ScFunc03 = 0;
    public static int ScFunc04 = 0;
    public static int ScFunc05 = 0;
    public static int ScFuncSB = 0;
    public static int heightplus = 0;
    public static int tick = 0;
    public static int runOutOfKi = 0;
    public static int c = 0;
    public static boolean selected = false;
    public static short csicsu = 0;
    public static boolean KAchrgOn = false;
    private int previousTime = 0;
    private int currentTime = 0;
    private int countingValue = 0;
    public static int counterValue = 0;
    ArrayList<double[]> dbs = new ArrayList();
    private static int gdb = 0;
    private static int ticking;
    private static int TiLess;
    private static int TiSen;
    private Minecraft mc = DBCClient.mc;
    public static int time;
    public static int power;
    public static int jump;
    public static int ascend;
    public static int pup;
    public static int ptime;
    public static int partnorm;
    public static int inSuperTime;
    public static int inSuperTime2;
    public static int inSuperTime3;
    public static float explevel;
    public static String textura;
    public static Item SuperHair;
    public static Item NormalHair;
    public static Block BlockHair01;
    private int check = 0;
    public int test = 0;
    private int timeincham;
    private boolean liedown;
    private int curHand;
    private static boolean inc;
    public static boolean charge;
    public static int charg;
    public static int mountHelper;
    public static boolean instantTransmissionOn;
    public static boolean instantTransmissionRequestSent;
    public static Instant instantTransmissionPress;
    public static boolean instantTransmissionWarning;

    public static boolean isPlayerInCreativeMode() {
        return JRMCoreH.isInCreativeMode((Entity)DBCClient.mc.field_71439_g);
    }

    public void onRenderTickInGUI(GuiScreen guiscreen) {
        if (this.mc.field_71439_g != null) {
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            ItemStack hand = player.field_71071_by.func_70448_g();
            ItemStack stackhead = ExtendedPlayer.get((EntityPlayer)this.mc.field_71439_g).inventory.func_70301_a(2);
            if (JRMCoreH.armTypScoutAllOn(stackhead) && DBCKeyHandler.ScFunc.func_151470_d()) {
                if (++ScFuncSB > 3 && ScFunc00 == 0) {
                    if (ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0) {
                        ScFunc00 = 1;
                        ScFuncSB = 0;
                        DBCKeyHandler.ScFunc.func_74506_a();
                    }
                }
                if (ScFuncSB > 3 && ScFunc00 == 1 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0) {
                    ScFuncSB = 0;
                    DBCKeyHandler.ScFunc.func_74506_a();
                }
                if (ScFuncSB > 3 && ScFunc00 == 0) {
                    if (ScFunc01 == 1 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0) {
                        ScFunc01 = 0;
                        ScFunc02 = 1;
                        ScFuncSB = 0;
                        KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                    }
                }
                if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 1 && ScFunc03 == 0 && ScFunc04 == 0) {
                    ScFunc02 = 0;
                    ScFunc03 = 1;
                    ScFuncSB = 0;
                    KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                }
                if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 1 && ScFunc04 == 0) {
                    ScFunc03 = 0;
                    ScFunc04 = 1;
                    ScFuncSB = 0;
                    KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                }
                if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 1) {
                    ScFunc04 = 0;
                    ScFuncSB = 0;
                    ScFunc00 = 0;
                    KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                }
            }
            DBCClient.scouterGui.renderScouter();
        }
    }

    public static boolean onHotbar(Item item, EntityPlayer player) {
        for (int i = 0; i < 9; ++i) {
            if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != item) continue;
            return true;
        }
        return false;
    }

    public void onRenderTick() {
        this.currentTime = (int)(System.currentTimeMillis() / 1000L);
        if (this.currentTime != this.previousTime) {
            this.previousTime = this.currentTime;
            counterValue = this.countingValue;
            this.countingValue = 0;
        }
        if (this.currentTime == this.previousTime) {
            ++this.countingValue;
        }
        if (!this.mc.func_147113_T()) {
            for (int i = 0; i < JRMCoreH.techniqueCooldowns.length; ++i) {
                if (JRMCoreH.techniqueCooldowns[i] >= 0.0f) {
                    int n = i;
                    JRMCoreH.techniqueCooldowns[n] = JRMCoreH.techniqueCooldowns[n] - 10.0f / (float)counterValue;
                }
                if (!(JRMCoreH.techniqueCooldowns[i] <= 0.0f)) continue;
                JRMCoreH.techniqueCooldowns[i] = 0.0f;
            }
            JRMCoreH.updateAllOldCooldownValues();
        }
        if (this.mc.field_71415_G) {
            DBCClientTickHandler dBCClientTickHandler = this;
            if (dBCClientTickHandler.mc.func_71382_s()) {
                EntityClientPlayerMP var4 = Minecraft.func_71410_x().field_71439_g;
                ItemStack hand = this.mc.field_71439_g.field_71071_by.func_70448_g();
                ItemStack stackhead = ExtendedPlayer.get((EntityPlayer)this.mc.field_71439_g).inventory.func_70301_a(2);
                if (stackhead != null && JRMCoreH.armTypScoutAllOn(stackhead)) {
                    if (DBCKeyHandler.ScFunc.func_151470_d()) {
                        if (++ScFuncSB > 3 && ScFunc00 == 0) {
                            if (ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0 && ScFunc05 == 0) {
                                ScFunc00 = 1;
                                ScFuncSB = 0;
                                DBCKeyHandler.ScFunc.func_74506_a();
                            }
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 1 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0 && ScFunc05 == 0) {
                            ScFuncSB = 0;
                            DBCKeyHandler.ScFunc.func_74506_a();
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 0) {
                            if (ScFunc01 == 1 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0 && ScFunc05 == 0) {
                                ScFunc01 = 0;
                                ScFunc02 = 1;
                                ScFuncSB = 0;
                                KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                            }
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 1 && ScFunc03 == 0 && ScFunc04 == 0 && ScFunc05 == 0) {
                            ScFunc02 = 0;
                            ScFunc03 = 1;
                            ScFuncSB = 0;
                            KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 1 && ScFunc04 == 0 && ScFunc05 == 0) {
                            ScFunc03 = 0;
                            ScFunc04 = 1;
                            ScFuncSB = 0;
                            KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 1 && ScFunc05 == 0) {
                            ScFunc04 = 0;
                            ScFunc05 = 1;
                            ScFuncSB = 0;
                            KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                        }
                        if (ScFuncSB > 3 && ScFunc00 == 0 && ScFunc01 == 0 && ScFunc02 == 0 && ScFunc03 == 0 && ScFunc04 == 0 && ScFunc05 == 1) {
                            ScFunc05 = 0;
                            ScFuncSB = 0;
                            ScFunc00 = 0;
                            KeyBinding.func_74510_a((int)DBCKeyHandler.ScFunc.func_151463_i(), (boolean)false);
                        }
                    }
                } else if (DBCKeyHandler.ScFunc.func_151468_f() && JRMCoreH.SklLvl(6) > 0) {
                    if (++DBCEH.kisnsMd > 4) {
                        DBCEH.kisnsMd = 0;
                    }
                    String t = JRMCoreH.trlai("dbc", "kisensemode" + DBCEH.kisnsMd);
                    ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    JRMCoreClient.mc.field_71439_g.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(color));
                }
                if (this.mc.field_71474_y.field_74320_O == 0) {
                    DBCClient.scouterGui.renderScouter();
                }
            }
        }
    }

    public void upd(EntityPlayer p) {
        if (++gdb > 40) {
            gdb = 0;
            this.dbs.clear();
            Block blockID = null;
            if (p.field_70170_p.field_73011_w.field_76574_g == 20) {
                blockID = BlocksDBC.BlockNamekDragonBall;
            }
            if (p.field_70170_p.field_73011_w.field_76574_g == 0) {
                blockID = BlocksDBC.BlockDragonBall;
            }
            int m = 80;
            int l1 = MathHelper.func_76128_c((double)p.field_70165_t);
            int i11 = MathHelper.func_76128_c((double)p.field_70161_v);
            for (int j11 = l1 - m; j11 <= l1 + m; ++j11) {
                for (int j2 = i11 - m; j2 <= i11 + m; ++j2) {
                    for (int k2 = 109; k2 >= 64; --k2) {
                        if (p.field_70170_p.func_147439_a(j11, k2, j2) != blockID) continue;
                        double[] d = new double[]{j11, j2};
                        this.dbs.add(d);
                    }
                }
            }
        }
    }

    public void DragonRadar(EntityPlayer p) {
        this.upd(p);
        int pitch = (int)p.field_70125_A + 60;
        for (int i = 0; i < this.dbs.size(); ++i) {
            DBCClient.SagaSys.DragonDetect(this.dbs.get(i)[0] - p.field_70165_t, this.dbs.get(i)[1] - p.field_70161_v, pitch > 0 ? pitch : 0);
        }
    }

    public void onTickInGUI() {
        GuiScreen guiscreen = this.mc.field_71462_r;
        if (DBCGuiSpacePod01.ToEarth == 1 || DBCGuiSpacePod01.ToVegeta == 1 || DBCGuiSpacePod01.ToNamek == 1) {
            int dbcspacepod1 = DBCGuiSpacePod01.ToEarth == 1 ? 3 : (DBCGuiSpacePod01.ToVegeta == 1 ? 2 : (DBCGuiSpacePod01.ToNamek == 1 ? 1 : 0));
            PD.sendToServer(new DBCPspacepod1(dbcspacepod1));
        }
    }

    public static void dri(int a) {
        PD.sendToServer(new DBCPdri(a));
    }

    public void changeCurEnAtSlct(int par1) {
        byte lmt;
        byte by = lmt = JRMCoreH.mrAtts ? (byte)8 : 4;
        if (par1 > 0) {
            par1 = 1;
        }
        if (par1 < 0) {
            par1 = -1;
        }
        JRMCoreH.EnAtSlct = (byte)(JRMCoreH.EnAtSlct - par1);
        while (JRMCoreH.EnAtSlct < 0) {
            JRMCoreH.EnAtSlct = (byte)(JRMCoreH.EnAtSlct + lmt);
        }
        while (JRMCoreH.EnAtSlct >= lmt) {
            JRMCoreH.EnAtSlct = (byte)(JRMCoreH.EnAtSlct - lmt);
        }
    }

    public void onTickInGameEnd() {
    }

    public void onTickInGame() {
        EntityClientPlayerMP var4 = Minecraft.func_71410_x().field_71439_g;
        WorldClient var3 = FMLClientHandler.instance().getClient().field_71441_e;
        if (this.mc.field_71441_e != null && this.mc.field_71439_g != null && !this.mc.field_71439_g.field_70128_L) {
            ItemStack hand;
            if (this.mc.func_71387_A()) {
                // empty if block
            }
            if (mountHelper != 0) {
                var4.func_70078_a(var4.field_70170_p.func_73045_a(mountHelper));
                if (var4.func_70115_ae()) {
                    mountHelper = 0;
                }
            }
            boolean inAabb = false;
            if (var4.field_71093_bK == DBCConfig.dimTimeChamber) {
                ++this.timeincham;
                if (this.timeincham >= 24000) {
                    DBCKiAttacks.dbctick(-6);
                    this.timeincham = 0;
                    var4.func_145747_a((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"dbc.HTC.toolong")));
                }
                inAabb = true;
            }
            if (var4.field_71093_bK == DBCConfig.dimNullRealm && this.mc.field_71439_g.field_70163_u <= (double)DBCConfig.NullRealmMinimumHeight) {
                JRMCoreHDBC.requestNullRealmKnockout();
            }
            if (!inAabb) {
                this.timeincham = 0;
            }
            if (var4.field_71093_bK == DBCConfig.otherWorld) {
                AxisAlignedBB aabbkaio;
                List kaio;
                AxisAlignedBB par2AxisAlignedBB = AxisAlignedBB.func_72330_a((double)60.0, (double)10.0, (double)35.0, (double)90.0, (double)110.0, (double)65.0);
                List enma = var3.func_72872_a(EntityMasterEnma.class, par2AxisAlignedBB);
                if (enma.size() > 1) {
                    for (int i = 1; enma.size() > i; ++i) {
                        Entity m = (Entity)enma.get(i);
                        JRMCoreH.KAsounds(m, 999);
                    }
                }
                if ((kaio = var3.func_72872_a(EntityMasterKaio.class, aabbkaio = AxisAlignedBB.func_72330_a((double)87.0, (double)1.0, (double)-3739.0, (double)127.0, (double)140.0, (double)-3699.0))).size() > 1) {
                    for (int i = 1; kaio.size() > i; ++i) {
                        Entity m = (Entity)kaio.get(i);
                        JRMCoreH.KAsounds(m, 999);
                    }
                }
            }
            if (var4.field_71093_bK == 0) {
                AxisAlignedBB kn = AxisAlignedBB.func_72330_a((double)76.0, (double)64.0, (double)41.0, (double)79.0, (double)129.0, (double)44.0);
                List l3 = var4.field_70170_p.func_72872_a(EntityPlayer.class, kn);
                for (int i = 0; i < l3.size(); ++i) {
                    boolean flag;
                    EntityPlayer e2 = (EntityPlayer)l3.get(i);
                    if (e2.func_70005_c_() != var4.func_70005_c_()) continue;
                    float f5 = 0.15f;
                    if (var4.field_70159_w < (double)(-f5)) {
                        var4.field_70159_w = -f5;
                    }
                    if (var4.field_70159_w > (double)f5) {
                        var4.field_70159_w = f5;
                    }
                    if (var4.field_70179_y < (double)(-f5)) {
                        var4.field_70179_y = -f5;
                    }
                    if (var4.field_70179_y > (double)f5) {
                        var4.field_70179_y = f5;
                    }
                    var4.field_70143_R = 0.0f;
                    if (var4.field_70181_x < -0.15) {
                        var4.field_70181_x = -0.15;
                    }
                    boolean bl = flag = var4.func_70093_af() && var4 instanceof EntityPlayer;
                    if (flag && var4.field_70181_x < 0.0) {
                        var4.field_70181_x = 0.0;
                    }
                    if (!var4.field_70123_F) continue;
                    var4.field_70181_x = 0.2;
                }
            }
            if ((hand = this.mc.field_71439_g.field_71071_by.func_70448_g()) != null || !DBCClient.mc.field_71474_y.field_74313_G.func_151470_d() || JRMCoreH.KASelected != 16 || JRMCoreKeyHandler.KiCharge.func_151470_d() || JRMCoreH.kiAmount > 0) {
                // empty if block
            }
            if (DBCGuiSpacePod01.ToEarth == 1 || DBCGuiSpacePod01.ToVegeta == 1 || DBCGuiSpacePod01.ToNamek == 1) {
                int dbcspacepod1 = DBCGuiSpacePod01.ToEarth == 1 ? 3 : (DBCGuiSpacePod01.ToVegeta == 1 ? 2 : (DBCGuiSpacePod01.ToNamek == 1 ? 1 : 0));
                PD.sendToServer(new DBCPspacepod1(dbcspacepod1));
            }
            if (ScFunc00 == 1) {
                if (++ScFunc0 == 2) {
                    ScFunc01 = 0;
                    ScFunc00 = 1;
                    ScFunc02 = 0;
                    ScFunc03 = 0;
                    ScFunc04 = 0;
                    ScFunc05 = 0;
                    int dbcscouter1 = var4.func_145782_y();
                    PD.sendToServer(new DBCPscouter1(dbcscouter1));
                }
                if (ScFunc0 == 30) {
                    ScFunc01 = 1;
                    ScFunc00 = 0;
                    ScFunc0 = 0;
                }
            }
            if (ScouterGui.count == 1 && ++countT == 5) {
                int dbcscouter2 = var4.func_145782_y();
                PD.sendToServer(new DBCPscouter2(dbcscouter2));
                countT = 0;
            }
            if (ScouterGui.warn == 1 && ++warnT == 10) {
                int dbcscouter3 = var4.func_145782_y();
                PD.sendToServer(new DBCPscouter3(dbcscouter3));
                warnT = 10;
            }
            if (ScouterGui.warn != 1) {
                warnT = 0;
            }
            if (ScouterGui.startcount == 1) {
                ++startcountT;
                if (warnT == 10) {
                    int dbcscouter4 = var4.func_145782_y();
                    PD.sendToServer(new DBCPscouter4(dbcscouter4));
                    startcountT = 10;
                }
            }
            if (ScouterGui.startcount != 1) {
                startcountT = 0;
            }
            if (DBCClient.mc.field_71439_g.field_70154_o != null && DBCClient.mc.field_71439_g.field_70154_o.getClass() == SpacePod01Entity.class && DBCClient.mc.field_71474_y.field_74322_I.func_151470_d()) {
                DBCClient.mc.field_71439_g.openGui((Object)mod_DragonBC.instance, 0, (World)DBCClient.mc.field_71441_e, (int)DBCClient.mc.field_71439_g.field_70165_t, (int)DBCClient.mc.field_71439_g.field_70163_u, (int)DBCClient.mc.field_71439_g.field_70161_v);
            }
            if (DBCClient.mc.field_71439_g.field_70154_o != null) {
                if (DBCClient.mc.field_71474_y.field_74351_w.func_151470_d()) {
                    JRMCoreH.forw = 1.0;
                    DBCClientTickHandler.dri(1);
                } else if (DBCClient.mc.field_71474_y.field_74368_y.func_151470_d()) {
                    JRMCoreH.forw = 2.0;
                    DBCClientTickHandler.dri(2);
                }
                if (DBCClient.mc.field_71474_y.field_74370_x.func_151470_d()) {
                    JRMCoreH.forw = 1.0;
                    DBCClientTickHandler.dri(5);
                } else if (DBCClient.mc.field_71474_y.field_74366_z.func_151470_d()) {
                    JRMCoreH.forw = 2.0;
                    DBCClientTickHandler.dri(6);
                }
                if (DBCClient.mc.field_71474_y.field_74314_A.func_151470_d() && DBCClient.mc.field_71439_g.field_70154_o != null) {
                    JRMCoreH.forw = 3.0;
                    DBCClientTickHandler.dri(3);
                } else if (JRMCoreKeyHandler.Fn.func_151470_d() && DBCClient.mc.field_71439_g.field_70154_o != null) {
                    JRMCoreH.forw = 4.0;
                    DBCClientTickHandler.dri(4);
                } else {
                    JRMCoreH.forw = 0.0;
                }
            }
            if (JRMCoreH.PlyrPwr((EntityPlayer)var4) == 1) {
                int itLevel;
                boolean itEnabled;
                if (JRMCoreH.curRelease != 0 && !JRMCoreH.StusEfctsMe(11) && !JRMCoreH.kob) {
                    ExtendedPlayer props;
                    int type;
                    byte[] sts;
                    boolean key = DBCClient.mc.field_71474_y.field_74322_I.func_151470_d();
                    if (hand == null && key && !DBCClient.mc.field_71474_y.field_74313_G.func_151470_d() && !JRMCoreH.isChrgng) {
                        byte b = 0;
                        if (JRMCoreKeyHandler.Fn.func_151470_d()) {
                            b = 1;
                        }
                        DBCKiTech.EnAtSlct(b);
                        KeyBinding cfr_ignored_0 = DBCClient.mc.field_71474_y.field_74322_I;
                        KeyBinding.func_74510_a((int)DBCClient.mc.field_71474_y.field_74322_I.func_151463_i(), (boolean)false);
                        DBCClientTickHandler.nuller();
                    } else if (JRMCoreKeyHandler.Fn.func_151470_d() && !DBCClient.mc.field_71474_y.field_74313_G.func_151470_d() && !JRMCoreH.isChrgng) {
                        int k = JRMCoreCliTicH.mw;
                        if (k != 0) {
                            this.changeCurEnAtSlct(k);
                            var4.field_71071_by.field_70461_c = this.curHand;
                            if (DBCClient.mc.field_71474_y.field_74331_S) {
                                if (k > 0) {
                                    k = 1;
                                }
                                if (k < 0) {
                                    k = -1;
                                }
                                DBCClient.mc.field_71474_y.field_74328_V += (float)k * 0.25f;
                            }
                        }
                        for (int i = 0; i < 8; ++i) {
                            if (Keyboard.getEventKey() != 2 + i) continue;
                            JRMCoreH.EnAtSlct = (byte)(JRMCoreH.mrAtts ? i : (i < 4 ? i : i - 4));
                            var4.field_71071_by.field_70461_c = this.curHand;
                        }
                        var4.field_71071_by.field_70461_c = this.curHand;
                        DBCClientTickHandler.nuller();
                    }
                    this.curHand = var4.field_71071_by.field_70461_c;
                    float p = DBCClient.mc.field_71439_g.field_70125_A < 0.0f ? (float)((int)DBCClient.mc.field_71439_g.field_70125_A * -1) : (float)((int)DBCClient.mc.field_71439_g.field_70125_A);
                    float y = DBCClient.mc.field_71439_g.field_70177_z < 0.0f ? (float)((int)DBCClient.mc.field_71439_g.field_70177_z * -1) : (float)((int)DBCClient.mc.field_71439_g.field_70177_z);
                    boolean rotat = p > DBCH.RotPic && p > DBCH.RotPic + 0.1f || p < DBCH.RotPic && p < DBCH.RotPic - 0.1f || y > DBCH.RotYaw && y > DBCH.RotYaw + 0.1f || y < DBCH.RotYaw && y < DBCH.RotYaw - 0.1f;
                    byte selectionID = JRMCoreH.EnAtSlct;
                    float currentCooldown = JRMCoreH.techCD(selectionID);
                    String[] tech = JRMCoreH.tech(selectionID);
                    if (hand == null && DBCClient.mc.field_71474_y.field_74313_G.func_151470_d() && JRMCoreKeyHandler.Fn.func_151470_d() && JRMCoreH.curEnergy > 0 && !JRMCoreKeyHandler.KiCharge.func_151470_d() && tech != null && DBCKiTech.KAkiEn(selectionID, JRMCoreH.curRelease, JRMCoreH.chrgPrc) && JRMCoreConfig.dat5695[JRMCoreH.techDBCty(tech)]) {
                        ExtendedPlayer props2;
                        sts = JRMCoreH.tech_statmods(tech[19]);
                        type = JRMCoreH.techDBCty(tech);
                        if (!JRMCoreH.isShtng && currentCooldown == 0.0f) {
                            int WILo;
                            JRMCoreH.isChrgng = JRMCoreH.techDBCctWc(tech, sts) > 10.0f;
                            int f = (int)(50.0f / JRMCoreH.techDBCctWc(tech, sts) * (float)JRMCoreH.charged);
                            int WIL = WILo = JRMCoreH.PlyrAttrbts[3];
                            boolean c = JRMCoreH.isFused();
                            WIL = JRMCoreH.getPlayerAttribute((EntityPlayer)DBCClient.mc.field_71439_g, JRMCoreH.PlyrAttrbts, 3, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(5), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.StusEfctsMe(20), 1, JRMCoreH.PlyrSkills, c, JRMCoreH.getMajinAbsorption());
                            int WIL2 = JRMCoreH.getPlayerAttribute((EntityPlayer)DBCClient.mc.field_71439_g, JRMCoreH.PlyrAttrbts, 3, 0, 0, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), false, false, false, false, 1, JRMCoreH.PlyrSkills, c, JRMCoreH.getMajinAbsorption());
                            int stat = JRMCoreH.stat((Entity)DBCClient.mc.field_71439_g, 3, JRMCoreH.Pwrtyp, 4, WIL, JRMCoreH.Race, JRMCoreH.Class, 0.0f);
                            int stat2 = JRMCoreH.stat((Entity)DBCClient.mc.field_71439_g, 3, JRMCoreH.Pwrtyp, 4, WIL2, JRMCoreH.Race, JRMCoreH.Class, 0.0f);
                            float costKi = (float)(JRMCoreH.techDBCkic(tech, stat2, sts) * JRMCoreH.chrgPrc) * 0.02f * (float)JRMCoreH.curRelease * 0.01f;
                            costKi = (float)((double)costKi * JRMCoreConfig.dat5696[type][2]);
                            if (tech[0].equals("KAFakeMoon")) {
                                costKi = Integer.parseInt(tech[7]);
                            }
                            if ((float)JRMCoreH.curEnergy > costKi) {
                                if (f > 50 && csicsu == csicsu / 5 * 5) {
                                    csicsu = (short)(csicsu + 1);
                                    JRMCoreH.charged = (short)(JRMCoreH.charged + 1);
                                } else if (f > 50) {
                                    csicsu = (short)(csicsu + 1);
                                } else if (f <= 50) {
                                    JRMCoreH.charged = (short)(JRMCoreH.charged + 1);
                                }
                            }
                            if (JRMCoreH.charged > 0 && JRMCoreH.curRelease > 0) {
                                JRMCoreH.cast = 50.0f / JRMCoreH.techDBCctWc(tech, sts) * (float)JRMCoreH.charged;
                                JRMCoreH.chrgPrc = (byte)(JRMCoreH.cast > 100.0f ? 100.0f : JRMCoreH.cast);
                            }
                        }
                        if ((props2 = ExtendedPlayer.get((EntityPlayer)var4)).getAnimKiShoot() != type + 1) {
                            int perc = 100;
                            DBCKiTech.triForce(3, type + 1, selectionID);
                            props2.setAnimKiShoot(type + 1);
                            props2.setAnimKiShootOn(1);
                            int color = Integer.parseInt(JRMCoreH.tech(selectionID)[10]);
                            byte align = JRMCoreH.getByte((EntityPlayer)var4, "jrmcAlign");
                            boolean setGoDOn = false;
                            for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                                if (JRMCoreH.plyrs[pl] == null || !JRMCoreH.plyrs[pl].equals(JRMCoreClient.mc.field_71439_g.func_70005_c_())) continue;
                                String s = JRMCoreH.data5[pl].split(";")[0];
                                align = Byte.parseByte(s);
                                setGoDOn = JRMCoreH.StusEfctsClient(20, pl);
                                break;
                            }
                            if (color == 0) {
                                if (align > 66) {
                                    color = 2;
                                }
                                if (align <= 66 && align >= 33) {
                                    color = 3;
                                }
                                if (align < 33) {
                                    color = 4;
                                }
                            }
                            boolean isCustomAttack = selectionID < 4;
                            props2.setGoDOn(setGoDOn && isCustomAttack && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENABLED ? 1 : 0);
                            props2.setKiShotCol(color);
                            float density = Float.parseFloat(JRMCoreH.tech(selectionID)[11]);
                            int dam1 = JRMCoreH.getEnegyDamageC(JRMCoreH.tech(selectionID), sts);
                            float size = JRMCoreH.calculateEnergyScale(dam1, JRMCoreH.getMaxEnergyDamage(), perc, sts, (byte)density, 0.01f, 0.1f);
                            props2.setKiShotSiz(size);
                            int part = 0;
                            String data = JRMCoreH.tech(selectionID)[0];
                            if (data.toLowerCase().contains("spiritbomb") || data.toLowerCase().contains("spirit bomb")) {
                                part = 1;
                            } else if (data.toLowerCase().contains("kahame") || data.toLowerCase().contains("kamehame") || data.toLowerCase().contains("kame hame")) {
                                part = 2;
                            } else if (data.toLowerCase().contains("galic")) {
                                part = 4;
                            } else if (Integer.parseInt(JRMCoreH.tech(selectionID)[3]) == 8 && Integer.parseInt(JRMCoreH.tech(selectionID)[6]) == 1) {
                                part = 3;
                            }
                            props2.setKiShotPart(part);
                        }
                        if ((float)JRMCoreH.charged >= JRMCoreH.techDBCctWc(tech, sts) / 2.0f && type == 6 && JRMCoreH.chrgPrc != 0) {
                            if (JRMCoreH.cDEnAt(selectionID, JRMCoreH.techDBCcd(tech, sts)) && JRMCoreH.curEnergy > 0) {
                                JRMCoreH.isShtng = true;
                                DBCKiTech.EnAt(selectionID, JRMCoreH.chrgPrc);
                                DBCClientTickHandler.nuller();
                            }
                            JRMCoreH.isShtng = false;
                            if (tech.length > 12 && JRMCoreH.charged == 1 && !inc) {
                                inc = true;
                                JRMCoreH.quad(1, selectionID > 3 ? 10 : Integer.parseInt(tech[3]), 0, Integer.parseInt(tech[12]) - (selectionID > 3 ? 1 : 0));
                            }
                        }
                        if (tech.length > 12 && JRMCoreH.charged == 3 && !inc) {
                            inc = true;
                            JRMCoreH.quad(1, selectionID > 3 ? 10 : Integer.parseInt(tech[3]), 0, Integer.parseInt(tech[12]) - (selectionID > 3 ? 1 : 0));
                        }
                    } else if (tech != null) {
                        sts = JRMCoreH.tech_statmods(tech[19]);
                        if (DBCKiTech.KAkiEn(selectionID, JRMCoreH.curRelease, JRMCoreH.chrgPrc)) {
                            boolean doContinues;
                            type = JRMCoreH.techDBCty(tech);
                            boolean isMoving = DBCClient.mc.field_71439_g.field_70159_w < 0.05 && DBCClient.mc.field_71439_g.field_70179_y < 0.05 && DBCClient.mc.field_71439_g.field_70159_w > -0.05 && DBCClient.mc.field_71439_g.field_70179_y > -0.05 && DBCClient.mc.field_71439_g.field_70181_x < 0.05;
                            boolean bl = doContinues = type >= JRMCoreConfig.ContinuesKiAttacks.length ? false : JRMCoreConfig.ContinuesKiAttacks[type];
                            if (doContinues && (float)JRMCoreH.charged > JRMCoreH.techDBCctWc(tech, sts) / 2.0f) {
                                if (isMoving) {
                                    if ((float)JRMCoreH.charged > JRMCoreH.techDBCctWc(tech, sts) / 2.0f && JRMCoreH.chrgPrc != 0 && !JRMCoreH.isShtng) {
                                        DBCClient.mc.field_71439_g.field_70181_x *= 0.0;
                                        if (JRMCoreH.cDEnAt(selectionID, JRMCoreH.techDBCcd(tech, sts)) && JRMCoreH.curEnergy > 0) {
                                            DBCKiTech.EnAt(DBCPacketHandlerServer.WAVE_FIRING);
                                            JRMCoreH.isShtng = true;
                                            DBCKiTech.EnAt(selectionID, JRMCoreH.chrgPrc);
                                            DBCClientTickHandler.nuller();
                                        }
                                    }
                                } else {
                                    DBCClientTickHandler.nuller();
                                    DBCKiTech.EnAt(DBCPacketHandlerServer.WAVE_STOP);
                                }
                            } else if ((float)JRMCoreH.charged > JRMCoreH.techDBCctWc(tech, sts) / 2.0f && type != 6 && JRMCoreH.chrgPrc != 0) {
                                if (JRMCoreH.cDEnAt(selectionID, JRMCoreH.techDBCcd(tech, sts)) && JRMCoreH.curEnergy > 0) {
                                    JRMCoreH.isShtng = true;
                                    DBCKiTech.EnAt(selectionID, JRMCoreH.chrgPrc);
                                    DBCClientTickHandler.nuller();
                                }
                                JRMCoreH.isShtng = false;
                            } else if (JRMCoreH.curRelease != 0) {
                                DBCClientTickHandler.nuller();
                            }
                            if (!isMoving) {
                                JRMCoreH.isShtng = false;
                            }
                        }
                    } else {
                        DBCClientTickHandler.nuller();
                        JRMCoreH.isShtng = false;
                    }
                    if (!JRMCoreH.isShtng && !JRMCoreH.isChrgng && (props = ExtendedPlayer.get((EntityPlayer)var4)).getAnimKiShoot() != 0) {
                        DBCKiTech.triForce(3, 0, 0);
                        props.setAnimKiShoot(0);
                        props.setAnimKiShootOn(0);
                    }
                    DBCH.RotPic = p;
                    DBCH.RotYaw = y;
                    JRMCoreHC.Blocking();
                    DBCKiTech.ChargeKi();
                    DBCKiTech.JumpKi(DBCClient.mc.field_71474_y.field_74314_A);
                    DBCKiTech.FloatKi(JRMCoreKeyHandler.KiFlight, DBCClient.mc.field_71474_y.field_74314_A, DBCClient.mc.field_71474_y.field_74311_E);
                    DBCKiTech.TurboMode(JRMCoreKeyHandler.KiDash);
                    DBCKiTech.DashKi(var4.func_70051_ag() || DBCKiTech.turbo);
                    DBCKiTech.Ascend(JRMCoreKeyHandler.KiAscend);
                    DBCKiTech.Descend(JRMCoreKeyHandler.KiDescend);
                } else {
                    if (JGConfigClientSettings.dbcFastFusionSpectatorCameraFollowOn && JRMCoreH.curRelease != 0 && JRMCoreH.StusEfctsMe(11) && !JRMCoreH.kob && JRMCoreH.isFused() && JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreH.dnn(18)) {
                        for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                            EntityPlayer player;
                            String[] fusionData;
                            if (!JRMCoreH.plyrs[pl].equals(var4.func_70005_c_())) continue;
                            String[] fullFusionData = JRMCoreH.dat18[pl].split(";");
                            if (fullFusionData.length < 3 || (fusionData = fullFusionData[2].split(",")).length != 3 || (player = var4.field_70170_p.func_72924_a(fusionData[0])) == null) break;
                            var4.field_70159_w -= (var4.field_70165_t - player.field_70165_t) / 3.0;
                            var4.field_70181_x -= (var4.field_70163_u - player.field_70163_u) / 3.0;
                            var4.field_70179_y -= (var4.field_70161_v - player.field_70161_v) / 3.0;
                            break;
                        }
                    }
                    if (JRMCoreH.isPowerTypeKi() && DBCKiTech.floating && JRMCoreH.curRelease == 0) {
                        DBCKiTech.floating = false;
                    }
                    if (!JRMCoreH.kob) {
                        if (!JRMCoreH.StusEfctsMe(11)) {
                            DBCKiTech.Descend(JRMCoreKeyHandler.KiDescend);
                            DBCKiTech.DashKi(false);
                        }
                        DBCKiTech.ChargeKi();
                    }
                    if (JRMCoreKeyHandler.KiFlight.func_151470_d() || JRMCoreKeyHandler.KiDash.func_151470_d() || JRMCoreKeyHandler.KiAscend.func_151470_d()) {
                        String t1 = JRMCoreH.StusEfctsMe(11) ? JRMCoreH.trl("dbc", "fusedspectator") : JRMCoreH.trl("dbc.clienttick.increltouseki");
                        String t2 = JRMCoreKeyHandler.KiCharge.func_151464_g();
                        String tf = String.format(t1, t2);
                        this.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(tf));
                        KeyBinding.func_74510_a((int)JRMCoreKeyHandler.KiFlight.func_151463_i(), (boolean)false);
                        KeyBinding.func_74510_a((int)JRMCoreKeyHandler.KiDash.func_151463_i(), (boolean)false);
                        KeyBinding.func_74510_a((int)JRMCoreKeyHandler.KiAscend.func_151463_i(), (boolean)false);
                    }
                }
                if (JRMCoreH.isPowerTypeKi()) {
                    JRMCoreHC.BPC_ME = JRMCoreH.gkap(JRMCoreH.bpc((EntityPlayer)JRMCoreClient.mc.field_71439_g, JRMCoreClient.mc.field_71439_g.func_70005_c_(), JRMCoreH.Pwrtyp), "BPC_ME");
                    if (JRMCoreHC.t1s) {
                        JRMCoreHC.BPC_ME2 = JRMCoreH.bpc((EntityPlayer)JRMCoreClient.mc.field_71439_g, JRMCoreClient.mc.field_71439_g.func_70005_c_(), JRMCoreH.Pwrtyp, 100);
                    }
                    if (!JRMCoreH.damInd.isEmpty() && JRMCoreH.SklLvl(6) > 0) {
                        ArrayList<String> remove = new ArrayList<String>();
                        if (!JRMCoreH.damInd.isEmpty()) {
                            for (Map.Entry<String, String> mapEntry : JRMCoreH.damInd.entrySet()) {
                                String[] k = mapEntry.getKey().split(":");
                                String[] v = mapEntry.getValue().split(":");
                                double x = Double.parseDouble(k[0]);
                                double y = Double.parseDouble(k[1]);
                                double z = Double.parseDouble(k[2]);
                                double amount = Double.parseDouble(v[0]);
                                float timeleft = Float.parseFloat(v[1]) - 1.0f;
                                remove.add(mapEntry.getKey());
                                double X = x;
                                double Y = y;
                                double Z = z;
                                mod_JRMCore.proxy.generateDamIndParticles(x, y, z, amount, timeleft);
                            }
                        }
                        for (int i = 0; i < remove.size(); ++i) {
                            JRMCoreH.damInd.remove(remove.get(i));
                        }
                        remove.clear();
                    }
                }
                boolean itEnabledShort = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[0];
                boolean itEnabledLong = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[1];
                boolean bl = itEnabled = itEnabledShort || itEnabledLong;
                if (itEnabled) {
                    boolean isUsed = this.mc.field_71474_y.field_74313_G.func_151470_d();
                    if (JRMCoreH.isPowerTypeKi() && JRMCoreH.curRelease != 0) {
                        int itLevel2 = JRMCoreH.SklLvl(17, JRMCoreH.Pwrtyp);
                        if (itLevel2 > 0 && DBCKeyHandler.thirdFn.func_151470_d()) {
                            boolean cancelUse;
                            boolean disabled = false;
                            if (JRMCoreH.StusEfctsMe(11) && !JRMCoreH.kob && JRMCoreH.isFused() && JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreH.dnn(18)) {
                                for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                                    EntityPlayer player;
                                    String[] fusionData;
                                    if (!JRMCoreH.plyrs[pl].equals(var4.func_70005_c_())) continue;
                                    String[] fullFusionData = JRMCoreH.dat18[pl].split(";");
                                    if (fullFusionData.length < 3 || (fusionData = fullFusionData[2].split(",")).length != 3 || (player = var4.field_70170_p.func_72924_a(fusionData[0])) == null) break;
                                    disabled = true;
                                    break;
                                }
                            }
                            boolean bl2 = cancelUse = this.mc.field_71474_y.field_74312_F.func_151470_d() || this.mc.field_71439_g.field_71071_by.func_70448_g() != null || disabled;
                            if (cancelUse) {
                                DBCClientTickHandler.resetIT(isUsed);
                                KeyBinding.func_74510_a((int)DBCKeyHandler.thirdFn.func_151463_i(), (boolean)false);
                            } else {
                                instantTransmissionOn = true;
                                if (isUsed) {
                                    if (itEnabledShort) {
                                        if (!instantTransmissionRequestSent) {
                                            DBCKiTech.EnAt(DBCPacketHandlerServer.INSTANT_TRANSMISSION, (byte)0);
                                            instantTransmissionRequestSent = true;
                                            instantTransmissionPress = null;
                                            instantTransmissionPress = Instant.now();
                                        } else {
                                            instantTransmissionPress = null;
                                            instantTransmissionOn = false;
                                        }
                                    } else {
                                        String message = "Instant Transmission Failed! Short teleportation is Disabled! ";
                                        DBCClient.mc.field_71439_g.func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                                    }
                                } else if (instantTransmissionPress == null && !instantTransmissionRequestSent) {
                                    instantTransmissionPress = Instant.now();
                                } else if (!instantTransmissionRequestSent) {
                                    long timer = Duration.between(instantTransmissionPress, Instant.now()).getSeconds();
                                    if (timer >= 4L) {
                                        if (itEnabledLong) {
                                            EntityClientPlayerMP plyr = DBCClient.mc.field_71439_g;
                                            plyr.openGui((Object)mod_JRMCore.instance, 10100, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
                                            instantTransmissionRequestSent = true;
                                            instantTransmissionPress = null;
                                        } else {
                                            String message = "Instant Transmission Failed! Long teleportation is Disabled! ";
                                            DBCClient.mc.field_71439_g.func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                                        }
                                    }
                                } else {
                                    instantTransmissionPress = null;
                                    instantTransmissionOn = false;
                                }
                            }
                        } else {
                            DBCClientTickHandler.resetIT(isUsed);
                        }
                    } else {
                        DBCClientTickHandler.resetIT(isUsed);
                    }
                } else if (JRMCoreH.isPowerTypeKi() && JRMCoreH.curRelease != 0 && (itLevel = JRMCoreH.SklLvl(17, JRMCoreH.Pwrtyp)) > 0) {
                    if (DBCKeyHandler.thirdFn.func_151470_d()) {
                        if (!instantTransmissionWarning) {
                            String message = "Instant Transmission Failed! Teleportation is Disabled! ";
                            DBCClient.mc.field_71439_g.func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                            instantTransmissionWarning = true;
                        }
                    } else {
                        instantTransmissionWarning = false;
                    }
                }
            }
        }
    }

    public static void resetIT(boolean isUsed) {
        instantTransmissionOn = false;
        if (!isUsed) {
            instantTransmissionRequestSent = false;
        }
        instantTransmissionPress = null;
    }

    public static void nuller() {
        JRMCoreH.chrgPrc = 0;
        JRMCoreH.charged = 0;
        JRMCoreH.channel = 0;
        JRMCoreH.wave = 0;
        JRMCoreH.isChrgng = false;
        JRMCoreH.cast = 0.0f;
        csicsu = 0;
        inc = false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!JRMCoreH.paused) {
            if (event.phase.equals((Object)TickEvent.Phase.START)) {
                this.onTickInGame();
            } else if (event.phase.equals((Object)TickEvent.Phase.END)) {
                this.onTickInGameEnd();
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !JRMCoreH.paused) {
            this.onRenderTick();
        }
    }

    static {
        time = 0;
        power = 0;
        jump = 0;
        ascend = 0;
        pup = 0;
        ptime = 0;
        partnorm = 0;
        inSuperTime = 0;
        inSuperTime2 = 0;
        inSuperTime3 = 0;
        charge = false;
        charg = 0;
        mountHelper = 0;
        instantTransmissionOn = false;
        instantTransmissionRequestSent = false;
        instantTransmissionPress = null;
        instantTransmissionWarning = false;
    }
}

