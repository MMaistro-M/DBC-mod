/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.faction.FactionGetPacket;
import kamkeel.npcs.network.packets.request.faction.FactionRemovePacket;
import kamkeel.npcs.network.packets.request.faction.FactionSavePacket;
import kamkeel.npcs.network.packets.request.faction.FactionsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.SubGuiNpcFactionPoints;
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
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageFactions
extends GuiNPCInterface2
implements IScrollData,
ICustomScrollListener,
ITextfieldListener,
IGuiData,
ISubGuiListener,
GuiYesNoCallback {
    private GuiCustomScroll scrollFactions;
    private HashMap<String, Integer> data = new HashMap();
    private Faction faction = new Faction();
    private String selected = null;
    private String search = "";

    public GuiNPCManageFactions(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new FactionsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(0, this.guiLeft + 368, this.guiTop + 8, 45, 20, "gui.add"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 32, 45, 20, "gui.remove"));
        if (this.scrollFactions == null) {
            this.scrollFactions = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollFactions.setSize(143, 185);
        }
        this.scrollFactions.guiLeft = this.guiLeft + 220;
        this.scrollFactions.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollFactions);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 4 + 3 + 185, 143, 20, this.search));
        if (this.faction.id == -1) {
            return;
        }
        this.addTextField(new GuiNpcTextField(0, this, this.guiLeft + 40, this.guiTop + 4, 136, 20, this.faction.name));
        this.getTextField(0).func_146203_f(20);
        this.addLabel(new GuiNpcLabel(0, "gui.name", this.guiLeft + 8, this.guiTop + 9));
        this.addLabel(new GuiNpcLabel(10, "ID", this.guiLeft + 178, this.guiTop + 4));
        this.addLabel(new GuiNpcLabel(11, this.faction.id + "", this.guiLeft + 178, this.guiTop + 14));
        String color = Integer.toHexString(this.faction.color);
        while (color.length() < 6) {
            color = "0" + color;
        }
        this.addButton(new GuiNpcButton(10, this.guiLeft + 40, this.guiTop + 26, 60, 20, color));
        this.addLabel(new GuiNpcLabel(1, "gui.color", this.guiLeft + 8, this.guiTop + 31));
        this.getButton(10).setTextColor(this.faction.color);
        this.addLabel(new GuiNpcLabel(2, "faction.points", this.guiLeft + 105, this.guiTop + 31));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 156, this.guiTop + 26, 45, 20, "selectServer.edit"));
        int y = this.guiTop + 48;
        this.addLabel(new GuiNpcLabel(3, "faction.hidden", this.guiLeft + 8, y + 5));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 100, y, 45, 20, new String[]{"gui.no", "gui.yes"}, this.faction.hideFaction ? 1 : 0));
        this.getButton(3).setHoverText("faction.hidden.hover");
        this.addLabel(new GuiNpcLabel(4, "faction.attacked", this.guiLeft + 8, (y += 23) + 5));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 100, y, 45, 20, new String[]{"gui.no", "gui.yes"}, this.faction.getsAttacked ? 1 : 0));
        this.getButton(4).setHoverText("faction.attacked.hover");
        this.addLabel(new GuiNpcLabel(5, "faction.passive", this.guiLeft + 8, (y += 23) + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 100, y, 45, 20, new String[]{"gui.no", "gui.yes"}, this.faction.isPassive ? 1 : 0));
        this.getButton(5).setHoverText("faction.passive.hover");
        this.addLabel(new GuiNpcLabel(6, "faction.hostiles", this.guiLeft + 8, this.guiTop + 120));
        ArrayList<String> hostileList = new ArrayList<String>(this.scrollFactions.getList());
        hostileList.remove(this.faction.name);
        HashSet<String> set = new HashSet<String>();
        for (String s : this.data.keySet()) {
            if (s.equals(this.faction.name) || !this.faction.attackFactions.contains(this.data.get(s))) continue;
            set.add(s);
        }
        GuiCustomScroll scrollHostileFactions = new GuiCustomScroll((GuiScreen)this, 1, true);
        scrollHostileFactions.setSize(163, 78);
        scrollHostileFactions.guiLeft = this.guiLeft + 4;
        scrollHostileFactions.guiTop = this.guiTop + 134;
        scrollHostileFactions.setList(hostileList);
        scrollHostileFactions.setSelectedList(set);
        this.addScroll(scrollHostileFactions);
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
            Faction faction = new Faction(-1, name, 65280, 1000);
            NBTTagCompound compound = new NBTTagCompound();
            faction.writeNBT(compound);
            PacketClient.sendClient(new FactionSavePacket(compound));
        }
        if (button.field_146127_k == 1 && this.data.containsKey(this.scrollFactions.getSelected())) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.scrollFactions.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 1);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (button.field_146127_k == 2) {
            this.setSubGui(new SubGuiNpcFactionPoints(this.faction));
        }
        if (button.field_146127_k == 3) {
            boolean bl = this.faction.hideFaction = button.getValue() == 1;
        }
        if (button.field_146127_k == 4) {
            boolean bl = this.faction.getsAttacked = button.getValue() == 1;
        }
        if (button.field_146127_k == 5) {
            boolean bl = this.faction.isPassive = button.getValue() == 1;
        }
        if (button.field_146127_k == 10) {
            this.setSubGui(new SubGuiColorSelector(this.faction.color));
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.faction = new Faction();
        this.faction.readNBT(compound);
        this.setSelected(this.faction.name);
        this.func_73866_w_();
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollFactions.resetScroll();
            this.scrollFactions.setList(this.getSearchList());
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
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scrollFactions.getSelected();
        this.data = data;
        this.scrollFactions.setList(this.getSearchList());
        if (name != null) {
            this.scrollFactions.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scrollFactions.setSelected(selected);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.save();
            this.selected = this.scrollFactions.getSelected();
            FactionGetPacket.getFaction(this.data.get(this.selected));
        } else if (guiCustomScroll.id == 1) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (String s : guiCustomScroll.getSelectedList()) {
                if (!this.data.containsKey(s)) continue;
                set.add(this.data.get(s));
            }
            this.faction.attackFactions = set;
            this.save();
        }
    }

    @Override
    public void save() {
        if (this.selected != null && this.data.containsKey(this.selected) && this.faction != null) {
            NBTTagCompound compound = new NBTTagCompound();
            this.faction.writeNBT(compound);
            PacketClient.sendClient(new FactionSavePacket(compound));
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (this.faction.id == -1) {
            return;
        }
        if (guiNpcTextField.id == 0) {
            String name = guiNpcTextField.func_146179_b();
            if (!name.isEmpty() && !this.data.containsKey(name)) {
                String old = this.faction.name;
                this.data.remove(this.faction.name);
                this.faction.name = name;
                this.data.put(this.faction.name, this.faction.id);
                this.selected = name;
                this.scrollFactions.replace(old, this.faction.name);
            }
        } else if (guiNpcTextField.id == 1) {
            int color = 0;
            try {
                color = Integer.parseInt(guiNpcTextField.func_146179_b(), 16);
            }
            catch (NumberFormatException e) {
                color = 0;
            }
            this.faction.color = color;
            guiNpcTextField.func_146193_g(this.faction.color);
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiColorSelector) {
            this.faction.color = ((SubGuiColorSelector)subgui).color;
            this.func_73866_w_();
        }
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 1 && this.data.containsKey(this.scrollFactions.getSelected())) {
            PacketClient.sendClient(new FactionRemovePacket(this.data.get(this.selected)));
            this.scrollFactions.clear();
            this.faction = new Faction();
            this.func_73866_w_();
        }
    }
}

