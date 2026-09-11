/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.entity;

import java.util.UUID;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.energyexplosion.EnergyExplosionSpawnPacket;
import noppes.npcs.api.entity.IEnergyExplosion;
import noppes.npcs.scripted.entity.ScriptEnergyAbility;

public class ScriptEnergyExplosion<T extends EntityEnergyExplosion>
extends ScriptEnergyAbility<T>
implements IEnergyExplosion {
    public ScriptEnergyExplosion(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 22;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 22 || super.typeOf(type);
    }

    @Override
    public float getRadius() {
        return ((EntityEnergyExplosion)this.entity).getExplosionRadius();
    }

    @Override
    public void setRadius(float radius) {
        ((EntityEnergyExplosion)this.entity).setExplosionRadius(radius);
    }

    @Override
    public int getDuration() {
        return ((EntityEnergyExplosion)this.entity).getDurationTicks();
    }

    @Override
    public float getDamage() {
        return ((EntityEnergyExplosion)this.entity).getDamage();
    }

    @Override
    public void setDamage(float damage) {
        ((EntityEnergyExplosion)this.entity).setDamage(damage);
    }

    @Override
    public float getKnockback() {
        return ((EntityEnergyExplosion)this.entity).getKnockback();
    }

    @Override
    public void setKnockback(float knockback) {
        ((EntityEnergyExplosion)this.entity).setKnockback(knockback);
    }

    @Override
    public float getKnockbackUp() {
        return ((EntityEnergyExplosion)this.entity).getKnockbackUp();
    }

    @Override
    public void setKnockbackUp(float knockbackUp) {
        ((EntityEnergyExplosion)this.entity).setKnockbackUp(knockbackUp);
    }

    @Override
    public float getDamageFalloff() {
        return ((EntityEnergyExplosion)this.entity).getDamageFalloff();
    }

    @Override
    public void setDamageFalloff(float falloff) {
        ((EntityEnergyExplosion)this.entity).setDamageFalloff(falloff);
    }

    @Override
    public void spawn() {
        if (((EntityEnergyExplosion)this.entity).field_70175_ag || ((EntityEnergyExplosion)this.entity).field_70170_p == null) {
            return;
        }
        if (!((EntityEnergyExplosion)this.entity).field_70170_p.field_72995_K) {
            String instanceId = "energy_explosion_" + UUID.randomUUID();
            PacketHandler.Instance.sendToAll(new EnergyExplosionSpawnPacket(instanceId, ((EntityEnergyExplosion)this.entity).exportSpawnNBT()));
            if (((EntityEnergyExplosion)this.entity).isDamageEnabled()) {
                ((EntityEnergyExplosion)this.entity).field_70170_p.func_72838_d(this.entity);
            }
        }
    }
}

