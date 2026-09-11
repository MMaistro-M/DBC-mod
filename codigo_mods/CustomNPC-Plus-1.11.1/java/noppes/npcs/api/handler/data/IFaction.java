/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;

public interface IFaction {
    public int getId();

    public String getName();

    public void setName(String var1);

    public void setDefaultPoints(int var1);

    public int getDefaultPoints();

    public void setFriendlyPoints(int var1);

    public int getFriendlyPoints();

    public void setNeutralPoints(int var1);

    public int getNeutralPoints();

    public void setColor(int var1);

    public int getColor();

    public int playerStatus(IPlayer var1);

    public boolean isAggressiveToNpc(ICustomNpc var1);

    public boolean getIsHidden();

    public void setIsHidden(boolean var1);

    public boolean isPassive();

    public void setIsPassive(boolean var1);

    public boolean attackedByMobs();

    public void setAttackedByMobs(boolean var1);

    public boolean isEnemyFaction(IFaction var1);

    public IFaction[] getEnemyFactions();

    public void addEnemyFaction(IFaction var1);

    public void removeEnemyFaction(IFaction var1);

    public void save();
}

