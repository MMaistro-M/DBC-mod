/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.DragonBC.common.Npcs.aai;

import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.entity.aai.AAi;
import JinRyuu.JRMCore.server.JGMathHelper;

public class AAiDBCFlyingCharge
extends AAi {
    private double multi;
    private double rate;
    private double limit;

    public AAiDBCFlyingCharge(double[] values) {
        this(values[0], values[1], values[2]);
    }

    public AAiDBCFlyingCharge(double multi, double rate, double limit) {
        this.multi = multi;
        this.rate = rate;
        this.limit = limit;
    }

    @Override
    public void update() {
        EntityDBC entity = (EntityDBC)this.aaiSystem.entity;
        if (entity.func_70089_S() && !entity.field_70170_p.field_72995_K && !entity.isLocked() && !entity.chargingKiAttack && entity.getTargetedEntity() != null && entity.func_70685_l(entity.getTargetedEntity()) && this.checkChanceToUse(this.rate) && entity.canFly && !entity.field_70122_E) {
            if (JGMathHelper.doubleSmallerThan(entity.field_70159_w, 0.5)) {
                double xDiff;
                entity.field_70159_w = xDiff = (entity.getTargetedEntity().field_70165_t - entity.field_70165_t) / 50.0 * this.multi;
                if (this.limit != -1.0) {
                    entity.field_70159_w = JGMathHelper.doubleLimit(entity.field_70159_w, this.limit);
                }
            }
            if (JGMathHelper.doubleSmallerThan(entity.field_70179_y, 0.5)) {
                double zDiff;
                entity.field_70179_y = zDiff = (entity.getTargetedEntity().field_70161_v - entity.field_70161_v) / 50.0 * this.multi;
                if (this.limit != -1.0) {
                    entity.field_70179_y = JGMathHelper.doubleLimit(entity.field_70179_y, this.limit);
                }
            }
        }
    }
}

