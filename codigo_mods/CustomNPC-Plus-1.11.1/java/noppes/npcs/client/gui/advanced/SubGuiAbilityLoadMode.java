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

public class SubGuiAbilityLoadMode
extends SubGuiSimpleChoice {
    public static final int MODE_CLONE = 0;
    public static final int MODE_REFERENCE = 1;

    public SubGuiAbilityLoadMode() {
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 90;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.load.mode", this.guiLeft + 10, y));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 15, y += 25, 80, 20, "ability.clone"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, y, 80, 20, "ability.reference"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 55, y += 25, 90, 20, "gui.cancel"));
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

