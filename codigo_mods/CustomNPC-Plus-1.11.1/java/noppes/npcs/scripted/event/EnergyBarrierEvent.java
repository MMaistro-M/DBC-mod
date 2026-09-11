/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IEnergyBarrier;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.IEnergyBarrierEvent;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class EnergyBarrierEvent
extends CustomNPCsEvent
implements IEnergyBarrierEvent {
    public final IEnergyBarrier barrier;
    public final IEntity owner;

    public EnergyBarrierEvent(IEnergyBarrier barrier) {
        this.barrier = barrier;
        this.owner = barrier.getOwner();
    }

    @Override
    public IEnergyBarrier getBarrier() {
        return this.barrier;
    }

    @Override
    public IEntity getOwner() {
        return this.owner;
    }

    @Override
    public String getHookName() {
        return "energyBarrierEvent";
    }

    public static class DestroyedEvent
    extends EnergyBarrierEvent
    implements IEnergyBarrierEvent.DestroyedEvent {
        public DestroyedEvent(IEnergyBarrier barrier) {
            super(barrier);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_BARRIER_DESTROYED.function;
        }
    }

    @Cancelable
    public static class HitEvent
    extends EnergyBarrierEvent
    implements IEnergyBarrierEvent.HitEvent {
        private final IEnergyProjectile projectile;
        private float damage;

        public HitEvent(IEnergyBarrier barrier, IEnergyProjectile projectile, float damage) {
            super(barrier);
            this.projectile = projectile;
            this.damage = damage;
        }

        @Override
        public IEnergyProjectile getProjectile() {
            return this.projectile;
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
        public String getHookName() {
            return EnumScriptType.ENERGY_BARRIER_HIT.function;
        }
    }

    public static class UpdateEvent
    extends EnergyBarrierEvent
    implements IEnergyBarrierEvent.UpdateEvent {
        public UpdateEvent(IEnergyBarrier barrier) {
            super(barrier);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_BARRIER_TICK.function;
        }
    }

    public static class SpawnedEvent
    extends EnergyBarrierEvent
    implements IEnergyBarrierEvent.SpawnedEvent {
        public SpawnedEvent(IEnergyBarrier barrier) {
            super(barrier);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_BARRIER_SPAWNED.function;
        }
    }
}

