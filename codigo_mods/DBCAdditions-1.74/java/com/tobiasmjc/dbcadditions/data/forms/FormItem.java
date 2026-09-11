/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.tobiasmjc.dbcadditions.data.forms;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class FormItem {
    private static int MIN_ID = 0;
    private String displayName;
    private int id;
    private byte[] races;
    private int racialSkillLevel;
    private int godSkillLevel;
    private int kaioSkillLevel;
    private int mysticSkillLevel;
    private int ultraInstinctSkillLevel;
    private int ultraEgoSkillLevel;
    private boolean tailRequired;
    public int customForm = -1;
    private Map<Integer, Integer> requiredSkills = new HashMap<Integer, Integer>();

    public FormItem(int id, String displayName, byte[] races, int racialSkillLevel) {
        this.id = id;
        this.displayName = displayName;
        this.races = races;
        this.racialSkillLevel = racialSkillLevel;
        this.godSkillLevel = -1;
    }

    public FormItem(int id, String displayName, byte[] races, int racialSkillLevel, int godSkillLevel) {
        this.id = id;
        this.displayName = displayName;
        this.races = races;
        this.racialSkillLevel = racialSkillLevel;
        this.godSkillLevel = godSkillLevel;
    }

    public FormItem(String displayName, byte[] races, int racialSkillLevel) {
        this.id = MIN_ID++;
        this.displayName = displayName;
        this.races = races;
        this.racialSkillLevel = racialSkillLevel;
        this.godSkillLevel = -1;
    }

    public FormItem(String displayName, byte[] races, int racialSkillLevel, int godSkillLevel) {
        this.id = MIN_ID++;
        this.displayName = displayName;
        this.races = races;
        this.racialSkillLevel = racialSkillLevel;
        this.godSkillLevel = godSkillLevel;
    }

    public boolean equals(FormItem form) {
        return form.id == this.id;
    }

    public boolean isVisible() {
        return !this.displayName.isEmpty();
    }

    public boolean isCustomForm() {
        return this.getCustomForm() != null;
    }

    public FormItem addRequiredCustomSkill(DBCASkill skill, int level) {
        this.requiredSkills.put(skill.getID(), level);
        return this;
    }

    public FormItem setTailRequired(boolean required) {
        this.tailRequired = required;
        return this;
    }

    public int getID() {
        return this.id;
    }

    public FormItem setCustomForm(int customForm) {
        this.customForm = customForm;
        return this;
    }

    public DBCAForm getCustomForm() {
        return DBCAForms.getForm(this.customForm);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean canTransform(EntityPlayer player) {
        if (!DBCAConfig.CustomForms && this.isCustomForm()) {
            return false;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            DBCASkill[] skills = DBCASkills.getPlayerSkills(DataUtils.getDBCASkills(player)).toArray(new DBCASkill[0]);
            byte race = JRMCoreH.Race;
            byte customRace = DataUtils.getDBCARace(player);
            int racialSkillLevel = JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX);
            int kaioSkillLvl = JRMCoreH.SklLvl(8, JRMCoreH.Pwrtyp);
            int godSkillLvl = JRMCoreH.SklLvl(9, JRMCoreH.Pwrtyp);
            int mysticLvl = JRMCoreH.SklLvl(10, JRMCoreH.Pwrtyp);
            int ultraInstinctLevel = JRMCoreH.SklLvl(16, JRMCoreH.Pwrtyp);
            int ultraEgoLvl = JRMCoreH.SklLvl(18, JRMCoreH.Pwrtyp);
            boolean tail = JRMCoreH.tailHas(JRMCoreH.TlMd);
            return this.canTransform(race, customRace, racialSkillLevel, godSkillLvl, tail, kaioSkillLvl, mysticLvl, ultraInstinctLevel, ultraEgoLvl, skills);
        }
        DBCASkill[] skills = DBCASkills.getPlayerSkills(DataUtils.getDBCASkills(player)).toArray(new DBCASkill[0]);
        JGPlayerMP jgPlayer = new JGPlayerMP(player);
        jgPlayer.connectBaseNBT();
        boolean tailMode = JRMCoreH.tailHas(JRMCoreH.getByte(player, "jrmcTlmd"));
        byte customRace = DataUtils.getDBCARace(player);
        int racialSkillLevel = JRMCoreH.SklLvlX(1, jgPlayer.getNBT().func_74779_i("jrmcSSltX"));
        int kaioSkillLvl = JRMCoreH.SklLvl(8, player);
        int godSkillLvl = JRMCoreH.SklLvl(9, player);
        int mysticLvl = JRMCoreH.SklLvl(10, player);
        int ultraInstinctLevel = JRMCoreH.SklLvl(16, player);
        int ultraEgoLvl = JRMCoreH.SklLvl(18, player);
        return this.canTransform(jgPlayer.getRace(), customRace, racialSkillLevel, godSkillLvl, tailMode, kaioSkillLvl, mysticLvl, ultraInstinctLevel, ultraEgoLvl, skills);
    }

    public boolean canTransform(byte race, byte customRace, int racialSkillLevel, int godSkillLevel, boolean hasTail, int kaioLvl, int mysticLvl, int uiLvl, int ueLvl, DBCASkill[] skills) {
        if (!DBCAConfig.CustomForms && this.isCustomForm()) {
            return false;
        }
        if (this.isCustomForm() && !this.getCustomForm().isEnabled()) {
            return false;
        }
        if (this.tailRequired && !hasTail) {
            return false;
        }
        if (this.kaioSkillLevel > kaioLvl || this.mysticSkillLevel > mysticLvl || this.ultraInstinctSkillLevel > uiLvl || this.ultraEgoSkillLevel > ueLvl) {
            return false;
        }
        if (!this.requiredSkills.isEmpty()) {
            for (Map.Entry<Integer, Integer> requiredSkill : this.requiredSkills.entrySet()) {
                boolean requirementMet = false;
                for (DBCASkill skill : skills) {
                    if (skill.getID() == requiredSkill.getKey().intValue() && skill.getLevel() >= requiredSkill.getValue()) {
                        requirementMet = true;
                        break;
                    }
                }
                if (!requirementMet) {
                    return false;
                }
            }
        }
        return this.isRaceCorrect(race, customRace) && godSkillLevel > this.godSkillLevel && racialSkillLevel > this.racialSkillLevel;
    }

    public boolean isRaceCorrect(byte race, byte customRace) {
        for (byte r : this.races) {
            if (!(customRace > 0 ? customRace + 10 == r : race == r)) continue;
            return true;
        }
        return false;
    }

    public void setSkillLevels(int kaio, int mystic, int ui, int ue) {
        this.kaioSkillLevel = kaio;
        this.mysticSkillLevel = mystic;
        this.ultraInstinctSkillLevel = ui;
        this.ultraEgoSkillLevel = ue;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("DisplayName", this.displayName);
        compound.func_74768_a("ID", this.id);
        compound.func_74783_a("Races", DataUtils.toIntArray(this.races));
        compound.func_74768_a("RacialSkillLevel", this.racialSkillLevel);
        compound.func_74768_a("GodSkillLevel", this.godSkillLevel);
        compound.func_74768_a("KaioSkillLevel", this.kaioSkillLevel);
        compound.func_74768_a("MysticSkillLevel", this.mysticSkillLevel);
        compound.func_74768_a("UISkillLevel", this.ultraInstinctSkillLevel);
        compound.func_74768_a("UESkillLevel", this.ultraEgoSkillLevel);
        compound.func_74757_a("TailRequired", this.tailRequired);
        compound.func_74768_a("CustomForm", this.customForm);
        NBTTagList skillsList = new NBTTagList();
        for (Map.Entry<Integer, Integer> entry : this.requiredSkills.entrySet()) {
            NBTTagCompound skillTag = new NBTTagCompound();
            skillTag.func_74768_a("SkillID", entry.getKey().intValue());
            skillTag.func_74768_a("SkillLevel", entry.getValue().intValue());
            skillsList.func_74742_a((NBTBase)skillTag);
        }
        compound.func_74782_a("RequiredSkills", (NBTBase)skillsList);
        return compound;
    }

    public static FormItem read(NBTTagCompound compound) {
        String displayName = compound.func_74779_i("DisplayName");
        byte[] races = DataUtils.toByteArray(compound.func_74759_k("Races"));
        int id = compound.func_74762_e("ID");
        int racialSkillLevel = compound.func_74762_e("RacialSkillLevel");
        int godSkillLevel = compound.func_74762_e("GodSkillLevel");
        int kaioSkillLevel = compound.func_74762_e("KaioSkillLevel");
        int mysticSkillLevel = compound.func_74762_e("MysticSkillLevel");
        int ueSkillLevel = compound.func_74762_e("UESkillLevel");
        int uiSkillLevel = compound.func_74762_e("UISkillLevel");
        boolean tailRequired = compound.func_74767_n("TailRequired");
        FormItem form = new FormItem(id, displayName, races, racialSkillLevel, godSkillLevel);
        form.setTailRequired(tailRequired);
        form.setCustomForm(compound.func_74762_e("CustomForm"));
        form.setSkillLevels(kaioSkillLevel, mysticSkillLevel, uiSkillLevel, ueSkillLevel);
        NBTTagList skillsList = compound.func_150295_c("RequiredSkills", 10);
        for (int i = 0; i < skillsList.func_74745_c(); ++i) {
            NBTTagCompound skillTag = skillsList.func_150305_b(i);
            form.addRequiredCustomSkill(DBCASkills.getSkill(skillTag.func_74762_e("SkillID")), skillTag.func_74762_e("SkillLevel"));
        }
        return form;
    }
}
