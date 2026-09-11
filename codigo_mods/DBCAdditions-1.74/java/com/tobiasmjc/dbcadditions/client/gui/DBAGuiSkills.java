/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.gui;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreGuiButtons00;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA1;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA3;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreGuiSliderX00;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DBAGuiSkills
extends GuiScreen {
    private int xSize = 256;
    private int ySize = 159;
    private int tick;
    private int guiLeft;
    private int guiTop;
    private JRMCoreGuiScreen parent;
    private int IDtoProcessConfirmFor;
    private boolean confirmationWindow;

    public DBAGuiSkills(JRMCoreGuiScreen parent) {
        this.parent = parent;
        this.confirmationWindow = false;
        this.IDtoProcessConfirmFor = -1;
    }

    private void renderSkillMenu(FontRenderer var8, int var6, int var7, int x, int y) {
        String s19;
        int tpCost;
        int mindRequirementResult;
        int mindRequirement;
        int nw7;
        this.field_146292_n.clear();
        ++this.tick;
        if (this.tick >= 20) {
            this.tick = 0;
            JRMCoreH.jrmct(1);
            JRMCoreH.jrmct(3);
        }
        this.guiLeft = (this.field_146294_l - this.xSize) / 2;
        this.guiTop = (this.field_146295_m - this.ySize) / 2;
        int posX = this.field_146294_l / 2;
        int posY = this.field_146295_m / 2;
        int xSize2 = 256;
        int ySize2 = 159;
        int guiLeft2 = (this.field_146294_l - xSize2) / 2;
        int guiTop2 = (this.field_146295_m - ySize2) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation guiLocation2 = new ResourceLocation(JRMCoreGuiScreen.wish);
        JRMCoreGuiScreen.field_146297_k.field_71446_o.func_110577_a(guiLocation2);
        this.func_73729_b(guiLeft2, guiTop2, 0, 0, xSize2, ySize2);
        int[][] rSklsMR = null;
        int[][] cSklsMR = null;
        String[] rSkls = JRMCoreH.vlblRSkls;
        int[][] rSklsLvl = JRMCoreH.DBCRacialSkillTPCost;
        String[] rSklsNms = JRMCoreH.vlblRSklsNms;
        rSklsMR = JRMCoreH.DBCRacialSkillMindCost;
        String[] cSkls = JRMCoreH.vlblCSkls;
        int[][] cSklsLvl = JRMCoreH.vlblCSklsLvl;
        String[] cSklsNms = JRMCoreH.vlblCSklsNms;
        String[] skls2 = JRMCoreH.DBCSkillsIDs;
        int[] sklsUps = JRMCoreH.vlblSklsUps;
        int[][] sklsLvl = JRMCoreH.DBCSkillTPCost;
        String[] sklsNms2 = JRMCoreH.DBCSkillNames;
        int[][] sklsMR = JRMCoreH.DBCSkillMindCost;
        String mod2 = "dbc";
        int mindUsed = JRMCoreH.skillSlot_MindUsed();
        boolean canAffordMind = JRMCoreH.skillSlot_EnoughMindLeft();
        int mindLeft = JRMCoreH.skillSlot_AvailableMindLeft();
        boolean canAffordTP = true;
        int skillID = 0;
        if (JRMCoreH.PlyrSkillX != null && !JRMCoreH.PlyrSkillX.contains("pty") && JRMCoreH.PlyrSkillX.length() > 1) {
            String un = JRMCoreH.SklName(JRMCoreH.PlyrSkillX, rSkls, rSklsNms, JRMCoreH.Race);
            String name2 = JRMCoreH.trl(mod2, un);
            int skillLvl = Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2));
            var8.func_78276_b("\u00a70" + name2 + (skillLvl <= 9 ? " " + this.textLevel(skillLvl) : ""), guiLeft2 + 5, guiTop2 + 15 + ++skillID * 10, 0);
            nw7 = this.field_146289_q.func_78256_a(name2);
            JRMCoreGuiScreen.drawDetails(JRMCoreH.trl(mod2, un + "Desc"), guiLeft2 + 5, guiTop2 + 15 + skillID * 10 + 2, nw7, 6, x, y, var8);
            mindRequirement = JRMCoreH.skillMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, rSklsMR);
            mindRequirementResult = mindUsed + mindRequirement;
            canAffordMind = JRMCoreH.canAffordSkill(JRMCoreH.statMindC(), mindRequirementResult);
            tpCost = JRMCoreH.skillTPCost_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, rSklsLvl);
            if (JRMCoreConfig.dat5711 && tpCost != -1) {
                if (JRMCoreH.rSai(JRMCoreH.Race) && skillLvl < 7) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID * 10, 10, 2, canAffordMind));
                } else if (JRMCoreH.Race == 4 && skillLvl < 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID * 10, 10, 2, canAffordMind));
                } else if (JRMCoreH.Race != 4 && skillLvl < 5) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID * 10, 10, 2, canAffordMind));
                }
            }
            if (JRMCoreH.Race == 4 && JRMCoreHDBC.auc(skillLvl) && !JRMCoreH.data(JRMCoreGuiScreen.field_146297_k.field_71439_g.func_70005_c_(), 16, "").contains(";")) {
                this.field_146292_n.add(new JRMCoreGuiButtonsA3(392, guiLeft2 + 10 + var8.func_78256_a(name2 + (skillLvl < 7 ? this.textLevel(skillLvl) : "")), guiTop2 + 13 + skillID * 10, 20, 1));
            }
            s19 = skillLvl < (JRMCoreH.Race == 1 || JRMCoreH.Race == 2 ? 7 : (JRMCoreH.Race == 4 ? 6 : 5)) ? (tpCost == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : "TP: " + JRMCoreH.numSep(tpCost) + " M: " + JRMCoreH.numSep(mindRequirement)) : JRMCoreH.trl("jrmc", "Maxed");
            var8.func_78276_b(s19, guiLeft2 + 250 - var8.func_78256_a(s19), guiTop2 + 15 + skillID * 10, 0);
        }
        if (JRMCoreH.PlyrSkillY != null && !JRMCoreH.PlyrSkillY.contains("pty") && !JRMCoreH.PlyrSkillY.contains("Sai") && JRMCoreH.Race != 1 && JRMCoreH.Race != 2 && JRMCoreH.PlyrSkillY.length() > 1) {
            String name2;
            String un = name2 = JRMCoreH.SklName(JRMCoreH.PlyrSkillY, cSkls, cSklsNms);
            int n15 = Integer.parseInt(JRMCoreH.PlyrSkillY.substring(2));
            var8.func_78276_b("\u00a70" + (JRMCoreH.Race == 1 || JRMCoreH.Race == 2 ? JRMCoreH.TransSaiUpNam[n15] : name2 + this.textLevel(n15 + 1)), guiLeft2 + 5, guiTop2 + 15 + ++skillID * 10, 0);
            nw7 = this.field_146289_q.func_78256_a(name2);
            JRMCoreGuiScreen.drawDetails(JRMCoreH.trl(mod2, name2 + "Desc"), guiLeft2 + 5, guiTop2 + 15 + skillID * 10 + 2, nw7, 6, x, y, var8);
            mindRequirement = JRMCoreH.skillMindRequirement(JRMCoreH.PlyrSkillY, cSkls, cSklsMR);
            mindRequirementResult = mindUsed + mindRequirement;
            canAffordMind = JRMCoreH.canAffordSkill(JRMCoreH.statMindC(), mindRequirementResult);
            tpCost = JRMCoreH.skillTPCost_X(JRMCoreH.PlyrSkillY, JRMCoreH.Race, cSklsLvl);
            if (JRMCoreConfig.dat5711 && n15 <= 8 && tpCost != -1) {
                this.field_146292_n.add(new JRMCoreGuiButtonsA3(391, guiLeft2 - 10, guiTop2 + 13 + skillID * 10, 10, 2, canAffordMind));
            }
            s19 = n15 <= 8 ? (tpCost == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : "TP: " + JRMCoreH.numSep(tpCost) + " M: " + JRMCoreH.numSep(mindRequirement)) : JRMCoreH.trl("jrmc", "Maxed");
            var8.func_78276_b(s19, guiLeft2 + 250 - var8.func_78256_a(s19), guiTop2 + 15 + skillID * 10, 0);
        }
        if (JRMCoreH.PlyrSkills != null) {
            int m8 = JRMCoreH.PlyrSkills.length;
            float m9 = 5.0f;
            int sz3 = 10;
            this.parent.scrollMouseJump = 1;
            if (m8 > 10) {
                if ((float)m8 - 5.0f < (float)this.parent.scroll) {
                    this.parent.scroll = (int)((float)m8 - 5.0f);
                } else if (this.parent.scroll < 0) {
                    this.parent.scroll = 0;
                }
                if (this.parent.mousePressed && !JRMCoreGuiButtonsA1.clicked) {
                    this.parent.scroll = (int)(((float)m8 - 5.0f) * JRMCoreGuiScreen.scrollSide);
                } else {
                    JRMCoreGuiScreen.scrollSide = JRMCoreGuiSliderX00.sliderValue = (float)this.parent.scroll / ((float)m8 - 5.0f);
                }
            } else {
                this.parent.scroll = 0;
            }
            if (m8 > 10) {
                if (JRMCoreGuiScreen.scrollSide > 0.0f) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(43, guiLeft2 + xSize2 / 2 + 110 + 18, guiTop2 + 80 - 70, "i"));
                }
                if (JRMCoreGuiScreen.scrollSide < 1.0f) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA1(44, guiLeft2 + xSize2 / 2 + 110 + 18, guiTop2 + 80 + 60, "v"));
                }
                this.field_146292_n.add(new JRMCoreGuiSliderX00(1000000, guiLeft2 + xSize2 / 2 + 110 + 18, guiTop2 + 25, this.parent.mousePressed, JRMCoreGuiScreen.scrollSide, 1.0f));
            }
            for (int i18 = this.parent.scroll; i18 < (JRMCoreH.PlyrSkills.length > this.parent.scroll + 10 ? this.parent.scroll + 10 : JRMCoreH.PlyrSkills.length); ++i18) {
                String currentSkill = JRMCoreH.PlyrSkills[i18];
                if (currentSkill.contains("pty") || currentSkill.length() <= 2) continue;
                ++skillID;
                String un2 = JRMCoreH.SklName(currentSkill, skls2, sklsNms2);
                String name3 = JRMCoreH.trl(mod2, un2);
                int n16 = Integer.parseInt(currentSkill.substring(2));
                n16 = JRMCoreH.isPowerTypeKi() && !JRMCoreH.rSai(JRMCoreH.Race) && currentSkill.contains(JRMCoreH.DBCSkillsIDs[9]) ? (n16 > 0 ? 0 : n16) : JRMCoreH.SklLvl_m(currentSkill, skls2, n16);
                int d8 = n16;
                String nm = "\u00a70" + name3 + " " + this.textLevel(n16 + 1);
                var8.func_78276_b(nm, guiLeft2 + 5, guiTop2 + 20 + skillID * 10, 0);
                String a4 = "";
                if (JRMCoreH.isPowerTypeKi()) {
                    switch (JRMCoreH.SklID(currentSkill, skls2)) {
                        case 7: {
                            a4 = "" + JRMCoreConfig.SklMedCat;
                        }
                    }
                }
                int nw8 = this.field_146289_q.func_78256_a(name3);
                JRMCoreGuiScreen.drawDetails(JRMCoreH.trl(mod2, un2 + "Desc" + a4), guiLeft2 + 5, guiTop2 + 20 + skillID * 10 + 2, nw8, 6, x, y, var8);
                this.field_146292_n.add(new JRMCoreGuiButtonsA3(360 + i18, guiLeft2 + 243, guiTop2 + 20 + skillID * 10 - 2, 10, 3));
                int mindRequirement2 = JRMCoreH.skillMindRequirement(currentSkill, skls2, sklsMR);
                int mindRequirementResult2 = mindUsed + mindRequirement2;
                canAffordMind = JRMCoreH.canAffordSkill(JRMCoreH.statMindC(), mindRequirementResult2);
                d8 = JRMCoreH.isPowerTypeKi() && !JRMCoreH.rSai(JRMCoreH.Race) && currentSkill.contains(JRMCoreH.DBCSkillsIDs[9]) ? -1 : JRMCoreH.SklInit(currentSkill, skls2, sklsUps);
                int tpCost2 = JRMCoreH.skillTPCost(currentSkill, skls2, sklsLvl);
                if (n16 <= d8 && tpCost2 != -1) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(330 + i18, guiLeft2 - 10, guiTop2 + 18 + skillID * 10, 10, 2, canAffordMind));
                }
                String st3 = n16 <= JRMCoreH.SklInit(currentSkill, skls2, sklsUps) ? (tpCost2 == -1 ? JRMCoreH.trl("jrmc", "UpgradeLocked") : "TP: " + JRMCoreH.numSep(tpCost2) + " M: " + JRMCoreH.numSep(mindRequirement2)) : JRMCoreH.trl("jrmc", "Maxed");
                var8.func_78276_b(st3, guiLeft2 + 240 - var8.func_78256_a(st3), guiTop2 + 20 + skillID * 10, 0);
            }
        }
        var8.func_78276_b(JRMCoreH.numSep(JRMCoreH.curTP) + " Training Points (TP)", guiLeft2 + 10, guiTop2 + 150, 0);
        String textCost = "TP and Mind Cost:";
        JRMCoreGuiScreen.drawStringWithBorder(var8, "TP and Mind Cost:", guiLeft2 + 250 - var8.func_78256_a("TP and Mind Cost:"), guiTop2 + 5 - 2, 16765738);
        if (this.confirmationWindow && this.IDtoProcessConfirmFor >= 0) {
            xSize2 = 140;
            ySize2 = 71;
            int wpx = 60;
            int wpy = 50;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            guiLocation2 = new ResourceLocation(JRMCoreGuiScreen.wish);
            JRMCoreGuiScreen.field_146297_k.field_71446_o.func_110577_a(guiLocation2);
            this.func_73729_b(guiLeft2 + 60, guiTop2 + 50, 0, 159, xSize2, ySize2);
            String curSkl = JRMCoreH.PlyrSkills[this.IDtoProcessConfirmFor];
            if (!curSkl.contains("pty") && curSkl.length() > 2) {
                ++skillID;
                String name4 = JRMCoreH.Pwrtyp == 1 ? JRMCoreH.trl("dbc", JRMCoreH.SklName(curSkl, skls2, sklsNms2)) : JRMCoreH.trl("nc", JRMCoreH.SklName(curSkl, skls2, sklsNms2));
                JRMCoreH.txt(JRMCoreH.trl("jrmc", "delskillconfirm", name4), JRMCoreH.cldr, 0, true, guiLeft2 + 60 + 5, guiTop2 + 50 + 5, xSize2 - 10);
                this.field_146292_n.add(new JRMCoreGuiButtons00(300 + this.IDtoProcessConfirmFor, guiLeft2 + 5 + 60, guiTop2 + 45 + 50, 40, 20, JRMCoreH.trl("jrmc", "Yes"), 0));
            }
            this.field_146292_n.add(new JRMCoreGuiButtons00(399, guiLeft2 + 95 + 60, guiTop2 + 45 + 50, 40, 20, JRMCoreH.trl("jrmc", "No"), 0));
        }
    }

    protected void func_146284_a(GuiButton button) {
        if (this.confirmationWindow) {
            this.confirmationWindow = false;
        }
        for (int i6 = 0; i6 < JRMCoreH.PlyrSkills.length; i6 = (int)((byte)(i6 + 1))) {
            if (button.field_146127_k != 360 + i6) continue;
            this.confirmationWindow = true;
            this.IDtoProcessConfirmFor = i6;
        }
        if (button.field_146127_k == 399) {
            this.confirmationWindow = false;
        }
        super.func_146284_a(button);
    }

    public void func_73863_a(int x, int y, float f) {
        super.func_73863_a(x, y, f);
        ScaledResolution var5 = new ScaledResolution(JRMCoreGuiScreen.field_146297_k, JRMCoreGuiScreen.field_146297_k.field_71443_c, JRMCoreGuiScreen.field_146297_k.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = JRMCoreGuiScreen.field_146297_k.field_71466_p;
        this.renderSkillMenu(var8, var6, var7, x, y);
    }

    private String textLevel(int lvl) {
        return "\u00a78(lvl: " + lvl + ")";
    }
}

