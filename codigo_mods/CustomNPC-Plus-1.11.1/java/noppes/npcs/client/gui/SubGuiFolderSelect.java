/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiFolderSelect
extends SubGuiInterface
implements ICustomScrollListener {
    public String selectedFolder;
    private final List<String> folderList;
    private GuiCustomScroll folderScroll;

    public SubGuiFolderSelect(List<String> folders, String currentSelection) {
        this.folderList = new ArrayList<String>(folders);
        this.selectedFolder = currentSelection;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 220;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.folderScroll = new GuiCustomScroll(this, 0);
        this.folderScroll.setSize(this.xSize - 8, this.ySize - 50);
        this.folderScroll.guiLeft = this.guiLeft + 4;
        this.folderScroll.guiTop = this.guiTop + 4;
        this.folderScroll.setList(new ArrayList<String>(this.folderList));
        if (this.selectedFolder != null) {
            this.folderScroll.setSelected(this.selectedFolder);
        }
        this.addScroll(this.folderScroll);
        int btnW = (this.xSize - 12) / 2;
        this.addButton(new GuiNpcButton(0, this.guiLeft + 4, this.guiTop + this.ySize - 44, btnW, 20, "gui.done"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 4 + btnW + 4, this.guiTop + this.ySize - 44, btnW, 20, "gui.cancel"));
        this.getButton((int)0).field_146124_l = this.folderScroll.hasSelected();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        this.getButton((int)0).field_146124_l = this.folderScroll.hasSelected();
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
        this.selectedFolder = selection;
        this.close();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0) {
            this.selectedFolder = this.folderScroll.getSelected();
            this.close();
        }
        if (guibutton.field_146127_k == 1) {
            this.selectedFolder = null;
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

