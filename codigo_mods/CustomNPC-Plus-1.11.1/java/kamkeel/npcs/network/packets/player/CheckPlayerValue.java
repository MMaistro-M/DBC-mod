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
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerFactionData;
import noppes.npcs.controllers.data.PlayerQuestData;

public class CheckPlayerValue
extends AbstractPacket {
    public static final String packetName = "Player|CheckPlayerVal";
    private Type type;

    public CheckPlayerValue() {
    }

    public CheckPlayerValue(Type type) {
        this.type = type;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.GetPlayerValue;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        Type type = Type.values()[in.readInt()];
        switch (type) {
            case Faction: {
                PlayerFactionData data = PlayerData.get((EntityPlayer)playerMP).factionData;
                GuiDataPacket.sendGuiData(playerMP, data.getPlayerGuiData());
                break;
            }
            case QuestLog: {
                NoppesUtilPlayer.sendQuestLogData(playerMP);
                break;
            }
            case TrackedQuest: {
                NoppesUtilPlayer.sendTrackedQuest(playerMP);
                break;
            }
            case CheckQuestCompletion: {
                PlayerData playerData = PlayerData.get((EntityPlayer)playerMP);
                PlayerQuestData questData = PlayerData.get((EntityPlayer)playerMP).questData;
                Party playerParty = playerData.getPlayerParty();
                if (playerParty != null) {
                    PartyController.Instance().checkQuestCompletion(playerParty, null);
                }
                questData.checkQuestCompletion(playerData, null);
            }
        }
    }

    public static enum Type {
        Faction,
        QuestLog,
        TrackedQuest,
        CheckQuestCompletion;

    }
}

