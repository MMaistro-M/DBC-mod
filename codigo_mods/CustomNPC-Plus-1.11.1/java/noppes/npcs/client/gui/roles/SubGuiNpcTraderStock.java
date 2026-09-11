/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.roles;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.roles.SubGuiNpcTraderCooldownReset;
import noppes.npcs.client.gui.roles.SubGuiNpcTraderStockReset;
import noppes.npcs.client.gui.roles.SubGuiNpcTraderStockSlots;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.gui.util.SubGuiNpcCooldownPicker;
import noppes.npcs.constants.EnumStockReset;
import noppes.npcs.roles.RoleTrader;

public class SubGuiNpcTraderStock
extends SubGuiInterface
implements ISubGuiListener,
ITextfieldListener {
    private final RoleTrader role;

    public SubGuiNpcTraderStock(RoleTrader role) {
        this.role = role;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 220;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(1, "stock.enable", this.guiLeft + 10, y + 5));
        this.addButton(new GuiNpcButtonYesNo(1, this.guiLeft + 140, y, this.role.stock.enableStock));
        this.getButton(1).setHoverText("stock.enable.hover");
        this.addLabel(new GuiNpcLabel(2, "stock.perplayer", this.guiLeft + 10, (y += 24) + 5));
        this.addButton(new GuiNpcButtonYesNo(2, this.guiLeft + 140, y, this.role.stock.perPlayer));
        this.getButton(2).setHoverText("stock.perplayer.hover");
        this.addLabel(new GuiNpcLabel(3, "stock.resettype", this.guiLeft + 10, (y += 24) + 5));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 90, y, 120, 20, EnumStockReset.getDisplayNames(), this.role.stock.resetType.ordinal()));
        this.getButton(3).setHoverText("stock.resettype.hover");
        y += 24;
        EnumStockReset resetType = this.role.stock.resetType;
        if (resetType == EnumStockReset.MCCUSTOM || resetType == EnumStockReset.RLCUSTOM) {
            this.addLabel(new GuiNpcLabel(4, "stock.customtime", this.guiLeft + 10, y + 5));
            this.addButton(new GuiNpcButton(4, this.guiLeft + 90, y, 120, 20, "gui.edit"));
            this.getButton(4).setHoverText("stock.customtime.hover");
            y += 24;
        }
        this.addLabel(new GuiNpcLabel(5, "stock.defaultmax", this.guiLeft + 10, y + 5));
        GuiNpcTextField defaultStock = new GuiNpcTextField(5, this, this.field_146289_q, this.guiLeft + 140, y, 60, 20, this.getDefaultStockString());
        defaultStock.setIntegersOnly();
        defaultStock.setMinMaxDefault(-1, Integer.MAX_VALUE, -1);
        this.addTextField(defaultStock);
        this.addButton(new GuiNpcButton(5, this.guiLeft + 10, y += 26, 95, 20, "stock.perslot"));
        this.getButton(5).setHoverText("stock.perslot.hover");
        this.addButton(new GuiNpcButton(6, this.guiLeft + 115, y, 95, 20, "stock.reset"));
        this.getButton(6).setHoverText("stock.reset.hover");
        this.addButton(new GuiNpcButton(7, this.guiLeft + 10, y += 24, 200, 20, "stock.resetcooldown"));
        this.getButton(7).setHoverText("stock.resetcooldown.hover");
        this.addButton(new GuiNpcButton(0, this.guiLeft + (this.xSize - 60) / 2, this.guiTop + this.ySize - 40, 60, 20, "gui.done"));
    }

    private String getDefaultStockString() {
        int first = this.role.stock.maxStock[0];
        for (int i = 1; i < 18; ++i) {
            if (this.role.stock.maxStock[i] == first) continue;
            return "-1";
        }
        return "" + first;
    }

    @Override
    public void buttonEvent(GuiButton button) {
        switch (button.field_146127_k) {
            case 0: {
                this.close();
                break;
            }
            case 1: {
                this.role.stock.enableStock = ((GuiNpcButtonYesNo)button).getBoolean();
                break;
            }
            case 2: {
                this.role.stock.perPlayer = ((GuiNpcButtonYesNo)button).getBoolean();
                break;
            }
            case 3: {
                int value = ((GuiNpcButton)button).getValue();
                this.role.stock.resetType = EnumStockReset.values()[value];
                this.func_73866_w_();
                break;
            }
            case 4: {
                boolean isMC = this.role.stock.resetType == EnumStockReset.MCCUSTOM;
                this.setSubGui(new SubGuiNpcCooldownPicker(isMC, this.role.stock.customResetTime));
                break;
            }
            case 5: {
                this.setSubGui(new SubGuiNpcTraderStockSlots(this.role));
                break;
            }
            case 6: {
                this.setSubGui(new SubGuiNpcTraderStockReset(this.role));
                break;
            }
            case 7: {
                this.setSubGui(new SubGuiNpcTraderCooldownReset(this.role));
            }
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subGui) {
        if (subGui instanceof SubGuiNpcCooldownPicker) {
            long value = ((SubGuiNpcCooldownPicker)subGui).cooldownValue;
            boolean isMC = this.role.stock.resetType == EnumStockReset.MCCUSTOM;
            long minimum = isMC ? 1L : 1000L;
            this.role.stock.customResetTime = Math.max(minimum, value);
        }
        if (subGui instanceof SubGuiNpcTraderStockSlots) {
            this.func_73866_w_();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (textField.id == 5) {
            int value = textField.getInteger();
            for (int i = 0; i < 18; ++i) {
                this.role.stock.setMaxStock(i, value);
            }
        }
    }
}

