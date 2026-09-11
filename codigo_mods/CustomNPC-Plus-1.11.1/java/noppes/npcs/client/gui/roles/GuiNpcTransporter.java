/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.roles;

import java.util.HashMap;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.transport.TransportCategoriesGetPacket;
import kamkeel.npcs.network.packets.request.transport.TransportGetLocationPacket;
import kamkeel.npcs.network.packets.request.transport.TransportSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.TransportLocation;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcTransporter
extends GuiNPCInterface2
implements IScrollData,
IGuiData {
    private GuiCustomScroll scroll;
    public TransportLocation location = new TransportLocation();
    private HashMap<String, Integer> data = new HashMap();

    public GuiNpcTransporter(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new TransportCategoriesGetPacket());
        PacketClient.sendClient(new TransportGetLocationPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        Vector<String> list = new Vector<String>();
        list.addAll(this.data.keySet());
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(143, 208);
        }
        this.scroll.guiLeft = this.guiLeft + 214;
        this.scroll.guiTop = this.guiTop + 4;
        this.addScroll(this.scroll);
        this.addLabel(new GuiNpcLabel(0, "gui.name", this.guiLeft + 4, this.field_146295_m + 8));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 3, 140, 20, this.location.name));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 4, this.guiTop + 31, new String[]{"transporter.discovered", "transporter.start", "transporter.interaction"}, this.location.type));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.location.type = button.getValue();
        }
    }

    @Override
    public void save() {
        if (!this.scroll.hasSelected()) {
            return;
        }
        String name = this.getTextField(0).func_146179_b();
        if (!name.isEmpty()) {
            this.location.name = name;
        }
        this.location.posX = this.player.field_70165_t;
        this.location.posY = this.player.field_70163_u;
        this.location.posZ = this.player.field_70161_v;
        this.location.dimension = this.player.field_71093_bK;
        int cat = this.data.get(this.scroll.getSelected());
        PacketClient.sendClient(new TransportSavePacket(cat, this.location.writeNBT()));
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.data = data;
        this.scroll.setList(list);
    }

    @Override
    public void setSelected(String selected) {
        this.scroll.setSelected(selected);
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        TransportLocation loc = new TransportLocation();
        loc.readNBT(compound);
        this.location = loc;
        this.func_73866_w_();
    }
}

