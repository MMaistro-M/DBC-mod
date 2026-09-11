/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 */
package invtweaks;

import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksObfuscation;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class InvTweaksGuiShortcutsHelp
extends GuiScreen {
    private static final int ID_DONE = 0;
    private InvTweaksObfuscation obf;
    private GuiScreen parentScreen;
    private InvTweaksConfig config;

    public InvTweaksGuiShortcutsHelp(Minecraft mc, GuiScreen parentScreen, InvTweaksConfig config) {
        this.obf = new InvTweaksObfuscation(mc);
        this.parentScreen = parentScreen;
        this.config = config;
    }

    public void func_73866_w_() {
        LinkedList<GuiButton> controlList = new LinkedList<GuiButton>();
        controlList.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, "Done"));
        this.field_146292_n = controlList;
    }

    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        this.func_73732_a(this.obf.getFontRenderer(), "WARNING: Since 1.3.1, shortcuts won't work as expected. Looking for a workaround...", this.field_146294_l / 2, 5, 0xFF0000);
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.title"), this.field_146294_l / 2, 20, 0xFFFFFF);
        String clickLabel = StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.click");
        int y = this.field_146295_m / 6 - 2;
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.onestack"), "LSHIFT " + StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.or") + " RSHIFT + " + clickLabel, 0xFFFF00, y);
        this.drawShortcutLine("", this.buildUpOrDownLabel("shortcutKeyToUpperSection", this.obf.getKeyBindingForwardKeyCode(), StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.forward")) + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine("", this.buildUpOrDownLabel("shortcutKeyToLowerSection", this.obf.getKeyBindingBackKeyCode(), StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.backwards")) + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.oneitem"), this.config.getProperty("shortcutKeyOneItem") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.allitems"), this.config.getProperty("shortcutKeyAllItems") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.everything"), this.config.getProperty("shortcutKeyEverything") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.hotbar"), "0-9 + " + clickLabel, 65331, y += 19);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.emptyslot"), StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.rightclick"), 65331, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.drop"), this.config.getProperty("shortcutKeyDrop") + " + " + clickLabel, 65331, y += 12);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.craftall"), "LSHIFT, RSHIFT + " + clickLabel, 0xFF8800, y += 19);
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.craftone"), this.config.getProperty("shortcutKeyOneItem") + " + " + clickLabel, 0xFF8800, y += 12);
        String sortKeyName = this.getKeyName(this.config.getSortKeyCode(), "(Sort Key)");
        this.drawShortcutLine(StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.selectconfig"), "0-9 + " + sortKeyName, 0x88FFFF, y += 19);
        super.func_73863_a(i, j, f);
    }

    protected void func_146284_a(GuiButton guibutton) {
        switch (guibutton.field_146127_k) {
            case 0: {
                this.obf.displayGuiScreen(this.parentScreen);
            }
        }
    }

    protected void func_73869_a(char c, int keyCode) {
        if (keyCode == 1) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    private String buildUpOrDownLabel(String shortcutProp, int keyCode, String defaultKeyName) {
        String shortcutLabel = this.config.getProperty(shortcutProp);
        String keyLabel = this.getKeyName(keyCode, defaultKeyName);
        if (keyLabel.equals(shortcutLabel)) {
            return keyLabel;
        }
        return keyLabel + "/" + shortcutLabel;
    }

    protected String getKeyName(int keyCode, String defaultValue) {
        try {
            return Keyboard.getKeyName((int)keyCode);
        }
        catch (Exception e) {
            return defaultValue;
        }
    }

    private void drawShortcutLine(String label, String value, int color, int y) {
        this.func_73731_b(this.obf.getFontRenderer(), label, 30, y, -1);
        if (value != null) {
            this.func_73731_b(this.obf.getFontRenderer(), value.contains("DEFAULT") ? "-" : value.replaceAll(", ", " " + StatCollector.func_74838_a((String)"invtweaks.help.shortcuts.or") + " "), this.field_146294_l / 2 - 30, y, color);
        }
    }
}

