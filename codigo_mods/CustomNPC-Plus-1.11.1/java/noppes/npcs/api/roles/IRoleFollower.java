/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.roles;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.roles.IRole;

public interface IRoleFollower
extends IRole {
    public void setOwner(IPlayer var1);

    public IPlayer getOwner();

    public boolean hasOwner();

    public boolean isFollowing();

    public void setIsFollowing(boolean var1);

    public int getDaysLeft();

    public void addDaysLeft(int var1);

    public boolean getInfiniteDays();

    public void setInfiniteDays(boolean var1);

    public boolean getGuiDisabled();

    public void setGuiDisabled(boolean var1);

    public void setRate(int var1, int var2);

    public int getRate(int var1);

    public void setDialogHire(String var1);

    public String getDialogHire();

    public void setDialogFarewell(String var1);

    public String getDialogFarewell();
}

