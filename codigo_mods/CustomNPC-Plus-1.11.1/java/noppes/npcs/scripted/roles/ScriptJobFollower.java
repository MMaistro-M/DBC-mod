/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.jobs.IJobFollower;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobFollower;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobFollower
extends ScriptJobInterface
implements IJobFollower {
    private JobFollower job;

    public ScriptJobFollower(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobFollower)npc.jobInterface;
    }

    @Override
    public String getFollowingName() {
        return this.job.name;
    }

    @Override
    public void setFollowingName(String name) {
        this.job.name = name;
    }

    @Override
    public ICustomNpc getFollowingNpc() {
        if (!this.isFollowing()) {
            return null;
        }
        return this.job.following.script.dummyNpc;
    }

    @Override
    public boolean isFollowing() {
        return this.job.isFollowing();
    }

    @Override
    public int getType() {
        return 4;
    }
}

