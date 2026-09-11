/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.IPos;

public interface ITransportLocation {
    public int getId();

    public void setName(String var1);

    public String getName();

    public void setDimension(int var1);

    public int getDimension();

    public void setType(int var1);

    public int getType();

    public void setPosition(int var1, int var2, int var3);

    public void setPosition(IPos var1);

    public double getX();

    public double getY();

    public double getZ();

    public void save();
}

