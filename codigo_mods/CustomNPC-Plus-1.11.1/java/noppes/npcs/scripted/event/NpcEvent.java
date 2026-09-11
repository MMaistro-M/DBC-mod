/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.INpcEvent;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class NpcEvent
extends CustomNPCsEvent
implements INpcEvent {
    public final ICustomNpc npc;

    public NpcEvent(ICustomNpc npc) {
        this.npc = npc;
    }

    @Override
    public ICustomNpc getNpc() {
        return this.npc;
    }

    public static class InitEvent
    extends NpcEvent
    implements INpcEvent.InitEvent {
        public InitEvent(ICustomNpc npc) {
            super(npc);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INIT.function;
        }
    }

    public static class UpdateEvent
    extends NpcEvent
    implements INpcEvent.UpdateEvent {
        public UpdateEvent(ICustomNpc npc) {
            super(npc);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TICK.function;
        }
    }

    @Cancelable
    public static class TargetEvent
    extends NpcEvent
    implements INpcEvent.TargetEvent {
        public IEntityLivingBase entity;

        public TargetEvent(ICustomNpc npc, EntityLivingBase entity) {
            super(npc);
            this.entity = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)entity);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TARGET.function;
        }

        @Override
        public void setTarget(IEntityLivingBase entity) {
            this.entity = entity;
        }

        @Override
        public IEntityLivingBase getTarget() {
            return this.entity;
        }
    }

    @Cancelable
    public static class TargetLostEvent
    extends NpcEvent
    implements INpcEvent.TargetLostEvent {
        public final IEntityLivingBase oldTarget;
        public final IEntityLivingBase newTarget;

        public TargetLostEvent(ICustomNpc npc, EntityLivingBase oldTarget, EntityLivingBase newTarget) {
            super(npc);
            this.oldTarget = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)oldTarget);
            this.newTarget = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)newTarget);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.TARGET_LOST.function;
        }

        @Override
        public IEntityLivingBase getTarget() {
            return this.oldTarget;
        }

        @Override
        public IEntityLivingBase getNewTarget() {
            return this.newTarget;
        }
    }

    public static class DialogClosedEvent
    extends NpcEvent
    implements INpcEvent.DialogClosedEvent {
        public final IPlayer player;
        public final int id;
        public final int optionId;
        public final Dialog dialogObj;

        public DialogClosedEvent(ICustomNpc npc, EntityPlayer player, int id, int optionId, Dialog dialog) {
            super(npc);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
            this.id = id;
            this.optionId = optionId;
            this.dialogObj = dialog;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DIALOG_CLOSE.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public Dialog getDialog() {
            return this.dialogObj;
        }

        @Override
        public int getDialogId() {
            return this.id;
        }

        @Override
        public int getOptionId() {
            return this.optionId;
        }

        public boolean isClosing() {
            return true;
        }
    }

    @Cancelable
    public static class DialogEvent
    extends NpcEvent
    implements INpcEvent.DialogEvent {
        public final IPlayer player;
        public final int id;
        public final int optionId;
        public final IDialog dialogObj;

        public DialogEvent(ICustomNpc npc, EntityPlayer player, int id, int optionId, Dialog dialog) {
            super(npc);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
            this.id = id;
            this.optionId = optionId;
            this.dialogObj = dialog;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DIALOG.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }

        @Override
        public IDialog getDialog() {
            return this.dialogObj;
        }

        @Override
        public int getDialogId() {
            return this.id;
        }

        @Override
        public int getOptionId() {
            return this.optionId;
        }

        public boolean isClosing() {
            return true;
        }
    }

    @Cancelable
    public static class InteractEvent
    extends NpcEvent
    implements INpcEvent.InteractEvent {
        public final IPlayer player;

        public InteractEvent(ICustomNpc npc, EntityPlayer player) {
            super(npc);
            this.player = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.INTERACT.function;
        }

        @Override
        public IPlayer getPlayer() {
            return this.player;
        }
    }

    @Cancelable
    public static class DiedEvent
    extends NpcEvent
    implements INpcEvent.DiedEvent {
        public final IDamageSource damageSource;
        public final String type;
        public final IEntity source;
        public IItemStack[] droppedItems;
        public int expDropped;

        public DiedEvent(ICustomNpc npc, DamageSource damagesource, Entity entity, ArrayList<ItemStack> droppedItems, int expDropped) {
            super(npc);
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
            this.type = damagesource.field_76373_n;
            this.source = NpcAPI.Instance().getIEntity(entity);
            ArrayList<IItemStack> iItemStacks = new ArrayList<IItemStack>();
            for (ItemStack itemStack : droppedItems) {
                IItemStack iItemStack = NpcAPI.Instance().getIItemStack(itemStack);
                iItemStacks.add(iItemStack);
            }
            this.droppedItems = iItemStacks.toArray(new IItemStack[0]);
            this.expDropped = expDropped;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.KILLED.function;
        }

        @Override
        public IEntity getSource() {
            return this.source;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public String getType() {
            return this.damageSource.getMCDamageSource().field_76373_n;
        }

        @Override
        public void setDroppedItems(IItemStack[] droppedItems) {
            this.droppedItems = droppedItems;
        }

        @Override
        public IItemStack[] getDroppedItems() {
            return this.droppedItems;
        }

        @Override
        public void setExpDropped(int expDropped) {
            this.expDropped = expDropped;
        }

        @Override
        public int getExpDropped() {
            return this.expDropped;
        }
    }

    public static class KilledEntityEvent
    extends NpcEvent
    implements INpcEvent.KilledEntityEvent {
        public final IEntityLivingBase entity;

        public KilledEntityEvent(ICustomNpc npc, EntityLivingBase entity) {
            super(npc);
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

    @Cancelable
    public static class SwingEvent
    extends NpcEvent
    implements INpcEvent.SwingEvent {
        public final IItemStack itemStack;

        public SwingEvent(ICustomNpc npc, ItemStack itemStack) {
            super(npc);
            this.itemStack = itemStack == null ? null : NpcAPI.Instance().getIItemStack(itemStack);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ATTACK_SWING.function;
        }

        @Override
        public IItemStack getItemStack() {
            return this.itemStack;
        }
    }

    @Cancelable
    public static class MeleeAttackEvent
    extends NpcEvent
    implements INpcEvent.MeleeAttackEvent {
        public final IEntityLivingBase target;
        public float damage;

        public MeleeAttackEvent(ICustomNpc npc, float damage, EntityLivingBase target) {
            super(npc);
            this.target = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)target);
            this.damage = damage;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ATTACK_MELEE.function;
        }

        @Override
        public IEntityLivingBase getTarget() {
            return this.target;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }

        @Override
        public void setDamage(float damage) {
            this.damage = damage;
        }

        public boolean isRange() {
            return false;
        }
    }

    @Cancelable
    public static class RangedLaunchedEvent
    extends NpcEvent
    implements INpcEvent.RangedLaunchedEvent {
        public final IEntityLivingBase target;
        public float damage;

        public RangedLaunchedEvent(ICustomNpc npc, float damage, EntityLivingBase target) {
            super(npc);
            this.target = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)target);
            this.damage = damage;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.RANGED_LAUNCHED.function;
        }

        @Override
        public IEntityLivingBase getTarget() {
            return this.target;
        }

        @Override
        public void setDamage(float damage) {
            this.damage = damage;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }

        public boolean isRange() {
            return true;
        }
    }

    @Cancelable
    public static class DamagedEvent
    extends NpcEvent
    implements INpcEvent.DamagedEvent {
        public final IDamageSource damageSource;
        public final IEntity source;
        public float damage;
        private boolean clearTarget = false;
        public boolean clear = false;
        public final DamageSource damagesource;

        public DamagedEvent(ICustomNpc npc, Entity source, float damage, DamageSource damagesource) {
            super(npc);
            this.source = NpcAPI.Instance().getIEntity(source);
            this.damage = damage;
            this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
            this.damagesource = damagesource;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DAMAGED.function;
        }

        @Override
        public IEntity getSource() {
            return this.source;
        }

        @Override
        public IDamageSource getDamageSource() {
            return this.damageSource;
        }

        @Override
        public float getDamage() {
            return this.damage;
        }

        @Override
        public void setDamage(float damage) {
            this.damage = damage;
        }

        @Override
        public void setClearTarget(boolean bo) {
            this.clearTarget = bo;
            this.clear = bo;
        }

        @Override
        public boolean getClearTarget() {
            return this.clearTarget && this.clear;
        }

        @Override
        public String getType() {
            return this.damageSource.getMCDamageSource().field_76373_n;
        }
    }

    public static class CollideEvent
    extends NpcEvent
    implements INpcEvent.CollideEvent {
        public final IEntity entity;

        public CollideEvent(ICustomNpc npc, Entity entity) {
            super(npc);
            this.entity = NpcAPI.Instance().getIEntity(entity);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.COLLIDE.function;
        }

        @Override
        public IEntity getEntity() {
            return this.entity;
        }
    }

    public static class TimerEvent
    extends NpcEvent
    implements INpcEvent.TimerEvent {
        public final int id;

        public TimerEvent(ICustomNpc npc, int id) {
            super(npc);
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
}

