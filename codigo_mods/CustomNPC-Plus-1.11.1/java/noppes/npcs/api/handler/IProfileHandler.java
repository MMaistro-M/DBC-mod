/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerData;
import noppes.npcs.api.handler.data.IProfile;

public interface IProfileHandler {
    public IProfile getProfile(IPlayer var1);

    public boolean changeSlot(IPlayer var1, int var2);

    public boolean hasSlot(IPlayer var1, int var2);

    public boolean removeSlot(IPlayer var1, int var2);

    public IPlayerData getSlotPlayerData(IPlayer var1, int var2);

    public void saveSlotData(IPlayer var1);
}

