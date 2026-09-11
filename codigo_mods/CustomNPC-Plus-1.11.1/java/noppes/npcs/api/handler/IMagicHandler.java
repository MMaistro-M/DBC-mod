/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.IMagic;
import noppes.npcs.api.handler.data.IMagicCycle;

public interface IMagicHandler {
    public IMagic getMagic(int var1);

    public IMagicCycle getCycle(int var1);

    public void addMagicToCycle(int var1, int var2, int var3, int var4);

    public void removeMagicFromCycle(int var1, int var2);
}

