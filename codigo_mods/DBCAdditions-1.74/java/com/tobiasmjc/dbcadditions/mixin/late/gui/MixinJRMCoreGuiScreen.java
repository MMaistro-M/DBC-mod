/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package com.tobiasmjc.dbcadditions.mixin.late.gui;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiButtonC1;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA3;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGRaceHelper;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUPacketRemoveSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPacketUpgradeSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={JRMCoreGuiScreen.class}, remap=false)
public class MixinJRMCoreGuiScreen
extends GuiScreen {
    private static boolean isRacialString;
    private static int tpCost;
    private static int mindCost;
    @Shadow
    protected static List<Object[]> detailList;
    @Shadow
    public int guiID;
    @Shadow
    private boolean confirmationWindow;
    @Shadow
    private int IDtoProcessConfirmFor;

    @Inject(method={"actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V"}, at={@At(value="TAIL")}, remap=true)
    public void onActionPerformed(GuiButton button, CallbackInfo ci) {
        if (button.field_146127_k == 392) {
            MixinJRMCoreGuiScreen.setMenuDefColors();
        }
        if (button.field_146127_k < 300 || button.field_146127_k >= 390) {
            return;
        }
        String[] skills = MixinJRMCoreGuiScreen.getSkills();
        HashMap<Integer, DBCASkill> customSkillsIndex = new HashMap<Integer, DBCASkill>();
        for (DBCASkill dBCASkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)Minecraft.func_71410_x().field_71439_g))) {
            String customSkillStr = dBCASkill.idToCode() + (dBCASkill.getLevel() - 1);
            int index = Arrays.asList(skills).indexOf(customSkillStr);
            customSkillsIndex.put(index, dBCASkill);
            if (button.field_146127_k != 300 + index || index != this.IDtoProcessConfirmFor) continue;
            DBUPackets.sendToServer(new DBUPacketRemoveSkill(dBCASkill.getID()));
        }
        for (Map.Entry entry : customSkillsIndex.entrySet()) {
            if (button.field_146127_k == 360 + (Integer)entry.getKey()) {
                this.confirmationWindow = true;
                this.IDtoProcessConfirmFor = (Integer)entry.getKey();
                continue;
            }
            if (button.field_146127_k != 330 + (Integer)entry.getKey()) continue;
            DBUPackets.sendToServer(new DBUPacketUpgradeSkill(((DBCASkill)entry.getValue()).getID()));
        }
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;skillMindRequirement(Ljava/lang/String;[Ljava/lang/String;[[I)I"))
    public int customSkillMindCost(String skill, String[] skills, int[][] requirements) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (!skill.startsWith(customSkill.idToCode())) continue;
            return customSkill.getMindCost();
        }
        return JRMCoreH.skillMindRequirement(skill, skills, requirements);
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;skillTPCost(Ljava/lang/String;[Ljava/lang/String;[[I)I"))
    public int customSkillTpCost(String skill, String[] skills, int[][] tpCosts) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (!skill.startsWith(customSkill.idToCode())) continue;
            return customSkill.getTPCost() * (customSkill.getLevel() + 1);
        }
        return JRMCoreH.skillTPCost(skill, skills, tpCosts);
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;SklInit(Ljava/lang/String;[Ljava/lang/String;[I)I"))
    public int getMaxLevel(String curSkl, String[] s1, int[] s2) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (!curSkl.startsWith(customSkill.idToCode())) continue;
            return customSkill.getMaxLevel() - 2;
        }
        return JRMCoreH.SklInit(curSkl, s1, s2);
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;trl(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;"))
    private static String getTranslateNames(String mod, String name) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (name.equalsIgnoreCase(customSkill.getName())) {
                return StatCollector.func_74838_a((String)customSkill.getDisplayName());
            }
            if (!name.equalsIgnoreCase(customSkill.getName() + "Desc")) continue;
            return StatCollector.func_74838_a((String)customSkill.getDescription());
        }
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.trl(mod, name);
        }
        byte raceID = DataUtils.getDBCARace((EntityPlayer)player);
        if (raceID > 0) {
            DBCARace race = DBCARaces.getRace(raceID);
            if (name.equalsIgnoreCase("Human")) {
                return DBCAUtils.translate(race.getName());
            }
            if (name.equalsIgnoreCase("HiddenPotential")) {
                return DBCAUtils.translate(race.getName() + "-FormName");
            }
            if (name.equalsIgnoreCase("HiddenPotentialDesc")) {
                return DBCAUtils.translate(race.getName() + "-FormDesc");
            }
            if (name.equalsIgnoreCase("Base")) {
                return DBCAUtils.translate(race.getName() + "-BaseName");
            }
            if (name.equalsIgnoreCase("ArcosianUltimateColor")) {
                return DBCAUtils.translate(race.getName() + "-UltimateForm") + " Color";
            }
        }
        if (name.equalsIgnoreCase("Maxed") && isRacialString) {
            isRacialString = false;
            return "TP: " + JRMCoreH.numSep(tpCost) + " M: " + JRMCoreH.numSep(mindCost);
        }
        return JRMCoreH.trl(mod, name);
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;SklName(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;"))
    private static String getSkillName(String curSkl, String[] s1, String[] s2) {
        String skillName = curSkl;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (!curSkl.startsWith(customSkill.idToCode())) continue;
            return customSkill.getName();
        }
        return JRMCoreH.SklName(skillName, s1, s2);
    }

    private static String[] getRaces() {
        return new String[]{"Human", "Saiyan", "Half-Saiyan", "Namekian", "Arcosian", "Majin", DBCARaces.BIO_ANDROID.getName()};
    }

    private static String[] getSkills() {
        String[] skills = JRMCoreH.PlyrSkills;
        ArrayList<String> customSkills = new ArrayList<String>();
        customSkills.addAll(Arrays.asList(skills));
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            customSkills.add(customSkill.idToCode() + (customSkill.getLevel() - 1));
        }
        return customSkills.toArray(new String[0]);
    }

    @Inject(method={"initGui"}, at={@At(value="TAIL")}, remap=true)
    private static void initRace(CallbackInfo ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        byte dbcaRace = DataUtils.getDBCARace((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (JRMCoreH.Race == 0 && dbcaRace > 0) {
            JRMCoreGuiScreen.RaceSlcted = 5 + dbcaRace;
        }
    }

    @Redirect(method={"drawScreen", "RaceSlctF", "RaceSlctB", "actionPerformed", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;Races:[Ljava/lang/String;"))
    private static String[] addRaces() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.Races;
        }
        return MixinJRMCoreGuiScreen.getRaces();
    }

    @Redirect(method={"drawScreen", "RaceSlctF", "RaceSlctB", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;RaceAllow:[Ljava/lang/String;"))
    private static String[] allowRaces() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.RaceAllow;
        }
        return new String[]{"All", "DBC", "DBC", "DBC", "DBC", "DBC", "DBC", "HHC", "HHC", "HHC", "HHC", "HHC", "HHC", "HHC", "HHC"};
    }

    @Redirect(method={"drawScreen", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;RaceCanHaveHair:[Ljava/lang/String;"))
    private static String[] canHaveHair() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.RaceCanHaveHair;
        }
        return new String[]{"H", "H", "H", "A", "R", "H", DBCARaces.BIO_ANDROID.getHairType(), "H", "H", "H", "H", "H", "H", "H", "H"};
    }

    @Redirect(method={"drawScreen", "actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;customSknLimits:[[I"))
    private static int[][] customSkinLimits() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.customSknLimits;
        }
        return new int[][]{{1, 1, 5, 5, 6, 2}, {1, 1, 5, 5, 6, 0}, {1, 2, 5, 5, 6, 2}, {3, 3, 5, 5, 3, 2}, {3, 4, 5, 6, 2, 2}, {1, 1, 5, 5, 6, 2}, DBCARaces.BIO_ANDROID.getSkinLimits()};
    }

    @Redirect(method={"drawScreen", "actionPerformed(Lnet/minecraft/client/gui/GuiButton;)V", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;customSknLimitsBCP:[I"))
    private static int[] customSkinLimitsBCP() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.customSknLimitsBCP;
        }
        int[] def = new int[]{7, 7, 7, 3, 3, 7};
        ArrayList<Integer> defList = new ArrayList<Integer>(Arrays.stream(def).boxed().collect(Collectors.toList()));
        for (DBCARace race : DBCARaces.RACES) {
            defList.add(race.getColorPresetLimit());
        }
        return defList.stream().mapToInt(Integer::intValue).toArray();
    }

    @Redirect(method={"setchangeeyecol"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;defeyecols:[[I"))
    private static int[][] eyeColors() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.defeyecols;
        }
        return new int[][]{{1, 1, 1, 1, 14617612, 14551628, 0xB11B00}, {4896782, 1, 4896782, 4896782, 1, 8235495, 0xB11B00}, {14617612, 1, 14617612, 14617612, 4896782, 0xFFFFFF, 0xB11B00}};
    }

    @Redirect(method={"setchangebodycol"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;defbodycols:[[[I"))
    private static int[][][] bodyColors() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.defbodycols;
        }
        return new int[][][]{new int[][]{{16297621, 6498048}, {16297621, 6498048}, {16297621, 6498048}, {5095183, 13796998, 12854822}, {15460342, 16111595, 8533141, 16550015}, {16757199, 15766205}, {3140920, 16768592, 16750672}}, new int[][]{{15979704, 6498048}, {15979704, 6498048}, {15979704, 6498048}, {4566029, 0xD88A8A, 0xDB2B2B}, {15460342, 15188457, 287340, 16550015}, {16752073, 16028862}, {0}}, new int[][]{{13014656, 6498048}, {13014656, 6498048}, {13014656, 6498048}, {4896782, 12875121, 12920870}, {15460342, 10442657, 3625381, 13125463}, {16483508, 15825582}, {0}}, new int[][]{{12622942, 6498048}, {12622942, 6498048}, {12622942, 6498048}, {0}, {0}, {14383492, 13987449}, {0}}, new int[][]{{10112303, 6498048}, {10112303, 6498048}, {10112303, 6498048}, {0}, {0}, {11433702, 10776284}, {0}}, new int[][]{{7225375, 6498048}, {7225375, 6498048}, {7225375, 6498048}, {0}, {0}, {7907292, 7578067}, {0}}, new int[][]{{3677711, 6498048}, {3677711, 6498048}, {3677711, 6498048}, {0}, {0}, {7916929, 7652472}, {0}}};
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;attributeStart(IIII)I"))
    private static int attributeStart(int powerType, int attribute, int race, int classID) {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.attributeStart(powerType, attribute, race, classID);
        }
        if (race > 5) {
            return JRMCoreH.attributeStart(powerType, attribute, 0, classID);
        }
        return JRMCoreH.attributeStart(powerType, attribute, race, classID);
    }

    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;getStatIncreases(III)[F"))
    private static float[] getStatIncreases(int powerType, int race, int classID) {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.getStatIncreases(powerType, race, classID);
        }
        if (powerType == 1) {
            return JGConfigRaces.CONFIG_RACES_STATS_MULTI[race > 5 ? 0 : race][classID];
        }
        return JRMCoreH.statInc[powerType];
    }

    @Redirect(method={"drawScreen", "setchangerace"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;RaceGenders:[I"))
    private static int[] raceGenders() {
        if (!DBCAConfig.CustomRaces) {
            return JRMCoreH.RaceGenders;
        }
        return new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    }

    @Redirect(method={"drawScreen"}, at=@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;PlyrSkills:[Ljava/lang/String;"), remap=false)
    public String[] addCustomSkills() {
        if (!DBCAConfig.CustomForms) {
            return JRMCoreH.PlyrSkills;
        }
        return MixinJRMCoreGuiScreen.getSkills();
    }

    @Inject(method={"drawScreen"}, at={@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreConfig;dat5711:Z", ordinal=0, shift=At.Shift.BEFORE)}, cancellable=true)
    private void customRaceColorButton(int x, int y, float f, CallbackInfo ci, @Local(name={"skillID"}) LocalIntRef skillID) {
        byte maxLevel;
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        int skillLvl = Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2));
        int guiLeft2 = (this.field_146294_l - 256) / 2;
        int guiTop2 = (this.field_146295_m - 159) / 2;
        FontRenderer var8 = Minecraft.func_71410_x().field_71466_p;
        String un = JRMCoreH.SklName(JRMCoreH.PlyrSkillX, JRMCoreH.vlblRSkls, JRMCoreH.vlblRSklsNms, JRMCoreH.Race);
        String name2 = JRMCoreH.trl("dbc", un);
        int mindUsed = JRMCoreH.skillSlot_MindUsed();
        int mindRequirement = JRMCoreH.skillMindRequirement_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillMindCost);
        int mindRequirementResult = mindUsed + mindRequirement;
        boolean canAffordMind = JRMCoreH.canAffordSkill(JRMCoreH.statMindC(), mindRequirementResult);
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        DBCARace race = DBCARaces.getRace(DataUtils.getDBCARace((EntityPlayer)player));
        boolean customRace = race != null;
        byte by = maxLevel = customRace ? (byte)race.getTPCosts().length : JGRaceHelper.getMaxRacialSkillLevel(true, false, JRMCoreH.Race);
        if (skillLvl < maxLevel) {
            if (!customRace) {
                if (JRMCoreH.rSai(JRMCoreH.Race) && skillLvl >= 7) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID.get() * 10, 10, 2, canAffordMind));
                    isRacialString = true;
                    mindCost = mindRequirement;
                    tpCost = JRMCoreH.skillTPCost_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillTPCost);
                } else if (JRMCoreH.Race == 4 && skillLvl >= 6) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID.get() * 10, 10, 2, canAffordMind));
                    isRacialString = true;
                    mindCost = mindRequirement;
                    tpCost = JRMCoreH.skillTPCost_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillTPCost);
                } else if (JRMCoreH.Race != 4 && skillLvl >= 5 && !JRMCoreH.rSai(JRMCoreH.Race)) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID.get() * 10, 10, 2, canAffordMind));
                    isRacialString = true;
                    mindCost = mindRequirement;
                    tpCost = JRMCoreH.skillTPCost_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, JRMCoreH.DBCRacialSkillTPCost);
                }
            } else {
                int[] tpCosts = race.getTPCosts();
                if (skillLvl >= 5) {
                    this.field_146292_n.add(new JRMCoreGuiButtonsA3(390, guiLeft2 - 10, guiTop2 + 13 + skillID.get() * 10, 10, 2, canAffordMind));
                    isRacialString = true;
                    mindCost = race.getMindCosts()[skillLvl];
                    tpCost = tpCosts[skillLvl];
                }
            }
        }
        if (race == null) {
            return;
        }
        String dnsau = JRMCoreH.data(16, "");
        if (race.getColorMinRacial() != -1 && skillLvl >= race.getColorMinRacial() && !dnsau.contains(";")) {
            this.field_146292_n.add(new JRMCoreGuiButtonsA3(392, guiLeft2 + 10 + var8.func_78256_a(name2 + (skillLvl < 6 ? this.textLevel1(skillLvl) : "")), guiTop2 + 13 + skillID.get() * 10, 20, 1));
        }
    }

    @Inject(method={"drawScreen"}, at={@At(value="TAIL")}, cancellable=true, remap=true)
    private void customRaceColorMenu(int x, int y, float f, CallbackInfo ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (!DBCARaces.isCustomRace((EntityPlayer)player)) {
            return;
        }
        DBCARace race = DBCARaces.getRace(DataUtils.getDBCARace((EntityPlayer)player));
        if (race == null) {
            return;
        }
        if (this.guiID == 19) {
            this.field_146292_n.removeIf(b -> {
                if (b instanceof JRMCoreGuiButtonC1) {
                    JRMCoreGuiButtonC1 b1 = (JRMCoreGuiButtonC1)((Object)((Object)b));
                    if (b1.field_146127_k >= 5016 && b1.field_146127_k <= 5019 && race.getUltimateFormColors()[b1.field_146127_k - 5016] == -1) {
                        b1.field_146125_m = false;
                        return true;
                    }
                }
                return false;
            });
        }
    }

    private static void setMenuDefColors() {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (!DBCARaces.isCustomRace((EntityPlayer)player)) {
            return;
        }
        DBCARace race = DBCARaces.getRace(DataUtils.getDBCARace((EntityPlayer)player));
        if (race == null) {
            return;
        }
        JRMCoreGuiScreen.BodyauColMainSlcted = race.getUltimateFormColors()[0] != -1 ? race.getUltimateFormColors()[0] : 0;
        JRMCoreGuiScreen.BodyauColSub1Slcted = race.getUltimateFormColors()[1] != -1 ? race.getUltimateFormColors()[1] : 0;
        JRMCoreGuiScreen.BodyauColSub2Slcted = race.getUltimateFormColors()[2] != -1 ? race.getUltimateFormColors()[2] : 0;
        JRMCoreGuiScreen.BodyauColSub3Slcted = race.getUltimateFormColors()[3] != -1 ? race.getUltimateFormColors()[3] : 0;
    }

    @Inject(method={"csau_df"}, at={@At(value="TAIL")}, cancellable=true)
    private static void customRaceColorMenuDefColors(CallbackInfo ci) {
        MixinJRMCoreGuiScreen.setMenuDefColors();
    }

    private String textLevel1(int lvl) {
        return "\u00a78(lvl: " + lvl + ")";
    }

    @Inject(method={"drawDetails"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onDrawDetails(String s1, String s2, int xpos, int ypos, int x, int y, FontRenderer var8, CallbackInfo ci) {
        boolean isDrawingStats;
        if (!DBCAConfig.CustomRaces && !DBCAConfig.CustomForms) {
            return;
        }
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        byte formID = DataUtils.getDBCAState((EntityPlayer)player);
        if (formID <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        boolean isDrawingAttributes = (s1.contains("STR:") || s1.contains("DEX:") || s1.contains("WIL:")) && s1.contains("\u00a7");
        boolean bl = isDrawingStats = s1.contains(JRMCoreH.trl("jrmc", "mleDB") + ":") || s1.contains(JRMCoreH.trl("jrmc", "DefDB") + ":") || s1.contains(JRMCoreH.trl("jrmc", "Passive") + ":") || s1.contains(JRMCoreH.trl("jrmc", "EnPwDB") + ":") && s1.contains("\u00a7");
        if (form != null) {
            boolean isInKaioken = JRMCoreH.StusEfctsMe(5);
            String formName = form.DisplayName == null ? DBCAUtils.translate(form.getName()) : form.DisplayName;
            DBCAFormMastery mastery = form.getMastery((EntityPlayer)player);
            if (s1.contains(JRMCoreH.trl("jrmc", "TRState") + ":")) {
                String TRState2 = JRMCoreH.trl("jrmc", "TRState");
                String name = form.getColorCode() + formName;
                s1 = TRState2 + ": " + name;
                int kaiokenID = JRMCoreH.getFormID("Kaioken", JRMCoreH.Race);
                double kaiokenLevel = JRMCoreH.getFormMasteryValue((EntityPlayer)JRMCoreClient.mc.field_71439_g, kaiokenID);
                double formMasteryLevel = BigDecimal.valueOf(mastery.level).setScale(2, RoundingMode.HALF_UP).doubleValue();
                String baseName = JRMCoreH.trl("jrmc", "Base");
                DBCARace race = DBCARaces.getRace(DataUtils.getDBCARace((EntityPlayer)player));
                if (race != null) {
                    baseName = DBCAUtils.translate(race.getName() + "-BaseName");
                }
                s2 = formName + s2.split(baseName)[1].split("Lvl:")[0] + "Lvl: \u00a74" + formMasteryLevel;
                if (isInKaioken) {
                    s2 = s2 + "\n\u00a78Kaioken Mastery Lvl: \u00a74" + BigDecimal.valueOf(kaiokenLevel).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
            }
            if (isDrawingAttributes) {
                int id = -1;
                if (s1.contains("STR:")) {
                    id = 0;
                } else if (s1.contains("DEX:")) {
                    id = 1;
                } else if (s1.contains("WIL:")) {
                    id = 2;
                }
                if (s1.contains("\u00a74")) {
                    String attributeStr = s1.split(":")[1].split("\u00a74")[1];
                    s1 = s1.split("\u00a74")[0] + "\u00a76" + attributeStr;
                    s2 = JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Modified") + ": \u00a76" + attributeStr + "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Original") + ": " + JRMCoreH.cldr + JRMCoreH.PlyrAttrbts()[id] + "\n" + JRMCoreH.cldgy + s2;
                }
            } else if (isDrawingStats && s1.contains("\u00a74")) {
                s1 = s1.split("\u00a74")[0] + "\u00a76" + s1.split("\u00a74")[1];
            }
            ci.cancel();
            int wpos = var8.func_78256_a(s1);
            var8.func_78276_b(s1, xpos, ypos, 0);
            if (xpos < x && xpos + wpos > x && ypos - 3 < y && ypos + 10 > y) {
                int ll = 200;
                Object[] txt = new Object[]{s2, "\u00a78", 0, true, x + 5, y + 5, ll};
                detailList.add(txt);
            }
        }
    }
}

