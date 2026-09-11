/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.common.painting.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public abstract class AbstractToolOption {
    protected final String optionName;

    public AbstractToolOption(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return this.optionName;
    }

    public String getUnlocalisedlabel() {
        return "tooloption." + "armourersWorkshop".toLowerCase() + ":" + this.getOptionName();
    }

    public String getLocalisedLabel() {
        return StatCollector.func_74838_a((String)this.getUnlocalisedlabel());
    }

    @SideOnly(value=Side.CLIENT)
    public abstract int getDisplayWidth();

    @SideOnly(value=Side.CLIENT)
    public abstract int getDisplayHeight();

    @SideOnly(value=Side.CLIENT)
    public abstract GuiButton getGuiControl(int var1, int var2, int var3, NBTTagCompound var4);

    public abstract Object readFromNBT(NBTTagCompound var1);

    public abstract Object readFromNBT(NBTTagCompound var1, Object var2);

    public abstract void writeToNBT(NBTTagCompound var1, GuiButton var2);

    public abstract void writeToNBT(NBTTagCompound var1, Object var2);
}

