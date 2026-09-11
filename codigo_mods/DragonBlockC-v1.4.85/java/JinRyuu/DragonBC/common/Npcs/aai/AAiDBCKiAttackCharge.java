/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package JinRyuu.DragonBC.common.Npcs.aai;

import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.entity.aai.AAi;
import JinRyuu.JRMCore.server.JGMathHelper;
import java.util.Random;
import net.minecraft.entity.Entity;

public class AAiDBCKiAttackCharge
extends AAi {
    private double multi;
    private double rate;
    private double limit;

    public AAiDBCKiAttackCharge(double[] values) {
        this(values[0], values[1], values[2]);
    }

    public AAiDBCKiAttackCharge(double multi, double rate, double limit) {
        this.multi = multi;
        this.rate = rate;
        this.limit = limit;
    }

    @Override
    public void update() {
        EntityDBC entity = (EntityDBC)this.aaiSystem.entity;
        if (entity.func_70089_S() && entity.canFireKiAttacks && !entity.isLocked() && !entity.field_70170_p.field_72995_K && entity.getTargetedEntity() != null && entity.getTargetedEntity().func_70089_S() && entity.getTargetedEntity().func_70068_e((Entity)entity) < 4096.0) {
            int chargeTimerMax = 25;
            int KI_ATTACK_DISTANCE = 10;
            double xzDist = entity.getXZDistanceToEntity(entity.getTargetedEntity());
            double yDist = entity.getYDistanceToEntity(entity.getTargetedEntity());
            if (!entity.chargingKiAttack && entity.field_70173_aa % 25 == 0 && this.checkChanceToUse(this.rate)) {
                entity.chargingKiAttack = true;
                entity.chargingKiAttackTimer = 0;
                entity.chargingKiAttackTimerMax = (byte)(35 + new Random().nextInt(25));
            }
            if (entity.chargingKiAttack) {
                ++entity.chargingKiAttackTimer;
                if (entity.chargingKiAttackTimer >= entity.chargingKiAttackTimerMax) {
                    entity.chargingKiAttack = false;
                    entity.chargingKiAttackTimer = 0;
                }
                if (xzDist < 10.0) {
                    double xDiff = (entity.getTargetedEntity().field_70165_t - entity.field_70165_t) / 10.0 * this.multi;
                    double zDiff = (entity.getTargetedEntity().field_70161_v - entity.field_70161_v) / 10.0 * this.multi;
                    entity.field_70159_w = -xDiff;
                    entity.field_70159_w = JGMathHelper.doubleLimit(entity.field_70159_w, this.limit);
                    entity.field_70179_y = -zDiff;
                    entity.field_70179_y = JGMathHelper.doubleLimit(entity.field_70179_y, this.limit);
                }
            }
        }
    }
}

