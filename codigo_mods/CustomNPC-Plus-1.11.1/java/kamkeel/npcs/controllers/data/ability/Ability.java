/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.data.AbilityIconData;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.enums.InvulnerableMode;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.RotationMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.gui.IAbilityFieldProvider;
import kamkeel.npcs.controllers.data.ability.preview.PreviewEntityHandler;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyPanel;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.FileNameHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.EventHooks;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.api.INbt;
import noppes.npcs.api.ability.IAbility;
import noppes.npcs.client.gui.advanced.SubGuiAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiAbilityMagic;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.AbilityScript;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.MagicEntry;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.AbilityEvent;

public abstract class Ability
implements IAbility,
IAbilityAction {
    protected String id = "";
    protected String name = "";
    protected String displayName = "";
    protected String typeId = "";
    protected int weight = 10;
    protected boolean enabled = true;
    protected List<AbilityCondition> conditions = new ArrayList<AbilityCondition>();
    protected TargetingMode targetingMode = TargetingMode.AGGRO_TARGET;
    protected float minRange = 0.0f;
    protected float maxRange = 20.0f;
    protected int cooldownTicks = 0;
    protected int windUpTicks = 20;
    protected boolean syncWindupWithAnimation = true;
    protected int dazedTicks = 80;
    protected boolean interruptible = true;
    protected InvulnerableMode invulnerableMode = InvulnerableMode.NONE;
    protected boolean ignoreIFrames = false;
    protected LockMode lockMovement = LockMode.WINDUP;
    protected RotationMode rotationMode = RotationMode.FREE;
    protected LockMode rotationPhase = LockMode.WINDUP_AND_ACTIVE;
    protected float trackSpeed = 0.0f;
    protected int windUpColor = -2130754560;
    protected int activeColor = -1057030144;
    protected String windUpSound = "";
    protected String activeSound = "";
    protected int windUpAnimationId = -1;
    protected int activeAnimationId = -1;
    protected int dazedAnimationId = -1;
    protected String windUpAnimationName = "";
    protected String activeAnimationName = "";
    protected String dazedAnimationName = "Ability_Generic_Dazed";
    protected boolean showTelegraph = true;
    protected TelegraphType telegraphType = TelegraphType.CIRCLE;
    protected float telegraphHeightOffset = 0.1f;
    protected NBTTagCompound customData = new NBTTagCompound();
    protected HashSet<UUID> tagUUIDs = new HashSet();
    protected MagicData magicData = new MagicData();
    protected UserType allowedBy = UserType.BOTH;
    protected boolean ignoreCooldown = false;
    protected boolean perAbilityCooldown = false;
    protected boolean freeOnCast = false;
    protected transient boolean builtIn = false;
    protected transient String registryKey;
    protected transient DefaultIconLayer[] defaultIconLayers = null;
    protected transient int defaultIconWidth = 48;
    protected transient int defaultIconHeight = 48;
    protected transient Predicate<EntityPlayer> playerRequirement;
    protected int toggleStates = 0;
    protected boolean hasActiveToggle = false;
    protected String[] toggleStateLabels;
    protected List<AbilityPotionEffect> effects = new ArrayList<AbilityPotionEffect>();
    protected boolean burstEnabled = false;
    protected int burstAmount = 0;
    protected int burstDelay = 0;
    protected boolean burstReplayAnimations = true;
    protected boolean burstOverlap = false;
    protected transient AbilityPhase phase = AbilityPhase.IDLE;
    protected transient int currentTick = 0;
    protected transient EntityLivingBase currentTarget;
    protected transient long executionStartTime;
    protected transient List<TelegraphInstance> telegraphInstances = new ArrayList<TelegraphInstance>();
    protected transient int burstIndex = 0;
    protected transient List<Entity> burstEntities = new ArrayList<Entity>();
    protected transient AbilityScript instanceScript;
    protected transient float damageMultiplier = 1.0f;
    protected transient boolean previewMode = false;
    protected transient PreviewEntityHandler previewEntityHandler;
    protected transient boolean npcInlineEdit = false;

    public abstract void onExecute(EntityLivingBase var1, EntityLivingBase var2);

    public abstract void onActiveTick(EntityLivingBase var1, EntityLivingBase var2, int var3);

    public abstract void writeTypeNBT(NBTTagCompound var1);

    public abstract void readTypeNBT(NBTTagCompound var1);

    public boolean hasDamage() {
        return true;
    }

    public boolean hasMagic() {
        return this.hasDamage();
    }

    public float getDisplayDamage() {
        return 0.0f;
    }

    public boolean isDisplayDamageDPS() {
        return false;
    }

    public float getDisplayBarrierHealth() {
        return 0.0f;
    }

    public boolean isDisplayReflect() {
        return false;
    }

    public float getDisplayReflectStrength() {
        return 0.0f;
    }

    public boolean isDisplayAbsorbing() {
        return false;
    }

    public List<AbilityVariant> getVariants() {
        return Collections.emptyList();
    }

    public boolean allowBurst() {
        return true;
    }

    public boolean allowOverlap() {
        return false;
    }

    public boolean isConcurrentCapable() {
        return false;
    }

    public boolean allowFreeOnCast() {
        return false;
    }

    public void detach() {
    }

    public void onToggle(EntityLivingBase caster, int oldState, int newState) {
    }

    public boolean onToggleTick(EntityLivingBase caster, int tickCount, int state) {
        return true;
    }

    public boolean isReadyForBurstCompletion(int activeTick) {
        return true;
    }

    public void onWindUpTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
    }

    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
    }

    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
    }

    public void resetForBurst() {
    }

    protected boolean applyAbilityDamage(EntityLivingBase caster, EntityLivingBase hitEntity, float damage, float knockback) {
        double dx = hitEntity.field_70165_t - caster.field_70165_t;
        double dz = hitEntity.field_70161_v - caster.field_70161_v;
        return this.applyAbilityDamageInternal(caster, hitEntity, damage, knockback, 0.0f, dx, dz);
    }

    protected boolean applyAbilityDamageWithDirection(EntityLivingBase caster, EntityLivingBase hitEntity, float damage, float knockback, double knockbackDirX, double knockbackDirZ) {
        return this.applyAbilityDamageInternal(caster, hitEntity, damage, knockback, 0.0f, knockbackDirX, knockbackDirZ);
    }

    public static int clearHurtResistanceIfNeeded(EntityLivingBase target, boolean ignoreIFrames) {
        if (!ignoreIFrames || target == null) {
            return -1;
        }
        int previous = target.field_70172_ad;
        target.field_70172_ad = 0;
        return previous;
    }

    public static void restoreHurtResistanceIfNeeded(EntityLivingBase target, boolean ignoreIFrames, int previous) {
        if (!ignoreIFrames || target == null || previous < 0) {
            return;
        }
        target.field_70172_ad = previous;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean applyAbilityDamageInternal(EntityLivingBase caster, EntityLivingBase hitEntity, float damage, float knockback, float knockbackUp, double knockbackDirX, double knockbackDirZ) {
        AbilityEvent.HitEvent hitEvent = new AbilityEvent.HitEvent(caster, this, this.currentTarget, hitEntity, damage, knockback, knockbackUp);
        if (EventHooks.onAbilityHit(this, hitEvent)) {
            return false;
        }
        damage = hitEvent.getDamage();
        knockback = hitEvent.getKnockback();
        knockbackUp = hitEvent.getKnockbackUp();
        double prevMotionX = hitEntity.field_70159_w;
        double prevMotionY = hitEntity.field_70181_x;
        double prevMotionZ = hitEntity.field_70179_y;
        if (damage > 0.0f) {
            int previousHurtResistantTime = Ability.clearHurtResistanceIfNeeded(hitEntity, this.ignoreIFrames);
            try {
                boolean handled = AbilityController.Instance.fireOnAbilityDamage(this, caster, hitEntity, damage, knockback, knockbackUp, knockbackDirX, knockbackDirZ, 1.0f);
                if (!handled) {
                    float finalDamage = damage;
                    MagicData resolved = this.resolveMagicData(caster);
                    if (resolved != null && !resolved.isEmpty()) {
                        finalDamage = AttributeAttackUtil.calculateAbilityDamage(caster, hitEntity, damage, resolved);
                    }
                    if (caster instanceof EntityNPCInterface) {
                        hitEntity.func_70097_a((DamageSource)new NpcDamageSource("mob", (Entity)((EntityNPCInterface)caster)), finalDamage);
                    } else if (caster instanceof EntityPlayer) {
                        hitEntity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)caster)), finalDamage);
                    } else {
                        hitEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)caster), finalDamage);
                    }
                }
            }
            finally {
                Ability.restoreHurtResistanceIfNeeded(hitEntity, this.ignoreIFrames, previousHurtResistantTime);
            }
        }
        if (knockback > 0.0f || knockbackUp > 0.0f) {
            this.applyKnockback(hitEntity, knockbackDirX, knockbackDirZ, knockback, knockbackUp);
        } else {
            hitEntity.field_70159_w = prevMotionX;
            hitEntity.field_70181_x = prevMotionY;
            hitEntity.field_70179_y = prevMotionZ;
            hitEntity.field_70133_I = true;
        }
        return true;
    }

    private void applyKnockback(EntityLivingBase hitEntity, double dirX, double dirZ, float knockback, float knockbackUp) {
        double len = Math.sqrt(dirX * dirX + dirZ * dirZ);
        double x = 0.0;
        double z = 0.0;
        if (len > 0.0) {
            x = dirX / len * (double)knockback * 0.5;
            z = dirZ / len * (double)knockback * 0.5;
        }
        double y = knockbackUp > 0.0f ? (double)knockbackUp : 0.1;
        hitEntity.func_70024_g(x, y, z);
        hitEntity.field_70133_I = true;
    }

    protected void applyEffects(EntityLivingBase entity) {
        if (entity == null || this.effects.isEmpty()) {
            return;
        }
        for (AbilityPotionEffect effect : this.effects) {
            effect.apply(entity);
        }
    }

    public float getTelegraphRadius() {
        return 5.0f;
    }

    public float getTelegraphLength() {
        return 5.0f;
    }

    public float getTelegraphWidth() {
        return 2.0f;
    }

    public float getTelegraphAngle() {
        return 45.0f;
    }

    public float getTelegraphInnerRadius() {
        return 0.0f;
    }

    public boolean hasOwnTelegraphControls() {
        return false;
    }

    protected final boolean isPlayerCaster(EntityLivingBase caster) {
        return caster instanceof EntityPlayer;
    }

    public boolean keepTelegraphDuringActive() {
        return false;
    }

    public boolean hasAbilityMovement() {
        return false;
    }

    public boolean isTargetingModeLocked() {
        return true;
    }

    public TargetingMode[] getAllowedTargetingModes() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
    }

    @SideOnly(value=Side.CLIENT)
    public final List<FieldDef> getAllDefinitions() {
        int i;
        ArrayList<FieldDef> defs = new ArrayList<FieldDef>();
        defs.add(FieldDef.stringField("gui.name", this::getName, this::setName).tab("General"));
        defs.add(FieldDef.stringField("gui.displayName", this::getRawDisplayName, this::setDisplayName).tab("General"));
        defs.add(FieldDef.labelField("ability.validFor", () -> "\u00a7e" + StatCollector.func_74838_a((String)("ability.userType." + this.getAllowedBy().name()))).tab("General"));
        defs.add(FieldDef.row(FieldDef.intField("ability.weight", this::getWeight, this::setWeight).range(1.0f, 1000.0f), FieldDef.boolField("gui.enabled", this::isEnabled, this::setEnabled)).tab("General"));
        defs.add(FieldDef.section("ability.section.timing").tab("General"));
        defs.add(FieldDef.intField("ability.windUpTicks", this::getRawWindUpTicks, this::setWindUpTicks).tab("General").range(0.0f, 1000.0f).visibleWhen(() -> !this.hasWindUpAnimation()));
        defs.add(FieldDef.row(FieldDef.intField("ability.windUpTicks", this::getRawWindUpTicks, this::setWindUpTicks).range(0.0f, 1000.0f), FieldDef.boolField("ability.syncWindup", this::isSyncWindupWithAnimation, this::setSyncWindupWithAnimation).hover("ability.hover.sync")).tab("General").visibleWhen(() -> this.hasWindUpAnimation() && !this.isSyncWindupWithAnimation()));
        defs.add(FieldDef.row(FieldDef.labelField("ability.windUpTicks", () -> this.getWindUpTicks() + "t"), FieldDef.boolField("ability.syncWindup", this::isSyncWindupWithAnimation, this::setSyncWindupWithAnimation).hover("ability.hover.sync")).tab("General").visibleWhen(() -> this.hasWindUpAnimation() && this.isSyncWindupWithAnimation()));
        defs.add(FieldDef.row(FieldDef.intField("ability.cooldownTicks", this::getCooldownTicks, this::setCooldownTicks).range(0.0f, 10000.0f), FieldDef.boolField("ability.perAbilityCooldown", this::isPerAbilityCooldown, this::setPerAbilityCooldown)).tab("General"));
        if (this.allowFreeOnCast()) {
            defs.add(FieldDef.boolField("ability.freeOnCast", this::isFreeOnCast, this::setFreeOnCast).tab("General").hover("ability.hover.freeOnCast"));
        }
        defs.add(FieldDef.section("ability.section.movement").tab("General"));
        defs.add(FieldDef.stringEnumField("ability.lockMovement", LockMode.getDisplayKeys(), () -> this.getLockMovement().getDisplayKey(), v -> {
            for (LockMode t : LockMode.values()) {
                if (!t.getDisplayKey().equals(v)) continue;
                this.setLockMovement(t);
                break;
            }
        }).hover("ability.hover.lockMovement").tab("General"));
        defs.add(FieldDef.row(FieldDef.stringEnumField("ability.rotationMode", RotationMode.getDisplayKeys(), () -> this.getRotationMode().getDisplayKey(), v -> {
            for (RotationMode m : RotationMode.values()) {
                if (!m.getDisplayKey().equals(v)) continue;
                this.setRotationMode(m);
                break;
            }
        }).hover("ability.hover.rotationMode"), FieldDef.stringEnumField("ability.rotationPhase", Ability.getRotationPhaseKeys(), () -> this.getRotationPhase().getDisplayKey(), v -> {
            for (LockMode t : LockMode.values()) {
                if (!t.getDisplayKey().equals(v)) continue;
                this.setRotationPhase(t);
                break;
            }
        }).hover("ability.hover.rotationPhase").visibleWhen(() -> this.rotationMode != RotationMode.FREE)).tab("General"));
        defs.add(FieldDef.floatField("ability.trackSpeed", this::getTrackSpeed, this::setTrackSpeed).range(0.0f, 12.0f).hover("ability.hover.trackSpeed").visibleWhen(() -> this.rotationMode == RotationMode.TRACK).tab("General"));
        defs.add(FieldDef.row(FieldDef.boolField("ability.interruptible", this::isInterruptible, this::setInterruptible).hover("ability.hover.interruptible").enabledWhen(() -> !this.invulnerableMode.invulnerableDuringWindup()), FieldDef.intField("ability.dazedTicks", this::getDazedTicks, this::setDazedTicks).range(0.0f, 1000.0f).visibleWhen(() -> this.isInterruptible() && !this.invulnerableMode.invulnerableDuringWindup())).tab("General"));
        defs.add(FieldDef.stringEnumField("ability.invulnerable", InvulnerableMode.getDisplayKeys(), () -> this.getInvulnerableMode().getDisplayKey(), v -> {
            for (InvulnerableMode mode : InvulnerableMode.values()) {
                if (!mode.getDisplayKey().equals(v)) continue;
                this.setInvulnerableMode(mode);
                break;
            }
        }).hover("ability.hover.invulnerable").tab("General"));
        defs.add(FieldDef.boolField("ability.ignoreIFrames", this::isIgnoreIFrames, this::setIgnoreIFrames).hover("ability.hover.ignoreIFrames").visibleWhen(this::hasDamage).tab("General"));
        if (this.allowBurst()) {
            defs.add(FieldDef.section("ability.section.burst").tab("General"));
            defs.add(FieldDef.boolField("ability.burstEnabled", this::isBurstEnabled, this::setBurstEnabled).tab("General"));
            defs.add(FieldDef.row(FieldDef.intField("ability.burstAmount", this::getBurstAmount, this::setBurstAmount).range(1.0f, 100.0f), FieldDef.intField("ability.burstDelay", this::getBurstDelay, this::setBurstDelay).range(0.0f, 1000.0f)).tab("General").visibleWhen(this::isBurstEnabled));
            defs.add(FieldDef.boolField("ability.burstReplayAnimations", this::isBurstReplayAnimations, this::setBurstReplayAnimations).tab("General").visibleWhen(this::isBurstEnabled).hover("ability.hover.burstReplay"));
            if (this.allowOverlap()) {
                defs.add(FieldDef.boolField("ability.burstOverlap", this::isBurstOverlap, this::setBurstOverlap).tab("General").visibleWhen(this::isBurstEnabled).hover("ability.hover.burstOverlap"));
            }
        }
        if (this.hasMagic()) {
            defs.add(FieldDef.section("ability.section.magic").tab("General"));
            defs.add(FieldDef.labelField("ability.magic.note", () -> {
                if (this.magicData.isEmpty()) {
                    return StatCollector.func_74838_a((String)"ability.magic.usesCaster");
                }
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<Integer, MagicEntry> entry : this.magicData.getMagics().entrySet()) {
                    Magic magic = MagicController.getInstance().getMagic(entry.getKey());
                    if (magic == null) continue;
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(magic.getDisplayName()).append(": ").append(Math.round(entry.getValue().split * 100.0f)).append("%");
                }
                return sb.toString();
            }).tab("General"));
            defs.add(FieldDef.subGuiField("ability.magic.editor", () -> new SubGuiAbilityMagic(this.magicData.copy()), gui -> {
                MagicData result = gui.magicData;
                this.magicData.getMagics().clear();
                for (Map.Entry<Integer, MagicEntry> e : result.getMagics().entrySet()) {
                    this.magicData.getMagics().put(e.getKey(), e.getValue());
                }
            }).buttonLabel(() -> this.magicData.isEmpty() ? StatCollector.func_74838_a((String)"ability.magic.usesCaster") : this.magicData.getMagics().size() + " Magic(s)").tab("General"));
        }
        defs.add(FieldDef.row(FieldDef.intField("ability.minRange", () -> (int)this.getMinRange(), v -> this.setMinRange(v.intValue())).range(0.0f, 100.0f), FieldDef.intField("ability.maxRange", () -> (int)this.getMaxRange(), v -> this.setMaxRange(v.intValue())).range(1.0f, 100.0f)).tab("Target"));
        if (!this.isTargetingModeLocked()) {
            TargetingMode[] allowed = this.getAllowedTargetingModes();
            if (allowed != null && allowed.length > 0) {
                String[] allowedKeys = new String[allowed.length];
                for (i = 0; i < allowed.length; ++i) {
                    allowedKeys[i] = allowed[i].name();
                }
                defs.add(FieldDef.stringEnumField("ability.targetingMode", allowedKeys, () -> this.getTargetingMode().name(), v -> {
                    try {
                        this.setTargetingMode(TargetingMode.valueOf(v));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }).tab("Target").hover("ability.hover.targeting"));
            } else {
                defs.add(FieldDef.enumField("ability.targetingMode", TargetingMode.class, this::getTargetingMode, this::setTargetingMode).tab("Target").hover("ability.hover.targeting"));
            }
        }
        defs.add(FieldDef.section("ability.section.sounds").tab("Effects"));
        defs.add(FieldDef.soundSubGui("ability.windUpSound", this::getWindUpSound, this::setWindUpSound).tab("Effects"));
        defs.add(FieldDef.soundSubGui("ability.activeSound", this::getActiveSound, this::setActiveSound).tab("Effects"));
        defs.add(FieldDef.section("ability.section.animations").tab("Effects"));
        defs.add(FieldDef.animSubGui("ability.windUpAnimation", this::getWindUpAnimationId, this::setWindUpAnimationId, this::getWindUpAnimationName, this::setWindUpAnimationName).tab("Effects"));
        defs.add(FieldDef.animSubGui("ability.activeAnimation", this::getActiveAnimationId, this::setActiveAnimationId, this::getActiveAnimationName, this::setActiveAnimationName).tab("Effects"));
        defs.add(FieldDef.animSubGui("ability.dazedAnimation", this::getDazedAnimationId, this::setDazedAnimationId, this::getDazedAnimationName, this::setDazedAnimationName).tab("Effects"));
        TelegraphType tType = this.getTelegraphType();
        if (tType != null && tType != TelegraphType.NONE && !this.hasOwnTelegraphControls()) {
            defs.add(FieldDef.section("ability.section.telegraph").tab("Effects").hover("telegraph." + tType.name().toLowerCase()));
            defs.add(FieldDef.boolField("ability.showTelegraph", this::isShowTelegraph, this::setShowTelegraph).tab("Effects").hover("ability.hover.showTelegraph"));
            defs.add(FieldDef.colorSubGui("ability.windUpColor", this::getWindUpColor, this::setWindUpColor).tab("Effects").visibleWhen(this::isShowTelegraph));
            defs.add(FieldDef.colorSubGui("ability.activeColor", this::getActiveColor, this::setActiveColor).tab("Effects").visibleWhen(this::isShowTelegraph));
        }
        int baseSize = defs.size();
        this.getAbilityDefinitions(defs);
        for (i = baseSize; i < defs.size(); ++i) {
            if (((FieldDef)defs.get(i)).getTab() != null) continue;
            ((FieldDef)defs.get(i)).tab("Type");
        }
        if (!this.isNpcInlineEdit()) {
            AbilityIconData icon = AbilityIconData.fromAbility(this);
            BooleanSupplier isCustom = icon::isEnabled;
            defs.add(FieldDef.section("ability.section.icon").tab("Effects"));
            defs.add(FieldDef.boolField("ability.hasIcon", icon::isEnabled, icon::setEnabled).tab("Effects"));
            defs.add(FieldDef.row(FieldDef.intField("gui.width", icon::getWidth, icon::setWidth).range(1.0f, 256.0f), FieldDef.intField("gui.height", icon::getHeight, icon::setHeight).range(1.0f, 256.0f)).tab("Effects").visibleWhen(isCustom));
            defs.add(FieldDef.floatField("gui.scale", icon::getScale, icon::setScale).tab("Effects").range(0.1f, 10.0f).visibleWhen(isCustom));
            defs.add(FieldDef.intField("ability.icon.layers", icon::getLayerCount, icon::setLayerCount).tab("Effects").range(1.0f, 3.0f).visibleWhen(isCustom));
            this.addIconLayerFields(defs, icon, 0, isCustom);
            this.addIconLayerFields(defs, icon, 1, () -> isCustom.getAsBoolean() && icon.getLayerCount() >= 2);
            this.addIconLayerFields(defs, icon, 2, () -> isCustom.getAsBoolean() && icon.getLayerCount() >= 3);
            defs.add(FieldDef.section("Animation").tab("Effects").visibleWhen(isCustom));
            defs.add(FieldDef.boolField("gui.animated", icon::isAnimated, icon::setAnimated).tab("Effects").visibleWhen(isCustom).hover("gui.animated.hover"));
            defs.add(FieldDef.intField("gui.frameCount", icon::getFrameCount, icon::setFrameCount).tab("Effects").range(1.0f, 256.0f).visibleWhen(() -> isCustom.getAsBoolean() && icon.isAnimated()));
            defs.add(FieldDef.intField("gui.frameTime", icon::getFrameTime, icon::setFrameTime).tab("Effects").range(1.0f, 100.0f).visibleWhen(() -> isCustom.getAsBoolean() && icon.isAnimated()));
        }
        for (IAbilityFieldProvider provider : AbilityController.Instance.getFieldProviders()) {
            provider.addFieldDefinitions(this, defs);
        }
        return defs;
    }

    private void addIconLayerFields(List<FieldDef> defs, AbilityIconData icon, int layerIndex, BooleanSupplier visible) {
        String sectionLabel = StatCollector.func_74838_a((String)"ability.icon.layer") + " " + (layerIndex + 1);
        defs.add(FieldDef.section(sectionLabel).tab("Effects").visibleWhen(visible));
        defs.add(FieldDef.textureSubGui("gui.texture", () -> icon.getLayer((int)layerIndex).texture, t -> icon.setLayerTexture(layerIndex, (String)t)).tab("Effects").visibleWhen(visible));
        defs.add(FieldDef.row(FieldDef.intField("ability.icon.x", () -> icon.getLayer((int)layerIndex).iconX, x -> icon.setLayerIconX(layerIndex, (int)x)).range(0.0f, 4096.0f), FieldDef.intField("ability.icon.y", () -> icon.getLayer((int)layerIndex).iconY, y -> icon.setLayerIconY(layerIndex, (int)y)).range(0.0f, 4096.0f)).tab("Effects").visibleWhen(visible));
        defs.add(FieldDef.colorSubGui("ability.icon.tint", () -> icon.getLayer((int)layerIndex).tintColor, c -> icon.setLayerTintColor(layerIndex, (int)c)).tab("Effects").visibleWhen(visible));
    }

    @SideOnly(value=Side.CLIENT)
    public SubGuiAbilityConfig createConfigGui(IAbilityConfigCallback callback) {
        return new SubGuiAbilityConfig(this, callback);
    }

    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        Telegraph telegraph;
        double z;
        double y;
        double x;
        boolean positionAtCaster;
        if (!this.showTelegraph || this.telegraphType == TelegraphType.NONE) {
            return null;
        }
        float yaw = caster.field_70177_z;
        boolean bl = positionAtCaster = this.targetingMode == TargetingMode.AOE_SELF || this.targetingMode == TargetingMode.SELF || this.telegraphType == TelegraphType.LINE || this.telegraphType == TelegraphType.CONE;
        if (positionAtCaster) {
            x = caster.field_70165_t;
            y = Ability.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
            z = caster.field_70161_v;
        } else if (target != null) {
            x = target.field_70165_t;
            y = Ability.findGroundLevel(caster.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v);
            z = target.field_70161_v;
        } else {
            x = caster.field_70165_t;
            y = Ability.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
            z = caster.field_70161_v;
        }
        switch (this.telegraphType) {
            case CIRCLE: {
                telegraph = Telegraph.circle(this.getTelegraphRadius());
                break;
            }
            case RING: {
                telegraph = Telegraph.ring(this.getTelegraphRadius(), this.getTelegraphInnerRadius());
                break;
            }
            case LINE: {
                telegraph = Telegraph.line(this.getTelegraphLength(), this.getTelegraphWidth());
                break;
            }
            case CONE: {
                telegraph = Telegraph.cone(this.getTelegraphLength(), this.getTelegraphAngle(), this.getTelegraphInnerRadius());
                break;
            }
            case POINT: {
                telegraph = new Telegraph("", TelegraphType.POINT);
                break;
            }
            case SQUARE: {
                telegraph = Telegraph.square(this.getTelegraphRadius());
                break;
            }
            default: {
                return null;
            }
        }
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setCasterEntityId(caster.func_145782_y());
        if (positionAtCaster) {
            instance.setEntityIdToFollow(caster.func_145782_y());
            if (!(this.telegraphType != TelegraphType.LINE && this.telegraphType != TelegraphType.CONE || this.isRotationLockedDuringWindup())) {
                if (target != null) {
                    instance.setTargetEntityId(target.func_145782_y());
                } else {
                    instance.setTrackFollowedEntityYaw(true);
                }
            }
        } else if (target != null) {
            instance.setEntityIdToFollow(target.func_145782_y());
        }
        return instance;
    }

    public List<TelegraphInstance> createTelegraphs(EntityLivingBase caster, EntityLivingBase target) {
        TelegraphInstance instance = this.createTelegraph(caster, target);
        if (instance == null) {
            return new ArrayList<TelegraphInstance>();
        }
        return new ArrayList<TelegraphInstance>(Collections.singletonList(instance));
    }

    public static double findGroundLevel(World world, double x, double startY, double z) {
        return Ability.findGroundLevel(world, x, startY, z, 3);
    }

    public static double findGroundLevel(World world, double x, double startY, double z, int maxSearchDown) {
        int startBlockY;
        if (world == null) {
            return startY - 0.5;
        }
        int blockX = (int)Math.floor(x);
        int blockZ = (int)Math.floor(z);
        for (int checkY = startBlockY = (int)Math.floor(startY); checkY >= startBlockY - maxSearchDown && checkY >= 0; --checkY) {
            Block block = world.func_147439_a(blockX, checkY, blockZ);
            if (block == null || !block.func_149688_o().func_76220_a()) continue;
            return checkY + 1;
        }
        return startY - 0.5;
    }

    public List<TelegraphInstance> getTelegraphInstances() {
        return this.telegraphInstances;
    }

    public void setTelegraphInstances(List<TelegraphInstance> instances) {
        this.telegraphInstances = instances != null ? instances : new ArrayList();
    }

    public void start(EntityLivingBase target) {
        this.currentTick = 0;
        this.currentTarget = target;
        this.executionStartTime = System.currentTimeMillis();
        this.burstIndex = 0;
        this.burstEntities.clear();
        this.phase = this.windUpTicks <= 0 ? AbilityPhase.ACTIVE : AbilityPhase.WINDUP;
    }

    public boolean tick() {
        if (this.phase == AbilityPhase.IDLE) {
            return false;
        }
        ++this.currentTick;
        switch (this.phase) {
            case WINDUP: {
                if (this.currentTick < this.windUpTicks) break;
                this.phase = AbilityPhase.ACTIVE;
                this.currentTick = 0;
                return true;
            }
            case ACTIVE: {
                break;
            }
            case DAZED: {
                if (this.currentTick < this.dazedTicks) break;
                this.phase = AbilityPhase.IDLE;
                this.currentTick = 0;
                return true;
            }
            case BURST_DELAY: {
                if (this.currentTick < this.burstDelay) break;
                this.phase = this.burstReplayAnimations && this.getWindUpTicks() > 0 ? AbilityPhase.WINDUP : AbilityPhase.ACTIVE;
                this.currentTick = 0;
                return true;
            }
        }
        return false;
    }

    public boolean signalCompletion() {
        if (this.phase == AbilityPhase.ACTIVE) {
            if (this.burstEnabled && this.burstAmount > 0 && this.burstIndex < this.burstAmount) {
                this.resetForBurst();
                ++this.burstIndex;
                this.phase = AbilityPhase.BURST_DELAY;
                this.currentTick = 0;
                return true;
            }
            this.burstEntities.clear();
            if (this.freeOnCast) {
                this.detach();
            } else {
                this.cleanup();
            }
            this.phase = AbilityPhase.IDLE;
            this.currentTick = 0;
            return true;
        }
        return false;
    }

    public void interrupt() {
        this.cleanupBurstEntities();
        this.cleanup();
        if (this.interruptible && this.phase == AbilityPhase.WINDUP) {
            this.phase = AbilityPhase.DAZED;
            this.currentTick = 0;
        } else {
            this.phase = AbilityPhase.IDLE;
            this.currentTick = 0;
        }
        this.burstIndex = 0;
        this.currentTarget = null;
        this.telegraphInstances.clear();
    }

    public void cancel() {
        this.cleanupBurstEntities();
        this.cleanup();
        this.phase = AbilityPhase.IDLE;
        this.currentTick = 0;
        this.burstIndex = 0;
        this.currentTarget = null;
        this.telegraphInstances.clear();
    }

    @Override
    public boolean isExecuting() {
        return this.phase != AbilityPhase.IDLE;
    }

    public boolean canInterrupt(DamageSource source) {
        if (!this.interruptible || this.phase != AbilityPhase.WINDUP) {
            return false;
        }
        if (this.isInvulnerableForCurrentPhase()) {
            return false;
        }
        if (source == null) {
            return false;
        }
        if (source.func_82725_o() || source.func_76347_k() || source.func_94541_c()) {
            return false;
        }
        return source.func_76346_g() != null;
    }

    public void reset() {
        this.cleanupBurstEntities();
        this.cleanup();
        this.phase = AbilityPhase.IDLE;
        this.currentTick = 0;
        this.burstIndex = 0;
        this.currentTarget = null;
        this.telegraphInstances.clear();
        this.previewMode = false;
        this.previewEntityHandler = null;
        this.instanceScript = null;
    }

    public void cleanup() {
    }

    protected void cleanupBurstEntities() {
        for (Entity e : this.burstEntities) {
            if (e == null || e.field_70128_L) continue;
            this.killAbilityEntity(e);
        }
        this.burstEntities.clear();
    }

    public boolean isPreview() {
        return this.previewMode;
    }

    public void setPreviewMode(boolean preview) {
        this.previewMode = preview;
    }

    public void setPreviewEntityHandler(PreviewEntityHandler handler) {
        this.previewEntityHandler = handler;
    }

    protected void spawnAbilityEntity(Entity entity) {
        if (this.previewMode && this.previewEntityHandler != null) {
            this.previewEntityHandler.onEntitySpawned(entity);
        } else {
            entity.field_70170_p.func_72838_d(entity);
            if (!entity.field_70170_p.field_72995_K && entity instanceof EntityEnergyProjectile) {
                EventHooks.onEnergyProjectileFired((EntityEnergyProjectile)entity);
            }
        }
        if (this.burstEnabled && this.burstOverlap) {
            this.burstEntities.add(entity);
        }
    }

    protected void killAbilityEntity(Entity entity) {
        if (entity == null) {
            return;
        }
        entity.func_70106_y();
        if (this.previewMode && this.previewEntityHandler != null) {
            this.previewEntityHandler.onEntityRemoved(entity);
        }
    }

    public int getMaxPreviewDuration() {
        return 200;
    }

    @Override
    public boolean checkConditions(EntityLivingBase caster, EntityLivingBase target) {
        for (AbilityCondition c : this.conditions) {
            Boolean extendedCondition;
            if (!c.getUserType().allowsNpc() || !((extendedCondition = AbilityController.Instance.fireCheckConditions(c, caster, target)) != null ? extendedCondition == false : !c.check(caster, target))) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean checkConditionsForPlayer(EntityLivingBase caster) {
        for (AbilityCondition c : this.conditions) {
            Boolean extendedCondition;
            if (!c.getUserType().allowsPlayer() || c.requiresTarget() || !((extendedCondition = AbilityController.Instance.fireCheckConditionsForPlayer(c, caster)) != null ? extendedCondition == false : !c.check(caster, null))) continue;
            return false;
        }
        return true;
    }

    @Override
    public NBTTagCompound writeNBT(boolean saveScripts) {
        AbilityScript handler;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("id", this.id);
        nbt.func_74778_a("name", this.name);
        nbt.func_74778_a("displayName", this.displayName);
        nbt.func_74778_a("typeId", this.typeId);
        nbt.func_74768_a("weight", this.weight);
        nbt.func_74757_a("enabled", this.enabled);
        nbt.func_74778_a("targetingMode", this.targetingMode.name());
        nbt.func_74776_a("minRange", this.minRange);
        nbt.func_74776_a("maxRange", this.maxRange);
        nbt.func_74768_a("cooldown", this.cooldownTicks);
        nbt.func_74768_a("windUp", this.windUpTicks);
        nbt.func_74757_a("syncWindup", this.syncWindupWithAnimation);
        nbt.func_74768_a("recovery", this.dazedTicks);
        nbt.func_74757_a("interruptible", this.interruptible);
        nbt.func_74768_a("invulnerableMode", this.invulnerableMode.ordinal());
        nbt.func_74757_a("ignoreIFrames", this.ignoreIFrames);
        nbt.func_74768_a("lockMovement", this.lockMovement.ordinal());
        nbt.func_74768_a("rotationMode", this.rotationMode.ordinal());
        nbt.func_74768_a("rotationPhase", this.rotationPhase.ordinal());
        nbt.func_74776_a("trackSpeed", this.trackSpeed);
        nbt.func_74768_a("windUpColor", this.windUpColor);
        nbt.func_74768_a("activeColor", this.activeColor);
        nbt.func_74778_a("windUpSound", this.windUpSound);
        nbt.func_74778_a("activeSound", this.activeSound);
        nbt.func_74768_a("windUpAnimationId", this.windUpAnimationId);
        nbt.func_74768_a("activeAnimationId", this.activeAnimationId);
        nbt.func_74768_a("dazedAnimationId", this.dazedAnimationId);
        nbt.func_74778_a("windUpAnimationName", this.resolveAnimationName(this.windUpAnimationId, this.windUpAnimationName));
        nbt.func_74778_a("activeAnimationName", this.resolveAnimationName(this.activeAnimationId, this.activeAnimationName));
        nbt.func_74778_a("dazedAnimationName", this.resolveAnimationName(this.dazedAnimationId, this.dazedAnimationName));
        nbt.func_74757_a("showTelegraph", this.showTelegraph);
        nbt.func_74778_a("telegraphType", this.telegraphType.name());
        nbt.func_74776_a("telegraphHeightOffset", this.telegraphHeightOffset);
        nbt.func_74782_a("customData", (NBTBase)((NBTTagCompound)this.customData.func_74737_b()));
        TagController.writeTagUUIDs(nbt, "TagUUIDs", this.tagUUIDs);
        this.magicData.writeToNBT(nbt);
        nbt.func_74768_a("allowedBy", this.allowedBy.ordinal());
        nbt.func_74757_a("ignoreCooldown", this.ignoreCooldown);
        nbt.func_74757_a("perAbilityCooldown", this.perAbilityCooldown);
        nbt.func_74757_a("freeOnCast", this.freeOnCast);
        nbt.func_74768_a("toggleStates", this.toggleStates);
        nbt.func_74757_a("hasActiveToggle", this.hasActiveToggle);
        if (this.toggleStateLabels != null && this.toggleStateLabels.length > 0) {
            NBTTagList labelList = new NBTTagList();
            for (String label : this.toggleStateLabels) {
                labelList.func_74742_a((NBTBase)new NBTTagString(label != null ? label : ""));
            }
            nbt.func_74782_a("toggleStateLabels", (NBTBase)labelList);
        }
        nbt.func_74757_a("burstEnabled", this.burstEnabled);
        nbt.func_74768_a("burstAmount", this.burstAmount);
        nbt.func_74768_a("burstDelay", this.burstDelay);
        nbt.func_74757_a("burstReplayAnimations", this.burstReplayAnimations);
        nbt.func_74757_a("burstOverlap", this.burstOverlap);
        NBTTagList condList = new NBTTagList();
        for (AbilityCondition c : this.conditions) {
            condList.func_74742_a((NBTBase)c.writeNBT());
        }
        nbt.func_74782_a("conditions", (NBTBase)condList);
        NBTTagList effectList = new NBTTagList();
        for (AbilityPotionEffect effect : this.effects) {
            effectList.func_74742_a((NBTBase)effect.writeNBT());
        }
        nbt.func_74782_a("effects", (NBTBase)effectList);
        NBTTagCompound typeNBT = new NBTTagCompound();
        this.writeTypeNBT(typeNBT);
        nbt.func_74782_a("typeData", (NBTBase)typeNBT);
        if (saveScripts && (handler = this.getScriptHandler()) != null) {
            NBTTagCompound scriptData = new NBTTagCompound();
            handler.writeToNBT(scriptData);
            nbt.func_74782_a("ScriptData", (NBTBase)scriptData);
        }
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        int i;
        this.id = nbt.func_74779_i("id");
        this.name = nbt.func_74779_i("name");
        this.displayName = nbt.func_74779_i("displayName");
        this.typeId = nbt.func_74779_i("typeId");
        this.weight = nbt.func_74764_b("weight") ? nbt.func_74762_e("weight") : 10;
        this.enabled = nbt.func_74764_b("enabled") ? nbt.func_74767_n("enabled") : true;
        try {
            this.targetingMode = TargetingMode.valueOf(nbt.func_74779_i("targetingMode"));
        }
        catch (Exception e) {
            this.targetingMode = TargetingMode.AGGRO_TARGET;
        }
        this.minRange = nbt.func_74760_g("minRange");
        this.maxRange = nbt.func_74760_g("maxRange");
        this.cooldownTicks = nbt.func_74762_e("cooldown");
        this.windUpTicks = nbt.func_74762_e("windUp");
        this.syncWindupWithAnimation = nbt.func_74764_b("syncWindup") ? nbt.func_74767_n("syncWindup") : true;
        this.dazedTicks = nbt.func_74764_b("recovery") ? nbt.func_74762_e("recovery") : 80;
        this.interruptible = nbt.func_74764_b("interruptible") ? nbt.func_74767_n("interruptible") : true;
        this.invulnerableMode = nbt.func_74764_b("invulnerableMode") ? InvulnerableMode.fromOrdinal(nbt.func_74762_e("invulnerableMode")) : InvulnerableMode.NONE;
        this.ignoreIFrames = nbt.func_74764_b("ignoreIFrames") && nbt.func_74767_n("ignoreIFrames");
        this.lockMovement = LockMode.fromOrdinal(nbt.func_74762_e("lockMovement"));
        this.rotationMode = RotationMode.fromOrdinal(nbt.func_74762_e("rotationMode"));
        this.rotationPhase = LockMode.fromOrdinal(nbt.func_74762_e("rotationPhase"));
        this.trackSpeed = nbt.func_74764_b("trackSpeed") ? nbt.func_74760_g("trackSpeed") : 0.0f;
        this.windUpColor = nbt.func_74764_b("windUpColor") ? nbt.func_74762_e("windUpColor") : -2130754560;
        this.activeColor = nbt.func_74764_b("activeColor") ? nbt.func_74762_e("activeColor") : -1057030144;
        this.windUpSound = nbt.func_74779_i("windUpSound");
        this.activeSound = nbt.func_74779_i("activeSound");
        this.windUpAnimationId = nbt.func_74762_e("windUpAnimationId");
        this.activeAnimationId = nbt.func_74762_e("activeAnimationId");
        this.dazedAnimationId = nbt.func_74764_b("dazedAnimationId") ? nbt.func_74762_e("dazedAnimationId") : -1;
        this.windUpAnimationName = nbt.func_74779_i("windUpAnimationName");
        this.activeAnimationName = nbt.func_74779_i("activeAnimationName");
        this.dazedAnimationName = nbt.func_74764_b("dazedAnimationName") ? nbt.func_74779_i("dazedAnimationName") : "Ability_Generic_Dazed";
        this.showTelegraph = nbt.func_74764_b("showTelegraph") ? nbt.func_74767_n("showTelegraph") : true;
        try {
            this.telegraphType = TelegraphType.valueOf(nbt.func_74779_i("telegraphType"));
        }
        catch (Exception e) {
            this.telegraphType = TelegraphType.CIRCLE;
        }
        this.telegraphHeightOffset = nbt.func_74760_g("telegraphHeightOffset");
        this.customData = (NBTTagCompound)nbt.func_74775_l("customData").func_74737_b();
        this.tagUUIDs = TagController.readTagUUIDs(nbt, "TagUUIDs");
        this.magicData.readToNBT(nbt);
        this.allowedBy = UserType.fromOrdinal(nbt.func_74762_e("allowedBy"));
        this.ignoreCooldown = nbt.func_74767_n("ignoreCooldown");
        this.perAbilityCooldown = nbt.func_74767_n("perAbilityCooldown");
        this.freeOnCast = nbt.func_74767_n("freeOnCast");
        this.toggleStates = nbt.func_74762_e("toggleStates");
        this.hasActiveToggle = nbt.func_74767_n("hasActiveToggle");
        if (nbt.func_74764_b("toggleStateLabels")) {
            NBTTagList labelList = nbt.func_150295_c("toggleStateLabels", 8);
            this.toggleStateLabels = new String[labelList.func_74745_c()];
            for (i = 0; i < labelList.func_74745_c(); ++i) {
                String label = labelList.func_150307_f(i);
                this.toggleStateLabels[i] = label != null && !label.isEmpty() ? label : null;
            }
        } else {
            this.toggleStateLabels = null;
        }
        this.burstEnabled = nbt.func_74767_n("burstEnabled");
        this.burstAmount = nbt.func_74762_e("burstAmount");
        this.burstDelay = nbt.func_74762_e("burstDelay");
        this.burstReplayAnimations = nbt.func_74764_b("burstReplayAnimations") ? nbt.func_74767_n("burstReplayAnimations") : true;
        this.burstOverlap = nbt.func_74767_n("burstOverlap");
        this.conditions.clear();
        NBTTagList condList = nbt.func_150295_c("conditions", 10);
        for (i = 0; i < condList.func_74745_c(); ++i) {
            AbilityCondition c = AbilityCondition.fromNBT(condList.func_150305_b(i));
            if (c == null) continue;
            this.conditions.add(c);
        }
        this.effects.clear();
        if (nbt.func_74764_b("effects")) {
            NBTTagList effectList = nbt.func_150295_c("effects", 10);
            for (int i2 = 0; i2 < effectList.func_74745_c(); ++i2) {
                AbilityPotionEffect effect = AbilityPotionEffect.fromNBT(effectList.func_150305_b(i2));
                if (effect == null || !effect.isValid()) continue;
                this.effects.add(effect);
            }
        }
        if (nbt.func_74764_b("typeData")) {
            this.readTypeNBT(nbt.func_74775_l("typeData"));
        }
        if (nbt.func_150297_b("ScriptData", 10) && this.getScriptHandler() == null) {
            AbilityScript handler = new AbilityScript(this.id);
            handler.readFromNBT(nbt.func_74775_l("ScriptData"));
            this.setScriptHandler(handler);
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (this.builtIn) {
            this.name = name != null ? name : "";
            return;
        }
        String fallback = this.name != null && !this.name.isEmpty() ? this.name : "Ability";
        this.name = FileNameHelper.sanitizeName(name, fallback);
    }

    @Override
    public String getDisplayName() {
        String result = this.displayName != null && !this.displayName.isEmpty() ? this.displayName : FileNameHelper.toDisplayName(this.name);
        return result != null ? result.replaceAll("&([0-9a-fk-or])", "\u00a7$1") : "";
    }

    public String getRawDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName != null ? displayName : "";
    }

    @Override
    public String getTypeId() {
        return this.typeId;
    }

    public boolean isBuiltIn() {
        return this.builtIn;
    }

    public String getRegistryKey() {
        return this.registryKey;
    }

    protected void configureAsBuiltIn(String registryKey) {
        this.builtIn = true;
        this.registryKey = registryKey;
        this.id = registryKey;
        this.name = registryKey;
        this.typeId = "ability." + registryKey.replace(':', '.');
    }

    public DefaultIconLayer[] getDefaultIconLayers() {
        return this.defaultIconLayers;
    }

    public int getDefaultIconWidth() {
        return this.defaultIconWidth;
    }

    public int getDefaultIconHeight() {
        return this.defaultIconHeight;
    }

    public boolean hasDefaultIcon() {
        return this.defaultIconLayers != null && this.defaultIconLayers.length > 0 && this.defaultIconLayers[0].texture != null && !this.defaultIconLayers[0].texture.isEmpty();
    }

    public Ability deepCopy() {
        Ability copy = AbilityController.Instance.fromNBT(this.writeNBT(false));
        if (copy != null) {
            copy.playerRequirement = this.playerRequirement;
        }
        return copy;
    }

    @Override
    public boolean isChain() {
        return false;
    }

    @Override
    public IAbilityAction deepCopyAction() {
        return this.deepCopy();
    }

    public boolean isNpcInlineEdit() {
        return this.npcInlineEdit;
    }

    public void setNpcInlineEdit(boolean npcInlineEdit) {
        this.npcInlineEdit = npcInlineEdit;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public TargetingMode getTargetingMode() {
        return this.targetingMode;
    }

    public void setTargetingMode(TargetingMode targetingMode) {
        this.targetingMode = targetingMode;
    }

    @Override
    public float getMinRange() {
        return this.minRange;
    }

    @Override
    public void setMinRange(float minRange) {
        this.minRange = minRange;
    }

    @Override
    public float getMaxRange() {
        return this.maxRange;
    }

    @Override
    public void setMaxRange(float maxRange) {
        this.maxRange = maxRange;
    }

    @Override
    public int getCooldownTicks() {
        return this.cooldownTicks;
    }

    @Override
    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @Override
    public int getWindUpTicks() {
        return this.calculateWindupFromAnimation();
    }

    public int getRawWindUpTicks() {
        return this.windUpTicks;
    }

    @Override
    public void setWindUpTicks(int windUpTicks) {
        this.windUpTicks = Math.max(0, windUpTicks);
    }

    public boolean isSyncWindupWithAnimation() {
        return this.syncWindupWithAnimation;
    }

    public boolean hasWindUpAnimation() {
        return this.windUpAnimationName != null && !this.windUpAnimationName.isEmpty() || this.windUpAnimationId >= 0;
    }

    public void setSyncWindupWithAnimation(boolean syncWindupWithAnimation) {
        this.syncWindupWithAnimation = syncWindupWithAnimation;
    }

    @Override
    public int getDazedTicks() {
        return this.dazedTicks;
    }

    @Override
    public void setDazedTicks(int dazedTicks) {
        this.dazedTicks = Math.max(0, dazedTicks);
    }

    @Override
    public boolean isInterruptible() {
        return this.interruptible;
    }

    @Override
    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    public InvulnerableMode getInvulnerableMode() {
        return this.invulnerableMode;
    }

    public void setInvulnerableMode(InvulnerableMode invulnerableMode) {
        this.invulnerableMode = invulnerableMode != null ? invulnerableMode : InvulnerableMode.NONE;
    }

    public boolean isInvulnerableDuringWindup() {
        return this.invulnerableMode.invulnerableDuringWindup();
    }

    public boolean isInvulnerableDuringActive() {
        return this.invulnerableMode.invulnerableDuringActive();
    }

    @Override
    public boolean isIgnoreIFrames() {
        return this.ignoreIFrames;
    }

    @Override
    public void setIgnoreIFrames(boolean ignore) {
        this.ignoreIFrames = ignore;
    }

    public LockMode getLockMovement() {
        return this.lockMovement;
    }

    public void setLockMovement(LockMode lockMovement) {
        this.lockMovement = lockMovement;
    }

    public RotationMode getRotationMode() {
        return this.rotationMode;
    }

    public void setRotationMode(RotationMode rotationMode) {
        this.rotationMode = rotationMode;
    }

    public LockMode getRotationPhase() {
        return this.rotationPhase;
    }

    public void setRotationPhase(LockMode rotationPhase) {
        this.rotationPhase = rotationPhase;
    }

    public float getTrackSpeed() {
        return this.trackSpeed;
    }

    public void setTrackSpeed(float trackSpeed) {
        this.trackSpeed = trackSpeed;
    }

    @Override
    public int getLockMovementType() {
        return this.lockMovement.ordinal();
    }

    @Override
    public void setLockMovementType(int type) {
        this.lockMovement = LockMode.fromOrdinal(type);
    }

    @Override
    public int getRotationModeType() {
        return this.rotationMode.ordinal();
    }

    @Override
    public void setRotationModeType(int type) {
        this.rotationMode = RotationMode.fromOrdinal(type);
    }

    @Override
    public int getRotationPhaseType() {
        return this.rotationPhase.ordinal();
    }

    @Override
    public void setRotationPhaseType(int type) {
        this.rotationPhase = LockMode.fromOrdinal(type);
    }

    @Override
    public float getTrackSpeedValue() {
        return this.trackSpeed;
    }

    @Override
    public void setTrackSpeedValue(float speed) {
        this.trackSpeed = Math.max(0.0f, speed);
    }

    @Override
    public boolean isMovementLockedDuringWindup() {
        return this.lockMovement.locksWindup();
    }

    @Override
    public boolean isMovementLockedDuringActive() {
        return this.lockMovement.locksActive();
    }

    @Override
    public boolean isRotationLockedDuringWindup() {
        return this.rotationMode == RotationMode.LOCKED && this.rotationPhase.locksWindup();
    }

    @Override
    public boolean isRotationLockedDuringActive() {
        return this.rotationMode == RotationMode.LOCKED && this.rotationPhase.locksActive();
    }

    public boolean isMovementLockedForCurrentPhase() {
        switch (this.phase) {
            case WINDUP: {
                return this.isMovementLockedDuringWindup();
            }
            case ACTIVE: {
                return this.isMovementLockedDuringActive();
            }
            case BURST_DELAY: {
                return false;
            }
        }
        return false;
    }

    public boolean isInvulnerableForCurrentPhase() {
        return this.invulnerableMode.isInvulnerableInPhase(this.phase);
    }

    public boolean isInvulnerableForPhase(AbilityPhase phase) {
        return this.invulnerableMode.isInvulnerableInPhase(phase);
    }

    public boolean isRotationLockedForCurrentPhase() {
        switch (this.phase) {
            case WINDUP: {
                return this.isRotationLockedDuringWindup();
            }
            case ACTIVE: {
                return this.isRotationLockedDuringActive();
            }
            case BURST_DELAY: {
                return false;
            }
        }
        return false;
    }

    public boolean isHitScanForCurrentPhase() {
        if (this.rotationMode != RotationMode.TRACK) {
            return false;
        }
        switch (this.phase) {
            case WINDUP: {
                return this.rotationPhase.locksWindup();
            }
            case ACTIVE: {
                return this.rotationPhase.locksActive();
            }
            case BURST_DELAY: {
                return false;
            }
        }
        return false;
    }

    public int getWindUpColor() {
        return this.windUpColor;
    }

    public void setWindUpColor(int windUpColor) {
        this.windUpColor = windUpColor;
    }

    public int getActiveColor() {
        return this.activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public String getWindUpSound() {
        return this.windUpSound;
    }

    public void setWindUpSound(String windUpSound) {
        this.windUpSound = windUpSound;
    }

    public String getActiveSound() {
        return this.activeSound;
    }

    public void setActiveSound(String activeSound) {
        this.activeSound = activeSound;
    }

    protected String resolveAnimationName(int animId, String storedName) {
        Animation anim;
        if (animId >= 0 && AnimationController.Instance != null && (anim = (Animation)AnimationController.Instance.get(animId)) != null && anim.name != null && !anim.name.isEmpty()) {
            return anim.name;
        }
        return storedName;
    }

    public Animation getWindUpAnimation() {
        if (AnimationController.Instance == null) {
            return null;
        }
        if (this.windUpAnimationId >= 0) {
            return (Animation)AnimationController.Instance.get(this.windUpAnimationId);
        }
        if (this.windUpAnimationName != null && !this.windUpAnimationName.isEmpty()) {
            return (Animation)AnimationController.Instance.get(this.windUpAnimationName, true);
        }
        return null;
    }

    public int getWindUpAnimationId() {
        return this.windUpAnimationId;
    }

    public void setWindUpAnimationId(int windUpAnimationId) {
        this.windUpAnimationId = windUpAnimationId;
    }

    public String getWindUpAnimationName() {
        return this.windUpAnimationName;
    }

    public void setWindUpAnimationName(String windUpAnimationName) {
        this.windUpAnimationName = windUpAnimationName != null ? windUpAnimationName : "";
    }

    public Animation getActiveAnimation() {
        if (AnimationController.Instance == null) {
            return null;
        }
        if (this.activeAnimationId >= 0) {
            return (Animation)AnimationController.Instance.get(this.activeAnimationId);
        }
        if (this.activeAnimationName != null && !this.activeAnimationName.isEmpty()) {
            return (Animation)AnimationController.Instance.get(this.activeAnimationName, true);
        }
        return null;
    }

    public int getActiveAnimationId() {
        return this.activeAnimationId;
    }

    public void setActiveAnimationId(int activeAnimationId) {
        this.activeAnimationId = activeAnimationId;
    }

    public String getActiveAnimationName() {
        return this.activeAnimationName;
    }

    public void setActiveAnimationName(String activeAnimationName) {
        this.activeAnimationName = activeAnimationName != null ? activeAnimationName : "";
    }

    public Animation getDazedAnimation() {
        if (AnimationController.Instance == null) {
            return null;
        }
        if (this.dazedAnimationId >= 0) {
            return (Animation)AnimationController.Instance.get(this.dazedAnimationId);
        }
        if (this.dazedAnimationName != null && !this.dazedAnimationName.isEmpty()) {
            return (Animation)AnimationController.Instance.get(this.dazedAnimationName, true);
        }
        return null;
    }

    public int getDazedAnimationId() {
        return this.dazedAnimationId;
    }

    public void setDazedAnimationId(int dazedAnimationId) {
        this.dazedAnimationId = dazedAnimationId;
    }

    public String getDazedAnimationName() {
        return this.dazedAnimationName;
    }

    public void setDazedAnimationName(String dazedAnimationName) {
        this.dazedAnimationName = dazedAnimationName != null ? dazedAnimationName : "";
    }

    public boolean isShowTelegraph() {
        return this.showTelegraph;
    }

    public void setShowTelegraph(boolean showTelegraph) {
        this.showTelegraph = showTelegraph;
    }

    public TelegraphType getTelegraphType() {
        return this.telegraphType;
    }

    public void setTelegraphType(TelegraphType telegraphType) {
        this.telegraphType = telegraphType;
    }

    public float getTelegraphHeightOffset() {
        return this.telegraphHeightOffset;
    }

    public void setTelegraphHeightOffset(float telegraphHeightOffset) {
        this.telegraphHeightOffset = telegraphHeightOffset;
    }

    public AbilityPhase getPhase() {
        return this.phase;
    }

    public float getDamageMultiplier() {
        return this.damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public int getPhaseInt() {
        return this.phase.ordinal();
    }

    @Override
    public int getCurrentTick() {
        return this.currentTick;
    }

    public EntityLivingBase getCurrentTarget() {
        return this.currentTarget;
    }

    public NBTTagCompound getCustomData() {
        return this.customData;
    }

    public HashSet<UUID> getTagUUIDs() {
        return this.tagUUIDs;
    }

    public void setTagUUIDs(HashSet<UUID> tagUUIDs) {
        this.tagUUIDs = tagUUIDs;
    }

    @Override
    public MagicData getMagicData() {
        return this.magicData;
    }

    public MagicData resolveMagicData(EntityLivingBase caster) {
        if (!this.magicData.isEmpty()) {
            return this.magicData;
        }
        if (caster instanceof EntityPlayer) {
            PlayerData data = PlayerData.get((EntityPlayer)caster);
            if (data != null && data.magicData != null && !data.magicData.isEmpty()) {
                return data.magicData;
            }
        } else if (caster instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)caster;
            if (npc.stats != null && npc.stats.magicData != null && !npc.stats.magicData.isEmpty()) {
                return npc.stats.magicData;
            }
        }
        return null;
    }

    @Override
    public List<AbilityCondition> getConditions() {
        return this.conditions;
    }

    public void addCondition(AbilityCondition c) {
        this.conditions.add(c);
    }

    @Override
    public UserType getAllowedBy() {
        return this.allowedBy;
    }

    public void setAllowedBy(UserType allowedBy) {
        this.allowedBy = allowedBy;
    }

    public Predicate<EntityPlayer> getPlayerRequirement() {
        return this.playerRequirement;
    }

    public void setPlayerRequirement(Predicate<EntityPlayer> requirement) {
        this.playerRequirement = requirement;
    }

    @Override
    public boolean isAvailableFor(EntityPlayer player) {
        return this.playerRequirement == null || this.playerRequirement.test(player);
    }

    @Override
    public boolean isIgnoreCooldown() {
        return this.ignoreCooldown;
    }

    @Override
    public void setIgnoreCooldown(boolean ignoreCooldown) {
        this.ignoreCooldown = ignoreCooldown;
    }

    @Override
    public boolean isPerAbilityCooldown() {
        return this.perAbilityCooldown;
    }

    @Override
    public void setPerAbilityCooldown(boolean perAbilityCooldown) {
        this.perAbilityCooldown = perAbilityCooldown;
    }

    public boolean isFreeOnCast() {
        return this.freeOnCast;
    }

    public void setFreeOnCast(boolean freeOnCast) {
        this.freeOnCast = freeOnCast;
    }

    @Override
    public boolean isToggleable() {
        return this.toggleStates > 0;
    }

    @Override
    public int getToggleStates() {
        return this.toggleStates;
    }

    @Override
    public void setToggleStates(int toggleStates) {
        this.toggleStates = Math.max(0, toggleStates);
    }

    public boolean hasActiveToggle() {
        return this.hasActiveToggle;
    }

    public void setHasActiveToggle(boolean hasActiveToggle) {
        this.hasActiveToggle = hasActiveToggle;
    }

    @Override
    public String getToggleStateLabel(int state) {
        if (this.toggleStateLabels != null && state >= 1 && state <= this.toggleStateLabels.length) {
            return this.toggleStateLabels[state - 1];
        }
        return null;
    }

    public void setToggleStateLabels(String ... labels) {
        this.toggleStateLabels = labels != null && labels.length > 0 ? labels : null;
    }

    public List<AbilityPotionEffect> getEffects() {
        return this.effects;
    }

    public void setEffects(List<AbilityPotionEffect> effects) {
        this.effects = effects != null ? effects : new ArrayList();
    }

    public void addEffect(AbilityPotionEffect effect) {
        if (effect != null && effect.isValid()) {
            this.effects.add(effect);
        }
    }

    public void clearEffects() {
        this.effects.clear();
    }

    @Override
    public boolean isBurstEnabled() {
        return this.burstEnabled;
    }

    @Override
    public void setBurstEnabled(boolean burstEnabled) {
        this.burstEnabled = burstEnabled;
    }

    @Override
    public int getBurstAmount() {
        return this.burstAmount;
    }

    @Override
    public void setBurstAmount(int burstAmount) {
        this.burstAmount = Math.max(0, burstAmount);
    }

    @Override
    public int getBurstDelay() {
        return this.burstDelay;
    }

    @Override
    public void setBurstDelay(int burstDelay) {
        this.burstDelay = Math.max(0, burstDelay);
    }

    @Override
    public boolean isBurstReplayAnimations() {
        return this.burstReplayAnimations;
    }

    @Override
    public void setBurstReplayAnimations(boolean burstReplayAnimations) {
        this.burstReplayAnimations = burstReplayAnimations;
    }

    @Override
    public boolean isBurstOverlap() {
        return this.burstOverlap;
    }

    @Override
    public void setBurstOverlap(boolean burstOverlap) {
        this.burstOverlap = burstOverlap;
    }

    public int getBurstIndex() {
        return this.burstIndex;
    }

    private static String[] getRotationPhaseKeys() {
        return new String[]{"ability.lockMove.windup", "ability.lockMove.active", "ability.lockMove.both"};
    }

    private int calculateWindupFromAnimation() {
        if (!this.syncWindupWithAnimation) {
            return this.windUpTicks;
        }
        if (AnimationController.Instance == null) {
            return this.windUpTicks;
        }
        Animation animation = null;
        if (this.windUpAnimationId >= 0) {
            animation = (Animation)AnimationController.Instance.get(this.windUpAnimationId);
        } else if (this.windUpAnimationName != null && !this.windUpAnimationName.isEmpty()) {
            animation = (Animation)AnimationController.Instance.get(this.windUpAnimationName, true);
        }
        if (animation == null || animation.frames.isEmpty()) {
            return this.windUpTicks;
        }
        int totalDuration = 0;
        for (Frame frame : animation.frames) {
            totalDuration += frame.getDuration();
        }
        return totalDuration;
    }

    protected static boolean isMovementBlocked(EntityLivingBase caster, double dirX, double dirZ, double speed) {
        double nextX = dirX * speed;
        double nextZ = dirZ * speed;
        AxisAlignedBB nextBox = caster.field_70121_D.func_72329_c().func_72317_d(nextX, 0.0, nextZ);
        double stepThreshold = nextBox.field_72338_b + Math.max((double)caster.field_70138_W, 0.5);
        int x1 = (int)Math.floor(nextBox.field_72340_a);
        int x2 = (int)Math.floor(nextBox.field_72336_d + 1.0);
        int y1 = (int)Math.floor(nextBox.field_72338_b) - 1;
        int y2 = (int)Math.floor(nextBox.field_72337_e + 1.0);
        int z1 = (int)Math.floor(nextBox.field_72339_c);
        int z2 = (int)Math.floor(nextBox.field_72334_f + 1.0);
        ArrayList collisionBoxes = new ArrayList();
        for (int bx = x1; bx < x2; ++bx) {
            for (int bz = z1; bz < z2; ++bz) {
                for (int by = y1; by < y2; ++by) {
                    Block block = caster.field_70170_p.func_147439_a(bx, by, bz);
                    if (!block.func_149688_o().func_76230_c()) continue;
                    collisionBoxes.clear();
                    block.func_149743_a(caster.field_70170_p, bx, by, bz, nextBox, collisionBoxes, (Entity)caster);
                    for (AxisAlignedBB box : collisionBoxes) {
                        if (!(box.field_72337_e > stepThreshold)) continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected static boolean isMovementBlockedByBarrier(EntityLivingBase caster, double dirX, double dirZ, double speed) {
        if (caster.field_70170_p == null) {
            return false;
        }
        double nextX = caster.field_70165_t + dirX * speed;
        double nextZ = caster.field_70161_v + dirZ * speed;
        double eyeY = caster.field_70163_u + (double)caster.func_70047_e() * 0.5;
        List<EntityEnergyBarrier> barriers = EntityEnergyBarrier.getActiveBarriers(caster.field_70170_p);
        for (EntityEnergyBarrier barrier : barriers) {
            double localY;
            double hitZ;
            double hitX;
            double localRight;
            double t;
            double rayDirZ;
            double rayDirX;
            double denom;
            Entity barrierOwner;
            if (barrier.field_70128_L || barrier.isCharging() || !barrier.getBarrierData().solid || barrier.getOwnerEntityId() == caster.func_145782_y() || (barrierOwner = barrier.getOwnerEntity()) instanceof EntityLivingBase && AbilityTargetHelper.isAlly(caster, (Entity)((EntityLivingBase)barrierOwner))) continue;
            if (barrier instanceof EntityEnergyDome) {
                EntityEnergyDome dome = (EntityEnergyDome)barrier;
                float radius = dome.getDomeRadius();
                double ocX = caster.field_70165_t - dome.field_70165_t;
                double ocY = eyeY - dome.field_70163_u;
                double rdX = nextX - caster.field_70165_t;
                double ocZ = caster.field_70161_v - dome.field_70161_v;
                double rdZ = nextZ - caster.field_70161_v;
                double b = 2.0 * (ocX * rdX + ocZ * rdZ);
                double a = rdX * rdX + rdZ * rdZ;
                double c = ocX * ocX + ocZ * ocZ - (double)radius * (double)radius;
                double discriminant = b * b - 4.0 * a * c;
                if (discriminant < 0.0 || a < 1.0E-10) continue;
                double sqrtD = Math.sqrt(discriminant);
                double t1 = (-b - sqrtD) / (2.0 * a);
                double t2 = (-b + sqrtD) / (2.0 * a);
                if (!(t1 >= 0.0 && t1 <= 1.0) && (!(t2 >= 0.0) || !(t2 <= 1.0))) continue;
                return true;
            }
            if (!(barrier instanceof EntityEnergyPanel)) continue;
            EntityEnergyPanel panel = (EntityEnergyPanel)barrier;
            float halfW = panel.getPanelData().getPanelWidth() * 0.5f;
            float halfH = panel.getPanelData().getPanelHeight() * 0.5f;
            float yawRad = (float)Math.toRadians(panel.getPanelYaw());
            double normalX = -Math.sin(yawRad);
            double normalZ = Math.cos(yawRad);
            double cos = Math.cos(yawRad);
            double sin = Math.sin(yawRad);
            double relX = caster.field_70165_t - panel.field_70165_t;
            double relZ = caster.field_70161_v - panel.field_70161_v;
            double prevDist = relX * normalX + relZ * normalZ;
            double relNX = nextX - panel.field_70165_t;
            double relNZ = nextZ - panel.field_70161_v;
            double currDist = relNX * normalX + relNZ * normalZ;
            if (prevDist * currDist > 0.0 || Math.abs(denom = (rayDirX = nextX - caster.field_70165_t) * normalX + (rayDirZ = nextZ - caster.field_70161_v) * normalZ) < 1.0E-10 || (t = -prevDist / denom) < 0.0 || t > 1.0 || Math.abs(localRight = ((hitX = caster.field_70165_t + rayDirX * t) - panel.field_70165_t) * cos + ((hitZ = caster.field_70161_v + rayDirZ * t) - panel.field_70161_v) * sin) > (double)halfW || Math.abs(localY = eyeY - panel.field_70163_u) > (double)halfH) continue;
            return true;
        }
        return false;
    }

    public static boolean hasLineOfSight(World world, EntityLivingBase caster, EntityLivingBase target) {
        Vec3 end;
        Vec3 start = Vec3.func_72443_a((double)caster.field_70165_t, (double)(caster.field_70163_u + (double)caster.func_70047_e()), (double)caster.field_70161_v);
        MovingObjectPosition result = world.func_72933_a(start, end = Vec3.func_72443_a((double)target.field_70165_t, (double)(target.field_70163_u + (double)target.field_70131_O * 0.5), (double)target.field_70161_v));
        return result == null;
    }

    public static boolean isBlockedByBarrier(World world, EntityLivingBase caster, EntityLivingBase target) {
        double searchRange = 55.0;
        double minX = Math.min(caster.field_70165_t, target.field_70165_t) - searchRange;
        double minY = Math.min(caster.field_70163_u, target.field_70163_u) - searchRange;
        double minZ = Math.min(caster.field_70161_v, target.field_70161_v) - searchRange;
        double maxX = Math.max(caster.field_70165_t, target.field_70165_t) + searchRange;
        double maxY = Math.max(caster.field_70163_u, target.field_70163_u) + searchRange;
        double maxZ = Math.max(caster.field_70161_v, target.field_70161_v) + searchRange;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
        List barriers = world.func_72872_a(EntityEnergyBarrier.class, searchBox);
        for (EntityEnergyBarrier barrier : barriers) {
            Entity barrierOwner;
            if (barrier.field_70128_L || barrier.isCharging() || barrier.getOwnerEntityId() == caster.func_145782_y() || (barrierOwner = barrier.getOwnerEntity()) instanceof EntityLivingBase && AbilityTargetHelper.isAlly(caster, (Entity)((EntityLivingBase)barrierOwner)) || !(barrier instanceof EntityEnergyDome ? Ability.isDomeBlocking((EntityEnergyDome)barrier, caster, target) : barrier instanceof EntityEnergyPanel && Ability.isPanelBlocking((EntityEnergyPanel)barrier, caster, target))) continue;
            return true;
        }
        return false;
    }

    private static boolean isDomeBlocking(EntityEnergyDome dome, EntityLivingBase caster, EntityLivingBase target) {
        float radius = dome.getDomeRadius();
        double cdx = caster.field_70165_t - dome.field_70165_t;
        double cdy = caster.field_70163_u + (double)caster.func_70047_e() - dome.field_70163_u;
        double cdz = caster.field_70161_v - dome.field_70161_v;
        double casterDist = Math.sqrt(cdx * cdx + cdy * cdy + cdz * cdz);
        double tdx = target.field_70165_t - dome.field_70165_t;
        double tdy = target.field_70163_u + (double)target.field_70131_O * 0.5 - dome.field_70163_u;
        double tdz = target.field_70161_v - dome.field_70161_v;
        double targetDist = Math.sqrt(tdx * tdx + tdy * tdy + tdz * tdz);
        return casterDist > (double)radius && targetDist < (double)radius;
    }

    private static boolean isPanelBlocking(EntityEnergyPanel panel, EntityLivingBase caster, EntityLivingBase target) {
        float halfW = panel.getPanelData().panelWidth * 0.5f;
        float halfH = panel.getPanelData().panelHeight * 0.5f;
        float yawRad = (float)Math.toRadians(panel.getPanelYaw());
        double normalX = -Math.sin(yawRad);
        double normalZ = Math.cos(yawRad);
        double startX = caster.field_70165_t;
        double startY = caster.field_70163_u + (double)caster.func_70047_e();
        double startZ = caster.field_70161_v;
        double dx = target.field_70165_t - startX;
        double dy = target.field_70163_u + (double)target.field_70131_O * 0.5 - startY;
        double dz = target.field_70161_v - startZ;
        double denom = dx * normalX + dz * normalZ;
        if (Math.abs(denom) < 1.0E-4) {
            return false;
        }
        double relX = panel.field_70165_t - startX;
        double relZ = panel.field_70161_v - startZ;
        double t = (relX * normalX + relZ * normalZ) / denom;
        if (t < 0.0 || t > 1.0) {
            return false;
        }
        double hitX = startX + dx * t;
        double hitY = startY + dy * t;
        double hitZ = startZ + dz * t;
        double cos = Math.cos(yawRad);
        double sin = Math.sin(yawRad);
        double localRight = (hitX - panel.field_70165_t) * cos + (hitZ - panel.field_70161_v) * sin;
        double localUp = hitY - panel.field_70163_u;
        return Math.abs(localRight) <= (double)halfW && Math.abs(localUp) <= (double)halfH;
    }

    @Override
    public int getAllowedByType() {
        return this.allowedBy.ordinal();
    }

    @Override
    public void setAllowedByType(int type) {
        this.allowedBy = UserType.fromOrdinal(type);
    }

    @Override
    public INbt getNbt() {
        return NpcAPI.Instance().getINbt(this.writeNBT(false));
    }

    @Override
    public void setNbt(INbt nbt) {
        this.readNBT(nbt.getMCNBT());
    }

    public AbilityScript getScriptHandler() {
        if (this.id == null || this.id.isEmpty()) {
            return null;
        }
        return AbilityController.Instance.abilityScriptHandlers.get(this.id);
    }

    public void setScriptHandler(AbilityScript handler) {
        if (this.id == null || this.id.isEmpty()) {
            return;
        }
        AbilityController.Instance.abilityScriptHandlers.put(this.id, handler);
    }

    public AbilityScript getOrCreateScriptHandler() {
        if (this.id == null || this.id.isEmpty()) {
            return null;
        }
        AbilityScript handler = this.getScriptHandler();
        if (handler == null) {
            handler = new AbilityScript(this.id);
            AbilityController.Instance.abilityScriptHandlers.put(this.id, handler);
        }
        return handler;
    }

    public AbilityScript getOrCreateInstanceScript() {
        if (this.instanceScript != null) {
            return this.instanceScript;
        }
        AbilityScript template = this.getScriptHandler();
        if (template == null || !template.getEnabled() || template.container == null) {
            return null;
        }
        this.instanceScript = new AbilityScript(this.id);
        this.instanceScript.setLanguage(template.getLanguage());
        this.instanceScript.setEnabled(true);
        IScriptUnit clone = template.container.createInstanceScope(this.instanceScript);
        this.instanceScript.addScriptUnit(clone);
        return this.instanceScript;
    }

    public static class DefaultIconLayer {
        public final String texture;
        public final String[] stateTextures;
        public final IntSupplier colorSource;

        public DefaultIconLayer(String texture, String[] stateTextures, IntSupplier colorSource) {
            this.texture = texture;
            this.stateTextures = stateTextures;
            this.colorSource = colorSource;
        }

        public DefaultIconLayer(String texture, IntSupplier colorSource) {
            this(texture, null, colorSource);
        }

        public DefaultIconLayer(String texture) {
            this(texture, null, null);
        }

        public String getTextureForState(int state) {
            if (state > 0 && this.stateTextures != null && state - 1 < this.stateTextures.length) {
                return this.stateTextures[state - 1];
            }
            return this.texture;
        }

        public int getColor() {
            return this.colorSource != null ? this.colorSource.getAsInt() : 0xFFFFFF;
        }
    }
}

