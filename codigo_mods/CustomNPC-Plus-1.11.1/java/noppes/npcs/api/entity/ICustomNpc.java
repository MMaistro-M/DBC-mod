/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.EntityCreature;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.ability.IDataAbilities;
import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.entity.data.IHitboxData;
import noppes.npcs.api.entity.data.IModelData;
import noppes.npcs.api.entity.data.ITintData;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.IOverlayHandler;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IFaction;
import noppes.npcs.api.handler.data.ILines;
import noppes.npcs.api.handler.data.IMagicData;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.jobs.IJob;
import noppes.npcs.api.roles.IRole;

public interface ICustomNpc<T extends EntityCreature>
extends IEntityLiving<T>,
IAnimatable {
    public int getSize();

    public void setSize(int var1);

    public int getModelType();

    public void setModelType(int var1);

    public String getName();

    @Override
    public void setRotation(float var1);

    public void setRotationType(int var1);

    public int getRotationType();

    public void setMovingType(int var1);

    public int getMovingType();

    public void setName(String var1);

    public String getTitle();

    public void setTitle(String var1);

    public String getTexture();

    public void setTexture(String var1);

    public IPos getHome();

    public int getHomeX();

    public void setHomeX(int var1);

    public int getHomeY();

    public void setHomeY(int var1);

    public int getHomeZ();

    public void setHomeZ(int var1);

    public void setHome(int var1, int var2, int var3);

    public void setHome(IPos var1);

    @Override
    public void setMaxHealth(double var1);

    public void setReturnToHome(boolean var1);

    public boolean getReturnToHome();

    public IFaction getFaction();

    public void setFaction(int var1);

    public void setAttackFactions(boolean var1);

    public boolean getAttackFactions();

    public void setDefendFaction(boolean var1);

    public boolean getDefendFaction();

    @Override
    public int getType();

    @Override
    public boolean typeOf(int var1);

    public void shootItem(IEntityLivingBase var1, IItemStack var2, int var3);

    public void setProjectilesKeepTerrain(boolean var1);

    public boolean getProjectilesKeepTerrain();

    public void setProjectileInvincibility(boolean var1);

    public boolean getProjectileInvincibility();

    public void say(String var1);

    public void say(IPlayer var1, String var2);

    public IDialog getDialog(int var1);

    public int getDialogId(int var1);

    public void setDialog(int var1, IDialog var2);

    public void setDialog(int var1, int var2);

    public ILines getInteractLines();

    public ILines getWorldLines();

    public ILines getAttackLines();

    public ILines getKilledLines();

    public ILines getKillLines();

    public boolean getOrderedLines();

    public void setOrderedLines(boolean var1);

    public String getIdleSound();

    public void setIdleSound(String var1);

    public String getAngrySound();

    public void setAngrySound(String var1);

    public String getHurtSound();

    public void setHurtSound(String var1);

    public String getDeathSound();

    public void setDeathSound(String var1);

    public String getStepSound();

    public void setStepSound(String var1);

    public boolean getDisablePitch();

    public void setDisablePitch(boolean var1);

    public void kill();

    public void reset();

    @Override
    public IAnimationData getAnimationData();

    public IRole getRole();

    public void setRole(int var1);

    public IJob getJob();

    public void setJob(int var1);

    public IItemStack getRightItem();

    public void setRightItem(IItemStack var1);

    @Deprecated
    public IItemStack getLefttItem();

    public IItemStack getLeftItem();

    public void setLeftItem(IItemStack var1);

    public IItemStack getProjectileItem();

    public void setProjectileItem(IItemStack var1);

    @Deprecated
    public boolean canAimWhileShooting();

    @Deprecated
    public void aimWhileShooting(boolean var1);

    public void setAimType(byte var1);

    public byte getAimType();

    public void setMinProjectileDelay(int var1);

    public int getMinProjectileDelay();

    public void setMaxProjectileDelay(int var1);

    public int getMaxProjectileDelay();

    public void setRangedRange(int var1);

    public int getRangedRange();

    public void setFireRate(int var1);

    public int getFireRate();

    public void setBurstCount(int var1);

    public int getBurstCount();

    public void setShotCount(int var1);

    public int getShotCount();

    public void setAccuracy(int var1);

    public int getAccuracy();

    public String getFireSound();

    public void setFireSound(String var1);

    @Override
    public IItemStack getArmor(int var1);

    @Override
    public void setArmor(int var1, IItemStack var2);

    public IItemStack getLootItem(int var1);

    public void setLootItem(int var1, IItemStack var2);

    public double getLootChance(int var1);

    public void setLootChance(int var1, double var2);

    public int getLootMode();

    public void setLootMode(int var1);

    public void setMinLootXP(int var1);

    public void setMaxLootXP(int var1);

    public int getMinLootXP();

    public int getMaxLootXP();

    public boolean getCanDrown();

    public void setCanDrown(boolean var1);

    public void setDrowningType(int var1);

    public boolean canBreathe();

    public void setAnimation(int var1);

    public void setTacticalVariant(int var1);

    public int getTacticalVariant();

    public void setTacticalVariant(String var1);

    public String getTacticalVariantName();

    public String getCombatPolicyName();

    public void setCombatPolicy(int var1);

    public int getCombatPolicy();

    public void setCombatPolicy(String var1);

    public void setTacticalRadius(int var1);

    public int getTacticalRadius();

    public int getTacticalChance();

    public void setTacticalChance(int var1);

    public boolean getCanSwim();

    public void setCanSwim(boolean var1);

    public boolean getReactsToFire();

    public void setReactsToFire(boolean var1);

    public boolean getAvoidsWater();

    public void setAvoidsWater(boolean var1);

    public boolean getAvoidsSun();

    public void setAvoidsSun(boolean var1);

    public boolean getDirectLOS();

    public void setDirectLOS(boolean var1);

    public int getLeapType();

    public void setLeapType(int var1);

    public boolean getCanSprint();

    public void setCanSprint(boolean var1);

    public boolean getStopAndInteract();

    public void setStopAndInteract(boolean var1);

    public int getDoorInteract();

    public void setDoorInteract(int var1);

    public int getWalkingRange();

    public void setWalkingRange(int var1);

    public boolean getNpcInteracting();

    public void setNpcInteracting(boolean var1);

    public boolean getMovingPause();

    public void setMovingPause(boolean var1);

    public int getMovingPattern();

    public void setMovingPattern(int var1);

    public int getCanFireIndirect();

    public void setCanFireIndirect(int var1);

    public int getUseRangeMelee();

    public void setUseRangeMelee(int var1);

    public int getDistanceToMelee();

    public void setDistanceToMelee(int var1);

    public float getBodyOffsetX();

    public void setBodyOffsetX(float var1);

    public float getBodyOffsetY();

    public void setBodyOffsetY(float var1);

    public float getBodyOffsetZ();

    public void setBodyOffsetZ(float var1);

    public void setIgnoreCobweb(boolean var1);

    public boolean getIgnoreCobweb();

    public void setOnFoundEnemy(int var1);

    public int onFoundEnemy();

    public void setShelterFrom(int var1);

    public int getShelterFrom();

    public boolean hasLivingAnimation();

    public void setLivingAnimation(boolean var1);

    public void setVisibleType(int var1);

    public int getVisibleType();

    public void setVisibleTo(IPlayer var1, boolean var2);

    public boolean isVisibleTo(IPlayer var1);

    public void setShowName(int var1);

    public int getShowName();

    public int getShowBossBar();

    public void setShowBossBar(int var1);

    @Override
    public double getMeleeStrength();

    @Override
    public void setMeleeStrength(double var1);

    public int getMeleeSpeed();

    public void setMeleeSpeed(int var1);

    public int getMeleeRange();

    public void setMeleeRange(int var1);

    public int getSwingWarmup();

    public void setSwingWarmup(int var1);

    public int getKnockback();

    public void setKnockback(int var1);

    public int getAggroRange();

    public void setAggroRange(int var1);

    public float getRangedStrength();

    public void setRangedStrength(float var1);

    public int getRangedSpeed();

    public void setRangedSpeed(int var1);

    public int getRangedBurst();

    public void setRangedBurst(int var1);

    public int getRespawnTime();

    public void setRespawnTime(int var1);

    public int getRespawnCycle();

    public void setRespawnCycle(int var1);

    public boolean getHideKilledBody();

    public void hideKilledBody(boolean var1);

    public boolean naturallyDespawns();

    public void setNaturallyDespawns(boolean var1);

    public boolean spawnedFromSoulStone();

    public String getSoulStonePlayerName();

    public boolean isSoulStoneInit();

    public boolean getRefuseSoulStone();

    public void setRefuseSoulStone(boolean var1);

    public int getMinPointsToSoulStone();

    public void setMinPointsToSoulStone(int var1);

    public void giveItem(IPlayer var1, IItemStack var2);

    public void executeCommand(String var1);

    public IModelData getModelData();

    public IHitboxData getHitboxData();

    public ITintData getTintData();

    @Deprecated
    public void setHeadScale(float var1, float var2, float var3);

    @Deprecated
    public void setBodyScale(float var1, float var2, float var3);

    @Deprecated
    public void setArmsScale(float var1, float var2, float var3);

    @Deprecated
    public void setLegsScale(float var1, float var2, float var3);

    public void setExplosionResistance(float var1);

    public float getExplosionResistance();

    public void setMeleeResistance(float var1);

    public float getMeleeResistance();

    public void setArrowResistance(float var1);

    public float getArrowResistance();

    @Override
    public void setKnockbackResistance(double var1);

    @Override
    public double getKnockbackResistance();

    public boolean getDamageDisabled();

    public void setDamageDisabled(boolean var1);

    public boolean getNoFallDamage();

    public void setNoFallDamage(boolean var1);

    public boolean getImmuneToFire();

    public void setImmuneToFire(boolean var1);

    public boolean getPotionImmune();

    public void setPotionImmune(boolean var1);

    public boolean getBurnInSun();

    public void setBurnInSun(boolean var1);

    public boolean getAttackInvisible();

    public void setAttackInvisible(boolean var1);

    public int getCreatureType();

    public void setCreatureType(int var1);

    public void setRetaliateType(int var1);

    public float getCombatRegen();

    public void setCombatRegen(float var1);

    public float getHealthRegen();

    public void setHealthRegen(float var1);

    @Override
    public long getAge();

    public ITimers getTimers();

    public void setFly(int var1);

    public boolean canFly();

    public void setFlySpeed(double var1);

    public double getFlySpeed(double var1);

    public void setFlyGravity(double var1);

    public double getFlyGravity(double var1);

    public void setFlyHeightLimit(int var1);

    public int getFlyHeightLimit(int var1);

    public void limitFlyHeight(boolean var1);

    public boolean isFlyHeightLimited(boolean var1);

    @Override
    public void setSpeed(double var1);

    @Override
    public double getSpeed();

    public void setSkinType(byte var1);

    public byte getSkinType();

    public void setSkinUrl(String var1);

    public String getSkinUrl();

    public void setCloakTexture(String var1);

    public String getCloakTexture();

    public void setOverlayTexture(String var1);

    public String getOverlayTexture();

    public String getGlowTexture();

    public void setGlowTexture(String var1);

    public IOverlayHandler getOverlays();

    public void setCollisionType(int var1);

    public int getCollisionType();

    public void updateClient();

    public void updateAI();

    public IActionManager getActionManager();

    public IMagicData getMagicData();

    public IDataAbilities getAbilityData();

    public IEnergyProjectile[] getActiveEnergyProjectiles();
}

