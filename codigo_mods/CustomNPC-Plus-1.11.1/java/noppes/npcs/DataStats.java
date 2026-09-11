/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.Resistances;
import noppes.npcs.constants.EnumParticleType;
import noppes.npcs.constants.EnumPotionType;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.ScriptParticle;
import noppes.npcs.util.ValueUtil;

public class DataStats {
    private float attackStrength = 5.0f;
    public int attackSpeed = 20;
    public int swingWarmUp = 0;
    public int attackRange = 2;
    public int knockback = 0;
    public int minDelay = 20;
    public int maxDelay = 40;
    public int rangedRange = 15;
    public int fireRate = 5;
    public int burstCount = 1;
    public int shotCount = 1;
    public int accuracy = 60;
    public int aggroRange = 16;
    public EnumPotionType potionType = EnumPotionType.None;
    public int potionDuration = 5;
    public int potionAmp = 0;
    public int potionManualId = 0;
    public double maxHealth = 20.0;
    public int respawnTime = 20;
    public int spawnCycle = 0;
    public boolean hideKilledBody = false;
    public boolean canDespawn = false;
    public boolean playerSetCanDespawn = false;
    public Resistances resistances = new Resistances();
    public MagicData magicData = new MagicData();
    public boolean ignoreCobweb = false;
    public boolean immuneToFire = false;
    public boolean potionImmune = false;
    public int drowningType = 1;
    public boolean burnInSun = false;
    public boolean noFallDamage = false;
    public float healthRegen = 1.0f;
    public float combatRegen = 0.0f;
    public float pDamage = 4.0f;
    public int pImpact = 0;
    public int pSize = 5;
    public int pSpeed = 10;
    public int pArea = 0;
    public int pDur = 5;
    public boolean pPhysics = true;
    public boolean pXlr8 = false;
    public boolean pGlows = false;
    public boolean pExplode = false;
    public boolean pRender3D = false;
    public boolean pSpin = false;
    public boolean pStick = false;
    public boolean pBurnItem = false;
    public EnumPotionType pEffect = EnumPotionType.None;
    public int pManualId = 0;
    public EnumParticleType pTrail = EnumParticleType.None;
    public ScriptParticle pCustom = new ScriptParticle("");
    public int pEffAmp = 0;
    public String fireSound = "random.bow";
    public boolean onSoundBegin = false;
    public boolean playBurstSound = true;
    public byte aimType = 0;
    public boolean projectilesKeepTerrain = false;
    public boolean projectileInvincibility = true;
    public EnumCreatureAttribute creatureType = EnumCreatureAttribute.UNDEFINED;
    private EntityNPCInterface npc;
    public boolean attackInvisible = false;
    public int collidesWith = 1;

