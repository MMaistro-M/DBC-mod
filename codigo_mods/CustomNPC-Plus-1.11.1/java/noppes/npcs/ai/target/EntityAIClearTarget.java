/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAITarget
 */
package noppes.npcs.ai.target;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIClearTarget
extends EntityAITarget {
    private EntityNPCInterface npc;
    private EntityLivingBase target;

    public EntityAIClearTarget(EntityNPCInterface npc) {
        super((EntityCreature)npc, false);
        this.npc = npc;
    }

    public boolean func_75250_a() {
        this.target = this.npc.func_70638_az();
        if (this.target == null) {
            return false;
        }
        if (this.npc.getOwner() != null && !this.npc.isInRange((Entity)this.npc.getOwner(), this.npc.stats.aggroRange * 2)) {
            return true;
        }
        return this.npc.combatHandler.checkTarget();
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(null);
        if (this.target == this.field_75299_d.func_70643_av()) {
            this.field_75299_d.func_70604_c(null);
        }
        super.func_75249_e();
    }
}

