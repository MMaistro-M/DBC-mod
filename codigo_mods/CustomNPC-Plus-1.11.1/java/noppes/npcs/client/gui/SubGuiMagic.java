/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.magic.MagicSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.global.GuiNpcManageMagic;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumTextureType;
import noppes.npcs.controllers.data.Magic;

public class SubGuiMagic
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener,
ICustomScrollListener {
    public GuiNpcManageMagic parent;
    private GuiCustomScroll allMagic;
    private GuiCustomScroll interactionsScroll;
    private Magic magic;
    private String search = "";
    private GuiNpcTextField interactionField;
    private String selectedInteraction;
    private HashMap<String, Integer> interactionNames = new HashMap();
    private HashMap<String, Float> interactionValues = new HashMap();

    public SubGuiMagic(GuiNpcManageMagic parent, Magic magic) {
        this.magic = magic;
        this.parent = parent;
        this.setBackground("menubg.png");
        this.xSize = 360;
        this.ySize = 216;
        this.closeOnEsc = true;
        this.processInteractions();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 6;
        this.addLabel(new GuiNpcLabel(1, "gui.name", this.guiLeft + 4, y + 5));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.magic.name));
        this.addLabel(new GuiNpcLabel(-10, "ID", this.guiLeft + 200 + 80 + 5, y + 2));
        this.addLabel(new GuiNpcLabel(-11, this.magic.id + "", this.guiLeft + 200 + 80 + 5, y + 12));
        this.addLabel(new GuiNpcLabel(2, "gui.displayName", this.guiLeft + 4, (y += 25) + 5));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.magic.displayName));
        String color = Integer.toHexString(this.magic.color);
        while (color.length() < 6) {
            color = 0 + color;
        }
        this.addButton(new GuiNpcButton(12, this.guiLeft + 80 + 5 + 200, y, 65, 20, color));
        this.getButton(12).setTextColor(this.magic.color);
        this.addLabel(new GuiNpcLabel(3, "display.texture", this.guiLeft + 4, (y += 25) + 5));
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.magic.iconTexture));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 80 + 5 + 200, y, 65, 20, EnumTextureType.names(), this.magic.type.ordinal()));
        if (this.allMagic == null) {
            this.allMagic = new GuiCustomScroll(this, 0);
            this.allMagic.setSize(150, 107);
        }
        this.allMagic.guiLeft = this.guiLeft + 5;
        this.allMagic.guiTop = this.guiTop + 80;
        this.addScroll(this.allMagic);
        this.addTextField(new GuiNpcTextField(34, this, this.field_146289_q, this.guiLeft + 5, this.guiTop + 30 + 160, 150, 20, this.search));
        this.allMagic.setList(this.getSearchList());
        if (this.interactionsScroll == null) {
            this.interactionsScroll = new GuiCustomScroll(this, 1);
            this.interactionsScroll.setSize(150, 107);
        }
        this.interactionsScroll.guiLeft = this.guiLeft + 200;
        this.interactionsScroll.guiTop = this.guiTop + 80;
        this.interactionsScroll.setList(new ArrayList<String>(this.interactionNames.keySet()));
        this.addScroll(this.interactionsScroll);
        this.addLabel(new GuiNpcLabel(50, "magic.interaction", this.guiLeft + 170, this.guiTop + 30 + 160 + 5));
        this.interactionField = new GuiNpcTextField(90, this, this.field_146289_q, this.guiLeft + 245, this.guiTop + 30 + 160, 45, 20, "");
        this.interactionField.setFloatsOnly();
        this.interactionField.setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
        this.addTextField(this.interactionField);
        if (this.selectedInteraction != null) {
            this.interactionField.func_146180_a(this.interactionValues.get(this.selectedInteraction) + "");
        } else {
            this.interactionField.enabled = false;
        }
        this.addButton(new GuiNpcButton(60, this.guiLeft + 160, this.guiTop + 90, 30, 20, ">"));
        this.addButton(new GuiNpcButton(61, this.guiLeft + 160, this.guiTop + 112, 30, 20, "<"));
        this.addButton(new GuiNpcButton(62, this.guiLeft + 160, this.guiTop + 140, 30, 20, ">>"));
        this.addButton(new GuiNpcButton(63, this.guiLeft + 160, this.guiTop + 162, 30, 20, "<<"));
        this.addButton(new GuiNpcButton(99, this.guiLeft + 303, this.guiTop + 30 + 160, 50, 20, "gui.done"));
    }

    @Override
    public void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 99) {
            this.close();
        }
        if (button.field_146127_k == 13) {
            this.magic.type = EnumTextureType.values()[((GuiNpcButton)button).getValue()];
        }
        if (button.field_146127_k == 12) {
            this.setSubGui(new SubGuiColorSelector(this.magic.color));
        }
        if (button.field_146127_k == 60 && this.allMagic.hasSelected() && !this.interactionNames.containsKey(this.allMagic.getSelected()) && this.parent.magicData.containsKey(this.allMagic.getSelected())) {
            this.interactionNames.put(this.allMagic.getSelected(), this.parent.magicData.get(this.allMagic.getSelected()));
            this.interactionValues.put(this.allMagic.getSelected(), Float.valueOf(0.0f));
            this.interactionsScroll.list.add(this.allMagic.getSelected());
        }
        if (button.field_146127_k == 61 && this.interactionsScroll.hasSelected()) {
            this.interactionNames.remove(this.interactionsScroll.getSelected());
            this.interactionValues.remove(this.interactionsScroll.getSelected());
            this.interactionsScroll.list.remove(this.interactionsScroll.selected);
            this.interactionsScroll.selected = -1;
            this.interactionField.enabled = false;
            this.interactionField.func_146180_a("");
        }
        if (button.field_146127_k == 62) {
            for (String name : this.parent.magicData.keySet()) {
                if (this.interactionNames.containsKey(name)) continue;
                this.interactionNames.put(name, this.parent.magicData.get(name));
                this.interactionValues.put(name, Float.valueOf(0.0f));
            }
            this.interactionsScroll.setList(new ArrayList<String>(this.interactionNames.keySet()));
        }
        if (button.field_146127_k == 63) {
            this.interactionNames.clear();
            this.interactionValues.clear();
            this.interactionsScroll.setList(new ArrayList<String>());
            this.interactionsScroll.selected = -1;
            this.interactionField.enabled = false;
            this.interactionField.func_146180_a("");
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (guiNpcTextField.id == 1) {
            if (this.magic.id < 0) {
                guiNpcTextField.func_146180_a("");
            } else {
                String name = guiNpcTextField.func_146179_b();
                if (name.isEmpty() || this.parent.magicData.containsKey(name)) {
                    guiNpcTextField.func_146180_a(this.magic.name);
                } else if (this.magic.id >= 0) {
                    String old = this.magic.name;
                    this.parent.magicData.remove(old);
                    this.magic.name = name;
                    this.parent.magicData.put(this.magic.name, this.magic.id);
                    this.parent.rightScroll.replace(old, this.magic.name);
                }
            }
        }
        if (guiNpcTextField.id == 2) {
            this.magic.displayName = guiNpcTextField.func_146179_b();
        }
        if (guiNpcTextField.id == 3) {
            this.magic.iconTexture = guiNpcTextField.func_146179_b();
        }
        if (guiNpcTextField.id == 90 && this.selectedInteraction != null) {
            this.interactionValues.put(this.selectedInteraction, Float.valueOf(guiNpcTextField.getFloat()));
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.allMagic != null) {
            this.allMagic.func_73864_a(i, j, k);
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(34) != null && this.getTextField(34).func_146206_l()) {
            if (this.search.equals(this.getTextField(34).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(34).func_146179_b().toLowerCase();
            this.allMagic.setList(this.getSearchList());
            this.allMagic.resetScroll();
        }
    }

    private List<String> getSearchList() {
        ArrayList<String> original = new ArrayList<String>(this.parent.magicData.keySet());
        if (this.search.isEmpty()) {
            return original;
        }
        ArrayList<String> filtered = new ArrayList<String>();
        for (String name : original) {
            if (!name.toLowerCase().contains(this.search)) continue;
            filtered.add(name);
        }
        return filtered;
    }

    public void processInteractions() {
        this.interactionNames.clear();
        this.interactionValues.clear();
        for (int id : this.magic.interactions.keySet()) {
            for (String name : this.parent.magicData.keySet()) {
                if (this.parent.magicData.get(name) != id) continue;
                this.interactionNames.put(name, id);
                this.interactionValues.put(name, this.magic.interactions.get(id));
            }
        }
    }

    public void setInteractions() {
        this.magic.interactions.clear();
        for (String name : this.interactionNames.keySet()) {
            this.magic.interactions.put(this.interactionNames.get(name), this.interactionValues.get(name));
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiColorSelector) {
            this.magic.color = ((SubGuiColorSelector)subgui).color;
            this.func_73866_w_();
        }
    }

    @Override
    public void close() {
        this.setInteractions();
        NBTTagCompound compound = new NBTTagCompound();
        this.magic.writeNBT(compound);
        PacketClient.sendClient(new MagicSavePacket(compound));
        super.close();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 1) {
            GuiNpcTextField.unfocus();
            this.selectedInteraction = this.interactionsScroll.getSelected();
            this.interactionField.enabled = true;
            this.interactionField.func_146180_a(this.interactionValues.get(this.selectedInteraction) + "");
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }
}

