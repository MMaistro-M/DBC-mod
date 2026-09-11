/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.MarkData;
import noppes.npcs.entity.EntityNPCInterface;

public final class MarkDataPacket
extends AbstractPacket {
    public static final String packetName = "Data|MarkData";
    private int entityID;
    private NBTTagCompound compound;

    public MarkDataPacket() {
    }

    public MarkDataPacket(int entityID, NBTTagCompound compound) {
        this.entityID = entityID;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.MARK_DATA;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.entityID);
        ByteBufUtils.writeNBT(out, this.compound);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        Entity entity = player.field_70170_p.func_73045_a(in.readInt());
        if (!(entity instanceof EntityNPCInterface)) {
            return;
        }
        EntityNPCInterface npc = (EntityNPCInterface)entity;
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        MarkData data = MarkData.get(npc);
        data.setNBT(nbt);
    }
}

