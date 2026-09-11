/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiCustomSlider
extends GuiSlider {
    private boolean fineTuneButtons;
    private double lastValue;

    public GuiCustomSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, GuiSlider.ISlider par) {
        super(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, par);
        this.lastValue = this.sliderValue;
    }

    public GuiCustomSlider setFineTuneButtons(boolean fineTuneButtons) {
        this.fineTuneButtons = fineTuneButtons;
        return this;
    }

    protected void func_146119_b(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            if (this.dragging) {
                this.updateSliderLocation(mouseX, mouseY);
                if (this.lastValue != this.sliderValue) {
                    this.updateSlider();
                    this.lastValue = this.sliderValue;
                }
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (this.fineTuneButtons) {
                boolean overRight;
                boolean overLeft = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + 9 && mouseY < this.field_146129_i + this.field_146121_g;
                int k = 1;
                if (overLeft) {
                    k = 2;
                }
                GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)(46 + k * 20), (int)9, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
                k = 1;
                boolean bl = overRight = mouseX >= this.field_146128_h + this.field_146120_f - 9 && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
                if (overRight) {
                    k = 2;
                }
                GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)(this.field_146128_h + this.field_146120_f - 9), (int)this.field_146129_i, (int)0, (int)(46 + k * 20), (int)9, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            }
            int k = 1;
            if (this.field_146123_n) {
                k = 2;
            }
            if (this.fineTuneButtons) {
                GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)(this.field_146128_h + 10 + (int)(this.sliderValue * (double)(this.field_146120_f - 8 - 20))), (int)this.field_146129_i, (int)0, (int)(46 + k * 20), (int)8, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            } else {
                GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)(this.field_146128_h + (int)(this.sliderValue * (double)(this.field_146120_f - 8))), (int)this.field_146129_i, (int)0, (int)(46 + k * 20), (int)8, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            }
        }
    }

    public boolean func_146116_c(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.fineTuneButtons) {
            if (this.isPosInRec(this.field_146128_h, this.field_146129_i, 9, this.field_146121_g, mouseX, mouseY)) {
                this.setValue(this.getValue() - 1.0);
                this.updateSlider();
                return true;
            }
            if (this.isPosInRec(this.field_146128_h + this.field_146120_f - 9, this.field_146129_i, 9, this.field_146121_g, mouseX, mouseY)) {
                this.setValue(this.getValue() + 1.0);
                this.updateSlider();
                return true;
            }
            if (this.isPosInRec(this.field_146128_h + 9, this.field_146129_i, this.field_146120_f - 18, this.field_146121_g, mouseX, mouseY)) {
                this.updateSliderLocation(mouseX, mouseY);
                this.updateSlider();
                this.dragging = true;
                return true;
            }
        } else if (this.isPosInRec(this.field_146128_h, this.field_146129_i, this.field_146120_f, this.field_146121_g, mouseX, mouseY)) {
            this.updateSliderLocation(mouseX, mouseY);
            this.updateSlider();
            this.dragging = true;
            return true;
        }
        return false;
    }

    public void func_146112_a(Minecraft minecraft, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        if (this.fineTuneButtons) {
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)(this.field_146128_h + 10), (int)this.field_146129_i, (int)0, (int)(46 + k * 18), (int)(this.field_146120_f - 20), (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
        } else {
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)(46 + k * 20), (int)this.field_146120_f, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
        }
        this.func_146119_b(minecraft, mouseX, mouseY);
        int color = 0xE0E0E0;
        if (this.packedFGColour != 0) {
            color = this.packedFGColour;
        } else if (!this.field_146124_l) {
            color = 0xA0A0A0;
        } else if (this.field_146123_n) {
            color = 0xFFFFA0;
        }
        String buttonText = this.field_146126_j;
        int strWidth = minecraft.field_71466_p.func_78256_a(buttonText);
        int ellipsisWidth = minecraft.field_71466_p.func_78256_a("...");
        if (strWidth > this.field_146120_f - 6 && strWidth > ellipsisWidth) {
            buttonText = minecraft.field_71466_p.func_78269_a(buttonText, this.field_146120_f - 6 - ellipsisWidth).trim() + "...";
        }
        this.func_73732_a(minecraft.field_71466_p, buttonText, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, color);
    }

    private boolean isPosInRec(int recX, int recY, int recW, int recH, int posX, int posY) {
        return posX >= recX && posY >= recY && posX < recX + recW && posY < recY + recH;
    }

    private void updateSliderLocation(int mouseX, int mouseY) {
        this.sliderValue = this.fineTuneButtons ? (double)((float)(mouseX - (this.field_146128_h + 8 + 4)) / (float)(this.field_146120_f - 8 - 18)) : (double)((float)(mouseX - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8));
    }
}

