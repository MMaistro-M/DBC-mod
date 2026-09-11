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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.gui;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.DragonBC.common.Gui.DBCGuiButtons01;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiButtons00MS;
import JinRyuu.JRMCore.JRMCoreGuiButtons01;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA1;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.DBC.DBCPwish;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUPacketLearnSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPacketWish;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class DBCATalk
extends GuiScreen {
    private Minecraft mc = JRMCoreClient.mc;
    private FontRenderer fontRenderer;
    private int tick;
    int POTARA_WISH;
    private GuiIngame Guiingame;
    private int wish;
    private int ipg;
    private int lp;
    private int master;
    private HashMap<String, String> MsnSysTalkTo;
    private String CurMaster;
    private String name;
    private int text;
    private int scroll;
    protected GuiTextField inputField;
    private String defaultInputFieldText;
    private int updateTimer;
    private int updateTime;
    private boolean updateTimerStopper;
    public static final int GOHAN = 22;
    public static final int GOHAN_SKILLS = 122;
    public static final int GOHAN_ATTACKS = 1221;
    public static final int GOKU = 17;
    public static final int GOKU_SKILLS = 117;
    public static final int GOKU_ATTACKS = 1171;
    public static final int PICCOLO = 20;
    public static final int PICCOLO_SKILLS = 120;
    public static final int PICCOLO_ATTACKS = 1201;
    public static final int FREEZA = 18;
    public static final int FREEZA_SKILLS = 118;
    public static final int FREEZA_ATTACKS = 1181;
    public static final int VEGETA = 21;
    public static final int VEGETA_SKILLS = 121;
    public static final int VEGETA_ATTACKS = 1211;
    public static final int TRUNKS = 23;
    public static final int TRUNKS_SKILLS = 123;
    public static final int TRUNKS_ATTACKS = 1231;
    public static final int WHIS = 9002;
    public static final int WHIS_SKILLS = 9102;
    public static final int WHIS_TELEPORT_BP = 778;
    public static final int WHIS_TELEPORT_UT = 779;
    public static final int KAIO = 12;
    public static final int KAIO_SKILLS = 112;
    public static final int KAIO_TECHNIQUES = 1121;
    public static final int WEIGHTS = 1105;
    public static final int DIFFICULTY = 221;
    public static final int OLD_KAI = 24;
    public static final int OLD_KAI_SKILLS = 124;
    public static final int OLD_KAI_POTARA = 1241;
    public static final int BEERUS = 25;
    public static final int BEERUS_SKILLS = 125;
    public static final int KIBITO = 200;
    public static final int KIBITO_TELEPORT = 777;
    public static final int ROSHI = 15;
    public static final int ROSHI_SKILLS = 115;
    public static final int ROSHI_ATTACKS = 1151;
    public static final int BABIDI = 19;
    public static final int BABIDI_SKILLS = 119;
    public static final int BABIDI_ATTACKS = 1191;
    public static final int CELL = 16;
    public static final int CELL_SKILLS = 116;
    public static final int CELL_ATTACKS = 1161;
    public static int count = 0;
    public static int warn = 0;
    public static int startcount = 0;
    private String Process;
    private int wid;
    private int hei;
    private String textureFile;

    public void func_73866_w_() {
    }

    public DBCATalk(int w, World wld, int x, int y, int z) {
        this.fontRenderer = this.mc.field_71466_p;
        this.POTARA_WISH = 77831;
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
        double n = 0.5;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((double)x - n), (double)((double)y - n), (double)((double)z - n), (double)((double)x + n), (double)((double)y + n), (double)((double)z + n));
        List l = wld.func_72872_a(EntitySafeZone.class, aabb);
        Iterator it = l.iterator();
        if (it.hasNext()) {
            EntitySafeZone k = (EntitySafeZone)((Object)it.next());
            this.CurMaster = k.func_70005_c_();
        }
        this.wish = w;
        this.updateTime = 0;
        JRMCoreH.revTmr = -1;
        JRMCoreH.Master = 1;
        this.MsnSysTalkTo = JRMCoreM.getMda_Obj_TalkTo();
    }

    public void func_146284_a(GuiButton button) {
        int mind;
        int id;
        DBCASkill skill;
        int j;
        if (button.field_146127_k >= this.POTARA_WISH + 1 && button.field_146127_k <= this.POTARA_WISH + 6) {
            DBUPackets.sendToServer(new DBUPacketWish((byte)(button.field_146127_k - this.POTARA_WISH + 3)));
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 1241) {
            this.wish = 1241;
        }
        if (button.field_146127_k == -1) {
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 0) {
            this.dbcWish(button.field_146127_k);
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 43 && this.scroll < 0) {
            this.scroll += 21;
        }
        if (button.field_146127_k == 44) {
            this.scroll -= 21;
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
        for (j = 0; j < 15; ++j) {
            if (button.field_146127_k == 10111 + j) {
                this.wish = 111 + j;
            }
            if (button.field_146127_k != 101111 + j * 10) continue;
            this.wish = 1111 + j * 10;
        }
        if (button.field_146127_k == 777) {
            byte b = 2;
            if (this.mc.field_71439_g.field_71093_bK == 0) {
                b = 0;
            }
            DBUPackets.sendToServer(new DBUPacketWish(b));
            this.mc.field_71439_g.func_71053_j();
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
        for (j = 0; j < JRMCoreH.DBCSkillsIDs.length; ++j) {
            if (button.field_146127_k != 1000 + j) continue;
            JRMCoreH.Skll((byte)1, (byte)j);
        }
        for (int i = 0; i < (JRMCoreH.Pwrtyp == 2 ? JRMCoreH.pmj : JRMCoreH.pmdbc).length; i = (int)((byte)(i + 1))) {
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
        if (button.field_146127_k == 778) {
            byte b = 2;
            if (this.mc.field_71439_g.field_71093_bK == 0) {
                b = 1;
            }
            DBUPackets.sendToServer(new DBUPacketWish(b));
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 779) {
            byte b = 2;
            if (this.mc.field_71439_g.field_71093_bK == 0) {
                b = 3;
            }
            DBUPackets.sendToServer(new DBUPacketWish(b));
            this.mc.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k >= 77777 && (skill = DBCASkills.getSkill(id = button.field_146127_k - 77777)) != null && (mind = JRMCoreH.skillSlot_AvailableMindLeft()) - skill.getMindCost() >= 0) {
            DBUPackets.sendToServer(new DBUPacketLearnSkill(id));
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

    public boolean customSklLrn(int id, int x, int y, int guiTop) {
        byte dbcarace;
        if (y - guiTop < 25 || y - guiTop > 130) {
            return false;
        }
        DBCASkill skill = DBCASkills.getSkill(id);
        if (!skill.race(JRMCoreH.Race, dbcarace = DataUtils.getDBCARace((EntityPlayer)Minecraft.func_71410_x().field_71439_g))) {
            return false;
        }
        String name = StatCollector.func_74838_a((String)skill.getDisplayName());
        int level = -1;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)Minecraft.func_71410_x().field_71439_g))) {
            if (customSkill.getID() != id) continue;
            level = customSkill.getLevel();
        }
        DBCGuiButtons01 btn = new DBCGuiButtons01(level < 1 ? 77777 + skill.getID() : -1, x, y, 83, 20, name);
        btn.field_146124_l = skill.getTPCost() == -1 ? false : (skill.getTPCost() <= JRMCoreH.curTP ? level < 1 : false);
        this.field_146292_n.add(btn);
        this.mc.field_71466_p.func_78276_b(level < 1 ? (skill.getTPCost() == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + skill.getTPCost() + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + skill.getMindCost()) : JRMCoreH.trl("dbc.talkgui.owned"), x + 120, y + 7, 0);
        return true;
    }

    public boolean sklLrn(int i, int x, int y, int guiTop) {
        if (y - guiTop < 25 || y - guiTop > 130) {
            return false;
        }
        DBCGuiButtons01 btn = new DBCGuiButtons01(JRMCoreH.SklLvl(i, (byte)1) < 1 ? 1000 + i : -1, x, y, 83, 20, JRMCoreH.trl("dbc", JRMCoreH.DBCSkillNames[i]));
        btn.field_146124_l = JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? false : (JRMCoreH.getDBCSkillTPCost(i, 0) <= JRMCoreH.curTP ? JRMCoreH.SklLvl(i) < 1 : false);
        this.field_146292_n.add(btn);
        this.mc.field_71466_p.func_78276_b(JRMCoreH.SklLvl(i, (byte)1) < 1 ? (JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(i, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned"), x + 120, y + 7, 0);
        return true;
    }

    public boolean sklLrn(int i, int x, int y, boolean b, int guiTop) {
        if (y - guiTop < 25 || y - guiTop > 130) {
            return false;
        }
        DBCGuiButtons01 btn = new DBCGuiButtons01(JRMCoreH.SklLvl(i, (byte)1) < 1 ? 1000 + i : -1, x, y, 83, 20, JRMCoreH.trl("dbc", JRMCoreH.DBCSkillNames[i]));
        btn.field_146124_l = JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? false : (JRMCoreH.getDBCSkillTPCost(i, 0) <= JRMCoreH.curTP && JRMCoreH.SklLvl(i) < 1 ? b : false);
        this.field_146292_n.add(btn);
        this.mc.field_71466_p.func_78276_b(JRMCoreH.SklLvl(i) < 1 ? (JRMCoreH.getDBCSkillTPCost(i, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(i, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned"), x + 120, y + 7, 0);
        return true;
    }

    private void loadDialogs(int guiTop, int guiLeft) {
        if (this.wish == 25) {
            FontRenderer var8 = this.mc.field_71466_p;
            this.current(DBCAUtils.translate("talkgui.beerusName"), 10, 10, var8, guiLeft, guiTop);
            String s = DBCAUtils.translate("talkgui.beerusD1");
            String s2 = DBCAUtils.translate("talkgui.beerusD2");
            this.current(s, 15, 20, var8, guiLeft, guiTop);
            this.current(s2, 15, 30, var8, guiLeft, guiTop);
        } else if (this.wish == 9002) {
            FontRenderer var8 = this.mc.field_71466_p;
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
        } else if (this.wish == 12) {
            FontRenderer var8 = this.mc.field_71466_p;
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
        } else if (this.wish == 24) {
            FontRenderer var8 = this.mc.field_71466_p;
            this.current(DBCAUtils.translate("talkgui.oldkaiName"), 10, 10, var8, guiLeft, guiTop);
            String s = DBCAUtils.translate("talkgui.oldkaiD1");
            String s2 = DBCAUtils.translate("talkgui.oldkaiD2");
            String s3 = DBCAUtils.translate("talkgui.oldkaiD3");
            String s4 = DBCAUtils.translate("talkgui.oldkaiD4");
            if (JRMCoreH.align < 33) {
                s = DBCAUtils.translate("talkgui.oldkaiD5");
                s2 = "";
                s3 = "";
                s4 = "";
            }
            this.current(s, 15, 20, var8, guiLeft, guiTop);
            this.current(s2, 15, 30, var8, guiLeft, guiTop);
            this.current(s3, 15, 40, var8, guiLeft, guiTop);
            this.current(s4, 15, 50, var8, guiLeft, guiTop);
        } else if (this.wish == 1241) {
            FontRenderer var8 = this.mc.field_71466_p;
            this.current(DBCAUtils.translate("talkgui.oldkaiName"), 10, 10, var8, guiLeft, guiTop);
            String s = DBCAUtils.translate("talkgui.oldkaiPotaraD1");
            this.current(s, 15, 20, var8, guiLeft, guiTop);
        } else if (this.wish == 200) {
            FontRenderer var8 = this.mc.field_71466_p;
            this.current(DBCAUtils.translate("talkgui.kibitoName"), 10, 10, var8, guiLeft, guiTop);
            String s = DBCAUtils.translate("talkgui.kibitoD1");
            this.current(s, 15, 20, var8, guiLeft, guiTop);
        }
    }

    public void func_73863_a(int x, int y, float f) {
        int nr;
        ResourceLocation tx;
        String wish;
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
            wish = "jinryuudragonbc:saa.png";
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            tx = new ResourceLocation(wish);
            this.mc.field_71446_o.func_110577_a(tx);
            this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
            nr = 0;
            this.Process = en;
            nr += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            boolean t = Boolean.parseBoolean(this.MsnSysTalkTo.get("translated"));
            this.Process = JRMCoreH.trl(this.MsnSysTalkTo.get("G"));
            nr += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
            this.Process = JRMCoreH.trl(this.MsnSysTalkTo.get("B"));
            int pw = var8.func_78256_a(this.Process) + 8;
            this.field_146292_n.add(new JRMCoreGuiButtons00MS(6000, guiLeft + xSize - 6 - pw, guiTop + ySize - 5 - 20, pw, 20, this.Process, 0, this.MsnSysTalkTo.get("series"), "-3"));
            this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
        }
        if (cont) {
            int fnw;
            int nw;
            String n;
            int cost;
            int costTp;
            int onw;
            String on;
            String fn;
            int nms;
            int i2;
            int i;
            int ml;
            int m;
            int i3;
            byte dbcarace = DataUtils.getDBCARace((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            if (this.wish == 25) {
                this.drawBg("saa", xSize, ySize);
                this.master = 6;
                boolean line = false;
                int line2 = 0;
                String str = JRMCoreH.trl("dbc.talkgui.skills");
                i3 = this.field_146289_q.func_78256_a(str) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - i3, posY + 10, i3, 20, str));
                ++line2;
                ++line2;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 125) {
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i = 0;
                this.sklLrn(18, guiLeft + 5, guiTop + 25 + i * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.BEERUS || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                    }
                }
                if (i >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 9002) {
                this.drawBg("saa", xSize, ySize);
                this.master = 18;
                int line = 0;
                int line2 = 0;
                String n1 = "";
                int ln = 0;
                int dimension = Minecraft.func_71410_x().field_71439_g.field_71093_bK;
                if (JRMCoreH.align > 33 && dimension == 99) {
                    String str = JRMCoreH.trl("dbc.talkgui.skills");
                    int i4 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 100, posX + 115 - i4, posY + 15 + line2 * 21, i4, 20, str));
                    ++line2;
                    if (JRMCoreH.align > 33) {
                        str = JRMCoreH.trl("dbc.talkgui.giveweights");
                        i4 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115, posY + 15 + line * 21, i4, 20, str));
                        ++line;
                    }
                } else if (dimension == 0) {
                    n1 = DBCAUtils.translate("talkgui.goBeerus");
                    String n2 = DBCAUtils.translate("talkgui.goTOP");
                    int nw2 = this.field_146289_q.func_78256_a(n2);
                    ln = 1;
                    this.field_146292_n.add(new DBCGuiButtons01(52, posX - nw2 / 2, posY + ln * 25 - 30, nw2, 20, n2));
                    ln = 2;
                    String n22 = DBCAUtils.translate("talkgui.goUniversalTournament");
                    int nw22 = this.field_146289_q.func_78256_a(n22);
                    this.field_146292_n.add(new DBCGuiButtons01(779, posX - nw22 / 2, posY + ln * 25 - 30, nw22, 20, n22));
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 115, posY + 65, 20, 20, "X"));
                if (dimension != 0) {
                    n1 = DBCAUtils.translate("talkgui.goEarth");
                }
                ln = 3;
                int nw1 = this.field_146289_q.func_78256_a(n1) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(778, posX - nw1 / 2, posY + ln * 25 - 30, nw1, 20, n1));
            } else if (this.wish == 9102) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i = 0;
                boolean gf = DBCConfig.Godform && JRMCoreHDBC.godKiAble();
                this.sklLrn(9, guiLeft + 5, guiTop + 25 + i * 21 + this.scroll, gf, guiTop);
                ++i;
                if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
                    this.sklLrn(16, guiLeft + 5, guiTop + 25 + i * 21 + this.scroll, guiTop);
                }
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.WHIS || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                    }
                }
                if (i >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 24) {
                this.drawBg("saa", xSize, ySize);
                this.master = 6;
                boolean line = false;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    String str = JRMCoreH.trl("dbc.talkgui.skills");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - i3, posY + 10, i3, 20, str));
                    ++line2;
                    if (DBCAConfig.PotaraMinLevel != -1 && DataUtils.getPotaraCooldown((EntityPlayer)Minecraft.func_71410_x().field_71439_g) <= 0 && JRMCoreH.getPlayerLevel(JRMCoreH.PlyrAttrbts) >= DBCAConfig.PotaraMinLevel) {
                        str = DBCAUtils.translate("talkgui.askForPotara");
                        i3 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(1241, posX + 115 - i3, posY + 32, i3, 20, str));
                        ++line2;
                    }
                }
                ++line2;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 124) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i = 0;
                String n3 = JRMCoreH.trl("dbc", "PURitual");
                int nw3 = this.field_146289_q.func_78256_a(n3) + 8;
                if (25 + i * 21 + this.scroll >= 25 && 25 + i * 21 + this.scroll <= 130) {
                    Object btn = new DBCGuiButtons01(JRMCoreH.SklLvl(10, (byte)1) < 1 ? 1010 : -1, guiLeft + 5, guiTop + 25 + i * 21 + this.scroll, 83, 20, n3);
                    ((DBCGuiButtons01)((Object)btn)).field_146124_l = JRMCoreH.align == 100 && JRMCoreH.SklLvl(10) == 0 && JRMCoreH.getDBCSkillTPCost(10, 0) != -1 && JRMCoreH.getDBCSkillTPCost(10, 0) <= JRMCoreH.curTP;
                    this.field_146292_n.add(btn);
                    this.mc.field_71466_p.func_78276_b(JRMCoreH.align < 100 ? JRMCoreH.trl("jrmc", "NeedToBeGood", 100) : (JRMCoreH.SklLvl(10) < 1 ? (JRMCoreH.getDBCSkillTPCost(10, 0) == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : JRMCoreH.trl("dbc.talkgui.cost") + ": " + JRMCoreH.getDBCSkillTPCost(10, 0) + " " + JRMCoreH.trl("dbc.talkgui.mind") + ": " + JRMCoreH.skillMindRequirement(JRMCoreH.DBCSkillsIDs[i], JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost)) : JRMCoreH.trl("dbc.talkgui.owned")), guiLeft + 5 + 120, guiTop + 25 + 7 + i * 21 + this.scroll, 0);
                }
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.OLD_KAI || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                    }
                }
                if (i >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1241) {
                this.drawBg("saa", xSize, ySize);
                this.master = 6;
                boolean line = false;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    String str = StatCollector.func_74838_a((String)"item.potara_yellow.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 1, posX - 30 - i3, posY - 18, i3, 20, str));
                    ++line2;
                    str = StatCollector.func_74838_a((String)"item.potara_green.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 2, posX + 70 - i3, posY - 18, i3, 20, str));
                    ++line2;
                    str = StatCollector.func_74838_a((String)"item.potara_blue.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 3, posX - 30 - i3, posY + 4, i3, 20, str));
                    ++line2;
                    str = StatCollector.func_74838_a((String)"item.potara_red.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 4, posX + 70 - i3, posY + 4, i3, 20, str));
                    ++line2;
                    str = StatCollector.func_74838_a((String)"item.potara_pink.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 5, posX - 30 - i3, posY + 26, i3, 20, str));
                    ++line2;
                    str = StatCollector.func_74838_a((String)"item.potara_white.name");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.POTARA_WISH + 6, posX + 70 - i3, posY + 26, i3, 20, str));
                    ++line2;
                }
                ++line2;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 12) {
                this.drawBg("saa", xSize, ySize);
                this.master = 6;
                int line = 0;
                int line2 = 0;
                if (JRMCoreH.align > 33) {
                    if (JRMCoreH.Master == 1) {
                        String str1 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int j = this.field_146289_q.func_78256_a(str1) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - j, posY + 15 + line2 * 21, j, 20, str1));
                        ++line2;
                    }
                    String str = JRMCoreH.trl("dbc.talkgui.skills");
                    i3 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - i3, posY + 15 + line2 * 21, i3, 20, str));
                    ++line2;
                }
                String n4 = JRMCoreH.trl("dbc.talkgui.difftonormal");
                int nw4 = this.field_146289_q.func_78256_a(n4) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(103, posX + 115 - nw4, posY + 15 + line2 * 21, nw4, 20, n4));
                ++line2;
                if (JRMCoreH.align > 33) {
                    n4 = JRMCoreH.trl("dbc.talkgui.giveweights");
                    nw4 = this.field_146289_q.func_78256_a(n4) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line * 21, nw4, 20, n4));
                    ++line;
                }
                if (JRMCoreH.StusEfctsMe(12)) {
                    n4 = JRMCoreH.trl("dbc", "majinLoose");
                    nw4 = this.field_146289_q.func_78256_a(n4) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(50, posX + 115 - nw4, posY + 15 + line2 * 21, nw4, 20, n4));
                    ++line2;
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 112) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                m = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                ml = JRMCoreH.statMindC() - m;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i = 0;
                this.sklLrn(8, guiLeft + 5, guiTop + 25 + i * 21 + this.scroll, guiTop);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                this.sklLrn(13, guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.KAIO || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i * 21 + this.scroll, guiTop);
                    }
                }
                if (i >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1121) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (int i5 = 0; i5 < nms; i5 = (int)((byte)(i5 + 1))) {
                    int owner = Integer.parseInt(PMA[i5][2]);
                    if (owner != 1 && owner != 6) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i5][0]);
                        int fnw2 = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        on = JRMCoreH.techOwnd(i5, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw2, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i5, (byte)1)) {
                            cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i5]) * 0.9f);
                            n = " " + cost + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i5, (byte)1) ? 4200 + i5 : -1, guiLeft + xSize / 2 - 122 + fnw2 + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw, n, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n, guiLeft + xSize / 2 - 122 + fnw2 + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            } else if (this.wish == 1105) {
                String im = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(im);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                nr = 0;
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.giveweightsdesc");
                nr += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                int line = 0;
                String n5 = JRMCoreH.trl("dbc.talkgui.weightamount");
                int nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                ++this.text;
                if (this.text == 1) {
                    this.name(var8, posX - 220 + nw5, posY - 25 + line * 21);
                    this.inputField.func_146180_a("5");
                } else {
                    this.text = 2;
                }
                if (this.inputField != null) {
                    this.inputField.func_146194_f();
                    this.name = this.inputField.func_146179_b();
                    var8.func_78276_b(n5, posX - 120, posY - 7 + line * 21, 0);
                }
                ++line;
                n5 = ItemsDBC.ItemWeightHandLeg.func_77653_i(new ItemStack(ItemsDBC.ItemWeightHandLeg));
                nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                if (this.master == 6) {
                    this.field_146292_n.add(new DBCGuiButtons01(210, posX - 120, posY - 15 + line * 21, nw5, 20, n5));
                    ++line;
                }
                n5 = ItemsDBC.ItemWeightShell.func_77653_i(new ItemStack(ItemsDBC.ItemWeightShell));
                nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                if (this.master == 2) {
                    this.field_146292_n.add(new DBCGuiButtons01(211, posX - 120, posY - 15 + line * 21, nw5, 20, n5));
                    ++line;
                }
                n5 = ItemsDBC.ItemWeightShirt.func_77653_i(new ItemStack(ItemsDBC.ItemWeightShirt));
                nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                if (this.master == 6 || this.master == 5) {
                    this.field_146292_n.add(new DBCGuiButtons01(212, posX - 120, posY - 15 + line * 21, nw5, 20, n5));
                    ++line;
                }
                n5 = ItemsDBC.ItemWeightCape.func_77653_i(new ItemStack(ItemsDBC.ItemWeightCape));
                nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                if (this.master == 8) {
                    this.field_146292_n.add(new DBCGuiButtons01(213, posX - 120, posY - 15 + line * 21, nw5, 20, n5));
                    ++line;
                }
                n5 = ItemsDBC.ItemWeightHeavySuit.func_77653_i(new ItemStack(ItemsDBC.ItemWeightHeavySuit));
                nw5 = this.field_146289_q.func_78256_a(n5) + 8;
                if (this.master == 18) {
                    this.field_146292_n.add(new DBCGuiButtons01(214, posX - 120, posY - 15 + line * 21, nw5, 20, n5));
                    ++line;
                }
            } else if (this.wish == 221) {
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                Iterator iterator = this.mc.field_71466_p.func_78271_c(JRMCoreH.trl("dbc", "KaioDiffRed"), 245).iterator();
                i2 = 0;
                while (iterator.hasNext()) {
                    String s1 = (String)iterator.next();
                    var8.func_78276_b(s1, guiLeft + 5, guiTop + 5 + ++i2 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(104, posX - 35, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.no")));
                this.field_146292_n.add(new DBCGuiButtons01(105, posX - 120, posY + 55, 80, 20, JRMCoreH.trl("dbc.talkgui.confirm")));
            } else if (this.wish == 200) {
                this.drawBg("saa", xSize, ySize);
                boolean line = false;
                int line2 = 0;
                int worldId = Minecraft.func_71410_x().field_71439_g.field_71093_bK;
                String str = "";
                if (worldId == 98) {
                    str = DBCAUtils.translate("talkgui.goEarth");
                    i = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(777, posX + 115 - i, posY + 10, i, 20, str));
                } else {
                    str = DBCAUtils.translate("talkgui.goSacredWorld");
                }
                i = this.field_146289_q.func_78256_a(str) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(777, posX + 115 - i, posY + 10, i, 20, str));
                ++line2;
                ++line2;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 22) {
                this.master = 14;
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                nr = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[14]);
                nr += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.goodneut");
                    nr += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        i = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i / 2, posY + 35, i, 20, str));
                    }
                    String n6 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw6 = this.field_146289_q.func_78256_a(n6) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw6 / 2, posY + 55, nw6, 20, n6));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.evil");
                    nr += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 122) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml2 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml2), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                i3 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i3 * 21 + this.scroll, guiTop);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i3 * 21 + this.scroll, guiTop);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i3 * 21 + this.scroll, guiTop);
                this.sklLrn(15, guiLeft + 5, guiTop + 25 + ++i3 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.GOHAN || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i3 * 21 + this.scroll, guiTop);
                    }
                }
                if (i3 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1221) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                wish = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                tx = new ResourceLocation(wish);
                this.mc.field_71446_o.func_110577_a(tx);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                i2 = 0;
                nms = PMA.length;
                for (int i6 = 0; i6 < nms; i6 = (int)((byte)(i6 + 1))) {
                    int owner = Integer.parseInt(PMA[i6][2]);
                    if (owner != 2 && owner != 1 && owner != 8 && owner != 14) continue;
                    if (i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i6][0]);
                        int fnw3 = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, 0);
                        on = JRMCoreH.techOwnd(i6, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw3, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i6, (byte)1)) {
                            cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i6]) * 0.9f);
                            n = " " + cost + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i6, (byte)1) ? 4200 + i6 : -1, guiLeft + xSize / 2 - 122 + fnw3 + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, nw, n, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n, guiLeft + xSize / 2 - 122 + fnw3 + onw, guiTop + (ySize + 1) / 2 - 64 + i2 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i2;
                }
            }
            if (this.wish == 17) {
                this.master = 13;
                String wish2 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx2 = new ResourceLocation(wish2);
                this.mc.field_71446_o.func_110577_a(tx2);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr2 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[13]);
                nr2 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr2 * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songoku.goodneut");
                    nr2 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr2 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i7 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i7 / 2, posY + 35, i7, 20, str));
                    }
                    String n7 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw7 = this.field_146289_q.func_78256_a(n7) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw7 / 2, posY + 55, nw7, 20, n7));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songoku.evil");
                    nr2 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr2 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 117) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish3 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx3 = new ResourceLocation(wish3);
                this.mc.field_71446_o.func_110577_a(tx3);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml3 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml3), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i8 = 0;
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + i8 * 21 + this.scroll, guiTop);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                this.sklLrn(0, guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                this.sklLrn(14, guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                this.sklLrn(17, guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.GOKU || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i8 * 21 + this.scroll, guiTop);
                    }
                }
                if (i8 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1171) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish4 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx4 = new ResourceLocation(wish4);
                this.mc.field_71446_o.func_110577_a(tx4);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i22 = 0;
                int nms2 = PMA.length;
                for (int i9 = 0; i9 < nms2; i9 = (int)((byte)(i9 + 1))) {
                    int owner = Integer.parseInt(PMA[i9][2]);
                    if (owner != 2 && owner != 1 && owner != 6 && owner != 7) continue;
                    if (i22 <= 13 + this.ipg * 13 && i22 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i9][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i22 * 10 - this.lp * 13 * 10, 0);
                        on = JRMCoreH.techOwnd(i9, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i22 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i9, (byte)1)) {
                            cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i9]) * 0.9f);
                            n = " " + cost + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i9, (byte)1) ? 4200 + i9 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i22 * 10 - this.lp * 13 * 10, nw, n, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i22 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i22;
                }
            }
            if (this.wish == 21) {
                this.master = 9;
                String wish5 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx5 = new ResourceLocation(wish5);
                this.mc.field_71446_o.func_110577_a(tx5);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr3 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[this.master]);
                nr3 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr3 * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.vegeta.goodneut");
                    nr3 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr3 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i10 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i10 / 2, posY + 35, i10, 20, str));
                    }
                    String n8 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw8 = this.field_146289_q.func_78256_a(n8) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw8 / 2, posY + 55, nw8, 20, n8));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.vegeta.evil");
                    nr3 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr3 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 121) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish6 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx6 = new ResourceLocation(wish6);
                this.mc.field_71446_o.func_110577_a(tx6);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m2 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml4 = JRMCoreH.statMindC() - m2;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml4), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i11 = 0;
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + i11 * 21 + this.scroll, guiTop);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i11 * 21 + this.scroll, guiTop);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i11 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.VEGETA || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i11 * 21 + this.scroll, guiTop);
                    }
                }
                if (i11 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1211) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish7 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx7 = new ResourceLocation(wish7);
                this.mc.field_71446_o.func_110577_a(tx7);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i23 = 0;
                int nms3 = PMA.length;
                for (int i12 = 0; i12 < nms3; i12 = (int)((byte)(i12 + 1))) {
                    int owner = Integer.parseInt(PMA[i12][2]);
                    if (owner != 1 && owner != 9) continue;
                    if (i23 <= 13 + this.ipg * 13 && i23 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i12][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i23 * 10 - this.lp * 13 * 10, 0);
                        on = JRMCoreH.techOwnd(i12, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i23 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i12, (byte)1)) {
                            cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i12]) * 0.9f);
                            n = " " + cost + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i12, (byte)1) ? 4200 + i12 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i23 * 10 - this.lp * 13 * 10, nw, n, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i23 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i23;
                }
            }
            if (this.wish == 23) {
                this.master = 10;
                String wish8 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx8 = new ResourceLocation(wish8);
                this.mc.field_71446_o.func_110577_a(tx8);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr4 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[10]);
                nr4 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr4 * 10, 0);
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.goodneut");
                    nr4 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr4 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i13 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i13 / 2, posY + 35, i13, 20, str));
                    }
                    String n9 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw9 = this.field_146289_q.func_78256_a(n9) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw9 / 2, posY + 55, nw9, 20, n9));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.songohan.evil");
                    nr4 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr4 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 123) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish9 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx9 = new ResourceLocation(wish9);
                this.mc.field_71446_o.func_110577_a(tx9);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int ml5 = JRMCoreH.skillSlot_AvailableMindLeft();
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml5), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i14 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i14 * 21 + this.scroll, guiTop);
                this.sklLrn(14, guiLeft + 5, guiTop + 25 + ++i14 * 21 + this.scroll, guiTop);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i14 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.TRUNKS || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i14 * 21 + this.scroll, guiTop);
                    }
                }
                if (i14 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1231) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish10 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx10 = new ResourceLocation(wish10);
                this.mc.field_71446_o.func_110577_a(tx10);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i24 = 0;
                int nms4 = PMA.length;
                for (int i15 = 0; i15 < nms4; i15 = (int)((byte)(i15 + 1))) {
                    int owner = Integer.parseInt(PMA[i15][2]);
                    if (owner != 2 && owner != 1 && owner != 14 && owner != 10) continue;
                    if (i24 <= 13 + this.ipg * 13 && i24 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i15][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i24 * 10 - this.lp * 13 * 10, 0);
                        on = JRMCoreH.techOwnd(i15, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on);
                        var8.func_78276_b(on, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i24 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i15, (byte)1)) {
                            cost = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i15]) * 0.9f);
                            n = " " + cost + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i15, (byte)1) ? 4200 + i15 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i24 * 10 - this.lp * 13 * 10, nw, n, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i24 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i24;
                }
            }
            if (this.wish == 15) {
                this.master = 2;
                String wish11 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx11 = new ResourceLocation(wish11);
                this.mc.field_71446_o.func_110577_a(tx11);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr5 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[2]);
                nr5 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr5 * 10, 0);
                this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.roshi.goodneut");
                nr5 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr5 * 10, 0);
                int line = 0;
                if (JRMCoreH.align > 32) {
                    if (JRMCoreH.Master == 1) {
                        String str1 = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int j = this.field_146289_q.func_78256_a(str1) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - j, posY + 15 + line * 21, j, 20, str1));
                        ++line;
                    }
                    String str = JRMCoreH.trl("dbc.talkgui.skills");
                    int i16 = this.field_146289_q.func_78256_a(str) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - i16, posY + 15 + line * 21, i16, 20, str));
                    ++line;
                }
                line = 0;
                String n10 = JRMCoreH.trl("dbc.talkgui.giveweights");
                int nw10 = this.field_146289_q.func_78256_a(n10) + 8;
                this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line * 21, nw10, 20, n10));
                ++line;
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 115) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish12 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx12 = new ResourceLocation(wish12);
                this.mc.field_71446_o.func_110577_a(tx12);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m3 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml6 = JRMCoreH.statMindC() - m3;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml6), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i17 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i17 * 21 + this.scroll, guiTop);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i17 * 21 + this.scroll, guiTop);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i17 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.ROSHI || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i17 * 21 + this.scroll, guiTop);
                    }
                }
                if (i17 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1151) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish13 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx13 = new ResourceLocation(wish13);
                this.mc.field_71446_o.func_110577_a(tx13);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i25 = 0;
                int nms5 = PMA.length;
                for (int i18 = 0; i18 < nms5; i18 = (int)((byte)(i18 + 1))) {
                    if (Integer.parseInt(PMA[i18][2]) != 2 && Integer.parseInt(PMA[i18][2]) != 1) continue;
                    if (i25 <= 13 + this.ipg * 13 && i25 >= 0 + this.ipg * 13) {
                        String fn2 = JRMCoreH.trl("dbc", PMA[i18][0]);
                        int fnw4 = this.fontRenderer.func_78256_a(fn2);
                        var8.func_78276_b(fn2, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i25 * 10 - this.lp * 13 * 10, 0);
                        String on2 = JRMCoreH.techOwnd(i18, (byte)1) ? " Owned" : "";
                        int onw2 = this.fontRenderer.func_78256_a(on2);
                        var8.func_78276_b(on2, guiLeft + xSize / 2 - 122 + fnw4, guiTop + (ySize + 1) / 2 - 64 + i25 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i18, (byte)1)) {
                            int costTp2;
                            int cost2 = costTp2 = (int)((float)JRMCoreH.techDBCtpc(PMA[i18]) * 0.9f);
                            String n11 = " " + cost2 + " tp";
                            int nw11 = this.fontRenderer.func_78256_a(fn2);
                            if (JRMCoreH.curTP >= cost2) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i18, (byte)1) ? 4200 + i18 : -1, guiLeft + xSize / 2 - 122 + fnw4 + onw2, guiTop + (ySize + 1) / 2 - 64 + i25 * 10 - this.lp * 13 * 10, nw11, n11, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n11, guiLeft + xSize / 2 - 122 + fnw4 + onw2, guiTop + (ySize + 1) / 2 - 64 + i25 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i25;
                }
            }
            if (this.wish == 20) {
                this.master = 8;
                String wish14 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx14 = new ResourceLocation(wish14);
                this.mc.field_71446_o.func_110577_a(tx14);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr6 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[this.master]);
                nr6 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr6 * 10, 0);
                int line = 0;
                if (JRMCoreH.align > 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.piccolo.goodneut");
                    nr6 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr6 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i19 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 115 - i19, posY + 15 + line * 21, i19, 20, str));
                        ++line;
                    }
                    String n12 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw12 = this.field_146289_q.func_78256_a(n12) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 115 - nw12, posY + 15 + line * 21, nw12, 20, n12));
                    ++line;
                    line = 0;
                    n12 = JRMCoreH.trl("dbc.talkgui.giveweights");
                    nw12 = this.field_146289_q.func_78256_a(n12) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(110, posX - 115 - 0, posY + 15 + line * 21, nw12, 20, n12));
                    ++line;
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.piccolo.evil");
                    nr6 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr6 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 120) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish15 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx15 = new ResourceLocation(wish15);
                this.mc.field_71446_o.func_110577_a(tx15);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m4 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml7 = JRMCoreH.statMindC() - m4;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml7), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i20 = 0;
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + i20 * 21 + this.scroll, guiTop);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i20 * 21 + this.scroll, guiTop);
                this.sklLrn(7, guiLeft + 5, guiTop + 25 + ++i20 * 21 + this.scroll, guiTop);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i20 * 21 + this.scroll, guiTop);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i20 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.PICCOLO || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i20 * 21 + this.scroll, guiTop);
                    }
                }
                if (i20 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1201) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish16 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx16 = new ResourceLocation(wish16);
                this.mc.field_71446_o.func_110577_a(tx16);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i26 = 0;
                int nms6 = PMA.length;
                for (int i21 = 0; i21 < nms6; i21 = (int)((byte)(i21 + 1))) {
                    int owner = Integer.parseInt(PMA[i21][2]);
                    if (owner != 1 && owner != 7 && owner != 8) continue;
                    if (i26 <= 13 + this.ipg * 13 && i26 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i21][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i26 * 10 - this.lp * 13 * 10, 0);
                        String on3 = JRMCoreH.techOwnd(i21, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on3);
                        var8.func_78276_b(on3, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i26 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i21, (byte)1)) {
                            int cost3 = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i21]) * 0.9f);
                            String n13 = " " + cost3 + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost3) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i21, (byte)1) ? 4200 + i21 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i26 * 10 - this.lp * 13 * 10, nw, n13, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n13, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i26 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i26;
                }
            } else if (this.wish == 18) {
                this.master = 11;
                String wish17 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx17 = new ResourceLocation(wish17);
                this.mc.field_71446_o.func_110577_a(tx17);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr7 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[11]);
                nr7 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr7 * 10, 0);
                if (JRMCoreH.align <= 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.freeza.evil");
                    nr7 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr7 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i22 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i22 / 2, posY + 35, i22, 20, str));
                    }
                    String n14 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw13 = this.field_146289_q.func_78256_a(n14) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw13 / 2, posY + 55, nw13, 20, n14));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.freeza.else");
                    nr7 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr7 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 118) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish18 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx18 = new ResourceLocation(wish18);
                this.mc.field_71446_o.func_110577_a(tx18);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m5 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml8 = JRMCoreH.statMindC() - m5;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml8), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i23 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i23 * 21 + this.scroll, guiTop);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i23 * 21 + this.scroll, guiTop);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i23 * 21 + this.scroll, guiTop);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i23 * 21 + this.scroll, guiTop);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i23 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.FRIEZA || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i23 * 21 + this.scroll, guiTop);
                    }
                }
                if (i23 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1181) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish19 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx19 = new ResourceLocation(wish19);
                this.mc.field_71446_o.func_110577_a(tx19);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i27 = 0;
                int nms7 = PMA.length;
                for (int i24 = 0; i24 < nms7; i24 = (int)((byte)(i24 + 1))) {
                    int owner = Integer.parseInt(PMA[i24][2]);
                    if (owner != 1 && owner != 11) continue;
                    if (i27 <= 13 + this.ipg * 13 && i27 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i24][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i27 * 10 - this.lp * 13 * 10, 0);
                        String on4 = JRMCoreH.techOwnd(i24, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on4);
                        var8.func_78276_b(on4, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i27 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i24, (byte)1)) {
                            int cost4 = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i24]) * 0.9f);
                            String n15 = " " + cost4 + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost4) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i24, (byte)1) ? 4200 + i24 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i27 * 10 - this.lp * 13 * 10, nw, n15, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n15, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i27 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i27;
                }
            }
            if (this.wish == 16) {
                this.master = 12;
                String wish20 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx20 = new ResourceLocation(wish20);
                this.mc.field_71446_o.func_110577_a(tx20);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr8 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[12]);
                nr8 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr8 * 10, 0);
                if (JRMCoreH.align <= 32) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.cell.evil");
                    nr8 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr8 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i25 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i25 / 2, posY + 35, i25, 20, str));
                    }
                    String n16 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw14 = this.field_146289_q.func_78256_a(n16) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw14 / 2, posY + 55, nw14, 20, n16));
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.cell.goodneut");
                    nr8 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr8 * 10, 0);
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 116) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish21 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx21 = new ResourceLocation(wish21);
                this.mc.field_71446_o.func_110577_a(tx21);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m6 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml9 = JRMCoreH.statMindC() - m6;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml9), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i26 = 0;
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + i26 * 21 + this.scroll, guiTop);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                this.sklLrn(6, guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                this.sklLrn(11, guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                this.sklLrn(12, guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                this.sklLrn(17, guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.CELL || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i26 * 21 + this.scroll, guiTop);
                    }
                }
                if (i26 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1161) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish22 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx22 = new ResourceLocation(wish22);
                this.mc.field_71446_o.func_110577_a(tx22);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i28 = 0;
                int nms8 = PMA.length;
                for (int i27 = 0; i27 < nms8; i27 = (int)((byte)(i27 + 1))) {
                    int owner = Integer.parseInt(PMA[i27][2]);
                    if (owner != 2 && owner != 1 && owner != 7 && owner != 8 && owner != 9 && owner != 11) continue;
                    if (i28 <= 13 + this.ipg * 13 && i28 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i27][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i28 * 10 - this.lp * 13 * 10, 0);
                        String on5 = JRMCoreH.techOwnd(i27, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on5);
                        var8.func_78276_b(on5, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i28 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i27, (byte)1)) {
                            int cost5 = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i27]) * 0.9f);
                            String n17 = " " + cost5 + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost5) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i27, (byte)1) ? 4200 + i27 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i28 * 10 - this.lp * 13 * 10, nw, n17, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n17, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i28 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i28;
                }
            }
            if (this.wish == 19) {
                this.master = 15;
                String wish23 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx23 = new ResourceLocation(wish23);
                this.mc.field_71446_o.func_110577_a(tx23);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                int nr9 = 0;
                this.Process = StatCollector.func_74838_a((String)JRMCoreH.Masters[15]);
                nr9 += JRMCoreH.txt(this.Process, "", 0, true, guiLeft + 6, guiTop + 5 + nr9 * 10, 0);
                int ln = 0;
                if (JRMCoreH.align <= 65) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.evilneut");
                    nr9 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr9 * 10, 0);
                    if (JRMCoreH.Master == 1) {
                        String str = JRMCoreH.trl("dbc.talkgui.learntechs");
                        int i28 = this.field_146289_q.func_78256_a(str) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01((this.wish + 10100) * 10 + 1, posX + 15 - i28 / 2, posY + 15 + ln * 20, i28, 20, str));
                        ++ln;
                    }
                    String n18 = JRMCoreH.trl("dbc.talkgui.skills");
                    int nw15 = this.field_146289_q.func_78256_a(n18) + 8;
                    this.field_146292_n.add(new DBCGuiButtons01(this.wish + 10100, posX + 15 - nw15 / 2, posY + 15 + ln * 20, nw15, 20, n18));
                    ++ln;
                    if (!JRMCoreH.StusEfctsMe(12)) {
                        n18 = JRMCoreH.trl("dbc", "majinGet");
                        nw15 = this.field_146289_q.func_78256_a(n18) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(51, posX + 15 - nw15 / 2, posY + 15 + ln * 20, nw15, 20, n18));
                        ++ln;
                    }
                } else if (JRMCoreH.align == 100) {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.fullgood");
                    nr9 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr9 * 10, 0);
                } else {
                    this.Process = StatCollector.func_74838_a((String)"dbc.talkgui.babidi.good");
                    nr9 += JRMCoreH.txt(this.Process, "", 5, true, guiLeft + 6, guiTop + 5 + nr9 * 10, 0);
                    if (!JRMCoreH.StusEfctsMe(12)) {
                        String n19 = JRMCoreH.trl("dbc", "majinGet");
                        int nw16 = this.field_146289_q.func_78256_a(n19) + 8;
                        this.field_146292_n.add(new DBCGuiButtons01(51, posX + 15 - nw16 / 2, posY + 15 + ln * 20, nw16, 20, n19));
                        ++ln;
                    }
                }
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
            } else if (this.wish == 119) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish24 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx24 = new ResourceLocation(wish24);
                this.mc.field_71446_o.func_110577_a(tx24);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                int m7 = JRMCoreH.skillSlot_SpentMindRequirement(JRMCoreH.PlyrSkills, JRMCoreH.DBCSkillsIDs, JRMCoreH.DBCSkillMindCost) + JRMCoreH.skillSlot_SpentMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
                int ml10 = JRMCoreH.statMindC() - m7;
                var8.func_78276_b(JRMCoreH.trl("jrmc", "AvailableMind") + ": " + JRMCoreH.numSep(ml10), guiLeft + 5, guiTop + 15, 0);
                var8.func_78276_b("TP: " + JRMCoreH.numSep(JRMCoreH.curTP), guiLeft + 5, guiTop + 5, 0);
                int i29 = 0;
                this.sklLrn(1, guiLeft + 5, guiTop + 25 + i29 * 21 + this.scroll, guiTop);
                this.sklLrn(2, guiLeft + 5, guiTop + 25 + ++i29 * 21 + this.scroll, guiTop);
                this.sklLrn(3, guiLeft + 5, guiTop + 25 + ++i29 * 21 + this.scroll, guiTop);
                this.sklLrn(5, guiLeft + 5, guiTop + 25 + ++i29 * 21 + this.scroll, guiTop);
                this.sklLrn(4, guiLeft + 5, guiTop + 25 + ++i29 * 21 + this.scroll, guiTop);
                if (DBCAConfig.CustomForms) {
                    for (DBCASkill skill : DBCASkills.SKILLS) {
                        if (skill.SkillMaster != DBCASkill.Master.BABIDI || !skill.isEnabled() || !skill.race(JRMCoreH.Race, dbcarace)) continue;
                        this.customSklLrn(skill.getID(), guiLeft + 5, guiTop + 25 + ++i29 * 21 + this.scroll, guiTop);
                    }
                }
                if (i29 >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 - 70, "i"));
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + (ySize + 1) / 2 + 60, "v"));
                }
            } else if (this.wish == 1191) {
                this.field_146292_n.add(new DBCGuiButtons01(-1, posX - 150, posY + 65, 20, 20, "X"));
                String wish25 = "jinryuudragonbc:saa.png";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation tx25 = new ResourceLocation(wish25);
                this.mc.field_71446_o.func_110577_a(tx25);
                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                posX = this.field_146294_l / 2;
                posY = this.field_146295_m / 2;
                String[][] PMA = JRMCoreH.pmdbc;
                var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points", guiLeft + 10, guiTop + 5, 0);
                int i29 = 0;
                int nms9 = PMA.length;
                for (int i30 = 0; i30 < nms9; i30 = (int)((byte)(i30 + 1))) {
                    int owner = Integer.parseInt(PMA[i30][2]);
                    if (owner != 1) continue;
                    if (i29 <= 13 + this.ipg * 13 && i29 >= 0 + this.ipg * 13) {
                        fn = JRMCoreH.trl("dbc", PMA[i30][0]);
                        fnw = this.fontRenderer.func_78256_a(fn);
                        var8.func_78276_b(fn, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 64 + i29 * 10 - this.lp * 13 * 10, 0);
                        String on6 = JRMCoreH.techOwnd(i30, (byte)1) ? " Owned" : "";
                        onw = this.fontRenderer.func_78256_a(on6);
                        var8.func_78276_b(on6, guiLeft + xSize / 2 - 122 + fnw, guiTop + (ySize + 1) / 2 - 64 + i29 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[1]);
                        if (!JRMCoreH.techOwnd(i30, (byte)1)) {
                            int cost6 = costTp = (int)((float)JRMCoreH.techDBCtpc(PMA[i30]) * 0.9f);
                            String n20 = " " + cost6 + " tp";
                            nw = this.fontRenderer.func_78256_a(fn);
                            if (JRMCoreH.curTP >= cost6) {
                                this.field_146292_n.add(new JRMCoreGuiButtons01(!JRMCoreH.techOwnd(i30, (byte)1) ? 4200 + i30 : -1, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i29 * 10 - this.lp * 13 * 10, nw, n20, JRMCoreH.techNCCol[1]));
                            } else {
                                var8.func_78276_b(n20, guiLeft + xSize / 2 - 122 + fnw + onw, guiTop + (ySize + 1) / 2 - 64 + i29 * 10 - this.lp * 13 * 10, JRMCoreH.techNCCol[0]);
                            }
                        }
                    }
                    ++i29;
                }
            }
            this.loadDialogs(guiTop, guiLeft);
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
        WorldClient worldClient = minecraft.field_71441_e;
        EntityClientPlayerMP entityClientPlayerMP = minecraft.field_71439_g;
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

