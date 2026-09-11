/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.WorldServer
 */
package kamkeel.npcs.network.packets.request.feather;

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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;
import noppes.npcs.NoppesUtilPlayer;

public final class DimensionTeleportPacket
extends AbstractPacket {
    public static String packetName = "Request|DimensionTeleport";
    private int dimensionID;

    public DimensionTeleportPacket() {
    }

    public DimensionTeleportPacket(int dimensionID) {
        this.dimensionID = dimensionID;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.DimensionTeleport;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.dimensionID);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.TELEPORTER)) {
            return;
        }
        int dimension = in.readInt();
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(dimension);
        ChunkCoordinates coords = world.func_73054_j();
        if (coords == null) {
            coords = world.func_72861_E();
            if (!world.func_147437_c(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) {
                coords.field_71572_b = world.func_72825_h(coords.field_71574_a, coords.field_71573_c);
            } else {
                while (world.func_147437_c(coords.field_71574_a, coords.field_71572_b - 1, coords.field_71573_c) && coords.field_71572_b > 0) {
                    --coords.field_71572_b;
                }
                if (coords.field_71572_b == 0) {
                    coords.field_71572_b = world.func_72825_h(coords.field_71574_a, coords.field_71573_c);
                }
            }
        }
        NoppesUtilPlayer.teleportPlayer((EntityPlayerMP)player, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, dimension);
    }
}

