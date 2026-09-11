/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.roles;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.ITransportLocation;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerTransportData;
import noppes.npcs.controllers.data.TransportLocation;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RoleTransporter
extends RoleInterface {
    public int transportId = -1;
    public String name;
    private int ticks = 10;

    public RoleTransporter(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("TransporterId", this.transportId);
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.transportId = nbttagcompound.func_74762_e("TransporterId");
        TransportLocation loc = this.getLocation();
        if (loc != null) {
            this.name = loc.name;
        }
    }

    @Override
    public boolean aiShouldExecute() {
        --this.ticks;
        if (this.ticks > 0) {
            return false;
        }
        this.ticks = 10;
        if (!this.hasTransport()) {
            return false;
        }
        TransportLocation loc = this.getLocation();
        if (loc.type != 0) {
            return false;
        }
        List inRange = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b(6.0, 6.0, 6.0));
        for (EntityPlayer player : inRange) {
            if (!this.npc.canSee((Entity)player)) continue;
            this.unlock(player, loc);
        }
        return false;
    }

    @Override
    public void aiStartExecuting() {
    }

    @Override
    public void interact(EntityPlayer player) {
        if (this.hasTransport()) {
            TransportLocation loc = this.getLocation();
            if (loc.type == 2) {
                this.unlock(player, loc);
            }
            NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerTransporter, this.npc);
        }
    }

    public void unlock(EntityPlayer player, ITransportLocation loc) {
        PlayerTransportData data = PlayerData.get((EntityPlayer)player).transportData;
        if (data.transports.contains(this.transportId)) {
            return;
        }
        data.transports.add(this.transportId);
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("transporter.unlock", new Object[]{loc.getName()}));
    }

    public TransportLocation getLocation() {
        if (this.npc.isRemote()) {
            return null;
        }
        return TransportController.getInstance().getTransport(this.transportId);
    }

    public boolean hasTransport() {
        TransportLocation loc = this.getLocation();
        return loc != null && loc.id == this.transportId;
    }

    public void setTransport(ITransportLocation location) {
        this.transportId = location.getId();
        this.name = location.getName();
    }
}

