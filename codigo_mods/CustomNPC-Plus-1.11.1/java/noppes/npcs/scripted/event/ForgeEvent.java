/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraftforge.event.entity.EntityEvent
 *  net.minecraftforge.event.world.WorldEvent
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.IForgeEvent;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.CustomNPCsEvent;

@Cancelable
public class ForgeEvent
extends CustomNPCsEvent
implements IForgeEvent {
    public final Event event;

    public ForgeEvent(Event event) {
        this.event = event;
    }

    @Override
    public Event getEvent() {
        return this.event;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.FORGE_EVENT.function;
    }

    public static class InitEvent
    extends ForgeEvent
    implements IForgeEvent.InitEvent {
        public InitEvent() {
            super(null);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FORGE_INIT.function;
        }
    }

    @Cancelable
    public static class EntityEvent
    extends ForgeEvent
    implements IForgeEvent.EntityEvent {
        public final IEntity entity;

        public EntityEvent(net.minecraftforge.event.entity.EntityEvent event, IEntity entity) {
            super((Event)event);
            this.entity = entity;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FORGE_ENTITY.function;
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }
    }

    @Cancelable
    public static class WorldEvent
    extends ForgeEvent
    implements IForgeEvent.WorldEvent {
        public final IWorld world;

        public WorldEvent(net.minecraftforge.event.world.WorldEvent event, IWorld world) {
            super((Event)event);
            this.world = world;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FORGE_WORLD.function;
        }

        @Override
        public IWorld getWorld() {
            return this.world;
        }
    }
}

