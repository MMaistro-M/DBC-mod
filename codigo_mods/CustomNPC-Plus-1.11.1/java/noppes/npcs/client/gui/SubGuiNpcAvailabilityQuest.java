/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.packets.request.quest.QuestGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.select.GuiQuestSelection;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiSelectionListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumAvailabilityQuest;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.Quest;

public class SubGuiNpcAvailabilityQuest
extends SubGuiInterface
implements GuiSelectionListener,
IGuiData {
    private Availability availabitily;
    private int slot = 0;

    public SubGuiNpcAvailabilityQuest(Availability availabitily) {
        this.availabitily = availabitily;
        this.setBackground("menubg.png");
        this.xSize = 316;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "availability.available", this.guiLeft, this.guiTop + 4));
        this.getLabel(1).center(this.xSize);
        int y = this.guiTop + 12;
        this.addButton(new GuiNpcButton(0, this.guiLeft + 4, y, 90, 20, new String[]{"availability.always", "availability.after", "availability.before", "availability.whenactive", "availability.whennotactive", "availability.acceptable", "availability.notacceptable"}, this.availabitily.questAvailable.ordinal()));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 96, y, 192, 20, "availability.selectquest"));
        this.getButton(10).setEnabled(this.availabitily.questAvailable != EnumAvailabilityQuest.Always);
        this.addButton(new GuiNpcButton(20, this.guiLeft + 290, y, 20, 20, "X"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 4, y += 23, 90, 20, new String[]{"availability.always", "availability.after", "availability.before", "availability.whenactive", "availability.whennotactive", "availability.acceptable", "availability.notacceptable"}, this.availabitily.quest2Available.ordinal()));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 96, y, 192, 20, "availability.selectquest"));
        this.getButton(11).setEnabled(this.availabitily.quest2Available != EnumAvailabilityQuest.Always);
        this.addButton(new GuiNpcButton(21, this.guiLeft + 290, y, 20, 20, "X"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 4, y += 23, 90, 20, new String[]{"availability.always", "availability.after", "availability.before", "availability.whenactive", "availability.whennotactive", "availability.acceptable", "availability.notacceptable"}, this.availabitily.quest3Available.ordinal()));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 96, y, 192, 20, "availability.selectquest"));
        this.getButton(12).setEnabled(this.availabitily.quest3Available != EnumAvailabilityQuest.Always);
        this.addButton(new GuiNpcButton(22, this.guiLeft + 290, y, 20, 20, "X"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 4, y += 23, 90, 20, new String[]{"availability.always", "availability.after", "availability.before", "availability.whenactive", "availability.whennotactive", "availability.acceptable", "availability.notacceptable"}, this.availabitily.quest4Available.ordinal()));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 96, y, 192, 20, "availability.selectquest"));
        this.getButton(13).setEnabled(this.availabitily.quest4Available != EnumAvailabilityQuest.Always);
        this.addButton(new GuiNpcButton(23, this.guiLeft + 290, y, 20, 20, "X"));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 82, this.guiTop + 192, 98, 20, "gui.done"));
        this.updateGuiButtons();
    }

    private void updateGuiButtons() {
        this.getButton(10).setDisplayText("availability.selectquest");
        this.getButton(11).setDisplayText("availability.selectquest");
        this.getButton(12).setDisplayText("availability.selectquest");
        this.getButton(13).setDisplayText("availability.selectquest");
        if (this.availabitily.questId >= 0) {
            QuestGetPacket.getQuest(this.availabitily.questId);
        }
        if (this.availabitily.quest2Id >= 0) {
            QuestGetPacket.getQuest(this.availabitily.quest2Id);
        }
        if (this.availabitily.quest3Id >= 0) {
            QuestGetPacket.getQuest(this.availabitily.quest3Id);
        }
        if (this.availabitily.quest4Id >= 0) {
            QuestGetPacket.getQuest(this.availabitily.quest4Id);
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.availabitily.questAvailable = EnumAvailabilityQuest.values()[button.getValue()];
            if (this.availabitily.questAvailable == EnumAvailabilityQuest.Always) {
                this.availabitily.questId = -1;
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 1) {
            this.availabitily.quest2Available = EnumAvailabilityQuest.values()[button.getValue()];
            if (this.availabitily.quest2Available == EnumAvailabilityQuest.Always) {
                this.availabitily.quest2Id = -1;
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            this.availabitily.quest3Available = EnumAvailabilityQuest.values()[button.getValue()];
            if (this.availabitily.quest3Available == EnumAvailabilityQuest.Always) {
                this.availabitily.quest3Id = -1;
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            this.availabitily.quest4Available = EnumAvailabilityQuest.values()[button.getValue()];
            if (this.availabitily.quest4Available == EnumAvailabilityQuest.Always) {
                this.availabitily.quest4Id = -1;
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 10) {
            this.slot = 1;
            this.setSubGui(new GuiQuestSelection(this.availabitily.questId));
        }
        if (button.field_146127_k == 11) {
            this.slot = 2;
            this.setSubGui(new GuiQuestSelection(this.availabitily.quest2Id));
        }
        if (button.field_146127_k == 12) {
            this.slot = 3;
            this.setSubGui(new GuiQuestSelection(this.availabitily.quest3Id));
        }
        if (button.field_146127_k == 13) {
            this.slot = 4;
            this.setSubGui(new GuiQuestSelection(this.availabitily.quest4Id));
        }
        if (button.field_146127_k == 20) {
            this.availabitily.questId = -1;
            this.getButton(10).setDisplayText("availability.selectquest");
        }
        if (button.field_146127_k == 21) {
            this.availabitily.quest2Id = -1;
            this.getButton(11).setDisplayText("availability.selectquest");
        }
        if (button.field_146127_k == 22) {
            this.availabitily.quest3Id = -1;
            this.getButton(12).setDisplayText("availability.selectquest");
        }
        if (button.field_146127_k == 23) {
            this.availabitily.quest4Id = -1;
            this.getButton(13).setDisplayText("availability.selectquest");
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void selected(int id, String name) {
        if (this.slot == 1) {
            this.availabitily.questId = id;
        }
        if (this.slot == 2) {
            this.availabitily.quest2Id = id;
        }
        if (this.slot == 3) {
            this.availabitily.quest3Id = id;
        }
        if (this.slot == 4) {
            this.availabitily.quest4Id = id;
        }
        this.updateGuiButtons();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        Quest quest = new Quest();
        quest.readNBT(compound);
        if (this.availabitily.questId == quest.id) {
            this.getButton(10).setDisplayText(quest.title);
        }
        if (this.availabitily.quest2Id == quest.id) {
            this.getButton(11).setDisplayText(quest.title);
        }
        if (this.availabitily.quest3Id == quest.id) {
            this.getButton(12).setDisplayText(quest.title);
        }
        if (this.availabitily.quest4Id == quest.id) {
            this.getButton(13).setDisplayText(quest.title);
        }
    }
}

