/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.Gui
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;

@SideOnly(value=Side.CLIENT)
public class GuiTab
extends Gui {
    private final String name;
    public boolean enabled;
    public boolean visible;
    private int iconTextureX = 0;
    private int iconTextureY = 0;
    private int iconTextureWidth = 16;
    private int iconTextureHeight = 16;
    private int tabTextureWidth = 26;
    private int tabTextureHeight = 26;
    private int padLeft;
    private int padRight;
    private int padTop;
    private int padBottom = 0;
    private int animationFrames = 0;
    private int animationSpeed = 0;

    public GuiTab(String name) {
        this.name = name;
        this.enabled = true;
        this.visible = true;
    }

    public String getName() {
        return this.name;
    }

    public void render(int x, int y, int mouseX, int mouseY, boolean activeTab) {
        int textureOffsetX = 0;
        int textureOffsetY = this.tabTextureHeight;
        if (this.isMouseOver(x, y, mouseX, mouseY)) {
            textureOffsetX += this.tabTextureWidth;
        }
        if (!this.enabled) {
            textureOffsetX = this.tabTextureWidth * 2;
        }
        if (activeTab) {
            textureOffsetY = 0;
        }
        this.func_73729_b(x, y, textureOffsetX, textureOffsetY, this.tabTextureWidth, this.tabTextureHeight);
        this.renderIcon(x, y, mouseX, mouseY);
    }

    public GuiTab setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public GuiTab setVisable(boolean visible) {
        this.visible = visible;
        return this;
    }

    public GuiTab setIconLocation(int x, int y) {
        this.iconTextureX = x;
        this.iconTextureY = y;
        return this;
    }

    public GuiTab setIconSize(int width, int height) {
        this.iconTextureWidth = width;
        this.iconTextureHeight = height;
        return this;
    }

    public GuiTab setTabTextureSize(int width, int height) {
        this.tabTextureWidth = width;
        this.tabTextureHeight = height;
        return this;
    }

    public GuiTab setPadding(int left, int right, int top, int bottom) {
        this.padLeft = left;
        this.padRight = right;
        this.padTop = top;
        this.padBottom = bottom;
        return this;
    }

    public GuiTab setAnimation(int frames, int speed) {
        this.animationFrames = frames;
        this.animationSpeed = speed;
        return this;
    }

    public boolean isMouseOver(int x, int y, int mouseX, int mouseY) {
        return mouseX >= x + this.padLeft & mouseX < x + this.tabTextureWidth - this.padRight && mouseY >= y + this.padTop & mouseY < y + this.tabTextureHeight - this.padBottom;
    }

    private void renderIcon(int x, int y, int mouseX, int mouseY) {
        int animationOffset = 0;
        if (this.isMouseOver(x, y, mouseX, mouseY) & this.animationFrames > 0) {
            int frame = (int)(System.currentTimeMillis() / (long)this.animationSpeed % (long)this.animationFrames);
            animationOffset += this.iconTextureHeight * frame;
        }
        this.func_73729_b(x + (int)((float)this.tabTextureWidth / 2.0f - (float)this.iconTextureWidth / 2.0f), y + (int)((float)this.tabTextureHeight / 2.0f - (float)this.iconTextureHeight / 2.0f), this.iconTextureX, this.iconTextureY + animationOffset, this.iconTextureWidth, this.iconTextureHeight);
    }
}

