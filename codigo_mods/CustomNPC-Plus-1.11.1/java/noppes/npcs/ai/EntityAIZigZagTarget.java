/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import noppes.npcs.ai.RandomPositionGeneratorAlt;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIZigZagTarget
extends EntityAIBase {
    private EntityCreature theEntity;
    private EntityLivingBase targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private int entityPosX;
    private int entityPosY;
    private int entityPosZ;
    private double speed;
    private float tacticalRange;

    public EntityAIZigZagTarget(EntityCreature par1EntityCreature, double par2, float par4) {
        this.theEntity = par1EntityCreature;
        this.speed = par2;
        this.tacticalRange = par4;
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        this.targetEntity = this.theEntity.func_70638_az();
        if (this.targetEntity == null) {
            return false;
        }
        if (this.targetEntity.func_70068_e((Entity)this.theEntity) < (double)(this.tacticalRange * this.tacticalRange)) {
            return false;
        }
        PathEntity pathentity = this.theEntity.func_70661_as().func_75494_a((Entity)this.targetEntity);
        if (pathentity != null && (float)pathentity.func_75874_d() >= this.tacticalRange) {
            PathPoint pathpoint = pathentity.func_75877_a(MathHelper.func_76128_c((double)((double)this.tacticalRange / 2.0)));
            this.entityPosX = pathpoint.field_75839_a;
            this.entityPosY = pathpoint.field_75837_b;
            this.entityPosZ = pathpoint.field_75838_c;
            Vec3 vec3 = RandomPositionGeneratorAlt.findRandomTargetBlockTowards(this.theEntity, (int)this.tacticalRange, 3, Vec3.func_72443_a((double)this.entityPosX, (double)this.entityPosY, (double)this.entityPosZ));
            if (vec3 != null && this.targetEntity.func_70092_e(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c) < this.targetEntity.func_70092_e((double)this.entityPosX, (double)this.entityPosY, (double)this.entityPosZ)) {
                this.movePosX = vec3.field_72450_a;
                this.movePosY = vec3.field_72448_b;
                this.movePosZ = vec3.field_72449_c;
                return true;
            }
        }
        return false;
    }

    public boolean func_75253_b() {
        return !this.theEntity.func_70661_as().func_75500_f() && this.targetEntity.func_70089_S() && this.targetEntity.func_70068_e((Entity)this.theEntity) > (double)(this.tacticalRange * this.tacticalRange);
    }

    public void func_75249_e() {
        this.theEntity.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.speed);
    }

    public void func_75246_d() {
        if (!(this.theEntity instanceof EntityNPCInterface) || !((EntityNPCInterface)this.theEntity).abilities.isRotationLocked()) {
            this.theEntity.func_70671_ap().func_75651_a((Entity)this.targetEntity, 30.0f, 30.0f);
        }
    }
}

