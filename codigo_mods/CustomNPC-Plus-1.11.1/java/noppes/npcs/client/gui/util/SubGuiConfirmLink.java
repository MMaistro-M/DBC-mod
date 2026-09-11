/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.I18n
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiConfirmLink
extends SubGuiInterface {
    private final String url;
    public boolean confirmed = false;

    public SubGuiConfirmLink(String url) {
        this.url = url;
        this.xSize = 260;
        this.ySize = 120;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcLabel titleLabel = new GuiNpcLabel(0, I18n.func_135052_a((String)"chat.link.confirmTrusted", (Object[])new Object[0]), this.guiLeft, this.guiTop + 10, 0xFFFFFF);
        titleLabel.center(this.xSize);
        this.addLabel(titleLabel);
        GuiNpcLabel urlLabel = new GuiNpcLabel(1, this.url, this.guiLeft, this.guiTop + 28, 0xCCCCFF);
        urlLabel.center(this.xSize);
        this.addLabel(urlLabel);
        int btnWidth = 115;
        int spacing = 10;
        int totalWidth = btnWidth * 2 + spacing;
        int startX = this.guiLeft + (this.xSize - totalWidth) / 2;
        this.addButton(new GuiNpcButton(0, startX, this.guiTop + 52, btnWidth, 20, "chat.link.open"));
        this.addButton(new GuiNpcButton(1, startX + btnWidth + spacing, this.guiTop + 52, btnWidth, 20, "chat.copy"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + (this.xSize - btnWidth) / 2, this.guiTop + 78, btnWidth, 20, "gui.cancel"));
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, -1072689136, -1072689136);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.confirmed = true;
            this.openLink(this.url);
        } else if (guibutton.field_146127_k == 1) {
            this.confirmed = true;
            NoppesStringUtils.setClipboardContents(this.url);
        }
        this.close();
    }

    @Override
    public void save() {
    }
}

