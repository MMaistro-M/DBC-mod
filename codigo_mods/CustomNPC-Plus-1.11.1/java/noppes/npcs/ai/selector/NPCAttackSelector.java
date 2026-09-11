/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.potion.Potion
 */
package noppes.npcs.ai.selector;

import kamkeel.npcs.addon.DBCAddon;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.constants.EnumCompanionJobs;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobGuard;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.companion.CompanionGuard;

public class NPCAttackSelector
implements IEntitySelector {
    private EntityNPCInterface npc;

    public NPCAttackSelector(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public boolean func_82704_a(Entity entity) {
        if (!entity.func_70089_S() || entity == this.npc || this.npc.func_70032_d(entity) > (float)this.npc.stats.aggroRange || !(entity instanceof EntityLivingBase) || ((EntityLivingBase)entity).func_110143_aJ() < 1.0f) {
            return false;
        }
        if (this.npc.ais.directLOS && !this.npc.func_70635_at().func_75522_a(entity)) {
            return false;
        }
        if (!this.npc.stats.attackInvisible && ((EntityLivingBase)entity).func_70644_a(Potion.field_76441_p) && !this.npc.isInRange(entity, 3.0)) {
            return false;
        }
        if (!this.npc.isFollower() && this.npc.ais.returnToStart) {
            int allowedDistance = this.npc.stats.aggroRange * 2;
            if (this.npc.ais.movingType == EnumMovingType.Wandering) {
                allowedDistance += this.npc.ais.walkingRange;
            }
            double distance = entity.func_70092_e((double)this.npc.getStartXPos(), this.npc.getStartYPos(), (double)this.npc.getStartZPos());
            if (this.npc.ais.movingType == EnumMovingType.MovingPath) {
                int[] arr = this.npc.ais.getCurrentMovingPath();
                distance = entity.func_70092_e((double)arr[0], (double)arr[1], (double)arr[2]);
            }
            if (distance > (double)(allowedDistance * allowedDistance)) {
                return false;
            }
        }
        if (this.npc.advanced.job == EnumJobType.Guard && ((JobGuard)this.npc.jobInterface).isEntityApplicable(entity)) {
            return true;
        }
        if (this.npc.advanced.role == EnumRoleType.Companion) {
            RoleCompanion role = (RoleCompanion)this.npc.roleInterface;
            if (role.job == EnumCompanionJobs.GUARD && ((CompanionGuard)role.jobInterface).isEntityApplicable(entity)) {
                return true;
            }
        }
        if (entity instanceof EntityPlayerMP) {
            if (this.npc.faction.isAggressiveToPlayer((EntityPlayer)entity)) {
                if (PixelmonHelper.Enabled && this.npc.advanced.job == EnumJobType.Spawner) {
                    return PixelmonHelper.canBattle((EntityPlayerMP)entity, this.npc);
                }
                if (DBCAddon.instance.isKO(this.npc, (EntityPlayer)entity)) {
                    return false;
                }
                return !((EntityPlayerMP)entity).field_71075_bZ.field_75102_a;
            }
            return false;
        }
        if (entity instanceof EntityNPCInterface) {
            if (((EntityNPCInterface)entity).isKilled()) {
                return false;
            }
            if (this.npc.advanced.attackOtherFactions) {
                return this.npc.faction.isAggressiveToNpc((EntityNPCInterface)entity);
            }
        }
        return false;
    }
}

