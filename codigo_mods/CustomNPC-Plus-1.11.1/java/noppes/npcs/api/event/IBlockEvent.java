/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IBlockEvent
extends ICustomNPCsEvent {
    public IBlock getBlock();

    public static interface TimerEvent
    extends IBlockEvent {
        public int getId();
    }

    public static interface CollidedEvent
    extends IBlockEvent {
        public IEntity getEntity();
    }

    @Cancelable
    public static interface HarvestedEvent
    extends IBlockEvent {
        public IPlayer getPlayer();
    }

    public static interface ClickedEvent
    extends IBlockEvent {
        public IPlayer getPlayer();
    }

    public static interface UpdateEvent
    extends IBlockEvent {
    }

    public static interface InitEvent
    extends IBlockEvent {
    }

    public static interface NeighborChangedEvent
    extends IBlockEvent {
        public IPos getChangedPos();
    }

    public static interface RainFillEvent
    extends IBlockEvent {
    }

    @Cancelable
    public static interface ExplodedEvent
    extends IBlockEvent {
    }

    public static interface BreakEvent
    extends IBlockEvent {
    }

    public static interface RedstoneEvent
    extends IBlockEvent {
        public int getPrevPower();

        public int getPower();
    }

    @Cancelable
    public static interface InteractEvent
    extends IBlockEvent {
        public IPlayer getPlayer();

        public float getHitX();

        public float getHitY();

        public float getHitZ();

        public int getSide();
    }

    @Cancelable
    public static interface EntityFallenUponEvent
    extends IBlockEvent {
        public IEntity getEntity();

        public float getDistanceFallen();
    }
}

