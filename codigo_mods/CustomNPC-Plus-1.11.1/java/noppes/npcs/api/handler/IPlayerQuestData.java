/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.IQuest;

public interface IPlayerQuestData {
    public IQuest getTrackedQuest();

    public void startQuest(int var1);

    public void finishQuest(int var1);

    public void stopQuest(int var1);

    public void removeQuest(int var1);

    public boolean hasFinishedQuest(int var1);

    public boolean hasActiveQuest(int var1);

    public IQuest[] getActiveQuests();

    public IQuest[] getFinishedQuests();

    public long getLastCompletedTime(int var1);

    public void setLastCompletedTime(int var1, long var2);
}

