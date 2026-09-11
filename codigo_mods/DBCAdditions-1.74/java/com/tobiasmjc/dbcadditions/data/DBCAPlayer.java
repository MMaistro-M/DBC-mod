/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 */
package com.tobiasmjc.dbcadditions.data;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;

public class DBCAPlayer {
    public String formMasteries;
    public int selectedForm;
    public int DBAForm;
    public int DBARace;
    public boolean PotaraFusion;
    public int PotaraCooldown;
    public String Skills;
    private EntityPlayer p;
    private NBTTagCompound nbt;
    private NBTTagCompound dbcaData;
    private JGPlayerMP jgPlayer;

    public DBCAPlayer(EntityPlayer player) {
        this.p = player;
        this.nbt = player.getEntityData().func_74775_l("PlayerPersisted");
        this.jgPlayer = new JGPlayerMP(player);
        this.jgPlayer.connectBaseNBT();
        this.loadNBTData();
    }

    public void ascendDBCForm(byte form) {
        this.nbt.func_74774_a("jrmcState", form);
        this.nbt.func_74774_a("jrmcSaiRg", (byte)0);
    }

    public void ascendDBAForm(int form) {
        this.setDBAForm(form);
    }

    public void descend() {
        this.setDBAForm(0);
    }

    public byte getRelease() {
        return this.nbt.func_74771_c("jrmcRelease");
    }

    public byte getRace() {
        return this.nbt.func_74771_c("jrmcRace");
    }

    public byte getDBCForm() {
        return this.nbt.func_74771_c("jrmcState");
    }

    public void setPotaraFusion(boolean p) {
        this.PotaraFusion = p;
        this.saveNBTData();
    }

    private void setDBAForm(int form) {
        if (form > 0) {
            byte race = this.nbt.func_74771_c("jrmcRace");
            if (JRMCoreH.isRaceArcosian(race)) {
                if (form != 4) {
                    this.nbt.func_74774_a("jrmcState", (byte)4);
                }
            } else {
                this.nbt.func_74774_a("jrmcState", (byte)0);
            }
        }
        this.DBAForm = form;
        this.saveNBTData();
    }

    public void saveNBTData() {
        NBTTagCompound dbcCompound = new NBTTagCompound();
        if (this.nbt.func_74764_b("DBCAData")) {
            dbcCompound = this.nbt.func_74775_l("DBCAData");
        }
        dbcCompound.func_74768_a("SelectedForm", this.selectedForm);
        dbcCompound.func_74768_a("DBCAForm", this.DBAForm);
        dbcCompound.func_74768_a("DBCARace", this.DBARace);
        dbcCompound.func_74768_a("PotaraCooldown", this.PotaraCooldown);
        dbcCompound.func_74774_a("PotaraFusion", this.PotaraFusion ? (byte)1 : 0);
        dbcCompound.func_74778_a("Skills", this.Skills);
        dbcCompound.func_74778_a("FormMastery", this.formMasteries);
        this.nbt.func_74782_a("DBCAData", (NBTBase)dbcCompound);
    }

    public void loadNBTData() {
        this.dbcaData = this.nbt.func_74775_l("DBCAData");
        this.DBAForm = this.dbcaData.func_74764_b("DBCAForm") ? this.dbcaData.func_74762_e("DBCAForm") : -1;
        this.DBARace = this.dbcaData.func_74764_b("DBCARace") ? this.dbcaData.func_74762_e("DBCARace") : -1;
        int n = this.PotaraCooldown = this.dbcaData.func_74764_b("PotaraCooldown") ? this.dbcaData.func_74762_e("PotaraCooldown") : 0;
        this.PotaraFusion = this.dbcaData.func_74764_b("PotaraFusion") ? this.dbcaData.func_74771_c("PotaraFusion") != 0 : false;
        this.selectedForm = this.dbcaData.func_74764_b("SelectedForm") ? this.dbcaData.func_74762_e("SelectedForm") : -1;
        this.formMasteries = this.dbcaData.func_74764_b("FormMastery") ? this.dbcaData.func_74779_i("FormMastery") : "";
        this.Skills = this.dbcaData.func_74764_b("Skills") ? this.dbcaData.func_74779_i("Skills") : "";
    }

