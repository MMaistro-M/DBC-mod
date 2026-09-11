/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class GuiColourSelector
extends GuiButtonExt {
    private Color selectedColour;
    private int colorWidth;
    private int colourHeight;
    private int rowLength;
    private ResourceLocation guiTexture;
    private UtilColour.ColourFamily colourFamily;

    public GuiColourSelector(int id, int xPos, int yPos, int width, int height, int colorWidth, int colourHeight, int rowLength, ResourceLocation guiTexture) {
        super(id, xPos, yPos, width, height, "");
        this.colorWidth = colorWidth;
        this.colourHeight = colourHeight;
        this.rowLength = rowLength;
        this.guiTexture = guiTexture;
        this.colourFamily = UtilColour.ColourFamily.MINECRAFT;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)46, (int)this.field_146120_f, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
        mc.field_71446_o.func_110577_a(this.guiTexture);
        for (int i = 0; i < 16; ++i) {
            int curRow = i / this.rowLength;
            Color c = new Color(UtilColour.getMinecraftColor(i, this.colourFamily));
            float red = (float)c.getRed() / 255.0f;
            float green = (float)c.getGreen() / 255.0f;
            float blue = (float)c.getBlue() / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
            int xPos = this.field_146128_h + 1 + this.colorWidth * i - curRow * this.colourHeight * this.rowLength;
            int yPos = this.field_146129_i + 1 + curRow * 10;
            this.func_73729_b(xPos, yPos, 146, 52, this.colorWidth, this.colourHeight);
            if (!(mouseX >= xPos & mouseY >= yPos & mouseX <= xPos + this.colorWidth & mouseY <= yPos + this.colourHeight)) continue;
            this.selectedColour = new Color(UtilColour.getMinecraftColor(i, this.colourFamily));
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_146119_b(mc, mouseX, mouseY);
    }

    public UtilColour.ColourFamily getColourFamily() {
        return this.colourFamily;
    }

    public void setColourFamily(UtilColour.ColourFamily colourFamily) {
        this.colourFamily = colourFamily;
    }

    public Color getSelectedColour() {
        return this.selectedColour;
    }
}

