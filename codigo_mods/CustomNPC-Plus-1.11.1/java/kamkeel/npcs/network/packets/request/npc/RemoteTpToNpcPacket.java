/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.request.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.entity.EntityNPCInterface;

public final class RemoteTpToNpcPacket
extends AbstractPacket {
    public static String packetName = "Request|RemoteTpToNpc";
    private int entityID;

    public RemoteTpToNpcPacket() {
    }

    public RemoteTpToNpcPacket(int entityID) {
        this.entityID = entityID;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.RemoteTpToNpc;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_TELEPORT;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.entityID);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        Entity entity = player.field_70170_p.func_73045_a(in.readInt());
        if (!(entity instanceof EntityNPCInterface)) {
            return;
        }
        this.npc = (EntityNPCInterface)entity;
        ((EntityPlayerMP)player).field_71135_a.func_147364_a(this.npc.field_70165_t, this.npc.field_70163_u, this.npc.field_70161_v, 0.0f, 0.0f);
    }
}

