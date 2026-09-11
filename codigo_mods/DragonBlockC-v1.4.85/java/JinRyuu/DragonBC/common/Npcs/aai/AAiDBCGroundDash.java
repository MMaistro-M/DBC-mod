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
import net.minecraft.entity.Entity;

public class AAiDBCGroundDash
extends AAi {
    private double multi;
    private double rate;
    private double multi2;
    private double limit;

    public AAiDBCGroundDash(double[] values) {
        this(values[0], values[1], values[2], values[3]);
    }

    public AAiDBCGroundDash(double multi, double rate, double multi2, double limit) {
        this.multi = multi;
        this.rate = rate;
        this.multi2 = multi2;
        this.limit = limit;
    }

    @Override
    public void update() {
        EntityDBC entity = (EntityDBC)this.aaiSystem.entity;
        if (entity.func_70089_S() && !entity.field_70170_p.field_72995_K && !entity.isLocked() && entity.getTargetedEntity() != null && entity.getTargetedEntity().func_70089_S() && entity.getTargetedEntity().func_70068_e((Entity)entity) < 4096.0) {
            double xzDist = entity.getXZDistanceToEntity(entity.getTargetedEntity());
            if (this.checkChanceToUse(this.rate) && !entity.chargingKiAttack && xzDist >= 6.0 && entity.field_70122_E && !entity.isJumping()) {
                entity.field_70159_w *= this.multi;
                entity.field_70179_y *= this.multi;
                double xDiff = (entity.getTargetedEntity().field_70165_t - entity.field_70165_t) / 50.0 * this.multi2;
                double zDiff = (entity.getTargetedEntity().field_70161_v - entity.field_70161_v) / 50.0 * this.multi2;
                entity.field_70159_w += xDiff;
                if (this.limit != -1.0) {
                    entity.field_70159_w = JGMathHelper.doubleLimit(entity.field_70159_w, this.limit);
                }
                entity.field_70179_y += zDiff;
                if (this.limit != -1.0) {
                    entity.field_70179_y = JGMathHelper.doubleLimit(entity.field_70179_y, this.limit);
                }
            }
        }
    }
}

