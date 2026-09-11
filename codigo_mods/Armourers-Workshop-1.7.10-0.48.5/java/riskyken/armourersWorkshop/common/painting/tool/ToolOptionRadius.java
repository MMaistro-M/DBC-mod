/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.painting.tool;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;

public class ToolOptionRadius
extends AbstractToolOption {
    public ToolOptionRadius(String optionName) {
        super(optionName);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getDisplayWidth() {
        return 150;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getDisplayHeight() {
        return 20;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public GuiButton getGuiControl(int id, int x, int y, NBTTagCompound compound) {
        GuiSlider sliderControl = new GuiSlider(id, x, y, this.getLocalisedLabel() + " ", 1.0, 6.0, (double)((Integer)this.readFromNBT(compound)).intValue(), null);
        sliderControl.showDecimal = false;
        return sliderControl;
    }

    @Override
    public Object readFromNBT(NBTTagCompound compound) {
        return this.readFromNBT(compound, 2);
    }

    @Override
    public Object readFromNBT(NBTTagCompound compound, Object value) {
        int intensityValue = (Integer)value;
        if (compound != null && compound.func_74764_b(this.optionName)) {
            intensityValue = compound.func_74762_e(this.optionName);
        }
        return intensityValue;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound, GuiButton control) {
        GuiSlider sliderControl = (GuiSlider)control;
        this.writeToNBT(compound, sliderControl.getValueInt());
    }

    @Override
    public void writeToNBT(NBTTagCompound compound, Object value) {
        compound.func_74768_a(this.optionName, ((Integer)value).intValue());
    }
}

