/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiAdminPanel;

public class GuiAdminPanel
extends GuiScreen {
    protected final int guiWidth;
    protected final int guiHeight;
    protected int guiLeft;
    protected int guiTop;
    private GuiButtonExt recoverSkins;
    private GuiButtonExt reloadLibrary;
    private GuiButtonExt updateSkins;

    public GuiAdminPanel() {
        this.guiWidth = 180;
        this.guiHeight = 128;
    }

    public void func_73866_w_() {
        this.guiLeft = this.field_146294_l / 2 - this.guiWidth / 2;
        this.guiTop = this.field_146295_m / 2 - this.guiHeight / 2;
        this.field_146292_n.clear();
        this.recoverSkins = new GuiButtonExt(0, this.guiLeft + 5, this.guiTop + 5, 100, 15, "Recover Skins");
        this.reloadLibrary = new GuiButtonExt(0, this.guiLeft + 5, this.guiTop + 25, 100, 15, "Reload Library");
        this.updateSkins = new GuiButtonExt(0, this.guiLeft + 5, this.guiTop + 45, 100, 15, "Update Skins");
        this.field_146292_n.add(this.recoverSkins);
        this.field_146292_n.add(this.reloadLibrary);
        this.field_146292_n.add(this.updateSkins);
    }

    protected void func_73869_a(char key, int keycode) {
        if (keycode == 1 || keycode == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        super.func_73869_a(key, keycode);
    }

    public boolean func_73868_f() {
        return false;
    }

    protected void func_146284_a(GuiButton button) {
        MessageClientGuiAdminPanel message;
        if (button == this.recoverSkins) {
            message = new MessageClientGuiAdminPanel(MessageClientGuiAdminPanel.AdminPanelCommand.RECOVER_SKINS);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
        if (button == this.reloadLibrary) {
            message = new MessageClientGuiAdminPanel(MessageClientGuiAdminPanel.AdminPanelCommand.RELOAD_LIBRARY);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
        if (button == this.updateSkins) {
            message = new MessageClientGuiAdminPanel(MessageClientGuiAdminPanel.AdminPanelCommand.UPDATE_SKINS);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.func_73733_a(this.guiLeft, this.guiTop, this.guiLeft + this.guiWidth, this.guiTop + this.guiHeight, -1072689136, -804253680);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}

