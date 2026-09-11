/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.DataStats;
import noppes.npcs.client.gui.select.GuiSoundSelection;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcRangeProperties
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener {
    private DataStats stats;
    private GuiNpcTextField soundSelected = null;

    public SubGuiNpcRangeProperties(DataStats stats) {
        this.stats = stats;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 80, y, 50, 18, this.stats.accuracy + ""));
        this.addLabel(new GuiNpcLabel(1, "stats.accuracy", this.guiLeft + 5, y + 5));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(1).setMinMaxDefault(0, 100, 90);
        this.addTextField(new GuiNpcTextField(8, this, this.field_146289_q, this.guiLeft + 200, y, 50, 18, this.stats.shotCount + ""));
        this.addLabel(new GuiNpcLabel(8, "stats.shotcount", this.guiLeft + 135, y + 5));
        this.getTextField((int)8).integersOnly = true;
        this.getTextField(8).setMinMaxDefault(1, Integer.MAX_VALUE, 1);
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 80, y += 22, 50, 18, this.stats.rangedRange + ""));
        this.addLabel(new GuiNpcLabel(2, "stats.rangedrange", this.guiLeft + 5, y + 5));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(1, 64, 2);
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 80, y += 22, 50, 18, this.stats.minDelay + ""));
        this.addLabel(new GuiNpcLabel(3, "stats.mindelay", this.guiLeft + 5, y + 5));
        this.getTextField((int)3).integersOnly = true;
        this.getTextField(3).setMinMaxDefault(1, Integer.MAX_VALUE, 20);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 200, y, 50, 18, this.stats.maxDelay + ""));
        this.addLabel(new GuiNpcLabel(4, "stats.maxdelay", this.guiLeft + 135, y + 5));
        this.getTextField((int)4).integersOnly = true;
        this.getTextField(4).setMinMaxDefault(1, Integer.MAX_VALUE, 20);
        this.addTextField(new GuiNpcTextField(6, this, this.field_146289_q, this.guiLeft + 80, y += 22, 50, 18, this.stats.burstCount + ""));
        this.addLabel(new GuiNpcLabel(6, "stats.burstcount", this.guiLeft + 5, y + 5));
        this.getTextField((int)6).integersOnly = true;
        this.getTextField(6).setMinMaxDefault(1, Integer.MAX_VALUE, 20);
        this.addTextField(new GuiNpcTextField(5, this, this.field_146289_q, this.guiLeft + 200, y, 50, 18, this.stats.fireRate + ""));
        this.addLabel(new GuiNpcLabel(5, "stats.burstspeed", this.guiLeft + 135, y + 5));
        this.getTextField((int)5).integersOnly = true;
        this.getTextField(5).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        this.addTextField(new GuiNpcTextField(7, this, this.field_146289_q, this.guiLeft + 80, y += 22, 100, 20, this.stats.fireSound));
        this.addLabel(new GuiNpcLabel(7, "stats.firesound:", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 187, y, 60, 20, "gui.select"));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 100, y += 22, 70, 20, new String[]{"stats.onShot", "stats.onStart"}, this.stats.onSoundBegin ? 1 : 0));
        this.addLabel(new GuiNpcLabel(10, "stats.firesound:", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 100, y += 22, 70, 20, new String[]{"gui.no", "gui.yes", "stats.onShot"}, (int)this.stats.aimType));
        this.addLabel(new GuiNpcLabel(9, "stats.aimWhileShooting", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 100, y += 22, 70, 20, new String[]{"stats.allowed", "stats.ignored"}, this.stats.projectileInvincibility ? 0 : 1));
        this.addLabel(new GuiNpcLabel(11, "stats.invincibility", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 190, this.guiTop + 190, 60, 20, "gui.done"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1) {
            this.stats.accuracy = textfield.getInteger();
        } else if (textfield.id == 2) {
            this.stats.rangedRange = textfield.getInteger();
        } else if (textfield.id == 3) {
            if (textfield.getInteger() > this.stats.maxDelay) {
                this.stats.minDelay = this.stats.maxDelay;
                textfield.func_146180_a(this.stats.minDelay + "");
            } else {
                this.stats.minDelay = textfield.getInteger();
            }
        } else if (textfield.id == 4) {
            if (textfield.getInteger() < this.stats.minDelay) {
                this.stats.maxDelay = this.stats.minDelay;
                textfield.func_146180_a(this.stats.maxDelay + "");
            } else {
                this.stats.maxDelay = textfield.getInteger();
            }
        } else if (textfield.id == 5) {
            this.stats.fireRate = textfield.getInteger();
        } else if (textfield.id == 6) {
            this.stats.burstCount = textfield.getInteger();
        } else if (textfield.id == 7) {
            this.stats.fireSound = textfield.func_146179_b();
        } else if (textfield.id == 8) {
            this.stats.shotCount = textfield.getInteger();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 7) {
            this.soundSelected = this.getTextField(7);
            this.setSubGui(new GuiSoundSelection(this.soundSelected.func_146179_b()));
        }
        if (id == 10) {
            boolean bl = this.stats.onSoundBegin = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (id == 66) {
            this.close();
        } else if (id == 9) {
            this.stats.aimType = (byte)((GuiNpcButton)guibutton).getValue();
        } else if (id == 11) {
            this.stats.projectileInvincibility = ((GuiNpcButton)guibutton).getValue() == 0;
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        GuiSoundSelection gss = (GuiSoundSelection)subgui;
        if (gss.selectedResource != null) {
            this.soundSelected.func_146180_a(gss.selectedResource.toString());
            this.unFocused(this.soundSelected);
            this.func_73866_w_();
        }
    }
}

