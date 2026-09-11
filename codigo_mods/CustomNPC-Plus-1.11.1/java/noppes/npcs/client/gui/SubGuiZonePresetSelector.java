/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.SubGuiInterface;

@SideOnly(value=Side.CLIENT)
public class SubGuiZonePresetSelector
extends SubGuiInterface {
    public String selectedPreset = null;
    private static final String[] PRESET_NAMES = new String[]{"DEFAULT", "TOXIC", "INFERNO", "ARCANE", "ELECTRIC", "FROST"};
    private static final String[] PRESET_LABELS = new String[]{"ability.preset.default", "ability.preset.toxic", "ability.preset.inferno", "ability.preset.arcane", "ability.preset.electric", "ability.preset.frost"};
    private static final int[] PRESET_COLORS = new int[]{0xCCCCCC, 0x44DD44, 0xFF6611, 0xAA44FF, 0x4488FF, 0x88CCFF};

    public SubGuiZonePresetSelector() {
        this.xSize = 170;
        this.ySize = 135;
        this.setBackground("menubg.png");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int btnW = 72;
        int btnH = 20;
        int gapY = 4;
        int col1 = this.guiLeft + 7;
        int col2 = col1 + btnW + 6;
        int startY = this.guiTop + 22;
        for (int i = 0; i < 6; ++i) {
            int x = i % 2 == 0 ? col1 : col2;
            int y = startY + i / 2 * (btnH + gapY);
            this.addButton(new GuiNpcButton(i, x, y, btnW, btnH, PRESET_LABELS[i]));
        }
        int cancelY = startY + 3 * (btnH + gapY) + 4;
        this.addButton(new GuiNpcButton(10, this.guiLeft + this.xSize / 2 - 36, cancelY, 72, btnH, "gui.cancel"));
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        if (btn.field_146127_k >= 0 && btn.field_146127_k < 6) {
            this.selectedPreset = PRESET_NAMES[btn.field_146127_k];
            this.close();
        } else if (btn.field_146127_k == 10) {
            this.close();
        }
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float f) {
        super.func_73863_a(mouseX, mouseY, f);
        this.func_73732_a(this.field_146289_q, StatCollector.func_74838_a((String)"gui.applyPreset"), this.guiLeft + this.xSize / 2, this.guiTop + 8, 0xFFFFFF);
        for (int i = 0; i < 6; ++i) {
            GuiNpcButton btn = this.getButton(i);
            if (btn == null || !btn.field_146125_m) continue;
            int x = btn.field_146128_h + 4;
            int y = btn.field_146129_i + 5;
            SubGuiZonePresetSelector.func_73734_a((int)x, (int)y, (int)(x + 10), (int)(y + 10), (int)(0xFF000000 | PRESET_COLORS[i]));
        }
    }
}

