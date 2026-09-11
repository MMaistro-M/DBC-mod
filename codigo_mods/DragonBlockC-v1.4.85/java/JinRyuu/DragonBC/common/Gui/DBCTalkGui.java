/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.DBCKiAttacks;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.DragonBC.common.Gui.DBCGuiButtons01;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiButtons00MS;
import JinRyuu.JRMCore.JRMCoreGuiButtons01;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.DBC.DBCPwish;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class DBCTalkGui
extends GuiScreen {
    public static final int ENMA = 10;
    public static final int ENMA_REINCARNATE = 1001;
    public static final int KAMI = 11;
    public static final int KAMI_STARTANEW = 1101;
    public static final int KAMI_CUTTAIL = 1102;
    public static final int KAMI_REGROWTAIL = 1104;
    public static final int KAMI_WEIGHT = 1105;
    public static final int KAMI_SKILLS = 111;
    public static final int JIN = 9000;
    public static final int JIN_SKILLS = 9100;
    public static final int GURU = 9001;
    public static final int GURU_SKILLS = 9101;
    public static final int WHIS = 9002;
    public static final int WHIS_SKILLS = 9102;
    public static final int KAIO = 12;
    public static final int KAIO_SKILLS = 112;
    public static final int KAIO_ATTACKS = 1121;
    public static final int KAIO_DIFFICULTY = 221;
    public static final int KARIN = 13;
    public static final int KARIN_SKILLS = 113;
    public static final int ROSHI = 15;
    public static final int ROSHI_SKILLS = 115;
    public static final int ROSHI_ATTACKS = 1151;
    public static final int CELL = 16;
    public static final int CELL_SKILLS = 116;
    public static final int CELL_ATTACKS = 1161;
    public static final int GOKU = 17;
    public static final int GOKU_SKILLS = 117;
    public static final int GOKU_ATTACKS = 1171;
    public static final int FRIEZA = 18;
    public static final int FRIEZA_SKILLS = 118;
    public static final int FRIEZA_ATTACKS = 1181;
    public static final int BABIDI = 19;
    public static final int BABIDI_SKILLS = 119;
    public static final int BABIDI_ATTACKS = 1191;
    public static final int PICCOLO = 20;
    public static final int PICCOLO_SKILLS = 120;
    public static final int PICCOLO_ATTACKS = 1201;
    public static final int VEGETA = 21;
    public static final int VEGETA_SKILLS = 121;
    public static final int VEGETA_ATTACKS = 1211;
    public static final int GOHAN = 22;
    public static final int GOHAN_SKILLS = 122;
    public static final int GOHAN_ATTACKS = 1221;
    public static final int TRUNKS = 23;
    public static final int TRUNKS_SKILLS = 123;
    public static final int TRUNKS_ATTACKS = 1231;
    private Minecraft mc = JRMCoreClient.mc;
    private FontRenderer fontRenderer;
    private int tick;
    private GuiIngame Guiingame;
    private int wish;
    private int ipg;
    private int lp;
    private int master;
    private HashMap<String, String> MsnSysTalkTo;
    private String CurMaster;
    private String name;
    private int text;
    protected GuiTextField inputField;
    private String defaultInputFieldText;
    private int updateTimer;
    private int updateTime;
    private boolean updateTimerStopper;
    public static int count = 0;
    public static int warn = 0;
    public static int startcount = 0;
    private String Process;
    private int wid;
    private int hei;
    private String textureFile;

    public void renderSuperProtect(int ki) {
        this.field_146292_n.clear();
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        this.field_146292_n.add(new DBCGuiButtons01(100, posX - 0, posY - 0, 20, 20, "TEST"));
    }

    public DBCTalkGui(int w, World wld, int x, int y, int z) {
        this.fontRenderer = this.mc.field_71466_p;
        this.wish = 0;
        this.ipg = 0;
        this.lp = 0;
        this.master = 0;
        this.CurMaster = "";
        this.name = "";
        this.text = 0;
        this.defaultInputFieldText = "";
        this.updateTimer = 0;
        this.updateTime = 0;
        this.updateTimerStopper = false;
        this.Process = "Something is Wrong";
        this.wid = 0;
        this.hei = 0;
        this.textureFile = "jinryuudragonbc:sagas.png";
        double n = 0.1;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((double)x - n), (double)((double)y + n), (double)((double)z - n), (double)((double)x + n), (double)((double)y + n), (double)((double)z + n));
        List l = wld.func_72872_a(EntityDBCKami.class, aabb);
        Iterator it = l.iterator();
        if (it.hasNext()) {
            EntityDBCKami k = (EntityDBCKami)((Object)it.next());
            this.CurMaster = k.func_70005_c_();
        }
        this.wish = w;
        this.updateTime = 0;
        JRMCoreH.revTmr = -1;
        JRMCoreH.Master = 1;
        this.MsnSysTalkTo = JRMCoreM.getMda_Obj_TalkTo();
        if (this.wish == 13) {
            DBCKiAttacks.dbctick(-100);
        } else if (this.wish == 12) {
            DBCKiAttacks.dbctick(-101);
        } else if (this.wish == 15) {
            DBCKiAttacks.dbctick(-101);
        } else if (this.wish == 16) {
            DBCKiAttacks.dbctick(-101);
        } else if (this.wish == 17) {
            DBCKiAttacks.dbctick(-101);
        } else if (this.wish == 18) {
            DBCKiAttacks.dbctick(-101);
        } else if (this.wish == 19) {
            DBCKiAttacks.dbctick(-101);
        }
    }

    public void func_73866_w_() {
    }

    public void func_146284_a(GuiButton button) {
        int i;
        if (button.field_146127_k == -1) {
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 0) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 1) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 2) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 3) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 4) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 10) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 11) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 12) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 13) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 14) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 99) {
            this.updateTime = 0;
            if (this.wish == 10) {
                this.wish = 1001;
            } else if (this.wish == 1001) {
                this.wish = 10;
            }
        }
        if (button.field_146127_k == 100) {
            this.dbcTelep(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 101) {
            this.dbcTelep(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 103) {
            this.wish = 221;
        }
        if (button.field_146127_k == 104) {
            this.wish = 12;
        }
        if (button.field_146127_k == 105) {
            this.mc.field_71439_g.func_71053_j();
            JRMCoreH.Char((byte)101, (byte)0);
        }
        if (button.field_146127_k == 106) {
            JRMCoreH.Char((byte)104, (byte)0);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 107) {
            this.wish = 1102;
        }
        if (button.field_146127_k == 108) {
            this.wish = 1104;
        }
        if (button.field_146127_k == 109) {
            JRMCoreH.Char((byte)105, (byte)0);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 198) {
            this.wish = 113;
        }
        if (button.field_146127_k == 110) {
            this.wish = 1105;
        }
        if (button.field_146127_k == 111) {
            this.wish = 2005;
        }
        for (int i2 = 0; i2 < 15; ++i2) {
            if (button.field_146127_k == 10111 + i2) {
                this.wish = 111 + i2;
            }
            if (button.field_146127_k != 101111 + i2 * 10) continue;
            this.wish = 1111 + i2 * 10;
        }
        if (button.field_146127_k == 9100) {
            this.wish = 9100;
        }
        if (button.field_146127_k == 9101) {
            this.wish = 9101;
        }
        if (button.field_146127_k == 9102) {
            this.wish = 9102;
        }
        if (button.field_146127_k == 199) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 201) {
            this.mc.field_71439_g.func_71053_j();
            JRMCoreH.Char((byte)4, (byte)0);
            JRMCoreH.Char((byte)100, (byte)0);
            boolean doit = true;
            if (JRMCoreH.DBC() && this.mc.field_71439_g != null) {
                boolean bl = doit = !JRMCoreH.isFused();
            }
            if (doit) {
                JRMCoreH.resetChar();
                DBCKiTech.turbo = false;
            }
        }
        if (button.field_146127_k == 2011) {
            this.wish = 1101;
        }
        if (button.field_146127_k == 2012) {
            this.wish = 11;
        }
        if (button.field_146127_k == 202) {
            if (JRMCoreH.align > 65) {
                this.dbcWish(button.field_146127_k);
            } else {
                this.dbcWish(button.field_146127_k + 1);
            }
            this.mc.field_71439_g.func_71053_j();
        }
        if (button instanceof JRMCoreGuiButtons00MS && button.field_146127_k == 6000) {
            JRMCoreGuiButtons00MS btn = (JRMCoreGuiButtons00MS)button;
            if (btn.d2.equals("-3")) {
                this.mc.field_71439_g.func_71053_j();
            }
            JRMCoreGuiScreen.dataSend(btn.d1, btn.d2);
        }
        for (i = 0; i < JRMCoreH.DBCSkillsIDs.length; ++i) {
            if (button.field_146127_k != 1000 + i) continue;
            JRMCoreH.Skll((byte)1, (byte)i);
        }
        for (i = 0; i < (JRMCoreH.Pwrtyp == 2 ? JRMCoreH.pmj : JRMCoreH.pmdbc).length; i = (int)((byte)(i + 1))) {
            if (button.field_146127_k != 4200 + i) continue;
            JRMCoreH.Tech((byte)4, "" + i);
            this.wish = (this.wish - 1000) / 10;
            this.ipg = 0;
        }
        if ((this.name.matches("[0-9].+") || this.name.matches("[0-9]+")) && this.name.length() > 0) {
            try {
                this.name = (Float.parseFloat(this.name) > 5000.0f ? 5000.0f : (Float.parseFloat(this.name) < 0.1f ? 0.1f : Float.parseFloat(this.name))) + "";
            }
            catch (Exception e) {
                this.name = "5";
            }
            if (button.field_146127_k == 210) {
                PD.sendToServer(new DBCPwish(2, "0;" + this.name));
                this.mc.field_71439_g.func_71053_j();
            }
            if (button.field_146127_k == 211) {
                PD.sendToServer(new DBCPwish(2, "1;" + this.name));
                this.mc.field_71439_g.func_71053_j();
            }
            if (button.field_146127_k == 212) {
                PD.sendToServer(new DBCPwish(2, "2;" + this.name));
                this.mc.field_71439_g.func_71053_j();
            }
            if (button.field_146127_k == 213) {
                PD.sendToServer(new DBCPwish(2, "3;" + this.name));
                this.mc.field_71439_g.func_71053_j();
            }
            if (button.field_146127_k == 214) {
                PD.sendToServer(new DBCPwish(2, "4;" + this.name));
                this.mc.field_71439_g.func_71053_j();
            }
        }
        if (button.field_146127_k == 50) {
            PD.sendToServer(new DBCPwish(5, "0"));
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 51) {
            PD.sendToServer(new DBCPwish(5, "1"));
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 52) {
            PD.sendToServer(new DBCPwish(6, ""));
            this.mc.field_71439_g.func_71053_j();
        }
    }

    public void dri(int id) {
        int dri = id;
        PD.sendToServer(new DBCPdri(dri));
    }

    public void dbcWish(int id) {
        int dbcwish = id;
        PD.sendToServer(new DBCPwish(1, dbcwish + ""));
    }

    public void dbcTelep(int id) {
        int dbcwish = id;
        PD.sendToServer(new DBCPwish(1, dbcwish + ""));
    }

    private void name(FontRenderer var8, int i, int j) {
        this.inputField = new GuiTextField(var8, i + 100, j + 15 + 0, 100, 12);
        this.inputField.func_146203_f(20);
        this.inputField.func_146185_a(true);
        this.inputField.func_146195_b(true);
        this.inputField.func_146180_a(this.defaultInputFieldText);
        this.inputField.func_146205_d(true);
    }

    public void func_73876_c() {
        if (this.inputField != null) {
            this.inputField.func_146178_a();
        }
    }

    protected void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.inputField != null) {
            this.inputField.func_146201_a(c, i);
        }
    }

    protected void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (this.inputField != null) {
            this.inputField.func_146192_a(i, j, k);
        }
    }

    public void sklLrn(int i, int x, int y) {
        DBCGuiButtons01 btn = new DBCGuiButtons01(JRMCoreH.SklLvl(i, (byte)1) < 1 ? 1000 + i : -1, x, y, 83, 20, JRMCoreH.trl("dbc", JRMCoreH.DBCSkillNames[i]));
        btn.field_146124_l = JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? false : (JRMCoreH.getDBCSkillTPCost(i, 0) <= JRMCoreH.curTP ? JRMCoreH.SklLvl(i) < 1 : false);
        this.field_146292_n.add(btn);
        this.mc.field_71466_p.func_78276_b(JRMCoreH.SklLvl(i, (byte)1) < 1 ? (JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(i, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned"), x + 120, y + 7, 0);
    }

    public void sklLrn(int i, int x, int y, boolean b) {
        DBCGuiButtons01 btn = new DBCGuiButtons01(JRMCoreH.SklLvl(i, (byte)1) < 1 ? 1000 + i : -1, x, y, 83, 20, JRMCoreH.trl("dbc", JRMCoreH.DBCSkillNames[i]));
        btn.field_146124_l = JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? false : (JRMCoreH.getDBCSkillTPCost(i, 0) <= JRMCoreH.curTP && JRMCoreH.SklLvl(i) < 1 ? b : false);
        this.field_146292_n.add(btn);
        this.mc.field_71466_p.func_78276_b(JRMCoreH.SklLvl(i) < 1 ? (JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(i, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned"), x + 120, y + 7, 0);
    }

    public void func_73863_a(int x, int y, float f) {
        String en;
        ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.mc.field_71466_p;
        this.field_146292_n.clear();
        ++this.tick;
        if (this.tick >= 20) {
            this.tick = 0;
            JRMCoreH.jrmct(1);
            JRMCoreH.jrmct(3);
        }
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        int xSize = 256;
        int ySize = 160;
        int guiLeft = (this.field_146294_l - xSize) / 2;
        int guiTop = (this.field_146295_m - ySize) / 2;
        boolean cont = true;
        if (this.MsnSysTalkTo != null && (en = EntityList.func_75620_a((String)this.MsnSysTalkTo.get("N"), null).func_70005_c_()).equalsIgnoreCase(this.CurMaster)) {
            cont = false;
            String wish = "jinryuudragonbc:saa.png";
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ResourceLocation tx = new ResourceLocation(wish);
            this.mc.field_71446_o.func_110577_a(tx);
            this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
            int nr = 0;
            this.Process = en;
            nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            boolean t = Boolean.parseBoolean(this.MsnSysTalkTo.get("translated"));
            this.Process = JRMCoreH.trl(this.MsnSysTalkTo.get("G"));
            nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            this.Process = JRMCoreH.trl(this.MsnSysTalkTo.get("B"));
            int pw = var8.func_78256_a(this.Process) + 8;
            this.field_146292_n.add(new JRMCoreGuiButtons00MS(6000, guiLeft + xSize - 6 - pw, guiTop + ySize - 5 - 20, pw, 20, this.Process, 0, this.MsnSysTalkTo.get("series"), "-3"));
            this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
        }
        if (cont) {
            int nw;
            int onw;
            int fnw;
            int owner;
            int i;
            int nms;
            int i2;
            boolean gf;
            int nw2;
            int i3;
            int ml;
            int nw3;
            int line;
            ResourceLocation tx;
            String n;
            int i22;
            int i4;
            if (this.wish == 10) {
                xSize = 182;
                ySize = 191;
                guiLeft = (this.field_146294_l - xSize) / 2;
                guiTop = (this.field_146295_m - ySize) / 2;
                this.drawBg("enma", xSize, ySize);
                int time = (int)(System.currentTimeMillis() / 1000L);
                if (this.updateTime < time) {
                    this.updateTime = time + 5;
                    DBCH.packDuo(-1, 0);
                }
                i4 = 0;
                i22 = 0;
                int a = JRMCoreH.Algnmnt(JRMCoreH.align);
                i22 = JRMCoreH.txt(JRMCoreH.trl("dbc.talkgui.enmawelcome" + (a == 0 ? "G" : (a == 1 ? "N" : "E"))), JRMCoreH.clb, 0, true, guiLeft + 5, guiTop + 5 + i4 * 10, 175);
                i4 += i22;
                if (DBCConfig.Reinc > 0.0f) {
                    n = JRMCoreH.trl("dbc.talkgui.reincarnate");
                    int nw4 = var8.func_78256_a(n);
                    this.field_146292_n.add(new DBCGuiButtons01(99, guiLeft + xSize / 2 - nw4 / 2 - 5, guiTop + 5 + i4 * 21 - i22 * 10, nw4 + 10, 20, n));
                    ++i4;
                }
                if (JRMCoreH.revTmr > 0 && DBCConfig.FreeRev) {
                    int rt = (int)JRMCoreH.gkap(JRMCoreH.revTmr - 5, "rt", 5, 0.025);
                    int rt1 = rt / 1 % 60;
                    int rt2 = rt / 60 % 60;
                    int rt3 = rt / 3600 % 24;
                    int rt4 = rt / 86400;
                    n = JRMCoreH.trl("dbc.talkgui.revivetime", new Object[]{JRMCoreH.format_lz2(rt1), JRMCoreH.format_lz2(rt2), JRMCoreH.format_lz2(rt3), JRMCoreH.format_lz2(rt4)});
                    int nw5 = var8.func_78256_a(n);
                    var8.func_78276_b(n, guiLeft + xSize / 2 - nw5 / 2, guiTop + 12 + i4 * 21 - i22 * 10, 0);
                    n = JRMCoreH.trl("dbc.talkgui.revfree");
                    nw5 = var8.func_78256_a(n);
                    DBCGuiButtons01 b = new DBCGuiButtons01(-1, posX - nw5 / 2 - 5, guiTop + 5 + ++i4 * 21 - i22 * 10, nw5 + 10, 20, n);
                    b.field_146124_l = false;
                    this.field_146292_n.add(b);
                    ++i4;
                } else if (JRMCoreH.revTmr == 0) {
                    n = JRMCoreH.trl("dbc.talkgui.revfree");
                    int nw6 = var8.func_78256_a(n);
                    this.field_146292_n.add(new DBCGuiButtons01(100, posX - nw6 / 2 - 5, guiTop + 5 + i4 * 21 - i22 * 10, nw6 + 10, 20, n));
                    ++i4;
                }
                n = JRMCoreH.trl("dbc.talkgui.stay");
                int nw7 = var8.func_78256_a(n);
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - nw7 / 2 - 5, guiTop + 5 + i4 * 21 - i22 * 10, nw7 + 10, 20, n));
                ++i4;
            } else if (this.wish == 1001) {
                xSize = 182;
                ySize = 191;
                guiLeft = (this.field_146294_l - xSize) / 2;
                guiTop = (this.field_146295_m - ySize) / 2;
                this.drawBg("enma", xSize, ySize);
                int time = (int)(System.currentTimeMillis() / 1000L);
                if (this.updateTime < time && !this.updateTimerStopper) {
                    this.updateTime = time + 10;
                }
                this.updateTimer = this.updateTime - time;
                if (this.updateTimer <= 0 && !this.updateTimerStopper) {
                    this.updateTimerStopper = true;
                }
                i4 = 0;
                i22 = 0;
                i22 = JRMCoreH.txt(JRMCoreH.trl("dbc.talkgui.enmareincarnate", new Object[]{(int)(DBCConfig.Reinc * 100.0f) + "%"}), JRMCoreH.clb, 0, true, guiLeft + 5, guiTop + 5 + i4 * 10, 175);
                this.field_146292_n.add(new DBCGuiButtons01(99, guiLeft + xSize / 2 - 40 - 5, guiTop + 10 + (i4 += i22) * 21 - i22 * 10, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                ++i4;
                if (this.updateTimer <= 0) {
                    this.field_146292_n.add(new DBCGuiButtons01(101, guiLeft + xSize / 2 - 40 - 5, guiTop + 10 + i4 * 21 - i22 * 10, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
                    ++i4;
                } else {
                    n = JRMCoreH.trl("dbc.talkgui.enmareincavilabl", new Object[]{JRMCoreH.format_lz2(this.updateTimer % 60)});
                    int nw8 = var8.func_78256_a(n);
                    var8.func_78276_b(n, guiLeft + xSize / 2 - nw8 / 2, guiTop + 10 + i4 * 21 - i22 * 10 + 5, 0);
                    ++i4;
                }
            } else if (this.wish == 9000) {
                this.master = 16;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            }
            if (this.wish == 9001) {
                this.master = 17;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            }
            if (this.wish == 9002) {
                this.master = 18;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            }
            if (this.wish == 11) {
                this.master = 5;
                int line2 = 0;
                if (JRMCoreH.SagaProg == 1700) {
                    this.field_146292_n.add(new DBCGuiButtons01(203, posX - 35, posY - 5, 100, 20, StatCollector.func_74838_a((String)"dbc.MainSaga.17.1")));
                } else if (JRMCoreH.SagaSideProg == 10100) {
                    this.field_146292_n.add(new DBCGuiButtons01(204, posX - 35, posY - 5, 100, 20, StatCollector.func_74838_a((String)"dbc.sidesagas.101.talkok")));
                } else {
                    if (!(JRMCoreH.Race != 1 && JRMCoreH.Race != 2 || JRMCoreH.TlMd != 3 && JRMCoreH.TlMd != 4)) {
                        n = JRMCoreH.trl("dbc.talkgui.kami.forcetailregrow");
                        int nw9 = this.field_146289_q.func_78256_a(n) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(108, posX + 115 - nw9, posY + 15 + line2 * 21, nw9, 20, n));
                        ++line2;
                    }
                    if (!(JRMCoreH.Race != 1 && JRMCoreH.Race != 2 || JRMCoreH.TlMd != -1 && JRMCoreH.TlMd != 0 && JRMCoreH.TlMd != 1)) {
                        n = JRMCoreH.trl("dbc.talkgui.kami.cutdowntail");
                        int nw10 = this.field_146289_q.func_78256_a(n) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(107, posX + 115 - nw10, posY + 15 + line2 * 21, nw10, 20, n));
                        ++line2;
                    }
                    n = JRMCoreH.trl("dbc.talkgui.anew");
                    int nw11 = this.field_146289_q.func_78256_a(n) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(2011, posX + 115 - nw11, posY + 15 + line2 * 21, nw11, 20, n));
                    n = JRMCoreH.trl("dbc.talkgui.skills");
                    nw11 = this.field_146289_q.func_78256_a(n) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - nw11, posY + 15 + ++line2 * 21, nw11, 20, n));
                    ++line2;
                    line2 = 0;
                    n = JRMCoreH.trl("dbc.talkgui.giveweights");
                    nw11 = this.field_146289_q.func_78256_a(n) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line2 * 21, nw11, 20, n));
                    ++line2;
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 1101) {
                this.field_146292_n.add(new DBCGuiButtons01(2012, posX - 35, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                this.field_146292_n.add(new DBCGuiButtons01(201, posX - 120, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
            } else if (this.wish == 1102) {
                this.field_146292_n.add(new DBCGuiButtons01(2012, posX - 35, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                this.field_146292_n.add(new DBCGuiButtons01(106, posX - 120, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
            } else if (this.wish == 1104) {
                this.field_146292_n.add(new DBCGuiButtons01(2012, posX - 35, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                this.field_146292_n.add(new DBCGuiButtons01(109, posX - 120, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
            } else if (this.wish == 1105) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.giveweightsdesc");
                nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                line = 0;
                String n2 = JRMCoreH.trl("dbc.talkgui.weightamount");
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                ++this.text;
                if (this.text == 1) {
                    this.name(var8, posX - 220 + nw3, posY - 25 + line * 21);
                    this.inputField.func_146180_a("5");
                } else {
                    this.text = 2;
                }
                if (this.inputField != null) {
                    this.inputField.func_146194_f();
                    this.name = this.inputField.func_146179_b();
                    var8.func_78276_b(n2, posX - 120, posY - 7 + line * 21, 0);
                }
                ++line;
                n2 = ItemsDBC.ItemWeightHandLeg.func_77653_i(new ItemStack(ItemsDBC.ItemWeightHandLeg));
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                if (this.master == 6) {
                    this.field_146292_n.add(new DBCGuiButtons01(210, posX - 120, posY - 15 + line * 21, nw3, 20, n2));
                    ++line;
                }
                n2 = ItemsDBC.ItemWeightShell.func_77653_i(new ItemStack(ItemsDBC.ItemWeightShell));
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                if (this.master == 2) {
                    this.field_146292_n.add(new DBCGuiButtons01(211, posX - 120, posY - 15 + line * 21, nw3, 20, n2));
                    ++line;
                }
                n2 = ItemsDBC.ItemWeightShirt.func_77653_i(new ItemStack(ItemsDBC.ItemWeightShirt));
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                if (this.master == 6 || this.master == 5) {
                    this.field_146292_n.add(new DBCGuiButtons01(212, posX - 120, posY - 15 + line * 21, nw3, 20, n2));
                    ++line;
                }
                n2 = ItemsDBC.ItemWeightCape.func_77653_i(new ItemStack(ItemsDBC.ItemWeightCape));
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                if (this.master == 8) {
                    this.field_146292_n.add(new DBCGuiButtons01(213, posX - 120, posY - 15 + line * 21, nw3, 20, n2));
                    ++line;
                }
                n2 = ItemsDBC.ItemWeightHeavySuit.func_77653_i(new ItemStack(ItemsDBC.ItemWeightHeavySuit));
                nw3 = this.field_146289_q.func_78256_a(n2) + 8;
                if (this.master == 18) {
                    this.field_146292_n.add(new DBCGuiButtons01(214, posX - 120, posY - 15 + line * 21, nw3, 20, n2));
                    ++line;
                }
            } else if (this.wish == 111) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(7, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 9000) {
                this.drawBg("saa", xSize, ySize);
                this.master = 17;
                boolean line3 = false;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    String n3 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw2 = this.field_146289_q.func_78256_a(n3) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 100, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n3));
                    ++line2;
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 9100) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                gf = DBCConfig.Godform && JRMCoreHDBC.godKiAble();
                this.sklLrn(9, guiLeft + 5, guiTop + 25 + i3 * 21, gf);
                ++i3;
                if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
                    this.sklLrn(16, guiLeft + 5, guiTop + 25 + i3 * 21);
                    ++i3;
                }
            } else if (this.wish == 9001) {
                this.drawBg("saa", xSize, ySize);
                this.master = 17;
                boolean line4 = false;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    String n4 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw2 = this.field_146289_q.func_78256_a(n4) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 100, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n4));
                    ++line2;
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 9101) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + i3 * 21);
                ++i3;
            } else if (this.wish == 9002) {
                this.drawBg("saa", xSize, ySize);
                this.master = 18;
                int line5 = 0;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    String n5 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw2 = this.field_146289_q.func_78256_a(n5) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 100, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n5));
                    ++line2;
                    if (JRMCoreH.align > 33) {
                        n5 = JRMCoreH.trl("dbc.talkgui.giveweights");
                        nw2 = this.field_146289_q.func_78256_a(n5) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115, posY + 15 + line5 * 21, nw2, 20, n5));
                        ++line5;
                    }
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                int ln = 0;
                String n6 = JRMCoreH.trl("dbc", "whisteleport");
                nw2 = this.field_146289_q.func_78256_a(n6) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(52, posX - nw2 / 2, posY + 20 + ++ln * 20, nw2, 20, n6));
                ++ln;
            } else if (this.wish == 9102) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                gf = DBCConfig.Godform && JRMCoreHDBC.godKiAble();
                this.sklLrn(9, guiLeft + 5, guiTop + 25 + i3 * 21, gf);
                ++i3;
                if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
                    this.sklLrn(16, guiLeft + 5, guiTop + 25 + i3 * 21);
                    ++i3;
                }
                this.sklLrn(18, guiLeft + 5, guiTop + 25 + i3 * 21);
                ++i3;
            } else if (this.wish == 12) {
                this.drawBg("saa", xSize, ySize);
                this.master = 6;
                int line6 = 0;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    if (JRMCoreH.Master == 1) {
                        String n7 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw2 = this.field_146289_q.func_78256_a(n7) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n7));
                        ++line2;
                    }
                    String n8 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw2 = this.field_146289_q.func_78256_a(n8) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n8));
                    ++line2;
                }
                String n9 = JRMCoreH.trl("dbc.talkgui.difftonormal");
                nw2 = this.field_146289_q.func_78256_a(n9) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(103, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n9));
                ++line2;
                if (JRMCoreH.align > 33) {
                    n9 = JRMCoreH.trl("dbc.talkgui.giveweights");
                    nw2 = this.field_146289_q.func_78256_a(n9) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line6 * 21, nw2, 20, n9));
                    ++line6;
                }
                if (JRMCoreH.StusEfctsMe(12)) {
                    n9 = JRMCoreH.trl("dbc", "majinLoose");
                    nw2 = this.field_146289_q.func_78256_a(n9) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(50, posX + 115 - nw2, posY + 15 + line2 * 21, nw2, 20, n9));
                    ++line2;
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 112) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                gf = DBCConfig.Godform && JRMCoreHDBC.godKiAble();
                String n10 = JRMCoreH.trl("dbc", "PURitual");
                int nw12 = this.field_146289_q.func_78256_a(n10) + 8;
                DBCGuiButtons01 btn = new DBCGuiButtons01(JRMCoreH.SklLvl(10, (byte)1) < 1 ? 1010 : -1, guiLeft + 5, guiTop + 25 + i3 * 21, 83, 20, n10);
                btn.field_146124_l = JRMCoreH.align == 100 && JRMCoreH.SklLvl(10) == 0 && JRMCoreH.getDBCSkillTPCost(10, 0) != -1 && JRMCoreH.getDBCSkillTPCost(10, 0) <= JRMCoreH.curTP;
                this.field_146292_n.add(btn);
                this.mc.field_71466_p.func_78276_b(JRMCoreH.align < 100 ? JRMCoreH.trl("jrmc", "NeedToBeGood", 100) : (JRMCoreH.SklLvl(10) < 1 ? (JRMCoreH.getDBCSkillTPCost(10, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(10, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i3], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned")), guiLeft + 5 + 120, guiTop + 25 + 7 + i3 * 21, 0);
                this.sklLrn(8, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(13, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1121) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 1 && owner != 6) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n11 = " " + cost + " tp";
                            int nw13 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw13, n11, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n11, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 221) {
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                Iterator iterator = this.mc.field_71466_p.func_78271_c(JRMCoreH.trl("dbc", "KaioDiffRed"), 245).iterator();
                i2 = 0;
                while (iterator.hasNext()) {
                    String s1 = (String)iterator.next();
                    var8.func_78276_b("\u00a70" + s1, guiLeft + 5, guiTop + 5 + ++i2 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(104, posX - 35, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                this.field_146292_n.add(new DBCGuiButtons01(105, posX - 120, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
            } else if (this.wish == 13) {
                this.master = 4;
                if (JRMCoreH.align > 65) {
                    if (JRMCoreH.Senzu == 0) {
                        this.field_146292_n.add(new DBCGuiButtons01(199, posX - 35, posY + 35, 100, 20, StatCollector.func_74838_a((String)"dbc.talkgui.karin.senzu")));
                    }
                    this.field_146292_n.add(new DBCGuiButtons01(198, posX - 35, posY + 55, 100, 20, StatCollector.func_74838_a((String)"dbc.talkgui.skills")));
                    if (!this.mc.field_71439_g.field_71071_by.func_146028_b(ItemsDBC.KintounItem)) {
                        this.field_146292_n.add(new DBCGuiButtons01(202, posX - 35, posY + 15, 100, 20, StatCollector.func_74838_a((String)"dbc.talkgui.kintoun")));
                    }
                } else if (!this.mc.field_71439_g.field_71071_by.func_146028_b(ItemsDBC.KintounBlackItem)) {
                    this.field_146292_n.add(new DBCGuiButtons01(202, posX - 35, posY + 15, 140, 20, StatCollector.func_74838_a((String)"dbc.talkgui.kintounblack")));
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 113) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 15) {
                this.master = 2;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[2]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.roshi.goodneut");
                nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                line = 0;
                if (JRMCoreH.align > 32) {
                    if (JRMCoreH.Master == 1) {
                        String n12 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw3 = this.field_146289_q.func_78256_a(n12) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - nw3, posY + 15 + line * 21, nw3, 20, n12));
                        ++line;
                    }
                    String n13 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw3 = this.field_146289_q.func_78256_a(n13) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - nw3, posY + 15 + line * 21, nw3, 20, n13));
                    ++line;
                }
                line = 0;
                String n14 = JRMCoreH.trl("dbc.talkgui.giveweights");
                nw3 = this.field_146289_q.func_78256_a(n14) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line * 21, nw3, 20, n14));
                ++line;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 115) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1151) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    if (Integer.parseInt(PMA[i][2]) != 2 && Integer.parseInt(PMA[i][2]) != 1) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        int fnw2 = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        int onw2 = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw2, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n15 = " " + cost + " tp";
                            int nw14 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw2 + onw2, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw14, n15, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n15, guiLeft + xSize / 2 - 122 + fnw2 + onw2, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 16) {
                this.master = 12;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[12]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align <= 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.cell.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n16 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n16) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n16));
                    }
                    String n17 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n17) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n17));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.cell.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 116) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(17, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1161) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 2 && owner != 1 && owner != 7 && owner != 8 && owner != 9 && owner != 11) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n18 = " " + cost + " tp";
                            int nw15 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw15, n18, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n18, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 17) {
                this.master = 13;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[13]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songoku.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n19 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n19) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n19));
                    }
                    String n20 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n20) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n20));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songoku.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 117) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml2 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml2), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i4 = 0;
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + i4 * 21);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(0, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(14, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(17, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                ++i4;
            } else if (this.wish == 1171) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 2 && owner != 1 && owner != 6 && owner != 7) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n21 = " " + cost + " tp";
                            int nw16 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw16, n21, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n21, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 18) {
                this.master = 11;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[11]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align <= 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.freeza.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n22 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n22) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n22));
                    }
                    String n23 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n23) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n23));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.freeza.else");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 118) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1181) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 1 && owner != 11) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n24 = " " + cost + " tp";
                            int nw17 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw17, n24, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n24, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 19) {
                this.master = 15;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[15]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                int ln = 0;
                if (JRMCoreH.align <= 65) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.evilneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n25 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw3 = this.field_146289_q.func_78256_a(n25) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw3 / 2, posY + 15 + ln * 20, nw3, 20, n25));
                        ++ln;
                    }
                    String n26 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw3 = this.field_146289_q.func_78256_a(n26) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw3 / 2, posY + 15 + ln * 20, nw3, 20, n26));
                    ++ln;
                    if (!JRMCoreH.StusEfctsMe(12)) {
                        n26 = JRMCoreH.trl("dbc", "majinGet");
                        nw3 = this.field_146289_q.func_78256_a(n26) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(51, posX + 15 - nw3 / 2, posY + 15 + ln * 20, nw3, 20, n26));
                        ++ln;
                    }
                } else if (JRMCoreH.align == 100) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.fullgood");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.good");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (!JRMCoreH.StusEfctsMe(12)) {
                        String n27 = JRMCoreH.trl("dbc", "majinGet");
                        nw3 = this.field_146289_q.func_78256_a(n27) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(51, posX + 15 - nw3 / 2, posY + 15 + ln * 20, nw3, 20, n27));
                        ++ln;
                    }
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 119) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1191) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 1) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n28 = " " + cost + " tp";
                            int nw18 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw18, n28, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n28, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 20) {
                this.master = 8;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[this.master]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                line = 0;
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.piccolo.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n29 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw3 = this.field_146289_q.func_78256_a(n29) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - nw3, posY + 15 + line * 21, nw3, 20, n29));
                        ++line;
                    }
                    String n30 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw3 = this.field_146289_q.func_78256_a(n30) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - nw3, posY + 15 + line * 21, nw3, 20, n30));
                    ++line;
                    line = 0;
                    n30 = JRMCoreH.trl("dbc.talkgui.giveweights");
                    nw3 = this.field_146289_q.func_78256_a(n30) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line * 21, nw3, 20, n30));
                    ++line;
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.piccolo.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 120) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(7, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1201) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 1 && owner != 7 && owner != 8) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n31 = " " + cost + " tp";
                            int nw19 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw19, n31, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n31, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 21) {
                this.master = 9;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[this.master]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.vegeta.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n32 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n32) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n32));
                    }
                    String n33 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n33) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n33));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.vegeta.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 121) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + i3 * 21);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i3 * 21);
                ++i3;
            } else if (this.wish == 1211) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 1 && owner != 9) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n34 = " " + cost + " tp";
                            int nw20 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw20, n34, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n34, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 22) {
                this.master = 14;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[14]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n35 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n35) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n35));
                    }
                    String n36 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n36) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n36));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 122) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml3 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml3), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i4 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i4 * 21);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(15, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                ++i4;
            } else if (this.wish == 1221) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 2 && owner != 1 && owner != 8 && owner != 14) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n37 = " " + cost + " tp";
                            int nw21 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw21, n37, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n37, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 23) {
                this.master = 10;
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[10]);
                nr += JRMCoreH.txt(this.Process, "\u00a78", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.goodneut");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String n38 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        nw = this.field_146289_q.func_78256_a(n38) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - nw / 2, posY + 35, nw, 20, n38));
                    }
                    String n39 = JRMCoreH.trl("dbc.talkgui.skills");
                    nw = this.field_146289_q.func_78256_a(n39) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw / 2, posY + 55, nw, 20, n39));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.evil");
                    nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 123) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml4 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml4), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i4 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i4 * 21);
                this.sklLrn(14, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                this.sklLrn(15, guiLeft + 5, guiTop + 25 + ++i4 * 21);
                ++i4;
            } else if (this.wish == 1231) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (i = 0; i < nms; i = (int)((byte)(i + 1))) {
                    owner = Integer.parseInt(PMA[i][2]);
                    if (owner != 2 && owner != 1 && owner != 14 && owner != 10) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        String fn = JRMCoreH.trl("dbc", PMA[i][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        String on = JRMCoreH.techOwnd(i, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i, (byte)1)) {
                            int costTp;
                            int cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i]) * 0.9f);
                            String n40 = " " + cost + " tp";
                            int nw22 = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i, (byte)1) ? 4200 + i : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw22, n40, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n40, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            }
            if (this.wish == 9000) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx2 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx2);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.line1000"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.line1001");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1002");
                String s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line1003");
                if (JRMCoreH.align > 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1004");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1005");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line1006");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1007");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1008");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line1009");
                }
                this.current(s, 15, 20, var8, guiLeft, guiTop);
                this.current(s2, 15, 30, var8, guiLeft, guiTop);
                this.current(s3, 15, 40, var8, guiLeft, guiTop);
            } else if (this.wish == 9001) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx3 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx3);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.line1010"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.line1011");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1012");
                if (JRMCoreH.align > 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1013");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1014");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1015");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1016");
                }
                this.current(s, 15, 20, var8, guiLeft, guiTop);
                this.current(s2, 15, 30, var8, guiLeft, guiTop);
            } else if (this.wish == 9002) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx4 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx4);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.line1017"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.line1018");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1019");
                if (JRMCoreH.align > 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1020");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1021");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line1022");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line1023");
                }
                this.current(s, 15, 20, var8, guiLeft, guiTop);
                this.current(s2, 15, 30, var8, guiLeft, guiTop);
            } else if (this.wish == 11) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx5 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx5);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.line0000"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.line0001");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0002");
                String s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0003");
                if (JRMCoreH.align > 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line0004");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0005");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0006");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line0007");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0008");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0009");
                }
                if (JRMCoreH.SagaProg == 1700) {
                    s = StatCollector.func_74838_a((String)"dbc.MainSaga.17.2");
                    s2 = StatCollector.func_74838_a((String)"dbc.MainSaga.17.3");
                    s3 = StatCollector.func_74838_a((String)"dbc.MainSaga.17.4");
                }
                if (JRMCoreH.SagaSideProg == 10100) {
                    s = StatCollector.func_74838_a((String)"dbc.sidesagas.101.talk");
                    int n41 = JRMCoreH.txt(s, "\u00a70", 5, true, guiLeft + 15, guiTop + 20, 0);
                } else {
                    this.current(s, 15, 20, var8, guiLeft, guiTop);
                    this.current(s2, 15, 30, var8, guiLeft, guiTop);
                    this.current(s3, 15, 40, var8, guiLeft, guiTop);
                }
            } else if (this.wish == 1101) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx6 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx6);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.kami.startanew");
                nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            } else if (this.wish == 1102) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx7 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx7);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.kami.confirmtailcut");
                nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            } else if (this.wish == 1104) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx8 = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx8);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr = 0;
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.kami.tailregrow");
                nr += JRMCoreH.txt(this.Process, "\u00a70", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            } else if (this.wish == 12) {
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.line0010"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.line0011");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0012");
                String s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0013");
                if (JRMCoreH.align > 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line0014");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0015");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0016");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.line0017");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.line0018");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.line0019");
                }
                this.current(s, 15, 20, var8, guiLeft, guiTop);
                this.current(s2, 15, 30, var8, guiLeft, guiTop);
                this.current(s3, 15, 40, var8, guiLeft, guiTop);
            } else if (this.wish == 13) {
                String wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx9 = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx9);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                this.current(StatCollector.func_74838_a((String)"dbc.talkgui.karin.00"), 10, 10, var8, guiLeft, guiTop);
                String s = StatCollector.func_74838_a((String)"dbc.talkgui.karin.01");
                String s2 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.02");
                String s3 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.03");
                if (JRMCoreH.align < 66) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.karin.04");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.05");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.06");
                }
                if (JRMCoreH.align < 33) {
                    s = StatCollector.func_74838_a((String)"dbc.talkgui.karin.07");
                    s2 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.08");
                    s3 = StatCollector.func_74838_a((String)"dbc.talkgui.karin.09");
                }
                if (JRMCoreH.Senzu == 1) {
                    this.current(StatCollector.func_74838_a((String)"dbc.talkgui.karin.nosenzu"), 25, 80, var8, guiLeft, guiTop);
                }
                this.current(s, 15, 20, var8, guiLeft, guiTop);
                this.current(s2, 15, 30, var8, guiLeft, guiTop);
                this.current(s3, 15, 40, var8, guiLeft, guiTop);
            }
        }
        super.func_73863_a(x, y, f);
    }

    private void drawBg(String name) {
        this.drawBg(name, 256, 160);
    }

    private void drawBg(String name, int xSize, int ySize) {
        String wish = "jinryuudragonbc:" + name + ".png";
        int guiLeft = (this.field_146294_l - xSize) / 2;
        int guiTop = (this.field_146295_m - ySize) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(wish);
        this.mc.field_71446_o.func_110577_a(tx);
        this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    public void current(String var35, int posx, int posy, FontRenderer var8, int var6, int var7) {
        int wid = var8.func_78256_a(var35) / 2;
        int posX = var6 + posx;
        int posY = var7 + posy;
        var8.func_78276_b(var35, posX, posY, 0);
    }

    public boolean func_73868_f() {
        return false;
    }

    public void SagasPrint() {
        this.func_73866_w_();
        Minecraft minecraft = this.mc;
        WorldClient world = minecraft.field_71441_e;
        EntityClientPlayerMP entityplayersp = minecraft.field_71439_g;
        ScaledResolution scaledresolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
        int width = scaledresolution.func_78326_a() / 2;
        int height = scaledresolution.func_78328_b() / 2;
        int widthplus = 8;
        minecraft.field_71460_t.func_78478_c();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        RenderHelper.func_74519_b();
        RenderHelper.func_74518_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73732_a(this.fontRenderer, this.Process, width + this.wid, height + this.hei, 16768306);
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
    }

    public void SagasBack(int var6, int var7) {
        int width = var6;
        int height = var7;
        int xSize = 182;
        int ySize = 191;
        int guiLeft = (width - xSize) / 2;
        int guiTop = (height - ySize) / 2;
        String var4 = "jinryuudragonbc:sagas.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(var4);
        this.mc.field_71446_o.func_110577_a(tx);
        this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}

