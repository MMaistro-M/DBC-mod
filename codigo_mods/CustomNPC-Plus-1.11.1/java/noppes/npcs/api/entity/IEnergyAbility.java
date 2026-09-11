/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.INbt;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.data.IMagicData;

public interface IEnergyAbility<T extends Entity>
extends IEntity<T> {
    public int getOwnerEntityId();

    public IEntity getOwner();

    public int getInnerColor();

    public void setInnerColor(int var1);

    public float getInnerAlpha();

    public void setInnerAlpha(float var1);

    public int getOuterColor();

    public void setOuterColor(int var1);

    public boolean isOuterColorEnabled();

    public void setOuterColorEnabled(boolean var1);

    public float getOuterColorWidth();

    public void setOuterColorWidth(float var1);

    public float getOuterColorAlpha();

    public void setOuterColorAlpha(float var1);

    public boolean hasLightningEffect();

    public void setLightningEffect(boolean var1);

    public float getLightningDensity();

    public void setLightningDensity(float var1);

    public float getLightningRadius();

    public void setLightningRadius(float var1);

    public int getLightningFadeTime();

    public void setLightningFadeTime(int var1);

    public boolean isCharging();

    public float getChargeProgress();

    public boolean isIgnoreIFrames();

    public void setIgnoreIFrames(boolean var1);

    public INbt getDamageData();

    public void setDamageData(INbt var1);

    public IMagicData getMagicData();

    public void setMagicData(IMagicData var1);
}

