/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiHSBSlider
extends GuiSlider {
    private static final ResourceLocation sliderTexture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/controls/sliderHue.png");
    private HSBSliderType type;
    private IHSBSliderCallback callback = null;
    private float hueValue;
    private float briValue;

    public GuiHSBSlider(int id, int xPos, int yPos, int width, int height, IHSBSliderCallback callback, HSBSliderType type, float curValue, float hueValue, float brightnessValue) {
        super(id, xPos, yPos, width, height, "", "", 0.0, 1531.0, 0.0, false, false);
        this.type = type;
        this.hueValue = hueValue;
        this.briValue = brightnessValue;
        this.setValue(curValue);
        this.callback = callback;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            this.mouseCheck();
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int k = this.func_146114_a(this.field_146123_n);
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)46, (int)this.field_146120_f, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            mc.field_71446_o.func_110577_a(sliderTexture);
            if (this.type == HSBSliderType.SATURATION) {
                Color hueColour = Color.getHSBColor(this.hueValue, 1.0f, 1.0f);
                float red = (float)hueColour.getRed() / 255.0f;
                float green = (float)hueColour.getGreen() / 255.0f;
                float blue = (float)hueColour.getBlue() / 255.0f;
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
                this.drawTexturedModalRectScaled(this.field_146128_h + 1, this.field_146129_i + 1, 0, 176, 256, 20, this.field_146120_f - 2, this.field_146121_g - 2);
            }
            int srcY = 236;
            if (this.type == HSBSliderType.BRIGHTNESS) {
                srcY -= 20;
            }
            if (this.type == HSBSliderType.SATURATION) {
                srcY -= 40;
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (this.type == HSBSliderType.SATURATION) {
                Color hueColour = Color.getHSBColor(0.0f, 0.0f, this.briValue);
                float red = (float)hueColour.getRed() / 255.0f;
                float green = (float)hueColour.getGreen() / 255.0f;
                float blue = (float)hueColour.getBlue() / 255.0f;
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
                this.drawTexturedModalRectScaled(this.field_146128_h + 1, this.field_146129_i + 1, 0, srcY, 231, 20, this.field_146120_f - 2, this.field_146121_g - 2);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                this.drawTexturedModalRectScaled(this.field_146128_h + 1, this.field_146129_i + 1, 0, srcY, 256, 20, this.field_146120_f - 2, this.field_146121_g - 2);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_146119_b(mc, mouseX, mouseY);
        }
    }

    private void mouseCheck() {
        if (Loader.isModLoaded((String)"TooManyItems") && Mouse.isCreated() && this.dragging && !Mouse.isButtonDown((int)0)) {
            this.dragging = false;
        }
    }

    protected void func_146119_b(Minecraft mc, int par2, int par3) {
        if (this.field_146125_m) {
            if (this.dragging) {
                this.sliderValue = (float)(par2 - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);
                this.updateSlider();
                if (this.callback != null) {
                    this.callback.valueUpdated(this, this.sliderValue);
                }
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ScaledResolution screenRes = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
            double scaleWidth = (double)mc.field_71443_c / screenRes.func_78327_c();
            double scaleHeight = (double)mc.field_71440_d / screenRes.func_78324_d();
            GL11.glEnable((int)3089);
            GL11.glScissor((int)((int)((double)(this.field_146128_h + 1) * scaleWidth)), (int)(mc.field_71440_d - (int)((double)(this.field_146129_i + this.field_146121_g - 1) * scaleHeight)), (int)((int)((double)(this.field_146120_f - 2) * scaleWidth)), (int)((int)((double)(this.field_146121_g - 2) * scaleHeight)));
            this.func_73729_b(this.field_146128_h + (int)(this.sliderValue * (double)(this.field_146120_f - 3) - 2.0), this.field_146129_i, 0, 0, 7, 4);
            this.func_73729_b(this.field_146128_h + (int)(this.sliderValue * (double)(this.field_146120_f - 3) - 2.0), this.field_146129_i + this.field_146121_g - 4, 7, 0, 7, 4);
            GL11.glDisable((int)3089);
        }
    }

    public HSBSliderType getType() {
        return this.type;
    }

    public double getValue() {
        return this.sliderValue;
    }

    public void setValue(double value) {
        this.sliderValue = value;
        this.updateSlider();
        if (this.callback != null) {
            this.callback.valueUpdated(this, this.sliderValue);
        }
    }

    public void setHue(float value) {
        this.hueValue = value;
    }

    public void setBrightness(float value) {
        this.briValue = value;
    }

    public void drawTexturedModalRectScaled(int x, int y, int u, int v, int srcWidth, int srcHeight, int tarWidth, int tarHeight) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(x + 0), (double)(y + tarHeight), (double)this.field_73735_i, (double)((float)(u + 0) * f), (double)((float)(v + srcHeight) * f1));
        tessellator.func_78374_a((double)(x + tarWidth), (double)(y + tarHeight), (double)this.field_73735_i, (double)((float)(u + srcWidth) * f), (double)((float)(v + srcHeight) * f1));
        tessellator.func_78374_a((double)(x + tarWidth), (double)(y + 0), (double)this.field_73735_i, (double)((float)(u + srcWidth) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78374_a((double)(x + 0), (double)(y + 0), (double)this.field_73735_i, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78381_a();
    }

    public static interface IHSBSliderCallback {
        public void valueUpdated(GuiHSBSlider var1, double var2);
    }

    public static enum HSBSliderType {
        HUE,
        SATURATION,
        BRIGHTNESS;

    }
}

