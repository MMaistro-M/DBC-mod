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
        this.nbt = player.getEntityData().getCompoundTag("PlayerPersisted");
        this.jgPlayer = new JGPlayerMP(player);
        this.jgPlayer.connectBaseNBT();
        this.loadNBTData();
    }

    public void ascendDBCForm(byte form) {
        this.nbt.setByte("jrmcState", form);
        this.nbt.setByte("jrmcSaiRg", (byte)0);
    }

    public void ascendDBAForm(int form) {
        this.setDBAForm(form);
    }

    public void descend() {
        this.setDBAForm(0);
    }

    public byte getRelease() {
        return this.nbt.getByte("jrmcRelease");
    }

    public byte getRace() {
        return this.nbt.getByte("jrmcRace");
    }

    public byte getDBCForm() {
        return this.nbt.getByte("jrmcState");
    }

    public void setPotaraFusion(boolean p) {
        this.PotaraFusion = p;
        this.saveNBTData();
    }

    private void setDBAForm(int form) {
        if (form > 0) {
            byte race = this.nbt.getByte("jrmcRace");
            if (JRMCoreH.isRaceArcosian(race)) {
                if (form != 4) {
                    this.nbt.setByte("jrmcState", (byte)4);
                }
            } else {
                this.nbt.setByte("jrmcState", (byte)0);
            }
        }
        this.DBAForm = form;
        this.saveNBTData();
    }

    public void saveNBTData() {
        NBTTagCompound dbcCompound = new NBTTagCompound();
        if (this.nbt.hasKey("DBCAData")) {
            dbcCompound = this.nbt.getCompoundTag("DBCAData");
        }
        dbcCompound.setInteger("SelectedForm", this.selectedForm);
        dbcCompound.setInteger("DBCAForm", this.DBAForm);
        dbcCompound.setInteger("DBCARace", this.DBARace);
        dbcCompound.setInteger("PotaraCooldown", this.PotaraCooldown);
        dbcCompound.setByte("PotaraFusion", this.PotaraFusion ? (byte)1 : 0);
        dbcCompound.setString("Skills", this.Skills);
        dbcCompound.setString("FormMastery", this.formMasteries);
        this.nbt.setTag("DBCAData", (NBTBase)dbcCompound);
    }

    public void loadNBTData() {
        this.dbcaData = this.nbt.getCompoundTag("DBCAData");
        this.DBAForm = this.dbcaData.hasKey("DBCAForm") ? this.dbcaData.getInteger("DBCAForm") : -1;
        this.DBARace = this.dbcaData.hasKey("DBCARace") ? this.dbcaData.getInteger("DBCARace") : -1;
        int n = this.PotaraCooldown = this.dbcaData.hasKey("PotaraCooldown") ? this.dbcaData.getInteger("PotaraCooldown") : 0;
        this.PotaraFusion = this.dbcaData.hasKey("PotaraFusion") ? this.dbcaData.getByte("PotaraFusion") != 0 : false;
        this.selectedForm = this.dbcaData.hasKey("SelectedForm") ? this.dbcaData.getInteger("SelectedForm") : -1;
        this.formMasteries = this.dbcaData.hasKey("FormMastery") ? this.dbcaData.getString("FormMastery") : "";
        this.Skills = this.dbcaData.hasKey("Skills") ? this.dbcaData.getString("Skills") : "";
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
        String skills = this.dbcaData.hasKey("Skills") ? this.dbcaData.getString("Skills") : "";
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
        byte race = this.nbt.getByte("jrmcRace");
        int[] playerAttributes = this.jgPlayer.getAttributes();
        String StE = this.jgPlayer.getStatusEffects();
        String fusionMembers = this.nbt.getString("jrmcFuzion");
        if (fusionMembers.equals(" ")) {
            NBTTagCompound nbt2;
            byte rc2;
            this.jgPlayer.setTransformationMeter(0);
            if (!pl2.getCommandSenderName().equals(this.p.getCommandSenderName()) && JRMCoreH.race_match(race, rc2 = (nbt2 = DataUtils.nbt(pl2, "pres")).getByte("jrmcRace"))) {
                DBCAPlayer dbcaPl2 = DBCAPlayer.get(pl2);
                if (this.DBARace != dbcaPl2.DBARace) {
                    return;
                }
                boolean isNotFused2 = !JRMCoreH.isFused((Entity)pl2);
                String StE2 = nbt2.getString("jrmcStatusEff");
                if (isNotFused2) {
                    JRMCoreH.PlyrSettingsRem(nbt2, 4);
                    JRMCoreH.PlyrSettingsRem(this.nbt, 4);
                    StE = JRMCoreH.StusEfcts(10, StE, this.nbt, true);
                    StE2 = JRMCoreH.StusEfcts(11, StE2, nbt2, true);
                    String fznn = JRMCoreHDBC.f_namgen(this.p.getCommandSenderName(), pl2.getCommandSenderName());
                    int FznTime = JRMCoreConfig.FznTime;
                    if (potara) {
                        FznTime = DBCAConfig.PotaraTime * 12;
                    }
                    nbt2.setString("jrmcFuzion", this.p.getCommandSenderName() + "," + pl2.getCommandSenderName() + "," + FznTime);
                    this.nbt.setString("jrmcFuzion", this.p.getCommandSenderName() + "," + pl2.getCommandSenderName() + "," + FznTime);
                    mod_DragonBC.logger.info(this.p.getCommandSenderName() + " and " + pl2.getCommandSenderName() + " fused to " + fznn + "!");
                    String t = JRMCoreH.trlai("dbc", "playersFused");
                    if (potara) {
                        t = DBCAUtils.translate("potaraFuse");
                    }
                    this.p.addChatMessage(new ChatComponentTranslation(t, new Object[]{this.p.getCommandSenderName(), pl2.getCommandSenderName(), fznn}).setChatStyle(DBCPacketHandlerServer.styleYellow));
                    pl2.addChatMessage(new ChatComponentTranslation(t, new Object[]{this.p.getCommandSenderName(), pl2.getCommandSenderName(), fznn}).setChatStyle(DBCPacketHandlerServer.styleYellow));
                    this.p.worldObj.playSoundAtEntity((Entity)this.p, "jinryuudragonbc:DBC.fusefin", 0.15f, 1.0f);
                    StE = JRMCoreH.StusEfcts(3, StE, this.nbt, false);
                    StE = JRMCoreH.StusEfcts(5, StE, this.nbt, false);
                    StE = JRMCoreH.StusEfcts(4, StE, this.nbt, false);
                    StE2 = JRMCoreH.StusEfcts(3, StE2, nbt2, false);
                    StE2 = JRMCoreH.StusEfcts(5, StE2, nbt2, false);
                    StE2 = JRMCoreH.StusEfcts(4, StE2, nbt2, false);
                    this.nbt.setByte("jrmcState2", (byte)0);
                    nbt2.setByte("jrmcState2", (byte)0);
                    String[] PlyrSkills = JRMCoreH.PlyrSkills(this.p);
                    byte pwr = this.nbt.getByte("jrmcPwrtyp");
                    byte rce = this.nbt.getByte("jrmcRace");
                    byte cls = this.nbt.getByte("jrmcClass");
                    int maxBody = JRMCoreH.stat((Entity)this.p, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0f);
                    int ki = JRMCoreH.stat((Entity)this.p, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, (int)pwr));
                    playerAttributes = JRMCoreH.PlyrAttrbts(pl2);
                    PlyrSkills = JRMCoreH.PlyrSkills(pl2);
                    int maxBodyF = JRMCoreH.stat((Entity)pl2, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0f);
                    int kiF = JRMCoreH.stat((Entity)pl2, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, (int)pwr));
                    double curBody = this.nbt.getInteger("jrmcBdy");
                    double curEn = this.nbt.getInteger("jrmcEnrgy");
                    this.nbt.setInteger("jrmcBdy", (int)(curBody / (double)maxBody * (double)maxBodyF));
                    this.nbt.setInteger("jrmcEnrgy", (int)(curEn / (double)ki * (double)kiF));
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
