/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.jobs;

import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.jobs.IJob;

public interface IJobFollower
extends IJob {
    public String getFollowingName();

    public void setFollowingName(String var1);

    public ICustomNpc getFollowingNpc();

    public boolean isFollowing();
}

