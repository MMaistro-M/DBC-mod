/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.advanced.SubGuiSimpleChoice;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;

public class SubGuiAbilityEditMode
extends SubGuiSimpleChoice {
    public static final int MODE_CLONE_MODIFY = 0;
    public static final int MODE_MODIFY_PARENT = 1;

    public SubGuiAbilityEditMode() {
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 90;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.edit.mode", this.guiLeft + 10, y));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 25, 95, 20, "ability.cloneModify"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 115, y, 95, 20, "ability.modifyParent"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 65, y += 25, 90, 20, "gui.cancel"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.setResult(0);
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.setResult(1);
            this.close();
        } else if (guibutton.field_146127_k == 2) {
            this.close();
        }
    }
}

