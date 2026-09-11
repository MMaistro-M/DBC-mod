/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package noppes.npcs.scripted.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.handler.data.IPlayerEffect;
import noppes.npcs.api.handler.data.IProfile;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class PlayerEvent
extends CustomNPCsEvent
implements IPlayerEvent {
    public final IPlayer player;

    public PlayerEvent(IPlayer player) {
        this.player = player;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Cancelable
    public static class ProfileEvent
    extends PlayerEvent
    implements IPlayerEvent.ProfileEvent {
        public final IProfile profile;
        public final int slot;
        public final boolean post;

        public ProfileEvent(IPlayer player, IProfile profile, int slot, boolean post) {
            super(player);
            this.profile = profile;
            this.slot = slot;
            this.post = post;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.PROFILE.function;
        }

        @Override
        public IProfile getProfile() {
            return this.profile;
        }

        @Override
        public int getSlot() {
            return this.slot;
        }

        @Override
        public boolean isPost() {
            return this.post;
        }

        @Cancelable
        public static class Create
        extends ProfileEvent
        implements IPlayerEvent.ProfileEvent.Create {
            public Create(IPlayer player, IProfile profile, int slot, boolean post) {
                super(player, profile, slot, post);
            }

            @Override
            public String getHookName() {
                return EnumScriptType.PROFILE_CREATE.function;
            }
        }

        @Cancelable
        public static class Removed
        extends ProfileEvent
        implements IPlayerEvent.ProfileEvent.Removed {
            public Removed(IPlayer player, IProfile profile, int slot, boolean post) {
                super(player, profile, slot, post);
            }

            @Override
            public String getHookName() {
                return EnumScriptType.PROFILE_REMOVE.function;
            }
        }

        @Cancelable
        public static class Changed
        extends ProfileEvent
        implements IPlayerEvent.ProfileEvent.Changed {
            public final int prevSlot;

            public Changed(IPlayer player, IProfile profile, int slot, int prevSlot, boolean post) {
                super(player, profile, slot, post);
                this.prevSlot = prevSlot;
            }

            @Override
            public int getPrevSlot() {
                return this.prevSlot;
            }

            @Override
            public String getHookName() {
                return EnumScriptType.PROFILE_CHANGE.function;
            }
        }
    }

    public static class EffectEvent
    extends PlayerEvent
    implements IPlayerEvent.EffectEvent {
        public final IPlayerEffect effect;

        public EffectEvent(IPlayer player, IPlayerEffect statusEffect) {
            super(player);
            this.effect = statusEffect;
        }

        @Override
        public IPlayerEffect getEffect() {
            return this.effect;
        }

        public static enum ExpirationType {
            REMOVED,
            RUN_OUT,
            DEATH;

        }

        public static class Removed
        extends EffectEvent
        implements IPlayerEvent.EffectEvent.Removed {
            private final ExpirationType type;

            public Removed(IPlayer player, IPlayerEffect statusEffect, ExpirationType type) {
                super(player, statusEffect);
                this.type = type;
            }

            @Override
            public boolean hasTimerRunOut() {
                return this.type == ExpirationType.RUN_OUT;
            }

            @Override
            public boolean causedByDeath() {
                return this.type == ExpirationType.DEATH;
            }
        }

        public static class Ticked
        extends EffectEvent
        implements IPlayerEvent.EffectEvent.Ticked {
            public Ticked(IPlayer player, IPlayerEffect statusEffect) {
                super(player, statusEffect);
            }
        }

        public static class Added
        extends EffectEvent
        implements IPlayerEvent.EffectEvent.Added {
            public Added(IPlayer player, IPlayerEffect statusEffect) {
                super(player, statusEffect);
            }
        }
    }

    @Cancelable
    public static class RangedChargeEvent
    extends PlayerEvent
    implements IPlayerEvent.RangedChargeEvent {
        public RangedChargeEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RANGED_LAUNCHED.function;
        }
    }

    public static class Bonemeal
    extends PlayerEvent
    implements IPlayerEvent.BonemealEvent {
        public final IBlock block;
        public final int x;
        public final int y;
        public final int z;

        public Bonemeal(IPlayer player, int x, int y, int z, World world) {
            super(player);
            this.block = this.API.getIBlock(NpcAPI.Instance().getIWorld(world), x, y, z);
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.BONEMEAL.function;
        }

        @Override
        public IBlock getBlock() {
            return this.block;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public int getZ() {
            return this.z;
        }
    }

    public static class FillBucket
    extends PlayerEvent
    implements IPlayerEvent.FillBucketEvent {
        public final IItemStack current;
        public final IItemStack result;

        public FillBucket(IPlayer player, ItemStack current, ItemStack result) {
            super(player);
            this.current = NpcAPI.Instance().getIItemStack(current);
            this.result = NpcAPI.Instance().getIItemStack(result);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FILL_BUCKET.function;
        }

        @Override
        public IItemStack getCurrent() {
            return this.current;
        }

        @Override
        public IItemStack getFilled() {
            return this.result;
        }
    }

    public static class Achievement
    extends PlayerEvent
    implements IPlayerEvent.AchievementEvent {
        public final String description;

        public Achievement(IPlayer player, String description) {
            super(player);
            this.description = description;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ACHIEVEMENT.function;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
    }

    public static class Sleep
    extends PlayerEvent
    implements IPlayerEvent.SleepEvent {
        public final int x;
        public final int y;
        public final int z;

        public Sleep(IPlayer player, int x, int y, int z) {
            super(player);
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.SLEEP.function;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public int getZ() {
            return this.z;
        }
    }

    public static class WakeUp
    extends PlayerEvent
    implements IPlayerEvent.WakeUpEvent {
        public final boolean setSpawn;

        public WakeUp(IPlayer player, boolean setSpawn) {
            super(player);
            this.setSpawn = setSpawn;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.WAKE_UP.function;
        }

        @Override
        public boolean setSpawn() {
            return this.setSpawn;
        }
    }

    public static class UseHoe
    extends PlayerEvent
    implements IPlayerEvent.UseHoeEvent {
        public final IItemStack hoe;
        public final int x;
        public final int y;
        public final int z;

        public UseHoe(IPlayer player, ItemStack item, int x, int y, int z) {
            super(player);
            this.hoe = NpcAPI.Instance().getIItemStack(item);
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.USE_HOE.function;
        }

        @Override
        public IItemStack getHoe() {
            return this.hoe;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public int getZ() {
            return this.z;
        }
    }

    @Cancelable
    public static class BreakEvent
    extends PlayerEvent
    implements IPlayerEvent.BreakEvent {
        public final IBlock block;
        public int exp;

        public BreakEvent(IPlayer player, IBlock block, int exp) {
            super(player);
            this.block = block;
            this.exp = exp;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.BREAK_BLOCK.function;
        }

        @Override
        public IBlock getBlock() {
            return this.block;
        }

        @Override
        public int getExp() {
            return this.exp;
        }
    }

    public static class FinishUsingItem
    extends PlayerEvent
    implements IPlayerEvent.FinishUsingItem {
        public final IItemStack item;
        public final int duration;

        public FinishUsingItem(IPlayer player, ItemStack item, int duration) {
            super(player);
            this.item = NpcAPI.Instance().getIItemStack(item);
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FINISH_USING_ITEM.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class StopUsingItem
    extends PlayerEvent
    implements IPlayerEvent.StopUsingItem {
        public final IItemStack item;
        public final int duration;

        public StopUsingItem(IPlayer player, ItemStack item, int duration) {
            super(player);
            this.item = NpcAPI.Instance().getIItemStack(item);
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.STOP_USING_ITEM.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class UsingItem
    extends PlayerEvent
    implements IPlayerEvent.UsingItem {
        public final IItemStack item;
        public final int duration;

        public UsingItem(IPlayer player, ItemStack item, int duration) {
            super(player);
            this.item = NpcAPI.Instance().getIItemStack(item);
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.USING_ITEM.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    @Cancelable
    public static class StartUsingItem
    extends PlayerEvent
    implements IPlayerEvent.StartUsingItem {
        public final IItemStack item;
        public final int duration;

        public StartUsingItem(IPlayer player, ItemStack item, int duration) {
            super(player);
            this.item = NpcAPI.Instance().getIItemStack(item);
            this.duration = duration;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.START_USING_ITEM.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }

        @Override
        public int getDuration() {
            return this.duration;
        }
    }

    public static class InitEvent
    extends PlayerEvent
    implements IPlayerEvent.InitEvent {
        public InitEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INIT.function;
        }
    }

    public static class UpdateEvent
    extends PlayerEvent
    implements IPlayerEvent.UpdateEvent {
        public UpdateEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TICK.function;
        }
    }

    @Cancelable
    public static class RightClickEvent
    extends PlayerEvent
    implements IPlayerEvent.RightClickEvent {
        public final int type;
        public final Object target;

        public RightClickEvent(IPlayer player, int type, Object target) {
            super(player);
            this.type = type;
            this.target = target;
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
    extends PlayerEvent
    implements IPlayerEvent.InteractEvent {
        public final int type;
        public final IEntity target;

        public InteractEvent(IPlayer player, int type, IEntity target) {
            super(player);
            this.type = type;
            this.target = target;
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
    }

    @Cancelable
    public static class TossEvent
    extends PlayerEvent
    implements IPlayerEvent.TossEvent {
        public final IItemStack item;

        public TossEvent(IPlayer player, IItemStack item) {
            super(player);
            this.item = item;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TOSS.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }
    }

    @Cancelable
    public static class DropEvent
    extends PlayerEvent
    implements IPlayerEvent.DropEvent {
        public final IItemStack[] items;

        public DropEvent(IPlayer player, IItemStack[] items) {
            super(player);
            this.items = items;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DROP.function;
        }

        @Override
        public IItemStack[] getItems() {
            return this.items;
        }
    }

    @Cancelable
    public static class PickUpEvent
    extends PlayerEvent
    implements IPlayerEvent.PickUpEvent {
        public final IItemStack item;

        public PickUpEvent(IPlayer player, IItemStack item) {
            super(player);
            this.item = item;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.PICKUP.function;
        }

        @Override
        public IItemStack getItem() {
            return this.item;
        }
    }

    public static class ContainerOpen
    extends PlayerEvent
    implements IPlayerEvent.ContainerOpen {
        public final IContainer container;

        public ContainerOpen(IPlayer player, IContainer container) {
            super(player);
            this.container = container;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CONTAINER_OPEN.function;
        }

        @Override
        public IContainer getContainer() {
            return this.container;
        }
    }

    public static class ContainerClosed
    extends PlayerEvent
    implements IPlayerEvent.ContainerClosed {
        public final IContainer container;

        public ContainerClosed(IPlayer player, IContainer container) {
            super(player);
            this.container = container;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CONTAINER_CLOSED.function;
        }

        @Override
        public IContainer getContainer() {
            return this.container;
        }
    }

    @Cancelable
    public static class DamagedEntityEvent
    extends PlayerEvent
    implements IPlayerEvent.DamagedEntityEvent {
        public final IDamageSource damageSource;
        public final IEntity target;
        public float damage;

        public DamagedEntityEvent(IPlayer player, Entity target, float damage, DamageSource damagesource) {
            super(player);
            this.target = NpcAPI.Instance().getIEntity(target);
            this.damage = damage;
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DAMAGED_ENTITY.function;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public IEntity getTarget() {
            return this.target;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }
    }

    @Cancelable
    public static class AttackEvent
    extends PlayerEvent
    implements IPlayerEvent.AttackEvent {
        public final IDamageSource damageSource;
        public final IEntity target;
        public float damage;

        public AttackEvent(IPlayer player, Entity target, float damage, DamageSource damagesource) {
            super(player);
            this.target = NpcAPI.Instance().getIEntity(target);
            this.damage = damage;
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ATTACK.function;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public IEntity getTarget() {
            return this.target;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }
    }

    @Cancelable
    public static class RangedLaunchedEvent
    extends PlayerEvent
    implements IPlayerEvent.RangedLaunchedEvent {
        public final IItemStack bow;
        public int charge;

        public RangedLaunchedEvent(IPlayer player, ItemStack bow, int charge) {
            super(player);
            this.bow = NpcAPI.Instance().getIItemStack(bow);
            this.charge = charge;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RANGED_LAUNCHED.function;
        }

        @Override
        public IItemStack getBow() {
            return this.bow;
        }

        @Override
        public int getCharge() {
            return this.charge;
        }
    }

    public static class DiedEvent
    extends PlayerEvent
    implements IPlayerEvent.DiedEvent {
        public final IDamageSource damageSource;
        public final String type;
        public final IEntity source;

        public DiedEvent(IPlayer player, DamageSource damagesource, Entity entity) {
            super(player);
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
            this.type = damagesource.func_76355_l();
            this.source = NpcAPI.Instance().getIEntity(entity);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.KILLED.function;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public String getType() {
            return this.type;
        }

        @Override
        public IEntity getSource() {
            return this.source;
        }
    }

    public static class KilledEntityEvent
    extends PlayerEvent
    implements IPlayerEvent.KilledEntityEvent {
        public final IEntityLivingBase entity;

        public KilledEntityEvent(IPlayer player, EntityLivingBase entity) {
            super(player);
            this.entity = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)entity);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.KILLS.function;
        }

        @Override
        public IEntityLivingBase getEntity() {
            return this.entity;
        }
    }

    public static class JumpEvent
    extends PlayerEvent
    implements IPlayerEvent.JumpEvent {
        public JumpEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.JUMP.function;
        }
    }

    @Cancelable
    public static class FallEvent
    extends PlayerEvent
    implements IPlayerEvent.FallEvent {
        public final float distance;

        public FallEvent(IPlayer player, float distance) {
            super(player);
            this.distance = distance;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.FALL.function;
        }

        @Override
        public float getDistance() {
            return this.distance;
        }
    }

    @Cancelable
    public static class SoundEvent
    extends PlayerEvent
    implements IPlayerEvent.SoundEvent {
        public final String name;
        public final float pitch;
        public final float volume;

        public SoundEvent(IPlayer player, String name, float pitch, float volume) {
            super(player);
            this.name = name;
            this.pitch = pitch;
            this.volume = volume;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.PLAYSOUND.function;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getPitch() {
            return this.pitch;
        }

        @Override
        public float getVolume() {
            return this.volume;
        }
    }

    @Cancelable
    public static class LightningEvent
    extends PlayerEvent
    implements IPlayerEvent.LightningEvent {
        public LightningEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LIGHTNING.function;
        }
    }

    @Cancelable
    public static class DamagedEvent
    extends PlayerEvent
    implements IPlayerEvent.DamagedEvent {
        public final IDamageSource damageSource;
        public final IEntity source;
        public float damage;
        public boolean clearTarget = false;

        public DamagedEvent(IPlayer player, Entity source, float damage, DamageSource damagesource) {
            super(player);
            this.source = NpcAPI.Instance().getIEntity(source);
            this.damage = damage;
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DAMAGED.function;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public IEntity getSource() {
            return this.source;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }
    }

    @Cancelable
    public static class AttackedEvent
    extends PlayerEvent
    implements IPlayerEvent.AttackedEvent {
        public final IDamageSource damageSource;
        public final IEntity source;
        public float damage;

        public AttackedEvent(IPlayer player, Entity source, float damage, DamageSource damagesource) {
            super(player);
            this.source = NpcAPI.Instance().getIEntity(source);
            this.damage = damage;
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ATTACKED.function;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public IEntity getSource() {
            return this.source;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }
    }

    public static class TimerEvent
    extends PlayerEvent
    implements IPlayerEvent.TimerEvent {
        public final int id;

        public TimerEvent(IPlayer player, int id) {
            super(player);
            this.id = id;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TIMER.function;
        }

        @Override
        public int getId() {
            return this.id;
        }
    }

    public static class ChangedDimension
    extends PlayerEvent
    implements IPlayerEvent.ChangedDimension {
        public final int fromDim;
        public final int toDim;

        public ChangedDimension(IPlayer player, int fromDim, int toDim) {
            super(player);
            this.fromDim = fromDim;
            this.toDim = toDim;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CHANGED_DIM.function;
        }

        @Override
        public int getFromDim() {
            return this.fromDim;
        }

        @Override
        public int getToDim() {
            return this.toDim;
        }
    }

    public static class RespawnEvent
    extends PlayerEvent
    implements IPlayerEvent.RespawnEvent {
        public RespawnEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RESPAWN.function;
        }
    }

    public static class LoginEvent
    extends PlayerEvent
    implements IPlayerEvent.LoginEvent {
        public LoginEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LOGIN.function;
        }
    }

    public static class LogoutEvent
    extends PlayerEvent
    implements IPlayerEvent.LogoutEvent {
        public LogoutEvent(IPlayer player) {
            super(player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LOGOUT.function;
        }
    }

    public static class LevelUpEvent
    extends PlayerEvent
    implements IPlayerEvent.LevelUpEvent {
        public final int change;

        public LevelUpEvent(IPlayer player, int change) {
            super(player);
            this.change = change;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LEVEL_UP.function;
        }

        @Override
        public int getChange() {
            return this.change;
        }
    }

    public static class PickupXPEvent
    extends PlayerEvent
    implements IPlayerEvent.PickupXPEvent {
        public final int amount;

        public PickupXPEvent(IPlayer player, EntityXPOrb orb) {
            super(player);
            this.amount = orb.field_70530_e;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.PICKUP_XP.function;
        }

        @Override
        public int getAmount() {
            return this.amount;
        }
    }

    public static class MouseClickedEvent
    extends PlayerEvent
    implements IPlayerEvent.MouseClickedEvent {
        public final boolean isCtrlPressed;
        public final boolean isAltPressed;
        public final boolean isShiftPressed;
        public final boolean isMetaPressed;
        public final int[] keysDown;
        public final int button;
        public final int mouseWheel;
        public final boolean buttonDown;

        public MouseClickedEvent(IPlayer player, int button, int mouseWheel, boolean buttonDown, boolean isCtrlPressed, boolean isAltPressed, boolean isShiftPressed, boolean isMetaPressed, int[] heldKeys) {
            super(player);
            this.button = button;
            this.mouseWheel = mouseWheel;
            this.buttonDown = buttonDown;
            this.isCtrlPressed = isCtrlPressed;
            this.isAltPressed = isAltPressed;
            this.isShiftPressed = isShiftPressed;
            this.isMetaPressed = isMetaPressed;
            this.keysDown = heldKeys;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.MOUSE_CLICKED.function;
        }

        @Override
        public int getButton() {
            return this.button;
        }

        @Override
        public int getMouseWheel() {
            return this.mouseWheel;
        }

        @Override
        public boolean buttonDown() {
            return this.buttonDown;
        }

        @Override
        public boolean isCtrlPressed() {
            return this.isCtrlPressed;
        }

        @Override
        public boolean isAltPressed() {
            return this.isAltPressed;
        }

        @Override
        public boolean isShiftPressed() {
            return this.isShiftPressed;
        }

        @Override
        public boolean isMetaPressed() {
            return this.isMetaPressed;
        }

        @Override
        public int[] getKeysDown() {
            return this.keysDown;
        }
    }

    public static class KeyPressedEvent
    extends PlayerEvent
    implements IPlayerEvent.KeyPressedEvent {
        public final int key;
        public final boolean isCtrlPressed;
        public final boolean isAltPressed;
        public final boolean isShiftPressed;
        public final boolean isMetaPressed;
        public final boolean keyDown;
        public final int[] keysDown;

        public KeyPressedEvent(IPlayer player, int key, boolean isCtrlPressed, boolean isAltPressed, boolean isShiftPressed, boolean isMetaPressed, boolean keyDown, int[] heldKeys) {
            super(player);
            this.key = key;
            this.isCtrlPressed = isCtrlPressed;
            this.isAltPressed = isAltPressed;
            this.isShiftPressed = isShiftPressed;
            this.isMetaPressed = isMetaPressed;
            this.keyDown = keyDown;
            this.keysDown = heldKeys;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.KEY_PRESSED.function;
        }

        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public boolean isCtrlPressed() {
            return this.isCtrlPressed;
        }

        @Override
        public boolean isAltPressed() {
            return this.isAltPressed;
        }

        @Override
        public boolean isShiftPressed() {
            return this.isShiftPressed;
        }

        @Override
        public boolean isMetaPressed() {
            return this.isMetaPressed;
        }

        @Override
        public boolean keyDown() {
            return this.keyDown;
        }

        @Override
        public int[] getKeysDown() {
            return this.keysDown;
        }
    }

    @Cancelable
    public static class ChatEvent
    extends PlayerEvent
    implements IPlayerEvent.ChatEvent {
        public String message;

        public ChatEvent(IPlayer player, String message) {
            super(player);
            this.message = message;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CHAT.function;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}

