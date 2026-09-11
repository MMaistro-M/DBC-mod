/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 */
package noppes.npcs.ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import kamkeel.npcs.addon.DBCAddon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.entity.EntityNPCInterface;

public class CombatHandler {
    private Map<EntityLivingBase, Float> aggressors = new HashMap<EntityLivingBase, Float>();
    private Map<EntityLivingBase, LinkedList<Float>> recentDamages = new HashMap<EntityLivingBase, LinkedList<Float>>();
    private EntityNPCInterface npc;
    private long startTime = 0L;
    private int combatResetTimer = 0;

    public CombatHandler(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public void update() {
        if (this.npc.isKilled()) {
            if (this.npc.isAttacking()) {
                this.reset();
            }
            return;
        }
        if (this.npc.func_70638_az() != null && !this.npc.isAttacking()) {
            this.start();
        }
        if (!this.shouldCombatContinue()) {
            if (this.combatResetTimer++ > 40) {
                this.reset();
            }
            return;
        }
        this.combatResetTimer = 0;
        EntityLivingBase target = this.npc.func_70638_az();
        if (target != null && this.npc.abilities.enabled && !this.npc.abilities.isExecutingAbility()) {
            this.npc.abilities.trySelectAndStart(target);
        }
    }

    private boolean shouldCombatContinue() {
        if (this.npc.func_70638_az() == null) {
            return false;
        }
        return this.isValidTarget(this.npc.func_70638_az());
    }

    public void damage(DamageSource source, float damageAmount) {
        this.combatResetTimer = 0;
        Entity e = NoppesUtilServer.GetDamageSource(source);
        if (e instanceof EntityLivingBase) {
            Float f;
            EntityLivingBase el = (EntityLivingBase)e;
            LinkedList recentDamageList = this.recentDamages.computeIfAbsent(el, k -> new LinkedList());
            recentDamageList.addLast(Float.valueOf(damageAmount));
            if (recentDamageList.size() > 15) {
                recentDamageList.removeFirst();
            }
            if ((f = this.aggressors.get(el)) == null) {
                f = Float.valueOf(0.0f);
            }
            this.aggressors.put(el, Float.valueOf(f.floatValue() + damageAmount));
        }
        this.npc.abilities.onDamage(source, damageAmount);
    }

    public void start() {
        this.combatResetTimer = 0;
        this.startTime = this.npc.field_70170_p.func_72912_H().func_82573_f();
        this.npc.setBoolFlag(true, 4);
    }

    public void reset() {
        this.combatResetTimer = 0;
        this.startTime = 0L;
        this.aggressors.clear();
        this.recentDamages.clear();
        this.npc.setBoolFlag(false, 4);
        this.npc.abilities.reset();
    }

    public boolean checkTarget() {
        if (this.aggressors.isEmpty() || this.npc.field_70173_aa % 10 != 0) {
            return false;
        }
        EntityLivingBase target = this.npc.func_70638_az();
        Float current = Float.valueOf(0.0f);
        if (this.isValidTarget(target)) {
            current = this.aggressors.get(target);
            if (current == null) {
                current = Float.valueOf(0.0f);
            }
        } else {
            target = null;
        }
        for (Map.Entry<EntityLivingBase, Float> entry : this.aggressors.entrySet()) {
            if (!(entry.getValue().floatValue() > current.floatValue()) || !this.isValidTarget(entry.getKey())) continue;
            current = entry.getValue();
            target = entry.getKey();
        }
        return target == null;
    }

    public boolean isValidTarget(EntityLivingBase target) {
        if (target == null || !target.func_70089_S()) {
            return false;
        }
        if (target instanceof EntityPlayer && (((EntityPlayer)target).field_71075_bZ.field_75102_a || DBCAddon.instance.isKO(this.npc, (EntityPlayer)target))) {
            return false;
        }
        return this.npc.isInRange((Entity)target, this.npc.stats.aggroRange);
    }

    public float calculateThreatLevel(EntityLivingBase entity) {
        float threatLevel = 0.0f;
        LinkedList<Float> recentDamageList = this.recentDamages.get(entity);
        if (recentDamageList != null) {
            long currentTime = this.npc.field_70170_p.func_72912_H().func_82573_f();
            for (Float damage : recentDamageList) {
                float decayFactor = Math.max(0.0f, 1.0f - (float)this.startTime / (float)currentTime);
                threatLevel += damage.floatValue() * decayFactor;
            }
        }
        return threatLevel;
    }

    public boolean shouldChangeTarget(double chance) {
        double randomNum = Math.random() * 100.0;
        return randomNum < chance;
    }

    public boolean shouldSwitchTactically(EntityLivingBase originalTarget, EntityLivingBase newTarget, boolean reverse) {
        float currentThreat = this.calculateThreatLevel(originalTarget);
        float newThreat = this.calculateThreatLevel(newTarget);
        if (reverse) {
            return newThreat < currentThreat;
        }
        return newThreat > currentThreat;
    }

    public boolean isExecutingAbility() {
        return this.npc.abilities.isExecutingAbility();
    }

    public boolean isAbilityControllingMovement() {
        return this.npc.abilities.isAbilityControllingMovement();
    }

    public boolean shouldBlockAttack() {
        return this.npc.abilities.shouldBlockAttack();
    }
}

