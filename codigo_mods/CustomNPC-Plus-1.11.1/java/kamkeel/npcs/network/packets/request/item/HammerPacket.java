/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 */
package kamkeel.npcs.network.packets.request.item;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.tiles.TileBanner;
import noppes.npcs.blocks.tiles.TileChair;

public final class HammerPacket
extends AbstractPacket {
    public static String packetName = "Request|Hammer";
    private int x;
    private int y;
    private int z;

    public HammerPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public HammerPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.Hammer;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_BUILD;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeInt(this.z);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int z;
        int y;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.HAMMER, player)) {
            return;
        }
        int x = in.readInt();
        TileEntity tile = player.field_70170_p.func_147438_o(x, y = in.readInt(), z = in.readInt());
        if (tile instanceof TileChair) {
            ((TileChair)tile).push();
        } else if (tile instanceof TileBanner) {
            ((TileBanner)tile).changeVariant();
        }
    }
}

