/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.data.skills;

import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;

public class DBCASkill {
    private static List<String> forbidden = new ArrayList<String>(Arrays.asList("FZ", "JP", "DS", "FL", "EN", "OC", "KS", "MD", "KK", "GF", "OK", "KP", "KF", "KB", "DF", "KI", "UI", "IT", "GD"));
    private static int ID = 0;
    private String displayName;
    private String description;
    private int id;
    private String name;
    private int level;
    private int maxLevel;
    private int tpCost;
    private int mindCost;
    private boolean isEnabled = true;
    public Master SkillMaster = Master.NONE;
    public byte[] races;

    public DBCASkill(String name, String displayName, int tpCost, int mindCost, int maxLevel, byte[] races) {
        this.races = new byte[]{1, 2, 4, 3, 0, 5, (byte)(DBCARaces.BIO_ANDROID.ID + 10)};
        this.name = name;
        this.description = displayName + "-Desc";
        this.maxLevel = maxLevel;
        this.tpCost = tpCost;
        this.mindCost = mindCost;
        this.id = ID++;
        this.races = races;
        this.displayName = displayName;
        forbidden.add(this.idToCode());
    }

    public DBCASkill(String name, String displayName, int tpCost, int mindCost, int maxLevel) {
        this.races = new byte[]{1, 2, 4, 3, 0, 5, (byte)(DBCARaces.BIO_ANDROID.ID + 10)};
        this.name = name;
        this.description = displayName + "-Desc";
        this.maxLevel = maxLevel;
        this.tpCost = tpCost;
        this.mindCost = mindCost;
        this.id = ID++;
        this.displayName = displayName;
        forbidden.add(this.idToCode());
    }

    public DBCASkill(String name, String displayName, int maxLevel) {
        this.races = new byte[]{1, 2, 4, 3, 0, 5, (byte)(DBCARaces.BIO_ANDROID.ID + 10)};
        this.name = name;
        this.description = displayName + "-Desc";
        this.maxLevel = maxLevel;
        this.tpCost = 500;
        this.mindCost = 5;
        this.id = ID++;
        this.displayName = displayName;
        forbidden.add(this.idToCode());
    }

    public String idToCode() {
        int count = 0;
        int i = 0;
        while (true) {
            int first = i / 26;
            int second = i % 26;
            if (first >= 26) {
                throw new IllegalArgumentException("Too many custom skills have been created! Limit is 660!");
            }
            char firstLetter = (char)(65 + first);
            char secondLetter = (char)(65 + second);
            String code = "" + firstLetter + secondLetter;
            if (!forbidden.contains(code)) {
                if (count == this.id) {
                    return code;
                }
                ++count;
            }
            ++i;
        }
    }

    public DBCASkill setMaster(Master m) {
        this.SkillMaster = m;
        return this;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getID() {
        return this.id;
    }

    public boolean race(byte race, byte customRace) {
        for (byte r : this.races) {
            if (!(customRace > 0 ? customRace + 10 == r : race == r)) continue;
            return true;
        }
        return false;
    }

    public void setTPCost(int tpCost) {
        this.tpCost = tpCost;
    }

    public void setMindCost(int mindCost) {
        this.mindCost = mindCost;
    }

    public int getTPCost() {
        return this.tpCost;
    }

    public int getMindCost() {
        return this.mindCost;
    }

    public String getName() {
        return this.name.replace(' ', '_');
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("SkillID", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74757_a("Enabled", this.isEnabled);
        compound.func_74778_a("DisplayName", this.displayName);
        compound.func_74778_a("Description", this.description);
        compound.func_74768_a("Level", this.level);
        compound.func_74768_a("MaxLevel", this.maxLevel);
        compound.func_74768_a("TPCost", this.tpCost);
        compound.func_74768_a("MindCost", this.mindCost);
        compound.func_74773_a("Races", this.races);
        compound.func_74768_a("SkillMaster", this.SkillMaster.ordinal());
        return compound;
    }

    public static DBCASkill read(NBTTagCompound compound) {
        int id = compound.func_74762_e("SkillID");
        String name = compound.func_74779_i("Name");
        String displayName = compound.func_74779_i("DisplayName");
        String description = compound.func_74779_i("Description");
        int level = compound.func_74762_e("Level");
        int maxLevel = compound.func_74762_e("MaxLevel");
        int tpCost = compound.func_74762_e("TPCost");
        int mindCost = compound.func_74762_e("MindCost");
        byte[] races = compound.func_74770_j("Races");
        Master master = Master.values()[compound.func_74762_e("SkillMaster")];
        DBCASkill skill = new DBCASkill(name, displayName, tpCost, mindCost, maxLevel, races);
        skill.id = id;
        skill.setDescription(description);
        skill.setLevel(level);
        skill.setEnabled(compound.func_74767_n("Enabled"));
        skill.SkillMaster = master;
        return skill;
    }

    public static enum Master {
        NONE,
        GOKU,
        GOHAN,
        PICCOLO,
        WHIS,
        OLD_KAI,
        BEERUS,
        KAIO,
        VEGETA,
        TRUNKS,
        FRIEZA,
        ROSHI,
        CELL,
        BABIDI;

    }
}

