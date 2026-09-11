/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import noppes.npcs.api.jobs.IJobConversation;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobConversation;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobConversation
extends ScriptJobInterface
implements IJobConversation {
    private JobConversation job;

    public ScriptJobConversation(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobConversation)npc.jobInterface;
    }

    @Override
    public int getType() {
        return 6;
    }
}

