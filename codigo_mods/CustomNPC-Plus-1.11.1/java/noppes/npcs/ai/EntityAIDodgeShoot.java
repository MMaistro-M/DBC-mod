/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;
import noppes.npcs.ai.RandomPositionGeneratorAlt;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIDodgeShoot
extends EntityAIBase {
    private EntityNPCInterface entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    public EntityAIDodgeShoot(EntityNPCInterface par1EntityNPCInterface) {
        this.entity = par1EntityNPCInterface;
        this.func_75248_a(AiMutex.PASSIVE);
    }

    public boolean func_75250_a() {
        Vec3 vec;
        EntityLivingBase var1 = this.entity.func_70638_az();
        if (var1 == null || !var1.func_70089_S()) {
            return false;
        }
        if (this.entity.inventory.getProjectile() == null) {
            return false;
        }
        if (this.entity.getRangedTask() == null) {
            return false;
        }
        Vec3 vec3 = vec = this.entity.getRangedTask().hasFired() ? RandomPositionGeneratorAlt.findRandomTarget(this.entity, 4, 1) : null;
        if (vec == null) {
            return false;
        }
        this.xPosition = vec.field_72450_a;
        this.yPosition = vec.field_72448_b;
        this.zPosition = vec.field_72449_c;
        return true;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, 1.2);
    }

    public void func_75246_d() {
        if (this.entity.func_70638_az() != null && !this.entity.abilities.isRotationLocked()) {
            this.entity.func_70671_ap().func_75651_a((Entity)this.entity.func_70638_az(), 30.0f, 30.0f);
        }
    }
}

