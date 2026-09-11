/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.scripted.roles;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.jobs.IJob;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;
import noppes.npcs.scripted.NpcAPI;

public class ScriptJobInterface
implements IJob {
    public final EntityNPCInterface npc;
    public final JobInterface jobInterface;

    public ScriptJobInterface(JobInterface jobInterface) {
        this.npc = null;
        this.jobInterface = jobInterface;
    }

    public ScriptJobInterface(EntityNPCInterface npc) {
        this.npc = npc;
        this.jobInterface = npc.jobInterface;
    }

    @Override
    public int getType() {
        return 0;
    }

    public JobInterface getJobInterface() {
        return this.jobInterface;
    }

    @Override
    public ICustomNpc getNpc() {
        return (ICustomNpc)NpcAPI.Instance().getIEntity((Entity)this.npc);
    }
}

