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
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;

@SideOnly(value=Side.CLIENT)
public abstract class GuiPanel
extends Gui {
    protected final GuiScreen parent;
    protected final Minecraft mc;
    protected final FontRenderer fontRenderer;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean enabled;
    protected boolean visible;
    protected ArrayList<GuiButton> buttonList;
    private GuiButton selectedButton;

    public GuiPanel(GuiScreen parent, int x, int y, int width, int height) {
        this.parent = parent;
        this.mc = Minecraft.func_71410_x();
        this.fontRenderer = this.mc.field_71466_p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enabled = true;
        this.visible = true;
        this.buttonList = new ArrayList();
    }

    public void initGui() {
    }

    public GuiPanel setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public GuiPanel setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public GuiPanel setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public GuiPanel setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.enabled | !this.visible) {
            return false;
        }
        if (button == 0) {
            for (int i = 0; i < this.buttonList.size(); ++i) {
                GuiButton guiButton = this.buttonList.get(i);
                if (!guiButton.func_146116_c(this.mc, mouseX, mouseY)) continue;
                GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this.parent, guiButton, this.buttonList);
                if (MinecraftForge.EVENT_BUS.post((Event)event)) break;
                this.selectedButton = event.button;
                event.button.func_146113_a(this.mc.func_147118_V());
                this.actionPerformed(event.button);
                if (this.parent.equals(this.mc.field_71462_r)) {
                    MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post(this.parent, event.button, this.buttonList));
                }
                return true;
            }
        }
        return false;
    }

    protected void actionPerformed(GuiButton button) {
    }

    public void mouseMovedOrUp(int mouseX, int mouseY, int button) {
        if (!this.enabled | !this.visible) {
            return;
        }
        if (this.selectedButton != null && button == 0) {
            this.selectedButton.func_146118_a(mouseX, mouseY);
            this.selectedButton = null;
        }
    }

    public boolean keyTyped(char c, int keycode) {
        if (!this.enabled | !this.visible) {
            return false;
        }
        return false;
    }

    protected void drawbuttons(int mouseX, int mouseY) {
        int i;
        for (i = 0; i < this.buttonList.size(); ++i) {
            this.buttonList.get(i).func_146112_a(this.mc, mouseX, mouseY);
        }
        for (i = 0; i < this.buttonList.size(); ++i) {
            if (!(this.buttonList.get(i) instanceof GuiIconButton)) continue;
            ((GuiIconButton)this.buttonList.get(i)).drawRollover(this.mc, mouseX, mouseY);
        }
    }

    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        this.drawbuttons(mouseX, mouseY);
    }

    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
    }

    public void update() {
    }
}

