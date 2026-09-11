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
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.Animation;

public class SubGuiAnimationOptions
extends SubGuiInterface {
    private final Animation animation;

    public SubGuiAnimationOptions(Animation animation) {
        this.animation = animation;
        this.setBackground("menubg.png");
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(11, "animation.whileStanding", this.guiLeft + 5, this.guiTop + 36));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 80, this.guiTop + 30, 30, 20, new String[]{"gui.yes", "gui.no"}, this.animation.whileStanding ? 0 : 1));
        this.addLabel(new GuiNpcLabel(12, "animation.whileAttacking", this.guiLeft + 5, this.guiTop + 56));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 80, this.guiTop + 50, 30, 20, new String[]{"gui.yes", "gui.no"}, this.animation.whileAttacking ? 0 : 1));
        this.addLabel(new GuiNpcLabel(13, "animation.whileMoving", this.guiLeft + 5, this.guiTop + 76));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 80, this.guiTop + 70, 30, 20, new String[]{"gui.yes", "gui.no"}, this.animation.whileMoving ? 0 : 1));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        int value = ((GuiNpcButton)guibutton).getValue();
        if (guibutton.field_146127_k == 11) {
            this.animation.whileStanding = value == 0;
        } else if (guibutton.field_146127_k == 12) {
            this.animation.whileAttacking = value == 0;
        } else if (guibutton.field_146127_k == 13) {
            this.animation.whileMoving = value == 0;
        }
        this.func_73866_w_();
    }
}

