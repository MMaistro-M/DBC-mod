/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public final class ProjectileClientSyncPacket
extends AbstractPacket {
    private int entityId;
    private NBTTagCompound syncData;

    public ProjectileClientSyncPacket() {
    }

    public ProjectileClientSyncPacket(int entityId, NBTTagCompound syncData) {
        this.entityId = entityId;
        this.syncData = syncData;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.PROJECTILE_CLIENT_SYNC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.entityId);
        ByteBufUtils.writeNBT(out, this.syncData);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int id = in.readInt();
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        if (nbt == null) {
            return;
        }
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (world == null) {
            return;
        }
        Entity entity = world.func_73045_a(id);
        if (entity instanceof EntityEnergyProjectile) {
            ((EntityEnergyProjectile)entity).applyClientSyncData(nbt);
        }
    }

    public static void sendToTracking(EntityEnergyProjectile projectile, NBTTagCompound syncData) {
        if (projectile == null || syncData == null) {
            return;
        }
        PacketHandler.Instance.sendTracking(new ProjectileClientSyncPacket(projectile.func_145782_y(), syncData), projectile);
    }
}

