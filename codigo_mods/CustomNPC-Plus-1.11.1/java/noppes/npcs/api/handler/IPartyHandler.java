/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IParty;

public interface IPartyHandler {
    public IParty createParty(IPlayer var1);

    public void disbandParty(IPlayer var1);
}

