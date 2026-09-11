/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.ability.IDataAbilities;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEnergyProjectile;
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
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumCombatPolicy;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.constants.EnumStandingType;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleMount;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptLiving;
import noppes.npcs.scripted.roles.ScriptJobBard;
import noppes.npcs.scripted.roles.ScriptJobConversation;
import noppes.npcs.scripted.roles.ScriptJobFollower;
import noppes.npcs.scripted.roles.ScriptJobGuard;
import noppes.npcs.scripted.roles.ScriptJobHealer;
import noppes.npcs.scripted.roles.ScriptJobInterface;
import noppes.npcs.scripted.roles.ScriptJobItemGiver;
import noppes.npcs.scripted.roles.ScriptJobSpawner;
import noppes.npcs.scripted.roles.ScriptRoleBank;
import noppes.npcs.scripted.roles.ScriptRoleCompanion;
import noppes.npcs.scripted.roles.ScriptRoleFollower;
import noppes.npcs.scripted.roles.ScriptRoleInterface;
import noppes.npcs.scripted.roles.ScriptRoleMailman;
import noppes.npcs.scripted.roles.ScriptRoleTrader;
import noppes.npcs.scripted.roles.ScriptRoleTransporter;
import noppes.npcs.scripted.wrapper.ScriptDataAbilities;
import noppes.npcs.util.ValueUtil;

