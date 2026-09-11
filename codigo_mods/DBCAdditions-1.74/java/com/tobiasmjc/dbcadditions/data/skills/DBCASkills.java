/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.data.skills;

import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBCASkills {
    public static Set<DBCASkill> HOST_SKILLS = new HashSet<DBCASkill>();
    public static Set<DBCASkill> SKILLS = new HashSet<DBCASkill>();
    public static DBCASkill Beast = new DBCASkill("beast", "dbcadditions.beast", 75000, 10, 1, new byte[]{1, 2, 0}).setMaster(DBCASkill.Master.GOHAN);
    public static DBCASkill NamekianPotential = new DBCASkill("namekian_potential", "dbcadditions.namekian_potential", 12000, 10, 2, new byte[]{3}).setMaster(DBCASkill.Master.PICCOLO);
    public static DBCASkill ArcosianPotential = new DBCASkill("arcosian_potential", "dbcadditions.arcosian_potential", 100000, 10, 1, new byte[]{4}).setMaster(DBCASkill.Master.FRIEZA);

    public static List<Integer> getPlayerSkillsID(String skillStr) {
        ArrayList<Integer> skills = new ArrayList<Integer>();
        if (skillStr == null || skillStr.isEmpty()) {
            return skills;
        }
        for (String skill : skillStr.split("-")) {
            if (skill.isEmpty()) continue;
            try {
                String[] parts = skill.split(",");
                if (parts.length < 1) continue;
                skills.add(Integer.parseInt(parts[0]));
            }
            catch (NumberFormatException e) {
                // Ignore stale or malformed player skill data.
            }
        }
        return skills;
    }

    public static List<DBCASkill> getPlayerSkills(String skillStr) {
        ArrayList<DBCASkill> skills = new ArrayList<DBCASkill>();
        if (skillStr == null || skillStr.isEmpty()) {
            return skills;
        }
        for (String skill : skillStr.split("-")) {
            if (skill.isEmpty()) continue;
            try {
                String[] parts = skill.split(",");
                if (parts.length < 2) continue;
                int id = Integer.parseInt(parts[0]);
                int level = Integer.parseInt(parts[1]);
                DBCASkill dbcaSkill = DBCASkills.getSkill(id);
                if (dbcaSkill == null) continue;
                dbcaSkill.setLevel(level);
                skills.add(dbcaSkill);
            }
            catch (NumberFormatException e) {
                // Ignore stale or malformed player skill data.
            }
        }
        return skills;
    }

    public static List<String> getPlayerSkillsNames(String skillStr) {
        ArrayList<String> skills = new ArrayList<String>();
        if (skillStr == null || skillStr.isEmpty()) {
            return skills;
        }
        for (String skill : skillStr.split("-")) {
            if (skill.isEmpty()) continue;
            try {
                String[] parts = skill.split(",");
                if (parts.length < 1) continue;
                DBCASkill dbcaSkill = DBCASkills.getSkill(Integer.parseInt(parts[0]));
                if (dbcaSkill == null) continue;
                skills.add(dbcaSkill.getName());
            }
            catch (NumberFormatException e) {
                // Ignore stale or malformed player skill data.
            }
        }
        return skills;
    }

    public static DBCASkill getPlayerSkill(String str, int id) {
        for (DBCASkill skill : DBCASkills.getPlayerSkills(str)) {
            if (skill.getID() != id) continue;
            return skill;
        }
        return null;
    }

    public static DBCASkill getSkill(int id) {
        for (DBCASkill skill : SKILLS) {
            if (skill.getID() != id) continue;
            return skill;
        }
        return null;
    }

    public static DBCASkill getSkillHost(String name) {
        for (DBCASkill skill : HOST_SKILLS) {
            if (!skill.getName().equalsIgnoreCase(name)) continue;
            return skill;
        }
        return null;
    }

    public static DBCASkill getSkill(String name) {
        for (DBCASkill skill : SKILLS) {
            if (!skill.getName().equalsIgnoreCase(name)) continue;
            return skill;
        }
        return null;
    }

    public static void registerSkill(DBCASkill skill) {
        HOST_SKILLS.add(skill);
    }

    public static void registerSkills() {
        DBCASkills.registerSkill(Beast);
        DBCASkills.registerSkill(NamekianPotential);
        DBCASkills.registerSkill(ArcosianPotential);
    }
}
