/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.skinlibrary;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;

@SideOnly(value=Side.CLIENT)
public class GuiDialogDelete
extends AbstractGuiDialog {
    private final boolean folder;
    private final String fileName;
    private GuiButtonExt buttonClose;
    private GuiButtonExt buttonDelete;

    public GuiDialogDelete(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height, boolean folder, String fileName) {
        super(parent, name, callback, width, height);
        this.folder = folder;
        this.fileName = fileName;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonClose = new GuiButtonExt(-1, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "close"));
        this.buttonDelete = new GuiButtonExt(-1, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "delete"));
        this.buttonList.add(this.buttonClose);
        this.buttonList.add(this.buttonDelete);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonClose) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonDelete) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        super.drawForeground(mouseX, mouseY, partialTickTime);
        this.drawTitle();
        if (this.folder) {
            this.fontRenderer.func_78279_b(String.format(GuiHelper.getLocalizedControlName(this.name, "deleteFolder"), this.fileName), this.x + 10, this.y + 45, this.width - 20, -39322);
        } else {
            this.fontRenderer.func_78279_b(String.format(GuiHelper.getLocalizedControlName(this.name, "deleteFile"), this.fileName), this.x + 10, this.y + 45, this.width - 20, -39322);
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isFolder() {
        return this.folder;
    }
}

