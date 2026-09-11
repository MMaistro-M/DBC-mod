/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Slot
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.globallibrary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialogContainer;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPaneJoinBeta;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelHeader;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelHome;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelSearchBox;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelSearchResults;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelSkinEdit;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelSkinInfo;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelUpload;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelUserSkins;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.inventory.ContainerGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibrary
extends AbstractGuiDialogContainer {
    public final TileEntityGlobalSkinLibrary tileEntity;
    public final EntityPlayer player;
    public ArrayList<GuiPanel> panelList;
    private int oldMouseX;
    private int oldMouseY;
    public Executor jsonDownloadExecutor = Executors.newFixedThreadPool(2);
    public Executor uploadExecutor = Executors.newFixedThreadPool(1);
    private static final int PADDING = 5;
    private boolean isNEIVisible;
    public GuiGlobalLibraryPanelHeader panelHeader;
    public GuiGlobalLibraryPanelSearchBox panelSearchBox;
    public GuiGlobalLibraryPanelHome panelHome;
    public GuiGlobalLibraryPanelSearchResults panelSearchResults;
    public GuiGlobalLibraryPanelSkinInfo panelSkinInfo;
    public GuiGlobalLibraryPanelUpload panelUpload;
    public GuiGlobalLibraryPaneJoinBeta panelJoinBeta;
    public GuiGlobalLibraryPanelUserSkins panelUserSkins;
    public GuiGlobalLibraryPanelSkinEdit panelSkinEdit;
    private Screen screen;

    public GuiGlobalLibrary(TileEntityGlobalSkinLibrary tileEntity, InventoryPlayer inventoryPlayer) {
        super(new ContainerGlobalSkinLibrary(inventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.player = Minecraft.func_71410_x().field_71439_g;
        this.panelList = new ArrayList();
        this.panelHeader = new GuiGlobalLibraryPanelHeader((GuiScreen)this, 2, 2, this.field_146294_l - 4, 26);
        this.panelList.add(this.panelHeader);
        this.panelSearchBox = new GuiGlobalLibraryPanelSearchBox(this, 2, 31, this.field_146294_l - 4, 23);
        this.panelList.add(this.panelSearchBox);
        this.panelHome = new GuiGlobalLibraryPanelHome((GuiScreen)this, 2, 136, this.field_146294_l / 2 - 5, this.field_146295_m - 141);
        this.panelList.add(this.panelHome);
        this.panelSearchResults = new GuiGlobalLibraryPanelSearchResults((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelSearchResults);
        this.panelSkinInfo = new GuiGlobalLibraryPanelSkinInfo((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelSkinInfo);
        this.panelUpload = new GuiGlobalLibraryPanelUpload((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelUpload);
        this.panelJoinBeta = new GuiGlobalLibraryPaneJoinBeta((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelJoinBeta);
        this.panelUserSkins = new GuiGlobalLibraryPanelUserSkins((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelUserSkins);
        this.panelSkinEdit = new GuiGlobalLibraryPanelSkinEdit((GuiScreen)this, 5, 5, 100, 100);
        this.panelList.add(this.panelSkinEdit);
        this.screen = Screen.HOME;
        this.isNEIVisible = ModAddonManager.addonNEI.isVisible();
        if (!PlushieAuth.startedRemoteUserCheck()) {
            PlushieAuth.doRemoteUserCheck();
        }
    }

    @Override
    public void func_73866_w_() {
        ScaledResolution reso = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        this.field_146999_f = reso.func_78326_a();
        this.field_147000_g = reso.func_78328_b();
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.setupPanels();
        for (int i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).initGui();
        }
        if (this.screen == Screen.HOME) {
            this.panelHome.updateSkinPanels();
        }
    }

    public void setSlotVisibility(boolean visible) {
        for (int x = 0; x < 9; ++x) {
            Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x);
            if (!(slot instanceof SlotHidable)) continue;
            ((SlotHidable)slot).setVisible(visible);
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x + y * 9 + 9);
                if (!(slot instanceof SlotHidable)) continue;
                ((SlotHidable)slot).setVisible(visible);
            }
        }
        SlotHidable slot = this.getInputSlot();
        if (slot instanceof SlotHidable) {
            slot.setVisible(visible);
        }
        if ((slot = this.getOutputSlot()) instanceof SlotHidable) {
            slot.setVisible(visible);
        }
    }

    public void setPlayerSlotLocation(int xPos, int yPos) {
        for (int x = 0; x < 9; ++x) {
            Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x);
            if (!(slot instanceof SlotHidable)) continue;
            ((SlotHidable)slot).setDisplayPosition(xPos + x * 18, yPos + 58);
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x + y * 9 + 9);
                if (!(slot instanceof SlotHidable)) continue;
                ((SlotHidable)slot).setDisplayPosition(xPos + x * 18, yPos + y * 18);
            }
        }
    }

    public void setInputSlotLocation(int xPos, int yPos) {
        SlotHidable slot = this.getInputSlot();
        if (slot instanceof SlotHidable) {
            slot.setDisplayPosition(xPos, yPos);
        }
    }

    public void setOutputSlotLocation(int xPos, int yPos) {
        SlotHidable slot = this.getOutputSlot();
        if (slot instanceof SlotHidable) {
            slot.setDisplayPosition(xPos, yPos);
        }
    }

    public SlotHidable getInputSlot() {
        return (SlotHidable)((Object)this.field_147002_h.field_75151_b.get(36));
    }

    public SlotHidable getOutputSlot() {
        return (SlotHidable)((Object)this.field_147002_h.field_75151_b.get(37));
    }

    public ContainerGlobalSkinLibrary getContainer() {
        return (ContainerGlobalSkinLibrary)this.field_147002_h;
    }

    private void setupPanels() {
        for (int i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).setVisible(false);
        }
        this.setSlotVisibility(false);
        int yOffset = 5;
        this.panelHeader.setPosition(5, 5).setSize(this.field_146294_l - 10, 26);
        this.panelHeader.setVisible(true);
        yOffset += 31;
        int neiBump = 0;
        if (this.isNEIVisible) {
            neiBump = 18;
        }
        switch (this.screen) {
            case HOME: {
                this.panelSearchBox.setPosition(5, yOffset).setSize(this.field_146294_l - 10, 23);
                this.panelSearchBox.setVisible(true);
                this.panelHome.setPosition(5, yOffset += 28).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelHome.setVisible(true);
                break;
            }
            case SEARCH: {
                this.panelSearchBox.setPosition(5, yOffset).setSize(this.field_146294_l - 10, 23);
                this.panelSearchBox.setVisible(true);
                this.panelSearchResults.setPosition(5, yOffset += 28).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelSearchResults.setVisible(true);
                break;
            }
            case SKIN_INFO: {
                this.panelSearchBox.setPosition(5, yOffset).setSize(this.field_146294_l - 10, 23);
                this.panelSearchBox.setVisible(true);
                this.panelSkinInfo.setPosition(5, yOffset += 28).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelSkinInfo.setVisible(true);
                break;
            }
            case UPLOAD: {
                this.panelUpload.setPosition(5, yOffset).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelUpload.setVisible(true);
                this.setSlotVisibility(true);
                break;
            }
            case JOIN_BETA: {
                this.panelJoinBeta.setPosition(5, yOffset).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelJoinBeta.setVisible(true);
                break;
            }
            case USER_SKINS: {
                this.panelSearchBox.setPosition(5, yOffset).setSize(this.field_146294_l - 10, 23);
                this.panelSearchBox.setVisible(true);
                this.panelUserSkins.setPosition(5, yOffset += 28).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelUserSkins.setVisible(true);
                break;
            }
            case SKIN_EDIT: {
                this.panelSearchBox.setPosition(5, yOffset).setSize(this.field_146294_l - 10, 23);
                this.panelSearchBox.setVisible(true);
                this.panelSkinEdit.setPosition(5, yOffset += 28).setSize(this.field_146294_l - 10, this.field_146295_m - yOffset - 5 - neiBump);
                this.panelSkinEdit.setVisible(true);
                break;
            }
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        PlushieAuth.taskCheck();
        for (int i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).update();
        }
    }

    public void switchScreen(Screen screen) {
        this.screen = screen;
        this.setupPanels();
        for (int i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).initGui();
        }
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int button) {
        if (!this.isDialogOpen()) {
            for (int i = 0; i < this.panelList.size() && !this.panelList.get(i).mouseClicked(mouseX, mouseY, button); ++i) {
            }
        }
        super.func_73864_a(mouseX, mouseY, button);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int button) {
        if (!this.isDialogOpen()) {
            for (int i = 0; i < this.panelList.size(); ++i) {
                this.panelList.get(i).mouseMovedOrUp(mouseX, mouseY, button);
            }
        }
        super.func_146286_b(mouseX, mouseY, button);
    }

    @Override
    protected void func_73869_a(char c, int keycode) {
        boolean keyTyped = false;
        if (!this.isDialogOpen()) {
            for (int i = 0; i < this.panelList.size(); ++i) {
                if (!this.panelList.get(i).keyTyped(c, keycode)) continue;
                keyTyped = true;
            }
        }
        if (!keyTyped) {
            super.func_73869_a(c, keycode);
        }
        this.checkNEIVisibility();
    }

    private void checkNEIVisibility() {
        if (this.isNEIVisible != ModAddonManager.addonNEI.isVisible()) {
            this.isNEIVisible = !this.isNEIVisible;
            this.func_73866_w_();
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTickTime) {
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
        if (this.isDialogOpen()) {
            mouseY = 0;
            mouseX = 0;
        }
        super.func_73863_a(mouseX, mouseY, partialTickTime);
    }

    protected void func_146976_a(float partialTickTime, int mouseX, int mouseY) {
        int i;
        for (i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).draw(mouseX, mouseY, partialTickTime);
        }
        for (i = 0; i < this.panelList.size(); ++i) {
            this.panelList.get(i).drawForeground(mouseX, mouseY, partialTickTime);
        }
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        if (this.isDialogOpen()) {
            GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
            this.dialog.draw(this.oldMouseX, this.oldMouseY, 0.0f);
            GL11.glTranslatef((float)this.field_147003_i, (float)this.field_147009_r, (float)0.0f);
        }
    }

    public String getGuiName() {
        return "globalSkinLibrary";
    }

    public void gotSkinFromServer(Skin skin) {
        this.panelUpload.uploadSkin(skin);
    }

    public static enum Screen {
        HOME,
        SEARCH,
        UPLOAD,
        SKIN_INFO,
        USER_SKINS,
        FAVOURITES,
        JOIN_BETA,
        SKIN_EDIT;

    }
}

