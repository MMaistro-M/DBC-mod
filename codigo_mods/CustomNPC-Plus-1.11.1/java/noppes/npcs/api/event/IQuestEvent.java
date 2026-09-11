/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.item.IItemStack;

public interface IQuestEvent
extends IPlayerEvent {
    public IQuest getQuest();

    @Cancelable
    public static interface QuestTurnedInEvent
    extends IQuestEvent {
        public void setExpReward(int var1);

        public void setItemRewards(IItemStack[] var1);

        public int getExpReward();

        public IItemStack[] getItemRewards();
    }

    @Cancelable
    public static interface QuestStartEvent
    extends IQuestEvent {
    }

    public static interface QuestCompletedEvent
    extends IQuestEvent {
    }
}

