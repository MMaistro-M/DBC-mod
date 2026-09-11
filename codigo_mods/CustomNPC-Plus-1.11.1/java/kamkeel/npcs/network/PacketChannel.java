/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.network;

import java.util.Hashtable;
import java.util.Map;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.enums.EnumChannelType;

public class PacketChannel {
    private String channelName;
    private EnumChannelType channelType;
    public Map<Integer, AbstractPacket> packets = new Hashtable<Integer, AbstractPacket>();

    public PacketChannel(String channelName, EnumChannelType channelType) {
        this.channelName = channelName;
        this.channelType = channelType;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public EnumChannelType getChannelType() {
        return this.channelType;
    }

    public void setChannelType(EnumChannelType channelType) {
        this.channelType = channelType;
    }

    public void registerPacket(AbstractPacket packet) {
        if (this.packets.containsKey(packet.getType().ordinal())) {
            System.out.println("Packet ID: " + packet.getType().ordinal() + " is already registered in " + this.channelName);
            return;
        }
        this.packets.put(packet.getType().ordinal(), packet);
    }
}

