/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.ai.pathfinder;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;
import noppes.npcs.entity.EntityNPCFlying;
import noppes.npcs.entity.EntityNPCInterface;

public class FlyingMoveHelper
extends EntityMoveHelper {
    private final EntityNPCInterface entity;
    private double posX;
    private double posY;
    private double posZ;
    private double speed;
    public boolean field_75643_f;

    public FlyingMoveHelper(EntityNPCInterface entity) {
        super((EntityLiving)entity);
        this.entity = entity;
        this.posX = entity.field_70165_t;
        this.posY = entity.field_70163_u;
        this.posZ = entity.field_70161_v;
    }

    public void func_75641_c() {
        if (this.field_75643_f) {
            this.field_75643_f = false;
            double speed = this.speed * this.entity.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
            double verticalSpeed = this.speed * this.entity.ais.flySpeed / 8.0;
            this.entity.func_70659_e((float)speed);
            double d0 = this.posX - this.entity.field_70165_t;
            double d1 = this.posY - this.entity.field_70163_u;
            double d2 = this.posZ - this.entity.field_70161_v;
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            double d5 = MathHelper.func_76133_a((double)d4);
            speed = Math.min(d5 / 5.0, speed);
            if (d4 >= 0.5) {
                this.entity.field_70159_w += (speed * (d0 / d5) - this.entity.field_70159_w) * speed;
                this.entity.field_70179_y += (speed * (d2 / d5) - this.entity.field_70179_y) * speed;
                if (((EntityNPCFlying)this.entity).flyLimitAllow || !this.entity.ais.hasFlyLimit) {
                    this.entity.field_70181_x = verticalSpeed * (d1 / d5);
                    if (this.entity.field_70181_x > 0.0) {
                        this.entity.field_70181_x += 0.1;
                    }
                }
                this.entity.field_70133_I = true;
                this.entity.field_70177_z = this.limitAngle(this.entity.field_70177_z, (float)((Math.atan2(-d0, -d2) + Math.PI) * -57.29577951308232), 20.0f);
            }
        }
    }

    private float limitAngle(float angleIn, float lower, float upper) {
        float f1;
        float f = MathHelper.func_76142_g((float)(lower - angleIn));
        if (f > upper) {
            f = upper;
        }
        if (f < -upper) {
            f = -upper;
        }
        if ((f1 = angleIn + f) < 0.0f) {
            f1 += 360.0f;
        } else if (f1 > 360.0f) {
            f1 -= 360.0f;
        }
        return f1;
    }

    public void func_75642_a(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_) {
        this.posX = p_75642_1_;
        this.posY = p_75642_3_;
        this.posZ = p_75642_5_;
        this.speed = p_75642_7_;
        this.field_75643_f = true;
    }

    public boolean func_75640_a() {
        return this.field_75643_f;
    }

    public double func_75638_b() {
        return this.speed;
    }
}

