/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyAnchorData;

public class EnergyAnchorData
implements IEnergyAnchorData {
    public AnchorPoint anchorPoint = AnchorPoint.FRONT;
    public float anchorOffsetX = 0.0f;
    public float anchorOffsetY = 0.0f;
    public float anchorOffsetZ = 0.0f;
    public boolean launchFromAnchor = false;

    public EnergyAnchorData() {
    }

    public EnergyAnchorData(AnchorPoint anchorPoint) {
        this.anchorPoint = anchorPoint;
    }

    public EnergyAnchorData(AnchorPoint anchorPoint, float anchorOffsetX, float anchorOffsetY, float anchorOffsetZ) {
        this.anchorPoint = anchorPoint;
        this.anchorOffsetX = anchorOffsetX;
        this.anchorOffsetY = anchorOffsetY;
        this.anchorOffsetZ = anchorOffsetZ;
    }

    public EnergyAnchorData(int anchorPoint) {
        this.anchorPoint = AnchorPoint.fromOrdinal(anchorPoint);
    }

    public EnergyAnchorData(int anchorPoint, float anchorOffsetX, float anchorOffsetY, float anchorOffsetZ) {
        this.anchorPoint = AnchorPoint.fromOrdinal(anchorPoint);
        this.anchorOffsetX = anchorOffsetX;
        this.anchorOffsetY = anchorOffsetY;
        this.anchorOffsetZ = anchorOffsetZ;
    }

    public AnchorPoint getAnchorPoint() {
        return this.anchorPoint;
    }

    public void setAnchorPoint(AnchorPoint anchorPoint) {
        this.anchorPoint = anchorPoint;
    }

    @Override
    public int getAnchor() {
        return this.anchorPoint.ordinal();
    }

    @Override
    public void setAnchor(int anchor) {
        this.anchorPoint = AnchorPoint.fromOrdinal(anchor);
    }

    @Override
    public float getAnchorOffsetX() {
        return this.anchorOffsetX;
    }

    @Override
    public void setAnchorOffsetX(float anchorOffsetX) {
        this.anchorOffsetX = anchorOffsetX;
    }

    @Override
    public float getAnchorOffsetY() {
        return this.anchorOffsetY;
    }

    @Override
    public void setAnchorOffsetY(float anchorOffsetY) {
        this.anchorOffsetY = anchorOffsetY;
    }

    @Override
    public float getAnchorOffsetZ() {
        return this.anchorOffsetZ;
    }

    @Override
    public void setAnchorOffsetZ(float anchorOffsetZ) {
        this.anchorOffsetZ = anchorOffsetZ;
    }

    @Override
    public boolean getLaunchFromAnchor() {
        return this.launchFromAnchor;
    }

    @Override
    public void setLaunchFromAnchor(boolean launchFromAnchor) {
        this.launchFromAnchor = launchFromAnchor;
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("anchorPoint", this.anchorPoint.ordinal());
        nbt.func_74776_a("anchorOffsetX", this.anchorOffsetX);
        nbt.func_74776_a("anchorOffsetY", this.anchorOffsetY);
        nbt.func_74776_a("anchorOffsetZ", this.anchorOffsetZ);
        nbt.func_74757_a("launchFromAnchor", this.launchFromAnchor);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.anchorPoint = AnchorPoint.fromOrdinal(nbt.func_74762_e("anchorPoint"));
        this.anchorOffsetX = nbt.func_74760_g("anchorOffsetX");
        this.anchorOffsetY = nbt.func_74760_g("anchorOffsetY");
        this.anchorOffsetZ = nbt.func_74760_g("anchorOffsetZ");
        this.launchFromAnchor = nbt.func_74767_n("launchFromAnchor");
    }

    public EnergyAnchorData copy() {
        EnergyAnchorData copy = new EnergyAnchorData(this.anchorPoint, this.anchorOffsetX, this.anchorOffsetY, this.anchorOffsetZ);
        copy.launchFromAnchor = this.launchFromAnchor;
        return copy;
    }
}

