/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.roles;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.roles.RoleTrader;

public class SubGuiNpcTraderStockSlots
extends SubGuiInterface
implements ITextfieldListener {
    private final RoleTrader role;

    public SubGuiNpcTraderStockSlots(RoleTrader role) {
        this.role = role;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "stock.perslot.title", this.guiLeft + 10, this.guiTop + 6));
        int startY = this.guiTop + 22;
        int colWidth = 80;
        for (int i = 0; i < 18; ++i) {
            int col = i % 3;
            int row = i / 3;
            int x = this.guiLeft + 10 + col * colWidth;
            int y = startY + row * 28;
            this.addLabel(new GuiNpcLabel(i + 1, "Slot " + (i + 1) + ":", x, y + 5));
            int maxStock = this.role.stock.maxStock[i];
            String value = maxStock < 0 ? "-1" : "" + maxStock;
            GuiNpcTextField field = new GuiNpcTextField(i, this, this.field_146289_q, x + 42, y, 32, 18, value);
            field.setIntegersOnly();
            field.setMinMaxDefault(-1, Integer.MAX_VALUE, -1);
            this.addTextField(field);
        }
        this.addButton(new GuiNpcButton(0, this.guiLeft + (this.xSize - 60) / 2, this.guiTop + this.ySize - 26, 60, 20, "gui.done"));
    }

    @Override
    public void buttonEvent(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        int slot = textField.id;
        if (slot >= 0 && slot < 18) {
            int value = textField.getInteger();
            this.role.stock.setMaxStock(slot, value);
        }
    }
}

