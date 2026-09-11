/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IForgeEvent
extends ICustomNPCsEvent {
    public Event getEvent();

    public static interface InitEvent
    extends IForgeEvent {
    }

    public static interface EntityEvent
    extends IForgeEvent {
        public IEntity getEntity();
    }

    public static interface WorldEvent
    extends IForgeEvent {
        public IWorld getWorld();
    }
}

