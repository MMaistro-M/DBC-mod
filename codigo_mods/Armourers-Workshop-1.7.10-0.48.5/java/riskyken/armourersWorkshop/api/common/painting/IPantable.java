/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.painting;

import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.painting.PaintType;

public interface IPantable {
    @Deprecated
    public void setColour(int var1);

    @Deprecated
    public void setColour(int var1, int var2);

    public void setColour(byte[] var1, int var2);

    public void setColour(ICubeColour var1);

    public int getColour(int var1);

    public void setPaintType(PaintType var1, int var2);

    public PaintType getPaintType(int var1);

    public ICubeColour getColour();
}

