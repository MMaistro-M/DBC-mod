/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.scripted.ScriptParticle;

public class SubGuiScriptParticle
extends SubGuiInterface
implements ITextfieldListener {
    private ScriptParticle particle;

    public SubGuiScriptParticle(ScriptParticle particle) {
        this.particle = particle;
        this.setBackground("menubg.png");
        this.xSize = 350;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(1, "display.texture", this.guiLeft + 5, y));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 70, y - 5, 260, 20, this.particle.directory));
        this.addLabel(new GuiNpcLabel(2, "particle.maxAge", this.guiLeft + 5, y += 30));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 70, y - 5, 50, 20, this.particle.maxAge + ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(1, Integer.MAX_VALUE, this.particle.maxAge);
        this.addLabel(new GuiNpcLabel(3, "effect.editor.width", this.guiLeft + 5, y += 30));
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 70, y - 5, 50, 20, this.particle.width + ""));
        this.getTextField((int)3).integersOnly = true;
        this.getTextField(3).setMinMaxDefault(-1, Integer.MAX_VALUE, this.particle.width);
        this.addLabel(new GuiNpcLabel(4, "effect.editor.height", this.guiLeft + 140, y));
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 190, y - 5, 50, 20, this.particle.height + ""));
        this.getTextField((int)4).integersOnly = true;
        this.getTextField(4).setMinMaxDefault(-1, Integer.MAX_VALUE, this.particle.height);
        this.addLabel(new GuiNpcLabel(5, "particle.facePlayer", this.guiLeft + 5, (y += 30) + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 70, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.particle.facePlayer ? 1 : 0));
        this.addLabel(new GuiNpcLabel(6, "display.glow", this.guiLeft + 120, y + 5));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 170, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.particle.glows ? 1 : 0));
        this.addLabel(new GuiNpcLabel(7, "particle.noClip", this.guiLeft + 220, y + 5));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 280, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.particle.noClip ? 1 : 0));
        this.addLabel(new GuiNpcLabel(8, "model.scale", this.guiLeft + 5, (y += 30) + 5));
        this.addLabel(new GuiNpcLabel(9, "X:", this.guiLeft + 100, y + 5));
        this.addTextField(new GuiNpcTextField(8, this, this.field_146289_q, this.guiLeft + 120, y - 5, 40, 20, this.particle.scaleX1 + ""));
        this.addLabel(new GuiNpcLabel(10, "Y:", this.guiLeft + 170, y + 5));
        this.addTextField(new GuiNpcTextField(9, this, this.field_146289_q, this.guiLeft + 190, y - 5, 40, 20, this.particle.scaleY1 + ""));
        this.addButton(new GuiNpcButton(66, this.guiLeft + this.xSize - 50, this.guiTop + this.ySize - 30, 45, 20, "gui.done"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1) {
            this.particle.directory = textfield.func_146179_b();
        } else if (textfield.id == 2) {
            this.particle.maxAge = textfield.getInteger();
        } else if (textfield.id == 3) {
            this.particle.width = textfield.getInteger();
        } else if (textfield.id == 4) {
            this.particle.height = textfield.getInteger();
        } else if (textfield.id == 8) {
            float value;
            this.particle.scaleX1 = value = Float.parseFloat(textfield.func_146179_b());
            this.particle.scaleX2 = value;
        } else if (textfield.id == 9) {
            float value;
            this.particle.scaleY1 = value = Float.parseFloat(textfield.func_146179_b());
            this.particle.scaleY2 = value;
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 5) {
            boolean bl = this.particle.facePlayer = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (guibutton.field_146127_k == 6) {
            boolean bl = this.particle.glows = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (guibutton.field_146127_k == 7) {
            boolean bl = this.particle.noClip = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (guibutton.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void close() {
        GuiNpcTextField.unfocus();
        super.close();
    }
}

