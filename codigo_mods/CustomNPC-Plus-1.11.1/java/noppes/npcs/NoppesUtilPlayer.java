/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.oredict.OreDictionary
 */
package noppes.npcs;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumSoundOperation;
import kamkeel.npcs.network.packets.data.DisableMouseInputPacket;
import kamkeel.npcs.network.packets.data.OverlayQuestTrackingPacket;
import kamkeel.npcs.network.packets.data.QuestCompletionPacket;
import kamkeel.npcs.network.packets.data.SoundManagementPacket;
import kamkeel.npcs.network.packets.data.SwingPlayerArmPacket;
import kamkeel.npcs.network.packets.data.gui.GuiClosePacket;
import kamkeel.npcs.network.packets.data.gui.IsGuiOpenPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.network.packets.data.large.PartyDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.oredict.OreDictionary;
import noppes.npcs.CustomTeleporter;
import noppes.npcs.EventHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.QuestLogData;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.constants.EnumPartyExchange;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.containers.ContainerNPCBankInterface;
import noppes.npcs.containers.ContainerNPCFollower;
import noppes.npcs.containers.ContainerNPCFollowerHire;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.Bank;
import noppes.npcs.controllers.data.BankData;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PartyOptions;
import noppes.npcs.controllers.data.PlayerBankData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.PlayerTransportData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.controllers.data.TransportLocation;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleFollower;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptSound;
import noppes.npcs.scripted.event.PartyEvent;
import noppes.npcs.scripted.event.player.DialogEvent;
import noppes.npcs.scripted.event.player.QuestEvent;

public class NoppesUtilPlayer {
    public static void changeFollowerState(EntityPlayerMP player, EntityNPCInterface npc) {
        if (npc.advanced.role != EnumRoleType.Follower) {
            return;
        }
        RoleFollower role = (RoleFollower)npc.roleInterface;
        EntityPlayer owner = role.owner;
        if (owner == null || !owner.func_70005_c_().equals(player.func_70005_c_())) {
            return;
        }
        role.isFollowing = !role.isFollowing;
    }

    public static void hireFollower(EntityPlayerMP player, EntityNPCInterface npc) {
        if (npc.advanced.role != EnumRoleType.Follower) {
            return;
        }
        Container con = player.field_71070_bA;
        if (con == null || !(con instanceof ContainerNPCFollowerHire)) {
            return;
        }
        ContainerNPCFollowerHire container = (ContainerNPCFollowerHire)con;
        RoleFollower role = (RoleFollower)npc.roleInterface;
        NoppesUtilPlayer.followerBuy(role, (IInventory)container.currencyMatrix, player, npc);
    }

    public static void extendFollower(EntityPlayerMP player, EntityNPCInterface npc) {
        if (npc.advanced.role != EnumRoleType.Follower) {
            return;
        }
        Container con = player.field_71070_bA;
        if (con == null || !(con instanceof ContainerNPCFollower)) {
            return;
        }
        ContainerNPCFollower container = (ContainerNPCFollower)con;
        RoleFollower role = (RoleFollower)npc.roleInterface;
        NoppesUtilPlayer.followerBuy(role, container.currencyMatrix, player, npc);
    }

    public static void transport(EntityPlayerMP player, EntityNPCInterface npc, String location) {
        TransportLocation loc = TransportController.getInstance().getTransport(location);
        PlayerTransportData playerdata = PlayerData.get((EntityPlayer)player).transportData;
        if (loc == null || !loc.isDefault() && !playerdata.transports.contains(loc.id)) {
            return;
        }
        NoppesUtilPlayer.teleportPlayer(player, loc.posX, loc.posY, loc.posZ, loc.dimension);
    }

