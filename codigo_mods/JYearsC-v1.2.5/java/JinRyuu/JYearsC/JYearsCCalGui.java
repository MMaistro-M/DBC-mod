/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.JYearsCH;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.p.YC.JYearsCP;
import JinRyuu.JYearsC.JYearsCGuiButtons00;
import JinRyuu.JYearsC.mod_JYearsC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class JYearsCCalGui
extends GuiScreen {
    public int jyc = 0;
    public static String[] dayNames = JYearsCH.dayNames;
    public static String[] monthNames = JYearsCH.monthNames;
    public static String[] monthInDays = JYearsCH.monthInDays;
    public static int[] mid = JYearsCH.mID;
    public int years = JYearsCH.y;
    public int d = JYearsCH.d;
    public int m = JYearsCH.m;
    public static int count = 0;
    public static int warn = 0;
    public static int startcount = 0;
    private String Process = "Something is Wrong";
    private int wid = 0;
    private int hei = 0;
    private String textureFile = "jinryuudragonbc:sagas.png";

    public JYearsCCalGui(int w) {
        this.jyc = w;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        this.field_146292_n.add(new JYearsCGuiButtons00(0, posX - 190, posY - 80, 60, 20, "ID Card"));
        this.field_146292_n.add(new JYearsCGuiButtons00(1, posX - 190, posY - 55, 60, 20, "Calendar"));
        this.field_146292_n.add(new JYearsCGuiButtons00(2, posX - 190, posY - 30, 60, 20, "The Days"));
        this.field_146292_n.add(new JYearsCGuiButtons00(10, posX - 150, posY + 65, 20, 20, "X"));
        if (this.jyc == 0) {
            float A = 0.0f;
            if (JYearsCH.p != null && JYearsCH.p.length > 0) {
                for (String n : JYearsCH.p) {
                    String[] m = n.split(";");
                    if (!this.field_146297_k.field_71439_g.getDisplayName().equals(m[0])) continue;
                    A = Float.parseFloat(m[1]);
                }
            }
            if (A > 400.0f) {
                this.field_146292_n.add(new JYearsCGuiButtons00(11, posX + 0, posY + 10, 60, 20, "Rebirth"));
            }
        }
    }

    public Object actionPerformed(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        int selct = par1 - 20;
        boolean KA = false;
        JYearsCGuiButtons00 ret = KA ? new JYearsCGuiButtons00(par1, par2, par3, par4, par5, par6Str) : new JYearsCGuiButtons00(par1, par2, par3, par4, par5, par6Str);
        return ret;
    }

    public void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 10) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 0) {
            this.field_146297_k.field_71439_g.openGui((Object)mod_JYearsC.instance, 0, (World)this.field_146297_k.field_71441_e, (int)this.field_146297_k.field_71439_g.field_70165_t, (int)this.field_146297_k.field_71439_g.field_70163_u, (int)this.field_146297_k.field_71439_g.field_70161_v);
        }
        if (button.field_146127_k == 1) {
            this.field_146297_k.field_71439_g.openGui((Object)mod_JYearsC.instance, 1, (World)this.field_146297_k.field_71441_e, (int)this.field_146297_k.field_71439_g.field_70165_t, (int)this.field_146297_k.field_71439_g.field_70163_u, (int)this.field_146297_k.field_71439_g.field_70161_v);
        }
        if (button.field_146127_k == 2) {
            this.field_146297_k.field_71439_g.openGui((Object)mod_JYearsC.instance, 2, (World)this.field_146297_k.field_71441_e, (int)this.field_146297_k.field_71439_g.field_70165_t, (int)this.field_146297_k.field_71439_g.field_70163_u, (int)this.field_146297_k.field_71439_g.field_70161_v);
        }
        if (button.field_146127_k == 11) {
            JYearsCCalGui.jyc(1);
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    public void player() {
    }

    public static void jyc(int py) {
        int jycdatey = 0;
        int jycdatem = 0;
        int jycdated = 0;
        int jycpy = py;
        String jycp = "";
        PD.sendToServer(new JYearsCP(jycdatey, jycdatem, jycdated, jycp, jycpy));
    }

    public void func_73863_a(int x, int y, float f) {
        ScaledResolution var5 = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.field_146297_k.field_71466_p;
        String wish = "jinryuujyearsc:cal.png";
        int xSize = 256;
        int ySize = 160;
        int guiLeft = (this.field_146294_l - xSize) / 2;
        int guiTop = (this.field_146295_m - ySize) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation guiLocation = new ResourceLocation(wish);
        this.field_146297_k.field_71446_o.func_110577_a(guiLocation);
        this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
        guiLeft += 5;
        guiTop = guiTop + 10 + 5;
        if (this.jyc == 0) {
            float A = 0.0f;
            if (JYearsCH.p != null && JYearsCH.p.length > 0) {
                for (String n : JYearsCH.p) {
                    String[] m = n.split(";");
                    if (!this.field_146297_k.field_71439_g.getDisplayName().equals(m[0])) continue;
                    A = Float.parseFloat(m[1]);
                }
            }
            var8.func_78276_b("Name: \u00a78" + this.field_146297_k.field_71439_g.getDisplayName(), guiLeft, guiTop, 0);
            var8.func_78276_b("Time Lived: \u00a78" + (int)(A <= 46.0f ? A : A - (float)((int)(A / 46.0f)) * 46.0f) + " Days " + (A > 46.0f ? "and " + (int)(A / 46.0f) + " Minecraft Years" : ""), guiLeft, guiTop + 10, 0);
            var8.func_78276_b("Real Years Converted: \u00a78" + (int)(6.0f + A * 10.0f / 46.0f) + " Years", guiLeft, guiTop + 20, 0);
            var8.func_78276_b("Grow Stage: \u00a78" + (A < 23.0f ? "Child" : (A < 46.0f ? "Teen" : "Adult")), guiLeft, guiTop + 30, 0);
            if (A > 400.0f) {
                var8.func_78276_b("Rebirth will cost an Emerald.", guiLeft + 20, guiTop + 65, 0);
            }
        }
        int days = 0;
        int j2 = 0;
        for (int k = 0; k < this.years % dayNames.length + 1; ++k) {
            for (int i = 0; i < mid.length; ++i) {
                if (this.jyc == 2) {
                    var8.func_78276_b(monthNames[i], guiLeft + i * 61, guiTop, 0);
                }
                if (this.jyc == 1) {
                    var8.func_78276_b(monthNames[i], guiLeft + (i == 0 || i == 2 ? 0 : 122), guiTop + (i == 0 || i == 1 ? 0 : 80), 0);
                }
                for (int j = 0; j < mid[i]; ++j) {
                    if (days > 4) {
                        days = 0;
                    }
                    if (i == this.m && j == this.d) {
                        j2 = days;
                    }
                    if (k == this.years % dayNames.length) {
                        ResourceLocation tx;
                        int yS;
                        int xS;
                        if (this.jyc == 1) {
                            xS = 14;
                            yS = 12;
                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                            tx = new ResourceLocation(wish);
                            this.field_146297_k.field_71446_o.func_110577_a(tx);
                            if (i == this.m && j == this.d) {
                                this.func_73729_b(guiLeft - 4 + (i == 0 || i == 2 ? (j < 6 ? j * 14 : (j >= 9 ? j * 14 - 84 - 0 : j * 14 - 84)) : (j < 6 ? 122 + j * 14 : (j >= 9 ? 122 + j * 14 - 84 - 0 : 122 + j * 14 - 84))), guiTop - 2 + 10 + (i == 0 || i == 1 ? (j > 5 ? 10 : 0) : (j > 5 ? 90 : 80)), 0, 176, xS, yS);
                            }
                            var8.func_78276_b("\u00a76" + (j + 1), guiLeft + (i == 0 || i == 2 ? (j < 6 ? j * 14 : (j >= 9 ? j * 14 - 84 - 3 : j * 14 - 84)) : (j < 6 ? 122 + j * 14 : (j >= 9 ? 122 + j * 14 - 84 - 3 : 122 + j * 14 - 84))), guiTop + 10 + (i == 0 || i == 1 ? (j > 5 ? 10 : 0) : (j > 5 ? 90 : 80)), 0);
                        }
                        if (this.jyc == 2) {
                            xS = 64;
                            yS = 10;
                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                            tx = new ResourceLocation(wish);
                            this.field_146297_k.field_71446_o.func_110577_a(tx);
                            if (i == this.m && j == this.d) {
                                this.func_73729_b(guiLeft + i * 61 - 3, guiTop + 10 + j * 8 - 1, 0, 163, xS, yS);
                            }
                            var8.func_78276_b("\u00a76" + (j + 1) + " " + "\u00a78" + dayNames[days], guiLeft + i * 61, guiTop + 10 + j * 8, 0);
                        }
                    }
                    ++days;
                }
            }
        }
        String ks = "";
        if (this.d + 1 == 1) {
            ks = "st";
        }
        if (this.d + 1 == 2) {
            ks = "nd";
        }
        if (this.d + 1 == 3) {
            ks = "rd";
        }
        if (this.d + 1 > 3) {
            ks = "th";
        }
        String s = "\u00a76" + (this.d + 1) + ks + " \u00a70of \u00a78" + monthNames[this.m] + " \u00a70in " + "\u00a78" + this.years;
        s = " \u00a78" + monthNames[this.m] + ": " + "\u00a76" + (this.d + 1) + ks + " \u00a70of " + "\u00a78" + this.years + "\u00a70, " + dayNames[j2];
        var8.func_78276_b(s, guiLeft + (xSize / 2 - s.length()) / 2, guiTop - 10, 0);
        super.func_73863_a(x, y, f);
    }

    public boolean func_73868_f() {
        return false;
    }

    public void current(String var35, int posx, int posy, FontRenderer var8, int var6, int var7) {
        int wid = var8.func_78256_a(var35) / 2;
        int posX = var6 / 2 + posx - wid;
        int posY = var7 / 2 + posy + 8;
        var8.func_78276_b(var35, posX + 1, posY, 0);
        var8.func_78276_b(var35, posX - 1, posY, 0);
        var8.func_78276_b(var35, posX, posY + 1, 0);
        var8.func_78276_b(var35, posX, posY - 1, 0);
        var8.func_78276_b(var35, posX, posY, 8388564);
    }

    public void SagasPage(int var6, int var7) {
        this.textureFile = "jinryuudragonbc:sagas.png";
        this.ScouterRenderBlur(var6, var7);
    }

    public void SagasPrint() {
        this.func_73866_w_();
        Minecraft minecraft = this.field_146297_k;
        WorldClient world = minecraft.field_71441_e;
        EntityClientPlayerMP entityplayersp = minecraft.field_71439_g;
        ScaledResolution scaledresolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
        int width = scaledresolution.func_78326_a() / 2;
        int height = scaledresolution.func_78328_b() / 2;
        int widthplus = 8;
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        RenderHelper.func_74519_b();
        RenderHelper.func_74518_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73732_a(this.field_146297_k.field_71466_p, this.Process, width + this.wid, height + this.hei, 16768306);
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
        this.field_146297_k.field_71446_o.func_110577_a(tx);
        this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    public void ScouterRenderBlur(int par1, int par2) {
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3008);
        ResourceLocation tx = new ResourceLocation(this.textureFile);
        this.field_146297_k.field_71446_o.func_110577_a(tx);
        Tessellator var3 = Tessellator.field_78398_a;
        var3.func_78382_b();
        var3.func_78374_a(0.0, (double)par2, -90.0, 0.0, 1.0);
        var3.func_78374_a((double)par1, (double)par2, -90.0, 1.0, 1.0);
        var3.func_78374_a((double)par1, 0.0, -90.0, 1.0, 0.0);
        var3.func_78374_a(0.0, 0.0, -90.0, 0.0, 0.0);
        var3.func_78381_a();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

