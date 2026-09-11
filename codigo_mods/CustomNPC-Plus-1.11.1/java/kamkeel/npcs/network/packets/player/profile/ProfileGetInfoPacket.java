/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package kamkeel.npcs.network.packets.player.profile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.controllers.data.profile.ProfileInfoEntry;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.ISlot;
import noppes.npcs.config.ConfigMain;

public final class ProfileGetInfoPacket
extends AbstractPacket {
    public static String packetName = "Request|ProfileGetInfo";

    @Override
    public Enum getType() {
        return EnumPlayerPacket.ProfileGetInfo;
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
        ProfileGetInfoPacket.sendProfileInfo(player);
    }

    public static void sendProfileInfo(EntityPlayer player) {
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, ProfileGetInfoPacket.profileInfoPayload(player));
    }

    public static NBTTagCompound profileInfoPayload(EntityPlayer player) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("PROFILE_INFO", true);
        NBTTagList slotList = new NBTTagList();
        Profile profile = ProfileController.Instance.getProfile(player);
        if (profile != null) {
            for (ISlot slot : profile.getSlots().values()) {
                NBTTagCompound slotCompound = new NBTTagCompound();
                NBTTagList infoList = new NBTTagList();
                slotCompound.func_74768_a("ID", slot.getId());
                List<ProfileInfoEntry> profileInfo = ProfileController.Instance.getProfileInfo(player, slot.getId());
                for (ProfileInfoEntry profileInfoEntry : profileInfo) {
                    infoList.func_74742_a((NBTBase)profileInfoEntry.writeToNBT());
                }
                slotCompound.func_74782_a("INFO", (NBTBase)infoList);
                slotList.func_74742_a((NBTBase)slotCompound);
            }
            compound.func_74782_a("SLOTS", (NBTBase)slotList);
        }
        return compound;
    }

    public static HashMap<Integer, List<ProfileInfoEntry>> readProfileInfo(NBTTagCompound compound) {
        HashMap<Integer, List<ProfileInfoEntry>> slotInfoMap = new HashMap<Integer, List<ProfileInfoEntry>>();
        if (!compound.func_74767_n("PROFILE_INFO")) {
            return slotInfoMap;
        }
        NBTTagList slotList = compound.func_150295_c("SLOTS", 10);
        for (int i = 0; i < slotList.func_74745_c(); ++i) {
            NBTTagCompound slotCompound = slotList.func_150305_b(i);
            int slotId = slotCompound.func_74762_e("ID");
            NBTTagList infoList = slotCompound.func_150295_c("INFO", 10);
            ArrayList<ProfileInfoEntry> infoEntries = new ArrayList<ProfileInfoEntry>();
            for (int j = 0; j < infoList.func_74745_c(); ++j) {
                NBTTagCompound infoTag = infoList.func_150305_b(j);
                infoEntries.add(ProfileInfoEntry.readFromNBT(infoTag));
            }
            slotInfoMap.put(slotId, infoEntries);
        }
        return slotInfoMap;
    }
}

