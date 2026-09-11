/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.ITransportLocation;

public interface IPlayerTransportData {
    public boolean hasTransport(int var1);

    public void addTransport(int var1);

    public void addTransport(ITransportLocation var1);

    public ITransportLocation getTransport(int var1);

    public ITransportLocation[] getTransports();

    public void removeTransport(int var1);
}

