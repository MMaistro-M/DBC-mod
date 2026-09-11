/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityTameable
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.passive.EntityTameable;
import noppes.npcs.api.entity.IAnimal;

public interface IPixelmon<T extends EntityTameable>
extends IAnimal<T> {
    public boolean getIsShiny();

    public void setIsShiny(boolean var1);

    public int getLevel();

    public void setLevel(int var1);

    public int getIV(int var1);

    public void setIV(int var1, int var2);

    public int getEV(int var1);

    public void setEV(int var1, int var2);

    public int getStat(int var1);

    public void setStat(int var1, int var2);

    public int getSize();

    public void setSize(int var1);

    public int getHapiness();

    public void setHapiness(int var1);

    public int getNature();

    public void setNature(int var1);

    public int getPokeball();

    public void setPokeball(int var1);

    public String getNickname();

    public boolean hasNickname();

    public void setNickname(String var1);

    public String getMove(int var1);

    public void setMove(int var1, String var2);
}

