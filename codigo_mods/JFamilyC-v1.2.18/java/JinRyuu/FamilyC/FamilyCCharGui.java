/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCGuiButtons00;
import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreGuiButtons00;
import JinRyuu.JRMCore.JRMCoreGuiButtons01;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA2;
import JinRyuu.JRMCore.JRMCoreGuiButtonsTab;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JYearsCH;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class FamilyCCharGui
extends GuiScreen {
    public int jfc = 0;
    private int fnba = 0;
    public static String dtcf = "0";
    public static String dtca = "0";
    public static String dtcft = "0";
    public static String dtcd = "0";
    public static String dtcdad = "";
    public static String dtcmom = "";
    String reg = "[^'a-zA-Z0-9-]";
    public static String famNam = "";
    public static String chiNam = "";
    public static String propInv = "";
    public static String flwTrgtNm = "";
    public static String adopInv = "";
    public static String kick = "";
    public static String name = "";
    public static String children = "";
    protected GuiTextField inputField;
    private String defaultInputFieldText = "";
    private int ipg = 0;
    public static int inv = 0;
    private int famMems = 0;
    private String dfu = "";

    public FamilyCCharGui(int w) {
        this.jfc = w;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        this.npcNuller();
        children = "";
        if (inv == -1) {
            inv = 0;
        }
        if (this.jfc == 1) {
            FamilyCH.jfcd(21, "");
        }
        if (!(JRMCoreH.targ instanceof EntityPlayer)) {
            JRMCoreH.targ = null;
        }
    }

    public Object actionPerformed(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        int selct = par1 - 20;
        boolean KA = false;
        FamilyCGuiButtons00 ret = KA ? new FamilyCGuiButtons00(par1, par2, par3, par4, par5, par6Str) : new FamilyCGuiButtons00(par1, par2, par3, par4, par5, par6Str);
        return ret;
    }

    public static int Slct(String dir, int Select, int l) {
        int selct;
        Select = dir.contains("B") ? ((selct = Select - 1) >= 0 ? selct : l - 1) : ((selct = Select + 1) < l ? selct : 0);
        return Select;
    }

    public void npcNuller() {
        dtcf = "0";
        dtca = "0";
        dtcft = "0";
        dtcd = "0";
        dtcdad = "";
        dtcmom = "";
        flwTrgtNm = "";
    }

    public void func_146284_a(GuiButton button) {
        EntityPlayer p;
        if (button.field_146127_k == 10) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 1) {
            Entity npc = JRMCoreH.targNPC;
            FamilyCH.jfcd(20, JRMCoreH.targNPC.func_145782_y() + ":" + dtcf + ":" + dtca + ":" + dtcft + ":" + dtcd);
            this.npcNuller();
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 2) {
            dtcf = "" + FamilyCCharGui.Slct("F", Integer.parseInt(dtcf), 4);
        }
        if (button.field_146127_k == -2) {
            dtcf = "" + FamilyCCharGui.Slct("B", Integer.parseInt(dtcf), 4);
        }
        if (button.field_146127_k == 3) {
            dtca = "" + FamilyCCharGui.Slct("F", Integer.parseInt(dtca), 2);
        }
        if (button.field_146127_k == -3) {
            dtca = "" + FamilyCCharGui.Slct("B", Integer.parseInt(dtca), 2);
        }
        if (button.field_146127_k == 4) {
            dtcd = "" + FamilyCCharGui.Slct("F", Integer.parseInt(dtcd), 2);
        }
        if (button.field_146127_k == -4) {
            dtcd = "" + FamilyCCharGui.Slct("B", Integer.parseInt(dtcd), 2);
        }
        if (button.field_146127_k == 5) {
            inv = 21;
        }
        if (inv == 21) {
            for (int i = 0; i < (JRMCoreH.plyrs != null ? JRMCoreH.plyrs.length : 10); i = (int)((byte)(i + 1))) {
                if (button.field_146127_k != 2000 + i || JRMCoreH.plyrs == null || JRMCoreH.plyrs.length <= i || (p = this.field_146297_k.field_71441_e.func_72924_a(JRMCoreH.plyrs[i])) == null) continue;
                flwTrgtNm = p.func_70005_c_();
                dtcft = p.func_145782_y() + "";
                inv = 0;
            }
        }
        if (inv == -1 && button.field_146127_k == 6) {
            for (int i = 0; i < 3; i = (int)((byte)(i + 1))) {
                chiNam = chiNam.length() < 2 ? chiNam + "_" : chiNam;
            }
            chiNam = chiNam.replaceAll(this.reg, "_");
            FamilyCH.jfcd(22, JRMCoreH.targNPC.func_145782_y() + ":" + chiNam);
            inv = 0;
            this.defaultInputFieldText = "";
        }
        if (button.field_146127_k == 60) {
            this.jfc = 1;
        }
        if (button.field_146127_k == 61) {
            this.jfc = 61;
        }
        if (button.field_146127_k == 62) {
            this.jfc = 70;
        }
        if (button.field_146127_k == 77) {
            int n = this.fnba = this.fnba == 0 ? 1 : 0;
        }
        if (button.field_146127_k == 78) {
            inv = 1;
        }
        if (button.field_146127_k == 79) {
            inv = 2;
        }
        if (button.field_146127_k == 80) {
            for (int i = 0; i < 3; i = (int)((byte)(i + 1))) {
                famNam = famNam.length() < 2 ? famNam + "A" : famNam;
            }
            famNam = famNam.replaceAll(this.reg, "_");
            famNam = famNam + "," + this.fnba;
            FamilyCH.jfcd(0, famNam);
            inv = 0;
        }
        if (button.field_146127_k == 81) {
            FamilyCH.jfcd(4, "");
            inv = 0;
        }
        if (button.field_146127_k == 82) {
            FamilyCH.jfcd(3, "");
            inv = 0;
        }
        if (button.field_146127_k == 83) {
            FamilyCH.jfcd(5, "");
            inv = 0;
        }
        if (button.field_146127_k == 85) {
            FamilyCH.jfcd(6, "");
            inv = 0;
        }
        if (button.field_146127_k == 86) {
            FamilyCH.jfcd(7, kick);
            inv = 0;
        }
        if (button.field_146127_k == 88) {
            ++this.ipg;
        }
        if (button.field_146127_k == 89) {
            --this.ipg;
        }
        if (button.field_146127_k == 90) {
            inv = 0;
        }
        if (button.field_146127_k == 91) {
            inv = 4;
        }
        if (button.field_146127_k == 92) {
            inv = 5;
        }
        if (button.field_146127_k == 93) {
            inv = 6;
        }
        if (button.field_146127_k == 94) {
            for (int i = 0; i < 3; i = (int)((byte)(i + 1))) {
                chiNam = chiNam.length() < 2 ? chiNam + "A" : chiNam;
            }
            chiNam = chiNam.replaceAll(this.reg, "_");
            FamilyCH.jfcd(8, chiNam);
            inv = 0;
        }
        if (button.field_146127_k == 95) {
            FamilyCH.jfcd(9, "");
            inv = 0;
        }
        if (button.field_146127_k == 96) {
            FamilyCH.jfcd(10, JRMCoreH.targ.func_70005_c_());
            inv = 0;
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 97) {
            this.inputField.func_146180_a(FamilyCH.namGen());
        }
        if (inv == 1 || inv == 2) {
            for (int i = 0; i < (JRMCoreH.plyrs != null ? JRMCoreH.plyrs.length : 10); i = (int)((byte)(i + 1))) {
                if (button.field_146127_k != 2000 + i || JRMCoreH.plyrs == null || JRMCoreH.plyrs.length <= i || (p = this.field_146297_k.field_71441_e.func_72924_a(JRMCoreH.plyrs[i])) == null) continue;
                propInv = p.func_70005_c_();
                adopInv = p.func_70005_c_();
                FamilyCH.jfcd(inv == 1 ? 1 : (inv == 2 ? 2 : 10), inv == 1 ? propInv : (inv == 2 ? adopInv : "0"));
                inv = 0;
                propInv = "";
                adopInv = "";
            }
        }
        if ((inv == 3 || inv == 4 || inv == 5 || inv == 6) && button.field_146127_k >= 2000 && button.field_146127_k < 2500) {
            int k = button.field_146127_k - 2000;
            String[] s = this.dfu.split(":");
            kick = s[k];
            FamilyCH.jfcd(7, kick);
            kick = "";
            inv = 0;
        }
        this.nuller();
    }

    private void name(FontRenderer var8, int i, int j) {
        this.inputField = new GuiTextField(var8, i, j, 100, 12);
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

    public void nuller() {
        JRMCoreH.ask = null;
        JRMCoreH.targ = null;
    }

    public void player() {
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    public void func_73863_a(int x, int y, float f) {
        block115: {
            block118: {
                block120: {
                    block122: {
                        block123: {
                            block121: {
                                block119: {
                                    block117: {
                                        block116: {
                                            var5 = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
                                            var6 = var5.func_78326_a();
                                            var7 = var5.func_78328_b();
                                            var8 = this.field_146297_k.field_71466_p;
                                            wish = "jinryuufamilyc:gui.png";
                                            this.field_146292_n.clear();
                                            txtng = true;
                                            if (this.jfc >= 60 && this.jfc <= 70) {
                                                xSize = 256;
                                                ySize = 159;
                                                guiLeft = (this.field_146294_l - xSize) / 2;
                                                guiTop = (this.field_146295_m - ySize) / 2;
                                                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                                guiLocation = new ResourceLocation(wish);
                                                this.field_146297_k.field_71446_o.func_110577_a(guiLocation);
                                                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                                                this.field_146292_n.add(new JRMCoreGuiButtons00(10, guiLeft + xSize / 2 - 150, guiTop + ySize / 2 + 65, 20, 20, "X", 0));
                                                if (this.jfc == 60) {
                                                    // empty if block
                                                }
                                                if (this.jfc == 61) {
                                                    // empty if block
                                                }
                                                if (this.jfc == 70) {
                                                    this.field_146292_n.add(new JRMCoreGuiButtonsTab(60, guiLeft + xSize / 2 - 110, guiTop + (ySize + 1) / 2 - 99, 60, 20, "Status", 0));
                                                    this.field_146292_n.add(new JRMCoreGuiButtonsTab(62, guiLeft + xSize / 2 - 10, guiTop + (ySize + 1) / 2 - 99, 60, 20, "Family", 1));
                                                    if (FamilyCH.FamID == 0) {
                                                        if (FamilyCH.prop != null && FamilyCH.prop.length() > 1) {
                                                            n = FamilyCH.prop;
                                                            s = "You recived a propose from " + n;
                                                            var8.func_78276_b(s, guiLeft + 5, guiTop + 5, 0);
                                                            a = "Accept";
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(82, guiLeft + xSize / 2 + 10, guiTop + (ySize + 1) / 2 - 50, this.field_146289_q.func_78256_a(a) + 8, 20, a, 0));
                                                            d = "Decline";
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(83, guiLeft + xSize / 2 + 10, guiTop + (ySize + 1) / 2 - 30, this.field_146289_q.func_78256_a(d) + 8, 20, d, 0));
                                                        } else if (FamilyCH.adop != null && FamilyCH.adop.length() > 1) {
                                                            n = FamilyCH.adop;
                                                            s = "You received an adoption request " + n;
                                                            var8.func_78276_b(s, guiLeft + 5, guiTop + 5, 0);
                                                            a = "Accept";
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(81, guiLeft + xSize / 2 + 10, guiTop + (ySize + 1) / 2 - 50, this.field_146289_q.func_78256_a(a) + 8, 20, a, 0));
                                                            d = "Decline";
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(83, guiLeft + xSize / 2 + 10, guiTop + (ySize + 1) / 2 - 30, this.field_146289_q.func_78256_a(d) + 8, 20, d, 0));
                                                        } else {
                                                            fnst = "Family name: ";
                                                            fnstw = this.field_146289_q.func_78256_a(fnst);
                                                            var8.func_78276_b(fnst, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 10 + 2, 0);
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(80, guiLeft + xSize / 2 - 85, guiTop + (ySize + 1) / 2 + 35, 170, 20, "Create Family", 0));
                                                            fn = this.fnba == 0 ? "before" : "after";
                                                            fnw = this.field_146289_q.func_78256_a(fn);
                                                            fns = "Family name will be ";
                                                            fnsw = this.field_146289_q.func_78256_a(fns);
                                                            fnf = " player name";
                                                            fnfw = this.field_146289_q.func_78256_a(fns);
                                                            var8.func_78276_b(fns, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74, 0);
                                                            this.field_146292_n.add(new JRMCoreGuiButtons01(77, guiLeft + xSize / 2 - 122 + fnsw, guiTop + (ySize + 1) / 2 - 74 - 1, fnw, fn, 0));
                                                            var8.func_78276_b(fnf + ".", guiLeft + xSize / 2 - 122 + (fnsw + fnw + fnfw > 250 ? 0 : fnw + fnsw + 1), guiTop + (ySize + 1) / 2 - 74 + (fnsw + fnw + fnfw > 250 ? 10 : 0), 0);
                                                            if (this.inputField == null) {
                                                                this.name(var8, guiLeft + 5 + fnstw, guiTop + 16);
                                                            } else {
                                                                gen = "Random Name";
                                                                this.field_146292_n.add(new JRMCoreGuiButtons00(97, guiLeft + xSize / 2 + 10, guiTop + (ySize + 1) / 2 - 50, this.field_146289_q.func_78256_a(gen) + 8, 20, gen, 0));
                                                            }
                                                            if (this.inputField != null) {
                                                                this.inputField.func_146194_f();
                                                                FamilyCCharGui.famNam = this.inputField.func_146179_b();
                                                            }
                                                        }
                                                        txtng = false;
                                                    } else {
                                                        if (FamilyCCharGui.inv == 4 || FamilyCCharGui.inv == 5 || FamilyCCharGui.inv == 6) {
                                                            h = "";
                                                            hi = 0;
                                                            if (FamilyCH.famMem != null && FamilyCH.famMem.length > 0) {
                                                                for (pl = 0; pl < FamilyCH.famMem.length; ++pl) {
                                                                    n = FamilyCH.famMem[pl];
                                                                    if (n.contains("!")) {
                                                                        fm2 = n.split("!");
                                                                        fm = fm2[1].split(",");
                                                                    } else {
                                                                        fm = n.split(",");
                                                                    }
                                                                    b = false;
                                                                    for (f1 = 0; f1 < fm.length; ++f1) {
                                                                        n2 = fm[f1];
                                                                        n3 = n2.split(":");
                                                                        if (n3[0].length() <= 2 || f1 >= 2) continue;
                                                                        if (hi < 2) {
                                                                            h = h + "," + n3[0];
                                                                        }
                                                                        ++hi;
                                                                    }
                                                                }
                                                            }
                                                            h = h.length() > 0 ? h.substring(1) : "";
                                                            ha = h.split(",");
                                                            i = 0;
                                                            i2 = 0;
                                                            s = "";
                                                            if (FamilyCH.famMem != null && FamilyCH.famMem.length > 0) {
                                                                for (pl = 0; pl < FamilyCH.famMem.length; ++pl) {
                                                                    n = FamilyCH.famMem[pl];
                                                                    if (n.contains("!")) {
                                                                        fm2 = n.split("!");
                                                                        fm = fm2[1].split(",");
                                                                    } else {
                                                                        fm = n.split(",");
                                                                    }
                                                                    v0 = sfp = FamilyCH.FamP.length() > 0 ? Integer.parseInt(FamilyCH.FamP) : -1;
                                                                    if (fm.length <= 1 || (FamilyCCharGui.inv != 5 && FamilyCCharGui.inv != 6 || !fm[0].equals(this.field_146297_k.field_71439_g.func_70005_c_()) && !fm[1].equals(this.field_146297_k.field_71439_g.func_70005_c_())) && (FamilyCCharGui.inv != 4 || pl <= 1)) continue;
                                                                    for (f1 = 0; f1 < fm.length; ++f1) {
                                                                        if (i2 > 14 + this.ipg * 14 || i2 < 0 + this.ipg * 14) continue;
                                                                        n2 = fm[f1];
                                                                        n3 = n2.split(":");
                                                                        nh = true;
                                                                        for (hi2 = 0; hi2 < ha.length; ++hi2) {
                                                                            if (!ha[hi2].equals(n3[0])) continue;
                                                                            nh = false;
                                                                        }
                                                                        if (FamilyCCharGui.inv == 4 && (pl > 1 || f1 > 1 || nh) && n3[0].length() > 1 && !n3[0].equals(this.field_146297_k.field_71439_g.func_70005_c_()) || FamilyCCharGui.inv == 5 && f1 == 1 && n3[0].length() > 1 && !n3[0].equals(this.field_146297_k.field_71439_g.func_70005_c_()) || FamilyCCharGui.inv == 6 && f1 > 1 && n3[0].length() > 1 && !n3[0].equals(this.field_146297_k.field_71439_g.func_70005_c_())) {
                                                                            this.field_146292_n.add(new JRMCoreGuiButtons01(2000 + i, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + i * 10 - this.ipg * 14 * 10, this.field_146289_q.func_78256_a(n3[0]), n3[0], f1 == 0 || f1 == 1 ? 0x646464 : 0));
                                                                            s = s + ":" + n3[0];
                                                                            ++i;
                                                                        }
                                                                        ++i2;
                                                                    }
                                                                }
                                                            }
                                                            this.dfu = s = s.length() > 0 ? s.substring(1) : s;
                                                            if (FamilyCH.famMem.length > 1 && i2 > 14 + this.ipg * 14) {
                                                                n = "Next";
                                                                this.field_146292_n.add(new JRMCoreGuiButtons00(88, guiLeft + xSize / 2 + 130, guiTop + (ySize + 1) / 2 + 15, this.field_146289_q.func_78256_a(n) + 8, 20, n, 0));
                                                            }
                                                            if (this.ipg != 0) {
                                                                p = "Prev";
                                                                pw = this.field_146289_q.func_78256_a(p) + 8;
                                                                this.field_146292_n.add(new JRMCoreGuiButtons00(89, guiLeft + xSize / 2 - 130 - pw, guiTop + (ySize + 1) / 2 + 15, pw, 20, p, 0));
                                                            }
                                                            this.famMems = i;
                                                        }
                                                        if (FamilyCCharGui.inv == 1 || FamilyCCharGui.inv == 2) {
                                                            i2 = 0;
                                                            gn = false;
                                                            gp = false;
                                                            if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && FamilyCH.famNams != null && FamilyCH.famNams.length >= JRMCoreH.plyrs.length) {
                                                                for (i = 0; i < JRMCoreH.plyrs.length; ++i) {
                                                                    if (i2 > 14 + this.ipg * 14 || i2 < 0 + this.ipg * 14 || FamilyCH.famNams[i].length() >= 2 || (e = this.field_146297_k.field_71441_e.func_72924_a(JRMCoreH.plyrs[i])) == null) continue;
                                                                    n = e.func_70005_c_();
                                                                    if (!n.equals(this.field_146297_k.field_71439_g.func_70005_c_())) {
                                                                        this.field_146292_n.add(new JRMCoreGuiButtons01(2000 + i, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + i2 * 10 - this.ipg * 14 * 10, this.field_146289_q.func_78256_a(n), n, 0));
                                                                    }
                                                                    ++i2;
                                                                }
                                                            }
                                                            if (JRMCoreH.plyrs.length > 14 + this.ipg * 14) {
                                                                n = "Next";
                                                                this.field_146292_n.add(new JRMCoreGuiButtons00(88, guiLeft + xSize / 2 + 130, guiTop + (ySize + 1) / 2 + 15, this.field_146289_q.func_78256_a(n) + 8, 20, n, 0));
                                                            }
                                                            if (this.ipg != 0) {
                                                                p = "Prev";
                                                                pw = this.field_146289_q.func_78256_a(p) + 8;
                                                                this.field_146292_n.add(new JRMCoreGuiButtons00(89, guiLeft + xSize / 2 - 130 - pw, guiTop + (ySize + 1) / 2 + 15, pw, 20, p, 0));
                                                            }
                                                        }
                                                        if (FamilyCCharGui.inv == 0) {
                                                            if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && FamilyCH.famNams != null) {
                                                                for (pl = 0; pl < (JRMCoreH.plyrs.length > FamilyCH.famNams.length ? FamilyCH.famNams.length : JRMCoreH.plyrs.length); ++pl) {
                                                                    if (!JRMCoreH.plyrs[pl].equals(this.field_146297_k.field_71439_g.func_70005_c_()) || FamilyCH.famNams[pl].length() <= 2) continue;
                                                                    s1 = FamilyCH.famNams[pl];
                                                                    v1 = s2 = s1.contains(",") != false ? s1.toString().split(",") : null;
                                                                    if (s2 == null) continue;
                                                                    var8.func_78276_b(s2[0] + " Family", guiLeft + 15, guiTop + 5, 0);
                                                                }
                                                            }
                                                            i = 0;
                                                            i2 = 0;
                                                            head = false;
                                                            main = false;
                                                            parent = false;
                                                            havechild = false;
                                                            single = false;
                                                            anyone = false;
                                                            infam = false;
                                                            h = "";
                                                            hi = 0;
                                                            if (FamilyCH.famMem != null && FamilyCH.famMem.length > 0) {
                                                                for (pl = 0; pl < FamilyCH.famMem.length; ++pl) {
                                                                    n = FamilyCH.famMem[pl];
                                                                    if (n.contains("!")) {
                                                                        fm2 = n.split("!");
                                                                        fm = fm2[1].split(",");
                                                                    } else {
                                                                        fm = n.split(",");
                                                                    }
                                                                    for (f1 = 0; f1 < fm.length; ++f1) {
                                                                        n2 = fm[f1];
                                                                        n3 = n2.split(":");
                                                                        if (n3[0].length() > 2 && i2 <= 13 + this.ipg * 13 && i2 >= 0 + this.ipg * 13) {
                                                                            var8.func_78276_b(n3[0] + (n3[0].equals(this.field_146297_k.field_71439_g.func_70005_c_()) != false ? " <" : ""), guiLeft + 5, guiTop + 16 + i2 * 10 - this.ipg * 13 * 10, f1 == 0 || f1 == 1 ? 0x646464 : 0);
                                                                            ++i2;
                                                                        }
                                                                        if (n3[0].length() > 2 && f1 < 2) {
                                                                            if (hi < 2 && !h.equalsIgnoreCase("," + n3[0])) {
                                                                                h = h + "," + n3[0];
                                                                            }
                                                                            ++hi;
                                                                        }
                                                                        b = false;
                                                                        if (n3[0].equals(this.field_146297_k.field_71439_g.func_70005_c_())) {
                                                                            b = true;
                                                                            infam = true;
                                                                        }
                                                                        if (n3[0].equals("Player729")) {
                                                                            // empty if block
                                                                        }
                                                                        if (!head && pl == 0 && f1 < 2 && b) {
                                                                            head = true;
                                                                        }
                                                                        if (!main && f1 == 0 && b && fm[1].length() > 2) {
                                                                            main = true;
                                                                        }
                                                                        if (!parent && b) {
                                                                            parent = true;
                                                                        }
                                                                        if (!havechild && fm.length > 2 && f1 < 2 && b) {
                                                                            havechild = true;
                                                                        }
                                                                        if (!single && b && (f1 == 0 ? fm[1].length() < 2 : f1 != 1 || fm[0].length() < 2)) {
                                                                            single = true;
                                                                        }
                                                                        anyone = true;
                                                                    }
                                                                }
                                                            }
                                                            h = h.length() > 0 ? h.substring(1) : "";
                                                            ha = h.split(",");
                                                            for (hi2 = 0; hi2 < ha.length; ++hi2) {
                                                                if (!ha[hi2].equals(this.field_146297_k.field_71439_g.func_70005_c_())) continue;
                                                                head = true;
                                                            }
                                                            i = -1;
                                                            if (infam) {
                                                                if (single) {
                                                                    in = "Propose list";
                                                                    inw = this.field_146289_q.func_78256_a(in) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(78, guiLeft + xSize / 2 + 10 + 60 - inw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(in) + 8, 20, in, 0));
                                                                }
                                                                if (main) {
                                                                    k = "Force Divorce";
                                                                    kw = this.field_146289_q.func_78256_a(k) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(92, guiLeft + xSize / 2 + 10 + 60 - kw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(k) + 8, 20, k, 0));
                                                                }
                                                                if (parent) {
                                                                    s = "Adopt list";
                                                                    sw = this.field_146289_q.func_78256_a(s) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(79, guiLeft + xSize / 2 + 10 + 60 - sw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(s) + 8, 20, s, 0));
                                                                    if (havechild) {
                                                                        k = "Unadopt child";
                                                                        kw = this.field_146289_q.func_78256_a(k) + 8;
                                                                        this.field_146292_n.add(new JRMCoreGuiButtons00(93, guiLeft + xSize / 2 + 10 + 60 - kw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(k) + 8, 20, k, 0));
                                                                    }
                                                                }
                                                                if (head) {
                                                                    k = "Disinherit";
                                                                    kw = this.field_146289_q.func_78256_a(k) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(91, guiLeft + xSize / 2 + 10 + 60 - kw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(k) + 8, 20, k, 0));
                                                                }
                                                                if (!single) {
                                                                    l = "Divorce";
                                                                    lw = this.field_146289_q.func_78256_a(l) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(85, guiLeft + xSize / 2 + 10 + 60 - lw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(l) + 8, 20, l, 0));
                                                                } else {
                                                                    l = "Leave Family";
                                                                    lw = this.field_146289_q.func_78256_a(l) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(85, guiLeft + xSize / 2 + 10 + 60 - lw / 2, guiTop + (ySize + 1) / 2 - 70 + ++i * 21, this.field_146289_q.func_78256_a(l) + 8, 20, l, 0));
                                                                }
                                                                if (FamilyCH.famMem.length > 1 && i2 > 14 + this.ipg * 14) {
                                                                    n = "Next";
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(88, guiLeft + xSize / 2 + 130, guiTop + (ySize + 1) / 2 + 15, this.field_146289_q.func_78256_a(n) + 8, 20, n, 0));
                                                                }
                                                                if (this.ipg != 0) {
                                                                    p = "Prev";
                                                                    pw = this.field_146289_q.func_78256_a(p) + 8;
                                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(89, guiLeft + xSize / 2 - 130 - pw, guiTop + (ySize + 1) / 2 + 15, pw, 20, p, 0));
                                                                }
                                                                if (main) {
                                                                    // empty if block
                                                                }
                                                            }
                                                        }
                                                        if (FamilyCCharGui.inv != 0) {
                                                            s = "Back";
                                                            sw = this.field_146289_q.func_78256_a(s) + 8;
                                                            this.field_146292_n.add(new JRMCoreGuiButtons00(90, guiLeft + xSize / 2 - 130 - sw, guiTop + (ySize + 1) / 2 + 40, sw, 20, s, 0));
                                                        }
                                                    }
                                                }
                                            }
                                            if (this.jfc == 0) {
                                                // empty if block
                                            }
                                            if (this.jfc == 1) {
                                                xSize = 256;
                                                ySize = 159;
                                                guiLeft = (this.field_146294_l - xSize) / 2;
                                                guiTop = (this.field_146295_m - ySize) / 2;
                                                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                                guiLocation = new ResourceLocation(wish);
                                                this.field_146297_k.field_71446_o.func_110577_a(guiLocation);
                                                this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                                                this.field_146292_n.add(new JRMCoreGuiButtonsTab(62, guiLeft + xSize / 2 - 10, guiTop + (ySize + 1) / 2 - 99, 60, 20, "Family", 0));
                                                this.field_146292_n.add(new JRMCoreGuiButtonsTab(60, guiLeft + xSize / 2 - 110, guiTop + (ySize + 1) / 2 - 99, 60, 20, "Status", 1));
                                                this.field_146292_n.add(new JRMCoreGuiButtons00(10, guiLeft + xSize / 2 - 150, guiTop + ySize / 2 + 65, 20, 20, "X", 0));
                                                if (JRMCoreH.proc != null && JRMCoreH.proc.contains(";")) {
                                                    pr = JRMCoreH.proc.split(";");
                                                    if (pr.length > 3) {
                                                        i = Integer.parseInt(pr[4]);
                                                        s = "Pregnancy will be over in " + (i > 12 ? i / 12 + 1 + " minutes" : i * 5 + " seconds") + ".";
                                                        var8.func_78276_b(s, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 60, 0);
                                                        s = "Child name will be: " + pr[3] + ".";
                                                        var8.func_78276_b(s, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 60 + 10, 0);
                                                        s = "Childs father is: " + pr[2] + ".";
                                                        var8.func_78276_b(s, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 60 + 20, 0);
                                                    }
                                                } else if (JRMCoreH.proc != null && JRMCoreH.proc.length() > 2 && !JRMCoreH.proc.contains(";")) {
                                                    n = JRMCoreH.proc;
                                                    s = "Procreation offer from " + n;
                                                    var8.func_78276_b(s, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 60, 0);
                                                    a = "Accept";
                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(94, guiLeft + xSize / 2 + 10 - 80, guiTop + (ySize + 1) / 2 - 50 + 65, this.field_146289_q.func_78256_a(a) + 8, 20, a, 0));
                                                    d = "Decline";
                                                    this.field_146292_n.add(new JRMCoreGuiButtons00(95, guiLeft + xSize / 2 + 10 - 20, guiTop + (ySize + 1) / 2 - 50 + 65, this.field_146289_q.func_78256_a(d) + 8, 20, d, 0));
                                                    fnst = "Child name: ";
                                                    fnstw = this.field_146289_q.func_78256_a(fnst);
                                                    var8.func_78276_b(fnst, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 70, 0);
                                                    if (this.inputField == null) {
                                                        this.name(var8, guiLeft + 5 + fnstw, guiTop + 75 + 1);
                                                    } else {
                                                        gen /* !! */  = "Random Name";
                                                        gw = this.field_146289_q.func_78256_a((String)gen /* !! */ ) + 8;
                                                        this.field_146292_n.add(new JRMCoreGuiButtons00(97, guiLeft + xSize / 2 + 10 + 30, guiTop + (ySize + 1) / 2 - 50 + 45, gw, 20, (String)gen /* !! */ , 0));
                                                    }
                                                    if (this.inputField != null) {
                                                        this.inputField.func_146194_f();
                                                        FamilyCCharGui.chiNam = this.inputField.func_146179_b();
                                                    }
                                                    txtng = false;
                                                } else if (JRMCoreH.targ != null && JRMCoreH.proc != null && !JRMCoreH.proc.contains(";")) {
                                                    G = 0;
                                                    R = 0;
                                                    if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreH.dnn(1)) {
                                                        for (pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                                                            if (!JRMCoreH.plyrs[pl].equals(JRMCoreH.targ.func_70005_c_())) continue;
                                                            s = JRMCoreH.data1[pl].split(";");
                                                            G = JRMCoreH.dnsGender(s[1]);
                                                            R = Integer.parseInt(s[0]);
                                                        }
                                                    }
                                                    if (G != JRMCoreH.dnsGender(JRMCoreH.dns) && FamilyCH.procWith(JRMCoreH.Race, R)) {
                                                        p = "Procreation";
                                                        pw = this.field_146289_q.func_78256_a(p) + 8;
                                                        this.field_146292_n.add(new JRMCoreGuiButtons00(96, guiLeft + xSize / 2 + 10 + 60 - pw / 2, guiTop + (ySize + 1) / 2 - 50 + 45, this.field_146289_q.func_78256_a(p) + 8, 20, p, 0));
                                                    }
                                                }
                                                S = 0;
                                                Stat = 0;
                                                par = "";
                                                chi = "";
                                                mer = "";
                                                id = "";
                                                if (JRMCoreH.targ == null) {
                                                    id = JRMCoreH.jrmcPDtaDNS(this.field_146297_k.field_71439_g.func_70005_c_());
                                                } else if (JRMCoreH.targ != null) {
                                                    id = JRMCoreH.jrmcPDtaDNS(JRMCoreH.targ.func_70005_c_());
                                                }
                                                S = JRMCoreH.dnsGender(id);
                                                if (JRMCoreH.p != null && JRMCoreH.p.length > 0) {
                                                    for (String n : JRMCoreH.p) {
                                                        m = n.split(";");
                                                        if (JRMCoreH.targ == null && this.field_146297_k.field_71439_g.func_70005_c_().equals(m[0])) {
                                                            Stat = Integer.parseInt(m[2]);
                                                            par = m[3];
                                                            chi = m[4];
                                                            mer = m[5];
                                                        }
                                                        if (JRMCoreH.targ == null || !JRMCoreH.targ.func_70005_c_().equals(m[0])) continue;
                                                        Stat = Integer.parseInt(m[2]);
                                                        par = m[3];
                                                        chi = m[4];
                                                        mer = m[5];
                                                    }
                                                }
                                                for (String n : c = FamilyCCharGui.children.split(";")) {
                                                    c1 = n.split(":");
                                                    if (c1.length <= 2) continue;
                                                    chi = chi + ", " + c1[2] + (c1.length > 3 ? " " + JRMCoreH.clgy + "(" + c1[3].replace(",", ", ") + ")" + JRMCoreH.clb : "");
                                                }
                                                if (chi.length() > 2) {
                                                    chi = chi.substring(2);
                                                }
                                                targ = JRMCoreH.targ != null;
                                                nam = targ != false ? JRMCoreH.targ.func_70005_c_() : this.field_146297_k.field_71439_g.func_70005_c_();
                                                var8.func_78276_b("Name: \u00a78" + nam + " \u00a70Gender: \u00a78" + (S == 0 ? "Male" : "Female"), guiLeft + 5, guiTop + 5, 0);
                                                i = 0;
                                                if (JRMCoreH.JYC()) {
                                                    A = 0.0f;
                                                    if (JYearsCH.p != null && JYearsCH.p.length > 0) {
                                                        for (String n : JYearsCH.p) {
                                                            m = n.split(";");
                                                            if (!nam.equals(m[0])) continue;
                                                            A = Float.parseFloat(m[1]);
                                                        }
                                                    }
                                                    var8.func_78276_b("Time Lived: \u00a78" + (int)(A <= 46.0f ? A : A - (float)((int)(A / 46.0f)) * 46.0f) + " Days " + (A > 46.0f ? "and " + (int)(A / 46.0f) + " Minecraft Years" : ""), guiLeft + 5, guiTop + 15 + i * 10, 0);
                                                    ++i;
                                                }
                                                if (!targ && chi.length() > 3) {
                                                    ch = "Children: \u00a78" + chi;
                                                    sa = new String[]{ch};
                                                    ++i;
                                                    i = JRMCoreH.lbs(sa, i, var8, guiLeft, guiTop);
                                                }
                                            }
                                            if (this.jfc != 2) break block115;
                                            xSize = 256;
                                            ySize = 159;
                                            guiLeft = (this.field_146294_l - xSize) / 2;
                                            guiTop = (this.field_146295_m - ySize) / 2;
                                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                            guiLocation = new ResourceLocation(wish);
                                            this.field_146297_k.field_71446_o.func_110577_a(guiLocation);
                                            this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
                                            this.field_146292_n.add(new JRMCoreGuiButtons00(10, guiLeft + xSize / 2 - 150, guiTop + ySize / 2 + 65, 20, 20, "X", 0));
                                            if (FamilyCCharGui.dtcdad.length() <= 2) break block116;
                                            if (FamilyCCharGui.dtcdad.equalsIgnoreCase(this.field_146297_k.field_71439_g.func_70005_c_())) ** GOTO lbl-1000
                                        }
                                        if (FamilyCCharGui.dtcmom.length() > 2) {
                                            ** if (!FamilyCCharGui.dtcmom.equalsIgnoreCase((String)this.field_146297_k.field_71439_g.func_70005_c_())) goto lbl-1000
                                        }
                                        ** GOTO lbl-1000
