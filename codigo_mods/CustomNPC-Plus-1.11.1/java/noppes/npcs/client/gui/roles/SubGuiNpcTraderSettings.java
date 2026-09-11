/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.roles;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.roles.RoleTrader;

public class SubGuiNpcTraderSettings
extends SubGuiInterface {
    private final RoleTrader role;

    public SubGuiNpcTraderSettings(RoleTrader role) {
        this.role = role;
        this.setBackground("menubg.png");
        this.xSize = 176;
        this.ySize = 136;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 14;
        this.addLabel(new GuiNpcLabel(1, "gui.ignoreDamage", this.guiLeft + 10, y + 5));
        this.addButton(new GuiNpcButtonYesNo(1, this.guiLeft + 120, y, this.role.ignoreDamage));
        this.getButton(1).setHoverText("gui.ignoreDamage.hover");
        this.addLabel(new GuiNpcLabel(2, "gui.ignoreNBT", this.guiLeft + 10, (y += 28) + 5));
        this.addButton(new GuiNpcButtonYesNo(2, this.guiLeft + 120, y, this.role.ignoreNBT));
        this.getButton(2).setHoverText("gui.ignoreNBT.hover");
        this.addLabel(new GuiNpcLabel(3, "gui.recordHistory", this.guiLeft + 10, (y += 28) + 5));
        this.addButton(new GuiNpcButtonYesNo(3, this.guiLeft + 120, y, this.role.recordHistory));
        this.getButton(3).setHoverText("gui.recordHistory.hover");
        this.addButton(new GuiNpcButton(0, this.guiLeft + (this.xSize - 60) / 2, y += 34, 60, 20, "gui.done"));
    }

    @Override
    public void buttonEvent(GuiButton button) {
        switch (button.field_146127_k) {
            case 0: {
                this.close();
                break;
            }
            case 1: {
                this.role.ignoreDamage = ((GuiNpcButtonYesNo)button).getBoolean();
                break;
            }
            case 2: {
                this.role.ignoreNBT = ((GuiNpcButtonYesNo)button).getBoolean();
                break;
            }
            case 3: {
                this.role.recordHistory = ((GuiNpcButtonYesNo)button).getBoolean();
            }
        }
    }
}

