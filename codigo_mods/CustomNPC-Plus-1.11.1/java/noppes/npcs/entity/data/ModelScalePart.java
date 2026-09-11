/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.entity.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.data.IModelScalePart;
import noppes.npcs.util.ValueUtil;

public class ModelScalePart
implements IModelScalePart {
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74776_a("ScaleX", this.scaleX);
        compound.func_74776_a("ScaleY", this.scaleY);
        compound.func_74776_a("ScaleZ", this.scaleZ);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.scaleX = ValueUtil.clamp(compound.func_74760_g("ScaleX"), 0.5f, 1.5f);
        this.scaleY = ValueUtil.clamp(compound.func_74760_g("ScaleY"), 0.5f, 1.5f);
        this.scaleZ = ValueUtil.clamp(compound.func_74760_g("ScaleZ"), 0.5f, 1.5f);
    }

    public String toString() {
        return "ScaleX: " + this.scaleX + " - ScaleY: " + this.scaleY + " - ScaleZ: " + this.scaleZ;
    }

    public void setScale(float x, float y) {
        this.scaleZ = this.scaleX = x;
        this.scaleY = y;
    }

    @Override
    public void setScale(float x, float y, float z) {
        this.scaleX = ValueUtil.clamp(x, 0.5f, 1.5f);
        this.scaleY = ValueUtil.clamp(y, 0.5f, 1.5f);
        this.scaleZ = ValueUtil.clamp(z, 0.5f, 1.5f);
    }

    @Override
    public float getScaleX() {
        return this.scaleX;
    }

    @Override
    public float getScaleY() {
        return this.scaleY;
    }

    @Override
    public float getScaleZ() {
        return this.scaleZ;
    }
}

