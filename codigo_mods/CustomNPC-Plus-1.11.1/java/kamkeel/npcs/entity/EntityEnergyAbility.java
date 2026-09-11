/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.LogWriter;
import noppes.npcs.controllers.data.MagicData;

public abstract class EntityEnergyAbility
extends Entity
implements IEntityAdditionalSpawnData {
    protected static final int CHARGE_TIMEOUT_GRACE = 60;
    protected static final int PREVIEW_CHARGE_GRACE = 3;
    protected static final int HARD_LIFETIME_CAP = 1200;
    protected static final int BARRIER_HARD_LIFETIME_CAP = 12000;
    protected static final float MAX_ENTITY_SIZE = 100.0f;
    protected static final float MAX_ENTITY_RADIUS = 50.0f;
    protected EnergyDisplayData displayData = new EnergyDisplayData();
    protected EnergyLightningData lightningData = new EnergyLightningData();
    @SideOnly(value=Side.CLIENT)
    public transient Object lightningState;
    protected int ownerEntityId = -1;
    protected transient Ability sourceAbility = null;
    protected NBTTagCompound customDamageData = null;
    protected MagicData magicData = new MagicData();
    protected boolean ignoreIFrames = false;
    protected boolean previewMode = false;
    protected EntityLivingBase previewOwner = null;
    protected boolean charging = false;
    protected int chargeDuration = 0;
    protected int chargeTick = 0;
    protected static final int DW_CHARGING = 20;

    public EntityEnergyAbility(World world) {
        super(world);
        this.field_70178_ae = true;
        this.field_70158_ak = true;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(20, (Object)0);
    }

    public boolean isCharging() {
        if (this.previewMode) {
            return this.charging;
        }
        return this.field_70180_af.func_75683_a(20) == 1;
    }

    public void setCharging(boolean value) {
        this.charging = value;
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(20, (Object)((byte)(value ? 1 : 0)));
        }
    }

    public void setChargeDuration(int duration) {
        this.chargeDuration = duration;
    }

    public void resetChargeTick() {
        this.chargeTick = this.chargeDuration;
    }

    public float getChargeProgress() {
        if (this.chargeDuration <= 0) {
            return 1.0f;
        }
        return Math.min(1.0f, (float)this.chargeTick / (float)this.chargeDuration);
    }

    public float getInterpolatedChargeProgress(float partialTicks) {
        if (this.chargeDuration <= 0) {
            return 1.0f;
        }
        float prevProgress = Math.min(1.0f, Math.max(0.0f, (float)(this.chargeTick - 1) / (float)this.chargeDuration));
        float currProgress = Math.min(1.0f, (float)this.chargeTick / (float)this.chargeDuration);
        return prevProgress + (currProgress - prevProgress) * partialTicks;
    }

    public Entity getOwnerEntity() {
        if (this.previewMode && this.previewOwner != null) {
            return this.previewOwner;
        }
        if (this.ownerEntityId == -1) {
            return null;
        }
        return this.field_70170_p.func_73045_a(this.ownerEntityId);
    }

    public int getOwnerEntityId() {
        return this.ownerEntityId;
    }

    public void setOwnerEntityId(int id) {
        this.ownerEntityId = id;
    }

    public Ability getSourceAbility() {
        return this.sourceAbility;
    }

    public void setSourceAbility(Ability ability) {
        this.sourceAbility = ability;
    }

    public NBTTagCompound getCustomDamageData() {
        return this.customDamageData;
    }

    public void setCustomDamageData(NBTTagCompound data) {
        this.customDamageData = data;
    }

    public MagicData getMagicData() {
        return this.magicData;
    }

    public void setMagicData(MagicData data) {
        this.magicData = data != null ? data : new MagicData();
    }

    public boolean isIgnoreIFrames() {
        return this.ignoreIFrames;
    }

    public void setIgnoreIFrames(boolean ignoreIFrames) {
        this.ignoreIFrames = ignoreIFrames;
    }

    public boolean isPreviewMode() {
        return this.previewMode;
    }

    public void setPreviewMode(boolean preview) {
        this.previewMode = preview;
    }

    public void setPreviewOwner(EntityLivingBase owner) {
        this.previewOwner = owner;
    }

    public EnergyDisplayData getDisplayData() {
        return this.displayData;
    }

    public EnergyLightningData getLightningData() {
        return this.lightningData;
    }

    public int getInnerColor() {
        return this.displayData.getInnerColor();
    }

    public void setInnerColor(int color) {
        this.displayData.setInnerColor(color);
    }

    public float getInnerAlpha() {
        return this.displayData.getInnerAlpha();
    }

    public void setInnerAlpha(float alpha) {
        this.displayData.setInnerAlpha(alpha);
    }

    public int getOuterColor() {
        return this.displayData.getOuterColor();
    }

    public void setOuterColor(int color) {
        this.displayData.setOuterColor(color);
    }

    public boolean isOuterColorEnabled() {
        return this.displayData.isOuterColorEnabled();
    }

    public void setOuterColorEnabled(boolean enabled) {
        this.displayData.setOuterColorEnabled(enabled);
    }

    public float getOuterColorWidth() {
        return this.displayData.getOuterColorWidth();
    }

    public void setOuterColorWidth(float width) {
        this.displayData.setOuterColorWidth(width);
    }

    public float getOuterColorAlpha() {
        return this.displayData.getOuterColorAlpha();
    }

    public void setOuterColorAlpha(float alpha) {
        this.displayData.setOuterColorAlpha(alpha);
    }

    public float getRotationSpeed() {
        return this.displayData.getRotationSpeed();
    }

    public void setRotationSpeed(float speed) {
        this.displayData.setRotationSpeed(speed);
    }

    public boolean hasLightningEffect() {
        return this.lightningData.isLightningEffect();
    }

    public void setLightningEffect(boolean enabled) {
        this.lightningData.setLightningEffect(enabled);
    }

    public float getLightningDensity() {
        return this.lightningData.getLightningDensity();
    }

    public void setLightningDensity(float density) {
        this.lightningData.setLightningDensity(density);
    }

    public float getLightningRadius() {
        return this.lightningData.getLightningRadius();
    }

    public void setLightningRadius(float radius) {
        this.lightningData.setLightningRadius(radius);
    }

    public int getLightningFadeTime() {
        return this.lightningData.getLightningFadeTime();
    }

    public void setLightningFadeTime(int ticks) {
        this.lightningData.setLightningFadeTime(ticks);
    }

    public float func_70013_c(float partialTicks) {
        return 1.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float partialTicks) {
        return 0xF000F0;
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    public boolean func_70104_M() {
        return false;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public boolean func_70027_ad() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    protected static float sanitize(float value, float fallback, float max) {
        if (Float.isNaN(value) || Float.isInfinite(value) || value < 0.0f) {
            return fallback;
        }
        return Math.min(value, max);
    }

    protected void writeEnergyBaseNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("OwnerId", this.ownerEntityId);
        nbt.func_74757_a("IgnoreIFrames", this.ignoreIFrames);
        this.displayData.writeNBT(nbt);
        this.lightningData.writeNBT(nbt);
        if (this.customDamageData != null) {
            nbt.func_74782_a("CustomDamageData", (NBTBase)this.customDamageData);
        }
        this.magicData.writeToNBT(nbt);
    }

    protected void readEnergyBaseNBT(NBTTagCompound nbt) {
        this.ownerEntityId = nbt.func_74762_e("OwnerId");
        this.ignoreIFrames = nbt.func_74767_n("IgnoreIFrames");
        this.displayData.readNBT(nbt);
        this.lightningData.readNBT(nbt);
        if (nbt.func_74764_b("CustomDamageData")) {
            this.customDamageData = nbt.func_74775_l("CustomDamageData");
        }
        this.magicData.readToNBT(nbt);
    }

    protected void func_70037_a(NBTTagCompound nbt) {
        this.func_70106_y();
    }

    protected void func_70014_b(NBTTagCompound nbt) {
    }

    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeEnergyBaseNBT(nbt);
    }

    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readEnergyBaseNBT(nbt);
    }

    public final NBTTagCompound exportSpawnNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeSpawnNBT(nbt);
        return nbt;
    }

    public final void importSpawnNBT(NBTTagCompound nbt) {
        if (nbt != null) {
            this.readSpawnNBT(nbt);
        }
    }

    public void writeSpawnData(ByteBuf buffer) {
        try {
            NBTTagCompound compound = new NBTTagCompound();
            this.writeSpawnNBT(compound);
            ByteBufUtils.writeNBT(buffer, compound);
        }
        catch (Exception e) {
            LogWriter.error("Error writing energy ability spawn data", e);
        }
    }

    public void readSpawnData(ByteBuf buffer) {
        try {
            NBTTagCompound compound = ByteBufUtils.readNBT(buffer);
            if (compound != null) {
                this.readSpawnNBT(compound);
            }
        }
        catch (Exception e) {
            LogWriter.error("Error reading energy ability spawn data", e);
        }
    }
}

