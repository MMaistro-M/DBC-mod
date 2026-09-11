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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAILeapAtTargetNpc
extends EntityAIBase {
    private final EntityNPCInterface npc;
    private EntityLivingBase leapTarget;
    private final float leapMotionY;

    public EntityAILeapAtTargetNpc(EntityNPCInterface npc, float leapMotionY) {
        this.npc = npc;
        this.leapMotionY = leapMotionY;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        if (this.npc.abilities.isAbilityControllingMovement()) {
            return false;
        }
        this.leapTarget = this.npc.func_70638_az();
        if (this.leapTarget == null) {
            return false;
        }
        double distanceSq = this.npc.func_70068_e((Entity)this.leapTarget);
        if (distanceSq < 4.0 || distanceSq > 16.0) {
            return false;
        }
        if (!this.npc.field_70122_E) {
            return false;
        }
        return this.npc.func_70681_au().nextInt(5) == 0;
    }

    public boolean func_75253_b() {
        boolean airborne;
        boolean bl = airborne = !this.npc.field_70122_E;
        if (!airborne) {
            this.npc.setNpcJumpingState(false);
        }
        return airborne;
    }

    public void func_75249_e() {
        double d0 = this.leapTarget.field_70165_t - this.npc.field_70165_t;
        double d1 = this.leapTarget.field_70161_v - this.npc.field_70161_v;
        float f = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1));
        if ((double)f >= 1.0E-4) {
            this.npc.field_70159_w += d0 / (double)f * 0.5 * 0.8 + this.npc.field_70159_w * 0.2;
            this.npc.field_70179_y += d1 / (double)f * 0.5 * 0.8 + this.npc.field_70179_y * 0.2;
        }
        this.npc.field_70181_x = this.leapMotionY;
        this.npc.setNpcJumpingState(true);
    }

    public void func_75251_c() {
        this.npc.setNpcJumpingState(false);
    }
}

