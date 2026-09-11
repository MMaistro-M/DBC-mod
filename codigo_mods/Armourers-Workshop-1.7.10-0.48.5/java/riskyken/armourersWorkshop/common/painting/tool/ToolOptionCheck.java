/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.painting.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;

public class ToolOptionCheck
extends AbstractToolOption {
    private final boolean defaultCheck;

    public ToolOptionCheck(String optionName) {
        this(optionName, true);
    }

    public ToolOptionCheck(String optionName, boolean defaultCheck) {
        super(optionName);
        this.defaultCheck = defaultCheck;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getDisplayWidth() {
        return 180;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getDisplayHeight() {
        return 9;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public GuiButton getGuiControl(int id, int x, int y, NBTTagCompound compound) {
        return new GuiCheckBox(id, x, y, this.getLocalisedLabel(), (Boolean)this.readFromNBT(compound));
    }

    public boolean readFromNBTBool(NBTTagCompound compound) {
        boolean checked = this.defaultCheck;
        if (compound != null && compound.func_74764_b(this.optionName)) {
            checked = compound.func_74767_n(this.optionName);
        }
        return checked;
    }

    @Override
    public Object readFromNBT(NBTTagCompound compound) {
        return this.readFromNBT(compound, this.defaultCheck);
    }

    @Override
    public Object readFromNBT(NBTTagCompound compound, Object value) {
        boolean checked = (Boolean)value;
        if (compound != null && compound.func_74764_b(this.optionName)) {
            checked = compound.func_74767_n(this.optionName);
        }
        return checked;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound, GuiButton control) {
        GuiCheckBox checkControl = (GuiCheckBox)control;
        this.writeToNBT(compound, checkControl.isChecked());
    }

    @Override
    public void writeToNBT(NBTTagCompound compound, Object value) {
        compound.func_74757_a(this.optionName, ((Boolean)value).booleanValue());
    }
}

