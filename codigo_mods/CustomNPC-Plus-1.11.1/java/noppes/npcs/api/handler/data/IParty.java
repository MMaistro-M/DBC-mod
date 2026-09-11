/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.List;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IQuest;

public interface IParty {
    public String getPartyUUIDString();

    public boolean getIsLocked();

    public void setQuest(IQuest var1);

    public IQuest getQuest();

    public int getCurrentQuestID();

    public String getCurrentQuestName();

    public boolean addPlayer(String var1);

    public boolean removePlayer(String var1);

    public boolean addPlayer(IPlayer var1);

    public boolean removePlayer(IPlayer var1);

    public boolean hasPlayer(IPlayer var1);

    public boolean hasPlayer(String var1);

    public String getPartyLeaderName();

    public List<String> getPlayerNamesList();

    public boolean validateQuest(int var1, boolean var2);

    public void toggleFriendlyFire();

    public boolean friendlyFire();

    public void updateQuestObjectiveData();

    public void updatePartyData();
}

