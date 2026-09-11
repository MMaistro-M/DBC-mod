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
public class GuiDialogOverwrite
extends AbstractGuiDialog {
    private final String fileName;
    private GuiButtonExt buttonClose;
    private GuiButtonExt buttonOK;

    public GuiDialogOverwrite(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height, String fileName) {
        super(parent, name, callback, width, height);
        this.fileName = fileName;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonClose = new GuiButtonExt(-1, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "close"));
        this.buttonOK = new GuiButtonExt(-1, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "ok"));
        this.buttonList.add(this.buttonClose);
        this.buttonList.add(this.buttonOK);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonClose) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonOK) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float partialTickTime) {
        super.drawBackground(mouseX, mouseY, partialTickTime);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        super.drawForeground(mouseX, mouseY, partialTickTime);
        this.drawTitle();
        this.fontRenderer.func_78279_b(String.format(GuiHelper.getLocalizedControlName(this.name, "overwriteFile"), this.fileName), this.x + 10, this.y + 45, this.width - 20, -39322);
    }

    public String getFileName() {
        return this.fileName;
    }
}

