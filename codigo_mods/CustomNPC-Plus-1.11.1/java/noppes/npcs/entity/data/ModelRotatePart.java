/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.entity.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.data.IModelRotatePart;
import noppes.npcs.util.ValueUtil;

public class ModelRotatePart
implements IModelRotatePart {
    public float rotationX = 0.0f;
    public float rotationY = 0.0f;
    public float rotationZ = 0.0f;
    public boolean disabled = false;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("Disabled", this.disabled);
        compound.func_74776_a("RotationX", this.rotationX);
        compound.func_74776_a("RotationY", this.rotationY);
        compound.func_74776_a("RotationZ", this.rotationZ);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.disabled = compound.func_74767_n("Disabled");
        this.rotationX = ValueUtil.clamp(compound.func_74760_g("RotationX"), -0.5f, 0.5f);
        this.rotationY = ValueUtil.clamp(compound.func_74760_g("RotationY"), -0.5f, 0.5f);
        this.rotationZ = ValueUtil.clamp(compound.func_74760_g("RotationZ"), -0.5f, 0.5f);
    }

    @Override
    public void setRotation(float x, float y, float z) {
        this.rotationX = ValueUtil.clamp(x, -0.5f, 0.5f);
        this.rotationY = ValueUtil.clamp(y, -0.5f, 0.5f);
        this.rotationZ = ValueUtil.clamp(z, -0.5f, 0.5f);
    }

    @Override
    public float getRotateX() {
        return this.rotationX;
    }

    @Override
    public float getRotateY() {
        return this.rotationY;
    }

    @Override
    public float getRotateZ() {
        return this.rotationZ;
    }

    @Override
    public void disabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean disabled() {
        return this.disabled;
    }
}

