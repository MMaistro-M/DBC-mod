/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.controllers.data.ability.util;

import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public class AbilityTargetHelper {
    public static boolean isAlly(EntityLivingBase caster, Entity target) {
        if (caster == null || target == null) {
            return false;
        }
        if (target == caster) {
            return true;
        }
        if (caster.field_70170_p == null || caster.field_70170_p.field_72995_K) {
            return false;
        }
        if (target instanceof EntityNPCInterface) {
            EntityNPCInterface targetNpc = (EntityNPCInterface)target;
            if (targetNpc.faction.isPassive) {
                return true;
            }
            if (caster instanceof EntityNPCInterface) {
                EntityNPCInterface casterNpc = (EntityNPCInterface)caster;
                if (casterNpc.faction.id == targetNpc.faction.id) {
                    return true;
                }
            }
            if (caster instanceof EntityPlayer) {
                EntityPlayer casterPlayer = (EntityPlayer)caster;
                if (PlayerData.get(casterPlayer) == null) {
                    return false;
                }
                if (targetNpc.faction.isFriendlyToPlayer(casterPlayer)) {
                    return true;
                }
            }
            return false;
        }
        if (target instanceof EntityPlayer && caster instanceof EntityNPCInterface) {
            EntityNPCInterface casterNpc = (EntityNPCInterface)caster;
            EntityPlayer targetPlayer = (EntityPlayer)target;
            if (PlayerData.get(targetPlayer) == null) {
                return false;
            }
            return casterNpc.faction.isFriendlyToPlayer(targetPlayer);
        }
        if (target instanceof EntityPlayer && caster instanceof EntityPlayer) {
            Party party;
            EntityPlayer casterPlayer = (EntityPlayer)caster;
            EntityPlayer targetPlayer = (EntityPlayer)target;
            PlayerData casterData = PlayerData.get(casterPlayer);
            PlayerData targetData = PlayerData.get(targetPlayer);
            if (casterData == null || targetData == null) {
                return false;
            }
            return casterData.partyUUID != null && casterData.partyUUID.equals(targetData.partyUUID) && (party = PartyController.Instance().getParty(casterData.partyUUID)) != null && !party.friendlyFire();
        }
        return false;
    }

    public static boolean shouldAffect(EntityLivingBase caster, Entity target, TargetFilter filter, boolean includeSelf) {
        if (caster == null || target == null || filter == null) {
            return false;
        }
        if (caster.field_70170_p == null || caster.field_70170_p.field_72995_K) {
            return false;
        }
        if (target == caster) {
            return includeSelf;
        }
        if (!(target instanceof EntityLivingBase)) {
            return false;
        }
        if (!target.func_70089_S()) {
            return false;
        }
        switch (filter) {
            case ALLIES: {
                return AbilityTargetHelper.isAlly(caster, target);
            }
            case ENEMIES: {
                return !AbilityTargetHelper.isAlly(caster, target);
            }
            case ALL: {
                return true;
            }
        }
        return false;
    }
}

