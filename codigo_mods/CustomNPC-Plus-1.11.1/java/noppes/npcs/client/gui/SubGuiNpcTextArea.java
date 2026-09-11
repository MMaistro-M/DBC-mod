/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextArea;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcTextArea
extends SubGuiInterface {
    public String text;
    private GuiNpcTextArea textarea;

    public SubGuiNpcTextArea(String text) {
        this.text = text;
        this.setBackground("bgfilled.png");
        this.xSize = 256;
        this.ySize = 256;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        this.xSize = (int)((double)this.field_146294_l * 0.88);
        this.ySize = (int)((double)this.xSize * 0.56);
        this.bgScale = (float)this.xSize / 440.0f;
        super.func_73866_w_();
        if (this.textarea != null) {
            this.text = this.textarea.func_146179_b();
        }
        int yoffset = (int)((double)this.ySize * 0.02);
        this.textarea = new GuiNpcTextArea(2, this, this.guiLeft + yoffset, this.guiTop + yoffset, this.xSize - 100 - yoffset * 2, this.ySize - yoffset * 2, this.text);
        this.addTextField(this.textarea);
        this.field_146292_n.add(new GuiNpcButton(102, this.guiLeft + this.xSize - 90 - yoffset, this.guiTop + 20, 56, 20, "gui.clear"));
        this.field_146292_n.add(new GuiNpcButton(101, this.guiLeft + this.xSize - 90 - yoffset, this.guiTop + 43, 56, 20, "gui.paste"));
        this.field_146292_n.add(new GuiNpcButton(100, this.guiLeft + this.xSize - 90 - yoffset, this.guiTop + 66, 56, 20, "gui.copy"));
        this.field_146292_n.add(new GuiNpcButton(0, this.guiLeft + this.xSize - 90 - yoffset, this.guiTop + 160, 56, 20, "gui.close"));
        this.xSize = 420;
        this.ySize = 256;
    }

    @Override
    public void close() {
        this.text = this.getTextField(2).func_146179_b();
        super.close();
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 100) {
            NoppesStringUtils.setClipboardContents(this.getTextField(2).func_146179_b());
        }
        if (id == 101) {
            this.getTextField(2).func_146180_a(NoppesStringUtils.getClipboardContents());
        }
        if (id == 102) {
            this.getTextField(2).func_146180_a("");
        }
        if (id == 0) {
            this.close();
        }
    }
}

