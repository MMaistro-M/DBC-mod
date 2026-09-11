/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInventorySize
extends GuiButtonExt {
    private static final int BUTTON_WIDTH = 10;
    private static final int BUTTON_HEIGHT = 10;
    public final int maxWidth;
    public final int maxHeight;
    private int selectionWidth = 1;
    private int selectionHeight = 1;
    private ResourceLocation texture;
    private int srcX = 0;
    private int srcY = 0;

    public GuiInventorySize(int id, int xPos, int yPos, int maxWidth, int maxHeight) {
        super(id, xPos, yPos, maxWidth * 10, maxHeight * 10, "");
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void setSrc(ResourceLocation texture, int srcX, int srcY) {
        this.texture = texture;
        this.srcX = srcX;
        this.srcY = srcY;
    }

    public void setSelection(int selectionWidth, int selectionHeight) {
        this.selectionWidth = MathHelper.func_76125_a((int)selectionWidth, (int)1, (int)this.maxWidth);
        this.selectionHeight = MathHelper.func_76125_a((int)selectionHeight, (int)1, (int)this.maxHeight);
    }

    public int getSelectionWidth() {
        return this.selectionWidth;
    }

    public int getSelectionHeight() {
        return this.selectionHeight;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        int hoverWidth = MathHelper.func_76123_f((float)((float)(mouseX - this.field_146128_h) / 10.0f));
        int hoverHeight = MathHelper.func_76123_f((float)((float)(mouseY - this.field_146129_i) / 10.0f));
        if (this.field_146125_m) {
            mc.field_71446_o.func_110577_a(this.texture);
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int k = this.func_146114_a(this.field_146123_n);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            for (int ix = 0; ix < this.maxWidth; ++ix) {
                for (int iy = 0; iy < this.maxHeight; ++iy) {
                    int srcX = this.srcX;
                    int srcY = this.srcY;
                    if (ix < this.selectionWidth & iy < this.selectionHeight) {
                        srcX += 10;
                    }
                    if (ix < hoverWidth & iy < hoverHeight & k == 2) {
                        srcY += 10;
                    }
                    this.func_73729_b(this.field_146128_h + 10 * ix, this.field_146129_i + 10 * iy, srcX, srcY, 10, 10);
                }
            }
        }
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        if (super.func_146116_c(mc, mouseX, mouseY)) {
            int selectionWidth = MathHelper.func_76123_f((float)((float)(mouseX - this.field_146128_h) / 10.0f));
            int selectionHeight = MathHelper.func_76123_f((float)((float)(mouseY - this.field_146129_i) / 10.0f));
            this.setSelection(selectionWidth, selectionHeight);
            return true;
        }
        return false;
    }
}

