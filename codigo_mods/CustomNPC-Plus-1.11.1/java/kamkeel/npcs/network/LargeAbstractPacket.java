/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.network;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.LogWriter;

public abstract class LargeAbstractPacket
extends AbstractPacket {
    private static final Map<UUID, PacketStorage> packetChunks = new ConcurrentHashMap<UUID, PacketStorage>();
    public static final int CHUNK_SIZE = 10000;

    @Override
    public List<FMLProxyPacket> generatePackets() {
        byte[] fullData;
        ArrayList<FMLProxyPacket> packets = new ArrayList<FMLProxyPacket>();
        PacketChannel packetChannel = this.getChannel();
        try {
            fullData = this.getData();
        }
        catch (IOException e) {
            e.printStackTrace();
            return packets;
        }
        int totalSize = fullData.length;
        UUID packetId = UUID.randomUUID();
        for (int offset = 0; offset < totalSize; offset += 10000) {
            int chunkSize = Math.min(10000, totalSize - offset);
            ByteBuf chunkBuf = Unpooled.buffer();
            chunkBuf.writeInt(packetChannel.getChannelType().ordinal());
            chunkBuf.writeInt(this.getType().ordinal());
            chunkBuf.writeLong(packetId.getMostSignificantBits());
            chunkBuf.writeLong(packetId.getLeastSignificantBits());
            chunkBuf.writeInt(totalSize);
            chunkBuf.writeInt(offset);
            chunkBuf.writeInt((totalSize + 10000 - 1) / 10000);
            chunkBuf.writeBytes(fullData, offset, chunkSize);
            FMLProxyPacket proxyPacket = new FMLProxyPacket(chunkBuf, packetChannel.getChannelName());
            packets.add(proxyPacket);
        }
        return packets;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        block13: {
            UUID packetId = null;
            PacketStorage storage = null;
            ByteBuf chunk = null;
            ByteBuf completeData = null;
            try {
                long mostSigBits = in.readLong();
                long leastSigBits = in.readLong();
                packetId = new UUID(mostSigBits, leastSigBits);
                int totalSize = in.readInt();
                int offset = in.readInt();
                int totalChunks = in.readInt();
                int chunkSize = Math.min(10000, totalSize - offset);
                if (chunkSize <= 0 || offset < 0 || offset + chunkSize > totalSize) {
                    throw new IndexOutOfBoundsException("Invalid chunk size/offset: chunkSize=" + chunkSize + ", offset=" + offset + ", totalSize=" + totalSize);
                }
                chunk = in.readBytes(chunkSize);
                storage = packetChunks.computeIfAbsent(packetId, k -> new PacketStorage(Unpooled.buffer((int)totalSize), 0, totalSize));
                storage.data.setBytes(offset, chunk);
                storage.receivedSoFar += chunkSize;
                if (storage.receivedSoFar < totalSize) break block13;
                packetChunks.remove(packetId);
                completeData = storage.data.copy(0, totalSize);
                try {
                    this.handleCompleteData(completeData, player);
                }
                finally {
                    completeData.release();
                }
                storage.data.release();
            }
            catch (Exception e) {
                LogWriter.error("Error in receiveData for packetId " + (packetId != null ? packetId.toString() : "null") + ": " + e.getMessage());
                e.printStackTrace();
                if (storage != null && storage.receivedSoFar < storage.totalSize) {
                    storage.data.release();
                    if (packetId != null) {
                        packetChunks.remove(packetId);
                    }
                }
                throw e;
            }
            finally {
                if (chunk != null && chunk.refCnt() > 0) {
                    chunk.release();
                }
                if (completeData != null && completeData.refCnt() > 0) {
                    completeData.release();
                }
            }
        }
    }

    protected abstract byte[] getData() throws IOException;

    protected abstract void handleCompleteData(ByteBuf var1, EntityPlayer var2) throws IOException;

    @Override
    public final FMLProxyPacket generatePacket() {
        return null;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
    }

    private static class PacketStorage {
        final ByteBuf data;
        int receivedSoFar;
        final int totalSize;

        PacketStorage(ByteBuf data, int receivedSoFar, int totalSize) {
            this.data = data;
            this.receivedSoFar = receivedSoFar;
            this.totalSize = totalSize;
        }
    }
}

