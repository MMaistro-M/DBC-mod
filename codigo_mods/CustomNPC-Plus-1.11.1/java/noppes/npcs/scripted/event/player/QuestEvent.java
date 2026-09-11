/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IQuestEvent;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class QuestEvent
extends PlayerEvent
implements IQuestEvent {
    public final IQuest quest;

    public QuestEvent(IPlayer player, IQuest quest) {
        super(player);
        this.quest = quest;
    }

    @Override
    public IQuest getQuest() {
        return this.quest;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.QUEST_EVENT.function;
    }

    @Cancelable
    public static class QuestTurnedInEvent
    extends QuestEvent
    implements IQuestEvent.QuestTurnedInEvent {
        public int expReward = 0;
        public IItemStack[] itemRewards = new IItemStack[0];

        public QuestTurnedInEvent(IPlayer player, IQuest quest) {
            super(player, quest);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.QUEST_TURNIN.function;
        }

        @Override
        public void setExpReward(int expReward) {
            this.expReward = expReward;
        }

        @Override
        public void setItemRewards(IItemStack[] itemRewards) {
            this.itemRewards = itemRewards;
        }

        @Override
        public int getExpReward() {
            return this.expReward;
        }

        @Override
        public IItemStack[] getItemRewards() {
            return this.itemRewards;
        }
    }

    @Cancelable
    public static class QuestStartEvent
    extends QuestEvent
    implements IQuestEvent.QuestStartEvent {
        public QuestStartEvent(IPlayer player, IQuest quest) {
            super(player, quest);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.QUEST_START.function;
        }
    }

    public static class QuestCompletedEvent
    extends QuestEvent
    implements IQuestEvent.QuestCompletedEvent {
        public QuestCompletedEvent(IPlayer player, IQuest quest) {
            super(player, quest);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.QUEST_COMPLETED.function;
        }
    }
}

