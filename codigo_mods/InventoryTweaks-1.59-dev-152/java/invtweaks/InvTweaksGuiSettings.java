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
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksGuiModNotWorking;
import invtweaks.InvTweaksGuiSettingsAbstract;
import invtweaks.InvTweaksGuiSettingsAdvanced;
import invtweaks.InvTweaksGuiShortcutsHelp;
import invtweaks.InvTweaksGuiTooltipButton;
import invtweaks.InvTweaksObfuscation;
import java.awt.Desktop;
import java.net.URL;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Point;

public class InvTweaksGuiSettings
extends InvTweaksGuiSettingsAbstract {
    private static final Logger log = InvTweaks.log;
    private static final int ID_MIDDLE_CLICK = 1;
    private static final int ID_BEFORE_BREAK = 2;
    private static final int ID_SHORTCUTS = 3;
    private static final int ID_SHORTCUTS_HELP = 4;
    private static final int ID_AUTO_REFILL = 5;
    private static final int ID_MORE_OPTIONS = 6;
    private static final int ID_BUG_SORTING = 7;
    private static final int ID_EDITRULES = 100;
    private static final int ID_EDITTREE = 101;
    private static final int ID_HELP = 102;
    private static String labelMiddleClick;
    private static String labelShortcuts;
    private static String labelAutoRefill;
    private static String labelAutoRefillBeforeBreak;
    private static String labelMoreOptions;
    private static String labelBugSorting;
    private InvTweaksGuiTooltipButton sortMappingButton;
    private boolean sortMappingEdition;

    public InvTweaksGuiSettings(GuiScreen parentScreen) {
        Minecraft minecraft = InvTweaks.getMinecraftInstance();
        InvTweaks.getInstance();
        this(minecraft, parentScreen, InvTweaks.getConfigManager().getConfig());
    }

    public InvTweaksGuiSettings(Minecraft mc, GuiScreen parentScreen, InvTweaksConfig config) {
        super(mc, parentScreen, config);
        this.sortMappingEdition = false;
        labelMiddleClick = StatCollector.func_74838_a((String)"invtweaks.settings.middleclick");
        labelShortcuts = StatCollector.func_74838_a((String)"invtweaks.settings.shortcuts");
        labelAutoRefill = StatCollector.func_74838_a((String)"invtweaks.settings.autorefill");
        labelAutoRefillBeforeBreak = StatCollector.func_74838_a((String)"invtweaks.settings.beforebreak");
        labelMoreOptions = StatCollector.func_74838_a((String)"invtweaks.settings.moreoptions");
        labelBugSorting = StatCollector.func_74838_a((String)"invtweaks.help.bugsorting");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        List controlList = this.field_146292_n;
        Point p = new Point();
        int i = 0;
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(100, p.getX() + 55, this.field_146295_m / 6 + 96, StatCollector.func_74838_a((String)"invtweaks.settings.rulesfile")));
        controlList.add(new GuiButton(101, p.getX() + 55, this.field_146295_m / 6 + 120, StatCollector.func_74838_a((String)"invtweaks.settings.treefile")));
        controlList.add(new GuiButton(102, p.getX() + 55, this.field_146295_m / 6 + 144, StatCollector.func_74838_a((String)"invtweaks.settings.onlinehelp")));
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(4, p.getX() + 130, p.getY(), 20, 20, "?", "Shortcuts help"));
        String shortcuts = this.config.getProperty("enableShortcuts");
        InvTweaksGuiTooltipButton shortcutsBtn = new InvTweaksGuiTooltipButton(3, p.getX(), p.getY(), 130, 20, this.computeBooleanButtonLabel("enableShortcuts", labelShortcuts), StatCollector.func_74838_a((String)"invtweaks.settings.shortcuts.tooltip"));
        controlList.add(shortcutsBtn);
        if (shortcuts.equals("convenientInventoryCompatibility")) {
            shortcutsBtn.field_146124_l = false;
            shortcutsBtn.setTooltip(shortcutsBtn.getTooltip() + "\n(" + StatCollector.func_74838_a((String)"invtweaks.settings.disableci.tooltip") + ")");
        }
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton beforeBreakBtn = new InvTweaksGuiTooltipButton(2, p.getX(), p.getY(), this.computeBooleanButtonLabel("autoRefillBeforeBreak", labelAutoRefillBeforeBreak), StatCollector.func_74838_a((String)"invtweaks.settings.beforebreak.tooltip"));
        controlList.add(beforeBreakBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton autoRefillBtn = new InvTweaksGuiTooltipButton(5, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableAutoRefill", labelAutoRefill), StatCollector.func_74838_a((String)"invtweaks.settings.autorefill.tooltip"));
        controlList.add(autoRefillBtn);
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(6, p.getX(), p.getY(), labelMoreOptions, StatCollector.func_74838_a((String)"invtweaks.settings.moreoptions.tooltip")));
        controlList.add(new InvTweaksGuiTooltipButton(7, 5, this.field_146295_m - 20, 100, 20, labelBugSorting, null, false));
        String middleClick = this.config.getProperty("enableMiddleClick");
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton middleClickBtn = new InvTweaksGuiTooltipButton(1, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableMiddleClick", labelMiddleClick), StatCollector.func_74838_a((String)"invtweaks.settings.middleclick.tooltip"));
        controlList.add(middleClickBtn);
        if (middleClick.equals("convenientInventoryCompatibility")) {
            middleClickBtn.field_146124_l = false;
            middleClickBtn.setTooltip(middleClickBtn.getTooltip() + "\n(" + StatCollector.func_74838_a((String)"invtweaks.settings.disableci.tooltip"));
        }
        if (!Desktop.isDesktopSupported()) {
            for (Object o : controlList) {
                if (!InvTweaksObfuscation.isGuiButton(o)) continue;
                GuiButton guiButton = (GuiButton)o;
                if (guiButton.field_146127_k < 100 || guiButton.field_146127_k > 102) continue;
                guiButton.field_146124_l = false;
            }
        }
        this.field_146292_n = controlList;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        switch (guibutton.field_146127_k) {
            case 1: {
                this.toggleBooleanButton(guibutton, "enableMiddleClick", labelMiddleClick);
                break;
            }
            case 5: {
                this.toggleBooleanButton(guibutton, "enableAutoRefill", labelAutoRefill);
                break;
            }
            case 2: {
                this.toggleBooleanButton(guibutton, "autoRefillBeforeBreak", labelAutoRefillBeforeBreak);
                break;
            }
            case 3: {
                this.toggleBooleanButton(guibutton, "enableShortcuts", labelShortcuts);
                break;
            }
            case 4: {
                this.obf.displayGuiScreen(new InvTweaksGuiShortcutsHelp(this.field_146297_k, this, this.config));
                break;
            }
            case 6: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettingsAdvanced(this.field_146297_k, this.parentScreen, this.config));
                break;
            }
            case 7: {
                this.obf.displayGuiScreen(new InvTweaksGuiModNotWorking(this.field_146297_k, this.parentScreen, this.config));
                break;
            }
            case 100: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_RULES_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.rulesfile.error", e);
                }
                break;
            }
            case 101: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_TREE_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.treefile.error", e);
                }
                break;
            }
            case 102: {
                try {
                    Desktop.getDesktop().browse(new URL("http://inventory-tweaks.readthedocs.org").toURI());
                    break;
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.onlinehelp.error", e);
                }
            }
        }
    }
}

