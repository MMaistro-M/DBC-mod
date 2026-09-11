/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.player;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilPlayer;

public class DialogSelectPacket
extends AbstractPacket {
    public static final String packetName = "Player|DialogSelect";
    private int dialogID;
    private int optionID;

    public DialogSelectPacket() {
    }

    public DialogSelectPacket(int dialogID, int optionID) {
        this.dialogID = dialogID;
        this.optionID = optionID;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.DialogSelect;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.dialogID);
        out.writeInt(this.optionID);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        NoppesUtilPlayer.dialogSelected(in.readInt(), in.readInt(), playerMP, this.npc);
    }
}

