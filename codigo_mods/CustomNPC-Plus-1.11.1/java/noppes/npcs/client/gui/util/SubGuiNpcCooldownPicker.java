/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcCooldownPicker
extends SubGuiInterface
implements ITextfieldListener {
    private final boolean isMCCustom;
    public long cooldownValue;
    private GuiNpcTextField daysField;
    private GuiNpcTextField hoursField;
    private GuiNpcTextField minutesField;
    private GuiNpcTextField secondsField;

    public SubGuiNpcCooldownPicker(boolean isMCCustom, long currentCooldown) {
        this.isMCCustom = isMCCustom;
        this.cooldownValue = currentCooldown;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 222;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (this.isMCCustom) {
            long currentMillis = this.cooldownValue * 50L;
            days = (int)(currentMillis / 86400000L);
            long remainder = currentMillis % 86400000L;
            hours = (int)(remainder / 3600000L);
            minutes = (int)((remainder %= 3600000L) / 60000L);
            seconds = (int)((remainder %= 60000L) / 1000L);
        } else {
            days = (int)(this.cooldownValue / 86400000L);
            long remainder = this.cooldownValue % 86400000L;
            hours = (int)(remainder / 3600000L);
            minutes = (int)((remainder %= 3600000L) / 60000L);
            seconds = (int)((remainder %= 60000L) / 1000L);
        }
        int y = this.guiTop + 20;
        this.addLabel(new GuiNpcLabel(1, "mailbox.days", this.guiLeft + 10, y));
        this.daysField = new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + this.xSize - 110, y - 5, 40, 20, "" + days);
        this.daysField.setIntegersOnly();
        this.daysField.setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        this.addTextField(this.daysField);
        this.addLabel(new GuiNpcLabel(2, "mailbox.hours", this.guiLeft + 10, y += 30));
        this.hoursField = new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + this.xSize - 110, y - 5, 40, 20, "" + hours);
        this.hoursField.setIntegersOnly();
        this.hoursField.setMinMaxDefault(0, 24, 0);
        this.addTextField(this.hoursField);
        this.addLabel(new GuiNpcLabel(3, "mailbox.minutes", this.guiLeft + 10, y += 30));
        this.minutesField = new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + this.xSize - 110, y - 5, 40, 20, "" + minutes);
        this.minutesField.setIntegersOnly();
        this.minutesField.setMinMaxDefault(0, 60, 0);
        this.addTextField(this.minutesField);
        this.addLabel(new GuiNpcLabel(4, "mailbox.seconds", this.guiLeft + 10, y += 30));
        this.secondsField = new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + this.xSize - 110, y - 5, 40, 20, "" + seconds);
        this.secondsField.setIntegersOnly();
        this.secondsField.setMinMaxDefault(0, 60, 0);
        this.addTextField(this.secondsField);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, this.guiTop + this.ySize - 30, 80, 20, "gui.done"));
    }

    @Override
    public void buttonEvent(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.close();
        }
    }

    @Override
    public void close() {
        int inputDays = this.parseInt(this.daysField.func_146179_b());
        int inputHours = this.parseInt(this.hoursField.func_146179_b());
        int inputMinutes = this.parseInt(this.minutesField.func_146179_b());
        int inputSeconds = this.parseInt(this.secondsField.func_146179_b());
        long totalMillis = (long)inputDays * 86400000L + (long)inputHours * 3600000L + (long)inputMinutes * 60000L + (long)inputSeconds * 1000L;
        this.cooldownValue = this.isMCCustom ? totalMillis / 50L : totalMillis;
        super.close();
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
    }

    private int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        }
        catch (Exception e) {
            return 0;
        }
    }
}

