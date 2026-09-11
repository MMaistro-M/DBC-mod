/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.entity;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;

public interface IDBCPlayer
extends IPlayer {
    public void setStat(String var1, int var2);

    public int getStat(String var1);

    public void addBonusAttribute(String var1, String var2, String var3, double var4);

    public void addBonusAttribute(String var1, String var2, String var3, double var4, boolean var6);

    public void addToBonusAttribute(String var1, String var2, String var3, double var4);

    public void setBonusAttribute(String var1, String var2, String var3, double var4);

    public void getBonusAttribute(String var1, String var2);

    public void removeBonusAttribute(String var1, String var2);

    public void clearBonusAttribute(String var1);

    public String bonusAttribute(String var1, String var2, String var3);

    public String bonusAttribute(String var1, String var2, String var3, String var4, double var5, boolean var7);

    public void setRelease(byte var1);

    public byte getRelease();

    public void setBody(int var1);

    public int getBody();

    public void setHP(int var1);

    public int getHP();

    public void setStamina(int var1);

    public int getStamina();

    public void setKi(int var1);

    public int getKi();

    public void setTP(int var1);

    public int getTP();

    public void setGravity(float var1);

    public float getGravity();

    public boolean isBlocking();

    public void setHairCode(String var1);

    public String getHairCode();

    public void setExtraCode(String var1);

    public String getExtraCode();

    public void setItem(IItemStack var1, byte var2, boolean var3);

    public IItemStack getItem(byte var1, boolean var2);

    @Override
    public IItemStack[] getInventory();

    public void setForm(byte var1);

    public byte getForm();

    public void setForm2(byte var1);

    public byte getForm2();

    public double getRacialFormMastery(byte var1);

    public void setRacialFormMastery(byte var1, double var2);

    public void addRacialFormMastery(byte var1, double var2);

    public double getOtherFormMastery(String var1);

    public void setOtherFormMastery(String var1, double var2);

    public void addOtherFormMastery(String var1, double var2);

    public void setPowerPoints(int var1);

    public int getPowerPoints();

    public void setAuraColor(int var1);

    public int getAuraColor();

    public void setFormLevel(int var1);

    public int getFormLevel();

    public void setSkills(String var1);

    public String getSkills();

    public void setJRMCSE(String var1);

    public String getJRMCSE();

    public void setRace(byte var1);

    public int getRace();

    public void setDBCClass(byte var1);

    public byte getDBCClass();

    public void setPowerType(byte var1);

    public int getPowerType();

    public int getKillCount(String var1);

    public String getFusionString();
}