    public double getFormMastery(int formId) {
        double level = 0.0;
        for (String mastery : this.formMasteries.split(";")) {
            if (!mastery.startsWith(formId + ",")) continue;
            try {
                String[] parts = mastery.split(",");
                if (parts.length <= 1) continue;
                String levelPart = parts[1].split(";")[0];
                level = Double.parseDouble(levelPart);
            }
            catch (NumberFormatException e) {
                level = 0.0;
                System.err.println("Error parsing level from mastery data: " + mastery);
            }
        }
        return level;
    }

    public void learnSkill(DBCASkill skill) {
        if (this.hasSkill(skill)) {
            return;
        }
        if (!DBCASkills.getPlayerSkillsID(this.Skills).contains(skill.getID())) {
            this.Skills = this.Skills + skill.getID() + ",1-";
            this.saveNBTData();
        }
    }

    public void removeSkill(DBCASkill skill) {
        if (!this.hasSkill(skill)) {
            return;
        }
        String skills = this.dbcaData.func_74764_b("Skills") ? this.dbcaData.func_74779_i("Skills") : "";
        String newSkills = "";
        for (String skillStr : skills.split("-")) {
            String[] parts = skillStr.split(",");
            if (parts.length < 2) continue;
            int id;
            int level;
            try {
                id = Integer.parseInt(parts[0]);
                level = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e) {
                continue;
            }
            if (id == skill.getID()) continue;
            newSkills = newSkills + id + "," + level + "-";
        }
        this.Skills = newSkills;
        this.saveNBTData();
    }

    public NBTTagCompound getDBCAData() {
        return this.dbcaData;
    }

    public void setSkillLevel(DBCASkill skill, int lv) {
        if (!this.hasSkill(skill)) {
            return;
        }
        String newSkills = "";
        for (String skillStr : this.Skills.split("-")) {
            String[] parts = skillStr.split(",");
            if (parts.length < 2) continue;
            int id;
            int level;
            try {
                id = Integer.parseInt(parts[0]);
                level = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e) {
                continue;
            }
            if (id == skill.getID()) {
                level = Math.min(lv, skill.getMaxLevel());
            }
            newSkills = newSkills + id + "," + level + "-";
        }
        this.Skills = newSkills;
        this.saveNBTData();
    }

    public void increaseSkillLevel(DBCASkill skill) {
        this.setSkillLevel(skill, skill.getLevel() + 1);
    }

    public boolean hasSkill(DBCASkill skill) {
        return DBCASkills.getPlayerSkillsID(this.Skills).contains(skill.getID());
    }

