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
package kamkeel.npcs.network.packets.player.profile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.controllers.data.profile.ProfileOperation;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetInfoPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigMain;

public final class ProfileCreatePacket
extends AbstractPacket {
    public static String packetName = "Request|ProfileCreate";

    @Override
    public Enum getType() {
        return EnumPlayerPacket.ProfileCreate;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.PROFILE_CREATE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!ConfigMain.ProfilesEnabled) {
            return;
        }
        Profile profile = ProfileController.Instance.getProfile(player);
        ProfileOperation operation = ProfileController.Instance.createSlotInternal(profile);
        ProfileGetPacket.sendProfileNBT(player);
        ProfileGetInfoPacket.sendProfileInfo(player);
        ChatAlertPacket.sendChatAlert((EntityPlayerMP)player, operation.getMessage());
    }
}

