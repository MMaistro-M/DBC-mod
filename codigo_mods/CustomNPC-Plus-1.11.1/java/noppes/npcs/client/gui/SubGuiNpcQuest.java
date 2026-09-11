/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.quest.QuestOpenGuiPacket;
import kamkeel.npcs.network.packets.request.quest.QuestSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.SubGuiMailmanSendSetup;
import noppes.npcs.client.gui.SubGuiNpcCommand;
import noppes.npcs.client.gui.SubGuiNpcFactionOptions;
import noppes.npcs.client.gui.SubGuiNpcPartyOptions;
import noppes.npcs.client.gui.SubGuiNpcProfileOptions;
import noppes.npcs.client.gui.SubGuiNpcTextArea;
import noppes.npcs.client.gui.global.GuiNPCManageQuest;
import noppes.npcs.client.gui.questtypes.GuiNpcQuestTypeDialog;
import noppes.npcs.client.gui.questtypes.GuiNpcQuestTypeKill;
import noppes.npcs.client.gui.questtypes.GuiNpcQuestTypeLocation;
import noppes.npcs.client.gui.questtypes.GuiNpcQuestTypeManual;
import noppes.npcs.client.gui.select.GuiQuestSelection;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiSelectionListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.gui.util.SubGuiNpcCooldownPicker;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestRepeat;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.controllers.data.Quest;

