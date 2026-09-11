/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.quests;

import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IQuestInterface;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;

public abstract class QuestInterface
implements IQuestInterface {
    public int questId;

    public abstract void writeEntityToNBT(NBTTagCompound var1);

    public abstract void readEntityFromNBT(NBTTagCompound var1);

    public abstract boolean isCompleted(PlayerData var1);

    public void handleComplete(EntityPlayer player) {
        PlayerQuestData questData = PlayerData.get((EntityPlayer)player).questData;
        if (questData != null && questData.getTrackedQuest() != null && this.questId == PlayerData.get((EntityPlayer)player).questData.getTrackedQuest().getId()) {
            PlayerData.get((EntityPlayer)player).questData.untrackQuest();
        }
    }

    public void handlePartyComplete(EntityPlayer player, Party party, boolean isLeader, EnumPartyObjectives objectives) {
        this.handleComplete(player);
    }

    public void removePartyItems(Party party) {
    }

    public abstract Vector<String> getQuestLogStatus(EntityPlayer var1);

    public abstract IQuestObjective[] getObjectives(EntityPlayer var1);

    public abstract IQuestObjective[] getPartyObjectives(Party var1);

    public abstract Vector<String> getPartyQuestLogStatus(Party var1);

    public abstract boolean isPartyCompleted(Party var1);
}

