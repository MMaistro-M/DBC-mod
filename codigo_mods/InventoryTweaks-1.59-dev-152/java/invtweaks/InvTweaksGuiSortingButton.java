/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksGuiIconButton;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import net.minecraft.client.Minecraft;

public class InvTweaksGuiSortingButton
extends InvTweaksGuiIconButton {
    private final ContainerSection section = ContainerSection.CHEST;
    private SortingMethod algorithm;
    private int rowSize;

    public InvTweaksGuiSortingButton(InvTweaksConfigManager cfgManager, int id, int x, int y, int w, int h, String displayString, String tooltip, SortingMethod algorithm, int rowSize, boolean useCustomTexture) {
        super(cfgManager, id, x, y, w, h, displayString, tooltip, useCustomTexture);
        this.algorithm = algorithm;
        this.rowSize = rowSize;
    }

    @Override
    public void func_146112_a(Minecraft minecraft, int i, int j) {
        super.func_146112_a(minecraft, i, j);
        int textColor = this.getTextColor(i, j);
        if (this.field_146126_j.equals("h")) {
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 4), (int)textColor);
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 6), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 7), (int)textColor);
        } else if (this.field_146126_j.equals("v")) {
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + 4), (int)(this.field_146129_i + this.field_146121_g - 3), (int)textColor);
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 6), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + 7), (int)(this.field_146129_i + this.field_146121_g - 3), (int)textColor);
        } else {
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 3), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 4), (int)textColor);
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 5), (int)(this.field_146129_i + 4), (int)(this.field_146128_h + 6), (int)(this.field_146129_i + 5), (int)textColor);
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 4), (int)(this.field_146129_i + 5), (int)(this.field_146128_h + 5), (int)(this.field_146129_i + 6), (int)textColor);
            InvTweaksGuiSortingButton.func_73734_a((int)(this.field_146128_h + 3), (int)(this.field_146129_i + 6), (int)(this.field_146128_h + this.field_146120_f - 3), (int)(this.field_146129_i + 7), (int)textColor);
        }
    }

    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        if (super.func_146116_c(minecraft, i, j)) {
            try {
                new InvTweaksHandlerSorting(minecraft, this.cfgManager.getConfig(), this.section, this.algorithm, this.rowSize).sort();
            }
            catch (Exception e) {
                InvTweaks.logInGameErrorStatic("invtweaks.sort.chest.error", e);
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}

