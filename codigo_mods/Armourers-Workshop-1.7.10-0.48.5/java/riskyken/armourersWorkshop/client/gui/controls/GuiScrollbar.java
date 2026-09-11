/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiScrollbar
extends GuiButton {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/controls/scrollbar.png");
    public int sliderValue = 0;
    public final int sliderMaxValue;
    private boolean dragging;
    private boolean horizontal;
    private int xOffset = 0;
    private int yOffset = 0;

    public GuiScrollbar(int id, int x, int y, int width, int height, String text, boolean horizontal) {
        super(id, x, y, width, height, text);
        this.horizontal = horizontal;
        if (horizontal) {
            this.xOffset = 1;
            this.sliderMaxValue = width - 30;
        } else {
            this.yOffset = 1;
            this.sliderMaxValue = height - 30;
        }
    }

    public int func_146114_a(boolean par1) {
        return 0;
    }

    public void func_146112_a(Minecraft minecraft, int x, int y) {
        if (this.field_146125_m) {
            this.updateMouse();
            FontRenderer fontRendererObj = minecraft.field_71466_p;
            minecraft.func_110434_K().func_110577_a(texture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawArrows(minecraft, x, y);
            this.func_146119_b(minecraft, x, y);
        }
    }

    private void drawArrows(Minecraft minecraft, int x, int y) {
        int sourceX = 0;
        int sourceY = 0;
        sourceX = this.yOffset * 20;
        this.drawHover(this.field_146128_h, this.field_146129_i, sourceX, sourceY, 10, 10, x, y);
        this.drawHover(this.field_146128_h + (this.field_146120_f - 10) * this.xOffset, this.field_146129_i + (this.field_146121_g - 10) * this.yOffset, sourceX + 10, sourceY, 10, 10, x, y);
        this.func_73729_b(this.field_146128_h + 10 * this.xOffset, this.field_146129_i + 10 * this.yOffset, sourceX, 20, 10, 10);
        this.func_73729_b(this.field_146128_h + (this.field_146120_f - 20) * this.xOffset, this.field_146129_i + (this.field_146121_g - 20) * this.yOffset, sourceX + 10, 20, 10, 10);
        int gutterSize = this.sliderMaxValue - 30 + 10;
        this.func_73729_b(this.field_146128_h + 20 * this.xOffset, this.field_146129_i + 20 * this.yOffset, 246 * this.yOffset, 246 * this.xOffset, 10 + gutterSize * this.xOffset, 10 + gutterSize * this.yOffset);
        this.drawHover(this.field_146128_h + (this.sliderValue + 10) * this.xOffset, this.field_146129_i + (this.sliderValue + 10) * this.yOffset, 40, sourceY, 10, 10, x, y);
    }

    private void drawHover(int x, int y, int sourceX, int sourceY, int width, int height, int mouseX, int mouseY) {
        int hover = 0;
        if (this.isHovering(mouseX, mouseY, x, y, width, height)) {
            hover = 10;
        }
        this.func_73729_b(x, y, sourceX, sourceY + hover, width, height);
    }

    private boolean isHovering(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void func_146118_a(int par1, int par2) {
        this.dragging = false;
    }

    private void updateMouse() {
        if (Mouse.isCreated()) {
            int dWheel = Mouse.getDWheel();
            if (dWheel < 0) {
                this.setValue(this.sliderValue + 2);
            } else if (dWheel > 0) {
                this.setValue(this.sliderValue - 2);
            }
        }
    }

    public void setValue(int newValue) {
        this.sliderValue = newValue;
        if (this.sliderValue <= 0) {
            this.sliderValue = 0;
        }
        if (this.sliderValue > this.sliderMaxValue) {
            this.sliderValue = this.sliderMaxValue;
        }
    }

    public boolean func_146116_c(Minecraft par1Minecraft, int x, int y) {
        if (super.func_146116_c(par1Minecraft, x, y)) {
            if (!this.isHovering(x, y, this.field_146128_h, this.field_146129_i, 10, 10)) {
                if (!this.isHovering(x, y, this.field_146128_h + this.xOffset * (this.field_146120_f - 10), this.field_146129_i + this.yOffset * (this.field_146121_g - 10), 10, 10)) {
                    this.dragging = true;
                } else {
                    this.setValue(this.sliderValue + 4);
                }
            } else {
                this.setValue(this.sliderValue - 4);
            }
            return true;
        }
        return false;
    }

    protected void func_146119_b(Minecraft par1Minecraft, int x, int y) {
        if (this.dragging) {
            if (this.horizontal) {
                this.setValue(x - this.field_146128_h - 15);
            } else {
                this.setValue(y - this.field_146129_i - 15);
            }
        }
    }

    public int getValue() {
        return this.sliderValue;
    }

    public int getPercentageValue() {
        if (this.sliderValue == 0) {
            return 0;
        }
        return (int)((float)this.sliderValue / (float)this.sliderMaxValue * 100.0f);
    }
}

