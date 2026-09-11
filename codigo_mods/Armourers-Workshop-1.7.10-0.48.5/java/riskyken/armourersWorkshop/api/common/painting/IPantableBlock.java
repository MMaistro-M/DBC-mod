/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.IBlockAccess
 */
package riskyken.armourersWorkshop.api.common.painting;

import net.minecraft.world.IBlockAccess;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.painting.PaintType;

public interface IPantableBlock {
    @Deprecated
    public boolean setColour(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6);

    public boolean setColour(IBlockAccess var1, int var2, int var3, int var4, byte[] var5, int var6);

    public int getColour(IBlockAccess var1, int var2, int var3, int var4, int var5);

    public void setPaintType(IBlockAccess var1, int var2, int var3, int var4, PaintType var5, int var6);

    public PaintType getPaintType(IBlockAccess var1, int var2, int var3, int var4, int var5);

    public ICubeColour getColour(IBlockAccess var1, int var2, int var3, int var4);

    public boolean isRemoteOnly(IBlockAccess var1, int var2, int var3, int var4, int var5);
}

