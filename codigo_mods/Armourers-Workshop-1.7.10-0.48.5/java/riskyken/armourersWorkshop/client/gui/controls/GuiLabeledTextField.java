/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiTextField
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

@SideOnly(value=Side.CLIENT)
public class GuiLabeledTextField
extends GuiTextField {
    private final FontRenderer fontRenderer;
    private String emptyLabel = "";

    public GuiLabeledTextField(FontRenderer fontRenderer, int x, int y, int width, int height) {
        super(fontRenderer, x, y, width, height);
        this.fontRenderer = fontRenderer;
    }

    public void setEmptyLabel(String emptyLabel) {
        this.emptyLabel = emptyLabel;
    }

    public void func_146194_f() {
        String s;
        super.func_146194_f();
        if (this.func_146176_q() && this.func_146179_b().trim().isEmpty() && (s = this.fontRenderer.func_78269_a(this.emptyLabel, this.func_146200_o())).length() > 0 & !this.func_146206_l()) {
            int lX = this.func_146181_i() ? this.field_146209_f + 4 : this.field_146209_f;
            int lY = this.func_146181_i() ? this.field_146210_g + (this.field_146219_i - 8) / 2 : this.field_146210_g;
            this.fontRenderer.func_78261_a(this.emptyLabel, lX, lY, -8421505);
        }
    }
}

