/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksGuiSettingsAbstract;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Logger;

public class InvTweaksGuiModNotWorking
extends InvTweaksGuiSettingsAbstract {
    private static final Logger log = InvTweaks.log;

    public InvTweaksGuiModNotWorking(Minecraft mc, GuiScreen parentScreen, InvTweaksConfig config) {
        super(mc, parentScreen, config);
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
        int x = this.field_146294_l / 2;
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.help.bugsorting.pt1"), x, 80, 0xBBBBBB);
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.help.bugsorting.pt2"), x, 95, 0xBBBBBB);
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.help.bugsorting.pt3"), x, 110, 0xBBBBBB);
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.help.bugsorting.pt4"), x, 150, 0xFFFF99);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        switch (guibutton.field_146127_k) {
            case 200: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettings(this.field_146297_k, this.parentScreen, this.config));
            }
        }
    }
}

