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

public final class ProjectileReflectPacket
extends AbstractPacket {
    public static final String packetName = "Data|ProjectileReflect";
    private int entityId;
    private NBTTagCompound reflectData;

    public ProjectileReflectPacket() {
    }

    public ProjectileReflectPacket(int entityId, NBTTagCompound reflectData) {
        this.entityId = entityId;
        this.reflectData = reflectData;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.PROJECTILE_REFLECT;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.entityId);
        ByteBufUtils.writeNBT(out, this.reflectData);
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
            ((EntityEnergyProjectile)entity).applyReflectionData(nbt);
        }
    }

    public static void sendToTracking(EntityEnergyProjectile projectile, NBTTagCompound reflectData) {
        if (projectile == null || reflectData == null) {
            return;
        }
        PacketHandler.Instance.sendTracking(new ProjectileReflectPacket(projectile.func_145782_y(), reflectData), projectile);
    }
}

