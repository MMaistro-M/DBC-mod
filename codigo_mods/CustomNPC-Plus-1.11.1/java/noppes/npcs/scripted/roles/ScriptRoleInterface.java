/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.INpc
 */
package noppes.npcs.scripted.roles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.INpc;
import noppes.npcs.api.roles.IRole;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;
import noppes.npcs.scripted.NpcAPI;

public class ScriptRoleInterface
implements IRole {
    public final EntityNPCInterface npc;
    public final RoleInterface role;

    public ScriptRoleInterface(EntityNPCInterface npc) {
        this.npc = npc;
        this.role = npc.roleInterface;
    }

    @Override
    public INpc getNpc() {
        return (INpc)NpcAPI.Instance().getIEntity((Entity)this.npc);
    }

    @Override
    public int getType() {
        return 0;
    }
}

