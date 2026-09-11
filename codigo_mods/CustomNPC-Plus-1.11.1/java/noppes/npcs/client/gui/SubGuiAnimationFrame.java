/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.Frame;

public class SubGuiAnimationFrame
extends SubGuiInterface
implements ITextfieldListener {
    private final Frame editingFrame;

    public SubGuiAnimationFrame(Frame editingFrame) {
        this.editingFrame = editingFrame;
        this.setBackground("menubg.png");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(10, "animation.customized", this.guiLeft + 5, this.guiTop + 16));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 75, this.guiTop + 10, 30, 20, new String[]{"gui.yes", "gui.no"}, this.editingFrame.isCustomized() ? 0 : 1));
        if (this.editingFrame.isCustomized()) {
            this.addLabel(new GuiNpcLabel(11, "stats.speed", this.guiLeft + 5, this.guiTop + 42));
            this.addTextField(new GuiNpcTextField(11, this, this.guiLeft + 75, this.guiTop + 37, 30, 15, this.editingFrame.speed + ""));
            this.getTextField((int)11).floatsOnly = true;
            this.getTextField(11).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 1.0f);
            this.addLabel(new GuiNpcLabel(12, "animation.smoothing", this.guiLeft + 5, this.guiTop + 63));
            this.addButton(new GuiNpcButton(12, this.guiLeft + 75, this.guiTop + 55, 60, 20, new String[]{"animation.smooth", "animation.linear", "gui.none"}, (int)this.editingFrame.smooth));
        }
        this.addLabel(new GuiNpcLabel(14, "Comment", this.guiLeft + 5, this.guiTop + 106));
        this.addTextField(new GuiNpcTextField(14, this, this.guiLeft + 50, this.guiTop + 101, 120, 15, this.editingFrame.getComment()));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (this.editingFrame == null) {
            return;
        }
        int value = ((GuiNpcButton)guibutton).getValue();
        if (guibutton.field_146127_k == 10) {
            this.editingFrame.setCustomized(!this.editingFrame.isCustomized());
        } else if (guibutton.field_146127_k == 12) {
            this.editingFrame.smooth = (byte)value;
        }
        this.func_73866_w_();
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (this.editingFrame == null) {
            return;
        }
        if (textfield.id == 11) {
            this.editingFrame.speed = textfield.getFloat();
        } else if (textfield.id == 14) {
            this.editingFrame.setComment(textfield.func_146179_b());
        }
    }
}

