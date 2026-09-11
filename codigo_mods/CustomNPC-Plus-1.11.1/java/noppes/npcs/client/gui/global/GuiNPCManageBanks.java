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
import kamkeel.npcs.network.packets.request.bank.BankGetPacket;
import kamkeel.npcs.network.packets.request.bank.BankRemovePacket;
import kamkeel.npcs.network.packets.request.bank.BankSavePacket;
import kamkeel.npcs.network.packets.request.bank.BanksGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface2;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.containers.ContainerManageBanks;
import noppes.npcs.controllers.data.Bank;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageBanks
extends GuiContainerNPCInterface2
implements IScrollData,
ICustomScrollListener,
ITextfieldListener,
IGuiData {
    private GuiCustomScroll scroll;
    private HashMap<String, Integer> data = new HashMap();
    private ContainerManageBanks container;
    private Bank bank = new Bank();
    private String selected = null;
    private String search = "";

    public GuiNPCManageBanks(EntityNPCInterface npc, ContainerManageBanks container) {
        super(npc, container);
        this.container = container;
        this.drawDefaultBackground = false;
        PacketClient.sendClient(new BanksGetPacket());
        this.setBackground("npcbanksetup.png");
        this.field_147000_g = 200;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(6, this.field_147003_i + 340, this.field_147009_r + 10, 45, 20, "gui.add"));
        this.addButton(new GuiNpcButton(7, this.field_147003_i + 340, this.field_147009_r + 32, 45, 20, "gui.remove"));
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
        }
        this.scroll.setSize(160, 180);
        this.scroll.guiLeft = this.field_147003_i + 174;
        this.scroll.guiTop = this.field_147009_r + 8;
        this.addScroll(this.scroll);
        for (int i = 0; i < 6; ++i) {
            int x = this.field_147003_i + 6;
            int y = this.field_147009_r + 36 + i * 22;
            this.addButton(new GuiNpcButton(i, x + 50, y, 80, 20, new String[]{"Can Upgrade", "Can't Upgrade", "Upgraded"}, 0));
            this.getButton(i).setEnabled(false);
        }
        this.addTextField(new GuiNpcTextField(0, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 8, this.field_147009_r + 8, 160, 16, ""));
        this.getTextField(0).func_146203_f(20);
        this.addTextField(new GuiNpcTextField(1, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 10, this.field_147009_r + 80, 16, 16, ""));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(1).func_146203_f(1);
        this.addTextField(new GuiNpcTextField(2, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 10, this.field_147009_r + 110, 16, 16, ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).func_146203_f(1);
        this.addTextField(new GuiNpcTextField(33, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 174, this.field_147009_r + 8 + 3 + 180, 160, 20, this.search));
    }

    @Override
    public void func_73863_a(int x, int y, float f) {
        super.func_73863_a(x, y, f);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 6) {
            this.save();
            this.scroll.clear();
            String name = "New";
            while (this.data.containsKey(name)) {
                name = name + "_";
            }
            Bank bank = new Bank();
            bank.name = name;
            NBTTagCompound compound = new NBTTagCompound();
            bank.writeEntityToNBT(compound);
            PacketClient.sendClient(new BankSavePacket(compound));
        } else if (button.field_146127_k == 7) {
            if (this.data.containsKey(this.scroll.getSelected())) {
                PacketClient.sendClient(new BankRemovePacket(this.data.get(this.selected)));
            }
        } else if (button.field_146127_k >= 0 && button.field_146127_k < 6) {
            this.bank.slotTypes.put(button.field_146127_k, button.getValue());
        }
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        this.field_146289_q.func_78276_b("Tab Cost", 23, 28, CustomNpcResourceListener.DefaultTextColor);
        this.field_146289_q.func_78276_b("Upg. Cost", 123, 28, CustomNpcResourceListener.DefaultTextColor);
        this.field_146289_q.func_78276_b("Start", 6, 70, CustomNpcResourceListener.DefaultTextColor);
        this.field_146289_q.func_78276_b("Max", 9, 100, CustomNpcResourceListener.DefaultTextColor);
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        Bank bank = new Bank();
        bank.readEntityFromNBT(compound);
        this.bank = bank;
        if (bank.id == -1) {
            this.getTextField(0).func_146180_a("");
            this.getTextField(1).func_146180_a("");
            this.getTextField(2).func_146180_a("");
            for (int i = 0; i < 6; ++i) {
                this.getButton(i).setDisplay(0);
                this.getButton(i).setEnabled(false);
            }
        } else {
            this.getTextField(0).func_146180_a(bank.name);
            this.getTextField(1).func_146180_a(Integer.toString(bank.startSlots));
            this.getTextField(2).func_146180_a(Integer.toString(bank.maxSlots));
            for (int i = 0; i < 6; ++i) {
                int type = 0;
                if (bank.slotTypes.containsKey(i)) {
                    type = bank.slotTypes.get(i);
                }
                this.getButton(i).setDisplay(type);
                this.getButton(i).setEnabled(true);
            }
        }
        this.setSelected(bank.name);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(33) != null && this.getTextField(33).func_146206_l()) {
            if (this.search.equals(this.getTextField(33).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(33).func_146179_b().toLowerCase();
            this.scroll.setList(this.getSearchList());
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
        String name = this.scroll.getSelected();
        this.data = data;
        this.scroll.setList(this.getSearchList());
        if (name != null) {
            this.scroll.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scroll.setSelected(selected);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.save();
            this.selected = this.scroll.getSelected();
            PacketClient.sendClient(new BankGetPacket(this.data.get(this.selected)));
        }
    }

    @Override
    public void save() {
        if (this.selected != null && this.data.containsKey(this.selected) && this.bank != null) {
            NBTTagCompound compound = new NBTTagCompound();
            this.bank.currencyInventory = this.container.bank.currencyInventory;
            this.bank.upgradeInventory = this.container.bank.upgradeInventory;
            this.bank.writeEntityToNBT(compound);
            PacketClient.sendClient(new BankSavePacket(compound));
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        if (this.bank.id != -1) {
            if (guiNpcTextField.id == 0) {
                String name = guiNpcTextField.func_146179_b();
                if (!name.isEmpty() && !this.data.containsKey(name)) {
                    String old = this.bank.name;
                    this.data.remove(this.bank.name);
                    this.bank.name = name;
                    this.data.put(this.bank.name, this.bank.id);
                    this.selected = name;
                    this.scroll.replace(old, this.bank.name);
                }
            } else if (guiNpcTextField.id == 1 || guiNpcTextField.id == 2) {
                int num = 1;
                if (!guiNpcTextField.isEmpty()) {
                    num = guiNpcTextField.getInteger();
                }
                if (num > 6) {
                    num = 6;
                }
                if (num < 0) {
                    num = 0;
                }
                if (guiNpcTextField.id == 1) {
                    this.bank.startSlots = num;
                } else if (guiNpcTextField.id == 2) {
                    this.bank.maxSlots = num;
                }
                if (this.bank.startSlots > this.bank.maxSlots) {
                    this.bank.maxSlots = this.bank.startSlots;
                }
                guiNpcTextField.func_146180_a(Integer.toString(num));
            }
        }
    }
}

