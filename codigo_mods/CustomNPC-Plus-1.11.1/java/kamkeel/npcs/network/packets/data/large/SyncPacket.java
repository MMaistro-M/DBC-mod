/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data.large;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.network.LargeAbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.network.enums.EnumSyncAction;
import kamkeel.npcs.network.enums.EnumSyncType;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;

public final class SyncPacket
extends LargeAbstractPacket {
    private EnumSyncType enumSyncType;
    private EnumSyncAction enumSyncAction;
    private NBTTagCompound syncData;
    private int operationID;
    private int revision = -1;
    private byte[] cachedPayload;
    private byte[][] cachedChunks;

    public SyncPacket() {
    }

    public SyncPacket(EnumSyncType enumSyncType, EnumSyncAction enumSyncAction, int catId, NBTTagCompound syncData) {
        this(enumSyncType, enumSyncAction, catId, -1, syncData);
    }

    public SyncPacket(EnumSyncType enumSyncType, EnumSyncAction enumSyncAction, int catId, int revision, NBTTagCompound syncData) {
        this.enumSyncType = enumSyncType;
        this.enumSyncAction = enumSyncAction;
        this.syncData = syncData;
        this.operationID = catId;
        this.revision = revision;
    }

    public SyncPacket(EnumSyncType enumSyncType, SyncController.CachedSyncPayload payload) {
        this.enumSyncType = enumSyncType;
        this.enumSyncAction = EnumSyncAction.RELOAD;
        this.operationID = -1;
        this.revision = payload.getRevision();
        this.cachedPayload = payload.getPayload();
        this.cachedChunks = payload.getChunks();
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.SYNC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected byte[] getData() throws IOException {
        if (this.cachedPayload != null) {
            return this.cachedPayload;
        }
        ByteBuf buffer = Unpooled.buffer();
        try {
            buffer.writeInt(this.enumSyncType.ordinal());
            buffer.writeInt(this.enumSyncAction.ordinal());
            buffer.writeInt(this.operationID);
            buffer.writeInt(this.revision);
            ByteBufUtils.writeBigNBT(buffer, this.syncData);
            byte[] bytes = new byte[buffer.readableBytes()];
            buffer.readBytes(bytes);
            byte[] byArray = bytes;
            return byArray;
        }
        finally {
            buffer.release();
        }
    }

    @Override
    public List<FMLProxyPacket> generatePackets() {
        if (this.cachedChunks == null) {
            return super.generatePackets();
        }
        ArrayList<FMLProxyPacket> packets = new ArrayList<FMLProxyPacket>(this.cachedChunks.length);
        PacketChannel packetChannel = this.getChannel();
        EnumDataPacket dataPacket = (EnumDataPacket)this.getType();
        UUID packetId = UUID.randomUUID();
        int totalSize = this.cachedPayload.length;
        int offset = 0;
        for (byte[] chunk : this.cachedChunks) {
            ByteBuf chunkBuf = Unpooled.buffer();
            chunkBuf.writeInt(packetChannel.getChannelType().ordinal());
            chunkBuf.writeInt(dataPacket.ordinal());
            chunkBuf.writeLong(packetId.getMostSignificantBits());
            chunkBuf.writeLong(packetId.getLeastSignificantBits());
            chunkBuf.writeInt(totalSize);
            chunkBuf.writeInt(offset);
            chunkBuf.writeInt(this.cachedChunks.length);
            chunkBuf.writeBytes(chunk);
            packets.add(new FMLProxyPacket(chunkBuf, packetChannel.getChannelName()));
            offset += chunk.length;
        }
        return packets;
    }

    @Override
    protected void handleCompleteData(ByteBuf data, EntityPlayer player) throws IOException {
        if (CustomNpcs.side() != Side.CLIENT) {
            return;
        }
        int syncTypeOrdinal = data.readInt();
        int syncActionOrdinal = data.readInt();
        int categoryID = data.readInt();
        int incomingRevision = data.readInt();
        EnumSyncType type = EnumSyncType.values()[syncTypeOrdinal];
        EnumSyncAction action = EnumSyncAction.values()[syncActionOrdinal];
        try {
            NBTTagCompound tag = ByteBufUtils.readBigNBT(data);
            this.handleSyncPacketClient(type, action, categoryID, incomingRevision, tag);
        }
        catch (RuntimeException e) {
            LogWriter.error(String.format("Attempted to Sync %s but it was too big", type.toString()));
        }
    }

    private void handleSyncPacketClient(EnumSyncType enumSyncType, EnumSyncAction enumSyncAction, int id, int incomingRevision, NBTTagCompound data) {
        switch (enumSyncAction) {
            case RELOAD: {
                SyncController.clientSync(enumSyncType, incomingRevision, data);
                break;
            }
            case UPDATE: {
                SyncController.clientUpdate(enumSyncType, id, incomingRevision, data);
                break;
            }
            case REMOVE: {
                SyncController.clientSyncRemove(enumSyncType, id, incomingRevision);
            }
        }
    }
}

