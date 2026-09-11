/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.handler.data.ITransportLocation;

public interface ITransportCategory {
    public int getId();

    public void setTitle(String var1);

    public String getTitle();

    public void addLocation(String var1);

    public ITransportLocation getLocation(String var1);

    public void removeLocation(String var1);
}