    public DataStats(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74782_a("Resistances", (NBTBase)this.resistances.writeToNBT());
        compound.func_74780_a("MaxHealth", this.maxHealth);
        compound.func_74768_a("AggroRange", this.aggroRange);
        compound.func_74757_a("HideBodyWhenKilled", this.hideKilledBody);
        compound.func_74768_a("RespawnTime", this.respawnTime);
        compound.func_74768_a("SpawnCycle", this.spawnCycle);
        compound.func_74768_a("CreatureType", this.creatureType.ordinal());
        compound.func_74757_a("IgnoreCobweb", this.ignoreCobweb);
        compound.func_74776_a("HealthRegen", this.healthRegen);
        compound.func_74776_a("CombatRegen", this.combatRegen);
        compound.func_74776_a("AttackStrenght", this.attackStrength);
        compound.func_74768_a("AttackRange", this.attackRange);
        compound.func_74768_a("AttackSpeed", this.attackSpeed);
        compound.func_74768_a("SwingWarmup", this.swingWarmUp);
        compound.func_74768_a("KnockBack", this.knockback);
        compound.func_74768_a("PotionEffect", this.potionType.ordinal());
        compound.func_74768_a("PotionDuration", this.potionDuration);
        compound.func_74768_a("PotionAmp", this.potionAmp);
        if (this.potionType == EnumPotionType.Manual) {
            compound.func_74768_a("PotionManualId", this.potionManualId);
        }
        compound.func_74768_a("MaxFiringRange", this.rangedRange);
        compound.func_74768_a("FireRate", this.fireRate);
        compound.func_74768_a("minDelay", this.minDelay);
        compound.func_74768_a("maxDelay", this.maxDelay);
        compound.func_74768_a("BurstCount", this.burstCount);
        compound.func_74768_a("ShotCount", this.shotCount);
        compound.func_74768_a("Accuracy", this.accuracy);
        compound.func_74776_a("pDamage", this.pDamage);
        compound.func_74768_a("pImpact", this.pImpact);
        compound.func_74768_a("pSize", this.pSize);
        compound.func_74768_a("pSpeed", this.pSpeed);
        compound.func_74768_a("pArea", this.pArea);
        compound.func_74768_a("pDur", this.pDur);
        compound.func_74757_a("pPhysics", this.pPhysics);
        compound.func_74757_a("pXlr8", this.pXlr8);
        compound.func_74757_a("pGlows", this.pGlows);
        compound.func_74757_a("pExplode", this.pExplode);
        compound.func_74757_a("pRender3D", this.pRender3D);
        compound.func_74757_a("pSpin", this.pSpin);
        compound.func_74757_a("pStick", this.pStick);
        compound.func_74768_a("pEffect", this.pEffect.ordinal());
        compound.func_74768_a("pTrail", this.pTrail.ordinal());
        compound.func_74768_a("pEffAmp", this.pEffAmp);
        compound.func_74778_a("FiringSound", this.fireSound);
        compound.func_74757_a("FiringSoundBegin", this.onSoundBegin);
        compound.func_74774_a("AimType", this.aimType);
        compound.func_74757_a("ProjectilesKeepTerrain", this.projectilesKeepTerrain);
        compound.func_74757_a("ProjectileInvincibility", this.projectileInvincibility);
        if (this.pTrail == EnumParticleType.Custom) {
            compound.func_74782_a("pCustom", (NBTBase)this.pCustom.writeToNBT());
        }
        if (this.pEffect == EnumPotionType.Fire) {
            compound.func_74757_a("pBurnItem", this.pBurnItem);
        }
        if (this.pEffect == EnumPotionType.Manual) {
            compound.func_74768_a("pManualId", this.pManualId);
        }
        compound.func_74757_a("ImmuneToFire", this.immuneToFire);
        compound.func_74757_a("PotionImmune", this.potionImmune);
        compound.func_74768_a("DrowningType", this.drowningType);
        compound.func_74757_a("BurnInSun", this.burnInSun);
        compound.func_74757_a("NoFallDamage", this.noFallDamage);
        compound.func_74757_a("AttackInvisible", this.attackInvisible);
        compound.func_74757_a("CanDespawn", this.canDespawn);
        compound.func_74757_a("PlayerSetCanDespawn", this.playerSetCanDespawn);
        compound.func_74768_a("CollidesWith", this.collidesWith);
        this.magicData.writeToNBT(compound);
        return compound;
    }

