/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IParty;
import noppes.npcs.api.handler.data.IQuest;

public interface IPartyEvent {
    public IParty getParty();

    public IQuest getQuest();

    public static interface PartyDisbandEvent
    extends IPartyEvent {
    }

    public static interface PartyLeaveEvent
    extends IPartyEvent {
        public IPlayer getPlayer();

        public String getPlayerName();
    }

    @Cancelable
    public static interface PartyKickEvent
    extends IPartyEvent {
        public IPlayer getPlayer();

        public String getPlayerName();
    }

    @Cancelable
    public static interface PartyInviteEvent
    extends IPartyEvent {
        public IPlayer getPlayer();

        public String getPlayerName();
    }

    @Cancelable
    public static interface PartyQuestTurnedInEvent
    extends IPartyEvent {
    }

    @Cancelable
    public static interface PartyQuestSetEvent
    extends IPartyEvent {
    }

    public static interface PartyQuestCompletedEvent
    extends IPartyEvent {
    }
}

