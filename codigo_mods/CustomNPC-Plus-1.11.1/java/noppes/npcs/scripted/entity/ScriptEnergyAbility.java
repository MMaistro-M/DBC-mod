/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergyAbility;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.INbt;
import noppes.npcs.api.entity.IEnergyAbility;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.data.IMagicData;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public abstract class ScriptEnergyAbility<T extends EntityEnergyAbility>
extends ScriptEntity<T>
implements IEnergyAbility {
    public ScriptEnergyAbility(T entity) {
        super(entity);
    }

    @Override
    public int getOwnerEntityId() {
        return ((EntityEnergyAbility)this.entity).getOwnerEntityId();
    }

    @Override
    public IEntity getOwner() {
        Entity owner = ((EntityEnergyAbility)this.entity).getOwnerEntity();
        return owner != null ? NpcAPI.Instance().getIEntity(owner) : null;
    }

    @Override
    public int getInnerColor() {
        return ((EntityEnergyAbility)this.entity).getInnerColor();
    }

    @Override
    public void setInnerColor(int color) {
        ((EntityEnergyAbility)this.entity).setInnerColor(color);
    }

    @Override
    public float getInnerAlpha() {
        return ((EntityEnergyAbility)this.entity).getInnerAlpha();
    }

    @Override
    public void setInnerAlpha(float alpha) {
        ((EntityEnergyAbility)this.entity).setInnerAlpha(alpha);
    }

    @Override
    public int getOuterColor() {
        return ((EntityEnergyAbility)this.entity).getOuterColor();
    }

    @Override
    public void setOuterColor(int color) {
        ((EntityEnergyAbility)this.entity).setOuterColor(color);
    }

    @Override
    public boolean isOuterColorEnabled() {
        return ((EntityEnergyAbility)this.entity).isOuterColorEnabled();
    }

    @Override
    public void setOuterColorEnabled(boolean enabled) {
        ((EntityEnergyAbility)this.entity).setOuterColorEnabled(enabled);
    }

    @Override
    public float getOuterColorWidth() {
        return ((EntityEnergyAbility)this.entity).getOuterColorWidth();
    }

    @Override
    public void setOuterColorWidth(float width) {
        ((EntityEnergyAbility)this.entity).setOuterColorWidth(width);
    }

    @Override
    public float getOuterColorAlpha() {
        return ((EntityEnergyAbility)this.entity).getOuterColorAlpha();
    }

    @Override
    public void setOuterColorAlpha(float alpha) {
        ((EntityEnergyAbility)this.entity).setOuterColorAlpha(alpha);
    }

    @Override
    public boolean hasLightningEffect() {
        return ((EntityEnergyAbility)this.entity).hasLightningEffect();
    }

    @Override
    public void setLightningEffect(boolean enabled) {
        ((EntityEnergyAbility)this.entity).setLightningEffect(enabled);
    }

    @Override
    public float getLightningDensity() {
        return ((EntityEnergyAbility)this.entity).getLightningDensity();
    }

    @Override
    public void setLightningDensity(float density) {
        ((EntityEnergyAbility)this.entity).setLightningDensity(density);
    }

    @Override
    public float getLightningRadius() {
        return ((EntityEnergyAbility)this.entity).getLightningRadius();
    }

    @Override
    public void setLightningRadius(float radius) {
        ((EntityEnergyAbility)this.entity).setLightningRadius(radius);
    }

    @Override
    public int getLightningFadeTime() {
        return ((EntityEnergyAbility)this.entity).getLightningFadeTime();
    }

    @Override
    public void setLightningFadeTime(int ticks) {
        ((EntityEnergyAbility)this.entity).setLightningFadeTime(ticks);
    }

    @Override
    public boolean isCharging() {
        return ((EntityEnergyAbility)this.entity).isCharging();
    }

    @Override
    public float getChargeProgress() {
        return ((EntityEnergyAbility)this.entity).getChargeProgress();
    }

    @Override
    public boolean isIgnoreIFrames() {
        return ((EntityEnergyAbility)this.entity).isIgnoreIFrames();
    }

    @Override
    public void setIgnoreIFrames(boolean ignore) {
        ((EntityEnergyAbility)this.entity).setIgnoreIFrames(ignore);
    }

    @Override
    public INbt getDamageData() {
        NBTTagCompound data = ((EntityEnergyAbility)this.entity).getCustomDamageData();
        if (data == null) {
            data = new NBTTagCompound();
            ((EntityEnergyAbility)this.entity).setCustomDamageData(data);
        }
        return NpcAPI.Instance().getINbt(data);
    }

    @Override
    public void setDamageData(INbt data) {
        if (data == null) {
            ((EntityEnergyAbility)this.entity).setCustomDamageData(null);
        } else {
            ((EntityEnergyAbility)this.entity).setCustomDamageData(data.getMCNBT());
        }
    }

    @Override
    public IMagicData getMagicData() {
        return ((EntityEnergyAbility)this.entity).getMagicData();
    }

    @Override
    public void setMagicData(IMagicData data) {
        if (data instanceof MagicData) {
            ((EntityEnergyAbility)this.entity).setMagicData((MagicData)data);
        }
    }
}

