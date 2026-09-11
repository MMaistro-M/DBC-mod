/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IFactionEvent;
import noppes.npcs.api.handler.data.IFaction;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class FactionEvent
extends PlayerEvent
implements IFactionEvent {
    public final IFaction faction;

    public FactionEvent(IPlayer player, IFaction faction) {
        super(player);
        this.faction = faction;
    }

    @Override
    public IFaction getFaction() {
        return this.faction;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.FACTION_EVENT.function;
    }

    @Cancelable
    public static class FactionPoints
    extends FactionEvent
    implements IFactionEvent.FactionPoints {
        public boolean decrease;
        public int points;

        public FactionPoints(IPlayer player, IFaction faction, boolean decrease, int points) {
            super(player, faction);
            this.decrease = decrease;
            this.points = points;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FACTION_POINTS.function;
        }

        @Override
        public boolean decreased() {
            return this.decrease;
        }

        @Override
        public int getPoints() {
            return this.points;
        }
    }
}