public class ScriptNpc<T extends EntityNPCInterface>
extends ScriptLiving<T>
implements ICustomNpc {
    public EntityNPCInterface npc;

    public ScriptNpc(T npc) {
        super(npc);
        this.npc = npc;
    }

    @Override
    public int getSize() {
        return this.npc.display.modelSize;
    }

    @Override
    public void setSize(int size) {
        if (size < 1) {
            size = 1;
        }
        if (size > ConfigMain.NpcSizeLimit) {
            size = ConfigMain.NpcSizeLimit;
        }
        this.npc.display.modelSize = size;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public int getModelType() {
        return this.npc.display.modelType;
    }

    @Override
    public void setModelType(int modelType) {
        if (modelType > 2) {
            modelType = 2;
        } else if (modelType < 0) {
            modelType = 0;
        }
        this.npc.display.modelType = modelType;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getName() {
        return this.npc.display.name;
    }

    @Override
    public void setRotation(float rotationYaw, float rotationPitch) {
        this.setRotation(rotationYaw);
        this.npc.field_70125_A = rotationPitch;
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
        int r = (int)rotation;
        if (this.npc.ais.orientation != r) {
            this.npc.updateClient = true;
            this.npc.ais.orientation = r;
            this.npc.field_70177_z = this.npc.field_70761_aq = (float)this.npc.ais.orientation;
            this.npc.field_70759_as = this.npc.field_70761_aq;
            this.npc.field_70760_ar = r;
            this.npc.field_70126_B = r;
            this.npc.field_70758_at = r;
        }
    }

    @Override
    public void setRotationType(int rotationType) {
        for (EnumStandingType e : EnumStandingType.values()) {
            if (e.ordinal() != rotationType) continue;
            this.npc.ais.standingType = e;
            break;
        }
    }

    @Override
    public int getRotationType() {
        return this.npc.ais.standingType.ordinal();
    }

    @Override
    public void setMovingType(int movingType) {
        for (EnumMovingType e : EnumMovingType.values()) {
            if (e.ordinal() != movingType) continue;
            this.npc.ais.movingType = e;
            break;
        }
    }

    @Override
    public int getMovingType() {
        return this.npc.ais.movingType.ordinal();
    }

    @Override
    public void setName(String name) {
        this.npc.display.name = name;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getTitle() {
        return this.npc.display.title;
    }

    @Override
    public void setTitle(String title) {
        this.npc.display.title = title;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getTexture() {
        return this.npc.display.texture;
    }

    @Override
    public void setTexture(String texture) {
        this.npc.display.texture = texture;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public IPos getHome() {
        return NpcAPI.Instance().getIPos(this.npc.ais.startPos[0], this.npc.ais.startPos[1], this.npc.ais.startPos[2]);
    }

    @Override
    public int getHomeX() {
        return this.npc.getStartPos()[0];
    }

    @Override
    public void setHomeX(int x) {
        this.npc.ais.startPos[0] = x;
    }

    @Override
    public int getHomeY() {
        return this.npc.getStartPos()[1];
    }

    @Override
    public void setHomeY(int y) {
        this.npc.ais.startPos[1] = y;
    }

    @Override
    public int getHomeZ() {
        return this.npc.getStartPos()[2];
    }

    @Override
    public void setHomeZ(int z) {
        this.npc.ais.startPos[2] = z;
    }

    @Override
    public void setHome(int x, int y, int z) {
        this.npc.ais.startPos = new int[]{x, y, z};
    }

    @Override
    public void setHome(IPos pos) {
        this.npc.ais.startPos = new int[]{pos.getX(), pos.getY(), pos.getZ()};
    }

    @Override
    public void setMaxHealth(double health) {
        this.npc.stats.setMaxHealth(health);
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public void setReturnToHome(boolean bo) {
        if (this.npc.advanced.role == EnumRoleType.Mount && this.npc.roleInterface instanceof RoleMount) {
            ((RoleMount)this.npc.roleInterface).setReturnToStartPreference(bo);
        } else {
            this.npc.ais.returnToStart = bo;
        }
    }

    @Override
    public boolean getReturnToHome() {
        if (this.npc.advanced.role == EnumRoleType.Mount && this.npc.roleInterface instanceof RoleMount) {
            return ((RoleMount)this.npc.roleInterface).getReturnToStartPreference();
        }
        return this.npc.ais.returnToStart;
    }

    @Override
    public IFaction getFaction() {
        return FactionController.getInstance().get(this.npc.faction.id);
    }

    @Override
    public void setFaction(int id) {
        this.npc.setFaction(id);
    }

    @Override
    public void setAttackFactions(boolean attackOtherFactions) {
        this.npc.advanced.attackOtherFactions = attackOtherFactions;
    }

    @Override
    public boolean getAttackFactions() {
        return this.npc.advanced.attackOtherFactions;
    }

    @Override
    public void setDefendFaction(boolean defendFaction) {
        this.npc.advanced.defendFaction = defendFaction;
    }

    @Override
    public boolean getDefendFaction() {
        return this.npc.advanced.defendFaction;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 2 ? true : super.typeOf(type);
    }

    @Override
    public void shootItem(IEntityLivingBase target, IItemStack item, int accuracy) {
        if (item == null) {
            return;
        }
        if (accuracy < 0) {
            accuracy = 0;
        } else if (accuracy > 100) {
            accuracy = 100;
        }
        this.npc.shoot((EntityLivingBase)target.getMCEntity(), accuracy, item.getMCItemStack(), false);
    }

    @Override
    public void setProjectilesKeepTerrain(boolean t) {
        this.npc.stats.projectilesKeepTerrain = t;
    }

    @Override
    public boolean getProjectilesKeepTerrain() {
        return this.npc.stats.projectilesKeepTerrain;
    }

    @Override
    public void setProjectileInvincibility(boolean allowed) {
        this.npc.stats.projectileInvincibility = allowed;
    }

    @Override
    public boolean getProjectileInvincibility() {
        return this.npc.stats.projectileInvincibility;
    }

    @Override
    public void say(String message) {
        this.npc.saySurrounding(new Line(message));
    }

    @Override
    public void say(IPlayer player, String message) {
        if (player == null || message == null || message.isEmpty()) {
            return;
        }
        this.npc.say((EntityPlayer)player.getMCEntity(), new Line(message));
    }

    @Override
    public IDialog getDialog(int slot) {
        return NpcAPI.Instance().getDialogs().get(this.getDialogId(slot));
    }

    @Override
    public int getDialogId(int slot) {
        DialogOption option;
        if (this.npc.dialogs.containsKey(slot) && (option = this.npc.dialogs.get(slot)).hasDialog()) {
            return option.dialogId;
        }
        return -1;
    }

    @Override
    public void setDialog(int slot, IDialog dialog) {
        this.setDialog(slot, dialog.getId());
    }

    @Override
    public void setDialog(int slot, int dialogId) {
        NoppesUtilServer.setNpcDialog(slot, dialogId, this.npc);
    }

    @Override
    public ILines getInteractLines() {
        return this.npc.advanced.interactLines;
    }

    @Override
    public ILines getWorldLines() {
        return this.npc.advanced.worldLines;
    }

    @Override
    public ILines getAttackLines() {
        return this.npc.advanced.attackLines;
    }

    @Override
    public ILines getKilledLines() {
        return this.npc.advanced.killedLines;
    }

    @Override
    public ILines getKillLines() {
        return this.npc.advanced.killLines;
    }

    @Override
    public boolean getOrderedLines() {
        return this.npc.advanced.orderedLines;
    }

    @Override
    public void setOrderedLines(boolean ordered) {
        this.npc.advanced.orderedLines = ordered;
    }

    @Override
    public String getIdleSound() {
        return this.npc.advanced.idleSound;
    }

    @Override
    public void setIdleSound(String sound) {
        this.npc.advanced.idleSound = sound;
    }

    @Override
    public String getAngrySound() {
        return this.npc.advanced.angrySound;
    }

    @Override
    public void setAngrySound(String sound) {
        this.npc.advanced.angrySound = sound;
    }

    @Override
    public String getHurtSound() {
        return this.npc.advanced.hurtSound;
    }

    @Override
    public void setHurtSound(String sound) {
        this.npc.advanced.hurtSound = sound;
    }

    @Override
    public String getDeathSound() {
        return this.npc.advanced.deathSound;
    }

    @Override
    public void setDeathSound(String sound) {
        this.npc.advanced.deathSound = sound;
    }

    @Override
    public String getStepSound() {
        return this.npc.advanced.stepSound;
    }

    @Override
    public void setStepSound(String sound) {
        this.npc.advanced.stepSound = sound;
    }

    @Override
    public boolean getDisablePitch() {
        return this.npc.advanced.disablePitch;
    }

    @Override
    public void setDisablePitch(boolean disablePitch) {
        this.npc.advanced.disablePitch = disablePitch;
    }

    @Override
    public void kill() {
        this.npc.func_70106_y();
    }

    @Override
    public void reset() {
        this.npc.reset();
    }

    @Override
    public IAnimationData getAnimationData() {
        return this.npc.display.animationData;
    }

    @Override
    public IRole getRole() {
        if (this.npc.advanced.role == EnumRoleType.Bank) {
            return new ScriptRoleBank(this.npc);
        }
        if (this.npc.advanced.role == EnumRoleType.Follower) {
            return new ScriptRoleFollower(this.npc);
        }
        if (this.npc.advanced.role == EnumRoleType.Postman) {
            return new ScriptRoleMailman(this.npc);
        }
        if (this.npc.advanced.role == EnumRoleType.Trader) {
            return new ScriptRoleTrader(this.npc);
        }
        if (this.npc.advanced.role == EnumRoleType.Transporter) {
            return new ScriptRoleTransporter(this.npc);
        }
        if (this.npc.advanced.role == EnumRoleType.Companion) {
            return new ScriptRoleCompanion(this.npc);
        }
        return new ScriptRoleInterface(this.npc);
    }

    @Override
    public void setRole(int role) {
        for (EnumRoleType e : EnumRoleType.values()) {
            if (e.ordinal() != role) continue;
            this.npc.advanced.role = e;
            this.npc.advanced.setRole(role);
            break;
        }
    }

    @Override
    public IJob getJob() {
        if (this.npc.advanced.job == EnumJobType.Bard) {
            return new ScriptJobBard(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.Conversation) {
            return new ScriptJobConversation(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.Follower) {
            return new ScriptJobFollower(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.Guard) {
            return new ScriptJobGuard(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.Healer) {
            return new ScriptJobHealer(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.ItemGiver) {
            return new ScriptJobItemGiver(this.npc);
        }
        if (this.npc.advanced.job == EnumJobType.Spawner) {
            return new ScriptJobSpawner(this.npc);
        }
        return new ScriptJobInterface(this.npc);
    }

    @Override
    public void setJob(int job) {
        for (EnumJobType e : EnumJobType.values()) {
            if (e.ordinal() != job) continue;
            this.npc.advanced.job = e;
            this.npc.advanced.setJob(job);
            break;
        }
    }

    @Override
    public IItemStack getRightItem() {
        return NpcAPI.Instance().getIItemStack(this.npc.inventory.getWeapon());
    }

    @Override
    public void setRightItem(IItemStack item) {
        if (item == null) {
            this.npc.inventory.setWeapon(null);
        } else {
            this.npc.inventory.setWeapon(item.getMCItemStack());
        }
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public IItemStack getLefttItem() {
        return NpcAPI.Instance().getIItemStack(this.npc.getOffHand());
    }

    @Override
    public IItemStack getLeftItem() {
        return this.getLefttItem();
    }

    @Override
    public void setLeftItem(IItemStack item) {
        if (item == null) {
            this.npc.inventory.setOffHand(null);
        } else {
            this.npc.inventory.setOffHand(item.getMCItemStack());
        }
    }

    @Override
    public IItemStack getProjectileItem() {
        return NpcAPI.Instance().getIItemStack(this.npc.inventory.getProjectile());
    }

    @Override
    public void setProjectileItem(IItemStack item) {
        if (item == null) {
            this.npc.inventory.setProjectile(null);
        } else {
            this.npc.inventory.setProjectile(item.getMCItemStack());
        }
        this.npc.script.aiNeedsUpdate = true;
    }

    @Override
    public boolean canAimWhileShooting() {
        return this.npc.stats.aimType == 1;
    }

    @Override
    public void aimWhileShooting(boolean aimWhileShooting) {
        this.npc.stats.aimType = (byte)(aimWhileShooting ? 1 : 0);
    }

    @Override
    public void setAimType(byte aimWhileShooting) {
        if (aimWhileShooting < 0 || aimWhileShooting > 2) {
            return;
        }
        this.npc.stats.aimType = aimWhileShooting;
    }

    @Override
    public byte getAimType() {
        return this.npc.stats.aimType;
    }

    @Override
    public void setMinProjectileDelay(int minDelay) {
        this.npc.stats.minDelay = minDelay;
    }

    @Override
    public int getMinProjectileDelay() {
        return this.npc.stats.minDelay;
    }

    @Override
    public void setMaxProjectileDelay(int maxDelay) {
        this.npc.stats.maxDelay = maxDelay;
    }

    @Override
    public int getMaxProjectileDelay() {
        return this.npc.stats.maxDelay;
    }

    @Override
    public void setRangedRange(int rangedRange) {
        this.npc.stats.rangedRange = rangedRange;
    }

    @Override
    public int getRangedRange() {
        return this.npc.stats.rangedRange;
    }

    public void setRangedRage(int rangedRage) {
        this.setRangedRange(rangedRage);
    }

    public int getRangedRage() {
        return this.getRangedRange();
    }

    @Override
    public void setFireRate(int rate) {
        this.npc.stats.fireRate = rate;
    }

    @Override
    public int getFireRate() {
        return this.npc.stats.fireRate;
    }

    @Override
    public void setBurstCount(int burstCount) {
        this.npc.stats.burstCount = burstCount;
    }

    @Override
    public int getBurstCount() {
        return this.npc.stats.burstCount;
    }

    @Override
    public void setShotCount(int shotCount) {
        this.npc.stats.shotCount = shotCount;
    }

    @Override
    public int getShotCount() {
        return this.npc.stats.shotCount;
    }

    @Override
    public void setAccuracy(int accuracy) {
        this.npc.stats.accuracy = accuracy;
    }

    @Override
    public int getAccuracy() {
        return this.npc.stats.accuracy;
    }

    @Override
    public String getFireSound() {
        return this.npc.stats.fireSound;
    }

    @Override
    public void setFireSound(String fireSound) {
        this.npc.stats.fireSound = fireSound;
    }

    @Override
    public IItemStack getArmor(int slot) {
        return NpcAPI.Instance().getIItemStack(this.npc.inventory.armor.get(slot));
    }

    @Override
    public void setArmor(int slot, IItemStack item) {
        if (item == null) {
            this.npc.inventory.armor.put(slot, null);
        } else {
            this.npc.inventory.armor.put(slot, item.getMCItemStack());
        }
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public IItemStack getLootItem(int slot) {
        return NpcAPI.Instance().getIItemStack(this.npc.inventory.func_70301_a(slot + 7));
    }

    @Override
    public void setLootItem(int slot, IItemStack item) {
        if (item == null || item.getMCItemStack() == null) {
            this.npc.inventory.func_70299_a(slot + 7, null);
        } else {
            this.npc.inventory.func_70299_a(slot + 7, item.getMCItemStack());
        }
    }

    @Override
    public double getLootChance(int slot) {
        if (!this.npc.inventory.dropchance.containsKey(slot)) {
            return 100.0;
        }
        return this.npc.inventory.dropchance.get(slot);
    }

    @Override
    public void setLootChance(int slot, double chance) {
        if (!this.npc.inventory.dropchance.containsKey(slot)) {
            return;
        }
        if (chance < 0.0) {
            chance = 0.0;
        }
        if (chance > 100.0) {
            chance = 100.0;
        }
        this.npc.inventory.dropchance.put(slot, chance);
    }

    @Override
    public int getLootMode() {
        return this.npc.inventory.lootMode;
    }

    @Override
    public void setLootMode(int lootMode) {
        if (lootMode < 0 || lootMode > 1) {
            return;
        }
        this.npc.inventory.lootMode = lootMode;
    }

    @Override
    public void setMinLootXP(int lootXP) {
        if (lootXP > this.npc.inventory.maxExp) {
            lootXP = this.npc.inventory.maxExp;
        }
        if (lootXP < 0) {
            lootXP = 0;
        }
        if (lootXP > Short.MAX_VALUE) {
            lootXP = Short.MAX_VALUE;
        }
        this.npc.inventory.minExp = lootXP;
    }

    @Override
    public void setMaxLootXP(int lootXP) {
        if (lootXP < this.npc.inventory.minExp) {
            lootXP = this.npc.inventory.minExp;
        }
        if (lootXP < 0) {
            lootXP = 0;
        }
        if (lootXP > Short.MAX_VALUE) {
            lootXP = Short.MAX_VALUE;
        }
        this.npc.inventory.maxExp = lootXP;
    }

    @Override
    public int getMinLootXP() {
        return this.npc.inventory.minExp;
    }

    @Override
    public int getMaxLootXP() {
        return this.npc.inventory.maxExp;
    }

    @Override
    public void setAnimation(int type) {
        if (type == 0) {
            this.npc.ais.animationType = EnumAnimation.NONE;
        } else if (type == 1) {
            this.npc.ais.animationType = EnumAnimation.SITTING;
        } else if (type == 5) {
            this.npc.ais.animationType = EnumAnimation.DANCING;
        } else if (type == 4) {
            this.npc.ais.animationType = EnumAnimation.SNEAKING;
        } else if (type == 2) {
            this.npc.ais.animationType = EnumAnimation.LYING;
        } else if (type == 3) {
            this.npc.ais.animationType = EnumAnimation.HUG;
        }
    }

    @Override
    public void setTacticalVariant(int variant) {
        if (variant > EnumNavType.values().length - 1 || variant < 0) {
            return;
        }
        this.npc.ais.tacticalVariant = EnumNavType.values()[variant];
        this.npc.ais.directLOS = EnumNavType.values()[variant] != EnumNavType.Stalk && this.npc.ais.directLOS;
    }

    @Override
    public int getTacticalVariant() {
        return this.npc.ais.tacticalVariant.ordinal();
    }

    @Override
    public void setTacticalVariant(String variant) {
        boolean found = false;
        for (String s : EnumNavType.names()) {
            if (!s.equals(variant)) continue;
            found = true;
        }
        if (!found) {
            return;
        }
        this.npc.ais.tacticalVariant = EnumNavType.valueOf(variant);
    }

    @Override
    public String getTacticalVariantName() {
        return this.npc.ais.tacticalVariant.name();
    }

    @Override
    public void setCombatPolicy(int variant) {
        if (variant > EnumCombatPolicy.values().length - 1 || variant < 0) {
            return;
        }
        this.npc.ais.combatPolicy = EnumCombatPolicy.values()[variant];
    }

    @Override
    public int getCombatPolicy() {
        return this.npc.ais.combatPolicy.ordinal();
    }

    @Override
    public void setCombatPolicy(String variant) {
        boolean found = false;
        for (String s : EnumCombatPolicy.names()) {
            if (!s.equals(variant)) continue;
            found = true;
        }
        if (!found) {
            return;
        }
        this.npc.ais.combatPolicy = EnumCombatPolicy.valueOf(variant);
    }

    @Override
    public String getCombatPolicyName() {
        return this.npc.ais.combatPolicy.name();
    }

    @Override
    public void setTacticalRadius(int tacticalRadius) {
        if (tacticalRadius < 0) {
            tacticalRadius = 0;
        }
        this.npc.ais.tacticalRadius = tacticalRadius;
    }

    @Override
    public int getTacticalRadius() {
        return this.npc.ais.tacticalRadius;
    }

    @Override
    public int getTacticalChance() {
        return this.npc.ais.tacticalChance;
    }

    @Override
    public void setTacticalChance(int chance) {
        this.npc.ais.tacticalChance = chance;
    }

    @Override
    public boolean getCanSwim() {
        return this.npc.ais.canSwim;
    }

    @Override
    public void setCanSwim(boolean canSwim) {
        this.npc.ais.canSwim = canSwim;
    }

    @Override
    public boolean getReactsToFire() {
        return this.npc.ais.reactsToFire;
    }

    @Override
    public void setReactsToFire(boolean reactsToFire) {
        this.npc.ais.reactsToFire = reactsToFire;
    }

    @Override
    public boolean getAvoidsWater() {
        return this.npc.ais.avoidsWater;
    }

    @Override
    public void setAvoidsWater(boolean avoidsWater) {
        this.npc.ais.avoidsWater = avoidsWater;
    }

    @Override
    public boolean getAvoidsSun() {
        return this.npc.ais.avoidsSun;
    }

    @Override
    public void setAvoidsSun(boolean avoidsSun) {
        this.npc.ais.avoidsSun = avoidsSun;
    }

    @Override
    public boolean getDirectLOS() {
        return this.npc.ais.directLOS;
    }

    @Override
    public void setDirectLOS(boolean directLOS) {
        this.npc.ais.directLOS = directLOS;
    }

    @Override
    public int getLeapType() {
        return this.npc.ais.leapType;
    }

    @Override
    public void setLeapType(int leapType) {
        this.npc.ais.leapType = leapType;
    }

    @Override
    public boolean getCanSprint() {
        return this.npc.ais.canSprint;
    }

    @Override
    public void setCanSprint(boolean canSprint) {
        this.npc.ais.canSprint = canSprint;
    }

    @Override
    public boolean getStopAndInteract() {
        return this.npc.ais.stopAndInteract;
    }

    @Override
    public void setStopAndInteract(boolean stopAndInteract) {
        this.npc.ais.stopAndInteract = stopAndInteract;
    }

    @Override
    public int getDoorInteract() {
        return this.npc.ais.doorInteract;
    }

    @Override
    public void setDoorInteract(int doorInteract) {
        this.npc.ais.doorInteract = doorInteract;
    }

    @Override
    public int getWalkingRange() {
        return this.npc.ais.walkingRange;
    }

    @Override
    public void setWalkingRange(int range) {
        this.npc.ais.walkingRange = range;
    }

    @Override
    public boolean getNpcInteracting() {
        return this.npc.ais.npcInteracting;
    }

    @Override
    public void setNpcInteracting(boolean npcInteracting) {
        this.npc.ais.npcInteracting = npcInteracting;
    }

    @Override
    public boolean getMovingPause() {
        return this.npc.ais.movingPause;
    }

    @Override
    public void setMovingPause(boolean movingPause) {
        this.npc.ais.movingPause = movingPause;
    }

    @Override
    public int getMovingPattern() {
        return this.npc.ais.movingPattern;
    }

    @Override
    public void setMovingPattern(int pattern) {
        if (pattern < 0) {
            pattern = 0;
        }
        if (pattern > 1) {
            pattern = 1;
        }
        this.npc.ais.movingPattern = pattern;
    }

    @Override
    public int getCanFireIndirect() {
        return this.npc.ais.canFireIndirect;
    }

    @Override
    public void setCanFireIndirect(int canFireIndirect) {
        this.npc.ais.canFireIndirect = canFireIndirect;
    }

    @Override
    public int getUseRangeMelee() {
        return this.npc.ais.useRangeMelee;
    }

    @Override
    public void setUseRangeMelee(int useRangeMelee) {
        this.npc.ais.useRangeMelee = useRangeMelee;
    }

    @Override
    public int getDistanceToMelee() {
        return this.npc.ais.distanceToMelee;
    }

    @Override
    public void setDistanceToMelee(int distance) {
        this.npc.ais.distanceToMelee = distance;
    }

    @Override
    public float getBodyOffsetX() {
        return this.npc.ais.bodyOffsetX;
    }

    @Override
    public void setBodyOffsetX(float offsetX) {
        this.npc.ais.bodyOffsetX = offsetX;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public float getBodyOffsetY() {
        return this.npc.ais.bodyOffsetY;
    }

    @Override
    public void setBodyOffsetY(float offsetY) {
        this.npc.ais.bodyOffsetY = offsetY;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public float getBodyOffsetZ() {
        return this.npc.ais.bodyOffsetZ;
    }

    @Override
    public void setBodyOffsetZ(float offsetZ) {
        this.npc.ais.bodyOffsetZ = offsetZ;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public void setIgnoreCobweb(boolean ignore) {
        this.npc.stats.ignoreCobweb = ignore;
    }

    @Override
    public boolean getIgnoreCobweb() {
        return this.npc.stats.ignoreCobweb;
    }

    @Override
    public void setOnFoundEnemy(int onAttack) {
        if (onAttack < 0 || onAttack > 3) {
            return;
        }
        this.npc.ais.onAttack = onAttack;
    }

    @Override
    public int onFoundEnemy() {
        return this.npc.ais.onAttack;
    }

    @Override
    public void setShelterFrom(int shelterFrom) {
        if (shelterFrom < 0 || shelterFrom > 2) {
            return;
        }
        this.npc.ais.findShelter = shelterFrom;
    }

    @Override
    public int getShelterFrom() {
        return this.npc.ais.findShelter;
    }

    @Override
    public boolean hasLivingAnimation() {
        return !this.npc.display.disableLivingAnimation;
    }

    @Override
    public void setLivingAnimation(boolean livingAnimation) {
        this.npc.display.disableLivingAnimation = !livingAnimation;
    }

    @Override
    public void setVisibleType(int type) {
        this.npc.display.visible = type;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public int getVisibleType() {
        return this.npc.display.visible;
    }

    @Override
    public void setVisibleTo(IPlayer player, boolean visible) {
        UUID uuid = player.getMCEntity().getPersistentID();
        ArrayList<UUID> uuidList = this.npc.display.invisibleToList;
        if (uuidList != null) {
            if (!uuidList.contains(uuid)) {
                if (!visible) {
                    this.npc.display.invisibleToList.add(uuid);
                }
            } else if (visible) {
                this.npc.display.invisibleToList.remove(uuid);
            }
        }
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public boolean isVisibleTo(IPlayer player) {
        return !this.npc.scriptInvisibleToPlayer((EntityPlayer)player.getMCEntity());
    }

    @Override
    public void setShowName(int type) {
        this.npc.display.showName = type;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public int getShowName() {
        return this.npc.display.showName;
    }

    @Override
    public int getShowBossBar() {
        return this.npc.display.showBossBar;
    }

    @Override
    public void setShowBossBar(int type) {
        this.npc.display.showBossBar = (byte)type;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public double getMeleeStrength() {
        return this.npc.stats.getAttackStrength();
    }

    @Override
    public void setMeleeStrength(double strength) {
        this.npc.stats.setAttackStrength(strength);
    }

    @Override
    public int getMeleeSpeed() {
        return this.npc.stats.attackSpeed;
    }

    @Override
    public void setMeleeSpeed(int speed) {
        this.npc.stats.attackSpeed = speed;
    }

    @Override
    public int getMeleeRange() {
        return this.npc.stats.attackRange;
    }

    @Override
    public void setMeleeRange(int range) {
        this.npc.stats.attackRange = range;
    }

    @Override
    public int getSwingWarmup() {
        return this.npc.stats.swingWarmUp;
    }

    @Override
    public void setSwingWarmup(int ticks) {
        this.npc.stats.swingWarmUp = ticks;
    }

    @Override
    public int getKnockback() {
        return this.npc.stats.knockback;
    }

    @Override
    public void setKnockback(int knockback) {
        this.npc.stats.knockback = knockback;
    }

    @Override
    public int getAggroRange() {
        return this.npc.stats.aggroRange;
    }

    @Override
    public void setAggroRange(int aggroRange) {
        this.npc.stats.aggroRange = aggroRange;
    }

    @Override
    public float getRangedStrength() {
        return this.npc.stats.pDamage;
    }

    @Override
    public void setRangedStrength(float strength) {
        this.npc.stats.pDamage = (float)Math.floor(strength);
    }

    @Override
    public int getRangedSpeed() {
        return this.npc.stats.pSpeed;
    }

    @Override
    public void setRangedSpeed(int speed) {
        this.npc.stats.pSpeed = speed;
    }

    @Override
    public int getRangedBurst() {
        return this.npc.stats.burstCount;
    }

    @Override
    public void setRangedBurst(int count) {
        this.npc.stats.burstCount = count;
    }

    @Override
    public int getRespawnTime() {
        return this.npc.stats.respawnTime;
    }

    @Override
    public void setRespawnTime(int time) {
        this.npc.stats.respawnTime = time;
    }

    @Override
    public int getRespawnCycle() {
        return this.npc.stats.spawnCycle;
    }

    @Override
    public void setRespawnCycle(int cycle) {
        if (cycle < 0) {
            cycle = 0;
        }
        if (cycle > 3) {
            cycle = 3;
        }
        this.npc.stats.spawnCycle = cycle;
    }

    @Override
    public boolean getHideKilledBody() {
        return this.npc.stats.hideKilledBody;
    }

    @Override
    public void hideKilledBody(boolean hide) {
        this.npc.stats.hideKilledBody = hide;
    }

    @Override
    public boolean naturallyDespawns() {
        return this.npc.stats.canDespawn;
    }

    @Override
    public void setNaturallyDespawns(boolean canDespawn) {
        this.npc.stats.canDespawn = canDespawn;
        this.npc.stats.playerSetCanDespawn = canDespawn;
        this.npc.syncDespawnPersistence();
    }

    @Override
    public boolean spawnedFromSoulStone() {
        return !this.npc.advanced.soulStonePlayerName.equals("");
    }

    @Override
    public String getSoulStonePlayerName() {
        return this.npc.advanced.soulStonePlayerName;
    }

    @Override
    public boolean getRefuseSoulStone() {
        return this.npc.advanced.refuseSoulStone;
    }

    @Override
    public void setRefuseSoulStone(boolean refuse) {
        this.npc.advanced.refuseSoulStone = refuse;
    }

    @Override
    public boolean isSoulStoneInit() {
        return this.npc.advanced.soulStoneInit;
    }

    @Override
    public int getMinPointsToSoulStone() {
        return this.npc.advanced.minFactionPointsToSoulStone;
    }

    @Override
    public void setMinPointsToSoulStone(int points) {
        this.npc.advanced.minFactionPointsToSoulStone = points;
    }

    @Override
    public void giveItem(IPlayer player, IItemStack item) {
        this.npc.givePlayerItem((EntityPlayer)player.getMCEntity(), item.getMCItemStack());
    }

    @Override
    public void executeCommand(String command) {
        NoppesUtilServer.runCommand((EntityLivingBase)this.npc, this.npc.func_70005_c_(), command, null);
    }

    @Override
    public IModelData getModelData() {
        return this.npc instanceof EntityCustomNpc ? ((EntityCustomNpc)this.npc).modelData : null;
    }

    @Override
    public IHitboxData getHitboxData() {
        return this.npc.display.hitboxData;
    }

    @Override
    public ITintData getTintData() {
        return this.npc.display.tintData;
    }

    @Override
    public void setHeadScale(float x, float y, float z) {
        if (this.npc instanceof EntityCustomNpc) {
            ((EntityCustomNpc)this.npc).modelData.modelScale.head.scaleX = ValueUtil.clamp(x, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.head.scaleY = ValueUtil.clamp(y, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.head.scaleZ = ValueUtil.clamp(z, 0.5f, 1.5f);
            this.npc.script.clientNeedsUpdate = true;
        }
    }

    @Override
    public void setBodyScale(float x, float y, float z) {
        if (this.npc instanceof EntityCustomNpc) {
            ((EntityCustomNpc)this.npc).modelData.modelScale.body.scaleX = ValueUtil.clamp(x, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.body.scaleY = ValueUtil.clamp(y, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.body.scaleZ = ValueUtil.clamp(z, 0.5f, 1.5f);
            this.npc.script.clientNeedsUpdate = true;
        }
    }

    @Override
    public void setArmsScale(float x, float y, float z) {
        if (this.npc instanceof EntityCustomNpc) {
            ((EntityCustomNpc)this.npc).modelData.modelScale.arms.scaleX = ValueUtil.clamp(x, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.arms.scaleY = ValueUtil.clamp(y, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.arms.scaleZ = ValueUtil.clamp(z, 0.5f, 1.5f);
            this.npc.script.clientNeedsUpdate = true;
        }
    }

    @Override
    public void setLegsScale(float x, float y, float z) {
        if (this.npc instanceof EntityCustomNpc) {
            ((EntityCustomNpc)this.npc).modelData.modelScale.legs.scaleX = ValueUtil.clamp(x, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.legs.scaleY = ValueUtil.clamp(y, 0.5f, 1.5f);
            ((EntityCustomNpc)this.npc).modelData.modelScale.legs.scaleZ = ValueUtil.clamp(z, 0.5f, 1.5f);
            this.npc.script.clientNeedsUpdate = true;
        }
    }

    @Override
    public void setExplosionResistance(float resistance) {
        this.npc.stats.resistances.explosion = ValueUtil.clamp(resistance, 0.0f, 2.0f);
    }

    @Override
    public float getExplosionResistance() {
        return this.npc.stats.resistances.explosion;
    }

    @Override
    public void setMeleeResistance(float resistance) {
        this.npc.stats.resistances.playermelee = ValueUtil.clamp(resistance, 0.0f, 2.0f);
    }

    @Override
    public float getMeleeResistance() {
        return this.npc.stats.resistances.playermelee;
    }

    @Override
    public void setArrowResistance(float resistance) {
        this.npc.stats.resistances.arrow = ValueUtil.clamp(resistance, 0.0f, 2.0f);
    }

    @Override
    public float getArrowResistance() {
        return this.npc.stats.resistances.arrow;
    }

    @Override
    public void setKnockbackResistance(double resistance) {
        this.npc.stats.resistances.knockback = ValueUtil.clamp((float)resistance, 0.0f, 2.0f);
    }

    @Override
    public double getKnockbackResistance() {
        return this.npc.stats.resistances.knockback;
    }

    @Override
    public boolean getDamageDisabled() {
        return this.npc.stats.resistances.disableDamage;
    }

    @Override
    public void setDamageDisabled(boolean disabled) {
        this.npc.stats.resistances.disableDamage = disabled;
    }

    @Override
    public boolean getNoFallDamage() {
        return this.npc.stats.noFallDamage;
    }

    @Override
    public void setNoFallDamage(boolean noFallDamage) {
        this.npc.stats.noFallDamage = noFallDamage;
    }

    @Override
    public boolean getImmuneToFire() {
        return this.npc.stats.immuneToFire;
    }

    @Override
    public void setImmuneToFire(boolean immuneToFire) {
        this.npc.stats.immuneToFire = immuneToFire;
        this.npc.setImmuneToFire(immuneToFire);
    }

    @Override
    public boolean getPotionImmune() {
        return this.npc.stats.potionImmune;
    }

    @Override
    public void setPotionImmune(boolean potionImmune) {
        this.npc.stats.potionImmune = potionImmune;
    }

    @Override
    public boolean getBurnInSun() {
        return this.npc.stats.burnInSun;
    }

    @Override
    public void setBurnInSun(boolean burnInSun) {
        this.npc.stats.burnInSun = burnInSun;
    }

    @Override
    public boolean getAttackInvisible() {
        return this.npc.stats.attackInvisible;
    }

    @Override
    public void setAttackInvisible(boolean attackInvisible) {
        this.npc.stats.attackInvisible = attackInvisible;
    }

    @Override
    public int getCreatureType() {
        return this.npc.stats.creatureType.ordinal();
    }

    @Override
    public void setCreatureType(int type) {
        EnumCreatureAttribute[] values = EnumCreatureAttribute.values();
        if (type >= 0 && type < values.length) {
            this.npc.stats.creatureType = values[type];
        }
    }

    @Override
    public void setRetaliateType(int type) {
        if (type < 0) {
            type = 0;
        }
        if (type > 3) {
            type = 3;
        }
        this.npc.ais.onAttack = type;
        this.npc.updateTasks();
    }

    @Override
    public float getCombatRegen() {
        return this.npc.stats.combatRegen;
    }

    @Override
    public void setCombatRegen(float regen) {
        this.npc.stats.combatRegen = (float)Math.floor(regen);
    }

    @Override
    public float getHealthRegen() {
        return this.npc.stats.healthRegen;
    }

    @Override
    public void setHealthRegen(float regen) {
        this.npc.stats.healthRegen = (float)Math.floor(regen);
    }

    @Override
    public boolean getCanDrown() {
        return this.npc.stats.drowningType > 0;
    }

    @Override
    public void setCanDrown(boolean d) {
        this.npc.stats.drowningType = d ? 1 : 0;
    }

    @Override
    public void setDrowningType(int type) {
        if (type < 0) {
            type = 0;
        }
        if (type > 2) {
            type = 2;
        }
        this.npc.stats.drowningType = type;
    }

    @Override
    public boolean canBreathe() {
        return this.npc.canBreathe();
    }

    @Override
    public long getAge() {
        return this.npc.totalTicksAlive;
    }

    @Override
    public ITimers getTimers() {
        return this.npc.timers;
    }

    @Override
    public void setFly(int fly) {
        fly = fly > 0 ? 1 : 0;
        this.npc.ais.movementType = fly;
    }

    @Override
    public boolean canFly() {
        return this.npc.ais.movementType == 1;
    }

    @Override
    public void setFlySpeed(double flySpeed) {
        if (flySpeed < 0.0) {
            flySpeed = 0.0;
        }
        this.npc.ais.flySpeed = flySpeed;
    }

    @Override
    public double getFlySpeed(double flySpeed) {
        return this.npc.ais.flySpeed;
    }

    @Override
    public void setFlyGravity(double flyGravity) {
        if (flyGravity < 0.0) {
            flyGravity = 0.0;
        }
        if (flyGravity > 1.0) {
            flyGravity = 1.0;
        }
        this.npc.ais.flyGravity = flyGravity;
    }

    @Override
    public double getFlyGravity(double flySpeed) {
        return this.npc.ais.flyGravity;
    }

    @Override
    public void setFlyHeightLimit(int flyHeightLimit) {
        if (flyHeightLimit < 0) {
            flyHeightLimit = 0;
        }
        this.npc.ais.flyHeightLimit = flyHeightLimit;
    }

    @Override
    public int getFlyHeightLimit(int flyHeightLimit) {
        return this.npc.ais.flyHeightLimit;
    }

    @Override
    public void limitFlyHeight(boolean limit) {
        this.npc.ais.hasFlyLimit = limit;
    }

    @Override
    public boolean isFlyHeightLimited(boolean limit) {
        return this.npc.ais.hasFlyLimit;
    }

    @Override
    public void setSpeed(double speed) {
        this.npc.ais.setWalkingSpeed(speed);
    }

    @Override
    public double getSpeed() {
        return this.npc.ais.getWalkingSpeed();
    }

    @Override
    public void setSkinType(byte type) {
        this.npc.display.skinType = type;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public byte getSkinType() {
        return this.npc.display.skinType;
    }

    @Override
    public void setSkinUrl(String url) {
        if (this.npc.display.url.equals(url)) {
            return;
        }
        this.npc.display.url = url;
        this.npc.textureLocation = null;
        if (this.npc.display.skinType < 2) {
            this.npc.display.skinType = (byte)2;
        }
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getSkinUrl() {
        return this.npc.display.url;
    }

    @Override
    public void setCloakTexture(String cloakTexture) {
        this.npc.display.cloakTexture = cloakTexture;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getCloakTexture() {
        return this.npc.display.cloakTexture;
    }

    @Override
    public void setOverlayTexture(String overlayTexture) {
        if (this.getOverlays().size() >= ConfigMain.SkinOverlayLimit) {
            return;
        }
        this.getOverlays().add(0, NpcAPI.Instance().createSkinOverlay(overlayTexture));
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public String getOverlayTexture() {
        if (!this.npc.display.skinOverlayData.overlayList.containsKey(0)) {
            return "";
        }
        return this.npc.display.skinOverlayData.overlayList.get(0).getTexture();
    }

    @Override
    public String getGlowTexture() {
        return this.npc.display.glowTexture;
    }

    @Override
    public void setGlowTexture(String texture) {
        this.npc.display.glowTexture = texture;
        this.npc.script.clientNeedsUpdate = true;
    }

    @Override
    public IOverlayHandler getOverlays() {
        return this.npc.display.skinOverlayData;
    }

    @Override
    public void setCollisionType(int type) {
        this.npc.stats.collidesWith = type;
    }

    @Override
    public int getCollisionType() {
        return this.npc.stats.collidesWith;
    }

    @Override
    public void updateClient() {
        this.npc.updateClient();
    }

    @Override
    public void updateAI() {
        this.npc.updateTasks();
    }

    @Override
    public IActionManager getActionManager() {
        if (this.npc.actionManager.getName().isEmpty()) {
            this.npc.actionManager.setName(this.npc.func_70005_c_());
        }
        return this.npc.actionManager;
    }

    @Override
    public IMagicData getMagicData() {
        return this.npc.stats.magicData;
    }

    @Override
    public IDataAbilities getAbilityData() {
        return new ScriptDataAbilities(this.npc);
    }

    @Override
    public IEnergyProjectile[] getActiveEnergyProjectiles() {
        List<EntityEnergyProjectile> entities = EntityEnergyProjectile.getActiveProjectiles(this.npc.func_145782_y());
        IEnergyProjectile[] result = new IEnergyProjectile[entities.size()];
        for (int i = 0; i < entities.size(); ++i) {
            result[i] = (IEnergyProjectile)NpcAPI.Instance().getIEntity(entities.get(i));
        }
        return result;
    }
}

