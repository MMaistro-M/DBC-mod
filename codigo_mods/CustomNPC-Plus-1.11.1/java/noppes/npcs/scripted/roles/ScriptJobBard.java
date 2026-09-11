/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import noppes.npcs.api.jobs.IJobBard;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobBard;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobBard
extends ScriptJobInterface
implements IJobBard {
    private JobBard job;

    public ScriptJobBard(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobBard)npc.jobInterface;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String getSong() {
        return this.job.song;
    }

    @Override
    public void setSong(String song) {
        this.job.song = song;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public void setInstrument(int i) {
        this.job.setInstrument(i);
    }

    @Override
    public int getInstrumentId() {
        return this.job.getInstrument().ordinal();
    }

    @Override
    public void setMinRange(int range) {
        this.job.minRange = range;
    }

    @Override
    public int getMinRange() {
        return this.job.minRange;
    }

    @Override
    public void setMaxRange(int range) {
        this.job.maxRange = range;
    }

    @Override
    public int getMaxRange() {
        return this.job.maxRange;
    }

    @Override
    public void setStreaming(boolean streaming) {
        this.job.isStreamer = streaming;
    }

    @Override
    public boolean getStreaming() {
        return this.job.isStreamer;
    }

    @Override
    public void hasOffRange(boolean value) {
        this.job.hasOffRange = value;
    }

    @Override
    public boolean hasOffRange() {
        return this.job.hasOffRange;
    }
}

