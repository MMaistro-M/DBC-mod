/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.network.packets.player.ability;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import noppes.npcs.controllers.data.PlayerData;

public final class AbilityTogglePacket
extends AbstractPacket {
    public static final String packetName = "Player|AbilityToggle";
    private String abilityKey;

    public AbilityTogglePacket() {
    }

    public AbilityTogglePacket(String abilityKey) {
        this.abilityKey = abilityKey;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.AbilityToggle;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.abilityKey != null ? this.abilityKey : "");
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String key = ByteBufUtils.readString(in);
        if (key == null || key.isEmpty()) {
            return;
        }
        if (AbilityController.Instance == null) {
            return;
        }
        Ability ability = AbilityController.Instance.resolveAbility(key);
        if (ability == null || !ability.isToggleable() || !ability.getAllowedBy().allowsPlayer()) {
            return;
        }
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null || playerData.abilityData == null) {
            return;
        }
        if (!playerData.abilityData.hasUnlockedAbility(key)) {
            return;
        }
        int newState = playerData.abilityData.toggleAbility(key);
        playerData.abilityData.syncToClient();
        String displayName = ability.getDisplayName();
        if (newState > 0) {
            String stateLabel = ability.getToggleStateLabel(newState);
            if (stateLabel != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a7a" + displayName + " " + stateLabel));
            } else {
                String enabled = StatCollector.func_74838_a((String)"gui.enabled");
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a7a" + displayName + " " + enabled));
            }
        } else {
            String disabled = StatCollector.func_74838_a((String)"gui.disabled");
            player.func_145747_a((IChatComponent)new ChatComponentText("\u00a7c" + displayName + " " + disabled));
        }
    }
}