lbl-1000:
                                        // 2 sources

                                        {
                                            v2 = true;
                                            ** GOTO lbl435
                                        }
lbl-1000:
                                        // 2 sources

                                        {
                                            v2 = parents = false;
                                        }
lbl435:
                                        // 2 sources

                                        if (JRMCoreH.targNPC != null) break block117;
                                        this.field_146297_k.field_71439_g.func_71053_j();
                                        FamilyCCharGui.inv = 0;
                                        break block118;
                                    }
                                    if (!parents && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) break block118;
                                    if (FamilyCCharGui.inv != -1 || !(JRMCoreH.targNPC instanceof EntityNPC)) break block119;
                                    fnst = "Child name: ";
                                    fnstw = this.field_146289_q.func_78256_a(fnst);
                                    var8.func_78276_b(fnst, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + 70, 0);
                                    s1 = JRMCoreH.targNPC instanceof EntityNPC != false ? ((EntityNPC)JRMCoreH.targNPC).getNam() : "";
                                    s1w = this.field_146289_q.func_78256_a(s1);
                                    this.defaultInputFieldText = s1;
                                    a = "Name the child";
                                    this.field_146292_n.add(new JRMCoreGuiButtons00(6, guiLeft + xSize / 2 + 10 - 80, guiTop + (ySize + 1) / 2 - 50 + 65, this.field_146289_q.func_78256_a(a) + 8, 20, a, 0));
                                    if (this.defaultInputFieldText.length() > 2) {
                                        if (this.inputField == null) {
                                            this.name(var8, guiLeft + 5 + fnstw, guiTop + 75 + 1);
                                        } else {
                                            gen /* !! */  = "Random Name";
                                            gw = this.field_146289_q.func_78256_a((String)gen /* !! */ ) + 8;
                                            this.field_146292_n.add(new JRMCoreGuiButtons00(97, guiLeft + xSize / 2 + 10 + 30, guiTop + (ySize + 1) / 2 - 50 + 45, gw, 20, (String)gen /* !! */ , 0));
                                        }
                                        if (this.inputField != null) {
                                            this.inputField.func_146194_f();
                                            FamilyCCharGui.chiNam = this.inputField.func_146179_b();
                                        }
                                    }
                                    txtng = false;
                                    break block118;
                                }
                                if (FamilyCCharGui.inv != 0 || !(JRMCoreH.targNPC instanceof EntityNPC)) break block120;
                                i = 0;
                                npc = (EntityNPC)JRMCoreH.targNPC;
                                s1 = JRMCoreH.targNPC instanceof EntityNPC != false ? ((EntityNPC)JRMCoreH.targNPC).getNam() : "Child";
                                s1w = this.field_146289_q.func_78256_a(s1);
                                var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 20 - 15, guiTop + (ySize + 1) / 2 - 70 + i * 15 + 4 - 5, 0);
                                v3 = new StringBuilder().append("Hi ");
                                if (!FamilyCCharGui.dtcmom.equalsIgnoreCase(FamilyCCharGui.dtcdad)) break block121;
                                v4 = JRMCoreH.dnsGender(JRMCoreH.dns) == 0 ? "dad" : "mom";
                                break block122;
                            }
                            if (FamilyCCharGui.dtcmom.length() <= 2) break block123;
                            if (!FamilyCCharGui.dtcmom.equalsIgnoreCase(this.field_146297_k.field_71439_g.func_70005_c_())) break block123;
                            v4 = "mom";
                            break block122;
                        }
                        if (FamilyCCharGui.dtcdad.length() <= 2) ** GOTO lbl-1000
                        if (FamilyCCharGui.dtcdad.equalsIgnoreCase(this.field_146297_k.field_71439_g.func_70005_c_())) {
                            v4 = "dad";
                        } else lbl-1000:
                        // 2 sources

                        {
                            v4 = "whoever you are";
                        }
                    }
                    s1 = v3.append(v4).append(" what can I do for you.").toString();
                    s1w = this.field_146289_q.func_78256_a(s1);
                    var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 20 - 15, guiTop + (ySize + 1) / 2 - 70 + i * 15 + 4 - 5 + 10, 0);
                    ++i;
                    v5 = trgtnm = FamilyCCharGui.flwTrgtNm.length() > 2 ? FamilyCCharGui.flwTrgtNm : "a target";
                    s1 = FamilyCCharGui.dtcf.contains("0") != false ? "Don't follow" : (FamilyCCharGui.dtcf.contains("1") != false ? "Follow Dad" : (FamilyCCharGui.dtcf.contains("2") != false ? "Follow Mom" : "Follow " + trgtnm));
                    s2w = this.field_146289_q.func_78256_a(s1);
                    CanFollow = true;
                    if (CanFollow) {
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(-2, guiLeft + xSize / 2 - 125, guiTop + (ySize + 1) / 2 - 65 + i * 15, "<"));
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(2, guiLeft + xSize / 2 - 125 + 20 + s2w + 4, guiTop + (ySize + 1) / 2 - 65 + i * 15, ">"));
                        var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 17, guiTop + (ySize + 1) / 2 - 65 + i * 15 + 1, 0);
                        if (FamilyCCharGui.dtcf.contains("3")) {
                            s1 = "Select Target";
                            s1w = this.field_146289_q.func_78256_a(s1);
                            this.field_146292_n.add(new JRMCoreGuiButtons00(5, guiLeft + xSize / 2 + 70 - s1w + s2w + 4, guiTop + (ySize + 1) / 2 - 65 + i * 15 - 2, s1w + 8, 20, s1, 0));
                        }
                        ++i;
                    }
                    s1 = FamilyCCharGui.dtca.contains("0") != false ? "Defensive" : "Aggressive";
                    s1w = this.field_146289_q.func_78256_a(s1);
                    CanAggro = true;
                    if (CanAggro) {
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(-3, guiLeft + xSize / 2 - 125, guiTop + (ySize + 1) / 2 - 65 + i * 15, "<"));
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(3, guiLeft + xSize / 2 - 125 + 20 + s1w + 4, guiTop + (ySize + 1) / 2 - 65 + i * 15, ">"));
                        var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 17, guiTop + (ySize + 1) / 2 - 65 + i * 15 + 1, 0);
                        ++i;
                    }
                    s1 = FamilyCCharGui.dtcd.contains("0") != false ? "Don't Drop Equipment" : "Drop Equipment";
                    s1w = this.field_146289_q.func_78256_a(s1);
                    CanDrop = true;
                    if (CanDrop) {
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(-4, guiLeft + xSize / 2 - 125, guiTop + (ySize + 1) / 2 - 65 + i * 15, "<"));
                        this.field_146292_n.add(new JRMCoreGuiButtonsA2(4, guiLeft + xSize / 2 - 125 + 20 + s1w + 4, guiTop + (ySize + 1) / 2 - 65 + i * 15, ">"));
                        var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 17, guiTop + (ySize + 1) / 2 - 65 + i * 15 + 1, 0);
                        ++i;
                    }
                    nr = 6;
                    attrbts = new int[6];
                    attr = npc.getAttrbts().split(":");
                    for (i1 = 0; i1 < 6; ++i1) {
                        attrbts[i1] = Integer.parseInt(attr[i1]);
                    }
                    for (i1 = 0; i1 < JRMCoreH.attrInit[1].length; ++i1) {
                        s1 = JRMCoreH.trl("jrmc", JRMCoreH.attrNms[1][i1]) + ": " + attrbts[i1];
                        nr += JRMCoreH.txt(s1, "", 0, true, guiLeft + xSize / 2 - 125 + 10, guiTop + (ySize + 1) / 2 - 65 + nr * 10, 0);
                    }
                    nr = 6;
                    dmgmin = (int)((float)(attrbts[0] * 1) + (float)attrbts[3] * 0.5f * 50.0f * 0.02f);
                    dmgmax = (int)((float)(attrbts[0] * 3) + (float)attrbts[3] * 0.5f * 50.0f * 0.02f);
                    s1 = "Melee: " + dmgmin + "-" + dmgmax + " dmg";
                    nr += JRMCoreH.txt(s1, "", 0, true, guiLeft + xSize / 2, guiTop + (ySize + 1) / 2 - 65 + nr * 10, 0);
                    ++nr;
                    Stamina = attrbts[2] * 2;
                    add = (float)Stamina * ((float)JRMCoreConfig.hRgnRt * 0.5f);
                    rate = (int)(add < 1.0f ? 1.0f : add);
                    s1 = "Health: " + (int)npc.func_110143_aJ() + "/" + attrbts[2] * (JRMCoreH.DBC() != false || JRMCoreH.NC() != false ? 40 : 5) + "+" + JRMCoreH.cldr + rate + "/5s";
                    nr += JRMCoreH.txt(s1, "", 0, true, guiLeft + xSize / 2, guiTop + (ySize + 1) / 2 - 65 + nr * 10, 0);
                    s1 = "Tell";
                    s1w = this.field_146289_q.func_78256_a(s1);
                    if (parents) {
                        this.field_146292_n.add(new JRMCoreGuiButtons00(1, guiLeft + xSize / 2 + 130, guiTop + (ySize + 1) / 2 - 50 + 115, s1w + 8, 20, s1, 0));
                    }
                    break block118;
                }
                if (FamilyCCharGui.inv == 21) {
                    i2 = 0;
                    gn = false;
                    gp = false;
                    if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0) {
                        for (i = 0; i < JRMCoreH.plyrs.length; ++i) {
                            if (i2 > 14 + this.ipg * 14 || i2 < 0 + this.ipg * 14 || (e = this.field_146297_k.field_71441_e.func_72924_a(JRMCoreH.plyrs[i])) == null || (n = e.func_70005_c_()).equals(this.field_146297_k.field_71439_g.func_70005_c_())) continue;
                            this.field_146292_n.add(new JRMCoreGuiButtons01(2000 + i, guiLeft + xSize / 2 - 122, guiTop + (ySize + 1) / 2 - 74 + i2 * 10 - this.ipg * 14 * 10, this.field_146289_q.func_78256_a(n), n, 0));
                            ++i2;
                        }
                    }
                    if (i2 == 0) {
                        s1 = "No other players found.";
                        s1w = this.field_146289_q.func_78256_a(s1);
                        var8.func_78276_b(s1, guiLeft + xSize / 2 - 125 + 20, guiTop + (ySize + 1) / 2 - 70 + 4, 0);
                    }
                    if (JRMCoreH.plyrs.length > 14 + this.ipg * 14) {
                        n = "Next";
                        this.field_146292_n.add(new JRMCoreGuiButtons00(88, guiLeft + xSize / 2 + 130, guiTop + (ySize + 1) / 2 + 15, this.field_146289_q.func_78256_a(n) + 8, 20, n, 0));
                    }
                    if (this.ipg != 0) {
                        p = "Prev";
                        pw = this.field_146289_q.func_78256_a(p) + 8;
                        this.field_146292_n.add(new JRMCoreGuiButtons00(89, guiLeft + xSize / 2 - 130 - pw, guiTop + (ySize + 1) / 2 + 15, pw, 20, p, 0));
                    }
                }
            }
            this.field_146292_n.add(new JRMCoreGuiButtons00(10, guiLeft + xSize / 2 - 150, guiTop + ySize / 2 + 65, 20, 20, "X", 0));
        }
        if (txtng) {
            this.inputField = null;
        }
        super.func_73863_a(x, y, f);
    }

    public boolean func_73868_f() {
        return false;
    }

    public void current(String var35, int posx, int posy, FontRenderer var8, int var6, int var7) {
        int wid = var8.func_78256_a(var35) / 2;
        int posX = var6 / 2 + posx - wid;
        int posY = var7 / 2 + posy;
        var8.func_78276_b(var35, posX + 1, posY, 0);
        var8.func_78276_b(var35, posX - 1, posY, 0);
        var8.func_78276_b(var35, posX, posY + 1, 0);
        var8.func_78276_b(var35, posX, posY - 1, 0);
        var8.func_78276_b(var35, posX, posY, 8388564);
    }
}

