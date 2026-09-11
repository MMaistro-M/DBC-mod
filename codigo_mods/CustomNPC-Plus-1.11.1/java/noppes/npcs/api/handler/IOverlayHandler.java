/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.ISkinOverlay;

public interface IOverlayHandler {
    public void add(int var1, ISkinOverlay var2);

    public ISkinOverlay get(int var1);

    public boolean has(int var1);

    public boolean remove(int var1);

    public int size();

    public void clear();
}

