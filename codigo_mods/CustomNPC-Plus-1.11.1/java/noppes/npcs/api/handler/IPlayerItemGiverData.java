/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.jobs.IJobItemGiver;

public interface IPlayerItemGiverData {
    public long getTime(IJobItemGiver var1);

    public void setTime(IJobItemGiver var1, long var2);

    public boolean hasInteractedBefore(IJobItemGiver var1);
}

