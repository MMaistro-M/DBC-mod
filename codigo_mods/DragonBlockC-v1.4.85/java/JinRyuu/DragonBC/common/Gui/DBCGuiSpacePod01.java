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
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Gui.DBCGuiButtonsC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.DBC.DBCPspacepod1;
import JinRyuu.JRMCore.p.PD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class DBCGuiSpacePod01
extends GuiScreen {
    public World world;
    public EntityPlayer player;
    public Minecraft field_146297_k;
    public int planet20;
    int px;
    int py;
    int pz;
    public static String guired = "jinryuudragonbc:guibutton05.png";
    public static String guigreen = "jinryuudragonbc:guibutton06.png";
    public static String buttontex = "";
    public static int spon = 0;
    public static int spoff = 0;
    public static int spstart = 0;
    public static int sp3 = 0;
    public static int sp2 = 0;
    public static int sp1 = 0;
    public static int ToNamek = 0;
    public static int ToVegeta = 0;
    public static int ToEarth = 0;
    public final int xSizeOfTexture = 400;
    public final int ySizeOfTexture = 300;
    public static int loadtime = 10;
    public static int sc = -1;
    public static int ss = 0;
    public static int ssl = 0;
    public static int ssD = 0;
    public static final int screen_OFF = -1;
    public static final int screen_Loading = 0;
    public static final int screen_MainMenu = 1;
    public static final int screen_Destinations = 2;
    public static final int screen_Travelling = 3;
    public static final int screen_Arrived = 4;
    public static final int panel_left_mode = 0;
    public static final int panel_mid_on = 1;
    public static final int panel_right_ok = 2;
    public static final ResourceLocation Panel = new ResourceLocation("jinryuudragonbc:sppanel.png");
    public static final int[] dests = new int[]{DBCConfig.planetEarth, DBCConfig.planetNamek, DBCConfig.planetVegeta};
    public static final int[] destsTP = new int[]{3, 1, 2};

    public DBCGuiSpacePod01(int x, int y, int z) {
        this.world = DBCClient.mc.field_71441_e;
        this.player = DBCClient.mc.field_71439_g;
        this.field_146297_k = DBCClient.mc;
        this.planet20 = 0;
        this.xSizeOfTexture = 400;
        this.ySizeOfTexture = 300;
        this.px = x;
        this.py = y;
        this.pz = z;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
    }

    public void actionPerformed2(GuiButton button) {
        EntityClientPlayerMP var4 = this.field_146297_k.field_71439_g;
        WorldClient var3 = this.field_146297_k.field_71441_e;
        if (button.field_146127_k == 0) {
            if (spon == 0) {
                spon = 1;
            } else {
                this.spResetCounters();
            }
        }
        if (button.field_146127_k == 1) {
            this.spResetCounters();
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        if (button.field_146127_k == 2) {
            int dbcspacepod1;
            if (sp3 == 0) {
                if (sp2 == 0) {
                    if (sp1 == 0) {
                        this.field_146297_k.field_71439_g.func_71053_j();
                        this.spResetCounters();
                    }
                }
            }
            if (sp3 == 1) {
                if (sp2 == 0) {
                    if (sp1 == 0) {
                        this.field_146297_k.field_71439_g.func_71053_j();
                        ToEarth = 1;
                        ToNamek = 0;
                        ToVegeta = 0;
                        this.spResetCounters();
                    }
                }
            }
            if (sp3 == 0) {
                if (sp2 == 1) {
                    if (sp1 == 0) {
                        this.field_146297_k.field_71439_g.func_71053_j();
                        ToEarth = 0;
                        ToNamek = 0;
                        ToVegeta = 1;
                        this.spResetCounters();
                    }
                }
            }
            if (sp3 == 0) {
                if (sp2 == 0) {
                    if (sp1 == 1) {
                        this.field_146297_k.field_71439_g.func_71053_j();
                        ToEarth = 0;
                        ToNamek = 1;
                        ToVegeta = 0;
                        this.spResetCounters();
                    }
                }
            }
            if (ToEarth == 1) {
                ToEarth = 0;
                ToNamek = 0;
                ToVegeta = 0;
                dbcspacepod1 = 3;
                PD.sendToServer(new DBCPspacepod1((byte)dbcspacepod1));
            }
            if (ToVegeta == 1) {
                ToEarth = 0;
                ToNamek = 0;
                ToVegeta = 0;
                dbcspacepod1 = 2;
                PD.sendToServer(new DBCPspacepod1((byte)dbcspacepod1));
            }
            if (ToNamek == 1) {
                ToEarth = 0;
                ToNamek = 0;
                ToVegeta = 0;
                dbcspacepod1 = 1;
                PD.sendToServer(new DBCPspacepod1((byte)dbcspacepod1));
            }
        }
        if (button.field_146127_k == 3) {
            sp3 = 1;
            sp2 = 0;
            sp1 = 0;
        }
        if (button.field_146127_k == 4) {
            sp3 = 0;
            sp2 = 1;
            sp1 = 0;
        }
        if (button.field_146127_k == 5) {
            sp3 = 0;
            sp2 = 0;
            sp1 = 1;
        }
        if (button.field_146127_k == 6) {
            sp3 = 0;
            sp2 = 0;
            sp1 = 0;
            ToEarth = 0;
            ToNamek = 0;
            ToVegeta = 0;
        }
    }

    public void spResetCounters() {
        spon = 0;
        spoff = 0;
        spstart = 0;
        sp3 = 0;
        sp2 = 0;
        sp1 = 0;
    }

    public void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 1) {
            if (sc == -1) {
                loadtime = 10;
                sc = 0;
            } else {
                sc = -1;
                this.field_146297_k.field_71439_g.func_71053_j();
            }
        } else if (button.field_146127_k == 0) {
            if (sc == 2 && ++ss > ssl) {
                ss = 0;
            }
        } else if (button.field_146127_k == 2) {
            if (sc == 1) {
                sc = 2;
            } else if (sc == 2) {
                switch (ss) {
                    case 0: {
                        sc = 1;
                        break;
                    }
                    case 1: {
                        PD.sendToServer(new DBCPspacepod1(ssD));
                        sc = 1;
                        break;
                    }
                    case 2: {
                        PD.sendToServer(new DBCPspacepod1(ssD));
                        sc = 1;
                        break;
                    }
                }
            }
            ss = 0;
        }
    }

    public void func_73863_a(int x, int y, float f) {
        this.field_146292_n.clear();
        ScaledResolution var5 = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.field_146289_q;
        String spacepod = "jinryuudragonbc:spacepodGUI01.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(spacepod);
        this.field_146297_k.field_71446_o.func_110577_a(tx);
        Tessellator var3 = Tessellator.field_78398_a;
        var3.func_78382_b();
        var3.func_78374_a(0.0, (double)var7, -90.0, 0.0, 1.0);
        var3.func_78374_a((double)var6, (double)var7, -90.0, 1.0, 1.0);
        var3.func_78374_a((double)var6, 0.0, -90.0, 1.0, 0.0);
        var3.func_78374_a(0.0, 0.0, -90.0, 0.0, 0.0);
        var3.func_78381_a();
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        int xSize = 140;
        int ySize = 70;
        int guiLeft = (this.field_146294_l - xSize) / 2;
        int guiTop = (this.field_146295_m - ySize) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation guiLocation = Panel;
        this.field_146297_k.field_71446_o.func_110577_a(guiLocation);
        this.func_73729_b(posX - 90, posY - 44, 0, 0 + (sc == -1 ? 0 : 70), xSize, ySize);
        this.field_146292_n.add(0, new DBCGuiButtonsC(0, posX - 90, posY + 29, 50, 15, "", guigreen));
        this.field_146292_n.add(1, new DBCGuiButtonsC(1, posX - 35, posY + 29, 30, 15, "On", guired));
        this.field_146292_n.add(2, new DBCGuiButtonsC(2, posX + 0, posY + 29, 50, 15, "", guigreen));
        if (sc == -1) {
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)0))).field_146126_j = "";
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)1))).field_146126_j = JRMCoreH.trl("jrmc", "On");
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)2))).field_146126_j = "";
        } else if (sc == 0) {
            if (loadtime > 0) {
                ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)0))).field_146126_j = "";
                ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)1))).field_146126_j = "";
                ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)2))).field_146126_j = "";
                --loadtime;
                var8.func_78276_b(JRMCoreH.cllg + "LOADING LAUNCHER", posX - 70, posY - 30, 0);
            } else {
                sc = 1;
            }
        } else if (sc == 1) {
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)0))).field_146126_j = JRMCoreH.trl("jrmc", "Select");
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)1))).field_146126_j = JRMCoreH.trl("jrmc", "Off");
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)2))).field_146126_j = JRMCoreH.trl("jrmc", "OK");
            var8.func_78276_b(JRMCoreH.cllg + JRMCoreH.trl("jrmc", "Planet") + ": " + JRMCoreH.cly + JRMCoreH.trl("dbc", DBCH.plntNms.get(DBCClient.mc.field_71439_g.field_71093_bK)), posX - 80, posY - 40, 0);
            ssl = 0;
            var8.func_78276_b(JRMCoreH.cllg + "> " + JRMCoreH.trl("jrmc", "Destinations"), posX - 70, posY - 20, 0);
        } else if (sc == 2) {
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)0))).field_146126_j = JRMCoreH.trl("jrmc", "Select");
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)1))).field_146126_j = JRMCoreH.trl("jrmc", "Off");
            ((DBCGuiButtonsC)((Object)this.field_146292_n.get((int)2))).field_146126_j = JRMCoreH.trl("jrmc", "OK");
            var8.func_78276_b(JRMCoreH.cllg + JRMCoreH.trl("jrmc", "Destinations"), posX - 80, posY - 40, 0);
            ssl = 2;
            var8.func_78276_b(JRMCoreH.cly + "> ", posX - 70, posY - 30 + ss * 10, 0);
            int i = 0;
            var8.func_78276_b(JRMCoreH.cly + JRMCoreH.trl("jrmc", "Back"), posX - 60, posY - 30 + i * 10, 0);
            ++i;
            for (int j = 0; j < dests.length; ++j) {
                if (DBCClient.mc.field_71439_g.field_71093_bK == dests[j]) continue;
                if (ss == i) {
                    ssD = destsTP[j];
                }
                var8.func_78276_b(JRMCoreH.cllg + DBCH.plntNms.get(dests[j]), posX - 60, posY - 30 + i * 10, 0);
                ++i;
            }
        }
        super.func_73863_a(x, y, f);
    }

    public boolean func_73868_f() {
        return false;
    }
}

