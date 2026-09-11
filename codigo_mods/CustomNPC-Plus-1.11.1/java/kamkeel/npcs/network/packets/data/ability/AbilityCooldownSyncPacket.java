/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.data.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public final class AbilityCooldownSyncPacket
extends AbstractPacket {
    public static final String packetName = "Data|AbilityCooldownSync";
    private long globalCooldownEndTime;
    private int globalCooldownDuration;
    private HashMap<String, Long> perAbilityCooldownEndTimes;
    private HashMap<String, Integer> perAbilityCooldownDurations;

    public AbilityCooldownSyncPacket() {
    }

    public AbilityCooldownSyncPacket(long globalEndTime, int globalDuration, HashMap<String, Long> perEndTimes, HashMap<String, Integer> perDurations) {
        this.globalCooldownEndTime = globalEndTime;
        this.globalCooldownDuration = globalDuration;
        this.perAbilityCooldownEndTimes = perEndTimes;
        this.perAbilityCooldownDurations = perDurations;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.ABILITY_COOLDOWN_SYNC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeLong(this.globalCooldownEndTime);
        out.writeInt(this.globalCooldownDuration);
        int count = this.perAbilityCooldownEndTimes != null ? this.perAbilityCooldownEndTimes.size() : 0;
        out.writeInt(count);
        if (count > 0) {
            for (Map.Entry<String, Long> entry : this.perAbilityCooldownEndTimes.entrySet()) {
                ByteBufUtils.writeUTF8String((ByteBuf)out, (String)entry.getKey());
                out.writeLong(entry.getValue().longValue());
                Integer dur = this.perAbilityCooldownDurations.get(entry.getKey());
                out.writeInt(dur != null ? dur : 0);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        long globalEndTime = in.readLong();
        int globalDuration = in.readInt();
        int count = in.readInt();
        HashMap<String, Long> perEndTimes = new HashMap<String, Long>();
        HashMap<String, Integer> perDurations = new HashMap<String, Integer>();
        for (int i = 0; i < count; ++i) {
            String key = ByteBufUtils.readUTF8String((ByteBuf)in);
            long endTime = in.readLong();
            int duration = in.readInt();
            perEndTimes.put(key, endTime);
            perDurations.put(key, duration);
        }
        PlayerData data = ClientCacheHandler.playerData;
        if (data != null && data.abilityData != null) {
            data.abilityData.applyCooldownSync(globalEndTime, globalDuration, perEndTimes, perDurations);
        }
    }

    public static void sendToPlayer(EntityPlayerMP player) {
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (data == null || data.abilityData == null) {
            return;
        }
        PacketHandler.Instance.sendToPlayer(new AbilityCooldownSyncPacket(data.abilityData.getCooldownEndTime(), data.abilityData.getGlobalCooldownDurationValue(), data.abilityData.getPerAbilityCooldownEndTimes(), data.abilityData.getPerAbilityCooldownDurations()), player);
    }
}

