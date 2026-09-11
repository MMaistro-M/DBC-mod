/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.entity.EntityNPCInterface;

public final class UpdateNpcPacket
extends AbstractPacket {
    public static final String packetName = "Data|UpdateNpc";
    private NBTTagCompound npcCompound;

    public UpdateNpcPacket() {
    }

    public UpdateNpcPacket(NBTTagCompound npcCompound) {
        this.npcCompound = npcCompound;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.UPDATE_NPC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.npcCompound);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        Entity entity = Minecraft.func_71410_x().field_71441_e.func_73045_a(compound.func_74762_e("EntityId"));
        if (entity instanceof EntityNPCInterface) {
            ((EntityNPCInterface)entity).readSpawnData(compound);
        }
    }
}