    public static void teleportPlayer(EntityPlayerMP player, double posX, double posY, double posZ, int dimension) {
        if (player.field_70154_o instanceof EntityNPCInterface) {
            player.func_70078_a(null);
        }
        if (player.field_71093_bK != dimension) {
            MinecraftServer server = MinecraftServer.func_71276_C();
            WorldServer wor = server.func_71218_a(dimension);
            if (wor == null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("Broken transporter. Dimension does not exist"));
                return;
            }
            player.func_70012_b(posX, posY, posZ, player.field_70177_z, player.field_70125_A);
            server.func_71203_ab().transferPlayerToDimension(player, dimension, (Teleporter)new CustomTeleporter(wor));
            player.field_71135_a.func_147364_a(posX, posY, posZ, player.field_70177_z, player.field_70125_A);
            if (!wor.field_73010_i.contains(player)) {
                wor.func_72838_d((Entity)player);
            }
        } else {
            player.field_71135_a.func_147364_a(posX, posY, posZ, player.field_70177_z, player.field_70125_A);
        }
        player.field_70170_p.func_72866_a((Entity)player, false);
    }

    public static void teleportPlayer(EntityPlayerMP player, double posX, double posY, double posZ, float yaw, float pitch, int dimension) {
        if (player.field_70154_o instanceof EntityNPCInterface) {
            player.func_70078_a(null);
        }
        if (player.field_71093_bK != dimension) {
            MinecraftServer server = MinecraftServer.func_71276_C();
            WorldServer wor = server.func_71218_a(dimension);
            if (wor == null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("Broken transporter. Dimenion does not exist"));
                return;
            }
            player.func_70012_b(posX, posY, posZ, yaw, pitch);
            server.func_71203_ab().transferPlayerToDimension(player, dimension, (Teleporter)new CustomTeleporter(wor));
            player.field_71135_a.func_147364_a(posX, posY, posZ, yaw, pitch);
            if (!wor.field_73010_i.contains(player)) {
                wor.func_72838_d((Entity)player);
            }
        } else {
            player.field_71135_a.func_147364_a(posX, posY, posZ, yaw, pitch);
        }
        player.field_70170_p.func_72866_a((Entity)player, false);
    }

    public static void disableMouseInput(EntityPlayerMP player, long time, int ... buttonIds) {
        StringBuilder stringedIds = new StringBuilder();
        for (int i = 0; i < buttonIds.length; ++i) {
            stringedIds.append(i);
            if (i >= buttonIds.length - 1) continue;
            stringedIds.append(";");
        }
        PacketHandler.Instance.sendToPlayer(new DisableMouseInputPacket(time, stringedIds.toString()), player);
    }

    public static void swingPlayerArm(EntityPlayerMP player) {
        PacketHandler.Instance.sendToPlayer(new SwingPlayerArmPacket(), player);
    }

    private static void followerBuy(RoleFollower role, IInventory currencyInv, EntityPlayerMP player, EntityNPCInterface npc) {
        ItemStack currency = currencyInv.func_70301_a(0);
        if (currency == null) {
            return;
        }
        HashMap<ItemStack, Integer> cd = new HashMap<ItemStack, Integer>();
        for (int i : role.inventory.items.keySet()) {
            ItemStack is = role.inventory.items.get(i);
            if (is == null || is.func_77973_b() != currency.func_77973_b() || is.func_77981_g() && is.func_77960_j() != currency.func_77960_j()) continue;
            int days = 1;
            if (role.rates.containsKey(i)) {
                days = role.rates.get(i);
            }
            cd.put(is, days);
        }
        if (cd.size() == 0) {
            return;
        }
        int stackSize = currency.field_77994_a;
        int days = 0;
        int possibleDays = 0;
        int possibleSize = stackSize;
        while (true) {
            for (ItemStack item : cd.keySet()) {
                int newStackSize;
                int size;
                int posDays;
                int rDays = (Integer)cd.get(item);
                int rValue = item.field_77994_a;
                if (rValue > stackSize || possibleDays > (posDays = (size = stackSize - (newStackSize = stackSize % rValue)) / rValue * rDays)) continue;
                possibleDays = posDays;
                possibleSize = newStackSize;
            }
            if (stackSize == possibleSize) break;
            stackSize = possibleSize;
            days += possibleDays;
            possibleDays = 0;
        }
        if (days == 0) {
            return;
        }
        if (stackSize <= 0) {
            currencyInv.func_70299_a(0, null);
        } else {
            currency = currency.func_77979_a(stackSize);
        }
        npc.say((EntityPlayer)player, new Line(NoppesStringUtils.formatText(role.dialogHire.replace("{days}", days + ""), new Object[]{player, npc})));
        role.setOwner((EntityPlayer)player);
        role.addDays(days);
    }

    public static void bankUpgrade(EntityPlayerMP player, EntityNPCInterface npc) {
        if (npc.advanced.role != EnumRoleType.Bank) {
            return;
        }
        Container con = player.field_71070_bA;
        if (con == null || !(con instanceof ContainerNPCBankInterface)) {
            return;
        }
        ContainerNPCBankInterface container = (ContainerNPCBankInterface)con;
        Bank bank = BankController.getInstance().getBank(container.bankid);
        ItemStack item = bank.upgradeInventory.func_70301_a(container.slot);
        if (item == null) {
            return;
        }
        int price = item.field_77994_a;
        ItemStack currency = container.currencyMatrix.func_70301_a(0);
        if (currency == null || price > currency.field_77994_a) {
            return;
        }
        if (currency.field_77994_a - price == 0) {
            container.currencyMatrix.func_70299_a(0, null);
        } else {
            currency = currency.func_77979_a(price);
        }
        player.func_71128_l();
        PlayerBankData data = PlayerDataController.Instance.getBankData((EntityPlayer)player, bank.id);
        BankData bankData = data.getBank(bank.id);
        bankData.upgradedSlots.put(container.slot, true);
        bankData.openBankGui((EntityPlayer)player, npc, bank.id, container.slot);
    }

    public static void bankUnlock(EntityPlayerMP player, EntityNPCInterface npc) {
        if (npc.advanced.role != EnumRoleType.Bank) {
            return;
        }
        Container con = player.field_71070_bA;
        if (con == null || !(con instanceof ContainerNPCBankInterface)) {
            return;
        }
        ContainerNPCBankInterface container = (ContainerNPCBankInterface)con;
        Bank bank = BankController.getInstance().getBank(container.bankid);
        ItemStack item = bank.currencyInventory.func_70301_a(container.slot);
        if (item == null) {
            return;
        }
        int price = item.field_77994_a;
        ItemStack currency = container.currencyMatrix.func_70301_a(0);
        if (currency == null || price > currency.field_77994_a) {
            return;
        }
        if (currency.field_77994_a - price == 0) {
            container.currencyMatrix.func_70299_a(0, null);
        } else {
            currency = currency.func_77979_a(price);
        }
        player.func_71128_l();
        PlayerBankData data = PlayerDataController.Instance.getBankData((EntityPlayer)player, bank.id);
        BankData bankData = data.getBank(bank.id);
        if (bankData.unlockedSlots + 1 <= bank.maxSlots) {
            ++bankData.unlockedSlots;
        }
        bankData.openBankGui((EntityPlayer)player, npc, bank.id, container.slot);
    }

    public static void dialogSelected(int dialogId, int optionId, EntityPlayerMP player, EntityNPCInterface npc) {
        Dialog dialog = DialogController.Instance.dialogs.get(dialogId);
        if (dialog == null) {
            return;
        }
        DialogOption option = dialog.options.get(optionId);
        if (EventHooks.onDialogOption((EntityPlayer)player, new DialogEvent.DialogOption((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), dialogId, optionId, dialog))) {
            return;
        }
        if (!npc.isRemote()) {
            EventHooks.onNPCDialogClosed(npc, (EntityPlayer)player, dialogId, optionId + 1, dialog);
            if (!dialog.hasDialogs((EntityPlayer)player) && !dialog.hasOtherOptions()) {
                EventHooks.onDialogClosed((EntityPlayer)player, new DialogEvent.DialogClosed((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), dialogId, optionId, dialog));
                return;
            }
            if (option == null || option.optionType == EnumOptionType.DialogOption && (!option.isAvailable((EntityPlayer)player) || !option.hasDialog()) || option.optionType == EnumOptionType.Disabled || option.optionType == EnumOptionType.QuitOption) {
                EventHooks.onDialogClosed((EntityPlayer)player, new DialogEvent.DialogClosed((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), dialogId, optionId, dialog));
                return;
            }
        }
        if (option.optionType == EnumOptionType.RoleOption) {
            if (npc.roleInterface != null) {
                npc.roleInterface.interact((EntityPlayer)player);
            } else {
                GuiClosePacket.closeGUI(player, -1, new NBTTagCompound());
            }
        } else if (option.optionType == EnumOptionType.DialogOption) {
            NoppesUtilServer.openDialog((EntityPlayer)player, npc, option.getDialog(), optionId + 1);
        } else if (option.optionType == EnumOptionType.CommandBlock) {
            GuiClosePacket.closeGUI(player, -1, new NBTTagCompound());
            NoppesUtilServer.runCommand((EntityPlayer)player, npc.func_70005_c_(), option.command);
        } else {
            GuiClosePacket.closeGUI(player, -1, new NBTTagCompound());
        }
    }

    public static void updateQuestLogData(ByteBuf buffer, EntityPlayerMP player) throws IOException {
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        NBTTagCompound compound = ByteBufUtils.readNBT(buffer);
        HashMap<String, String> questAlerts = NBTTags.getStringStringMap(compound.func_150295_c("Alerts", 10));
        for (Map.Entry<String, String> entry : questAlerts.entrySet()) {
            Quest quest = NoppesUtilPlayer.getQuestFromStringKey(entry.getKey());
            if (quest == null) continue;
            playerData.questData.activeQuests.get((Object)Integer.valueOf((int)quest.id)).sendAlerts = Boolean.parseBoolean(entry.getValue());
        }
        String trackedQuestString = ByteBufUtils.readString(buffer);
        Quest trackedQuest = NoppesUtilPlayer.getQuestFromStringKey(trackedQuestString);
        if (trackedQuest != null) {
            playerData.questData.trackQuest(trackedQuest);
        } else {
            playerData.questData.untrackQuest();
        }
    }

    public static void clearTrackQuest(EntityPlayerMP player) throws IOException {
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        playerData.questData.untrackQuest();
    }

    public static void updatePartyQuestLogData(ByteBuf buffer, EntityPlayerMP player) throws IOException {
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        String trackedQuestString = ByteBufUtils.readString(buffer);
        Quest trackedQuest = NoppesUtilPlayer.getQuestFromStringKey(trackedQuestString);
        if (trackedQuest != null) {
            playerData.questData.trackParty(playerData.getPlayerParty());
        } else {
            playerData.questData.untrackQuest();
        }
    }

    private static Quest getQuestFromStringKey(String string) {
        if (string != null && string.contains(":")) {
            String questName;
            String categoryName;
            String[] splitString = string.split(":");
            if (splitString.length == 3 && splitString[0].equals("P")) {
                categoryName = splitString[1];
                questName = splitString[2];
            } else {
                if (splitString.length < 2) {
                    return null;
                }
                categoryName = splitString[0];
                questName = splitString[1];
            }
            for (QuestCategory category : QuestController.Instance.categories.values()) {
                if (!category.title.equals(categoryName)) continue;
                for (Quest quest : category.quests.values()) {
                    if (!quest.title.equals(questName)) continue;
                    return quest;
                }
            }
        }
        return null;
    }

    public static void sendTrackedQuestData(EntityPlayerMP player) {
        Quest trackedQuest = (Quest)PlayerData.get((EntityPlayer)player).questData.getTrackedQuest();
        if (trackedQuest != null) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74782_a("Quest", (NBTBase)trackedQuest.writeToNBT(new NBTTagCompound()));
            compound.func_74778_a("CategoryName", trackedQuest.getCategory().getName());
            if (trackedQuest.completion == EnumQuestCompletion.Instant) {
                compound.func_74757_a("Instant", true);
            } else {
                compound.func_74778_a("TurnInNPC", trackedQuest.getNpcName());
            }
            NBTTagList nbtTagList = new NBTTagList();
            for (IQuestObjective objective : trackedQuest.questInterface.getObjectives((EntityPlayer)player)) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(objective.getText()));
            }
            compound.func_74782_a("ObjectiveList", (NBTBase)nbtTagList);
            PacketHandler.Instance.sendToPlayer(new OverlayQuestTrackingPacket(compound), player);
        }
    }

    public static void sendPartyTrackedQuestData(EntityPlayerMP player, Party party) {
        Quest trackedQuest = (Quest)PlayerData.get((EntityPlayer)player).questData.getTrackedQuest();
        if (trackedQuest != null) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74782_a("Quest", (NBTBase)trackedQuest.writeToNBT(new NBTTagCompound()));
            compound.func_74778_a("CategoryName", trackedQuest.getCategory().getName());
            if (trackedQuest.completion == EnumQuestCompletion.Instant) {
                compound.func_74757_a("Instant", true);
            } else {
                compound.func_74778_a("TurnInNPC", trackedQuest.getNpcName());
            }
            NBTTagList nbtTagList = new NBTTagList();
            for (IQuestObjective objective : trackedQuest.questInterface.getPartyObjectives(party)) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(objective.getText()));
                if (objective.getAdditionalText() == null) continue;
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(objective.getAdditionalText()));
            }
            compound.func_74782_a("ObjectiveList", (NBTBase)nbtTagList);
            PacketHandler.Instance.sendToPlayer(new OverlayQuestTrackingPacket(compound), player);
        }
    }

    public static void sendQuestLogData(EntityPlayerMP player) {
        if (!PlayerQuestController.hasActiveQuests((EntityPlayer)player)) {
            return;
        }
        QuestLogData data = new QuestLogData();
        data.setData((EntityPlayer)player);
        GuiDataPacket.sendGuiData(player, data.writeNBT());
    }

    public static void sendTrackedQuest(EntityPlayerMP player) {
        QuestLogData data = new QuestLogData();
        data.setTrackedQuestKey((EntityPlayer)player);
        PartyDataPacket.sendPartyData(player, data.writeTrackedQuest());
    }

    public static boolean questCompletion(EntityPlayerMP player, int questId) {
        if (player == null) {
            return false;
        }
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        PlayerQuestData questData = playerData.questData;
        QuestData data = questData.activeQuests.get(questId);
        if (data == null) {
            return false;
        }
        if (!data.quest.questInterface.isCompleted(playerData)) {
            return false;
        }
        if (data.quest.completion == EnumQuestCompletion.Instant) {
            EventHooks.onQuestFinished((EntityPlayer)player, data.quest);
        }
        QuestEvent.QuestTurnedInEvent event = new QuestEvent.QuestTurnedInEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), data.quest);
        event.expReward = data.quest.rewardExp;
        ArrayList<IItemStack> list = new ArrayList<IItemStack>();
        for (ItemStack item : data.quest.rewardItems.items.values()) {
            IItemStack iStack;
            if (item == null || item.field_77994_a <= 0 || (iStack = NpcAPI.Instance().getIItemStack(item)) == null) continue;
            list.add(iStack);
        }
        if (!data.quest.randomReward) {
            event.itemRewards = list.toArray(new IItemStack[list.size()]);
        } else if (!list.isEmpty()) {
            event.itemRewards = new IItemStack[]{(IItemStack)list.get(player.func_70681_au().nextInt(list.size()))};
        }
        EventHooks.onQuestTurnedIn((EntityPlayer)player, event);
        if (event.isCancelled()) {
            return false;
        }
        for (IItemStack item : event.itemRewards) {
            if (item == null) continue;
            NoppesUtilServer.GivePlayerItem((Entity)player, (EntityPlayer)player, item.getMCItemStack());
        }
        data.quest.questInterface.handleComplete((EntityPlayer)player);
        if (data.quest.rewardExp > 0) {
            player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.1f, 0.5f * ((player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.8f));
            player.func_71023_q(data.quest.rewardExp);
        }
        data.quest.factionOptions.addPoints((EntityPlayer)player);
        if (data.quest.mail.isValid()) {
            PlayerDataController.Instance.addPlayerMessage(player.func_70005_c_(), data.quest.mail);
        }
        if (!data.quest.command.isEmpty()) {
            NoppesUtilServer.runCommand((EntityPlayer)player, "QuestCompletion", data.quest.command);
        }
        PlayerQuestController.setQuestFinished(data.quest, (EntityPlayer)player);
        if (data.quest.hasNewQuest()) {
            QuestData nextQuest = new QuestData(data.quest.getNextQuest());
            nextQuest.sendAlerts = data.quest.id != data.quest.getNextQuest().id || data.sendAlerts;
            PlayerQuestController.addActiveQuest(nextQuest, (EntityPlayer)player);
            questData.trackQuest(nextQuest.quest);
        }
        playerData.save();
        return true;
    }

    public static boolean questPartyCompletion(Party party) {
        PlayerData playerData;
        EntityPlayer player;
        if (party == null) {
            return false;
        }
        QuestData data = party.getQuestData();
        if (data == null) {
            return false;
        }
        if (!data.quest.questInterface.isPartyCompleted(party)) {
            return false;
        }
        Quest quest = data.quest;
        if (quest == null) {
            return false;
        }
        if (data.quest.completion == EnumQuestCompletion.Instant) {
            EventHooks.onPartyFinished(party, data.quest);
        }
        PartyEvent.PartyQuestTurnedInEvent partyEv = new PartyEvent.PartyQuestTurnedInEvent(party, data.quest);
        EventHooks.onPartyTurnIn(party, partyEv);
        if (partyEv.isCancelled()) {
            return false;
        }
        boolean hasNextQuest = data.quest.hasNewQuest();
        PartyOptions partyOptions = quest.partyOptions;
        EnumPartyExchange complete = partyOptions.completeFor;
        EnumPartyExchange reward = partyOptions.rewardControl;
        EnumPartyExchange command = partyOptions.executeCommand;
        for (String name : party.getPlayerNames()) {
            player = NoppesUtilServer.getPlayerByName(name);
            if (player == null || (playerData = PlayerData.get(player)) == null) continue;
            boolean isLeader = party.getLeaderUUID().equals(player.func_110124_au());
            boolean hasActiveQuest = playerData.questData.hasActiveQuest(quest.getId());
            boolean hasFinishedQuest = playerData.questData.hasFinishedQuest(quest.getId());
            boolean meetsComplete = NoppesUtilPlayer.meetsPartyExchange(complete, hasActiveQuest, hasFinishedQuest, isLeader);
            boolean meetsReward = NoppesUtilPlayer.meetsPartyExchange(reward, hasActiveQuest, hasFinishedQuest, isLeader);
            boolean meetsCommand = NoppesUtilPlayer.meetsPartyExchange(command, hasActiveQuest, hasFinishedQuest, isLeader);
            if (meetsComplete && data.quest.completion == EnumQuestCompletion.Instant) {
                EventHooks.onQuestFinished(player, data.quest);
            }
            if (meetsReward) {
                QuestEvent.QuestTurnedInEvent event = new QuestEvent.QuestTurnedInEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), data.quest);
                event.expReward = data.quest.rewardExp;
                ArrayList<IItemStack> list = new ArrayList<IItemStack>();
                for (ItemStack item : data.quest.rewardItems.items.values()) {
                    IItemStack iStack;
                    if (item == null || item.field_77994_a <= 0 || (iStack = NpcAPI.Instance().getIItemStack(item)) == null) continue;
                    list.add(iStack);
                }
                if (!data.quest.randomReward) {
                    event.itemRewards = list.toArray(new IItemStack[list.size()]);
                } else if (!list.isEmpty()) {
                    event.itemRewards = new IItemStack[]{(IItemStack)list.get(player.func_70681_au().nextInt(list.size()))};
                }
                EventHooks.onQuestTurnedIn(player, event);
                if (event.isCancelled()) continue;
                for (IItemStack item : event.itemRewards) {
                    if (item == null) continue;
                    NoppesUtilServer.GivePlayerItem((Entity)player, player, item.getMCItemStack());
                }
            }
            data.quest.questInterface.handlePartyComplete(player, party, isLeader, partyOptions.objectiveRequirement);
            if (meetsReward) {
                if (data.quest.rewardExp > 0) {
                    player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.1f, 0.5f * ((player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.8f));
                    player.func_71023_q(data.quest.rewardExp);
                }
                data.quest.factionOptions.addPoints(player);
                if (data.quest.mail.isValid()) {
                    PlayerDataController.Instance.addPlayerMessage(player.func_70005_c_(), data.quest.mail);
                }
            }
            if (meetsCommand && !data.quest.command.isEmpty()) {
                NoppesUtilServer.runCommand(player, "QuestCompletion", data.quest.command);
            }
            if (meetsComplete) {
                PlayerQuestController.setQuestPartyFinished(data.quest, player, data);
            }
            if (hasNextQuest && meetsComplete) {
                QuestData nextQuest = new QuestData(data.quest.getNextQuest());
                nextQuest.sendAlerts = data.quest.id != data.quest.getNextQuest().id || data.sendAlerts;
                PlayerQuestController.addActiveQuest(nextQuest, player);
            }
            playerData.save();
            QuestCompletionPacket.sendQuestComplete((EntityPlayerMP)player, data.quest.writeToNBT(new NBTTagCompound()));
        }
        if (quest.type == EnumQuestType.Item && partyOptions.objectiveRequirement == EnumPartyObjectives.Shared) {
            quest.questInterface.removePartyItems(party);
        }
        if (hasNextQuest) {
            if (party.validateQuest(data.quest.getNextQuest().getId(), true)) {
                party.setQuest(data.quest.getNextQuest());
                for (String name : party.getPlayerNames()) {
                    player = NoppesUtilServer.getPlayerByName(name);
                    if (player == null || (playerData = PlayerData.get(player)) == null) continue;
                    playerData.questData.trackParty(party);
                }
            } else {
                party.setQuest(null);
            }
        } else {
            party.setQuest(null);
        }
        if (data.quest.completion == EnumQuestCompletion.Npc) {
            PartyController.Instance().sendQuestChat(party, "party.completeChat");
            PartyController.Instance().pingPartyUpdate(party);
        }
        return true;
    }

    public static boolean meetsPartyExchange(EnumPartyExchange exchange, boolean hasActive, boolean hasFinished, boolean isLeader) {
        if (exchange == EnumPartyExchange.All) {
            return true;
        }
        if (isLeader) {
            return true;
        }
        if (exchange == EnumPartyExchange.Enrolled && hasActive) {
            return true;
        }
        return exchange == EnumPartyExchange.Valid && (hasActive || hasFinished);
    }

    public static boolean compareItems(ItemStack item, ItemStack item2, boolean ignoreDamage, boolean ignoreNBT) {
        if (item2 == null || item == null) {
            return false;
        }
        boolean oreMatched = false;
        OreDictionary.itemMatches((ItemStack)item, (ItemStack)item2, (boolean)false);
        int[] ids = OreDictionary.getOreIDs((ItemStack)item);
        if (ids.length > 0) {
            for (int id : ids) {
                boolean match1 = false;
                boolean match2 = false;
                for (ItemStack is : OreDictionary.getOres((Integer)id)) {
                    if (NoppesUtilPlayer.compareItemDetails(item, is, ignoreDamage, ignoreNBT)) {
                        match1 = true;
                    }
                    if (!NoppesUtilPlayer.compareItemDetails(item2, is, ignoreDamage, ignoreNBT)) continue;
                    match2 = true;
                }
                if (!match1 || !match2) continue;
                return true;
            }
        }
        return NoppesUtilPlayer.compareItemDetails(item, item2, ignoreDamage, ignoreNBT);
    }

    private static boolean compareItemDetails(ItemStack item, ItemStack item2, boolean ignoreDamage, boolean ignoreNBT) {
        if (item.func_77973_b() != item2.func_77973_b()) {
            return false;
        }
        if (!ignoreDamage && item.func_77960_j() != -1 && item.func_77960_j() != item2.func_77960_j()) {
            return false;
        }
        if (!(ignoreNBT || item.field_77990_d == null || item2.field_77990_d != null && item.field_77990_d.equals((Object)item2.field_77990_d))) {
            return false;
        }
        return ignoreNBT || item2.field_77990_d == null || item.field_77990_d != null;
    }

    public static boolean compareItems(EntityPlayer player, ItemStack item, boolean ignoreDamage, boolean ignoreNBT) {
        int size = 0;
        for (ItemStack is : player.field_71071_by.field_70462_a) {
            if (is == null || !NoppesUtilPlayer.compareItems(item, is, ignoreDamage, ignoreNBT)) continue;
            size += is.field_77994_a;
        }
        return size >= item.field_77994_a;
    }

    public static void consumeItem(EntityPlayer player, ItemStack item, boolean ignoreDamage, boolean ignoreNBT) {
        if (item == null) {
            return;
        }
        int size = item.field_77994_a;
        for (int i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
            ItemStack is = player.field_71071_by.field_70462_a[i];
            if (is == null || !NoppesUtilPlayer.compareItems(item, is, ignoreDamage, ignoreNBT)) continue;
            if (size >= is.field_77994_a) {
                size -= is.field_77994_a;
                player.field_71071_by.field_70462_a[i] = null;
                continue;
            }
            player.field_71071_by.field_70462_a[i].func_77979_a(size);
            break;
        }
    }

    public static void isGUIOpen(EntityPlayerMP player) {
        PacketHandler.Instance.sendToPlayer(new IsGuiOpenPacket(), player);
    }

    public static List<ItemStack> countStacks(IInventory inv, boolean ignoreDamage, boolean ignoreNBT) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack item = inv.func_70301_a(i);
            if (NoppesUtilServer.IsItemStackNull(item)) continue;
            boolean found = false;
            for (ItemStack is : list) {
                if (!NoppesUtilPlayer.compareItems(item, is, ignoreDamage, ignoreNBT)) continue;
                is.field_77994_a += item.field_77994_a;
                found = true;
                break;
            }
            if (found) continue;
            list.add(item.func_77946_l());
        }
        return list;
    }

    public static void playSoundTo(EntityPlayerMP player, int id, ScriptSound sound) {
        NBTTagCompound compound = sound.writeToNBT();
        if (sound.sourceEntity == null || player.field_70170_p.field_73011_w.field_76574_g == sound.sourceEntity.getDimension()) {
            PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.PLAY_SOUND_TO, id, compound), player);
        }
    }

    public static void playSoundTo(EntityPlayerMP player, ScriptSound sound) {
        NBTTagCompound compound = sound.writeToNBT();
        if (sound.sourceEntity == null || player.field_70170_p.field_73011_w.field_76574_g == sound.sourceEntity.getDimension()) {
            PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.PLAY_SOUND_TO_NO_ID, compound), player);
        }
    }

    public static void stopSoundFor(EntityPlayerMP player, int id) {
        PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.STOP_SOUND_FOR, id), player);
    }

    public static void pauseSoundsFor(EntityPlayerMP player) {
        PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.PAUSE_SOUNDS), player);
    }

    public static void continueSoundsFor(EntityPlayerMP player) {
        PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.CONTINUE_SOUNDS), player);
    }

    public static void stopSoundsFor(EntityPlayerMP player) {
        PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.STOP_SOUNDS), player);
    }
}

