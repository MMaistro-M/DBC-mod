/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.IEnergyProjectileEvent;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class EnergyProjectileEvent
extends CustomNPCsEvent
implements IEnergyProjectileEvent {
    public final IEnergyProjectile projectile;
    public final IEntity owner;

    public EnergyProjectileEvent(IEnergyProjectile projectile) {
        this.projectile = projectile;
        this.owner = projectile.getOwner();
    }

    @Override
    public IEnergyProjectile getProjectile() {
        return this.projectile;
    }

    @Override
    public IEntity getOwner() {
        return this.owner;
    }

    @Override
    public String getHookName() {
        return "energyProjectileEvent";
    }

    public static class ExpiredEvent
    extends EnergyProjectileEvent
    implements IEnergyProjectileEvent.ExpiredEvent {
        public ExpiredEvent(IEnergyProjectile projectile) {
            super(projectile);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_PROJECTILE_EXPIRED.function;
        }
    }

    public static class BlockImpactEvent
    extends EnergyProjectileEvent
    implements IEnergyProjectileEvent.BlockImpactEvent {
        private final IBlock block;

        public BlockImpactEvent(IEnergyProjectile projectile, IBlock block) {
            super(projectile);
            this.block = block;
        }

        @Override
        public IBlock getBlock() {
            return this.block;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT.function;
        }
    }

    @Cancelable
    public static class EntityImpactEvent
    extends EnergyProjectileEvent
    implements IEnergyProjectileEvent.EntityImpactEvent {
        private final IEntity target;
        private float damage;

        public EntityImpactEvent(IEnergyProjectile projectile, IEntity target, float damage) {
            super(projectile);
            this.target = target;
            this.damage = damage;
        }

        @Override
        public IEntity getTarget() {
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

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT.function;
        }
    }

    public static class UpdateEvent
    extends EnergyProjectileEvent
    implements IEnergyProjectileEvent.UpdateEvent {
        private final int tick;

        public UpdateEvent(IEnergyProjectile projectile, int tick) {
            super(projectile);
            this.tick = tick;
        }

        @Override
        public int getTick() {
            return this.tick;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_PROJECTILE_TICK.function;
        }
    }

    public static class FiredEvent
    extends EnergyProjectileEvent
    implements IEnergyProjectileEvent.FiredEvent {
        public FiredEvent(IEnergyProjectile projectile) {
            super(projectile);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.ENERGY_PROJECTILE_FIRED.function;
        }
    }
}

