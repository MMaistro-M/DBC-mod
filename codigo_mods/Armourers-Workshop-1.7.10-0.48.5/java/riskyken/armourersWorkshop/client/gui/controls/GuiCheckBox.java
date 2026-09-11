/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiCheckBox
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class GuiCheckBox
extends cpw.mods.fml.client.config.GuiCheckBox {
    private int boxWidth = 9;
    private int textColour;

    public GuiCheckBox(int id, int x, int y, String text, boolean checked) {
        super(id, x, y, text, checked);
        this.field_146121_g = 9;
        this.textColour = 0x404040;
    }

    public GuiCheckBox setTextColour(int textColour) {
        this.textColour = textColour;
        return this;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.boxWidth && mouseY < this.field_146129_i + this.field_146121_g;
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)46, (int)this.boxWidth, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            this.func_146119_b(mc, mouseX, mouseY);
            int color = this.textColour;
            if (this.packedFGColour != 0) {
                color = this.packedFGColour;
            } else if (!this.field_146124_l) {
                color = 0xA0A0A0;
            }
            if (this.isChecked()) {
                this.func_73732_a(mc.field_71466_p, "x", this.field_146128_h + this.boxWidth / 2 + 1, this.field_146129_i, -3355444);
            }
            mc.field_71466_p.func_85187_a(this.field_146126_j, this.field_146128_h + this.boxWidth + 2, this.field_146129_i + 1, color, false);
        }
    }
}

