/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.command;

import java.util.Collection;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumProfileSync;
import noppes.npcs.constants.EnumQuestRepeat;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.controllers.data.QuestData;

public class QuestCategoryCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "questcat";
    }

    @Override
    public String getDescription() {
        return "Quest Category operations";
    }

    @CommandKamkeelBase.SubCommand(desc="Find quest category id number by its name", usage="<quest cat name>")
    public void id(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide a name for the quest category");
            return;
        }
        String catName = String.join((CharSequence)" ", args).toLowerCase();
        Collection<QuestCategory> questCats = QuestController.Instance.categories.values();
        int count = 0;
        for (QuestCategory cat : questCats) {
            if (!cat.getName().toLowerCase().contains(catName)) continue;
            ColorUtil.sendResult(sender, String.format("Quest Cat \u00a7e%d\u00a77 - \u00a7c'%s'", cat.id, cat.getName()));
            ++count;
        }
        if (count == 0) {
            ColorUtil.sendResult(sender, String.format("No Quest Cat found with name: \u00a7c'%s'", catName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Finish a quest category for a player", usage="<player> <questcatid>")
    public void finish(ICommandSender sender, String[] args) throws CommandException {
        int questcatid;
        String playername = args[0];
        try {
            questcatid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestCatID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        QuestCategory questCategory = QuestController.Instance.categories.get(questcatid);
        if (questCategory == null) {
            ColorUtil.sendError(sender, "Unknown QuestCatID: " + questcatid);
            return;
        }
        int count = 0;
        for (PlayerData playerdata : data) {
            count = 0;
            for (Quest quest : questCategory.quests.values()) {
                long completeTime;
                playerdata.questData.activeQuests.remove(quest.id);
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
                if (playerdata.player != null) {
                    AchievementPacket.sendAchievement((EntityPlayerMP)playerdata.player, false, "quest.completed", quest.title);
                    ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerdata.player, "quest.completed", ": ", quest.title);
                }
                ++count;
            }
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Completed Quest Cat \u00a7c'%s' \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questCategory.getName(), questcatid, playerdata.playername));
            ColorUtil.sendResult(sender, String.format("Completed a total of \u00a7b%d \u00a77quests", count));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Stop a quest category for a players active quests", usage="<player> <questcatid>")
    public void stop(ICommandSender sender, String[] args) throws CommandException {
        int questcatid;
        String playername = args[0];
        try {
            questcatid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestCatID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        QuestCategory questCategory = QuestController.Instance.categories.get(questcatid);
        if (questCategory == null) {
            ColorUtil.sendError(sender, "Unknown QuestCatID: " + questcatid);
            return;
        }
        int count = 0;
        for (PlayerData playerdata : data) {
            for (Quest quest : questCategory.quests.values()) {
                if (!playerdata.questData.activeQuests.containsKey(quest.id)) continue;
                playerdata.questData.activeQuests.remove(quest.id);
                ++count;
            }
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Stopped Quest Cat \u00a7c'%s' \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questCategory.getName(), questcatid, playerdata.playername));
            ColorUtil.sendResult(sender, String.format("Stopped a total of \u00a7b%d \u00a77quests", count));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Start a quest category for a player", usage="<player> <questcatid>")
    public void start(ICommandSender sender, String[] args) throws CommandException {
        int questcatid;
        String playername = args[0];
        try {
            questcatid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestCatID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        QuestCategory questCategory = QuestController.Instance.categories.get(questcatid);
        if (questCategory == null) {
            ColorUtil.sendError(sender, "Unknown QuestCatID: " + questcatid);
            return;
        }
        int count = 0;
        for (PlayerData playerdata : data) {
            for (Quest quest : questCategory.quests.values()) {
                if (playerdata.questData.activeQuests.containsKey(quest.id)) continue;
                QuestData questData = new QuestData(quest);
                playerdata.questData.activeQuests.put(quest.id, questData);
                ++count;
                if (playerdata.player == null || !questData.sendAlerts) continue;
                AchievementPacket.sendAchievement((EntityPlayerMP)playerdata.player, false, "quest.newquest", quest.title);
                ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerdata.player, "quest.newquest", ": ", quest.title);
            }
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Started Quest Cat \u00a7c'%s' \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questCategory.getName(), questcatid, playerdata.playername));
            ColorUtil.sendResult(sender, String.format("Started a total of \u00a7b%d \u00a77quests", count));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Remove a quest cat from active/finished", usage="<player> <questcatid>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        int questcatid;
        String playername = args[0];
        try {
            questcatid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "QuestCatID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, String.format("Unknown player '%s'", playername));
            return;
        }
        QuestCategory questCategory = QuestController.Instance.categories.get(questcatid);
        if (questCategory == null) {
            ColorUtil.sendError(sender, "Unknown QuestCatID: " + questcatid);
            return;
        }
        int count = 0;
        for (PlayerData playerdata : data) {
            for (Quest quest : questCategory.quests.values()) {
                playerdata.questData.activeQuests.remove(quest.id);
                playerdata.questData.finishedQuests.remove(quest.id);
                ++count;
            }
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Cleared Quest Cat \u00a7c'%s' \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", questCategory.getName(), questcatid, playerdata.playername));
            ColorUtil.sendResult(sender, String.format("Cleared a total of \u00a7b%d \u00a77quests", count));
        }
    }
}

