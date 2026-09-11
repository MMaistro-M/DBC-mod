/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package invtweaks;

import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiTooltipButton;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class InvTweaksGuiIconButton
extends InvTweaksGuiTooltipButton {
    protected InvTweaksConfigManager cfgManager;
    private boolean useCustomTexture;
    private static ResourceLocation resourceButtonCustom = new ResourceLocation("inventorytweaks", "textures/gui/button10px.png");
    private static ResourceLocation resourceButtonDefault = new ResourceLocation("textures/gui/widgets.png");

    public InvTweaksGuiIconButton(InvTweaksConfigManager cfgManager, int id, int x, int y, int w, int h, String displayString, String tooltip, boolean useCustomTexture) {
        super(id, x, y, w, h, displayString, tooltip);
        this.cfgManager = cfgManager;
        this.useCustomTexture = useCustomTexture;
    }

    @Override
    public void func_146112_a(Minecraft minecraft, int i, int j) {
        super.func_146112_a(minecraft, i, j);
        int k = this.func_146114_a(this.isMouseOverButton(i, j));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.useCustomTexture) {
            minecraft.func_110434_K().func_110577_a(resourceButtonCustom);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, (k - 1) * 10, 0, this.field_146120_f, this.field_146121_g);
        } else {
            minecraft.func_110434_K().func_110577_a(resourceButtonDefault);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 1, 46 + k * 20 + 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h, this.field_146129_i + this.field_146121_g / 2, 1, 46 + k * 20 + 20 - this.field_146121_g / 2 - 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2 - 1, 46 + k * 20 + 1, this.field_146120_f / 2, this.field_146121_g / 2);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + this.field_146121_g / 2, 200 - this.field_146120_f / 2 - 1, 46 + k * 20 + 19 - this.field_146121_g / 2, this.field_146120_f / 2, this.field_146121_g / 2);
        }
    }
}

