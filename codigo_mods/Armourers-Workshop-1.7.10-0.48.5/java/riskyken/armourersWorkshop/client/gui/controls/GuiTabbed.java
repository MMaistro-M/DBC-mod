/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabController;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;

@SideOnly(value=Side.CLIENT)
public abstract class GuiTabbed
extends GuiContainer {
    protected GuiTabController tabController;
    protected ArrayList<GuiTabPanel> tabList;
    protected static int activeTab = 0;

    public GuiTabbed(Container container, boolean fullscreen, ResourceLocation texture) {
        super(container);
        this.tabController = new GuiTabController((GuiScreen)this, fullscreen, texture);
        this.tabList = new ArrayList();
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.tabController.initGui(this.field_147003_i - 17, this.field_147009_r, this.field_146999_f, this.field_147000_g);
        this.tabController.setActiveTabIndex(activeTab);
        for (int i = 0; i < this.tabList.size(); ++i) {
            this.tabList.get(i).initGui(this.field_147003_i, this.field_147009_r, this.field_146999_f, this.field_147000_g);
        }
        this.field_146292_n.add(this.tabController);
        this.tabChanged();
    }

    protected void tabChanged() {
        activeTab = this.tabController.getActiveTabIndex();
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = this.tabList.get(i);
            tab.tabChanged(activeTab);
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int button) {
        super.func_73864_a(mouseX, mouseY, button);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = this.tabList.get(i);
            if (tab.getTabId() != activeTab) continue;
            tab.mouseClicked(mouseX, mouseY, button);
        }
    }

    protected void func_146286_b(int mouseX, int mouseY, int button) {
        super.func_146286_b(mouseX, mouseY, button);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = this.tabList.get(i);
            if (tab.getTabId() != activeTab) continue;
            tab.mouseMovedOrUp(mouseX, mouseY, button);
        }
    }

    protected void func_146284_a(GuiButton button) {
        if (button == this.tabController) {
            this.tabChanged();
        }
    }

    protected void func_73869_a(char c, int keycode) {
        boolean keyTyped = false;
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = this.tabList.get(i);
            if (tab.getTabId() != activeTab) continue;
            keyTyped = tab.keyTyped(c, keycode);
        }
        if (!keyTyped) {
            super.func_73869_a(c, keycode);
        }
    }
}

