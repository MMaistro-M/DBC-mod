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
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiMoveConfirm
extends SubGuiInterface {
    public boolean confirmed = false;
    private final int count;
    public final int destTab;
    public final String destFolder;

    public SubGuiMoveConfirm(int count, int destTab, String destFolder) {
        this.count = count;
        this.destTab = destTab;
        this.destFolder = destFolder;
        this.xSize = 220;
        this.ySize = 90;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        String line1 = "Move " + this.count + " clone" + (this.count != 1 ? "s" : "") + " to:";
        this.addLabel(new GuiNpcLabel(0, line1, this.guiLeft + 10, this.guiTop + 10, 0xFFFFFF));
        String dest = this.destFolder != null ? "Folder: \"" + this.destFolder + "\"" : "Tab: " + this.destTab;
        this.addLabel(new GuiNpcLabel(1, "  " + dest, this.guiLeft + 10, this.guiTop + 25, 0xFFFFFF));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, this.guiTop + 55, 95, 20, "gui.yes"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 115, this.guiTop + 55, 95, 20, "gui.cancel"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.confirmed = true;
            this.close();
        }
        if (guibutton.field_146127_k == 1) {
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

