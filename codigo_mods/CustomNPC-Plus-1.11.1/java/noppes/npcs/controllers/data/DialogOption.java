/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.data.Dialog;

public class DialogOption {
    public int dialogId = -1;
    public String title = "Talk";
    public EnumOptionType optionType = EnumOptionType.DialogOption;
    public int optionColor = 0xE0E0E0;
    public String command = "";

    public void readNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        this.title = compound.func_74779_i("Title");
        this.dialogId = compound.func_74762_e("Dialog");
        this.optionColor = compound.func_74762_e("DialogColor");
        this.optionType = EnumOptionType.values()[compound.func_74762_e("OptionType")];
        this.command = compound.func_74779_i("DialogCommand");
        if (this.optionColor == 0) {
            this.optionColor = 0xE0E0E0;
        }
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Title", this.title);
        compound.func_74768_a("OptionType", this.optionType.ordinal());
        compound.func_74768_a("Dialog", this.dialogId);
        compound.func_74768_a("DialogColor", this.optionColor);
        compound.func_74778_a("DialogCommand", this.command);
        return compound;
    }

    public boolean hasDialog() {
        if (this.dialogId <= 0) {
            return false;
        }
        if (!DialogController.Instance.hasDialog(this.dialogId)) {
            this.dialogId = -1;
            return false;
        }
        return true;
    }

    public Dialog getDialog() {
        if (!this.hasDialog()) {
            return null;
        }
        return DialogController.Instance.dialogs.get(this.dialogId);
    }

    public boolean isAvailable(EntityPlayer player) {
        Dialog dialog = this.getDialog();
        if (dialog == null) {
            return false;
        }
        return dialog.availability.isAvailable(player);
    }
}

