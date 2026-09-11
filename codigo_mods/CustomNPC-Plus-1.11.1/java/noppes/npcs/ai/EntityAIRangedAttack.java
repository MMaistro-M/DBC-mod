/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIRangedAttack
extends EntityAIBase {
    private final EntityNPCInterface entityHost;
    private final IRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;
    private int rangedAttackTime = 0;
    private int field_75318_f = 0;
    private int field_70846_g = 0;
    private int attackTick = 0;
    private boolean hasFired = false;
    private boolean navOverride = false;
    private boolean isShooting = false;

    public EntityAIRangedAttack(IRangedAttackMob par1IRangedAttackMob) {
        if (!(par1IRangedAttackMob instanceof EntityLivingBase)) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        this.rangedAttackEntityHost = par1IRangedAttackMob;
        this.entityHost = (EntityNPCInterface)par1IRangedAttackMob;
        this.rangedAttackTime = this.entityHost.stats.minDelay / 2;
        this.func_75248_a(this.navOverride ? AiMutex.PATHING : AiMutex.LOOK + AiMutex.PASSIVE);
    }

    public boolean func_75250_a() {
        EntityLivingBase var1 = this.entityHost.func_70638_az();
        if (var1 == null || !var1.func_70089_S()) {
            this.isShooting = false;
            return false;
        }
        if (this.entityHost.func_70032_d((Entity)var1) > (float)this.entityHost.stats.aggroRange) {
            this.isShooting = false;
            return false;
        }
        if (this.entityHost.inventory.getProjectile() == null) {
            this.isShooting = false;
            return false;
        }
        double var2 = this.entityHost.func_70092_e(var1.field_70165_t, var1.field_70121_D.field_72338_b, var1.field_70161_v);
        double var3 = this.entityHost.ais.distanceToMelee * this.entityHost.ais.distanceToMelee;
        if (this.entityHost.ais.useRangeMelee >= 1 && var2 <= var3) {
            this.isShooting = false;
            return false;
        }
        this.attackTarget = var1;
        return true;
    }

    public boolean func_75253_b() {
        return this.func_75250_a() || !this.entityHost.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
        this.attackTarget = null;
        this.entityHost.func_70624_b(null);
        this.entityHost.func_70661_as().func_75499_g();
        this.field_75318_f = 0;
        this.hasFired = false;
        this.rangedAttackTime = this.entityHost.stats.minDelay / 2;
    }

    public void func_75246_d() {
        if (!this.entityHost.abilities.isRotationLocked()) {
            this.entityHost.func_70671_ap().func_75651_a((Entity)this.attackTarget, 30.0f, 30.0f);
        }
        double var1 = this.entityHost.func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.field_70121_D.field_72338_b, this.attackTarget.field_70161_v);
        float field_82642_h = this.entityHost.stats.rangedRange * this.entityHost.stats.rangedRange;
        if (!this.navOverride && this.entityHost.ais.directLOS) {
            int v;
            this.field_75318_f = this.entityHost.func_70635_at().func_75522_a((Entity)this.attackTarget) ? ++this.field_75318_f : 0;
            int n = v = this.entityHost.ais.tacticalVariant == EnumNavType.Default ? 20 : 5;
            if (var1 <= (double)field_82642_h && this.field_75318_f >= v) {
                this.entityHost.func_70661_as().func_75499_g();
            } else {
                this.entityHost.func_70661_as().func_75497_a((Entity)this.attackTarget, 1.0);
            }
        }
        this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
        if (this.rangedAttackTime <= 0 && var1 <= (double)field_82642_h && (this.entityHost.func_70635_at().func_75522_a((Entity)this.attackTarget) || this.entityHost.ais.canFireIndirect == 2)) {
            if (this.field_70846_g == 0) {
                this.entityHost.stats.playBurstSound = true;
            }
            if (this.field_70846_g++ <= this.entityHost.stats.burstCount) {
                this.rangedAttackTime = this.entityHost.stats.fireRate;
                this.isShooting = true;
            } else {
                this.field_70846_g = 0;
                this.hasFired = true;
                this.rangedAttackTime = this.entityHost.stats.maxDelay - MathHelper.func_76141_d((float)(this.entityHost.func_70681_au().nextFloat() * (float)(this.entityHost.stats.maxDelay - this.entityHost.stats.minDelay)));
                this.isShooting = false;
            }
            if (this.field_70846_g > 1) {
                boolean indirect = false;
                switch (this.entityHost.ais.canFireIndirect) {
                    case 1: {
                        indirect = var1 > (double)field_82642_h / 2.0;
                        break;
                    }
                    case 2: {
                        indirect = !this.entityHost.func_70635_at().func_75522_a((Entity)this.attackTarget);
                    }
                }
                this.rangedAttackEntityHost.func_82196_d(this.attackTarget, indirect ? 1.0f : 0.0f);
                if (this.entityHost.currentAnimation != EnumAnimation.AIMING) {
                    this.entityHost.func_71038_i();
                }
            }
        }
    }

    public boolean hasFired() {
        return this.hasFired;
    }

    public boolean isShooting() {
        return this.isShooting;
    }

    public void navOverride(boolean nav) {
        this.navOverride = nav;
        this.func_75248_a(this.navOverride ? AiMutex.PATHING : AiMutex.LOOK + AiMutex.PASSIVE);
    }
}

