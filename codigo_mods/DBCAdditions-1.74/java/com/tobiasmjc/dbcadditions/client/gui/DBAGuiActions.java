/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.gui;

import JinRyuu.JRMCore.JRMCoreA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class DBAGuiActions
extends Gui {
    public void renderActionMenu() {
        JRMCoreClient.mc.field_71417_B.func_74374_c();
        ScaledResolution var5 = new ScaledResolution(JRMCoreClient.mc, JRMCoreClient.mc.field_71443_c, JRMCoreClient.mc.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        float posX = (float)Mouse.getX() * 1.0f / (float)JRMCoreClient.mc.field_71443_c * 1.0f;
        float posY = (float)Mouse.getY() * 1.0f / (float)JRMCoreClient.mc.field_71440_d * 1.0f;
        int mouseX = (int)((float)var6 * posX);
        int mouseY = var7 - (int)((float)var7 * posY);
        FontRenderer var8 = JRMCoreClient.mc.field_71466_p;
        JRMCoreClient.mc.field_71460_t.func_78478_c();
        int var51 = var6 / 2;
        int var61 = var7 / 2;
        int var21 = 41;
        int height = 41;
        this.field_73735_i = -90.0f;
        ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:allw.png");
        JRMCoreClient.mc.field_71446_o.func_110577_a(tx2);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        JRMCoreH.txt("Hover over and release " + Keyboard.getKeyName((int)JRMCoreKeyHandler.actionMenu.func_151463_i()), JRMCoreH.cldgy, 0, true, var51 - 50, var61 - 110, 180);
        boolean doAction = false;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                String var34 = "";
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                int X = var51 - 135 + i * 90;
                int Y = var61 - 90 + j * 60;
                int id = i + j * 3 + DBCAClientData.actionPage * 9;
                boolean h = false;
                if (JRMCoreA.actions.get(id) != null) {
                    h = this.hovered(mouseX, mouseY, X, Y, 90, 60);
                    if (h) {
                        GL11.glPushMatrix();
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
                        GL11.glDisable((int)3553);
                        JRMCoreHC.dtm(X, Y, 0, 0, 89.0f, 59.0f, -90.0f);
                        GL11.glEnable((int)3553);
                        GL11.glPopMatrix();
                        DBCAClientData.selectedAction = id;
                        String brd = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, true);
                        var34 = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, false);
                        if (id == 1) {
                            brd = var34 = DBCAUtils.translate("form_selection_menu");
                        } else if (id == 2 && DataUtils.getDBCARace((EntityPlayer)Minecraft.func_71410_x().field_71439_g) == DBCARaces.BIO_ANDROID.ID) {
                            boolean black = false;
                            if (JGConfigClientSettings.CLIENT_GR13) {
                                black = true;
                            }
                            String opt1 = JGConfigClientSettings.CLIENT_GR12 ? "Enable" : "Off";
                            String opt2 = JGConfigClientSettings.CLIENT_GR12 ? "Disable" : "On";
                            boolean bo = JRMCoreH.StusEfctsMe(21);
                            String enable = JRMCoreH.trl("jrmc", opt1);
                            String disable = JRMCoreH.trl("jrmc", opt2);
                            brd = var34 = DBCAUtils.translate("absorption") + ": " + (!bo ? (black ? "" : "\u00a74") + enable : (black ? "" : "\u00a72") + disable);
                        }
                        String clr = JGConfigClientSettings.CLIENT_GR13 ? JRMCoreH.clgy : JRMCoreH.clgd;
                        JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5 + 1, Y + 5, 80);
                        JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5 - 1, Y + 5, 80);
                        JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5, Y + 5 + 1, 80);
                        JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5, Y + 5 - 1, 80);
                        int l = JRMCoreH.txt(var34, clr, 0, true, X + 5, Y + 5, 80);
                        doAction = true;
                        continue;
                    }
                    if (id % 9 == 4) continue;
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                    GL11.glDisable((int)3553);
                    JRMCoreHC.dtm(X, Y, 0, 0, 89.0f, 59.0f, -90.0f);
                    GL11.glEnable((int)3553);
                    GL11.glPopMatrix();
                    String brd = "";
                    if (JRMCoreH.Pwrtyp == 1) {
                        brd = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, true);
                        var34 = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, false);
                    }
                    if (id == 1) {
                        brd = var34 = DBCAUtils.translate("form_selection_menu");
                    } else if (id != 2 || DataUtils.getDBCARace((EntityPlayer)Minecraft.func_71410_x().field_71439_g) == DBCARaces.BIO_ANDROID.ID) {
                        // empty if block
                    }
                    JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5 + 1, Y + 5, 80);
                    JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5 - 1, Y + 5, 80);
                    JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5, Y + 5 + 1, 80);
                    JRMCoreH.txt(brd, JRMCoreH.clb, 0, true, X + 5, Y + 5 - 1, 80);
                    int n = JRMCoreH.txt(var34, JRMCoreH.clw, 0, true, X + 5, Y + 5, 80);
                    continue;
                }
                if (id % 9 != 4) {
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.25f);
                    GL11.glDisable((int)3553);
                    JRMCoreHC.dtm(X, Y, 0, 0, 89.0f, 59.0f, -90.0f);
                    GL11.glEnable((int)3553);
                    GL11.glPopMatrix();
                    continue;
                }
                if (id % 9 != 4) continue;
                h = this.hovered(mouseX, mouseY, X, Y, 90, 60);
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.5f + (h ? 0.25f : 0.0f)));
                GL11.glDisable((int)3553);
                JRMCoreHC.dtm(X + 22, Y + 15, 0, 0, 45.0f, 30.0f, -90.0f);
                GL11.glEnable((int)3553);
                GL11.glPopMatrix();
                var34 = "MORE";
                int l = JRMCoreH.txt(var34, h ? JRMCoreH.clgy : JRMCoreH.clb, 0, true, X + 5 + 22, Y + 5 + 15, 80);
                if (!h) continue;
                DBCAClientData.selectedAction = id;
                doAction = true;
            }
        }
        if (!JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d()) {
            DBCAClientData.actionNBO = false;
        }
        if (JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d() && DBCAClientData.selectedAction % 9 != 4) {
            KeyBinding.func_74510_a((int)JRMCoreKeyHandler.actionMenu.func_151463_i(), (boolean)false);
        }
        if (!doAction) {
            DBCAClientData.selectedAction = -1;
        }
    }

    public boolean hovered(int mX, int mY, int px, int py, int w, int h) {
        return mX > px && mX < px + w && mY > py && mY < py + h;
    }
}