    public void fuse(EntityPlayer pl2, boolean potara) {
        byte race = this.nbt.func_74771_c("jrmcRace");
        int[] playerAttributes = this.jgPlayer.getAttributes();
        String StE = this.jgPlayer.getStatusEffects();
        String fusionMembers = this.nbt.func_74779_i("jrmcFuzion");
        if (fusionMembers.equals(" ")) {
            NBTTagCompound nbt2;
            byte rc2;
            this.jgPlayer.setTransformationMeter(0);
            if (!pl2.func_70005_c_().equals(this.p.func_70005_c_()) && JRMCoreH.race_match(race, rc2 = (nbt2 = DataUtils.nbt(pl2, "pres")).func_74771_c("jrmcRace"))) {
                DBCAPlayer dbcaPl2 = DBCAPlayer.get(pl2);
                if (this.DBARace != dbcaPl2.DBARace) {
                    return;
                }
                boolean isNotFused2 = !JRMCoreH.isFused((Entity)pl2);
                String StE2 = nbt2.func_74779_i("jrmcStatusEff");
                if (isNotFused2) {
                    JRMCoreH.PlyrSettingsRem(nbt2, 4);
                    JRMCoreH.PlyrSettingsRem(this.nbt, 4);
                    StE = JRMCoreH.StusEfcts(10, StE, this.nbt, true);
                    StE2 = JRMCoreH.StusEfcts(11, StE2, nbt2, true);
                    String fznn = JRMCoreHDBC.f_namgen(this.p.func_70005_c_(), pl2.func_70005_c_());
                    int FznTime = JRMCoreConfig.FznTime;
                    if (potara) {
                        FznTime = DBCAConfig.PotaraTime * 12;
                    }
                    nbt2.func_74778_a("jrmcFuzion", this.p.func_70005_c_() + "," + pl2.func_70005_c_() + "," + FznTime);
                    this.nbt.func_74778_a("jrmcFuzion", this.p.func_70005_c_() + "," + pl2.func_70005_c_() + "," + FznTime);
                    mod_DragonBC.logger.info(this.p.func_70005_c_() + " and " + pl2.func_70005_c_() + " fused to " + fznn + "!");
                    String t = JRMCoreH.trlai("dbc", "playersFused");
                    if (potara) {
                        t = DBCAUtils.translate("potaraFuse");
                    }
                    this.p.func_145747_a(new ChatComponentTranslation(t, new Object[]{this.p.func_70005_c_(), pl2.func_70005_c_(), fznn}).func_150255_a(DBCPacketHandlerServer.styleYellow));
                    pl2.func_145747_a(new ChatComponentTranslation(t, new Object[]{this.p.func_70005_c_(), pl2.func_70005_c_(), fznn}).func_150255_a(DBCPacketHandlerServer.styleYellow));
                    this.p.field_70170_p.func_72956_a((Entity)this.p, "jinryuudragonbc:DBC.fusefin", 0.15f, 1.0f);
                    StE = JRMCoreH.StusEfcts(3, StE, this.nbt, false);
                    StE = JRMCoreH.StusEfcts(5, StE, this.nbt, false);
                    StE = JRMCoreH.StusEfcts(4, StE, this.nbt, false);
                    StE2 = JRMCoreH.StusEfcts(3, StE2, nbt2, false);
                    StE2 = JRMCoreH.StusEfcts(5, StE2, nbt2, false);
                    StE2 = JRMCoreH.StusEfcts(4, StE2, nbt2, false);
                    this.nbt.func_74774_a("jrmcState2", (byte)0);
                    nbt2.func_74774_a("jrmcState2", (byte)0);
                    String[] PlyrSkills = JRMCoreH.PlyrSkills(this.p);
                    byte pwr = this.nbt.func_74771_c("jrmcPwrtyp");
                    byte rce = this.nbt.func_74771_c("jrmcRace");
                    byte cls = this.nbt.func_74771_c("jrmcClass");
                    int maxBody = JRMCoreH.stat((Entity)this.p, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0f);
                    int ki = JRMCoreH.stat((Entity)this.p, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, (int)pwr));
                    playerAttributes = JRMCoreH.PlyrAttrbts(pl2);
                    PlyrSkills = JRMCoreH.PlyrSkills(pl2);
                    int maxBodyF = JRMCoreH.stat((Entity)pl2, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0f);
                    int kiF = JRMCoreH.stat((Entity)pl2, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, (int)pwr));
                    double curBody = this.nbt.func_74762_e("jrmcBdy");
                    double curEn = this.nbt.func_74762_e("jrmcEnrgy");
                    this.nbt.func_74768_a("jrmcBdy", (int)(curBody / (double)maxBody * (double)maxBodyF));
                    this.nbt.func_74768_a("jrmcEnrgy", (int)(curEn / (double)ki * (double)kiF));
                    if (potara) {
                        this.setPotaraFusion(true);
                        this.saveNBTData();
                        DBCAPlayer p2 = DBCAPlayer.get(pl2);
                        p2.setPotaraFusion(true);
                        p2.saveNBTData();
                    }
                }
            }
        }
    }

    public static DBCAPlayer get(EntityPlayer player) {
        DBCAPlayer p = new DBCAPlayer(player);
        return p;
    }
}