public class SubGuiNpcQuest
extends SubGuiInterface
implements ISubGuiListener,
GuiSelectionListener,
ITextfieldListener {
    public int questCategoryID;
    public Quest quest;
    private final GuiNPCManageQuest parent;
    private boolean questlogTA = false;

    public SubGuiNpcQuest(GuiNPCManageQuest parent, Quest quest, int catId) {
        this.parent = parent;
        this.quest = quest;
        this.questCategoryID = catId;
        this.setBackground("menubg.png");
        this.xSize = 360;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "gui.title", this.guiLeft + 4, this.guiTop + 8));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 36, this.guiTop + 3, 200, 20, this.quest.title));
        this.addLabel(new GuiNpcLabel(0, "ID", this.guiLeft + 238, this.guiTop + 4));
        this.addLabel(new GuiNpcLabel(2, this.quest.id + "", this.guiLeft + 238, this.guiTop + 14));
        this.addLabel(new GuiNpcLabel(3, "quest.completedtext", this.guiLeft + 7, this.guiTop + 33));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 120, this.guiTop + 28, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(4, "quest.questlogtext", this.guiLeft + 7, this.guiTop + 57));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 120, this.guiTop + 52, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(5, "quest.reward", this.guiLeft + 7, this.guiTop + 81));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 120, this.guiTop + 76, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(10, "faction.options", this.guiLeft + 180, this.guiTop + 33));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 303, this.guiTop + 28, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(11, "advMode.command", this.guiLeft + 180, this.guiTop + 57));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 303, this.guiTop + 52, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(6, "gui.type", this.guiLeft + 180, this.guiTop + 81));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 240, this.guiTop + 76, 60, 20, new String[]{"quest.item", "quest.dialog", "quest.kill", "quest.location", "quest.areakill", "quest.manual"}, this.quest.type.ordinal()));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 303, this.guiTop + 76, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(17, "party.options", this.guiLeft + 180, this.guiTop + 135));
        this.addButton(new GuiNpcButton(18, this.guiLeft + 303, this.guiTop + 130, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(33, "quest.profile", this.guiLeft + 180, this.guiTop + 159));
        this.addButton(new GuiNpcButton(33, this.guiLeft + 303, this.guiTop + 154, 50, 20, "selectServer.edit"));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 7, this.guiTop + 102, 90, 20, new String[]{"quest.npc", "quest.instant"}, this.quest.completion.ordinal()));
        if (this.quest.completerNpc.isEmpty() && this.npc != null) {
            this.quest.completerNpc = this.npc.display.name;
        }
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 104, this.guiTop + 102, 154, 20, this.quest.completerNpc));
        this.getTextField((int)2).enabled = this.quest.completion == EnumQuestCompletion.Npc;
        this.addButton(new GuiNpcButton(15, this.guiLeft + 4, this.guiTop + 130, 144, 20, "quest.next"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 150, this.guiTop + 130, 20, 20, "X"));
        if (!this.parent.nextQuestName.isEmpty()) {
            this.getButton(15).setDisplayText(this.parent.nextQuestName);
        }
        this.addButton(new GuiNpcButton(13, this.guiLeft + 4, this.guiTop + 154, 144, 20, "mailbox.setup"));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 150, this.guiTop + 154, 20, 20, "X"));
        if (!this.quest.mail.subject.isEmpty()) {
            this.getButton(13).setDisplayText(this.quest.mail.subject);
        }
        this.addLabel(new GuiNpcLabel(8, "quest.repeatable", this.guiLeft + 7, this.guiTop + 178 + 5));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 100, this.guiTop + 178, 70, 20, new String[]{"gui.no", "gui.yes", "quest.mcdaily", "quest.mcweekly", "quest.rldaily", "quest.rlweekly", "quest.mccustom", "quest.rlcustom"}, this.quest.repeat.ordinal()));
        if (this.quest.repeat == EnumQuestRepeat.MCCUSTOM || this.quest.repeat == EnumQuestRepeat.RLCUSTOM) {
            this.addButton(new GuiNpcButton(19, this.guiLeft + 175, this.guiTop + 178, 90, 20, "Set Cooldown"));
        }
        this.addButton(new GuiNpcButton(16, this.guiLeft + 303, this.guiTop + 192, 50, 20, "gui.done"));
        if (!this.quest.mail.subject.isEmpty()) {
            this.getButton(13).setDisplayText(this.quest.mail.subject);
        }
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 3 && this.quest.id >= 0) {
            this.questlogTA = false;
            this.setSubGui(new SubGuiNpcTextArea(this.quest.completeText));
        }
        if (button.field_146127_k == 4 && this.quest.id >= 0) {
            this.questlogTA = true;
            this.setSubGui(new SubGuiNpcTextArea(this.quest.logText));
        }
        if (button.field_146127_k == 5 && this.quest.id >= 0) {
            PacketClient.sendClient(new QuestOpenGuiPacket(EnumGuiType.QuestReward, this.quest.writeToNBT(new NBTTagCompound())));
        }
        if (button.field_146127_k == 6 && this.quest.id >= 0) {
            this.quest.setType(EnumQuestType.values()[button.getValue()]);
        }
        if (button.field_146127_k == 7) {
            if (this.quest.type == EnumQuestType.Item) {
                PacketClient.sendClient(new QuestOpenGuiPacket(EnumGuiType.QuestItem, this.quest.writeToNBT(new NBTTagCompound())));
            }
            if (this.quest.type == EnumQuestType.Dialog) {
                this.setSubGui(new GuiNpcQuestTypeDialog(this.npc, this.quest, this));
            }
            if (this.quest.type == EnumQuestType.Kill) {
                this.setSubGui(new GuiNpcQuestTypeKill(this.npc, this.quest, this));
            }
            if (this.quest.type == EnumQuestType.Location) {
                this.setSubGui(new GuiNpcQuestTypeLocation(this.npc, this.quest, this));
            }
            if (this.quest.type == EnumQuestType.AreaKill) {
                this.setSubGui(new GuiNpcQuestTypeKill(this.npc, this.quest, this));
            }
            if (this.quest.type == EnumQuestType.Manual) {
                this.setSubGui(new GuiNpcQuestTypeManual(this.npc, this.quest, this));
            }
        }
        if (button.field_146127_k == 8) {
            this.quest.repeat = EnumQuestRepeat.values()[button.getValue()];
            this.func_73866_w_();
        }
        if (button.field_146127_k == 9) {
            this.quest.completion = EnumQuestCompletion.values()[button.getValue()];
            boolean bl = this.getTextField((int)2).enabled = this.quest.completion == EnumQuestCompletion.Npc;
        }
        if (button.field_146127_k == 10) {
            this.setSubGui(new SubGuiNpcFactionOptions(this.quest.factionOptions));
        }
        if (button.field_146127_k == 11) {
            this.setSubGui(new SubGuiNpcCommand(this.quest.command));
        }
        if (button.field_146127_k == 12 && this.quest.id >= 0) {
            this.quest.nextQuestid = -1;
            this.parent.nextQuestName = "";
            this.func_73866_w_();
        }
        if (button.field_146127_k == 13) {
            this.setSubGui(new SubGuiMailmanSendSetup(this.quest.mail, this.getParent()));
        }
        if (button.field_146127_k == 14) {
            this.quest.mail = new PlayerMail();
            this.func_73866_w_();
        }
        if (button.field_146127_k == 15 && this.quest.id >= 0) {
            this.setSubGui(new GuiQuestSelection(this.quest.nextQuestid));
        }
        if (button.field_146127_k == 18) {
            this.setSubGui(new SubGuiNpcPartyOptions(this.quest.partyOptions));
        }
        if (button.field_146127_k == 19) {
            boolean isMCCustom = this.quest.repeat == EnumQuestRepeat.MCCUSTOM;
            this.setSubGui(new SubGuiNpcCooldownPicker(isMCCustom, this.quest.customCooldown));
        }
        if (button.field_146127_k == 33) {
            this.setSubGui(new SubGuiNpcProfileOptions(this.quest.profileOptions));
        }
        if (button.field_146127_k == 16) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (guiNpcTextField.id == 1) {
            if (this.quest.id < 0) {
                guiNpcTextField.func_146180_a("");
            } else {
                String name = guiNpcTextField.func_146179_b();
                if (name.isEmpty() || this.parent.questData.containsKey(name)) {
                    guiNpcTextField.func_146180_a(this.quest.title);
                } else if (this.quest.id >= 0) {
                    String old = this.quest.title;
                    this.parent.questData.remove(old);
                    this.quest.title = name;
                    this.parent.questData.put(this.quest.title, this.quest.id);
                    this.parent.questScroll.replace(old, this.quest.title);
                }
            }
        }
        if (guiNpcTextField.id == 2) {
            this.quest.completerNpc = guiNpcTextField.func_146179_b();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiNpcTextArea) {
            SubGuiNpcTextArea gui = (SubGuiNpcTextArea)subgui;
            if (this.questlogTA) {
                this.quest.logText = gui.text;
            } else {
                this.quest.completeText = gui.text;
            }
        } else if (subgui instanceof SubGuiNpcCommand) {
            SubGuiNpcCommand sub = (SubGuiNpcCommand)subgui;
            this.quest.command = sub.command;
            this.func_73866_w_();
        } else if (subgui instanceof SubGuiNpcCooldownPicker) {
            SubGuiNpcCooldownPicker cooldownGui = (SubGuiNpcCooldownPicker)subgui;
            this.quest.customCooldown = cooldownGui.cooldownValue;
            this.func_73866_w_();
        } else {
            this.func_73866_w_();
        }
    }

    @Override
    public void selected(int id, String name) {
        this.quest.nextQuestid = id;
        this.quest.nextQuestTitle = name;
        this.parent.nextQuestName = name;
        this.func_73866_w_();
        PacketClient.sendClient(new QuestSavePacket(this.questCategoryID, this.quest.writeToNBT(new NBTTagCompound()), false));
    }

    @Override
    public void save() {
    }
}

