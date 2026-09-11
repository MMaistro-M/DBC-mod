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
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.roles.RoleTrader;

public class SubGuiNpcTraderStockReset
extends SubGuiInterface {
    private final RoleTrader role;
    public boolean confirmed = false;

    public SubGuiNpcTraderStockReset(RoleTrader role) {
        this.role = role;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 100;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 15;
        this.addLabel(new GuiNpcLabel(0, "stock.reset.confirm", this.guiLeft + 10, y));
        this.addLabel(new GuiNpcLabel(1, "stock.reset.warning", this.guiLeft + 10, y += 12));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 30, y += 35, 60, 20, "gui.yes"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 110, y, 60, 20, "gui.no"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            long currentTime = this.role.stock.resetType.isRealTime() ? System.currentTimeMillis() : (this.role.npc != null && this.role.npc.field_70170_p != null ? this.role.npc.field_70170_p.func_82737_E() : 0L);
            this.role.stock.resetStock(currentTime);
            this.confirmed = true;
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.close();
        }
    }
}

