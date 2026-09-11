/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.api.common.skin.cubes;

import net.minecraft.nbt.NBTTagCompound;

public interface ICubeColour {
    public byte getRed(int var1);

    public byte getGreen(int var1);

    public byte getBlue(int var1);

    public byte getPaintType(int var1);

    public byte[] getRed();

    public byte[] getGreen();

    public byte[] getBlue();

    public byte[] getPaintType();

    public void setColour(int var1, int var2);

    @Deprecated
    public void setColour(int var1);

    public void setRed(byte var1, int var2);

    public void setGreen(byte var1, int var2);

    public void setBlue(byte var1, int var2);

    public void setPaintType(byte var1, int var2);

    public void readFromNBT(NBTTagCompound var1);

    public void writeToNBT(NBTTagCompound var1);
}

