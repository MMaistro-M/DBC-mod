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
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

@SideOnly(value=Side.CLIENT)
public class GuiDialogNewFolder
extends AbstractGuiDialog {
    private GuiLabeledTextField textFolderName;
    private GuiButtonExt buttonClose;
    private GuiButtonExt buttonCreate;
    private boolean invalidFolderName;

    public GuiDialogNewFolder(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height) {
        super(parent, name, callback, width, height);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.textFolderName = new GuiLabeledTextField(this.fontRenderer, this.x + 10, this.y + 26, this.width - 20, 12);
        this.textFolderName.func_146203_f(30);
        this.textFolderName.setEmptyLabel(GuiHelper.getLocalizedControlName(this.name, "enterFolderName"));
        this.buttonClose = new GuiButtonExt(-1, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "close"));
        this.buttonCreate = new GuiButtonExt(-1, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "create"));
        this.buttonList.add(this.buttonClose);
        this.buttonList.add(this.buttonCreate);
        this.textFolderName.func_146195_b(true);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonClose) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonCreate && !this.getFolderName().equals("")) {
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
        this.textFolderName.func_146194_f();
        this.drawTitle();
        if (this.invalidFolderName) {
            this.fontRenderer.func_78279_b(GuiHelper.getLocalizedControlName(this.name, "invalidFolderName"), this.x + 10, this.y + 45, this.width - 20, -39322);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        this.textFolderName.func_146192_a(mouseX, mouseY, button);
        if (button == 1 && this.textFolderName.func_146206_l()) {
            this.textFolderName.func_146180_a("");
            this.checkFolderName();
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        if (this.textFolderName.func_146201_a(c, keycode)) {
            this.checkFolderName();
            return true;
        }
        if (keycode == 28 && !this.getFolderName().equals("") & !this.invalidFolderName) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
        return super.keyTyped(c, keycode);
    }

    private void checkFolderName() {
        this.invalidFolderName = !SkinIOUtils.makeFileNameValid(this.getFolderName()).equals(this.getFolderName());
        this.buttonCreate.field_146124_l = !this.invalidFolderName;
    }

    public String getFolderName() {
        return this.textFolderName.func_146179_b().trim();
    }
}