    public void readToNBT(NBTTagCompound compound) {
        this.resistances.readToNBT(compound.func_74775_l("Resistances"));
        this.setMaxHealth(compound.func_74769_h("MaxHealth"));
        this.hideKilledBody = compound.func_74767_n("HideBodyWhenKilled");
        this.aggroRange = compound.func_74762_e("AggroRange");
        this.respawnTime = compound.func_74762_e("RespawnTime");
        this.spawnCycle = compound.func_74762_e("SpawnCycle");
        this.creatureType = EnumCreatureAttribute.values()[compound.func_74762_e("CreatureType") % EnumCreatureAttribute.values().length];
        this.ignoreCobweb = compound.func_74767_n("IgnoreCobweb");
        this.healthRegen = compound.func_74760_g("HealthRegen");
        this.combatRegen = compound.func_74760_g("CombatRegen");
        this.setAttackStrength(compound.func_74760_g("AttackStrenght"));
        this.attackSpeed = compound.func_74762_e("AttackSpeed");
        this.swingWarmUp = ValueUtil.clamp(compound.func_74762_e("SwingWarmup"), 0, 1000);
        this.attackRange = compound.func_74762_e("AttackRange");
        this.knockback = compound.func_74762_e("KnockBack");
        this.potionType = EnumPotionType.fromOrdinal(compound.func_74762_e("PotionEffect"));
        this.potionDuration = compound.func_74762_e("PotionDuration");
        this.potionAmp = compound.func_74762_e("PotionAmp");
        if (this.potionType == EnumPotionType.Manual) {
            this.potionManualId = compound.func_74762_e("PotionManualId");
        }
        this.rangedRange = compound.func_74762_e("MaxFiringRange");
        this.fireRate = compound.func_74762_e("FireRate");
        this.minDelay = ValueUtil.clamp(compound.func_74762_e("minDelay"), 1, 9999);
        this.maxDelay = ValueUtil.clamp(compound.func_74762_e("maxDelay"), 1, 9999);
        this.burstCount = compound.func_74762_e("BurstCount");
        this.shotCount = ValueUtil.clamp(compound.func_74762_e("ShotCount"), 1, 10);
        this.accuracy = compound.func_74762_e("Accuracy");
        this.pDamage = compound.func_74760_g("pDamage");
        this.pImpact = compound.func_74762_e("pImpact");
        this.pSize = compound.func_74762_e("pSize");
        this.pSpeed = compound.func_74762_e("pSpeed");
        this.pArea = compound.func_74762_e("pArea");
        this.pDur = compound.func_74762_e("pDur");
        this.pPhysics = compound.func_74767_n("pPhysics");
        this.pXlr8 = compound.func_74767_n("pXlr8");
        this.pGlows = compound.func_74767_n("pGlows");
        this.pExplode = compound.func_74767_n("pExplode");
        this.pRender3D = compound.func_74767_n("pRender3D");
        this.pSpin = compound.func_74767_n("pSpin");
        this.pStick = compound.func_74767_n("pStick");
        this.pEffect = EnumPotionType.fromOrdinal(compound.func_74762_e("pEffect"));
        this.pTrail = EnumParticleType.values()[compound.func_74762_e("pTrail") % EnumParticleType.values().length];
        this.pEffAmp = compound.func_74762_e("pEffAmp");
        this.fireSound = compound.func_74779_i("FiringSound");
        this.onSoundBegin = compound.func_74767_n("FiringSoundBegin");
        this.aimType = compound.func_74771_c("AimType");
        this.projectilesKeepTerrain = compound.func_74767_n("ProjectilesKeepTerrain");
        this.projectileInvincibility = compound.func_74764_b("ProjectileInvincibility") ? compound.func_74767_n("ProjectileInvincibility") : true;
        if (this.pTrail == EnumParticleType.Custom) {
            this.pCustom = ScriptParticle.fromNBT(compound.func_74775_l("pCustom"));
        }
        if (this.pEffect == EnumPotionType.Fire) {
            this.pBurnItem = compound.func_74767_n("pBurnItem");
        }
        if (this.pEffect == EnumPotionType.Manual) {
            this.pManualId = compound.func_74762_e("pManualId");
        }
        this.immuneToFire = compound.func_74767_n("ImmuneToFire");
        this.potionImmune = compound.func_74767_n("PotionImmune");
        this.drowningType = compound.func_74762_e("DrowningType");
        this.burnInSun = compound.func_74767_n("BurnInSun");
        this.noFallDamage = compound.func_74767_n("NoFallDamage");
        this.attackInvisible = compound.func_74767_n("AttackInvisible");
        this.canDespawn = compound.func_74767_n("CanDespawn");
        this.playerSetCanDespawn = compound.func_74767_n("PlayerSetCanDespawn");
        this.magicData.readToNBT(compound);
        this.npc.setImmuneToFire(this.immuneToFire);
        this.collidesWith = compound.func_74764_b("CollidesWith") ? compound.func_74762_e("CollidesWith") : 1;
    }

    public float getAttackStrength() {
        return this.attackStrength;
    }

    public void setAttackStrength(double strength) {
        this.attackStrength = (float)Math.floor(strength);
        this.npc.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((double)this.attackStrength);
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = Math.floor(maxHealth);
        this.npc.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
    }
}

