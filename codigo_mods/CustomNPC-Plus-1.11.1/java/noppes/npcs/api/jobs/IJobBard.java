/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.jobs;

import noppes.npcs.api.jobs.IJob;

public interface IJobBard
extends IJob {
    public String getSong();

    public void setSong(String var1);

    public void setInstrument(int var1);

    public int getInstrumentId();

    public void setMinRange(int var1);

    public int getMinRange();

    public void setMaxRange(int var1);

    public int getMaxRange();

    public void setStreaming(boolean var1);

    public boolean getStreaming();

    public void hasOffRange(boolean var1);

    public boolean hasOffRange();
}

