/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.gui;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUPacketSelectForm;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class DBAGuiFormWheel
extends GuiScreen {
    private boolean nextPage;
    private boolean clicked;

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
        this.renderWheel(mouseX, mouseY, fontRenderer);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    private List<FormItem> getAvailableForms(EntityPlayer player) {
        ArrayList<FormItem> forms = new ArrayList<FormItem>();
        byte race = JRMCoreH.Race;
        byte customRace = DataUtils.getDBCARace(player);
        FormItem[] RacialForms = FormItemsDBA.FormItems.toArray(new FormItem[0]);
        DBCASkill[] skills = DBCASkills.getPlayerSkills(DataUtils.getDBCASkills(player)).toArray(new DBCASkill[0]);
        for (int i = 0; i < RacialForms.length; ++i) {
            FormItem form = RacialForms[i];
            if (form.isVisible() && form.canTransform(player) && (!form.equals(FormItemsDBA.SS4) || JRMCoreH.s4ft > 0)) {
                forms.add(form);
                continue;
            }
            if (!form.isRaceCorrect(race, customRace)) continue;
            forms.add(null);
        }
        return forms;
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
        this.clicked = true;
    }

    private void renderWheel(int mouseX, int mouseY, FontRenderer fontRenderer) {
        ScaledResolution res = new ScaledResolution(JRMCoreClient.mc, JRMCoreClient.mc.field_71443_c, JRMCoreClient.mc.field_71440_d);
        int w = res.func_78326_a();
        int h = res.func_78328_b();
        JRMCoreClient.mc.field_71460_t.func_78478_c();
        int centerX = w / 2;
        int centerY = h / 2;
        this.field_73735_i = -90.0f;
        ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:allw.png");
        JRMCoreClient.mc.field_71446_o.func_110577_a(tx2);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        JRMCoreH.txt(DBCAUtils.translate("form_selection_menu"), JRMCoreH.clgd, 0, true, centerX - 50, centerY - 114, 180);
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        List<FormItem> forms = this.getAvailableForms((EntityPlayer)player);
        int id = this.nextPage ? 8 : 0;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (x == 1 && y == 1) {
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                    GL11.glDisable((int)3553);
                    int xPos = centerX - 150 + x * 117;
                    int yPos = centerY - 100 + y * 70;
                    boolean hovering = this.hovered(mouseX, mouseY, xPos, yPos, 60, 45);
                    if (hovering) {
                        GL11.glColor4f((float)0.96f, (float)0.97f, (float)0.86f, (float)0.65f);
                        if (this.clicked) {
                            this.clicked = false;
                            this.nextPage = !this.nextPage;
                        }
                    }
                    JRMCoreHC.dtm(xPos, yPos, 0, 0, 60.0f, 45.0f, -45.0f);
                    GL11.glEnable((int)3553);
                    GL11.glPopMatrix();
                    JRMCoreH.txt(this.nextPage ? DBCAUtils.translate("form_selection_menu_prev") : DBCAUtils.translate("form_selection_menu_next"), JRMCoreH.clb, 0, true, xPos + 5, yPos + 5 - 1, 45);
                    continue;
                }
                FormItem form = null;
                if (forms.size() > id) {
                    form = forms.get(id);
                }
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                GL11.glDisable((int)3553);
                int xPos = centerX - 150 + x * 100;
                int yPos = centerY - 100 + y * 63;
                boolean hovering = this.hovered(mouseX, mouseY, xPos, yPos, 90, 60);
                if (hovering) {
                    GL11.glColor4f((float)0.96f, (float)0.97f, (float)0.86f, (float)0.65f);
                    if (Mouse.isButtonDown((int)0)) {
                        DBCAClientData.selectedForm = form != null ? form.getID() : -1;
                        DBUPackets.sendToServer(new DBUPacketSelectForm(DBCAClientData.selectedForm));
                        player.func_71053_j();
                    }
                }
                JRMCoreHC.dtm(xPos, yPos, 0, 0, 90.0f, 60.0f, -90.0f);
                GL11.glEnable((int)3553);
                GL11.glPopMatrix();
                ++id;
                if (form == null) continue;
                JRMCoreH.txt(StatCollector.func_74838_a((String)form.getDisplayName()), JRMCoreH.clb, 0, true, xPos + 5, yPos + 5 - 1, 80);
            }
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    public boolean hovered(int mX, int mY, int px, int py, int w, int h) {
        return mX > px && mX < px + w && mY > py && mY < py + h;
    }
}

