/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomNPCsEvent;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;

public interface IItemEvent
extends ICustomNPCsEvent {
    public IItemCustomizable getItem();

    public static interface RepairItem
    extends IItemEvent {
        public IItemStack getLeft();

        public IItemStack getRight();

        public IItemStack getOutput();

        public float getAnvilBreakChance();
    }

    public static interface BreakItem
    extends IItemEvent {
        public IItemStack getBrokenStack();

        public IPlayer getPlayer();
    }

    public static interface FinishUsingItem
    extends IItemEvent {
        public IPlayer getPlayer();

        public int getDuration();
    }

    public static interface StopUsingItem
    extends IItemEvent {
        public IPlayer getPlayer();

        public int getDuration();
    }

    public static interface UsingItem
    extends IItemEvent {
        public IPlayer getPlayer();

        public int getDuration();
    }

    public static interface StartUsingItem
    extends IItemEvent {
        public IPlayer getPlayer();

        public int getDuration();
    }

    @Cancelable
    public static interface AttackEvent
    extends IItemEvent {
        public int getType();

        public IEntity getTarget();

        public IEntity getSwingingEntity();
    }

    @Cancelable
    public static interface RightClickEvent
    extends IItemEvent {
        public int getType();

        public Object getTarget();

        public IPlayer getPlayer();
    }

    @Cancelable
    public static interface InteractEvent
    extends IItemEvent {
        public int getType();

        public IEntity getTarget();

        public IPlayer getPlayer();
    }

    @Cancelable
    public static interface SpawnEvent
    extends IItemEvent {
        public IEntity getEntity();
    }

    public static interface PickedUpEvent
    extends IItemEvent {
        public IPlayer getPlayer();
    }

    @Cancelable
    public static interface TossedEvent
    extends IItemEvent {
        public IEntity getEntity();

        public IPlayer getPlayer();
    }

    public static interface UpdateEvent
    extends IItemEvent {
        public IEntity getEntity();
    }

    public static interface InitEvent
    extends IItemEvent {
    }
}

