/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.Resistances;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.ISliderListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcResistanceProperties
extends SubGuiInterface
implements ISliderListener {
    private Resistances resistances;

    public SubGuiNpcResistanceProperties(Resistances resistances) {
        this.resistances = resistances;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "enchantment.knockback", this.guiLeft + 4, y + 5));
        this.addSlider(new GuiNpcSlider(this, 0, this.guiLeft + 94, y, (int)(this.resistances.knockback * 100.0f - 100.0f) + "%", this.resistances.knockback / 2.0f));
        this.addLabel(new GuiNpcLabel(1, "item.arrow.name", this.guiLeft + 4, (y += 22) + 5));
        this.addSlider(new GuiNpcSlider(this, 1, this.guiLeft + 94, y, (int)(this.resistances.arrow * 100.0f - 100.0f) + "%", this.resistances.arrow / 2.0f));
        this.getSlider((int)1).field_146124_l = !this.resistances.disableDamage;
        this.addLabel(new GuiNpcLabel(2, "stats.melee", this.guiLeft + 4, (y += 22) + 5));
        this.addSlider(new GuiNpcSlider(this, 2, this.guiLeft + 94, y, (int)(this.resistances.playermelee * 100.0f - 100.0f) + "%", this.resistances.playermelee / 2.0f));
        this.getSlider((int)2).field_146124_l = !this.resistances.disableDamage;
        this.addLabel(new GuiNpcLabel(3, "stats.explosion", this.guiLeft + 4, (y += 22) + 5));
        this.addSlider(new GuiNpcSlider(this, 3, this.guiLeft + 94, y, (int)(this.resistances.explosion * 100.0f - 100.0f) + "%", this.resistances.explosion / 2.0f));
        this.getSlider((int)3).field_146124_l = !this.resistances.disableDamage;
        this.addLabel(new GuiNpcLabel(21, "stats.disabledamage", this.guiLeft + 4, (y += 22) + 5));
        this.addButton(new GuiNpcButton(21, this.guiLeft + 94, y, 56, 20, new String[]{"gui.no", "gui.yes"}, this.resistances.disableDamage ? 1 : 0));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 190, this.guiTop + 190, 60, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 21) {
            this.resistances.disableDamage = ((GuiNpcButton)guibutton).getValue() == 1;
            this.func_73866_w_();
        }
        if (id == 66) {
            this.close();
        }
    }

    @Override
    public void mouseDragged(GuiNpcSlider slider) {
        slider.field_146126_j = (int)(slider.sliderValue * 200.0f - 100.0f) + "%";
    }

    @Override
    public void mousePressed(GuiNpcSlider slider) {
    }

    @Override
    public void mouseReleased(GuiNpcSlider slider) {
        if (slider.field_146127_k == 0) {
            this.resistances.knockback = slider.sliderValue * 2.0f;
        }
        if (slider.field_146127_k == 1) {
            this.resistances.arrow = slider.sliderValue * 2.0f;
        }
        if (slider.field_146127_k == 2) {
            this.resistances.playermelee = slider.sliderValue * 2.0f;
        }
        if (slider.field_146127_k == 3) {
            this.resistances.explosion = slider.sliderValue * 2.0f;
        }
    }
}

