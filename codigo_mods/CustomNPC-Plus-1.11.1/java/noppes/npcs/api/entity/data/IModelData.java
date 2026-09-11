/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.entity.data;

import noppes.npcs.api.entity.data.IModelRotate;
import noppes.npcs.api.entity.data.IModelScale;

public interface IModelData {
    public void headWear(byte var1);

    public byte headWear();

    public void bodyWear(byte var1);

    public byte bodyWear();

    public void rightArmWear(byte var1);

    public byte rightArmWear();

    public void leftArmWear(byte var1);

    public byte leftArmWear();

    public void rightLegWear(byte var1);

    public byte rightLegWear();

    public void leftLegWear(byte var1);

    public byte leftLegWear();

    public void hidePart(int var1, byte var2);

    public int hidden(int var1);

    public void enableRotation(boolean var1);

    public boolean enableRotation();

    public IModelRotate getRotation();

    public IModelScale getScale();

    public void setEntity(String var1);

    public String getEntity();
}

