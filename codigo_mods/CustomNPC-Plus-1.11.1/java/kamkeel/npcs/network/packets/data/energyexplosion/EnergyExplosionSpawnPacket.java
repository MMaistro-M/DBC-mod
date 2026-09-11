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
 *  net.minecraft.world.World
 */
package kamkeel.npcs.network.packets.data.energyexplosion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.entity.EntityEnergyExplosion;
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
import net.minecraft.world.World;

public final class EnergyExplosionSpawnPacket
extends AbstractPacket {
    public static final String packetName = "Data|EnergyExplosionSpawn";
    private String instanceId;
    private NBTTagCompound spawnNbt;

    public EnergyExplosionSpawnPacket() {
    }

    public EnergyExplosionSpawnPacket(String instanceId, NBTTagCompound spawnNbt) {
        this.instanceId = instanceId;
        this.spawnNbt = spawnNbt;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.ENERGY_EXPLOSION_SPAWN;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.instanceId);
        ByteBufUtils.writeNBT(out, this.spawnNbt);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        ByteBufUtils.readString(in);
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        if (nbt == null) {
            return;
        }
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (world == null) {
            return;
        }
        EntityEnergyExplosion preview = new EntityEnergyExplosion((World)world);
        preview.importSpawnNBT(nbt);
        world.func_72838_d((Entity)preview);
    }

    public static void sendToTracking(String instanceId, EntityEnergyExplosion previewEntity, Entity trackingEntity) {
        if (instanceId == null || previewEntity == null || trackingEntity == null) {
            return;
        }
        NBTTagCompound nbt = previewEntity.exportSpawnNBT();
        PacketHandler.Instance.sendTracking(new EnergyExplosionSpawnPacket(instanceId, nbt), trackingEntity);
    }
}

