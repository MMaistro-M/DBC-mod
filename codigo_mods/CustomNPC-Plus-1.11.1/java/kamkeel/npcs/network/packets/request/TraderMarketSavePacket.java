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
package kamkeel.npcs.network.packets.request;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxz.utils.Market;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.roles.RoleTrader;

public final class TraderMarketSavePacket
extends AbstractPacket {
    public static String packetName = "Request|TraderMarketSave";
    private String marketName;
    private boolean setMarket;

    public TraderMarketSavePacket() {
    }

    public TraderMarketSavePacket(String marketName, boolean setMarket) {
        this.marketName = marketName;
        this.setMarket = setMarket;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.TraderMarketSave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_ADVANCED_TRADER;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.marketName);
        out.writeBoolean(this.setMarket);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        String market = ByteBufUtils.readString(in);
        if (market == null) {
            return;
        }
        boolean bo = in.readBoolean();
        if (this.npc.roleInterface instanceof RoleTrader) {
            if (bo) {
                Market.setMarket(this.npc, market);
            } else {
                Market.save((RoleTrader)this.npc.roleInterface, market);
            }
        }
    }
}

