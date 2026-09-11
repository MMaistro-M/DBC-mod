/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.event;

import noppes.npcs.api.IBlock;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.api.event.IProjectileEvent;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class ProjectileEvent
extends CustomNPCsEvent
implements IProjectileEvent {
    public IProjectile projectile;
    public IEntity source;

    public ProjectileEvent(IProjectile projectile) {
        this.projectile = projectile;
        this.source = projectile.getThrower();
    }

    @Override
    public IProjectile getProjectile() {
        return this.projectile;
    }

    @Override
    public IEntity getSource() {
        return this.source;
    }

    public static class ImpactEvent
    extends ProjectileEvent
    implements IProjectileEvent.ImpactEvent {
        public final int type;
        public final Object target;

        public ImpactEvent(IProjectile projectile, int type, Object target) {
            super(projectile);
            this.type = type;
            this.target = target;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public IEntity getEntity() {
            if (this.type == 0) {
                return (IEntity)this.target;
            }
            return null;
        }

        @Override
        public IBlock getBlock() {
            if (this.type == 1) {
                return (IBlock)this.target;
            }
            return null;
        }
    }

    public static class UpdateEvent
    extends ProjectileEvent
    implements IProjectileEvent.UpdateEvent {
        public UpdateEvent(IProjectile projectile) {
            super(projectile);
        }
    }
}

