/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.handler.data.IFaction;

public interface IFactionEvent
extends IPlayerEvent {
    public IFaction getFaction();

    @Cancelable
    public static interface FactionPoints
    extends IFactionEvent {
        public boolean decreased();

        public int getPoints();
    }
}

