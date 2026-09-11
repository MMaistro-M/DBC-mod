/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 */
package noppes.npcs.ai;

import net.minecraft.entity.ai.EntityAIBase;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIAnimation
extends EntityAIBase {
    private EntityNPCInterface npc;
    private boolean isAttacking = false;
    private boolean isDead = false;
    private boolean isAtStartpoint = false;
    private boolean hasPath = false;
    private int tick = 4;

    public EntityAIAnimation(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public boolean func_75250_a() {
        boolean bl = this.isDead = !this.npc.func_70089_S();
        if (this.isDead) {
            return this.npc.currentAnimation != EnumAnimation.LYING;
        }
        if (this.npc.stats.aimType == 1 && this.npc.isAttacking()) {
            return this.npc.currentAnimation != EnumAnimation.AIMING;
        }
        if (this.npc.stats.aimType == 2 && this.npc.isAttacking() && this.npc.getRangedTask() != null && this.npc.getRangedTask().isShooting()) {
            return this.npc.currentAnimation != EnumAnimation.AIMING;
        }
        if (this.npc.ais.animationType == EnumAnimation.NONE) {
            return this.npc.currentAnimation != EnumAnimation.NONE;
        }
        this.isAttacking = this.npc.isAttacking();
        if (this.npc.ais.returnToStart) {
            this.isAtStartpoint = this.npc.isVeryNearAssignedPlace();
        }
        boolean bl2 = this.hasPath = !this.npc.func_70661_as().func_75500_f();
        if (this.npc.ais.movingType == EnumMovingType.Standing && this.hasNavigation() && this.npc.currentAnimation.getWalkingAnimation() == 0) {
            return this.npc.currentAnimation != EnumAnimation.NONE;
        }
        return this.npc.currentAnimation != this.npc.ais.animationType;
    }

    public void func_75246_d() {
        if (this.npc.stats.aimType == 1 && this.npc.isAttacking()) {
            this.setAnimation(EnumAnimation.AIMING);
            return;
        }
        if (this.npc.stats.aimType == 2 && this.npc.isAttacking() && this.npc.getRangedTask() != null && this.npc.getRangedTask().isShooting()) {
            this.setAnimation(EnumAnimation.AIMING);
            return;
        }
        EnumAnimation type = this.npc.ais.animationType;
        if (this.isDead) {
            type = EnumAnimation.LYING;
        } else if (this.npc.ais.movingType == EnumMovingType.Standing && this.npc.ais.animationType.getWalkingAnimation() == 0 && this.hasNavigation()) {
            type = EnumAnimation.NONE;
        }
        this.setAnimation(type);
    }

    private void setAnimation(EnumAnimation animation) {
        this.npc.setCurrentAnimation(animation);
        this.npc.updateHitbox();
        this.npc.func_70107_b(this.npc.field_70165_t, this.npc.field_70163_u, this.npc.field_70161_v);
    }

    private boolean hasNavigation() {
        return this.isAttacking || this.npc.ais.returnToStart && !this.isAtStartpoint && !this.npc.isFollower() || this.hasPath;
    }
}

