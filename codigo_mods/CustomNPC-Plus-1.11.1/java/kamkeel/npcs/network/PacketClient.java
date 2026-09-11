/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.network;

import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketHandler;

public class PacketClient
extends PacketHandler {
    public static void sendClient(AbstractPacket packet) {
        PacketHandler.Instance.sendToServer(packet);
    }
}

