/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;
import noppes.npcs.ai.RandomPositionGeneratorAlt;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIAvoidTarget
extends EntityAIBase {
    private EntityNPCInterface theEntity;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    private float health;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;
    private Class targetEntityClass;

    public EntityAIAvoidTarget(EntityNPCInterface par1EntityNPC) {
        this.theEntity = par1EntityNPC;
        this.distanceFromEntity = this.theEntity.stats.aggroRange;
        this.health = this.theEntity.func_110143_aJ();
        this.entityPathNavigate = par1EntityNPC.func_70661_as();
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        boolean var4;
        boolean var3;
        EntityLivingBase target = this.theEntity.func_70638_az();
        if (target == null) {
            return false;
        }
        this.targetEntityClass = target.getClass();
        if (this.targetEntityClass == EntityPlayer.class) {
            this.closestLivingEntity = this.theEntity.field_70170_p.func_72890_a((Entity)this.theEntity, (double)this.distanceFromEntity);
            if (this.closestLivingEntity == null) {
                return false;
            }
        } else {
            List var1 = this.theEntity.field_70170_p.func_72872_a(this.targetEntityClass, this.theEntity.field_70121_D.func_72314_b((double)this.distanceFromEntity, 3.0, (double)this.distanceFromEntity));
            if (var1.isEmpty()) {
                return false;
            }
            this.closestLivingEntity = (Entity)var1.get(0);
        }
        if (!this.theEntity.func_70635_at().func_75522_a(this.closestLivingEntity) && this.theEntity.ais.directLOS) {
            return false;
        }
        Vec3 var2 = RandomPositionGeneratorAlt.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.func_72443_a((double)this.closestLivingEntity.field_70165_t, (double)this.closestLivingEntity.field_70163_u, (double)this.closestLivingEntity.field_70161_v));
        boolean bl = var3 = this.theEntity.inventory.getProjectile() == null || this.theEntity.ais.useRangeMelee == 2;
        boolean bl2 = var3 ? this.health == this.theEntity.func_110143_aJ() : (var4 = this.theEntity.getRangedTask() != null && !this.theEntity.getRangedTask().hasFired());
        if (var2 == null) {
            return false;
        }
        if (this.closestLivingEntity.func_70092_e(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c) < this.closestLivingEntity.func_70068_e((Entity)this.theEntity)) {
            return false;
        }
        if (this.theEntity.ais.tacticalVariant == EnumNavType.HitNRun && var4) {
            return false;
        }
        this.entityPathEntity = this.entityPathNavigate.func_75488_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c);
        return this.entityPathEntity == null ? false : this.entityPathEntity.func_75880_b(var2);
    }

    public boolean func_75253_b() {
        return !this.entityPathNavigate.func_75500_f();
    }

    public void func_75249_e() {
        this.entityPathNavigate.func_75484_a(this.entityPathEntity, 1.0);
    }

    public void func_75251_c() {
        this.closestLivingEntity = null;
        this.theEntity.func_70624_b(null);
    }

    public void func_75246_d() {
        float dist;
        if (this.theEntity.func_70068_e(this.closestLivingEntity) < 49.0) {
            this.theEntity.func_70661_as().func_75489_a(1.2);
        } else {
            this.theEntity.func_70661_as().func_75489_a(1.0);
        }
        if (this.theEntity.ais.tacticalVariant == EnumNavType.HitNRun && ((dist = this.theEntity.func_70032_d(this.closestLivingEntity)) > this.distanceFromEntity || dist < (float)this.theEntity.ais.tacticalRadius)) {
            this.health = this.theEntity.func_110143_aJ();
        }
    }
}

