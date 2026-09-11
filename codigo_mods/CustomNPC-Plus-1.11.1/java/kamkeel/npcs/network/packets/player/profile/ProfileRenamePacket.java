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
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetInfoPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigMain;

public final class ProfileRenamePacket
extends AbstractPacket {
    public static String packetName = "Request|ProfileRename";
    private int slotID;
    private String name;

    public ProfileRenamePacket() {
    }

    public ProfileRenamePacket(int slotID, String name) {
        this.slotID = slotID;
        this.name = name;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.ProfileRename;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.PROFILE_RENAME;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.slotID);
        ByteBufUtils.writeString(out, this.name);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!ConfigMain.ProfilesEnabled) {
            return;
        }
        int slot = in.readInt();
        String newName = ByteBufUtils.readString(in);
        Profile profile = ProfileController.Instance.getProfile(player);
        if (!profile.getSlots().containsKey(slot)) {
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)player, "No slot found");
            return;
        }
        ProfileController.Instance.getProfile(player).getSlots().get(slot).setName(newName);
        ProfileController.Instance.save(player, ProfileController.Instance.getProfile(player));
        ProfileGetPacket.sendProfileNBT(player);
        ProfileGetInfoPacket.sendProfileInfo(player);
    }
}

