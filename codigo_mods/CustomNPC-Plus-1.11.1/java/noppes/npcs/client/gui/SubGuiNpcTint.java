/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.TintData;

public class SubGuiNpcTint
extends SubGuiInterface
implements ISubGuiListener,
ITextfieldListener {
    private final TintData tintData;
    private int lastColorClicked = -1;

    public SubGuiNpcTint(TintData tintData) {
        this.tintData = tintData;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = 30;
        this.addLabel(new GuiNpcLabel(0, "tint.enable", this.guiLeft + 5, this.guiTop + y + 5));
        this.addButton(new GuiNpcButtonYesNo(0, this.guiLeft + 122, this.guiTop + y, 56, 20, this.tintData.isTintEnabled()));
        y += 22;
        if (this.tintData.isTintEnabled()) {
            this.addLabel(new GuiNpcLabel(1, "tint.enablegeneral", this.guiLeft + 5, this.guiTop + y + 5));
            this.addButton(new GuiNpcButtonYesNo(1, this.guiLeft + 122, this.guiTop + y, 56, 20, this.tintData.isGeneralTintEnabled()));
            y += 22;
            if (this.tintData.isGeneralTintEnabled()) {
                String color = Integer.toHexString(this.tintData.getGeneralTint());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addLabel(new GuiNpcLabel(2, "tint.tint", this.guiLeft + 4, this.guiTop + y + 5));
                this.addButton(new GuiNpcButton(2, this.guiLeft + 122, this.guiTop + y, 60, 20, color));
                this.getButton(2).setTextColor(this.tintData.getGeneralTint());
                this.addLabel(new GuiNpcLabel(3, "tint.alpha", this.guiLeft + 5, this.guiTop + (y += 22) + 5));
                this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 122, this.guiTop + y, 60, 20, this.tintData.getGeneralAlpha() + ""));
                this.getTextField((int)3).integersOnly = true;
                this.getTextField(3).setMinMaxDefault(1, 100, 40);
                y += 22;
            }
            this.addLabel(new GuiNpcLabel(4, "tint.enablehurt", this.guiLeft + 5, this.guiTop + y + 5));
            this.addButton(new GuiNpcButtonYesNo(4, this.guiLeft + 122, this.guiTop + y, 56, 20, this.tintData.isHurtTintEnabled()));
            y += 22;
            if (this.tintData.isHurtTintEnabled()) {
                String color2 = Integer.toHexString(this.tintData.getHurtTint());
                while (color2.length() < 6) {
                    color2 = 0 + color2;
                }
                this.addLabel(new GuiNpcLabel(5, "tint.hurt", this.guiLeft + 4, this.guiTop + y + 5));
                this.addButton(new GuiNpcButton(5, this.guiLeft + 122, this.guiTop + y, 60, 20, color2));
                this.getButton(5).setTextColor(this.tintData.getHurtTint());
            }
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 82, this.guiTop + 190, 98, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (id == 0) {
            this.tintData.setTintEnabled(((GuiNpcButtonYesNo)button).getBoolean());
            this.func_73866_w_();
        }
        if (id == 1) {
            this.tintData.setGeneralTintEnabled(((GuiNpcButtonYesNo)button).getBoolean());
            this.func_73866_w_();
        }
        if (id == 4) {
            this.tintData.setHurtTintEnabled(((GuiNpcButtonYesNo)button).getBoolean());
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            this.setSubGui(new SubGuiColorSelector(this.tintData.getGeneralTint()));
            this.lastColorClicked = 0;
        }
        if (button.field_146127_k == 5) {
            this.setSubGui(new SubGuiColorSelector(this.tintData.getHurtTint()));
            this.lastColorClicked = 1;
        }
        if (id == 66) {
            this.close();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (this.lastColorClicked == 0) {
            this.tintData.setGeneralTint(((SubGuiColorSelector)subgui).color);
        } else if (this.lastColorClicked == 1) {
            this.tintData.setHurtTint(((SubGuiColorSelector)subgui).color);
        }
        this.func_73866_w_();
        this.save();
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 3) {
            this.tintData.setGeneralAlpha(textfield.getInteger());
        }
    }
}

