/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.ai;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIOrbitTarget
extends EntityAIBase {
    private EntityNPCInterface theEntity;
    private EntityLivingBase targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private double speed;
    private float distance;
    private int delay = 0;
    private float angle = 0.0f;
    private int direction = 1;
    private float targetDistance;
    private boolean decay;
    private boolean canNavigate = true;
    private float decayRate = 1.0f;
    private int tick = 0;

    public EntityAIOrbitTarget(EntityNPCInterface par1EntityCreature, double par2, float par4, boolean par5) {
        this.theEntity = par1EntityCreature;
        this.speed = par2;
        this.distance = par4;
        this.decay = par5;
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        if (--this.delay > 0) {
            return false;
        }
        this.targetEntity = this.theEntity.func_70638_az();
        if (this.targetEntity == null) {
            return false;
        }
        double d0 = this.theEntity.func_70032_d((Entity)this.targetEntity);
        return d0 >= (double)(this.distance / 2.0f) && (this.theEntity.inventory.getProjectile() != null || d0 <= (double)this.distance);
    }

    public boolean func_75253_b() {
        double d0 = this.targetEntity.func_70032_d((Entity)this.theEntity);
        return this.targetEntity.func_70089_S() && d0 >= (double)(this.distance / 2.0f) && d0 <= (double)(this.distance * 1.5f) && !this.theEntity.func_70090_H() && this.canNavigate;
    }

    public void func_75251_c() {
        this.theEntity.func_70661_as().func_75499_g();
        this.delay = 60;
        if (this.theEntity.getRangedTask() != null) {
            this.theEntity.getRangedTask().navOverride(false);
        }
    }

    public void func_75249_e() {
        this.canNavigate = true;
        Random random = this.theEntity.func_70681_au();
        this.direction = random.nextInt(10) > 5 ? 1 : -1;
        this.decayRate = random.nextFloat() + this.distance / 16.0f;
        this.targetDistance = this.theEntity.func_70032_d((Entity)this.targetEntity);
        double d0 = this.theEntity.field_70165_t - this.targetEntity.field_70165_t;
        double d1 = this.theEntity.field_70161_v - this.targetEntity.field_70161_v;
        this.angle = (float)(Math.atan2(d1, d0) * 180.0 / Math.PI);
        if (this.theEntity.getRangedTask() != null) {
            this.theEntity.getRangedTask().navOverride(true);
        }
    }

    public void func_75246_d() {
        if (!this.theEntity.abilities.isRotationLocked()) {
            this.theEntity.func_70671_ap().func_75651_a((Entity)this.targetEntity, 30.0f, 30.0f);
        }
        if (this.theEntity.func_70661_as().func_75500_f() && this.tick >= 0 && this.theEntity.field_70122_E && !this.theEntity.func_70090_H()) {
            double d0 = (double)this.targetDistance * (double)MathHelper.func_76134_b((float)(this.angle / 180.0f * (float)Math.PI));
            double d1 = (double)this.targetDistance * (double)MathHelper.func_76126_a((float)(this.angle / 180.0f * (float)Math.PI));
            this.movePosX = this.targetEntity.field_70165_t + d0;
            this.movePosY = this.targetEntity.field_70121_D.field_72337_e;
            this.movePosZ = this.targetEntity.field_70161_v + d1;
            this.theEntity.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.speed);
            this.angle += 15.0f * (float)this.direction;
            this.tick = MathHelper.func_76143_f((double)(this.theEntity.func_70011_f(this.movePosX, this.movePosY, this.movePosZ) / (double)(this.theEntity.getSpeed() / 20.0f)));
            if (this.decay) {
                this.targetDistance -= this.decayRate;
            }
        }
        if (this.tick >= 0) {
            --this.tick;
        }
    }
}

