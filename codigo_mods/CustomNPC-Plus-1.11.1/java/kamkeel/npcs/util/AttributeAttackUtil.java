/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import kamkeel.npcs.CustomAttributes;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.attribute.tracker.PlayerAttributeTracker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.MagicEntry;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public class AttributeAttackUtil {
    private static final Random random = new Random();
    public static Boolean lastAttackCritted = null;

    public static AllocationResult allocateMagicDamage(float physicalDamage, Map<Integer, MagicEntry> magicData) {
        HashMap<Integer, Float> allocation = new HashMap<Integer, Float>();
        float totalSplit = 0.0f;
        if (magicData != null) {
            for (Map.Entry<Integer, MagicEntry> entry : magicData.entrySet()) {
                int magicId = entry.getKey();
                float splitVal = entry.getValue().split;
                float allocated = physicalDamage * splitVal + entry.getValue().damage;
                allocation.put(magicId, Float.valueOf(allocated));
                totalSplit += splitVal;
            }
        }
        float leftover = physicalDamage * (float)Math.max(0.0, 1.0 - (double)totalSplit);
        return new AllocationResult(allocation, leftover);
    }

    public static void addAttributeMagicDamage(Map<Integer, Float> allocation, Map<Integer, Float> attributeDamage, Map<Integer, Float> magicBoost) {
        if (!ConfigMain.AttributesEnabled) {
            return;
        }
        for (Map.Entry<Integer, Float> entry : attributeDamage.entrySet()) {
            int magicId = entry.getKey();
            float damage = entry.getValue().floatValue();
            float boost = magicBoost.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue();
            allocation.put(magicId, Float.valueOf(allocation.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue() + (damage *= 1.0f + boost / 100.0f)));
        }
    }

    public static void applyMagicInteractions(Map<Integer, Float> allocation, Set<Integer> defenderMagicIDs, MagicController magicController) {
        for (Map.Entry<Integer, Float> entry : allocation.entrySet()) {
            int magicId = entry.getKey();
            float magicDamage = entry.getValue().floatValue();
            Magic magic = magicController.getMagic(magicId);
            if (magic == null || magic.interactions == null) continue;
            float multiplier = 1.0f;
            for (Map.Entry<Integer, Float> inter : magic.interactions.entrySet()) {
                if (!defenderMagicIDs.contains(inter.getKey())) continue;
                multiplier *= 1.0f + inter.getValue().floatValue();
            }
            entry.setValue(Float.valueOf(magicDamage * multiplier));
        }
    }

    public static float applyDefenderMagicDefense(Map<Integer, Float> allocation, PlayerAttributeTracker defender, MagicController magicController) {
        if (!ConfigMain.AttributesEnabled) {
            return 0.0f;
        }
        float adjusted = 0.0f;
        for (Map.Entry<Integer, Float> entry : allocation.entrySet()) {
            int magicId = entry.getKey();
            if (magicController.getMagic(magicId) == null) continue;
            float damage = entry.getValue().floatValue();
            float defense = defender.magicDefense.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue();
            float resistance = defender.magicResistance.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue();
            float totalDefense = defense * (1.0f + resistance / 100.0f);
            adjusted += Math.max(0.0f, damage -= totalDefense);
        }
        return adjusted;
    }

    public static float calculateDamagePlayerToPlayer(EntityPlayer attackPlayer, EntityPlayer defendPlayer, float baseDamage) {
        PlayerData attackerData = PlayerData.get(attackPlayer);
        PlayerData defenderData = PlayerData.get(defendPlayer);
        PlayerAttributeTracker attacker = AttributeController.getTracker(attackPlayer);
        PlayerAttributeTracker defender = AttributeController.getTracker(defendPlayer);
        float physicalDamage = AttributeAttackUtil.applyMainAttack(baseDamage, attacker);
        AllocationResult result = AttributeAttackUtil.allocateMagicDamage(physicalDamage, attackerData.magicData.getMagics());
        float leftover = AttributeAttackUtil.applyNeutral(result.leftover, attacker);
        AttributeAttackUtil.addAttributeMagicDamage(result.allocation, attacker.magicDamage, attacker.magicBoost);
        MagicController magicController = MagicController.getInstance();
        HashSet<Integer> defenderMagicIDs = new HashSet<Integer>(defenderData.magicData.getMagics().keySet());
        AttributeAttackUtil.applyMagicInteractions(result.allocation, defenderMagicIDs, magicController);
        float adjustedMagic = AttributeAttackUtil.applyDefenderMagicDefense(result.allocation, defender, magicController);
        return AttributeAttackUtil.applyCrit(leftover + adjustedMagic, attacker);
    }

    public static float calculateDamagePlayerToNPC(EntityPlayer attackPlayer, EntityNPCInterface npc, float baseDamage) {
        PlayerData attackerData = PlayerData.get(attackPlayer);
        PlayerAttributeTracker attacker = AttributeController.getTracker(attackPlayer);
        float physicalDamage = AttributeAttackUtil.applyMainAttack(baseDamage, attacker);
        AllocationResult result = AttributeAttackUtil.allocateMagicDamage(physicalDamage, attackerData.magicData.getMagics());
        float leftover = AttributeAttackUtil.applyNeutral(result.leftover, attacker);
        AttributeAttackUtil.addAttributeMagicDamage(result.allocation, attacker.magicDamage, attacker.magicBoost);
        MagicController magicController = MagicController.getInstance();
        HashSet<Integer> npcMagicIDs = new HashSet<Integer>();
        if (npc.stats != null && npc.stats.magicData != null) {
            npcMagicIDs = new HashSet<Integer>(npc.stats.magicData.getMagics().keySet());
        }
        AttributeAttackUtil.applyMagicInteractions(result.allocation, npcMagicIDs, magicController);
        float adjustedMagic = 0.0f;
        for (float val : result.allocation.values()) {
            adjustedMagic += val;
        }
        return AttributeAttackUtil.applyCrit(leftover + adjustedMagic, attacker);
    }

    public static float applyCrit(float damage, PlayerAttributeTracker tracker) {
        if (ConfigMain.AttributesEnabled && AttributeAttackUtil.rollCrit(tracker)) {
            damage = AttributeAttackUtil.applyCritDamage(damage, tracker);
        }
        return damage;
    }

    public static boolean rollCrit(PlayerAttributeTracker tracker) {
        if (ConfigMain.AttributesEnabled && tracker != null) {
            float critChance = tracker.getAttributeValue(CustomAttributes.CRITICAL_CHANCE);
            return random.nextFloat() < critChance / 100.0f;
        }
        return false;
    }

    public static float applyCritDamage(float damage, PlayerAttributeTracker tracker) {
        if (ConfigMain.AttributesEnabled && tracker != null) {
            float critBonus = tracker.getAttributeValue(CustomAttributes.CRITICAL_DAMAGE);
            damage = damage * (1.0f + (float)ConfigMain.AttributesCriticalBoost / 100.0f) + critBonus;
        }
        return damage;
    }

    public static float applyMainAttack(float damage, PlayerAttributeTracker tracker) {
        if (ConfigMain.AttributesEnabled) {
            float mainAttack = tracker.getAttributeValue(CustomAttributes.MAIN_ATTACK);
            float mainBoost = tracker.getAttributeValue(CustomAttributes.MAIN_BOOST) / 100.0f;
            damage = damage * (1.0f + mainBoost) + mainAttack;
        }
        return damage;
    }

    public static float applyNeutral(float leftover, PlayerAttributeTracker tracker) {
        if (ConfigMain.AttributesEnabled) {
            float neutralDamage = tracker.getAttributeValue(CustomAttributes.NEUTRAL_ATTACK);
            float neutralBoost = tracker.getAttributeValue(CustomAttributes.NEUTRAL_BOOST);
            leftover += neutralDamage * (1.0f + neutralBoost / 100.0f);
        }
        return leftover;
    }

    public static float calculateDamageNPCtoPlayer(EntityNPCInterface npc, EntityPlayer defendingPlayer, float baseDamage) {
        PlayerData defenderData = PlayerData.get(defendingPlayer);
        PlayerAttributeTracker defender = AttributeController.getTracker(defendingPlayer);
        if (npc.stats == null || npc.stats.magicData == null) {
            return baseDamage;
        }
        AllocationResult result = AttributeAttackUtil.allocateMagicDamage(baseDamage, npc.stats.magicData.getMagics());
        float leftover = result.leftover;
        MagicController magicController = MagicController.getInstance();
        HashSet<Integer> defenderMagicIDs = new HashSet<Integer>(defenderData.magicData.getMagics().keySet());
        AttributeAttackUtil.applyMagicInteractions(result.allocation, defenderMagicIDs, magicController);
        float adjustedMagic = AttributeAttackUtil.applyDefenderMagicDefense(result.allocation, defender, magicController);
        return leftover + adjustedMagic;
    }

    public static float calculateGearOutput(PlayerAttributeTracker attacker) {
        float neutralDamage = attacker.getAttributeValue(CustomAttributes.NEUTRAL_ATTACK);
        float neutralBoost = 1.0f + attacker.getAttributeValue(CustomAttributes.NEUTRAL_BOOST) / 100.0f;
        float neutralTotal = neutralDamage * neutralBoost;
        float magicTotal = 0.0f;
        MagicController magicController = MagicController.getInstance();
        for (Map.Entry<Integer, Float> entry : attacker.magicDamage.entrySet()) {
            int magicId = entry.getKey();
            if (magicController.getMagic(magicId) == null) continue;
            float attackMagic = entry.getValue().floatValue();
            float boostMagic = attacker.magicBoost.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue();
            magicTotal += Math.max(0.0f, attackMagic * (boostMagic / 100.0f + 1.0f));
        }
        return neutralTotal + magicTotal;
    }

    public static float calculateOutgoing(EntityPlayer player, float baseDamage) {
        if (!ConfigMain.AttributesEnabled) {
            return baseDamage;
        }
        PlayerAttributeTracker tracker = AttributeController.getTracker(player);
        float mainAttackFlat = tracker.getAttributeValue(CustomAttributes.MAIN_ATTACK);
        float mainBoostPercent = tracker.getAttributeValue(CustomAttributes.MAIN_BOOST) / 100.0f;
        float mainDamage = baseDamage * (1.0f + mainBoostPercent) + mainAttackFlat;
        float gearDamage = tracker.gearOutput;
        return mainDamage + gearDamage;
    }

    public static float calculateMagicInteractionMultiplier(MagicData attackerMagic, MagicData defenderMagic) {
        if (attackerMagic == null || defenderMagic == null) {
            return 1.0f;
        }
        if (attackerMagic.isEmpty() || defenderMagic.isEmpty()) {
            return 1.0f;
        }
        MagicController mc = MagicController.getInstance();
        float multiplier = 1.0f;
        for (int attackMagicId : attackerMagic.getMagics().keySet()) {
            Magic magic = mc.getMagic(attackMagicId);
            if (magic == null || magic.interactions == null) continue;
            for (Map.Entry<Integer, Float> interaction : magic.interactions.entrySet()) {
                if (!defenderMagic.getMagics().containsKey(interaction.getKey())) continue;
                multiplier *= 1.0f + interaction.getValue().floatValue();
            }
        }
        return multiplier;
    }

    public static void applyMagicBoostToAllocation(Map<Integer, Float> allocation, Map<Integer, Float> magicBoost) {
        if (!ConfigMain.AttributesEnabled) {
            return;
        }
        for (Map.Entry<Integer, Float> entry : allocation.entrySet()) {
            float boost = magicBoost.getOrDefault(entry.getKey(), Float.valueOf(0.0f)).floatValue();
            if (boost == 0.0f) continue;
            entry.setValue(Float.valueOf(entry.getValue().floatValue() * (1.0f + boost / 100.0f)));
        }
    }

    public static float applyMagicBoostToHealth(EntityPlayer caster, MagicData magicData, float health) {
        if (!ConfigMain.AttributesEnabled) {
            return health;
        }
        if (magicData == null || magicData.isEmpty()) {
            return health;
        }
        PlayerAttributeTracker tracker = AttributeController.getTracker(caster);
        if (tracker == null) {
            return health;
        }
        float multiplier = 1.0f;
        for (Map.Entry<Integer, MagicEntry> entry : magicData.getMagics().entrySet()) {
            float split = entry.getValue().split;
            float boost = tracker.magicBoost.getOrDefault(entry.getKey(), Float.valueOf(0.0f)).floatValue();
            multiplier += split * (boost / 100.0f);
        }
        return health * multiplier;
    }

    public static float calculateAbilityDamage(EntityLivingBase caster, EntityLivingBase target, float damage, MagicData abilityMagic) {
        PlayerAttributeTracker defenderTracker;
        PlayerAttributeTracker tracker;
        if (abilityMagic == null || abilityMagic.isEmpty()) {
            return damage;
        }
        AllocationResult result = AttributeAttackUtil.allocateMagicDamage(damage, abilityMagic.getMagics());
        float leftover = result.leftover;
        if (caster instanceof EntityPlayer && ConfigMain.AttributesEnabled && (tracker = AttributeController.getTracker((EntityPlayer)caster)) != null) {
            AttributeAttackUtil.applyMagicBoostToAllocation(result.allocation, tracker.magicBoost);
        }
        MagicController magicController = MagicController.getInstance();
        HashSet<Integer> defenderMagicIDs = null;
        if (target instanceof EntityPlayer) {
            PlayerData defenderData = PlayerData.get((EntityPlayer)target);
            if (defenderData != null) {
                defenderMagicIDs = new HashSet<Integer>(defenderData.magicData.getMagics().keySet());
            }
        } else if (target instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)target;
            if (npc.stats != null && npc.stats.magicData != null) {
                defenderMagicIDs = new HashSet<Integer>(npc.stats.magicData.getMagics().keySet());
            }
        }
        if (defenderMagicIDs != null) {
            AttributeAttackUtil.applyMagicInteractions(result.allocation, defenderMagicIDs, magicController);
        }
        if (target instanceof EntityPlayer && ConfigMain.AttributesEnabled && (defenderTracker = AttributeController.getTracker((EntityPlayer)target)) != null) {
            float adjustedMagic = AttributeAttackUtil.applyDefenderMagicDefense(result.allocation, defenderTracker, magicController);
            return leftover + adjustedMagic;
        }
        float magicTotal = 0.0f;
        for (float val : result.allocation.values()) {
            magicTotal += val;
        }
        return leftover + magicTotal;
    }

    public static class AllocationResult {
        public Map<Integer, Float> allocation;
        public float leftover;

        public AllocationResult(Map<Integer, Float> allocation, float leftover) {
            this.allocation = allocation;
            this.leftover = leftover;
        }
    }
}

