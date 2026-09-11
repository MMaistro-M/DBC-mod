/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 */
package kamkeel.npcs.network.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import kamkeel.npcs.network.LargeAbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.blocks.tiles.TileBigSign;

public class SaveSignPacket
extends LargeAbstractPacket {
    public static final String packetName = "Player|SaveSign";
    private int x;
    private int y;
    private int z;
    private String text;

    public SaveSignPacket() {
    }

    public SaveSignPacket(int x, int y, int z, String text) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.text = text;
    }

    @Override
    protected byte[] getData() throws IOException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        ByteBufUtils.writeString(buffer, this.text);
        return buffer.array();
    }

    @Override
    protected void handleCompleteData(ByteBuf data, EntityPlayer player) throws IOException {
        int z;
        int y;
        int x = data.readInt();
        if (player.field_70170_p.func_72899_e(x, y = data.readInt(), z = data.readInt())) {
            TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
            if (!(tile instanceof TileBigSign)) {
                return;
            }
            TileBigSign sign = (TileBigSign)tile;
            if (sign.canEdit) {
                sign.setText(ByteBufUtils.readString(data));
                sign.canEdit = false;
                player.field_70170_p.func_147471_g(x, y, z);
            }
        }
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.SaveSign;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }
}

