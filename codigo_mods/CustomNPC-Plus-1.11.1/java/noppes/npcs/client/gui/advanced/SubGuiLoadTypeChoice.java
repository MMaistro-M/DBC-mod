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

public class SubGuiLoadTypeChoice
extends SubGuiSimpleChoice {
    public static final int RESULT_NONE = -1;
    public static final int RESULT_ABILITY = 0;
    public static final int RESULT_CHAIN = 1;

    public SubGuiLoadTypeChoice() {
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 80;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.loadType", this.guiLeft + 10, y));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 20, 85, 20, "ability.loadAbility"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, y, 85, 20, "ability.loadChain"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 55, y += 24, 90, 20, "gui.cancel"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 || id == 1) {
            this.setResult(id);
            this.close();
        } else if (id == 2) {
            this.close();
        }
    }
}

