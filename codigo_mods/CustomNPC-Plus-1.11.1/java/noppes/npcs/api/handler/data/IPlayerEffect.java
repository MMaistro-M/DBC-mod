/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.entity.IPlayer;

public interface IPlayerEffect {
    public void kill();

    public int getId();

    public int getDuration();

    public void setDuration(int var1);

    public byte getLevel();

    public void setLevel(byte var1);

    public String getName();

    public void performEffect(IPlayer var1);

    public int getIndex();

    public void setIndex(int var1);
}

