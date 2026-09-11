/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.api.common.skin.data;

import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;

public interface ISkinPart {
    public ISkinPartType getPartType();

    public int getMarkerCount();

    public Point3D getMarker(int var1);

    public ForgeDirection getMarkerSide(int var1);
}

