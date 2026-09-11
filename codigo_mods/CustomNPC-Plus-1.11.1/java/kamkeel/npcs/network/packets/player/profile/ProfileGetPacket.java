/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
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
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.config.ConfigMain;

public final class ProfileGetPacket
extends AbstractPacket {
    public static String packetName = "Request|ProfileGet";

    @Override
    public Enum getType() {
        return EnumPlayerPacket.ProfileGet;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
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
        ProfileGetPacket.sendProfileNBT(player);
    }

    public static void sendProfileNBT(EntityPlayer player) {
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, ProfileGetPacket.profileNBTPayload(player));
    }

    public static NBTTagCompound profileNBTPayload(EntityPlayer player) {
        Profile profile = ProfileController.Instance.getProfile(player);
        NBTTagCompound compound = profile.writeToNBT();
        compound.func_74757_a("PROFILE", true);
        return compound;
    }
}

