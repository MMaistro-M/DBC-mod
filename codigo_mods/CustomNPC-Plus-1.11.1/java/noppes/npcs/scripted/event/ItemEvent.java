/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IItemEvent;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class ItemEvent
extends CustomNPCsEvent
implements IItemEvent {
    public final IItemCustomizable item;

    public ItemEvent(IItemCustomizable item) {
        this.item = item;
    }

    @Override
    public IItemCustomizable getItem() {
        return this.item;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.CUSTOM_ITEM_EVENT.function;
    }

    public static class RepairItem
    extends ItemEvent
    implements IItemEvent.RepairItem {
        public final IPlayer player;
        public final IItemStack left;
        public final IItemStack right;
        public final float anvilBreakChance;

        public RepairItem(IItemCustomizable item, IPlayer player, IItemStack left, IItemStack right, float anvilBreakChance) {
            super(item);
            this.player = player;
            this.left = left;
            this.right = right;
            this.anvilBreakChance = anvilBreakChance;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.REPAIR_ITEM.function;
        }

        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public IItemStack getLeft() {
            return this.left;
        }

        @Override
        public IItemStack getRight() {
            return this.right;
        }

        @Override
        public IItemStack getOutput() {
            return this.item;
        }

        @Override
        public float getAnvilBreakChance() {
            return this.anvilBreakChance;
        }
    }

    public static class BreakItem
    extends ItemEvent
    implements IItemEvent.BreakItem {
        public final IPlayer player;

        public BreakItem(IItemCustomizable item, IPlayer player) {
            super(item);
            this.player = player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.BREAK_ITEM.function;
        }

        @Override
        public IItemStack getBrokenStack() {
            return this.item;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    @Cancelable
    public static class FinishUsingItem
    extends ItemEvent
    implements IItemEvent.FinishUsingItem {
        public final IPlayer player;
        public final int duration;

        public FinishUsingItem(IItemCustomizable item, IPlayer player, int duration) {
            super(item);
            this.player = player;
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FINISH_USING_ITEM.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class StopUsingItem
    extends ItemEvent
    implements IItemEvent.StopUsingItem {
        public final IPlayer player;
        public final int duration;

        public StopUsingItem(IItemCustomizable item, IPlayer player, int duration) {
            super(item);
            this.player = player;
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.STOP_USING_ITEM.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class UsingItem
    extends ItemEvent
    implements IItemEvent.UsingItem {
        public final IPlayer player;
        public final int duration;

        public UsingItem(IItemCustomizable item, IPlayer player, int duration) {
            super(item);
            this.player = player;
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.USING_ITEM.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class StartUsingItem
    extends ItemEvent
    implements IItemEvent.StartUsingItem {
        public final IPlayer player;
        public final int duration;

        public StartUsingItem(IItemCustomizable item, IPlayer player, int duration) {
            super(item);
            this.player = player;
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.START_USING_ITEM.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class AttackEvent
    extends ItemEvent
    implements IItemEvent.AttackEvent {
        public final int type;
        public final IEntity target;
        public final IEntity swingingEntity;

        public AttackEvent(IItemCustomizable item, IEntity swingingEntity, int type, IEntity target) {
            super(item);
            this.type = type;
            this.target = target;
            this.swingingEntity = swingingEntity;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ATTACK.function;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public IEntity getTarget() {
            return this.target;
        }

        @Override
        public IEntity getSwingingEntity() {
            return this.swingingEntity;
        }
    }

    @Cancelable
    public static class RightClickEvent
    extends ItemEvent
    implements IItemEvent.RightClickEvent {
        public final int type;
        public final Object target;
        public final IPlayer player;

        public RightClickEvent(IItemCustomizable item, IPlayer player, int type, Object target) {
            super(item);
            this.type = type;
            this.target = target;
            this.player = player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RIGHT_CLICK.function;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public Object getTarget() {
            return this.target;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    @Cancelable
    public static class InteractEvent
    extends ItemEvent
    implements IItemEvent.InteractEvent {
        public final int type;
        public final IEntity target;
        public final IPlayer player;

        public InteractEvent(IItemCustomizable item, IPlayer player, int type, IEntity target) {
            super(item);
            this.type = type;
            this.target = target;
            this.player = player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INTERACT.function;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public IEntity getTarget() {
            return this.target;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    @Cancelable
    public static class SpawnEvent
    extends ItemEvent
    implements IItemEvent.SpawnEvent {
        public final IEntity entity;

        public SpawnEvent(IItemCustomizable item, IEntity entity) {
            super(item);
            this.entity = entity;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.SPAWN.function;
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }
    }

    public static class PickedUpEvent
    extends ItemEvent
    implements IItemEvent.PickedUpEvent {
        public final IPlayer player;

        public PickedUpEvent(IItemCustomizable item, IPlayer player) {
            super(item);
            this.player = player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.PICKEDUP.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    @Cancelable
    public static class TossedEvent
    extends ItemEvent
    implements IItemEvent.TossedEvent {
        public final IEntity entity;
        public final IPlayer player;

        public TossedEvent(IItemCustomizable item, IPlayer player, IEntity entity) {
            super(item);
            this.entity = entity;
            this.player = player;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TOSSED.function;
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    public static class UpdateEvent
    extends ItemEvent
    implements IItemEvent.UpdateEvent {
        public final IEntity entity;

        public UpdateEvent(IItemCustomizable item, IEntity entity) {
            super(item);
            this.entity = entity;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TICK.function;
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }
    }

    public static class InitEvent
    extends ItemEvent
    implements IItemEvent.InitEvent {
        public InitEvent(IItemCustomizable item) {
            super(item);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INIT.function;
        }
    }
}

