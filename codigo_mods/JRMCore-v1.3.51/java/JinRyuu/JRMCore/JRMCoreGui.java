/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreA;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreHNC;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.Version;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class JRMCoreGui
extends Gui {
    protected FontRenderer fontRenderer;

    public JRMCoreGui() {
        this.fontRenderer = JRMCoreClient.mc.field_71466_p;
    }

    public void checkVersion() {
        String rvf = "http://updateinfo.ryugujo.hu/info.txt";
        String line = null;
        try {
            URL url = new URL("http://updateinfo.ryugujo.hu/info.txt");
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(isr);
            line = reader.readLine();
            if (line != null) {
                Version.ammv = line;
                reader.close();
                isr.close();
                return;
            }
            reader.close();
            isr.close();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void checkText() {
        Thread one = new Thread(){

            @Override
            public void run() {
                String rvf = "http://updateinfo.jingames.net/getLatestPost.php";
                String line = null;
                StringBuilder content = new StringBuilder();
                int empty = 0;
                int count = 0;
                try {
                    URL url = new URL("http://updateinfo.jingames.net/getLatestPost.php");
                    InputStreamReader isr = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(isr);
                    while ((line = reader.readLine()) != null) {
                        if (line.length() > 3) {
                            content.append(line.replaceAll("\u00a0", " ") + "/n");
                            ++count;
                            empty = 0;
                        } else {
                            if (empty < 1) {
                                content.append(line.replaceAll("\u00a0", " ") + "/n");
                                ++count;
                            }
                            ++empty;
                        }
                        if (count <= 18) continue;
                    }
                    Version.news = content.toString();
                    reader.close();
                    isr.close();
                }
                catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                this.interrupt();
            }
        };
        one.start();
    }

    public void renderActionMenu() {
        JRMCoreClient.mc.field_71417_B.func_74374_c();
        ScaledResolution var5 = new ScaledResolution(JRMCoreClient.mc, JRMCoreClient.mc.field_71443_c, JRMCoreClient.mc.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        float posX = (float)Mouse.getX() * 1.0f / ((float)JRMCoreClient.mc.field_71443_c * 1.0f);
        float posY = (float)Mouse.getY() * 1.0f / ((float)JRMCoreClient.mc.field_71440_d * 1.0f);
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
                int id = i + j * 3;
                boolean h = false;
                if (JRMCoreA.actions.get(id += JRMCoreCliTicH.actionNPA * 9) != null) {
                    h = this.hovered(mouseX, mouseY, X, Y, 90, 60);
                    if (h) {
                        GL11.glPushMatrix();
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
                        GL11.glDisable((int)3553);
                        JRMCoreHC.dtm(X, Y, 0, 0, 89.0f, 59.0f, -90.0f);
                        GL11.glEnable((int)3553);
                        GL11.glPopMatrix();
                        JRMCoreCliTicH.actionSelectID = id;
                        String brd = "";
                        if (JRMCoreH.Pwrtyp == 1) {
                            brd = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, true);
                            var34 = JRMCoreHDBC.action((Integer)JRMCoreA.actions.get(id), false, false);
                        }
                        if (JRMCoreH.Pwrtyp == 2) {
                            brd = JRMCoreHNC.action((Integer)JRMCoreA.actions.get(id), false, true);
                            var34 = JRMCoreHNC.action((Integer)JRMCoreA.actions.get(id), false, false);
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
                    if (JRMCoreH.Pwrtyp == 2) {
                        brd = JRMCoreHNC.action((Integer)JRMCoreA.actions.get(id), false, true);
                        var34 = JRMCoreHNC.action((Integer)JRMCoreA.actions.get(id), false, false);
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
                JRMCoreCliTicH.actionSelectID = id;
                doAction = true;
            }
        }
        if (!JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d()) {
            JRMCoreCliTicH.actionNBO = false;
        }
        if (JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d() && JRMCoreCliTicH.actionSelectID % 9 != 4) {
            KeyBinding.func_74510_a((int)JRMCoreKeyHandler.actionMenu.func_151463_i(), (boolean)false);
        }
        if (!doAction) {
            JRMCoreCliTicH.actionSelectID = -1;
        }
    }

    public boolean hovered(int mX, int mY, int px, int py, int w, int h) {
        return mX > px && mX < px + w && mY > py && mY < py + h;
    }
}

