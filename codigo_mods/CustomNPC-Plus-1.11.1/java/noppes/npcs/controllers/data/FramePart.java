/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IFramePart;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;

public class FramePart
implements IFramePart {
    public Animation parent;
    public EnumAnimationPart part;
    public float[] rotation = new float[]{0.0f, 0.0f, 0.0f};
    public float[] pivot = new float[]{0.0f, 0.0f, 0.0f};
    boolean customized = false;
    public float speed = 1.0f;
    public byte smooth = 0;
    public float[] prevRotations = new float[]{0.0f, 0.0f, 0.0f};
    public float[] prevPivots = new float[]{0.0f, 0.0f, 0.0f};
    public float partialRotationTick;
    public float partialPivotTick;

    public FramePart() {
    }

    public FramePart(EnumAnimationPart part) {
        this.part = part;
    }

    public EnumAnimationPart getPart() {
        return this.part;
    }

    @Override
    public String getName() {
        return this.part.name();
    }

    @Override
    public int getPartId() {
        return this.part.ordinal();
    }

    public void setPart(EnumAnimationPart part) {
        this.part = part;
    }

    @Override
    public IFramePart setPart(String name) {
        try {
            this.setPart(EnumAnimationPart.valueOf(name));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return this;
    }

    @Override
    public IFramePart setPart(int partId) {
        for (EnumAnimationPart enumPart : EnumAnimationPart.values()) {
            if (enumPart.ordinal() != partId) continue;
            this.setPart(enumPart);
            break;
        }
        return this;
    }

    @Override
    public float[] getRotations() {
        return this.rotation;
    }

    @Override
    public IFramePart setRotations(float[] rotation) {
        this.rotation = rotation;
        return this;
    }

    @Override
    public float[] getPivots() {
        return this.pivot;
    }

    @Override
    public IFramePart setPivots(float[] pivot) {
        this.pivot = pivot;
        return this;
    }

    @Override
    public boolean isCustomized() {
        return this.customized;
    }

    @Override
    public IFramePart setCustomized(boolean customized) {
        this.customized = customized;
        return this;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public IFramePart setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public byte isSmooth() {
        return this.smooth;
    }

    @Override
    public IFramePart setSmooth(byte smooth) {
        this.smooth = smooth;
        return this;
    }

    public void readFromNBT(NBTTagCompound compound) {
        int i;
        this.part = EnumAnimationPart.valueOf(compound.func_74779_i("Part"));
        for (i = 0; i < 3; ++i) {
            this.rotation[i] = compound.func_74760_g("Rotation" + i);
        }
        for (i = 0; i < 3; ++i) {
            this.pivot[i] = compound.func_74760_g("Pivot" + i);
        }
        if (compound.func_74764_b("Speed")) {
            this.customized = true;
            this.speed = compound.func_74760_g("Speed");
        }
        if (compound.func_74764_b("Smooth")) {
            this.customized = true;
            this.smooth = compound.func_74771_c("Smooth");
        }
    }

    public NBTTagCompound writeToNBT() {
        int i;
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Part", this.part.toString());
        for (i = 0; i < 3; ++i) {
            compound.func_74776_a("Rotation" + i, this.rotation[i]);
        }
        for (i = 0; i < 3; ++i) {
            compound.func_74776_a("Pivot" + i, this.pivot[i]);
        }
        if (this.customized) {
            compound.func_74776_a("Speed", this.speed);
            compound.func_74774_a("Smooth", this.smooth);
        }
        return compound;
    }

    public FramePart copy() {
        FramePart part = new FramePart(this.part);
        part.rotation = new float[]{this.rotation[0], this.rotation[1], this.rotation[2]};
        part.pivot = new float[]{this.pivot[0], this.pivot[1], this.pivot[2]};
        part.customized = this.customized;
        part.speed = this.speed;
        part.smooth = this.smooth;
        return part;
    }

    @SideOnly(value=Side.CLIENT)
    public void interpolateAngles() {
        if (this.parent != null && this.parent.paused) {
            return;
        }
        float pi = (float)Math.PI / 180;
        if (this.smooth == 2) {
            this.prevRotations[0] = this.rotation[0] * pi;
            this.prevRotations[1] = this.rotation[1] * pi;
            this.prevRotations[2] = this.rotation[2] * pi;
        } else if (this.partialRotationTick != ClientEventHandler.partialRenderTick) {
            this.partialRotationTick = ClientEventHandler.partialRenderTick;
            if (this.smooth == 0) {
                this.prevRotations[0] = (this.rotation[0] * pi - this.prevRotations[0]) * Math.abs(this.speed) / 10.0f + this.prevRotations[0];
                this.prevRotations[1] = (this.rotation[1] * pi - this.prevRotations[1]) * Math.abs(this.speed) / 10.0f + this.prevRotations[1];
                this.prevRotations[2] = (this.rotation[2] * pi - this.prevRotations[2]) * Math.abs(this.speed) / 10.0f + this.prevRotations[2];
            } else {
                int directionX = Float.compare(this.rotation[0] * pi, this.prevRotations[0]);
                this.prevRotations[0] = this.prevRotations[0] + (float)directionX * this.speed / 10.0f;
                this.prevRotations[0] = directionX == 1 ? Math.min(this.rotation[0] * pi, this.prevRotations[0]) : Math.max(this.rotation[0] * pi, this.prevRotations[0]);
                int directionY = Float.compare(this.rotation[1] * pi, this.prevRotations[1]);
                this.prevRotations[1] = this.prevRotations[1] + (float)directionY * this.speed / 10.0f;
                this.prevRotations[1] = directionY == 1 ? Math.min(this.rotation[1] * pi, this.prevRotations[1]) : Math.max(this.rotation[1] * pi, this.prevRotations[1]);
                int directionZ = Float.compare(this.rotation[2] * pi, this.prevRotations[2]);
                this.prevRotations[2] = this.prevRotations[2] + (float)directionZ * this.speed / 10.0f;
                this.prevRotations[2] = directionZ == 1 ? Math.min(this.rotation[2] * pi, this.prevRotations[2]) : Math.max(this.rotation[2] * pi, this.prevRotations[2]);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void interpolateOffset() {
        if (this.parent != null && this.parent.paused) {
            return;
        }
        if (this.smooth == 2) {
            this.prevPivots[0] = this.pivot[0];
            this.prevPivots[1] = this.pivot[1];
            this.prevPivots[2] = this.pivot[2];
        } else if (this.partialPivotTick != ClientEventHandler.partialRenderTick) {
            this.partialPivotTick = ClientEventHandler.partialRenderTick;
            if (this.smooth == 0) {
                this.prevPivots[0] = (this.pivot[0] - this.prevPivots[0]) * Math.abs(this.speed) / 10.0f + this.prevPivots[0];
                this.prevPivots[1] = (this.pivot[1] - this.prevPivots[1]) * Math.abs(this.speed) / 10.0f + this.prevPivots[1];
                this.prevPivots[2] = (this.pivot[2] - this.prevPivots[2]) * Math.abs(this.speed) / 10.0f + this.prevPivots[2];
            } else {
                int directionX = Float.compare(this.pivot[0], this.prevPivots[0]);
                this.prevPivots[0] = this.prevPivots[0] + (float)directionX * this.speed / 10.0f;
                this.prevPivots[0] = directionX == 1 ? Math.min(this.pivot[0], this.prevPivots[0]) : Math.max(this.pivot[0], this.prevPivots[0]);
                int directionY = Float.compare(this.pivot[1], this.prevPivots[1]);
                this.prevPivots[1] = this.prevPivots[1] + (float)directionY * this.speed / 10.0f;
                this.prevPivots[1] = directionY == 1 ? Math.min(this.pivot[1], this.prevPivots[1]) : Math.max(this.pivot[1], this.prevPivots[1]);
                int directionZ = Float.compare(this.pivot[2], this.prevPivots[2]);
                this.prevPivots[2] = this.prevPivots[2] + (float)directionZ * this.speed / 10.0f;
                this.prevPivots[2] = directionZ == 1 ? Math.min(this.pivot[2], this.prevPivots[2]) : Math.max(this.pivot[2], this.prevPivots[2]);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void jumpToCurrentFrame() {
        this.partialRotationTick = ClientEventHandler.partialRenderTick;
        this.partialPivotTick = ClientEventHandler.partialRenderTick;
        this.prevPivots[0] = this.pivot[0];
        this.prevPivots[1] = this.pivot[1];
        this.prevPivots[2] = this.pivot[2];
        float pi = (float)Math.PI / 180;
        this.prevRotations[0] = this.rotation[0] * pi;
        this.prevRotations[1] = this.rotation[1] * pi;
        this.prevRotations[2] = this.rotation[2] * pi;
    }
}

