/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import noppes.npcs.api.roles.IRoleMailman;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.roles.ScriptRoleInterface;

public class ScriptRoleCompanion
extends ScriptRoleInterface
implements IRoleMailman {
    public ScriptRoleCompanion(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public int getType() {
        return 6;
    }
}

