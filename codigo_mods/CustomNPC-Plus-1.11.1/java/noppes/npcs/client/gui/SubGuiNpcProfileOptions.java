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
import noppes.npcs.constants.EnumProfileSync;
import noppes.npcs.controllers.data.ProfileOptions;

public class SubGuiNpcProfileOptions
extends SubGuiInterface {
    private final ProfileOptions options;

    public SubGuiNpcProfileOptions(ProfileOptions options) {
        this.options = options;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = 0;
        this.addButton(new GuiNpcButton(0, this.guiLeft + 130, this.guiTop + 10 + y, 60, 20, new String[]{"gui.no", "gui.yes"}, this.options.enableOptions ? 1 : 0));
        this.addLabel(new GuiNpcLabel(1, "profile.enableOptions", this.guiLeft + 10, this.guiTop + 17 + y));
        if (this.options.enableOptions) {
            this.addButton(new GuiNpcButton(5, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumProfileSync.values(), this.options.completeControl.ordinal()));
            this.addLabel(new GuiNpcLabel(5, "profile.completion", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(18, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumProfileSync.values(), this.options.cooldownControl.ordinal()));
            this.addLabel(new GuiNpcLabel(18, "profile.cooldown", this.guiLeft + 10, this.guiTop + 17 + y));
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 200, this.guiTop + 192, 50, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        switch (id) {
            case 0: {
                this.options.enableOptions = !this.options.enableOptions;
                break;
            }
            case 5: {
                this.options.completeControl = EnumProfileSync.valueOf(guibutton.field_146126_j);
                break;
            }
            case 18: {
                this.options.cooldownControl = EnumProfileSync.valueOf(guibutton.field_146126_j);
                break;
            }
            case 66: {
                this.close();
            }
        }
        this.func_73866_w_();
    }
}

