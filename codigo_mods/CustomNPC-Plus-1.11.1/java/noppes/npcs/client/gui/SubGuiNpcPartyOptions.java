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
import noppes.npcs.constants.EnumPartyExchange;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumPartyRequirements;
import noppes.npcs.controllers.data.PartyOptions;

public class SubGuiNpcPartyOptions
extends SubGuiInterface
implements ITextfieldListener {
    private final PartyOptions options;

    public SubGuiNpcPartyOptions(PartyOptions options) {
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
        this.addButton(new GuiNpcButton(0, this.guiLeft + 130, this.guiTop + 10 + y, 60, 20, new String[]{"gui.no", "gui.yes"}, this.options.allowParty ? 1 : 0));
        this.addLabel(new GuiNpcLabel(1, "party.allowParty", this.guiLeft + 10, this.guiTop + 17 + y));
        if (this.options.allowParty) {
            this.addButton(new GuiNpcButton(24, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, new String[]{"gui.no", "gui.yes"}, this.options.onlyParty ? 1 : 0));
            this.addLabel(new GuiNpcLabel(24, "party.only", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(5, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumPartyRequirements.values(), this.options.partyRequirements.ordinal()));
            this.addLabel(new GuiNpcLabel(6, "party.partyRequirements", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(18, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumPartyObjectives.values(), this.options.objectiveRequirement.ordinal()));
            this.addLabel(new GuiNpcLabel(19, "quest.objectives", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(10, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumPartyExchange.values(), this.options.rewardControl.ordinal()));
            this.addLabel(new GuiNpcLabel(11, "quest.reward", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(15, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumPartyExchange.values(), this.options.completeFor.ordinal()));
            this.addLabel(new GuiNpcLabel(16, "party.completeFor", this.guiLeft + 10, this.guiTop + 17 + y));
            this.addButton(new GuiNpcButton(25, this.guiLeft + 130, this.guiTop + 10 + (y += 23), 60, 20, EnumPartyExchange.values(), this.options.executeCommand.ordinal()));
            this.addLabel(new GuiNpcLabel(25, "party.commandFor", this.guiLeft + 10, this.guiTop + 17 + y));
            GuiNpcTextField minField = new GuiNpcTextField(21, this, this.guiLeft + 60, this.guiTop + 10 + (y += 23), 30, 20, "" + this.options.minPartySize);
            minField.integersOnly = true;
            minField.setMinMaxDefault(1, Integer.MAX_VALUE, 1);
            this.addTextField(minField);
            this.addLabel(new GuiNpcLabel(21, "party.minPartySize", this.guiLeft + 10, this.guiTop + 17 + y));
            GuiNpcTextField maxField = new GuiNpcTextField(20, this, this.guiLeft + 160, this.guiTop + 10 + y, 30, 20, "" + this.options.maxPartySize);
            maxField.integersOnly = true;
            maxField.setMinMaxDefault(1, Integer.MAX_VALUE, 4);
            this.addTextField(maxField);
            this.addLabel(new GuiNpcLabel(20, "party.maxPartySize", this.guiLeft + 110, this.guiTop + 17 + y));
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 200, this.guiTop + 192, 50, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        switch (id) {
            case 0: {
                this.options.allowParty = !this.options.allowParty;
                break;
            }
            case 5: {
                this.options.partyRequirements = EnumPartyRequirements.valueOf(guibutton.field_146126_j);
                break;
            }
            case 10: {
                this.options.rewardControl = EnumPartyExchange.valueOf(guibutton.field_146126_j);
                break;
            }
            case 15: {
                this.options.completeFor = EnumPartyExchange.valueOf(guibutton.field_146126_j);
                break;
            }
            case 18: {
                this.options.objectiveRequirement = EnumPartyObjectives.valueOf(guibutton.field_146126_j);
                break;
            }
            case 24: {
                this.options.onlyParty = !this.options.onlyParty;
                break;
            }
            case 25: {
                this.options.executeCommand = EnumPartyExchange.valueOf(guibutton.field_146126_j);
                break;
            }
            case 66: {
                this.close();
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 20) {
            this.options.setMaxPartySize(textfield.getInteger());
            this.func_73866_w_();
        }
        if (textfield.id == 21) {
            this.options.setMinPartySize(textfield.getInteger());
            this.func_73866_w_();
        }
    }
}

