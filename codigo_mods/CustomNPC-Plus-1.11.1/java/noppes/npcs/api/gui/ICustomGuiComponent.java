/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.gui;

import net.minecraft.nbt.NBTTagCompound;

public interface ICustomGuiComponent {
    public int getID();

    public ICustomGuiComponent setID(int var1);

    public int getPosX();

    public int getPosY();

    public ICustomGuiComponent setPos(int var1, int var2);

    public boolean hasHoverText();

    public String[] getHoverText();

    public ICustomGuiComponent setHoverText(String var1);

    public ICustomGuiComponent setHoverText(String[] var1);

    public int getColor();

    public ICustomGuiComponent setColor(int var1);

    public float getAlpha();

    public void setAlpha(float var1);

    public float getRotation();

    public void setRotation(float var1);

    public NBTTagCompound toNBT(NBTTagCompound var1);

    public ICustomGuiComponent fromNBT(NBTTagCompound var1);
}

