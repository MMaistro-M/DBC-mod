/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.dialog;

import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;

public class GuiGlobalLibraryDialogDelete
extends AbstractGuiDialog {
    private GuiButtonExt buttonOk;
    private GuiButtonExt buttonCancel;

    public GuiGlobalLibraryDialogDelete(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height) {
        super(parent, name, callback, width, height);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonOk = new GuiButtonExt(0, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "ok"));
        this.buttonCancel = new GuiButtonExt(0, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "cancel"));
        this.buttonList.add(this.buttonOk);
        this.buttonList.add(this.buttonCancel);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonCancel) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonOk) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        super.drawForeground(mouseX, mouseY, partialTickTime);
        this.drawTitle();
    }
}

