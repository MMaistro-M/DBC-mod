/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.client.gui.global;

import java.util.HashMap;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.transport.TransportCategoriesGetPacket;
import kamkeel.npcs.network.packets.request.transport.TransportCategoryRemovePacket;
import kamkeel.npcs.network.packets.request.transport.TransportRemovePacket;
import kamkeel.npcs.network.packets.request.transport.TransportsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.GuiNPCTransportCategoryEdit;
import noppes.npcs.client.gui.mainmenu.GuiNPCGlobalMainMenu;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNPCStringSlot;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageTransporters
extends GuiNPCInterface
implements IScrollData {
    private GuiNPCStringSlot slot;
    private HashMap<String, Integer> data;
    private boolean selectCategory = true;

    public GuiNPCManageTransporters(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new TransportCategoriesGetPacket());
        this.drawDefaultBackground = false;
        this.title = "Transport Categories";
        this.data = new HashMap();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        Vector<String> list = new Vector<String>();
        this.slot = new GuiNPCStringSlot(list, this, false, 18);
        this.slot.func_148134_d(4, 5);
        this.addButton(new GuiNpcButton(0, this.field_146294_l / 2 - 100, this.field_146295_m - 52, 65, 20, "gui.add"));
        this.addButton(new GuiNpcButton(1, this.field_146294_l / 2 - 33, this.field_146295_m - 52, 65, 20, "selectServer.edit"));
        this.getButton(0).setEnabled(this.selectCategory);
        this.getButton(1).setEnabled(this.selectCategory);
        this.addButton(new GuiNpcButton(3, this.field_146294_l / 2 + 33, this.field_146295_m - 52, 65, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(2, this.field_146294_l / 2 - 100, this.field_146295_m - 31, 98, 20, "gui.open"));
        this.getButton(2).setEnabled(this.selectCategory);
        this.addButton(new GuiNpcButton(4, this.field_146294_l / 2 + 2, this.field_146295_m - 31, 98, 20, "gui.back"));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.slot.func_148128_a(i, j, f);
        super.func_73863_a(i, j, f);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 && this.selectCategory) {
            NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNPCTransportCategoryEdit(this.npc, this, "", -1));
        }
        if (id == 1) {
            if (this.slot.selected == null || this.slot.selected.isEmpty()) {
                return;
            }
            if (this.selectCategory) {
                NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNPCTransportCategoryEdit(this.npc, this, this.slot.selected, this.data.get(this.slot.selected)));
            }
        }
        if (id == 4) {
            if (this.selectCategory) {
                this.close();
                NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNPCGlobalMainMenu(this.npc));
            } else {
                this.title = "Transport Categories";
                this.selectCategory = true;
                PacketClient.sendClient(new TransportCategoriesGetPacket());
                this.func_73866_w_();
            }
        }
        if (id == 3) {
            if (this.slot.selected == null || this.slot.selected.isEmpty()) {
                return;
            }
            this.save();
            if (this.selectCategory) {
                PacketClient.sendClient(new TransportCategoryRemovePacket(this.data.get(this.slot.selected)));
            } else {
                PacketClient.sendClient(new TransportRemovePacket(this.data.get(this.slot.selected)));
            }
            this.func_73866_w_();
        }
        if (id == 2) {
            this.doubleClicked();
        }
    }

    @Override
    public void doubleClicked() {
        if (this.slot.selected == null || this.slot.selected.isEmpty()) {
            return;
        }
        if (this.selectCategory) {
            this.selectCategory = false;
            this.title = "TransportLocations";
            PacketClient.sendClient(new TransportsGetPacket(this.data.get(this.slot.selected)));
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.data = data;
        this.slot.setList(list);
    }

    @Override
    public void setSelected(String selected) {
    }
}

