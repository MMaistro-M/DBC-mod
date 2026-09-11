/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.custom.interfaces;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.custom.interfaces.IGuiComponent;

public interface IDataHolder
extends IGuiComponent {
    public NBTTagCompound toNBT();
}

