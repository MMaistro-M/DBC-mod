/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.profile;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.profile.IProfileData;
import kamkeel.npcs.controllers.data.profile.ProfileInfoEntry;
import kamkeel.npcs.controllers.data.profile.ProfileOperation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.PlayerData;

public class CNPCData
implements IProfileData {
    @Override
    public String getTagName() {
        return "CNPC+";
    }

    @Override
    public NBTTagCompound getCurrentNBT(EntityPlayer player) {
        PlayerData customNPCData = PlayerData.get(player);
        NBTTagCompound compound = customNPCData.getNBT();
        compound.func_82580_o("TradeData");
        return compound;
    }

    @Override
    public void save(EntityPlayer player) {
        PlayerData customNPCData = PlayerData.get(player);
        customNPCData.save();
        SyncController.syncPlayerData((EntityPlayerMP)player, false);
    }

    @Override
    public void setNBT(EntityPlayer player, NBTTagCompound replace) {
        PlayerData customNPCData = PlayerData.get(player);
        NBTTagCompound sharedData = new NBTTagCompound();
        customNPCData.tradeData.writeToNBT(sharedData);
        if (replace.func_82582_d()) {
            PlayerData newData = new PlayerData();
            newData.player = player;
            customNPCData.setNBT((NBTTagCompound)newData.getNBT().func_74737_b());
        } else {
            customNPCData.setNBT(replace);
        }
        customNPCData.tradeData.readFromNBT(sharedData);
        customNPCData.updateClient = true;
    }

    @Override
    public int getSwitchPriority() {
        return 0;
    }

    @Override
    public ProfileOperation verifySwitch(EntityPlayer player) {
        PlayerData playerData = PlayerData.get(player);
        if (playerData.partyUUID != null) {
            return ProfileOperation.error("Cannot switch while in Party");
        }
        if (playerData.abilityData.isExecutingAbility()) {
            return ProfileOperation.error("Cannot switch while performing an Ability");
        }
        return ProfileOperation.success("");
    }

    @Override
    public List<ProfileInfoEntry> getInfo(EntityPlayer player, NBTTagCompound compound) {
        PlayerData playerData = new PlayerData();
        playerData.player = player;
        playerData.setNBT(compound);
        ArrayList<ProfileInfoEntry> info = new ArrayList<ProfileInfoEntry>();
        info.add(new ProfileInfoEntry("profile.info.quest.finished", 6355543, playerData.questData.finishedQuests.size(), 0xFFFFFF));
        info.add(new ProfileInfoEntry("profile.info.quest.active", 16208694, playerData.questData.activeQuests.size(), 0xFFFFFF));
        info.add(new ProfileInfoEntry("profile.info.dialog.read", 4697333, playerData.dialogData.dialogsRead.size(), 0xFFFFFF));
        return info;
    }
}

