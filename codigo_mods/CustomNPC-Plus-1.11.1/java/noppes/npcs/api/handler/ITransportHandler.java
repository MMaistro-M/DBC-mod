/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.ITransportCategory;

public interface ITransportHandler {
    public ITransportCategory[] categories();

    public void createCategory(String var1);

    public ITransportCategory getCategory(String var1);

    public void removeCategory(String var1);
}

