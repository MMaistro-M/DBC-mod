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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.network.packets.data.energycharge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.lang.reflect.Constructor;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargePreviewManager;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class EnergyChargeSpawnPacket
extends AbstractPacket {
    public static final String packetName = "Data|EnergyChargeSpawn";
    private String instanceId;
    private String entityClassName;
    private NBTTagCompound spawnNbt;

    public EnergyChargeSpawnPacket() {
    }

    public EnergyChargeSpawnPacket(String instanceId, String entityClassName, NBTTagCompound spawnNbt) {
        this.instanceId = instanceId;
        this.entityClassName = entityClassName;
        this.spawnNbt = spawnNbt;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.ENERGY_CHARGE_SPAWN;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.instanceId);
        ByteBufUtils.writeString(out, this.entityClassName);
        ByteBufUtils.writeNBT(out, this.spawnNbt);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String id = ByteBufUtils.readString(in);
        String className = ByteBufUtils.readString(in);
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        if (id == null || className == null || nbt == null) {
            return;
        }
        if (EnergyChargePreviewManager.ClientInstance == null) {
            return;
        }
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (world == null) {
            return;
        }
        EntityEnergyProjectile preview = EnergyChargeSpawnPacket.createPreviewEntity(className, (World)world);
        if (preview == null) {
            return;
        }
        preview.setPreviewMode(true);
        preview.importSpawnNBT(nbt);
        Entity owner = preview.getOwnerEntity();
        if (owner instanceof EntityLivingBase) {
            preview.setPreviewOwner((EntityLivingBase)owner);
        }
        EnergyChargePreviewManager.ClientInstance.addPreview(id, preview);
    }

    @SideOnly(value=Side.CLIENT)
    private static EntityEnergyProjectile createPreviewEntity(String className, World world) {
        try {
            Class<?> clazz = Class.forName(className);
            if (!EntityEnergyProjectile.class.isAssignableFrom(clazz)) {
                return null;
            }
            Constructor<?> constructor = clazz.getConstructor(World.class);
            Object instance = constructor.newInstance(world);
            return (EntityEnergyProjectile)((Object)instance);
        }
        catch (Exception ignored) {
            return null;
        }
    }

    public static void sendToTracking(String instanceId, EntityEnergyProjectile previewEntity, Entity trackingEntity) {
        if (previewEntity == null || trackingEntity == null) {
            return;
        }
        String className = ((Object)((Object)previewEntity)).getClass().getName();
        NBTTagCompound nbt = previewEntity.exportSpawnNBT();
        PacketHandler.Instance.sendTracking(new EnergyChargeSpawnPacket(instanceId, className, nbt), trackingEntity);
    }
}

