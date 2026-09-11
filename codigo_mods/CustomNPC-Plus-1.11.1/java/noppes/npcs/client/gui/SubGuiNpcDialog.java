/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.dialog.DialogSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiMailmanSendSetup;
import noppes.npcs.client.gui.SubGuiNpcAvailability;
import noppes.npcs.client.gui.SubGuiNpcCommand;
import noppes.npcs.client.gui.SubGuiNpcDialogOptions;
import noppes.npcs.client.gui.SubGuiNpcDialogVisual;
import noppes.npcs.client.gui.SubGuiNpcFactionOptions;
import noppes.npcs.client.gui.SubGuiNpcTextArea;
import noppes.npcs.client.gui.global.GuiNPCManageDialogs;
import noppes.npcs.client.gui.player.GuiDialogInteract;
import noppes.npcs.client.gui.select.GuiQuestSelection;
import noppes.npcs.client.gui.select.GuiSoundSelection;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiSelectionListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.entity.EntityDialogNpc;

public class SubGuiNpcDialog
extends SubGuiInterface
implements ISubGuiListener,
GuiSelectionListener,
ITextfieldListener {
    public int dialogCategoryID;
    public Dialog dialog;
    private final GuiNPCManageDialogs parent;

    public SubGuiNpcDialog(GuiNPCManageDialogs parent, Dialog dialog, int catId) {
        this.parent = parent;
        this.dialog = dialog;
        this.dialogCategoryID = catId;
        this.setBackground("menubg.png");
        this.xSize = 360;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "gui.title", this.guiLeft + 4, this.guiTop + 8));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 36, this.guiTop + 3, 200, 20, this.dialog.title));
        this.addLabel(new GuiNpcLabel(0, "ID", this.guiLeft + 238, this.guiTop + 4));
        this.addLabel(new GuiNpcLabel(2, this.dialog.id + "", this.guiLeft + 238, this.guiTop + 14));
        this.addLabel(new GuiNpcLabel(3, "dialog.dialogtext", this.guiLeft + 7, this.guiTop + 33));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 120, this.guiTop + 28, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(6, "dialog.options", this.guiLeft + 7, this.guiTop + 57));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 120, this.guiTop + 52, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(11, "dialog.visualOption", this.guiLeft + 7, this.guiTop + 81));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 120, this.guiTop + 76, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(9, "gui.selectSound", this.guiLeft + 7, this.guiTop + 105));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 90, this.guiTop + 100, 194, 20, this.dialog.sound));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 293, this.guiTop + 100, 60, 20, "gui.select"));
        this.addLabel(new GuiNpcLabel(4, "availability.options", this.guiLeft + 180, this.guiTop + 33));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 303, this.guiTop + 28, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(5, "faction.options", this.guiLeft + 180, this.guiTop + 57));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 303, this.guiTop + 52, 50, 20, "selectServer.edit"));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 4, this.guiTop + 130, 144, 20, "availability.selectquest"));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 150, this.guiTop + 130, 20, 20, "X"));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 4, this.guiTop + 154, 144, 20, "mailbox.setup"));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 150, this.guiTop + 154, 20, 20, "X"));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 303, this.guiTop + 130, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(10, "advMode.command", this.guiLeft + 180, this.guiTop + 130 + 5));
        this.addButton(new GuiNpcButtonYesNo(15, this.guiLeft + 303, this.guiTop + 154, this.dialog.disableEsc));
        this.addLabel(new GuiNpcLabel(15, "dialog.disableEsc", this.guiLeft + 180, this.guiTop + 154 + 5));
        this.addButton(new GuiNpcButton(16, this.guiLeft + 303, this.guiTop + 192, 50, 20, "gui.done"));
        this.addButton(new GuiNpcButton(17, this.guiLeft + 303 - 55, this.guiTop + 192, 50, 20, "gui.test"));
        if (!this.parent.dialogQuestName.equals("")) {
            this.getButton(7).setDisplayText(this.parent.dialogQuestName);
        }
        if (!this.dialog.mail.subject.isEmpty()) {
            this.getButton(13).setDisplayText(this.dialog.mail.subject);
        }
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 3 && this.dialog.id >= 0) {
            this.setSubGui(new SubGuiNpcTextArea(this.dialog.text));
        }
        if (id == 4 && this.dialog.id >= 0) {
            this.setSubGui(new SubGuiNpcAvailability(this.dialog.availability));
        }
        if (id == 5 && this.dialog.id >= 0) {
            this.setSubGui(new SubGuiNpcFactionOptions(this.dialog.factionOptions));
        }
        if (id == 6 && this.dialog.id >= 0) {
            this.setSubGui(new SubGuiNpcDialogOptions(this.dialog));
        }
        if (id == 7 && this.dialog.id >= 0) {
            this.setSubGui(new GuiQuestSelection(this.dialog.quest));
        }
        if (id == 8 && this.dialog.id >= 0) {
            this.dialog.quest = -1;
            this.parent.dialogQuestName = "";
            this.func_73866_w_();
        }
        if (id == 9 && this.dialog.id >= 0) {
            this.setSubGui(new GuiSoundSelection(this.getTextField(2).func_146179_b()));
        }
        if (id == 10) {
            this.setSubGui(new SubGuiNpcCommand(this.dialog.command));
        }
        if (id == 11) {
            this.setSubGui(new SubGuiNpcDialogVisual(this.dialog));
        }
        if (id == 13) {
            this.setSubGui(new SubGuiMailmanSendSetup(this.dialog.mail, this.getParent()));
        }
        if (id == 14) {
            this.dialog.mail = new PlayerMail();
            this.func_73866_w_();
        }
        if (id == 15 && guibutton instanceof GuiNpcButton) {
            boolean bl = this.dialog.disableEsc = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (id == 16) {
            this.close();
        }
        if (id == 17) {
            EntityDialogNpc npc = new EntityDialogNpc(this.player.field_70170_p);
            npc.display.name = "TEST";
            EntityUtil.Copy((EntityLivingBase)this.player, (EntityLivingBase)npc);
            GuiDialogInteract gui = new GuiDialogInteract(this.getParent(), npc, this.dialog);
            NoppesUtil.openGUI((EntityPlayer)this.player, gui);
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (guiNpcTextField.id == 1) {
            if (this.dialog.id < 0) {
                guiNpcTextField.func_146180_a("");
            } else {
                String name = guiNpcTextField.func_146179_b();
                if (name.isEmpty() || this.parent.dialogData.containsKey(name)) {
                    guiNpcTextField.func_146180_a(this.dialog.title);
                } else if (this.dialog.id >= 0) {
                    String old = this.dialog.title;
                    this.parent.dialogData.remove(old);
                    this.dialog.title = name;
                    this.parent.dialogData.put(this.dialog.title, this.dialog.id);
                    this.parent.dialogScroll.replace(old, this.dialog.title);
                }
            }
        }
        if (guiNpcTextField.id == 2) {
            this.dialog.sound = guiNpcTextField.func_146179_b();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiNpcTextArea) {
            SubGuiNpcTextArea gui = (SubGuiNpcTextArea)subgui;
            this.dialog.text = gui.text;
        } else if (subgui instanceof SubGuiNpcCommand) {
            this.dialog.command = ((SubGuiNpcCommand)subgui).command;
        } else if (subgui instanceof GuiQuestSelection) {
            GuiQuestSelection gqs = (GuiQuestSelection)subgui;
            if (gqs.selectedQuest != null) {
                this.dialog.quest = gqs.selectedQuest.id;
                this.func_73866_w_();
            }
        } else if (subgui instanceof SubGuiMailmanSendSetup) {
            this.func_73866_w_();
        } else if (subgui instanceof GuiSoundSelection) {
            GuiSoundSelection gss = (GuiSoundSelection)subgui;
            if (gss.selectedResource != null) {
                this.getTextField(2).func_146180_a(gss.selectedResource.toString());
                this.unFocused(this.getTextField(2));
                this.func_73866_w_();
            }
        }
    }

    @Override
    public void selected(int ob, String name) {
        this.dialog.quest = ob;
        this.parent.dialogQuestName = name;
        this.func_73866_w_();
        PacketClient.sendClient(new DialogSavePacket(this.dialogCategoryID, this.dialog.writeToNBT(new NBTTagCompound()), false));
    }

    @Override
    public void save() {
    }
}

