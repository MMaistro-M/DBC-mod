/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomNPCsEvent;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.item.IItemStack;

public interface INpcEvent
extends ICustomNPCsEvent {
    public ICustomNpc getNpc();

    public static interface InitEvent
    extends INpcEvent {
    }

    public static interface UpdateEvent
    extends INpcEvent {
    }

    @Cancelable
    public static interface TargetEvent
    extends INpcEvent {
        public void setTarget(IEntityLivingBase var1);

        public IEntityLivingBase getTarget();
    }

    @Cancelable
    public static interface TargetLostEvent
    extends INpcEvent {
        public IEntityLivingBase getTarget();

        public IEntityLivingBase getNewTarget();
    }

    public static interface DialogClosedEvent
    extends INpcEvent {
        public IPlayer getPlayer();

        public IDialog getDialog();

        public int getDialogId();

        public int getOptionId();
    }

    @Cancelable
    public static interface DialogEvent
    extends INpcEvent {
        public IPlayer getPlayer();

        public IDialog getDialog();

        public int getDialogId();

        public int getOptionId();
    }

    @Cancelable
    public static interface InteractEvent
    extends INpcEvent {
        public IPlayer getPlayer();
    }

    @Cancelable
    public static interface DiedEvent
    extends INpcEvent {
        public IEntity getSource();

        public IDamageSource getDamageSource();

        public String getType();

        public void setDroppedItems(IItemStack[] var1);

        public IItemStack[] getDroppedItems();

        public void setExpDropped(int var1);

        public int getExpDropped();
    }

    public static interface KilledEntityEvent {
        public IEntityLivingBase getEntity();
    }

    public static interface SwingEvent
    extends INpcEvent {
        public IItemStack getItemStack();
    }

    @Cancelable
    public static interface MeleeAttackEvent
    extends INpcEvent {
        public IEntityLivingBase getTarget();

        public void setDamage(float var1);

        public float getDamage();
    }

    @Cancelable
    public static interface RangedLaunchedEvent
    extends INpcEvent {
        public IEntityLivingBase getTarget();

        public void setDamage(float var1);

        public float getDamage();
    }

    @Cancelable
    public static interface DamagedEvent
    extends INpcEvent {
        public IEntity getSource();

        public IDamageSource getDamageSource();

        public float getDamage();

        public void setDamage(float var1);

        public void setClearTarget(boolean var1);

        public boolean getClearTarget();

        public String getType();
    }

    public static interface CollideEvent
    extends INpcEvent {
        public IEntity getEntity();
    }

    public static interface TimerEvent
    extends INpcEvent {
        public int getId();
    }
}

