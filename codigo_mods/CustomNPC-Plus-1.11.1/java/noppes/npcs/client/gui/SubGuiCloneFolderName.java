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
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.CloneFolder;

public class SubGuiCloneFolderName
extends SubGuiInterface {
    private String originalName;
    public boolean cancelled = true;
    private String folderName = "";

    public SubGuiCloneFolderName(String existingName) {
        this.originalName = existingName;
        this.folderName = existingName;
        this.xSize = 200;
        this.ySize = 80;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        String title = this.isRename() ? "Rename Folder" : "New Folder";
        this.addLabel(new GuiNpcLabel(0, title, this.guiLeft + 5, this.guiTop + 5));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 5, this.guiTop + 20, 190, 20, this.folderName));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 5, this.guiTop + 50, 90, 20, "gui.done"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 100, this.guiTop + 50, 90, 20, "gui.cancel"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.folderName = this.getTextField(0).func_146179_b().trim();
            if (CloneFolder.isValidName(this.folderName)) {
                this.cancelled = false;
                this.close();
            }
        }
        if (guibutton.field_146127_k == 1) {
            this.cancelled = true;
            this.close();
        }
    }

    public boolean isRename() {
        return this.originalName != null && !this.originalName.isEmpty();
    }

    public String getFolderName() {
        return this.folderName;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    @Override
    public void save() {
    }
}

