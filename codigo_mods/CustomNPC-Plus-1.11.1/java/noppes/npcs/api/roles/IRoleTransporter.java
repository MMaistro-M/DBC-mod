/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package noppes.npcs.api.roles;

import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ITransportLocation;
import noppes.npcs.api.roles.IRole;

public interface IRoleTransporter
extends IRole {
    public String getName();

    public int getTransportId();

    public void unlock(IPlayer<EntityPlayerMP> var1, ITransportLocation var2);

    public ITransportLocation getTransport();

    public boolean hasTransport();

    public void setTransport(ITransportLocation var1);
}

