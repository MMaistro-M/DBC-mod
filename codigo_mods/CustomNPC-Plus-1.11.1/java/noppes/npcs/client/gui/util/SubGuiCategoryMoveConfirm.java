/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiCategoryMoveConfirm
extends SubGuiInterface {
    public boolean confirmed = false;
    private final int count;
    public final String destCategoryName;

    public SubGuiCategoryMoveConfirm(int count, String destCategoryName) {
        this.count = count;
        this.destCategoryName = destCategoryName;
        this.xSize = 220;
        this.ySize = 90;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        String line1 = String.format(StatCollector.func_74838_a((String)"gui.move.confirmLine"), this.count);
        this.addLabel(new GuiNpcLabel(0, line1, this.guiLeft + 10, this.guiTop + 10, 0xFFFFFF));
        String dest = StatCollector.func_74838_a((String)"gui.category") + ": \"" + this.destCategoryName + "\"";
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

