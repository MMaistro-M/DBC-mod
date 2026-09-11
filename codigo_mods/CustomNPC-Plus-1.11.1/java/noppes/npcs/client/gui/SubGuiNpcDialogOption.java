/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.packets.request.dialog.DialogGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.select.GuiDialogSelection;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiSelectionListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogOption;

public class SubGuiNpcDialogOption
extends SubGuiInterface
implements IGuiData,
ITextfieldListener,
GuiSelectionListener,
ISubGuiListener {
    private DialogOption option;
    public static int LastColor = 0xE0E0E0;

    public SubGuiNpcDialogOption(DialogOption option) {
        this.option = option;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(66, "dialog.editoption", this.guiLeft, this.guiTop + 4));
        this.getLabel(66).center(this.xSize);
        this.addLabel(new GuiNpcLabel(0, "gui.title", this.guiLeft + 4, this.guiTop + 20));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 40, this.guiTop + 15, 196, 20, this.option.title));
        String color = Integer.toHexString(this.option.optionColor);
        while (color.length() < 6) {
            color = 0 + color;
        }
        this.addLabel(new GuiNpcLabel(2, "gui.color", this.guiLeft + 4, this.guiTop + 45));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 62, this.guiTop + 40, 92, 20, color));
        this.getButton(2).setTextColor(this.option.optionColor);
        this.addLabel(new GuiNpcLabel(1, "dialog.optiontype", this.guiLeft + 4, this.guiTop + 67));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 62, this.guiTop + 62, 92, 20, new String[]{"gui.close", "dialog.dialog", "gui.disabled", "menu.role", "tile.commandBlock.name"}, this.option.optionType.ordinal()));
        if (this.option.optionType == EnumOptionType.DialogOption) {
            this.addButton(new GuiNpcButton(3, this.guiLeft + 4, this.guiTop + 84, "availability.selectdialog"));
            if (this.option.dialogId >= 0) {
                DialogGetPacket.getDialog(this.option.dialogId);
            }
        }
        if (this.option.optionType == EnumOptionType.CommandBlock) {
            this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 84, 248, 20, this.option.command));
            this.getTextField(4).func_146203_f(Short.MAX_VALUE);
            this.addLabel(new GuiNpcLabel(5, "advMode.nearestPlayer", this.guiLeft + 4, this.guiTop + 110));
            this.addLabel(new GuiNpcLabel(6, "advMode.randomPlayer", this.guiLeft + 4, this.guiTop + 125));
            this.addLabel(new GuiNpcLabel(7, "advMode.allPlayers", this.guiLeft + 4, this.guiTop + 140));
            this.addLabel(new GuiNpcLabel(8, "dialog.commandoptionplayer", this.guiLeft + 4, this.guiTop + 155));
            this.addLabel(new GuiNpcLabel(9, "dialog.commandmultiple", this.guiLeft + 4, this.guiTop + 170));
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 82, this.guiTop + 190, 98, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 1) {
            this.option.optionType = EnumOptionType.values()[button.getValue()];
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            this.setSubGui(new SubGuiColorSelector(this.option.optionColor));
        }
        if (button.field_146127_k == 3) {
            this.setSubGui(new GuiDialogSelection(this.option.dialogId));
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 0) {
            if (textfield.isEmpty()) {
                textfield.func_146180_a(this.option.title);
            } else {
                this.option.title = textfield.func_146179_b();
            }
        }
        if (textfield.id == 4) {
            this.option.command = textfield.func_146179_b();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("DialogId")) {
            Dialog dialog = new Dialog();
            dialog.readNBT(compound);
            this.option.dialogId = dialog.id;
            if (this.getButton(3) != null) {
                this.getButton(3).setDisplayText(dialog.title);
            }
        }
    }

    @Override
    public void selected(int ob, String name) {
        this.option.dialogId = ob;
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        Dialog dialog;
        if (subgui instanceof SubGuiColorSelector) {
            LastColor = this.option.optionColor = ((SubGuiColorSelector)subgui).color;
        }
        if (subgui instanceof GuiDialogSelection && (dialog = ((GuiDialogSelection)subgui).selectedDialog) != null) {
            this.option.dialogId = dialog.id;
        }
        this.func_73866_w_();
    }
}

