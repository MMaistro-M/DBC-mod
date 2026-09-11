/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.player;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.player.GuiAuctionListing;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiAuctionSearch
extends SubGuiInterface
implements ITextfieldListener {
    private static final int TXT_SEARCH = 0;
    private static final int BTN_SUBMIT = 1;
    private static final int BTN_CLEAR = 2;
    private static final int BTN_CLOSE = 3;
    private String searchText;

    public SubGuiAuctionSearch(String currentSearchText) {
        this.searchText = currentSearchText != null ? currentSearchText : "";
        this.setBackground("menubg.png");
        this.xSize = 180;
        this.ySize = 80;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(3, this.guiLeft + this.xSize - 22, this.guiTop + 4, 18, 18, "X"));
        this.addLabel(new GuiNpcLabel(0, "auction.filter.search", this.guiLeft + 10, this.guiTop + 10, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 10, this.guiTop + 25, 160, 18, this.searchText));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 10, this.guiTop + 50, 70, 20, "gui.apply"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 85, this.guiTop + 50, 70, 20, "gui.clear"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 0) {
            this.searchText = textfield.func_146179_b();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 1) {
            GuiNpcTextField field = this.getTextField(0);
            if (field != null) {
                this.searchText = field.func_146179_b();
            }
            if (this.parent instanceof GuiAuctionListing) {
                ((GuiAuctionListing)this.parent).onSearchSubmit(this.searchText);
            }
            this.close();
        } else if (guibutton.field_146127_k == 2) {
            this.searchText = "";
            GuiNpcTextField field = this.getTextField(0);
            if (field != null) {
                field.func_146180_a("");
            }
            if (this.parent instanceof GuiAuctionListing) {
                ((GuiAuctionListing)this.parent).onSearchSubmit("");
            }
            this.close();
        } else if (guibutton.field_146127_k == 3) {
            this.close();
        }
    }

    public String getSearchText() {
        return this.searchText;
    }
}

