/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.armourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.armourer.tab.GuiTabArmourerBlockUtils;
import riskyken.armourersWorkshop.client.gui.armourer.tab.GuiTabArmourerDisplaySettings;
import riskyken.armourersWorkshop.client.gui.armourer.tab.GuiTabArmourerMain;
import riskyken.armourersWorkshop.client.gui.armourer.tab.GuiTabArmourerSkinSettings;
import riskyken.armourersWorkshop.client.gui.controls.GuiTab;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabbed;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiArmourer
extends GuiTabbed
implements AbstractGuiDialog.IDialogCallback {
    private static final ResourceLocation texture = new ResourceLocation(LibGuiResources.ARMOURER);
    private static final ResourceLocation textureTabs = new ResourceLocation(LibGuiResources.ARMOURER_TABS);
    public final TileEntityArmourer tileEntity;
    private final String inventoryName;
    protected AbstractGuiDialog dialog;
    int oldMouseX;
    int oldMouseY;
    public GuiTabArmourerMain tabMain;
    public GuiTabArmourerDisplaySettings tabDisplaySettings;
    public GuiTabArmourerSkinSettings tabSkinSettings;
    public GuiTabArmourerBlockUtils tabBlockUtils;

    public GuiArmourer(InventoryPlayer invPlayer, TileEntityArmourer tileEntity) {
        super(new ContainerArmourer(invPlayer, tileEntity), false, textureTabs);
        this.tileEntity = tileEntity;
        this.inventoryName = tileEntity.func_145825_b();
        this.tabMain = new GuiTabArmourerMain(0, (GuiScreen)this);
        this.tabDisplaySettings = new GuiTabArmourerDisplaySettings(1, (GuiScreen)this);
        this.tabSkinSettings = new GuiTabArmourerSkinSettings(2, (GuiScreen)this);
        this.tabBlockUtils = new GuiTabArmourerBlockUtils(3, (GuiScreen)this);
        this.tabList.add(this.tabMain);
        this.tabList.add(this.tabDisplaySettings);
        this.tabList.add(this.tabSkinSettings);
        this.tabList.add(this.tabBlockUtils);
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.main")).setIconLocation(52, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.displaySettings")).setIconLocation(68, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.skinSettings")).setIconLocation(84, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.blockUtils")).setIconLocation(100, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3).setAnimation(8, 150));
        this.tabController.setActiveTabIndex(activeTab);
        this.tabChanged();
        this.skinTypeUpdate(tileEntity.getSkinType());
    }

    private void setSlotVisibility(boolean visible) {
        for (int i = 0; i < this.field_147002_h.field_75151_b.size(); ++i) {
            Object slot = this.field_147002_h.field_75151_b.get(i);
            if (slot == null || !(slot instanceof SlotHidable)) continue;
            ((SlotHidable)((Object)slot)).setVisible(visible);
        }
    }

    @Override
    protected void tabChanged() {
        super.tabChanged();
        this.setSlotVisibility(activeTab == 0);
    }

    @Override
    public void func_73866_w_() {
        this.field_146999_f = 176;
        this.field_147000_g = 224;
        super.func_73866_w_();
        if (this.isDialogOpen()) {
            this.dialog.initGui();
        }
        this.field_146292_n.clear();
        this.field_146292_n.add(this.tabController);
    }

    public void func_73876_c() {
        super.func_73876_c();
        if (this.tileEntity.loadedArmourItem) {
            this.tileEntity.loadedArmourItem = false;
            SkinProperties skinProperties = this.tileEntity.getSkinProps();
            this.tabMain.resetValues(skinProperties);
            this.tabSkinSettings.resetValues(skinProperties);
            this.skinTypeUpdate(this.tileEntity.getSkinType());
        }
    }

    public void skinTypeUpdate(ISkinType skinType) {
        if (skinType == SkinTypeRegistry.skinBow | skinType == SkinTypeRegistry.skinSword) {
            this.tabController.getTab(2).setVisable(false);
        } else {
            this.tabController.getTab(2).setVisable(true);
        }
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
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawBackgroundLayer(partialTickTime, mouseX, mouseY);
        }
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GL11.glDisable((int)2929);
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b());
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawForegroundLayer(mouseX, mouseY);
        }
        if (this.isDialogOpen()) {
            GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
            this.dialog.draw(this.oldMouseX, this.oldMouseY, 0.0f);
            GL11.glTranslatef((float)this.field_147003_i, (float)this.field_147009_r, (float)0.0f);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
        this.tabController.drawHoverText(this.field_146297_k, mouseX, mouseY);
        GL11.glPopMatrix();
    }

    @Override
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

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int button) {
        if (this.isDialogOpen()) {
            this.dialog.mouseMovedOrUp(mouseX, mouseY, button);
        } else {
            super.func_146286_b(mouseX, mouseY, button);
        }
    }

    @Override
    protected void func_73869_a(char c, int keycode) {
        if (this.isDialogOpen()) {
            this.dialog.keyTyped(c, keycode);
        } else {
            super.func_73869_a(c, keycode);
        }
    }

    public void openDialog(AbstractGuiDialog dialog) {
        this.dialog = dialog;
        dialog.initGui();
    }

    protected boolean isDialogOpen() {
        return this.dialog != null;
    }

    @Override
    public void dialogResult(AbstractGuiDialog dialog, AbstractGuiDialog.DialogResult result) {
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab || !(tab instanceof AbstractGuiDialog.IDialogCallback)) continue;
            ((AbstractGuiDialog.IDialogCallback)((Object)tab)).dialogResult(dialog, result);
        }
        this.dialog = null;
    }
}

