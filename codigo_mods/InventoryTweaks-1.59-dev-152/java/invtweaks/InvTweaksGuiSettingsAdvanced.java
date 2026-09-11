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
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksGuiSettingsAbstract;
import invtweaks.InvTweaksGuiTooltipButton;
import invtweaks.InvTweaksObfuscation;
import invtweaks.forge.InvTweaksMod;
import java.awt.Desktop;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Point;

public class InvTweaksGuiSettingsAdvanced
extends InvTweaksGuiSettingsAbstract {
    private static final Logger log = InvTweaks.log;
    private static final int ID_SORT_ON_PICKUP = 1;
    private static final int ID_AUTO_EQUIP_ARMOR = 2;
    private static final int ID_ENABLE_SOUNDS = 3;
    private static final int ID_CHESTS_BUTTONS = 4;
    private static final int ID_SERVER_ASSIST = 5;
    private static final int ID_EDITSHORTCUTS = 100;
    private static String labelChestButtons;
    private static String labelSortOnPickup;
    private static String labelEquipArmor;
    private static String labelEnableSounds;
    private static String labelServerAssist;

    public InvTweaksGuiSettingsAdvanced(Minecraft mc, GuiScreen parentScreen, InvTweaksConfig config) {
        super(mc, parentScreen, config);
        labelSortOnPickup = StatCollector.func_74838_a((String)"invtweaks.settings.advanced.sortonpickup");
        labelEquipArmor = StatCollector.func_74838_a((String)"invtweaks.settings.advanced.autoequip");
        labelEnableSounds = StatCollector.func_74838_a((String)"invtweaks.settings.advanced.sounds");
        labelChestButtons = StatCollector.func_74838_a((String)"invtweaks.settings.chestbuttons");
        labelServerAssist = StatCollector.func_74838_a((String)"invtweaks.settings.advanced.serverassist");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        List controlList = this.field_146292_n;
        Point p = new Point();
        int i = 0;
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(100, p.getX() + 55, this.field_146295_m / 6 + 144, StatCollector.func_74838_a((String)"invtweaks.settings.advanced.mappingsfile")));
        i += 2;
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton sortOnPickupBtn = new InvTweaksGuiTooltipButton(1, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableSortingOnPickup", labelSortOnPickup), StatCollector.func_74838_a((String)"invtweaks.settings.advanced.sortonpickup.tooltip"));
        controlList.add(sortOnPickupBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton enableSoundsBtn = new InvTweaksGuiTooltipButton(3, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableSounds", labelEnableSounds), StatCollector.func_74838_a((String)"invtweaks.settings.advanced.sounds.tooltip"));
        controlList.add(enableSoundsBtn);
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(4, p.getX(), p.getY(), this.computeBooleanButtonLabel("showChestButtons", labelChestButtons), StatCollector.func_74838_a((String)"invtweaks.settings.chestbuttons.tooltip")));
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton autoEquipArmorBtn = new InvTweaksGuiTooltipButton(2, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableAutoEquipArmor", labelEquipArmor), StatCollector.func_74838_a((String)"invtweaks.settings.advanced.autoequip.tooltip"));
        controlList.add(autoEquipArmorBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton serverAssistBtn = new InvTweaksGuiTooltipButton(5, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableServerItemSwap", labelServerAssist), StatCollector.func_74838_a((String)"invtweaks.settings.advanced.serverassist.tooltip"));
        controlList.add(serverAssistBtn);
        if (!Desktop.isDesktopSupported()) {
            for (Object o : controlList) {
                if (!InvTweaksObfuscation.isGuiButton(o)) continue;
                GuiButton button = (GuiButton)o;
                if (button.field_146127_k != 100) continue;
                button.field_146124_l = false;
            }
        }
        this.field_146292_n = controlList;
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
        int x = this.field_146294_l / 2;
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.settings.pvpwarning.pt1"), x, 40, 0x999999);
        this.func_73732_a(this.obf.getFontRenderer(), StatCollector.func_74838_a((String)"invtweaks.settings.pvpwarning.pt2"), x, 50, 0x999999);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        switch (guibutton.field_146127_k) {
            case 1: {
                this.toggleBooleanButton(guibutton, "enableSortingOnPickup", labelSortOnPickup);
                break;
            }
            case 2: {
                this.toggleBooleanButton(guibutton, "enableAutoEquipArmor", labelEquipArmor);
                break;
            }
            case 3: {
                this.toggleBooleanButton(guibutton, "enableSounds", labelEnableSounds);
                break;
            }
            case 4: {
                this.toggleBooleanButton(guibutton, "showChestButtons", labelChestButtons);
                break;
            }
            case 5: {
                this.toggleBooleanButton(guibutton, "enableServerItemSwap", labelServerAssist);
                InvTweaksMod.proxy.setServerAssistEnabled(!InvTweaks.getConfigManager().getConfig().getProperty("enableServerItemSwap").equals("false"));
                break;
            }
            case 100: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_PROPS_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.advanced.mappingsfile.error", e);
                }
                break;
            }
            case 200: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettings(this.field_146297_k, this.parentScreen, this.config));
            }
        }
    }
}

