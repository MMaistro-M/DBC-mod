/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package noppes.npcs.scripted.roles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ITransportLocation;
import noppes.npcs.api.roles.IRoleTransporter;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTransporter;
import noppes.npcs.scripted.roles.ScriptRoleInterface;

public class ScriptRoleTransporter
extends ScriptRoleInterface
implements IRoleTransporter {
    private final RoleTransporter role;

    public ScriptRoleTransporter(EntityNPCInterface npc) {
        super(npc);
        this.role = (RoleTransporter)npc.roleInterface;
    }

    @Override
    public String getName() {
        return this.role.name;
    }

    @Override
    public int getTransportId() {
        return this.role.transportId;
    }

    @Override
    public void unlock(IPlayer<EntityPlayerMP> player, ITransportLocation location) {
        this.role.unlock((EntityPlayer)player.getMCEntity(), location);
    }

    @Override
    public ITransportLocation getTransport() {
        return this.role.getLocation();
    }

    @Override
    public boolean hasTransport() {
        return this.role.hasTransport();
    }

    @Override
    public void setTransport(ITransportLocation location) {
        this.role.setTransport(location);
    }

    @Override
    public int getType() {
        return 4;
    }
}

