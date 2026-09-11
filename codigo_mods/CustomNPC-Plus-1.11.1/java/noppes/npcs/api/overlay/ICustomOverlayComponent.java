/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.overlay;

import net.minecraft.nbt.NBTTagCompound;

public interface ICustomOverlayComponent {
    public int getID();

    public ICustomOverlayComponent setID(int var1);

    public int getPosX();

    public int getPosY();

    public ICustomOverlayComponent setPos(int var1, int var2);

    public int getAlignment();

    public void setAlignment(int var1);

    public int getColor();

    public ICustomOverlayComponent setColor(int var1);

    public float getAlpha();

    public void setAlpha(float var1);

    public float getRotation();

    public void setRotation(float var1);

    public NBTTagCompound toNBT(NBTTagCompound var1);

    public ICustomOverlayComponent fromNBT(NBTTagCompound var1);
}

