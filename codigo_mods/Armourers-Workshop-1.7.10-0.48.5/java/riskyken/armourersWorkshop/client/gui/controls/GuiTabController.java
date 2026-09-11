/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiTab;

@SideOnly(value=Side.CLIENT)
public class GuiTabController
extends GuiButtonExt {
    private final ResourceLocation texture;
    private GuiScreen parent;
    private boolean fullscreen;
    private int activeTab = -1;
    private ArrayList<GuiTab> tabs = new ArrayList();
    private int tabSpacing = 27;

    public GuiTabController(GuiScreen parent, boolean fullscreen, int xPos, int yPos, int width, int height, ResourceLocation texture) {
        super(0, xPos, yPos, width, height, "");
        this.parent = parent;
        this.fullscreen = fullscreen;
        this.texture = texture;
        if (!fullscreen) {
            this.tabSpacing = 25;
        }
    }

    public GuiTabController(GuiScreen parent, boolean fullscreen, ResourceLocation texture) {
        this(parent, fullscreen, 0, 0, 0, 0, texture);
    }

    public void setTabSpacing(int tabSpacing) {
        this.tabSpacing = tabSpacing;
    }

    public void initGui(int xPos, int yPos, int width, int height) {
        if (this.fullscreen) {
            this.field_146128_h = 0;
            this.field_146129_i = 0;
            this.field_146120_f = this.parent.field_146294_l;
            this.field_146121_g = this.parent.field_146295_m;
        } else {
            this.field_146128_h = xPos;
            this.field_146129_i = yPos;
            this.field_146120_f = width;
            this.field_146121_g = height;
        }
    }

    public void setActiveTabIndex(int index) {
        this.activeTab = index < this.getTabCount() - 1 ? index : this.getTabCount() - 1;
        if (this.getTabCount() == 0) {
            this.activeTab = -1;
        }
    }

    public void addTab(GuiTab tab) {
        this.tabs.add(tab);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getActiveTabIndex() {
        return this.activeTab;
    }

    public String getActiveTabName() {
        GuiTab tab = this.getActiveTab();
        if (tab != null) {
            return tab.getName();
        }
        return "";
    }

    public GuiTab getTab(int index) {
        if (index >= 0 & index < this.tabs.size()) {
            return this.tabs.get(index);
        }
        return null;
    }

    public GuiTab getActiveTab() {
        if (this.activeTab >= 0 & this.activeTab < this.tabs.size()) {
            return this.tabs.get(this.activeTab);
        }
        return null;
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        int yOffset = (int)((float)this.field_146121_g / 2.0f - (float)this.tabs.size() * (float)this.tabSpacing / 2.0f);
        if (!this.fullscreen) {
            yOffset = 5;
        }
        int count = 0;
        for (int i = 0; i < this.tabs.size(); ++i) {
            GuiTab tab = this.tabs.get(i);
            if (!tab.visible) continue;
            if (tab.isMouseOver(this.field_146128_h - 4, this.field_146129_i + count * this.tabSpacing + yOffset, mouseX, mouseY) && tab.enabled) {
                this.activeTab = i;
                return true;
            }
            ++count;
        }
        return false;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        mc.field_71446_o.func_110577_a(this.texture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int yOffset = (int)((float)this.field_146121_g / 2.0f - (float)this.tabs.size() * (float)this.tabSpacing / 2.0f);
        if (!this.fullscreen) {
            yOffset = 5;
        }
        int count = 0;
        for (int i = 0; i < this.tabs.size(); ++i) {
            GuiTab tab = this.tabs.get(i);
            if (!tab.visible) continue;
            tab.render(this.field_146128_h - 4, this.field_146129_i + count * this.tabSpacing + yOffset, mouseX, mouseY, this.activeTab == i);
            ++count;
        }
    }

    public void drawHoverText(Minecraft mc, int mouseX, int mouseY) {
        int yOffset = (int)((float)this.field_146121_g / 2.0f - (float)this.tabs.size() * (float)this.tabSpacing / 2.0f);
        if (!this.fullscreen) {
            yOffset = 5;
        }
        GuiTab hoverTab = null;
        int count = 0;
        for (int i = 0; i < this.tabs.size(); ++i) {
            GuiTab tab = this.tabs.get(i);
            if (!tab.visible) continue;
            if (tab.isMouseOver(this.field_146128_h - 4, this.field_146129_i + count * this.tabSpacing + yOffset, mouseX, mouseY)) {
                hoverTab = tab;
            }
            ++count;
        }
        if (hoverTab != null) {
            ArrayList<String> textList = new ArrayList<String>();
            textList.add(hoverTab.getName());
            GuiHelper.drawHoveringText(textList, mouseX, mouseY, mc.field_71466_p, this.parent.field_146294_l, this.parent.field_146295_m, this.field_73735_i);
        }
    }
}

