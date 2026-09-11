/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.blocks.tiles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.quests.QuestLocation;

public class TileWaypoint
extends TileEntity {
    public String name = "";
    private int ticks = 10;
    private List<EntityPlayer> recentlyChecked = new ArrayList<EntityPlayer>();
    private List<EntityPlayer> toCheck;
    public int range = 10;

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K || this.name.isEmpty()) {
            return;
        }
        --this.ticks;
        if (this.ticks > 0) {
            return;
        }
        this.ticks = 10;
        this.toCheck = this.getPlayerList(this.range, this.range, this.range);
        this.toCheck.removeAll(this.recentlyChecked);
        List<EntityPlayer> listMax = this.getPlayerList(this.range + 10, this.range + 10, this.range + 10);
        this.recentlyChecked.retainAll(listMax);
        this.recentlyChecked.addAll(this.toCheck);
        if (this.toCheck.isEmpty()) {
            return;
        }
        for (EntityPlayer player : this.toCheck) {
            PlayerData playerData = PlayerData.get(player);
            PlayerQuestData questData = playerData.questData;
            Party party = playerData.getPlayerParty();
            Quest partyQuest = null;
            if (party != null && party.getQuestData() != null && party.getQuestData().quest != null && party.getQuestData().quest.type == EnumQuestType.Location) {
                QuestData partyQuestData = party.getQuestData();
                partyQuest = partyQuestData.quest;
                QuestLocation partyQuestLocation = (QuestLocation)partyQuestData.quest.questInterface;
                boolean isPartyLeader = player.func_110124_au().equals(party.getLeaderUUID());
                if (partyQuestLocation.setFoundParty(party, player, this.name, isPartyLeader)) {
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation(this.name + " " + StatCollector.func_74838_a((String)"quest.found"), new Object[0]));
                    PartyController.Instance().pingPartyQuestObjectiveUpdate(party);
                    PartyController.Instance().checkQuestCompletion(party, EnumQuestType.Location);
                }
            }
            ArrayList<QuestData> activeQuestValues = new ArrayList<QuestData>(questData.activeQuests.values());
            for (QuestData data : activeQuestValues) {
                QuestLocation quest;
                if (data.quest.type != EnumQuestType.Location || partyQuest != null && partyQuest.id == data.quest.getId() || data.quest.partyOptions.allowParty && data.quest.partyOptions.onlyParty || !(quest = (QuestLocation)data.quest.questInterface).setFound(data, this.name)) continue;
                player.func_145747_a((IChatComponent)new ChatComponentTranslation(this.name + " " + StatCollector.func_74838_a((String)"quest.found"), new Object[0]));
                questData.checkQuestCompletion(playerData, EnumQuestType.Location);
            }
        }
    }

    private List<EntityPlayer> getPlayerList(int x, int y, int z) {
        return this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b((double)x, (double)y, (double)z));
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.name = compound.func_74779_i("LocationName");
        this.range = compound.func_74762_e("LocationRange");
        if (this.range < 2) {
            this.range = 2;
        }
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        if (!this.name.isEmpty()) {
            compound.func_74778_a("LocationName", this.name);
        }
        compound.func_74768_a("LocationRange", this.range);
    }
}

