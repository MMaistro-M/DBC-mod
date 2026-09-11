/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.overlay;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.api.overlay.IOverlayLabel;
import noppes.npcs.api.overlay.IOverlayLine;
import noppes.npcs.api.overlay.IOverlayTexturedRect;

public interface ICustomOverlay {
    public int getID();

    public List<ICustomOverlayComponent> getComponents();

    public int getDefaultAlignment();

    public void setDefaultAlignment(int var1);

    public IOverlayTexturedRect addTexturedRect(int var1, String var2, int var3, int var4, int var5, int var6);

    public IOverlayTexturedRect addTexturedRect(int var1, String var2, int var3, int var4, int var5, int var6, int var7, int var8);

    public IOverlayLabel addLabel(int var1, String var2, int var3, int var4, int var5, int var6);

    public IOverlayLabel addLabel(int var1, String var2, int var3, int var4, int var5, int var6, int var7);

    public IOverlayLine addLine(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public IOverlayLine addLine(int var1, int var2, int var3, int var4, int var5);

    public ICustomOverlayComponent getComponent(int var1);

    public void removeComponent(int var1);

    public void updateComponent(ICustomOverlayComponent var1);

    public void update(IPlayer var1);

    public ICustomOverlay fromNBT(NBTTagCompound var1);

    public NBTTagCompound toNBT();
}

