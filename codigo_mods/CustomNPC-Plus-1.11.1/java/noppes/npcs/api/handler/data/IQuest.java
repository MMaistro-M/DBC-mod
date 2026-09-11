/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.IContainer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IPartyOptions;
import noppes.npcs.api.handler.data.IProfileOptions;
import noppes.npcs.api.handler.data.IQuestCategory;
import noppes.npcs.api.handler.data.IQuestInterface;
import noppes.npcs.api.handler.data.IQuestObjective;

public interface IQuest {
    public int getId();

    public String getName();

    public void setName(String var1);

    public int getType();

    public void setType(int var1);

    public String getLogText();

    public void setLogText(String var1);

    public String getCompleteText();

    public void setCompleteText(String var1);

    public IQuest getNextQuest();

    public void setNextQuest(IQuest var1);

    public IQuestObjective[] getObjectives(IPlayer var1);

    public IQuestCategory getCategory();

    public IContainer getRewards();

    public String getNpcName();

    public void setNpcName(String var1);

    public void save();

    public boolean getIsRepeatable();

    public long getTimeUntilRepeat(IPlayer var1);

    public void setRepeatType(int var1);

    public int getRepeatType();

    public IQuestInterface getQuestInterface();

    public IPartyOptions getPartyOptions();

    public IProfileOptions getProfileOptions();

    public long getCustomCooldown();

    public void setCustomCooldown(long var1);
}

