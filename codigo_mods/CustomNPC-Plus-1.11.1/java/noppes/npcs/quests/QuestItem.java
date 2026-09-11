/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.quests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.api.handler.data.IQuestItem;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.util.ValueUtil;

public class QuestItem
extends QuestInterface
implements IQuestItem {
    public NpcMiscInventory items = new NpcMiscInventory(3);
    public static EntityPlayer pickedUpPlayerSolo;
    public static ItemStack pickedUp;
    public static ItemStack pickedUpParty;
    public static EntityPlayer pickedUpPlayer;
    public boolean leaveItems = false;
    public boolean ignoreDamage = false;
    public boolean ignoreNBT = false;

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.items.setFromNBT(compound.func_74775_l("Items"));
        this.leaveItems = compound.func_74767_n("LeaveItems");
        this.ignoreDamage = compound.func_74767_n("IgnoreDamage");
        this.ignoreNBT = compound.func_74767_n("IgnoreNBT");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.func_74782_a("Items", (NBTBase)this.items.getToNBT());
        compound.func_74757_a("LeaveItems", this.leaveItems);
        compound.func_74757_a("IgnoreDamage", this.ignoreDamage);
        compound.func_74757_a("IgnoreNBT", this.ignoreNBT);
    }

    @Override
    public boolean isCompleted(PlayerData playerData) {
        if (playerData == null || playerData.player == null) {
            return false;
        }
        List<ItemStack> requiredStacks = NoppesUtilPlayer.countStacks(this.items, this.ignoreDamage, this.ignoreNBT);
        List<ItemStack> availableStacks = this.aggregateAvailableStacks(this.collectAvailableStacks(playerData.player, pickedUp));
        for (ItemStack required : requiredStacks) {
            boolean done = false;
            for (ItemStack available : availableStacks) {
                if (!NoppesUtilPlayer.compareItems(required, available, this.ignoreDamage, this.ignoreNBT) || available.field_77994_a < required.field_77994_a) continue;
                done = true;
                break;
            }
            if (done) continue;
            return false;
        }
        return true;
    }

    public HashMap<Integer, ItemStack> getProcessSet(EntityPlayer player) {
        return this.buildProgressMap(this.collectAvailableStacks(player, pickedUp));
    }

    @Override
    public void handleComplete(EntityPlayer player) {
        super.handleComplete(player);
        if (this.leaveItems) {
            return;
        }
        this.removeItems(player);
    }

    @Override
    public void handlePartyComplete(EntityPlayer player, Party party, boolean isLeader, EnumPartyObjectives objectives) {
        super.handlePartyComplete(player, party, isLeader, objectives);
        if (this.leaveItems) {
            return;
        }
        if (isLeader && objectives == EnumPartyObjectives.Leader) {
            this.removeItems(player);
        } else if (objectives == EnumPartyObjectives.All) {
            this.removeItems(player);
        } else if (objectives == EnumPartyObjectives.Shared) {
            // empty if block
        }
    }

    @Override
    public void removePartyItems(Party party) {
        for (ItemStack questItem : this.items.items.values()) {
            int removedItems;
            UUID uuid;
            EntityPlayer player;
            int remainingItems = questItem.field_77994_a;
            Iterator<UUID> iterator = party.getPlayerUUIDs().iterator();
            while (iterator.hasNext() && ((player = NoppesUtilServer.getPlayer(uuid = iterator.next())) == null || (remainingItems -= (removedItems = this.removeItemFromPlayer(player, questItem, remainingItems))) > 0)) {
            }
        }
    }

    private int removeItemFromPlayer(EntityPlayer player, ItemStack questItem, int numItemsToRemove) {
        int itemsRemoved = 0;
        for (int i = 0; i < player.field_71071_by.field_70462_a.length && itemsRemoved < numItemsToRemove; ++i) {
            int size;
            ItemStack itemStack = player.field_71071_by.field_70462_a[i];
            if (itemStack == null || !NoppesUtilPlayer.compareItems(itemStack, questItem, this.ignoreDamage, this.ignoreNBT)) continue;
            int itemsToRemoveFromStack = Math.min(itemStack.field_77994_a, numItemsToRemove - itemsRemoved);
            if (itemsToRemoveFromStack >= (size = itemStack.field_77994_a)) {
                player.field_71071_by.func_70299_a(i, null);
                itemStack.func_77979_a(size);
            } else {
                itemStack.func_77979_a(itemsToRemoveFromStack);
            }
            if ((itemsRemoved += size) >= numItemsToRemove) break;
        }
        return itemsRemoved;
    }

    public void removeItems(EntityPlayer player) {
        block0: for (ItemStack questitem : this.items.items.values()) {
            int stacksize = questitem.field_77994_a;
            for (int i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                ItemStack item = player.field_71071_by.field_70462_a[i];
                if (item == null || !NoppesUtilPlayer.compareItems(item, questitem, this.ignoreDamage, this.ignoreNBT)) continue;
                int size = item.field_77994_a;
                if (stacksize - size >= 0) {
                    player.field_71071_by.func_70299_a(i, null);
                    item.func_77979_a(size);
                } else {
                    item.func_77979_a(stacksize);
                }
                if ((stacksize -= size) <= 0) continue block0;
            }
        }
    }

    @Override
    public Vector<String> getQuestLogStatus(EntityPlayer player) {
        Vector<String> vec = new Vector<String>();
        HashMap<Integer, ItemStack> map = this.getProcessSet(player);
        for (int slot : map.keySet()) {
            ItemStack item = map.get(slot);
            ItemStack quest = this.items.items.get(slot);
            if (item == null) continue;
            String process = item.field_77994_a + "";
            if (item.field_77994_a > quest.field_77994_a) {
                process = quest.field_77994_a + "";
            }
            process = process + "/" + quest.field_77994_a + "";
            if (item.func_82837_s()) {
                vec.add(item.func_82833_r() + ": " + process);
                continue;
            }
            vec.add(item.func_77977_a() + ".name: " + process);
        }
        return vec;
    }

    @Override
    public IQuestObjective[] getObjectives(EntityPlayer player) {
        ArrayList<QuestItemObjective> list = new ArrayList<QuestItemObjective>();
        List<ItemStack> questItems = NoppesUtilPlayer.countStacks(this.items, this.ignoreDamage, this.ignoreNBT);
        for (ItemStack stack : questItems) {
            if (stack.field_77994_a <= 0) continue;
            list.add(new QuestItemObjective(this, player, stack));
        }
        return list.toArray(new IQuestObjective[0]);
    }

    @Override
    public IQuestObjective[] getPartyObjectives(Party party) {
        ArrayList<QuestItemObjective> list = new ArrayList<QuestItemObjective>();
        List<ItemStack> questItems = NoppesUtilPlayer.countStacks(this.items, this.ignoreDamage, this.ignoreNBT);
        for (ItemStack stack : questItems) {
            if (stack.field_77994_a <= 0) continue;
            list.add(new QuestItemObjective(this, party, stack));
        }
        return list.toArray(new IQuestObjective[0]);
    }

    @Override
    public Vector<String> getPartyQuestLogStatus(Party party) {
        Vector<String> vec;
        block16: {
            EnumPartyObjectives objectives;
            block15: {
                vec = new Vector<String>();
                if (party == null || party.getObjectiveRequirement() == null) {
                    return vec;
                }
                objectives = party.getObjectiveRequirement();
                if (objectives != EnumPartyObjectives.All) break block15;
                HashMap<Integer, String> questVector = new HashMap<Integer, String>();
                HashMap<Integer, List> playerVector = new HashMap<Integer, List>();
                Iterator<Comparable<Integer>> iterator = this.items.items.keySet().iterator();
                while (iterator.hasNext()) {
                    int slot = iterator.next();
                    ItemStack questItem = this.items.items.get(slot);
                    if (questItem == null) continue;
                    if (questItem.func_82837_s()) {
                        questVector.put(slot, questItem.func_82833_r() + ": " + questItem.field_77994_a);
                    } else {
                        questVector.put(slot, questItem.func_77977_a() + ".name: " + questItem.field_77994_a);
                    }
                    playerVector.put(slot, new ArrayList());
                }
                for (UUID uuid : party.getPlayerUUIDs()) {
                    EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
                    if (player == null) continue;
                    HashMap<Integer, ItemStack> perPlayerMap = this.getProcessSetParty(player);
                    for (int slot : perPlayerMap.keySet()) {
                        List slotCurrent = (List)playerVector.get(slot);
                        ItemStack item = perPlayerMap.get(slot);
                        ItemStack quest = this.items.items.get(slot);
                        if (item == null || quest == null || item.field_77994_a > quest.field_77994_a) continue;
                        slotCurrent.add(player.func_70005_c_() + ": " + item.field_77994_a);
                        playerVector.put(slot, slotCurrent);
                    }
                }
                iterator = questVector.keySet().iterator();
                while (iterator.hasNext()) {
                    int slot = (Integer)iterator.next();
                    String questEntry = (String)questVector.get(slot);
                    vec.add(questEntry);
                    List playerItems = (List)playerVector.get(slot);
                    if (playerItems == null || playerItems.isEmpty()) continue;
                    StringBuilder playerNamesBuilder = new StringBuilder();
                    for (String playerName : playerItems) {
                        if (playerNamesBuilder.length() > 0) {
                            playerNamesBuilder.append(", ");
                        }
                        playerNamesBuilder.append(playerName);
                    }
                    vec.add("[" + playerNamesBuilder.toString() + "]");
                }
                break block16;
            }
            if (objectives != EnumPartyObjectives.Shared && objectives != EnumPartyObjectives.Leader) break block16;
            HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
            Iterator<Comparable<Integer>> iterator = this.items.items.keySet().iterator();
            while (iterator.hasNext()) {
                int slot = iterator.next();
                ItemStack item = this.items.items.get(slot);
                if (item == null) continue;
                totals.put(slot, 0);
            }
            for (UUID uuid : party.getPlayerUUIDs()) {
                EntityPlayer player;
                if (uuid == null || objectives == EnumPartyObjectives.Leader && !party.getLeaderUUID().equals(uuid) || (player = NoppesUtilServer.getPlayer(uuid)) == null) continue;
                HashMap<Integer, ItemStack> perPlayerMap = this.getProcessSetParty(player);
                for (Map.Entry<Integer, ItemStack> entry : this.items.items.entrySet()) {
                    ItemStack item;
                    int slot = entry.getKey();
                    ItemStack reqItem = entry.getValue();
                    if (reqItem == null || (item = perPlayerMap.get(slot)) == null || !NoppesUtilPlayer.compareItems(reqItem, item, this.ignoreDamage, this.ignoreNBT)) continue;
                    int count = (Integer)totals.get(slot);
                    totals.put(slot, count += item.field_77994_a);
                }
            }
            iterator = this.items.items.keySet().iterator();
            while (iterator.hasNext()) {
                int slot = (Integer)iterator.next();
                ItemStack reqItem = this.items.items.get(slot);
                if (reqItem == null) continue;
                int totalCount = (Integer)totals.get(slot);
                if (totalCount > reqItem.field_77994_a) {
                    totalCount = reqItem.field_77994_a;
                }
                String process = totalCount + "/" + reqItem.field_77994_a;
                if (reqItem.func_82837_s()) {
                    vec.add(reqItem.func_82833_r() + ": " + process);
                    continue;
                }
                vec.add(reqItem.func_77977_a() + ".name: " + process);
            }
        }
        return vec;
    }

    @Override
    public boolean isPartyCompleted(Party party) {
        block9: {
            EnumPartyObjectives objectives;
            block8: {
                if (party == null || party.getObjectiveRequirement() == null) {
                    return false;
                }
                objectives = party.getObjectiveRequirement();
                if (objectives != EnumPartyObjectives.All) break block8;
                for (UUID uuid : party.getPlayerUUIDs()) {
                    EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
                    if (player == null) continue;
                    HashMap<Integer, ItemStack> perPlayerMap = this.getProcessSetParty(player);
                    for (ItemStack reqItem : this.items.items.values()) {
                        boolean done = false;
                        for (ItemStack item : perPlayerMap.values()) {
                            if (!NoppesUtilPlayer.compareItems(reqItem, item, this.ignoreDamage, this.ignoreNBT) || item.field_77994_a < reqItem.field_77994_a) continue;
                            done = true;
                            break;
                        }
                        if (done) continue;
                        return false;
                    }
                }
                break block9;
            }
            if (objectives != EnumPartyObjectives.Shared && objectives != EnumPartyObjectives.Leader) break block9;
            HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
            Iterator<Comparable<Integer>> iterator = this.items.items.keySet().iterator();
            while (iterator.hasNext()) {
                int slot = iterator.next();
                ItemStack item = this.items.items.get(slot);
                if (item == null) continue;
                totals.put(slot, 0);
            }
            for (UUID uuid : party.getPlayerUUIDs()) {
                EntityPlayer player;
                if (uuid == null || objectives == EnumPartyObjectives.Leader && !party.getLeaderUUID().equals(uuid) || (player = NoppesUtilServer.getPlayer(uuid)) == null) continue;
                HashMap<Integer, ItemStack> perPlayerMap = this.getProcessSetParty(player);
                for (Map.Entry<Integer, ItemStack> entry : this.items.items.entrySet()) {
                    ItemStack item;
                    int slot = entry.getKey();
                    ItemStack reqItem = entry.getValue();
                    if (reqItem == null || (item = perPlayerMap.get(slot)) == null || !NoppesUtilPlayer.compareItems(reqItem, item, this.ignoreDamage, this.ignoreNBT)) continue;
                    int count = (Integer)totals.get(slot);
                    totals.put(slot, count += item.field_77994_a);
                }
            }
            iterator = this.items.items.keySet().iterator();
            while (iterator.hasNext()) {
                int slot = (Integer)iterator.next();
                ItemStack reqItem = this.items.items.get(slot);
                int totalCount = (Integer)totals.get(slot);
                if (totalCount >= reqItem.field_77994_a) continue;
                return false;
            }
        }
        return true;
    }

    public HashMap<Integer, ItemStack> getProcessSetParty(EntityPlayer player) {
        ItemStack extra = null;
        if (pickedUpPlayer != null && player != null && player.func_70005_c_().equals(pickedUpPlayer.func_70005_c_())) {
            extra = pickedUpParty;
        }
        return this.buildProgressMap(this.collectAvailableStacks(player, extra));
    }

    private List<ItemStack> collectAvailableStacks(EntityPlayer player, ItemStack extra) {
        ArrayList<ItemStack> available = new ArrayList<ItemStack>();
        if (player == null) {
            return available;
        }
        for (ItemStack stack : player.field_71071_by.field_70462_a) {
            if (stack == null || stack.field_77994_a <= 0) continue;
            available.add(stack.func_77946_l());
        }
        if (extra != null && extra.field_77994_a > 0) {
            available.add(extra.func_77946_l());
        }
        return available;
    }

    private HashMap<Integer, ItemStack> buildProgressMap(List<ItemStack> available) {
        HashMap<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
        ArrayList<Integer> slots = new ArrayList<Integer>(this.items.items.keySet());
        Collections.sort(slots);
        Iterator iterator = slots.iterator();
        while (iterator.hasNext()) {
            int slot = (Integer)iterator.next();
            ItemStack requirement = this.items.items.get(slot);
            if (requirement == null) continue;
            ItemStack progress = requirement.func_77946_l();
            progress.field_77994_a = 0;
            int remaining = requirement.field_77994_a;
            for (ItemStack stack : available) {
                if (stack == null || stack.field_77994_a <= 0 || !NoppesUtilPlayer.compareItems(requirement, stack, this.ignoreDamage, this.ignoreNBT)) continue;
                int used = Math.min(stack.field_77994_a, remaining);
                if (used > 0) {
                    progress.field_77994_a += used;
                    stack.field_77994_a -= used;
                    remaining -= used;
                }
                if (remaining > 0) continue;
                break;
            }
            map.put(slot, progress);
        }
        return map;
    }

    private List<ItemStack> aggregateAvailableStacks(List<ItemStack> available) {
        ArrayList<ItemStack> aggregated = new ArrayList<ItemStack>();
        for (ItemStack stack : available) {
            if (stack == null || stack.field_77994_a <= 0) continue;
            boolean found = false;
            for (ItemStack existing : aggregated) {
                if (!NoppesUtilPlayer.compareItems(existing, stack, this.ignoreDamage, this.ignoreNBT)) continue;
                existing.field_77994_a += stack.field_77994_a;
                found = true;
                break;
            }
            if (found) continue;
            aggregated.add(stack.func_77946_l());
        }
        return aggregated;
    }

    @Override
    public void setLeaveItems(boolean leaveItems) {
        this.leaveItems = leaveItems;
    }

    @Override
    public boolean getLeaveItems() {
        return this.leaveItems;
    }

    @Override
    public void setIgnoreDamage(boolean ignoreDamage) {
        this.ignoreDamage = ignoreDamage;
    }

    @Override
    public boolean getIgnoreDamage() {
        return this.ignoreDamage;
    }

    @Override
    public void setIgnoreNbt(boolean ignoreNbt) {
        this.ignoreNBT = ignoreNbt;
    }

    @Override
    public boolean getIgnoreNbt() {
        return this.ignoreNBT;
    }

    class QuestItemObjective
    implements IQuestObjective {
        private final QuestItem parent;
        private final EntityPlayer player;
        private final Party party;
        private final ItemStack questItem;

        public QuestItemObjective(QuestItem this$02, EntityPlayer player, ItemStack item) {
            this.parent = this$02;
            this.player = player;
            this.questItem = item;
            this.party = null;
        }

        public QuestItemObjective(QuestItem this$02, Party party, ItemStack item) {
            this.parent = this$02;
            this.player = null;
            this.questItem = item;
            this.party = party;
        }

        @Override
        public int getProgress() {
            int count = 0;
            if (this.player != null) {
                ItemStack item = pickedUp;
                if (!NoppesUtilServer.IsItemStackNull(item) && NoppesUtilPlayer.compareItems(this.questItem, item, this.parent.ignoreDamage, this.parent.ignoreNBT)) {
                    count += item.field_77994_a;
                }
                for (int i = 0; i < this.player.field_71071_by.func_70302_i_(); ++i) {
                    item = this.player.field_71071_by.func_70301_a(i);
                    if (NoppesUtilServer.IsItemStackNull(item) || !NoppesUtilPlayer.compareItems(this.questItem, item, this.parent.ignoreDamage, this.parent.ignoreNBT)) continue;
                    count += item.field_77994_a;
                }
            } else if (this.party != null && this.party.getObjectiveRequirement() != null) {
                EnumPartyObjectives objectives = this.party.getObjectiveRequirement();
                for (String name : this.party.getPlayerNames()) {
                    EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
                    if (playerMP == null || !playerMP.func_110124_au().equals(this.party.getLeaderUUID()) && objectives == EnumPartyObjectives.Leader) continue;
                    int amount = 0;
                    for (int i = 0; i < playerMP.field_71071_by.func_70302_i_(); ++i) {
                        ItemStack item = playerMP.field_71071_by.func_70301_a(i);
                        if (NoppesUtilServer.IsItemStackNull(item) || !NoppesUtilPlayer.compareItems(this.questItem, item, this.parent.ignoreDamage, this.parent.ignoreNBT)) continue;
                        amount += item.field_77994_a;
                    }
                    if (objectives == EnumPartyObjectives.All) {
                        count += ValueUtil.clamp(amount, 0, this.questItem.field_77994_a);
                        continue;
                    }
                    count += amount;
                }
                if (objectives == EnumPartyObjectives.All) {
                    return ValueUtil.clamp(count, 0, this.questItem.field_77994_a * this.party.getPlayerNames().size());
                }
            }
            return ValueUtil.clamp(count, 0, this.questItem.field_77994_a);
        }

        @Override
        public void setProgress(int progress) {
            throw new CustomNPCsException("Cant set the progress of ItemQuests", new Object[0]);
        }

        @Override
        public void setPlayerProgress(String playerName, int progress) {
            throw new CustomNPCsException("Cant set the progress of ItemQuests", new Object[0]);
        }

        @Override
        public int getMaxProgress() {
            EnumPartyObjectives objectives;
            if (this.party != null && this.party.getObjectiveRequirement() != null && (objectives = this.party.getObjectiveRequirement()) == EnumPartyObjectives.All) {
                return this.questItem.field_77994_a * this.party.getPlayerNames().size();
            }
            return this.questItem.field_77994_a;
        }

        @Override
        public boolean isCompleted() {
            if (this.player != null) {
                return NoppesUtilPlayer.compareItems(this.player, this.questItem, this.parent.ignoreDamage, this.parent.ignoreNBT);
            }
            if (this.party != null) {
                return this.comparePartyItems(this.party, this.questItem, this.parent.ignoreDamage, this.parent.ignoreNBT);
            }
            return false;
        }

        public boolean comparePartyItems(Party party, ItemStack item, boolean ignoreDamage, boolean ignoreNBT) {
            int size = 0;
            if (party.getObjectiveRequirement() == null) {
                return false;
            }
            EnumPartyObjectives objectives = party.getObjectiveRequirement();
            for (String name : party.getPlayerNames()) {
                EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
                if (playerMP == null || objectives == EnumPartyObjectives.Leader && !playerMP.func_110124_au().equals(party.getLeaderUUID())) continue;
                int amount = 0;
                for (ItemStack is : playerMP.field_71071_by.field_70462_a) {
                    if (!NoppesUtilPlayer.compareItems(item, is, ignoreDamage, ignoreNBT)) continue;
                    amount += is.field_77994_a;
                }
                if (objectives == EnumPartyObjectives.All) {
                    size += ValueUtil.clamp(amount, 0, this.questItem.field_77994_a);
                    continue;
                }
                size += amount;
            }
            if (objectives == EnumPartyObjectives.All) {
                return size >= item.field_77994_a * party.getPlayerNames().size();
            }
            return size >= item.field_77994_a;
        }

        @Override
        public String getText() {
            return this.questItem.func_82833_r() + ": " + this.getProgress() + "/" + this.getMaxProgress();
        }

        @Override
        public String getAdditionalText() {
            if (this.party != null && this.party.getObjectiveRequirement() == EnumPartyObjectives.All) {
                ArrayList<String> incompletePlayers = new ArrayList<String>();
                EnumPartyObjectives objectives = this.party.getObjectiveRequirement();
                for (String name : this.party.getPlayerNames()) {
                    EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
                    if (playerMP == null) {
                        incompletePlayers.add(name + ": N/A");
                        continue;
                    }
                    int amount = 0;
                    for (ItemStack is : playerMP.field_71071_by.field_70462_a) {
                        if (!NoppesUtilPlayer.compareItems(this.questItem, is, QuestItem.this.ignoreDamage, QuestItem.this.ignoreNBT)) continue;
                        amount += is.field_77994_a;
                    }
                    int completedSize = ValueUtil.clamp(amount, 0, this.questItem.field_77994_a);
                    if (completedSize >= this.questItem.field_77994_a) continue;
                    incompletePlayers.add(name + ": " + completedSize);
                }
                if (!incompletePlayers.isEmpty()) {
                    return "[" + String.join((CharSequence)", ", incompletePlayers) + "]";
                }
            }
            return null;
        }
    }
}

