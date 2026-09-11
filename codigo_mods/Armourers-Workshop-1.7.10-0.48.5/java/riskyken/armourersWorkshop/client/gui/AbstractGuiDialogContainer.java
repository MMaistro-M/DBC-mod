/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;

@SideOnly(value=Side.CLIENT)
public abstract class AbstractGuiDialogContainer
extends GuiContainer
implements AbstractGuiDialog.IDialogCallback {
    protected AbstractGuiDialog dialog;

    public AbstractGuiDialogContainer(Container container) {
        super(container);
    }

    public void openDialog(AbstractGuiDialog dialog) {
        this.dialog = dialog;
        this.dialog.initGui();
    }

    protected boolean isDialogOpen() {
        return this.dialog != null;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.isDialogOpen()) {
            this.dialog.initGui();
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int button) {
        if (this.isDialogOpen()) {
            this.dialog.mouseClicked(mouseX, mouseY, button);
        } else {
            super.func_73864_a(mouseX, mouseY, button);
        }
    }

    protected void func_146273_a(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
        if (this.isDialogOpen()) {
            this.dialog.mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        } else {
            super.func_146273_a(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int button) {
        if (this.isDialogOpen()) {
            this.dialog.mouseMovedOrUp(mouseX, mouseY, button);
        } else {
            super.func_146286_b(mouseX, mouseY, button);
        }
    }

    protected void func_73869_a(char c, int keycode) {
        if (this.isDialogOpen()) {
            this.dialog.keyTyped(c, keycode);
        } else {
            super.func_73869_a(c, keycode);
        }
    }

    @Override
    public void dialogResult(AbstractGuiDialog dialog, AbstractGuiDialog.DialogResult result) {
        this.dialog = null;
    }
}

