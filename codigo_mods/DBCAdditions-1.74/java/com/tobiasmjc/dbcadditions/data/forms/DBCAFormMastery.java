/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.data.forms;

import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.FormMasteryData;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class DBCAFormMastery {
    private String encodedFormMastery;
    public FormMasteryData MasteryData;
    public double level;
    private int formId;
    public EntityPlayer player;

    private DBCAFormMastery(EntityPlayer player, int formId) {
        this.formId = formId;
        this.player = player;
        this.loadNBTData(player);
        this.loadMasteryData();
    }

    public DBCAFormMastery(String encodedMastery, int formId) {
        this.formId = formId;
        this.encodedFormMastery = encodedMastery;
        this.loadMasteryData();
    }

    public static DBCAFormMastery getFormMastery(EntityPlayer player, int formID) {
        return new DBCAFormMastery(player, formID);
    }

    public double calculateMultiplier() {
        return this.level * this.MasteryData.attributeMultiplier;
    }

    public void increaseFormMastery(double add) {
        this.setFormMastery(this.level + add);
    }

    public void setFormMastery(double level) {
        this.level = Math.min(level, this.MasteryData.maxLevel);
        this.saveNBTData();
    }

    public void saveNBTData() {
        DBCAPlayer dbcaPlayer = DBCAPlayer.get(this.player);
        NBTTagCompound playerNbt = dbcaPlayer.getDBCAData();
        if (playerNbt.func_74764_b("FormMastery")) {
            String existingFormMastery = playerNbt.func_74779_i("FormMastery");
            String[] entries = existingFormMastery.split("-");
            StringBuilder newFormMasteryBuilder = new StringBuilder();
            boolean found = false;
            for (String entry : entries) {
                if (entry.isEmpty()) continue;
                String[] parts = entry.split(",");
                String existingFormId = parts[0];
                if (existingFormId.equals(this.formId + "")) {
                    newFormMasteryBuilder.append(this.formId).append(",").append(this.level).append("-");
                    found = true;
                    continue;
                }
                newFormMasteryBuilder.append(entry).append("-");
            }
            if (!found) {
                newFormMasteryBuilder.append(this.formId).append(",").append(this.level).append("-");
            }
            dbcaPlayer.formMasteries = newFormMasteryBuilder.toString();
            dbcaPlayer.saveNBTData();
        } else {
            dbcaPlayer.formMasteries = "formId," + this.level + "-";
            dbcaPlayer.saveNBTData();
        }
    }

    private void loadNBTData(EntityPlayer player) {
        NBTTagCompound formMasteryCompound = DataUtils.nbt(player, "pres").func_74775_l("DBCAData");
        this.encodedFormMastery = formMasteryCompound.func_74779_i("FormMastery");
    }

    private void loadMasteryData() {
        this.level = DBCAFormMastery.getFormMasteryLevel(this.encodedFormMastery, this.formId);
        DBCAForm form = DBCAForms.getForm((int)this.formId);
        this.MasteryData = form == null ? new FormMasteryData() : form.MasteryData;
    }

    public static double getFormMasteryLevel(String encodedFormMastery, int formId) {
        double level = 0.0;
        for (String mastery : encodedFormMastery.split("-")) {
            if (!mastery.startsWith(formId + ",")) continue;
            try {
                String[] parts = mastery.split(",");
                if (parts.length <= 1) continue;
                String levelPart = parts[1].split("-")[0];
                level = Double.parseDouble(levelPart);
            }
            catch (NumberFormatException e) {
                level = 0.0;
                System.err.println("Error parsing level from mastery data: " + mastery);
            }
        }
        return level;
    }
}
