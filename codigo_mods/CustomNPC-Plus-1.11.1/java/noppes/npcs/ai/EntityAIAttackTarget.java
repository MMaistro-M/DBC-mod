/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package noppes.npcs.ai;

import kamkeel.npcs.addon.DBCAddon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIAttackTarget
extends EntityAIBase {
    private World world;
    private EntityNPCInterface npc;
    private EntityLivingBase entityTarget;
    private int attackTick = 0;
    private PathEntity entityPathEntity;
    private int field_75445_i;
    private boolean navOverride = false;

    public EntityAIAttackTarget(EntityNPCInterface par1EntityLiving) {
        this.npc = par1EntityLiving;
        this.world = par1EntityLiving.field_70170_p;
        this.func_75248_a(this.navOverride ? AiMutex.PATHING : AiMutex.LOOK + AiMutex.PASSIVE);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.npc.func_70638_az();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S()) {
            return false;
        }
        if (this.npc.inventory.getProjectile() != null && this.npc.ais.useRangeMelee == 0) {
            return false;
        }
        if (this.npc.abilities.isAbilityControllingMovement()) {
            return false;
        }
        double var2 = this.npc.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
        double var3 = this.npc.ais.distanceToMelee * this.npc.ais.distanceToMelee;
        if (this.npc.ais.useRangeMelee == 1 && var2 > var3) {
            return false;
        }
        this.entityTarget = entitylivingbase;
        this.npc.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)this.npc.stats.aggroRange);
        this.entityPathEntity = this.npc.func_70661_as().func_75494_a((Entity)entitylivingbase);
        this.npc.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)ConfigMain.NpcNavRange);
        return this.entityPathEntity != null;
    }

    public boolean func_75253_b() {
        this.entityTarget = this.npc.func_70638_az();
        if (this.entityTarget == null || !this.entityTarget.func_70089_S()) {
            return false;
        }
        if (this.entityTarget instanceof EntityPlayer && DBCAddon.instance.isKO(this.npc, (EntityPlayer)this.entityTarget)) {
            return false;
        }
        if (this.npc.func_70032_d((Entity)this.entityTarget) > (float)this.npc.stats.aggroRange) {
            return false;
        }
        if (this.npc.ais.useRangeMelee == 1 && this.npc.func_70068_e((Entity)this.entityTarget) > (double)(this.npc.ais.distanceToMelee * this.npc.ais.distanceToMelee)) {
            return false;
        }
        return this.npc.func_110176_b(MathHelper.func_76128_c((double)this.entityTarget.field_70165_t), MathHelper.func_76128_c((double)this.entityTarget.field_70163_u), MathHelper.func_76128_c((double)this.entityTarget.field_70161_v));
    }

    public void func_75249_e() {
        if (!this.navOverride) {
            this.npc.func_70661_as().func_75484_a(this.entityPathEntity, 1.3);
        }
        this.field_75445_i = 0;
        if (this.npc.getRangedTask() != null && this.npc.ais.useRangeMelee == 2) {
            this.npc.getRangedTask().navOverride(true);
        }
    }

    public void func_75251_c() {
        this.entityPathEntity = null;
        this.entityTarget = null;
        this.npc.func_70624_b(null);
        this.npc.func_70661_as().func_75499_g();
        if (this.npc.getRangedTask() != null && this.npc.ais.useRangeMelee == 2) {
            this.npc.getRangedTask().navOverride(false);
        }
    }

    public void func_75246_d() {
        if (!this.npc.abilities.isRotationLocked()) {
            this.npc.func_70671_ap().func_75651_a((Entity)this.entityTarget, 30.0f, 30.0f);
        }
        if (!this.navOverride && !this.npc.abilities.isAbilityControllingMovement() && --this.field_75445_i <= 0) {
            this.field_75445_i = 4 + this.npc.func_70681_au().nextInt(7);
            this.npc.func_70661_as().func_75497_a((Entity)this.entityTarget, (double)1.3f);
        }
        this.attackTick = Math.max(this.attackTick - 1, 0);
        if (this.npc.abilities.shouldBlockAttack()) {
            return;
        }
        double distance = this.npc.func_70092_e(this.entityTarget.field_70165_t, this.entityTarget.field_70121_D.field_72338_b, this.entityTarget.field_70161_v);
        double minRange = this.npc.field_70130_N * 2.0f * this.npc.field_70130_N * 2.0f + this.entityTarget.field_70130_N;
        double range = (float)(this.npc.stats.attackRange * this.npc.stats.attackRange) + this.entityTarget.field_70130_N;
        if (minRange > range) {
            range = minRange;
        }
        if (distance <= range && (this.npc.canSee((Entity)this.entityTarget) || distance < minRange)) {
            if (this.attackTick <= 0) {
                this.attackTick = this.npc.stats.attackSpeed;
                if (this.npc.stats.swingWarmUp == 0) {
                    this.npc.func_70652_k((Entity)this.entityTarget);
                }
                this.npc.func_71038_i();
            } else if (this.npc.stats.swingWarmUp > 0 && this.attackTick == this.npc.stats.attackSpeed - this.npc.stats.swingWarmUp) {
                this.npc.func_70652_k((Entity)this.entityTarget);
            }
        }
    }

    public void navOverride(boolean nav) {
        this.navOverride = nav;
        this.func_75248_a(this.navOverride ? AiMutex.PATHING : AiMutex.LOOK + AiMutex.PASSIVE);
    }
}

