/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.HitboxData;

public class SubGuiCustomHitbox
extends SubGuiInterface
implements ITextfieldListener {
    private final HitboxData hitboxData;

    public SubGuiCustomHitbox(HitboxData hitboxData) {
        this.hitboxData = hitboxData;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "hitbox.hitbox", this.guiLeft + 5, this.guiTop + 35));
        this.addButton(new GuiNpcButtonYesNo(0, this.guiLeft + 122, this.guiTop + 30, 56, 20, this.hitboxData.isHitboxEnabled()));
        if (this.hitboxData.isHitboxEnabled()) {
            this.addLabel(new GuiNpcLabel(3, "hitbox.width", this.guiLeft + 5, this.guiTop + 57));
            this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 122, this.guiTop + 53, 50, 18, this.hitboxData.getWidthScale() + ""));
            this.getTextField((int)2).floatsOnly = true;
            this.getTextField(2).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 1.0f);
            this.addLabel(new GuiNpcLabel(4, "hitbox.height", this.guiLeft + 5, this.guiTop + 79));
            this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 122, this.guiTop + 75, 50, 18, this.hitboxData.getHeightScale() + ""));
            this.getTextField((int)3).floatsOnly = true;
            this.getTextField(3).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 1.0f);
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 82, this.guiTop + 190, 98, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.hitboxData.setHitboxEnabled(((GuiNpcButtonYesNo)button).getBoolean());
            this.func_73866_w_();
        }
        if (id == 66) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 2) {
            this.hitboxData.setWidthScale(textfield.getFloat());
        }
        if (textfield.id == 3) {
            this.hitboxData.setHeightScale(textfield.getFloat());
        }
    }
}

