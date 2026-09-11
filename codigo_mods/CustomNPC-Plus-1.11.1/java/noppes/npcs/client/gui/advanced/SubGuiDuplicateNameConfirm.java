/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiDuplicateNameConfirm
extends SubGuiInterface {
    private Result result = Result.CANCEL;

    public SubGuiDuplicateNameConfirm() {
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 80;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.duplicate.name", this.guiLeft + 10, y));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 30, 65, 20, "gui.continue"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 78, y, 65, 20, "gui.back"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 146, y, 65, 20, "gui.cancel"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.result = Result.CONTINUE;
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.result = Result.BACK;
            this.close();
        } else if (guibutton.field_146127_k == 2) {
            this.result = Result.CANCEL;
            this.close();
        }
    }

    public Result getResult() {
        return this.result;
    }

    public boolean isConfirmed() {
        return this.result == Result.CONTINUE;
    }

    public boolean isBack() {
        return this.result == Result.BACK;
    }

    public boolean isCancelled() {
        return this.result == Result.CANCEL;
    }

    public static enum Result {
        CONTINUE,
        BACK,
        CANCEL;

    }
}

