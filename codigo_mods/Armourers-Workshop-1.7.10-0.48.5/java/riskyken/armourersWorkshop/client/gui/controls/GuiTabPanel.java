/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(value=Side.CLIENT)
public abstract class GuiTabPanel
extends Gui {
    private final int tabId;
    protected final GuiScreen parent;
    protected final FontRenderer fontRenderer;
    protected final Minecraft mc;
    protected ArrayList<GuiButton> buttonList;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private final boolean fullscreen;
    private GuiButton selectedButton;

    public GuiTabPanel(int tabId, GuiScreen parent, boolean fullscreen) {
        this.tabId = tabId;
        this.parent = parent;
        this.mc = Minecraft.func_71410_x();
        this.fontRenderer = this.mc.field_71466_p;
        this.fullscreen = fullscreen;
        this.buttonList = new ArrayList();
    }

    public void initGui(int xPos, int yPos, int width, int height) {
        this.buttonList.clear();
        if (this.fullscreen) {
            this.x = 0;
            this.y = 0;
            this.width = this.parent.field_146294_l;
            this.height = this.parent.field_146295_m;
        } else {
            this.x = xPos;
            this.y = yPos;
            this.width = width;
            this.height = height;
        }
    }

    public int getTabId() {
        return this.tabId;
    }

    public void tabChanged(int tabIndex) {
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0) {
            for (int i = 0; i < this.buttonList.size(); ++i) {
                GuiButton guiButton = this.buttonList.get(i);
                if (!guiButton.func_146116_c(this.mc, mouseX - this.x, mouseY - this.y)) continue;
                GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this.parent, guiButton, this.buttonList);
                if (MinecraftForge.EVENT_BUS.post((Event)event)) break;
                this.selectedButton = event.button;
                event.button.func_146113_a(this.mc.func_147118_V());
                this.actionPerformed(event.button);
                if (!this.parent.equals(this.mc.field_71462_r)) continue;
                MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post(this.parent, event.button, this.buttonList));
            }
        }
    }

    protected void actionPerformed(GuiButton button) {
    }

    public void mouseMovedOrUp(int mouseX, int mouseY, int button) {
        if (this.selectedButton != null && button == 0) {
            this.selectedButton.func_146118_a(mouseX - this.x, mouseY - this.y);
            this.selectedButton = null;
        }
    }

    public void drawForegroundLayer(int mouseX, int mouseY) {
        for (int i = 0; i < this.buttonList.size(); ++i) {
            this.buttonList.get(i).func_146112_a(this.mc, mouseX - this.x, mouseY - this.y);
        }
    }

    public boolean keyTyped(char c, int keycode) {
        return false;
    }

    public abstract void drawBackgroundLayer(float var1, int var2, int var3);
}

