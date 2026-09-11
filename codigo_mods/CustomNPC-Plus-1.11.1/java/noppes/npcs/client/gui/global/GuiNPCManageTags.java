/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.tags.TagGetPacket;
import kamkeel.npcs.network.packets.request.tags.TagRemovePacket;
import kamkeel.npcs.network.packets.request.tags.TagSavePacket;
import kamkeel.npcs.network.packets.request.tags.TagsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageTags
extends GuiNPCInterface2
implements IScrollData,
ICustomScrollListener,
ITextfieldListener,
IGuiData,
ISubGuiListener {
    private GuiCustomScroll scrollTags;
    private HashMap<String, Integer> data = new HashMap();
    private Tag tag = new Tag();
    private String selected = null;
    private String search = "";

    public GuiNPCManageTags(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new TagsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(0, this.guiLeft + 368, this.guiTop + 8, 45, 20, "gui.add"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 32, 45, 20, "gui.remove"));
        if (this.scrollTags == null) {
            this.scrollTags = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollTags.setSize(143, 185);
        }
        this.scrollTags.guiLeft = this.guiLeft + 220;
        this.scrollTags.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollTags);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 4 + 3 + 185, 143, 20, this.search));
        if (this.tag.id == -1) {
            return;
        }
        this.addTextField(new GuiNpcTextField(0, this, this.guiLeft + 40, this.guiTop + 4, 136, 20, this.tag.name));
        this.getTextField(0).func_146203_f(20);
        this.addLabel(new GuiNpcLabel(0, "gui.name", this.guiLeft + 8, this.guiTop + 9));
        this.addLabel(new GuiNpcLabel(10, "ID", this.guiLeft + 178, this.guiTop + 4));
        this.addLabel(new GuiNpcLabel(11, this.tag.id + "", this.guiLeft + 178, this.guiTop + 14));
        String color = Integer.toHexString(this.tag.color);
        while (color.length() < 6) {
            color = "0" + color;
        }
        this.addButton(new GuiNpcButton(10, this.guiLeft + 50, this.guiTop + 30, 60, 20, color));
        this.addLabel(new GuiNpcLabel(1, "gui.color", this.guiLeft + 8, this.guiTop + 35));
        this.getButton(10).setTextColor(this.tag.color);
        this.addLabel(new GuiNpcLabel(3, "faction.hidden", this.guiLeft + 8, this.guiTop + 59));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 50, this.guiTop + 54, 60, 20, new String[]{"gui.no", "gui.yes"}, this.tag.hideTag ? 1 : 0));
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollTags.resetScroll();
            this.scrollTags.setList(this.getSearchList());
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.data.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.data.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.save();
            String name = "New";
            while (this.data.containsKey(name)) {
                name = name + "_";
            }
            Tag tag = new Tag(-1, name, 65280);
            NBTTagCompound compound = new NBTTagCompound();
            tag.writeNBT(compound);
            PacketClient.sendClient(new TagSavePacket(compound));
        }
        if (button.field_146127_k == 1 && this.data.containsKey(this.scrollTags.getSelected())) {
            PacketClient.sendClient(new TagRemovePacket(this.data.get(this.selected)));
            this.scrollTags.clear();
            this.tag = new Tag();
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            boolean bl = this.tag.hideTag = button.getValue() == 1;
        }
        if (button.field_146127_k == 10) {
            this.setSubGui(new SubGuiColorSelector(this.tag.color));
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.tag = new Tag();
        this.tag.readNBT(compound);
        this.setSelected(this.tag.name);
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scrollTags.getSelected();
        this.data = data;
        this.scrollTags.setList(this.getSearchList());
        if (name != null) {
            this.scrollTags.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scrollTags.setSelected(selected);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.save();
            this.selected = this.scrollTags.getSelected();
            PacketClient.sendClient(new TagGetPacket(this.data.get(this.selected)));
        }
    }

    @Override
    public void save() {
        if (this.selected != null && this.data.containsKey(this.selected) && this.tag != null) {
            NBTTagCompound compound = new NBTTagCompound();
            this.tag.writeNBT(compound);
            PacketClient.sendClient(new TagSavePacket(compound));
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (this.tag.id == -1) {
            return;
        }
        if (guiNpcTextField.id == 0) {
            String name = guiNpcTextField.func_146179_b();
            if (!name.isEmpty() && !this.data.containsKey(name)) {
                String old = this.tag.name;
                this.data.remove(this.tag.name);
                this.tag.name = name;
                this.data.put(this.tag.name, this.tag.id);
                this.selected = name;
                this.scrollTags.replace(old, this.tag.name);
            }
        } else if (guiNpcTextField.id == 1) {
            int color = 0;
            try {
                color = Integer.parseInt(guiNpcTextField.func_146179_b(), 16);
            }
            catch (NumberFormatException e) {
                color = 0;
            }
            this.tag.color = color;
            guiNpcTextField.func_146193_g(this.tag.color);
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiColorSelector) {
            this.tag.color = ((SubGuiColorSelector)subgui).color;
            this.func_73866_w_();
        }
    }
}

