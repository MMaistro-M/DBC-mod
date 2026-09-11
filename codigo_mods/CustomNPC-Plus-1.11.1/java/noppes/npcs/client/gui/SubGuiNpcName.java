/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.DataDisplay;
import noppes.npcs.client.gui.mainmenu.GuiNpcDisplay;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcName
extends SubGuiInterface
implements ITextfieldListener {
    private DataDisplay display;

    public SubGuiNpcName(DataDisplay display) {
        this.display = display;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        this.addButton(new GuiNpcButton(66, this.guiLeft + this.xSize - 24, y, 20, 20, "X"));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 4, y += 50, 226, 20, this.display.name));
        this.addButton(new GuiButtonBiDirectional(1, this.guiLeft + 4, y += 22, 200, 20, new String[]{"markov.roman.name", "markov.japanese.name", "markov.slavic.name", "markov.welsh.name", "markov.sami.name", "markov.oldNorse.name", "markov.ancientGreek.name", "markov.aztec.name", "markov.classicCNPCs.name", "markov.spanish.name"}, this.display.getMarkovGeneratorId()));
        this.addButton(new GuiButtonBiDirectional(2, this.guiLeft + 64, y += 22, 120, 20, new String[]{"markov.gender.either", "markov.gender.male", "markov.gender.female"}, this.display.getMarkovGender()));
        this.addLabel(new GuiNpcLabel(2, "markov.gender.name", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 4, y += 42, 70, 20, "markov.generate"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 0) {
            if (!textfield.isEmpty()) {
                this.display.setName(textfield.func_146179_b());
            } else {
                textfield.func_146180_a(this.display.getName());
            }
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 1) {
            this.display.setMarkovGeneratorId(button.getValue());
        }
        if (button.field_146127_k == 2) {
            this.display.setMarkovGender(button.getValue());
        }
        if (button.field_146127_k == 3) {
            String name = this.display.getRandomName();
            this.display.setName(name);
            this.getTextField(0).func_146180_a(name);
            ((GuiNpcDisplay)this.parent).nameText.func_146180_a(name);
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }
}

