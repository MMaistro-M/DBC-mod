/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.util.Point
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksObfuscation;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Point;

public abstract class InvTweaksGuiSettingsAbstract
extends GuiScreen {
    protected static final Logger log = InvTweaks.log;
    protected static String ON;
    protected static String OFF;
    protected static String DISABLE_CI;
    protected Minecraft field_146297_k;
    protected InvTweaksObfuscation obf;
    protected InvTweaksConfig config;
    protected GuiScreen parentScreen;
    protected static String LABEL_DONE;
    protected static final int ID_DONE = 200;

    public InvTweaksGuiSettingsAbstract(Minecraft mc, GuiScreen parentScreen, InvTweaksConfig config) {
        LABEL_DONE = StatCollector.func_74838_a((String)"invtweaks.settings.exit");
        ON = ": " + StatCollector.func_74838_a((String)"invtweaks.settings.on");
        OFF = ": " + StatCollector.func_74838_a((String)"invtweaks.settings.off");
        DISABLE_CI = ": " + StatCollector.func_74838_a((String)"invtweaks.settings.disableci");
        this.field_146297_k = mc;
        this.obf = new InvTweaksObfuscation(mc);
        this.parentScreen = parentScreen;
        this.config = config;
    }

    public void func_73866_w_() {
        List controlList = this.field_146292_n;
        Point p = new Point();
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(200, p.getX() + 55, this.field_146295_m / 6 + 168, LABEL_DONE));
        this.field_146292_n = controlList;
    }

    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.settings.title"), this.field_146294_l / 2, 20, 0xFFFFFF);
        super.func_73863_a(i, j, f);
    }

    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 200) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    protected void func_73869_a(char c, int keyCode) {
        if (keyCode == 1) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    protected void moveToButtonCoords(int buttonOrder, Point p) {
        p.setX(this.field_146294_l / 2 - 155 + (buttonOrder + 1) % 2 * 160);
        p.setY(this.field_146295_m / 6 + buttonOrder / 2 * 24);
    }

    protected void toggleBooleanButton(GuiButton guibutton, String property, String label) {
        Boolean enabled = Boolean.valueOf(this.config.getProperty(property)) == false;
        this.config.setProperty(property, enabled.toString());
        guibutton.field_146126_j = this.computeBooleanButtonLabel(property, label);
    }

    protected String computeBooleanButtonLabel(String property, String label) {
        String propertyValue = this.config.getProperty(property);
        if (propertyValue.equals("convenientInventoryCompatibility")) {
            return label + DISABLE_CI;
        }
        Boolean enabled = Boolean.valueOf(propertyValue);
        return label + (enabled != false ? ON : OFF);
    }
}

