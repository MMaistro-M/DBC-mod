/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.command;

import java.util.Collection;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumProfileSync;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestRepeat;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.util.ValueUtil;

public class QuestCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "quest";
    }

    @Override
    public String getDescription() {
        return "Quest operations";
    }

    @CommandKamkeelBase.SubCommand(desc="Start a quest", usage="<player> <quest>")
    public void start(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        String playername = args[0];
        try {
            questid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID: " + questid);
            return;
        }
        for (PlayerData playerdata : data) {
            QuestData questdata = new QuestData(quest);
            playerdata.questData.activeQuests.put(questid, questdata);
            playerdata.save();
            if (playerdata.player != null && questdata.sendAlerts) {
                AchievementPacket.sendAchievement((EntityPlayerMP)playerdata.player, false, "quest.newquest", quest.title);
                ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerdata.player, "quest.newquest", ": ", quest.title);
            }
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Started Quest \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Finish a quest", usage="<player> <quest>")
    public void finish(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        String playername = args[0];
        try {
            questid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID: " + questid);
            return;
        }
        for (PlayerData playerdata : data) {
            long completeTime;
            playerdata.questData.activeQuests.remove(questid);
            if (quest.repeat == EnumQuestRepeat.RLDAILY || quest.repeat == EnumQuestRepeat.RLWEEKLY || quest.repeat == EnumQuestRepeat.RLCUSTOM) {
                completeTime = System.currentTimeMillis();
                playerdata.questData.finishedQuests.put(quest.id, completeTime);
            } else {
                completeTime = sender.func_130014_f_().func_82737_E();
                playerdata.questData.finishedQuests.put(quest.id, completeTime);
            }
            if (ConfigMain.ProfilesEnabled && playerdata.player != null && quest.profileOptions.enableOptions && (quest.profileOptions.completeControl == EnumProfileSync.Shared || quest.profileOptions.cooldownControl == EnumProfileSync.Shared)) {
                ProfileController.Instance.shareQuestCompletion(playerdata.player, quest.id, completeTime);
            }
            playerdata.save();
            if (playerdata.player != null) {
                AchievementPacket.sendAchievement((EntityPlayerMP)playerdata.player, false, "quest.completed", quest.title);
                ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerdata.player, "quest.completed", ": ", quest.title);
            }
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Finished Quest \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Stop a started quest", usage="<player> <quest>")
    public void stop(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        String playername = args[0];
        try {
            questid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID: " + questid);
            return;
        }
        for (PlayerData playerdata : data) {
            playerdata.questData.activeQuests.remove(questid);
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Stopped Quest \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Removes a quest from finished and active quests", usage="<player> <quest>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        String playername = args[0];
        try {
            questid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID");
            return;
        }
        for (PlayerData playerdata : data) {
            playerdata.questData.activeQuests.remove(questid);
            playerdata.questData.finishedQuests.remove(questid);
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Removed Quest \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Get/Set objectives for quests progress", usage="<player> <quest> [objective] [value]")
    public void objective(ICommandSender sender, String[] args) throws CommandException {
        int value;
        int objective;
        boolean partyValid;
        int questid;
        EntityPlayerMP player = QuestCommand.func_82359_c((ICommandSender)sender, (String)args[0]);
        try {
            questid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID");
            return;
        }
        PlayerData data = PlayerData.get((EntityPlayer)player);
        if (data == null) {
            ColorUtil.sendError(sender, "No PlayerData found for:" + player);
            return;
        }
        Party party = data.getPlayerParty();
        boolean bl = partyValid = party != null && party.getQuest() != null && party.getQuest().getId() == quest.id;
        if (!partyValid && !data.questData.activeQuests.containsKey(quest.id)) {
            ColorUtil.sendError(sender, "Player does not have quest active");
            return;
        }
        IQuestObjective[] objectives = partyValid ? quest.questInterface.getPartyObjectives(party) : quest.questInterface.getObjectives((EntityPlayer)player);
        if (args.length <= 2) {
            if (partyValid) {
                ColorUtil.sendResult(sender, "For Party: ");
            }
            for (IQuestObjective ob : objectives) {
                ColorUtil.sendResult(sender, ob.getText());
            }
            return;
        }
        try {
            objective = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Objective must be an integer. Most often 0, 1 or 2");
            return;
        }
        if (objective < 0 || objective >= objectives.length) {
            ColorUtil.sendError(sender, "Invalid objective number was given");
            return;
        }
        if (args.length <= 3) {
            ColorUtil.sendResult(sender, objectives[objective].getText());
            return;
        }
        IQuestObjective object = objectives[objective];
        String s = args[3];
        try {
            value = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Value must be an integer");
            return;
        }
        if (s.startsWith("-") || s.startsWith("+")) {
            value = ValueUtil.CorrectInt(object.getProgress() + value, 0, object.getMaxProgress());
        }
        if (partyValid) {
            object.setPlayerProgress(player.func_70005_c_(), value);
        } else {
            object.setProgress(value);
        }
        ColorUtil.sendResult(sender, "Successfully updated progress");
    }

    @CommandKamkeelBase.SubCommand(desc="Find quest id number by its name", usage="<questName>")
    public void id(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide a name for the quest");
            return;
        }
        String catName = String.join((CharSequence)" ", args).toLowerCase();
        Collection<Quest> quests = QuestController.Instance.quests.values();
        int count = 0;
        for (Quest quest : quests) {
            if (!quest.getName().toLowerCase().contains(catName)) continue;
            ColorUtil.sendResult(sender, String.format("Quest \u00a7e%d\u00a77 - \u00a7c'%s'", quest.id, quest.getName()));
            ++count;
        }
        if (count == 0) {
            ColorUtil.sendResult(sender, String.format("No Quest found with name: \u00a7c'%s'", catName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Find prerequisite quests for an id", usage="<questId>")
    public void prereq(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide an id for the quest");
            return;
        }
        try {
            questid = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID");
            return;
        }
        Collection<Quest> quests = QuestController.Instance.quests.values();
        ColorUtil.sendResult(sender, "Prerequisites:");
        ColorUtil.sendResult(sender, "--------------------");
        boolean foundOne = false;
        for (Quest allQuest : quests) {
            if (allQuest.nextQuestid != questid) continue;
            foundOne = true;
            ColorUtil.sendResult(sender, String.format("Quest %d: \u00a7a'%s'", allQuest.id, allQuest.title));
        }
        if (!foundOne) {
            ColorUtil.sendResult(sender, String.format("No Prerequisites found for Quest \u00a7c%d", questid));
        }
        ColorUtil.sendResult(sender, "--------------------");
    }

    @CommandKamkeelBase.SubCommand(desc="Quick info on a quest", usage="<questId>")
    public void info(ICommandSender sender, String[] args) throws CommandException {
        int questid;
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide an id for the quest");
            return;
        }
        try {
            questid = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestID must be an integer: " + args[1]);
            return;
        }
        Quest quest = QuestController.Instance.quests.get(questid);
        if (quest == null) {
            ColorUtil.sendError(sender, "Unknown QuestID");
            return;
        }
        ColorUtil.sendResult(sender, "--------------------");
        ColorUtil.sendResult(sender, String.format("\u00a7e%d\u00a77: \u00a7a%s", quest.id, quest.title));
        ColorUtil.sendResult(sender, String.format("Category: \u00a7b%s", quest.category.getName()));
        ColorUtil.sendResult(sender, String.format("Type: \u00a76%s", quest.type.toString()));
        ColorUtil.sendResult(sender, String.format("Repeatable: \u00a76%s", quest.repeat.toString()));
        ColorUtil.sendResult(sender, String.format("Complete: \u00a76%s", quest.completion.toString()));
        if (quest.completion == EnumQuestCompletion.Npc) {
            ColorUtil.sendResult(sender, String.format("NPC: \u00a76%s", quest.completerNpc));
        }
        if (quest.nextQuestid != -1) {
            ColorUtil.sendResult(sender, String.format("Next Quest ID: \u00a7c%d", quest.nextQuestid));
        }
        ColorUtil.sendResult(sender, "--------------------");
    }

    @CommandKamkeelBase.SubCommand(desc="reload quests from disk", permission=4)
    public void reload(ICommandSender sender, String[] args) {
        new QuestController().load();
        SyncController.syncAllQuests();
        ColorUtil.sendResult(sender, "Quests Reloaded");
    }
}

