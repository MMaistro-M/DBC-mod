/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.common.DimensionManager
 */
package kamkeel.npcs.network.packets.request;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.HashMap;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.ScrollDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import noppes.npcs.constants.EnumScrollData;

public final class DimensionsGetPacket
extends AbstractPacket {
    public static final String packetName = "Request|DimensionsGet";

    @Override
    public Enum getType() {
        return EnumRequestPacket.DimensionsGet;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Integer[] integerArray = DimensionManager.getStaticDimensionIDs();
        int n = integerArray.length;
        for (int i = 0; i < n; ++i) {
            int id = integerArray[i];
            WorldProvider provider = DimensionManager.createProviderFor((int)id);
            map.put(provider.func_80007_l(), id);
        }
        ScrollDataPacket.sendScrollData((EntityPlayerMP)player, map, EnumScrollData.OPTIONAL);
    }
}

