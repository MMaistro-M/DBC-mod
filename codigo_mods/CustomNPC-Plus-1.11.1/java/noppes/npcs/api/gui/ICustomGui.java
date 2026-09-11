/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.gui;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.IButton;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.gui.ILabel;
import noppes.npcs.api.gui.ILine;
import noppes.npcs.api.gui.IScroll;
import noppes.npcs.api.gui.ITextField;
import noppes.npcs.api.gui.ITexturedRect;
import noppes.npcs.api.item.IItemStack;

public interface ICustomGui {
    public int getID();

    public int getWidth();

    public int getHeight();

    public List<ICustomGuiComponent> getComponents();

    public void clear();

    public List<IItemSlot> getSlots();

    public void setSize(int var1, int var2);

    public void setDoesPauseGame(boolean var1);

    public boolean doesPauseGame();

    public void setBackgroundTexture(String var1);

    public String getBackgroundTexture();

    public IButton addButton(int var1, String var2, int var3, int var4);

    public IButton addButton(int var1, String var2, int var3, int var4, int var5, int var6);

    public IButton addTexturedButton(int var1, String var2, int var3, int var4, int var5, int var6, String var7);

    public IButton addTexturedButton(int var1, String var2, int var3, int var4, int var5, int var6, String var7, int var8, int var9);

    public ILabel addLabel(int var1, String var2, int var3, int var4, int var5, int var6);

    public ILabel addLabel(int var1, String var2, int var3, int var4, int var5, int var6, int var7);

    public ITextField addTextField(int var1, int var2, int var3, int var4, int var5);

    public ITexturedRect addTexturedRect(int var1, String var2, int var3, int var4, int var5, int var6);

    public ITexturedRect addTexturedRect(int var1, String var2, int var3, int var4, int var5, int var6, int var7, int var8);

    public IItemSlot addItemSlot(int var1, int var2, int var3);

    public IItemSlot addItemSlot(int var1, int var2, int var3, IItemStack var4);

    @Deprecated
    public IItemSlot addItemSlot(int var1, int var2);

    @Deprecated
    public IItemSlot addItemSlot(int var1, int var2, IItemStack var3);

    public IScroll addScroll(int var1, int var2, int var3, int var4, int var5, String[] var6);

    public ILine addLine(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public ILine addLine(int var1, int var2, int var3, int var4, int var5);

    public void showPlayerInventory(int var1, int var2);

    public ICustomGuiComponent getComponent(int var1);

    public void removeComponent(int var1);

    public void updateComponent(ICustomGuiComponent var1);

    public void update(IPlayer var1);

    public boolean getShowPlayerInv();

    public int getPlayerInvX();

    public int getPlayerInvY();

    public ICustomGui fromNBT(NBTTagCompound var1);

    public NBTTagCompound toNBT();
}

