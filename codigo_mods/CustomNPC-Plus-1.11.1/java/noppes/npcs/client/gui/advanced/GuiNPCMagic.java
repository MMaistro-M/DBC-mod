/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.magic.MagicGetAllPacket;
import kamkeel.npcs.network.packets.request.magic.MagicNpcGetPacket;
import kamkeel.npcs.network.packets.request.magic.MagicNpcSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCMagic
extends GuiNPCInterface2
implements IScrollData,
ICustomScrollListener,
IGuiData,
ITextfieldListener {
    private GuiCustomScroll allMagicScroll;
    private GuiCustomScroll npcMagicScroll;
    private final HashMap<String, Integer> allMagic = new HashMap();
    private final MagicData npcMagicData = new MagicData();
    private String search = "";
    private GuiNpcTextField splitField;
    private GuiNpcTextField damageField;

    public GuiNPCMagic(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new MagicGetAllPacket());
        PacketClient.sendClient(new MagicNpcGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.allMagicScroll == null) {
            this.allMagicScroll = new GuiCustomScroll(this, 0);
            this.allMagicScroll.setSize(150, 150);
        }
        this.allMagicScroll.guiLeft = this.guiLeft + 20;
        this.allMagicScroll.guiTop = this.guiTop + 24;
        this.allMagicScroll.setList(new ArrayList<String>(this.allMagic.keySet()));
        this.addScroll(this.allMagicScroll);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 20, this.guiTop + 24 + 155, 150, 20, this.search));
        if (this.npcMagicScroll == null) {
            this.npcMagicScroll = new GuiCustomScroll(this, 1);
            this.npcMagicScroll.setSize(150, 150);
        }
        this.npcMagicScroll.guiLeft = this.guiLeft + 250;
        this.npcMagicScroll.guiTop = this.guiTop + 24;
        ArrayList<String> selectedList = new ArrayList<String>();
        for (String name : this.allMagic.keySet()) {
            int id = this.allMagic.get(name);
            if (!this.npcMagicData.hasMagic(id)) continue;
            selectedList.add(name);
        }
        this.npcMagicScroll.setList(selectedList);
        this.addScroll(this.npcMagicScroll);
        this.addButton(new GuiNpcButton(70, this.guiLeft + 185, this.guiTop + 90, 55, 20, ">"));
        this.addButton(new GuiNpcButton(71, this.guiLeft + 185, this.guiTop + 112, 55, 20, "<"));
        this.addButton(new GuiNpcButton(72, this.guiLeft + 185, this.guiTop + 140, 55, 20, "magic.dist"));
        this.getButton(72).setHoverText("magic.distInfo");
        int tfY = this.guiTop + 24 + 155;
        this.addLabel(new GuiNpcLabel(5002, "magic.split", this.guiLeft + 193, tfY + 5));
        this.splitField = new GuiNpcTextField(73, this, this.field_146289_q, this.guiLeft + 250, tfY, 45, 20, "");
        this.splitField.setFloatsOnly();
        this.splitField.setMinMaxDefaultFloat(-1000000.0f, 1.0E9f, 0.0f);
        this.splitField.enabled = false;
        this.addTextField(this.splitField);
        this.addLabel(new GuiNpcLabel(5003, "magic.bonus", this.guiLeft + 300, tfY + 5));
        this.damageField = new GuiNpcTextField(74, this, this.field_146289_q, this.guiLeft + 400 - 45, tfY, 45, 20, "");
        this.damageField.setFloatsOnly();
        this.damageField.setMinMaxDefaultFloat(0.0f, 1.0E9f, 0.0f);
        this.damageField.enabled = false;
        this.addTextField(this.damageField);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 70) {
            String selected;
            int id;
            if (this.allMagicScroll.hasSelected() && !this.npcMagicData.hasMagic(id = this.allMagic.get(selected = this.allMagicScroll.getSelected()).intValue())) {
                this.npcMagicData.addMagic(id, 0.0f, 0.0f);
            }
            this.updateNpcMagicSelectedList();
            this.save();
            return;
        }
        if (guibutton.field_146127_k == 71) {
            String selected;
            int id;
            if (this.npcMagicScroll.hasSelected() && this.npcMagicData.hasMagic(id = this.allMagic.get(selected = this.npcMagicScroll.getSelected()).intValue())) {
                this.npcMagicData.removeMagic(id);
            }
            this.updateNpcMagicSelectedList();
            if (this.splitField != null) {
                this.splitField.func_146180_a("");
                this.splitField.enabled = false;
            }
            if (this.damageField != null) {
                this.damageField.func_146180_a("");
                this.damageField.enabled = false;
            }
            this.save();
            return;
        }
        if (guibutton.field_146127_k == 72) {
            if (this.npcMagicData.getMagics().size() > 0) {
                String name;
                int id;
                int count = this.npcMagicData.getMagics().size();
                float stdSplit = 1.0f / (float)count;
                for (Integer key : this.npcMagicData.getMagics().keySet()) {
                    this.npcMagicData.getMagic((int)key.intValue()).split = stdSplit;
                }
                if (this.npcMagicScroll.getSelected() != null && this.splitField != null && this.npcMagicData.hasMagic(id = this.allMagic.get(name = this.npcMagicScroll.getSelected()).intValue())) {
                    this.splitField.func_146180_a(this.npcMagicData.getMagic((int)id).split + "");
                }
            }
            this.save();
            return;
        }
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.MAGIC) {
            this.allMagic.clear();
            this.allMagic.putAll(data);
            if (this.allMagicScroll != null) {
                this.allMagicScroll.setList(new ArrayList<String>(this.allMagic.keySet()));
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.npcMagicData.readToNBT(compound);
        this.func_73866_w_();
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.allMagicScroll != null) {
            this.allMagicScroll.func_73864_a(i, j, k);
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(4) != null && this.getTextField(4).func_146206_l()) {
            if (this.search.equals(this.getTextField(4).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(4).func_146179_b().toLowerCase();
            this.allMagicScroll.setList(this.getSearchList());
            this.allMagicScroll.resetScroll();
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.allMagic.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.allMagic.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    private void updateNpcMagicSelectedList() {
        ArrayList<String> selected = new ArrayList<String>();
        for (String name : this.allMagic.keySet()) {
            int id = this.allMagic.get(name);
            if (!this.npcMagicData.hasMagic(id)) continue;
            selected.add(name);
        }
        if (this.npcMagicScroll != null) {
            this.npcMagicScroll.setList(selected);
        }
    }

    @Override
    public void save() {
        PacketClient.sendClient(new MagicNpcSavePacket(this.npcMagicData));
    }

    @Override
    public void setSelected(String selected) {
        if (this.allMagicScroll != null) {
            this.allMagicScroll.setSelected(selected);
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        String name;
        int id;
        if (guiCustomScroll == this.npcMagicScroll && this.npcMagicScroll.getSelected() != null && this.npcMagicData.hasMagic(id = this.allMagic.get(name = this.npcMagicScroll.getSelected()).intValue())) {
            if (this.splitField != null) {
                this.splitField.func_146180_a(this.npcMagicData.getMagic((int)id).split + "");
                this.splitField.enabled = true;
            }
            if (this.damageField != null) {
                this.damageField.func_146180_a(this.npcMagicData.getMagic((int)id).damage + "");
                this.damageField.enabled = true;
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        String name;
        int id;
        if ((textField.id == 73 || textField.id == 74) && this.npcMagicScroll != null && this.npcMagicScroll.getSelected() != null && this.npcMagicData.hasMagic(id = this.allMagic.get(name = this.npcMagicScroll.getSelected()).intValue())) {
            if (textField.id == 73) {
                try {
                    float split;
                    this.npcMagicData.getMagic((int)id).split = split = Float.parseFloat(textField.func_146179_b());
                }
                catch (NumberFormatException split) {}
            } else if (textField.id == 74) {
                try {
                    float bonus;
                    this.npcMagicData.getMagic((int)id).damage = bonus = Float.parseFloat(textField.func_146179_b());
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            this.save();
        }
    }
}

