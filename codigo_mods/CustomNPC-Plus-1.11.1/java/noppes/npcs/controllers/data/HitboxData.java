/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.data.IHitboxData;
import noppes.npcs.config.ConfigMain;

public class HitboxData
implements IHitboxData {
    private float widthScale = 1.0f;
    private float heightScale = 1.0f;
    private boolean hitboxEnabled = false;

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("HitboxEnabled", this.hitboxEnabled);
        if (this.hitboxEnabled) {
            if (this.widthScale > (float)ConfigMain.HitBoxScaleMax) {
                this.widthScale = ConfigMain.HitBoxScaleMax;
            }
            nbttagcompound.func_74776_a("HitboxWidthScale", this.widthScale);
            if (this.heightScale > (float)ConfigMain.HitBoxScaleMax) {
                this.heightScale = ConfigMain.HitBoxScaleMax;
            }
            nbttagcompound.func_74776_a("HitboxHeightScale", this.heightScale);
        }
        return nbttagcompound;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.hitboxEnabled = nbttagcompound.func_74767_n("HitboxEnabled");
        if (this.hitboxEnabled) {
            this.widthScale = nbttagcompound.func_74760_g("HitboxWidthScale");
            if (this.widthScale > (float)ConfigMain.HitBoxScaleMax) {
                this.widthScale = ConfigMain.HitBoxScaleMax;
            }
            this.heightScale = nbttagcompound.func_74760_g("HitboxHeightScale");
            if (this.heightScale > (float)ConfigMain.HitBoxScaleMax) {
                this.heightScale = ConfigMain.HitBoxScaleMax;
            }
        }
    }

    @Override
    public float getWidthScale() {
        return this.widthScale;
    }

    @Override
    public void setWidthScale(float widthScale) {
        this.widthScale = widthScale;
    }

    @Override
    public float getHeightScale() {
        return this.heightScale;
    }

    @Override
    public void setHeightScale(float heightScale) {
        this.heightScale = heightScale;
    }

    @Override
    public boolean isHitboxEnabled() {
        return this.hitboxEnabled;
    }

    @Override
    public void setHitboxEnabled(boolean hitboxEnabled) {
        this.hitboxEnabled = hitboxEnabled;
    }
}

