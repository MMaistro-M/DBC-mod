/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IBlockEvent;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class BlockEvent
extends CustomNPCsEvent
implements IBlockEvent {
    public IBlock block;

    public BlockEvent(IBlock block) {
        this.block = block;
    }

    @Override
    public IBlock getBlock() {
        return this.block;
    }

    public static class TimerEvent
    extends BlockEvent
    implements IBlockEvent.TimerEvent {
        public final int id;

        public TimerEvent(IBlock block, int id) {
            super(block);
            this.id = id;
        }

        @Override
        public int getId() {
            return this.id;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TIMER.function;
        }
    }

    public static class CollidedEvent
    extends BlockEvent
    implements IBlockEvent.CollidedEvent {
        public final IEntity entity;

        public CollidedEvent(IBlock block, Entity entity) {
            super(block);
            this.entity = NpcAPI.Instance().getIEntity(entity);
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.COLLIDE.function;
        }
    }

    @Cancelable
    public static class HarvestedEvent
    extends BlockEvent
    implements IBlockEvent.HarvestedEvent {
        public final IPlayer player;

        public HarvestedEvent(IBlock block, EntityPlayer player) {
            super(block);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.HARVESTED.function;
        }
    }

    public static class ClickedEvent
    extends BlockEvent
    implements IBlockEvent.ClickedEvent {
        public final IPlayer player;

        public ClickedEvent(IBlock block, EntityPlayer player) {
            super(block);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CLICKED.function;
        }
    }

    public static class UpdateEvent
    extends BlockEvent
    implements IBlockEvent.UpdateEvent {
        public UpdateEvent(IBlock block) {
            super(block);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TICK.function;
        }
    }

    public static class InitEvent
    extends BlockEvent
    implements IBlockEvent.InitEvent {
        public InitEvent(IBlock block) {
            super(block);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INIT.function;
        }
    }

    public static class NeighborChangedEvent
    extends BlockEvent
    implements IBlockEvent.NeighborChangedEvent {
        public final IPos changedPos;

        public NeighborChangedEvent(IBlock block, IPos changedPos) {
            super(block);
            this.changedPos = changedPos;
        }

        @Override
        public IPos getChangedPos() {
            return this.changedPos;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.NEIGHBOR_CHANGED.function;
        }
    }

    public static class RainFillEvent
    extends BlockEvent
    implements IBlockEvent.RainFillEvent {
        public RainFillEvent(IBlock block) {
            super(block);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RAIN_FILLED.function;
        }
    }

    @Cancelable
    public static class ExplodedEvent
    extends BlockEvent
    implements IBlockEvent.ExplodedEvent {
        public ExplodedEvent(IBlock block) {
            super(block);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.EXPLODED.function;
        }
    }

    public static class BreakEvent
    extends BlockEvent
    implements IBlockEvent.BreakEvent {
        public BreakEvent(IBlock block) {
            super(block);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.BROKEN.function;
        }
    }

    public static class RedstoneEvent
    extends BlockEvent
    implements IBlockEvent.RedstoneEvent {
        public final int prevPower;
        public final int power;

        public RedstoneEvent(IBlock block, int prevPower, int power) {
            super(block);
            this.power = power;
            this.prevPower = prevPower;
        }

        @Override
        public int getPrevPower() {
            return this.prevPower;
        }

        @Override
        public int getPower() {
            return this.power;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.REDSTONE.function;
        }
    }

    @Cancelable
    public static class InteractEvent
    extends BlockEvent
    implements IBlockEvent.InteractEvent {
        public final IPlayer player;
        public final float hitX;
        public final float hitY;
        public final float hitZ;
        public final int side;

        public InteractEvent(IBlock block, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
            super(block);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
            this.hitX = hitX;
            this.hitY = hitY;
            this.hitZ = hitZ;
            this.side = side;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public float getHitX() {
            return this.hitX;
        }

        @Override
        public float getHitY() {
            return this.hitY;
        }

        @Override
        public float getHitZ() {
            return this.hitZ;
        }

        @Override
        public int getSide() {
            return this.side;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INTERACT.function;
        }
    }

    @Cancelable
    public static class EntityFallenUponEvent
    extends BlockEvent
    implements IBlockEvent.EntityFallenUponEvent {
        public final IEntity entity;
        public float distanceFallen;

        public EntityFallenUponEvent(IBlock block, Entity entity, float distance) {
            super(block);
            this.distanceFallen = distance;
            this.entity = NpcAPI.Instance().getIEntity(entity);
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }

        @Override
        public float getDistanceFallen() {
            return this.distanceFallen;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FALLEN_UPON.function;
        }
    }
}

