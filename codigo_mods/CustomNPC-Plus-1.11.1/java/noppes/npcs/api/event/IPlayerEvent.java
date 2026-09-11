/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomNPCsEvent;
import noppes.npcs.api.handler.data.IPlayerEffect;
import noppes.npcs.api.handler.data.IProfile;
import noppes.npcs.api.item.IItemStack;

public interface IPlayerEvent
extends ICustomNPCsEvent {
    public IPlayer getPlayer();

    @Cancelable
    public static interface ProfileEvent
    extends IPlayerEvent {
        public IProfile getProfile();

        public int getSlot();

        public boolean isPost();

        @Cancelable
        public static interface Removed
        extends ProfileEvent {
        }

        @Cancelable
        public static interface Create
        extends ProfileEvent {
        }

        @Cancelable
        public static interface Changed
        extends ProfileEvent {
            public int getPrevSlot();
        }
    }

    public static interface EffectEvent
    extends IPlayerEvent {
        public IPlayerEffect getEffect();

        public static interface Removed
        extends EffectEvent {
            public boolean hasTimerRunOut();

            public boolean causedByDeath();
        }

        public static interface Ticked
        extends EffectEvent {
        }

        public static interface Added
        extends EffectEvent {
        }
    }

    public static interface RangedChargeEvent
    extends IPlayerEvent {
    }

    public static interface BonemealEvent
    extends IPlayerEvent {
        public IBlock getBlock();

        public int getX();

        public int getY();

        public int getZ();
    }

    public static interface FillBucketEvent
    extends IPlayerEvent {
        public IItemStack getCurrent();

        public IItemStack getFilled();
    }

    public static interface AchievementEvent
    extends IPlayerEvent {
        public String getDescription();
    }

    public static interface SleepEvent
    extends IPlayerEvent {
        public int getX();

        public int getY();

        public int getZ();
    }

    public static interface WakeUpEvent
    extends IPlayerEvent {
        public boolean setSpawn();
    }

    public static interface UseHoeEvent
    extends IPlayerEvent {
        public IItemStack getHoe();

        public int getX();

        public int getY();

        public int getZ();
    }

    @Cancelable
    public static interface BreakEvent
    extends IPlayerEvent {
        public IBlock getBlock();

        public int getExp();
    }

    public static interface FinishUsingItem
    extends IPlayerEvent {
        public IItemStack getItem();

        public int getDuration();
    }

    public static interface StopUsingItem
    extends IPlayerEvent {
        public IItemStack getItem();

        public int getDuration();
    }

    public static interface UsingItem
    extends IPlayerEvent {
        public IItemStack getItem();

        public int getDuration();
    }

    public static interface StartUsingItem
    extends IPlayerEvent {
        public IItemStack getItem();

        public int getDuration();
    }

    public static interface InitEvent
    extends IPlayerEvent {
    }

    public static interface UpdateEvent
    extends IPlayerEvent {
    }

    @Cancelable
    public static interface RightClickEvent
    extends IPlayerEvent {
        public int getType();

        public Object getTarget();

        @Override
        public IPlayer getPlayer();
    }

    @Cancelable
    public static interface InteractEvent
    extends IPlayerEvent {
        public int getType();

        public IEntity getTarget();
    }

    @Cancelable
    public static interface TossEvent
    extends IPlayerEvent {
        public IItemStack getItem();
    }

    @Cancelable
    public static interface DropEvent
    extends IPlayerEvent {
        public IItemStack[] getItems();
    }

    @Cancelable
    public static interface PickUpEvent
    extends IPlayerEvent {
        public IItemStack getItem();
    }

    public static interface ContainerOpen
    extends IPlayerEvent {
        public IContainer getContainer();
    }

    public static interface ContainerClosed
    extends IPlayerEvent {
        public IContainer getContainer();
    }

    @Cancelable
    public static interface DamagedEntityEvent
    extends IPlayerEvent {
        public IDamageSource getDamageSource();

        public IEntity getTarget();

        public float getDamage();
    }

    @Cancelable
    public static interface AttackEvent
    extends IPlayerEvent {
        public IDamageSource getDamageSource();

        public IEntity getTarget();

        public float getDamage();
    }

    @Cancelable
    public static interface RangedLaunchedEvent
    extends IPlayerEvent {
        public IItemStack getBow();

        public int getCharge();
    }

    public static interface DiedEvent
    extends IPlayerEvent {
        public IDamageSource getDamageSource();

        public String getType();

        public IEntity getSource();
    }

    public static interface KilledEntityEvent
    extends IPlayerEvent {
        public IEntityLivingBase getEntity();
    }

    public static interface JumpEvent
    extends IPlayerEvent {
    }

    @Cancelable
    public static interface FallEvent
    extends IPlayerEvent {
        public float getDistance();
    }

    @Cancelable
    public static interface SoundEvent
    extends IPlayerEvent {
        public String getName();

        public float getPitch();

        public float getVolume();
    }

    @Cancelable
    public static interface LightningEvent
    extends IPlayerEvent {
    }

    @Cancelable
    public static interface DamagedEvent
    extends IPlayerEvent {
        public IDamageSource getDamageSource();

        public IEntity getSource();

        public float getDamage();
    }

    @Cancelable
    public static interface AttackedEvent
    extends IPlayerEvent {
        public IDamageSource getDamageSource();

        public IEntity getSource();

        public float getDamage();
    }

    public static interface TimerEvent
    extends IPlayerEvent {
        public int getId();
    }

    public static interface ChangedDimension
    extends IPlayerEvent {
        public int getFromDim();

        public int getToDim();
    }

    public static interface RespawnEvent
    extends IPlayerEvent {
    }

    public static interface LoginEvent
    extends IPlayerEvent {
    }

    public static interface LogoutEvent
    extends IPlayerEvent {
    }

    public static interface LevelUpEvent
    extends IPlayerEvent {
        public int getChange();
    }

    public static interface PickupXPEvent
    extends IPlayerEvent {
        public int getAmount();
    }

    public static interface MouseClickedEvent
    extends IPlayerEvent {
        public int getButton();

        public int getMouseWheel();

        public boolean buttonDown();

        public boolean isCtrlPressed();

        public boolean isAltPressed();

        public boolean isShiftPressed();

        public boolean isMetaPressed();

        public int[] getKeysDown();
    }

    public static interface KeyPressedEvent
    extends IPlayerEvent {
        public int getKey();

        public boolean isCtrlPressed();

        public boolean isAltPressed();

        public boolean isShiftPressed();

        public boolean isMetaPressed();

        public boolean keyDown();

        public int[] getKeysDown();
    }

    @Cancelable
    public static interface ChatEvent
    extends IPlayerEvent {
        public void setMessage(String var1);

        public String getMessage();
    }
}

